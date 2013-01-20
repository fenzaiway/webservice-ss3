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
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;

/**
 * 日志评论Action 
 */
@ParentPackage("struts-default")
@Controller("logCommentAction")
@Namespace("/logComment")
public class LogCommentAction extends ActionSupport implements ModelDriven<LogComment>,
		Preparable {
	
	@Autowired
	private LogCommentServiceImpl logCommentServiceImpl;
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	
	private LogComment logComment;
	HttpServletRequest request = null;
	HttpSession session = null;
	
	private String myusername;	//当前评论用户
	private String zoneuser;	///评论的是哪个用户的日志
	private String commentText; ///评论的内容
	private int logId;	///被评论的日志ID
	public void prepare() throws Exception {
		if(null == logComment){
			logComment = new LogComment();
		}
		request = ServletActionContext.getRequest();
		session = request.getSession();
		myusername = (String) session.getAttribute("myusername");
		zoneuser = (String) session.getAttribute("zoneuser");
	}
	
	
	/**
	 * 保存评论的日志
	 */
	@Action(value="save",results={
			@Result(name="success",location="/loginfo/viewmore.do?zoneuser=%{zoneuser}&logInfoid=%{logId}",type="redirect"),
			@Result(name="login",location="/register/gotoLogin.do",type="redirect")
	})
	public String save(){
		LogInfo logInfo = logInfoServiceImpl.findById(logId);
		logComment.setCommentContent(commentText);
		//先判断用户是否登录，如果没有登录，跳转到登录页面
		if(null == myusername){
			return "login";
		}
		logComment.setUsername(myusername);
		///设置双向关联
		logComment.setLogInfo(logInfo);
		Set<LogComment> logComments = new HashSet<LogComment>();
		logComments.add(logComment);
		logInfo.setLogComments(logComments);
		logCommentServiceImpl.save(logComment);
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
