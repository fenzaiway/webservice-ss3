<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_navi.jsp" %>
<%@ include file="/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>相册列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>css/commons.css" type="text/css"></link>
	<style type="text/css">
		body{
			background-color: #fff;
			margin: 0px auto;
		}
		#albumTypeName{width: 150px;height: 18px;line-height: 18px;border: 1px solid green;}
	</style>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
  </head>
  
  <body>
  	<div style="width: 980px;margin: 0px auto;">
  	<s:property value="albumTypeList.get(0).album."/>
  	<div style="width: 980px;margin: 0px auto;">
    <div id="newAlbumType" style="display: none;">
    	<form action="${ctx }/albumtype/save.do" method="post">
    		相册名称：<input type="text" name="albumTypeName" id="albumTypeName"/>
    		<input type="submit" style="margin-left: 10px;width: 70px;height: 35px;line-height: 35px;" value="创建"/>
    	</form>
    </div>
    <s:if test="albumTypeList.size==0">
    	暂无数据，创建<a href="javascript:createAlbumType();">相册分类</a>
    </s:if>
    <s:else>
    	
    	<div id="albumType" style="width: 85%;border: 1px solid #ccc;height: auto;margin: 0px auto;margin-top: 20px;padding-left: 45px;">
    		
    		<s:iterator id="albumType" value="albumTypeList" status="st">
    			<a href="${ctx }/album/gotoAlbumList.do?albumTypeId=<s:property value='#albumType.id'/>">
    			<div style="width: 120px;height: 120px;border: 1px solid #eee;margin: 16px;display: inline;float: left;">
    				<div style="width: 120px;height: 100px;border: 1px solid green;">
    					<img alt="" style="width: 120px;height: 100px;border: 0 none;" src="<s:property value='#albumType.coverImg'/>">
    				</div>
    				<div style="width: 120px;height: 20px;border: 1px solid pink;text-align: center;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><span style="float:left;display:block;width:85px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><s:property value="#st.count"/>、<s:property value="#albumType.albumTypeName"/></span>（<s:property value="#albumType.album.size()"/>）</div>
    			</div></a>
    		</s:iterator>
    		<div style="clear: both;"></div>
    	</div>
    	创建<a href="javascript:createAlbumType();">相册分类</a>
    </s:else>
   </div>
   <script type="text/javascript">
  	
  	function createAlbumType()
  	{
  		$("#newAlbumType").css("display","block");
  	}
  	
  	$(function()
  	{
  		
  	});
   
   </script>
   </div>
   
  </body>
</html>
