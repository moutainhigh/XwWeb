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
			document.operform.elements["bas_indic"].value="A-贷款本金;B-本期应收本金;C-本期应收利息;D-费率";
			var customEle = new Array();
				customEle = document.operform.elements["customEle"].value.split("@");
			for(var i=0;i<customEle.length/1-1;i++){
				document.getElementsByName("customEle_parm")[i].value=customEle[i+1].split("=")[0];
				document.getElementsByName("customEleForm")[i].value=customEle[i+1].split("=")[1];
			}
		});
		
	  	/*
	  	 * 费用公式转换
	  	 */
	  	function getFeeFormula(){
	  		document.operform.elements["feeFormula"].value = "";
	  		var feeFormulaDes = document.operform.elements["feeFormulaDes"].value;
	  		var feeFormula = feeFormulaDes;
	  		
	  		var bas_indic = new Array();
	  		bas_indic = document.operform.elements["bas_indic"].value.split(";");
	  		for(var i=0;i<bas_indic.length;i++){
	  			feeFormula = feeFormula.replace(new RegExp(bas_indic[i].split("-")[1],"gm"),bas_indic[i].split("-")[0]);
				}
	  		document.operform.elements["feeFormula"].value = feeFormula;
	  	}
	  	/*
	  	 * 保存
	  	 */
	  	function func_submit(frm){
	  		
	  		var feeName = document.operform.elements["feeFormName"].value;
	  		var feeFormulaDes = document.operform.elements["feeFormulaDes"].value;
	  		var feeFormula = document.operform.elements["feeFormula"].value;
	  		var strExp=/^[\u4e00-\u9fa5]/;
	  		if(feeName=="" || feeName==null){
	  			sAlert("未填写【费用公式名称】！");
				return false;
		  	}
	  		if(feeFormulaDes=="" || feeFormulaDes==null){
	  			sAlert("未填写【费用公式描述】！");
				return false;
		  	}
	  		if(strExp.test(feeFormula)){
		  		sAlert("费用公式中存在未定义指标，请修改后保存！");
				return false;
		  	}
		  	frm.submit();
		}
	  //获取费率编号
	  /*
	  	function func_getFeeId(){
	  		var feeId = document.getElementsByName('feeId')[0].value;
	  		var url ="AcFeeRateAction_findByPop.action?longFeeId="+feeId ;
	  		showDialog(url);
	  	}
	  	*/
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
						<s:form name="operform" action="AcFeeFormulaAction_insert.action" method="post" theme="simple" validate="true">
							<table  align="center" width=1200  >
								<tr>
									<td align="right" width="20%" >
										费用公式编号
									</td>
									<td align="left" width="30%"><s:textfield alt="费用公式编号" property="feeFormId" 
											name="feeFormId"  readonly="true"/>[系统自动生成]
									</td>
									<td align="left" width="7%">费用公式名称<font color="red">*</font></td>
									<td align="left" width="30%"><s:textfield alt="费用公式名称" property="feeFormName"  maxlength="50"
											name="feeFormName" />
									</td>
								</tr>
								<tr>
									<td align="right" width="20%" >
										费率编号
									</td>
									<td align="left" width="30%" ><s:textfield alt="费率定义编号" property="frId"  readonly="true"
											name="frId" />
										<input type="button" value="&nbsp" name="&nbsp"  class="btn_50" 
											onclick="func_popCheckbox('CBPOP012','');"/>
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
										费用公式描述<font color="red">*</font>
									</td>
									<td align="left" colspan="3" >
										<s:textarea alt="费用公式描述" property="feeFormulaDes"  maxlength="500"
											name="feeFormulaDes" rows="3" onchange="getFeeFormula();"/>
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										费用公式
									</td>
									<td align="left" colspan="3">
										<s:textarea alt="费用公式" property="feeFormula"  readonly="true"
											name="feeFormula" rows="3"  />
									</td>
								</tr>
								
							</table>
							<s:hidden name="customEle" ></s:hidden>
							<div class="from_btn">
								<dhcc:button typeclass="button3" value="保存" action="保存"
									onclick="func_submit(this.form)"></dhcc:button>
								<dhcc:button typeclass="btn_80" value="返回" action="返回"
									onclick="AcFeeFormulaAction_findByPage.action"></dhcc:button>
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