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
 * 日志喜欢列表
 */
@Entity
@Table(name="tb_loglike")
@Repository
public class LogLike implements Serializable {

	private static final long serialVersionUID = 3140739939485041482L;
	
	/**
	 * likeID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * like时间
	 */
	@Expose
	@Column(name="lk_liketime",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String likeTime;
	
	/**
	 * like用户
	 */
	@Expose
	@Column(name="lk_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * like日志
	 */
	@ManyToOne
	private LogInfo logInfo;

	public LogLike() {}

	public LogLike(int id, String likeTime, LogInfo logInfo, String username) {
		super();
		this.id = id;
		this.likeTime = likeTime;
		this.logInfo = logInfo;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(String likeTime) {
		this.likeTime = likeTime;
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
