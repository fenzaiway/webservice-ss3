package com.way.blog.zone.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 日志分享表
 */
@Entity
@Table(name="tb_log_share")
@Repository
public class LogShare implements Serializable {
	
	private static final long serialVersionUID = 4314999714564341466L;

	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 分享用户
	 */
	@Expose
	@Column(name="lsh_username",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 分享时间
	 */
	@Expose
	@Column(name="lsh_share_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String shareTime;
	
	/**
	 * 分享描述
	 */
	@Expose
	@Column(name="lsh_share_desc",unique = false, nullable = true, insertable = true, updatable = true,length=150)
	private String shareDesc;
	
	/**
	 * 分享日志地址
	 */
	@Expose
	@Column(name="lsh_share_log_url",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String shareLogUrl;
	
	/**
	 * 分享日志标题
	 */
	@Expose
	@Column(name="lsh_share_log_title",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String shareLogTitle;
	
	@ManyToOne
	private LogInfo logInfo;

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public LogShare() {}

	public LogShare(int id, LogInfo logInfo, String shareDesc,
			String shareLogTitle, String shareLogUrl, String shareTime,
			String username) {
		super();
		this.id = id;
		this.logInfo = logInfo;
		this.shareDesc = shareDesc;
		this.shareLogTitle = shareLogTitle;
		this.shareLogUrl = shareLogUrl;
		this.shareTime = shareTime;
		this.username = username;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

	public String getShareDesc() {
		return shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}

	public String getShareLogUrl() {
		return shareLogUrl;
	}

	public void setShareLogUrl(String shareLogUrl) {
		this.shareLogUrl = shareLogUrl;
	}

	public String getShareLogTitle() {
		return shareLogTitle;
	}

	public void setShareLogTitle(String shareLogTitle) {
		this.shareLogTitle = shareLogTitle;
	}
}
