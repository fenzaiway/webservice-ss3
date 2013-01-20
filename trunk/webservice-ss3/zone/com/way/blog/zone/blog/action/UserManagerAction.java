package com.way.blog.zone.blog.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @author fenzaiway
 * 用户空间后台管理Action
 */
@Namespace("/userManager")
@ParentPackage("struts-default")
@Controller("userManagerAction")
public class UserManagerAction extends ActionSupport implements Preparable {

	public void prepare() throws Exception {

	}
	
	
	////进入后台管理
	@Action(value="gotoUserManager",
			results={
			@Result(name="success",location="/WEB-INF/jsp/zone/userManager.jsp")
	})
	public String gotoUserManager(){
		return SUCCESS;
	}
	
}
