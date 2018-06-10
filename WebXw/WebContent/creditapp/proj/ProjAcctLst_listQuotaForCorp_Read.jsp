<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjAcctLstAction_listQuotaForCorp_Read.action">
		<p class="p_blank">&nbsp;</p>
		<s:hidden name ="query"></s:hidden>
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">信息列表</div>
							<!--<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="ProjAcctLstAction_input.action" param="acctId=acctId"></dhcc:button>
								-->
							</div>
							<input name="acctId" value="<s:property value="acctId"/>" type="hidden"/>
						<dhcc:tableTag paginate="ProjAcctLstList" property="tableproj0013"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>