package com.way.blog.zone.blog.action;

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
import com.way.blog.base.action.BaseAction;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.AttentionServiceImpl;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

@Controller
@ParentPackage("struts-default")
@Namespace("/zone")
public class BlogZoneAction extends ActionSupport implements ModelDriven<BlogZone>,
		Preparable {

	@Autowired private BlogZoneServiceImpl blogZoneService;
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private LogTagServiceImpl logTagServiceImpl;
	@Autowired protected PaginationSupport paginationSupport;
	@Autowired private AttentionServiceImpl attentionServiceImpl;
	HttpServletRequest request = null;
	HttpSession session = null;
	private String username;
	private String myusername;	///Session中的用户名
	private String type;	////要访问的空间分类（日志、相册。。。）
	private String page;	///分类分页
	protected int startIndex = 0;
	private int isAttention = 0; ///用户是否关注了访问的空间 1表示已经关注，0表示还没有关注，-1表示进入的是自己的空间，如果用户还没有登录，则用-2表示
	
	private List<LogInfo> logInfoList;
	private List<LogTag> logTagList = new ArrayList<LogTag>();
	
	private BlogZone blogZone;
	
	public void prepare() throws Exception {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		myusername = (String) session.getAttribute("myusername");
	}
	

	/**
	 * 收到请求，根据用户名跳转到对于用户的空间主页
	 * @return
	 */
	@Action(value="userzone",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/userhome.jsp"), ///通过这个路径转到用户的主页
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
		
		this.checkIsUserAttention();
		
		/**
		 * 步骤：
		 * 1、加载用户的个人资料（暂时不做）
		 * 2、加载用户的空间资料（暂时不做）
		 * 3、加载用户的所有标签
		 * 4、加载日志信息，包括转载、评论数，
		 * 5、加载用户的最新照片（由于照片功能还没有完善，暂时不做）
		 */
		
		//3、加载用户的所有标签
		logTagList = logTagServiceImpl.getUserLogInfoTagList(username);
		
		//4、加载用户所有的日志
		paginationSupport = logInfoServiceImpl.findPageByQuery("from LogInfo where username=? and deleteStatue=0", PaginationSupport.PAGESIZE, startIndex, username);
		paginationSupport.setUrl("zone/"+username);
		logInfoList = paginationSupport.getItems();
		//logInfoList = logInfoServiceImpl.findByProperty("username", zoneuser);
		if(null != logInfoList){
			logInfoList = logInfoServiceImpl.changeLogInfoText(logInfoList);
		}
		///System.out.println(blogZone.getBlogZoneName());
		///System.out.println(blogZone.getZoneUrl());
//		logInfoList = logInfoServiceImpl.findByProperty("username", username);
//		if(null != logInfoList){
//			logInfoList = logInfoServiceImpl.changeLogInfoText(logInfoList);
//		}
		return SUCCESS;
	}

	/**
	 * 判断用户是否关注访问的空间了
	 *  1表示已经关注，0表示还没有关注，-1表示进入的是自己的空间，如果用户还没有登录，则用-2表示
	 */
	public void checkIsUserAttention(){
		/**
		 * 判断用户访问的这个空间该用户是否已经关注
		 * 1、先判断这个用户登录没有
		 * 2、先判断用户进入的是不是自己的空间，如果是，用-1表示
		 * 3、通过了前面的两步，则第三步就要判读该用户是否关注访问的这个用户的空间
		 */
		//1、判断用户是否已经登录
		if(null == myusername){ 
			isAttention = -2;
		}else if(myusername.equals(username)){
			//2、判断用户进入的是不是自己的空间
			isAttention = -1;
		}else{
			//3、验证用户是否已经登录
			if(attentionServiceImpl.isUserAttention(myusername, username)){
				///表示用户已经关注
				isAttention = 1;
			}else{
				//还没有关注
				isAttention = 0;
			}
		}
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


	public List<LogInfo> getLogInfoList() {
		return logInfoList;
	}


	public void setLogInfoList(List<LogInfo> logInfoList) {
		this.logInfoList = logInfoList;
	}


	public String getMyusername() {
		return myusername;
	}


	public void setMyusername(String myusername) {
		this.myusername = myusername;
	}


	public PaginationSupport getPaginationSupport() {
		return paginationSupport;
	}


	public void setPaginationSupport(PaginationSupport paginationSupport) {
		this.paginationSupport = paginationSupport;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public List<LogTag> getLogTagList() {
		return logTagList;
	}


	public void setLogTagList(List<LogTag> logTagList) {
		this.logTagList = logTagList;
	}

	public int getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(int isAttention) {
		this.isAttention = isAttention;
	}

	
	
}
