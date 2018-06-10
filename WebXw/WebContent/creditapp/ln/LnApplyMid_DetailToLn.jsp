<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>œÍ«È</title>
	</head>
	<script type="text/javascript">
	function getAppDate(){
		    fPopUpCalendarDlg();
		}
	function getBegDate(){
		    fPopUpCalendarDlg();
		}
	function getEndDate(){
		    fPopUpCalendarDlg();
		}		
		function getBirthday(){
		    fPopUpCalendarDlg();
		}
	</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="LnApplyRegActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formlnapplymid0004" mode="query" />
						<div class="from_btn">
							<!--<input type="button" value="∑µªÿ" onclick="javascript:window.location='LnApplyMidAction_findByPageToLnFail.action'" class="button_form"/>
						--></div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>