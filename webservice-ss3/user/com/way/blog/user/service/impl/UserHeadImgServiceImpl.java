package com.way.blog.user.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.entity.UserHeadImg;
import com.way.blog.user.entity.UserLogin;

@Service("userHeadImgServiceImpl")
public class UserHeadImgServiceImpl extends BaseGenericService<UserHeadImg, Integer> {
	@Override
	@Resource(name="userHeadImgDao")
	public void setDao(IHibernateGenericDao<UserHeadImg, Serializable> dao) {
		super.setDao(dao);
	}
	private static final String DEFAULT_IMG_LOCATION = "images/default_head/001.png"; /// 默认头像路径
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired private UserHeadImg userHeadImg;
	@Autowired private UserLogin userLogin;
	/**
	 * 根据用户取出用户的头像地址
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
	
	/**
	 * 保存用户头像
	 * @param username
	 * @return
	 */
	public int saveHeadImg(String username){
		userHeadImg.setUsername(username);
		userHeadImg.setImgLocation(DEFAULT_IMG_LOCATION);
		userLogin = userLoginServiceImpl.myFindByProperty("username", username);
		///设置双向关联
		userLogin.setUserHeadImg(userHeadImg);
		userHeadImg.setUser(userLogin);
		return save(userHeadImg);
	}
}
