package com.way.blog.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.entity.BlogZone;


@Controller("ajaxBlogZoneAction")
@ParentPackage("json-default")
@Namespace("/ajax/zone")
public class AjaxBlogZoneAction extends BaseAction {
	
	@Autowired private BlogZoneServiceImpl blogZoneServiceImpl;
	
	@Autowired private BlogZone blogZone;
	
	/**
	 * 获取用户的记录
	 * @return
	 */
	@Action(value="record")
	public String getRecord(){
		///System.out.println("testname==" + testname);
		
		this.returnJS(""+blogZoneServiceImpl.getUserZoneRecord(zoneuser));
		return null;
	}

	
}
