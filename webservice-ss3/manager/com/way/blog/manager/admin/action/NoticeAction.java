package com.way.blog.manager.admin.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.Message;
import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("noticeAction")
@ParentPackage("interceptor")
@Namespace("/admin/notice")
public class NoticeAction extends BaseAction {

	@Autowired private MessageServiceImpl messageServiceImpl;
	
	private String content;
	private List<Message> noticeList;

	/**
	 * 获取公告列表
	 * @return
	 */
	@Action(value="noticeList",results={
			@Result(name="success",location="/admin/notice/noticeList.jsp")
	})
	public String list(){
		paginationSupport = messageServiceImpl.getPaginationSupport(PaginationSupport.PAGESIZE, startIndex);
		noticeList = paginationSupport.getItems();
		return SUCCESS;
	}
	
	/**
	 * 添加公告
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success",location="/admin/notice/noticeList.do",type="redirect")
	})
	public String save(){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContextImpl.getAuthentication().getName();
		messageServiceImpl.saveMessage(username, 5, null, content, null);
		return SUCCESS;
	}
	
	
	@Action(value="gotoAddNotice",results={
			@Result(name="success",location="/admin/notice/addNotice.jsp")
	})
	public String gotoAdd(){
		return SUCCESS;
	}
	
	/**
	 * 查询
	 * @return
	 */
	@Action(value="noticeSearch",results={
			@Result(name="success",location="/admin/notice/noticeList.jsp")
	})
	public String noticeSearch(){
		paginationSupport = messageServiceImpl.getPaginationSupport(PaginationSupport.PAGESIZE, startIndex);
		noticeList = paginationSupport.getItems();
		return SUCCESS;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Message> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Message> noticeList) {
		this.noticeList = noticeList;
	}
	
	
}
