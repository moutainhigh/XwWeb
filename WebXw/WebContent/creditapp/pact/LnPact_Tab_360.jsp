<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<%
 String dataType =(String) request.getAttribute("dataType"); 
 String cifNo =(String) request.getParameter("cifNo"); 
  String query1 =(String) request.getParameter("query"); 
 System.out.println("query1="+query1);
%>
<html>
<head>
<title>小微金融运管平台管理系统</title>
</head>
<body class="body_bg">
<div class="right_bg">
<div class="right_w">
<div class="from_bg">
<div class="right_v">
<form action="#">
<table width="100%" align="center" height="100%">
	<tr>
		<td>
			<div class="tabCont">
						<dhcc:button typeclass="t_ico_tj" value="返回列表" action="返回列表"
							onclick="LnPactAction_listQuotaForCif.action" param="cifNo=cifNo@query=query"></dhcc:button>

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
</div>
</div>
</div>
</body>
</html>