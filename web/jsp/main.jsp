<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="newx.framework.FrameworkService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta HTTP-EQUIV="Expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><%=FrameworkService.getInstance().getTitle()%></title>
</head>
	<frameset name="topFrame" rows="49,35,*" cols="*" frameborder="NO" border="1" framespacing="0">
		<frame  scrolling="NO" noresize src="banner.jsp">
		<frame  scrolling="NO" noresize src="top_menu.jsp">
		<frameset id="MenuFrameset" name="MenuFrameset" cols="161,*" frameborder="NO" border="0" framespacing="0" rows="*">
  			<frameset id="NavFrame" name="NavFrame" cols="151,10" frameborder="0" border="0" framespacing="0">
    				<frame id="navigation" name="navigation" scrolling="auto" noresize src="left_menu.jsp">
    				<frame frameborder="no" scrolling="NO" noresize src="menubtn.jsp">
  			</frameset>
			<frame id="workspace" name="workspace" scrolling="AUTO" src="center.jsp">
		</frameset>
	</frameset>
	<noframes>
		<body bgcolor="#FFFFFF" text="#000000">
			对不起，您的浏览器不支持分帧。
		</body>
	</noframes>
</html>
