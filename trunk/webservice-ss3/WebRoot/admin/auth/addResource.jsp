<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加资源</title>
    
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
    <form action="admin/res/save.do" method="post">
    	资源名：<input type="text" name="resourceName" value=""/><br/>
    	资源路径：<input type="text" name="url" value=""/><br/>
    	资源是否可用：<select name="enabled">
    				<option value="1">可用</option>
    				<option value="0">冻结</option>
    			  </select><br>
    	资源类型：<select name="type">
    				<option value=".do">.do</option>
    				<option value=".action">.action</option>
    			  </select><br>
    	资源描述：<textarea rows="" cols="" name="descString"></textarea><br/>
    	<input type="submit" value="添加">
    </form>
  </body>
</html>
