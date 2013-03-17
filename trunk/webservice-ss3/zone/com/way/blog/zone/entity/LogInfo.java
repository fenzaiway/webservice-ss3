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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 日志
 */
@Entity
@Table(name="tb_logInfo")
@Repository
public class LogInfo implements Serializable {

	private static final long serialVersionUID = -6819401077962600516L;

	/**
	 * 日志ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;	
	
	/**
	 * 日志标题
	 */
	@Expose
	@Column(name="li_logtitle",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String logTitle;
	
	/**
	 * 日志内容
	 */
	@Expose
	@Column(name="li_logtext",unique = false, nullable = false, insertable = true, updatable = true,length=100000)
	private String logText;
	
	/**
	 * 日志发表时间
	 */
	@Expose
	@Column(name="li_log_publish_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String logPublishTime;
	
	/**
	 * 日志状态（是否发布1、发布 2、待发布）
	 */
	@Expose
	@Column(name="li_log_content_status",unique = false, nullable = false, insertable = true, updatable = true)
	private int logContentStatus;
	
	/**
	 * 日志是否允许评论(1、任何人2、好友3、禁止评论)
	 */
	@Expose
	@Column(name="li_log_allow_comment",unique = false, nullable = false, insertable = true, updatable = true)
	private int logAllowComment;
	
	/**
	 * 日志是否原创（1、原创 2、转载 3、其他）
	 */
	@Expose
	@Column(name="li_log_isoriginal",unique = false, nullable = false, insertable = true, updatable = true)
	private int logIsOriginal;
	
	/**
	 * 日志是否置顶(1、置顶2、不置顶)
	 */
	@Expose
	@Column(name="li_log_totop",unique = false, nullable = false, insertable = true, updatable = true)
	public int logToTop;
	
	/**
	 * 日志是否允许查看(1、任何人2、仅好友3、仅自己)
	 */
	@Expose
	@Column(name="li_log_allow_visit",unique = false, nullable = false, insertable = true, updatable = true)
	public int logAllowVisit;
	
	////添加用户名，方便查询
	/**
	 * 日志所属用户
	 */
	@Expose
	@Column(name="li_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 转载用户的昵称
	 */
	@Expose
	@Column(name="li_reprint_username",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String reprintUsername;
	
	/**
	 * 源日志ID
	 */
	@Expose
	@Column(name="li_source_loginfo_id",unique = false, nullable = true, insertable = true, updatable = true)
	private int sourceLogInfoId;
	
	/**
	 * 日志删除状态，如果状态为1，则，在显示日志列表的时候就不显示出来
	 */
	@Expose
	@Column(name="li_deleteStatue",unique = false, nullable = false, insertable = true, updatable = true)
	private int deleteStatue;
	
	/**
	 * 日志所属类型
	 */
	@ManyToOne
	public LogType logType;
	
	/**
	 * 日志附件
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogAttachment.class)
	public Set<LogAttachment> logAttachments = new HashSet<LogAttachment>();
	
	/**
	 * 日志评论
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=LogComment.class)
	public Set<LogComment> logComments = new HashSet<LogComment>();
	
	/**
	 * 日志转载
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=LogReprint.class)
	public Set<LogReprint> logReprints = new HashSet<LogReprint>();
	
	/**
	 * 日志草稿箱，一篇日志对应一草稿记录
	 */
	@OneToOne(mappedBy="logInfo",fetch=FetchType.LAZY,targetEntity=LogDraft.class)
	public Set<LogDraft> logDraft = new HashSet<LogDraft>();
	
	/**
	 * 日志分享
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogShare.class)
	public Set<LogShare> logShares = new HashSet<LogShare>();
	
	/**
	 * 日志Like
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=LogLike.class)
	public Set<LogLike> logLikes = new HashSet<LogLike>();
	
	/**
	 * 日志收藏表
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogStore.class)
	public Set<LogStore> logStores = new HashSet<LogStore>();
	
	/**
	 * 日志访问
	 */
	@OneToMany(mappedBy="logInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogVisit.class)
	public Set<LogVisit> logVisits = new HashSet<LogVisit>();

	/**
	 * 日志标签表
	 */
	@ManyToMany(mappedBy="logInfos",cascade={CascadeType.ALL},fetch=FetchType.EAGER,targetEntity=LogTag.class)
	private Set<LogTag> logTags = new HashSet<LogTag>();
	
	public LogInfo() {}

	public LogInfo(int id, String logTitle, String logText,
			String logPublishTime, int logContentStatus, int logAllowComment,
			int logIsOriginal, int logToTop, int logAllowVisit,
			String username, String reprintUsername, int sourceLogInfoId,
			int deleteStatue, LogType logType,
			Set<LogAttachment> logAttachments, Set<LogComment> logComments,
			Set<LogReprint> logReprints, Set<LogDraft> logDraft,
			Set<LogShare> logShares, Set<LogLike> logLikes,
			Set<LogStore> logStores, Set<LogVisit> logVisits,
			Set<LogTag> logTags) {
		super();
		this.id = id;
		this.logTitle = logTitle;
		this.logText = logText;
		this.logPublishTime = logPublishTime;
		this.logContentStatus = logContentStatus;
		this.logAllowComment = logAllowComment;
		this.logIsOriginal = logIsOriginal;
		this.logToTop = logToTop;
		this.logAllowVisit = logAllowVisit;
		this.username = username;
		this.reprintUsername = reprintUsername;
		this.sourceLogInfoId = sourceLogInfoId;
		this.deleteStatue = deleteStatue;
		this.logType = logType;
		this.logAttachments = logAttachments;
		this.logComments = logComments;
		this.logReprints = logReprints;
		this.logDraft = logDraft;
		this.logShares = logShares;
		this.logLikes = logLikes;
		this.logStores = logStores;
		this.logVisits = logVisits;
		this.logTags = logTags;
	}



	public int getDeleteStatue() {
		return deleteStatue;
	}

	public void setDeleteStatue(int deleteStatue) {
		this.deleteStatue = deleteStatue;
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

	public String getLogPublishTime() {
		return logPublishTime;
	}

	public void setLogPublishTime(String logPublishTime) {
		this.logPublishTime = logPublishTime;
	}

	public int getLogContentStatus() {
		return logContentStatus;
	}

	public void setLogContentStatus(int logContentStatus) {
		this.logContentStatus = logContentStatus;
	}

	public int getLogAllowComment() {
		return logAllowComment;
	}

	public void setLogAllowComment(int logAllowComment) {
		this.logAllowComment = logAllowComment;
	}

	public int getLogIsOriginal() {
		return logIsOriginal;
	}

	public void setLogIsOriginal(int logIsOriginal) {
		this.logIsOriginal = logIsOriginal;
	}

	public int getLogToTop() {
		return logToTop;
	}

	public void setLogToTop(int logToTop) {
		this.logToTop = logToTop;
	}

	public int getLogAllowVisit() {
		return logAllowVisit;
	}

	public void setLogAllowVisit(int logAllowVisit) {
		this.logAllowVisit = logAllowVisit;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public Set<LogAttachment> getLogAttachments() {
		return logAttachments;
	}

	public void setLogAttachments(Set<LogAttachment> logAttachments) {
		this.logAttachments = logAttachments;
	}

	public Set<LogComment> getLogComments() {
		return logComments;
	}

	public void setLogComments(Set<LogComment> logComments) {
		this.logComments = logComments;
	}

	public Set<LogReprint> getLogReprints() {
		return logReprints;
	}

	public void setLogReprints(Set<LogReprint> logReprints) {
		this.logReprints = logReprints;
	}

	public Set<LogDraft> getLogDraft() {
		return logDraft;
	}

	public void setLogDraft(Set<LogDraft> logDraft) {
		this.logDraft = logDraft;
	}

	public Set<LogShare> getLogShares() {
		return logShares;
	}

	public void setLogShares(Set<LogShare> logShares) {
		this.logShares = logShares;
	}

	public Set<LogLike> getLogLikes() {
		return logLikes;
	}

	public void setLogLikes(Set<LogLike> logLikes) {
		this.logLikes = logLikes;
	}

	public Set<LogVisit> getLogVisits() {
		return logVisits;
	}

	public void setLogVisits(Set<LogVisit> logVisits) {
		this.logVisits = logVisits;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<LogStore> getLogStores() {
		return logStores;
	}

	public void setLogStores(Set<LogStore> logStores) {
		this.logStores = logStores;
	}

	public String getReprintUsername() {
		return reprintUsername;
	}

	public void setReprintUsername(String reprintUsername) {
		this.reprintUsername = reprintUsername;
	}

	public int getSourceLogInfoId() {
		return sourceLogInfoId;
	}

	public void setSourceLogInfoId(int sourceLogInfoId) {
		this.sourceLogInfoId = sourceLogInfoId;
	}

	public Set<LogTag> getLogTags() {
		return logTags;
	}

	public void setLogTags(Set<LogTag> logTags) {
		this.logTags = logTags;
	}
	
}
