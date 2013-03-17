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
			html+=data[i].tagName;
			html+="<a href='javascript:userCancelSubTag("+data[i].id+")'>取消订阅</a>"
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
			var tagid = $(".unSubBut").attr("tagid");
			if(id == tagid) ///如果取消订阅按钮和链接权限订阅按钮匹配
			{
				var butHTML = "<input type='button' class='subBut' style='margin-top: 23px; margin-left:15px;width:70px; height:35px; line-height:35px;background-color:#94B600; color:#FFFFFF;border:0px solid #EDEDEF;font-size:16px; font-family:微软雅黑;' value='订阅'/>";
				$("#tagSubBut").empty().html(butHTML);
			}
			
			//removeTagHover(id);
			///加载推荐订阅的标签
			loadOtherTags();	
			///加载当期用户订阅的标签
			loadUserTags();
			//alert($("#tagSubBut"));
		}
	});
}

////在这个函数中加载数据
function init()
{
	///加载推荐订阅的标签
	loadOtherTags();	
	///加载当期用户订阅的标签
	loadUserTags();
	
	
}