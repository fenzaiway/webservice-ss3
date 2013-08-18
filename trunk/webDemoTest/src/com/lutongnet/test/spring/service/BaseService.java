/**
 * File	Name   : BaseService.java
 * Package     : org.com.lutongnet.jfl.service
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-20
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.test.spring.util.PaginationBean;

/**
 * @author 凌剑�?
 */
@Transactional
@Repository
@Service ( "baseService" )
public class BaseService {

	/**
	 * 注入JPA实体管理者，注意要显示设置unitName，方便以后多数据源操�?
	 */
	@PersistenceContext ( unitName = "default" )
	protected EntityManager	em;

	/**
	 * 分页
	 * 
	 * @param <T>
	 * @param countHQL
	 * @param dataHQL
	 * @param params
	 * @param current
	 * @param pageSize
	 * @param clazz
	 * @return
	 */
	@Transactional ( propagation = Propagation.SUPPORTS )
	public <T> PaginationBean findByPage ( String countHQL , String dataHQL , Map<String, Object> params , int current , int pageSize , Class<T> clazz ) {
		TypedQuery<Number> q1 = em.createQuery ( countHQL , Number.class );
		TypedQuery<T> q2 = em.createQuery ( dataHQL , clazz );
		for ( Map.Entry<String, Object> entry : params.entrySet ( ) ){
			q1.setParameter ( entry.getKey ( ) , entry.getValue ( ) );
			q2.setParameter ( entry.getKey ( ) , entry.getValue ( ) );
		}
		int rowCount = q1.getSingleResult ( ).intValue ( );
		PaginationBean pb = new PaginationBean ( current , rowCount , pageSize );
		List<T> dataList = q2.setFirstResult ( pb.getStart ( ) ).setMaxResults ( pb.getPageSize ( ) ).getResultList ( );
		pb.setDataList ( dataList );
		return pb;
	}

	/**
	 * 根据属�?判断是否存在相同的实�?
	 * 
	 * @param <T>
	 * @param propertyName
	 * @param propertyValue
	 * @param id
	 * @param clazz
	 * @return
	 */
	@Transactional ( propagation = Propagation.SUPPORTS )
	public <T> PaginationBean findByPage ( String countHQL , String dataHQL , Map<String, Object> params , int current , int pageSize) {
		Query query = em.createNativeQuery(countHQL);
		Query query1 = em.createNativeQuery(dataHQL);
		if(null != params)
		{
		    for ( Map.Entry<String, Object> entry : params.entrySet ( ) ){
		        query.setParameter(entry.getKey ( ), entry.getValue ( ));
		        query1.setParameter(entry.getKey ( ), entry.getValue ( ));
		    }
		}
		int rowCount = Integer.parseInt(query.getSingleResult ( ).toString());
		PaginationBean pb = new PaginationBean ( current , rowCount , pageSize );
		List<T> dataList = query1.setFirstResult ( pb.getStart ( ) ).setMaxResults ( pb.getPageSize ( ) ).getResultList ( );
		pb.setDataList ( dataList );
		return pb;
	}

	/**
	 * 根据属�?判断是否存在相同的实�?
	 * 
	 * @param <T>
	 * @param propertyName
	 * @param propertyValue
	 * @param id
	 * @param clazz
	 * @return
	 */
	@Transactional ( propagation = Propagation.SUPPORTS )
	public <T> boolean existsByProperty ( String propertyName , String propertyValue , Integer id , Class<T> clazz ) {
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object> ( );
		if ( id == null ){
			// 新增
			hql = "from " + clazz.getCanonicalName ( ) + " where " + propertyName + "=:" + propertyName;
			params.put ( propertyName , propertyValue );
		}else{
			hql = "from " + clazz.getCanonicalName ( ) + " where " + propertyName + "=:" + propertyName + " and id!=:id";
			params.put ( propertyName , propertyValue );
			params.put ( "id" , id );
		}
		Query q = em.createQuery ( hql , clazz );
		for ( Map.Entry<String, Object> entry : params.entrySet ( ) ){
			q.setParameter ( entry.getKey ( ) , entry.getValue ( ) );
		}

		return q.getResultList ( ).size ( ) > 0;
	}

	/**
	 * 根据名称判断是否存在相同的实体，分新增和修改两种情况
	 * 
	 * @param name
	 * @param id
	 * @param clazz
	 * @return
	 */
	@Transactional ( propagation = Propagation.SUPPORTS )
	public <T> boolean existsByName ( String name , Integer id , Class<T> clazz ) {
		return existsByProperty ( "name" , name , id , clazz );
	}
	
	@Transactional ( propagation = Propagation.SUPPORTS )
	public <T> List<T> queryBySQL ( String countSQL , Map<String, Object> params )
	{
	    
	    Query query = em.createNativeQuery(countSQL);
	    if(null != params && !params.isEmpty())
	    {
	        for ( Map.Entry<String, Object> entry : params.entrySet ( ) ){
	            query.setParameter(entry.getKey ( ), entry.getValue ( ));
	        }
	    }
        List<T> dataList = query.getResultList();
        return dataList;
	}
}
