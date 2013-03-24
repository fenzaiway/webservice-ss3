package com.way.blog.base.entity;

public class CommentListData {

	private int id; ///记录Id
	
	private String commentUsername; ///评论用户
	 
	private String replyUsername;  //回复用户
	
	private String headimgUrl;  //评论用户的头像地址
	
	private String conten; ///回复的内容
	
	private String time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentUsername() {
		return commentUsername;
	}

	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}

	public String getReplyUsername() {
		return replyUsername;
	}

	public void setReplyUsername(String replyUsername) {
		this.replyUsername = replyUsername;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getConten() {
		return conten;
	}

	public void setConten(String conten) {
		this.conten = conten;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
