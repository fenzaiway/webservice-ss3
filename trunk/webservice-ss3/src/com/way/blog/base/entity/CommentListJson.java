package com.way.blog.base.entity;

import java.util.List;

/**
 * 构造生成评论列表的json对象
 * @author fenzaiway
 *
 */
public class CommentListJson {
	
	private String loadMore; ///分页字段
	
	private int hasNext; ///判断还有下一页数据，如果有返回1，没有返回-1
	
	private int startIndex; ///如果有下一页，则下一页读取的开始下标
	 
	private List<CommentListData> data;

	public String getLoadMore() {
		return loadMore;
	}

	public void setLoadMore(String loadMore) {
		this.loadMore = loadMore;
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

	public List<CommentListData> getData() {
		return data;
	}

	public void setData(List<CommentListData> data) {
		this.data = data;
	}
	
	
}
