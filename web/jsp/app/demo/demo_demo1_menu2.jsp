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
<newx:sRecordSet id="demo_datasrc_test1">
    <newx:provider id="demo_dd_test1_s1" sql="select * from log_activity where name=:name" outParam="outparam1">
    	<newx:param name="name" type="S" value="阮欣南"/>
    </newx:provider>
    <newx:provider id="demo_dd_test1_s2" sql="select * from sys_right where 1 = 1 limit 1" outParam="outparam1"/>
    <newx:provider id="demo_sa_test1_s3" sql="select * from sys_right where 1 = 1 limit 1" outParam="outparam1"/>
    <newx:provider id="demo_sa_test1_s4" sql="select * from log_activity where id = :id" outParam="outparam1">
    	<newx:param name="id" type="I" value="42"/>
    </newx:provider>
</newx:sRecordSet>
<br>
<b>page:<%= pageContext.getAttribute("name")%></b>
<b><newx:attr name="ctime"/></b>
<br>
<br>
<b>多记录数据源</b>
<br>
<newx:mRecordSet id="demo_datasrc_test2">
    <newx:provider id="demo_ma_test1_s3" sql="select * from log_activity where name = $(name)">
    	<newx:param name="name" value="花俊悔"/>
    </newx:provider>
</newx:mRecordSet>
<b><newx:attr name="demo_datasrc_test2"/></b>
</body>
</html>
