<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ page import="app.creditapp.sys.entity.*"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ include file="/include/tld.jsp"%>
<%@ taglib uri="/WEB-INF/tld/dict.tld" prefix="dict"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
	<title></title>
	
	<style type="text/css">
#formula_columns {
	width: 250px;
	BORDER-BOTTOM: #4692ae 1px solid;
	BORDER-LEFT: #4692ae 1px solid;
	BORDER-TOP: #4692ae 1px solid;
	BORDER-RIGHT: #4692ae 1px solid
}

#formula_operator {
	width: 150px;
	BORDER-BOTTOM: #4692ae 1px solid;
	BORDER-LEFT: #4692ae 1px solid;
	BORDER-TOP: #4692ae 1px solid;
	BORDER-RIGHT: #4692ae 1px solid
}

textarea {
	width: 400px;
	BORDER-BOTTOM: #4692ae 1px solid;
	BORDER-LEFT: #4692ae 1px solid;
	BORDER-TOP: #4692ae 1px solid;
	BORDER-RIGHT: #4692ae 1px solid
}
</style>
<script type="text/javascript">
		/*
		 * 初始化基本指标
		 */
		$(document).ready(function(){
			document.operform.elements["bas_indic"].value="A-贷款本金;B-期限月;C-计息周期;D-贷款月利率;E-还款频率;F-上期剩余本金;G-本期期次;H-本段本金比例;I-本期与上期相差天数;J-本段总期数;K-贷款总期数;L-贷款年利率";
			var customEle = new Array();
				customEle = document.operform.elements["customEle"].value.split("@");
			for(var i=0;i<customEle.length/1-1;i++){
				document.getElementsByName("customEle_parm")[i].value=customEle[i+1].split("=")[0];
				document.getElementsByName("customEleForm")[i].value=customEle[i+1].split("=")[1];
			}
		});
		
	   	/*
	  	 * 获取应收本金公式
	  	 */
	  	function getPayCap(){
	   		document.operform.elements["payCap"].value = "";
	  		var payCap = document.operform.elements["payCap"].value;
	  		var payCapDes = document.operform.elements["payCapDes"].value;
	  		var cumEle = document.getElementsByName("customEle_parm").length;
	  		for(var i=(cumEle/1-1);i>=0;i--){
	  			var customElei = document.getElementsByName("customEle_parm")[i].value;
	  			if(customElei!=null&&customElei!=""){
					var customEleFormi = document.getElementsByName("customEleForm")[i].value;
					if(payCap == ""){
						payCap = payCapDes.replace(new RegExp(customElei,"gm"),"("+customEleFormi+")");
					}else{
						payCap = payCap.replace(new RegExp(customElei,"gm"),"("+customEleFormi+")");
					}
		  		}
	  		}
	  		if(payCap == ""){//若无自定义指标，则payCap为空，需将描述赋给公式再替换
	  			payCap = payCapDes;
			}
	  		var bas_indic = new Array();
	  		bas_indic = document.operform.elements["bas_indic"].value.split(";");
	  		for(var i=0;i<bas_indic.length;i++){
				payCap = payCap.replace(new RegExp(bas_indic[i].split("-")[1],"gm"),bas_indic[i].split("-")[0]);
			}
	  		document.operform.elements["payCap"].value = payCap;
	  		
	  	}
	  	
	  	/*
	  	 * 获取应收利息公式
	  	 */
	  	function getPayInte(){
	  		document.operform.elements["payInte"].value = "";
	  		var payInte = document.operform.elements["payInte"].value;
	  		var payInteDes = document.operform.elements["payInteDes"].value;
	  		var cumEle = document.getElementsByName("customEle_parm").length;
	  		for(var i=(cumEle/1-1);i>=0;i--){
		  		var customElei = document.getElementsByName("customEle_parm")[i].value;
	  			if(customElei!=null&&customElei!=""){
					var customEleFormi = document.getElementsByName("customEleForm")[i].value;
					if(payInte == ""){
						payInte = payInteDes.replace(new RegExp(customElei,"gm"),"("+customEleFormi+")");
					}else{
						payInte = payInte.replace(new RegExp(customElei,"gm"),"("+customEleFormi+")");
					}
		  		}
	  		}
	  		if(payInte == ""){//若无自定义指标，则payInte为空，需将描述赋给公式再替换
				payInte = payInteDes;
			}
	  		var bas_indic = new Array(6);
	  		bas_indic = document.operform.elements["bas_indic"].value.split(";");
	  		for(var i=0;i<bas_indic.length;i++){
				payInte = payInte.replace(new RegExp(bas_indic[i].split("-")[1],"gm"),bas_indic[i].split("-")[0]);
			}
	  		document.operform.elements["payInte"].value = payInte;
	  	}
	  	function func_submit(frm){
	  		var cumEle = document.getElementsByName("customEle_parm").length;
	  		var customEle = "";
	  		for(var i=0;i<cumEle;i++){
		  		if(document.getElementsByName("customEle_parm")[i].value!=""){
	  				customEle += "@"+document.getElementsByName("customEle_parm")[i].value+"="+ document.getElementsByName("customEleForm")[i].value;
		  		}
		  	}
	  		var formulaName = document.operform.elements["formulaName"].value;
	  		var payCap = document.operform.elements["payCap"].value;
	  		var payInte = document.operform.elements["payInte"].value;
	  		var strExp=/^[\u4e00-\u9fa5]/;
	  		if(formulaName=="" || formulaName==null){
	  			sAlert("未填写【还款计划名称】！");
				return false;
		  	}
	  		if(payInte=="" || payInte==null){
	  			sAlert("未填写【应还利息公式描述】！");
				return false;
		  	}
	  		if(payCap=="" || payCap==null){
	  			sAlert("未填写【应还本金公式描述】！");
				return false;
		  	}
	  		if(strExp.test(payInte)){
		  		sAlert("应还利息公式中存在未定义指标，请修改后保存！");
				return false;
		  	}
		  	if(strExp.test(payCap)){
				sAlert("应还本金公式中存在未定义指标，请修改后保存！");
				return false;
			}
		  	document.operform.elements["customEle"].value= customEle;
		  	frm.submit();
		}
		//自定义指标变动，则应收利息，应收本金公式都要做变动
		function changecustParm(){
			getPayInte();
			getPayCap();
		}
</script>
</head>
<body leftmargin="0" topmargin="0" class="body_bg">
	<div class="right_bg">
		<div class="right_w">
			<div class="from_bg">
				<div class="right_v">
			<s:actionerror />
		<s:actionmessage />
		<s:fielderror />
		<div>
			<div>
				<div>
				</div>
				<br>
				<div>
					<div  class="tabs-container" />
						<s:form name="operform" action="AcReplanFormulaAction_insert.action" method="post" theme="simple" validate="true">
							<table  align="center" width=1200  >
								<tr>
									<td align="right" width="20%" >
										还款计划编号
									</td>
									<td align="left" width="30%"><s:textfield alt="还款计划编号" property="formulaId" 
											name="formulaId"  readonly="true"/>[系统自动生成]
									</td>
									<td align="left" width="7%">还款计划名称<font color="red">*</font></td>
									<td align="left" width="30%"><s:textfield alt="还款计划名称" property="formulaName"  maxlength="50"											name="formulaName" />
									</td>
								</tr>
								<tr></tr>
								<tr>
									<td align="right" width="20%">
										基本指标<font color="red"></font>
									</td>
									<td align="left" colspan="3">
										<s:textarea alt="基本指标" property="bas_indic" readonly="true"
											name="bas_indic" rows="3"  />
									</td>
								</tr>
								<tr></tr>
								<tr>
									<td align="right" width="20%">
										自定义指标
									</td>
									<td align="left" >
										<s:textarea alt="自定义指标描述" 
											name="customEle_parm" onchange="changecustParm();"/>
									=
									</td>
									<td align="left" colspan="2">
										<s:textarea alt="自定义指标公式"
											name="customEleForm" rows="2" onchange="changecustParm();"/>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										自定义指标
									</td>
									<td align="left" >
										<s:textarea alt="自定义指标描述"
											name="customEle_parm" onchange="changecustParm();"/>
									=
									</td>
									<td align="left" colspan="2">
										<s:textarea alt="自定义指标公式"
											name="customEleForm" rows="2" onchange="changecustParm();" />
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										自定义指标
									</td>
									<td align="left" >
										<s:textarea alt="自定义指标描述"
											name="customEle_parm" onchange="changecustParm();"/>
									=
									</td>
									<td align="left" colspan="2">
										<s:textarea alt="自定义指标公式"
											name="customEleForm" rows="2" onchange="changecustParm();" />
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										应还利息公式描述<font color="red">*</font>
									</td>
									<td align="left" colspan="3" >
										<s:textarea alt="应还利息公式描述" property="payInteDes"   maxlength="1000"
											name="payInteDes" rows="3" onchange="getPayInte();"/>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										应还利息公式
									</td>
									<td align="left" colspan="3">
										<s:textarea alt="应还利息公式" property="payInte"  readonly="true" maxlength="1000"
											name="payInte" rows="3"  />
									</td>
								</tr>
								<tr></tr>
								<tr>
									<td align="right" width="20%">
										应还本金公式描述<font color="red">*</font>
									</td>
									<td align="left" colspan="3">
										<s:textarea alt="应还本金公式描述" property="payCapDes"  onchange="getPayCap()" maxlength="1000"
											name="payCapDes" rows="3"  />
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										应还本金公式
									</td>
									<td align="left" colspan="3">
										<s:textarea alt="应还本金公式" property="payCap" readonly="true" maxlength="1000"
											name="payCap" rows="3" />
									</td>
								</tr>
								<input type="hidden" name="flag"
									value="<%=request.getParameter("flag")%>" />
								<s:token />
							</table>
							<s:hidden name="customEle" ></s:hidden>
							<div class="from_btn">
								<dhcc:button typeclass="button3" value="保存" action="保存"
									onclick="func_submit(this.form)"></dhcc:button>
								<dhcc:button typeclass="btn_80" value="返回" action="返回"
									onclick="AcReplanFormulaAction_findByPage.action"></dhcc:button>
							</div>
								</s:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>