<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
	</head>
	<body class="body_bg" >
	<script type="text/javascript">
			function certificate(){
	    var idNo = document.getElementsByName("idNo")[0].value;
		var reg =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		var bl = reg.test(idNo);
		if(!bl){
			sAlert("请输入正确的证件号码");
			document.cms_form.elements["idNo"].value = "";
			return false;
	}
	}
	</script>
	
	<s:form method="post" theme="simple" name="cms_form"
		action="SysUserActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formsys0071" mode="query" />
						<div class="from_btn">
						<s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
								</s:if>
		                    <dhcc:button typeclass="button_form" value="返回" action="返回" onclick="SysUserAction_findByPage.action"></dhcc:button>
						 <!--<input type="hidden" id="roleNos" value="<s:property value="SysUser.roleNos"></s:property>">-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body><!--
	<script type="text/javascript">
		$(document).ready(function(){
			
		});
		function back(){
			window.parent.location.href='SysUserAction_findByPage.action';
		}
		function nochange(){
			sAlert("禁止手动修改!");
		}
	</script>
--></html>