<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<%String dataType =(String) request.getAttribute("dataType"); %>
<html>
<head>
<title>业务管理系统</title>
<script type="text/javascript">
var dataType="<%=dataType%>";
if(dataType!=null&&dataType!=""&&dataType!="null"){
	if(dataType=="success"){
		parent.opener.location="LnBatchAction_findByPage.action";
	}
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
			<div class="tab_btn_div">
				<s:if test="flag!='no'">
						<!-- 当back=1时不显示返回 因为在待审批工作台--评级审批时要调用该页面查看个人客户信息，这时不用返回按钮 -->
						<dhcc:button typeclass="button3" value="返回列表" action="返回列表"
							onclick="LnBatchAction_findByPage.action"></dhcc:button>
				</s:if>			</div>
		</td>
	</tr>
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