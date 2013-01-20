package com.way.blog.base.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.util.PaginationSupport;
import com.way.blog.util.json.HiJSONUtil;
import com.way.blog.zone.entity.LogInfo;

public class BaseAction extends ActionSupport implements Preparable{

	protected HttpServletRequest request = null;
	protected HttpSession session = null;
	@Autowired
	protected PaginationSupport paginationSupport;
	
	PrintWriter pw = null;
	Gson gson = null;
	JSONObject jsonObject = null;
	private String jsonString; ////json格式数据
	HttpServletResponse response = null;
	
	////添加字段，方便其他Action使用
	protected String myusername;  ////登录用户的session
	protected String zoneuser;	  //////空间用户的session
	protected String prePage;	  ////登录前的页面
	
	protected int startIndex = 0;
	
	public void prepare() throws Exception {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		myusername = (String) session.getAttribute("myusername");
		zoneuser = (String) session.getAttribute("zoneuser");
		prePage = (String) session.getAttribute("prePage");
	}
	
	/**
	 * 根据传进来的List转换成json字符串
	 * @param list
	 */
	public void returnJsonString(List list){
		gson = new Gson();
		jsonString = gson.toJson(list);
		this.returnJson(jsonString);
	}
	
	/**
	 * 根据传进来的对象转换为json字符串
	 * @param obj
	 */
	public void returnJsonByObject(Object obj){
		gson = new Gson();
		jsonString = gson.toJson(obj);
		this.returnJson(jsonString);
	}
	
	public String returnJsonByList(List list){
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();//只转化添加注解了的属性
		gson = builder.create();
		jsonString = gson.toJson(list);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("{\"items\":").append(jsonString).append(",");
		return stringBuffer.toString();
	}
	
	public void pageClass2Json(PaginationSupport paginationSupport){
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append(returnJsonByList(paginationSupport.getItems()));
		paginationSupport.setItems(null);
		gson = new Gson();
		jsonString = gson.toJson(paginationSupport);
		stringBuffer.append(jsonString.substring(1));
		this.returnJson(stringBuffer.toString());
	}
	
	/**
	 * 根据传递的字符串返回json数据到前台
	 * @param str
	 */
	public void returnJson(String str){
		response = ServletActionContext.getResponse();     
		response.setContentType("application/json;charset=utf-8");          
		response.setHeader("Cache-Control","no-cache");                    
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(str);
		pw.print(str);          
		pw.flush();          
		pw.close();  
	}

	
	public String getMyusername() {
		return myusername;
	}

	public void setMyusername(String myusername) {
		this.myusername = myusername;
	}

	public String getZoneuser() {
		return zoneuser;
	}

	public void setZoneuser(String zoneuser) {
		session.setAttribute("zoneuser",zoneuser);
		this.zoneuser = zoneuser;
	}

	public String getPrePage() {
		return prePage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
}
