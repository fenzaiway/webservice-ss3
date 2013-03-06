package com.way.blog.loginfo.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;

@javax.jws.WebService(targetNamespace = "http://service.loginfo.blog.way.com/", serviceName = "LogInfoServiceService", portName = "LogInfoServicePort", wsdlLocation = "WEB-INF/wsdl/LogInfoServiceService.wsdl")
public class LogInfoServiceDelegate {

	com.way.blog.loginfo.service.LogInfoService logInfoService = new com.way.blog.loginfo.service.LogInfoService();

	public String save(String title, String content, String tags,
			String username, int visible, int logTypeId) {
		return logInfoService.save(title, content, tags, username, visible,
				logTypeId);
	}

	public String getUserAttentionData(String username, int pageSize,
			int startIndex, Object[] values) {
		return logInfoService.getUserAttentionData(username, pageSize,
				startIndex, values);
	}

	public int getRecoreCount(String username) {
		return logInfoService.getRecoreCount(username);
	}

}