package com.way.blog.loginfo.service;

public interface ILogInfoService {
	public String save(String title, String content, String tags, String username, int visible, int logTypeId);
}
