<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<script type='text/javascript' src='<%=contextpath%>/creditapp/js/valid.js'></script>
	<script type="text/javascript">
	function getCurrDate(){
		    fPopUpCalendarDlg();
		}		
	function cal(form){
		form.action="AftWsElyRepyActionValidate.action";
		form.submit();
	}
	
	function getValues(payType){
//		var payType = document.getElementsByName("payType");
		alert("还款类型："+payType);
	}
	</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="AftWsElyRepyActionInsert.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<s:if test="payType == 全部还款">
							
						</s:if>
						<s:elseif test="payType == 部分还款">
							
						</s:elseif>
						<dhcc:formTag property="forminf0059" mode="query" />
						<div class="from_btn">
							<dhcc:button typeclass="button_form" value="测算" action="测算" onclick="cal(this.form)"></dhcc:button>
							<dhcc:button typeclass="button3" commit="true" value="确定" action="保存"  ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="AftWsElyRepyAction_findByPage.action"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>