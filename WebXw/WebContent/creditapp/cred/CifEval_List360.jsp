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
		action="CifEvalAction_findByPage360.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">客户评分</div>
						</div>
							<input name="cifNo" value="<s:property value="cifNo"/>" type="hidden"/>
						<dhcc:tableTag paginate="cifEvalList" property="tablecred0009"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>