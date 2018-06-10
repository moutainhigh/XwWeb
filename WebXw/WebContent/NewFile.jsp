<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form action="<%=path%>/creditapp/cms.action" method="post">
		<input type="text" placeholder="登录名" name="op.user_no" id="IdInput">
		<input type="password" placeholder="密码" name="op.pass_word"
			id="PwdInput">
		 <input type="submit" value="登录">
	</form>

</body>
</html>