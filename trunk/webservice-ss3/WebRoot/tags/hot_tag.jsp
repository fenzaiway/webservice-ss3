<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发现精彩-轻轻一点</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>css/hot_tag.css" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		
		jQuery.fn.extend({
	    	FocusBlur: function() {
	        $(this).live('focus',function(){
	            if(!$(this).data('old'))
	                $(this).data('old', $(this).val());
	            
	            if($(this).val()==$(this).data('old'))
	                $(this).val('');
	        });
	        $(this).live('blur',function(){
	            if($(this).val()=='')
	                $(this).val($(this).data('old'));
	        });
	        
	    	}
		});
		
		$(function()
		{
			$(".tagSearch").FocusBlur();
		});
	</script>



  </head>
  
  <body>
	<div style="margin-top: 100px;"></div>
	<div id="tags_main">
	<div id="tags_top_scroll">
    	<div class="scroll_imgs">
		<img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/>
		<cite>文字</cite>
		</div>
    	<div class="scroll_imgs"><img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="http://ww4.sinaimg.cn/thumb300/a25890cejw1e29z110rvhj.jpg" alt=""/></div>
        
       
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://ww3.sinaimg.cn/small/4e2e966bjw1dx4ifj2ox3j.jpg" alt=""/></div>
      
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="http://www.bobd.cn/design/UploadFiles1008/201008/20100812085103687.jpg" alt=""/></div>
        
    </div>
	<div id="tags_buttom_list">
    	<div id="tags_search">
        	<input type="text" name="tagName" class="tagSearch" value="搜索您感兴趣的标签"  style="width:360px; margin-top:10px; height:30px;"/><input type="button" value="搜索" style="width: 50px;background-color:#94B600 ;margin-top:10px; height:34px;line-height: 34px;border: 0px solid;"/>
        </div>
        <div id="tags_list_detail">
        	<table width="100%" cellpadding="0" cellspacing="0" id="tags_detail_table">
            	<th class="hot_tags"><span>热门标签</span></th><th class="similar_tags"><span>相关标签</span></th><th class="hot_spaces">热门空间</th>
                <s:iterator value="tagSpaceList" id="tagSpace">
				<tr>
                	<td class="hot_tags"><a href="tag/<s:property value="#tagSpace.tag.tagName"/>"><s:property value="#tagSpace.tag.tagName"/></a></td>
					<td class="similar_tags">
						<s:iterator value="#tagSpace.logTagList" id="tag">
						<a href=""><s:property value="#tag.tagName"/></a>
                		</s:iterator>
					</td>
						<td class="hot_spaces">
							<s:iterator value="#tagSpace.spaceList" id="space">
							<a href=""><div>
							<s:if test='#space.img==""'>
							<img class="space_img" src="http://tp4.sinaimg.cn/1370795567/180/5654876831/1"/>
							</s:if>
							<s:else>
								<img class="space_img" src="<s:property value='#space.img'/>"/>
							</s:else>
							</div></a>
							</s:iterator>
						</td>
					</tr>
				</s:iterator>
               
             	
            </table>
        </div>
    </div>   
	<div id="tags_buttom">底部</div>
</div>

    <s:debug></s:debug>
  </body>
</html>
