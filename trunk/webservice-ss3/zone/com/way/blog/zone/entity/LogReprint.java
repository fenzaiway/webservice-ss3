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
 * 转载表
 */
@Entity
@Table(name="tb_log_reprint")
@Repository
public class LogReprint implements Serializable {

	private static final long serialVersionUID = 9138085520831840429L;

	/**
	 * 转载ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 转载用户
	 */
	@Expose
	@Column(name="lr_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 转载地址
	 */
	@Expose
	@Column(name="lr_url",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String url;
	
	/**
	 * 转载时间
	 */
	@Expose
	@Column(name="lr_reprint_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String reprintTime;
	
	/**
	 * 转载的这篇日志转载后对应的ID
	 */
	@Expose
	@Column(name="lr_reprint_loginfo_id",unique = false, nullable = false, insertable = true, updatable = true)
	private int reprintLogInfoId;
	
	/**
	 * 源日志所属用户
	 */
	@Expose
	@Column(name="lr_source_loginfo_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String sourceLogIngoUsername;
	
	/**
	 * 转载日志
	 */
	@ManyToOne
	private LogInfo logInfo;

	public LogReprint() {}

	public LogReprint(int id, LogInfo logInfo, int reprintLogInfoId,
			String reprintTime, String sourceLogIngoUsername, String url,
			String username) {
		super();
		this.id = id;
		this.logInfo = logInfo;
		this.reprintLogInfoId = reprintLogInfoId;
		this.reprintTime = reprintTime;
		this.sourceLogIngoUsername = sourceLogIngoUsername;
		this.url = url;
		this.username = username;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReprintTime() {
		return reprintTime;
	}

	public void setReprintTime(String reprintTime) {
		this.reprintTime = reprintTime;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

	public int getReprintLogInfoId() {
		return reprintLogInfoId;
	}

	public void setReprintLogInfoId(int reprintLogInfoId) {
		this.reprintLogInfoId = reprintLogInfoId;
	}

	public String getSourceLogIngoUsername() {
		return sourceLogIngoUsername;
	}

	public void setSourceLogIngoUsername(String sourceLogIngoUsername) {
		this.sourceLogIngoUsername = sourceLogIngoUsername;
	}
	
}
