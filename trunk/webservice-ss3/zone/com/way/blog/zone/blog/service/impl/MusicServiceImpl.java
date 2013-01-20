package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.Music;

@Service("musicServiceImpl")
public class MusicServiceImpl extends BaseGenericService<Music, Integer> {

	@Override
	@Resource(name="musicDao")
	public void setDao(IHibernateGenericDao<Music, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
}
