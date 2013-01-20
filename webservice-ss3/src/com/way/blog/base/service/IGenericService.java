package com.way.blog.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.util.PaginationSupport;

@Transactional
public interface IGenericService<T, ID extends Serializable> {
	
	/** */
	/**
		
	 * 获得该DAO对应的POJO类型
		
	 */

	public abstract Class<T> getPojoClass();

	/** */
	/**
		
	 * 获得该DAO对应的POJO类型名
		
	 */

	public abstract String getPojoClassName();

	/** */
	/**
		
	 * 加载所有的对象
		
	 */

	@Transactional(readOnly=true)
	public abstract List<T> loadAll();

	/** */
	/**
		
	 * 根据hql查询
		
	 *
		
	 * @param values 可变参数
		
	 */
	@Transactional(readOnly=true)
	public abstract List find(String hql, Object... values);

	/** */
	/**
		
	 * 根据条件加载对象
		
	 *
		
	 * @param criteria Criteria实例
		
	 */
	@Transactional(readOnly=true)
	public abstract List<T> findByCriteria(final Criteria criteria);

	/** */
	/**
		
	 * 根据条件加载对象
		
	 * @param detachedCriteria DetachedCriteria实例
		
	 */
	@Transactional(readOnly=true)
	public abstract List<T> findByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
		
	 * 根据给定的实例查找对象
		
	 */
	@Transactional(readOnly=true)
	public abstract List<T> findByExample(T object);

	/** */
	/**
		
	 * 根据ID查找对象
		
	 */
	@Transactional(readOnly=true)
	public abstract T findById(Serializable id);

	/** */
	/**
		
	 * 根据某个具体属性进行查找
		
	 */
	@Transactional(readOnly=true)
	public abstract List<T> findByProperty(String propertyName, Object value);
	
	/**
	 * 根据对象的某个具体属性查找对象的记录
	 * @param propertyName 对象的属性
	 * @param value 对象的值
	 * @return
	 */
	public T myFindByProperty(String propertyName, Object value);

	/** */
	/**
		
	 * 新建对象实例化
	 * @throws Exception 
		
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract int save(T object) throws Exception;

	/** */
	/**
		
	 * 更新已存在的对象
		
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract void update(T object);

	/** */
	/**
		
	 * 删除指定ID的对象
		
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract void delete(Serializable id);

	/** */
	/**
		
	 * 删除指定对象
		
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract void delete(T persistentInstance);

	/** */
	/**
		
	 * 根据Criteria加载分页，指定页大小和起始位置
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByCriteria(
			final Criteria criteria, final int pageSize, final int startIndex);

	/** */
	/**
		
	 * 根据Criteria加载分页，默认页大小，从第0条开始
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByCriteria(final Criteria criteria);

	/** */
	/**
		
	 * 根据Criteria加载分页，默认页大小，从第startIndex条开始
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByCriteria(
			final Criteria criteria, final int startIndex);

	/** */
	/**
		
	 * 根据Criteria统计总数
		
	 */
	@Transactional(readOnly=true)
	public abstract int getCountByCriteria(final Criteria criteria);

	/** */
	/**
		
	 * 根据DetachedCriteria加载分页，指定页大小和起始位置
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);

	/** */
	/**
		
	 * 根据DetachedCriteria加载分页，默认页大小，从第0条开始
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
		
	 * 根据DetachedCriteria加载分页，默认页大小，从第startIndex条开始
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex);

	/** */
	/**
		
	 * 根据DetachedCriteria统计总数
		
	 */
	@Transactional(readOnly=true)
	public abstract int getCountByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
		
	 * 根据hql加载分页，指定页大小和起始位置
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByQuery(final String hql,
			final int pageSize, final int startIndex, Object... values);

	/** */
	/**
		
	 * 根据hql加载分页，默认页大小，从第0条开始
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByQuery(final String hql,
			Object... values);

	/** */
	/**
		
	 * 根据hql加载分页，默认页大小，从第startIndex条开始
		
	 */
	@Transactional(readOnly=true)
	public abstract PaginationSupport findPageByQuery(final String hql,
			final int startIndex, Object... values);

	/** */
	/**
		
	 * 根据hql统计总数
		
	 */
	@Transactional(readOnly=true)
	public abstract int getCountByQuery(final String hql, Object... values);

	/** */
	/**
		
	 * 创建Criteria对象
		
	 *
		
	 * @param criterions 可变的Restrictions条件列表
		
	 */
	@Transactional(readOnly=true)
	public abstract Criteria createCriteria(Criterion... criterions);

	/** */
	/**
		
	 * 创建Criteria对象，带排序字段与升降序字段
		
	 */
	@Transactional(readOnly=true)
	public abstract Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions);

	/** */
	/**
		
	 * 方法取自SpringSide.
		
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
		
	 * 留意可以连续设置,如下：
		
	 * <pre>
		
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
		
	 * </pre>
		
	 * 调用方式如下：
		
	 * <pre>
		
	 *        dao.createQuery(hql)
		
	 *        dao.createQuery(hql,arg0);
		
	 *        dao.createQuery(hql,arg0,arg1);
		
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
		
	 * </pre>
		
	 *
		
	 * @param values 可变参数.
		
	 */
	@Transactional(readOnly=true)
	public abstract Query createQuery(String hql, Object... values);
	
	/**
	 * 保存所有
	 */
	 public void saveAll(Collection<T> entities);
	 
	 /**
	  * 保存或者更新
	  */
	 public void saveOrUpdate(T object);
	 
	 /**
	  * 设置list中对象的某些字段为null
	  * @param list 要设置的list
	  * @param value 要设置的值
	  * @param values 要设置的字段
	  * @return 经过设置null后的list
	  */
	 public  List<T> setNull(List<T> list, Object value, String...values);
}
