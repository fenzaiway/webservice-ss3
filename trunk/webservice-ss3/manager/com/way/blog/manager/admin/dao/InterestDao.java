package com.way.blog.manager.admin.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.manager.admin.entity.Interest;

@Repository("interestDao")
@Transactional
public class InterestDao extends HibernateGenericDao<Interest, Integer> {

}
