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
		.headImg img{margin:3px;width: 70px;height: 70px;}
		.info
		{
			width: 630px;
			float:right;
			background-color: #fff;
			height:260px;
			min-height: 260px;
			height: auto!important;
			border:1px solid #ccc;
			margin-bottom: 15px;
			border-radius:8px;
			-webkit-box-shadow:0 0 10px 0 #aaa;
			-moz-box-shadow:0 0 10px 0 #aaa;
		 }
		 
		h3{color:#454545;}
		.info_like,.info_tag{margin-bottom: 25px;}
		.info_like{color:#A9A9A9;font-size:14px;float: right;margin-right: 20px;}
		.info_like li{margin-right: 15px;}
		.info_tag{color:#3FA7CB;font-size:14px; float: left;}
		.info_tag ul{margin-left: -15px;}
		.info_tag li{margin-left: 15px;}
		.info_detail{margin: 10px;font-size:16px;font-family:新宋体;line-height:22px;color:#454545;}
		.loginfo_img{width:280px;height: 200px; float: left;}
		.loginfo_img img{width:260px;height: 200px;padding: 10px;}
		.info_user{float: left;}
		.info_time{float: right;}
		.info_time,.info_user{margin:25px 15px;}
		#center_content{width:980px;}
		#top_navi{margin-top: -65px;width: 720px;height: 100px;border: 1px solid silver;float:left;z-index: 2;margin-bottom: 30px;}
		#head_navi{float: left;width:156px;background-color:#F9F9F9;height:100px; }
		#head_navi div{width:115px;height:115px;margin-top:-35px;margin-left:20px;background-color:#fff;}
		#head_navi img{margin:3px;width: 110px;height: 110px;}
		#user_setting{margin-top: -65px;width: 230px;height: 44px;border: 1px solid silver;float:right;background-color: #3DA8CC;color:#fff;}
		#left_navi{width: 230px;height: 355px;height:auto!important;border: 1px solid silver;float:right;margin-top: -130px;}
		.left_navi_1{height: 60px;border-bottom: 1px dotted #ccc; }
		.left_navi_1 span
		{float:left;width:65px;height:60px;border-right: 1px solid #ccc;text-align:center;
		}
		.rec_sub li{list-style:disc;float: none; }
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
		<div id="center_content">
		<div id="top_navi">
			<div id="head_navi">
				<div>
				 <img src="<%=basePath %>images/111.jpg" alt="头像" />
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
		<div id="user_setting"><s:property value="username"/></div>
		</div>
	<div class="clr"></div>
  	<span style="color:#999999;font-size:16px;font-family:Verdana, Arial, Helv, Helvetica, sans-serif;">我关注的空间动态</span>
	<div class="clr"></div><div class="clr"></div>
	<hr style="width: 720px;float: left;margin-bottom: 15px;"/>
	<div class="clr"></div>
	<div id="loginfo_list">
		<div class="loginfo_list_left">
			<div class="headImg">
				<img src="<%=basePath %>images/111.jpg" alt="头像" />
			</div>
			<div class="info">
				
				<div><span class="info_user">用户</span><span class="info_time">时间</span></div>
				<div class="clr"></div>
				<div>
					<h3>Marvell 发表四核 PXA1088 芯片组，支持全球多种移动网络</h3>
					<div style="margin-bottom: 10px;">
						<div class="loginfo_img"><img src="" alt="图片"/></div>
						<div class="info_detail">
						去年 Marvell 凭借其 802.11ac 无线芯片在市场中获得了不错的反响，而到了今年首先上来打头炮的就是这款专为手机和平板设计、型号为 PXA1088 的四核 ARM Cortex-A7 芯片组了。它最大的卖点在于支持全球范围内包括 21Mbps HSPA+、TD-HSPA+、EDGE 和 WCDMA 在内的多种移动网络，同时还配有负责 Wi-Fi、蓝牙 4.0、FM 广播功能的 Avastar 88W8777 晶片，另外还支持 GLONASS 和 GPS 卫星系统。 

当然除了支持多种移动网络之外 PXA1088 也还有其它的强项，它配有 Vivante GPU，支持 OpenGL ES 2.0 / 1.1以及 OpenVG 1.1，在 1080p 硬件编码 / 解码技术的加持下在视频方面也有相当出色的表现。Marvell 在新闻稿中透露已经有数家知名 OEM 厂商计划于今年上半年推出使用 PXA1088 的装置，如果在下周的 MWC 上能见到其中的一些就好咯。
						</div>
					</div>
					<div>
					<span class="info_tag">
						<ul>
							<li>#科学</li>
							<li>#数码 </li>
							<li>#科技</li>
							<li>#硬件</li>
						</ul>
					 </span>
					<span class="info_like">
						<ul>
							<li>热度</li>
							<li>转载 </li>
							<li>评论(5)</li>
							<li>喜欢(2)</li>
						</ul>
					</span></div>
				</div>

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
				<img src="<%=basePath %>images/111.jpg" alt="头像" />
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
	<div id="left_navi">
		<div style="width: 100%;height: 355px;">
			<div class="left_navi_1">
				<span>15<br/>关注</span>
				<span>5<br/>粉丝</span>
				<span style="border-right:0px solid #ccc;">30<br/>记录</span>
			</div>
			<div style="height: 60px;padding-top: 10px;padding-left:15px;border-bottom: 1px dotted #ccc;">
				<input type="text" name="tag" style="line-height: 30px;width: 170px;border-radius:3px;" /><input type="button" style="border-radius:3px;background:url(<%=basePath%>images/tagsearch.gif); border:1px solid #eee;  width:40px; height:30px; background-repeat:no-repeat;padding-buttom:5px;" >
			</div>
			<div style="height: 40px;height: auto!important;">
				<h4>推荐订阅的标签</h4>
				<ul class="rec_sub">
					<li>美女</li>
					<li>明星</li>
					<li>生活</li>
				</ul>
			</div>
			<div style="height: 160px;">推荐</div>
		</div>
		<div style="height: 40px;">发现更多有趣内容</div>
		<div style="height: 90px;">访问统计</div>
		<div style="height: 320px;">客户端</div>
	</div>
	<div class="clr"></div>
	<s:debug></s:debug>
</div></div>
  </body>
</html>
