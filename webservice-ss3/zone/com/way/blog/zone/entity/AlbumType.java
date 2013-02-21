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
	 * 相册分类所属用户
	 */
	@Expose
	@Column(name="at_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 封面
	 */
	@Expose
	@Column(name="at_coverimg",unique = false, nullable = false, insertable = true, updatable = true,length=100)
	private String coverImg;
	
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

	public AlbumType(int id, String albumTypeName, String albumTypeCreateTime,
			int isAlbumAllowView, int isPasswordView, String question,
			String passwordAnswer, String username, String coverImg,
			BlogZone blogZone, Set<Album> album) {
		super();
		this.id = id;
		this.albumTypeName = albumTypeName;
		this.albumTypeCreateTime = albumTypeCreateTime;
		this.isAlbumAllowView = isAlbumAllowView;
		this.isPasswordView = isPasswordView;
		this.question = question;
		this.passwordAnswer = passwordAnswer;
		this.username = username;
		this.coverImg = coverImg;
		this.blogZone = blogZone;
		this.album = album;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
