package com.way.blog.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author fenzaiway
 * 用户地址
 */
@Entity
@Table(name="tb_address")
@Repository
public class Address implements Serializable {
	
	private static final long serialVersionUID = -2142997413636506810L;

	/**
	 * 地址ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 省份
	 */
	@Column(name="addr_province",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String province;
	
	/**
	 * 城市
	 */
	@Column(name="addr_city",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String city;
	
	/**
	 * 城区
	 */
	@Column(name="addr_area",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String area;
	
	
	/**
	 * 关联用户详细资料
	 */
	@OneToOne
	private MyUserDetial myUserDetial;


	public Address() {}


	public Address(String area, String city, int id, MyUserDetial myUserDetial,
			String province) {
		super();
		this.area = area;
		this.city = city;
		this.id = id;
		this.myUserDetial = myUserDetial;
		this.province = province;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public MyUserDetial getMyUserDetial() {
		return myUserDetial;
	}


	public void setMyUserDetial(MyUserDetial myUserDetial) {
		this.myUserDetial = myUserDetial;
	}
	
}
