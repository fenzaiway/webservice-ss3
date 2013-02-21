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
 * 日志收藏表
 */
@Entity
@Table(name="tb_logstore")
@Repository
public class LogStore implements Serializable {

	private static final long serialVersionUID = 1809815029854835175L;

	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 收藏用户
	 */
	@Expose
	@Column(name="lst_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 收藏时间
	 */
	@Expose
	@Column(name="lst_store_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String storeTime;
	
	@Expose
	@Column(name="lst_store_loginfo_title",unique = false, nullable = false, insertable = true, updatable = true,length=100)
	private String logInfoTitle;
	
	/**
	 * 收藏日志的地址
	 */
	@Expose
	@Column(name="lst_store_source_url",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String sourceUrl;
	
	/**
	 * 收藏的日志所属用户
	 */
	@Expose
	@Column(name="lst_store_source_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String sourceUserName;
	
	/**
	 * 收藏描述
	 */
	@Expose
	@Column(name="lst_store_desc",unique = false, nullable = true, insertable = true, updatable = true,length=100)
	private String storeDesc;
	
	@ManyToOne
	private LogInfo logInfo;

	public LogStore() {}

	public LogStore(int id, String username, String storeTime,
			String logInfoTitle, String sourceUrl, String sourceUserName,
			String storeDesc, LogInfo logInfo) {
		super();
		this.id = id;
		this.username = username;
		this.storeTime = storeTime;
		this.logInfoTitle = logInfoTitle;
		this.sourceUrl = sourceUrl;
		this.sourceUserName = sourceUserName;
		this.storeDesc = storeDesc;
		this.logInfo = logInfo;
	}

	public String getLogInfoTitle() {
		return logInfoTitle;
	}

	public void setLogInfoTitle(String logInfoTitle) {
		this.logInfoTitle = logInfoTitle;
	}

	public LogInfo getLogInfo() {
		return logInfo;
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

	public String getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(String storeTime) {
		this.storeTime = storeTime;
	}

	public String getStoreDesc() {
		return storeDesc;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceUserName() {
		return sourceUserName;
	}

	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}
	
}
