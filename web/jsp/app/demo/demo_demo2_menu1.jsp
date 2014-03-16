<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="newx.tld" prefix="newx" %>
<%@page import="newx.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<title><%=SysUtil.getTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<LINK href="<%=request.getContextPath()%>/css/newx.css" type=text/css rel=StyleSheet>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/newx.js"></script>
</head>
<body>
<newx:navbar/>
<br>
<newx:stable title="测试标题" hasTitle="false">
<newx:provider id="log_activity_item" sql="select * from log_activity where id=$[id]" outParam="account|name">
	<newx:param name="id" type="I" value="8"/>
</newx:provider>
<newx:provider id="log_activity_item2" sql="select * from sys_right where id=:id" outParam="account">
	<newx:param name="id" type="S" value="demo_demo1_menu2"/>
</newx:provider>
<newx:tr>
	<newx:td txt="角色名字" type="2" name="name"/>
	<newx:td txt="账号" type="2" name="accoun2t"/>
	<newx:td txt="角色名" type="2" name="info"/>
</newx:tr>
<newx:tr>
	<newx:td txt="&nbsp;" tcolspan="6"/>
</newx:tr>
</newx:stable>
<br>
<newx:stable title="测试标题">
<newx:tr>
	<newx:td/>
	<td colspan="2">account:<b><newx:attr name="account"/></b></td>
	<newx:td/>
</newx:tr>
<newx:tr>
	<newx:td/>
	<newx:td/>
	<newx:td/>
</newx:tr>
</newx:stable>
</body>
</html>
