<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<% 
String cfd =(String) request.getAttribute("cfd");
%>
<html>
	<head>
		<title>详情</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CifAcctActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formacct00102" mode="query" />
						<div class="from_btn">
						<s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
						 </s:if>
						<s:if test="backSts!=1">
						   <input type="button" value="返回" onclick="javascript:window.location='CifAcctAction_listQuotaForCif_Read.action'" class="button_form"/> 
		                </s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>