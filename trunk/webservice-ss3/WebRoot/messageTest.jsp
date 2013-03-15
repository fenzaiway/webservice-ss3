<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
        <title>MSN 弹出消息框</title>
<script language="javascript" type="text/javascript">
function MessShow(id,width,height,caption,title,message,target,action) 
{ 
    this.id         = id; 
    this.title      = title; 
    this.caption    = caption; 
    this.message    = message; 
    this.target     = target; 
    this.action     = action; 
    this.width      = width?width:250; 
    this.height     = height?height:150; 
    this.timeout    = 250;      //消息停留时间
    this.speed      = 10;       //消息速度，越小越快
    this.step       = 2;        //移动步长
    this.right      = screen.width -1; 
    this.bottom     = screen.height;
    this.left       = this.right - this.width;
    this.top        = this.bottom - this.height;
    this.timer      = 0;
    this.pause      = false;
    this.close      = false;
    this.autoHide   = true;
}
MessShow.prototype.hide = function() 
{ 
    if(this.onunload())
    { 
        var offset = this.height>this.bottom-this.top?this.height:this.bottom-this.top;
        var me = this; 
        if(this.timer>0)
        { 
            window.clearInterval(me.timer); 
        } 
        var fun = function()
        { 
            if(me.pause==false||me.close)
            {
                var x = me.left;
                var y = 0;
                var width = me.width;
                var height = 0;
                if(me.offset>0){
                    height = me.offset;
                }    
                y = me.bottom - height;    
                if(y>=me.bottom){
                    window.clearInterval(me.timer); 
                    me.Pop.hide(); 
                } else {
                    me.offset = me.offset - me.step; 
                }
                me.Pop.show(x,y,width,height);   
            }            
        } 
        this.timer = window.setInterval(fun,this.speed)     
    } 
} 
//消息卸载事件，可以重写
MessShow.prototype.onunload = function() 
{ 
    return true; 
} 
// 消息命令事件，要实现自己的连接，请重写它 
MessShow.prototype.oncommand = function() 
{ 
    window.open(this.action,this.target);
    this.hide(); 
} 
// 消息显示方法 
MessShow.prototype.show = function() 
{ 
    var oPopup = window.createPopup(); //IE5.5+     
    this.Pop = oPopup;   
    var w = this.width; 
    var h = this.height; 
    var str = "<DIV style='BORDER-RIGHT:#005FEE 1px solid; BORDER-TOP:#005FEE 1px solid; Z-INDEX: 99999; LEFT: 0px; BORDER-LEFT:#005FEE 1px solid; WIDTH: " + w + "px; BORDER-BOTTOM:#005FEE 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: " + h + "px; BACKGROUND-COLOR:#FFFFFF'>" 
        str += "<TABLE style='BORDER-TOP: #FFFFFF 1px solid; BORDER-LEFT: #FFFFFF 1px solid' cellSpacing=0 cellPadding=0 width='100%' bgColor=#FFFFFF border=0>" 
        str += "<TR>" 
        str += "<TD style='FONT-SIZE: 12px;COLOR: #0052CC' width=30 height=24>∵</TD>" 
        str += "<TD style='PADDING-LEFT: 4px; FONT-WEIGHT: normal; FONT-SIZE: 12px; COLOR:#0052CC; PADDING-TOP: 4px' valign=middle width='100%'>" + this.caption + "</TD>" 
        str += "<TD style='PADDING-RIGHT: 2px; PADDING-TOP: 2px' valign=middle align=right width=19>" 
        str += "<SPAN title=关闭 style='FONT-WEIGHT: bold; FONT-SIZE: 12px; CURSOR: hand; COLOR: red; MARGIN-RIGHT: 4px' id='btSysClose' >×</SPAN></TD>" 
        str += "</TR>" 
        str += "<TR>" 
        str += "<TD style='PADDING-RIGHT: 1px;PADDING-BOTTOM: 1px' colSpan=3 height=" + (h-28) + ">" 
        str += "<DIV style='BORDER-RIGHT: FFFFFF 1px solid; PADDING-RIGHT: 8px; BORDER-TOP:#66A3FF 1px solid; PADDING-LEFT: 8px; FONT-SIZE: 12px; PADDING-BOTTOM: 8px; BORDER-LEFT:#FFFFFF 1px solid; WIDTH: 100%; COLOR:#FFFFFF; PADDING-TOP: 8px; BORDER-BOTTOM:#FFFFFF 1px solid; HEIGHT: 100%'><FONT color=#EE0000>" + this.title + "</FONT><BR><BR>" 
        str += "<DIV style='WORD-BREAK: break-all' align=left><A href='javascript:void(0)' hidefocus=true id='btCommand'><FONT color=#EE0000>" + this.message + "</FONT></A></DIV>" 
        str += "</DIV>" 
        str += "</TD>" 
        str += "</TR>" 
        str += "</TABLE>" 
        str += "</DIV>"
    oPopup.document.body.innerHTML = str; 
    this.offset = 0;
    var me = this; 
    oPopup.document.body.onmouseover = function(){me.pause=true;}
    oPopup.document.body.onmouseout = function(){me.pause=false;}
    var fun = function()
    { 
        var x = me.left;
        var y = 0;
        var width    = me.width;
        var height    = me.height;
            if(me.offset>me.height)
            {
                height = me.height;
            } else
            {
                height = me.offset;
            }
        y = me.bottom - me.offset;
        if(y<=me.top)
        {
            me.timeout--;
            if(me.timeout==0)
            {
                window.clearInterval(me.timer); 
                if(me.autoHide)
                {
                    me.hide();
                }
            }
        }
        else
        {
            me.offset = me.offset + me.step;
        }
        me.Pop.show(x,y,width,height); 
    }   
    this.timer = window.setInterval(fun,this.speed) 
    var btClose = oPopup.document.getElementById("btSysClose"); 
    btClose.onclick = function()
    { 
        me.close = true;
        me.hide(); 
    } 
    var btCommand = oPopup.document.getElementById("btCommand"); 
    btCommand.onclick = function()
    { 
        me.oncommand(); 
    } 
} 
// 设置速度方法
MessShow.prototype.speed = function(s)
{
    var t = 10;
    try
    {
        t = praseInt(s);
    }
    catch(e){}
    this.speed = t;
}
// 设置步长方法
MessShow.prototype.step = function(s)
{
    var t = 2;
    try
    {
        t = praseInt(s);
    }
    catch(e){} 
    this.step = t;
} 
MessShow.prototype.rect = function(left,right,top,bottom)
{
    try
    {
        this.left    = left?left:0;
        this.right    = right?right:screen.availWidth -1;
        this.top    = top?top:0;
        this.bottom = bottom?bottom:screen.availHeight;
    }
    catch(e)
    {}
}

      function load()
      {         
         var msg = new MessShow("hello",250,150,"信息","小马，你好！","QQ:******* 请求加为好友！","_bank",'http://www.baidu.com'); 
         msg.show(); 
      }
    </script>
</head>
<body onLoad="load()">
    <form id="form1" runat="server">
    <div>
     
    </div>
    </form>
</body>

</html>