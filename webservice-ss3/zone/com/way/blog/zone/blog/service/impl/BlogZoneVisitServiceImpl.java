package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.BlogZoneVisit;

@Service("blogZoneVisitServiceImpl")
public class BlogZoneVisitServiceImpl extends BaseGenericService<BlogZoneVisit,Integer> {

	@Override
	@Resource(name="blogZoneVisitDao")
	public void setDao(IHibernateGenericDao<BlogZoneVisit, Serializable> dao) {
		super.setDao(dao);
	}

	
}
