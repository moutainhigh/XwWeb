<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>小微金融运管平台管理系统</title>
<link href="css/Calculator.css" rel="stylesheet" type="text/css">
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
/**
*	通过传入的参数获取该参数的值，都为空给与提示
*/
function returnParms(query_parm){
	var isEmpty = true; 
	var val = "";
	var parms = "";
	if( query_parm.indexOf(",")>0 ){//where 后面有多个查询条件的
		var arr = query_parm.split(",");
		for( var i=0;i<arr.length;i++ ){
			if( arr[i].indexOf("@")>0 ){
				val = document.getElementsByName(arr[i].split("@")[0])[0].value ;
			}else{
				val = document.getElementsByName(arr[i])[0].value ;
			}
			if( val != null && val != "" && typeof(val)!='undefined' ){
				val = val.replace(/\,/g,"");
				parms += arr[i]+"="+val ;
				parms += ",";
				isEmpty=false;
				if(isHadChineseChart(val)){
					var len = getStrLength(val);
					if(len < 4){
						sAlert("请输入最少两个汉字进行查询!");
						return false;
					}
				}
			}
		}
		if( isEmpty ){
			//sAlert("请在相应页面属性输入值后，再点击放大镜查询!");
			//return false;
		}
	}else {//只有一个查询条件的
		if( query_parm != "" ){
			if( query_parm.indexOf("@")>0 ){
				val = document.getElementsByName(query_parm.split("@")[0])[0].value ;
			}else{
				val = document.getElementsByName(query_parm)[0].value ;
				val = val.replace(/\,/g,"");
			}
			if( val == "" ){
				//sAlert("请在相应页面属性输入值后，再点击放大镜查询!");
				//return false;
			}else{
				parms = query_parm + "=" + val;
			}
		}
	}
	return parms;
}

	/** 公用pop弹出页（单选框）
*	参数1标示场景ID
*   参数2表示要从页面传的值即SQL语句where后面的条件
**/
function func_popRadio(scene_id,query_parm){
	var parms = returnParms(query_parm);
	var returnVal;
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"../../PopParmConfAction_findByPop.action", //请求路径
	    cache: false, 
	    async: false,
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       		var db_rel = json.rel;
       		var sql = json.sql;
       		var col_name = json.col_name;
       		var size = json.size;
       		var query_name = json.query_name;
       		var disName = json.disName;
       		var pageNo = json.pageNo;
       		var query_sql= json.query_sql;
       		var hidden_col = json.hidden_col;
       		var if_query = json.if_query;
       		sql = sql.replace(/\%/g,"@");
       		sql = sql.replace(/\?/g,"%3F");
       		if( query_sql!=null ){
       			query_sql = query_sql.replace(/\%/g,"@");
       			query_sql = query_sql.replace(/\?/g,"%3F");
       		}
       		var url = "../../creditapp/dev/Pop_frame.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+
       					"&size="+size+"&query_name="+query_name+"&disName="+disName+"&pageNo="+pageNo+"&scene_id="+scene_id+
       					"&query_sql="+query_sql+"&hidden_col="+hidden_col+"&if_query="+if_query;
       		var returnVal;
       		var agent = navigator.userAgent.toLowerCase();
       		if (agent.indexOf("chrome") > 0 || agent.indexOf("firefox") > 0) {
       			var width = screen.availWidth;
       			var height = screen.availHeight;
       			var sFeatures = "status=no,width="+width+"px,height="+height+"px,top=0px,left=0px,menubar=no,scrollbars=no,alwaysLowered=yes;resizable=yes";
       		    var winObj = window.open(url, "_blank", sFeatures);
    		}else{
           		returnVal = window.showModalDialog(url,window,"dialogWidth=70;dialogHeight=38;center:yes;help:no;minimize:no;maximize:no;border:thin;status:no");

    		}
	    }
    });
	return returnVal;
}

</script>
<script type="text/javascript">
	function func_calPlanByPro(){
		
		if(checkData()){
			var dataObj;
			$.ajax({ 
			    type:"POST", //请求方式
			    url:"../../AcLnRepayPlnAction_calPlnsByPro.action", //请求路径
			    cache: false, 
			    async: false,
			    data:{'prdtNo':document.getElementById("prdtNo").value,//核算产品编号
			    	  'prdtId':document.getElementById("prdtId").value,
			    	  'loanAmt':document.getElementById("loanAmt").value,//贷款金额
			    	  'termMon':checkNull(document.getElementById("termMon").value),//贷款期限(月)
			    	  'termDay':checkNull(document.getElementById("termDay").value),//贷款期限(日)
			    	  'lnRate':document.getElementById("lnRate").value,//月利率
			    	  'repayDay':document.getElementById("repayDay").value,//还款日
			    	  'icDt':document.getElementById("icDt").value,//起息日期
			    	  'mtrDt':document.getElementById("mtrDt").value},//到期日期
			    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
			    dataType: 'json',   
			    success:function(data){  
	       			var dataObj=data;
	       			var $tab1 = $("#Table3");
	       			var tab = document.getElementById("Table3"); 
	       			$("#Table3 tr:gt(0)").remove();
	       			var instm=0.0;
	       			var prcp=0.0;
	       			var norm=0.0;
	       			for(var i=0;i<dataObj.length;i++){
	       				instm=instm+parseFloat(dataObj[i].instmAmt);
	       				prcp=prcp+parseFloat(dataObj[i].prcpAmt);
	       				norm=norm+parseFloat(dataObj[i].normInt);		
	       				var newRow = "<tr><td align='center'>"+dataObj[i].perdNo+"</td><td align='center'>"+dataObj[i].payDt+"</td><td align='center'>"+dataObj[i].instmAmt+"</td><td align='center'>"+dataObj[i].prcpAmt+"</td><td align='center'>"+dataObj[i].normInt+"</td></tr>";
	       				$("#Table3 tr:last").after(newRow);
	       			}	       			
	       		  	var newRow1 = "<tr><td align='center'>合计</td><td align='center'></td><td align='center'>"+instm.toFixed(2)+"</td><td align='center'>"+prcp.toFixed(2)+"</td><td align='center'>"+norm.toFixed(2)+"</td></tr>";
	       			$("#Table3 tr:last").after(newRow1);
		    	}
		});
		}
	}
	function checkNull(val) {
		if(val==null||val==undefined||val==""){
			return 0;
		}else{
			return val;
		}		
	}
</script>
   <script type="text/javascript">
	function checkData(){
		var reg = /^[0-9]+$/;
		var regFloat = /^\d+.\d+$|^\d+$/;
		var result = true;
		var prdtNo = document.getElementById("prdtNo").value;
		if(prdtNo==''||prdtNo==null){
			sAlert("请选择产品类型");
			result = false;
		}		
		
		var blloanAmt = regFloat.test(document.getElementById("loanAmt").value);
		if(!blloanAmt){
			sAlert("贷款金额为整数或小数");
			$("#loanAmt").val("");
			result = false;
		}
		
		var bllnRate = regFloat.test(document.getElementById("lnRate").value);
		if(!bllnRate){
			sAlert("月利率整数或小数");
			$("#lnRate").val("");
			result = false;
		}
		
		var bl1= checkNull(document.getElementById("termMon").value);
		var bl = reg.test(bl1);
		if(!bl){
			sAlert("贷款期限(月)必须为数字");
			$("#termMon").val("");
			result = false;
		}
		
		var bld1 = checkNull(document.getElementById("termDay").value);
		var bld = reg.test(bld1);
		if(!bld || parseInt(bld1)>30){
			sAlert("贷款期限(日)必须为0-30的数字");
			$("#termDay").val("");
			result = false;
		}
		
		if(bld1==0&&bl1==0){
			sAlert("贷款期限(月)和贷款期限(日)不能同时为空或同时为0");
			$("#termMon").val("");
			$("#termDay").val("");
			result = false;
		}
		
		var repay = document.getElementById("repayDay").value;
		var repayDay = parseInt(repay);
		if(repayDay>31||repay==''||repay==null||!reg.test(repay)){
			sAlert("还款日不能大于31天且不能为空");
			$("#repayDay").val("");
			result =  false;
		}
		
		var icDt = document.getElementById("icDt").value;
		if(icDt==''||icDt==null){
			sAlert("起息日期不能为空");
			result = false;
		}
		
		var mtrDt = document.getElementById("mtrDt").value;
		if(mtrDt==''||mtrDt==null){
			sAlert("到期日期不能为空");
			result = false;
		}
		return result;
	}
</script>
<script type="text/javascript">
	function printme()	{ 
		//document.body.innerHTML=document.getElementById('Table3').innerHTML;
		/**var bdhtml=window.document.body.innerHTML;    
		sprnstr="<!--startprint-->";    
		eprnstr="<!--endprint-->";    
		prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);    
		prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
		window.document.body.innerHTML=prnhtml;   
		alert(prnhtml);**/
		window.print(); 
	} 	
	//自动根据期限和起始日期计算结束日期
	function checkDate1(){
		var reg = /^[0-9]+$/;
		var beg_date = document.getElementById("icDt").value;
		var term_mon = document.getElementById("termMon").value;
		var term_day = document.getElementById("termDay").value;
		if(term_mon==null||term_mon==undefined||term_mon==""){
			term_mon="0";
		}else if(!reg.test(term_mon)){
			term_mon="0";
		}
		if(term_day==null||term_day==undefined||term_day==""){
			term_day="0";
		}else if(!reg.test(term_day)){
			term_day="0";
		}
		if(beg_date!=null&&beg_date!=undefined&&beg_date!=""){
				if($.trim(beg_date).length!=0&&beg_date!=null&&beg_date!=undefined){
        			$.post("../../AcLnRepayPlnActionCheckDate.action",{
					beg_date : beg_date,
					term_mon : term_mon,
					term_day : term_day
				},function(data){
					document.getElementById("mtrDt").value =data;
				});
       			 }else{
       			// document.getElementById("mtrDt").value =""
       			return false;
     	   }
		}
	}
	function resetPage(){
		window.location.href="Calculator.jsp";
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
										<h1 class="calator-title ca-T1">
											利息计算器
										</h1>
									</td>
								</tr>
								<tr>
									<td align="center" class="dashlh"></td>
								</tr>
							</table>
							<form id="AcLnRepayPlnAction" name="AcLnRepayPlnAction" method="post">
							<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w" >
								<TR>
              						<td align="right" class="tdlable">产品名称</TD>
              						<td colspan="1"  align="left" class="tdvalue" >
              							<input id="prdtName" type="text" class="INPUT_TEXT" name="prdtName" style="width: 133px;" tabIndex=1 readonly >
                    					<input id="prdtNo" type="text"  name="prdtNo" tabIndex=6 style="display: none;" readonly >
                    					<input id="prdtId" type="text"  name="prdtId" tabIndex=6 style="display: none;" >
                      					<input type="button" value="&nbsp" name="&nbsp"   class="btn_50" onclick="func_popRadio('POP088','')"> 
									<td align="right" class="tdlable">合作机构名称</TD>
              						<td align="left" class="tdvalue" ><input  class="textnum" id="brName" type="text" tabIndex=1 name="brName" disabled></TD>
                					</TD>
            					</TR>
            					<tr>
									<td  align="right" class="tdlable">贷款金额</td>
              						<td align="left" class="tdvalue" ><input class="INPUT_TEXT" tabIndex="2" style="width: 133px;"  id="loanAmt"  type="text" name="loanAmt" onchange="checkNum(this)" >
                					<FONT face="宋体">&nbsp;元</FONT></td>
									<td align="right" class="tdlable">月利率</TD>
              						<td align="left" class="tdvalue" ><input  class="INPUT_TEXT" id="lnRate" style="width: 133px;" type="text" tabIndex="3" name="lnRate">&nbsp;‰</TD>
								</tr>
								<tr>              						
									<td  align="right" class="tdlable">贷款期限</TD>
              						<td align="left" class="tdvalue" >
              							<input class="INPUT_TEXT" id="termMon" style="width: 133px;"  tabIndex="4" type="text" name="termMon" onchange="checkDate1()" >
                						<FONT face="宋体">&nbsp;月</FONT>
                					</TD>
                					<td  align="right" class="tdlable">贷款期限</TD>
              						<td align="left" class="tdvalue" >
              							<input class="INPUT_TEXT" id="termDay" style="width: 133px;"  tabIndex="5" type="text" name="termDay" onchange="checkDate1()">
                						<FONT face="宋体">&nbsp;日</FONT>
                					</TD>
              						<!-- <input class="btn" id="btnPrint" type="button" value="实际还款清单" style="width:88px" NAME="btnPrint" tabIndex="8"> -->
           						</tr>
								<tr>
									<td  align="right" class="tdlable">还款日</td>
									<td align="left" class="tdvalue" >
										<input id="repayDay" type="text" name="repayDay" tabIndex="6" class="INPUT_TEXT" style="width: 133px;" onchange="checkRepayDay(this);checkNum(this)">
									</td>
									<td></td>
									<td></td>
								</tr>
            					<TR>
				              		<td align="right" class="tdlable">起息日期</TD>
				              		<td align="left" class="tdvalue" >
										<input  class="datelogo" style="width: 133px;" id="icDt" type="text" tabIndex="7" name="icDt" onClick="fPopUpCalendarDlg();"  onchange="checkDate1()" readonly="readonly">
				                	</TD>
									<td align="right" class="tdlable">到期日期</TD>
              						<td align="left" class="tdvalue" >                    					
                      					<input class="textnum" tabIndex="8" id="mtrDt"  type="text" name="mtrDt" disabled  readonly="readonly">										
                					</TD>
				           		</TR>
								<tr>
									<td colspan="4" align="center"  ><INPUT class="btn" id="btnReturnList"
										tabIndex="9" style="width:88px" type="button"
										value="还款清单" name="btnReturnList"
										onclick="func_calPlanByPro()">
										</td>
								</tr>
							</table><br>
							<table id="Table2" width="660" border="0" align="center"  cellspacing="0">
									<tr>
										<td align="center" valign="top" colspan="6" >
										<h1 class="calator-title ca-T2">
											计算结果											
											<input class="btnDY" id="btnPrint" type="button" value="打印还款清单" style="width:88px" NAME="btnPrint" tabIndex="11" 
											onclick="printme()"  target="_self"/>
											<input class="btnDY" id="reset" type="button"  value="重置" style="width:88px" onclick="resetPage()" NAME="reset" tabIndex="10" />
										</h1>
										</td>
									</tr>
							</table>
							<table id="Table3" width="660" border="0" align="center"  cellspacing="0" class="calator-list">
			                		<tr class="head">
					                  <th align="middle">期次</th>
					                  <th align="middle"> 还款日期</th>
					                  <th align="middle"> 期供金额（元）</th>
					                  <th align="middle"> 还款本金（元）</th>
					                  <th align="middle"> 还款利息（元）</th>
			                		</tr>
			            	</table>
			            	</form>
							<table width="580" border="0" align="center" cellpadding="8"
								cellspacing="0">

								<tr>
									<td width="274" height="40" valign="bottom" class="dashlh"><h3></h3>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>