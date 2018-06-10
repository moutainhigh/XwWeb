<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/include/tld.jsp"%>
<head>
<title>业务</title>
<style>
html,body {
	margin: 0;
	padding: 0;
	overflow: hidden;
	height: 100%;
	width: 100%;
}
.checkDiv{
	color: orange !important;
	font-weight: bolder;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#info_view").find("a").click(function(){
		goMenu(this);
	});
});
function getBg(num) {
	for ( var id = 0; id < 3; id++) {
		if (id == num) {
			document.getElementById("mynav" + id).className = "current";
		} else {
			document.getElementById("mynav" + id).className = "";
		}
	}
}
function changeColor(mythis){
	if(null!=document.getElementById('message')&&undefined!=document.getElementById('message')&&''!=document.getElementById('message')){
		var fuck=document.getElementById("message");
		fuck.removeAttribute('id');
		fuck.style.color='';
		mythis.style.color='#c0c0c0';
	 	mythis.setAttribute('id','message');
	}else{
		mythis.setAttribute('id','message');
	 	document.getElementById('message').style.color='#c0c0c0';
	}
}
function goMenu(obj){
	$(obj).parent().parent().parent().find(".linktd").find("a").each(function(){
	//alert($(this).text());
	$(this).removeClass("checkDiv");
	});
	$(obj).addClass("checkDiv");
}
function changeframe(src) {
	if (src != "") {
		window.open(src, target = "businessFrame");
	}
}
function setStepNo(stepNo){
	self.parent.frames["parmsFrame_top"].curStepNo=stepNo;
	return stepNo;
}
function skipCheck() {
	changeframe('ParamIntegAction_findDetail.action?query=query&brNo='+getBrNo());
}
</script>
</head>
<body class="body_bg">
<div class="info_left">
<div style="width:100%; background-color:rgb(240,240,241)">
<div style="width:100%; background-color:transparent">
<div style="width:100%; background-color:transparent">
<div style="width:100%; text-align:center;">
<table align="left" width="100%" border="0" cellspacing="0" id="info_view"
	cellpadding="0" class="info_list">
	<tr>
		<td class="link360top" colspan="2" style="width:100%; padding:0;">业务管理</td>
	</tr>
	<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t1"></i>指标维护
		</th>
	</tr>	
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" class="checkDiv" onclick="changeframe('<%=contextpath%>/ParmRewRuleAction_findByPage.action?menuno=K0404');">筛查规则管理</a>
		</td>
	</tr>
	<!--<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t2"></i>影像管理
		</th>
	</tr>	
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/PacSceneAction_findByPage.action');">影像场景配置</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/PacTypeAction_findByPage.action');">影像类型配置</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/PacBaseAction_findByPage.action');">影像存储信息</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/PntTmplAction_findByPage.action');">打印模板配置</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/ProdPntRelAction_findByPage.action');">业务与打印模板关系配置</a>
		</td>
	</tr>-->
	<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t3"></i>流程管理
		</th>
	</tr>	
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/WkfApprovalRoleAction_findByPage.action?menuno=K0404');">审批角色维护</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/WkfApprovalUserAction_findByPage.action?menuno=K0404');">审批人员维护</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/AppWkfcfgAction_findByPage.action?menuno=K0404');">审批流程设计</a>
		</td>
	</tr>
	<!--
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/PntTmplAction_findByPage.action');">打印模板配置</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/ProdPntRelAction_findByPage.action');">业务与打印模板关系配置</a>
		</td>
	</tr> -->
	
	<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t1"></i>贷后管理
		</th>
	</tr>	
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/ParmRewAction_findByPage.action?menuno=K0404');">风险预警配置</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/ParmFiveConfAction_findByPage.action?menuno=K0404');">五级分类矩阵</a>
		</td>
	</tr>
	<!--
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/RiskMatrixAction_findByPage.action');">五级分类模型</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/AftRegularParmAction_findByPage.action');">贷后检查规则</a>
		</td>
	</tr> -->
	<!--
	<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t2"></i>业务维护
		</th>
	</tr>	
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/ApplyBaseAction_findByPageAll.action?menuno=L0205');">申请信息维护</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/MessageInfoAction_findByPage.action?menuno=L0204');">报文信息管理</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/LnPactAction_findByPage.action?menuno=L0206');">合同信息维护</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/LnNoteAction_findByPageAll.action?menuno=L0207');">出账信息维护</a>
		</td>
	</tr>
	-->
<!--
	<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t3"></i>授权管理
		</th>
	</tr>	
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/ProdBrnoAction_showFrame.action');">机构产品授权</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/LoanLimitationAction_findByPage.action');">贷款投放额度管理</a>
		</td>
	</tr> -->
	<tr class="bb">
		<th scope="col" nowrap="nowrap">
			<i class="clip360t2"></i>知识库管理
		</th>
	</tr>	
<!--	<tr>-->
<!--		<td class="linktd" nowrap="nowrap">-->
<!--			<a href="#" onclick="changeframe('<%=contextpath%>/ParmOpinConfAction_findByPage.action?menuno=K0404');">舆情配置</a>-->
<!--		</td>-->
<!--	</tr>-->
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/VersionInfoAction_findByPage.action?menuno=K0404');">版本管理</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/AcComHolidayAction_findByPage.action?menuno=K0404');">节假日管理</a>
		</td>
	</tr>
	<tr>
		<td class="linktd" nowrap="nowrap">
			<a href="#" onclick="changeframe('<%=contextpath%>/PubNoteAction_findByAll.action?menuno=K0404');">知识库管理</a>
		</td>
	</tr>
	<tr class="bb">
		<th scope="col">
			<i class="clip360t2"></i>影像管理
		</th>
	</tr>
	<tr>
		<td class="linktd">
			<a href="#"	onclick="changeframe('<%=contextpath%>/DocConfigAction_findByPage.action?&menuno=K0404');">影像文档配置</a>
		</td>
	</tr>
</table>
</div>
</div>
</div>
</div>
</div>
</body>
</html>