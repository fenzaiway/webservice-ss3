package com.way.blog.base.entity;

import java.util.List;

/**
 * 返回前台日志数据
 * @author fenzaiway
 * 转载、like的实际数量是获取原创日志的转载、like的
 */
public class LogInfoData {
	private int logid; //日志Id
	
	private String logTitle; //日志标题
	
	private String publishTime; //日志发布时间
	
	private String headImgUrl; //头像地址
	
	private List<String> tags; //标签列表
	
	private int hotNum; //热度
	
	private int commentNum; //评论数量
	
	private int likeNum; //喜欢数量

	private int reprintNum; //转载数量
	
	private String logContent; //日志内容
	
	private String[] imgs; ///日志图像列表
	
	private String username; ///日志所属用户
	
	private int isLike ;   ////这篇日志用户是否喜欢，喜欢为1，不喜欢为0

	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getHotNum() {
		return hotNum;
	}

	public void setHotNum(int hotNum) {
		this.hotNum = hotNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getReprintNum() {
		return reprintNum;
	}

	public void setReprintNum(int reprintNum) {
		this.reprintNum = reprintNum;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIsLike() {
		return isLike;
	}

	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}
	
	
}
