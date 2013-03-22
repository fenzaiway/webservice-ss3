package com.way.blog.manager.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Tag tag;
	
	private String logTagIds;
	

	
	private String logTagName; ///日志标签名
	
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
	
	@Action(value="sysTagUpdate",results={
			@Result(name="success",location="/admin/tag/tagList.do", type="redirect")
	})
	public String sysTagUpdate(){
		System.out.println("tagid == " + tag.getId());
		System.out.println("---logTagids = =" + logTagIds);
		tagServiceImpl.updateSysTag(tag.getId(), logTagIds);
		return SUCCESS;
	}
	
	@Action(value="gotoSysTagEdit",results={
			@Result(name="success",location="/admin/tag/sysTagEdit.jsp"),
	})
	public String gotoSysTagEdit(){
		tag = tagServiceImpl.findById(tagId);
		logTagList = logTagServiceImpl.loadAll();
		return SUCCESS;
	}
	
	
	/**
	 * 保存日志标签
	 * @return
	 */
	@Action(value="saveLogTag",results={
			@Result(name="success",location="/admin/tag/logtagList.do", type="redirect")
	})
	public String saveLogTag(){
		System.out.println("----------logTagname==" + logTagName);
		logTagServiceImpl.save(logTagName);
		return SUCCESS;
	}
	
	/**
	 * 转到添加标签页面
	 * @return
	 */
	@Action(value="gotoAddLogtag",results={
			@Result(name="success",location="/admin/tag/addLogtag.jsp"),
	})
	public String gotoAddLogtag(){
		
		return SUCCESS;
	}
	
	@Action(value="logTagSearch",results={
			//@Result(name="success",location="/admin/user/userLoginList.do",type="redirect")
			@Result(name="success",location="/admin/tag/logtagList.jsp")
	})
	public String search(){
		
		paginationSupport = logTagServiceImpl.search(PaginationSupport.PAGESIZE,startIndex,logTagName);
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("logTagName", logTagName);
		paginationSupport.setMapValue(map);
		paginationSupport.setUrl("admin/tag/logTagSearch.do");
		logTagList = paginationSupport.getItems();
		//System.out.println(hql);
		//session.setAttribute("hql", hql);
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

	public String getLogTagName() {
		return logTagName;
	}

	public void setLogTagName(String logTagName) {
		this.logTagName = logTagName;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getLogTagIds() {
		return logTagIds;
	}

	public void setLogTagIds(String logTagIds) {
		this.logTagIds = logTagIds;
	}
	
	
}
