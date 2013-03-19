<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/validataForm.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/datePicker.css">

<!-- 以下为js文件 -->
  <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/Validform.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/validataForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/plugin/datePicker.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/plugin/passwordStrength.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/plugin/WdatePicker.js"></script>
 <script type="text/javascript" src="<%=basePath%>editor/editor_config.js"></script>
 <script type="text/javascript" src="<%=basePath%>editor/editor_all.js"></script>
 <script type="text/javascript">
 	$(function()
 	{
		$(".registerform").Validform({
			tiptype:2
		});
 	});
 </script>