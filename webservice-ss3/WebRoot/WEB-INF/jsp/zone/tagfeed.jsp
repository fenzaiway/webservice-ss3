<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_shortup.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>最新关注内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="<%=basePath%>css/commons.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/userzone.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/tagfeed.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/focusBlur.js"></script>
<script type="text/javascript" src="<%=basePath%>js/subTag.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commons.js"></script>
	<SCRIPT type="text/javascript">
		var username;
		$(function()
		{
			username = '<s:property value="username"/>';
			myusername = '<s:property value="myusername"/>';
			
			if(""==myusername)
			{
				
			}else
			{
				init();
			}
			
			backToTop();
			//alert(username);
			$("#tag").FocusBlur();////设置当文本框获得焦点的时候，文字消失
		});
	</SCRIPT>
	
  </head>
  
  <body>
    <div class="outer">
 		<div class="inner">
		<div id="center_content">
		<div id="top_navi">
			<div id="tagName" style="margin-top: 30px; margin-left:35px;font-size: 24px;float: left; "><s:property value="tagName"/></div>
			<div>
				<s:if test='"" == myusername || null == myusername'>
					
				</s:if>
				<s:else>
					<input type="button" style="margin-top: 20px; margin-left:15px;width:70px; height:35px; line-height:35px;background-color:#94B600; color:#FFFFFF;border:0px solid #EDEDEF;font-size:16px; font-family:微软雅黑;" value="订阅"/>
				</s:else>
			</div>
		</div>
			<s:if test='"" == myusername || null == myusername'>
				<div id="user_setting" style="background-color: #fff"><a href="userlogin/gotoLogin.do">登录</a>后可订阅标签</div>
			</s:if>
			<s:else>
				<div id="user_setting"><s:property value="myusername"/></div>
			</s:else>
		</div>
	
	
	<div class="clr"></div>
	<div id="loginfo_list">
			<s:iterator value="searchTagData.data" id="data">
		<div class="loginfo_list_left">
			<div class="headImg">
				<img src="<%=basePath %>images/111.jpg" alt="头像" />
			</div>
			<div class="info">
				
				<div><span class="info_user"><s:property value="#data.username"/></span><span class="info_time"><s:property value="#data.publishTime"/></span></div>
				<div class="clr"><h4><s:property value="#data.logTitle"/></h4></div>
				<div>
					<div style="margin-bottom: 10px;">
						<div class="loginfo_img"><img src="<%=basePath %>images/ajaxDemo/mrPip.jpg" alt="图片"/></div>
						<div class="info_detail">
							<s:property value="#data.logContent" escape="false"/>
						</div>
					</div>
					<div class="clr"></div>
					<div>
					<span class="info_tag">
						<ul>
							<s:iterator value="#data.tags" id="tag">
								<li>#<s:property value="#tag"/></li>
							</s:iterator>
							
						</ul>
					 </span>
					<span class="info_like">
						<ul>
							<li>热度(0)</li>
							<li>转载(<s:property value="#data.reprintNum"/>)</li>
							<li>评论(<s:property value="#data.commentNum"/>)</li>
							<li>喜欢(<s:property value="#data.likeNum"/>)</li>
						</ul>
					</span></div>
				</div>

			</div>
			
			
		</div>
	
		<div class="clr"></div>
		</s:iterator>
		<div id="pagebar"></div>
</div>
	<s:if test='"" == myusername || null == myusername'>
	
	</s:if>
	<s:else>
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
				<span style="border:0px solid red;"><input type="text" id="tag" value="输入想要查找的标签" name="tag" style="height:30px;line-height: 30px;width: 165px;border-top-left-radius:4px;border-buttom-left-radius:4px;margin-top: 10px;font-size: 16px;color:#AFB0B0;border: 1px solid #ccc; border-right: 0px solid #ccc;padding-left: 3px;" />
				<input type="button" id="tagSearch" style="border-top-right-radius:4px;border-buttom-right-radius:4px;background:url(<%=basePath%>images/tagsearch.gif);line-height: 33px; border:1px solid #ccc;  width:40px; height:33px; background-repeat:no-repeat;margin-left:-5px;" ></span>
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
	</s:else>
	<div class="clr"></div>
	<s:debug></s:debug>
</div></div>
  </body>
</html>
