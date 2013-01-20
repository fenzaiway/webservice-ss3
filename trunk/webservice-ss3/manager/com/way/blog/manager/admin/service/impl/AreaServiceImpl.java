package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Area;

@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseGenericService<Area, Integer> {

	@Override
	@Resource(name="areaDao")
	public void setDao(IHibernateGenericDao<Area, Serializable> dao) {
		super.setDao(dao);
	}
	
}
