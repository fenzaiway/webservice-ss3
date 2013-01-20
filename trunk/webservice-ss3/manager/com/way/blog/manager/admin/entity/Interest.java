package com.way.blog.manager.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author fenzaiway
 *  兴趣表
 */
@Entity
@Table(name="tb_interest")
@Repository
public class Interest implements Serializable {
	
	/**
	 * 兴趣ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 兴趣名
	 */
	@Column(name="in_interest_name",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String interestName;
	
	/**
	 * 兴趣添加时间
	 */
	@Column(name="in_create_time",unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	private String createTime;
	
	/**
	 * 添加用户
	 */
	@Column(name="in_username",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String username;

	public Interest() {}

	public Interest(String createTime, int id, String interestName,
			String username) {
		super();
		this.createTime = createTime;
		this.id = id;
		this.interestName = interestName;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInterestName() {
		return interestName;
	}

	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
