<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'logstore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(function()
	{
		//var url = window.location+"";
		//var basePath = '<%=basePath%>';
		//url = url.substring(basePath.length,url.length);
		//alert(url);
	});
</script>
  </head>
  
  <body>
  	<a href='<s:property value="mySourceUrl"/>'><s:property value="myLogInfo.logTitle"/></a>
    <h3>日志收藏</h3>源日志用户<s:property value="myLogInfo.username"/>
    <form action="logstore/save.do" method="post">
    	<input type="hidden" value="<s:property value='mySourceUrl'/>" name="myLogStore.sourceUrl"/>
    	<input type="hidden" value="<s:property value='logInfoId'/>" name="logInfoId"/>
    	日志标题：<s:property value="myLogInfo.logTitle"/><br/> 
    	日志所属用户：<s:property value="myLogInfo.username"/><br/> 
    	描述：<input type="text" value="" size="50" name="myLogStore.storeDesc"><br/> 
    	<input type="submit" value="收藏"/><br/>
    </form>
    <s:debug></s:debug>
  </body>
</html>
