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
    
    <title>${myusername }--的消息列表</title>
    
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
  		<div style="float: left;"><span><h3 style="color: #3FA7CB">我的消息列表</h3></span></div>
		<div class="cls"></div>
	<ul>
		<s:iterator value="messageList" id="message" status="st">
			<li  class="li_list">
			<div class="separate-line"></div>
				<s:property value="#st.count"/>.&nbsp;<span style="color: #3FA7CB;"><s:property value="#message.msgContent" escape="false"/></span>
				<span style="float:right;margin-right:10px;">
					<s:if test="1 == #message.msgtype">评论消息</s:if>
					<s:elseif test="2 == #message.msgtype">转载消息</s:elseif>
					<s:elseif test="3 == #message.msgtype">收藏消息</s:elseif>
					<s:elseif test="4 == #message.msgtype">like消息</s:elseif>
					<s:elseif test="5 == #message.msgtype">公告消息</s:elseif>
					<s:elseif test="6 == #message.msgtype">关注信息 </s:elseif>
					<s:elseif test="7 == #message.msgtype">回复消息</s:elseif>
					
				</span>
			</li>
		</s:iterator>
		<div class="separate-line" style="margin-left: 25px;"></div>
	</ul>
    </div>
  </body>
</html>
