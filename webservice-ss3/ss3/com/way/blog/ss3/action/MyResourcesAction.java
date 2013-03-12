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
import com.way.blog.ss3.entity.MyResources;
import com.way.blog.ss3.service.impl.MyAuthorityServiceImpl;
import com.way.blog.ss3.service.impl.MyResourcesServiceImpl;
import com.way.blog.util.PaginationSupport;

@Controller("myResourcesAction")
@ParentPackage("interceptor")
@Namespace("/admin/res")
public class MyResourcesAction extends BaseAction implements ModelDriven<MyResources>{

	@Autowired private MyResourcesServiceImpl myResourcesServiceImpl;
	@Autowired private MyResources myResources;
	
	private List<MyResources> myResourcesList = new ArrayList<MyResources>();
	private int resid;
	/**
	 * 添加权限
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success", location="/admin/res/list.do",type="redirect"),
	})
	public String save(){
		myResourcesServiceImpl.save(myResources);
		return SUCCESS;
	}
	 
	@Action(value="list",results={
			@Result(name="success", location="/admin/auth/resourceList.jsp"),
	})
	public String list(){
		paginationSupport = myResourcesServiceImpl.findPageByQuery(MyResourcesServiceImpl.HQL, PaginationSupport.PAGESIZE, startIndex, new Object[]{});
		paginationSupport.setUrl("admin/res/list.do");
		myResourcesList = paginationSupport.getItems();
		return SUCCESS;
	}
	
	@Action(value="update",results={
			@Result(name="success", location="/admin/res/list.do",type="redirect"),
	})
	public String update(){
		myResourcesServiceImpl.update(myResources);
		return SUCCESS;
	}
	
	@Action(value="gotoAdd",results={
			@Result(name="success", location="/admin/auth/addResource.jsp"),
	})
	public String gotoAdd(){
		
		return SUCCESS;
	}
	
	@Action(value="gotoEdit",results={
			@Result(name="success", location="/admin/auth/resourceEdit.jsp"),
	})
	public String gotoEdit(){
		myResources = myResourcesServiceImpl.findById(resid);
		return SUCCESS;
	}

	public MyResources getModel() {
		// TODO Auto-generated method stub
		return myResources;
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

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}
	
	
}
