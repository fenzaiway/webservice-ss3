//var username;
	var position = 0;
	var tagHtml;  ////标签html
	var likeImg;  ////喜欢图片html
	var commentHtml = "<div class='comment_info'><div class='comment_info_input'>";
	commentHtml+="<div class='comment_text_div'><div class='comment_text' commentid='' contenteditable='true'></div>";
	commentHtml+="</div>";
	commentHtml+="<div><input type='button' class='comment_button' logid='' position='' value='发表'/></div> <div class='clr'>";
	commentHtml+="<div class='comment_position'></div>"		
	commentHtml+="</div></div>";
	commentHtml+="<div class='back_comment' position='' >收起按钮</div></div>";
	////加标签html	
	var addTagHtml = "<div class='addTagss'><input type='text' value='' class='addTagInput' name='tagName' /><input type='button' position='' logid='' class='addTagBut' value='添加'/></div><div class='clr'></div>";

	////热度列表
	var hotHtml = "<div class='hotList'></div>";
	
	var imgdiv = "";
	
	//判断空间用户是不是登录用户
	function isLoginUser(name)
	{
		if(name == myusername)
		{
			return true;
		}else{
			return false;
		}
		
	}
	
	function showListData(data)
	{
		var html = "";
		$("#pagebar").remove();
		var headImg = "";
		var logids = "";
		var logid;
		var name = "";
		for(var i=0;i<data.length;i++)
		{
			logid = data[i].logid;
			logids += logid;
			logids += ",";
			if(""==data[i].headImgUrl)
			{
				headImg = "images/111.jpg";
			}else
			{
				headImg = data[i].headImgUrl;
			}
			
			//判断是否文章包含Tag
			tagHtml = "";
			name = data[i].username;
			if(typeof(data[i].tags)=="undefined")
			{
				if(isLoginUser(name))
				{
					tagHtml+="<li><div class='addtags' position='"+position+"'>加标签</div></li>";
				}
			}else
			{
				for(var j=0; j<data[i].tags.length; j++)
				{
					tagHtml+="<li><a href='tag/"+data[i].tags[j]+"'>#"+data[i].tags[j]+"</a></li>";
				}
			}
			////判断用户是否喜欢该篇文章
			if(1 == data[i].isLike) ///如果用户喜欢
			{
				likeImg = "<img src='images/like3.gif' logid="+logid+" class='likeimg' title='取消喜欢' isLike="+data[i].isLike+" alt='like'/>";
			}else
			{
				likeImg = "<img src='images/like2.gif' logid="+logid+" class='likeimg' title='喜欢' isLike="+data[i].isLike+" alt='like'/>";
			}
			
			///判断用户是否关注其他用户的空间
			if(1 == data[i].isAttention)
			{
				imgdiv = "<div class='imgdiv'><ul><li><a username="+name+" isattention=1 href='javascript:void(0);'>取消关注</a></li></ul></div>";
			}else if(0 == data[i].isAttention)
			{
				imgdiv = "<div class='imgdiv'><ul><li><a username="+name+" isattention=0 href='javascript:void(0);'>加关注</a></li></ul></div>";
			}
			html+="<div class='loginfo_list_left'>";
				html+="<div class='headImg'>";
				html+="<a href='zone/"+name+"' title='"+name+"'><img src='"+headImg+"' alt='头像' /></a>";
				html+=imgdiv;
				html+="</div>";
					html+="<div class='info' logid="+logid+">";
					html+="<div><span class='info_user'><a href='zone/"+name+"'>"+name+"</a></span><span class='info_time'>"+data[i].publishTime+"</span></div>";
					html+="<div class='clr'><h4><a href='loginfo/viewmore.do?zoneuser="+name+"&logInfoid="+logid+"' title='"+data[i].logTitle+"'>"+data[i].logTitle+"</a></h4></div>";
					html+="<div>";
					html+="<div style='margin-bottom: 10px;'>";
					html+="<div class='loginfo_img'><img src='images/ajaxDemo/mrPip.jpg' alt='图片'/></div>";
					html+="<div class='info_detail'>";
					html+=data[i].logContent;		
					html+="</div>";
					html+="</div>";
					html+="<div class='clr'></div>";
					html+="<div>";
					html+="<span class='info_tag'>";
					html+="<ul>";
					html+=tagHtml;
					html+="</ul>";
					html+="</span>";
					html+="<span class='info_like'>";
					html+="<ul>";
					html+="<li><a href='javascript:void(0)' class='my_hotNum' logid="+logid+" position="+position+">热度(<span class='hotNum'>"+data[i].hotNum+"</span>)</a></li>";
					html+="<li>转载("+data[i].reprintNum+") </li>";
					html+="<li><a href='javascript:void(0)' class='my_info_comment'  position="+position+">评论(<span class='commentNum'>"+data[i].commentNum+"</span>)</a></li>";
					html+="<li>"+likeImg+"喜欢(<span class='likeNum'>"+data[i].likeNum+"</span>)</li>";
					html+="</ul>";
					html+="</span></div>";
					html+="</div></div>";
			html+="<div class='clr'></div>";
			html+=commentHtml;
			html+=hotHtml;
			html+="<div class='clr'></div>";
			html+="</div>";
			
			
			html+="</div>";	
			position++;
		}
		html+="<div class='clr'></div>";
		html+="<div id='pagebar' style='display:block;text-align:center'></div>";
		$("#loginfo_list").append(html);
		return logids;
	}
	//隐藏热度值列表
	function myHideHotList(position)
	{
		$(".hotList:eq("+position+")").slideToggle(function()
		{
			$(".hotList:eq("+position+")").css("display","none");
		});
	}
	//热度值框事件
	function hideHotList(position)
	{
		if($(".hotList:eq("+position+")").is(":visible"))
		{
			myHideHotList(position);
		}else
		{
			$(".hotList:eq("+position+")").slideToggle(function()
			{
				$(".hotList:eq("+position+")").css("display","block");
			});
		}
	}
	
	///点击热度值事件
	$(".my_hotNum").live("click",function()
	{
		var position = $(this).attr("position");
		var hotNum = $(this).find(".hotNum").text();
		hideComment(position);
		
		hideHotList(position);
		///当热度值不等于0的时候才去取热度列表
		if(0 != hotNum)
		{
			if($(".hotList:eq("+position+")").html()=="")
			{
				var logInfoId = $(this).attr("logid");
				var messageDataHtml = "<div class='messagedata'>"
				$.post("ajax/message/getHotMessageList.do",{"logInfoId":logInfoId},function(data)
				{
					for(var i=0; i<data.length; i++)
					{
						messageDataHtml+=data[i].content;
						messageDataHtml+="<br/>";
					}
					messageDataHtml+="<div>"
					$(".hotList:eq("+position+")").empty().html(messageDataHtml);
				});
			}
			
		}else
		{
			$(".hotList:eq("+position+")").empty().html("暂时还没有与这篇文章相关的热度值");
		}
		return false;
	});
	
	///加关注事件函数
//	function addAttention()
//	{
		$(".imgdiv a").live("click",function()
		{
			var isattention = $(this).attr("isattention");
			var tousername = $(this).attr("username");
			////取消喜欢
			if(1==isattention)
			{
				
				//alert($(".imgdiv a").length);
				
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
		
//	}
	
	//取消关注事件函数
	/*function cancelAttention()
	{
		
		$(".imgdiv a").live("click",function()
		{
			//alert($(this).attr("username"));
			var tousername = $(this).attr("username");
			
			$.post("attention/updateAttention.do",{"toUserName":tousername},function(data,status)
			{
				var imgdivHtml = "<a href='javascript:addAttention();'>加关注</a>";
				$(this).parents("li").empty().html(imgdivHtml);
			});
			return false;
		});
		//alert("取消关注");
		/*$.post("attention/updateAttention.do",{"toUserName":tousername},function(data,status)
		{
			attentionButHtml = "<input type='button' class='attentionBut' onclick='addAttention();' value='加关注'/>";
			$(".attentionDiv").empty().html(attentionButHtml);
		});
		
	}*/
	
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
	
	////回复评论
	function replay(commentid, logid,index,position)
	{
		var text = $(".comment_list_comment_info a:eq("+position+")").text();
		text = "回复" + text+":"
		//alert(commentid+"   " + logid);
		$(".comment_text:eq("+index+")").attr("commentid",commentid);
		$(".comment_text:eq("+index+")").empty().text(text);
	}
	
	function getcommentText(data,logid,index,position)
	{
		var commentListHtml = "";
		commentListHtml+= "<div class='comment_list'>";
		commentListHtml+= "<div class='comment_list_headimg'>";
		commentListHtml+= "<img src='images/111.jpg'/>";
		commentListHtml+= "</div>";
		commentListHtml+= "<div class='comment_list_comment_info'>";
		commentListHtml+= "<a href='zone/"+data.commentUsername+"'>"+data.commentUsername+"</a>&nbsp;&nbsp;"+data.conten+"";
		commentListHtml+= " <div class='clr'></div>";
		commentListHtml+= " </div>";
		commentListHtml+= " <div class='comment_list_reply'><a href='javascript:void(0);' onclick='replay("+data.id+","+logid+","+index+","+position+")'>回复</a></div>";
		commentListHtml+= " <div class='clr'></div>";
		commentListHtml+= " </div>";
		return commentListHtml;
	}
	
	function getReplayText(data,logid,index,position)
	{
		var commentListHtml = "";
		commentListHtml+= "<div class='comment_list'>";
		commentListHtml+= "<div class='comment_list_headimg'>";
		commentListHtml+= "<img src='images/111.jpg'/>";
		commentListHtml+= "</div>";
		commentListHtml+= "<div class='comment_list_comment_info'>";
		commentListHtml+= "<a href='zone/"+data.replyUsername+"'>"+data.replyUsername+"</a>回复<a href='zone/"+data.commentUsername+"'>"+data.commentUsername+"</a>&nbsp;&nbsp;"+data.conten+"";
		commentListHtml+= " <div class='clr'></div>";
		commentListHtml+= " </div>";
		commentListHtml+= " <div class='comment_list_reply'><a href='javascript:void(0);' onclick='replay("+data.id+","+logid+","+index+","+position+")'>回复</a></div>";
		commentListHtml+= " <div class='clr'></div>";
		commentListHtml+= " </div>";
		return commentListHtml;
	}

	////根据日志id加载日志的评论
	function loadCommentList(logid,index)
	{
		///alert(logid + "=--" + index);
		///alert($(".commentNum:eq("+index+")").text());
		var commentNum = $(".commentNum:eq("+index+")").text();
		var commentListHtml = "";
		
		////评论的数量不为0的时候才发起请求获取评论数据
		if(0!=commentNum)
		{
			
			
			$.post("ajax/comment/loadCommentList.do",{"logid":logid},function(data)
			{
				var length = data.length;
				for(var i=0; i<length; i++)
				{
					if(""==data[i].replyUsername)
					{
						commentListHtml+=getcommentText(data[i],logid,index,i);
					}else{
						commentListHtml+=getReplayText(data[i],logid,index,i);
					}
					
				}
				
				$(".comment_position:eq("+index+")").empty().append(commentListHtml);
			});
		}
		
		
	}
	
	////隐藏评论框
	function hideComment(index)
	{
		$(".comment_info:eq("+index+")").css(
		{
			"display":"none",
		});
		$(".info:eq("+index+")").css(
		{
			"margin-bottom":"15px"
		});
		
	}
	
	////移除加标签输入框
	function removeAddTag(index)
	{
		$(".loginfo_list_left:eq("+index+")").find(".addTagss").remove();
	}
	
	/////弹出登录框
	function showLogin()
	{
		$.XYTipsWindow({
			___title:"登录",
			___content:"iframe:login.html",
			___width:"460",
			___height:"150",
			___showbg:true,
			___drag:"___boxTitle"

		});
	}
	
	////拆分用户输入的标签
	function splitTag(tags)
	{
		tagHtml = "";
		var tagArray = tags.split(',');
		//alert();
		for(var i=0; i<tagArray.length; i++)
		{
			tagHtml+="<li><a href='tag/"+tagArray[i]+"'>#"+tagArray[i]+"</a></li>";
		}
		return tagHtml;
	}
	
	/////给添加标签按钮添加事件
	$(".addTagBut").live("click",function()
	{
		var inputTag = $(this).siblings("input");
		var tag = inputTag.val();
		var index = $(this).attr("position");
		var logid = $(this).attr("logid");
		//inputTag.val("").focus();/////情况表单，并设置焦点
		$.post("ajax/tag/saveLogTag.do",{"tags":tag,"logid":logid},function(data,status)
		{
			if("success" == status) ////如果添加成功
			{
				//、$(".addtags:eq("+index+")").remove();
				var liTag = $(".loginfo_list_left:eq("+index+")").find(".addtags").parent("li");
				$(".loginfo_list_left:eq("+index+")").find(".addtags").remove();
				liTag.empty().html(splitTag(tag)); ////将用户添加的标签拆分后显示在用户的界面上
				$(".loginfo_list_left:eq("+index+")").find(".addTagss").remove();////添加成功后，将文本框移除
			}
		});
		
	});
	
	
	///绑定回复点击事件
	$(".comment_list_reply").live("click",function()
	{

	});
	
	function updateComment(index)
	{
		var commentNum = parseInt($(".commentNum:eq("+index+")").text()); //获取原来的清理数量
		$(".commentNum:eq("+index+")").text(commentNum+1); ///更新评论数量
		$(".comment_text:eq("+index+")").empty().focus(); ///将输入框设置为空，并添加焦点
	}
	
	////点击发布按钮
	$(".comment_button").live("click",function()
	{
		///alert("发表");
		//alert(username + " " + myusername);
		if("" == myusername)
		{
			//showLogin();
			window.location.href="register/gotoLogin.do";
		}else{
			/////获取用户发表的内容
			//alert($(".comment_text").text());
			var logid = $(this).attr("logid");
			var index = $(this).attr("position");
			var commentText = $(".comment_text").text();
			if("回复" == commentText.substring(0,2)) ////判断用户输入的内容是不是以回复开头，如果是，则表示用户要发表的内容是回复
			{
				var toUserName = commentText.substring(2,commentText.indexOf(":")); ///得到被回复的是哪个用户
				var commentid="";
				var replayContent = commentText.substring((commentText.indexOf(":")+1));
				var commentid = $(".comment_text:eq("+index+")").attr("commentid");
				$.post("ajax/replay/saveReplay.do",{"logid":logid,"commentid":commentid,"toUserName":toUserName,"replayContent":replayContent},function(data)
				{
					$(".comment_position:eq("+index+")").prepend(getReplayText(data,logid,index,0)); ////向评论列表中添加一条最新的评论数据
					updateComment(index);
				});
				return false;
			}
			
			$.post("ajax/comment/save.do",{"commentText":commentText,"logid":logid},function(data)
			{
				$(".comment_position:eq("+index+")").prepend(getcommentText(data,logid,index,0)); ////向评论列表中添加一条最新的评论数据
				updateComment(index);
			});
			///alert($(this).attr("logid"));
		}
	});
	
	function showAddTag(index)
	{
		hideComment(index);
		$(".loginfo_list_left:eq("+index+")").append(addTagHtml); ////添加标签文本框
		var logid = $(".info:eq("+index+")").attr('logid');
		$(".loginfo_list_left:eq("+index+")").find(".addTagBut").attr("position",index).attr("logid",logid); ////给添加标签按钮设置属性
	}
	
	/**
	 * 加标签事件
	 * 由于先注册事件，所以第一次点击添加标签的时候是没有反应的
	 */
	$(".addtags").live("click",function()
	{
		var index = $(this).attr("position");
		
		showAddTag(index);
		$(this).toggle(function()
		{
			hideComment(index);
			myHideHotList(index);
			removeAddTag(index); ////移除标签文本框
		},
		function()
		{
			showAddTag(index);
		});
	});
	
	//给喜欢图片添加事件
	$(".likeimg").live("click",function()
	{
		var thisImg = $(this)
		var logId = parseInt(thisImg.attr("logid"));
		var isLike = parseInt(thisImg.attr("isLike"));
		if(0 == isLike) ////如果用户还没有喜欢
		{
			isLike = 1; //设置为1表示在后台添加喜欢
		}else if(1 == isLike)
		{
			isLike = 0;
		}
		var showLikeNum = thisImg.parent().find(".likeNum");
	//	alert( + "--" +  + "--" + .text());
		if(""==myusername)////如果用户没有登录
		{
			window.location.href="register/gotoLogin.do";
		}else
		{
			//alert(myusername + "--" + logId + "--" + isLike);
			$.post("loginfo/like.do",{"myusername":myusername,"myLogInfoId":logId,"isLike":isLike},function(data,status)
			{
				////改变图标颜色
				if(1 == isLike)
				{
					thisImg.attr("src","images/like3.gif").attr("title","取消喜欢").attr("isLike",1);
				}else if(0 == isLike)
				{
					thisImg.attr("src","images/like1.gif").attr("title","喜欢").attr("isLike",0);
				}
				showLikeNum.empty().text(data.length);
				
			});
		}
		
	});
	
	
	//改变喜欢图标样式
	$(".likeimg").live("mouseover",function()
	{
		//$(this).attr("src","images/like2.gif");
		if(1 != $(this).attr("isLike"))
		{
			$(this).attr("src","images/like2.gif");
		}
	}).live("mouseout",function()
	{
		if(1 != $(this).attr("isLike"))
		{
			$(this).attr("src","images/like1.gif");
		}
		
	});
	
	$(".my_info_comment").live("click",function()
	{
		var index = $(this).attr("position");
		var logid = $(".info:eq("+index+")").attr("logid");
		$(".comment_info:eq("+index+")").find(".comment_button").attr("logid",logid);
		$(".comment_info:eq("+index+")").find(".comment_button").attr("position",index);
		//alert($(".comment_list:eq("+index+")").text());
		////为了避免多次请求，如果列表为空，就异步请求评论数据
		removeAddTag(index);////将添加标签框移除
		myHideHotList(index);
		$(".comment_info:eq("+index+")").slideToggle('fast',function()
		{
			
			///如果评论框是显示的，像素就变为0，否则恢复
			if($(".comment_info:eq("+index+")").is(":visible"))
			{
				
				if($(".comment_position:eq("+index+")").html() == "")  ////如果评论数据为空的时候，才去请求数据
				{
					loadCommentList(logid,index); /////加载用户评论数据
				}
				
				$(".info:eq("+index+")").css("margin-bottom","0px");
				$(".comment_info:eq("+index+")").children(".back_comment").attr("position",index);
				
			}else
			{
				$(".info:eq("+index+")").css("margin-bottom","15px");
			}
		});
		
	});
	
	
	////收起评论列表
	$(".back_comment").live("click",function()
	{
		//alert($(this).attr("position"));
		//$(".comment_info:eq("+$(this).attr("position")+")").css("display","none");
		var index = $(this).attr("position");
		hideComment(index);
		/*$(".comment_info:eq("+index+")").css(
		{
			"display":"none",
		});
		$(".info:eq("+index+")").css(
		{
			"margin-bottom":"15px"
		});*/
		
	});
	
	function comment()
	{
		alert('添加评论');
		alert($(this).text());
	}

	function loadmore(id)
	{
		myRequest(id);
	}
	
	/*function checkIsUserLike(mylogids)
	{
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
	}*/
	
	//分页
	function myRequest(startIndex)
	{
		
		
		$.post("ajax/loginfo/loadLogInfo.do",{"startIndex":startIndex},function(data)
		{
			
			//var pager = data.pager;
			var loadmore = data.loadMore;
			var loadIndex = data.startIndex;
			
			showListData(data.data);
			
		//	$("#pagebar").empty().html(pager);
			//$("#pagebar").empty().html(loadmore);
			if(0==startIndex)
			{
				$("#pagebar").empty().css("display","none").html(loadIndex);
			}else
			{
				$("#pagebar").empty().html(loadmore);
			}
			
		});
		
	}
		
	////自动加载下一页
	function autoLoadMore()
	{
		var range = 20;             //距下边界长度/单位px
        var maxnum = 20;            //设置加载最多次数
        var num = 1;
        var stop=true;   ////这个变量是一个开关，是为了防止由于网络延迟导致的重复加载问题，这里设计这个变量是，只有当请求完成后才可以继续加载下一页数据
        var totalheight = 0; 
        var startIndex;
        $(window).scroll(function(){
            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
            
            //console.log("滚动条到顶部的垂直高度: "+$(document).scrollTop());
            //console.log("页面的文档高度 ："+$(document).height());
            //console.log('浏览器的高度：'+$(window).height());
			
            totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
    		if(($(document).height()-range) <= totalheight  && num != maxnum) {
				if(stop==true)
				{
		            stop=false;
		            startIndex = $("#pagebar").text();
		            $("#pagebar").empty().text("数据在加载中，请稍候....").css("display","block");
		            $.post("ajax/loginfo/loadLogInfo.do",{"startIndex":startIndex},function(data)
	        		{
	        			
	        			var loadIndex = data.startIndex;
	        			showListData(data.data);
	        			if(-1 == data.hasNext)
	        			{
	        				$("#pagebar").empty().html("精彩内容到此为止！<a href='tag/'>发现关注更多内容</a>");
	        			}else
	        			{
	        				$("#pagebar").empty().html(loadIndex).css("display","none");
		        			stop=true;
		        			
	        			}
	        			num++;
	        		});
	        
				}
				
	            
            }else if(num >= maxnum)  ///自动加载超过20次后，就显示分页按钮
            {
            	if(stop==true)
				{
		            stop=false;
		            myRequest($("#pagebar").text());
		            $.post("ajax/loginfo/loadLogInfo.do",{"startIndex":$("#pagebar").text()},function(data)
	        		{
	        			
		            	var loadmore = data.loadMore;
		            	showListData(data.data);
	        			$("#pagebar").empty().html(loadmore);
	        			//stop=true;
	        		});
	        
				}
            }
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

	
	function defaultWord(){
		$("#tag").FocusBlur();
	}
	

	//加载日志
	function loadLogInfo()
	{
		myRequest(0);
		/*$.post("ajax/loginfo/loadLogInfo.do",{},function(data,status)
		{
			
		});*/
	}
	
	
		
	$(function()
	{
		init();
		///顶部导航产生下划线
		$(".home_navi").mouseover(function()
		{
			$(this).css(
			{
				"background-color":"#F9F9F9",
				"border-bottom":"2px solid #3DA8CC"
			});
			return false;
		}).mouseout(function()
		{
			$(this).css(
			{
				"background-color":"#FFF",
				"border-bottom":"0px solid #3DA8CC"
			});
			return false;
		});

		///将img前面的p标签去掉
		$(".info_detail img").parent("p").each(function()
		{
			var xx=$(this).html();
			$(this).replaceWith(xx);
						
		});

		/*$("#tagSearch").click(function()
		{
			//alert($("#tag").val());
		});*/
		loadLogInfo();
		///自动加载更多
		autoLoadMore();
		//自动下拉提示
		//tagComplete();
		defaultWord();
		
		headimgHover();
	});