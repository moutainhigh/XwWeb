<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%
  String projNo = request.getParameter("projNo");
  String acctId = request.getParameter("acctId");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>小微金融运管平台管理系统</title>
<link href="css/FundCalReg_Comp.css" rel="stylesheet" type="text/css">
<link href="images/favicon.ico" mce_href="images/favicon.ico"
	rel="Shortcut Icon" type="image/x-icon">
<link href="images/favicon.ico" mce_href="images/favicon.ico"
	rel="Bookmark" type="image/x-icon">

<script language="javascript" src="js/wincontrol.js"></script>
<script language="javascript" src="js/Calendar.js"></script>
<script language="javascript" src="js/Calculator.js"></script>
<script language="javascript" src="js/WBselect.js"></script>
<script language="javascript" src="js/GetRate.js"></script>
<script language="javascript" src="js/Components.js"></script>
<script language="javascript" src="js/CheckDataFunction.js"></script>
</head>

<script type="text/javascript">
	function func_fundCalReg(){
		if(checkData()){
		var dataObj;
			$.ajax({
			    type:"POST", //请求方式
			    url:"../../ProjAcctAction_virtual_account_calculaor.action", //请求路径
			    cache: false, 
			    async: false,
			    data:{'projNo':document.getElementById("projNo").value,//项目编号
			    	  'acctId':<%=acctId%>,//发生金额
			    	  'endDate':document.getElementById("endDate").value,//截止日期
			    	  'CalCulType':document.getElementById("CalCulType").value},//测算交易类型	  
			    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
			    dataType: 'json',   
			    success:function(data){  
			    if(data==null){
			    	sAlert('数据不完整，无法测算');
			    }else{
			    	var dataObj=data;
	       			var CalCulType = data.CalCulType;
	       			var userDays = data.userDays;$("#userDays").val(userDays);
	       			var bal = data.bal;$("#bal").val(fmoney(bal,2));
	       			var amtbal = data.bal;$("#amtbal").val(fmoney(amtbal,2));
	       			var userAmt = data.userAmt;$("#userAmt").val(fmoney(userAmt,2));
			    }
		    	}
		});
		}
	}
	
	function checkData(){
		var endDate = document.getElementById("endDate").value;
		var CalCulType = document.getElementById("CalCulType").value;
		var result = true;
		if(CalCulType == '02' &&(endDate==''||endDate==null)){
			sAlert("金额测算，预测截止日期不能为空");
			result =  false;
		}
		return result;
		
	}
</script>
<script type="text/javascript">
	function func_fundCalRegNew(){
	document.getElementById("endDate").value = "";
	document.getElementById("bal").value = "";
	document.getElementById("amtbal").value = "";
	document.getElementById("userAmt").value = "";
	document.getElementById("userDays").value = "";
	}
</script>
<script type="text/javascript">
 function show_sub(v){  
		if(document.getElementById("CalCulType").value=="01"){
		document.getElementById("table1").style.display=""; 
		document.getElementById("table2").style.display = 'none';
		}else if(document.getElementById("CalCulType").value=="02"){
		document.getElementById("table1").style.display='none'; 
		document.getElementById("table2").style.display = "";
		}
	document.getElementById("endDate").value = "";
	document.getElementById("bal").value = "";
	document.getElementById("amtbal").value = "";
	document.getElementById("userAmt").value = "";
	document.getElementById("userDays").value = "";
}
</script>
<body class="right_bg">
	<div class="right_bg">
		<div class="right_w">
			<div class="from_bg">
				<table width="699" border="0" align="center" cellpadding="0" class="calculator-box"
					cellspacing="0" bgcolor="#FFFFFF">
					<tr>
						<td width="660" height="200" valign="top"><br />
							<table width="660" border="0" align="center" cellpadding="15">
								<tr>
									<td align="center" valign="top">
										<h1 class="calator-title">
											虚拟账户测算
										</h1>
									</td>
								</tr>
								<tr>
									<td align="center" class="dashlh"></td>
								</tr>
							</table>
							<form id="FundCalRegAction" name="FundCalRegAction" method="post">
							<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w" >
								<tr>
              						<td width="20%" align="right" class="tdlable">项目编号</td>
              						<td width="30%" align="left" class="tdvalue" >
                    					<input id="projNo" type="text"  name="projNo" tabIndex=6 class="txtnum" value="<%=projNo%>" readonly="readonly">
                					</td>
                					<td align="right" class="tdlable">测算类型</td>
				              		<td align="left" class="tdvalue" >
										<select name="CalCulType" id="CalCulType" onchange="show_sub(this.options[this.options.selectedIndex].value)">  
              								<option value="01">时间测算</option>
              								<option value="02">金额测算</option>
              							</select>
				                	</td>
								</tr>
				           	</table>	
				           	<table id="table1" width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w"  style="">
								<tr>
              						<td width="20%" align="right" class="tdlable">能用天数</td>
              						<td width="30%" align="left" class="tdvalue" >
                    					<input id="userDays" type="text"  name="userDays" tabIndex=6 class="txtnum" onchange="" readonly="readonly" style="border:none; border-bottom:1px solid block;">
                					    <FONT face="宋体">&nbsp;天</FONT>
                					</td>
              						<td width="20%" align="right" class="tdlable">剩余金额</td>
              						<td width="30%" align="left" class="tdvalue" >
                    					<input id="bal" type="text"  name="bal" tabIndex=6 class="txtnum" onchange="" readonly="readonly" style="border:none; border-bottom:1px solid block;">
                    					<FONT face="宋体">&nbsp;元</FONT>
                					</td>								
                				</tr>
				           	</table>
				           	<table id="table2" width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w"  style="display: none;">
								<tr>
              						<td align="right" class="tdlable">预测截止日期</td>
              						<td align="left" class="tdvalue" >
										<input  class="datelogo" style="width: 133px;" id="endDate" type="text" tabIndex="7" name="endDate" onClick="fPopUpCalendarDlg();"  readonly="readonly">
              							
              						</td>
								</tr>
								<tr>
              						<td width="20%" align="right" class="tdlable">所用金额</td>
              						<td width="30%" align="left" class="tdvalue" >
                    					<input id="userAmt" type="text"  name="userAmt" tabIndex=6 class="txtnum" onchange="" readonly="readonly" style="border:none; border-bottom:1px solid block;">
                    					<FONT face="宋体">&nbsp;元</FONT>
                					</td>
              						<td width="20%" align="right" class="tdlable">剩余金额</td>
              						<td width="30%" align="left" class="tdvalue" >
                    					<input id="amtbal" type="text"  name="amtbal"  class="txtnum" onchange="" readonly="readonly" style="border:none; border-bottom:1px solid block;">
                    					<FONT face="宋体">&nbsp;元</FONT>
                					</td>								
                				</tr>
				           	</table>				           										
							<table id="Table4" width="660" border="0" align="center"  cellspacing="0">
								<tr>
									<td colspan="4" align="center"  >
										<INPUT class="button3" id="btnReturnList1" tabIndex="7" style="width:88px" type="button"
										value="测算" name="btnReturnList1" onclick="func_fundCalReg()">
										<INPUT class="button3"  id="btnReturnList2" tabIndex="7" style="width:88px" type="button"
										value="重置" name="btnReturnList2" onclick="func_fundCalRegNew()">
										</td>
								</tr>
							</table>
			            	</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>