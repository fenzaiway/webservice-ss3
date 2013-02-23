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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;
import com.way.blog.user.entity.UserLogin;

/**
 * 
 * @author fenzaiway
 * 博客空间
 */
@Entity
@Table(name="tb_blog_zone")
@Repository
public class BlogZone implements Serializable {
	
	private static final long serialVersionUID = 8443430617548527608L;

	/**
	 * 空间ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 空间名称
	 */
	@Expose
	@Column(name="zone_blog_zonename",unique = false, nullable = false, insertable = true, updatable = true,length=100)
	private String blogZoneName;
	
	/**
	 * 空间路径
	 */
	@Expose
	@Column(name="zone_blog_url",unique = false, nullable = false, insertable = true, updatable = true,length=255)
	private String zoneUrl;
	
	/**
	 * 空间描述
	 */
	@Expose
	@Column(name="zone_blog_zonedesc",unique = false, nullable = true, insertable = true, updatable = true,length=100)
	private String blogZoneDesc;
	
	/**
	 * 空间创建时间
	 */
	@Expose
	@Column(name="zone_blog_create_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String blogZoneCreateTime;
	
	/**
	 * 空间签名
	 */
	@Expose
	@Column(name="zone_sign_name",unique = false, nullable = true, insertable = true, updatable = true,length=100)
	private String signName;
	
	/**
	 * 空间是否可以使用
	 */
	@Expose
	@Column(name="zone_enabled",unique = false, nullable = false, insertable = true, updatable = true)
	private int enabled;
	
	/**
	 * 空间用户昵称（唯一）
	 */
	@Expose
	@Column(name="zone_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 空间所属用户
	 */
	@OneToOne(mappedBy="blogZone",cascade=CascadeType.ALL,targetEntity=UserLogin.class,fetch=FetchType.LAZY)
	private UserLogin userLogin;

	/**
	 * 空间日志分类
	 */
	@OneToMany(mappedBy="blogZone",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=LogType.class)
	private Set<LogType> logTypes = new HashSet<LogType>();
	
	/**
	 * 相册分类
	 */
	@OneToMany(mappedBy="blogZone",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=AlbumType.class)
	private Set<AlbumType> albums = new HashSet<AlbumType>();
	
	/**
	 * 音乐
	 */
	@OneToMany(mappedBy="blogZone",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Music.class)
	private Set<Music> musics = new HashSet<Music>();
	
	/**
	 * 视频
	 */
	@OneToMany(mappedBy="blogZone",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Vedio.class)
	private Set<Vedio> vedios = new HashSet<Vedio>();
	
	/**
	 * 关注用户
	 */
	@OneToMany(mappedBy="blogZone",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Attention.class)
	private Set<Attention> attentions = new HashSet<Attention>();
	
	/**
	 * 访问记录
	 */
	@OneToMany(mappedBy="blogZone",cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=BlogZoneVisit.class)
	private Set<BlogZoneVisit> blogZoneVisits = new HashSet<BlogZoneVisit>();
	
	public BlogZone() {}

	public BlogZone(Set<AlbumType> albums, Set<Attention> attentions,
			String blogZoneCreateTime, String blogZoneDesc,
			String blogZoneName, Set<BlogZoneVisit> blogZoneVisits,
			int enabled, int id, Set<LogType> logTypes, Set<Music> musics,
			String signName, UserLogin userLogin, String username,
			Set<Vedio> vedios, String zoneUrl) {
		super();
		this.albums = albums;
		this.attentions = attentions;
		this.blogZoneCreateTime = blogZoneCreateTime;
		this.blogZoneDesc = blogZoneDesc;
		this.blogZoneName = blogZoneName;
		this.blogZoneVisits = blogZoneVisits;
		this.enabled = enabled;
		this.id = id;
		this.logTypes = logTypes;
		this.musics = musics;
		this.signName = signName;
		this.userLogin = userLogin;
		this.username = username;
		this.vedios = vedios;
		this.zoneUrl = zoneUrl;
	}

	public Set<BlogZoneVisit> getBlogZoneVisits() {
		return blogZoneVisits;
	}

	public void setBlogZoneVisits(Set<BlogZoneVisit> blogZoneVisits) {
		this.blogZoneVisits = blogZoneVisits;
	}

	public String getZoneUrl() {
		return zoneUrl;
	}

	public void setZoneUrl(String zoneUrl) {
		this.zoneUrl = zoneUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlogZoneName() {
		return blogZoneName;
	}

	public void setBlogZoneName(String blogZoneName) {
		this.blogZoneName = blogZoneName;
	}

	public String getBlogZoneDesc() {
		return blogZoneDesc;
	}

	public void setBlogZoneDesc(String blogZoneDesc) {
		this.blogZoneDesc = blogZoneDesc;
	}

	public String getBlogZoneCreateTime() {
		return blogZoneCreateTime;
	}

	public void setBlogZoneCreateTime(String blogZoneCreateTime) {
		this.blogZoneCreateTime = blogZoneCreateTime;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public Set<LogType> getLogTypes() {
		return logTypes;
	}

	public void setLogTypes(Set<LogType> logTypes) {
		this.logTypes = logTypes;
	}

	public Set<AlbumType> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<AlbumType> albums) {
		this.albums = albums;
	}

	public Set<Music> getMusics() {
		return musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}

	public Set<Vedio> getVedios() {
		return vedios;
	}

	public void setVedios(Set<Vedio> vedios) {
		this.vedios = vedios;
	}

	public Set<Attention> getAttentions() {
		return attentions;
	}

	public void setAttentions(Set<Attention> attentions) {
		this.attentions = attentions;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
