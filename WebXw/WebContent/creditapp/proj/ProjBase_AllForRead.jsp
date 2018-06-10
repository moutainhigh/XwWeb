<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<title>项目详细信息</title>
<%
	String contextpath = request.getContextPath();
	String projNo = request.getParameter("projNo");
	String projId = request.getParameter("projId");
	String isFlag = request.getParameter("isFlag");
	String projName = request.getParameter("projName");
	String projNatu = request.getParameter("projNatu");
%>

</head>
<frameset cols="205,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="<%=contextpath%>/creditapp/proj/ProjBase_All_Left.jsp?projNo=<%=projNo%>&projId=<%=projId%>&projName=<%=projName%>&projNatu=<%=projNatu%>&if_agri=<s:property value="if_agri"/>&query=<%=request.getParameter("query") %>" />
	
	<frameset rows="100,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="ProjBaseAction_getDetailTop.action?projNo=<%=projNo%>&projId=<%=projId%>&query=<%=request.getParameter("query") %>" />
		<frame name="persFrame" id="persFrame"
			src="ProjBaseAction_account_getAllDetail.action?projNo=<%=projNo%>&projId=<%=projId%>&projNatn_type=<s:property value="projNatn"/>&query=<%=request.getParameter("query") %>&isFlag=<%=request.getParameter("isFlag") %>" />
	
	</frameset>
</frameset>
<noframes>
<body>
	<div align="left">
	</div>
</body>
</noframes>
</html>