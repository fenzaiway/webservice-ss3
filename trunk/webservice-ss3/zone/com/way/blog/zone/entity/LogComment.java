package com.way.blog.zone.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 日志评论表
 */
@Entity
@Table(name="tb_log_comment")
@Repository
public class LogComment implements Serializable {

	private static final long serialVersionUID = -6437166723477907249L;

	/**
	 * 日志评论ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 日志评论内容
	 */
	@Expose
	@Column(name="lc_comment_content",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String commentContent;
	
	/**
	 * 日志评论时间
	 */
	@Expose
	@Column(name="lc_comment_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String commentTime;
	
	/**
	 * 日志评论用户
	 */
	@Expose
	@Column(name="lc_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 评论的日志
	 */
	@ManyToOne
	private LogInfo logInfo;
	
	/**
	 * 评论回复
	 */
	@OneToMany(mappedBy="logComment",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogCommentReply.class)
	private Set<LogCommentReply> logCommentReplys = new HashSet<LogCommentReply>();

	public LogComment() {}

	public LogComment(String commentContent, String commentTime, int id,
			Set<LogCommentReply> logCommentReplys, LogInfo logInfo,
			String username) {
		super();
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.id = id;
		this.logCommentReplys = logCommentReplys;
		this.logInfo = logInfo;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
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

	public Set<LogCommentReply> getLogCommentReplys() {
		return logCommentReplys;
	}

	public void setLogCommentReplys(Set<LogCommentReply> logCommentReplys) {
		this.logCommentReplys = logCommentReplys;
	}
	
	/**
	 * 设置关联关系为null
	 */
	public List<LogComment> setNull(List<LogComment> logCommentList){
		List<LogComment> lcList = new ArrayList<LogComment>();
		for(LogComment lc : logCommentList){
			lc.setLogInfo(null);
			lc.setLogCommentReplys(null);
			lcList.add(lc);
		}
		System.out.println("------int logcomment ----- ");
		return lcList;
	}
	
}
