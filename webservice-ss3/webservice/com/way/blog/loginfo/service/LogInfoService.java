package com.way.blog.loginfo.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;

/**
 * 向外部发布关于文章内容处理的接口
 * @author fenzaiway
 *
 */
@WebService
public class LogInfoService extends SpringBeanAutowiringSupport implements ILogInfoService{
	
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	
	/**
	 * 保存文章
	 * @param title 标题
	 * @param content 内容
	 * @param tags 标签
	 * @param username 用户名 
	 * @param visible 可见性
	 * @logTypeId 分类日志的Id,客户端调用的时候，使用0，表示发表到用户的个人日志分类目录下
	 * @return json格式数据，1表示成功，-1表示失败
	 */
	@WebMethod
	public String save(@WebParam(name="title")String title, @WebParam(name="content")String content, @WebParam(name="tags")String tags, @WebParam(name="username")String username, @WebParam(name="visible")int visible, @WebParam(name="logTypeId")int logTypeId){
		return logInfoServiceImpl.save(title, content, tags, username, visible, logTypeId);
	}
	
	/**
	 * 通过json返回用户请求数据
	 * @param username 哪个用户
	 * @param pageSize 每页请求多少条数据
	 * @param startIndex //数据开始点
	 * @param values //分页参数
	 * @return
	 */
	@WebMethod
	public String getUserAttentionData(@WebParam(name="username")String username, @WebParam(name="pageSize")int pageSize, @WebParam(name="startIndex")int startIndex,@WebParam(name="values")Object... values){
		return logInfoServiceImpl.getUserAttentionData(username, pageSize, startIndex, values);
	}
	
	/**
	 * 根据用户名获取用户的记录
	 * @param username
	 * @return
	 */
	@WebMethod
	public int getRecoreCount(@WebParam(name="username")String username){
		return logInfoServiceImpl.getRecoreCount(username);
	}
	
	/**
	 * 根据日志ID返回日志的JSON数据格式
	 * @param logId
	 * @return
	 */
	@WebMethod
	public String getLogInfoById(@WebParam(name="logId")String logId){
		return logInfoServiceImpl.getLogInfoById(logId);
	}
	
}
