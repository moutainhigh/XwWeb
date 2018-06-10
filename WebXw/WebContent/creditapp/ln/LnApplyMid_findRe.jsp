<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form" >
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table class="appauto0001">
							<tr>
								<th>字段</th>
								<th>值</th>
								<th>描述</th>
								<th>结果</th>
							</tr>
						</table>
						<dhcc:formTag property="formappauto0001" mode="query" />
						<div class="from_btn">
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>