package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogReprint;

@Service
public class LogReprintServiceImpl extends BaseGenericService<LogReprint, Integer> {

	@Override
	@Resource(name="logReprintDao")
	public void setDao(IHibernateGenericDao<LogReprint, Serializable> dao) {
		super.setDao(dao);
	}

	
}
