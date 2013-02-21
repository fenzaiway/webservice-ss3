<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/top_shortup.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">
    
    <title>My JSP 'logManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		div{margin-buttom: 5px;}
		img {
			width: 150px;
			height: 150px;
		}
	</style>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commons.js"></script>
  <link rel="stylesheet" href="<%=basePath%>css/commons.css" type="text/css"></link></head>
  
  <body>
	<div style="margin: 0 auto;width: 980px; height: auto!important;min-height: 100%;">
	<!-- 操作状态提示 -->
	<div id="prompt" style="display: none;"></div>
	<c:if test="${zoneuser==myusername}"><a href="zone/<s:property value='myusername'/>">个人中心</a><br/></c:if>
    日志管理（可以在这里发表和管理日志）
	<ul>
		<li>日志分类<c:if test="${zoneuser==myusername}"><a href="">管理</a></c:if>
		<br/>	
			<s:if test="logTypeList.isEmpty()">暂无数据
					<c:if test="${zoneuser==myusername}">
						添加<a href="javascript:void(0);" class="aaddLogType">分类</a>
					</c:if></s:if>
			<s:else>
				分类列表：<br/>
				<div id="logTypeList">
				<table style="width: 100%;" id="logTypeTable" cellpadding="1">
				<s:iterator value="logTypeList" id="logType">
					<tr bgcolor="#fff" logTypeId="<s:property value='#logType.id'/>">
						<td bgcolor="#CDE391"><s:property value="#logType.logTypeName"/></td>
						<c:if test="${zoneuser==myusername}">
							<s:if test="#logType.isDefaultLogType==1">
								<td  bgcolor="#CDE391" align="right" colspan="2">&nbsp;（此分类目录不支持编辑和删除）</td>
							</s:if>
							<s:else>
							<td  bgcolor="#CDE391" align="right"><a href='javascript:void(0);' class="logTypeEdit">编辑</a></td><td  bgcolor="#CDE391"><a href="javascript:void(0);"  class="logTypeDel">删除</a></td>
							</s:else></c:if>
					</tr>
				</s:iterator>
				</table>
				</div>
				<div id="editLogType" style="display: none;"><input type='text' value="" id="logTypeName" logTypeId="" name="logTypeName"/><input type='button' value='确定' id='updateOk' name="updateOk" width='50px'/><input type='button' value='取消' id='editCancel'/></div>
				<div id="addLogType" style="display: none;"><input type='text' value="" id="addLogTypeName" name="addLogTypeName"/><input type='button' value='确定' id='addOk' name="addOk" width='50px'/><input type='button' value='取消' id='addCancel'/></div>
				<br/>
					<c:if test="${zoneuser==myusername}">
						添加<a href="javascript:void(0);" class="aaddLogType">分类</a>
					</c:if>
			</s:else>
			
		</li>
		<hr/>
		<li>
			<c:if test="${zoneuser==myusername}">
				<a href="loginfo/newLogInfo.do">写日志</a>
			</c:if>
			<br/>
			日志列表<br/>
			<s:if test="logInfoList.isEmpty()">暂无数据</s:if>
			<s:else>
				
				<s:iterator value="logInfoList" id="logInfo">
					<div style="background:#fff;width: 900px;font-size: 14px;position:relative;margin-top: 20px;height: auto!important;height: 200px;min-height: 200px;">
						<div style="padding-top: 10px;"><a href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#logInfo.id'/>"><s:property value="#logInfo.logTitle"/></a></div>
						<div><s:property value="#logInfo.logPublishTime"/></div>
							<div>
								<s:property value="#logInfo.logText" escape="false"/>
							</div>
							<div style="height: 25px;position:absolute;right:20;bottom:25;"><a style="float: right;" href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#logInfo.id'/>">查看更多</a></div>
						<div style="position:absolute;right:20;bottom:5;">
							<a style="padding-left: 10px;" href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#logInfo.id'/>">阅读</a>（<s:property value="#logInfo.logVisits.size()"/>）|
							<a style="padding-left: 10px;" href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#logInfo.id'/>">评论</a>（<s:property value="#logInfo.logComments.size()"/>）|
							<c:if test="${zoneuser==myusername}"><a style="padding-left: 10px;" href="loginfo/gotoLogInfoUpdate.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#logInfo.id'/>">编辑</a>&nbsp;&nbsp;|<a style="padding-left: 10px;" href="javascript:deleteLogInfo(<s:property value='#logInfo.id'/>);">删除</a></c:if>
						</div>
						<div style="clear:both;margin: 1px;"></div>
					</div>
				</s:iterator>
				
				<br/>
				<div id="page" style="margin-top: 10px;">
					<s:property value="paginationSupport.pageToolBar" escape="false"/>
				</div>
			</s:else>
		</li>
		<c:if test="${zoneuser==myusername}"><li><a href="">草稿箱</a></li></c:if>
		<c:if test="${zoneuser==myusername}"><li><a href="">设置</a></li></c:if>
	</ul>
	
	<script type="text/javascript">
		
		////删除日志
		function deleteLogInfo(logId)
		{
			var flag = false;
			if(window.confirm("你确定要删除吗？删除的日志可以到回收站恢复"))
			{
				var url = "loginfo/delete.do?zoneuser=${zoneuser }&logInfoid="+logId;
				window.location=url;
				flag = true;
			}
			return flag;
		}
		var changTd;
		var logTypeName;
		/////获取分类名称，将分类的名字设置到input中
		function getValue(target)
		{
			changTd = target.parents("tr:first").find("td:first");
			logTypeName = target.parents("tr:first").find("td:first").text();
			var logTypeId = target.parents("tr:first").attr("logTypeId");
			$("#editLogType").css("display","block");
			$("#logTypeName").val(logTypeName);
			$("#logTypeName").attr("logTypeId",logTypeId);
		}
		////1秒后消失
		function out()
		{ 
			$('#prompt').fadeOut({top:'0'}, 500, function()
			{ 
				$(this).css({display:'none', top:'-100px'}); 
			});
		}
		
		////提示操作成功
		function showSuccess(showText)
		{
			$('#prompt').text(showText).css({display:'block', top:'-100px'}).animate({top: '+100'}, 500, function()
			{ 
				setTimeout(out, 1000); 
			});
		}
		
		////执行删除
		function exeDelete(target)
		{
			if(window.confirm('你确定要删除吗？删除后该分类目录将移动到“个人日志”分类下')){
                 	//alert("确定");
                 	//return true;
                	 var logTypeId = target.parents("tr:first").attr("logTypeId");
					///$(this).parents("tr:first").remove();
					//alert(logTypeId);
					$.post("logtype/delete.do",{"myLogTypeId":logTypeId},function(data,status)
					{
						/////成功删除，则将该行删除
						if("success"==status)
						{
							showSuccess("分类成功删除");
							target.parents("tr:first").remove();
						}
					})
              	}else{
                 //alert("取消");
                 return false;
             	}
		}
		
		$(function()
		{
			backToTop();
			////显示编辑
			$(".logTypeEdit").click(function()
			{
					getValue($(this));			
			});
			
			//显示分类
			$(".aaddLogType").click(function()
			{
				$("#addLogType").css("display","block");
			});
			
			////取消添加addCancel
			$("#addCancel").click(function()
			{
				$("#addLogType").css("display","none");
			});
			
			//取消编辑
			$("#editCancel").click(function()
			{
				$("#editLogType").css("display","none");
			});
			
			
			////删除分类
			$(".logTypeDel").click(function()
			{
				//alert('ss');
				exeDelete($(this));
				
			});
			
			///点击添加确定
			$("#addOk").click(function()
			{
				var addLogTypeName = $("#addLogTypeName").val();
				if(""==addLogTypeName)
				{
					alert("分类不能为空");
					return false;
				}
				//alert(addLogTypeName);
				///隐藏添加div
				$("#addLogType").css("display","none");
				//提交到后台保存
				if("" != addLogTypeName)
				{
					$.post("logtype/save.do",{"myLogTypeName":addLogTypeName},function(data,status)
					{
						/*var trHtml= "<tr bgcolor='#fff' logTypeId="+data.id +"><td bgcolor='#CDE391'>"+ data.logTypeName +"</td>"+
						"<td bgcolor='#CDE391' align='right'><a href='javascript:void(0);' class='logTypeEdit'>编辑</a></td>"+
						"<td bgcolor='#CDE391'><a href='javascript:void(0);'  class='logTypeDel'>删除</a></td></tr>";*/
						var trHtml = $("#logTypeList tr:eq(1)").clone(true); /////通过克隆后就不需要再注册事件
						trHtml.attr("logTypeId",data.id);
						trHtml.find("td:first").text(data.logTypeName);
						///清空input的值
						$("#addLogTypeName").val("");
						////注册编辑事件
						/*$(".logTypeEdit").live("click",function()
						{
							getValue($(this));
						});
						//注册删除事件
						$(".logTypeDel").live("click",function(event)
						{
							//exeDelete($(this));
							event.preventDefault();
						});*/
						showSuccess("分类添加成功");
						///将值添加到表格的末尾
						$("#logTypeTable").append(trHtml);
					});	
				}
			});
			
			////点击编辑确定
			$("#updateOk").click(function()
			{
				var name = $("#logTypeName").val();
				var id = $("#logTypeName").attr("logTypeId");
				if("" == name)
				{
					alert("分类不能为空");
					return false;
				}
				////输入的内容跟设置的内容一样，就不提交
				if(name==logTypeName)
				{
					$("#editLogType").css("display","none");
				}else
				{
					changTd.text(name);
					/////异步提交，后台更新
					$.post("logtype/update.do",{"myLogTypeName":name,"myLogTypeId":id},function(data,status)
					{
						//alert(status);
						if("success" == status)///成功后隐藏div
						{
							$("#editLogType").css("display","none");///将div隐藏
							showSuccess("操作成功");
						}
					});
				}
			});
		});
		
	</script>
</div>
  </body>
</html>
