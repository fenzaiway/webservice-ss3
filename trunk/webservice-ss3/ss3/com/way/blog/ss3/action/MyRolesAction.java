package com.way.blog.ss3.action;

import java.util.ArrayList;
import java.util.List;

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
import com.way.blog.util.PaginationSupport;

@Controller("myRolesAction")
@ParentPackage("interceptor")
@Namespace("/admin/role")
public class MyRolesAction extends BaseAction implements ModelDriven<MyRoles>{

	@Autowired private MyRolesServiceImpl myRolesServiceImpl;
	@Autowired private MyRoles myRoles;
	
	private List<MyRoles> myRolesList = new ArrayList<MyRoles>();
	private int roleid;
	
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
		myRolesServiceImpl.update(myRoles);
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
	
}
