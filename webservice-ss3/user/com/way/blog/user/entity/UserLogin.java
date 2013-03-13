package com.way.blog.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.ss3.entity.MyAuthority;
import com.way.blog.ss3.entity.MyRoles;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogTag;

/**
 * 
 * @author fenzaiway
 * 用户登录表
 */
@Repository
@Entity
@Table(name="tb_user_login")
public class UserLogin implements Serializable,UserDetails {
	
	private static final long serialVersionUID = 8703139935005689511L;

	/**
	 * 登录ID，跟注册的ID一致
	 */
	@Id
	@GeneratedValue(generator="incrementGenerator", strategy=GenerationType.IDENTITY)  
	@GenericGenerator(name="incrementGenerator", strategy="increment")
	@Column(name="id")
	private int id;
	
	/**
	 * 登录昵称
	 */
	@Column(name="user_username",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String nickname;  /////原先是username ,与SpringSecurity冲突，2013-3-12修改
	
	/**
	 * 登录密码
	 */
	@Column(name="user_password",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String password;
	
	/**
	 * 登录账号，即注册邮箱
	 */
	@Column(name="user_account",unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	private String account;
	
	/**
	 * 登录表创建时间
	 */
	@Column(name="user_create_time",unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	private String createTime;
	
	/**
	 * 账号是否可用
	 */
	@Column(name="user_enabled",unique = false, nullable = false, insertable = true, updatable = true)
	private int enabled;
	
	/**
	 * 是否是管理员
	 */
	@Column(name="user_isadmin",unique = false, nullable = false, insertable = true, updatable = true)
	private int isAdmin;
	
	/**
	 * 关联注册表
	 */
	@OneToOne(targetEntity=UserRegister.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private UserRegister userRegister;
	
	/**
	 * 关联的空间
	 */
	@OneToOne(cascade=CascadeType.ALL,targetEntity=BlogZone.class,fetch=FetchType.LAZY)
	private BlogZone blogZone;
	
	/**
	 * 用户详细信息
	 */
	@OneToOne(cascade=CascadeType.ALL,targetEntity=MyUserDetial.class)
	@PrimaryKeyJoinColumn
	private MyUserDetial myUserDetial;
	
	/**
	 * 用户角色
	 */
	@ManyToMany
	@JoinTable(name="tb_user_role",joinColumns={@JoinColumn(name="userid")},inverseJoinColumns={@JoinColumn(name="rid")})
	private Set<MyRoles> myRoles = new HashSet<MyRoles>();
	
	/**
	 * 用户头像
	 */
	@OneToOne(cascade=CascadeType.ALL,targetEntity=UserHeadImg.class)
	@PrimaryKeyJoinColumn
	private UserHeadImg userHeadImg;
	
	/**
	 * 用户关注的标签
	 */
	@ManyToMany(mappedBy="userLogins",cascade={CascadeType.ALL},fetch=FetchType.LAZY,targetEntity=Tag.class)
	private Set<Tag> tags = new HashSet<Tag>();
	
	public UserLogin() {}

	

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>(myRoles.size());
		for(MyRoles role : myRoles){
			for(MyAuthority auth : role.getMyAuthoritys()){
				System.out.println("---------------auth-- " + auth.getAuthorityName());
				grantedAuthorityList.add(new GrantedAuthorityImpl(auth.getAuthorityName()));
			}
		}
		
		return grantedAuthorityList;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		System.out.println("-------------------enabled-------" + enabled);
		return 1 == enabled ? true : false;
		//return true;
	}

	public MyUserDetial getMyUserDetial() {
		return myUserDetial;
	}

	public void setMyUserDetial(MyUserDetial myUserDetial) {
		this.myUserDetial = myUserDetial;
	}
	
	public Set<MyRoles> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(Set<MyRoles> myRoles) {
		this.myRoles = myRoles;
	}

	public UserHeadImg getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(UserHeadImg userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public UserLogin(String account, BlogZone blogZone, String createTime,
			int enabled, int id, int isAdmin, Set<MyRoles> myRoles,
			MyUserDetial myUserDetial, String nickname, String password,
			Set<Tag> tags, UserHeadImg userHeadImg, UserRegister userRegister) {
		super();
		this.account = account;
		this.blogZone = blogZone;
		this.createTime = createTime;
		this.enabled = enabled;
		this.id = id;
		this.isAdmin = isAdmin;
		this.myRoles = myRoles;
		this.myUserDetial = myUserDetial;
		this.nickname = nickname;
		this.password = password;
		this.tags = tags;
		this.userHeadImg = userHeadImg;
		this.userRegister = userRegister;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((blogZone == null) ? 0 : blogZone.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + enabled;
		result = prime * result + id;
		result = prime * result + isAdmin;
		result = prime * result + ((myRoles == null) ? 0 : myRoles.hashCode());
		result = prime * result
				+ ((myUserDetial == null) ? 0 : myUserDetial.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result
				+ ((userHeadImg == null) ? 0 : userHeadImg.hashCode());
		result = prime * result
				+ ((userRegister == null) ? 0 : userRegister.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogin other = (UserLogin) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (blogZone == null) {
			if (other.blogZone != null)
				return false;
		} else if (!blogZone.equals(other.blogZone))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id != other.id)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (myRoles == null) {
			if (other.myRoles != null)
				return false;
		} else if (!myRoles.equals(other.myRoles))
			return false;
		if (myUserDetial == null) {
			if (other.myUserDetial != null)
				return false;
		} else if (!myUserDetial.equals(other.myUserDetial))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (userHeadImg == null) {
			if (other.userHeadImg != null)
				return false;
		} else if (!userHeadImg.equals(other.userHeadImg))
			return false;
		if (userRegister == null) {
			if (other.userRegister != null)
				return false;
		} else if (!userRegister.equals(other.userRegister))
			return false;
		return true;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getUsername() {
		// TODO Auto-generated method stub
		System.out.println("-----------getUsername-----" + account);
		return this.getAccount();
	}

	

	
}
