package com.way.blog.ajax.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.manager.admin.entity.Area;
import com.way.blog.manager.admin.entity.City;
import com.way.blog.manager.admin.entity.Province;
import com.way.blog.manager.admin.service.impl.AreaServiceImpl;
import com.way.blog.manager.admin.service.impl.CityServiceImpl;
import com.way.blog.manager.admin.service.impl.ProvinceServiceImpl;


/**
 * 
 * @author fenzaiway
 * 城市级联ajax Action类
 */
@ParentPackage("json-default")
@Namespace("/address")
@Controller("addressAction")
public class AddressAction extends ActionSupport implements Preparable {

	@Autowired
	private ProvinceServiceImpl provinceService;
	@Autowired
	private CityServiceImpl cityService;
	@Autowired
	private AreaServiceImpl areaService;
	private List<Province> listProvince = new ArrayList<Province>();  ////省份列表
	private List<City> listCity = new ArrayList<City>();  ////城市列表
	private List<Area> listArea = new ArrayList<Area>();  ////城区列表
	
	////编号
	private String provinceID;
	
	private String cityID;
	
	private String jsonString; ////json格式数据
	
	HttpServletResponse response = null;    
	PrintWriter pw = null;
	Gson gson = null;
	
	public void prepare() throws Exception {
		
	}

	/**
	 * 根据省份编号查找城市
	 * @return
	 */
	@Action(value="getCity",results={
			@Result(name="success",type="json")
	})
	public String getCity(){
		Province province = new Province();
		province.setProvinceID(provinceID);
		listCity = cityService.findByProperty("province",province);
		this.returnJsonString(listCity);
		return SUCCESS;
	}
	
	/**
	 * 根据城市编号查找城区
	 * @return
	 */
	@Action(value="getArea",results={
			@Result(name="success",type="json")
	})
	public String getArea(){
		City city = new City();
		city.setCityID(cityID);
		listArea = areaService.findByProperty("city", city);
		this.returnJsonString(listArea);
		return SUCCESS;
	}

	/**
	 * 取得province列表
	 * @return
	 */
	@Action(value="getProvince",results={
			@Result(name="success",type="json")
	})
	public String getProvince(){
		listProvince = provinceService.loadAll();
		this.returnJsonString(listProvince);
		return SUCCESS;
	}
	
	public void returnJsonString(List list){
		
		response = ServletActionContext.getResponse();     
		response.setContentType("application/json;charset=utf-8");          
		response.setHeader("Cache-Control","no-cache");                    
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gson = new Gson();
		jsonString = gson.toJson(list);
		System.out.println(jsonString);
		pw.print(jsonString);          
		pw.flush();          
		pw.close();  
	}

	public List<Province> getListProvince() {
		return listProvince;
	}

	public void setListProvince(List<Province> listProvince) {
		this.listProvince = listProvince;
	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}

	public List<Area> getListArea() {
		return listArea;
	}

	public void setListArea(List<Area> listArea) {
		this.listArea = listArea;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	
	@Action(value="load",results={
			@Result(name="success", location="index.jsp")
	})
	public String load(){
		return SUCCESS;
	}
	
}
