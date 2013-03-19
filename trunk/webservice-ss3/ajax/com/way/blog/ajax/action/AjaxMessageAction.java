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

/**
 * 通过异步的方式获取消息
 * @author fenzaiway
 *
 */
@Controller("ajaxMessageAction")
@ParentPackage("json-default")
@Namespace("/ajax/message")
public class AjaxMessageAction extends BaseAction {

	@Autowired private MessageServiceImpl messageServiceImpl;
	@Autowired private Message message;
	
	private List<Message> messageList = new ArrayList<Message>();
	
	/**
	 * 获取最新的未读消息
	 * @return
	 */
	@Action(value="getNewMessage",results={
			@Result(name="success",type="json")
	})
	public String getNewMessage(){
		/**
		 * 步骤：
		 * 1、根据用户名获取属于该用户的未读消息
		 * 2、根据消息类型获取，如果消息类型为5 表示公告信息的话，而且是未读消息的，就可以获取出来
		 */
		
		messageList = messageServiceImpl.getNewMessage(myusername);
		if(null!=messageList && !messageList.isEmpty()){
			this.returnJsonByObjectOfExpose(messageList);
		}else{
			messageList = new ArrayList<Message>();
			this.returnJsonByObjectOfExpose(messageList);
		}
		
		return null;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
	
}
