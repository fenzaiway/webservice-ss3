<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/top_navi.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发表文字</title>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath%>htmlTest/ueditor/editor_config.js"></script>
    <script type="text/javascript" charset="utf-8"  src="<%=basePath%>htmlTest/ueditor/editor_all.js"></script>
	<script src="//mat1.gtimg.com/app/openjs/openjs.js"></script>
    <link href="<%=basePath%>css/feedTags.css" rel="stylesheet" type="text/css" />

<script src="<%=basePath%>js/jquery.feedTags.js" type="text/javascript"></script>
    <script type="text/javascript">
   
    ////、初始化微博数据同步	
    T.init({
        appkey:801321945
    });
    
    ////微博授权登录
    function wbLogin()
    {
    	T.login(function (loginStatus) {
        ///alert(loginStatus.nick);
    	},function (loginError) {
        alert(loginError.message);
    	});
    }
    
    //退出操作
    function logout()
	{
		T.logout(function(){});
	}
    
    ///同步微博
    function addWB(content)
    {
    	//alert(content);
    	T.api("t/add", {"content":content,"clientip":"222.217.220.226"},"json","post")
		 .success(function (response){
          //alert(response);
      })
     .error(function (code, message) {
          alert(message);
      });
    }
    
    //、获取文本长度
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
	function CutStr(str,len)   //elementID表示要进行处理的对象ID,len表示设置的限制字节数
	{
		//alert('aaa')
		//var str = document.getElementById(elementID).value;  //获取要处理的字符串
		var curStr = "";  //用于实时存储字符串
		/*for(var i = 0;i<str.length;i++)   //遍历整个字符串
		{
			curStr += str.charAt(i);  //记录当前遍历过的所有字符
			if(GetCharLength(curStr )>len)  //如果当前字符串超过限制长度
			{
				//alert('a');
				curStr = document.getElementById(elementID).value = str.substring(0,i);  //截取多余的字符,并把剩余字符串赋给要进行处理的对象
				return curStr;  //结束函数
			}
		}*/
		curStr = str.substring(0,len);
		return curStr;  //结束函数
	}
	
$(function()
{
	$('#feedTags').feedTags({
		isview:false,
		tags:$('#tags')
	});
	
	////判断用户是否选择同步到微博
	$("#syswb").click(function()
	{
		///alert("aa");
		///if()
		//alert($("#syswb:checked").val());
		if("on" == $("#syswb:checked").val())
		{
			//logout();
			wbLogin();
		}
	});
	
	////获取用户发布的内容，然后同步到微博
	$("#subBut").click(function()
	{
		if("on" == $("#syswb:checked").val())
		{
			var title = $("#logtitle").val();
			var content = $("#editor").text();
			var content = UE.getEditor('editor').getPlainTxt();
			//alert("title=" + title);
			///alert("editor=" + content);
			//addWB(title,"aa");/
			title = "【"+title+"】"; //////140长度
			var titleLen = GetCharLength(title);
			var contentLen = GetCharLength(content);
			var length = 140;
			if(contentLen < (length-titleLen))
			{
				content = title+ content;
			}else
			{
				content = CutStr(content,(length-titleLen-3));
				content = title+ content + "...";
			}
			////alert(GetCharLength(content));
			//alert(content);
			addWB(content);
			///$("#wordform").submit();
		}
	});
	
	$(".user_tags_li").click(function()///通过鼠标点击添加标签
	{
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
	
	//添加分类按钮事件
	$(".addTypeBut").live("click",function()
	{
			if($(".addType").is(":visible"))
			{
				$(".addType").toggle(function()
				{	$(".addType").css(
					{
						"display":"none"
					});
				});
			}else
			{
				$(".addType").toggle(function()
				{
					$(".addType").css(
					{
						"display":"block"
					});
				});
			}
	});
	
	//添加按钮事件
	$(".butSubmit").live("click",function()
	{
			var typeName = $("#typeName").val();
			if(""==typeName)
			{
				alert("分类不能为空");
				return false;
			}
			//alert(addLogTypeName);
			$("#typeName").val(""); ///清空文本框的值
			
			//提交到后台保存
			if("" != typeName)
			{
				$.post("logtype/save.do",{"myLogTypeName":typeName},function(data,status)
				{
					$(".seleteType").append("<option selected='selected' value='"+data.id+"'>"+typeName+"</option>");
					///隐藏添加div
					$(".addType").toggle(function()
					{	$(".addType").css(
						{
							"display":"none"
						});
					});
				});	
			}
	});
});


</script>
<style type="text/css">
	*{ margin:0px; padding:0px;}
	body{ background-color:#E2E2E4;}
	h2{ font-family:simsun,宋体; font-size:26px; color:#444444; margin-top:35px; margin-left:33px; height:auto!important;}
	h3{ margin-top:33px; margin-left:33px; margin-bottom:20px; font-style:normal; font-family:宋体;color:#444444;  height:auto!important; display:table;}
	#new_info_main{ width:980px; margin:0px auto; background-color:#F3F3F3; min-height:670px; height:auto!important; border:1px solid #B7B7B8; margin-top:100px; margin-bottom:100px;}
	#new_info_left{ width:720px; float:left; background-color:#fff; border-right:1px solid #EBEBEB;min-height:300px; height:auto!important;}
	#new_info_right{ width:255px; background-color:#F3F3F3; float:right;min-height:645px;; height:auto!important; position:relative; height:100%;}
	#detail{ height:45px; border-bottom:1px solid #EBEBEB; height:auto!important;}
	#new_info_title{ height:90px; height:auto!important;}
	#content{ height:33px;min-height:300px; height:auto!important;}
	#new_info_content{ width:610px; height:290px; margin-left:33px;min-height:290px; height:auto!important;}
	#new_info_button{ height:90px; margin-left:33px; margin-top:30px; height:auto!important; margin-bottom:15px;}
	.add_tags{ width:200px; background-color:#FFF; height:100px; margin:30px 10px 20px 20px;height:auto!important;}
	.a_1{ background-color:#F3F3F3; font-size:12px; color:#999999; padding:10px; margin-top:5px;}
	.user_tags,.all_tags{ width:200px; margin-left:20px; border:1px solid #79A9B1; margin-bottom:20px; min-height:100px; height:auto!important;}
	.user_tags ul{list-style: none;}
	.user_tags_li{ float: left; background-color:#01A2D8;margin-left: 5px; margin-top: 5px;color:#fff; padding: 3px;font-family:微软雅黑;}
	.button{ width:104px; height:40px; background-color:#E2E2E4; border:0px solid #EDEDEF; font-size:16px; font-family:微软雅黑;}
	.button:hover{ background-color:#EEEEEE;}
	.publish{ background-color:#94B600; color:#FFFFFF; float:right; margin-right:30px;}
	.publish:hover{ background-color:#9EC100;}
	.visiable{margin-left:20px; margin-bottom:15px;height:auto!important;}
	.visiable,.visiable select{ width:200px; height:35px; line-height:35px; color:#333; }
	.visiable select{border:1px solid #79A9B1; }
	.visiable select option{ margin-bottom:15px;}
	.left_detail{ width: 200px; margin-left: 20px; color: #999999; margin-bottom: 5px; display: block;}
	.clr{ clear:both;}
</style>
</head>

<body>
	<form action="loginfo/save.do" name="wordform" id="wordform" method="post">
	
	<div id="new_info_main">
    	<div id="new_info_left">
        	<div id="detail">
            	<h2>发布文字</h2>
            </div>
			<div id="new_info_title">
            	<h3>标题</h3>
                <input type="text" id="logtitle" name="logInfo.logTitle" style="border:1px solid #CECECF; width:650px; height:45px; line-height:45px; font-size:26px; font-family:Microsoft YaHei,微软雅黑,tahoma,arial,simsun,宋体; margin-left:33px;" maxlength="50" /> 
            </div>
            <div id="content">
            	 <h3>内容</h3>
                <div id="new_info_content">
                	<script  id="editor" type="text/plain"></script>
					<script type="text/javascript">
				    	//实例化编辑器
				  		///	var editor = new UE.ui.Editor();
				   		// editor.render("new_info_content");
				    	//1.2.4以后可以使用一下代码实例化编辑器
				    	UE.getEditor('editor')
					</script>
                </div>
            </div>
            
            <div id="new_info_button">
            	<input type="button" class="button" value="取　消"/>
              <input style="display:none" type="button" class="button" value="保存草稿"/>
              <input type="button" class="button" value="预　览"/>
              <input type="submit" id="subBut" class="button publish" value="发　布"/>
            </div>
        </div>
        <div id="new_info_right">
        	<div class="add_tags">
            	<div id="feedTags"></div>
               
                <input type="hidden" value="" name="myLogTags" style="width:200px;" id="tags" />
                 <div class="a_1">添加标签，用逗号或者回车号分隔</div>
            </div>
           <div class="user_tags">
           <ul>
           	<s:iterator value="logTagList" id="logtag">
           		<li class="user_tags_li"><s:property value="#logtag.tagName"/></li>
           	</s:iterator>
           	</ul>
			<div class="clr"></div>
           </div>
			<div class="clr"></div>
            <span class="left_detail">给标签分分类</span>
             <div class="visiable">
            	  <select name="tagid" id="tagid">
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
				<div style="border:1px solid #79A9B1;height: 35px;width: 200px;margin-top: 5px;margin-bottom: 5px;">
            	 <select name="logTypeId" id="select" class="seleteType" style="width:125px;border:0px solid #79A9B1;">
              		<s:iterator value="logTypeList" id="logType">
								<option value="<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></option>
					</s:iterator>
                 </select>
				<input  type="button" value="添加分类" class="addTypeBut" style="color:#FFFFFF;background-color:#94B600;font-size:16px; font-family:微软雅黑;float: right;height: 35px; width: 67px;line-height: 35px;border: 0px solid;"/>
           		</div>
			 <div class="addType" style="display: none;border:1px solid #79A9B1;height: 30px;width: 200px;margin-top: 5px;">	
			<input type="text" value="" id="typeName" name="typeName" style="font-size: 16px;border:0px solid #79A9B1;height: 30px; width: 150px;line-height: 30px;float: left;"/>
			<input type="button" class="butSubmit" value="添加" style="color:#FFFFFF;margin-left: 0px;background-color:#94B600; border:0px solid #79A9B1; font-size:16px; font-family:微软雅黑;height: 30px; width: 50px;line-height: 30px;"/>
			</div>
			</div>
            <div style="width:200px; height:35px; line-height:35px; color:#333;margin-left:20px; margin-bottom:15px;">同步到腾讯微博<input type="checkbox" id="syswb"/><input type="button" onclick="logout()" value="logout"/></div>
             <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div> 
        </div>
        <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div> 
    </div>
</form>
</body>

</html>
