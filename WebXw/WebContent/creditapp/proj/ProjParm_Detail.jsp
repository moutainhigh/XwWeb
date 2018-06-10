<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>œÍ«È</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjParmActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formproj0006" mode="query" />
						<div class="from_btn">
						<s:if test="query!='query'">

							 <input type="submit" value="±£¥Ê"  class="button3"/>
					
						 </s:if>
						<!--
						 <input type="button" value="∑µªÿ" onclick="javascript:window.location='AftFiveAction_findByPage.action'" class="button_form"/>
						-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>