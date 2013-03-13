package com.way.blog.base.entity;

import com.way.blog.user.entity.UserLogin;
import com.way.blog.zone.entity.BlogZone;

public class Space {
	
	private String img;
	
	private BlogZone blogZone;
	
	private UserLogin userLogin;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
	
	
}
