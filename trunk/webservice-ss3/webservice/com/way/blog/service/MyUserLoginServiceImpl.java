package com.way.blog.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.way.blog.base.entity.LoginReturn;
import com.way.blog.base.entity.ReturnStatus;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.JsonUtil;

@Service("MyUserLoginServiceImpl")
@WebService
public class MyUserLoginServiceImpl extends SpringBeanAutowiringSupport implements UserLoginService{
	
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired private UserLogin userLogin;
	/*@WebMethod
	public boolean userlogin(@WebParam(name="username")String username,@WebParam(name="password") String password){
		System.out.println(userLoginServiceImpl);
		if(null!=userLoginServiceImpl){
			return userLoginServiceImpl.login(username, password);
		}else{
			return false;
		}
		
//		if("fenzaiway".equals(username)&&"admin".equals(password)){
//			return true;
//		}
//		return false;
	}*/

	//用户登录
	@WebMethod
	public String login(@WebParam(name="username")String username,@WebParam(name="password") String password) {
		LoginReturn lr = new LoginReturn();
		if(userLoginServiceImpl.login(username, password)){
			userLogin = userLoginServiceImpl.myFindByProperty("account", username);
			lr.setStatus(1);
			lr.setUsername(userLogin.getNickname());
		}else{
			lr.setStatus(0);
		}
		return JsonUtil.toJson(lr);
	}


	
}
