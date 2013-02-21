package com.way.blog.user.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Repository;

@Repository
@Entity
@Table(name="tb_user_headimg")
public class UserHeadImg implements Serializable {

	private static final long serialVersionUID = 4393334409562269672L;
	
	@Id
	@GenericGenerator(name="foreignKey", strategy="foreign", parameters=@Parameter(value="user",name="property"))  
	@GeneratedValue(generator="foreignKey", strategy=GenerationType.IDENTITY)
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 用户昵称
	 */
	@Column(name="user_username",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String username;
	
	/**
	 * 头像位置
	 */
	@Column(name="user_img_location",unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	private String imgLocation;
	
	
	@OneToOne(mappedBy="userHeadImg",targetEntity=UserLogin.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="id",referencedColumnName="id")
	private UserLogin user;

	public UserHeadImg() {}

	public UserHeadImg(int id, String username, String imgLocation,
			UserLogin user) {
		super();
		this.id = id;
		this.username = username;
		this.imgLocation = imgLocation;
		this.user = user;
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

	public String getImgLocation() {
		return imgLocation;
	}

	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	public UserLogin getUser() {
		return user;
	}

	public void setUser(UserLogin user) {
		this.user = user;
	}

	

}
