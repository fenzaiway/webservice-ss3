package com.way.blog.ss3.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.ss3.entity.MyRoles;

@Repository("myRolesDao")
@Transactional
public class MyRolesDao extends HibernateGenericDao<MyRoles, Integer> {

}
