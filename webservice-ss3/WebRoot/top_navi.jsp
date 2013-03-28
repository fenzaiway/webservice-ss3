<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>轻轻一点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
body{
 margin:0px;
 padding:0px;
 /*background-color:#F0EEEF;*/
 background-color:#E3E3E5;
 width: 100%;
}
ul{list-style:none;}
li{float:left;}

#top{padding-bottom:5px;position:fixed;_position:absolute;top:0;left:0;right:0;z-index:1023;height:90px; background:#50803F;}
#head_img{position:relative; margin-top:55px;  height:160px; border:1px solid #ccc; margin-bottom:20px; background-color:#000000; filter: alpha(opacity=50); opacity: 0.5;border-bottom:2px solid #3DA8CC; z-index: -1;margin-right: 0px; margin-left: 0px;}
.clr{clear:both;}
.outer{
     width:100%; 
    
 }
 .inner{
     width:980px;
     height:50px;
	 min-height:50px;
	 height:auto!important;
	 padding-bottom:5px;
     margin:0 auto;
     padding-left: 30px;
 }
 #logo{float:left;margin-left: 15%;}
 #logo img{width:330px; height:90px;position:fixed;_position:absolute;top:0;}
 #top_text1{width:50%; height:70px; float:left;line-height: 70px;}
 #top_text1 a{color: #fff;text-decoration: none;}
 #top_text1 a:hover{text-decoration: underline;}
 #top_text1 ul li{ color:#FFFFFF; margin-left:20px; font-size:20px;}

 #top_text2{ float:right; width:50%; height:70px;line-height:70px;} 
 #top_text2 a{color: #D0EEFA;text-decoration: none;}
 #top_text2 ul li{ color:#D0EEFA; margin-left:10px; font-size:14px;} 
 .split{ margin-left:10px;}
 
</style>

</head>
  
  <body>
	
    <div id="top">
	
        	<a href="${ctx }/userzone/infocenter.do"><div id="logo"><img style="border: 0 none;" src="${ctx }/images/top_logo.jpg" alt="a"/></div></a>
         	<div style="position:relative; width:800px; float:right; height:55px; margin-bottom:5px;">
              <div id="top_text1">
                <span>
                <ul>
               <c:choose>
							<c:when test="${empty sessionScope.myusername}">
							</c:when>
                <c:otherwise>
                	<li><a href="${ctx }/userzone/infocenter.do">首页</a></li>
					<li><a href="${ctx }/zone/${myusername}">我的主页</a></li>
					<li><a href="${ctx }/admin/main/gotoMainMenu.do">系统管理</a></li>
					<li><a href="${ctx }/albumtype/gotoAlbumTypeList.do?zoneuser=${zoneuser }">相册</a></li>
                </c:otherwise>
				</c:choose>
				
				
				</ul></span>
              </div>
              	 <div id="top_text2">
                   <span>
                   <ul>
						<c:choose>
							<c:when test="${empty sessionScope.myusername}">
                			<li><a href="${ctx }/userlogin/gotoLogin.do">登录</a><span class="split">|</span></li>
	                        <li><a href="${ctx }/register/gotoRegister.do">注册</a><span class="split">|</span></li>
                			</c:when>
						<c:otherwise>
							<li><a href="${ctx }/tag/">发现</a><span class="split">|</span></li>
							<li><a href="${ctx }/myUserDetial/gotoUserdetial.do">设置</a><span class="split">|</span></li>
							<li>${myusername}<span class="split">|</span></li>
							 <li><a href="${ctx }/userlogin/sessionInvalidate.do">退出</a></li>
						</c:otherwise>
                     	</c:choose>
							 
                        </ul>
                    </span>
              	 </div>
             	 <div style="clear:both;"></div>
             </div>
            
      </div>
     
	
	<div style="height: 120px;"></div>
  </body>
</html>
