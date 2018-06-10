<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<body class="body_bg">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
				<s:form method="post" theme="simple" name="operform"
					action="SysMenuActionInsert.action">
					<dhcc:formTag property="formsys0016" mode="query" />
					<div class="from_btn">
						<dhcc:button typeclass="btn_80" commit="true" value="保存" action="保存"  ></dhcc:button>
						<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="SysMenuAction_findByPage.action"></dhcc:button>
					</div>
				</s:form>
			</div>
			</div>
		</div>
	</body>
</html>