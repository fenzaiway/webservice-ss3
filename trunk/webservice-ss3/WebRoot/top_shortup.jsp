<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>轻轻一点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
  <link rel="stylesheet" href="<%=basePath%>css/top_shortup.css" type="text/css"></link>-->
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

/*#top{padding-bottom:5px;position:fixed;_position:absolute;top:0;left:0;right:0;z-index:1023;height:90px; background:#50803F;}*/
#top{padding-bottom:5px;height:90px; background:#50803F;}
#head_img{position:relative; margin-top:0px;  height:160px; border:1px solid #ccc; margin-bottom:20px; background-color:#000000; filter: alpha(opacity=50); opacity: 0.5;border-bottom:2px solid #3DA8CC; z-index: -1;margin-right: 0px; margin-left: 0px;}
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
 /*#logo img{width:330px; height:90px;position:fixed;_position:absolute;top:0;}*/
 #logo img{width:330px; height:90px;}
 #top_text1{width:50%; height:70px; float:left;line-height: 70px;}
 #top_text1 a{color: #fff;text-decoration: none;}
 #top_text1 a:hover{text-decoration: underline;}
 #top_text1 ul li{ color:#FFFFFF; margin-left:20px; font-size:20px;}

 #top_text2{ float:right; width:50%; height:70px;line-height:70px;} 
 #top_text2 a{color: #D0EEFA;text-decoration: none;}
 #top_text2 ul li{ color:#D0EEFA; margin-left:10px; font-size:14px;} 
 .split{ margin-left:10px;}
 
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/message.js"></script>
<script src="http://mat1.gtimg.com/app/openjs/openjs.js"></script>
<script src="http://mat1.gtimg.com/app/openjs/widget/connect.js"></script>

<script type="text/javascript">
	
	var username11 = '<s:property value="myusername"/>';
	var msgHtml = "";
	$(function()
	{
		getMessage();//读取消息
		//alert($(".message").text());
		showNewMessage();
	});
</script>
</head>
  
  <body>
	
    <div id="top">
	
        	<a href="${ctx }/userzone/infocenter.do"><div id="logo"><img style="border: 0 none;" src="images/top_logo.jpg" alt="a"/></div></a>
         	<div style="position:relative; width:800px; float:right; height:55px; margin-bottom:5px;">
              <div id="top_text1">
                <span>
                <ul>
				<s:if test='"" == myusername || null == myusername'>
				</s:if>
				<s:else>
					<li><a href="${ctx }/userzone/infocenter.do">首页</a></li>
					<li><a href="${ctx }/zone/${myusername}">我的主页</a></li>
					<li><a href="${ctx }/admin/main/gotoMainMenu.do">系统管理</a></li>
					<li><a href="${ctx }/albumtype/gotoAlbumTypeList.do?zoneuser=${zoneuser }">相册</a></li>
				</s:else>
				
				
				</ul></span>
              </div>
              	 <div id="top_text2">
                   <span>
                   <ul>
						<s:if test='"" == myusername || null == myusername'>
						 <li><a href="${ctx }/userlogin/gotoLogin.do">登录</a><span class="split">|</span></li>
	                           <li><a href="${ctx }/register/gotoRegister.do">注册</a><span class="split">|</span></li>
	                          
						</s:if>
						<s:else>
						 <li>消息<span class="messageNum"></span><span class="split">|</span></li>
                     	<li><a href="${ctx }/tag/">发现</a><span class="split">|</span></li>
                     	<li><a href="${ctx }/myUserDetial/gotoUserdetial.do">设置</a><span class="split">|</span></li>
						<li><s:property value='myusername'/><span class="split">|</span></li>
						 <li><a href="${ctx }/userlogin/sessionInvalidate.do">退出</a></li>
							 
						</s:else>
                           
                        </ul>
                    </span>
              	 </div>
             	 <div style="clear:both;"></div>
             </div>
            
      </div>
     

	<div id="head_img">
		<img src="images/top_banner.gif" style="width: 100%;height: 160px;"/>
	</div>
	
  </body>
</html>
