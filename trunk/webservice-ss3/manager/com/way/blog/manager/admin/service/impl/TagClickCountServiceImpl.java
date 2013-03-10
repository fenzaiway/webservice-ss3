package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Interest;
import com.way.blog.manager.admin.entity.TagClickCount;

@Service("tagClickCountServiceImpl")
@Transactional
public class TagClickCountServiceImpl extends BaseGenericService<TagClickCount, Integer> {

	@Override
	@Resource(name="tagClickCountDao")
	public void setDao(IHibernateGenericDao<TagClickCount, Serializable> dao) {
		
		super.setDao(dao);
	}

	
}
