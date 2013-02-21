package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.AlbumType;
import com.way.blog.zone.entity.BlogZone;

@Service
public class AlbumTypeServiceImpl extends BaseGenericService<AlbumType, Integer> {
	@Autowired
	private BlogZoneServiceImpl blogZoneServiceImpl;
	
	@Override
	@Resource(name="albumTypeDao")
	public void setDao(IHibernateGenericDao<AlbumType, Serializable> dao) {
		super.setDao(dao);
	}

	/**
	 * 这里先使用默认值，后面再修改
	 */
	@Override
	public int save(AlbumType albumType) {
		// TODO Auto-generated method stub
		albumType.setAlbumTypeCreateTime(MyFormatDate.getNowDate());
		albumType.setIsAlbumAllowView(0);
		albumType.setIsPasswordView(1);
		albumType.setQuestion("");
		albumType.setPasswordAnswer("");
		BlogZone blogZone = blogZoneServiceImpl.myFindByProperty("username", albumType.getUsername());
		albumType.setBlogZone(blogZone);
		
		return super.save(albumType);
	}
	
	
}
