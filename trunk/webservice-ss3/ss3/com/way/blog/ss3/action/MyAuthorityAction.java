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
import com.way.blog.ss3.entity.MyAuthority;
import com.way.blog.ss3.service.impl.MyAuthorityServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("myAuthorityAction")
@ParentPackage("interceptor")
@Namespace("/admin/auth")
public class MyAuthorityAction extends BaseAction implements ModelDriven<MyAuthority>{

	@Autowired private MyAuthorityServiceImpl myAuthorityServiceImpl;
	@Autowired private MyAuthority myAuthority;

	private List<MyAuthority> myAuthorityList = new ArrayList<MyAuthority>();
	private int authid;
	
	/**
	 * 添加权限
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success", location="/admin/auth/list.do",type="redirect"),
	})
	public String save(){
		myAuthorityServiceImpl.save(myAuthority);
		return SUCCESS;
	}
	
	@Action(value="list",results={
			@Result(name="success", location="/admin/auth/authList.jsp"),
	})
	public String list(){
		paginationSupport = myAuthorityServiceImpl.findPageByQuery(MyAuthorityServiceImpl.HQL, PaginationSupport.PAGESIZE, authid, new Object[]{});
		paginationSupport.setUrl("admin/auth/list.do");
		myAuthorityList = paginationSupport.getItems();
		return SUCCESS;
	}
	
	@Action(value="update",results={
			@Result(name="success", location="/admin/auth/authList.do",type="redirect"),
	})
	public String update(){
		myAuthorityServiceImpl.update(myAuthority);
		return SUCCESS;
	}
	
	@Action(value="gotoAdd",results={
			@Result(name="success", location="/admin/auth/addAuth.jsp"),
	})
	public String gotoAdd(){
		
		return SUCCESS;
	}
	
	@Action(value="gotoEdit",results={
			@Result(name="success", location="/admin/auth/authEdit.jsp"),
	})
	public String gotoEdit(){
		myAuthority = myAuthorityServiceImpl.findById(authid);
		return SUCCESS;
	}
	
	
	
	public MyAuthority getModel() {
		// TODO Auto-generated method stub
		return myAuthority;
	}

	public MyAuthority getMyAuthority() {
		return myAuthority;
	}

	public void setMyAuthority(MyAuthority myAuthority) {
		this.myAuthority = myAuthority;
	}

	public List<MyAuthority> getMyAuthorityList() {
		return myAuthorityList;
	}

	public void setMyAuthorityList(List<MyAuthority> myAuthorityList) {
		this.myAuthorityList = myAuthorityList;
	}

	public int getAuthid() {
		return authid;
	}

	public void setAuthid(int authid) {
		this.authid = authid;
	}
	
	
}
