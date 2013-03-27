package com.way.blog.user.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.user.entity.UserHeadImg;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserHeadImgServiceImpl;
import com.way.blog.user.service.impl.UserLoginServiceImpl;

@Controller("userHeadImgAction")
@ParentPackage("interceptor")
@Namespace("/headimg")
public class UserHeadImgAction extends BaseAction implements ModelDriven<UserHeadImg>{
	@Autowired
	private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired
	private UserHeadImg userHeadImg;
	@Autowired
	private UserLogin userLogin;
	
	
	////保存头像,通过jquery异步提交的方式保存
	@Action(value="save",results={
			@Result(type="json")
	})
	public String save(){
		try {
			this.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		userHeadImgServiceImpl.saveHeadImg(myusername);
		return SUCCESS;
	}
	
	////更新
	@Action(value="update",results={
			@Result(type="json")
	})
	public String update(){
		UserHeadImg headImg = userHeadImgServiceImpl.findById(userHeadImg.getId());
		headImg.setImgLocation(userHeadImg.getImgLocation());
		userHeadImgServiceImpl.update(headImg);
		return SUCCESS;
	}
	
	///根据ID取出用户的头像
	public UserHeadImg findUserHeadImgById(int id){
		return userHeadImgServiceImpl.findById(id);
	}
	
	//根据用户名取出用户的头像
	public UserHeadImg findUserHeadImgMyUsername(String username){
		return userHeadImgServiceImpl.myFindByProperty("username", username);
	}
	
	public UserHeadImg getModel() {
		// TODO Auto-generated method stub
		return userHeadImg;
	}
	
	
	
}
