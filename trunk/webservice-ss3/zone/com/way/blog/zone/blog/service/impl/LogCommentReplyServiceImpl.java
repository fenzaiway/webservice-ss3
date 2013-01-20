package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogCommentReply;

@Service
public class LogCommentReplyServiceImpl extends BaseGenericService<LogCommentReply, Integer> {

	@Override
	@Resource(name="logCommentReplyDao")
	public void setDao(IHibernateGenericDao<LogCommentReply, Serializable> dao) {
		super.setDao(dao);
	}

	@Override
	public int save(LogCommentReply logCommentReply) {
		logCommentReply.setReplyTime(MyFormatDate.getNowDate());
		return super.save(logCommentReply);
	}

	
}
