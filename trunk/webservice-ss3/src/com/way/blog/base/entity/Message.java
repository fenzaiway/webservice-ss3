package com.way.blog.base.entity;

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
import com.way.blog.zone.entity.LogInfo;

/**
 * 消息实体类
 * @author fenzaiway
 * 
 */
@Entity
@Table(name="tb_message")
@Repository
public class Message implements Serializable {
	
	/**
	 * 消息Id
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 消息类型
	 * 1、表示评论消息
	 * 2、表示转载消息
	 * 3、表示收藏消息
	 * 4、表示like消息
	 * 5、表示公告消息
	 * 6、表示关注信息 
	 * 7、表示回复
	 */
	@Expose
	@Column(name="msg_type",unique = false, nullable = false, insertable = true, updatable = true,length=11)
	private int msgtype;
	
	/**
	 * 消息属于哪个用户,可以为空
	 */
	@Expose
	@Column(name="msg_username",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 哪个用户发起的消息
	 */
	@Expose
	@Column(name="msg_fromusername",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String fromusername;
	
	/**
	 * 消息内容
	 */
	@Expose
	@Column(name="msg_content",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String msgContent;
	
	/**
	 * 消息状态
	 * 1、表示已经阅读
	 * 0、表示还没有阅读
	 */
	@Expose
	@Column(name="msg_status",unique = false, nullable = false, insertable = true, updatable = true,length=11)
	private int msgStatus;
	
	/**
	 * 消息触发产生时间
	 */
	@Expose
	@Column(name="msg_trigger_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String triggerTime;
	
	/**
	 * 消息属于哪篇日志
	 */
	@ManyToOne
	private LogInfo logInfo; 

	public Message() {}

	public Message(String fromusername, int id, LogInfo logInfo,
			String msgContent, int msgStatus, int msgtype, String triggerTime,
			String username) {
		super();
		this.fromusername = fromusername;
		this.id = id;
		this.logInfo = logInfo;
		this.msgContent = msgContent;
		this.msgStatus = msgStatus;
		this.msgtype = msgtype;
		this.triggerTime = triggerTime;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public int getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(int msgStatus) {
		this.msgStatus = msgStatus;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

	public String getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(String triggerTime) {
		this.triggerTime = triggerTime;
	}
	
}
