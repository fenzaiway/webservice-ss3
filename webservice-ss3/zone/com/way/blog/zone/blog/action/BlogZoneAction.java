package com.way.blog.zone.blog.action;

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
import com.way.blog.base.action.BaseAction;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.entity.BlogZone;

@Controller
@ParentPackage("struts-default")
@Namespace("/zone")
public class BlogZoneAction extends ActionSupport implements ModelDriven<BlogZone>,
		Preparable {

	@Autowired
	private BlogZoneServiceImpl blogZoneService;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	HttpServletRequest request = null;
	HttpSession session = null;
	private String username;
	private String myusername;	///Session中的用户名
	private String type;	////要访问的空间分类（日志、相册。。。）
	private String page;	///分类分页
	
	private BlogZone blogZone;
	
	public void prepare() throws Exception {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		myusername = (String) session.getAttribute("myusername");
	}
	

	////收到请求，根据用户名跳转到对于用户的空间
	@Action(value="userzone",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/userzone.jsp"),
			@Result(name="input",location="/404.jsp"),
			@Result(name="login",location="/register/sessionError.do",type="redirect")
	})
	public String gotoUserZone(){
		////判断用户名是不是为空，为空的话，跳转到用户登录
		if("".equals(username)){
			return "login";
		}
		////1、先判断用户访问的这个路径的用户是否存在
		if(!userLoginServiceImpl.isUserNameExist(username)){
			return INPUT;
		}
		
		/////根据session获取查看用户
		////如果是其他用户查看，则空间的访问量+1
		if(null == myusername){ ////游客查看
			System.out.println("游客查看空间");
		}else if(!username.equals(myusername)){
			////其他登录用户
			System.out.println("会员用户:" + myusername);
		}else{
			System.out.println("用户自己查看空间");
		}
		
		
		blogZone = blogZoneService.myFindByProperty("username",username);
		//设置当前用户所在的空间，根据当前用户昵称来判断是哪个用户的空间
		session.setAttribute("zoneuser", username);
		///System.out.println(blogZone.getBlogZoneName());
		///System.out.println(blogZone.getZoneUrl());
		return SUCCESS;
	}

	public BlogZone getModel() {
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	
	
}
