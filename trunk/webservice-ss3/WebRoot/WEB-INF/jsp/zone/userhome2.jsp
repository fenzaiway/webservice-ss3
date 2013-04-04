<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userhome2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${ctx }/css/homeIndex1.css">
	<script type="text/javascript">
		$(function()
		{
			$(".article").live("mouseover",function()
			{	
				$(this).css("border-color","#50803F");
			}).live("mouseout",function()
			{
				$(this).css("border-color","#F8F8F8");
			});
		});
	</script>

  </head>
  
  <body>
<div id="main">
<div id="left">
	<div id="left_logtype">
    	<img  src="${ctx }/images/top_logo.jpg"/>
    	<ul>
        	<li>首页</li>
			<s:iterator value="logTypeList" id="logType">
				<li><a href=""><s:property value="#logType.logTypeName"/></a></li>
			</s:iterator>
            
        </ul>
    </div>
    
</div>

<div id="right">
			<s:iterator value="logInfoList" id="loginfo">
				<div class="article">
					<div class="content"><s:property value="#loginfo.logText"/></div>
					<div>
						<div class="title"><s:property value="#loginfo.logTitle"/></div>
						<div class="time"><s:property value="#loginfo.logPublishTime"/></div>
					</div>
				</div>
			</s:iterator>
	
</div>


</div>
<div id="bottom">
底部
</div>
</body>

</html>
