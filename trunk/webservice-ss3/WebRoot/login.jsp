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
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
		用户登录
		<div id="ss3">
			SpringSecurity登录
	   		<form action="${basePath}j_spring_security_check" method="post">
				<table>
					<tr>
		    			<td align="right">用户名：</td><td align="left"><input type="text" name="j_username" id="j_username" /></td>
		    		</tr>
		  			<tr>
		    			<td align="right">密　码： </td><td align="left"><input type="password" name="j_password" id="j_password" /></td>
		    		</tr>
		  			<tr>
		    			<td align="center"><input type="submit" value="登录"/></td>
		    		</tr>
				</table>
			</form>
		</div>
		<hr/>
		<s:fielderror fieldName="sessionTimeout"></s:fielderror>
		<s:fielderror fieldName="loginFail"></s:fielderror>
		<div id="normal">
			正常登录
	   		<form action="register/login.do" method="post">
				<table>
					<tr>
		    			<td align="right">用户名：</td><td align="left"><input type="text" name="account" id="account" /></td>
		    		</tr>
		  			<tr>
		    			<td align="right">密　码： </td><td align="left"><input type="password" name="mypassword" id="password" /></td>
		    		</tr>
		  			<tr>
		    			<td align="center"><input type="submit" value="登录"/></td>
		    		</tr>
				</table>
			</form>
		</div>
  </body>
</html>
