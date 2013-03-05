package com.way.blog.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;


/**
 * 请求文章数据
 * @author fenzaiway
 *
 */
@Controller("ajaxLogInfoAction")
@ParentPackage("json-default")
@Namespace("/ajax/loginfo")
public class AjaxLogInfoAction extends BaseAction {

	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	
	///加载用户关注数据
	@Action(value="loadLogInfo",results={
			@Result(name="success",type="json")
	})
	public String loadLogInfoDate(){
		//paginationSupport = logInfoServiceImpl.loadLogInfoDate(zoneuser, PaginationSupport.PAGESIZE, startIndex, null);
		//paginationSupport.setItems(logInfoServiceImpl.changeLogInfoText(paginationSupport.getItems()));
		//this.pageClass2Json(paginationSupport);
		this.returnJson(logInfoServiceImpl.getUserAttentionData(zoneuser, PaginationSupport.PAGESIZE, startIndex, null));
		return  null;
	}
	
	/**
	 * 通过接口的方式重新加载数据
	 */
}
