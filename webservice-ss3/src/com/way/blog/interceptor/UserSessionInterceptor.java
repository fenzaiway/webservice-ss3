package com.way.blog.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component
public class UserSessionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1713218771996200247L;
	
	private static final String[] allowTogoUrls = new String[]{"/userlogin/gotoLogin","/userlogin/login.do","/register/loginout.do",
		"/loginfo/goto","/loginfo/view","/loginfo/isUserLike","/loginfo/pageTest"};
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		///////用于检验用户Session，如果失效了，则返回登录页面重新登录
		System.out.println("------------session validata---------");
		 ActionContext ctx=invocation.getInvocationContext();
		 Map<String, Object> session = ctx.getSession();
         String account =(String)ctx.getSession().get("myusername");
         // 获取HttpServletRequest对象
         HttpServletRequest req = ServletActionContext.getRequest();
         String reqPath = req.getContextPath();
         String uri = req.getRequestURI();
//         List<String> allowTogoUrls = new ArrayList<String>();
//         //如果是登录或注销，则不进行判断
//         String loginAction = reqPath+"/register/gotoLogin";
//         String loginoutAction = reqPath+"/register/loginout.do";
//         allowTogoUrls.add(loginAction);
//         allowTogoUrls.add(loginoutAction);
//         //如果是游客或者其他会员访问，放行
//         String gotoAction = reqPath+"/loginfo/goto";
//         String viewAction = reqPath+"/loginfo/view";
//         allowTogoUrls.add(gotoAction);
//         allowTogoUrls.add(viewAction);
         
         
//         if(isAllowtogo(invocation,allowTogoUrls,uri)){
//        	 invocation.invoke();
//         }
         for(int i=0; i<allowTogoUrls.length; i++){
        	 String url = reqPath+allowTogoUrls[i];
        	 if(uri.startsWith(url)){
 				//break;
        		 return invocation.invoke();
 			}
         }
         System.out.println("---login validata----");
         if(account==null){
        	
             // 获取此请求的地址，请求地址包含application name，进行subString操作，去除application name
             String path = uri;
             // 获得请求中的参数
             String queryString = req.getQueryString();
             // 预防空指针
             if (queryString == null) {
                 queryString = "";
             }
             // 拼凑得到登陆之前的地址
             String realPath = path + "?" + queryString;
             System.out.println("realPath==" + realPath);
             // 存入session，方便调用
             session.put("prePage", realPath); 
             
             //如果超时，返回提示页面
             return "loginPage";
          
         }
         
         ////以登录，放行
         return invocation.invoke();
         
        
	}
	
	
	/**
	 * 判断是否是放行的路径
	 * @param invocation
	 * @param urlList
	 * @param uri
	 * @return
	 */
	public boolean isAllowtogo(ActionInvocation invocation,String[] urlList,String uri){
		boolean flag = false;
		for(String isAllow : urlList){
			if(uri.startsWith(isAllow)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
