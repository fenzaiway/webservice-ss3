package com.way.blog.base.entity;

public class MessageData {
	
	private int id;
	
	private String headImgUrl; //用户头像
	
	private String fromUserName; //哪个用户发起的消息
	
	private String content;  //热度内容

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
