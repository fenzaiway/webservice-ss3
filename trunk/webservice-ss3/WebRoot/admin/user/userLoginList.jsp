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
    
    <title>My JSP 'userList.jsp' starting page</title>
    
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script></head>
<SCRIPT type="text/javascript">
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

function link(){
    document.getElementById("fom").action="addrenwu.htm";
   document.getElementById("fom").submit();
}
	$(function()
	{
		$("#delButton").click(function()
		{
			//$(".delid:checked").each(function(){alert($(this).attr("delId"))});
			//var deleteIds = new Array();
			var deleteIds = "";///1,2,3形式传递到后台通过split解析
			var length = $(".delid:checked").length;
			for(var i=0; i<length; i++){
				//alert($(".delid:checked:eq("+ i +")").attr("delId"));
				//deleteIds[i] = $(".delid:checked:eq("+ i +")").attr("delId");
				deleteIds+=$(".delid:checked:eq("+ i +")").attr("delId");
				deleteIds+=","
			}
			//alert(deleteIds);
			$.post("admin/user/deleteAll.do",{"deleteIds":deleteIds},function(data,status){alert(data+"   " + status)});
		});
	});

</SCRIPT>

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
			  <td width="538">用户名：
				<input name="userLogin.username" type="text" size="12"/>
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
	              <input name="Submit" type="button" class="right-button08" id="delButton" value="删除所选" /></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="7" align="center" style="font-size:16px">用户列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">选择</td>
                    <td width="10%">用户昵称</td>
					<td width="10%">邮箱</td>
                    <td width="10%">注册时间</td>
					<td width="10%">账号是否可用</td>
					<td width="5%">优先级</td>
					<td width="12%">操作</td>
                  </tr>
                  <s:iterator value="userLoginList" id="userLogin">
                  <tr bgcolor="#FFFFFF">
				    <td height="20"><input type="checkbox" class="delid" delId="<s:property value="#userLogin.id"/>" name="delid"/></td>
                    <td ><s:property value="#userLogin.username"/></td>
					<td><s:property value="#userLogin.account"/></td>
                    <td><s:property value="#userLogin.createTime"/></td>
                    <td>
                    	<s:if test="#userLogin.enabled==1">可用</s:if>
                    	<s:else><span style="color:red;">不可用</span></s:else>
                    </td>
                    <td>急</td>
                    <td><a href="editrenwu.htm">编辑|</a><a href="listrenwumingxi.htm">查看|</a>
					<a href="admin/user/deleteById.do?userLogin.id=<s:property value='#userLogin.id'/>">删除</a></td>
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
