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
	
%>
<script type="text/javascript">

</script>
</head>
<frameset cols="0,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="#" />
	
	<frameset rows="100,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="ProjBaseAction_getDetailTop.action?projNo=<%=projNo%>&projId=<%=projId%>&query=<%=request.getParameter("query") %>" />
		<frame name="persFrame" id="persFrame" scrolling="no"
			src="ProjBaseAction_getTab.action?projNo=<%=projNo%>&projNatn_type=<s:property value="projNatn"/>&query=<%=request.getParameter("query") %>&isFlag=<%=request.getParameter("isFlag") %>&projId=<%=projId%>"/>
	</frameset>
	</frame>
</frameset>
<noframes>
<body>
	<div align="left">
	</div>
</body>
</noframes>
</html>