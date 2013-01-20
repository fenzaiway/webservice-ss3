package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogComment;

@Service("logCommentServiceImpl")
public class LogCommentServiceImpl extends BaseGenericService<LogComment, Integer> {

	@Override
	@Resource(name="logCommentDao")
	public void setDao(IHibernateGenericDao<LogComment, Serializable> dao) {
		super.setDao(dao);
	}

	@Override
	public int save(LogComment logComment) {
		logComment.setCommentTime(MyFormatDate.getNowDate());
		return super.save(logComment);
	}

	
}
