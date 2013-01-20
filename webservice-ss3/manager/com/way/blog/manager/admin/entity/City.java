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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Administrator
 * 分析：城市归属省份，而城市又有输入自己的城区
 *
 */
@Entity
@Table(name="tb_city")
@Repository
public class City implements Serializable {

	private static final long serialVersionUID = 3029596597342009080L;

	private int id;
	
	private String cityID;  ////城市编号
	
	private String city;	////城市名称
	
	//private String father;	////城市归属省份
	
	private Province province;  ////城市归属省份
	
	//private Set<Area> areaSet = new HashSet<Area>();   ////省市下面有多个城市

	public City() {
		super();
	}

	public City( String city, String cityID, int id,
			Province province) {
		super();
		//this.areaSet = areaSet;
		this.city = city;
		this.cityID = cityID;
		this.id = id;
		this.province = province;
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

	@Column(name="cityID",length=6)
	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	@Column(name="city",length=50)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="father",referencedColumnName="provinceID")
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

//	@OneToMany(mappedBy="city",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//	public Set<Area> getAreaSet() {
//		return areaSet;
//	}
//
//	public void setAreaSet(Set<Area> areaSet) {
//		this.areaSet = areaSet;
//	}
//	
}
