<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_shortup.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userzone.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#menu ul li{float: left; margin-left: 20px;}
		a{text-decoration: none;}
		a:hover{text-decoration: underline;color: red;}
		.home_navi{width: 140px;height: 100px;float:left;border-left:1px solid #E4E4E4;background-color: #fff;vertical-align: middle;}
		.sub_navi{width:55px;height: 65px;margin:0 auto;margin-top: 20px; }
		.sub_navi span{display: block;margin-top:5px;margin-left: 5px;}
		.sub_navi img{margin-left: 5px;margin-top: 3px;}
		#loginfo_list{height:260px;min-height: 260px;height: auto!important;width: 720px; float: left;}
		loginfo_list_left{height:260px;min-height: 260px;height: auto!important;width: 720px; margin-bottom: 30px;}
		.headImg{width: 85px;height: 70px;float: left;}
		.info{width: 630px;float:right;background-color: #fff;height:260px;min-height: 260px;height: auto!important;border:1px solid #ccc;margin-bottom: 15px; }
	</style>
 <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript">
	$(function()
	{
		$(".home_navi").mouseover(function()
		{
			$(this).css(
			{
				"background-color":"#F9F9F9",
				"border-bottom":"2px solid #3DA8CC"
			});
		}).mouseout(function()
		{
			$(this).css(
			{
				"background-color":"#FFF",
				"border-bottom":"0px solid #3DA8CC"
			});
		});
	});
</script>
  </head>
  
  <body>
	<div class="outer">
 		<div class="inner">
		<div style="width:980px;">
		<div style="margin-top: -65px;width: 720px;height: 100px;border: 1px solid silver;float:left;z-index: 2;margin-bottom: 30px;">
			<div style="float: left;width:156px;background-color:#F9F9F9;height:100px; ">
				<div style="width:115px;height:115px;margin-top:-35px;margin-left:20px;background-color:#fff;">
				 <img style="margin:3px;width: 110px;height: 110px;" src="<%=basePath %>images/111.jpg" alt="头像" />
				</div>
			</div>
			<div class="home_navi">
				<div class="sub_navi">
					<img src="<%=basePath %>images/navi_write.gif" alt="写文字">
					<span>写文字</span>
				</div>
			</div>
			<div class="home_navi">
				<div class="sub_navi">
					<img src="<%=basePath %>images/navi_photo.gif"  alt="传图片">
					<span>传图片</span>
				</div>
			</div>
			<div class="home_navi">
				<div class="sub_navi">
					<img src="<%=basePath %>images/navi_vedio.gif"  alt="转视频">
					<span>转视频</span>
				</div>
			</div>
			<div class="home_navi">
				<div class="sub_navi">
					<img src="<%=basePath %>images/navi_music.gif" alt="发音乐">
					<span>发音乐</span>
				</div>
			</div>
		</div>
		<div style="margin-top: -65px;width: 200px;height: 44px;border: 1px solid silver;float:right;background-color: #3DA8CC;color:#fff;"><s:property value="username"/></div>
		</div>
	<div class="clr"></div>
  	<span style="color:#999999;font-size:16px;font-family:Verdana, Arial, Helv, Helvetica, sans-serif;">我关注的空间动态</span>
	<div class="clr"></div><div class="clr"></div>
	<hr style="width: 720px;float: left;margin-bottom: 15px;"/>
	<div class="clr"></div>
	<div id="loginfo_list">
		<div class="loginfo_list_left">
			<div class="headImg">
				<img style="margin:3px;width: 70px;height: 70px;" src="<%=basePath %>images/111.jpg" alt="头像" />
			</div>
			<div class="info">
				2月2日 18:00
	寂静岭2 DVD高清版本下载
	哈哈 自家翻译的寂静岭2 DVD版, 号称[480P]
	绝非网上用自动引擎翻译的结果, 大部分是听译,  希望大家喜欢
	喜欢尝鲜的同学们可以来看看 网盘下载 欢迎转载分享哦
	http://pan.baidu.com/share/link?shareid=211716&uk=201692127
	评论转载
			</div>
			<div class="info">
						2月2日 18:00
			寂静岭2 DVD高清版本下载
			哈哈 自家翻译的寂静岭2 DVD版, 号称[480P]
			绝非网上用自动引擎翻译的结果, 大部分是听译,  希望大家喜欢
			喜欢尝鲜的同学们可以来看看 网盘下载 欢迎转载分享哦
			http://pan.baidu.com/share/link?shareid=211716&uk=201692127
			评论转载
			</div>
		</div>
	
		<div class="clr"></div>
		<div class="loginfo_list_left">
			<div class="headImg">
				<img style="margin:3px;width: 70px;height: 70px;" src="<%=basePath %>images/111.jpg" alt="头像" />
			</div>
		<div class="info">
				2月2日 18:00
	寂静岭2 DVD高清版本下载
	哈哈 自家翻译的寂静岭2 DVD版, 号称[480P]
	绝非网上用自动引擎翻译的结果, 大部分是听译,  希望大家喜欢
	喜欢尝鲜的同学们可以来看看 网盘下载 欢迎转载分享哦
	http://pan.baidu.com/share/link?shareid=211716&uk=201692127
	评论转载
			</div>

		</div>
<div class="clr"></div>
</div>
	<div style="width: 200px;height: 355px;height:auto!important;border: 1px solid silver;float:right;margin-top: -130px;">
		<div style="width: 100%;height: 355px;">
			<div style="height: 60px;">关注</div>
			<div style="height: 135px;">搜索</div>
			<div style="height: 160px;">推荐</div>
		</div>
	</div>
	<div class="clr"></div>
	<s:debug></s:debug>
</div></div>
  </body>
</html>
