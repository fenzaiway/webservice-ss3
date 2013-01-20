package com.way.blog.manager.admin.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.HibernateGenericDao;
import com.way.blog.manager.admin.entity.Province;

@Repository("provinceDao")
@Transactional
public class ProvinceDao extends HibernateGenericDao<Province, Integer> {

}
