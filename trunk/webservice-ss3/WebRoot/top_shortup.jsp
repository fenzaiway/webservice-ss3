<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

#top{padding-bottom:5px;position:fixed;_position:absolute;top:0;left:0;right:0;z-index:1023;height:50px; background:#3FA7CB;}
#head_img{position:relative; margin-top:55px; width:100%; height:160px; border:1px solid #ccc; margin-bottom:20px; background-color:#000000; filter: alpha(opacity=50); opacity: 0.5;border-bottom:2px solid #3DA8CC; z-index: -1;}
.clr{clear:both;}
.outer{
     width:100%; padding-left:30px;
    
 }
 .inner{
     width:980px;
     height:50px;
	 min-height:50px;
	 height:auto!important;
	 padding-bottom:5px;
     
     margin:0 auto;
 }
 #logo{float:left;}
 #logo img{width:100px; height:55px;position:fixed;_position:absolute;top:0;}
 #top_text1{width:50%; height:55px; float:left;}
 #top_text1 a{color: #fff;text-decoration: none;}
 #top_text1 a:hover{text-decoration: underline;}
 #top_text1 ul li{ color:#FFFFFF; margin-left:30px; font-size:18px;}

 #top_text2{ float:right; width:50%; height:55px;} 
 #top_text2 ul li{ color:#D0EEFA; margin-left:20px; font-size:13px;} 
 .split{ margin-left:10px;}
</style>
</head>
  
  <body>
    <div id="top">
	<div class="outer">
 		<div class="inner">
        	<div id="logo"><img src="images/top_logo.gif" alt="a"/></div>
         	<div style="position:relative; width:800px; float:right; height:55px; margin-bottom:5px;">
              <div id="top_text1">
                <span>
                <ul>
                  <li><a href="index/loadIndexContent.do">首页</a></li>
				<li><a href="admin/main/gotoMainMenu.do">系统管理</a></li>
				<li><a href="albumtype/gotoAlbumTypeList.do?zoneuser=${zoneuser }">相册</a></li>
				<li><a href="zone/${myusername}">个人中心</a></li></ul></span>
              </div>
              	 <div id="top_text2">
                   <span>
                   <ul>
                     <li>消息<span class="split">|</span></li>
                            <li>私信<span class="split">|</span></li>
                            <li><a href="register/gotoLogin.do">登录</a><span class="split">|</span></li>
                            <li><a href="register/gotoRegister.do">注册</a><span class="split">|</span></li>
                            <li><a href="register/sendMail.do">邮件</a><span class="split">|</span></li>
                            <li>退出</li>
                        </ul>
                    </span>
              	 </div>
             	 <div style="clear:both;"></div>
             </div>
            
      </div>
     
	</div>
</div>
	<div id="head_img">
		<img src="images/top_banner.gif" style="width: 100%;height: 160px;">
	</div>
  </body>
</html>
