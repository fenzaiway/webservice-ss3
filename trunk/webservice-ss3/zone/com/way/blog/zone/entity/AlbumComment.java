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
 * 相片评论
 */
@Entity
@Table(name="tb_album_comment")
@Repository
public class AlbumComment implements Serializable {

	private static final long serialVersionUID = -5076850918027436311L;

	/**
	 * 评论ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 评论内容
	 */
	@Expose
	@Column(name="alc_comment_content",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String commentContent;
	
	/**
	 * 评论时间
	 */
	@Expose
	@Column(name="alc_comment_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String commentTime;
	
	/**
	 * 评论相片
	 */
	@ManyToOne
	private Album album;
	
	/**
	 * 评论回复
	 */
	@OneToMany(mappedBy="albumComment",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=AlbumCommentReply.class)
	private Set<AlbumCommentReply> albumCommentReplys = new HashSet<AlbumCommentReply>();
	
	/**
	 * 哪个用户评论
	 */
	@Expose
	@Column(name="alc_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;

	public AlbumComment() {}

	public AlbumComment(Album album, Set<AlbumCommentReply> albumCommentReplys,
			String commentContent, String commentTime, int id, String username) {
		super();
		this.album = album;
		this.albumCommentReplys = albumCommentReplys;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.id = id;
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Set<AlbumCommentReply> getAlbumCommentReplys() {
		return albumCommentReplys;
	}

	public void setAlbumCommentReplys(Set<AlbumCommentReply> albumCommentReplys) {
		this.albumCommentReplys = albumCommentReplys;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
