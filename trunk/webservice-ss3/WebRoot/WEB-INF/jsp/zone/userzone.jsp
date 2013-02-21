<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_shortup.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userzone.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#menu ul li{float: left; margin-left: 20px;}
		a{text-decoration: none;}
		a:hover{text-decoration: underline;color: red;}
	</style>
  </head>
  
  <body>
    <s:property value="username"/>的空间（通过点击连接直接进入到相应模块进行管理（包括查看和管理））
	<div id="menu" style="float: none">
		<ul style="list-style: none;">
			<li><a href="">主页</a></li>
			<li><a href="loginfo/gotologinfo.do?zoneuser=${zoneuser }">日志</a></li>
			<li><a href="albumtype/gotoAlbumTypeList.do?zoneuser=${zoneuser }">相册</a></li>
			<li><a href="#">声音</a></li>
			<li><a href="#">影像</a></li>
			<li><a href="myUserDetial/gotoUserdetial.do">个人档</a></li>
			<li><a href="userManager/gotoUserManager.do">管理</a></li>
		</ul>
	</div>
	<br/><hr/>
	<div id="logInfo" style="float: none">
		日志列表
		<ul>
			<li></li>
		</ul>
	</div>
	<s:debug></s:debug>
  </body>
</html>
