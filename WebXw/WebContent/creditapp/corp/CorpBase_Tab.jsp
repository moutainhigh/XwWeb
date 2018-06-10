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
		parent.opener.location="CorpBaseAction_findByPage.action";
	}
	/*if(parent.opener.location.href.indexOf("CorpBaseAction_findByPage")){
		parent.opener.location.reload();
	}else{
		parent.opener.location.href="<%=contextpath%>/CorpBaseAction_findByPage.action";
	}*/
}
</script>
<style>
	.right_v{
		padding: 0px;
	}
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
		<td>
			<div class="tab_btn_div">
				<s:if test="flag!='no'">
					<s:if test="view!='view'">
						
					</s:if>
					<s:else>
						<!-- 当back=1时不显示返回 因为在待审批工作台--评级审批时要调用该页面查看个人客户信息，这时不用返回按钮 -->
						<dhcc:button typeclass="back_btn" value="返回列表" action="返回列表"
							onclick="CorpBaseAction_findByPage.action"></dhcc:button>
					</s:else>
				</s:if>
			</div>
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