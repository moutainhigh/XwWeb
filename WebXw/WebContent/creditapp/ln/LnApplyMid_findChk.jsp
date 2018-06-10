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

				<s:form method="post" theme="simple" validate="true" name="cms_form">
					<table id="accessinfotable" width="100%" border="0" align="center" cellspacing=1 class="ls_list">
						<thead>
							<tr class="bartop" height="26">
								<th class="TDstyle02"  >
									要素
								</th>
								<th class="TDstyle02"  >
									结果
								</th>
								<th class="TDstyle02"  width="6%">
									标识
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="acc_check_info" value="ruleReturnList">
							<!-- 检查通过-->
							<s:if test='codeRes=="01"'>
								<tr>
									<td align="center" class="TDstyle02" style="background-color: white">
										<s:property value="codeName" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: white">
										<s:property value="codeResDes" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: white">
										<img alt="通过" src="<%=contextpath%>/creditapp/sys/images/success.gif" width="18" height="18" border="0" escape="false">
									</td>
								</tr>
							</s:if>
							<!-- 检查不通过-->
							<s:elseif test='codeRes=="02"'>
								<tr>
									<td align="center" class="TDstyle02" style="background-color: #FFC125">
										<s:property value="codeName" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: #FFC125">
										<s:property value="codeResDes" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: #FFC125">
										<img alt="通过" src="<%=contextpath%>/creditapp/sys/images/false.gif" width="18" height="18" border="0" escape="false">
									</td>
								</tr>
							</s:elseif>		
							<s:elseif test='codeRes=="03"'>
								<tr>
									<td align="center" class="TDstyle02" style="background-color: #FAFAD2">
										<s:property value="codeName" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: #FAFAD2">
										<s:property value="codeResDes" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: #FAFAD2">
										<img alt="通过" src="<%=contextpath%>/creditapp/sys/images/NoChk.png" width="18" height="18" border="0" escape="false">
									</td>
								</tr>
							</s:elseif>	
							<s:elseif test='codeRes=="04"'>
								<tr>
									<td align="center" class="TDstyle02" style="background-color: white">
										<s:property value="codeName" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: white">
										<s:property value="codeResDes" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: white">
										<img alt="通过" src="<%=contextpath%>/creditapp/sys/images/success.gif" width="18" height="18" border="0" escape="false">
									</td>
								</tr>
							</s:elseif>	
							</s:iterator>
						</tbody>
					</table>		
				</s:form> 

					</div>
				</div>
			</div>
		</div>
	</s:form>
	<div id="stamp" style="position: absolute;right:0px;top:0px; z-index: 10;width: 100%;height: 100%;" ></div>
<style>
.passStamp{
	background: url(<%=request.getContextPath()%>/themes/images/stamp/seal-pass.png) no-repeat center;
}
.failStamp{
	background: url(<%=request.getContextPath()%>/themes/images/stamp/seal-nopass.png) no-repeat center;

</style>
	<script type="text/javascript">
$(document).ready(function(){
	if(<%=request.getAttribute("res")%>=='2'){
		$("#stamp").height($("#accessinfotable").height()).addClass("passStamp");
	}
	if(<%=request.getAttribute("res")%>=='3'){
		$("#stamp").height($("#accessinfotable").height()).addClass("failStamp");
	}
	
});
</script>
	</body>
</html>