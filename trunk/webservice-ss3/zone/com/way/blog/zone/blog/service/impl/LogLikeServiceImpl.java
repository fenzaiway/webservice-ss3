package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogLike;

@Service
public class LogLikeServiceImpl extends BaseGenericService<LogLike, Integer> {

	@Override
	@Resource(name="logLikeDao")
	public void setDao(IHibernateGenericDao<LogLike, Serializable> dao) {
		super.setDao(dao);
	}
	
	@Autowired private LogLike logLike;
	
	private List<LogLike> logLikeList = new ArrayList<LogLike>();

	/**
	 * 获取用户与这篇Id为logid的日志的关系
	 * @param username
	 * @param logid
	 * @return
	 */
	public LogLike getUserLike(String username, LogInfo logInfo){

		logLikeList = find("from LogLike where username=? and logInfo=?", new Object[]{username,logInfo});
		if(null!=logLikeList && !logLikeList.isEmpty()){
			logLike = logLikeList.get(0);
			logLike.setLogInfo(null);
		}else{
			logLike = new LogLike();
			logLike.setUsername(username);
		}
		return logLike;
	}
	
	/**
	 * 判断用户是否喜欢一篇日志，如果喜欢，返回true,否则返回false
	 * @param username
	 * @param logInfo
	 * @return
	 */
	public boolean checkIsUserLike(String username, LogInfo logInfo){
		logLike = getUserLike(username, logInfo);
		if(0!=logLike.getId()){
			return true;
		}else{
			return false;
		}
	}
}
