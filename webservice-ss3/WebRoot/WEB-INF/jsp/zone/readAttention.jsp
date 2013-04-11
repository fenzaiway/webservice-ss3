<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" href="${ctx }/css/commons.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="${ctx }/css/userSubTag.css">
<STYLE type="text/css">
		h3{float: left;}
		h3 a{text-decoration: none;color: #333;}
		h3 a:hover{text-decoration: underline;color: red;}
	</STYLE>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${ctx }/js/commons.js"></script>
	<script type="text/javascript">
		$(function()
		{
			$(".li_list").live("mouseover",function()
			{
				$(this).css("background-color","#F8F8F8");
				return false;
			}).live("mouseout",function()
			{
				$(this).css("background-color","#fff");
				return false;
			});
		});
	</script>
  </head>
  
  <body>
	<div id="top">
		<div id="navi">
			<span><h1><a href="${ctx }/userzone/infocenter.do">首页</a></h1></span>
			<span><h1><a href="${ctx }/zone/${myusername}">我的主页</a></h1></span>
			<span><h1><a href="${ctx }/albumtype/gotoAlbumTypeList.do?zoneuser=${zoneuser }">相册</a></h1></span>
			<span><h1><a href="${ctx }/tag/">发现</a></h1></span>
			<span><h1><a href="${ctx }/ajax/message/getUserMessageList.do">消息</a></h1></span>
		</div>
	</div>
	<div class="cls"></div>
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
