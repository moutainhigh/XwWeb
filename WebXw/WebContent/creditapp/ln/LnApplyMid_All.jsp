<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<title>正式进件申请信息</title>
<%
	String contextpath = request.getContextPath();
	String appId = request.getParameter("appId");
	String chkSts = request.getParameter("chkSts");
	String zdappSts = request.getParameter("zdappSts");
	String rgappSts = request.getParameter("rgappSts");
	String is_flag = request.getParameter("is_flag");
%>
<script type="text/javascript">
</script>
</head>
<frameset cols="0,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="persFrame_left" id="persFrame_left" noresize="noresize" scrolling="no"
		src="#" />
	
	<frameset rows="0,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_top" id="mainFrame_top" noresize="noresize" scrolling="no"
			src="LnApplyRegAction_getDetailTop.action?appId=<%=appId%>&query=<%=request.getParameter("query") %>" />
		<frame name="persFrame" id="persFrame"
			src="LnApplyMidAction_getTab.action?appId=<%=appId%>&zdappSts=<%=zdappSts%>&rgappSts=<%=rgappSts%>&chkSts=<%=chkSts%>&query=<%=request.getParameter("query") %>&is_flag=<%=request.getParameter("is_flag") %>"" />
	
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