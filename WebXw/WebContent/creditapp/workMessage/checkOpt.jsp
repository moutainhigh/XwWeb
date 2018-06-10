<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String title = request.getParameter("title");
String content = request.getParameter("content");
String time = request.getParameter("time");
%>

<!DOCTYPE html">
<html>
<head>
<base href="<%=basePath%>">
<style type="text/css">
html, td,select,textarea {
	font-size: 14px;
}
</style>
</head>
<body>
	<table style="width: 100%">
		<tr>
			<td style="text-align: center;color: #49C4CD"><h3><%=title %></h3></td>
		</tr>
		<tr>
			<td><hr></td>
		</tr>
		<tr>
			<td><%= content %></td>
		</tr>
		<tr>
			<td style="text-align: right"><%= time %></td>
		</tr>
		
	</table>
</body>
</html>
