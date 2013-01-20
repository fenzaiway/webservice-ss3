package com.way.blog.zone.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tb_log_type")
@Repository
public class LogType implements Serializable {

	private static final long serialVersionUID = -8601650587151672252L;

	/**
	 * 日志类型ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 日志类型名称
	 */
	@Expose
	@Column(name="lt_logtype_name",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String logTypeName;
	
	/**
	 * 日志类型创建时间
	 */
	@Expose
	@Column(name="lt_logtype_create_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String logTypeCreateTime;
	
	/**
	 * 是否是默认日志类型,1、表示是2、表示不是
	 */
	@Expose
	@Column(name="lt_isdefault_logtype",unique = false, nullable = false, insertable = true, updatable = true)
	private int isDefaultLogType;
	
	/**
	 * 日志分类所属用户
	 */
	@Expose
	@Column(name="lt_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 *日志类型所属空间
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private BlogZone blogZone;
	
	/**
	 * 日志类型下面所拥有的日志
	 */
	@OneToMany(mappedBy="logType",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogInfo.class)
	private Set<LogInfo> logInfos = new HashSet<LogInfo>();

	public LogType() {}

	public LogType(BlogZone blogZone, int id, int isDefaultLogType,
			Set<LogInfo> logInfos, String logTypeCreateTime,
			String logTypeName, String username) {
		super();
		this.blogZone = blogZone;
		this.id = id;
		this.isDefaultLogType = isDefaultLogType;
		this.logInfos = logInfos;
		this.logTypeCreateTime = logTypeCreateTime;
		this.logTypeName = logTypeName;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogTypeName() {
		return logTypeName;
	}

	public void setLogTypeName(String logTypeName) {
		this.logTypeName = logTypeName;
	}

	public String getLogTypeCreateTime() {
		return logTypeCreateTime;
	}

	public void setLogTypeCreateTime(String logTypeCreateTime) {
		this.logTypeCreateTime = logTypeCreateTime;
	}

	public int getIsDefaultLogType() {
		return isDefaultLogType;
	}

	public void setIsDefaultLogType(int isDefaultLogType) {
		this.isDefaultLogType = isDefaultLogType;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public Set<LogInfo> getLogInfos() {
		return logInfos;
	}

	public void setLogInfos(Set<LogInfo> logInfos) {
		this.logInfos = logInfos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
