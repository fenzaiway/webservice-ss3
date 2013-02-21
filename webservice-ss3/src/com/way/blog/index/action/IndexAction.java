package com.way.blog.index.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.user.entity.UserHeadImg;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogInfo;

/**
 * 首页内容控制器
 * project_name webservice-ss3
 *
 * package com.way.blog.index.action
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-13下午10:40:35
 *
 *
 */
@Controller("indexAction")
@ParentPackage("interceptor")
@Namespace("/index")
public class IndexAction extends BaseAction {
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	
	private List<LogInfo> logInfoList;
	private List<UserLogin> userLoginList;
	private List<UserHeadImg> userHeadImgList;
	private UserLogin userLogin;
	
	//private String 
	/**
	 * 加载首页内容
	 * @return
	 */
	@Action(value="loadIndexContent",results={
			@Result(name="success",location="/index.jsp")
	})
	public String loadIndexContent(){
		paginationSupport = logInfoServiceImpl.findPageByQuery("from LogInfo where 1=1 ", PaginationSupport.PAGESIZE, startIndex, new String[]{});
		paginationSupport.setUrl("index/loadIndexContent.do");
		//logInfoList = logInfoServiceImpl.loadAll();
		logInfoList = paginationSupport.getItems();
		this.loadUserLogin(logInfoList);
		return SUCCESS;
	}
	
	///根据日志列表获取用户列表
	public void loadUserLogin(List<LogInfo> logInfoList){
		userLoginList = new ArrayList<UserLogin>();
		userHeadImgList = new ArrayList<UserHeadImg>();
		for (LogInfo logInfo : logInfoList) {
			userLogin = userLoginServiceImpl.myFindByProperty("username", logInfo.getUsername());
			userLoginList.add(userLogin);
			userHeadImgList.add(userLogin.getUserHeadImg());
		}
	}
	
	@Action(value="loadUserLogin",results={
			@Result(type="json")
	})
	public String loadUserLogin(){
		//System.out.println(userLogin.getUsername());
		this.returnJsonByObject(userLoginServiceImpl.myFindByProperty("username", userLogin.getUsername()));
		return null;
	}
	
	public List<LogInfo> getLogInfoList() {
		return logInfoList;
	}

	public void setLogInfoList(List<LogInfo> logInfoList) {
		this.logInfoList = logInfoList;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
	public List<UserLogin> getUserLoginList() {
		return userLoginList;
	}
	public void setUserLoginList(List<UserLogin> userLoginList) {
		this.userLoginList = userLoginList;
	}
	public List<UserHeadImg> getUserHeadImgList() {
		return userHeadImgList;
	}
	public void setUserHeadImgList(List<UserHeadImg> userHeadImgList) {
		this.userHeadImgList = userHeadImgList;
	}

	
}
