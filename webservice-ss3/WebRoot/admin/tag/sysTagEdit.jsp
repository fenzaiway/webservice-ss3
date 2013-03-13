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
    
    <title>系统标签修改</title>
    
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
    	
	<form action="admin/tag/sysTagUpdate.do" method="post">
		<input type="hidden" name="tag.id" value="<s:property value='tag.id'/>" />
		<input type="text" name="tag.tagName" value="<s:property value='tag.tagName'/>" readonly="readonly" /></br/>
		<s:checkboxlist name="logTagIds" list="#request.logTagList"   listKey="id" listValue="tagName"/>
		<input type="submit" value="更新">
	</form>



  </body>
</html>
