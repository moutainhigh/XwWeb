<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
	</head>
	<%
		String flag = request.getParameter("if_checkbox");
	 %>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="PopParmConfActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<%if( "1".equals(flag) ){ %>
						<dhcc:formTag property="formdev0102" mode="query" />
						<%}else{ %>
						<dhcc:formTag property="formdev0002" mode="query" />
						<%} %>
						<div class="from_btn">
						<s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
						 </s:if>
						 
						
						  <input type="button" value="返回" onclick="javascript:window.location='PopParmConfAction_findByPage.action'" class="button_form"/>
		         <!--            <dhcc:button typeclass="button_form" value="返回" action="返回" onclick="PopParmConfAction_findByPage.action" param="if_checkbox=1"></dhcc:button> -->
					
						
                         
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>