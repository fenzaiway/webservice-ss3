<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加公告</title>
    
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
		h2{margin-top: -50px;margin-bottom: 20px;}
		.admin_add{padding-top:50px;min-height:230px;height:auto!important;margin: 0px auto;margin-top: 10%; background-color: #eee;width: 800px;}
		#but{margin-top:8px;margin-left:90px;width: 55px;height:30x;line-height: 30px;border: 1px solid #454545;}
	</style>
  </head>
  
  <body>
   	<div class="admin_add">
   		<center><h2>添加公告</h2></center>
   		<form action="admin/notice/save.do" method="post">
   			公告内容：
   			<textarea rows="5" cols="60" name="content"></textarea><br/>
   			<input type="submit" id="but" value="添加"/>
   		</form>
   	</div>
  </body>
</html>
