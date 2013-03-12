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

import com.way.blog.user.entity.UserLogin;

/**
 * 
 * @author fenzaiway
 * 用户角色
 */

@Entity
@Table(name="tb_myroles")
@Repository
public class MyRoles implements Serializable {
	
	/**
	 * 角色ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id")
	private int id;
	
	/**
	 * 角色名称
	 */
	@Column(name="ro_role_name",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String roleName;
	
	/**
	 * 角色描述
	 */
	@Column(name="ro_role_desc",unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String roleDesc;
	
	/**
	 * 权限-角色关系，多对多
	 */
	@ManyToMany
	@JoinTable(name="tb_authority_role",joinColumns={@JoinColumn(name="rid")},inverseJoinColumns={@JoinColumn(name="auid")})
	private Set<MyAuthority> myAuthoritys = new HashSet<MyAuthority>();
	
	/**
	 * 用户-角色关系，多对多
	 */
	@ManyToMany(mappedBy="myRoles",cascade={CascadeType.PERSIST,CascadeType.REFRESH},targetEntity=UserLogin.class)
	private Set<UserLogin> userLogins = new HashSet<UserLogin>();

	public MyRoles() {}

	public MyRoles(int id, Set<MyAuthority> myAuthoritys, String roleDesc,
			String roleName, Set<UserLogin> userLogins) {
		super();
		this.id = id;
		this.myAuthoritys = myAuthoritys;
		this.roleDesc = roleDesc;
		this.roleName = roleName;
		this.userLogins = userLogins;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<MyAuthority> getMyAuthoritys() {
		return myAuthoritys;
	}

	public void setMyAuthoritys(Set<MyAuthority> myAuthoritys) {
		this.myAuthoritys = myAuthoritys;
	}

	public Set<UserLogin> getUserLogins() {
		return userLogins;
	}

	public void setUserLogins(Set<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}
	
	
}
