package com.way.blog.ss3.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MyUserdetialService implements UserDetailsService {

	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		return null;
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
