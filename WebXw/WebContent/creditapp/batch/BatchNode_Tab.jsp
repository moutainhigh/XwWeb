<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<html>
<head>
<title>泸州市商业银行授信业务管理系统</title>
</head>
<body class="body_bg">
	<div class="right_bg">
		<form action="#">
			<table width="100%" align="center" height="90%">
				<tr>
					<td>
						<div class="tab_btn_div">
							<dhcc:button typeclass="back_btn" value="返回列表" action="返回列表"	onclick="BatchNodeAction_findByPage.action"></dhcc:button>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<dhcc:tabTag tabList="tabList"></dhcc:tabTag>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>


</html>