function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

//////根据用户选择的条目数进行批量删除
function deleteById()
{
	if(!window.confirm("你确定要删除吗？"))
	{
		return true;
	}
	
	var deleteIds = "";///1,2,3形式传递到后台通过split解析
	var length = $(".delid:checked").length;
	for(var i=0; i<length; i++){
		deleteIds+=$(".delid:checked:eq("+ i +")").attr("delId");
		deleteIds+=","
	}
	var url = $("#delButton").attr("url");
	$.post(url,{"deleteIds":deleteIds},function(data,status){alert(data+"   " + status)});
	
}


$(function()
{
	//////删除操作
	$("#delButton").click(function()
	{
		deleteById();
	});
				
});