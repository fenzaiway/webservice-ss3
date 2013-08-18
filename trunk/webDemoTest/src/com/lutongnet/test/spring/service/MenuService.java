/**
 * File	Name   : MenuService.java
 * Package     : org.com.lutongnet.jfl.service
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lutongnet.test.spring.entity.Menu;
import com.lutongnet.test.spring.req.MenuReq;
import com.lutongnet.test.spring.util.PaginationBean;

/**
 * @author 凌剑东
 */
@Transactional
@Repository
@Service ( "menuService" )
public class MenuService extends BaseService {

    /**
     * 查询菜单树
     * 
     * @param req
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public PaginationBean list ( MenuReq req ) {
        String countHQL = "select count(id) from Menu";
        String dataHQL = "from Menu";
        String condition = " where parent is null";
        String keyword = req.getKeyword ( );
        Map<String, Object> params = new HashMap<String, Object> ( );
        if ( StringUtils.hasText ( keyword ) ){
            condition += " and (lower(name) like :name or lower(description) like :description)";
            params.put ( "name" , "%" + keyword.toLowerCase ( ) + "%" );
            params.put ( "description" , "%" + keyword.toLowerCase ( ) + "%" );
        }
        return findByPage ( countHQL + condition , dataHQL + condition , params , req.getCurrent ( ) , req.getPageSize ( ) , Menu.class );
    }

    /**
     * 获取菜单信息
     * 
     * @param id
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public Menu get ( Integer id ) {
        return em.find ( Menu.class , id );
    }

    /**
     * 添加菜单树、菜单、节点
     * 
     * @param menu
     * @return
     */
    public Menu add ( Menu menu ) {
        if ( menu.getParent ( ) != null ){
            String hql = "select max(position)+1 from Menu where parent.id=:pid";
            Integer position = em.createQuery ( hql , Integer.class ).setParameter ( "pid" , menu.getParent ( ).getId ( ) ).getSingleResult ( );
            if ( position == null ){
                position = 0;
            }
            menu.setPosition ( position );
        }

        em.persist ( menu );
        return menu;
    }

    /**
     * 判断在同一父节点下是否存在相同的节点名
     * 
     * @param menu
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public boolean existsInParent ( Menu menu ) {
        String hql = "from Menu where name=:name and parent.id=:pid";
        List<?> dataList = em.createQuery ( hql ).setParameter ( "name" , menu.getName ( ) ).setParameter ( "pid" , menu.getParent ( ).getId ( ) )
                .getResultList ( );
        return dataList.size ( ) > 0;
    }

    /**
     * 更新时判断是否存在相同的节点名称
     * 
     * @param menu
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public boolean existsInParentWhenUpdate ( Menu menu ) {
        String hql = "from Menu where name=:name and parent.id=:pid and id!=:id";
        List<?> dataList = em.createQuery ( hql ).setParameter ( "name" , menu.getName ( ) ).setParameter ( "pid" , menu.getParent ( ).getId ( ) )
                .setParameter ( "id" , menu.getId ( ) ).getResultList ( );
        return dataList.size ( ) > 0;
    }

    /**
     * 删除菜单
     * 
     * @param id
     */
    public void remove ( Integer id ) {
        Menu menu = em.find ( Menu.class , id );
        em.remove ( menu );
    }

    /**
     * 移动菜单
     * 
     * @param id
     * @param pid
     */
    public void move ( Integer id , Integer pid , Integer prevId , Integer nextId ) {
        System.out.println ( id + "," + pid + "," + prevId + "," + nextId );
        Menu menu = em.find ( Menu.class , id );
        Menu parent = em.find ( Menu.class , pid );
        if ( prevId != null && nextId != null ){
            // 放到中间
            Menu prev = em.find ( Menu.class , prevId );
            int prevPosition = 0;
            for ( Menu child : parent.getChildren ( ) ){
                child.setPosition ( 2 * child.getPosition ( ) );
                if ( child.getId ( ) == prev.getId ( ) ){
                    prevPosition = child.getPosition ( );
                }
            }
            menu.setPosition ( prevPosition + 1 );
            menu.setParent ( parent );
            parent.getChildren ( ).add ( menu );
        }else if ( prevId == null && nextId == null ){
            // 放到节点下
            int i = 0;
            for ( Menu child : parent.getChildren ( ) ){
                if ( child.getId ( ) != id ){
                    child.setPosition ( i++ );
                }
            }
            menu.setPosition ( i );
            menu.setParent ( parent );
            parent.getChildren ( ).add ( menu );
        }else if ( prevId == null ){
            // 放到最前面
            menu.setParent ( parent );
            parent.getChildren ( ).add ( menu );
            menu.setPosition ( 0 );
            int i = 1;
            for ( Menu child : parent.getChildren ( ) ){
                if ( child.getId ( ) != id ){
                    child.setPosition ( i++ );
                }
            }
        }else if ( nextId == null ){
            // 放到最后面
            int i = 0;
            for ( Menu child : parent.getChildren ( ) ){
                if ( child.getId ( ) != id ){
                    child.setPosition ( i++ );
                }
            }
            menu.setPosition ( i );
            menu.setParent ( parent );
            parent.getChildren ( ).add ( menu );
        }
    }

    /**
     * 更新菜单
     * 
     * @param menu
     */
    public void update ( Menu menu ) {
        String hql = "update Menu set name=:name, uri=:uri, style=:style where id=:id";
        em.createQuery ( hql ).setParameter ( "name" , menu.getName ( ) ).setParameter ( "uri" , menu.getUri ( ) ).setParameter ( "id" , menu.getId ( ) )
                .setParameter ( "style" , menu.getStyle ( ) ).executeUpdate ( );
    }

    /**
     * 获取一个节点和子节点
     * 
     * @param id
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public Menu getNode ( Integer id ) {
        String hql = "from Menu m left join fetch m.children where m.id=" + id;
        List<Menu> nodeList = em.createQuery ( hql , Menu.class ).getResultList ( );
        if ( nodeList.size ( ) > 0 ){
            return nodeList.get ( 0 );
        }else{
            return null;
        }
    }

    /**
     * 获取整棵树
     * 
     * @param id
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public Menu getTree ( Integer id ) {
        Menu root = getNode ( id );
        for ( Menu node : root.getChildren ( ) ){
            getTree ( node.getId ( ) );
        }
        return root;
    }

    /**
     * 根据菜单树名获取整棵树
     * 
     * @param name
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public Menu getTreeByName ( String name ) {
        String hql = "from Menu where name=:name";
        Menu tree = null;
        try{
            tree = em.createQuery ( hql , Menu.class ).setParameter ( "name" , name ).getSingleResult ( );
        }catch ( NoResultException e ){
        }
        if ( tree != null ){
            tree = getTree ( tree.getId ( ) );
        }
        return tree;
    }

    /**
     * 获取所有树根节点
     * 
     * @return
     */
    @Transactional ( propagation = Propagation.SUPPORTS )
    public List<Menu> getAllTree ( ) {
        String hql = "from Menu where parent is null";
        return em.createQuery ( hql , Menu.class ).getResultList ( );
    }

    /**
     * 更新菜单树描述
     * 
     * @param id
     * @param description
     */
    public void updateDescription ( Integer id , String description ) {
        String hql = "update Menu set description=:description where id=" + id;
        em.createQuery ( hql ).setParameter ( "description" , description ).executeUpdate ( );
    }
}