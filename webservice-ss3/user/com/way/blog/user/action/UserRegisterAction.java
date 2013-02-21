package com.way.blog.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.base.action.BaseAction;
import com.way.blog.service.MyUserLoginServiceImpl;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.user.service.impl.UserRegisterServiceImpl;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;

@Controller("userRegisterAction")
@ParentPackage("struts-default")
@Namespace("/register")
public class UserRegisterAction extends BaseAction implements ModelDriven<UserRegister>,Preparable {

	@Autowired
	private UserRegisterServiceImpl userRegisterServiceImpl;
	@Autowired
	private BlogZoneServiceImpl blogZoneServiceImpl;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired 
	MyUserLoginServiceImpl myUserLoginServiceImpl;
	@Autowired
	private UserHeadImgAction userHeadImgAction;
	private UserLogin userLogin;
	
	private UserRegister userRegister;
	
	private String account;
	
	private String mypassword;
	
	

	/**
	 * 用户注册
	 * 注册成功的用户跳转到用户详细信息页面
	 * 可以在这里修改注册成功后跳转的路径
	 */
	@Action(value="save",results={
			@Result(name="success", location="/myUserDetial/gotoUserdetial.do",type="redirect"),
			@Result(name="input", location="/register/register.jsp")
	})
	public String save(){
		System.out.println("-----用户注册------");
		String IP = request.getRemoteAddr();
		userRegister.setIp(IP);
		try {
			userRegisterServiceImpl.mySave(userRegister);
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("saveError", e.getMessage());
			return INPUT;
		}
		///注册的时候同时为用户开通空间
		blogZoneServiceImpl.createBlog(userRegister);
		////注册成功后，将用户名保存到Session中
		session.setAttribute("myusername",userRegister.getUsername());
		//为用户设置一个默认的头像
		userHeadImgAction.save();
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 进入注册页面
	 */
	@Action(value="gotoRegister",results={
			@Result(name="success", location="/register/register.jsp"),
	})
	public String gotoRegister(){
		return SUCCESS;
	}
	
	
	/**
	 * 进入登录页面
	 */
	@Action(value="gotoLogin",results={
			@Result(name="success", location="/login.jsp"),
	})
	public String gotoLogin(){
		return SUCCESS;
	}
	
	/**
	 * 进入登录页面
	 */
	@Action(value="login",results={
			@Result(name="success", location="/zone/%{myusername}",type="redirect"),
			@Result(name="input",location="/login.jsp")
	})
	public String login(){
		String returnString=INPUT;
		////登录成功
		//if(userLoginServiceImpl.login(account, mypassword)){
		if(myUserLoginServiceImpl.userlogin(account, mypassword)){
			userLogin = userLoginServiceImpl.findUserLoginByAccount(account);
			myusername = userLogin.getUsername();
			session.setAttribute("myusername", myusername);
			returnString = SUCCESS;
		}
		////登录失败
		addFieldError("loginFail", "用户名或者密码错误");
		
		return returnString;
	}
	
	
	/////Session失效跳转
	@Action(value="sessionError",results={
			@Result(name="success",location="/login.jsp")
	})
	public String sessionError(){
		addFieldError("sessionTimeout", "用户长时间没操作，请重新登录");
		return SUCCESS;
	}
	
	public UserRegister getModel() {
		return userRegister;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMypassword() {
		return mypassword;
	}

	public void setMypassword(String mypassword) {
		this.mypassword = mypassword;
	}
	
	public String getMyusername() {
		return myusername;
	}

	public void setMyusername(String myusername) {
		this.myusername = myusername;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

}
