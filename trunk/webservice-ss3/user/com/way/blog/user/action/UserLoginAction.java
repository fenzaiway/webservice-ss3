package com.way.blog.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.service.MyUserLoginServiceImpl;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.web.SendMailService;

@Controller("userLoginAction")
@ParentPackage("interceptor")
@Namespace("/userlogin")
public class UserLoginAction extends BaseAction implements ModelDriven<UserLogin>{
	
	@Autowired
	private MyUserLoginServiceImpl myUserLoginServiceImpl;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	
	private UserLogin userLogin;
	
	private String account;
	private String mypassword;
	private String mailurl;
	/**
	 * 用户登录
	 * @return
	 */
	@Action(value="login",results={
			//@Result(name="success", location="/zone/%{myusername}",type="redirect"),
			@Result(name="success", location="%{prePage}",type="redirect"),
			@Result(name="input",location="/login.jsp"),
			@Result(name="mailActivity",location="/register/mailActivity.jsp"),
	})
	public String login(){
		/**
		 * 用户登录步骤
		 * 1、验证用户名、密码时候正确
		 * 2、验证用户的账号是否可用
		 */
		String returnString=INPUT;
		////登录成功
		//if(userLoginServiceImpl.login(account, mypassword)){
		if(myUserLoginServiceImpl.userlogin(account, mypassword)){
			userLogin = userLoginServiceImpl.findUserLoginByAccount(account);
			//验证用户的账号是否可用
			if(0==userLogin.getEnabled()){
				this.setMailurl(SendMailService.getEmailAddress(userLogin.getAccount()));
				this.setMyusername(userLogin.getNickname());
				returnString = "mailActivity";
			}else{
				myusername = userLogin.getNickname();
				session.setAttribute("myusername", myusername);
				session.setAttribute("zoneuser", myusername);
				if(null == prePage){ //如果用户登录前没有前往其他页面，那就默认跳转到用户的个人中心也页面
					//prePage = "/zone/"+myusername+"";
					prePage = "/userzone/infocenter.do";
				}
				returnString = SUCCESS;
			}
			
		}
		////登录失败
		addFieldError("loginFail", "用户名或者密码错误");
		
		return returnString;
	}
	
	
	@Action(value="sessionInvalidate",results={
			@Result(name="success", location="/login.jsp"),
	})
	public String session(){
		//session.removeAttribute("myusername");
		session.invalidate();
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
	
	public UserLogin getModel() {
		return userLogin;
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
	public String getMailurl() {
		return mailurl;
	}
	public void setMailurl(String mailurl) {
		this.mailurl = mailurl;
	}

	
}
