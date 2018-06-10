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
		action="LnPactAction_listQuotaForCif.action">
		<p class="p_blank">&nbsp;</p>
		<s:hidden name ="query"></s:hidden>
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">合同信息</div>
							</div>
							<input name="cifNo" value="<s:property value="cifNo"/>" type="hidden"/>
						<dhcc:tableTag paginate="lnPactList" property="tablelnpact0008"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>