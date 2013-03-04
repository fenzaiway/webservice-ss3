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
    
    <title>发表文字</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>htmlTest/ueditor/editor_config.js"></script>
    <script type="text/javascript" charset="utf-8"  src="<%=basePath%>htmlTest/ueditor/editor_all.js"></script>
    <link href="<%=basePath%>css/feedTags.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath%>js/jquery.feedTags.js" type="text/javascript"></script>
    <script type="text/javascript">
	function GetCharLength(str)
	{
		var iLength = 0;  //记录字符的字节数
		for(var i = 0;i<str.length;i++)  //遍历字符串中的每个字符
		{
			if(str.charCodeAt(i) >255)   //如果当前字符的编码大于255
			{
				iLength += 2;    //所占字节数加2
			}
			else
			{
				iLength += 1;   //否则所占字节数加1
			}
		}
		return iLength;   //返回字符所占字节数
	}
	//若字符串长度超过要求，截掉多余部分
	function CutStr(elementID,len)   //elementID表示要进行处理的对象ID,len表示设置的限制字节数
	{
		alert('aaa')
		var str = document.getElementById(elementID).value;  //获取要处理的字符串
		var curStr = "";  //用于实时存储字符串
		for(var i = 0;i<str.length;i++)   //遍历整个字符串
		{
			curStr += str.charAt(i);  //记录当前遍历过的所有字符
			if(GetCharLength(curStr )>len)  //如果当前字符串超过限制长度
			{
				alert('a');
				document.getElementById(elementID).value = str.substring(0,i);  //截取多余的字符,并把剩余字符串赋给要进行处理的对象
				return;  //结束函数
			}
		}
	}
$(function()
{
	$('#feedTags').feedTags({
		isview:false,
		tags:$('#tags')
	});
	
	$(".user_tags_li").click(function()///通过鼠标点击添加标签	{
		var flag = false;
		var tagText = $(this).text();
		$(".tag").each(function()//判断标签是否已经存在		
		{
			if(tagText==$(this).text())
			{
				flag = true;
				return false;
			}
		});
		if(flag)
		{
			alert('标签已经存在');
		}else
		{
			$(".inputTag").val($(this).text());
			$(".user_tags_li").addTag($('#feedTags'),$(this));
		}
		
	});
});


</script>
<style type="text/css">
	*{ margin:0px; padding:0px;}
	body{ background-color:#E2E2E4;}
	h2{ font-family:simsun,宋体; font-size:26px; color:#444444; margin-top:35px; margin-left:33px; height:auto!important;}
	h3{ margin-top:33px; margin-left:33px; margin-bottom:20px; font-style:normal; font-family:宋体;color:#444444;  height:auto!important; display:table;}
	#new_info_main{ width:926px; margin:0px auto; background-color:#F3F3F3; min-height:670px; height:auto!important; border:1px solid #B7B7B8; margin-top:100px; margin-bottom:100px;}
	#new_info_left{ width:670px; float:left; background-color:#fff; border-right:1px solid #EBEBEB;min-height:300px; height:auto!important;}
	#new_info_right{ width:255px; background-color:#F3F3F3; float:right;min-height:645px;; height:auto!important; position:relative; height:100%;}
	#detail{ height:45px; border-bottom:1px solid #EBEBEB; height:auto!important;}
	#new_info_title{ height:90px; height:auto!important;}
	#content{ height:33px;min-height:300px; height:auto!important;}
	#new_info_content{ width:610px; height:290px; margin-left:33px;min-height:290px; height:auto!important;}
	#new_info_button{ height:90px; margin-left:33px; margin-top:30px; height:auto!important; margin-bottom:15px;}
	.add_tags{ width:200px; background-color:#FFF; height:100px; margin:30px 10px 20px 20px;height:auto!important;}
	.add_tags span{ background-color:#F3F3F3; font-size:12px; color:#999999; padding:10px; margin-top:5px;}
	.user_tags,.all_tags{ width:200px; margin-left:20px; border:1px solid #79A9B1; margin-bottom:20px; min-height:100px; height:auto!important;}
	.user_tags ul{list-style: none;}
	.user_tags_li{ float: left; background-color:#01A2D8;margin-left: 5px; margin-top: 5px;color:#fff; padding: 3px;font-family:微软雅黑;}
	.button{ width:104px; height:40px; background-color:#E2E2E4; border:0px solid #EDEDEF; font-size:16px; font-family:微软雅黑;}
	.button:hover{ background-color:#EEEEEE;}
	.publish{ background-color:#94B600; color:#FFFFFF; float:right; margin-right:30px;}
	.publish:hover{ background-color:#9EC100;}
	.visiable{margin-left:20px; margin-bottom:15px;}
	.visiable,.visiable select{ width:200px; height:35px; line-height:35px; color:#333; }
	.visiable select{border:1px solid #79A9B1; }
	.visiable select option{ margin-bottom:15px;}
	.left_detail{ width: 200px; margin-left: 20px; color: #999999; margin-bottom: 5px; display: block;}
	.clr{ clear:both;}
</style>
</head>

<body>
	<form action="loginfo/save.do" method="post">
	<div id="new_info_main">
    	<div id="new_info_left">
        	<div id="detail">
            	<h2>发布文字</h2>
            </div>
			<div id="new_info_title">
            	<h3>标题</h3>
                <input type="text" name="logInfo.logTitle" style="border:1px solid #CECECF; width:610px; height:45px; line-height:45px; font-size:26px; font-family:Microsoft YaHei,微软雅黑,tahoma,arial,simsun,宋体; margin-left:33px;" maxlength="50" /> 
            </div>
            <div id="content">
            	 <h3>内容</h3>
                <div id="new_info_content">
                	<script  id="editor" type="text/plain"></script>
                </div>
            </div>
            
            <div id="new_info_button">
            	<input type="button" class="button" value="取　消"/>
              <input style="display:none" type="button" class="button" value="保存草稿"/>
              <input type="button" class="button" value="预　览"/>
              <input type="submit" class="button publish" value="发　布"/>
            </div>
        </div>
      
        <div id="new_info_right">
        	<div class="add_tags">
            	<div id="feedTags"></div>
               
                <input type="hidden" value="" name="myLogTags" style="width:200px;" id="tags" />
                 <span>添加标签，用逗号或者回车号分隔</span>
            </div>
           <div class="user_tags">
           <ul>
           	<s:iterator value="logTagList" id="logtag">
           		<li class="user_tags_li"><s:property value="#logtag.tagName"/></li>
           	</s:iterator>
           	</ul>
           </div>
            <span class="left_detail">给标签分分类</span>
             <div class="visiable">
            	  <select name="select" id="tagid">
              	<s:iterator value="tagList" id="tag">
								<option value="<s:property value="#tag.id"/>"><s:property value="#tag.tagName"/></option>
				</s:iterator>
              </select>
            </div>
            <span class="left_detail">访问权限</span>
            <div class="visiable">
              <select  name="logInfo.logAllowVisit" id="select">
                <option value="1">仅自己可见</option>
                <option value="2">所有人可见</option>
              </select>
            </div>
			<span class="left_detail">个人分类</span>
            <div class="visiable">
					
            	 <select name="logTypeId" id="select">
              	<s:iterator value="logTypeList" id="logType">
								<option value="<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></option>
				</s:iterator>
              </select>
            </div>
            
             <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div> 
        </div>
        <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div> 
    </div>
</form>
	<s:debug></s:debug>
</body>
<script type="text/javascript">
    //实例化编辑器
  ///	var editor = new UE.ui.Editor();
   // editor.render("new_info_content");
    //1.2.4以后可以使用一下代码实例化编辑器
    UE.getEditor('editor')
    
</script>
</html>
