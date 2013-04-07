<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:property value="logInfo.logTitle"/>--轻轻一点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/logInfoDetail2.css">
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
			$("#left_logtype li").live("mouseover",function()
			{
				$(this).css("background-color","#F8F8F8");
				return false;
			}).live("mouseout",function()
			{
				$(this).css("background-color","#FFF");
				return false;
			});
			$(".comment").click(function()
  			{
  				$("#commentText").focus();
  			});
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
	<div id="top"></div>
    <div id="main">
		<div id="left">
			<div id="left_logtype">
		    	<a href="${ctx }/userzone/infocenter.do"><img  src="${ctx }/images/top_logo.jpg"/></a>
		    	<ul>
		        	<li><a href="${ctx }/zone/${zoneuser}">首页</a></li>
					<s:iterator value="logTypeList" id="logType">
						<li><a href="${ctx}/loginfo/getLogInfoByLogTypeId.do?logTypeId=<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></a></li>
					</s:iterator>
		            
		        </ul>
		    </div>
		    
		</div>
		<div id="right">
			<div id="content">
				<div id="title"><h1><s:property value="logInfo.logTitle"/></h1></div>
				<div id="author">作者：<a href="${ctx }/zone/<s:property value="logInfo.username"/>"><s:property value="logInfo.username"/></a>&nbsp;&nbsp;|&nbsp;&nbsp;发布时间：<s:property value="logInfo.logPublishTime"/></div>
				<div class="separate-line"></div>
			</div>
			<div id="logtext">
				<s:property value="logInfo.logText" escape="false"/>
			</div>
			<div id="tag">
				<span>
					标签：<s:iterator value="logInfo.logTags" id="tag">
						<a href="${ctx }/tag/<s:property value="#tag.tagName"/>">#<s:property value="#tag.tagName"/></a>
						</s:iterator>
				</span>
			</div>
			<div class="bdShare">
				<!-- Baidu Button BEGIN -->
				<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
				<span class="bds_more">分享到：</span>
				<a class="bds_qzone"></a>
				<a class="bds_tsina"></a>
				<a class="bds_tqq"></a>
				<a class="bds_renren"></a>
				<a class="bds_t163"></a>
				<a class="shareCount"></a>
				</div>
				
				<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=5713954" ></script>
				<script type="text/javascript" id="bdshell_js"></script>
				<script type="text/javascript">
					document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
				</script>
				<!-- Baidu Button END -->
			</div>
			<div class="clr"></div>
			<s:if test="0!=similarLogInfoList.size()">
				<div id="similarLogInfo">
				<h5>相似文章：</h5>
				<ul><s:iterator value="similarLogInfoList" id="similarLogInfo">
				<li><span>.</span><a href="loginfo/viewmore.do?zoneuser=<s:property value='#similarLogInfo.zoneuser'/>&logInfoid=<s:property value='#similarLogInfo.logId'/>"><s:property value="#similarLogInfo.logTitle"/></a></li>
				</s:iterator></ul>
				</div>
			</s:if>
			<div id="bottom_data">
				<span class="data_span data_span_1">阅读（<s:property value="logInfo.logVisits.size()"/>）</span>┊
				<span class="data_span"><a href="javascript:void(0);" class="comment">评论</a>（<s:property value="logCommentList.size()"/>）</span>┊
				<span class="data_span">转载（<s:property value="logReprintList.size()"/>）</span>┊
				<span class="data_span">
				<s:if test="zoneuser!=myusername">
				<a href="javascript:isUserStore('<s:property value='myusername'/>')">收藏</a>（<s:property value="logStoreList.size()"/>）
				</s:if>
				<s:else>收藏（<s:property value="logStoreList.size()"/>）</span></s:else>		
				┊
				<span class="like"><a href='javascript:like(1);'>like（<span class="likeNumber"><s:property value="logLikeList.size()"/></span>）</a></span>
			</div>
			<div id="pre_next_info">
				上一篇|下一篇
			</div>
			<div class="comments">
				<span class="comment_span">
					全部评论：<s:property value="logCommentList.size()"/>条&nbsp;&nbsp;|
					&nbsp;&nbsp;
					<s:if test='"" == myusername || null == myusername'><a href="${ctx }/userlogin/gotoLogin.do">登录</a></s:if>
					<s:else>账号：${myusername }<a href="${ctx }/userlogin/sessionInvalidate.do">退出</a></s:else>
				</span>
				<div class="separate-line"></div>
				<div class="comments_list">
					<s:iterator value="logCommentList" id="logcomment">
						<div class="head_img"><img src="${ctx }/images/zone_headimg.gif"/></div>
						<div class="comment_detail">
							<div class="comm_name"><a href="${ctx }/zone/${zoneuser}"><s:property value="#logcomment.username"/></a>：<s:property value="#logcomment.commentContent"/></div>
							<div class="commentTime"><s:property value="#logcomment.commentTime"/></div>
						</div>
						
						<div class="separate-line"></div>
					</s:iterator>
				</div>
				<s:if test='"" == myusername || null == myusername'><a href="${ctx }/userlogin/gotoLogin.do">登录</a>后才可以回复</s:if>
				<s:else>
					<form action="logComment/save.do" method="post">
						<input type="hidden" name="logId" value="<s:property value='logInfo.id'/>">
						<textarea name="commentText" id="commentText" style="width: 100%;min-height: 100px;min-width: 100%;overflow-x:hidden;overflow-y:auto;resize:vertical;background-color: #F8F8F8;border: 1px solid #DDDDDD;"></textarea>
						<input type="submit" id="subBut" class="button publish" value="发　布"/>
					</form>
				</s:else>
			</div>
		</div>
		
	</div>
	<div class="clr"></div>
	<div id="bottom">
		<hr style="border: 3px solid #eee">
		<div class="tagfeed">
			  <div class="left_content">
                	<ul>
						<s:iterator value="userLogTagList" id="logtag">
                    	<li><a href="${ctx }/tag/<s:property value="#logtag.tagName"/>">#<s:property value="#logtag.tagName"/></a></li>
						</s:iterator>
                    </ul>
                </div>
		</div>
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
	</script>
  </body>
</html>
