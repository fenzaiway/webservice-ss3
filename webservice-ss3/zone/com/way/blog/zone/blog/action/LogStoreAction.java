package com.way.blog.zone.blog.action;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.ReturnStatus;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogStoreServiceImpl;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogStore;
/**
 * 日志收藏控制器
 * project_name webservice-ss3
 *
 * package com.way.blog.zone.blog.action
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-17下午12:26:17
 *
 *
 */
@Controller("logStoreAction")
@ParentPackage("interceptor")
@Namespace("/logstore")
public class LogStoreAction extends BaseAction implements ModelDriven<LogStore>{

	@Autowired
	private LogStoreServiceImpl logStoreServiceImpl;
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	 
	private List<LogStore> logStoreList = new ArrayList<LogStore>();
	
	private LogInfo myLogInfo;
	private LogStore myLogStore;
	
	private int logInfoId;	///日志Id
	private String mySourceUrl; ///源日志地址
	
	/*
	 * 转到日志收藏页面
	 */
	@Action(value="gotologstore",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/logstore.jsp"),
	})
	public String gotoLogStore(){
		mySourceUrl = "loginfo/viewmore.do?zoneuser="+zoneuser+"&logInfoid="+logInfoId;
		System.out.println("logInfoId"+logInfoId);
		myLogInfo = logInfoServiceImpl.findById(logInfoId);
		return SUCCESS;
	}
	
	@Action(value="save",results={
			@Result(name="success",location="/loginfo/viewmore.do?zoneuser=%{zoneuser}&logInfoid=%{logInfoId}",type="redirect")
	})
	public String save(){
		//判断用户的这篇日志是不是原创日志，如果不是，则取出该篇日志对应的原创日志
		myLogInfo = logInfoServiceImpl.findOriginalLogInfo(logInfoId);///取出来的是原创日志
		myLogStore.setStoreTime(MyFormatDate.getNowDate());
		myLogStore.setLogInfoTitle(myLogInfo.getLogTitle());
		myLogStore.setSourceUserName(myLogInfo.getUsername());
		myLogStore.setUsername(myusername);
		
		///设置双向关联
		myLogStore.setLogInfo(myLogInfo);
		if(null != myLogInfo.getLogStores() && !myLogInfo.getLogStores().isEmpty()){
			myLogInfo.getLogStores().add(myLogStore);
		}else{
			Set<LogStore> logStores = new HashSet<LogStore>();
			logStores.add(myLogStore);
			myLogInfo.setLogStores(logStores);
		}
		logStoreServiceImpl.save(myLogStore);
		return SUCCESS;
	}

	/**
	 * 判断该用户是不会已经收藏过
	 * @return
	 */
	@Action(value="checkIsUserStore",results={
			@Result(type="json")
	})
	public String checkIsUserStore(){
		mySourceUrl = "loginfo/viewmore.do?zoneuser="+zoneuser+"&logInfoid="+logInfoId;
		//myLogStore = logStoreServiceImpl.myFindByProperty("username", myusername);
		logStoreList = logStoreServiceImpl.find("from LogStore where username=? and sourceUrl=?", new String[]{myusername,mySourceUrl});
		ReturnStatus returnStatus = new ReturnStatus();
		if(null !=logStoreList && !logStoreList.isEmpty()){
			returnStatus.setStatus(1);
		}else{
			returnStatus.setStatus(0);
		}
		
		this.returnJsonByObject(returnStatus);
		return null;
	}
	
	public LogInfo getMyLogInfo() {
		return myLogInfo;
	}

	public void setMyLogInfo(LogInfo myLogInfo) {
		this.myLogInfo = myLogInfo;
	}

	public LogStore getMyLogStore() {
		return myLogStore;
	}

	public void setMyLogStore(LogStore myLogStore) {
		this.myLogStore = myLogStore;
	}

	public int getLogInfoId() {
		return logInfoId;
	}

	public void setLogInfoId(int logInfoId) {
		this.logInfoId = logInfoId;
	}

	public LogStore getModel() {
		// TODO Auto-generated method stub
		return myLogStore;
	}

	public String getMySourceUrl() {
		return mySourceUrl;
	}

	public void setMySourceUrl(String mySourceUrl) {
		this.mySourceUrl = mySourceUrl;
	}

	public List<LogStore> getLogStoreList() {
		return logStoreList;
	}

	public void setLogStoreList(List<LogStore> logStoreList) {
		this.logStoreList = logStoreList;
	}
	
}
