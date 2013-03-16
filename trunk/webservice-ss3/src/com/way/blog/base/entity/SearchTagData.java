package com.way.blog.base.entity;

import java.util.List;


/**
 * 封装用户通过标签查询的时候返回来的数据
 * @author fenzaiway
 *
 */
public class SearchTagData {
	
	private int hasPre; ///是否还有上一页
	private int hasNext;//是否还有下一页
	private int nextIndex; ///如果有下一页，则下一页读取的开始下标
	private int preIndex; //如果有上一页，上一页的下标
	
	private List<LogInfoData> data;

	public int getHasPre() {
		return hasPre;
	}

	public void setHasPre(int hasPre) {
		this.hasPre = hasPre;
	}

	public int getHasNext() {
		return hasNext;
	}

	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public int getPreIndex() {
		return preIndex;
	}

	public void setPreIndex(int preIndex) {
		this.preIndex = preIndex;
	}

	public List<LogInfoData> getData() {
		return data;
	}

	public void setData(List<LogInfoData> data) {
		this.data = data;
	}
	
	
}
