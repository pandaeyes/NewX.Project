<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="newx.framework.FrameworkService"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><%=FrameworkService.getInstance().getTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="<%= request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="<%= request.getContextPath()%>/css/bootstrap.responsive.css" rel="stylesheet" media="screen">
<script src="<%= request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script src="<%= request.getContextPath()%>/js/bootstrap.js"></script>
<title>banner</title>
</head>
<body  background="<%= request.getContextPath()%>/images/top_bg.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin-left:0px;margin-right:0px" width="100%">
<div class="row-fluid">
	<div class="span12">
        <img src="<%= request.getContextPath()%>/images/logo.gif" />
	</div>
</div>
</body>
</body>
</html>
