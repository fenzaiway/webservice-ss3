<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>腾讯微博登录</title>
    
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
    <input type="button" value="登录授权" onclick="login();"/>
    <input type="button" value="退出操作" onclick="logout();"/>
    <input type="button" value="状态" onclick="logStatus();"/>
	<input type="text" id="weibotext" /><input type="button" value="发表微博" id="button" onclick="addWeoBo();"/><br/>
<div id="qq_account_log"></div>
					
  </body>
	<script src="//mat1.gtimg.com/app/openjs/openjs.js"></script>
	<script src="<%=basePath%>js/jquery.js"></script>
<script src="http://mat1.gtimg.com/app/openjs/widget/connect.js"></script>
	<script type="text/javascript">
	 // 同步加载，无需回调函数
	    // 你的代码   
	    
		 T.init({
        appkey:801321945,
        autoclose:true
       // callbackurl:{}
    });
   function login() {

	T.login(function (loginStatus) {
		alert(loginStatus.nick);
		alert(loginStatus.access_token );
	},function (loginError) {
		alert(loginError.message);
	});
	
}
	function logout()
	{
		T.logout(function(){alert('ok!')});
	}
	
	function logStatus()
	{
		alert(T.loginStatus().access_token);
	}
	
	function addWeoBo(){
		var content = $("#weibotext").val();
		var url = document.URL ;
		content +=url;
		///alert($("#weibotext").val());\content
		//alert(content);
		//alert();
		///T.api(inName, inParamMap, inDataType, inType, inOverride)
		T.api("t/add", {"content":content,"clientip":"222.217.220.226"},"json","post")
		 .success(function (response){
          alert(response);
      })
     .error(function (code, message) {
          alert(message);
      });
	}
	</script>
	<script>
    T.init({appkey:801321945});
    T.widget("账号连接", {src : 'http://mat1.gtimg.com/app/openjs/widget/static/connect/images/50btn.png', head : 1}).show('qq_account_log');
</script>
</html>
