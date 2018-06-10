<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<title>个人客户详细信息</title>
<%
	String contextpath = request.getContextPath();
	String cifNo = request.getParameter("cifNo");
	String cifName = request.getParameter("cifName");
	String is_flag = request.getParameter("is_flag");
	System.out.println(is_flag+"------------"+cifNo);
	System.out.println(is_flag+"------------"+cifNo);
%>
<script type="text/javascript">
</script>
</head>
		<frameset cols="50%,*" border="0" frameBorder="no" frameSpacing="0">
			<frame name="cifPortrait" id="cifPortrait"
				src="<%=contextpath%>/CifPortraitAction_getById.action?cifNo=<%=cifNo%>&cifName=<%=cifName%>&query=<%=request.getParameter("query") %>" />
			<frame name="showView" id="showView" 
				src="<%=contextpath%>/CifPersInfAction_showView.action?cifNo=<%=cifNo%>&query=<%=request.getParameter("query") %>" />
		</frameset>
<noframes>
<body>
	<div align="left">
	1111
	</div>
</body>
</noframes>
</html>