package com.way.blog.zone.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * 视频
 */
@Entity
@Table(name="tb_vedio")
@Repository
public class Vedio implements Serializable {
	
	private static final long serialVersionUID = 1289576254885917747L;

	/**
	 * 视频ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 视频名称
	 */
	@Expose
	@Column(name="v_vedio_name",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String vedioName;
	
	/**
	 * 视频来源
	 */
	@Expose
	@Column(name="v_url_source",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String urlSource;
	
	/**
	 * 播放次数
	 */
	@Expose
	@Column(name="v_play_count",unique = false, nullable = true, insertable = true, updatable = true)
	private int playCount;
	
	/**
	 * 视频发布时间
	 */
	@Expose
	@Column(name="v_publish_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String publishTime;
	
	/**
	 * 视频所属空间
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private BlogZone blogZone;

	public Vedio() {}

	public Vedio(BlogZone blogZone, int id, int playCount, String publishTime,
			String urlSource, String vedioName) {
		super();
		this.blogZone = blogZone;
		this.id = id;
		this.playCount = playCount;
		this.publishTime = publishTime;
		this.urlSource = urlSource;
		this.vedioName = vedioName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVedioName() {
		return vedioName;
	}

	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}

	public String getUrlSource() {
		return urlSource;
	}

	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
}
