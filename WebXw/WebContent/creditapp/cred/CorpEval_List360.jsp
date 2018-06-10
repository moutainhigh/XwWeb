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
		action="CorpEvalAction_findByPage360.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">评级列表</div>
						</div>
							<input name="brNo" value="<s:property value="brNo"/>" type="hidden"/>
						<s:if test="formSts==1">
							<dhcc:tableTag paginate="corpEvalList" property="tablecred0011"
									head="true" />
						</s:if>
						<s:else>
							<dhcc:tableTag paginate="corpEvalList" property="tablecred0010"
										head="true" />
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>