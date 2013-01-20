package com.way.blog.user.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.MyUserDetial;

@Service("myUserDetialServiceImpl")
public class MyUserDetialServiceImpl extends BaseGenericService<MyUserDetial, Integer> {

	@Override
	@Resource(name="myUserDetialDao")
	public void setDao(IHibernateGenericDao<MyUserDetial, Serializable> dao) {
		super.setDao(dao);
	}
	
}
