<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String  projNo = (String)request.getAttribute("projNo");
%>
<html>
	<head>
		<title>�б�</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjAcctAction_findByPage.action">
		<!--
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formproj0007" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
							<dhcc:button value="��ѯ" action="��ѯ" commit="true"
								typeclass="btn_80"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
		-->
		<p class="p_blank">&nbsp;</p>
		
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">�˻��б�</div>
							<dhcc:button value="����" action="����" typeclass="t_ico_tj"
								onclick="ProjAcctAction_input.action" param="projNo=projNo@flag=flag"></dhcc:button>
						</div>
						<s:if test="flag==1">
							<input name="acctType" value="<s:property value="acctType"/>" type="hidden"/>
						<dhcc:tableTag paginate="projAcctList" property="tableproj0007"
									head="true" />
						</s:if>
						<s:else >
						<input name="acctType" value="<s:property value="acctType"/>" type="hidden"/>
						<dhcc:tableTag paginate="projAcctList" property="tableproj0004"
									head="true" />
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>