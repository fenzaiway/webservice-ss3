package com.way.blog.ss3.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author fenzaiway
 * 用户权限
 */
@Entity
@Table(name="tb_myauthority")
@Repository
public class MyAuthority implements Serializable {
	
	/**
	 * 权限ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id")
	private Integer id; 
	
	/**
	 * 权限是否可用
	 */
	@Column(name="au_enabled",unique = false, nullable = true, insertable = true, updatable = true)
	private Integer enable;  
	
	/**
	 * 权限名称
	 */
	@Column(name="au_authority_name",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String authorityName; 
	
	/**
	 * 权限描述
	 */
	@Column(name="au_authority_desc",unique = false, nullable = true, insertable = true, updatable = true,length=50)
	private String authorityDesc;
	
	/**
	 * 权限-角色关系
	 */
	@ManyToMany(mappedBy="myAuthoritys",cascade={CascadeType.PERSIST,CascadeType.REFRESH},targetEntity=MyRoles.class)
	private Set<MyRoles> myRoles = new HashSet<MyRoles>();
	
	/**
	 * 权限-资源关系
	 */
	@ManyToMany
	@JoinTable(name="tb_authority_resource",joinColumns={@JoinColumn(name="rid")},inverseJoinColumns={@JoinColumn(name="auid")})
	private Set<MyResources> myResources = new HashSet<MyResources>();

	public MyAuthority() {}

	public MyAuthority(Integer id, Integer enable, String authorityName,
			String authorityDesc, Set<MyRoles> myRoles,
			Set<MyResources> myResources) {
		super();
		this.id = id;
		this.enable = enable;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.myRoles = myRoles;
		this.myResources = myResources;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public Set<MyRoles> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(Set<MyRoles> myRoles) {
		this.myRoles = myRoles;
	}

	public Set<MyResources> getMyResources() {
		return myResources;
	}

	public void setMyResources(Set<MyResources> myResources) {
		this.myResources = myResources;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	} 
	
}
