package com.way.blog.base.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.util.PaginationSupport;

public class BaseGenericService<T, ID extends Serializable> implements IGenericService<T, Integer> {
	
	////泛型，通过注解注入调用的实现类
	private IHibernateGenericDao<T, Serializable> dao;
	
	public IHibernateGenericDao<T, Serializable> getDao() {
		return dao;
	}

	@Autowired
	public void setDao(IHibernateGenericDao<T, Serializable> dao) {
		this.dao = dao;
	}

	public Criteria createCriteria(Criterion... criterions) {
		return dao.createCriteria(criterions);
	}

	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions) {
		return dao.createCriteria(orderBy, isAsc, criterions);
	}

	public Query createQuery(String hql, Object... values) {
		return dao.createQuery(hql, values);
	}

	public void delete(Serializable id) {
		dao.delete(id);
	}

	public void delete(T persistentInstance) {
		dao.delete(persistentInstance);
	}

	public List find(String hql, Object... values) {
		return dao.find(hql, values);
	}

	public List<T> findByCriteria(Criteria criteria) {
		return dao.findByCriteria(criteria);
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return dao.findByCriteria(detachedCriteria);
	}

	public List<T> findByExample(T object) {
		return dao.findByExample(object);
	}

	public T findById(Serializable id) {
		return dao.findById(id);
	}

	public List<T> findByProperty(String propertyName, Object value) {
		return dao.findByProperty(propertyName, value);
	}

	public PaginationSupport findPageByCriteria(Criteria criteria,
			int pageSize, int startIndex) {
		return dao.findPageByCriteria(criteria, pageSize, startIndex);
	}

	public PaginationSupport findPageByCriteria(Criteria criteria) {
		return dao.findPageByCriteria(criteria);
	}

	public PaginationSupport findPageByCriteria(Criteria criteria,
			int startIndex) {
		return dao.findPageByCriteria(criteria, startIndex);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return dao.findPageByCriteria(detachedCriteria, pageSize, startIndex);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return dao.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return dao.findPageByCriteria(detachedCriteria, startIndex);
	}

	public PaginationSupport findPageByQuery(String hql, int pageSize,
			int startIndex, Object... values) {
		return dao.findPageByQuery(hql, pageSize, startIndex, values);
	}

	public PaginationSupport findPageByQuery(String hql, Object... values) {
		return dao.findPageByQuery(hql, values);
	}

	public PaginationSupport findPageByQuery(String hql, int startIndex,
			Object... values) {
		return dao.findPageByQuery(hql, startIndex, values);
	}

	public int getCountByCriteria(Criteria criteria) {
		return dao.getCountByCriteria(criteria);
	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return dao.getCountByCriteria(detachedCriteria);
	}

	public int getCountByQuery(String hql, Object... values) {
		return dao.getCountByQuery(hql, values);
	}

	public Class<T> getPojoClass() {
		return null;
	}

	public String getPojoClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> loadAll() {
		return dao.loadAll();
	}

	public int save(T object) {
		return dao.save(object);
	}

	public void update(T object) {
		dao.update(object);
	}

	public void saveAll(Collection<T> entities) {
		dao.saveAll(entities);
	}

	public T myFindByProperty(String propertyName, Object value) {
		List<T> tList = this.findByProperty(propertyName, value);
		return this.getTByList(tList);
//		if(null != tList && !tList.isEmpty()){
//			return tList.get(0);
//		}
//		return null;
	}

	/**
	 * 根据List判断是否有值，有值的话返回第一个数
	 */
	public T getTByList(List<T> list){
		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 判断list是不是为空,如果为空，返回true
	 */
	public boolean isListTEmpty(List<T> list){
		boolean flag = true;
		if(null != list && !list.isEmpty()){
			flag = false;
		}
		return flag;
	}

	public void saveOrUpdate(T object) {
		dao.saveOrUpdate(object);
	}
	
//	/**
//	 * 消除关联关系
//	 * @return
//	 */
//	public List<T> removeRelation(List<T> ListT, String method){
//		List<T> myListT = new ArrayList<T>(); 
//		for(T t : ListT){
//			//t.getClass().get
//		}
//		return null;
//	}

	/**
	 * @param list 要设置的list
	 * @param value 设置的值
	 * @param values 要设置null的字段
	 * @return 经过设置变量后的list
	 */
	public List<T> setNull(List<T> list, Object value, String... values) {
		return dao.setNull(list, value, values);   /////有问题，暂时不使用
		//return list;
	}
}
