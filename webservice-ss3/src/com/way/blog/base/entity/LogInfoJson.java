package com.way.blog.base.entity;

import java.util.List;

/**
 * 这个类的设计是返回json数据到前台页面显示数据
 * project_name webservice-ss3
 *
 * package com.way.blog.base.entity
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-3-5上午01:10:38
 *
 *
 */
public class LogInfoJson {
	
	private String loadMore; ///分页字段

	private List<LogInfoData> data;

	public String getLoadMore() {
		return loadMore;
	}

	public void setLoadMore(String loadMore) {
		this.loadMore = loadMore;
	}

	public List<LogInfoData> getData() {
		return data;
	}

	public void setData(List<LogInfoData> data) {
		this.data = data;
	}
	
	
	
}
