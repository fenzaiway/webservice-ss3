package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogType;

@Service("blogZoneServiceImpl")
public class BlogZoneServiceImpl extends BaseGenericService<BlogZone, Integer> {

	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired
	private LogTypeServiceImpl logTypeServiceImpl;
	
	
	@Override
	@Resource(name="blogZoneDao")
	public void setDao(IHibernateGenericDao<BlogZone, Serializable> dao) {
		super.setDao(dao);
	}
	
	////根据注册用户的昵称创建用户空间
	public void createBlog(UserRegister userRegister){
		String date = MyFormatDate.getFullDate(new Date());
		String username = userRegister.getUsername();
	
		BlogZone blogZone = new BlogZone();
		blogZone.setBlogZoneCreateTime(date);
		blogZone.setBlogZoneName(username+"的空间");	////默认根据用户名+“的空间”来设置默认空间名称
		blogZone.setZoneUrl("http://127.0.0.1:8080/blog/zone/"+username);	///游客或者会员可以通过这个路径访问用户的空间
		blogZone.setUsername(username);
		/**
		 * ////设置空间的可用状态，用户通过验证后才可以使用空间；
		 * 在测试的时候，设置为1
		 */
		blogZone.setEnabled(1);	
		UserLogin userLogin = userLoginServiceImpl.findUserLoginByUserName(username);
		////如果用户名不为空，则设置双向关联
		if(null != userLogin){
			userLogin.setBlogZone(blogZone);
			blogZone.setUserLogin(userLogin);
		}
		
		//////在创建空间的时候，设置默认日志分类
		LogType logType = new LogType();
		logType.setIsDefaultLogType(1);
		logType.setLogTypeName("个人日记");
		logType.setUsername(username);
		logType.setLogTypeCreateTime(MyFormatDate.getNowDate());
		///设置双向关联
		Set<LogType> logTypes = new HashSet<LogType>();
		blogZone.setLogTypes(logTypes);
		logType.setBlogZone(blogZone);
		logTypeServiceImpl.saveDefaultLogType(logType);
		super.save(blogZone);
	}

	
}
