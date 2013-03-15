package com.way.blog.ss3.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.way.blog.ss3.entity.MyAuthority;
import com.way.blog.ss3.entity.MyRoles;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;


public class MyUserdetialService implements UserDetailsService {

	@Autowired private UserLogin userLogin;
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<UserLogin> userList = null;
		//System.out.println("username===" + username);
		userList = userLoginServiceImpl.find("from UserLogin where account=? and enabled=1 and isAdmin=1", new Object[]{username});
		/*System.out.println(userList);
		if(null != userList && !userList.isEmpty()){
			userLogin = userList.get(0);
			for(MyRoles role : userLogin.getMyRoles()){
				for(MyAuthority auth : role.getMyAuthoritys()){
					System.out.println("------------  auth " + auth.getAuthorityName());
					GrantedAuthority ga = new GrantedAuthorityImpl(auth.getAuthorityName());
					auths.add(ga);
				}
				
			}
		}else if(userList.isEmpty()){
			throw new UsernameNotFoundException("User " + username + " not founded");
		}*/
		if(null != userList && !userList.isEmpty()){
			userLogin = userList.get(0);
			return userLogin;
		}
		
		return (new UserLogin());
	}

}
/* 
//@Repository("securityManagerSupport")
public class SecurityManagerSupport extends HibernateGenericDao<User, Integer>
		implements UserDetailsService {
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		System.out.println("userName===" + userName);
		User user = null;
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<User> userList = null;
		
		userList = this.find("from User where account=? and enabled=1", new Object[]{userName});
		System.out.println(userList);
		if(null != userList && !userList.isEmpty()){
			user = userList.get(0);
			for(Roles role : user.getRoles()){
				GrantedAuthority ga = new GrantedAuthorityImpl(role.getRoleName());
				auths.add(ga);
			}
		}else if(userList.isEmpty()){
			throw new UsernameNotFoundException("User " + userName + " not founded");
		}
		
		return user;
	}
	

}*/
