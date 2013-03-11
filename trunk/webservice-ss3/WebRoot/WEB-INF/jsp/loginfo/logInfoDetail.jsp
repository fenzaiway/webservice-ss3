<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:property value="logInfo.logTitle"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="<%=basePath%>css/commons.css" type="text/css"></link>
	<style type="text/css">
		a{text-decoration: none;}
		a:hover{text-decoration: underline;color: red;}
	</style>
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/commons.js"></script>
  <script type="text/javascript">
  		var myusername;
  		var logId;

  		//判断用户是不是已经收藏过
		function isUserStore(username)
		{
			var turnToUrl = 'logstore/gotologstore.do?logInfoId=<s:property value="logInfoid"/>';
			//alert(turnToUrl + "  " + username);
			$.post("logstore/checkIsUserStore.do?logInfoId=<s:property value='logInfoid'/>",{},function(data)
			{
				///alert(data.status);
				if(1==data.status)
				{
					///已经收藏过
					alert("您已经收藏过该日志");
				}else
				{
					window.location.href="${pageContext.request.contextPath}/"+turnToUrl;
				}
			})
			//return false;
		}
  		
  		$(function()
  		{
  			backToTop();
  			$("a").bind(
  			{
  				mouseover:function(e){
                        $(this).addClass("hover");
                        //alert($(this).text());
                         $(this).attr("title",$(this).text());
                         e.stopPropagation();
                },
               mouseout:function(e){
                        $(this).removeClass("hover");
                         e.stopPropagation();
                }
  			});
  			$(".showReply").click(function()
  			{
				var commentid = $(this).attr("commentId");
				var logId = $(this).attr("logId");
  				var replyString="<form action='logCommentReply/save.do' method='post'><input type='hidden' name='commentId' value='"+ commentid +"'><input type='hidden' name='logId' value='"+ logId +"'>";
  				replyString += "<pre><textarea rows='4' cols='50' name='replyText'></textarea></pre><input type='submit' value='发表'></form>"
  				//$("#reply").css("display","block");
  				$(this).next().empty();
  				$(this).next().html(replyString);
  			});
  			
  			$(".comment").click(function()
  			{
  				$("#commentText").focus();
  			});
  			
  			/*$.post("loginfo/getLogLike.do",{"myLogInfoId":logId},function(data)
  			{
  				$(".likeNumber").text(data.length);
  			})*/
  			
  			myusername = "${sessionScope.myusername}";///取出Session中的值
  			logId = <s:property value="logInfo.id"/>
  			if("" != myusername)
  			{
  				$.post("loginfo/isUserLike.do",{"myusername":myusername,"myLogInfoId":logId},function(data,status)
  				{
  					if(0!=data.id)
  					{
  						$(".like a").attr("href","javascript:like(0);");
  					}
  				});
  			}
  			
  			
  		});
  </script>

	
  </head>
  
  <body>
  		
		<div style="margin: 0 auto; background: white;height: auto;width: 980px;padding:0 15;height: auto!important;height: 100%;min-height: 100%;">
		<a href="loginfo/gotologinfo.do?zoneuser=${zoneuser }">日志</a><br/>
    	<h3 ><s:property value="logInfo.logTitle"/></h3><br/><s:property value="logInfo.logPublishTime"/>&nbsp;&nbsp;阅读（<s:property value="logInfo.logVisits.size()"/>）<br>
    	<div style="width: 98%;border: 1 solid green;padding: 0 10 0 10;">
    	<s:property value="logInfo.logText" escape="false"/></div><br/>
		<ul>
			<li><div class="like"><a href='javascript:like(1);'>like（<span class="likeNumber"><s:property value="logLikeList.size()"/></span>）</a></div></li>
			<li>
				<s:if test="zoneuser!=myusername">
					<a href="loginfo/turnToLogReprint.do?logInfoid=<s:property value="logInfo.id"/>">转载（<s:property value="logReprintList.size()"/>）</a>
				</s:if>
				<s:else>转载（<s:property value="logReprintList.size()"/>）</s:else>
			</li>
			<li><a href="javascript:void(0);" class="comment">评论（<s:property value="logCommentList.size()"/>）</a></li>
			<li><a href="">分享（<s:property value="logInfo.logShares.size()"/>）</a></li>
			<s:if test="zoneuser!=myusername">
			<li><a href="javascript:isUserStore('<s:property value='myusername'/>')">收藏（<s:property value="logStoreList.size()"/>）</a></li>
			</s:if>
			<s:else>收藏（<s:property value="logStoreList.size()"/>）</s:else>
		</ul><br/>
		<s:property value="logInfo.logType.logTypeName"/>|<s:property value="logInfo.logAllowVisit"/>
		
		<br>
		文章评论
		<hr noshade="noshade" size="1" align="left" width="30%">
		<s:iterator value="logCommentList" id="comment" status="s">
			<s:property value="#comment.username"/>|<s:property value="#s.count"/>楼&nbsp;&nbsp;<s:property value="#comment.commentTime"/><br/>
			<s:property value="#comment.commentContent"/>|<a href="javascript:void(0);" title="回复" commentId="<s:property value='#comment.id'/>" logId="<s:property value='logInfo.id'/>" class="showReply">回复</a><div class="reply"></div>
			&nbsp;&nbsp;
			<script type="text/javascript">
				$.post("ajax/commentReply/getLogCommentReply.do",{"commentId":<s:property value='#comment.id'/>},function(data)
				{
					var replyString = "";
					for(var i=0; i<data.length; i++){
						replyString +="&nbsp;&nbsp;"+data[i].username +"回复：<pre>" +data[i].replyContent+ "</pre>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].replyTime +"<br/>" 
					}
					$(".commentReply<s:property value='#s.count'/>").html(replyString);
				});
				
			</script>
			<div class="commentReply<s:property value='#s.count'/>" style="border-bottom:1px dashed #000; height:100px;width:350px;margin-bottom: 15px;height:auto !important;">
				
			</div>
		</s:iterator>	
		
		<form action="logComment/save.do" method="post">
			<input type="hidden" name="logId" value="<s:property value='logInfo.id'/>">
			<pre><textarea rows="4" cols="50" name="commentText" id="commentText">
	
			</textarea></pre>	
			<input type="submit" value="发表">
		</form>
		<div>
			相似文章<br/>
			<s:iterator value="similarLogInfoList" id="similarLogInfo">
				<a href="loginfo/viewmore.do?zoneuser=<s:property value='#similarLogInfo.zoneuser'/>&logInfoid=<s:property value='#similarLogInfo.logId'/>"><s:property value="#similarLogInfo.logTitle"/></a><br/>
			</s:iterator>
		</div>
	<script type="text/javascript"> 
		function like(islike)
		{
			if(""==myusername)////如果用户没有登录
			{
				window.location.href="${pageContext.request.contextPath}/register/gotoLogin.do";
			}else
			{
				$.post("loginfo/like.do",{"myusername":myusername,"myLogInfoId":logId,"isLike":islike},function(data,status)
				{
					changeIsLike(islike);
					$(".likeNumber").text(data.length);
				});
			}
			  
		}
	
		function changeIsLike(islike)
		{
			if(0==islike)////设置为1
			{
				$(".like a").attr("href","javascript:like(1);");
			}else
			{
				$(".like a").attr("href","javascript:like(0);");
			}
		}
		
		function toBreakWord(intLen){ 
			var obj=document.getElementById("content"); 
			var strContent=obj.innerHTML; 
			var strTemp=""; 
			while(strContent.length>intLen){ 
			strTemp +=strContent.substr(0,intLen)+"<br/>"; 
			strContent=strContent.substr(intLen,strContent.length); 
			} 
			strTemp +="<br>"+strContent; 
			obj.innerHTML=strTemp; 
		} 
		if(document.getElementById && !document.all) toBreakWord(85); 
</script>
	<s:debug></s:debug>
	</div>
	<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=2&amp;pos=right&amp;uid=5713954" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
var bds_config={"bdTop":184};
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
</script>
<!-- Baidu Button END -->
  </body>
</html>
