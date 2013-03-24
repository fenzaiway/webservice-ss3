package com.way.blog.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.zone.blog.service.impl.LogCommentReplyServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogCommentReply;
import com.way.blog.zone.entity.LogInfo;


@Controller("ajaxLogCommentReplayAction")
@ParentPackage("json-default")
@Namespace("/ajax/replay")
public class AjaxLogCommentReplayAction extends BaseAction {

	@Autowired private LogCommentReplyServiceImpl logCommentReplyServiceImpl;
	@Autowired private MessageServiceImpl messageServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	private LogCommentReply logCommentReply;
	
	public String replayContent;
	public String toUserName;
	public int commentid;
	public int logid;
	private LogInfo logInfo;
	
	/**
	 * 通过异步的方式保存回复
	 * @return
	 */
	@Action(value="saveReplay",results={
		@Result(type="json")
	})
	public String saveReplay(){
		int replayid = logCommentReplyServiceImpl.saveReplay(logid,commentid,myusername,toUserName,replayContent);
		///触发消息
		logInfo = logInfoServiceImpl.findById(logid);
		messageServiceImpl.saveMessage(myusername, 7, logInfo, null,toUserName);
		this.returnJsonByObject(logCommentReplyServiceImpl.getCommentListData(replayid));
		return null;
	}

	public String getReplayContent() {
		return replayContent;
	}

	public void setReplayContent(String replayContent) {
		this.replayContent = replayContent;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}
	
}
