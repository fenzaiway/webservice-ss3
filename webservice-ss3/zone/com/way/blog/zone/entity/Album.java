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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 相片
 */
@Entity
@Table(name="tb_album")
@Repository
public class Album implements Serializable {

	private static final long serialVersionUID = -4910762287872087408L;
	
	/**
	 * 相片ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 相片名称
	 */
	@Expose
	@Column(name="pic_name",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String picName;
	
	/**
	 * 相片描述
	 */
	@Expose
	@Column(name="pic_detial",unique = false, nullable = true, insertable = true, updatable = true,length=255)
	private String picDetial;
	
	/**
	 * 相片上传时间
	 */
	@Expose
	@Column(name="pic_upload_time",unique = false, nullable = true, insertable = true, updatable = true,length=25)
	private String picUpLoadTime;
	
	/**
	 * 相片存储位置
	 */
	@Expose
	@Column(name="pic_album_location",unique = false, nullable = true, insertable = true, updatable = true,length=100)
	private String albumLocation;
	
	/**
	 * 相片所属相册类型
	 */
	@ManyToOne
	private AlbumType albumType;
	
	/**
	 * 相片评论
	 */
	@OneToMany(mappedBy="album",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=AlbumComment.class)
	private Set<AlbumComment> albumComment = new HashSet<AlbumComment>();

	public Album() {}

	public Album(int id, String picName, String picDetial,
			String picUpLoadTime, String albumLocation, AlbumType albumType,
			Set<AlbumComment> albumComment) {
		super();
		this.id = id;
		this.picName = picName;
		this.picDetial = picDetial;
		this.picUpLoadTime = picUpLoadTime;
		this.albumLocation = albumLocation;
		this.albumType = albumType;
		this.albumComment = albumComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicDetial() {
		return picDetial;
	}

	public void setPicDetial(String picDetial) {
		this.picDetial = picDetial;
	}

	public String getPicUpLoadTime() {
		return picUpLoadTime;
	}

	public void setPicUpLoadTime(String picUpLoadTime) {
		this.picUpLoadTime = picUpLoadTime;
	}

	public AlbumType getAlbumType() {
		return albumType;
	}

	public void setAlbumType(AlbumType albumType) {
		this.albumType = albumType;
	}

	public Set<AlbumComment> getAlbumComment() {
		return albumComment;
	}

	public void setAlbumComment(Set<AlbumComment> albumComment) {
		this.albumComment = albumComment;
	}

	public String getAlbumLocation() {
		return albumLocation;
	}

	public void setAlbumLocation(String albumLocation) {
		this.albumLocation = albumLocation;
	}
	
}
