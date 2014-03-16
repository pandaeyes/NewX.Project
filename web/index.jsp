<%@ page contentType="text/html;charset=utf-8" %>
<%@page import="newx.framework.FrameworkService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=FrameworkService.getInstance().getTitle()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<style type="text/css">
<!--
*{overflow:hidden; font-size:9pt;}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/bg.gif);
	background-repeat: repeat-x;
}
-->
</style></head>
<%
String contextPath =  request.getContextPath();
%>
<script type="text/javascript">
$(document).ready(function() {
    $("#login").click(function() {
        var user = $("#user").val();
        var password = $("#password").val();
        if (typeof user == "undefined" || user == null || user == "") {
            alert("请输入用户名");
            return;
        }
        if (typeof password == "undefined" || password == null || password == "") {
            alert("请输入密码");
            return;
        }
        $("#frm").submit();
    });
});
</script>
<body>
<FORM id="frm" name="frm" method=post action="<%= request.getContextPath() %>/jsp/main.jsp">
<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="561" style="background:url(<%=contextPath%>/images/login/lbg.gif)"><table width="940" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="238" style="background:url(<%=contextPath%>/images/login/login01.jpg)">&nbsp;</td>
          </tr>
          <tr>
            <td height="190"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="208" height="190" style="background:url(<%=contextPath%>/images/login/login02.jpg)">&nbsp;</td>
                <td width="518" style="background:url(<%=contextPath%>/images/login/login03.jpg)"><table width="320" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="40" height="50"><img src="<%=contextPath%>/images/login/user.gif" width="30" height="30"></td>
                    <td width="38" height="50">用户</td>
                    <td width="242" height="50"><input type="text" name="textfield" id="user" style="width:164px; height:32px; line-height:34px; background:url(<%=contextPath%>/images/login/inputbg.gif) repeat-x; border:solid 1px #d1d1d1; font-size:9pt; font-family:Verdana, Geneva, sans-serif;"></td>
                  </tr>
                  <tr>
                    <td height="50"><img src="<%=contextPath%>/images/login/password.gif" width="28" height="32"></td>
                    <td height="50">密码</td>
                    <td height="50"><input type="password" name="textfield2" id="password" style="width:164px; height:32px; line-height:34px; background:url(<%=contextPath%>/images/login/inputbg.gif) repeat-x; border:solid 1px #d1d1d1; font-size:9pt; "></td>
                  </tr>
                  <tr>
                    <td height="40">&nbsp;</td>
                    <td height="40">&nbsp;</td>
                    <td height="60"><img id="login" style="cursor:pointer" src="<%=contextPath%>/images/login/login.gif" width="95" height="34"></td>
                  </tr>
                </table></td>
                <td width="214" style="background:url(<%=contextPath%>/images/login/login04.jpg)" >&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="133" style="background:url(<%=contextPath%>/images/login/login05.jpg)">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</FORM>
</body>
</html>
