package com.way.blog.ajax.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.zone.blog.service.impl.LogCommentReplyServiceImpl;
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogCommentReply;

@ParentPackage("json-default")
@Namespace("/ajax/commentReply")
@Controller("logCommentReplayAction")
public class LogCommentReplayAction implements Preparable {

	@Autowired
	private LogCommentReplyServiceImpl logCommentReplyServiceImpl;
	@Autowired
	private LogCommentServiceImpl logCommentServiceImpl;
	
	HttpServletResponse response = null;    
	PrintWriter pw = null;
	Gson gson = null;
	private String jsonString; ////json格式数据
	
	private int commentId;
	public void prepare() throws Exception {
		
	}
	
	@Action(value="getLogCommentReply",results={
			@Result(type="json")
	})
	public String getLogCommentReply(){
		LogComment logComment = logCommentServiceImpl.findById(commentId);
		List<LogCommentReply> logCommentReplyList = new ArrayList<LogCommentReply>(logComment.getLogCommentReplys());
		List<LogCommentReply> replyList = new ArrayList<LogCommentReply>();
		for(LogCommentReply logCommentReply : logCommentReplyList){
			logCommentReply.setLogComments(null);
			replyList.add(logCommentReply);
		}
		this.returnJsonString(replyList);
		return null;
	}

	public void returnJsonString(List list){
		
		response = ServletActionContext.getResponse();     
		response.setContentType("application/json;charset=utf-8");          
		response.setHeader("Cache-Control","no-cache");                    
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gson = new Gson();
		jsonString = gson.toJson(list);
		System.out.println(jsonString);
		pw.print(jsonString);          
		pw.flush();          
		pw.close();  
	}
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
	
}
