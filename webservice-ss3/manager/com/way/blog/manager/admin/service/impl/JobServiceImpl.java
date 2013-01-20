package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Job;

@Service("jobServiceImpl")
public class JobServiceImpl extends BaseGenericService<Job, Integer> {

	@Override
	@Resource(name="jobDao")
	public void setDao(IHibernateGenericDao<Job, Serializable> dao) {
		super.setDao(dao);
	}
	
}
