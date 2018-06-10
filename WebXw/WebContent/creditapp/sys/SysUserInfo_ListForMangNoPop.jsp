<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>客户经理POP</title>
		<%
			String param =(String) request.getAttribute("param");	
		%>
		<script type='text/javascript'>
window.name = "curWindow";
</script>
	</head>
	<body class="body_bg">
		<s:form method="post" theme="simple" name="cms_form"
			action="SysUserAction_findMangNoPop.action" target="curWindow" param="param=param">
			<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
							<table width="100%" align="center" class="searchstyle">
								<tr>
									<td>
										<dhcc:formTag property="formsys0099" mode="query" />
									</td>
								</tr>
							</table>
							<div class="tools_372">
								<dhcc:button value="查询" action="查询" onclick="func_commit();"
									typeclass="btn_80"></dhcc:button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<p class="p_blank">
				&nbsp;
			</p>

			<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
							<div class="tabCont">
								<div class="tabTitle">
									信息列表
								</div>
							</div> 
								<dhcc:tableTag paginate="sysUserList"
									property="tablesys0072" head="true" />
							</div>
						</div>
					</div>
				</div>
		</s:form>
		<input type="hidden" value="<%=param%>" id="param" name="param"/>
	</body>
	<script type="text/javascript">
//选定 
function handOver(lk) {
	mangNo = lk.split("?")[1].split("=")[1];//客户经理
	var param = "<%=param%>";
	var msg = '';
	$.ajax( {
		type : "POST",
		url : "CifRightAction_handOver.action",
		async : false,
		data : "param=" + param + "&mangNo=" + mangNo,
		success : function(data) {
			msg = data;
		}
	});
	var reloadflag = false;
	if(msg == '' || msg == null){
		msg = '客户交接批量处理成功！';
		reloadflag = true;
	}
	window.close();
	window.dialogArguments.alert(msg);
	if(reloadflag == true){
		window.dialogArguments.location.reload();
	}
}
function func_commit(){
	var param = "<%=param%>";
	var lk = "SysUserAction_findMangNoPop.action?param="+param;	
	document.cms_form.action=lk;
	document.cms_form.submit();
}
</script>
</html>