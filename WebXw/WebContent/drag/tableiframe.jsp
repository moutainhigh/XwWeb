<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
  String currTableId = request.getParameter("tableId");
  String src=request.getContextPath()+"/FormInitAction_initTableNoCormId.action";
  if(null!=currTableId&&""!=currTableId.trim())
  {
      src="previewTableTool.action?tableId="+currTableId;
  }
%>
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
		<script language="javascript">
			function openDivLeft() {
				var frames = window.parent.window.document.getElementById("frameid");
			}
			function openDivRight() {}
</script>
	</head>
	<!-- 
	<body bgcolor="#FFFFFF">
	<div id="m_div_button" style="position:absolute;left:200;top:10">
	</div>
	<iframe src="tablebutton.jsp" height="40" width="100%" id="p1" name="p1" scrolling="NO" frameborder="0">
	<br>
	<iframe src="draWgTable.jsp" height="100%" width="100%" id="p2" name="p2" scrolling="YES" frameborder="1">
	</iframe>
	</body>
	 -->
	<frameset rows="20,*">
		<frame src="tablebutton.jsp" id="p1" name="p1" scrolling="NO" frameborder="0" />
		<frame src="<%=src%>" id="p2" name="p2" scrolling="YES" frameborder="1" />
	</frameset>
	<body>
		<div id="m_div_button" style="position: absolute; left: 200; top: 10"></div>
	</body>
</html>