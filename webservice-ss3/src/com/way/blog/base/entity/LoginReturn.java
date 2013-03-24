package com.way.blog.base.entity;

public class LoginReturn {
	
	private int status;
	
	private String username; ///登录成功后返回用户名

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
