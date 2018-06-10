<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>泸州市商业银行授信业务管理系统</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="LnAcctMidAction_listQuotaForLn.action">
		<p class="p_blank">&nbsp;</p>
		<s:hidden name ="query"></s:hidden>
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">账户列表</div>
							
						</div>
							<input name="appId" value="<s:property value="appId"/>" type="hidden"/>
						<dhcc:tableTag paginate="LnAcctMidList" property="tablelnacctmid0001"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>