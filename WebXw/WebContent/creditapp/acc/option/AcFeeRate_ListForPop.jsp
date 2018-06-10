<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
		<script type="text/javascript">
		window.name="curWindow";
		
		$(document).ready(function(){
			var longFeeId = new Array();
			longFeeId =  "<%=request.getParameter("longFeeId")%>".split("@");
			var feeIds = document.getElementsByName("feeId");
			for(var i=0;i<feeIds.length;i++){
				for(var j=1;j<longFeeId.length;j++){
					if(feeIds[i].value!="" && feeIds[i].value.split("=")[1]==longFeeId[j]){
						feeIds[i].checked=true;
					}
				}
			}
		});
		
			function selFee(){
				var feeIds = document.getElementsByName("feeId");
				var feeId = "";
					for(var i=0;i<feeIds.length;i++){
						if(feeIds[i].checked){
							feeId = feeId + "@" + feeIds[i].value.split("=")[1];
						}
					}
				if(window.dialogArguments == null){//使用window.open的方式开启新窗口时取得父窗口
					var doc = window.opener.document;
				}else{//使用window.showModalDialog的方式开启新窗口时取得父窗口
					var doc = dialogArguments.document;
				}
				doc.getElementsByName("feeId")[0].value=feeId;
				window.close();
			}
		</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form" target="curWindow"
		action="AcFeeRateAction_findByPop.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formfee001" mode="query" />
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
							<div class="tabTitle">信息列表</div>
							<dhcc:button value="选定" action="选定" typeclass="t_ico_tj"
								onclick="selFee();"></dhcc:button>
						</div>
							
						<dhcc:tableTag paginate="acFeeRateList" property="tablefee002"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>