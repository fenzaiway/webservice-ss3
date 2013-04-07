<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/top_shortup.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>最新关注内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="<%=basePath%>css/commons.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/userzone.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/tagfeed.css">
	<link rel="stylesheet" href="<%=basePath%>css/jquery.bigautocomplete.css" type="text/css" />

	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/focusBlur.js"></script>
<script type="text/javascript" src="<%=basePath%>js/subTag.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.bigautocomplete.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commons.js"></script>
	<SCRIPT type="text/javascript">
		var username;
		//鼠标移动到用户头像，显示用户详细资料
		function headimgHover()
		{
			$(".headImg").live("mouseover",function(e)
			{  
				/*var x = e.pageX;
				var y = e.pageY;
				//alert($(this).height()+"--" + $(this).width());
				x=(x+$(this).width()+10);
				y=(y+$(this).height()-6);
				var showDivHtml = "<div id='userdetail' style='width:80px;height:100px;background-color:#fff;position:absolute;left:"+x+"px;top:"+y+"px;'>用户信息</div>"
				$("body").append(showDivHtml);*/
				$(this).find(".imgdiv").css("display","block");
				
			}).live("mouseout",function()
			{
				$(this).find(".imgdiv").css("display","none");
				//$("#userdetail").remove();
			});
		}
		$(function()
		{
			username = '<s:property value="myusername"/>';
			myusername = '<s:property value="myusername"/>';
			headimgHover();
			if(""==myusername)
			{
				
			}else
			{
				init();
				searchTag(); ///搜索标签
			}
			
			backToTop();
			tagComplete();
			//alert(username);
			$("#tag").FocusBlur();////设置当文本框获得焦点的时候，文字消失
			
			//用户订阅标签
			$(".subBut").live("click",function()
			{
				var tagName = $("#tagName").text()
				$.post("ajax/tag/savePageUserSubTag.do",{"myTagName":tagName,"username":myusername},function(data,status)
				{
					if("success"==status)
					{
						////订阅成功
						loadUserTags();///重新加载用户订阅的标签
						var butHTML = "<input type='button' class='unSubBut' tagid="+data.status+" style='margin-top: 25px; margin-left:15px;width:70px; height:35px; line-height:35px;background-color:#F4F4F4; color:#B4B4B4;border:1px solid #CFCFCF;font-size:16px; font-family:微软雅黑;' value='已订阅'/>";
						$("#tagSubBut").empty().html(butHTML);
					}
				});
			});
			
			var unSubButCSS="";
			//鼠标移动该表按钮样式
			$(".unSubBut").live("mouseover",function()
			{
				var style="margin-top: 25px; margin-left:15px;width:70px; height:35px; line-height:35px;background-color:#F4F4F4; color:#3FA7CB;border:1px solid #3FA7CB;font-size:16px; font-family:微软雅黑;";
				unSubButCSS = $(this).attr("style");
				$(this).attr("style",style).val("取消订阅");
				
			}).live("mouseout",function()
			{
				$(this).attr("style",unSubButCSS).val("已订阅");
			});
			
			///取消订阅
			$(".unSubBut").live("click",function()
			{
				///alert($(this).attr("tagid"));
				var tagid = $(this).attr("tagid"); ///获取这个标签的Id
				userCancelSubTag(tagid); //取消订阅标签
				
			});
			
			
			
			$(".imgdiv a").live("click",function()
			{
				var isattention = $(this).attr("isattention");
				var tousername = $(this).attr("username");
				////取消喜欢
				if(1==isattention)
				{
					
					//alert("取消关注" + tousername);
					
					$.post("attention/updateAttention.do",{"toUserName":tousername},function(data,status)
					{
						$(".imgdiv a").each(function() 
						{
							//
							var name11 = $(this).attr("username");
							var isAttention11 = $(this).attr("isattention");
							if(tousername==name11 && isattention==isAttention11) ////如果当前加载的内容是同一个人的话，那么当取消关注的时候，所有的所有的内容都需要修改
							{
								$(this).parent("li").html("<a username="+tousername+" isattention=0 href='javascript:void(0);'>加关注</a>");
							}
						});
					});
				}else if(0 == isattention) //加关注
				{
					$.post("attention/addAttention.do",{"toUserName":tousername},function(data,status)
					{
						$(".imgdiv a").each(function() 
						{
							//
							var name11 = $(this).attr("username");
							var isAttention11 = $(this).attr("isattention");
							if(tousername==name11 && isattention==isAttention11) ////如果当前加载的内容是同一个人的话，那么当取消关注的时候，所有的所有的内容都需要修改
							{
								$(this).parent("li").html("<a username="+tousername+" isattention=1 href='javascript:void(0);'>取消关注</a>");
							}
						});
					});
					
					
				}
				//alert($(this).attr("username"));
				
				/*$.post("attention/addAttention.do",{"toUserName":tousername},function(data,status)
				{
					var imgdivHtml = "<a href='javascript:cancelAttention();'>取消关注</a>";
					$(this).parents("li").empty().html(imgdivHtml);
				});*/
				return false;
			});
		});
	</SCRIPT>
	
  </head>
  
  <body>
    <div class="outer">
 		<div class="inner">
		<div id="center_content">
		<div id="top_navi">
			<div id="tagName" style="margin-top: 30px; margin-left:35px;font-size: 24px;float: left; "><s:property value="tagName"/></div>
			<div id="tagSubBut">
				<s:if test='"" == myusername || null == myusername'>
					
				</s:if>
				
				<s:else>
					<s:if test="1==isUserSub">
						<!-- 用户已经订阅 -->
						<input type="button" class="unSubBut" tagid="<s:property value="tagid"/>" style="margin-top: 25px; margin-left:15px;width:70px; height:35px; line-height:35px;background-color:#F4F4F4; color:#B4B4B4;border:1px solid #CFCFCF;font-size:16px; font-family:微软雅黑;" value="已订阅"/>
					</s:if>
					<s:elseif test="0==isUserSub">
						<input type="button" class="subBut" style="margin-top: 23px; margin-left:15px;width:70px; height:35px; line-height:35px;background-color:#94B600; color:#FFFFFF;border:0px solid #EDEDEF;font-size:16px; font-family:微软雅黑;" value="订阅"/>
					</s:elseif>
					
				</s:else>
			</div>
		</div>
			<s:if test='"" == myusername || null == myusername'>
				<div id="user_setting" style="background-color: #fff"><a href="userlogin/gotoLogin.do">登录</a>后可订阅标签</div>
			</s:if>
			<s:else>
				<div id="user_setting"><s:property value="myusername"/></div>
			</s:else>
		</div>
	
	
	<div class="clr"></div>
	<div id="loginfo_list">
			<s:if test='null == searchTagData'>
				暂时还没有与<span style="font-size:18px;color:red;"><s:property value="tagName"/></span>标签有关的内容，<a href="tag/" style="font-size: 18px;color: red;font-weight: bold">发现</a>更多有趣的内容^_^
			</s:if>
			<s:else>
				<s:iterator value="searchTagData.data" id="data">
		<div class="loginfo_list_left">
			<div class="headImg">
				<img src="<%=basePath %>images/111.jpg" title="sasa" alt="头像" />
				<s:if test="0==#data.isAttention">
					<div class='imgdiv'><ul><li><a username='<s:property value="#data.username"/>' isattention='<s:property value="#data.isAttention"/>' href='javascript:void(0);'>加关注</a></li></ul></div>
				</s:if>
				<s:elseif test="1==#data.isAttention">
					<div class='imgdiv'><ul><li><a username='<s:property value="#data.username"/>' isattention='<s:property value="#data.isAttention"/>' href='javascript:void(0);'>取消关注</a></li></ul></div>
				</s:elseif>
			</div>
			<div class="info">
				
				<div><span class="info_user"><a href="zone/<s:property value="#data.username"/>"><s:property value="#data.username"/></a></span><span class="info_time"><s:property value="#data.publishTime"/></span></div>
				<div class="clr"><h4><a href="loginfo/viewmore.do?logInfoid=<s:property value="#data.logid"/>"><s:property value="#data.logTitle"/></a></h4></div>
				<div>
					<div style="margin-bottom: 10px;">
						<div class="loginfo_img"><img src="<%=basePath %>images/ajaxDemo/mrPip.jpg" alt="图片"/></div>
						<div class="info_detail">
							<s:property value="#data.logContent" escape="false"/>
						</div>
					</div>
					<div class="clr"></div>
					<div>
					<span class="info_tag">
						<ul>
							<s:iterator value="#data.tags" id="tag">
								<li><a href='tag/<s:property value="#tag"/>'>#<s:property value="#tag"/></a></li>
							</s:iterator>
							
						</ul>
					 </span>
					<span class="info_like">
						<ul>
							<li>热度(0)</li>
							<li>转载(<s:property value="#data.reprintNum"/>)</li>
							<li>评论(<s:property value="#data.commentNum"/>)</li>
							<li>喜欢(<s:property value="#data.likeNum"/>)</li>
						</ul>
					</span></div>
				</div>

			</div>
			
			
		</div>
	
		<div class="clr"></div>
		</s:iterator>
		<div id="pagebar">
			<s:if test="1==searchTagData.hasNext">
				<!-- 有下一页 -->
				<a href="tag/<s:property value="tagName"/>?startIndex=<s:property value="searchTagData.nextIndex"/>"><img src="<%=basePath%>images/next.gif" style="float:right; margin-right:10px;" alt="下一页"/></a>
			</s:if>
			<s:if test="1==searchTagData.hasPre">
				<!-- 有上一页 -->
				<a href="tag/<s:property value="tagName"/>?startIndex=<s:property value="searchTagData.preIndex"/>"><img src="<%=basePath%>images/pre.gif" style="float:right; margin-right:10px;" alt="上一页"/></a>
			</s:if>
		</div>
			</s:else>
			
</div>
	<s:if test='"" == myusername || null == myusername'>
	
	</s:if>
	<s:else>
		<div id="left_navi">
		<div style="width: 100%;height: 355px;height: auto!important;">
			<div class="left_navi_1">
				<span><script src="attention/getAttentionNums.do" type="text/javascript"></script><br/>关注</span>
				<span><script src="attention/getFans.do" type="text/javascript"></script><br/>粉丝</span>
				<span style="border-right:0px solid #ccc;">
				<script src="ajax/zone/record.do" type="text/javascript"></script>
				<br/>记录</span>
			</div>
			<div style="height: 60px;padding-top: 10px;padding-left:15px;border-bottom: 1px dotted #ccc;">
				<form action="logtag/findLogInfoByTagName.do" id="tagForm" method="post">
				<span style="border:0px solid red;"><input type="text" id="tag" value="输入想要查找的标签" name="logTag.tagName" style="height:30px;line-height: 30px;width: 165px;border-top-left-radius:4px;border-buttom-left-radius:4px;margin-top: 10px;font-size: 16px;color:#AFB0B0;border: 1px solid #ccc; border-right: 0px solid #ccc;padding-left: 3px;" />
				<input type="button" id="tagSubmit" style="border-top-right-radius:4px;border-buttom-right-radius:4px;background:url(<%=basePath%>images/tagsearch.gif);line-height: 33px; border:1px solid #ccc;  width:40px; height:33px; background-repeat:no-repeat;margin-left:-5px;" ></span>
				</form>
			</div>
			<div style="height: 40px;height: auto!important;">
				<h5 style="float: left;padding: 0px;margin: 0px;">我订阅的标签</h5><span>&nbsp;&nbsp;<a href="${ctx }/loginfo/getSubTagLogInfo.do">查阅</a></span>
				<div class="clr"></div>
				<ul class="my_rec_sub">
					<li>美女</li>
					<li>明星</li>
					<li>生活</li>
				</ul>
			</div>
			<div style="height: 250px;height: auto!important;">
			<div><span style="float:left;">推荐订阅的标签</span><span style="float:right;"><a href="javascript:loadOtherTags();">换一批</a></span></div>
			<div class="clr"></div>
			<ul class="rec_sub">
					<li>美女</li>
					<li>明星</li>
					<li>生活</li>
				</ul>
			</div>
		</div>
		<div style="height: 40px;" id="more_tags"><a href="tag/" style="font-size: 16; font-family: 微软雅黑; text-decoration: none;">发现更多有趣内容</a></div>
		<div style="height: 90px; border-bottom: 1px solid #ccc;border-top: 1px solid #ccc;">访问统计
			<span style="display:block;">今日访问量：0</span>
			<span style="display:block;">总的访问量：0</span>
		</div>
		<div style="height: 320px;">客户端</div>
	</div>
	</s:else>
	<div class="clr"></div>
</div></div>
  </body>
</html>
