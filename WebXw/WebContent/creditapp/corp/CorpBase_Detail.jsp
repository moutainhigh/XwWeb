<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
	</head>
	<script type="text/javascript">
			function getLicBegDate(){
		    fPopUpCalendarDlg();
			}
	</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CorpBaseActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formcoop0002" mode="query" />
						<div class="from_btn">
						<s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
						 </s:if>
						 <s:if test="corpBase.sts==1">
						 <dhcc:button typeclass="button_form" value="返回" action="返回" onclick="CorpBaseAction_findByPage.action"></dhcc:button>
						</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>