package com.way.blog.user.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author fenzaiway
 * 用户注册实体
 * 表名以“tb_”开头
 * 字段以“re_”开头
 */
@Entity
@Table(name="tb_user_register")
@Repository
public class UserRegister implements Serializable {
	
	/**
	 * 注册ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 注册邮箱
	 */
	@Column(name="re_email",unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	private String email;
	
	/**
	 * 注册密码
	 */
	@Column(name="re_password",unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	private String password;
	
	/**
	 * 注册用户昵称，空间显示这个昵称
	 */
	@Column(name="re_username",unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	private String username;
	
	/**
	 * 注册IP
	 */
	@Column(name="re_ip",unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	private String ip;
	
	/**
	 * 注册时间
	 */
	@Column(name="re_registration_time",unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	private String registrationTime;
	
	/**
	 * 注册性别
	 */
	@Column(name="re_sex",unique = false, nullable = true, insertable = true, updatable = true)
	private int sex;
	
	/**
	 * 用户注册的时候生成的一个验证码，将该验证码发送到用户的邮箱让用户验证
	 * 验证码的生成规则：md5.code(email{username})
	 */
	@Column(name="re_verifi_code",unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	private String verifiCode;
	
	/**
	 * 注册账号是否可用
	 * 通过邮箱验证的才可使用，才可以生成记录保存登录信息表
	 * 0表示账号不可用，1表示可以正常使用
	 */
	@Column(name="re_enabled",unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	private int enabled;
	
	@OneToOne(cascade=CascadeType.ALL,targetEntity=UserLogin.class,mappedBy="userRegister")
	private UserLogin userLogin;

	public UserRegister() {}

	public UserRegister(int id, String email, String password, String username,
			String ip, String registrationTime, int sex, String verifiCode,
			int enabled, UserLogin userLogin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.ip = ip;
		this.registrationTime = registrationTime;
		this.sex = sex;
		this.verifiCode = verifiCode;
		this.enabled = enabled;
		this.userLogin = userLogin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public String getVerifiCode() {
		return verifiCode;
	}

	public void setVerifiCode(String verifiCode) {
		this.verifiCode = verifiCode;
	}
	
}
