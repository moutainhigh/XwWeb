<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>

<html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
	<head>
		<title>小微金融运管平台管理系统</title>
<%
	String fundNo = request.getParameter("fundNo");
	String projNo = request.getParameter("projNo");
	String fdType = request.getParameter("fdType");

%>	
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
	</head>
	<frameset cols="205,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no" 
		    src="<%=contextpath%>/creditapp/fund/FundBase_All_Left.jsp?fundNo=<%=fundNo%>&projNo=<%=projNo%>&fdType=<%=fdType%>&query=<%=request.getParameter("query") %>" />

		<frameset rows="100,*" border="0" frameBorder="no" frameSpacing="0">
			<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
				src="FundBaseAction_getDetailTop.action?fundNo=<%=fundNo%>&query=<%=request.getParameter("query")%>" />	
			<frame name="persFrame" id="persFrame"
			 src="FundBaseAction_getTabForEcharts.action?fundNo=<%=fundNo%>&query=<%=request.getParameter("query") %>" />
 		</frameset>
	</frameset>

	<body>
	<div align="left">	
    </div>
    </body>
</html>