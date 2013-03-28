<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:forward page="userlogin/gotoLogin.do"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录--轻轻一点，分享生活</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#tipShow{border: 1px solid gray;width: 100px;height: 100px;text-align: center;position: absolute;}
	</style>
  <script type="text/javascript" src="js/jquery.js"></script>
  <!--<script type="text/javascript">
  	$(function()
  	{
  		isAttention();////加载是否关注		
  	});

  	///加关注
  	function addAttention(id)
  	{
		//alert(id);
		var username=$(".userHeadImg:eq("+id+")").attr("title");
		//alert(username);
		$.post("attention/addAttention.do",{"toUserName":username},function(data,status)
		{
			
		});
		var html = "<input type='button' value='-取消关注' onclick='cancelAttention("+id+");'/>";
		$(".attention:eq("+id+")").empty().html(html);
  	}

  	//取消关注
	function cancelAttention(id)
	{
		//alert("can"+id);
		var username=$(".userHeadImg:eq("+id+")").attr("title");
		$.post("attention/updateAttention.do",{"toUserName":username},function(data,status)
		{
			
		});
		var html = "<input type='button' value='+加关注' onclick='addAttention("+id+");'/>";
		$(".attention:eq("+id+")").empty().html(html);
	}

  	//是否关注
  	function isAttention()
  	{
		var length = $(".attention").length;
		var toUserNames = "";
		for(var i=0; i<length; i++)
		{
			toUserNames += $(".userHeadImg:eq("+i+")").attr("title");
			toUserNames+=","
		}
		toUserNames = toUserNames.substring(0,toUserNames.lastIndexOf(','));
		
		$.post("attention/isAttention.do",{"toUserNames":toUserNames},function(data)
		{
			//alert(data.length);
			for(var i=0;i<data.length;i++)
			{
				if(1==data[i].isAttention)
				{
					//alert($(".attention:eq("+i+")").html());
					$(".attention:eq("+i+")").empty().html("<input type='button' value='-取消关注' onclick='cancelAttention("+i+");'/>");
				}
			}
		});
  	}
  </script>-->
  </head>

  <body>
  	
  	<!-- <div class="outer">
 		<div class="inner">
<div id="main">
<s:property value="loadIndexContent.size()"/>
	<div class="res_content">
		<div style="float: left;width: 100px;min-height:100px;height:auto!important;">
		<s:iterator value="userHeadImgList" id="userHeadImg" status="st">
		<div style="min-height: 100px;height:auto!important;margin-top: 15px;border: 1px solid pink;">
			<img alt="aa" class="userHeadImg" src='<s:property value="#userHeadImg.imgLocation"/>' title='<s:property value="#userHeadImg.username"/>' width="100px" height="100px"/>
			<div style="width: 100px;line-height: 20px;" class="attention">
			
			<s:if test="#userHeadImg.username!=myusername"><input type="button" value="+加关注" onclick='addAttention(<s:property value="#st.index"/>);'/></s:if></div>
			
		</div>
		</s:iterator>
		</div>
		<div style="float: left;width: 80%;min-height:120px;height:auto!important;">
		<s:iterator value="logInfoList" id="logInfo" status="st">
		<div  style="height: 120px;min-height: 120px;margin-top: 15px;border: 1px solid red" name='<s:property value="#logInfo.username"/>'><s:property value="#logInfo.logTitle"/></div>
		<div class="clr"></div>
		</s:iterator>
		</div>
	</div>
	<div style=" clear: both;"></div>
   <div id="pageToolbar"><s:property value="paginationSupport.pageToolBar" escape="false"/></div>
</div>
</div></div> -->
  </body>
</html>
