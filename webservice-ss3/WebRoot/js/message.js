//消息title提醒
var newMessageRemind={   
		_step: 0,   
		_title: document.title,   
		_timer: null,   
		//显示新消息提示   
		show:function(){   
		var temps = newMessageRemind._title.replace("【　　　】", "").replace("【新消息】", "");   
		newMessageRemind._timer = setTimeout(function() {   
		newMessageRemind.show();   
		//这里写Cookie操作   
		newMessageRemind._step++;   
		if (newMessageRemind._step == 3) { newMessageRemind._step = 1 };   
		if (newMessageRemind._step == 1) { document.title = "【　　　】" + temps };   
		if (newMessageRemind._step == 2) { document.title = "【新消息】" + temps };   
		}, 800);   
		return [newMessageRemind._timer, newMessageRemind._title];   
		},   
		//取消新消息提示   
		clear: function(){   
		clearTimeout(newMessageRemind._timer );   
		document.title = newMessageRemind._title;   
		//这里写Cookie操作   
		}   
	}
/**
 * 推送消息
 * @return
 */
function pushMessage()
{
	$.post("ajax/message/getNewMessage.do",{},function(data)
	{
		//alert(data.length);
		var newMessageNum = data.length;
		if(0 != newMessageNum)
		{
			$(".messageNum").html("("+newMessageNum+")");
			newMessageRemind.show(); //调用新消息提醒
			//取消新消息提醒 ：newMessageRemind.clear();
		}
	});
}

//显示消息
function showNewMessage()
{
	$(".messageNum").parent("li").click(function()
			{
				if($(".message").is(":visible"))
				{
					
					$(".message").slideUp(function()
					{
						$(".message").css("display","none");
					});
				}else
				{
						$(".message").slideDown(function()
						{
							$(".message").css("display","block");
							
							if(""!=$(".messageNum").text())
							{
								
								$.post("ajax/message/showMessage.do",{},function(data)
								{
									var length = data.length;
									msgHtml="";
									msgHtml+="<span>最新消息：</span><br/>";
									for(var i=0; i<length; i++)
									{
										msgHtml+=(i+1);
										msgHtml+=".&nbsp;";
										msgHtml+="<span class='msglist'>";
										msgHtml+=data[i].msgContent;
										msgHtml+="</span><br/>";
									}
									$(".messageNum").text(""); //读取最新消息后，把最新消息的个数清空
									newMessageRemind.clear(); //停止标题闪动
									$(".message").empty().html(msgHtml); //显示消息
									//异步更新消息为已读状态、
									$.post("ajax/message/updateMessage.do",{},function(data){});
								});
							}else
							{
								if(""==msgHtml)
								{
									msgHtml="暂时没有最新的消息";
								}
								$(".message").empty().html(msgHtml);
							}
					});
				}
				
	});
}

///对消息进行管理
function getMessage()
{
	/*第一次读取最新通知*/
	setTimeout(function() {
     pushMessage();
	},200);
  	/*30轮询读取函数*/
    setInterval(function() {
        pushMessage();
	}, 30000);
}