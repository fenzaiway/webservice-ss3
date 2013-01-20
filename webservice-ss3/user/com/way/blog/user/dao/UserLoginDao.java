package com.way.blog.user.dao;

import org.springframework.stereotype.Repository;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.user.entity.UserLogin;

@Repository("userLoginDao")
public class UserLoginDao extends HibernateGenericDao<UserLogin, Integer>{

}
