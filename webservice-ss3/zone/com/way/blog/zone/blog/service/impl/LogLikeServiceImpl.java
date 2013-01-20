package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogLike;

@Service
public class LogLikeServiceImpl extends BaseGenericService<LogLike, Integer> {

	@Override
	@Resource(name="logLikeDao")
	public void setDao(IHibernateGenericDao<LogLike, Serializable> dao) {
		super.setDao(dao);
	}

	
}
