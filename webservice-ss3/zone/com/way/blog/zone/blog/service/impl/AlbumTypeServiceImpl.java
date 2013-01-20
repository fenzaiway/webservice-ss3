package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.AlbumType;

@Service
public class AlbumTypeServiceImpl extends BaseGenericService<AlbumType, Integer> {

	@Override
	@Resource(name="albumTypeDao")
	public void setDao(IHibernateGenericDao<AlbumType, Serializable> dao) {
		super.setDao(dao);
	}
	
	
}
