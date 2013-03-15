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
    
    <title>文章详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/info_detail.css">
	

  </head>
  
  <body>
    
	<div id="user_top_navi">顶部导航</div>
    <div style="height:70px;"></div>
	
    <div id="main">
    	<div id="title">
        	<span class="title_1">
            	<h3><s:property value="logInfo.logTitle"/></h3>
            </span>
            <span class="time"><s:property value="logInfo.logPublishTime"/></span>
        </div>
        <div class="clr"></div>
        <div id="content">
         <s:property value="logInfo.logText" escape="false"/>
        </div>
       	<div>评论，转载</div> 
        <div class="like">可能喜欢</div> 
        <div class="page">上一篇,下一篇</div> 
        <div class="comment">评论框</div> 
        
    </div>
	<s:debug></s:debug>
  </body>
</html>
