<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<script type="text/javascript">
	function getRegBegDate(){
		    fPopUpCalendarDlg();
		
		}
    function getRegEndDate(){
		    fPopUpCalendarDlg();
		
		}		
		function checkCertCode(obj){
	    var  certCode = obj.value;
		var reg =/^\d+$/;
		var bl = reg.test(certCode);
		if(!bl){
			sAlert("请输入合法的资质证书编号");
			document.cms_form.elements["certCode"].value = "";
			return false;
		}
	}
	</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CorpCertActionInsert.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formcoop0006" mode="query" />
						<div class="from_btn">
							<dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="CorpCertAction_listQuotaForCorp.action" param="brNo=brNo"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>