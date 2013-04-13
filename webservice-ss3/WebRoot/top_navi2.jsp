<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'top_navi2.jsp' starting page</title>
    
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
	<script type="text/javascript" src="${ctx }/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="${ctx }/js/commons.js"></script>
	<script type="text/javascript">
		$(function()
		{
			backToTop();
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
  </body>
</html>
