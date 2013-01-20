package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogDraft;

@Service
public class LogDraftServiceImpl extends BaseGenericService<LogDraft, Integer> {

	@Override
	@Resource(name="logDraftDao")
	public void setDao(IHibernateGenericDao<LogDraft, Serializable> dao) {
		super.setDao(dao);
	}

	
}
