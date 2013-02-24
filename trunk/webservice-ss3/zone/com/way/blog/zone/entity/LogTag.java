package com.way.blog.zone.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;
import com.way.blog.manager.admin.entity.Tag;

/**
 * 日志-关键字表
 * project_name webservice-ss3
 *
 * package com.way.blog.zone.entity
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-16下午07:10:39
 *
 *
 */
@Entity
@Table(name="tb_log_tag")
@Repository
public class LogTag implements Serializable {

	private static final long serialVersionUID = 1L;
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
	@Column(name="tag_name",unique = false, nullable = false, insertable = true, updatable = true,length=250)
	private String tagName;
	
	/**
	 * 标签创建时间
	 */
	@Expose
	@Column(name="tag_create_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String tagCreateTime;
	
	/**
	 * 标签对应的日志，属于多对多的关系
	 * 一标签对应多篇日志，一日志有多个标签
	 */
	@ManyToMany
	@JoinTable(name="tb_loginfo_tag",joinColumns={@JoinColumn(name="tagid")},inverseJoinColumns={@JoinColumn(name="loginfoid")})
	private Set<LogInfo> logInfos = new HashSet<LogInfo>();
	
	/**
	 * 日志标签所属的系统标签
	 */
	@ManyToMany
	@JoinTable(name="tb_tag_logtag",joinColumns={@JoinColumn(name="logtagid")},inverseJoinColumns={@JoinColumn(name="tagid")})
	private Set<Tag> tags = new HashSet<Tag>();

	public LogTag() {}

	public LogTag(int id, String tagName, String tagCreateTime,
			Set<LogInfo> logInfos, Set<Tag> tags) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.tagCreateTime = tagCreateTime;
		this.logInfos = logInfos;
		this.tags = tags;
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

	public String getTagCreateTime() {
		return tagCreateTime;
	}

	public void setTagCreateTime(String tagCreateTime) {
		this.tagCreateTime = tagCreateTime;
	}

	public Set<LogInfo> getLogInfos() {
		return logInfos;
	}

	public void setLogInfos(Set<LogInfo> logInfos) {
		this.logInfos = logInfos;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	
}
