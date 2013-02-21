package com.way.blog.user.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.UserHeadImg;

@Service("userHeadImgServiceImpl")
public class UserHeadImgServiceImpl extends BaseGenericService<UserHeadImg, Integer> {
	@Override
	@Resource(name="userHeadImgDao")
	public void setDao(IHibernateGenericDao<UserHeadImg, Serializable> dao) {
		super.setDao(dao);
	}
}
