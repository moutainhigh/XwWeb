<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	Object themeObj = session==null?null:session.getValue("color");
	String theme = (themeObj==null||"".equals(themeObj))?"yellow":((String)themeObj);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="X-UA-Compatible" content="IE=11" />
<link rel='stylesheet' href='<%=request.getContextPath()%>/creditapp/calendar/css/jquery-ui.css' />

<title>测试用</title>
<style type="text/css">
	input,textarea,select { display:inline-block; margin-right:3px; vertical-align:middle;}
	.btn_50 {background: url(<%=request.getContextPath()%>/themes/theme_<%=theme %>/images/popico.png) no-repeat;background-position-x: 50%;width: 20;border:none;}
</style>
<script src='<%=request.getContextPath()%>/creditapp/calendar/js/jquery-form.js'></script>
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
	    url:"PopParmConfAction_findByPop.action", //请求路径
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
       		var url = "creditapp/dev/Pop_frame.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+
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
</head>
<body>
<div class="fancy" id="rootDiv">
	<h3 style="font-size: 16px;margin-bottom: 5px;margin-top: 5px;" id="htitle">提醒事件管理</h3>
    <form id="add_form" action="insertNewEvent.action" method="post">
	    <input type="hidden" name="action" value="add">
	    <p id="taskSelectDiv" style="display: none">  
	   	<span class="spanTitle">执行任务:</span>
	    <select name="seId" id="seId" style="width:100px" onchange="changeTempleInput()">
	    	<s:iterator value="scheduleTaskList" id="task">
	    		<option templateName='<s:property value="templateName"/>' value='<s:property value="seId"/>'><s:property value="jobName"/> </option>
	    	</s:iterator>
	    </select>
	    </p>
	    <p class="loan">
	    	<span class='spanTitle'>机构编号:</span>
	    	<input type='text' data-type='String' class='inputArg' required='required' name="brNo"/>
	    	<input type="button" value="&nbsp" name="&nbsp"   class="btn_50" onclick="func_popRadio('POP077','')">
	    </p>
	    <p class="appro">
	    	<span class='spanTitle'>项目编号:</span>
	    	<input type='text' data-type='String' class='inputArg' required='required' name="projNo" id="input"/>
	    	<input type="button" value="&nbsp" name="&nbsp" class="btn_50" onclick="func_popRadio('POP063','')">
	    </p>
	    <p class="appro">
	    	<span class='spanTitle'>放款金额:</span>
	    	<input type='text' data-type='Integer' class='inputArg' required='required' id="input"/>
	    </p>
	    <p class="appro">
	    	<span class='spanTitle'>虚拟账户ID:</span>
	    	<input type='text' data-type='String' class='inputArg' required='required' id="input"/>
	    </p>
	    <p id="eventContentDiv" style="padding: 4px;margin: 0px;margin-top:0px; font-size: 14px;vertical-align: top;height: auto;" >
	    	<span class="spanTitle" id="spanTitle">提醒内容:</span><span style="margin-left: 8px"><textarea rows="5" cols="45" style="padding-left: 5px;" id="eventContent"></textarea></span>
	    </p>
	    <p style="margin: 0px;margin-top:10px; font-size: 14px" id="p_starttime">
	    	<span class="spanTitle" id="timeTitle" >开始时间：</span>
	    	<input type="text" class="input datepicker" readonly="readonly" name="startdate" id="startdate" value='<%=request.getParameter("selDate")%>' onchange="changeStartDate(this.value)" onclick="fPopUpCalendarDlg()">
		    <span id="sel_start" style="">
			    <select name="s_hour" id="s_hour">
			    	<option value="00">00</option>
			        <option value="01">01</option>
			        <option value="02">02</option>
			        <option value="03">03</option>
			        <option value="04">04</option>
			        <option value="05">05</option>
			        <option value="06">06</option>
			        <option value="07">07</option>
			        <option value="08" selected>08</option>
			        <option value="09">09</option>
			        <option value="10">10</option>
			        <option value="11">11</option>
			        <option value="12">12</option>
			        <option value="13">13</option>
			        <option value="14">14</option>
			        <option value="15">15</option>
			        <option value="16">16</option>
			        <option value="17">17</option>
			        <option value="18">18</option>
			        <option value="19">19</option>
			        <option value="20">20</option>
			        <option value="21">21</option>
			        <option value="22">22</option>
			        <option value="23">23</option>
			    </select>:
			    <select name="s_minute" id="s_minute">
			    	<option value="00" selected>00</option>
			        <option value="10">10</option>
			        <option value="20">20</option>
			        <option value="30">30</option>
			        <option value="36">36</option>
			        <option value="40">40</option>
			        <option value="50">50</option>
			    </select>
		    </span>
	    </p>
	    <p id="p_endtime" style="display:none; padding: 4px;font-size: 14px">
	    	<span class="spanTitle" style="padding-right: 6px">结束时间：</span><input type="text" class="input datepicker" readonly="readonly" name="enddate" id="enddate" value="">
		    <span id="sel_end" style="">
			    <select name="e_hour" id="e_hour">
			    	<option value="00">00</option>
			    	<option value="01">01</option>
			        <option value="02">02</option>
			        <option value="03">03</option>
			        <option value="04">04</option>
			        <option value="05">05</option>
			        <option value="06">06</option>
			        <option value="07">07</option>
			        <option value="08">08</option>
			        <option value="09">09</option>
			        <option value="10">10</option>
			        <option value="11">11</option>
			        <option value="12" selected>12</option>
			        <option value="13">13</option>
			        <option value="14">14</option>
			        <option value="15">15</option>
			        <option value="16">16</option>
			        <option value="17">17</option>
			        <option value="18">18</option>
			        <option value="19">19</option>
			        <option value="20">20</option>
			        <option value="21">21</option>
			        <option value="22">22</option>
			        <option value="23">23</option>
			    </select>:
			    <select name="e_minute" id="e_minute">
			    	<option value="00" selected>00</option>
			        <option value="10">10</option>
			        <option value="20">20</option>
			        <option value="30">30</option>
			        <option value="40">40</option>
			        <option value="50">50</option>
			    </select>
		    </span>
	    </p>
	    <p id="repeat_p" style="display: none">
	    	<span class="spanTitle">截至时间：</span>
	    	<input type="text" readonly="readonly" class="input datepicker" name="repeatEndDate" id="repeatEndDate" value='' onclick="fPopUpCalendarDlg()">
		</p>
		<p id="gapdays_p" style="display: none">
	    	 <span class="spanTitle">间隔天数：</span>
	    	 <input type="text" class="input datepicker" name="gapDays" id="gapDays" value=''>
		</p>
	    <p>
	    	<!-- 全天事件，为待办事件所用  新需求中已被取消，所有事件均为全天事件
		    <label id="isalldayLabel"><input type="checkbox" value="1" id="isallday" name="isallday" checked> 全天</label>
	    	 -->
	    	<!-- 重复事件，为定时任务设定所用 -->
	    	<label id="isRepeatLabel" style="display: none"><input type="checkbox" value="1" id="isRepeat" name="isRepeat"> 重复事件</label>
	    </p>
	    
	    <div class="sub_btn">
	    	<span class="del" id="del_span">
	    		<input type="button" class="btn btn_del" id="del_event" value="删除">
	    	</span>
	    	<input type="button" id="check_ok" class="btn btn_ok" value="确定">
	    	<input type="button" class="btn btn_cancel" value="取消" onClick="$.fancybox.close()">
	    </div>
	   	<br/>
	    <div class="sub_btn">
	    	<input type="button" class="btn btn_change" value="定时任务设定" id="to_quartzTask" >
	    	<input type="button" class="btn btn_change" value="待办任务管理" id="to_penddingTask" style="display: none">
	    </div>
    </form>
</div>
<style>
.hidArg{display: none};
</style>

<script type="text/javascript">

/***************************根据模板编号确定参数模板********************************/
 
function changeTempleInput(){
	var name = $("#seId").find("option:selected").attr("templateName");
	$(".loan,.appro,.bat").addClass("hidArg");
	if(name =="" || name == "undefined" || name==null){
		
	}else{
		$("."+name+"").removeClass("hidArg");
	}
	
}

/***************************页面调整部分****************************/
$("#isallday").click(function(){
	if($("#sel_start").css("display")=="none"){
		$("#rootDiv").height(370);
		$("#sel_start,#p_endtime,#sel_end").show();
		$("#enddate").val($("#startdate").val());
	}else{
		$("#sel_start,#p_endtime,#sel_end").hide();
		$("#rootDiv").height(340);
	}
	$.fancybox.resize();//调整高度自适应
});

//结束日期应跟随开始日期相同
function changeStartDate(value){
	$("#enddate").val(value);
}

//当选择定时任务设定时，关闭结束日期以及全天事件，开启重复时间选择框
$("#to_quartzTask").click(function(){
	$("#htitle").html("定时任务设定");
	$("#spanTitle").html("定时任务设定");
	$("#p_endtime,#sel_end").hide('slow');
	$("#to_quartzTask,#isalldayLabel,#eventContentDiv").hide('slow');
	
	$("#to_penddingTask,#isRepeatLabel,#taskSelectDiv").show(800,function(){
		var hadRepeat = $("#isRepeat").get(0).checked;
		if(hadRepeat){
			$("#repeat_p,#gapdays_p").show();
		}else{
			$("#repeat_p,#gapdays_p").hide();
		}
	});
	changeTempleInput();
});
//定时任务转向待办任务设定
$("#to_penddingTask").click(function(){
	$("#htitle").html("提醒事件管理");
	$("#spanTitle").html("提醒内容");
	initDiv(340);
	$("#repeat_p,#gapdays_p,#isRepeatLabel,#to_penddingTask,#taskSelectDiv").hide(1000);
	$("#p_starttime,#isalldayLabel,#to_quartzTask,#eventContentDiv").show('slow',function(){
		
	});
	$(".loan,.appro,.bat").addClass("hidArg");
});

$("#isRepeat").click(function(){
	if($("#repeat_p").css("display")=="none"){
		initDiv(380);
		$("#repeat_p,#gapdays_p").show();
	}else{
		initDiv(340);
		$("#repeat_p,#gapdays_p").hide();
	}
	$.fancybox.resize();//调整高度自适应
});

function initDiv(hvalue){
	$("#rootDiv").height(hvalue);	
	$.fancybox.resize();//调整高度自适应
	//$("#rootDiv").animate({height:hvalue+"px"},"slow",null,function(){
	//	$.fancybox.resize();//调整高度自适应
	//});
}


/************业务逻辑部分开始***************************/

$(function(){
	$(".loan,.appro,.bat").addClass("hidArg");
	
	var eventId = '<%=request.getParameter("eventId")%>';
	var startTime;
	var endTime;
	var allDay;
	var isUpdate = false;
	
	if(eventId && eventId!=""){//由事件跳转来，为事件修改触发
		isUpdate = true;
		var calEventArray = cal12.fullCalendar('clientEvents');
		var calEvent;
		for(var ce in calEventArray){
			if(calEventArray[ce].eventId == eventId){
				calEvent = calEventArray[ce];
				if(!startTime)startTime = calEvent.start;
				else startTime = startTime < calEvent.start ? startTime : calEvent.start;
			}
		}
		startTime = $.fullCalendar.formatDate(startTime,'yyyy-MM-dd HH:mm');
		endTime = $.fullCalendar.formatDate(calEvent.end,'yyyy-MM-dd HH:mm');
		allDay = calEvent.allDay+"";
		$("#eventContent").val(calEvent.title);
		
		if(calEvent.impLevel == '1'){//待办事项
			$("#htitle").html("提醒事件管理");
			initDiv(340);
			$("#repeat_p,#gapdays_p,#isRepeatLabel,#to_penddingTask,#taskSelectDiv").hide(1000);
			$("#p_starttime,#isalldayLabel,#to_quartzTask,#eventContentDiv").show('slow',function(){
				
			});
			$(".loan,.appro,.bat").addClass("hidArg");
		}else{
			//alert(calEvent.seId+","+calEvent.taskId);
			$("select[name='seId']").val(calEvent.seId); 
			
			$("#htitle").html("定时任务设定");
			$("#p_endtime,#sel_end").hide('slow');
			$("#to_quartzTask,#isalldayLabel,#eventContentDiv").hide('slow');
			
			$("#to_penddingTask,#isRepeatLabel,#taskSelectDiv").show();
			
			if(calEvent.repeat && calEvent.repeat>0){
				$("#isRepeat").attr("checked","checked");
				$("#repeat_p,#gapdays_p").show();
				$("#repeatEndDate").val(calEvent.repeat_endDate);
				$("#gapDays").val(calEvent.repeat);
				initDiv(500);
			}
			changeTempleInput();
			//将参数赋予对应的值
			if(calEvent.argsObjs!=null && calEvent.argsObjs.length > 0){
				var oldArgsValues = calEvent.argsObjs.split("|");
				var templateType = oldArgsValues[0].split("-")[1];
				var currIndex = 1;
				if(templateType == "loan"){
					$(".loan").find("input").each(function(){
						$(this).val(oldArgsValues[currIndex++].split("-")[1]);
					});
				}else if(templateType == "appro"){
					$(".appro").find("#input").each(function(){
						$(this).val(oldArgsValues[currIndex++].split("-")[1]);
					});
				}else if(templateType == "bat"){
					$(".bat").find("#input").each(function(){
						$(this).val(oldArgsValues[currIndex++].split("-")[1]);
					});
				}
			}
		}
		
	}else{//新增事件
		$("#del_span").hide();
		startTime = '<%=request.getParameter("startTime")%>';
		endTime = '<%=request.getParameter("endTime")%>';
		allDay = '<%=request.getParameter("allDay")%>';
	}
	
	if(startTime){
		var startDateSel = startTime.substring(0,10);
		$("#startdate").val(startDateSel);
		if(allDay && allDay =="true"){
		}else{
			$("#isallday").attr("checked",false);
			var startHour = startTime.substring(11,13);
			var startMin = startTime.substring(14,startTime.length);
			//$("#sel_start").show();
			$("#s_hour").val(startHour)
			$("#s_minute").val(startMin)
			if(endTime){
				initDiv($("#rootDiv").height()+30);
				var endDateSel = endTime.substring(0,10);
				$("#p_endtime").show();
				$("#enddate").val(endDateSel);
				var endtHour = endTime.substring(11,13);
				var endtMin = endTime.substring(14,startTime.length);
				$("#sel_end").show();
				$("#e_hour").val(endtHour);
				$("#e_minute").val(endtMin);
			}
		}
	}
	
	$("#check_ok").click(function(){
		if(validateNull()){
			if(isUpdate){
				checkUpdate(eventId);
			}else{
				checkInsert();
			}
		}else{
			alert("请填写所有的参数设置");
		}
	});
	
	$("#del_event").click(function(){
		if(confirm("确定要删除该事件吗？")){
			$.ajax({
		   		type:"POST",
		   		url:"<%=request.getContextPath()%>/DailyEventAction_del.action",
		   		async:false,
		   		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		   		data:"eventId="+eventId,
		   		success : function(data) {
		   			if (data != null && data != "" && data != "undefined" && data=="success") {
						cal12.fullCalendar('removeEvents',function(e){
							return e.eventId ==eventId;
						});
			   			$.fancybox.close();
		   			}else{
						alert("删除数据失败，请检查数据库日志");
					}
		   		}
			});
		}
	});
	
	
});




function getValueFromPage(){
	//初始化一些属性值
	var title = $("#eventContent").val();//任务说明
	var start = null;//开始时间
	var end = start;
	var allDay = true;
	var impLevel = '1';
	
	var isRepeat = false;
	var repeat = 0;//重复天数
	var repeat_endDate = null;//重复事件结束日期
	
	title = $("#eventContent").val();//任务说明
	start = $("#startdate").val();//开始时间
	end = start;
	if($("#to_quartzTask").css("display")=="none"){//定时任务模式
		impLevel = '2';
		isRepeat = $("#isRepeat").get(0).checked;
		start = start+ " "+ $("#s_hour").val()+":"+$("#s_minute").val();
		allDay = false;
		end = start;
		if(isRepeat){
			repeat_endDate = $("#repeatEndDate").val();
			repeat = $("#gapDays").val();
		}else{
			repeat_endDate = null;
		}
	}else{//待办事项模式
		
		//待办事件改为提醒模式，其中所有事务均非全天事件，也不会有结束时间（开始时间=结束时间）
		start = start+ " "+ $("#s_hour").val()+":"+$("#s_minute").val();
		end = start;
		allDay = false;
		/*
		allDay = $("#isallday").get(0).checked;
		if(!allDay){
			start = start+ " "+ $("#s_hour").val()+":"+$("#s_minute").val();
			end = end + " "+$("#e_hour").val()+":"+$("#e_minute").val();
		}
		*/
	}
	
	var getEvent = {
			'title':title,
			'start':start,
			'end':end,
			'allDay':allDay,
			'impLevel':impLevel,
			'repeat':repeat,
			'isRepeat':isRepeat,
			'repeat_endDate':repeat_endDate,
			'color':'#fff',
			'backImg':checkImg(impLevel)
	};
	return getEvent;
}

function validateInputArgs(value){
	var reg = new RegExp("^[0-9]*$");
	if(reg.test(value)){
		return true;
	}else{
		return false;
	}
}

function getArgsObjs(){
	var argsObjs = "";
	var argInputs = document.getElementsByClassName("inputArg");
	for(var index = 0; index < argInputs.length; index++){
		if(!$(argInputs[index]).is(":hidden")){
			if(validateInputArgs(argInputs[index].value)){
				argsObjs = argsObjs + argInputs[index].getAttribute("data-type") + "-" + argInputs[index].value + "|";
			}else{
				return false;
			}
		}
			
	}
	var tempname = $("#seId").find("option:selected").attr("templateName");
	argsObjs = "templateName-"+tempname+"|"+argsObjs;
	return argsObjs;
}

function checkInsert(){
	var getEvent = getValueFromPage();
	var eventId = new Date().getTime()+'';
	if(getEvent.impLevel == '2'){
		getEvent.title = $("select[name='seId'] option:selected").text(); 
	}
	//获取参数集合
	var argsObjs = getArgsObjs();
	if(!argsObjs){
		alert("请输入正确的参数(数字格式)");
		return false;
	}
	var title = encodeURI(encodeURI(getEvent.title));
	var seId = $("select[name='seId'] option:selected").val();
	var sendData = "eventId="+eventId+"&title="+title+"&start="+getEvent.start+"&end="+getEvent.end+"&allDay="+getEvent.allDay+"&repeatEndDate="+getEvent.repeat_endDate + "&repeat="+getEvent.repeat+"&impLevel="+getEvent.impLevel+"&seId="+seId+"&argsObjs="+argsObjs;
	//alert(sendData);
	$.ajax({
   		type:"POST",
   		url:"<%=request.getContextPath()%>/DailyEventAction_insert.action",
   		async:false,
   		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
   		data:sendData,
		success : function(data) {
			if (data != null && data != "" && data != "undefined") {
				if(data.split("|")[0]=="success"){
					cal12.fullCalendar('renderEvent',
						{
							eventId:data.split("|")[1],
							title: getEvent.title,
							start: getEvent.start,
							end: getEvent.end,
							allDay: getEvent.allDay,
							repeat_endDate:getEvent.repeat_endDate,
							repeat:getEvent.repeat,
							impLevel:getEvent.impLevel,
							color:'#fff',
							seId:seId,
							taskId:data.split("|")[2],
							backImg:checkImg(getEvent.impLevel),
							argsObjs:argsObjs
						},
						true
					);
					$.fancybox.close();
				} else {
					alert("插入数据失败，请检查数据库日志");
				}
			} else {
				alert("系统操作失败!请联系管理员或检查日志");
			}
		}
	});
	
}

function checkUpdate(eventId){
	var getEvent = getValueFromPage();
	if(getEvent.impLevel == '2'){
		getEvent.title = $("select[name='seId'] option:selected").text(); 
	}
	var title = encodeURI(encodeURI(getEvent.title));
	var seId = $("select[name='seId'] option:selected").val();
	var argsObjs = getArgsObjs();
	if(!argsObjs){
		alert("请输入正确的参数(数字格式)");
	}
	var sendData = "eventId="+eventId+"&title="+title+"&start="+getEvent.start+"&end="+getEvent.end+"&allDay="+getEvent.allDay+"&repeatEndDate="+getEvent.repeat_endDate + "&repeat="+getEvent.repeat+"&impLevel="+getEvent.impLevel+"&seId="+seId+"&argsObjs="+argsObjs;
	
	$.ajax({
   		type:"POST",
   		url:"<%=request.getContextPath()%>/DailyEventAction_update.action",
   		async:false,
   		data:sendData,
		success : function(data) {
			if (data != null && data != "" && data != "undefined") {
				if(data.split("|")[0]=="success"){
					var calEventArray = cal12.fullCalendar('clientEvents');
					var calEvent;
					for(var ce in calEventArray){
						if(calEventArray[ce].eventId == eventId){
							calEvent = calEventArray[ce];
							calEvent.title = getEvent.title;
							calEvent.start = getEvent.start;
							calEvent.end = getEvent.end;
							calEvent.allDay = getEvent.allDay;
							calEvent.impLevel = getEvent.impLevel;
							calEvent.color = getEvent.color;
							calEvent.backImg = checkImg(getEvent.impLevel);
							calEvent.seId = seId;
							calEvent.taskId = data.split("|")[2];
							calEvent.argsObjs = argsObjs;
							cal12.fullCalendar( 'updateEvent', calEvent );
						}
					}
					$.fancybox.close();
					if(calEvent.repeat && calEvent.repeat>0) location.reload();
				} else {
					alert("修改数据失败，请检查数据库日志");
				}
			} else {
				alert("系统操作失败!请联系管理员或检查日志");
			}
		}
	});
}

function validateNull(){
	var flag = true;
	$("input[type=text],textarea").each(function(){
		if(!$(this).is(":hidden")){
			if($(this).val()==null || $(this).val()==""){
				flag = false;
				return false;
			}
		}
	});
	return flag;
}

function checkImg(value){
	if(value == '1'){
		return "greenPoint.png";
	}else if(value=='2'){
		return "yellowPoint.png";
	}else if(value=='3'){
		return "redPoint.png";
	}
	return "greenPoint.png";
		
}

</script>
</body>
</html>