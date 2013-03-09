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

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;

/**
 * 日志评论Action 
 */
@ParentPackage("interceptor")
@Controller("logCommentAction")
@Namespace("/logComment")
public class LogCommentAction extends BaseAction implements ModelDriven<LogComment>,
		Preparable {
	
	@Autowired
	private LogCommentServiceImpl logCommentServiceImpl;
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired
	private LogComment logComment;
	HttpServletRequest request = null;
	HttpSession session = null;
	
	private String commentText; ///评论的内容
	private int logId;	///被评论的日志ID
	
	
	
	/**
	 * 保存评论的日志
	 */
	@Action(value="save",results={
			@Result(name="success",location="/loginfo/viewmore.do?zoneuser=%{zoneuser}&logInfoid=%{logId}",type="redirect"),
			@Result(name="login",location="/register/gotoLogin.do",type="redirect")
	})
	public String save(){
		
		//logCommentServiceImpl.save(logComment);
		logCommentServiceImpl.save(logId, myusername, commentText);
		return SUCCESS;
	}
	
	public LogComment getModel() {
		return logComment;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

}
