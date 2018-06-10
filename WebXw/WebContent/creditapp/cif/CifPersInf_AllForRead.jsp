<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<head>
<title>个人客户详细信息</title>
<%
	String cifNo = request.getParameter("cifNo");
	String cifName = request.getParameter("cifName");
	String is_flag = request.getParameter("is_flag");
%>
</head>	
<frameset cols="205,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="<%=request.getContextPath()%>/creditapp/cif/CifPersInf_All_Lefts.jsp?cifNo=<%=cifNo%>&query=<%=request.getParameter("query") %>" />
	
	<frameset rows="115,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="CifPersInfAction_getDetailTop.action?cifNo=<%=cifNo%>&query=<%=request.getParameter("query") %>" />
			
		<frame name="persFrame_center" id="persFrame_center" noresize="noresize" scrolling="auto"
			src="<%=request.getContextPath()%>/creditapp/cif/CifPortrait_AllForRead.jsp?cifNo=<%=cifNo%>&cifName=<%=cifName%>&query=<%=request.getParameter("query") %>" />
			
	</frameset>
</frameset>
</html>