<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_navi.jsp" %>
<%@ include file="/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>相册分类列表</title>
    
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
	</style>
	
  </head>
  
  <body>
  	<div style="width: 980px;margin: 0px auto;">
  	<a href="albumtype/gotoAlbumTypeList.do">返回</a>相册分类列表
  	<br/>
  	<div id="picUpload" style="width: 980px;margin: 0 auto;display: none;">
  	</div>
  	 <script type="text/javascript">

		function handleCallBack(arg)
		{
			var html="";
			for(var i=0; i<arg.length; i++)
			{
				html+="<div class='upload' style='border:1px solid green; width:360px;'>";
				html+="<img src='"+arg[i].src+"' width='100px' height='100px'/>";
				html+="<br/>名称：<input type='text' class='picName' name='picName' />";
				html+="<br/>图片描述：<input type='text' class='picDetial' name='picDetial' />";
				html+="</div><br/>"	;
				
			}
			//alert(html);
			html+="<input type='button' id='picPost' value='上传'/>";
			
			$("#picUpload").css("display","block").html(html);
		}

		var myEditorImage;
		var d;
		function upImage()
		{
			d = myEditorImage.getDialog("insertimage");
			d.render();
			d.open();
		}
		myEditorImage = new UE.ui.Editor();
		myEditorImage.render('myEditorImage');
		myEditorImage.ready(function()
		{
			myEditorImage.setDisabled();
			myEditorImage.hide();
			myEditorImage.addListener('beforeInsertImage',function(t,arg)
			{
				handleCallBack(arg);
			});
		});	
    	
    </script>
  	<s:if test="albumList.size==0">
    	暂无数据，<a href="javascript:upImage();">上传</a>
    </s:if>
   	<s:else>
   		<s:iterator value="albumList" id="album">
   			<div style="width: 100px;height: 100px;float: left;margin-left: 15px;"><img width="100px" height="100px" src="<s:property value=' #album.albumLocation'/>"/></div>
   		</s:iterator>
   	</s:else>
   	<div style="clear: both;"></div>
   	<a href="javascript:upImage();">上传</a>
   <script type="text/javascript">
		$("#picPost").live("click",function()
		{
			var albumTypeId = <s:property value='albumTypeId'/>;
			$(".upload").each(function()
			{
				var albumLocation = $(this).children("img").attr("src");
				var picName=$(this).children(".picName").val();
				var picDetial=$(this).children(".picDetial").val();
				$.ajax(
				{
					type:"POST",
					async:false,///改为同步提交的方式
					url:"album/saveAlbum.do",
					data:{"picName":picName,"picDetial":picDetial,"albumLocation":albumLocation,"albumTypeId":albumTypeId},
					success:function()
					{
						$(this).addend("<img src='images/ok.png'/>");
					}
				});
				
				//$(this).remove();///将已经提交的表单移除
			});
			window.location.reload();
		});
	</script>
	<s:debug></s:debug>
	</div>
  </body>
</html>
