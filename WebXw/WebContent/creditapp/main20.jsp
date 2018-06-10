<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>信贷管理系统</title>
<script type="text/javascript">
	function changeMainFrame(url){
		//if(url == null || url == "undefined") url = "../ProjBaseAction_myProj_getAllDetail.action";
		if(url == null || url == "undefined"){
		}else{
			document.getElementById("rightFrame").src = url;
		}
	}
</script>
</head>
<!-- <frameset rows="61,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="header.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" /> -->
  <frameset id="mainFramestId"  cols="160,*" frameborder="no" border="0" framespacing="0">
    <frame src="left.jsp?first=../ProjBaseAction_myProj_getAllDetail.action" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" />
    <!-- 
	<frame src="nav.jsp"  name="navFrame" scrolling="no" noresize="noresize" id="navFrame" />
	 -->
    <frameset rows="40,*" frameborder="no" border="0" framespacing="0" name="mainFrame" id="mainFrame">
		<frame src="location.action" name="locFrame" scrolling="no" noresize="noresize" id="locFrame" />
		<frame src="" frameborder="0" name="rightFrame" id="rightFrame" scrolling="auto"/>
	</frameset>
  </frameset>
<!-- </frameset> -->
<noframes><body>
</body>
</noframes>
</html>
