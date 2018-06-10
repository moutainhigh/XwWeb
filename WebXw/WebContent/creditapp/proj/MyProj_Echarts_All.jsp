<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9">
			<title>我的项目信息</title> 
<%
 	String projNo = request.getParameter("projNo");
%>
		
	</head>
		<frameset rows="50%,*" border="0" frameBorder="no" frameSpacing="0">
			<frame name="persFrame_top" id="mainFrame_top" noresize="noresize"
				scrolling="no" src="ProjBaseAction_myProj_echarts.action" />
			<frame name="persFrame" id="persFrame" scrolling="no"
				src="ProjBaseAction_myProj_getDetailTab.action" />
		</frameset>
</html>
