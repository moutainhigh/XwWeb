<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
		
		<script>
			function showFoward(url){
				window.parent.location.href=url;
			}
		</script>
		<script>
		  	function selectTblOrg(url) {
		  		var ieConfig = "dialogWidth=44;dialogHeight=20;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no";
		  		url="<%=request.getContextPath()%>/"+url;
		  		showNewDailogWindow(url,true,800,300,null,ieConfig);
				//window.showModalDialog("<%=request.getContextPath()%>/"+url,window,"dialogWidth=44;dialogHeight=20;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
  		  	}
		</script>		
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjAcctAction_listQuotaForCorp.action">
		<p class="p_blank">&nbsp;</p>
		<s:hidden name ="query"></s:hidden>
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
							<input name="acctId" value="<s:property value="acctId"/>" type="hidden"/>
							<input name="projNo" value="<s:property value="projNo"/>" type="hidden"/>
						<dhcc:tableTag paginate="ProjAcctList" property="tableproj0008"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>