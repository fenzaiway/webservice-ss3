package com.way.blog.zone.blog.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

/**
 * 用户空间控制器
 * @author fenzaiway
 *
 */
@Controller("userZoneAction")
@ParentPackage("interceptor")
@Namespace("/userzone")
public class UserZoneAction extends BaseAction {

	@Autowired private LogTagServiceImpl logTagServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	
	private List<LogTag> logTagList = new ArrayList<LogTag>();
	private List<LogInfo> logInfoList = new ArrayList<LogInfo>(); 
	
	/**
	 * 进入用户的个人主页
	 * @return
	 */
//	@Action(value="home",results={
//			@Result(name="success",location="/WEB-INF/jsp/zone/userhome.jsp"),
//	})
//	public String gotoUserHome(){
//		/**
//		 * 步骤：
//		 * 1、加载用户的个人资料（暂时不做）
//		 * 2、加载用户的空间资料（暂时不做）
//		 * 3、加载用户的所有标签
//		 * 4、加载日志信息，包括转载、评论数，
//		 * 5、加载用户的最新照片（由于照片功能还没有完善，暂时不做）
//		 */
//		
//		//3、加载用户的所有标签
//		logTagList = logTagServiceImpl.getUserLogInfoTagList(myusername);
//		
//		//4、加载用户所有的日志
//		paginationSupport = logInfoServiceImpl.findPageByQuery("from LogInfo where username=? and deleteStatue=0", PaginationSupport.PAGESIZE, startIndex, myusername);
//		paginationSupport.setUrl("userzone/home.do");
//		logInfoList = paginationSupport.getItems();
//		//logInfoList = logInfoServiceImpl.findByProperty("username", zoneuser);
//		if(null != logInfoList){
//			logInfoList = logInfoServiceImpl.changeLogInfoText(logInfoList);
//		}
//		
//		
//		
//		return SUCCESS;
//	}

	/**
	 * 转入到用户的个人中心，如果用户没有登录，那么不能访问这个路径
	 * @return
	 */
	@Action(value="infocenter",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/userzone.jsp"),
	})
	public String userInfoCenter(){
		return SUCCESS;
	}
	
	public List<LogTag> getLogTagList() {
		return logTagList;
	}


	public void setLogTagList(List<LogTag> logTagList) {
		this.logTagList = logTagList;
	}


	public List<LogInfo> getLogInfoList() {
		return logInfoList;
	}


	public void setLogInfoList(List<LogInfo> logInfoList) {
		this.logInfoList = logInfoList;
	}
	
	
}
