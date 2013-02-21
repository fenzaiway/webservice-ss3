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
import com.way.blog.manager.admin.entity.Area;
import com.way.blog.manager.admin.entity.City;
import com.way.blog.manager.admin.entity.Interest;
import com.way.blog.manager.admin.entity.Job;
import com.way.blog.manager.admin.entity.Province;
import com.way.blog.manager.admin.service.impl.AreaServiceImpl;
import com.way.blog.manager.admin.service.impl.CityServiceImpl;
import com.way.blog.manager.admin.service.impl.InterestServiceImpl;
import com.way.blog.manager.admin.service.impl.JobServiceImpl;
import com.way.blog.manager.admin.service.impl.ProvinceServiceImpl;
import com.way.blog.user.entity.Address;
import com.way.blog.user.entity.MyUserDetial;
import com.way.blog.user.entity.UserHeadImg;
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
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;
	@Autowired
	private CityServiceImpl cityServiceImpl;
	@Autowired
	private AreaServiceImpl areaServiceImpl;
	@Autowired
	private UserHeadImgAction userHeadImgAction;
	private MyUserDetial myUserDetial;
	private UserHeadImg userHeadImg;
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
	
	private Province myProvince;
	private City myCity;
	private Area myArea;
	
	public void prepare() throws Exception {
		jobList = jobServiceImpl.loadAll();
		interestList = interestServiceImpl.loadAll();
		request = ServletActionContext.getRequest();
		session = request.getSession();
		////从Session中取出用户的个人详细信息
		username = (String) session.getAttribute("myusername");
		//System.out.println("username==="+ username);
		if(null != username){
			myUserDetial = myUserDetialServiceImpl.myFindByProperty("username", username);
			zoneUrl = "zone/"+username;
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
			@Result(name="success",location="/%{zoneUrl}",type="redirect"),
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
		//session.setAttribute("zoneuser", username);
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
		myUserDetial = myUserDetialServiceImpl.myFindByProperty("username",username);
		userHeadImg = userHeadImgAction.findUserHeadImgMyUsername(username);
		if(null!=myUserDetial){
			address = myUserDetial.getAddress();
			if(null != address ){
				myProvince = provinceServiceImpl.myFindByProperty("provinceID", address.getProvince());
				myCity = cityServiceImpl.myFindByProperty("cityID", address.getCity());
				myArea = areaServiceImpl.myFindByProperty("areaID", address.getArea());
			}
		}
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

	public Province getMyProvince() {
		return myProvince;
	}

	public void setMyProvince(Province myProvince) {
		this.myProvince = myProvince;
	}

	public City getMyCity() {
		return myCity;
	}

	public void setMyCity(City myCity) {
		this.myCity = myCity;
	}

	public Area getMyArea() {
		return myArea;
	}

	public void setMyArea(Area myArea) {
		this.myArea = myArea;
	}

	public String getZoneUrl() {
		return zoneUrl;
	}

	public void setZoneUrl(String zoneUrl) {
		this.zoneUrl = zoneUrl;
	}

	public UserHeadImg getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(UserHeadImg userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	
}
