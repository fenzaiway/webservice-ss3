<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文章详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/info_detail.css">
	
	<SCRIPT type="text/javascript">
		$(function()
		{
			//alert('aa');
			////移除img前面的内容，避免图片样式影响排版
			$("#content img").parent().each(function()
			{
				var xx=$(this).html();
				$(this).replaceWith(xx);
							
			});
			
			$("#backqqcom").remove();
			//$("img").parent().remove();
		})
	</SCRIPT>

  </head>
  
  <body>
    
	<div id="user_top_navi"><a href="userzone/infocenter.do">首页</a></div>
    <div style="height:70px;"></div>
	
    <div id="main">
    	<div id="title">
        	<span class="title_1">
            	<h3><s:property value="logInfo.logTitle"/></h3>
            </span>
            <span class="time"><s:property value="logInfo.logPublishTime"/></span>
        </div>
        <div class="clr"></div>
        <div id="content">
         <s:property value="logInfo.logText" escape="false"/>
        </div>
		<div id="tag">
			<ul>
				<s:iterator value="logInfo.logTags" id="tag">
					<li><a href="">#<s:property value="#tag.tagName"/></a></li>
				</s:iterator>
            </ul>
			
		</div>
       	<div class="show_data">
       	<span style="float:right; margin-right:15px;">
			<a style="padding-left: 10px;" href="">阅读（<s:property value="logInfo.logVisits.size()"/>）</a>
			<a style="padding-left: 10px;" href="">评论（<s:property value="logInfo.logComments.size()"/>）</a>
			<a style="padding-left: 10px;" href="">转载（<s:property value="logInfo.logComments.size()"/>）</a>
			<c:if test="${zoneuser==myusername}">
				<a style="padding-left: 10px;" href="">编辑</a>&nbsp;&nbsp;
				<a style="padding-left: 10px;" href="javascript:deleteLogInfo(<s:property value='logInfo.id'/>);">删除</a>
			</c:if>       		
       	</span>
		</div> 
        <div class="like">
			<span class="show_word">可能喜欢</span>
			<div class="may_like"></div>
			<div class="may_like"></div>
			<div class="may_like"></div>
			<div class="may_like"></div>
			<div class="may_like"></div>
			<div class="may_like"></div>
			<div class="may_like"></div>
		</div> 
        <div class="page">
			<span style="float:left;display:block;margin-left:10px;">上一篇</span>
			<span style="float:right;display:block;margin-right:10px;">下一篇</span>
		</div> 
        <div class="comment">
			<span class="show_word">评论</span>
			<div style="width: 760px; float: left;">
			<textarea rows="" cols="" class="comment_text"></textarea>
			<input type="button" value="发布" style="float: right;width:70px; height:35px; line-height:35px;background-color:#94B600; color:#FFFFFF;border:0px solid #EDEDEF;font-size:16px; font-family:微软雅黑;"/>
			</div>
			<div class="clr"></div>
			<div class="comment_list">11</div>
			<div class="comment_list">22</div>
			<div class="comment_list">33</div>
		</div> 
        
    </div>
	<s:debug></s:debug>
  </body>
</html>
