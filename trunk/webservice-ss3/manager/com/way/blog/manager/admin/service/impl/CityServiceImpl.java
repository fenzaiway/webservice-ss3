package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.City;

@Service("cityServiceImpl")
public class CityServiceImpl extends BaseGenericService<City, Integer> {

	@Override()
	@Resource(name="cityDao")
	public void setDao(IHibernateGenericDao<City, Serializable> dao) {
		super.setDao(dao);
	}
	
}
