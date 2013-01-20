package com.way.blog.user.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.manager.admin.entity.Interest;
import com.way.blog.manager.admin.entity.Job;
import com.way.blog.manager.admin.service.impl.InterestServiceImpl;
import com.way.blog.manager.admin.service.impl.JobServiceImpl;
import com.way.blog.user.entity.Address;
import com.way.blog.user.entity.MyUserDetial;
import com.way.blog.user.service.impl.MyUserDetialServiceImpl;

@Controller
@ParentPackage("struts-default")
@Namespace("/myUserDetial")
public class MyUserDetialAction extends ActionSupport implements
		ModelDriven<MyUserDetial>, Preparable {
	@Autowired
	private MyUserDetialServiceImpl myUserDetialServiceImpl;
	@Autowired
	private JobServiceImpl jobServiceImpl;
	@Autowired
	private InterestServiceImpl interestServiceImpl;
	
	private MyUserDetial myUserDetial;
	List<Job> jobList = new ArrayList<Job>();
	List<Interest> interestList = new ArrayList<Interest>();
	
	HttpServletRequest request = null;
	HttpSession session = null;
	private  String zoneUrl;
	String username;
	
	private String province;
	private String city;
	private String area;
	private Address address;
	
	
	public void prepare() throws Exception {
		jobList = jobServiceImpl.loadAll();
		interestList = interestServiceImpl.loadAll();
		request = ServletActionContext.getRequest();
		session = request.getSession();
		////从Session中取出用户的个人详细信息
		username = (String) session.getAttribute("username");
		System.out.println("username==="+ username);
		if(null != username){
			myUserDetial = myUserDetialServiceImpl.myFindByProperty("username", username);
			zoneUrl = "http://127.0.0.1:8080/blog/zone/"+username;
		}
	}

	public MyUserDetial getModel() {
		return myUserDetial;
	}
	
	/**
	 * 更新用户详细信息
	 * @return
	 */
	@Action(value="update",results={
			@Result(name="success",location="/index.jsp"),
			@Result(name="input",location="/WEB-INF/jsp/userlogin/myuserDetial.jsp"),
	})
	public String update(){
		address = new Address();
		address.setProvince(province);
		address.setCity(city);
		address.setArea(area);
		address.setMyUserDetial(myUserDetial);
		myUserDetial.setAddress(address);
		myUserDetial.setUsername(username);
		myUserDetialServiceImpl.update(myUserDetial);
		return SUCCESS;
	}
	
	/**
	 * 进入用户详细信息
	 * @return
	 */
	@Action(value="gotoUserdetial",results={
			@Result(name="success",location="/WEB-INF/jsp/userlogin/myuserDetial.jsp")
	})
	public String gotoMyUserDetial(){
		return SUCCESS;
	}

	public MyUserDetial getMyUserDetial() {
		return myUserDetial;
	}

	public void setMyUserDetial(MyUserDetial myUserDetial) {
		this.myUserDetial = myUserDetial;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Interest> getInterestList() {
		return interestList;
	}

	public void setInterestList(List<Interest> interestList) {
		this.interestList = interestList;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
