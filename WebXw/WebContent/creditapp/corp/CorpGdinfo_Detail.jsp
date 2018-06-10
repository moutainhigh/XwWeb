<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
	</head>
		<script type="text/javascript">
	function certificate(){
	    var gdIdno = document.getElementsByName("gdIdno")[0].value;
	    var gdIdType = document.getElementsByName("gdIdType")[0].value;
		var reg =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		var bl = reg.test(gdIdno);
		if(gdIdType=='0'){
			if(gdIdno!=''){
				if(!bl){
					sAlert("请输入正确的证件号码");
					document.cms_form.elements["gdIdno"].value = "";
					return false;
				}
			}
		}
	}
	
	function getInvDate(){
		    fPopUpCalendarDlg();
		}		
	
			function idNoconfirm(){
				var gdIdNo = document.getElementsByName('gdIdNo')[0];
				var gdIdType = document.getElementsByName('gdIdType')[0];
				if(gdIdType.value=="0"){
					if(gdIdNo.value!=""){
					if(!CheckIdValue(gdIdNo)){
						document.getElementsByName('gdIdNo')[0].value="";
					}
				}
				}
				
			}
		</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CorpGdinfoActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formcoop0010" mode="query" />
						<table></table>
						<div class="from_btn">
						<s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
		                     <dhcc:button typeclass="button_form" value="返回" action="返回" onclick="CorpGdinfoAction_listQuotaForCorp.action" param="brNo=brNo"></dhcc:button>
						 </s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>