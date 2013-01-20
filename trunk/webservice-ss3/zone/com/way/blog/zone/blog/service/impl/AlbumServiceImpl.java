package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.Album;

@Service("albumServiceImpl")
public class AlbumServiceImpl extends BaseGenericService<Album, Integer> {

	@Override
	@Resource(name="albumDao")
	public void setDao(IHibernateGenericDao<Album, Serializable> dao) {
		super.setDao(dao);
	}
	
}
