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
 * 工作表
 */
@Entity
@Table(name="tb_job")
@Repository
public class Job implements Serializable {
	/**
	 * 工作ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 行业类型
	 */
	@Column(name="job_type",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String jobType;
	
	/**
	 * 工作名称
	 */
	@Column(name="job_name",unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	private String jobName;
	
	/**
	 * 行业工作记录创建时间
	 */
	@Column(name="job_create_time",unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	private String createTime;
	
	/**
	 * 创建用户
	 */
	@Column(name="job_username",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String username;

	public Job() {}

	public Job(String createTime, int id, String jobName, String jobType,
			String username) {
		super();
		this.createTime = createTime;
		this.id = id;
		this.jobName = jobName;
		this.jobType = jobType;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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
