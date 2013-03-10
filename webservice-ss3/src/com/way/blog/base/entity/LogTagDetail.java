package com.way.blog.base.entity;

public class LogTagDetail {

	private String tagName; ////标签名称
	  
	private String tagCreateTime;  ////标签创建时间
	
	private long tagClickNum;  ////标签的点击次数
	
	private String sysTag;   ///标签所属的系统标签
	
	private long logNum;   ////标签对应的日志数量

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagCreateTime() {
		return tagCreateTime;
	}

	public void setTagCreateTime(String tagCreateTime) {
		this.tagCreateTime = tagCreateTime;
	}

	public long getTagClickNum() {
		return tagClickNum;
	}

	public void setTagClickNum(long tagClickNum) {
		this.tagClickNum = tagClickNum;
	}

	public String getSysTag() {
		return sysTag;
	}

	public void setSysTag(String sysTag) {
		this.sysTag = sysTag;
	}

	public long getLogNum() {
		return logNum;
	}

	public void setLogNum(long logNum) {
		this.logNum = logNum;
	}
	
	
}
