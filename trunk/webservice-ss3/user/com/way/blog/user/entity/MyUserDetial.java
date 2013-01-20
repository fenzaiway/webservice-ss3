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

/**
 * 
 * @author fenzaiway
 * 用户详细信息
 * 为了不引起跟SpringSecurity命名冲突，加上My
 */
@Entity
@Table(name="tb_myuser_detial")
@Repository
public class MyUserDetial implements Serializable {
	
	private static final long serialVersionUID = -3571373122351429984L;

	/**
	 * 详细资料ID
	 */
	@Id
	@GenericGenerator(name="foreignKey", strategy="foreign", parameters=@Parameter(value="user",name="property"))  
	@GeneratedValue(generator="foreignKey", strategy=GenerationType.IDENTITY)
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 用户昵称
	 */
	@Column(name="myd_username",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String username;
	
	/**
	 * 用户生日
	 */
	@Column(name="myd_birthday",unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	private String birthday;
	
	/**
	 * 用户血型
	 */
	@Column(name="myd_blood_type",unique = false, nullable = true, insertable = true, updatable = true)
	private int bloodType;
	
	/**
	 * 用户感情状态
	 */
	@Column(name="myd_love_statue",unique = false, nullable = true, insertable = true, updatable = true)
	private int loveStatue;
	
	/**
	 * 用户公司名称
	 */
	@Column(name="myd_company_name",unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	private String companyName;
	
	/**
	 * 用户公司地址
	 */
	@Column(name="myd_company_address",unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	private String companyAddress;
	
	/**
	 * 用户详细地址
	 */
	@Column(name="myd_address_detial",unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	private String addressDetial;
	
	/**
	 * 用户邮编
	 */
	@Column(name="myd_zcode",unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	private String zcode;
	
	/**
	 * 联系电话
	 */
	@Column(name="myd_phone",unique = false, nullable = true, insertable = true, updatable = true, length = 15)
	private String phone;
	
	/**
	 * 用户所在地
	 */
	@OneToOne(mappedBy="myUserDetial",fetch=FetchType.LAZY,targetEntity=Address.class,cascade=CascadeType.ALL)
	private Address address;
	
	/**
	 * 用户的工作
	 */
	@Column(name="myd_job_name",unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	private String jobName;
	
	/**
	 * 用户兴趣(至少选择2项)
	 */
	@Column(name="myd_interests",unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	private String interests;
	
	/**
	 * 关联的用户
	 */
	@OneToOne(mappedBy="myUserDetial",targetEntity=UserLogin.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="id",referencedColumnName="id")
	private UserLogin user;

	public MyUserDetial() {}

	public MyUserDetial(Address address, String addressDetial, String birthday,
			int bloodType, String companyAddress, String companyName, int id,
			String interests, String jobName, int loveStatue, String phone,
			UserLogin user, String username, String zcode) {
		super();
		this.address = address;
		this.addressDetial = addressDetial;
		this.birthday = birthday;
		this.bloodType = bloodType;
		this.companyAddress = companyAddress;
		this.companyName = companyName;
		this.id = id;
		this.interests = interests;
		this.jobName = jobName;
		this.loveStatue = loveStatue;
		this.phone = phone;
		this.user = user;
		this.username = username;
		this.zcode = zcode;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getBloodType() {
		return bloodType;
	}

	public void setBloodType(int bloodType) {
		this.bloodType = bloodType;
	}

	public int getLoveStatue() {
		return loveStatue;
	}

	public void setLoveStatue(int loveStatue) {
		this.loveStatue = loveStatue;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getAddressDetial() {
		return addressDetial;
	}

	public void setAddressDetial(String addressDetial) {
		this.addressDetial = addressDetial;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public UserLogin getUser() {
		return user;
	}

	public void setUser(UserLogin user) {
		this.user = user;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
