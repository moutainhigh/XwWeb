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
		action="FundRightDetailAction_findByPage.action">
				<div class="right_bg" style="display: none;">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td><!--
										<dhcc:formTag property="formfdrtdtl0001" mode="query" />-->
										<input name="projNo" value="<s:property value="projNo"/>">
								</td>
							</tr>
						</table>
						<div class="tools_372">
							
						</div>
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
							<div class="tabTitle">债权赎买历史</div>
						</div>
							
						<dhcc:tableTag paginate="fundRightDetailList" property="tablefdrtdtl0001"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>