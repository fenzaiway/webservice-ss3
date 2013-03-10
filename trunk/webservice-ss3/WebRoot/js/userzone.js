//var username;
	var position = 0;
	var tagHtml;
	var commentHtml = "<div class='comment_info'><div class='comment_info_input'>";
	commentHtml+="<div class='comment_text_div'><div class='comment_text' contenteditable='true'></div>";
	commentHtml+="</div>";
	commentHtml+="<div><input type='button' class='comment_button' logid='' position='' value='发表'/></div> <div class='clr'>";
	commentHtml+="<div class='comment_position'></div>"		
	commentHtml+="</div></div>";
	commentHtml+="<div class='back_comment' position='' >收起按钮</div></div>";
	////加标签html	
	var addTagHtml = "<div class='addTagss'><input type='text' value='' class='addTagInput' name='tagName' /><input type='button' position='' logid='' class='addTagBut' value='添加'/></div><div class='clr'></div>";
	function showListData(data)
	{
		var html = "";
		$("#pagebar").remove();
		var headImg = "";
		
		for(var i=0;i<data.length;i++)
		{
			if(""==data[i].headImgUrl)
			{
				headImg = "images/111.jpg";
			}else
			{
				headImg = data[i].headImgUrl;
			}
			
			//判断是否文章包含Tag
			tagHtml = "";
			if(typeof(data[i].tags)=="undefined")
			{
				tagHtml+="<li><div class='addtags' position='"+position+"'>加标签</div></li>";
			}else
			{
				for(var j=0; j<data[i].tags.length; j++)
				{
					tagHtml+="<li><a href='tag/"+data[i].tags[j]+"'>#"+data[i].tags[j]+"</a></li>";
				}
			}
			
			html+="<div class='loginfo_list_left'>";
				html+="<div class='headImg'>";
				html+="<img src='"+headImg+"' alt='头像' />";
				html+="</div>";
					html+="<div class='info' logid="+data[i].logid+">";
					html+="<div><span class='info_user'>"+data[i].username+"</span><span class='info_time'>"+data[i].publishTime+"</span></div>";
					html+="<div class='clr'><h4><a href='loginfo/viewmore.do?zoneuser="+username+"&logInfoid="+data[i].logid+"' title='"+data[i].logTitle+"'>"+data[i].logTitle+"</a></h4></div>";
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
					html+="<li>热度("+data[i].hotNum+")</li>";
					html+="<li>转载("+data[i].reprintNum+") </li>";
					html+="<li><a href='javascript:void(0)' class='my_info_comment'  position="+position+">评论(<span class='commentNum'>"+data[i].commentNum+"</span>)</a></li>";
					html+="<li><img src='images/like1.gif' class='likeimg' alt='like'/>喜欢("+data[i].likeNum+")</li>";
					html+="</ul>";
					html+="</span></div>";
					html+="</div></div>";
			html+="<div class='clr'></div>";
			html+=commentHtml;
			html+="<div class='clr'></div>";
			html+="</div>";
			
			
			html+="</div>";	
			position++;
		}
		html+="<div class='clr'></div>";
		html+="<div id='pagebar' style='display:block;text-align:center'></div>";
		$("#loginfo_list").append(html);
		
	}
	
	////回复评论
	function replay(commentid, logid,index)
	{
		var text = $(".comment_list_comment_info a:eq("+index+")").text();
		text = "回复" + text+":"
		//alert(commentid+"   " + logid);
		$(".comment_text:eq("+index+")").text(text);
	}
	
	function getcommentText(data,logid,index)
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
		commentListHtml+= " <div class='comment_list_reply'><a href='javascript:void(0);' onclick='replay("+data.id+","+logid+","+index+")'>回复</a></div>";
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
					commentListHtml+=getcommentText(data[i],logid,index);
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
			var commentText = $(".comment_text").text();
			var logid = $(this).attr("logid");
			var index = $(this).attr("position");
			$.post("ajax/comment/save.do",{"commentText":commentText,"logid":logid},function(data)
			{
				$(".comment_position:eq("+index+")").prepend(getcommentText(data,logid,index)); ////向评论列表中添加一条最新的评论数据
				var commentNum = parseInt($(".commentNum:eq("+index+")").text()); //获取原来的清理数量
				$(".commentNum:eq("+index+")").text(commentNum+1); ///更新评论数量
				$(".comment_text:eq("+index+")").empty().focus(); ///将输入框设置为空，并添加焦点
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
			removeAddTag(index); ////移除标签文本框
		},
		function()
		{
			showAddTag(index);
		});
	});
	
	//改变喜欢图标样式
	$(".likeimg").live("mouseover",function()
	{
		$(this).attr("src","images/like2.gif");
	}).live("mouseout",function()
	{
		$(this).attr("src","images/like1.gif");
	});
	
	$(".my_info_comment").live("click",function()
	{
		var index = $(this).attr("position");
		var logid = $(".info:eq("+index+")").attr("logid");
		$(".comment_info:eq("+index+")").find(".comment_button").attr("logid",logid);
		$(".comment_info:eq("+index+")").find(".comment_button").attr("position",index);
		//alert($(".comment_list:eq("+index+")").text());
		////为了避免多次请求，如果列表为空，就异步请求评论数据
		
		$(".comment_info:eq("+index+")").slideToggle('fast',function()
		{
			removeAddTag(index);////将评论框移除
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
	        				$("#pagebar").empty().html("精彩内容到此为止！<a href=''>发现关注更过内容</a>");
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

	//给标签添加下划线
	function addTasUnderline()
	{
		//给标签添加下划线
		$(".my_rec_sub li,.rec_sub li").mouseover(function()
		{
			$(this).css(
			{
				"background-color":"#F5F5F5",
				"border-bottom":"2px solid #3DA8CC"
			});
		}).mouseout(function()
		{
			$(this).css(
			{
				"background-color":"#F9F9F9",
				"border-bottom":"1px solid #ccc"
			});
		});
	}

	function loadOtherTags()
	{
		//alert(username);
		//根据用户请求用户还没有关注的标签
		$.post("ajax/tag/changeTag.do",{"username":username},function(data)
		{
					//alert(data);
			var html = "";
			for(var i=0; i<data.length;i++)
			{
				html+="<li tagid="+data[i].id+">";
				html+=data[i].tagName;
				html+="<a href='javascript:userSubTag("+data[i].id+")'>订阅</a>"
				html+="</li>";
			}
			$(".rec_sub").empty().html(html);
			addTasUnderline();
		});
	}
	
	////根据当前用户加载该用户订阅的标签
	function loadUserTags()
	{
		$.post("ajax/tag/loadUserTags.do",{"username":username},function(data)
		{
			var html = "";
			for(var i=0; i<data.length;i++)
			{
				html+="<li>";
				html+=data[i][0].tagName;
				html+="<a href='javascript:userCancelSubTag("+data[i][0].id+")'>取消订阅</a>"
				html+="</li>";
			}
			$(".my_rec_sub").empty().html(html);
			addTasUnderline();
		});
	}
	
	///当前标签消失
	function removeTagHover(index)
	{
		$(".rec_sub li").each(function()
		{
			//if()
			//alert(index);
			if(index == $(this).attr("tagid"))
			{
				$(this).remove();
			}
		});
		
	}

	//订阅标签
	function userSubTag(id)
	{
		
		///用户订阅标签，订阅成功后重新加载另一批用户还没有订阅的标签，同时加载用户已经订阅的标签
		$.post("ajax/tag/saveTag.do",{"username":username,"tagid":id},function(data,status)
		{
			if("success" == status)
			{
				
				removeTagHover(id);
				///加载推荐订阅的标签
				loadOtherTags();	
				///加载当期用户订阅的标签
				loadUserTags();
			}
		});
	}	
	
	///取消订阅标签
	function userCancelSubTag(id)
	{
		$.post("ajax/tag/cancelUserSubTag.do",{"tagid":id},function(data,status)
		{
			if("success" == status)
			{
				
				//removeTagHover(id);
				///加载推荐订阅的标签
				loadOtherTags();	
				///加载当期用户订阅的标签
				loadUserTags();
			}
		});
	}

	//加载日志
	function loadLogInfo()
	{
		myRequest(0);
		/*$.post("ajax/loginfo/loadLogInfo.do",{},function(data,status)
		{
			
		});*/
	}
	////在这个函数中加载数据
	function init()
	{
		///加载推荐订阅的标签
		loadOtherTags();	
		///加载当期用户订阅的标签
		loadUserTags();
		loadLogInfo();
		autoLoadMore();
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
		}).mouseout(function()
		{
			$(this).css(
			{
				"background-color":"#FFF",
				"border-bottom":"0px solid #3DA8CC"
			});
		});

		///将img前面的p标签去掉
		$(".info_detail img").parent("p").each(function()
		{
			var xx=$(this).html();
			$(this).replaceWith(xx);
						
		});

		
	});