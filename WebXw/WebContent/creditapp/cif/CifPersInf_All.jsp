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
	String brNo = request.getParameter("brNo");
	String is_flag = request.getParameter("is_flag");
	System.out.println(is_flag+"------------"+cifNo);
%>
<script type="text/javascript">
</script>
</head>
<frameset cols="0,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="#" />
	
	<frameset rows="100,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="CifPersInfAction_getDetailTop.action?cifNo=<%=cifNo%>&query=<%=request.getParameter("query") %>" />
		<frame name="persFrame" id="persFrame"
			src="CifPersInfAction_getTab.action?cifNo=<%=cifNo%>&brNo=<%=brNo%>&cust_type=<s:property value="cust_type"/>&ecif_no=<s:property value="ecif_no"/>&query=<%=request.getParameter("query") %>&is_flag=<%=request.getParameter("is_flag") %>"" />
	
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