package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.Vedio;

@Service
public class VedioServiceImpl extends BaseGenericService<Vedio, Integer> {

	@Override
	@Resource(name="vedioDao")
	public void setDao(IHibernateGenericDao<Vedio, Serializable> dao) {
		super.setDao(dao);
	}

	
}
