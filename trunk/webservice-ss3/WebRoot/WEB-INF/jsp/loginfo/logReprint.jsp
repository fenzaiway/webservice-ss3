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
    
    <title>My JSP 'logReprint.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table>
	<form action="loginfo/saveLogReprint.do" method="post">
		<input type="hidden" name="logInfo.logIsOriginal" value="2">
		<input type="hidden" name="myLogInfoId" value="<s:property value="logInfo.id"/>">
		<tr>
			<td>标题</td><td><input type="text" name="logInfo.logTitle" value="[转]<s:property value="logInfo.logTitle"/>"/></td>
		</tr>
		<tr>
			<td>内容</td><td><pre><textarea name="content" cols="50" rows="20"><s:property value="logInfo.logText"/></textarea></pre></td>
		</tr>
		
		<tr>
			<td>分类</td>
			<td>
				<select name="logTypeId">
					<s:iterator value="logTypeList" id="logType">
								<option value="<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></option>
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			 <td  style="width:70px;">权限：</td>
             <td colspan="2">
				<select name="logInfo.logAllowVisit">
					<option value="1">公开</option>
					<option value="2">仅好友</option>
					<option value="3">仅自己可见</option>
				</select>
            </td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="发表"/></td>
		</tr>
	
	</form>
</table>



  	<s:debug></s:debug>
  </body>
</html>
