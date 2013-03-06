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
	
	private int hasNext; ///判断还有下一页数据，如果有返回1，没有返回-1
	
	private int startIndex; ///如果有下一页，则下一页读取的开始下标

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

	public int getHasNext() {
		return hasNext;
	}

	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	
	
}
