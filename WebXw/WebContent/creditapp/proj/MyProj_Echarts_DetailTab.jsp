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
	/*
	if(parent.opener.location.href.indexOf("ProjBaseAction_findByPage")){
		parent.opener.location.reload();
	}else{
		parent.opener.location.href="<%=contextpath%>/ProjBaseAction_findByPage.action";
	}
	*/
}
</script>
<style>
.currDiv{display: block;}
.hideDiv{display: none;}
</style>
</head>
<body class="body_bg">
<div class="right_bg">
<div class="right_w">
<div class="from_bg">
<div class="right_v">
<form action="#">
<table width="100%" align="center" height="100%">
	<tr>
		<td style="vertical-align: top;height: 100%">
			<div class="worktab2">
				<div class="workTa"><a class="workTaon">集合类项目</a>
				</div>
    	  		<div class="workTa"><a>单一类项目<i></i></a>
    			</div>
			</div>
			<div id="contentDiv" style="width: 100%;text-align: center;height: 85%">
				<div id="divA" style="width: 100%;height: 100%;"  class="currDiv">
					<iframe src="<%=request.getContextPath() %>/ProjBaseAction_myProj_findByPage.action?menuno=A0101" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>
				</div>
				<div id="divB" style="width: 100%;height: 100%;"  class="hideDiv">
					<iframe src="<%=request.getContextPath() %>/ProjBaseAction_myProj_findByPage.action?menuno=A0101&projNatu=01" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>
				</div>
			</div>
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
var currflag = "A";
$(function(){
	$(".worktab2 .workTa a").click(function(){
		if($(this).hasClass("workTaon"))return false;
		$(".worktab2 .workTa a").removeClass("workTaon");
		$(this).addClass("workTaon");
		if(currflag == "A"){
			currflag = "B";
			$("#divA").removeClass("currDiv").addClass("hideDiv");
			$("#divB").removeClass("hideDiv").addClass("currDiv");
		}
		else{
		 	currflag = "A";
		 	$("#divB").removeClass("currDiv").addClass("hideDiv");
		 	$("#divA").removeClass("hideDiv").addClass("currDiv");
		}
	})
	$(".tipsclose").click(function(){
		$(".tipswin").hide();
	})
})
	
if (window.dialogArguments) {
	var inputs = document.getElementsByTagName("input");
	var b = inputs[inputs.length - 1];
	b.onclick = function click() {
		window.close();
	};
}
</script>
</html>