package com.way.blog.zone.entity;

import java.io.Serializable;
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

/**
 * 
 * @author fenzaiway
 * 日志评论回复表
 */
@Entity
@Table(name="tb_log_comment_reply")
@Repository
public class LogCommentReply implements Serializable {

	private static final long serialVersionUID = 472246274887251623L;


	/**
	 * 日志回复ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	

	/**
	 * 日志评论回复内容
	 */
	@Expose
	@Column(name="lcr_reply_content",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String replyContent;
	

	/**
	 * 日志评论回复时间
	 */
	@Expose
	@Column(name="lcr_reply_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String replyTime;
	

	/**
	 * 日志评论回复用户
	 */
	@Expose
	@Column(name="lcr_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	

	/**
	 * 回复哪条评论
	 */
	@ManyToMany
	@JoinTable(name="tb_comment_reply",joinColumns={@JoinColumn(name="lcrid")},inverseJoinColumns={@JoinColumn(name="lcid")})
	private Set<LogComment> logComments;

	

	public LogCommentReply() {}

	public LogCommentReply(int id, Set<LogComment> logComments,
			String replyContent, String replyTime, String username) {
		super();
		this.id = id;
		this.logComments = logComments;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.username = username;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public String getReplyTime() {
		return replyTime;
	}


	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public Set<LogComment> getLogComments() {
		return logComments;
	}

	public void setLogComments(Set<LogComment> logComments) {
		this.logComments = logComments;
	}

}
