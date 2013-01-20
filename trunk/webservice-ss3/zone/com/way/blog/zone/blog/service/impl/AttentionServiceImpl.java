package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.Attention;

@Service
public class AttentionServiceImpl extends BaseGenericService<Attention, Integer> {

	@Override
	@Resource(name="attentionDao")
	public void setDao(IHibernateGenericDao<Attention, Serializable> dao) {
		super.setDao(dao);
	}

	
}
