package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogType;

@Service("logTypeServiceImpl")
public class LogTypeServiceImpl extends BaseGenericService<LogType, Integer> {

	@Autowired
	private BlogZoneServiceImpl blogZoneServiceImpl;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	private BlogZone blogZone;
	private UserLogin userLogin;
	
	@Autowired LogType logType;
	
	@Override
	@Resource(name="logTypeDao")
	public void setDao(IHibernateGenericDao<LogType, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	/**
	 * 添加日志分类
	 * 在这里设置日志分类所属空间、分类创建的时间
	 */
	@Override
	public int save(LogType logType) {
		//取出登录账号
		userLogin = userLoginServiceImpl.myFindByProperty("nickname", logType.getUsername());
		////根据登录用户取出该用户的空间
		blogZone = userLogin.getBlogZone();
		logType.setLogTypeCreateTime(MyFormatDate.getNowDate());
		logType.setIsDefaultLogType(0);
		////设置双向关联关系
		logType.setBlogZone(blogZone);
		Set<LogType> logTypes = new HashSet<LogType>();
		logTypes.add(logType);
		blogZone.setLogTypes(logTypes);
		
		return super.save(logType);
	}
	
	/**
	 * 添加默认分类
	 * @param logType
	 * @return
	 */
	public int saveDefaultLogType(LogType logType){
		return super.save(logType);
	}
	
	
	/**
	 * 根据用户取出用户日志的默认分类
	 * @param username
	 * @return
	 */
	public LogType getDefault(String username){
		String hql = "from LogType where username=? and isDefaultLogType=1";
		List<LogType> logTypeList = this.find(hql, new String[]{username});
		if(!isListTEmpty(logTypeList)){
			logType = logTypeList.get(0);
		}
		return logType;
	}
}
