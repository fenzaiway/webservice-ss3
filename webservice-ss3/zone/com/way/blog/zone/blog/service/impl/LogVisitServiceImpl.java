package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogVisit;

@Service
public class LogVisitServiceImpl extends BaseGenericService<LogVisit, Integer> {

	@Override
	@Resource(name="logVisitDao")
	public void setDao(IHibernateGenericDao<LogVisit, Serializable> dao) {
		super.setDao(dao);
	}
	@Override
	public int save(LogVisit logVisit) {
		logVisit.setVisitTime(MyFormatDate.getNowDate());
		return super.save(logVisit);
	}

	
	
}
