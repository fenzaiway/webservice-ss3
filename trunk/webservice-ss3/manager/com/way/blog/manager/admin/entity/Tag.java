package com.way.blog.manager.admin.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.zone.entity.LogTag;

/**
 * 系统标签，后前可以从日志标签中选择用户填写标签最多的top-N来自动维护
 * project_name webservice-ss3
 *
 * package com.way.blog.manager.admin.entity
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-24下午12:55:09
 *
 *
 */
@Entity
@Table(name="tb_tag")
@Repository
public class Tag implements Serializable {
	
	private static final long serialVersionUID = 6610932909307394544L;
	
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
	private String createTime;
	
	/**
	 * 是不是系统标签 1 表示是 0表示不是
	 */
	@Expose
	@Column(name="tag_is_systag",unique = false, nullable = false, insertable = true, updatable = true,length=11)
	private int isSysTag;
	
	/**
	 * 用户关注的标签
	 */
	@ManyToMany
	@JoinTable(name="tb_user_tag",joinColumns={@JoinColumn(name="tagid")},inverseJoinColumns={@JoinColumn(name="userid")})
	private  Set<UserLogin> userLogins = new HashSet<UserLogin>();
	
	/**
	 * 系统标签下的日志标签
	 */
	@ManyToMany(mappedBy="tags",cascade={CascadeType.ALL},fetch=FetchType.LAZY,targetEntity=LogTag.class)
	private Set<LogTag> logTags = new HashSet<LogTag>();

	public Tag() {}

	

	public Tag(String createTime, int id, int isSysTag, Set<LogTag> logTags,
			String tagName, Set<UserLogin> userLogins) {
		super();
		this.createTime = createTime;
		this.id = id;
		this.isSysTag = isSysTag;
		this.logTags = logTags;
		this.tagName = tagName;
		this.userLogins = userLogins;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Set<UserLogin> getUserLogins() {
		return userLogins;
	}

	public void setUserLogins(Set<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}

	public Set<LogTag> getLogTags() {
		return logTags;
	}

	public void setLogTags(Set<LogTag> logTags) {
		this.logTags = logTags;
	}

	public int getIsSysTag() {
		return isSysTag;
	}

	public void setIsSysTag(int isSysTag) {
		this.isSysTag = isSysTag;
	}
	
	
}
