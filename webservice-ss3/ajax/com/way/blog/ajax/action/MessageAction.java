package com.way.blog.ajax.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.Message;
import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;

@Controller("messageAction")
@ParentPackage("json-default")
@Namespace("/ajax/message")
public class MessageAction extends BaseAction{

	@Autowired private MessageServiceImpl messageServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	
	private List<Message> messageList = new ArrayList<Message>();
	
	private int logInfoId;
	
	@Action(value="getHotMessageList",results={
			@Result(name="success",type="json")
	})
	public String getHotMessageList(){
		this.returnJsonByObject(messageServiceImpl.getMessageData(logInfoServiceImpl.findById(logInfoId)));
		return null;
	}

	/**
	 * 读取用户的消息列表
	 * @return
	 */
	@Action(value="getUserMessageList",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/message.jsp"),
	})
	public String getUserMessageList(){
		messageList = messageServiceImpl.messageList(myusername);
		return SUCCESS;
	}
	
	public int getLogInfoId() {
		return logInfoId;
	}

	public void setLogInfoId(int logInfoId) {
		this.logInfoId = logInfoId;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
	
}
