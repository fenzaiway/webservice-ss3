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
 * 空间访问记录表
 * @author fenzaiway
 *
 */
@Entity
@Table(name="tb_blog_zone_visits")
@Repository
public class BlogZoneVisit implements Serializable {

	private static final long serialVersionUID = 1495308034864692675L;
	
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 访问用户
	 */
	@Expose
	@Column(name="bzv_username",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String username;
	
	/**
	 * 空间所属用户
	 */
	@Expose
	@Column(name="bzv_zoneuser",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String zoneuser;
	
	/**
	 * 访问时间
	 */
	@Expose
	@Column(name="bzv_visit_time",unique = false, nullable = false, insertable = true, updatable = true,length=25)
	private String visitTime;
	
	/**
	 * 访问用户IP
	 */
	@Expose
	@Column(name="bzv_visit_ip",unique = false, nullable = false, insertable = true, updatable = true,length=50)
	private String visitIp;
	
	/**
	 * 关注的空间
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private BlogZone blogZone;

	public BlogZoneVisit() {}

	public BlogZoneVisit(BlogZone blogZone, int id, String username,
			String visitIp, String visitTime, String zoneuser) {
		super();
		this.blogZone = blogZone;
		this.id = id;
		this.username = username;
		this.visitIp = visitIp;
		this.visitTime = visitTime;
		this.zoneuser = zoneuser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZoneuser() {
		return zoneuser;
	}

	public void setZoneuser(String zoneuser) {
		this.zoneuser = zoneuser;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}
	
	
}
