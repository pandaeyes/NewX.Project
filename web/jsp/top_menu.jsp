<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="newx.framework.FrameworkService"%>
<%@page import="newx.mod.menu.MenuService"%>
<%@page import="newx.mod.menu.Right"%>
<%@page import="newx.util.SysUtil"%>
<%@page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=SysUtil.getTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="<%=request.getContextPath()%>/css/newx.css" rel="stylesheet">
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/newx.js"></script>
<style type="text/css">
body {height: 100%;}
.breadcrumb {
  height: 35px;
  width: 100%;
  vertical-align:middle;
  margin: 0 0 0px;
  list-style: none;
  background-color: #eceef3;
  background-image: -moz-linear-gradient(top, #ffffff, #a4b0c6);
  background-image: -ms-linear-gradient(top, #ffffff, #a4b0c6);
  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#a4b0c6));
  background-image: -webkit-linear-gradient(top, #ffffff, #a4b0c6);
  background-image: -o-linear-gradient(top, #ffffff, #a4b0c6);
  background-image: linear-gradient(top, #ffffff, #a4b0c6);
  background-repeat: repeat-x;
  border-style: solid;
  border-width: 1px 0px 1px 0px;
  border-color: #a4b0c6;
  /* 
  -webkit-border-radius: 3px;
     -moz-border-radius: 3px;
          border-radius: 3px;
  */
  filter: progid:dximagetransform.microsoft.gradient(startColorstr='#ffffff', endColorstr='#a4b0c6', GradientType=0);
  -webkit-box-shadow: inset 0 1px 0 #ffffff;
     -moz-box-shadow: inset 0 1px 0 #ffffff;
          box-shadow: inset 0 1px 0 #ffffff;
}
.img_border {
	padding-right: 5px;
}

/*平常的状态*/
.link_normal{
    font-size:9pt;
	font-family:宋体;
	color:#006A73;
    text-decoration:none;
}
/*连接被按下的时候 */
.link_hover{
	COLOR: #00872F; 
    font-size:9pt;
    font-family:宋体;
    text-decoration:none;
}
</style>
</head>
<%
	List<Right> menuList = MenuService.getInstance().getTopMenu();
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin-left:0px;margin-right:0px" width="100%" height='100%'>
    <div class="breadcrumb">
        <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" style="padding-top:2px">
            <tr>
                <td><div style="width:151px"></div></td>
                <td nowrap></td>
                <%
                	for (Right right : menuList) {
                %>
                <td nowrap><span fname="fname_top_menu" fid="<%= right.getId()%>"
                class="link_normal"
                onMouseOut="this.className='link_normal';"
                onMouseOver="this.className='link_hover';"
                ><img src="<%= request.getContextPath()%>/images/bof/pageSetup.png"/>&nbsp;<%= right.getRightname() %></span></td>
                <td nowrap width="10px"></td>
                <%
                }
                %>
                <td width="100%"></td>
            </tr>
        <table>
    </div>
</body>
<script type="text/javascript">
var rightList = new Object();
<%
for (Right right : menuList) {
	out.println("rightList[\""+ right.getId() + "\"] = \""+ right.getUrl().replaceAll("\"", "\\\\\"").replaceAll("rootdir", request.getContextPath()) + "\";");
}
%>
$(document).ready(function(){
	$("span[fname=\"fname_top_menu\"]").click(function(){
		var fid = $(this).attr("fid");
		if (!isNull(fid)) {
			$(window.parent.document).find("#navigation").attr("src", "<%=request.getContextPath()%>/jsp/left_menu.jsp?parenId=" + fid);
			if (!isNull(rightList[fid])) {
				$(window.parent.document).find("#workspace").attr("src", rightList[fid]);
			}
		}
    });
});
</script>
</html>
