package com.way.blog.ss3.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.ss3.entity.MyRoles;

@Service("myRolesServiceImpl")
public class MyRolesServiceImpl extends BaseGenericService<MyRoles, Integer> {

	@Override
	@Resource(name="myRolesDao")
	public void setDao(IHibernateGenericDao<MyRoles, Serializable> dao) {
		super.setDao(dao);
	}
	
	public static final String HQL = "from MyRoles where 1=1 ";
	
}
