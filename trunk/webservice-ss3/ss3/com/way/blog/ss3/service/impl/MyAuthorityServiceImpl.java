package com.way.blog.ss3.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.ss3.entity.MyAuthority;

@Service("myAuthorityServiceImpl")
public class MyAuthorityServiceImpl extends BaseGenericService<MyAuthority, Integer> {

	@Override
	@Resource(name="myAuthorityDao")
	public void setDao(IHibernateGenericDao<MyAuthority, Serializable> dao) {
		super.setDao(dao);
	}
	
}
