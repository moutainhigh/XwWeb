<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE8" >
<%@ include file="/include/tld.jsp"%>
<%@ page language="java" import="app.creditapp.pact.entity.LnPact" session = "true"%>
<%
String brNo=(String)request.getAttribute("brNo");
String projNo=(String)request.getAttribute("projNo");
String lnType=(String)request.getAttribute("lnType");
String url=(String)request.getAttribute("url");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <script type='text/javascript'>
	window.name="curWindow";
</script>
<html>
	<head>
		<title>贷款审批列表</title>
		<script type="text/javascript">
	function insertAppId() {
		var brNo = '<%=brNo%>';
		var projNo = '<%=projNo%>';
		var lnType = '<%=lnType%>';
		var url = '<%=url%>';
		var appId = document.getElementsByName("appId");
		var appIds = "";
		for(var i=0;i<appId.length;i++){
			if(appId[i].checked){
				appIds = appIds+"@"+appId[i].value.split("=")[1];
			}
		}
		if(appIds==0){
		alert("请选择您想审批的贷款！");
		}else{
		window.location = "ApproveLoanAction_getTab.action?brNo="+brNo+"&projNo="+projNo+"&lnType="+lnType+"&url="+url+"&appIds="+appIds;   
		}
	}
	function showViewDialogSmall(url){
		openWindow(url,700,230,true)
	}
</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="LnPactAction_findByPageForList.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formlnpact0004" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
							<dhcc:button value="查询" action="查询" commit="true"
								typeclass="btn_80"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<p class="p_blank">&nbsp;</p>
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">贷款审批列表</div>
							<dhcc:button value="审批" action="审批" typeclass="t_ico_tj"
								onclick="insertAppId();" ></dhcc:button>
								<dhcc:button typeclass="t_ico_tj" value="返回" action="返回" onclick="LnPactAction_findByPageAppr.action"></dhcc:button>
						</div>
							
						<dhcc:tableTag paginate="lnPactList" property="tablelnpact0005"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>