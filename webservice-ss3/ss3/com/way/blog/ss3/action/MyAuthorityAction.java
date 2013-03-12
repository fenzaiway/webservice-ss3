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
import com.way.blog.ss3.entity.MyResources;
import com.way.blog.ss3.entity.MyRoles;
import com.way.blog.ss3.service.impl.MyAuthorityServiceImpl;
import com.way.blog.ss3.service.impl.MyResourcesServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("myAuthorityAction")
@ParentPackage("interceptor")
@Namespace("/admin/auth")
public class MyAuthorityAction extends BaseAction implements ModelDriven<MyAuthority>{

	@Autowired private MyAuthorityServiceImpl myAuthorityServiceImpl;
	@Autowired private MyResourcesServiceImpl myResourcesServiceImpl;
	@Autowired private MyAuthority myAuthority;
	@Autowired private MyResources myResources;

	private List<MyAuthority> myAuthorityList = new ArrayList<MyAuthority>();
	private List<MyResources> myResourcesList = new ArrayList<MyResources>();
	private List<String> resids = new ArrayList<String>();
	private String authid;
	private String resid;
	
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
		paginationSupport = myAuthorityServiceImpl.findPageByQuery(MyAuthorityServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new Object[]{});
		paginationSupport.setUrl("admin/auth/list.do");
		myAuthorityList = paginationSupport.getItems();
		return SUCCESS;
	}
	
	@Action(value="update",results={
			@Result(name="success", location="/admin/auth/list.do",type="redirect"),
	})
	public String update(){
		
		String []residArray = resid.split(",");
		MyAuthority auth = myAuthorityServiceImpl.findById(myAuthority.getId());
		Set<MyResources> resSet;
		Set<MyAuthority> authSet;
		for(int i=0; i<residArray.length; i++){
			myResources = myResourcesServiceImpl.findById(Integer.parseInt(residArray[i].trim()));
			
			if(null != myResources.getMyAuthoritys() && !myResources.getMyAuthoritys().isEmpty()){
				myResources.getMyAuthoritys().add(auth);
			}else{
				authSet = new HashSet<MyAuthority>();
				authSet.add(auth);
				myResources.setMyAuthoritys(authSet);
			}
			
			if(null != auth.getMyResources()&& !auth.getMyResources().isEmpty()){
				auth.getMyResources().add(myResources);
			}else{
				resSet = new HashSet<MyResources>();
				resSet.add(myResources);
				auth.setMyResources(resSet);
			}
			
			myAuthorityServiceImpl.update(auth);
			myResourcesServiceImpl.update(myResources);
		}
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
		myAuthority = myAuthorityServiceImpl.findById(Integer.parseInt(authid.trim()));
		myResourcesList = myResourcesServiceImpl.loadAll();
		if(null != myAuthority.getMyResources() && !myAuthority.getMyResources().isEmpty()){
			for(MyResources res : myAuthority.getMyResources()){
				resids.add(res.getId()+"");
			}
		}
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

	public MyResources getMyResources() {
		return myResources;
	}

	public void setMyResources(MyResources myResources) {
		this.myResources = myResources;
	}

	public List<MyResources> getMyResourcesList() {
		return myResourcesList;
	}

	public void setMyResourcesList(List<MyResources> myResourcesList) {
		this.myResourcesList = myResourcesList;
	}

	public List<String> getResids() {
		return resids;
	}

	public void setResids(List<String> resids) {
		this.resids = resids;
	}

	public String getAuthid() {
		return authid;
	}

	public void setAuthid(String authid) {
		this.authid = authid;
	}

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}

	

	
	
}
