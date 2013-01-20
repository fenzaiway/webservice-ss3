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
    
    <title>My JSP 'pageTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
 	 <script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
	 <script type="text/javascript">
	 	var pageSize = 0;

	    var totalCount = 0; //总记录数
	
	    var startIndex = 0;
	    
	    var nextIndex = 0;
	    
	    var previousIndex = 0;
	    
	    var totalPageCount = 0;
	    
	    var currentPageNo = 0;
	    
	    var hasNextPage = false;
	    
	    var hasPreviousPage = false;
	    
	    var lastIndex = 0;
	    
	    /*显示评论*/
	    function showComment(data)
	    {
	    	var htmlText = "";
	    	for(var i = 0; i < data.length; i++)
	    	{
	    		htmlText += data[i].username + "|" + i+"楼&nbsp;&nbsp;&nbsp;" + data[i].commentTime + "<br/>" + data[i].commentContent + "|回复<br/><hr width='20%' align='left' style= 'border:1 dotted green' />";
	    	}
	    	$("#comment").empty().html(htmlText);
	    }
	    
	    /*显示日志列表*/
	    function showLogInfo(data)
	    {	//alert(data.length);
	    	var htmlText = "";
	    	for(var i = 0; i < data.length; i++)
	    	{
	    		htmlText += data[i].logTitle+"<br/>"+data[i].logText+"<br/><hr width='20%' align='left' style= 'border:1 dotted green' />"
	    	}
	    	$("#comment").empty().html(htmlText);
	    }
	    
		$(function()
		{
			///alert("aa");
			myRequest(0);
		});
		
		function myRequest(startIndex)
		{
			$.post("loginfo/pageTest.do",{"startIndex":startIndex},function(data)
			{
				
				var pager = data.pager;
				//showComment(data.items);
				showLogInfo(data.items);
				$("#page").empty();
				$("#page").html(pager);
				
			});
		}
		
		///首页
		function indexPage(startIndex)
		{
			myRequest(startIndex);
		}
		////尾页
	 	function endPage(startIndex)
	 	{
	 		myRequest(startIndex);
	 	}
		///上一页
		function prePage(startIndex)
		{
			myRequest(startIndex);
		}
		
		///下一页
		function nextPage(startIndex)
		{
			myRequest(startIndex);
		}
		
</script>
	</head>
  
  <body>
    <h3>测试异步分页</h3>
	<div id="comment">
	</div>
	<div id="page">
		
	</div>
	<s:debug></s:debug>
  </body>
</html>
