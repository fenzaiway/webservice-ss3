package com.way.blog.zone.blog.action;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTypeServiceImpl;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogType;

@Controller("logTypeAction")
@ParentPackage("interceptor")
@Namespace("/logtype")
public class LogTypeAction extends BaseAction implements ModelDriven<LogType>,
		Preparable {
	@Autowired
	private LogTypeServiceImpl logTypeServiceImpl;
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	
	private LogType logType;
	
	private String myLogTypeName;	////提交的分类名称
	private int myLogTypeId;		//修改分类时该分类的ID
		
	public LogType getModel() {
		return logType;
	}
	
	/**
	 * 保存分类
	 */
//			@Result(name="success",location="/loginfo/gotologinfo.do?zoneuser=%{username}",type="redirect"),
//			@Result(name="input",location="/WEB-INF/jsp/loginfo/addLogType.jsp"),
	//@Result(name="login",location="/login.jsp")
	@Action(value="save",results={
			@Result(type="json"),
	})
	public String save(){
		String username = (String) session.getAttribute("myusername");
		///这里如果采用异步添加的话，可以防止用户Session失效
		if(null == username){
			return "login";
		}
		logType = new LogType();
		logType.setLogTypeName(myLogTypeName);
		logType.setUsername(username);
		///保存
		int nowId = logTypeServiceImpl.save(logType);
		///返回json
		LogType lt = logTypeServiceImpl.findById(nowId);
		lt.setBlogZone(null);
		super.returnJsonByObject(lt);
		return null;
	}
	
	/**
	 * 异步修改分类
	 * 1、根据ID取出分类
	 * 2、设置分类名称
	 * 3、重新保存
	 */
	@Action(value="update",results={
			@Result(type="json")
	})
	public String update(){
		logType = logTypeServiceImpl.findById(myLogTypeId);
		logType.setLogTypeName(myLogTypeName);
		logTypeServiceImpl.update(logType);
		return null;
	}
	
	/**
	 * 删除分类
	 */
	@Action(value="delete",results={
			@Result(type="json")
	})
	public String delete(){
		////为避免恶意删除，先做判断
		if(1 == myLogTypeId){
			return null;
		}
		
		////1、先取出分类目录下的日志
		logType = logTypeServiceImpl.findById(myLogTypeId);
		////判断分类下是否有日志
		if(null!=logType.getLogInfos() && !logType.getLogInfos().isEmpty()){
			//原来的分类有日志
			//2、取出默认分类目录
			LogType defalueLogType = logTypeServiceImpl.myFindByProperty("isDefaultLogType", 1);
			//3、将该分类的日志移动到分类目录
			if(null == defalueLogType.getLogInfos() || defalueLogType.getLogInfos().isEmpty() && !logType.getLogInfos().isEmpty()){
				Set<LogInfo> logInfos = new HashSet<LogInfo>();
				for(LogInfo logInfo : logType.getLogInfos()){
					logInfo.setLogType(defalueLogType);///重新设置关联关系
					logInfos.add(logInfo);
				}
				defalueLogType.setLogInfos(logInfos);
			}else{ ////原来分类日志有值
				for(LogInfo logInfo : logType.getLogInfos()){
					logInfo.setLogType(defalueLogType);
					defalueLogType.getLogInfos().add(logInfo);
				}
			}
			////4、保存默认日志
			logTypeServiceImpl.update(defalueLogType);
		}
		//5、将该分类目录删除
		//logType.getLogInfos()
		logType.getLogInfos().removeAll(logType.getLogInfos());
		logTypeServiceImpl.delete(logType);
		
		return null;
	}
	
	
	/**
	 * 进入添加分类
	 */
	@Action(value="gotoAddLogType",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/addLogType.jsp"),
	})
	public String gotoAddLogType(){
		return SUCCESS;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public String getMyLogTypeName() {
		return myLogTypeName;
	}

	public void setMyLogTypeName(String myLogTypeName) {
		this.myLogTypeName = myLogTypeName;
	}

	public int getMyLogTypeId() {
		return myLogTypeId;
	}

	public void setMyLogTypeId(int myLogTypeId) {
		this.myLogTypeId = myLogTypeId;
	}
	
}
