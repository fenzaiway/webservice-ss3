/*
 *	feedTags
 *	轻博客标签
 *	作者：深蓝蝴蝶 2012-3-29
 *	
 *	tags: 	//值域对象
 *	isview: 	//模式，true-view模式、false-eidt模式

 *	$('#feedTags').feedTags({
 *		tags:$('#tags')
 *	});
 *	<div id="feedTags"></div>
 *	<input type="hidden" value="saaa,关键字abc" id="tags" />
 *	
 */
(function($){
	var defaults={
		tags:null,//值域对象
		isview:false//模式，true-view模式、false-eidt模式
	};

	$.fn.feedTags = function(opt){
		var options = $.extend({},defaults, opt||{});
		var tagmain = $(this);
		tagmain.addClass('feedTags');
		if(options.isview){
			LoadFeedTags(tagmain,options.tags,false);
		}else{
			bindFeedTags(tagmain,options.tags);
		}
	};
	$.fn.feedTagsLoad = function(opt){
		var options = $.extend({},defaults, opt||{});
		var tagmain = $(this);
		LoadFeedTags(tagmain,options.tags,true);
	};

	/*===============显示标签======================*/
	//显示标签
	function LoadFeedTags(tagmain,tags,cleardata) {
		if(cleardata){
			tagmain.empty();
		}
		//绑定数据载入
		var tags_v = tags.val().split(',');
		for (var i = 0, l = tags_v.length; i < l; i++) {
			var v = $.trim(tags_v[i]);
			if (v != '') {
				createShowTags(tagmain,v);
			}
		}
	}

	//构建一个可显示的标签
	function createShowTags(tagmain,v) {
		var tagstr = [];
		tagstr.push('<span class="tagshow">');
		tagstr.push(v);
		tagstr.push('</span>');
		tagstr = tagstr.join('');
		tagmain.append(tagstr);
	}

	/*===============创建标签======================*/
	function bindFeedTags(tagmain,tags) {
		//var tInputObj = $('<input type="text" class="inputTag" />');
		var tInputObj = $('<textarea class="inputTag" rows="1"></textarea>');
		tInputObj.appendTo(tagmain);

		//绑定点击时，输入框获得焦点
		tagmain.click(function(e) {
			tInputObj.focus();
		});
		
		tInputObj.focus(function() {
			$(this).css({ 'border': '0' });
		});

		//绑定输入事件
		//输入完成标记：1-失去焦点
		tInputObj.blur(function() {
			inputTags(tagmain,tInputObj,tags);
		});

		//输入完成标记：2-输入中有逗号(188)、空格(32)
		//输入完成标记：3-粘贴
		tInputObj.keydown(function(evt) {
			evt = evt || window.event;
			var x = evt.keyCode || evt.charCode;
			if (x == 188 || x == 32) {
				inputTags(tagmain,tInputObj,tags);
				//down按下后，up才触发，而这时候键值才会被接受
				window.setTimeout(function() { tInputObj.val(tInputObj.val().replace(/^[,\uFF0C]/, "")); }, 10);
			}
			if (evt.ctrlKey) {
				if (x == 86) {
					//延迟一下，再触发事件
					window.setTimeout(function() { inputTags(tagmain,tInputObj,tags); }, 10);
				}
			}
		});

		//绑定数据载入
		var tags_v = tags.val().split(',');
		for (var i = 0, l = tags_v.length; i < l; i++) {
			var v = $.trim(tags_v[i]);
			if (v != '') {
				createTags(tagmain,tInputObj,tags,v);
			}
		}
	}

	//构建一个标签的代码并绑定事件
	function createTags(tagmain,tInputObj,tags,v) {
		var tagstr = [];
		tagstr.push('<span class="tag">');
		tagstr.push('<a href="javascript:;" title="\u5220\u9664"></a>');
		tagstr.push(v);
		tagstr.push('</span>');
		tagstr = tagstr.join('');
		tagstr = tInputObj.before(tagstr).prev();
		//绑定删除事件
		tagstr.children('a').click(function(evt) {
			$(this).parent().remove();
			setNewTags(tagmain,tags);
		});
	}

	//生成多个标签
	function inputTags(tagmain,tInputObj,tags) {
		var v = $.trim(tInputObj.val());
		if (v != '') {
			var reg = /([,\s\uFF0C]+)/g;
			v = v.replace(reg, ',').split(',');
			for(var i=0,l=v.length;i<l;i++){
				var vv = v[i].replace(/</g,'&lt;').replace(/>/g,'&gt;');
				createTags(tagmain,tInputObj,tags,vv);
			}
		}
		tInputObj.val('');
		setNewTags(tagmain,tags);
	}

	//生成标签隐藏值
	function setNewTags(tagmain,tags) {
		var tags_v = [];
		tagmain.children('span').each(function() {
			tags_v.push($(this).text());
		});
		tags_v = tags_v.join(',');
		tags.val(tags_v);
	}
})(jQuery);
