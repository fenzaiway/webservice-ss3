package com.way.blog.user.service.impl;

import java.io.Serializable;
import java.util.List;

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
	
	/**
	 * 根据用户头像取出用户的头像地址
	 * @param username
	 * @return
	 */
	public String getHeadImgUrl(String username){
		//String sql = "select h.imgLocation from UserHeadImg where username='"+username+"'";
		//this.myGetHibernateTemplate().find
		//UserHeadImg uh = this.myFindByProperty(propertyName, value)
		UserHeadImg userHeadImg = this.myFindByProperty("username", username);
		//List<UserHeadImg> userHeadImgList =  this.myFindByProperty("username", username);//this.find(sql, new Object[]{});
		/*if(null!=userHeadImgList && !userHeadImgList.isEmpty()){
			return userHeadImgList.get(0).getImgLocation();
		}*/		
		if(null!=userHeadImg){
			return userHeadImg.getImgLocation();
		}
		return "";
	}
}
