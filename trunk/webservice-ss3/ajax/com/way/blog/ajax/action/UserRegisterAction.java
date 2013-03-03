package com.way.blog.ajax.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.user.service.impl.UserRegisterServiceImpl;
import com.way.blog.util.MD5;
import com.way.blog.util.web.SendMailService;

/**
 * 用户注册Ajax控制器
 * @author fenzaiway
 *
 */
@Controller("ajaxUserRegisterAction")
@ParentPackage("json-default")
@Namespace("/ajax/register")
public class UserRegisterAction extends BaseAction {
	@Autowired
	private SendMailService sendMailService;
	@Autowired
	private UserRegisterServiceImpl userRegisterServiceImpl;
	private  String SUBJECT = "感谢使用轻轻一点博客系统，请完成邮件验证";
	private String username;
	
	/**
	 * 重新发送邮件
	 * @return
	 */
	@Action(value="resend",results={
			@Result(type="json"),
	})
	public String reSend(){
		UserRegister ur = userRegisterServiceImpl.myFindByProperty("username", username);
		List<String> emailList = new ArrayList<String>();
		emailList.add(ur.getEmail());
		
		//this.setCode(ur.getVerifiCode());
		if(null == ur.getVerifiCode() || "".equals(ur.getVerifiCode())){	///验证码位空获取不存在，重新设置
			ur.setVerifiCode(MD5.code(userRegisterServiceImpl.getEncrypt(ur)));
			userRegisterServiceImpl.update(ur);
		}
		
		String html = "<html><head><title>请完成邮件验证</title></head><body>"+ur.getUsername()+"，您好！<br/></br/><br/>点击以下链接完成邮箱验证并激活在轻轻一点的帐号：<br/><a href='http://127.0.0.1:8090/ss3/register/userverify.do?code="+ur.getVerifiCode()+"'>http://192.168.32.1:8090/ss3/register/userverify.do?code="+ur.getVerifiCode()+"</a><br/>如无法点击，请将链接拷贝到浏览器地址栏中直接访问。</body></html>";
		sendMailService.sendMailByHtml(emailList, SUBJECT, html, null);
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
