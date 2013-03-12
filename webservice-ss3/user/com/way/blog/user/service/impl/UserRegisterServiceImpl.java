package com.way.blog.user.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.MyUserDetial;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.util.MD5;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.entity.BlogZone;


@Service("userRegisterServiceImpl")
public class UserRegisterServiceImpl extends BaseGenericService<UserRegister,Integer>{
	@Override
	@Resource(name="userRegisterDao")
	public void setDao(IHibernateGenericDao<UserRegister, Serializable> dao) {
		super.setDao(dao);
	}
	
	

	public String getEncrypt(UserRegister userRegister){
		String encrypt = userRegister.getEmail()+"{"+userRegister.getUsername()+"}";
		return MD5.code(encrypt);
	}
	
	/**
	 * 保存用户注册资料
	 * @param userRegister
	 * @return
	 * @throws Exception
	 */
	//@Override
	public int mySave(UserRegister userRegister) throws Exception{
		String date = MyFormatDate.getNowDate();
		/**
		 * 步骤：
		 * 1、判断要注册的用户的邮箱是否已经存在
		 * 2、判断要注册的用户名是否已经存在，用户的用户名在系统中是唯一的
		 * 3、根据email{username}的规则生成验证码，这个验证码是用来确认用户的邮箱是否是用户在使用的邮箱
		 * 4、设置注册用户为不可用状态，只有用户的邮箱通过验证的时候，用户才可以真正使用系统
		 */
		
		//判断用户名是否已经存在
		if(this.isUserNameExits(userRegister.getUsername())){
			throw new RuntimeException("用户名已经存在");
		}
		//判断用户邮箱是否已经存在
		if(this.isEmailExits(userRegister.getEmail())){
			throw new RuntimeException("要注册的邮箱已经存在");
		}
		//生成验证码
		
		userRegister.setVerifiCode(MD5.code(getEncrypt(userRegister)));
		userRegister.setEnabled(0);	//0表示账号不可用
		userRegister.setRegistrationTime(date);
		
		
//		///为了跟SpringSecurity中的密码校验一致，这里采用password+{username}的方式加密
		String password = MD5.getPasswordMD5Code(userRegister.getPassword().trim(), userRegister.getEmail().trim());
//		String date = MyFormatDate.getFullDate(new Date());
		String username = userRegister.getUsername().trim();
		userRegister.setUsername(username); ////去除空格后重新设置
		userRegister.setPassword(password);
//		////设置默认值，在Junit测试的时候，就不用再设置默认值了
//		userRegister.setEnabled(1);	///默认值为0表示空间不可用，在测试的时候，把默认值改为1
//		userRegister.setRegistrationTime(date);///默认注册时间为系统时间
//		
//		////////////////////////////////////////////////////
		/**
		 * 在保存用户注册信息的时候同时记录到用户登录表
		 */
		UserLogin userLogin = new UserLogin();
		userLogin.setAccount(userRegister.getEmail());
		//userLogin.setUsername(username);
		userLogin.setNickname(username);
		userLogin.setPassword(password);
		userLogin.setCreateTime(date);
		userLogin.setEnabled(0);//////默认值为0表示登录不可用，在测试的时候，把默认值改为1
//		
//		/**
//		 * 同时创建用户详细信息记录
//		 */
//		MyUserDetial myUserDetial = new MyUserDetial();
//		myUserDetial.setUsername(username);
//		myUserDetial.setInterests("");	////默认不添加用户的兴趣，在用户登录的时候，要求用户选择
//		//设置用户与详细信息双向关联
//		myUserDetial.setUser(userLogin);
//		userLogin.setMyUserDetial(myUserDetial);
//		
//		///设置登录表与注册表双向关联
		userLogin.setUserRegister(userRegister);
		userRegister.setUserLogin(userLogin);
		
		return super.save(userRegister);
	}
	
	/**
	 * 判断要注册的用户名是否存在
	 * return true 表示该用户名已经存在 false 用户名还没有存在
	 */
	public boolean isUserNameExits(String username){
		boolean flag = false;
		List<UserRegister> userRegisterList = null;
		userRegisterList = super.findByProperty("username", username);
		if(null != userRegisterList && !userRegisterList.isEmpty()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据邮箱判断用户要注册的邮箱是否已经存在
	 * @param email
	 * @return true 表示该邮箱已经存在 false 邮箱还没有存在
	 */
	public boolean isEmailExits(String email){
		boolean flag = false;
		if(null != super.myFindByProperty("email", email) && !(super.findByProperty("email", email).isEmpty())){
			flag =true;
		}
		return flag;
	}
	

}
