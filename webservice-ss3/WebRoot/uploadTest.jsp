<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uploadTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <link rel="stylesheet" href="<%=basePath%>css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery.uploadify.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>editor/editor_config.js"></script>
  <script type="text/javascript" src="<%=basePath%>editor/editor_all.js"></script>
  <script type="text/javascript">
  	$(function()
  	{
  		$("#uploadify").uploadify(
  		{
  			'swf':'<%=basePath%>media/uploadify.swf',
  			'folder':'uploads',
  			'queueID':'fileQueue',
  			'queueSizeLimit':10,
  			'fileTypeDesc':'rar文件或者jpg文件',
  			'fileTypeExts':'*.rar;*.zip;*.jpg;*.png;*.doc',
  			'auto':false,
  			'multi':true,
  			'removeCompleted':true,
  			'simUploadLimit':2,
  			'buttonText':'浏览',
  			'method':'post',
  			'overrideEvents':["onCancel"]
  		});
  	});
  </script>
  
  </head>
  
  <body>
    <a href="">文件上传</a>
    <div id="fileQueue"></div>
    <input type="file" name="uploadify" id="uploadify" multiple="true"/>
    <p>
    	<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a><br>
    	<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所以上传上传</a><br>
    	
    	
    </p>
    <br>
    通过百度Ueditor测试
    
    <script type="text/javascript">
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
    			var html="";
    			for(var i=0; i<arg.length; i++)
    			{
    				html+="<div class='upload' style='border:1px solid green; width:300px;'>";
    				html+="<img src='"+arg[i].src+"' width='100px' height='100px'/>";
    				html+="名称：<input type='text' name='picName' />";
    				html+="图片描述：<input type='text' name='picDetail' />";
    				html+="</div><br/>"	;
    				
    			}
    			//alert(html);
    			html+="<input type='button' id='picUpload' value='上传'/>";
    			
    			$("#pic").html(html);
    		});
    	});
    	
    </script>
    <div id="pic"><div style="border:1px solid green; width:300px;" class="upload"><img width="100px" height="100px" src="/ss3/editor/jsp/upload/20130208/91811360326477500.jpg">名称：<input type="text" class="picName" name="picName" value="">图片描述：<input type="text" name="picDetail"></div><br><div style="border:1px solid green; width:300px;" class="upload"><img width="100px" height="100px" src="/ss3/editor/jsp/upload/20130208/66571360326478445.jpg">名称：<input type="text" class="picName" name="picName" value="">图片描述：<input type="text" name="picDetail"></div><br><input type="button" value="上传" id="picUpload"></div>
    <input type="text" name="a" id="aabbcc" value=""/>
	<input type="button" value="上传图片" id="button" onclick="upImage();">  
	<hr>
	SpringMVC文件上传测试
	<form action="fileUpload/upload.upload" method="post" enctype="multipart/form-data" >
		<input type="file" name="file" />
		<input type="submit" value="上传">
	</form>  
	<hr>
	多文件上传
	<form action="fileUpload/upload4.upload" method="post" enctype="multipart/form-data" >
		<input type="file" name="file" />
		<input type="file" name="file" />
		<input type="file" name="file" />
		<input type="submit" value="上传">
	</form>  
	<script type="text/javascript">
		$("#picUpload").live("click",function()
		{
			$(".upload").each(function()
			{
				var src = $(this).children("img").attr("src");
				var picName=$(this).children(".picName").val();
				alert(picName);
				alert(src);
			});
		});
	</script>
  </body>
</html>
