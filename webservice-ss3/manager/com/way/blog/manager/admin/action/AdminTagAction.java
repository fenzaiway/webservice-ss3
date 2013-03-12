package com.way.blog.manager.admin.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.LogTagDetail;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.LogTag;

@Controller("adminTagAction")
@ParentPackage("interceptor")
@Namespace("/admin/tag")
public class AdminTagAction extends BaseAction {

	@Autowired private TagServiceImpl tagServiceImpl;
	@Autowired private LogTagServiceImpl logTagServiceImpl;
	
	private List<Tag> tagList = new ArrayList<Tag>(); ///系统标签列表
	private List<LogTag> logTagList = new ArrayList<LogTag>();  ///日志标签列表
	
	private LogTagDetail logTagDetail; ///日志标签详细资料
	
	private int tagId;  ////标签Id
	
	
	@Action(value="tagList",results={
			@Result(name="success",location="/admin/tag/systagList.jsp")
	})
	public String tagList(){
		paginationSupport = tagServiceImpl.findPageByQuery(TagServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new Object[]{});
		tagList = paginationSupport.getItems();
		paginationSupport.setUrl("admin/tag/tagList.do");
		return SUCCESS;
	}
	
	@Action(value="logtagList",results={
			@Result(name="success",location="/admin/tag/logtagList.jsp")
	})
	public String logTagList(){
		paginationSupport = logTagServiceImpl.findPageByQuery(LogTagServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new Object[]{});
		logTagList = paginationSupport.getItems();
		paginationSupport.setUrl("admin/tag/logtagList.do");
		return SUCCESS;
	}
	
	@Action(value="logTagDetail",results={
			@Result(name="success",location="/admin/tag/logtagDetail.jsp")
	})
	public String showLogTagDetail(){
		logTagDetail = logTagServiceImpl.getLogTagDetailByTagId(tagId);
		return SUCCESS;
	}
	
	@Action(value="deleteByLogTagId",results={
			@Result(name="success",location="/admin/tag/logtagList.do", type="redirect")
	})
	public String deleteByLogTagId(){
		logTagServiceImpl.delete(tagId);
		return SUCCESS;
	}
	
	
	
	
	

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public List<LogTag> getLogTagList() {
		return logTagList;
	}

	public void setLogTagList(List<LogTag> logTagList) {
		this.logTagList = logTagList;
	}
	
	public LogTagDetail getLogTagDetail() {
		return logTagDetail;
	}

	public void setLogTagDetail(LogTagDetail logTagDetail) {
		this.logTagDetail = logTagDetail;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	
}