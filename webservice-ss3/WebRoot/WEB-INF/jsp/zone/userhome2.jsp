<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
				return false;
			}).live("mouseout",function()
			{
				$(this).css("border-color","#F8F8F8");
				return false;
			});
			
			$("#left_logtype li").live("mouseover",function()
			{
				$(this).css("background-color","#F8F8F8");
				return false;
			}).live("mouseout",function()
			{
				$(this).css("background-color","#FFF");
				return false;
			});
		});
	</script>

  </head>
  
  <body>
<div id="main">
<div id="left">
	<div id="left_logtype">
    	<a href="${ctx }/userzone/infocenter.do"><img  src="${ctx }/images/top_logo.jpg"/></a>
    	<ul>
        	<li><a href="${ctx }/zone/${zoneuser}">首页</a></li>
			<s:iterator value="logTypeList" id="logType">
				<li><a href="${ctx}/loginfo/getLogInfoByLogTypeId.do?logTypeId=<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></a></li>
			</s:iterator>
            
        </ul>
    </div>
    
</div>
<div class="clr"></div>
<div id="right">
			
			<s:iterator value="logInfoList" id="loginfo">
				<div class="article">
				
					<div class="content">
						<span class="content_1">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="#loginfo.logText.length() > 100">
								<s:property value="#loginfo.logText.substring(0, 100)" escape="false"/>...
							</s:if>
							<s:else>
								<s:property value="#loginfo.logText" escape="false"/>...
							</s:else>
							<a href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#loginfo.id'/>">更多</a>
							
							
						<span>
					</div>
					<div class="article_bottom">
						<div class="title"><span><a href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#loginfo.id'/>"><s:property value="#loginfo.logTitle"/></a></span></div>
						<div class="time">
							<span class="time1"><s:property value="#loginfo.logPublishTime"/></span>
							<span class="time2"><img src="${ctx }/images/commons.gif"/><s:property value="#loginfo.logComments.size()"/></span>
						</div>
					</div>
				</div>
			</s:iterator>
	
</div>


</div>
</body>

</html>
