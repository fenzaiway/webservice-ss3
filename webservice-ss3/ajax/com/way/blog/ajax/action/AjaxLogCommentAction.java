package com.way.blog.ajax.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.entity.LogComment;

/**
 * 评论ajax控制器
 * @author fenzaiway
 *
 */
@Controller("ajaxLogCommentAction")
@ParentPackage("json-default")
@Namespace("/ajax/comment")
public class AjaxLogCommentAction extends BaseAction {

	@Autowired private LogCommentServiceImpl logCommentServiceImpl;
	@Autowired private LogComment logComment;
	private int logid;
	private String commentText; ///评论的内容
	
	
	@Action(value="loadCommentList",results={
			@Result(name="success",type="json")
	})
	public String loadCommentList(){
		this.returnJson(logCommentServiceImpl.loadCommentList(logid));
		return null;
	}

	@Action(value="save",results={
			@Result(name="success",type="json")
	})
	public String save(){
		int commentid = logCommentServiceImpl.save(logid, myusername, commentText);
		logComment = logCommentServiceImpl.findById(commentid);
		this.returnJsonByObject(logCommentServiceImpl.getCommentListData(logComment));
		return null;
	}
	
	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}
