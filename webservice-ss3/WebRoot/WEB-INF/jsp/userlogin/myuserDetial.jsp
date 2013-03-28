<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/common.jsp" %>
<%@ include file="/top_navi.jsp" %>
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
		*{margin: 0px;padding: 0px;}
		ul{list-style: none;}
		.detail_main li{float: none;}
		h2{line-height: 100px;margin-top: 33px;}
		.top{position: absolute;width: 100%;height: 100px;top: 50px;left: 0px;border-bottom: 0px solid E1E1E1; margin-bottom: 40px;clear: both;}
		.head_img{width: 100px;height: 100px;margin-right: 5px;border-right: 1px solid red;}
		.detail_main{margin: 0px auto;width: 820px;background-color: #eee;padding-left: 80px;padding-top: 30px;padding-bottom: 20px;}
		.span{width: 80px;height: 35px;line-height: 35px;display: block;float: left;text-align: right;margin-right: 10px;margin-top: 5px;}
		.input{width: 260px; height: 35px;line-height: 35px;border: 1px solid green;}
		select{width: 120px;border: 1px solid green;height: 33px;line-height: 33px;}
	</style>
	<script type="text/javascript">

		////选择用户兴趣
		function onSelectInterest()
		{
			var interest = "<s:property value='myUserDetial.interests' escape='false'/>";
			if("" == interest)
			{
				return false;
			}
			var array = interest.split(",");
			for(var i=0; i<array.length; i++)
			{
				for(var j=0; j<$(".myinterest").length; j++)
				{
					if($(".myinterest:eq("+j+")").val().trim()==array[i].trim())
					{
						$(".myinterest:eq("+j+")").attr("checked","checked");
						break;
					}
				}
			}
		}

		///根据用户的选择改变要上传的头像
		function setUserHeadImg(src)
		{
			$("#user_headimg").attr("src",src);
		}
	
		$(document).ready(function()
		{	
			////选择用户兴趣
			onSelectInterest();
			
			////页面一加载的时候，就查找省份
			$.ajax(
			{
				url:"address/getProvince.do",
				success:function(data)
				{
					var provinceData = "<option value='0'>--请选择--</option>";
					var provice = '<s:property value="myUserDetial.address.province"/>';
					for(var i = 0; i < data.length; i++)
					{
						if(provice==data[i].provinceID)
						{
							provinceData+="<option selected='selected' value="+data[i].provinceID+">"+data[i].province+"</option>"
						}else
						{
							provinceData+="<option value="+data[i].provinceID+">"+data[i].province+"</option>"
						}
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
			////用户选中头像的时候，设置头像到form中
			$(".head_img").click(function()
			{
				//alert($(this).attr("src"));
				setUserHeadImg($(this).attr("src"));
			});

			$("#uploadHeadImg").click(function()
			{
				var id = '<s:property value="userHeadImg.id"/>';
				var imgLocation = $("#user_headimg").attr("src");
				//alert(imgUrl);
				$.post("headimg/update.do",{"id":id,"imgLocation":imgLocation},function(data,status){alert(status)});
			});
		});
		
	</script>
  </head>
  
  <body>
  		<div class="top">
  			<center>
  				<h2>修改用户资料</h2>
  			</center>
  		</div>
  		<div class="detail_main">
		<div style="height: 120px;min-height:120px;height:auto!important; width:600px;border:1px solid green;margin-bottom: 20px;padding: 20px;">
		修改头像<br/>
		<img src="images/default_head/001.png" class="head_img" alt="headImg"/>
		<img src="images/default_head/002.png" class="head_img" alt="headImg"/>
		<img src="images/default_head/003.png" class="head_img" alt="headImg"/>
		<img src="images/default_head/004.png" class="head_img" alt="headImg"/>
		<img src="images/default_head/005.png" class="head_img" alt="headImg"/><br/>
		<div style="width:600px;border-top:1px solid green;position:relative;margin-top: 5px;">
			<img alt="headImg" src="<s:property value='userHeadImg.imgLocation'/>" id="user_headimg" style="width: 100px;height: 100px;"/>
			<span style="float:right;position:absolute;right:20;bottom:5;">
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
						//handleCallBack(arg);
						setUserHeadImg(arg[0].src);
					});
				});
			</script>
			<input type="button" value="选择头像" onclick="upImage();" id="selectHeadImg" />
			<input type="button" value="上传" id="uploadHeadImg" /></span>
		</div>
		</div>
		
		<form action="myUserDetial/update.do" method="post">
			<input type="hidden" value="${myUserDetial.id}" name="myUserDetial.id">
			<input type="hidden" value="${myUserDetial.address.id}" name="addressid">
			<ul>
				<li><span class="span">昵称：</span><input type="text" class="input" name="myUserDetial.username" value="${myUserDetial.username}" readonly="readonly"/></li><br/>
				<li><span class="span">生日：</span><input type="text" class="input" onClick="WdatePicker()" name="myUserDetial.birthday" value="${myUserDetial.birthday}"/></li><br/>
					<li>
						<span class="span">感情状态：</span>
						<select name="myUserDetial.loveStatue">
							<option value="1" <s:if test="myUserDetial.loveStatue==1">selected="selected"</s:if>>单身</option>
							<option value="2" <s:if test="myUserDetial.loveStatue==2">selected="selected"</s:if>>恋爱中</option>
							<option value="3" <s:if test="myUserDetial.loveStatue==3">selected="selected"</s:if>>已婚</option>
							<option value="4" <s:if test="myUserDetial.loveStatue==4">selected="selected"</s:if>>已订婚</option>
							<option value="5" <s:if test="myUserDetial.loveStatue==5">selected="selected"</s:if>>离异</option>
							<option value="6" <s:if test="myUserDetial.loveStatue==6">selected="selected"</s:if>>分居</option>
							<option value="7" <s:if test="myUserDetial.loveStatue==7">selected="selected"</s:if>>保密</option>
						</select>
					</li><br/>
				
					<li>
						<span class="span">用户血型：</span>
						<select name="myUserDetial.bloodType">
							<option value="1" <s:if test="myUserDetial.bloodType==1">selected="selected"</s:if>>A</option>
							<option value="2" <s:if test="myUserDetial.bloodType==2">selected="selected"</s:if>>B</option>
							<option value="3" <s:if test="myUserDetial.bloodType==3">selected="selected"</s:if>>O</option>
							<option value="4" <s:if test="myUserDetial.bloodType==4">selected="selected"</s:if>>AB</option>
							<option value="5" <s:if test="myUserDetial.bloodType==5">selected="selected"</s:if>>其他</option>
						</select>
					</li><br/>
				<li><span class="span">公司名称：</span><input type="text" class="input" name="myUserDetial.companyName" value="${myUserDetial.companyName }"/></li><br/>
				<li><span class="span">公司地址：</span><input type="text" class="input" name="myUserDetial.companyAddress" value="${ myUserDetial.companyAddress}"/></li><br/>
				
					<li>
						<span class="span">详细地址：</span>
						<input type="text" class="input" name="myUserDetial.addressDetial" value="${ myUserDetial.addressDetial}"/>
					</li><br/>
				<li><span class="span">邮编：</span><input class="input" type="text" name="myUserDetial.zcode" value="${myUserDetial.zcode }"/></li><br/>
				<li><span class="span">联系电话：</span><input class="input" type="text" name="myUserDetial.phone" value="${myUserDetial.phone }"/></li><br/>
				<li>
						<div id="address">
							<span class="span">所在地：</span>
							<select id="province" name="province">
								<option selected="selected">省份</option>
							</select>
							<select id="city" name="city">
								<s:text name='myCity.city!=""'><option selected="selected" value="<s:property value='myCity.cityID'/>"><s:property value="myCity.city" escape="false"/></option></s:text>
								<s:else><option selected="selected">城市</option></s:else>
							</select>
							<select id="area" name="area">
								<s:text name='myArea.area!=""'><option selected="selected" value="<s:property value='myArea.areaID'/>"><s:property value="myArea.area" escape="false"/></option></s:text>
								<s:else><option selected="selected">城区</option></s:else>
							</select>
						</div>
				</li><br/>
				
				<li>
					<span class="span">工作：</span>
					<s:select list="jobList" listKey="jobName" listValue="jobName" name="myUserDetial.jobName"></s:select>
				</li><br/>
				
				<li>
					<span class="span">兴趣爱好：</span>
					<div style="margin-right:165px;width: 550px;margin-left: 13px;float: right;">
					
					<s:iterator value="interestList" id="interest" status="st">
						<span style="display:inline-block;width:120px;"><input type="checkbox" value="${interest.interestName}" class="myinterest" name="myUserDetial.interests"/><s:property value="#interest.interestName"/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="(#st.count%3)==0">
							<br/>
						</s:if>
					</s:iterator>
					</div>
				</li><br/>
			</ul>
			<div style="clear: both;"></div>
			<s:if test='myUserDetial.interests==""'>
			<input type="submit" style="background-color:green;width: 55px;height: 33px;line-height: 33px;border: 1px solid #eee;" value="提交"/>
			</s:if>
			<s:else>
			<input type="submit" style="background-color:#94B600;width: 70px;height: 35px;line-height: 35px;border: 1px solid #eee;" value="更新"/>
			</s:else>
		</form>
	</div>
	<s:debug></s:debug>
	  </body>
</html>
