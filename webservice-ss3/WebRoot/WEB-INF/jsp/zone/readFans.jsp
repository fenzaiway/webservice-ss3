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
    
    <title>${myusername }--的粉丝列表</title>
    
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
  		<div style="float: left;"><span><h3 style="color: #3FA7CB">我的粉丝列表</h3></span><h3>|</h3><span><h3><a href="${ctx }/attention/readAttentionList.do">我的关注列表</a></h3></span></div>
		<div class="cls"></div>
	<ul>
		<s:iterator value="fansList" id="fans">
			<li  class="li_list">
			<div class="separate-line"></div>
			<a href="zone/<s:property value="#fans.fromUserName"/>"><s:property value="#fans.fromUserName"/></a>
				---
					<s:if test="1==#fans.isAttention">已关注</s:if><s:else>加关注</s:else>
				</li>
		</s:iterator>
		<div class="separate-line" style="margin-left: 25px;"></div>
	</ul>
    </div>
  </body>
</html>
