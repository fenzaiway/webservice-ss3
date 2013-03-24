package com.way.blog.user.action;

import java.util.ArrayList;
import java.util.List;

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
import com.way.blog.util.web.SendMailService;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;

@Controller("userRegisterAction")
@ParentPackage("struts-default")
@Namespace("/register")
public class UserRegisterAction extends BaseAction implements ModelDriven<UserRegister>,Preparable {
	private String username;
	private String code;
	private  String SUBJECT = "感谢使用轻轻一点博客系统，请完成邮件验证";
	private  String VERIFIHTML = "<html><head><title>请完成邮件验证</title></head><body>"+username+"，您好！<br/></br/><br/>点击以下链接完成邮箱验证并激活在轻轻一点的帐号：<br/><a href='http://192.168.32.1:8090/ss3/register/userverify.do?code="+code+"'>http://192.168.32.1:8090/ss3/register/userverify.do?code=%{code}</a><br/>如无法点击，请将链接拷贝到浏览器地址栏中直接访问。</body></html>";
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
	@Autowired
	private SendMailService sendMailService;
	
	private UserLogin userLogin;
	
	private UserRegister userRegister;
	
	private String account;
	
	private String mypassword;
	
	private String mailurl;

//	@Override
//	public void prepare() throws Exception {
//		// TODO Auto-generated method stub
//		super.prepare();
//		if(null!=userRegister.getEmail() ){
//			this.setAccount(userRegister.getEmail());
//			this.setMailurl(sendMailService.getEmailAddress(userRegister.getEmail()));
//		}
//	}
	
	/**
	 * 用户注册
	 * 注册成功的用户跳转到用户详细信息页面
	 * 可以在这里修改注册成功后跳转的路径
	 */
	@Action(value="save",results={
			//@Result(name="success", type = "redirectAction",location="mailActivity.do",params={"account","${username}"}),
			@Result(name="success", type = "redirectAction",location="gotoLogin.do"), ////测试的时候，用户注册完就算注册成功了，先不进行邮箱验证
			@Result(name="input", location="/register/register.jsp")
	})
	public String save(){
		System.out.println("-----用户注册------");
		String IP = request.getRemoteAddr();
		userRegister.setIp(IP);
		try {
			userRegisterServiceImpl.mySave(userRegister);
			///this.setUsername(userRegister.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("saveError", e.getMessage());
			return INPUT;
		}
		///注册的时候同时为用户开通空间(后期是用户通过邮箱验证后才开通空间)
		blogZoneServiceImpl.createBlog(userRegister);
		////注册成功后，将用户名保存到Session中
		//session.setAttribute("myusername",userRegister.getUsername());
		//为用户设置一个默认的头像
		//userHeadImgAction.save();
		
		
		return SUCCESS;
	}
	
	/**
	 * 进入验证邮箱页面
	 * @return
	 */
	@Action(value="mailActivity",results={
			@Result(name="success", location="/register/register.jsp"),
	})
	public String gotoMailActivity(){
		System.out.println("account============" + account);
		//用户注册成功后，向用户的邮箱发送验证地址
		UserRegister ur = userRegisterServiceImpl.myFindByProperty("username", account);
		List<String> emailList = new ArrayList<String>();
		emailList.add(ur.getEmail());
		
		//this.setCode(ur.getVerifiCode());
		
		String html = "<html><head><title>请完成邮件验证</title></head><body>"+ur.getUsername()+"，您好！<br/></br/><br/>点击以下链接完成邮箱验证并激活在轻轻一点的帐号：<br/><a href='http://127.0.0.1:8090/ss3/register/userverify.do?code="+ur.getVerifiCode()+"'>http://192.168.32.1:8090/ss3/register/userverify.do?code="+ur.getVerifiCode()+"</a><br/>如无法点击，请将链接拷贝到浏览器地址栏中直接访问。</body></html>";
		sendMailService.sendMailByHtml(emailList, SUBJECT, html, null);
		//session.setAttribute("username", ur.getEmail());
		//session.setAttribute("mailurl",sendMailService.getEmailAddress(ur.getEmail()) );
		this.setUsername(ur.getEmail());
		this.setMailurl(sendMailService.getEmailAddress(ur.getEmail()));
		return SUCCESS;
	}
	
	/**
	 * 将用户的注册、登录状态设置为可用状态
	 * @return
	 */
	//userverify
	@Action(value="userverify",results={
			@Result(name="success", location="/zone/fenzaiway",type="redirect"),
	})
	public String userverify(){
		System.out.println("++++++++++++++==" +code);
		/**
		 * 根据验证码取出用户的注册记录，将用户的注册账号设置为可用状态
		 * 
		 */
		userRegister = userRegisterServiceImpl.myFindByProperty("verifiCode", code);
		userRegister.setEnabled(1);
		userRegister.getUserLogin().setEnabled(1);
		userRegisterServiceImpl.update(userRegister);
		return SUCCESS;
	}
	
	
	public String reSend(){
		return null;
	}
	
	/**
	 * 测试发送邮件
	 * @return
	 */
	@Action(value="sendMail",results={
			@Result(name="success", location="/zone/fenzaiway",type="redirect"),
	})
	public String sendMail(){
		//sendMailService.sendMailByText("fenzaiway@qq.com", "测试邮件发送", "这是一封测试发送邮件的邮件");
		List<String> userEmailList = new ArrayList<String>();
		userEmailList.add("fenzaiway@qq.com");
		String htmlText="<html><head><title>测试封装的发送html</title></head><body>这是一篇使用封装的HTML邮件，<a href='http://www.baidu.com'>百度</a></body></html>";
		sendMailService.sendMailByHtml(userEmailList, "测试发送html邮件", htmlText, null);
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
		if(userLoginServiceImpl.login(account, mypassword)){
			userLogin = userLoginServiceImpl.findUserLoginByAccount(account);
			myusername = userLogin.getNickname();
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public String getMailurl() {
		return mailurl;
	}

	public void setMailurl(String mailurl) {
		this.mailurl = mailurl;
	}
	
	
}
