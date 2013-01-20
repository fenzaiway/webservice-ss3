package com.way.blog.manager.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Administrator
 * 城区归属于城市
 */
@Entity
@Table(name="tb_area")
@Repository
public class Area implements Serializable {
	private int id;
	
	private String areaID;	////城区编号
	
	private String area;	//城区名称
	
	//private String father;	////城区归属城市
	
	private City city;		////城区归属城市

	public Area() {
		super();
	}

	public Area(String area, String areaID, City city, int id) {
		super();
		this.area = area;
		this.areaID = areaID;
		this.city = city;
		this.id = id;
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

	@Column(name="areaID",length=50)
	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	@Column(name="area",length=60)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="father",referencedColumnName="cityID")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
