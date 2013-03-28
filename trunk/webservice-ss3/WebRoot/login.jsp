<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录--轻轻一点，分享生活</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{margin: 0px; padding: 0px;}
		body {
			background-image: url("images/login_bg.jpg");
			color: #F0F0F0;	
			font-family:sans-serif;
			font-size: 16px;
		}
		a{text-decoration: none;color: #F0F0F0}
		ul{list-style: none;}
		#top{height: 100px;width: 100%;background-color: #184677;display: none;}
		#top img{height: 100px;}
		#mailBox{background:#fff;border:1px solid #ddd;padding:3px 5px 5px;position:absolute;z-index:9999;display:none;-webkit-box-shadow:0px 2px 7px rgba(0, 0, 0, 0.35);-moz-box-shadow:0px 2px 7px rgba(0, 0, 0, 0.35);margin-top: 0px; }
#mailBox p{width:100%;margin:0;padding:0;height:20px;line-height:20px;clear:both;font-size:12px;color:#ccc;cursor:default;}
#mailBox ul{padding:0;margin:0;}
#mailBox li{font-size:12px;height:22px;line-height:22px;color:#939393;font-family:'Tahoma';list-style:none;cursor:pointer;overflow:hidden;}
#mailBox .cmail{color:#000;background:#e8f4fc;}
		#normal{position: absolute;top: 55%; left: 35%;}
		#submit,.input{width:265px; height:35px; line-height:35px;}
		#submit{width:70px;background-color:#94B600; color:#FFFFFF;border:0px solid #EDEDEF;font-size:16px; font-family:微软雅黑;}
		.error_msg{margin: 10px 0px;}
		.input{font-size: 20px;border-radius:3px;
	-webkit-box-shadow:0 0 5px 0 #aaa;
	-moz-box-shadow:0 0 5px 0 #aaa;border: 1px solid #CCCDCE;}
	.input:focus{-moz-box-shadow:0 0 6px rgba(0, 100, 255, .45); -webkit-box-shadow:0 0 6px rgba(0, 100, 255, .45); box-shadow:0 0 6px rgba(0, 100, 255, .45); border-color:#34538b;}
	.register{margin: 5px 0px;}
	</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/focusBlur.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/autoMail.js"></script>
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript">
		$(function()
		{
			$(".input").FocusBlur();////设置当文本框获得焦点的时候，文字消失
			$('#account').autoMail({
				emails:["163.com",
										"qq.com",
										"126.com",
										"hotmail.com",
										"souhu.com",
										"yahoo.com",
										"139.com",
										"wo.com.cn",
										"wo.com",
										"sina.com.cn",
										"sina.com",
										"189.com",
										"live.cn",
										"live.com.cn"
						]
			});
		});
		
		
	</script>
</head>
  
  <body>
	
		<div id="top">
				<img alt="" src="<%=basePath %>images/index_logo.jpg">
			</div>
		<div id="normal">
			
			<div class="error_msg">
				<s:fielderror fieldName="sessionTimeout"></s:fielderror>
				<s:fielderror fieldName="loginFail"></s:fielderror>
			</div>
			<div>
	   		<form action="userlogin/login.do" method="post">
		    			<input type="text" name="account" value="邮箱登录" class="input" id="account" />
		  			
		    			<input type="password" class="input" value="" name="mypassword" id="password" />
		    	
		    			<input type="submit" id="submit" value="登录"/>
			</form>
			</div>
			
			<div class='register'>还没账号？<a href="register/gotoRegister.do">注册</a></div>
		</div>
  </body>
</html>
