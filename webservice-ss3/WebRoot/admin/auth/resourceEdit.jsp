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
    
    <title>资源编辑</title>
    
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
     <form action="admin/res/update.do" method="post">
     	<input type="hidden" name="myResources.id" value="<s:property value='myResources.id'/>">
    	资源名：<input type="text" name="myResources.resourceName" value="<s:property value='myResources.resourceName'/>"/><br/>
    	资源路径：<input type="text" name="myResources.url" value="<s:property value='myResources.url'/>"/><br/>
    	资源是否可用：<select name="myResources.enable">
    				<option value="1">可用</option>
    				<option value="0">冻结</option>
    			  </select><br>
    	资源类型：<select name="myResources.type">
    				<option value=".do">.do</option>
    				<option value=".action">.action</option>
    			  </select><br>
    	资源描述：<textarea rows="" cols="" name="descString"><s:property value='myResources.descString'/></textarea><br/>
    	<input type="submit" value="更新">
    </form>
  </body>
</html>
