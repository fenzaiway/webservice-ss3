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
 * 日志附件表
 */
@Entity
@Table(name="tb_log_attachment")
@Repository
public class LogAttachment implements Serializable {

	private static final long serialVersionUID = 5271880088074044243L;
	
	/**
	 * 附件ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 附件地址
	 */
	@Expose
	@Column(name="latt_url",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String url;
	
	/**
	 * 附件名称
	 */
	@Expose
	@Column(name="latt_attachment_name",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String attachmentName;
	
	/**
	 * 附件上传时间
	 */
	@Expose
	@Column(name="ltta_attachment_upload_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String attachmentUploadTime;
	
	/**
	 * 附件下载次数
	 */
	@Expose
	@Column(name="ltta_attachment_download_count",unique = false, nullable = true, insertable = true, updatable = true)
	private int attachmentDownloadCount;
	
	/**
	 * 附件所属日志
	 */
	@ManyToOne
	private LogInfo logInfo;

	public LogAttachment() {}

	public LogAttachment(int attachmentDownloadCount, String attachmentName,
			String attachmentUploadTime, int id, LogInfo logInfo, String url) {
		super();
		this.attachmentDownloadCount = attachmentDownloadCount;
		this.attachmentName = attachmentName;
		this.attachmentUploadTime = attachmentUploadTime;
		this.id = id;
		this.logInfo = logInfo;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentUploadTime() {
		return attachmentUploadTime;
	}

	public void setAttachmentUploadTime(String attachmentUploadTime) {
		this.attachmentUploadTime = attachmentUploadTime;
	}

	public int getAttachmentDownloadCount() {
		return attachmentDownloadCount;
	}

	public void setAttachmentDownloadCount(int attachmentDownloadCount) {
		this.attachmentDownloadCount = attachmentDownloadCount;
	}

	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

}
