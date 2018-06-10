<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
  String currFormId = request.getParameter("formId");
  String src=request.getContextPath()+"/FormInitAction_initFormNoCormId.action";
  if(null!=currFormId&&""!=currFormId.trim())
  {
      src=request.getContextPath()+"/FormForToolBean_initForm.action?formId="+currFormId;
  }
%>
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
		<script language="javascript">
			function openDivLeft() {
				var frames = window.parent.window.document.getElementById("frameid");
			}
			function openDivRight() {
			}
        </script>
	</head>
	<!-- 
	<iframe src="formbutton.jsp" height="40" width="100%" id="p1" name="p1" scrolling="NO" frameborder="0">
	<br>
	<iframe src="dragForm.jsp" height="100%" width="100%" id="p2" name="p2" scrolling="YES" frameborder="1">
	</iframe>
	 -->
	 <!-- 
	 <frameset rows="40,*">
		<frame src="formbutton.jsp" id="p1" name="p1" scrolling="YES" frameborder="0"/>
		<frameset cols="120,*">
		    <frame src="formtoolbox.jsp" id="p3" name="p3" scrolling="YES" frameborder="1"/>
			<frame src="dragForm.jsp" id="p2" name="p2" scrolling="YES" frameborder="1"/>
		</frameset>
	</frameset>
	  -->
	<frameset rows="20,*">
		<frame src="formbutton.jsp" id="p1" name="p1" scrolling="NO" frameborder="0"/>
		<frame src="<%=src%>" id="p2" name="p2" scrolling="YES" frameborder="1"/>
	</frameset>
	<body>
		<div id="m_div_button" style="position:absolute;left:200;top:10">
		</div>
	</body>
</html>