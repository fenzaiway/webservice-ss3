package com.way.blog.zone.blog.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.entity.LogTag;

@Controller("logTagAction")
@ParentPackage("struts-default")
@Namespace("/logtag")
public class LogTagAction extends BaseAction implements ModelDriven<LogTag>{

	@Autowired LogTag logTag;
	
	
	/**
	 * 根据用户传递的tagName来查询出与这个tag相关的文章
	 * @return
	 */
	@Action(value="findLogInfoByTagName",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/tagfeed.jsp"),
	})
	public String findLogInfoByTagName(){
		
		return SUCCESS;
	}

	public LogTag getModel() {
		// TODO Auto-generated method stub
		return logTag;
	}
	
	
}
