<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="PrdtBaseActionInsert.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formsys0002" mode="query" />
						<div class="from_btn">
							<dhcc:button typeclass="button3" commit="true" value="保存" action="保存" param="prdtId=prdtId@prdtNo=prdtNo@brNo=brNo" ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="ParamIntegAction_getPrdtList.action" param="brNo=brNo"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
		<script type="text/javascript">
		function funcWkfApprovalRolePop2List(parNames, popNames,returnMethod) {
		    var url = "PrdtBaseAction_findByPagePopList.action?parNames=" + parNames + "&popNames=" + popNames+ "&returnMethod=" + returnMethod;
		    window.showModalDialog(
		    url,window,"dialogWidth=40;dialogHeight=30;resizable=no;scrollbars=no;status:yes;help:no;");
}
	</script>
</html>