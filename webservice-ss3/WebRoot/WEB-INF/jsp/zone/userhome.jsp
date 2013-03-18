<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		
		var tousername; //关注的是哪个用户的空间
		var attentionButHtml = "";
		
		///加关注事件函数
		function addAttention()
		{
			//alert(tousername)
			$.post("attention/addAttention.do",{"toUserName":tousername},function(data,status)
			{
				attentionButHtml = "<input type='button' class='attentionBut' onclick='cancelAttention();' value='取消关注'/>";
				$(".attentionDiv").empty().html(attentionButHtml);
			});
		}
		
		//取消关注事件函数
		function cancelAttention()
		{
			$.post("attention/updateAttention.do",{"toUserName":tousername},function(data,status)
			{
				attentionButHtml = "<input type='button' class='attentionBut' onclick='addAttention();' value='加关注'/>";
				$(".attentionDiv").empty().html(attentionButHtml);
			});
		}
		
		$(function()
		{
			tousername = '<s:property value="username"/>';
		});
		
	</script>
  </head>
  
  <body>
	<div id="user_top_navi"><a href="userzone/infocenter.do">首页</a></div>
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
            	<h3>${zoneuser}</h3>
            	<p>
            	${zoneuser}的空间</p>
                </div>
            </div>
          </div>
             <div id="fans">
             	<div class="left_content">
             	  <span>粉丝：<a href="">9800961</a></span><br/>
             	  <span><a href="http://msg.baidu.com/msg/writing?receiver=Hi_%BE%AB%B2%CA" target="_blank">私信</a> | <a href="http://hi.baidu.com/hispace/albums" target="_blank">相册</a> | <a href="http://hi.baidu.com/hispace/archive" target="_blank">存档</a> | <a href="http://hi.baidu.com/hispace#" onclick="return false">友情链接</a></span><br/>
             	  <span>
					<div class="attentionDiv" style="margin-top: -25px;">
             	  	<s:if test="0==isAttention">
						<input type="button" class="attentionBut" onclick="addAttention();" value="加关注"/>
             	  	</s:if>
					<s:elseif test="1==isAttention">
						<input type="button" class="attentionBut" onclick="cancelAttention();" value="取消关注"/>
					</s:elseif>
					<s:elseif test="-2==isAttention">
						<input type="button" class="attentionBut" onclick="gotoLogin();" value="登录"/>
					</s:elseif>
					</div>
             	  </span>
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
