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
<newx:form>
<newx:stable title="测试标题表单">
</newx:stable>
</newx:form>
</body>
</html>
