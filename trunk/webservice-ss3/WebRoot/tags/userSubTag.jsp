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
    
    <title>阅读订阅内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<div id="main">
	   	<s:if test="0==logInfoDataList.size"> 
	   		你订阅的标签暂时还没有内容更新。 
	   	</s:if>
		<s:else>
			<h3>您订阅的内容列表如下：</h3>
			<ul>
				<s:iterator value="logInfoDataList" id="logInfoData" status="st">
					<li class="li_list">
						<div><div class="separate-line"></div>
							<s:property value="#st.count"/>.&nbsp;
							<a href="loginfo/viewmore.do?zoneuser=<s:property value='#logInfoData.username'/>&logInfoid=<s:property value='#logInfoData.logid'/>"><s:property value="#logInfoData.logTitle"/></a>
							&nbsp;&nbsp;
							<a href="${ctx }/zone/<s:property value="#logInfoData.username"/>"><s:property value="#logInfoData.username"/></a>&nbsp;&nbsp;
							<s:property value="#logInfoData.publishTime"/>&nbsp;&nbsp;
							评论（<s:property value="#logInfoData.commentNum"/>）&nbsp;&nbsp;
							标签：<s:iterator value="#logInfoData.tags" id="tag">
									&nbsp;&nbsp;<a href="${ctx }/tag/<s:property value="#tag"/>">#<s:property value="#tag"/></a>
							</s:iterator>
							
						</div>
					</li>
				</s:iterator><div class="separate-line" style="margin-left: 25px;"></div>
				<li><a href="tag/" style="font-size: 16; font-family: 微软雅黑; text-decoration: none;">发现更多有趣内容</a></li>
			</ul>
		</s:else>
	</div>
  </body>
</html>
