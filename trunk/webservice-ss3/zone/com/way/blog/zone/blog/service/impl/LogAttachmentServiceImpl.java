package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogAttachment;

@Service
public class LogAttachmentServiceImpl extends BaseGenericService<LogAttachment, Integer> {

	@Override
	@Resource(name="logAttachmentDao")
	public void setDao(IHibernateGenericDao<LogAttachment, Serializable> dao) {
		super.setDao(dao);
	}
	
	
}
