<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${zoneuser }的主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${ctx }/css/homeIndex1.css">
	<style type="text/css">
		#top{z-index: 2;line-height: 40px;position: absolute;position: fixed;height: 40px;background-color: #EEEEEE;width:100%;top:0px;left: 0px;}
		#top h1{font-size:16px;}
		#navi{width: 980px;margin: 0px auto;}
		#navi a{color:#333;text-decoration: none;}
		#navi a:hover{text-decoration: underline;}
		#navi span{display: inline;float: left;margin-right: 25px;}
	</style>
	<script type="text/javascript">
		var tousername; //关注的是哪个用户的空间
		var attentionButHtml = "";
		
		//定义全局变量
		var lastLiHtml = ""; ///上一个Li的html内容
		var nowLiHtml = "";
		var lastTypeName = "";// 原来日志类型，用来判断是否要进行日志更新
		var lastIndex = -1; ///上一条记录
		
		///加关注事件函数
		function addAttention()
		{
			//alert(tousername)
			$.post("attention/addAttention.do",{"toUserName":tousername},function(data,status)
			{
				attentionButHtml = "<input type='button' class='attentionBut' onclick='cancelAttention();' value='取消关注'/>";
				$(".attentionDiv").empty().html(attentionButHtml);
			});
		}
		
		//取消关注事件函数
		function cancelAttention()
		{
			$.post("attention/updateAttention.do",{"toUserName":tousername},function(data,status)
			{
				attentionButHtml = "<input type='button' class='attentionBut' onclick='addAttention();' value='加关注'/>";
				$(".attentionDiv").empty().html(attentionButHtml);
			});
		}
		
		function gotoLogin()
		{
			window.location.href="${pageContext.request.contextPath}/userlogin/gotoLogin.do";
		}
		
		function delete_type(index)
		{
			if(window.confirm('你确定要删除吗？删除后该分类目录将移动到“个人日志”分类下'))
			{
              	var myLogTypeId = $("#left_logtype li:eq("+index+")").attr("logid");
					
				$.post("logtype/delete.do",{"myLogTypeId":myLogTypeId},function(data,status)
				{
					/////成功删除，则将该行删除
					if("success"==status)
					{
						$("#left_logtype li:eq("+index+")").remove();
						alert("分类成功删除");
					}
				})
              }else
              {
                 //alert("取消");
                // return false;
             }
		}
		
		///日志类型编辑
		function editType(index)
		{
			//alert('aaa');
			//判断上一个li Index是不是为-1
			if(-1 != lastIndex) //表示已经点击过编辑链接，上一条内容要进行还原
			{
				var lastLi = $("#left_logtype li:eq("+lastIndex+")");
				lastLi.empty().html(lastLiHtml); //还原上一条li的html数据
				lastIndex = index;
			}else
			{
				lastIndex = index;
			}
			var liThis = $("#left_logtype li:eq("+index+")");
			lastLiHtml = liThis.html(); //取得当前li的内容
			lastTypeName = liThis.find(".type_a").text();
			var editHtml = "<input class='type_name' type='text' value='"+lastTypeName+"' name=''>"+
							"<input type='button' class='update_but' value='更新'/><input type='button' class='cancle_but' value='取消'/>";
			
			liThis.empty().html(editHtml);
			
		}
		
		//取消更新
		function cancleBut()
		{
			$(".cancle_but").live("click",function()
			{
				$("#left_logtype li:eq("+lastIndex+")").empty().html(lastLiHtml);
				return false;
			});
		}
		
		//点击更新按钮
		function updateBut()
		{
			$(".update_but").live("click",function()
			{
				//获取内容
				var nowTypeName = $(".type_name").val();
				if("" == nowTypeName)
				{
					alert("分类名称不能为空");
					return false;
				}
				if(nowTypeName==lastTypeName) ///如果用户不更新分类名称的话
				{
					//还原原来的内容
					$("#left_logtype li:eq("+lastIndex+")").empty().html(lastLiHtml);
				}else //用户更新名称的时候才异步更新分类的名称
				{
					var myLogTypeId = $("#left_logtype li:eq("+lastIndex+")").attr("logid");
					//alert(myLogTypeId);
					$.post("logtype/update.do",{"myLogTypeName":nowTypeName,"myLogTypeId":myLogTypeId},function(data,status)
					{
						//alert(status);
						if("success" == status)///成功后隐藏div
						{
							$("#left_logtype li:eq("+lastIndex+")").empty().html(lastLiHtml);
							$("#left_logtype li:eq("+lastIndex+")").find(".type_a").text(nowTypeName);
							lastLiHtml="";
							lastIndex=-1;
							alert("更新成功");
						}
					});
				}
				return false;
			})
			
			
		}
		
		$(function()
		{
			backToTop();
			tousername = '<s:property value="username"/>';
			$(".article").live("mouseover",function()
			{	
				$(this).css("border-color","#50803F");
				return false;
			}).live("mouseout",function()
			{
				$(this).css("border-color","#F8F8F8");
				return false;
			});
			
			$("#left_logtype li").live("mouseover",function()
			{
				$(this).css("background-color","#F8F8F8");
				$(this).find("span").css("display","inline");
				return false;
			}).live("mouseout",function()
			{
				$(this).css("background-color","#FFF");
				$(".type_edit").css("display","none");
				return false;
			});
			
			
			cancleBut(); ///点击取消按钮
			updateBut(); //点击更新按钮
		});
	</script>

  </head>
  
  <body>
	<div id="top">
		<div id="navi">
			<span><h1><a href="${ctx }/userzone/infocenter.do">首页</a></h1></span>
			<span><h1><a href="${ctx }/zone/${myusername}">我的主页</a></h1></span>
			<span><h1><a href="${ctx }/albumtype/gotoAlbumTypeList.do?zoneuser=${zoneuser }">相册</a></h1></span>
			<span><h1><a href="${ctx }/tag/">发现</a></h1></span>
			<span><h1><a href="${ctx }/ajax/message/getUserMessageList.do">消息</a></h1></span>
		</div>
	</div>
	<div id="home_top"><h1>${zoneuser}</h1></div>
	<div id="attention">
		<div class="attentionDiv">
		<s:if test="0==isAttention">
					<input type="button" class="attentionBut" onclick="addAttention();" value="加关注"/>
             	  	</s:if>
					<s:elseif test="1==isAttention">
						<input type="button" class="attentionBut" onclick="cancelAttention();" value="取消关注"/>
					</s:elseif>
					<s:elseif test="-2==isAttention">
						<input type="button" class="attentionBut" onclick="gotoLogin();" value="登录"/>
					</s:elseif>
		</div>
</div>
<div id="main">
<div id="left">
	<div id="left_logtype">
    	<a href="${ctx }/userzone/infocenter.do"><img  src="${ctx }/images/top_logo.jpg"/></a>
    	<ul>
        	<li><a href="${ctx }/zone/${zoneuser}">首页</a></li>
			<s:iterator value="logTypeList" id="logType" status="st">
				<li logid=<s:property value="#logType.id"/>>
					<a class='type_a' href="${ctx}/loginfo/getLogInfoByLogTypeId.do?logTypeId=<s:property value="#logType.id"/>"><s:property value="#logType.logTypeName"/></a>
					<c:if test="${zoneuser==myusername}">
						<s:if test="0==#logType.isDefaultLogType">
						<span class="type_edit">
						&nbsp;&nbsp;<a href="javascript:editType(<s:property value='#st.count'/>)" >编辑</a>
						&nbsp;&nbsp;<a href="javascript:delete_type(<s:property value='#st.count'/>)" >删除</a>
						</span></s:if>
						<s:elseif test="1==#logType.isDefaultLogType">
							(不支持编辑及删除)
						</s:elseif>
					</c:if>
				</li>
			</s:iterator>
            
        </ul>
    </div>
    
</div>
<div class="clr"></div>
<div id="right">
			
			<s:iterator value="logInfoList" id="loginfo">
				<div class="article">
				
					<div class="content">
						<span class="content_1">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="#loginfo.logText.length() > 100">
								<s:property value="#loginfo.logText.substring(0, 100)" escape="false"/>...
							</s:if>
							<s:else>
								<s:property value="#loginfo.logText" escape="false"/>...
							</s:else>
							<a href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#loginfo.id'/>">更多</a>
							
							
						<span>
					</div>
					<div class="article_bottom">
						<div class="title"><span><a href="loginfo/viewmore.do?zoneuser=${zoneuser }&logInfoid=<s:property value='#loginfo.id'/>"><s:property value="#loginfo.logTitle"/></a></span></div>
						<div class="time">
							<span class="time1"><s:property value="#loginfo.logPublishTime"/></span>
							<span class="time2"><img src="${ctx }/images/commons.gif"/><s:property value="#loginfo.logComments.size()"/></span>
						</div>
					</div>
				</div>
			</s:iterator>
	
</div>


</div>
</body>

</html>
