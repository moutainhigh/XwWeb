<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="app.creditapp.sys.entity.SysOrg"%>
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9">
			<title>’Àªß–≈œ¢</title> 
<%
 	String projNo = request.getParameter("projNo");
%>
		
	</head>
	<frameset cols="0,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_left" id="persFrame_left" noresize="noresize"
			scrolling="no" src="#" />

		<frameset rows="30%,15%,30%,*" border="0" frameBorder="yes" frameSpacing="0" >
			<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" bordercolor=red
				scrolling="no" src="ProjBaseAction_getAccountBal.action?projNo=<%=projNo%>&acctType=01" />
			<frame name="persFrame" id="mainFrame_top" noresize="noresize" bordercolor="#008000" 
				src="ProjAcctAction_findByProjNoAndAcctType.action?projNo=<%=projNo%>&acctType=01" />
			<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" bordercolor="#008000" 
				scrolling="no" src="ProjBaseAction_getAccountVirBal.action?projNo=<%=projNo%>&acctType=02" />
			<frame name="persFrame" id="mainFrame_top" noresize="noresize" bordercolor="#008000" 
				src="ProjAcctAction_findByProjNoAndAcctType.action?projNo=<%=projNo%>&acctType=02" />	
		</frameset>

	</frameset>
	<noframes>
		<body>
			<div align="left">
			</div>
		</body>
	</noframes>
</html>