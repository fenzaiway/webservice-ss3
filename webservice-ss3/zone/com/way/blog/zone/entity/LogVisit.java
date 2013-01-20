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
 * 日志访问表
 */
@Entity
@Table(name="tb_log_visit")
@Repository
public class LogVisit implements Serializable {

	private static final long serialVersionUID = 3912518844361187649L;

	/**
	 * 访问ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 访问时间
	 */
	@Expose
	@Column(name="lv_logvisit_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String visitTime;
	
	/**
	 * 访问用户
	 */
	@Expose
	@Column(name="lv_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 访问的日志
	 */
	@ManyToOne
	private LogInfo logInfo;

	public LogVisit() {}

	public LogVisit(int id, LogInfo logInfo, String username, String visitTime) {
		super();
		this.id = id;
		this.logInfo = logInfo;
		this.username = username;
		this.visitTime = visitTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

}
