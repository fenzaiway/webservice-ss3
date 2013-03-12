<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色编辑</title>
    
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
    <form action="admin/role/update.do" method="post">
    	<input type="hidden" name="id" value="<s:property value='myRoles.id'/>">
    	角色名：<input type="text" name="roleName" value="<s:property value='myRoles.roleName'/>"/><br/>
    	角色描述：<textarea rows="" cols="" name="roleDesc"><s:property value='myRoles.roleDesc'/></textarea><br/>
    	<input type="submit" value="更新">
    </form>
  </body>
</html>
