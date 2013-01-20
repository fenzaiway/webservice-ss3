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
 * 音频
 */
@Entity
@Table(name="tb_music")
@Repository
public class Music implements Serializable {
	
	private static final long serialVersionUID = -8956418110652397039L;

	/**
	 * 音频ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 歌曲名称
	 */
	@Expose
	@Column(name="m_music_name",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String musicName;
	
	/**
	 * 歌手
	 */
	@Expose
	@Column(name="m_singer",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String singer;
	
	/**
	 * 来源地址
	 */
	@Expose
	@Column(name="m_url_source",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String urlSource;
	
	/**
	 * 播放次数
	 */
	@Expose
	@Column(name="m_play_count",unique = false, nullable = true, insertable = true, updatable = true)
	private int playCount;
	
	/**
	 * 发布时间
	 */
	@Expose
	@Column(name="m_publish_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String publishTime;
	
	/**
	 * 所属空间
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private BlogZone blogZone;

	public Music() {}

	public Music(BlogZone blogZone, int id, String musicName, int playCount,
			String publishTime, String singer, String urlSource) {
		super();
		this.blogZone = blogZone;
		this.id = id;
		this.musicName = musicName;
		this.playCount = playCount;
		this.publishTime = publishTime;
		this.singer = singer;
		this.urlSource = urlSource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
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
