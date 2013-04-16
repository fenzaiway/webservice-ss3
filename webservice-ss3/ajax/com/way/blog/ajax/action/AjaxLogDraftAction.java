package com.way.blog.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.LogDraftServiceImpl;
import com.way.blog.zone.entity.LogDraft;

@Controller("ajaxLogDraftAction")
@ParentPackage("json-default")
@Namespace("/ajax/draft")
public class AjaxLogDraftAction extends BaseAction {

	@Autowired private LogDraftServiceImpl logDraftServiceImpl;
	@Autowired private LogDraft logDraft;
	private String title;
	private String content;
	
	/**
	 * 保存草稿
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success",type="json")
	})
	public String save(){
		
		/**
		 * 步骤：
		 * 1.先根据草稿标题和用户名判断是否已经保存过草稿内容
		 * 2.如果还没有，则保存草稿
		 * 3.如果已经存在，则更新草稿表数据
		 * 
		 */
		
		//如果是还没有保存
		if(!logDraftServiceImpl.hasSaveDraft(myusername, title)){
			int id = logDraftServiceImpl.save(myusername, title, content);
			logDraft = logDraftServiceImpl.findById(id);
			
		}else{
			//已经存在同一篇草稿，则更新
			int id = logDraftServiceImpl.getDraftId(myusername, title);
			logDraftServiceImpl.update(content, id);
			logDraft = logDraftServiceImpl.findById(id);
		}
		this.returnJsonByObject(logDraft);
		return null;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
