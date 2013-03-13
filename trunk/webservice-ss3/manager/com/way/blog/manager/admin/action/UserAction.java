package com.way.blog.manager.admin.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.ss3.entity.MyRoles;
import com.way.blog.ss3.service.impl.MyRolesServiceImpl;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.user.service.impl.UserRegisterServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("userAction")
@ParentPackage("interceptor")
@Namespace("/admin/user")
public class UserAction extends BaseAction implements ModelDriven<UserLogin>{

	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired
	private UserRegisterServiceImpl userRegisterServiceImpl;
	@Autowired private MyRolesServiceImpl myRolesServiceImpl;
	@Autowired private MyRoles myRoles;
	
	private List<UserLogin> userLoginList;
	private List<UserRegister> userRegisterList;
	private List<MyRoles> myRolesList;
	private List<String> selectRoleids = new ArrayList<String>();
	private UserLogin userLogin;
	private UserRegister userRegister;
	private String roleid;
	private int userid;
	private String hql;
	
	
	private String deleteIds;
	

	@Action(value="userLoginList",results={
			@Result(name="success",location="/admin/user/userLoginList.jsp")
	})
	public String getUserList(){
		//userLoginList = userLoginServiceImpl.loadAll();
		
		paginationSupport = userLoginServiceImpl.findPageByQuery(UserLoginServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new String[]{});
		userLoginList = paginationSupport.getItems();
		paginationSupport.setUrl("admin/user/userLoginList.do");
		return SUCCESS;
	}
	
	@Action(value="search",results={
			//@Result(name="success",location="/admin/user/userLoginList.do",type="redirect")
			@Result(name="success",location="/admin/user/userLoginList.jsp")
	})
	public String search(){
		
		paginationSupport = userLoginServiceImpl.search(PaginationSupport.PAGESIZE,startIndex,userLogin);
		userLoginList = paginationSupport.getItems();
		//System.out.println(hql);
		//session.setAttribute("hql", hql);
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
	
	@Action(value="gotoEdit",results={
			@Result(name="success",location="/admin/user/userLoginEdit.jsp")
	})
	public String gotoEdit(){
		userLogin = userLoginServiceImpl.findById(userid);
		if(null!=userLogin.getMyRoles() && !userLogin.getMyRoles().isEmpty()){
			for(MyRoles mr : userLogin.getMyRoles()){
				selectRoleids.add(mr.getId()+"");
			}
		}
		myRolesList = myRolesServiceImpl.loadAll();
		return SUCCESS;
	}
	
	@Action(value="update",results={
			@Result(name="success",location="/admin/user/userLoginList.do",type="redirect")
	})
	public String update(){
		
		UserLogin ul = userLoginServiceImpl.findById(userLogin.getId());
		ul.setMyRoles(null);
		if(0 == roleid.length()){ //用户没有选择角色
			userLoginServiceImpl.update(ul);
			return SUCCESS;
		}
		
		
		String roleIds[] = splitRoleId(roleid);
		
		int length = roleIds.length;
		Set<UserLogin> userLoginSet;
		Set<MyRoles> myRolesSet = new HashSet<MyRoles>();
		for(int i=0; i<length; i++){
			myRoles = myRolesServiceImpl.findById(Integer.parseInt(roleIds[i].trim()));
			////设置双向关联
			/*if(null != myRoles.getUserLogins() && !myRoles.getUserLogins().isEmpty()){
				myRoles.getUserLogins().add(ul);
			}else{
				userLoginSet = new HashSet<UserLogin>();
				userLoginSet.add(ul);
				myRoles.setUserLogins(userLoginSet);
			}*/
			myRolesSet.add(myRoles);
		
			//myRolesServiceImpl.update(myRoles);
		}
		ul.setMyRoles(myRolesSet);
		userLoginServiceImpl.update(ul);
		return SUCCESS;
	}
	
	private String[] splitRoleId(String myRoleid)
	{
		return myRoleid.split(",");
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

	public UserLogin getModel() {
		// TODO Auto-generated method stub
		return userLogin;
	}

	public MyRoles getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(MyRoles myRoles) {
		this.myRoles = myRoles;
	}

	public List<MyRoles> getMyRolesList() {
		return myRolesList;
	}

	public void setMyRolesList(List<MyRoles> myRolesList) {
		this.myRolesList = myRolesList;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public List<String> getSelectRoleids() {
		return selectRoleids;
	}

	public void setSelectRoleids(List<String> selectRoleids) {
		this.selectRoleids = selectRoleids;
	}

	
	
	
}
