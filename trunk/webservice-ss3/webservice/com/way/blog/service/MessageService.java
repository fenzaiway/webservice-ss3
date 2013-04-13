package com.way.blog.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.way.blog.base.entity.Message;
import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.util.JsonUtil;

@Service("messageService")
@WebService
public class MessageService extends SpringBeanAutowiringSupport {
	
	@Autowired private MessageServiceImpl messageServiceImpl;
	
	/**
	 * 根据用户名取得用户的消息列表
	 * @param username
	 * @return
	 */
	@WebMethod
	public String getUserMessage(@WebParam(name="username")String username){
		List<Message> messageList = messageServiceImpl.messageList(username);
		if(!messageServiceImpl.isListTEmpty(messageList)){
			return JsonUtil.returnJsonByObjectOfExpose(messageList);
		}else{
			return "";
		}
		
	}
}
