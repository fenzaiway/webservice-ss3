<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加角色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="admin/auth/save.do" method="post">
    	权限名：<input type="text" name="authorityName" value=""/><br/>
    	权限是否可用：<select name="enable">
    				<option value="1">可用</option>
    				<option value="0">冻结</option>
    			  </select>
    	权限描述：<textarea rows="" cols="" name="authorityDesc"></textarea><br/>
    	<input type="submit" value="添加">
    </form>
  </body>
</html>
