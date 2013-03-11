package com.way.blog.manager.admin.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.LogInfoData;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogInfo;

@Controller("adminLogInfo")
@ParentPackage("interceptor")
@Namespace("/admin/loginfo")
public class AdminLogInfo extends BaseAction {
	
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private LogInfo logInfo;
	private List<LogInfo> logInfoList = new ArrayList<LogInfo>();
	private LogInfoData logInfoData;
	
	
	private int loginfoid;
	
	/**
	 * 获取用户日志列表
	 * @return
	 */
	@Action(value="loginfoList",results={
			@Result(name="success",location="/admin/loginfo/loginfoList.jsp")
	})
	public String logInfoList(){
		paginationSupport = logInfoServiceImpl.findPageByQuery(LogInfoServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new Object[]{});
		paginationSupport.setUrl("admin/loginfo/loginfoList.do");
		logInfoList = paginationSupport.getItems();
		logInfoList  = logInfoServiceImpl.changeLogInfoText(logInfoList);
		return SUCCESS;
	}

	@Action(value="viewLoginfo",results={
			@Result(name="success",location="/admin/loginfo/loginfoDetail.jsp")
	})
	public String showLogInfoDetail(){
		logInfo = logInfoServiceImpl.findById(loginfoid);
		logInfoData = logInfoServiceImpl.getLogInfoData(logInfo, null);
		return SUCCESS;
	}
	
	public List<LogInfo> getLogInfoList() {
		return logInfoList;
	}

	public void setLogInfoList(List<LogInfo> logInfoList) {
		this.logInfoList = logInfoList;
	}

	public LogInfoData getLogInfoData() {
		return logInfoData;
	}

	public void setLogInfoData(LogInfoData logInfoData) {
		this.logInfoData = logInfoData;
	}

	public int getLoginfoid() {
		return loginfoid;
	}

	public void setLoginfoid(int loginfoid) {
		this.loginfoid = loginfoid;
	}
	
	
}
