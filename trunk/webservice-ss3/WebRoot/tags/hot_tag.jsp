<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/top_navi.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" href="<%=basePath%>css/jquery.bigautocomplete.css" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.bigautocomplete.js"></script>
<script type="text/javascript" src="<%=basePath%>js/subTag.js"></script>
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
			tagComplete();
			searchTag();
		});
	</script>



  </head>
  
  <body>
	<div style="margin-top: 100px;"></div>
	<div id="tags_main">
	<div id="tags_top_scroll">
    	<div class="scroll_imgs">
		<img src="${ctx }/images/hottag_img/7.jpg" alt=""/>
		<cite>文字</cite>
		</div>
    	<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/1.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/2.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/3.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/4.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/5.jpg" alt=""/></div>
    	<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/6.jpg" alt=""/></div>

		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/8.jpg" alt=""/></div>
		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/9.jpg" alt=""/></div>
		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/10.jpg" alt=""/></div>
		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/11.jpg" alt=""/></div>
		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/12.jpg" alt=""/></div>
		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/13.jpg" alt=""/></div>
		<div class="scroll_imgs"><img src="${ctx }/images/hottag_img/14.jpg" alt=""/></div>
      
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/15.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/16.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/17.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/18.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/19.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/20.jpg" alt=""/></div>
        <div class="scroll_imgs"><img src="${ctx }/images/hottag_img/21.jpg" alt=""/></div>
        
        
    </div>
	<div id="tags_buttom_list">
    	<div id="tags_search">
        	<input type="text" name="tagName" id="tag" class="tagSearch" value="搜索您感兴趣的标签"  style="width:360px; margin-top:10px; height:30px;"/><input type="button" value="搜索" id="tagSubmit" style="width: 50px;background-color:#94B600 ;margin-top:10px; height:34px;line-height: 34px;border: 0px solid;"/>
        </div>
        <div id="tags_list_detail">
        	<table width="100%" cellpadding="0" cellspacing="0" id="tags_detail_table">
            	<th class="hot_tags"><span>热门标签</span></th><th class="similar_tags"><span>相关标签</span></th><th class="hot_spaces">热门空间</th>
                <s:iterator value="tagSpaceList" id="tagSpace">
				<tr>
                	<td class="hot_tags"><a href="tag/<s:property value="#tagSpace.tag.tagName"/>"><s:property value="#tagSpace.tag.tagName"/></a></td>
					<td class="similar_tags">
						<s:iterator value="#tagSpace.logTagList" id="tag">
						<a href="tag/<s:property value="#tag.tagName"/>"><s:property value="#tag.tagName"/></a>
                		</s:iterator>
					</td>
						<td class="hot_spaces">
							<s:iterator value="#tagSpace.spaceList" id="space">
									<div>
										<s:if test='#space.img==""'>
											<img class="space_img" src="http://tp4.sinaimg.cn/1370795567/180/5654876831/1"/>
										</s:if>
										<s:else>
											<img class="space_img" src="${ctx}/<s:property value='#space.img'/>"/>
										</s:else>
										
									</div>
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
