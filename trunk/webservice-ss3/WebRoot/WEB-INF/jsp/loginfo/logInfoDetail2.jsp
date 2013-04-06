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
    
    <title><s:property value="logInfo.logTitle"/>--轻轻一点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/logInfoDetail2.css">
	<script type="text/javascript">
		$(function()
		{
			
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
		<div id="right">
			<div id="content">
				<div id="title"><h1><s:property value="logInfo.logTitle"/></h1></div>
				<div id="author">作者：<a href="${ctx }/zone/<s:property value="logInfo.username"/>"><s:property value="logInfo.username"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;发布时间：<s:property value="logInfo.logPublishTime"/></div>
				<hr style="border: 1px dotted #D1D1D1; margin-top: 12px;letter-spacing: 1px;">
			</div>
			<div id="logtext">
				<s:property value="logInfo.logText" escape="false"/>
			</div>
			<div id="tag">
				<span>
					标签：<s:iterator value="logInfo.logTags" id="tag">
						<a href="${ctx }/tag/<s:property value="#tag.tagName"/>">#<s:property value="#tag.tagName"/></a>
						</s:iterator>
				</span>
			</div>
			<s:if test="0!=similarLogInfoList.size()">
				<div id="similarLogInfo">
				<h5>相似文章：</h5><br/>
				<ul><s:iterator value="similarLogInfoList" id="similarLogInfo">
				<li><span>.</span><a href="loginfo/viewmore.do?zoneuser=<s:property value='#similarLogInfo.zoneuser'/>&logInfoid=<s:property value='#similarLogInfo.logId'/>"><s:property value="#similarLogInfo.logTitle"/></a></li>
				</s:iterator></ul>
				</div>
			</s:if>
		</div>
	</div>
	
  </body>
</html>
