<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<%String dataType =(String) request.getAttribute("dataType"); %>
<html>
<head>
<title>小微金融运管平台管理系统</title>
<script type="text/javascript">
var dataType="<%=dataType%>";
if(dataType!=null&&dataType!=""&&dataType!="null"){
	if(dataType=="success"){
		parent.opener.location="ProjBaseAction_findByPage.action";
	}
	/*if(parent.opener.location.href.indexOf("ProjBaseAction_findByPage")){
		parent.opener.location.reload();
	}else{
		parent.opener.location.href="<%=contextpath%>/ProjBaseAction_findByPage.action";
	}*/
}
</script>
</head>
<body class="body_bg">
<div class="right_bg">
<div class="right_w">
<div class="from_bg">
<div class="right_v">
<form action="#">
<table width="100%" align="center" height="100%">
	<tr>
		<td>
		<dhcc:tabTag tabList="tabList"></dhcc:tabTag>
		</td>
	</tr>
</table>		
</form>
</div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
if (window.dialogArguments) {
	var inputs = document.getElementsByTagName("input");
	var b = inputs[inputs.length - 1];
	b.onclick = function click() {
		window.close();
	};
}
</script>
</html>