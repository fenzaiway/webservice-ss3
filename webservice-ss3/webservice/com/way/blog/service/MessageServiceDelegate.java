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

@javax.jws.WebService(targetNamespace = "http://service.blog.way.com/", serviceName = "MessageServiceService", portName = "MessageServicePort", wsdlLocation = "WEB-INF/wsdl/MessageServiceService.wsdl")
public class MessageServiceDelegate {

	com.way.blog.service.MessageService messageService = new com.way.blog.service.MessageService();

	public String getUserMessage(String username) {
		return messageService.getUserMessage(username);
	}

}