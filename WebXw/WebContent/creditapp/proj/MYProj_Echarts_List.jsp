<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>ап╠М</title>
		<script type="text/javascript">
		</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjBaseAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">					
						<dhcc:tableTag paginate="projBaseList" property="tableproj0009"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>