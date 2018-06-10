<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%@ page language="java" import="app.creditapp.ln.entity.LnApprIdea" session = "true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String url = (String)request.getAttribute("url");
System.out.println(url);
%>
<html>
	<head>
				<script type="text/javascript">
				/*
function doSubmit(f) {
		var batchNo= document.getElementsByName("batchNo")[0].value;
		f.action = "ApproveBlackAction_doSubmit.action";
		f.submit();
	}	
	*/
</script>
	</head>
<body class="body_bg">
	<div class="right_bg">
		<div class="right_w">
			<div class="from_bg">
			<div class="right_v">
				<s:form name="operform"
					action="ApproveAftAction_doSubmit.action" method="post"
					theme="simple">
					<dhcc:formTag property="formapprove4004" mode="query" />
					<input type="hidden" name="lastactivityType" value=<s:property value='lastactivityType'/>>
					<div class="from_btn">
						<dhcc:button typeclass="button3" commit="true" value="提交" action="提交"></dhcc:button>
						<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="ApproveAftAction_findByPage.action"></dhcc:button>
					
					</div>
				</s:form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>