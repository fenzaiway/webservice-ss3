package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogShare;

@Service
public class LogShareServiceImpl extends BaseGenericService<LogShare, Integer> {

	@Override
	@Resource(name="logShareDao")
	public void setDao(IHibernateGenericDao<LogShare, Serializable> dao) {
		super.setDao(dao);
	}

	
}
