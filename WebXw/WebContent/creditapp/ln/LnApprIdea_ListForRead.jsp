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
		action="LnApprIdeaAction_findByPageForRead.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
						</table>
					</div>
				</div>
			</div>
		</div>
		<p class="p_blank">&nbsp;</p>
		
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">已办列表</div><!--
							<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="LnApprIdeaAction_input.action"></dhcc:button>-->
						</div>
							<input name="appId" value="<s:property value="appId"/>" type="hidden"/>
						<dhcc:tableTag paginate="lnApprIdeaList" property="tablelnappridea0004"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>