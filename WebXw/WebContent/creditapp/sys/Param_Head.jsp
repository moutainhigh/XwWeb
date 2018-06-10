<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>	
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%
	Object themeObj2 = session==null?null:session.getValue("color");
	String theme2 = (themeObj2==null||"".equals(themeObj2))?"yellow":((String)themeObj2);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/include/tld.jsp"%>
<head>
<script language="javascript" src="<%=request.getContextPath()%>/themes/js/jquery-1.9.0.min.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/creditapp/js/pop.js"></script>

<link href="<%=request.getContextPath()%>/themes/theme_<%=theme2 %>/Css/sysUI_<%=theme2 %>.css" type="text/css" rel="stylesheet" />

<%
	String brNo = request.getParameter("brNo") ;
	String isQuery = request.getParameter("query");
	String ecif_no = request.getParameter("ecif_no");	
%>
<script type="text/javascript">
var curStepNo=0;
function setBrNo(){
	var brNo = document.getElementById("brNo").value;
	return brNo;
}
function getStepNo(){
	var stepNo = self.parent.frames["parmsFrame_left"].setStepNo();
	return stepNo;
}

function changeframe() {	
	var brNo = document.getElementById("brNo").value;
	if(brNo==null||brNo==undefined||brNo==""){
		alert("请选择合作机构");
		return false;
	}else{
		curSteoNo=0;
		window.open("ParamIntegAction_findDetail.action?query=query&brNo="+brNo, target = "parmsFrame");
	}
}
function clicktest() {
	var index = curStepNo;
    var obj = $(self.parent.frames["parmsFrame_left"].document).find('a').eq(index);
    $(obj).click();
	$(obj).parent().parent().parent().find(".linktd").find("a").each(function(){
		$(this).removeClass("checkDiv");
	});
	$(obj).addClass("checkDiv");
}


function nextStep(){
	curStepNo=curStepNo+1;
    var url = "<%=request.getContextPath()%>/creditapp/welcome.jsp";
	var brNo = document.getElementById("brNo").value;
	 if(curStepNo==1){	//罚息公式配置
		url="AcFineFormulaAction_findByPage.action";
	}else if(curStepNo==2){	//提前还款违约金配置
		url="AcDamFormulaAction_findByPage.action";
	}else if(curStepNo==3){	//费用定义配置
		url="AcFeeParmAction_findByPage.action";
	}else if(curStepNo==4){ 	//费用公式配置
		url="AcFeeFormulaAction_findByPage.action";
	}else if(curStepNo==5){	 //公共费率配置
		url="AcDamFormulaAction_findByPage.action";
	}else if(curStepNo==6){	//还款计划公式配置
		url="AcReplanFormulaAction_findByPage.action";
	}else if(curStepNo==7){		//还款方案配置
		url="AcReplanParmAction_findByPage.action";
	}else if(curStepNo==8){		//产品管理
		url="ParamIntegAction_getPrdtList.action?brNo="+brNo;			
	}else if(curStepNo==9){ //储存过程
		url="<%=contextpath%>";
	}else if(curStepNo==10){ //更新数据
		url="<%=contextpath%>";
	}else if(curStepNo==11){ //数据校验
		url="<%=contextpath%>";
	}else if(curStepNo > 11){ //没有下一步，跳转失败
		curStepNo=curStepNo-1;
		url="";
		return false;
	}	
	if (url != "") {
		window.open(url, target = "parmsFrame");
	}
	clicktest();
}

function lastStep(){
	curStepNo=curStepNo-1;	
    var url = "<%=request.getContextPath()%>/creditapp/welcome.jsp";
    var brNo = document.getElementById("brNo").value;
    if(curStepNo < 0){ //没有上一步，跳转失败
		curStepNo=curStepNo+1;
		url="";
		return false;
	}else if(curStepNo==0){
		url="ParamIntegAction_findDetail.action?query=query&brNo="+brNo;	
	}else  if(curStepNo==1){	//罚息公式配置
		url="AcFineFormulaAction_findByPage.action";
	}else if(curStepNo==2){	//提前还款违约金配置
		url="AcDamFormulaAction_findByPage.action";
	}else if(curStepNo==3){	//费用定义配置
		url="AcFeeParmAction_findByPage.action";
	}else if(curStepNo==4){ 	//费用公式配置
		url="AcFeeFormulaAction_findByPage.action";
	}else if(curStepNo==5){	 //公共费率配置
		url="AcDamFormulaAction_findByPage.action";
	}else if(curStepNo==6){	//还款计划公式配置
		url="AcReplanFormulaAction_findByPage.action";
	}else if(curStepNo==7){		//还款方案配置
		url="AcReplanParmAction_findByPage.action";
	}else if(curStepNo==8){		//产品管理
		url="ParamIntegAction_getPrdtList.action?brNo="+brNo;			
	}else if(curStepNo==9){ //储存过程
		url="<%=contextpath%>";
	}else if(curStepNo==10){ //更新数据
		url="<%=contextpath%>";
	}	
	if (url != "") {
		window.open(url, target = "parmsFrame");
	}
	clicktest();
}
</script>
</head>

<body>	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="from_w" >
			<tr>
              	<td align="right" class="tdlable">合作机构号</td>
              		<td colspan="1"  align="left" class="tdvalue" >
              			<input id="brNo" type="text" class="INPUT_TEXT" name="brNo"  tabIndex=1 readonly >
                		<input type="button" name="&nbsp"   class="btn_50" onclick="func_popRadio('POP038','') "> 
                	</td>            	
              		<td align="right" class="tdlable">合作机构名称</td>
              		<td align="left" class="tdvalue" >
              			<input  class="textnum" id="brName" type="text" style="width:200px" name="brName" disabled>
              		</td> 
            	</tr>
            	<tr>
					<td  align="right" class="tdlable">合作机构类型</td>
              		<td align="left" class="tdvalue" >
              			<input class="textnum" tabIndex="2" style="width:200px" id="brType" type="text" name="brType" disabled>
                	</td>
					<td align="right" class="tdlable">登记人员</TD>
              		<td align="left" class="tdvalue" >
              			<input  class="textnum" id="lnRate" style="width:200px" type="text" tabIndex="3" id="opNo" name="opNo" disabled>
              		</td>
				</tr>
            
            	<tr>					
					<td align="center" colspan="4" class="tdlable">
						<input type="button" href="#" onclick="changeframe();"
							value="准入检查" class="btn_80" id="btnDetail"	tabIndex="2" style="width:88px">
					</td>
				</tr>
				<tr>
					<td align="right" colspan="4" class="tdlable">
						<input type="button" href="#" onclick="lastStep()"
							value="上一步" class="btn_80" id="lastPage" name="lastPage" tabIndex="2" style="width:88px">
						<input type="button" href="#" onclick="nextStep()"
							value="下一步" class="btn_80" id="nextPage" name="nextPage" tabIndex="2" style="width:88px">
					</td>			
				</tr>					
	</table>
</body>
</html>