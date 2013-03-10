package com.way.blog.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.util.MD5;
import com.way.blog.util.PaginationSupport;



@Service("userLoginServiceImpl")
public class UserLoginServiceImpl extends BaseGenericService<UserLogin, Integer> {

	@Override
	@Resource(name="userLoginDao")
	public void setDao(IHibernateGenericDao<UserLogin, Serializable> dao) {
		super.setDao(dao);
	}
	
	public static final String HQL = "from UserLogin where 1=1";
	
	/**
	 * 根据用户名取出该用户的登录记录
	 * @param username 用户名
	 * @return 用户登录记录
	 */
	public UserLogin findUserLoginByUserName(String username){
		List<UserLogin> userLoginList = super.findByProperty("username", username);
		UserLogin userLogin = null;
		if(null != userLoginList && !userLoginList.isEmpty()){
			userLogin = userLoginList.get(0);
		}
		return userLogin;
	}
	
	///根据登录账号取出用户
	public UserLogin findUserLoginByAccount(String account){
		List<UserLogin> userLoginList = super.findByProperty("account", account);
		UserLogin userLogin = null;
		if(null != userLoginList && !userLoginList.isEmpty()){
			userLogin = userLoginList.get(0);
		}
		return userLogin;
	}
	
	//////判断用户名是不是存在
	public boolean isUserNameExist(String userName){
		List<UserLogin> userLoginList = null;
		boolean flag = false;
		userLoginList = super.findByProperty("username", userName);
		if(!isUserListEmpty(userLoginList)){
			flag = true;
		}
		return flag;
	}
	

	/////根据list判断取出的list有没有数据,如果为空或者没有数据，返回true,如果存在数据，返回false
	public boolean isUserListEmpty(List<UserLogin> userLoginList){
		boolean flag = true;
		if(null != userLoginList && !userLoginList.isEmpty()){
			flag = false;
		}
		return flag;
	}
	
	
	public boolean login(String account, String password){
		boolean flag = false;
		String hql = "from UserLogin where account=? and password=?";
		if(!isListTEmpty(find(hql, new String[]{account,MD5.getPasswordMD5Code(password, account)}))){
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 分页查询
	 * @param pageSize 每页显示查询行数
	 * @param startIndex 从哪个下标开始查询
	 * @param userLogin 对登录用户列表进行查询
	 * @return
	 */
	public PaginationSupport search(int pageSize, int startIndex, UserLogin userLogin){
		PaginationSupport paginationSupport = null;
		String hql = HQL;
		StringBuffer sb = new StringBuffer();
		sb.append(hql);
		if(null != userLogin.getUsername()){
			sb.append(" and username like '%"+userLogin.getUsername()+"%'");
		}
		paginationSupport = this.findPageByQuery(sb.toString(), pageSize, startIndex, new Object[]{});
		return paginationSupport;
	}
}
