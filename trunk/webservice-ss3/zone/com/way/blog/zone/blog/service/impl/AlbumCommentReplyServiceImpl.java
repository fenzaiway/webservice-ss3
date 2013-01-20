package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.AlbumCommentReply;

@Service
public class AlbumCommentReplyServiceImpl extends BaseGenericService<AlbumCommentReply, Integer> {

	@Override
	@Resource(name="albumCommentReplyDao")
	public void setDao(IHibernateGenericDao<AlbumCommentReply, Serializable> dao) {
		super.setDao(dao);
	}
	
	
}
