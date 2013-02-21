<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top_shortup.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link rel="stylesheet" href="<%=basePath%>css/top_shortup.css" type="text/css"></link></head>
  
  <body>
    <div class="shortcut">
	 <ul>
		<li><a href="index/loadIndexContent.do">首页</a></li>
		<li><a href="register/gotoRegister.do">进入注册</a></li>
		<li><a href="register/gotoLogin.do">登录</a></li>
		<li><a href="pageTest.jsp">测试异步分页</a></li>
		<li><a href="admin/main/gotoMainMenu.do">系统管理</a></li>
		<li><a href="zone/${myusername}">个人中心</a></li>
	</ul>
	<div class="clr"></div>
	</div>
	<div style="margin: 0px;padding: 0px;margin-bottom: 30px;"></div>
  </body>
</html>
