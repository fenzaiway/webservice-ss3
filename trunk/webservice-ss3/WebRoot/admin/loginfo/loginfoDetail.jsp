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

  </head>
  
  <body>
    <form name="fom" id="fom" method="post" action="admin/user/search.do">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
    <td height="30">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="images/nav04.gif" >
		   &nbsp;
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
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 	<tr>
                    	<td height="20" colspan="2" bgcolor="#EEEEEE"class="tablestyle_title">
							<div align="center" style="font-size:16px">日志详细信息</div>						</td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">标题:</td>
	                    <td width="84%">
							<a href='loginfo/viewmore.do?zoneuser=<s:property value="logInfoData.username"/>&logInfoid=<s:property value="logInfoData.logid"/>' title='<s:property value="logInfoData.logTitle"/>' target="_blank"><s:property value="logInfoData.logTitle"/></a>
						</td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">用户名:</td>
	                    <td width="84%"><s:property value="logInfoData.username"/></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">创建时间:</td>
	                    <td width="84%"><s:property value="logInfoData.publishTime"/></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">热度数:</td>
	                    <td width="84%"><s:property value="logInfoData.hotNum"/></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">评论数:</td>
	                    <td width="84%"><s:property value="logInfoData.commentNum"/></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">喜欢数:</td>
	                    <td width="84%"><s:property value="logInfoData.likeNum"/></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">转载数:</td>
	                    <td width="84%"><s:property value="logInfoData.reprintNum"/></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">日志对应标签:</td>
	                    <td width="84%">
							<s:iterator value="logInfoData.tags" id="tag">
								<s:property value="tag"/>、
							</s:iterator>
						</td>
                    </tr>
                    <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">日志图片:</td>
	                    <td width="84%"><s:property value="logTagDetail.logNum"/></td>
                    </tr>
					 <tr bgcolor="#FFFFFF" height="20">
					    <td width="16%" align="right">内容:</td>
	                    <td width="84%"><s:property value="logInfoData.logContent" escape="false"/></td>
                    </tr>
            	</table>
				</td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</td>
</tr>
</table>
</form>
  </body>
</html>
