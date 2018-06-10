<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<head>
 <title>正式进件申请信息</title>
<%
	String contextpath = request.getContextPath();
	String appId = request.getParameter("appId");
	String is_flag = request.getParameter("is_flag");
%>

</head>
<frameset cols="0,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="#" />
	
	<frameset rows="1,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="LnApplyRegAction_getDetailTop.action?appId=<%=appId%>&query=<%=request.getParameter("query") %>" />
		<frame name="persFrame" id="persFrame" 
			src="LnApplyMidAction_getTabFail.action?appId=<%=appId%>&query=<%=request.getParameter("query") %>&is_flag=<%=request.getParameter("is_flag") %>"/>
	
	</frameset>
</frameset>
<noframes>
<body>
	<div align="left">
	</div>
</body>
</noframes>
</html>