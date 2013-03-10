package com.way.blog.zone.blog.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.manager.admin.entity.TagClickCount;
import com.way.blog.manager.admin.service.impl.TagClickCountServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogTag;

@Controller("logTagAction")
@ParentPackage("struts-default")
@Namespace("/logtag")
public class LogTagAction extends BaseAction implements ModelDriven<LogTag>{

	@Autowired TagClickCountServiceImpl tagClickCountServiceImpl;
	@Autowired LogTag logTag;
	@Autowired TagClickCount tagClickCount;
	
	
	
	
	/**
	 * 根据用户传递的tagName来查询出与这个tag相关的文章
	 * @return
	 */
	@Action(value="findLogInfoByTagName",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/tagfeed.jsp"),
	})
	public String findLogInfoByTagName(){
		
		saveTagClick(logTag); ///保存每次用户通过点击标签进行查询的记录
		
		return SUCCESS;
	}

	public void saveTagClick(LogTag myLogTag){
		tagClickCount.setIp(request.getRemoteAddr());
		tagClickCount.setTagName(myLogTag.getTagName());
		tagClickCount.setClickTime(MyFormatDate.getNowDate());
		if(null == myusername){
			tagClickCount.setUsername("游客");
		}else{
			tagClickCount.setUsername(myusername);
		}
		tagClickCountServiceImpl.save(tagClickCount);
	}
	
	
	
	public LogTag getModel() {
		// TODO Auto-generated method stub
		return logTag;
	}
	
	
}
