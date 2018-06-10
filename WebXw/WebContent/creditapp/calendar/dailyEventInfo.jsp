<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link rel='stylesheet' href='<%=request.getContextPath()%>/creditapp/calendar/css/jquery-ui.css' />
<title>测试用</title>
<style type="text/css">
	input,textarea,select { display:inline-block; margin-right:3px; vertical-align:middle;}
</style>
<script src='<%=request.getContextPath()%>/creditapp/calendar/js/jquery-form.js'></script>
</head>
<body>
<div class="fancy">
	<h3 style="font-size: 16px;margin-bottom: 5px;margin-top: 5px;" id="htitle">新建事件</h3>
    <form id="add_form" action="insertNewEvent.action" method="post">
	    <input type="hidden" name="action" value="add">
	    <p style="padding: 4px;margin: 0px;margin-top:0px; font-size: 14px;vertical-align: top;height: auto;" >
	    	<span class="spanTitle">日程内容:</span><span style="margin-left: 8px"><textarea rows="5" cols="45" style="padding-left: 5px;" id="eventContent"></textarea></span>
	    </p>
	    <p style="padding: 4px;margin: 0px;margin-top:10px; font-size: 14px">
	    	<span class="spanTitle">开始时间：</span>
	    	<input type="text" class="input datepicker" readonly="readonly" name="startdate" id="startdate" value='<%=request.getParameter("selDate")%>' onclick="fPopUpCalendarDlg()">
		    <span id="sel_start" style="display:none">
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
		        <option value="40">40</option>
		        <option value="50">50</option>
		    </select>
		    </span>
	    </p>
	    <p id="p_endtime" style="display:none">
	    	结束时间：<input type="text" class="input datepicker" readonly="readonly" name="enddate" id="enddate" value="" onclick="fPopUpCalendarDlg()">
	    <span id="sel_end" style="display:none">
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
	    <p id="imp_p" >
	    	 事件类型：
	    	 <select id="impLevel" name="impLevel" style="width: 140px">
	    	 	<option value="1">待办事项</option>
	    	 	<option value="2">提醒</option>
	    	 	<option value="3">业务预警</option>
	    	 </select>
		</p>
	    <p id="repeat_p" style="display: none">
	    	 <label>截至时间：<input type="text" readonly="readonly" class="input datepicker" name="repeatEndDate" id="repeatEndDate" value='' onclick="fPopUpCalendarDlg()"></label>
		</p>
		<p id="gapdays_p" style="display: none">
	    	 <label>间隔天数：<input type="text" class="input datepicker" name="gapDays" id="gapDays" value=''></label>
		</p>
	    <p>
		    <label><input type="checkbox" value="1" id="isallday" name="isallday" checked> 全天</label>
		    <label><input type="checkbox" value="1" id="isend" name="isend"> 结束时间</label>
		    <label><input type="checkbox" value="1" id="isRepeat" name="isRepeat"><input type="checkbox" id="showRepeat" value="1" disabled="disabled" style="display: none"> 重复事件</label>
	    </p>
	    <div class="sub_btn">
	    	<span class="del" id="del_span">
	    		<input type="button" class="btn btn_del" id="del_event" value="删除">
	    	</span>
	    	<input type="button" id="check_ok" class="btn btn_ok" value="确定">
	    	<input type="button" class="btn btn_cancel" value="取消" onClick="$.fancybox.close()">
	    </div>
    </form>
</div>

<script type="text/javascript">
//如果是重复性事件就不允许它勾选，隐藏勾选选项并以不可修改的勾选框替代
//如果非重复性事件则不允许改为重复性事件
$(function(){
	//初始化界面
	var eventId = '<%=request.getParameter("eventId")%>';
	var startTime;
	var endTime;
	var allDay;
	var isUpdate = false;
	if(eventId && eventId!=""){//由事件跳转来，为事件修改触发
		$("#htitle").html("事件查看");
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
		$("#impLevel").val(calEvent.impLevel);
		
		if(calEvent.repeat && calEvent.repeat>0){
			$("#isRepeat").attr("checked","checked");
			$("#showRepeat").attr("checked","checked");
			$("#repeat_p,#gapdays_p").show();
			$("#repeatEndDate").val(calEvent.repeat_endDate);
			$("#gapDays").val(calEvent.repeat);
		}
		$("#isRepeat").hide();
		$("#showRepeat").show();
		
	}else{//新增跳转而来
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
			$("#sel_start").show();
			$("#s_hour").val(startHour)
			$("#s_minute").val(startMin)
		}
	}
	
	if(endTime){
		var endDateSel = endTime.substring(0,10);
		if(startTime != endTime){
			$("#isend").attr("checked","checked");
			$("#p_endtime").show();
			$("#enddate").val(endDateSel);
			if(allDay && allDay =="true"){
			}else{
				$("#isallday").attr("checked",false);
				var startHour = endTime.substring(11,13);
				var startMin = endTime.substring(14,startTime.length);
				$("#sel_end").show();
				$("#e_hour").val(startHour)
				$("#e_minute").val(startMin)
			}
		}
	}
	
	$("#isallday").click(function(){
		if($("#sel_start").css("display")=="none"){
			$("#sel_start,#sel_end").show();
		}else{
			$("#sel_start,#sel_end").hide();
		}
	});
	
	$("#isend").click(function(){
		if($("#p_endtime").css("display")=="none"){
			$("#p_endtime").show();
		}else{
			$("#p_endtime").hide();
		}
		$.fancybox.resize();//调整高度自适应
	});
	
	$("#isRepeat").click(function(){
		if($("#repeat_p").css("display")=="none"){
			$("#repeat_p,#gapdays_p").show();
		}else{
			$("#repeat_p,#gapdays_p").hide();
		}
		$.fancybox.resize();//调整高度自适应
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
	
	$("#check_ok").click(function(){
		if(isUpdate){
			checkUpdate(eventId);
		}else{
			checkInsert();
		}
	});
});

function checkInsert(){
	var getEvent = getValueFromPage();
	var eventId = new Date().getTime()+'';
	var title = encodeURI(encodeURI(getEvent.title));
	$.ajax({
   		type:"POST",
   		url:"<%=request.getContextPath()%>/DailyEventAction_insert.action",
   		async:false,
   		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
   		data:"eventId="+eventId+"&title="+title+"&start="+getEvent.start+"&end="+getEvent.end+"&allDay="+getEvent.allDay+"&repeatEndDate="+getEvent.repeat_endDate + "&repeat="+getEvent.repeat+"&impLevel="+getEvent.impLevel,
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
							color:checkColor(getEvent.impLevel)
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
	var title = encodeURI(encodeURI(getEvent.title));
	$.ajax({
   		type:"POST",
   		url:"<%=request.getContextPath()%>/DailyEventAction_update.action",
   		async:false,
   		data:"eventId="+eventId+"&title="+title+"&start="+getEvent.start+"&end="+getEvent.end+"&allDay="+getEvent.allDay+"&repeatEndDate="+getEvent.repeat_endDate + "&repeat="+getEvent.repeat+"&impLevel="+getEvent.impLevel,
		success : function(data) {
			if (data != null && data != "" && data != "undefined") {
				if(data=="success"){
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

function getValueFromPage(){
	var title = $("#eventContent").val();
	var start = $("#startdate").val();
	var end = $("#isend").get(0).checked?$("#enddate").val():start;
	var allDay = $("#isallday").get(0).checked;
	if(!allDay){
		start = start+ " "+ $("#s_hour").val()+":"+$("#s_minute").val();
		end = end + " "+$("#e_hour").val()+":"+$("#e_minute").val();
	}
	var impLevel = $("#impLevel").val();
	var repeat = 0;
	var repeat_endDate = 0;
	var isRepeat = $("#isRepeat").get(0).checked;
	if(isRepeat){
		repeat_endDate = $("#repeatEndDate").val();
		repeat = $("#gapDays").val();
	}else{
		repeat_endDate = null;
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
			'color':checkColor(impLevel)
	};
	return getEvent;
}

function checkColor(value){
	/*
	if(value == '1'){
		return "#fff4e8";
	}else if(value=='2'){
		return "#CD8C95";
	}else if(value=='3'){
		return "#FF6347";
	}*/
	return "#fff"
		
}

</script>
</body>
</html>