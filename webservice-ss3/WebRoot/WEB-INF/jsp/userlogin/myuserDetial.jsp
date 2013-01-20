<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myuserDetial.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		ul{list-style: none;}
	</style>
	<script type="text/javascript">
		$(document).ready(function()
		{	
			////页面一加载的时候，就查找省份
			$.ajax(
			{
				url:"address/getProvince.do",
				success:function(data)
				{
					var provinceData = "<option value='0'>--请选择--</option>";
					for(var i = 0; i < data.length; i++)
					{
						provinceData+="<option value="+data[i].provinceID+">"+data[i].province+"</option>"
					}
					
					$("#province").html(provinceData);
				}
			});
			
			////省份下拉选择的时候，触发事件
			$("#province").change(function()
			{
				var provinceID = $("#province option:selected").val();
				if(0 == provinceID)
				{
					$("#city").html("<option selected='selected'>城市</option>");
					return;
				}
				$.post(
					"address/getCity.do",
					{"provinceID":provinceID},
					function(data)
					{
						var cityData = "<option value='0'>--请选择--</option>";
						for(var i = 0; i < data.length; i++)
						{
							cityData+="<option value="+data[i].cityID+">"+data[i].city+"</option>"
						}
						$("#area").html("<option selected='selected'>城区</option>");
						$("#city").html(cityData);
					}
				);
			});
			
			////城市下拉选择的时候，触发事件
			$("#city").change(function()
			{
				var cityID = $("#city option:selected").val();
				if(0 == cityID)
				{
					$("#area").html("<option selected='selected'>城区</option>");
					return;
				}
				$.post(
					"address/getArea.do",
					{"cityID":cityID},
					function(data)
					{
						var areaData = "<option value='0'>--请选择--</option>";
						for(var i = 0; i < data.length; i++)
						{
							areaData+="<option value="+data[i].areaID+">"+data[i].area+"</option>"
						}
						$("#area").html(areaData);
					}
				);
			});
		})
	</script>
  </head>
  
  <body>
   	用户详细登录页面
	<s:if test="null == myUserDetial.username || '' == myUserDetial.username">
		请登录
	</s:if>
	<s:else>
			用户名：<s:property value="myUserDetial.username"/>
		用户的账号：<s:property value="myUserDetial.user.account"/>
		<hr>
		<form action="myUserDetial/update.do" method="post">
			<ul>
				<li>昵称：</li><li><input type="text" name="myUserDetial.username" value="${myUserDetial.username}" readonly="readonly"/></li><br/>
				<li>生日：</li><li><input type="text" name="myUserDetial.birthday" value="${myUserDetial.birthday}"/></li><br/>
				<li>感情状态：</li>
					<li>
						<select name="myUserDetial.loveStatue">
							<option value="1">单身</option>
							<option value="2">恋爱中</option>
							<option value="3">已婚</option>
							<option value="4">已订婚</option>
							<option value="5">离异</option>
							<option value="6">分居</option>
							<option value="7">保密</option>
							
						</select>
					</li><br/>
				<li>用户血型：</li>
					<li>
						<select name="myUserDetial.bloodType">
							<option value="1">A</option>
							<option value="2">B</option>
							<option value="3">O</option>
							<option value="4">AB</option>
							<option value="5">其他</option>
						</select>
					</li><br/>
				<li>公司名称：</li><li><input type="text" name="myUserDetial.companyName" value="${myUserDetial.companyName }"/></li><br/>
				<li>公司地址：</li><li><input type="text" name="myUserDetial.companyAddress" value="${ myUserDetial.companyAddress}"/></li><br/>
				<li>详细地址：</li>
					<li>
						<input type="text" name="myUserDetial.addressDetial" value="${ myUserDetial.addressDetial}"/>
					</li><br/>
				<li>邮编：</li><li><input type="text" name="myUserDetial.zcode" value="${myUserDetial.zcode }"/></li><br/>
				<li>联系电话：</li><li><input type="text" name="myUserDetial.phone" value="${myUserDetial.phone }"/></li><br/>
				<li>所在地：</li><li>
						<div id="address">
							<select id="province" name="province">
								<option selected="selected">省份</option>
							</select>
							<select id="city" name="city">
								<option selected="selected">城市</option>
							</select>
							<select id="area" name="area">
								<option selected="selected">城区</option>
							</select>
						</div>
				</li><br/>
				<li>工作：</li>
				<li>
					<s:select list="jobList" listKey="jobName" listValue="jobName" name="myUserDetial.jobName"></s:select>
				</li><br/>
				<li>兴趣爱好：</li>
				<li>
					<s:iterator value="interestList" id="interest" status="st">
						<span style="display:inline-block;width:120px;"><input type="checkbox" value="${interest.interestName}" name="myUserDetial.interests"/><s:property value="#interest.interestName"/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="(#st.count%3)==0">
							<br/>
						</s:if>
					</s:iterator>
				</li><br/>
			</ul>
			
			<input type="submit" value="提交"/>
		</form>
	</s:else>
  </body>
</html>
