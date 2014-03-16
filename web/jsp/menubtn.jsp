<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="newx.framework.FrameworkService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=FrameworkService.getInstance().getTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#pic").click(function(){
        if($("#showed").val() == "true") {
            $(window.parent.document).find("#MenuFrameset").attr("cols", "10,*");
            $(window.parent.document).find("#NavFrame").attr("cols", "0,10");
            $("#showed").val("false");
            $("#pic").attr("src", "<%=request.getContextPath()%>/images/show_left.gif");    
            $("#pic").attr("title", "显示导航菜单");
        } else {
            $(window.parent.document).find("#MenuFrameset").attr("cols", "161,*");
            $(window.parent.document).find("#NavFrame").attr("cols", "151,10");
            $("#showed").val("true");
            $("#pic").attr("src", "<%=request.getContextPath()%>/images/hide_left.gif");
            $("#pic").attr("title", "隐藏导航菜单");
        }
    });
});
</script>
<body bgcolor="#ADB6C6" background="<%=request.getContextPath()%>/images/scrollbar_bg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin-left:0px">
<table width="75%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><img id="pic" name="pic" style="cursor:pointer" src="<%=request.getContextPath()%>/images/hide_left.gif" width="11" height="50" title="隐藏导航菜单"></td>
  </tr>
</table>
<input id="showed" name="showed" type="hidden" value="true" width="0">
</body>
</html>
