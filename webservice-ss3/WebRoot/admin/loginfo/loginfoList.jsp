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
    
    <title>日志列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/admin.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/admin.js"></script>



  </head>
  
  <body>
    <form name="fom" id="fom" method="post" action="admin/user/search.do">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td height="30">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="images/nav04.gif">
		   	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		    <tr>
			  <td width="21"><img src="images/ico07.gif" width="20" height="18" /></td>
			  <td width="538">日志标题：
				<input name="userLogin.username" value="<s:property value='userLogin.username'/>" type="text" size="12"/>
				<input name="Submit4" type="submit" class="right-button02" value="查 询" />
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
	              <input name="Submit" type="button" class="right-button08" id="delButton" url="admin/tag/deleteAll.do" value="删除所选" /><div id="pageToolbar" style="float: right"><s:property value="paginationSupport.pageToolBar" escape="false"/></div></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="12" align="center" style="font-size:16px">日志标签列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">选择</td>
                    <td width="8%">日志标题</td>
                    <td width="8%">发表用户</td>
					<td width="15%">日志内容</td>
					<td width="8%">发表时间</td>
					<td width="8%">发表状态</td>
					<td width="8%">评论状态</td>
					<td width="8%">是否原创</td>
					<td width="8%">置顶状态</td>
					<td width="8%">删除状态</td>
					<td width="8%">所属类型</td>
					<td width="7%">操作</td>
                  </tr>
                 <s:iterator value="logInfoList" id="loginfo">
                <tr bgcolor="#FFFFFF">
		    	  <td height="20"><input type="checkbox" class="delid" delId="<s:property value="#loginfo.id"/>" name="delid"/></td>
                  <td >
					<a href='loginfo/viewmore.do?zoneuser=<s:property value="#loginfo.username"/>&logInfoid=<s:property value="#loginfo.id"/>' title='<s:property value="#loginfo.logTitle"/>' target="_blank"><s:property value="#loginfo.logTitle"/></a>
					</td>
                  <td ><s:property value="#loginfo.username"/></td>
                  <td ><s:property value="#loginfo.logText"/></td>
                  <td ><s:property value="#loginfo.logPublishTime"/></td>
                  <td >
					<s:if test="1 == #loginfo.logContentStatus">
						发布
					</s:if>
					<s:elseif test="2 == #loginfo.logContentStatus">
						代发布
					</s:elseif>
				  </td>
                  <td >
					<s:if test="1 == #loginfo.logAllowComment">
						任何人
					</s:if>
					<s:elseif test="2 == #loginfo.logAllowComment">
						仅好友
					</s:elseif>
					<s:else>禁止评论</s:else>
					</td>
                  <td >
					<s:if test="1 == #loginfo.logIsOriginal">
						原创
					</s:if>
					<s:elseif test="2 == #loginfo.logIsOriginal">
						转载
					</s:elseif>
					<s:else>其他</s:else>					
					</td>
                  <td >
					<s:if test="1 == #loginfo.logToTop">
						置顶
					</s:if>
					<s:elseif test="0 == #loginfo.logToTop">
						不置顶
					</s:elseif>
					</td>
                  <td >
					<s:if test="1 == #loginfo.deleteStatue">
						<span style="color:red">有问题</span>
					</s:if>
					<s:elseif test="0 == #loginfo.deleteStatue">
						正常
					</s:elseif>
					</td>
                  <td ><s:property value="#loginfo.logType.logTypeName"/></td>
                  <td><a href="editrenwu.htm">编辑|</a><a href="admin/loginfo/viewLoginfo.do?loginfoid=<s:property value='#loginfo.id'/>">查看|</a>
			<a href="admin/tag/deleteByLogTagId.do?tagId=<s:property value='#logtag.id'/>" onclick="return myConfirm()">删除</a></td>
                </tr>
		  </s:iterator>
            </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
 			
        <tr>
           <td align="right">&nbsp;<div id="pageToolbar"><s:property value="paginationSupport.pageToolBar" escape="false"/></div></td>
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
