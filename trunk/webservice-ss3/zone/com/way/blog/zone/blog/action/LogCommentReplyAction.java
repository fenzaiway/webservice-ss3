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
import com.way.blog.zone.blog.service.impl.LogCommentReplyServiceImpl;
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogCommentReply;

@ParentPackage("struts-default")
@Controller("logCommentReplyAction")
@Namespace("/logCommentReply")
public class LogCommentReplyAction extends BaseAction implements
		ModelDriven<LogCommentReply>, Preparable {
	@Autowired
	private LogCommentReplyServiceImpl logCommentReplyServiceImpl;
	@Autowired
	private LogCommentServiceImpl logCommentServiceImpl;
	@Autowired
	private LogCommentReply logCommentReply;
	
	HttpServletRequest request = null;
	HttpSession session = null;
	
	private String replyText; ///回复的内容
	private int commentId;	//被回复的评论ID
	private int logId;	///被评论的日志ID
	
	
	
	/**
	 * 保存评论 
	 */
	@Action(value="save",results={
			@Result(name="success",location="/loginfo/viewmore.do?zoneuser=%{zoneuser}&logInfoid=%{logId}",type="redirect"),
			@Result(name="login",location="/register/gotoLogin.do",type="redirect")
	})
	public String save(){
		if(null == myusername){
			return "login";
		}
		LogComment logComment = logCommentServiceImpl.findById(commentId);
		logCommentReply.setUsername(myusername);
		logCommentReply.setReplyContent(replyText);
		///设置双向关联
		Set<LogComment> logComments = new HashSet<LogComment>();
		logComments.add(logComment);
		logCommentReply.setLogComments(logComments);
		Set<LogCommentReply> logCommentReplys = new HashSet<LogCommentReply>();
		logCommentReplys.add(logCommentReply);
		logComment.setLogCommentReplys(logCommentReplys);
		logCommentReplyServiceImpl.save(logCommentReply);
		return SUCCESS;
	}
	
	public LogCommentReply getModel() {
		return logCommentReply;
	}

	public LogCommentReply getLogCommentReply() {
		return logCommentReply;
	}

	public void setLogCommentReply(LogCommentReply logCommentReply) {
		this.logCommentReply = logCommentReply;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getMyusername() {
		return myusername;
	}

	public void setMyusername(String myusername) {
		this.myusername = myusername;
	}

	public String getZoneuser() {
		return zoneuser;
	}

	public void setZoneuser(String zoneuser) {
		this.zoneuser = zoneuser;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}
	
}
