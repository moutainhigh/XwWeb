<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<title>参数一体化</title>
</head>
<frameset cols="217,*" border="0" frameBorder="no" frameSpacing="0">
	<frame name="parmsFrame_left" id="parmsFrame_left" noresize="noresize" scrolling="no"
		src="creditapp/sys/Param_Integ_Left.jsp" />
	
	<frameset rows="150,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="parmsFrame_top" id="parmsFrame_top" noresize="noresize" scrolling="no" 
			src="ParamIntegAction_headPage.action"/>
		<frame name="parmsFrame" id="parmsFrame" src="<%=request.getContextPath()%>/creditapp/welcome.jsp"/>
	
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