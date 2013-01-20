package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogStore;

@Service
public class LogStoreServiceImpl extends BaseGenericService<LogStore, Integer> {

	@Override
	@Resource(name="logStoreDao")
	public void setDao(IHibernateGenericDao<LogStore, Serializable> dao) {
		super.setDao(dao);
	}

	
}
