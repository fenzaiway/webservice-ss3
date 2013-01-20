package com.way.blog.user.dao;

import org.springframework.stereotype.Repository;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.user.entity.UserRegister;

@Repository("userRegisterDao")
public class UserRegisterDao extends HibernateGenericDao<UserRegister, Integer> {

}
