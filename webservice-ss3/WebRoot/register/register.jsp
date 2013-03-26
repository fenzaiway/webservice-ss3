<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>轻轻一点--注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{margin: 0px; padding: 0px;}
		body{background-color: #F5F5F5;}
		.top_banner{position: absolute;border-bottom: 1px solid #E1E1E1;height: 100px;width: 100%;left: 0px;top:0px;}
		.myRegisterForm{margin: 0px auto; margin-top: 100px;}
		#mailBox{background:#fff;border:1px solid #ddd;padding:3px 5px 5px;position:absolute;z-index:9999;display:none;-webkit-box-shadow:0px 2px 7px rgba(0, 0, 0, 0.35);-moz-box-shadow:0px 2px 7px rgba(0, 0, 0, 0.35);margin-top: 20px; }
#mailBox p{width:100%;margin:0;padding:0;height:20px;line-height:20px;clear:both;font-size:12px;color:#ccc;cursor:default;}
#mailBox ul{padding:0;margin:0;}
#mailBox li{font-size:12px;height:22px;line-height:22px;color:#939393;font-family:'Tahoma';list-style:none;cursor:pointer;overflow:hidden;}
#mailBox .cmail{color:#000;background:#e8f4fc;}
	</style>
  <script type="text/javascript" src="<%=basePath%>js/autoMail.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#email').autoMail({
				emails:["163.com",
										"qq.com",
										"126.com",
										"hotmail.com",
										"souhu.com",
										"yahoo.com",
										"139.com",
										"wo.com.cn",
										"wo.com",
										"sina.com.cn",
										"sina.com",
										"189.com",
										"live.cn",
										"live.com.cn"
						]
			});
		});
	</script>
</head>
  
  <body>
	<div class="top_banner">
		<div style="position: relative;left:25%;margin-top: 15px;">
			<a href="userlogin/gotoLogin.do"><img src="<%=basePath%>images/top_logo.gif"><img src="<%=basePath%>images/top_reg.gif"></a>
		</div>
	</div>
	<div class="myRegisterForm">
	 <s:fielderror fieldName="saveError"></s:fielderror>
	<form class="registerform" method="post" action="register/save.do">
            <table width="100%" style="table-layout:fixed;">
			 <tr>
	                <td class="need" style="width:10px;">*</td>
	                <td style="width:70px;">邮箱：</td>
	                <td style=" width:280px;"><input type="text" value="" id="email" name="userRegister.email" class="inputxt" datatype="e" nullmsg="请输入您邮箱！" errormsg="邮箱地址不正确！"  /></td>
	                <td>
	                    <div class="Validform_checktip"></div>
	                    <div class="info">请输入您的邮箱<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
	                </td>
	          </tr>
          	<!--文本，用户名 -->
            <tr>
            	<td class="need" style="width:10px;">*</td>
                <td style="width:70px;">昵称：</td>
                <td style=" width:280px;"><input type="text" name="userRegister.username" value="" class="inputxt" datatype="s6-18" nullmsg="请输入您的昵称" errormsg="昵称至少6个字符串，最多18个字符串"/></td>
                <td>
                	<div class="Validform_checktip"></div>
                    <div class="info">昵称至少6个字符串，最多18个字符串<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>
                </td>
            </tr>
		
           
           <!--密码 -->
            <tr>
            	<td class="need" style="width:10px;">*</td>
                <td style="width:70px;">密码：</td>
                <td style=" width:280px;"><input type="password" name="userRegister.password" value="" class="inputxt" plugin="passwordStrength" datatype="s6-18" nullmsg="请输入密码！" errormsg="密码至少6个字符,最多18个字符！"/></td>
                <td>
                	<div class="Validform_checktip"></div>
                    <div class="passwordStrength" style="display:none;"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>
                    <div class="info">密码至少6个字符,最多18个字符！<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>
                </td>
            </tr>
           <tr>
            	<td class="need" style="width:10px;">*</td>
                <td style="width:70px;">确认密码：</td>
                <td style=" width:280px;"><input type="password" name="repassword" value="" recheck="userRegister.password" class="inputxt" datatype="s6-18" nullmsg="请确认密码！" errormsg="两次输入的密码不一致！"/></td>
                <td>
                	<div class="Validform_checktip"></div>
                    <div class="info">请确认您的密码<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>
                </td>
            </tr>

		  <tr>
               <td class="need">*</td>
               <td>性别：</td>
               <td><input type="radio" checked="checked" value="1" name="userRegister.sex" id="male" class="pr1" datatype="*" nullmsg="请选择性别！" errormsg="请选择性别！" /><label for="male">男</label> <input type="radio"  value="2" name="userRegister.sex" id="female" class="pr1" /><label for="female">女</label></td>
               <td>
               	<div class="Validform_checktip"></div>
                   <div class="info">请告诉我们您的性别<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
               </td>
         </tr>

           
           <!-- 提交按钮 -->
           <tr>
                    <td class="need"></td>
                    <td></td>
                    <td colspan="2" style="padding:10px 0 18px 0;">
                        <input type="submit" value="" style="background-image: url('images/regbut.gif');width: 140px; height: 40px; border: 0px solid #fff;" />
                    </td>
                </tr>

          </table>
    </form>
	</div>
  </body>
</html>
