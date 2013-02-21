package com.way.blog.manager.admin.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.user.service.impl.UserRegisterServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("userAction")
@ParentPackage("interceptor")
@Namespace("/admin/user")
public class UserAction extends BaseAction {

	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired
	private UserRegisterServiceImpl userRegisterServiceImpl;
	
	private List<UserLogin> userLoginList;
	private List<UserRegister> userRegisterList;
	private UserLogin userLogin;
	private UserRegister userRegister;
	private String hql;
	private static final String HQL = "from UserLogin where 1=1";
	
	private String deleteIds;
	
	@Override
	public void init() {
		hql = (String) session.getAttribute("hql");
		if(null == hql){
			hql=HQL;
		}
		super.init();
	}

	@Action(value="userLoginList",results={
			@Result(name="success",location="/admin/user/userLoginList.jsp")
	})
	public String getUserList(){
		//userLoginList = userLoginServiceImpl.loadAll();
		if(1 == isValSession){
			session.removeAttribute("hql");
			hql = HQL;
		}
		paginationSupport = userLoginServiceImpl.findPageByQuery(HQL, PaginationSupport.PAGESIZE, startIndex, new String[]{});
		userLoginList = paginationSupport.getItems();
		paginationSupport.setUrl("admin/user/userLoginList.do");
		return SUCCESS;
	}
	
	@Action(value="search",results={
			@Result(name="success",location="/admin/user/userLoginList.do",type="redirect")
	})
	public String search(){
		System.out.println(userLogin.getUsername());
		hql = HQL + " and username like '%"+ userLogin.getUsername() +"%'";
		System.out.println(hql);
		session.setAttribute("hql", hql);
		return SUCCESS;
	}
	@Action(value="deleteAll",results={
			@Result(name="success",type="json")
	})
	public String deleteAll(){
		//System.out.println(deleteIds.length);
		////根据id删除用户，将所有关于用户的信息删除掉
		String[] ids = deleteIds.split(",");
		for(int i=0; i<ids.length; i++){
			System.out.println(ids[i]);
			//userLoginServiceImpl.delete(ids[i]);
		}
		//this.returnJson(ids.toString());
		return null;
	}
	
	@Action(value="deleteById",results={
			@Result(name="success",location="/admin/user/userLoginList.do",type="redirect")
	})
	public String deleteById(){
		userLoginServiceImpl.delete(userLogin.getId());
		return SUCCESS;
	}
	
	public List<UserLogin> getUserLoginList() {
		return userLoginList;
	}

	public void setUserLoginList(List<UserLogin> userLoginList) {
		this.userLoginList = userLoginList;
	}

	public List<UserRegister> getUserRegisterList() {
		return userRegisterList;
	}

	public void setUserRegisterList(List<UserRegister> userRegisterList) {
		this.userRegisterList = userRegisterList;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	
	
	
}
