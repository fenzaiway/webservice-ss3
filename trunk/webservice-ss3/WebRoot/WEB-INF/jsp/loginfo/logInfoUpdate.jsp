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
    
    <title>更新日志</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
  <script type="text/javascript" src="<%=basePath%>editor/editor_config.js"></script>
  <script type="text/javascript" src="<%=basePath%>editor/editor_all.js"></script>
<script type="text/javascript">
	//渲染编辑器
    function render(){
    	var editor_a = new baidu.editor.ui.Editor();
        editor_a.render('myEditor');
    }
	$(function()
	{
		render();
	});
	
	
</script>
  </head>
  
  <body>
    <form action="loginfo/update.do" method="post">
    <input type="hidden" name="id" value="<s:property value="logInfo.id"/>">
	<table>	
		<tr>
			<td style="width:70px;">发表类型：</td>
             <td colspan="2">
				<select name="logInfo.logIsOriginal">
					<option value="1" <s:if test="logInfo.logIsOriginal==1">selected="selected"</s:if>>原创</option>
					<option value="2" <s:if test="logInfo.logIsOriginal==2">selected="selected"</s:if>>转载</option>
					<option value="3" <s:if test="logInfo.logIsOriginal==3">selected="selected"</s:if>>其他</option>
				</select>
             </td>
		</tr>
		<tr>
			<td>标题</td><td><input type="text" name="logInfo.logTitle" value="<s:property value='logInfo.logTitle'/>"/></td>
		</tr>
		<tr>
			<td>内容</td><td>
				<div><textarea id="myEditor" name="content"><s:property value="logInfo.logText"/></textarea></div>&nbsp;
			</td>
			
		</tr>
		
		<tr>
			<td>分类</td>
			<td>
				<select name="logTypeId">
					
					<s:iterator value="logTypeList" id="logType">
							<s:if test="#logType.id==logInfo.logType.id">
								<option selected="selected" value="<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></option>
							</s:else>	
							
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			 <td  style="width:70px;">权限：</td>
             <td colspan="2">
				<select name="logInfo.logAllowVisit">
					<option value="1" <s:if test="logInfo.logAllowVisit==1">selected="selected"</s:if>>公开</option>
					<option value="2" <s:if test="logInfo.logAllowVisit==2">selected="selected"</s:if>>仅好友</option>
					<option value="3" <s:if test="logInfo.logAllowVisit==3">selected="selected"</s:if>>仅自己可见</option>
				</select>
            </td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="更新"/></td>
		</tr>
	</table>
	</form>
  </body>
</html>
