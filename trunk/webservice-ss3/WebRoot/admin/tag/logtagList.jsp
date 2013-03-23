<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>标签列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/admin.js"></script>
<script type="text/javascript" src="<%=basePath%>js/page.js"></script>

<SCRIPT type="text/javascript">
	$(function()
	{
		//$(".logtag").focus();
	});
</SCRIPT>
  </head>
  
  <body>
<form name="form" id="form" method="post" action="admin/tag/logTagSearch.do">
 <input type="hidden" id="startIndex" name="startIndex" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td height="30">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="images/nav04.gif">
		   	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		    <tr>
			  <td width="21"><img src="images/ico07.gif" width="20" height="18" /></td>
			  <td width="538">标签名：
				<input name="logTagName" value="<s:property value='logTagName'/>" type="text" size="12"/>
				<input name="Submit4" type="submit" class="right-button02" value="查 询" />
				<a href="admin/tag/gotoAddLogtag.do">添加标签</a>
			  </td>
			 
		    </tr>
          	</table>
          </td>
        </tr>
    	</table>
    </td>
    </tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20"><span class="newfont07">选择：<a href="javascript:selectAll();" class="right-font08">全选</a>-<a href="javascript:unselectAll();" class="right-font08">反选</a></span>
	              <input name="Submit" type="button" class="right-button08" id="delButton" url="admin/tag/deleteAll.do" value="删除所选" /></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="7" align="center" style="font-size:16px">日志标签列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">选择</td>
                    <td width="10%">标签名称</td>
					<td width="10%">创建时间</td>
					<td width="12%">操作</td>
                  </tr>
                  <s:iterator value="logTagList" id="logtag">
                  <tr bgcolor="#FFFFFF">
				    <td height="20"><input type="checkbox" class="delid" delId="<s:property value="#userLogin.id"/>" name="delid"/></td>
                    <td ><s:property value="#logtag.tagName"/></td>
					<td><s:property value="#logtag.tagCreateTime"/></td>
                    <td><a href="editrenwu.htm">编辑|</a><a href="admin/tag/logTagDetail.do?tagId=<s:property value='#logtag.id'/>">查看|</a>
					<a href="admin/tag/deleteByLogTagId.do?tagId=<s:property value='#logtag.id'/>" onclick="return myConfirm()">删除</a></td>
                  </tr>
				  </s:iterator>
            </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
           <td align="right">&nbsp;<div id="pageToolbar"><s:property value="paginationSupport.jqueryPage" escape="false"/></div></td>
        </tr>
          
      </table></td>
  </tr>
</table>
</td>
</tr>
</table>
</form>
  </body>
</html>
