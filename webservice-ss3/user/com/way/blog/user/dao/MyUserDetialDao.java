package com.way.blog.user.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.user.entity.MyUserDetial;

@Repository("myUserDetialDao")
@Transactional
public class MyUserDetialDao extends HibernateGenericDao<MyUserDetial, Integer> {

}
