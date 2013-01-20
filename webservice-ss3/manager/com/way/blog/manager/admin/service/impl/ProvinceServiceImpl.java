package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Province;

@Service("provinceServiceImpl")
@Transactional
public class ProvinceServiceImpl extends BaseGenericService<Province, Integer> {

	@Override
	@Resource(name="provinceDao")
	public void setDao(IHibernateGenericDao<Province, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
}
