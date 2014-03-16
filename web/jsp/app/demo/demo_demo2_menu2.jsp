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
<newx:mtable id="test_01" title="测试标题" size="5">
	<newx:head name="name" txt="名称"/>
	<newx:head name="account" txt="性别" dict="sys_dd_sex"/>
	<newx:head name="info2" txt="出生地"/>
	<newx:provider id="demo_ma_test1_s3" sql="select * from log_activity where name = $(name)">
    	<newx:param name="name" value="滕凌志"/>
    </newx:provider>
</newx:mtable>
<br>
<newx:mtable id="test_02" title="测试标题" hasTitle="false" size="10">
	<newx:head name="name" txt="名2称"/>
	<newx:head name="account" txt="性3别" dict="sys_dd_sex"/>
	<newx:head name="ctime" txt="出生地"/>
	<newx:provider id="demo_ma_test1_s3" sql="select * from log_activity where id < $[id]">
    	<newx:param name="id" type="I" value="500"/>
    </newx:provider>
</newx:mtable>
</body>
</html>
