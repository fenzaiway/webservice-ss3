package com.way.blog.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.way.blog.util.PaginationSupport;

public interface IHibernateGenericDao<T, ID extends Serializable> {

	/** */
	/**
		
	 * ��ø�DAO��Ӧ��POJO����
		
	 */

	public HibernateTemplate myGetHibernateTemplate();
	
	public abstract Class<T> getPojoClass();

	/** */
	/**
		
	 * ��ø�DAO��Ӧ��POJO������
		
	 */

	public abstract String getPojoClassName();

	/** */
	/**
		
	 * �������еĶ���
		
	 */

	public abstract List<T> loadAll();

	/** */
	/**
		
	 * ���hql��ѯ
		
	 *
		
	 * @param values �ɱ����
		
	 */

	public abstract List find(String hql, Object... values);

	/** */
	/**
		
	 * ���������ض���
		
	 *
		
	 * @param criteria Criteriaʵ��
		
	 */

	public abstract List<T> findByCriteria(final Criteria criteria);

	/** */
	/**
		
	 * ���������ض���
		
	 * @param detachedCriteria DetachedCriteriaʵ��
		
	 */
	public abstract List<T> findByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
		
	 * ��ݸ��ʵ����Ҷ���
		
	 */

	public abstract List<T> findByExample(T object);

	/** */
	/**
		
	 * ���ID���Ҷ���
		
	 */

	public abstract T findById(Serializable id);

	/** */
	/**
		
	 * ���ĳ��������Խ��в���
		
	 */

	public abstract List<T> findByProperty(String propertyName, Object value);

	/** */
	/**
		
	 * �½�����ʵ��
		
	 */

	public abstract int save(T object);

	/** */
	/**
		
	 * �����Ѵ��ڵĶ���
		
	 */

	public abstract void update(T object);

	/** */
	/**
		
	 * ɾ��ָ��ID�Ķ���
		
	 */

	public abstract void delete(Serializable id);

	/** */
	/**
		
	 * ɾ��ָ������
		
	 */

	public abstract void delete(T persistentInstance);

	/** */
	/**
		
	 * ���Criteria���ط�ҳ��ָ��ҳ��С����ʼλ��
		
	 */

	public abstract PaginationSupport findPageByCriteria(
			final Criteria criteria, final int pageSize, final int startIndex);

	/** */
	/**
		
	 * ���Criteria���ط�ҳ��Ĭ��ҳ��С���ӵ�0��ʼ
		
	 */

	public abstract PaginationSupport findPageByCriteria(final Criteria criteria);

	/** */
	/**
		
	 * ���Criteria���ط�ҳ��Ĭ��ҳ��С���ӵ�startIndex��ʼ
		
	 */

	public abstract PaginationSupport findPageByCriteria(
			final Criteria criteria, final int startIndex);

	/** */
	/**
		
	 * ���Criteriaͳ������
		
	 */

	public abstract int getCountByCriteria(final Criteria criteria);

	/** */
	/**
		
	 * ���DetachedCriteria���ط�ҳ��ָ��ҳ��С����ʼλ��
		
	 */

	public abstract PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);

	/** */
	/**
		
	 * ���DetachedCriteria���ط�ҳ��Ĭ��ҳ��С���ӵ�0��ʼ
		
	 */

	public abstract PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
		
	 * ���DetachedCriteria���ط�ҳ��Ĭ��ҳ��С���ӵ�startIndex��ʼ
		
	 */

	public abstract PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex);

	/** */
	/**
		
	 * ���DetachedCriteriaͳ������
		
	 */

	public abstract int getCountByCriteria(
			final DetachedCriteria detachedCriteria);

	/** */
	/**
		
	 * ���hql���ط�ҳ��ָ��ҳ��С����ʼλ��
		
	 */

	public abstract PaginationSupport findPageByQuery(final String hql,
			final int pageSize, final int startIndex, Object... values);

	/** */
	/**
		
	 * ���hql���ط�ҳ��Ĭ��ҳ��С���ӵ�0��ʼ
		
	 */

	public abstract PaginationSupport findPageByQuery(final String hql,
			Object... values);

	/** */
	/**
		
	 * ���hql���ط�ҳ��Ĭ��ҳ��С���ӵ�startIndex��ʼ
		
	 */

	public abstract PaginationSupport findPageByQuery(final String hql,
			final int startIndex, Object... values);

	/** */
	/**
		
	 * ���hqlͳ������
		
	 */

	public abstract int getCountByQuery(final String hql, Object... values);

	/** */
	/**
		
	 * ����Criteria����
		
	 *
		
	 * @param criterions �ɱ��Restrictions����б�
		
	 */

	public abstract Criteria createCriteria(Criterion... criterions);

	/** */
	/**
		
	 * ����Criteria���󣬴������ֶ��������ֶ�
		
	 */

	public abstract Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions);

	/** */
	/**
		
	 * ����ȡ��SpringSide.
		
	 * ����Query����. ������Ҫfirst,max,fetchsize,cache,cacheRegion��������õĺ���,�����ڷ���Query����������.
		
	 * �������l������,���£�
		
	 * <pre>
		
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
		
	 * </pre>
		
	 * ���÷�ʽ���£�
		
	 * <pre>
		
	 *        dao.createQuery(hql)
		
	 *        dao.createQuery(hql,arg0);
		
	 *        dao.createQuery(hql,arg0,arg1);
		
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
		
	 * </pre>
		
	 *
		
	 * @param values �ɱ����.
		
	 */

	public abstract Query createQuery(String hql, Object... values);
	
	/**
	 * ��������
	 */
	 public void saveAll(Collection<T> entities);
	
	 /**
	  * ������߸���
	  */
	 public void saveOrUpdate(T object);
	 
	 /**
	  * ����list�ж����ĳЩ�ֶ�Ϊnull
	  * @param list Ҫ���õ�list
	  * @param value Ҫ���õ�ֵ
	  * @param values Ҫ���õ��ֶ�
	  * @return ��������null���list
	  */
	 public  List<T> setNull(List<T> list, Object value, String...values);
}