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
 * 相册目录
 */
@Entity
@Table(name="tb_album_type")
@Repository
public class AlbumType implements Serializable {
	
	private static final long serialVersionUID = -144638795936261088L;

	/**
	 * 相册类型ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	public int id;
	
	/**
	 * 相册分类名称
	 */
	@Expose
	@Column(name="at_album_type_name",unique = false, nullable = false, insertable = true, updatable = true,length=100)
	private String albumTypeName;
	
	/**
	 * 相册分类创建时间
	 */
	@Expose
	@Column(name="at_album_type_create_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String albumTypeCreateTime;
	
	/**
	 * 相册是否允许查看
	 */
	@Expose
	@Column(name="at_is_album_allow_view",unique = false, nullable = true, insertable = true, updatable = true)
	private int isAlbumAllowView;
	
	/**
	 * 相册是否验证访问
	 */
	@Expose
	@Column(name="at_is_password_view",unique = false, nullable = true, insertable = true, updatable = true)
	private int isPasswordView;
	
	/**
	 * 密码问题
	 */
	@Expose
	@Column(name="at_question",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String question;
	
	/**
	 * 验证答案
	 */
	@Expose
	@Column(name="at_password_anwser",unique = false, nullable = true, insertable = true, updatable = true,length=100)
	private String passwordAnswer;
	
	/**
	 * 相册分类所属空间
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private BlogZone blogZone;
	
	/**
	 * 相册分类目录下的相片
	 */
	@OneToMany(mappedBy="albumType",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Album.class)
	private Set<Album> album = new HashSet<Album>();

	public AlbumType() {}

	public AlbumType(Set<Album> album, String albumTypeCreateTime,
			String albumTypeName, BlogZone blogZone, int id,
			int isAlbumAllowView, int isPasswordView, String passwordAnswer,
			String question) {
		super();
		this.album = album;
		this.albumTypeCreateTime = albumTypeCreateTime;
		this.albumTypeName = albumTypeName;
		this.blogZone = blogZone;
		this.id = id;
		this.isAlbumAllowView = isAlbumAllowView;
		this.isPasswordView = isPasswordView;
		this.passwordAnswer = passwordAnswer;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbumTypeName() {
		return albumTypeName;
	}

	public void setAlbumTypeName(String albumTypeName) {
		this.albumTypeName = albumTypeName;
	}

	public String getAlbumTypeCreateTime() {
		return albumTypeCreateTime;
	}

	public void setAlbumTypeCreateTime(String albumTypeCreateTime) {
		this.albumTypeCreateTime = albumTypeCreateTime;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public Set<Album> getAlbum() {
		return album;
	}

	public void setAlbum(Set<Album> album) {
		this.album = album;
	}

	public int getIsAlbumAllowView() {
		return isAlbumAllowView;
	}

	public void setIsAlbumAllowView(int isAlbumAllowView) {
		this.isAlbumAllowView = isAlbumAllowView;
	}

	public int getIsPasswordView() {
		return isPasswordView;
	}

	public void setIsPasswordView(int isPasswordView) {
		this.isPasswordView = isPasswordView;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}
	
}
