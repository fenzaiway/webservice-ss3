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
    
    <title>邮箱验证</title>
    
	<style type="text/css">
	*{margin:0px; padding:0px;}
	body{background-color:#E1E1E3; font-size:14px; font-family:Arial, Helvetica, sans-serif;}
	#mail_main{margin:0px auto; width:980px; min-height:460px; height:auto!important;}
	#mail_main p{font-size:12px; color:#959595;}
	#mail_top{width:980px; min-height:355px; height:auto!important; background-color:#FFFFFF;}
	#mail_buttom{width:980px; min-height:105px; height:auto!important; background-color:#FFFFFF; padding:15px 0px;}
	#mail_buttom p,span{ margin:15px 30px;}
	a{ color:#3FA7CB;}
</style>
  <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
	//var email = '<s:property value="username"/>';
	function resend()
	{	
		var username = $("#resend").attr("username");;
		
		$.post("ajax/register/resend.do",{"username":username},function(data,status)
		{
			if("success"==status)
			{
				alert("重新发送成功");
			}
		});
	}
	
	$(function()
	{
		//alert('aa');
	});
</SCRIPT>
</head>

<body>
	<div id="mail_main">
    	<div id="mail_top" align="center">
        	<div style="margin:50px 0px 50px 0px; margin-top:50pxp;"><img style="margin-top:50px;" src="<%=basePath%>images/mail_pic.gif" /></div>
            <div  style="margin-bottom:50px;"><img src="<%=basePath%>images/activate_title.png" alt="注册成功" style="margin-bottom:30px;" /><p>只需登录邮箱<s:property value='account'/>点击链接激活空间</p></div>
            <div><a href="<s:property value='mailurl'/>"><img src="<%=basePath%>images/activate_go.png" alt="前往登陆" /></a></div>
            <hr style="width:880px; border:1px solid #E6E6E6; margin-top:50px;" align="center"/>
        </div>
        
      <div id="mail_buttom">
        	<span>还没有收到验证邮件？</span>
			<p>1.尝试到广告邮件、垃圾邮件目录里找找看</p> 
		  <p>2.<a href="javascript:resend();" id="resend" username="<s:property value='myusername'/>">再次发送验证邮件 </a></p>
			<p>3.如果重发注册验证邮件仍然没有收到，请<a href="register/gotoRegister.do">更换另一个邮件地址</a>
			<p> 


      </div>
    </div>
<s:debug></s:debug>
</body>
</html>
