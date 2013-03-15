<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户编辑</title>
    
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
		    
	<form action="admin/user/update.do" method="post">
		<input type="hidden" value="<s:property value='userLogin.id'/>" name="id">
		<input type="text" name="userLogin.username" value="<s:property value='userLogin.username'/>" readonly="readonly"><br/>
		管理员：<select name="userLogin.isAdmin">
			<option value="1">是</option>
			<option value="0" selected="selected">否</option>
		</select>
		<s:checkboxlist name="roleid" list="#request.myRolesList" id="role"  listKey="id" listValue="roleDesc" value="#request.selectRoleids"/>
				
		<br>
		<input type="submit" value="更新"/>
	</form>

  </body>
</html>
