package com.way.blog.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;

@Controller("messageAction")
@ParentPackage("json-default")
@Namespace("/ajax/message")
public class MessageAction extends BaseAction{

	@Autowired private MessageServiceImpl messageServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	
	private int logInfoId;
	
	@Action(value="getHotMessageList",results={
			@Result(name="success",type="json")
	})
	public String getHotMessageList(){
		this.returnJsonByObject(messageServiceImpl.getMessageData(logInfoServiceImpl.findById(logInfoId)));
		return null;
	}

	public int getLogInfoId() {
		return logInfoId;
	}

	public void setLogInfoId(int logInfoId) {
		this.logInfoId = logInfoId;
	}
	
	
}
