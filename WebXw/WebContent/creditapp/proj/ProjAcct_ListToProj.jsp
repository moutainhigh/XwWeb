<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String  projNo = (String)request.getAttribute("projNo");
%>
<html>
	<head>
		<title>列表</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjAcctAction_findByPageToProj.action">
		<p class="p_blank">&nbsp;</p>
		
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<!--<div class="tabTitle">虚拟户列表</div>-->
							<div class="tabTitle">账户列表</div>
							
						</div>
						<input value="<s:property value="query"/>" name="query" type="hidden"> 
						<input value="<s:property value="projNo"/>" name="projNo" type="hidden"> 
							<input name="acctType" value="<s:property value="acctType"/>" type="hidden"/>
						<dhcc:tableTag paginate="projAcctList" property="tableproj0007"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>