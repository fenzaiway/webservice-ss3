package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogTag;

@Service("logTagServiceImpl")
public class LogTagServiceImpl extends BaseGenericService<LogTag, Integer> {

	@Override
	@Resource(name="logTagDao")
	public void setDao(IHibernateGenericDao<LogTag, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
	
}
