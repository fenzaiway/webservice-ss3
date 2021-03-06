package com.way.blog.ss3.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.ss3.entity.MyAuthority;

@Repository("myAuthorityDao")
@Transactional
public class MyAuthorityDao extends HibernateGenericDao<MyAuthority, Integer> {

}
