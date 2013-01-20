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
 * 相片评论回复
 */
@Entity
@Table(name="tb_album_comment_replay")
@Repository
public class AlbumCommentReply implements Serializable {

	private static final long serialVersionUID = -7508787880582654307L;
	
	/**
	 * 回复ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 回复内容
	 */
	@Expose
	@Column(name="alcr_reply_content",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String replyContent;
	
	/**
	 * 回复时间
	 */
	@Expose
	@Column(name="alcr_reply_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String replyTime;
	
	/**
	 * 回复哪条评论
	 */
	@ManyToOne
	private AlbumComment albumComment;
	
	/**
	 * 哪个用户回复
	 */
	@Expose
	@Column(name="alcr_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;

	public AlbumCommentReply() {}

	public AlbumCommentReply(AlbumComment albumComment, int id,
			String replyContent, String replyTime, String username) {
		super();
		this.albumComment = albumComment;
		this.id = id;
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

	public AlbumComment getAlbumComment() {
		return albumComment;
	}

	public void setAlbumComment(AlbumComment albumComment) {
		this.albumComment = albumComment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
