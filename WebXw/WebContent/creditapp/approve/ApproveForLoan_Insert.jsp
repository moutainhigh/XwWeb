<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%@ page language="java" import="app.creditapp.ln.entity.LnApprIdea" session = "true"%>
<%
String brNo=(String)request.getAttribute("brNo");
String projNo=(String)request.getAttribute("projNo");
String lnType=(String)request.getAttribute("lnType");
String taskIds="null";//(String)request.getAttribute("taskIds");
String processIds="null";//(String)request.getAttribute("processIds");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
				<script type="text/javascript">
				
function insertAppId(frm) {


		var brNo = '<%=brNo%>';
		var projNo = '<%=projNo%>';
		var lnType = '<%=lnType%>';
		var taskIds = '<%=taskIds%>';
		var processIds = '<%=processIds%>';
		var apprIdea = document.getElementsByName('apprIdea')[0].value;
		var apprDesc = document.getElementsByName('apprDesc')[0].value;
		if(apprDesc==null || apprDesc ==""){
		alert("请填写'审批意见说明'!");
		}else{
		frm.submit();
			//window.location = "ApproveLoanAction_doSubmit.action?brNo="+brNo+"&projNo="+projNo+"&lnType="+lnType+"&taskId="+taskIds+"&processId="+processIds+"&apprIdea="+apprIdea+"&apprDesc="+apprDesc;
		}
		
		
	}	

</script>
	</head>
<body class="body_bg">
	<div class="right_bg">
		<div class="right_w">
			<div class="from_bg">
			<div class="right_v">
				<s:form name="operform"
					action="ApproveLoanAction_doSubmit.action" method="post"
					theme="simple">
					<dhcc:formTag property="formapprove1001" mode="query" />
					<input type="hidden" name="lastactivityType" value=<s:property value='lastactivityType'/>>
					<div class="from_btn">
						<dhcc:button typeclass="button3" onclick="insertAppId(this.form);" value="提交" action="提交"></dhcc:button>
					<!--
								<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="LnPactAction_findByPageAppr.action"></dhcc:button>
					-->
					</div>
				</s:form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>