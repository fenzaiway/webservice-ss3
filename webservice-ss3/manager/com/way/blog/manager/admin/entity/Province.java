package com.way.blog.manager.admin.entity;

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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Administrator
 *	省份用用自己的城市，属于一对多关系
 */
@Entity
@Table(name="tb_province")
@Repository
public class Province implements Serializable {
	
	private static final long serialVersionUID = -8554981930279256468L;

	private int id;
	
	private String provinceID;	////省的编号
	
	private String province;	////省的名称
	
	//private Set<City> citySet = new HashSet<City>();

	public Province() {
		super();
	}


	public Province( int id, String province,
			String provinceID) {
		super();
		//this.citySet = citySet;
		this.id = id;
		this.province = province;
		this.provinceID = provinceID;
	}


	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="provinceID",length=6)
	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	@Column(name="province",length=40)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

//	@OneToMany(mappedBy="province",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//	public Set<City> getCitySet() {
//		return citySet;
//	}
//
//	public void setCitySet(Set<City> citySet) {
//		this.citySet = citySet;
//	}
	
}
