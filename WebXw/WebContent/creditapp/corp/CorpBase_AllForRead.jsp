<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<title>合作机构详细信息</title>
<%
	String contextpath = request.getContextPath();
	String brNo = request.getParameter("brNo");
%>
<script type="text/javascript">
</script>
</head>
<frameset cols="205,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="<%=contextpath%>/creditapp/corp/CorpBase_All_Left.jsp?brNo=<%=brNo%>&if_agri=<s:property value="if_agri"/>&query=<%=request.getParameter("query") %>" />
	
	<frameset rows="100,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="CorpBaseAction_getDetailTop.action?brNo=<%=brNo%>&query=<%=request.getParameter("query") %>" />
		<frame name="persFrame" id="persFrame"
			src="CorpBaseAction_getViewTab.action?brNo=<%=brNo%>&query=<%=request.getParameter("query") %>" />
	</frameset>
</frameset>
<noframes>
<body>
	<div align="left">
	</div>
</body>
</noframes>
</html>