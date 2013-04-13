<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/top_navi2.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${myusername }--关注的空间列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<STYLE type="text/css">
		h3{float: left;}
		h3 a{text-decoration: none;color: #333;}
		h3 a:hover{text-decoration: underline;color: red;}
	</STYLE>
  </head>
  
  <body>
	<div id="main">
	<div style="float: left;"><span><h3 style="color: #3FA7CB">我的关注列表</span></h3><h3>|</h3><h3><a href="${ctx }/attention/readFansList.do">我的粉丝列表</a></h3></span></div>
	<div class="cls"></div>	
	<ul>
		<s:iterator value="attentionList" id="attention">
			<li class="li_list">
			<div class="separate-line"></div>
			<a href="zone/<s:property value="#attention.toUserName"/>"><s:property value="#attention.toUserName"/></a></li>
		</s:iterator>
		<div class="separate-line" style="margin-left: 25px;"></div>
	</ul>
	</div>
  </body>
</html>
