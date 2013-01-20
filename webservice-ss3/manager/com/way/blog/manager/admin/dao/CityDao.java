package com.way.blog.manager.admin.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.manager.admin.entity.City;

@Repository("cityDao")
@Transactional
public class CityDao extends HibernateGenericDao<City, Integer> {

}
