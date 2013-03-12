package com.way.blog.ss3.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.ss3.entity.MyResources;

@Service("myResourcesServiceImpl")
public class MyResourcesServiceImpl extends BaseGenericService<MyResources, Integer> {

	@Override
	@Resource(name="myResourcesDao")
	public void setDao(IHibernateGenericDao<MyResources, Serializable> dao) {
		super.setDao(dao);
	}
	
	public static final String HQL = "from MyResources where 1=1 ";
	
}
