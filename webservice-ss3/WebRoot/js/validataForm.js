// JavaScript Document
$(function(){
	//$(".registerform").Validform();  //����һ�д��룡;
	
	var getInfoObj=function(){
			return 	$(this).parents("td").next().find(".info");
		}
	
	$("[datatype]").focusin(function(){
		if(this.timeout){clearTimeout(this.timeout);}
		var infoObj=getInfoObj.call(this);
		if(infoObj.siblings(".Validform_right").length!=0){
			return;	
		}
		infoObj.show().siblings().hide();
		
	}).focusout(function(){
		var infoObj=getInfoObj.call(this);
		this.timeout=setTimeout(function(){
			infoObj.hide().siblings(".Validform_wrong,.Validform_loading").show();
		},0);
		
	});
	
	$(".registerform").Validform({
		tiptype:2,
		usePlugin:{
			
			//��������ǿ��У����
			passwordstrength:{
				minLen:6,
				maxLen:18,
				trigger:function(obj,error){
					if(error){
						obj.parent().next().find(".passwordStrength").hide().siblings(".info").show();
					}else{
						obj.removeClass("Validform_error").parent().next().find(".passwordStrength").show().siblings().hide();	
					}
				}
			}
		}
	});
})
