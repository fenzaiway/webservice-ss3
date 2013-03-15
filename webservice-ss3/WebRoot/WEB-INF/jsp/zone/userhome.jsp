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
    
    <title>个人主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/userhome.css">
	

  </head>
  
  <body>
	<div id="user_top_navi">顶部导航</div>
    <div style="height:70px;"></div>
    <div id="main">
    	<div id="left">
        	<div id="head_img">
            	<div class="left_content">
                	<img src="<%=basePath%>images/111.jpg" alt="头像"/>
                </div>
            </div>
          <div id="intro">
          	<div class="left_content">
            	<div>
            	<h3>fenzaiway</h3>
            	<p>
            	百度空间官方空间，致力于将空间最好的内容推荐给大家！ 欢迎推荐/自荐优质空间和内容，精彩共同分享！</p>
                </div>
            </div>
          </div>
             <div id="fans">
             	<div class="left_content">
             	  <span>粉丝：<a href="">9800961</a></span><br/>
             	  <span><a href="http://msg.baidu.com/msg/writing?receiver=Hi_%BE%AB%B2%CA" target="_blank">私信</a> | <a href="http://hi.baidu.com/hispace/albums" target="_blank">相册</a> | <a href="http://hi.baidu.com/hispace/archive" target="_blank">存档</a> | <a href="http://hi.baidu.com/hispace#" onclick="return false">友情链接</a></span><br/>
             	  <span>加关注</span>
             	</div>
             </div>
            <div id="new_photo">
            	<div class="left_title_word">
            	<span class="title_word">最新照片</span>
                </div>
                <div class="left_content">照片</div>
            </div>
            <div id="all_tag">
            	<div class="left_title_word">
            	<span class="title_word">全部标签</span>
                </div>
                <div class="left_content">
                	<ul>
						<s:iterator value="logTagList" id="logtag">
                    	<li><a href=""><s:property value="#logtag.tagName"/></a></li>
						</s:iterator>
                    </ul>
                </div>
            </div>
        </div>
        <p>&nbsp;</p>
       
        <div id="right">
			<s:iterator value="logInfoList" id="logInfo">
        	<div class="log_title"><a href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#logInfo.id'/>"><s:property value="#logInfo.logTitle"/></a></div>
            <hr class="hr_title"/>
            <div class="log_content">
            	<img src="http://image.tianjimedia.com/uploadImages/2013/005/087W0N5R6WY0.jpg" alt=""/>
                <div class="content_detail">
                  <s:property value="#logInfo.logText" escape="false"/>
                </div>
            </div>
            <hr/>
            <div class="log_buttom">
             <span class="buttom_tag">
            <ul>
            	<li><a href="">#远方的父母</a></li>
            </ul>
            </span>
            <span  class="buttom_a">
				<a href="http://hi.baidu.com/hispace/item/27db1401ef781b35addc701a">阅读全文</a>
				<a href="http://hi.baidu.com/hispace/item/27db1401ef781b35addc701a#reply" hidefocus="">评论（<s:property value="#logInfo.logComments.size()"/>）</a>
				<a href="http://hi.baidu.com/hispace/item/27db1401ef781b35addc701a#repost" hidefocus="">转载(518)</a></span><br />
            </div>
            <hr/>
            <div class="log_split"></div>
			</s:iterator>
			<div id="page" style="margin-top: 10px;">
					<s:property value="paginationSupport.pageToolBar" escape="false"/>
				</div>
        </div>
		
        <div class="clr"></div>
        
    </div>    


<s:debug></s:debug>
  </body>
</html>
