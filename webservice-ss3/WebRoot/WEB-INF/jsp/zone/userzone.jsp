<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_shortup.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="<%=basePath%>css/commons.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/userzone.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.XYTipsWindow.2.8.js"></script>
<script type="text/javascript" src="<%=basePath%>js/commons.js"></script>
	<SCRIPT type="text/javascript">
		var username;
		var myusername;
		$(function()
		{
			backToTop();
			username = '<s:property value="username"/>';
			myusername = '<s:property value="myusername"/>';
			//alert(username);
		});
	</SCRIPT>
	
	<script type="text/javascript" src="<%=basePath%>js/userzone.js"></script>
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
					<span><a href="loginfo/newLogInfo.do">写文字</a></span>
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
	<div class="clr"></div>
  	<span style="color:#999999;font-size:16px;font-family:Verdana, Arial, Helv, Helvetica, sans-serif;">我关注的空间动态</span>
	<div class="clr"></div>
	<hr style="width: 720px;float: left;margin-bottom: 15px;"/>
	<div class="clr"></div>
	<div id="loginfo_list">
			<s:iterator value="logInfoList" id="loginfo">
		<div class="loginfo_list_left">
			<div class="headImg">
				<img src="<%=basePath %>images/111.jpg" alt="头像" />
			</div>
			<div class="info">
				
				<div><span class="info_user"><s:property value="#loginfo.username"/></span><span class="info_time"><s:property value="#loginfo.logPublishTime"/></span></div>
				<div class="clr"><h4><s:property value="#loginfo.logTitle"/></h4></div>
				<div>
					<div style="margin-bottom: 10px;">
						<div class="loginfo_img"><img src="<%=basePath %>images/ajaxDemo/mrPip.jpg" alt="图片"/></div>
						<div class="info_detail">
							<s:property value="#loginfo.logText" escape="false"/>
						</div>
					</div>
					<div class="clr"></div>
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
			
			
		</div>
	
		<div class="clr"></div>
		</s:iterator>
		<div id="pagebar"></div>
</div>
	<div id="left_navi">
		<div style="width: 100%;height: 355px;height: auto!important;">
			<div class="left_navi_1">
				<span>15<br/>关注</span>
				<span>5<br/>粉丝</span>
				<span style="border-right:0px solid #ccc;">
				<script src="ajax/zone/record.do" type="text/javascript"></script>
				<br/>记录</span>
			</div>
			<div style="height: 60px;padding-top: 10px;padding-left:15px;border-bottom: 1px dotted #ccc;">
				<span style="border:1px solid red;"><input type="text" name="tag" style="line-height: 30px;width: 165px;border-radius:3px;margin-top: 10px;" /><input type="button" style="border-radius:3px;background:url(<%=basePath%>images/tagsearch.gif); border:1px solid #ccc;  width:40px; height:30px; background-repeat:no-repeat;" ></span>
			</div>
			<div style="height: 40px;height: auto!important;">
				<h5>我订阅的标签</h5>
				<ul class="my_rec_sub">
					<li>美女</li>
					<li>明星</li>
					<li>生活</li>
				</ul>
			</div>
			<div style="height: 250px;height: auto!important;">
			<div><span style="float:left;">推荐订阅的标签</span><span style="float:right;"><a href="javascript:loadOtherTags();">换一批</a></span></div>
			<div class="clr"></div>
			<ul class="rec_sub">
					<li>美女</li>
					<li>明星</li>
					<li>生活</li>
				</ul>
			</div>
		</div>
		<div style="height: 40px;" id="more_tags">发现更多有趣内容</div>
		<div style="height: 90px; border-bottom: 1px solid #ccc;border-top: 1px solid #ccc;">访问统计
			<span style="display:block;">今日访问量：0</span>
			<span style="display:block;">总的访问量：0</span>
		</div>
		
		<div style="height: 320px;">客户端</div>
	</div>
	<div class="clr"></div>
	<s:debug></s:debug>
</div></div>
  </body>

</html>
