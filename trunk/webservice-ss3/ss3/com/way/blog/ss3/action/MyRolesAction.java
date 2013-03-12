package com.way.blog.ss3.action;

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
import com.way.blog.ss3.entity.MyAuthority;
import com.way.blog.ss3.entity.MyRoles;
import com.way.blog.ss3.service.impl.MyAuthorityServiceImpl;
import com.way.blog.ss3.service.impl.MyRolesServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("myRolesAction")
@ParentPackage("interceptor")
@Namespace("/admin/role")
public class MyRolesAction extends BaseAction implements ModelDriven<MyRoles>{

	@Autowired private MyRolesServiceImpl myRolesServiceImpl;
	@Autowired private MyAuthorityServiceImpl myAuthorityServiceImpl;
	@Autowired private MyRoles myRoles;
	@Autowired private MyAuthority myAuthority;
	
	private List<MyRoles> myRolesList = new ArrayList<MyRoles>();
	private List<MyAuthority> myAuthorityList = new ArrayList<MyAuthority>();
	private List<String> authids = new ArrayList<String>();
	private int roleid;
	private String authIds;
	
	/**
	 * 添加角色
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success", location="/admin/role/list.do",type="redirect"),
	})
	public String save(){
		myRolesServiceImpl.save(myRoles);
		return SUCCESS;
	}
	
	/**
	 * 系统角色列表
	 * @return
	 */
	@Action(value="list",results={
			@Result(name="success", location="/admin/auth/roleList.jsp"),
	})
	public String list(){
		paginationSupport = myRolesServiceImpl.findPageByQuery(MyRolesServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new Object[]{});
		paginationSupport.setUrl("admin/role/list.do");
		myRolesList = paginationSupport.getItems();
		return SUCCESS;
	}
	
	@Action(value="update",results={
			@Result(name="success", location="/admin/role/list.do",type="redirect"),
	})
	public String update(){
		
		String []authid = authIds.split(",");
		Set<MyRoles> roleSet;
		Set<MyAuthority> authSet;
		for(int i=0; i<authid.length; i++){
			myAuthority = myAuthorityServiceImpl.findById(Integer.parseInt(authid[i].trim()));
			if(null != myRoles.getMyAuthoritys() && !myRoles.getMyAuthoritys().isEmpty()){
				myRoles.getMyAuthoritys().add(myAuthority);
			}else{
				authSet = new HashSet<MyAuthority>();
				authSet.add(myAuthority);
				myRoles.setMyAuthoritys(authSet);
			}
			
			if(null != myAuthority.getMyRoles() && !myAuthority.getMyRoles().isEmpty()){
				myAuthority.getMyRoles().add(myRoles);
			}else{
				roleSet = new HashSet<MyRoles>();
				roleSet.add(myRoles);
				myAuthority.setMyRoles(roleSet);
			}
			
			myRolesServiceImpl.update(myRoles);
			myAuthorityServiceImpl.update(myAuthority);
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 进入角色编辑页面
	 * @return
	 */
	@Action(value="gotoEdit",results={
			@Result(name="success", location="/admin/auth/roleEdit.jsp"),
	})
	public String gotoEdit(){
		myRoles = myRolesServiceImpl.findById(roleid);
		myAuthorityList = myAuthorityServiceImpl.loadAll();
		
		if(null != myRoles.getMyAuthoritys() && !myRoles.getMyAuthoritys().isEmpty()){
			for(MyAuthority ma : myRoles.getMyAuthoritys()){
				authids.add(ma.getId()+"");
			}
		}
		return SUCCESS;
	}

	/**
	 * 进入添加角色页面
	 * @return
	 */
	@Action(value="gotoAddRole",results={
			@Result(name="success", location="/admin/auth/addRole.jsp"),
	})
	public String gotoAddRole(){
		return SUCCESS;
	}
	
	public MyRoles getModel() {
		// TODO Auto-generated method stub
		return myRoles;
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

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public List<MyAuthority> getMyAuthorityList() {
		return myAuthorityList;
	}

	public void setMyAuthorityList(List<MyAuthority> myAuthorityList) {
		this.myAuthorityList = myAuthorityList;
	}

	public List<String> getAuthids() {
		return authids;
	}

	public void setAuthids(List<String> authids) {
		this.authids = authids;
	}

	public String getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}
	
}
