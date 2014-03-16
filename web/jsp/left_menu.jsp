<%@page contentType="text/html;charset=utf-8" %>
<%@page import="newx.framework.FrameworkService"%>
<%@page import="newx.util.*"%>
<%@page import="newx.mod.menu.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=SysUtil.getTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>banner</title>
<link href="<%=request.getContextPath()%>/css/menu.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/newx.js"></script>
<style type="text/css">
/*平常的状态*/
.link_normal{
    font-size:9pt;
	font-family:宋体;
	color:#2E0390;
    text-decoration:none;
}
/*连接被按下的时候 */
.link_hover{
	color: #aaaaaa; 
    font-size:9pt;
    text-decoration:none;
}
</style>
</head>
<body class="navibody">
    <table width="75%" height="" border="0" cellspacing="0" cellpadding="0">
    <tr><td height="11" ></td></tr>
	</table>

<table style="background-repeat: repeat-y;background-position: right top;" border=0  cellspacing=0 cellpadding=0 width=100%>

<%
	String parentId = request.getParameter("parenId");
if (StringUtil.isNullOrEmpty(parentId)) {
	parentId = MenuService.getInstance().getFirstTopRight().getId();
}
List<RightTreeNode> list = MenuService.getInstance().getChildren(parentId);
List<Right> rightList = new ArrayList<Right>();
for (RightTreeNode node : list) {
	Right right = node.getValue();
	rightList.add(right);
	List<RightTreeNode> children = node.getChildren();
%>
    <tr class='Item' >
        <td align="center">
            <div name=Parent>
                <table id = "<%= right.getId()%>" style="cursor:pointer" width="100%" class="menubutton1" border="0" cellspacing="0" cellpadding="0">
                    <tr> <td style="padding-top: 3px;" align="center"> <%= right.getRightname()%> </td> </tr>
                </table>
            </div>
        </td>
    </tr>
    <tr> <td height='10px'></td></tr>
    <tr>
        <td>
            <div id = "<%= right.getId()%>" name='Child' style='display:none'>
                <table width=100% border=0 align="left" cellpadding=2 cellspacing=0 bordercolor=#ffffff class='memufont' style='border-collapse:collapse'>
                	<%
                	for (RightTreeNode lev3Node : children) {
                		Right lev3right = lev3Node.getValue();
                		rightList.add(lev3right);
                	%>
                    <tr><td class=lefticon></td><td align="left"><span name="lev3menu" id="<%= lev3right.getId() %>" style="cursor:pointer" class="link_normal"
                    onMouseOut="this.className='link_normal';"
                    onMouseOver="this.className='link_hover';"
                    > <%= lev3right.getRightname() %> </span></td></tr>
                	<%
                	}
                	%>
                </table>
            </div>
        </td>
    </tr>
<%
}
%>
</table>
<script type="text/javascript">
var rightList = new Object();
<%
for (Right right : rightList) {
	out.println("rightList[\""+ right.getId() + "\"] = \""+ right.getUrl().replaceAll("\"", "\\\\\"").replaceAll("rootdir", request.getContextPath()) + "\";");
}
%>
$(document).ready(function() {
    $("table.menubutton1").click(function() {
        $("div[id=" + this.id + "][name='Child']").toggle();
        if (!isNull(rightList[this.id])) {
        	$(window.parent.document).find("#workspace").attr("src", rightList[this.id]);
        }
    });
    $("span[name=lev3menu]").click(function() {
        if (!isNull(rightList[this.id])) {
        	$(window.parent.document).find("#workspace").attr("src", rightList[this.id]);
        }
    });
});
</script>
</body>
</html>
