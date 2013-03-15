package com.way.blog.ss3.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author fenzaiway
 * 系统资源（访问链接）
 */


@Entity
@Table(name="tb_myresource")
@Repository
public class MyResources implements Serializable {
	
	/**
	 * 资源ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id")
	private Integer id;  //资源ID
	
	/**
	 * 资源路径
	 */
	@Column(name="res_url",unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	private String url;  //资源路径
	
	/**
	 * 
	 */
	@Column(name="res_resource_name",unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	private String resourceName; ////访问资源的名称
	
	/**
	 * 资源类型
	 */
	@Column(name="res_type",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String type;  //资源类型(.do,.action,url)
	
	/**
	 * 资源描述
	 */
	@Column(name="res_desc_string",unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	private String descString;  //资源描述
	
	/**
	 * 资源是否可用
	 */
	@Column(name="res_enabled",unique = false, nullable = true, insertable = true, updatable = true)
	private Integer enabled; ///资源是否可用
	
	/**
	 * 资源-权限
	 */
	@ManyToMany(mappedBy="myResources",cascade={CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	private Set<MyAuthority> myAuthoritys = new HashSet<MyAuthority>();

	public MyResources() {}

	public MyResources(String descString, Integer enabled, Integer id,
			Set<MyAuthority> myAuthoritys, String resourceName, String type,
			String url) {
		super();
		this.descString = descString;
		this.enabled = enabled;
		this.id = id;
		this.myAuthoritys = myAuthoritys;
		this.resourceName = resourceName;
		this.type = type;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescString() {
		return descString;
	}

	public void setDescString(String descString) {
		this.descString = descString;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Set<MyAuthority> getMyAuthoritys() {
		return myAuthoritys;
	}

	public void setMyAuthoritys(Set<MyAuthority> myAuthoritys) {
		this.myAuthoritys = myAuthoritys;
	}
	
}
