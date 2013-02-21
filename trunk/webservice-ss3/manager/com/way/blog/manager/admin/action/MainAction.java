package com.way.blog.manager.admin.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;

@Controller("mainAction")
@ParentPackage("interceptor")
@Namespace("/admin/main")
public class MainAction extends BaseAction {
	
	
	@Action(value="gotoMainMenu",results={
			@Result(name="success",location="/admin/main.html")
	})
	public String gotoMainMenu(){
		return SUCCESS;
	}
}
