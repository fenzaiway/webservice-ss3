package com.way.blog.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.way.blog.user.service.impl.UserLoginServiceImpl;

@Service("MyUserLoginServiceImpl")
@WebService
public class MyUserLoginServiceImpl extends SpringBeanAutowiringSupport implements UserLoginService{
	
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	
	@WebMethod
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
	}
	
}
