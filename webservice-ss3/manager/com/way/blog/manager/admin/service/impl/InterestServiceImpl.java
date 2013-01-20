package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Interest;

@Service("interestServiceImpl")
public class InterestServiceImpl extends BaseGenericService<Interest, Integer> {

	@Override
	@Resource(name="interestDao")
	public void setDao(IHibernateGenericDao<Interest, Serializable> dao) {
		super.setDao(dao);
	}

	
}
