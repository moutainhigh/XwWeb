<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="AcFeeRateAction_findByPage.action">
		<p class="p_blank">&nbsp;</p>
		
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">信息列表</div>
							<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="AcFeeRateAction_input.action" param="frId=frId"></dhcc:button>
						</div>
						<s:hidden name="frId"></s:hidden>	
						<dhcc:tableTag paginate="acFeeRateList" property="tablefee001"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>