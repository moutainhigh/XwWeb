<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String contextpath = request.getContextPath();
	Object themeObj = session==null?null:session.getValue("color");
	String theme = themeObj==null?"yellow":((String)themeObj);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title></title>
<script type="text/javascript" src="<%=contextpath%>/themes/js/jquery-1.8.0.min.js"></script>
<link href="../themes/css/main.css" type="text/css" rel="stylesheet" />
<link href="../themes/theme_<%=theme %>/Css/sysUI_<%=theme %>.css" type="text/css" rel="stylesheet" />
</head>
<body class="body_bg1">
  <div class="main_right_top" style="float:left;display:inline">当前位置&nbsp;&gt;&nbsp;<span id="secondMenu">我的首页</span>&nbsp;&gt;&nbsp;<span id="thirdMenu">欢迎</span>
  </div>

</body>
</html>