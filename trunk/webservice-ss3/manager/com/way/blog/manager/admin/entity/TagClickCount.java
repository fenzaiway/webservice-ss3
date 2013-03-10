package com.way.blog.manager.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 对于标签的统计查询
 */
@Entity
@Table(name="tb_tag_click_count")
@Repository
public class TagClickCount implements Serializable {

	private static final long serialVersionUID = -6597605592470740391L;

	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 标签名
	 */
	@Expose
	@Column(name="click_tagname",unique = false, nullable = false, insertable = true, updatable = true,length=250)
	private String tagName;
	
	/**
	 * 点击用户，如果用户没有登录，就用游客表示
	 */
	@Expose
	@Column(name="click_username",unique = false, nullable = false, insertable = true, updatable = true,length=250)
	private String username;
	
	/**
	 * 点击时间，即查询时间
	 */
	@Expose
	@Column(name="click_clicktime",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String clickTime;
	
	/**
	 * 来自IP
	 */
	@Expose
	@Column(name="click_ip",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String ip;

	public TagClickCount() {}

	public TagClickCount(String clickTime, int id, String ip, String tagName,
			String username) {
		super();
		this.clickTime = clickTime;
		this.id = id;
		this.ip = ip;
		this.tagName = tagName;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClickTime() {
		return clickTime;
	}

	public void setClickTime(String clickTime) {
		this.clickTime = clickTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
