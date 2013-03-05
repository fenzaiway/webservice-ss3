//var username;
	var position = 0;
	var commentHtml = "<div class='comment_info'><div class='comment_info_input'>";
	commentHtml+="<div class='comment_text_div'><div id='comment_text' contenteditable='true'></div>";
	commentHtml+="</div>";
	commentHtml+="<div><input type='button' id='comment_button' value='发表'/></div> <div class='clr'></div></div>";
	commentHtml+="<div class='back_comment'>收起按钮</div>"
	
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
			
			html+="<div class='loginfo_list_left'>";
				html+="<div class='headImg'>";
				html+="<img src='"+headImg+"' alt='头像' />";
				html+="</div>";
					html+="<div class='info'>";
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
					html+="<li>#科学</li>";
					html+="<li>#数码 </li>";
					html+="<li>#科技</li>";
					html+="<li>#硬件</li>";
					html+="</ul>";
					html+="</span>";
					html+="<span class='info_like'>";
					html+="<ul>";
					html+="<li>热度("+data[i].hotNum+")</li>";
					html+="<li>转载("+data[i].reprintNum+") </li>";
					html+="<li><a href='javascript:void(0)' class='my_info_comment' position="+position+">评论("+data[i].commentNum+")</a></li>";
					html+="<li>喜欢("+data[i].likeNum+")</li>";
					html+="</ul>";
					html+="</span></div>";
					html+="</div></div>";
			html+="<div class='clr'></div>";
			html+=commentHtml;
			html+="</div>";
			
			
			html+="</div>";	
			position++;
		}
		html+="<div class='clr'></div>";
		html+="<div id='pagebar'></div>";
		$("#loginfo_list").append(html);
		
	}
	
	$(".my_info_comment").live("click",function()
	{
		$(".comment_info:eq("+$(this).attr("position")+")").slideToggle('fast');
		
	});
	
	$(".back_comment").live("click",function()
	{
		//alert('aa');
		//$(".comment_info:eq("+$(this).attr("position")+")").css("display","none");
		$(".comment_info").css("display","none");
		
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
				//showComment(data.items);
				//showLogInfo(data.items);
				//alert(data.data);
				showListData(data.data);
			//	$("#pagebar").empty().html(pager);
				$("#pagebar").empty().html(loadmore);
				//$("#page").html(pager);
				
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