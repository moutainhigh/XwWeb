<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=GBK"%>
<%
	String contextpath = request.getContextPath();
	Object themeObj = session==null?null:session.getValue("color");
	String theme = themeObj==null?"blue":((String)themeObj);
%>
<html>
	<head>
		<title>“ª∞„“Ï≥£</title>
<link rel="stylesheet" href="<%=contextpath%>/themes/css/main.css" type="text/css" />
<link rel="stylesheet" href="<%=contextpath%>/themes/theme_<%=theme%>/Css/sysUI_<%=theme%>.css" type="text/css" />
		<script type="text/javascript" language="javascript">
		function funcExit() {
			//window.history.back();
			window.top.location.href = "<%=request.getContextPath() %>/creditapp/logout.action";
		}
</script>
<style type="text/css">
span{
	font-size: 16px;
}
</style>
	</head>
	<body class="body_bg">
		<br/>
		<br/>
		<center>
		<s:actionmessage />
		</center>
		<div class="from_btn">
		<input type="button" value=" ÕÀ ≥ˆ " onclick="javascript:funcExit()" />
		</div>
	</body>
</html>
