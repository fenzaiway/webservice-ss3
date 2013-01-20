package com.way.blog.zone.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 草稿表
 */
@Entity
@Table(name="tb_log_draft")
@Repository
public class LogDraft implements Serializable {

	private static final long serialVersionUID = 1112425029795775080L;
	
	/**
	 * 草稿ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 草稿日志标题
	 */
	@Expose
	@Column(name="ld_title",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String logTitle;
	
	/**
	 * 草稿日志内容
	 */
	@Expose
	@Column(name="ld_logtext",unique = false, nullable = false, insertable = true, updatable = true,length=10000)
	private String logText;
	
	/**
	 * 草稿保存时间
	 */
	@Expose
	@Column(name="ld_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String saveTime;
	
	/**
	 * 草稿关联的日志
	 */
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	private LogInfo logInfo;

	public LogDraft() {}

	public LogDraft(int id, LogInfo logInfo, String logText, String logTitle,
			String saveTime) {
		super();
		this.id = id;
		this.logInfo = logInfo;
		this.logText = logText;
		this.logTitle = logTitle;
		this.saveTime = saveTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

	public String getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}
	
}
