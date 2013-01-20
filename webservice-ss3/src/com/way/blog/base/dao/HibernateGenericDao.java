package com.way.blog.base.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.*;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.hibernate.*;

import org.hibernate.criterion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import blog.log.SetNullDemo;

import com.way.blog.util.GenericsUtils;
import com.way.blog.util.PaginationSupport;


/** *//**

* 泛型Hibernate DAO类

*

* @author DigitalSonic

*/

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class HibernateGenericDao<T, ID extends Serializable> extends HibernateDaoSupport implements IHibernateGenericDao<T, ID>{

    private Class<T> pojoClass;

   

    /** *//**

     * 初始化DAO，获取POJO类型

     */

    public HibernateGenericDao(){

//        this.pojoClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        this.pojoClass = GenericsUtils.getSuperClassGenricType(getClass());

    }
    
    @Autowired
	public void setSessionFactory0(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
   
    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#getPojoClass()
	 */

    public Class<T> getPojoClass(){

        return this.pojoClass;

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#getPojoClassName()
	 */

    public String getPojoClassName(){

        return getPojoClass().getName();

    }

    //加载对象

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#loadAll()
	 */

    @Transactional(readOnly=true)
    public List<T> loadAll(){

        return (List<T>)getHibernateTemplate().loadAll(getPojoClass());

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#find(java.lang.String, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public List find(String hql, Object... values){

        return getHibernateTemplate().find(hql, values);

    }

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findByCriteria(org.hibernate.Criteria)
	 */
    @Transactional(readOnly=true)
    public List<T> findByCriteria(final Criteria criteria){

        List list = criteria.list();

        return transformResults(list);

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
    @Transactional(readOnly=true)
    public List<T> findByCriteria(final DetachedCriteria detachedCriteria){

        return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){

               public Object doInHibernate(Session session) throws HibernateException {

                   Criteria criteria = detachedCriteria.getExecutableCriteria(session);

                   List list = criteria.list();

                   return transformResults(list);

               }

           });

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findByExample(T)
	 */
    @Transactional(readOnly=true)
    public List<T> findByExample(T instance){

        List<T> results = (List<T>)getHibernateTemplate().findByExample(instance);

        return results;

    }   

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findById(ID)
	 */
    @Transactional(readOnly=true)
    public T findById(Serializable id){
    	T t = (T) getHibernateTemplate().get(getPojoClassName(), id);
    	if(!Hibernate.isInitialized(t)){
    		Hibernate.initialize(t);
    	}
        return t;

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public List<T> findByProperty(String propertyName, Object value){

       String queryString = "from " + getPojoClassName() + " as model where model."

                               + propertyName + "= ?";

       return (List<T>)getHibernateTemplate().find(queryString, value);

    }

   

    //新建、修改、删除

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#save(T)
	 */
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public int save(T transientInstance){
        return (Integer) getHibernateTemplate().save(transientInstance);

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#update(T)
	 */
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public void update(T object){

        getHibernateTemplate().update(object);

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#delete(ID)
	 */
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public void delete(Serializable id){

           T instance = findById(id);

           if (instance != null)

               getHibernateTemplate().delete(instance);

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#delete(T)
	 */
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public void delete(T persistentInstance){

        getHibernateTemplate().delete(persistentInstance);

    }   

   

    //分页

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByCriteria(org.hibernate.Criteria, int, int)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByCriteria(final Criteria criteria, final int pageSize, final int startIndex){   

        int totalCount = getCountByCriteria(criteria);   

        criteria.setProjection(null);

        List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();

        items = transformResults(items);

        PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);

        return ps;

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByCriteria(org.hibernate.Criteria)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByCriteria(final Criteria criteria){   

        return findPageByCriteria(criteria, PaginationSupport.PAGESIZE, 0);   

    }   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByCriteria(org.hibernate.Criteria, int)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByCriteria(final Criteria criteria, final int startIndex){   

        return findPageByCriteria(criteria, PaginationSupport.PAGESIZE, startIndex);   

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#getCountByCriteria(org.hibernate.Criteria)
	 */
    @Transactional(readOnly=true)
    public int getCountByCriteria(final Criteria criteria){   

        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();   

        return count.intValue();   

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex){   

        return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback(){   

            public Object doInHibernate(Session session) throws HibernateException{   

                Criteria criteria = detachedCriteria.getExecutableCriteria(session);

                int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();   

                criteria.setProjection(null);

                List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();

                items = transformResults(items);

                PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);   

                return ps;   

            }

        });   

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria){   

        return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, 0);   

    }   

  

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int startIndex){   

        return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, startIndex);   

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#getCountByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
    @Transactional(readOnly=true)
    public int getCountByCriteria(final DetachedCriteria detachedCriteria){   

        Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback(){   

            public Object doInHibernate(Session session) throws HibernateException{   

                Criteria criteria = detachedCriteria.getExecutableCriteria(session);   

                return criteria.setProjection(Projections.rowCount()).uniqueResult();   

            }   

        });   

        return count.intValue();   

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByQuery(java.lang.String, int, int, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByQuery(final String hql, final int pageSize, final int startIndex, Object...values){

        int totalCount = getCountByQuery(hql, values);

        if (totalCount < 1)

            return new PaginationSupport(new ArrayList(0), 0);

        Query query = createQuery(hql, values);
        
        List items = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

        PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);

        return ps;

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByQuery(java.lang.String, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByQuery(final String hql, Object...values){   

        return findPageByQuery(hql, PaginationSupport.PAGESIZE, 0, values);   

    }   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#findPageByQuery(java.lang.String, int, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public PaginationSupport findPageByQuery(final String hql, final int startIndex, Object...values){   

        return findPageByQuery(hql, PaginationSupport.PAGESIZE, startIndex, values);  

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#getCountByQuery(java.lang.String, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public int getCountByQuery(final String hql, Object...values){   

        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));

        List countlist = getHibernateTemplate().find(countQueryString, values);
        return Integer.parseInt(countlist.get(0)+"");

    }

   

    //创建Criteria和Query

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#createCriteria(org.hibernate.criterion.Criterion)
	 */
    @Transactional(readOnly=true)
    public Criteria createCriteria(Criterion...criterions){

        Criteria criteria = getSession().createCriteria(getPojoClass());

        for (Criterion c : criterions)

            criteria.add(c);

        return criteria;

    }

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#createCriteria(java.lang.String, boolean, org.hibernate.criterion.Criterion)
	 */
    @Transactional(readOnly=true)
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion...criterions){

        Criteria criteria = createCriteria(criterions);

        if (isAsc)

            criteria.addOrder(Order.asc(orderBy));

        else

            criteria.addOrder(Order.desc(orderBy));

        return criteria;

    }

   

    /** *//* (non-Javadoc)
	 * @see com.way.ssh.dao.base.IHibernateGenericDao#createQuery(java.lang.String, java.lang.Object)
	 */
    @Transactional(readOnly=true)
    public Query createQuery(String hql, Object... values){

        Query query = getSession().createQuery(hql);
        
        for (int i = 0; i < values.length; i++){

            query.setParameter(i, values[i]);

        }

        return query;

    }

   

    /** *//**

     * 方法取自SpringSide.

     * 去除hql的select子句，未考虑union的情况

     */

    private static String removeSelect(String hql){

        int beginPos = hql.toLowerCase().indexOf("from");

        return hql.substring(beginPos);

    }

    /** *//**

     * 方法取自SpringSide.

     * 去除hql的orderby子句

     */

    private static String removeOrders(String hql){

        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(hql);

        StringBuffer sb = new StringBuffer();

        while (m.find()){

            m.appendReplacement(sb, "");

        }

        m.appendTail(sb);

        return sb.toString();

    }

   

    /** *//**

     * 将联合查询的结果内容从Map或者Object[]转换为实体类型，如果没有转换必要则直接返回

     */

    private List transformResults(List items){

        if (items.size() > 0){

            if (items.get(0) instanceof Map){

                ArrayList list = new ArrayList(items.size());

                for (int i = 0; i < items.size(); i++){

                    Map map = (Map)items.get(i);

                    list.add(map.get(CriteriaSpecification.ROOT_ALIAS));

                }

                return list;

            } else if (items.get(0) instanceof Object[]){

                ArrayList list = new ArrayList(items.size());

                int pos = 0;

                for (int i = 0; i < ((Object[])items.get(0)).length; i++){

                    if (((Object[])items.get(0)).getClass() == getPojoClass()){

                        pos = i;

                        break;

                    }

                }

                for (int i = 0; i < items.size(); i++){

                    list.add(((Object[])items.get(i))[pos]);

                }

                return list;

            } else

                return items;

        } else

            return items;

    }
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public void saveAll(Collection<T> entities){
    	this.getHibernateTemplate().saveOrUpdateAll(entities);
    }

	public void saveOrUpdate(T object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	//==========================设置list中对象的某些字段为null开始========================///
	/**
	 * @param list 要设置的list
	 * @param value 设置的值
	 * @param values 要设置null的字段
	 * @return 经过设置变量后的list
	 */
	public  List<T> setNull(List<T> list, Object value, String...values){
		
		List<T> returnList = new ArrayList<T>();
		for(T t : list){
			for(String fieldName : values){
				SetNullDemo.invokeSet(t, fieldName, value);
			}
			returnList.add(t);
		}
		
		return returnList;
		
	}
	
	
	/**   
	 * java反射bean的get方法   
	 * @param objectClass   
	 * @param fieldName   
	 * @return   
	 */     

	@SuppressWarnings("unchecked")     

	public static Method getGetMethod(Class objectClass, String fieldName) {     
	    StringBuffer sb = new StringBuffer();     
	    sb.append("get");     
	    sb.append(fieldName.substring(0, 1).toUpperCase());     
	    sb.append(fieldName.substring(1));     
	    try {     
	        return objectClass.getMethod(sb.toString());     
	    } catch (Exception e) {     
	    }     
	    return null;     

	}     

	/**   
	 * java反射bean的set方法   
	 * @param objectClass   
	 * @param fieldName   
	 * @return   
	 */     

	@SuppressWarnings("unchecked")     

	public static Method getSetMethod(Class objectClass, String fieldName) {     
	    try {     
	        Class[] parameterTypes = new Class[1];     
	        Field field = objectClass.getDeclaredField(fieldName);     
	        parameterTypes[0] = field.getType();     
	        StringBuffer sb = new StringBuffer();     
	        sb.append("set");     
	        sb.append(fieldName.substring(0, 1).toUpperCase());     
	        sb.append(fieldName.substring(1));     
	        Method method = objectClass.getMethod(sb.toString(), parameterTypes);     
	        return method;     

	    } catch (Exception e) {     
	        e.printStackTrace();     
	    }     
	    return null;     

	}     

	/**   
	 * 执行set方法   
	 *    
	 * @param o执行对象   
	 * @param fieldName属性   
	 * @param value值   
	 */     

	public static void invokeSet(Object o, String fieldName, Object value) {     

	    Method method = getSetMethod(o.getClass(), fieldName);     
	    try {     
	        method.invoke(o, new Object[] { value });     
	    } catch (Exception e) {     
	        e.printStackTrace();     
	    }     

	}     

	/**   
	 * 执行get方法   
	 * @param o执行对象   
	 * @param fieldName属性   
	 */     
	public static Object invokeGet(Object o, String fieldName) {     

	    Method method = getGetMethod(o.getClass(), fieldName);     
	    try {     
	        return method.invoke(o, new Object[0]);     
	    } catch (Exception e) {     
	        e.printStackTrace();     
	    }     
	    return null;     

	}
	//==========================设置list中对象的某些字段为null结束========================///
}