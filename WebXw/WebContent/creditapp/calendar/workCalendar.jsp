<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edga" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%
	String eventJson = (String) request.getAttribute("eventJson");
	String holidayJson = (String)request.getAttribute("holidayJson");
	if(holidayJson == null || holidayJson.isEmpty())holidayJson = null;
	Object themeObj = session==null?null:session.getValue("color");
	String theme = (themeObj==null||"".equals(themeObj))?"yellow":((String)themeObj);
%>
<title>工作日历查看</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/creditapp/calendar/css/fancybox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/creditapp/calendar/chinaDaily/fullcalendar.css">
<!-- 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/creditapp/calendar/chinaDaily/fullcalendar.print.css">
 -->
<link rel='stylesheet' href='<%=request.getContextPath()%>/creditapp/calendar/css/jquery-ui.min.css' />
<style type="text/css">
 *{  
     scrollbar-face-color:#F3F3F3; /*面子*/  
     scrollbar-arrow-color:#C0C0C0; /*箭头*/  
     scrollbar-3dlight-color:#C0C0C0; /*最外左*/  
     scrollbar-highlight-color:#FFFFFF; /*左二*/  
     scrollbar-shadow-color:#FFFFFF; /*右二*/  
     scrollbar-darkshadow-color:#C0C0C0; /*右一*/  
     scrollbar-track-color:#F3F3F3; /*滑道*/  
 }  
 /*滚动条整体*/  
 ::-webkit-scrollbar{  
     width:8px; /*滚动条宽度*/  
 }  
 /*滚动条按钮*/  
 ::-webkit-scrollbar-button {  

 }  
 ::-webkit-scrollbar-track{  
     background-color:#F3F3F3;  /*滑道全部*/  
     width: 3px;
 }  
 ::-webkit-scrollbar-track-piece{  
     background-color:#F3F3F3;  /*滑道*/  
     -webkit-border-radius:4px; /*滑道圆角宽度*/  
 }  
 ::-webkit-scrollbar-thumb{  
     background-color:#F3F3F3; /*滑动条表面*/  
     border:solid 1px #C0C0C0; /*滑动条边框*/  
     border-radius:4px;  /*滑动条圆角宽度*/  
 }  
 /*横竖滚动条交角*/  
 ::-webkit-scrollbar-corner {  
     background-color: #F3F3F3;  
 }  
 /*横竖滚动条交角图案*/  
 ::-webkit-resizer {  
     /*background-image: url(/public/img/resizer-inactive.png);*/  
    background-repeat: no-repeat;  
    background-position: bottom right;  
}  
/*鼠标滑过滑动条*/  
 ::-webkit-scrollbar-thumb:hover{  
     background-color:#F3F3E0;  
 }  

#calendar{width:98%; margin:5px auto 10px auto}
.fancy{width:480px; height:auto;font-size: 14px;min-width: 400px;min-height: 450px }
.fancy2{width:480px; height:auto;font-size: 14px;min-width: 400px; }

.fancy h3{height:30px; line-height:30px; border-bottom:1px solid #d3d3d3; font-size:14px}
.fancy form{padding:10px;margin: 0px;}
.fancy p{height:28px; line-height:28px; padding:4px; color:#999;margin:0px;vertical-align: middle;}
.input{height:20px; line-height:20px; padding:2px; border:1px solid #d3d3d3; width:100px}
.texarea{padding:2px; border:1px solid #d3d3d3;}
.btn{-webkit-border-radius: 3px;-moz-border-radius:3px;padding:5px 12px; cursor:pointer}
.btn_ok{background: #360;border: 1px solid #390;color:#fff}
.btn_change{background: #3786a0;border: 1px solid #3786a0;color:#fff;width: 200px}
.btn_cancel{background:#f0f0f0;border: 1px solid #d3d3d3; color:#666 }
.btn_del{background:#f90;border: 1px solid #f80; color:#fff }
.sub_btn{height:32px; line-height:32px; padding-top:6px; border-top:1px solid #f0f0f0; text-align:right; position:relative}
.sub_btn .del{position:absolute; left:2px}
</style>
<script type="text/javascript">
//var testUrl = '<%=request.getContextPath()%>/creditapp/calendar/eventInfoForPending.jsp';
//var showUrl = '<%=request.getContextPath()%>/creditapp/calendar/eventInfoForShow.jsp';
var showUrl = '<%=request.getContextPath()%>/DailyEventAction_showInfoPage.action';
var testUrl = '<%=request.getContextPath()%>/DailyEventAction_initPendingPage.action';
var eventJson;
eval("eventJson=<%=eventJson%>");
var holidayJson;
eval("holidayJson=<%=holidayJson%>");


</script>
<script src='<%=request.getContextPath()%>/creditapp/calendar/js/jquery-1.9.1.min.js'></script>
<script src='<%=request.getContextPath()%>/creditapp/calendar/js/jquery-ui-1.10.2.custom.min.js'></script>
<script src="<%=request.getContextPath()%>/themes/js/jquery.ui.datepicker.js"  type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/themes/js/jquery.ui.datepicker-zh-CHS.js"type="text/javascript"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/creditapp/js/My97DatePicker/WdatePicker.js'></script>
<script src='<%=request.getContextPath()%>/creditapp/calendar/js/fullcalendar.js?ver=3333'></script>
<script src='<%=request.getContextPath()%>/creditapp/calendar/js/jquery.fancybox-1.3.1.pack.js'></script>
<script type="text/javascript">
var cal12 = null;
var maxHeight = $(window).height();
$(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		cal12 = $('#calendar').fullCalendar({
			theme: false,
			header: {
				left: 'today',
				center: '',
				right:'title'
				//right: 'month,agendaWeek,agendaDay'
			},
			height:maxHeight,
			firstDay:0,
			weekMode:'variable',
			editable: true,
			timeFormat: 'H:mm',
			axisFormat: 'H:mm',
			//weekNumbers:true,
			selectable: true,
			selectHelper: true,
			select: function(startTime, endTime, thisAllDay) {
				var timeFunName = null;
				var selDate =$.fullCalendar.formatDate(startTime,'yyyy-MM-dd');//格式化日期 
				var tselDate =$.fullCalendar.formatDate(startTime,'yyyyMMdd');//格式化日期 
				var start = $.fullCalendar.formatDate(startTime,'yyyy-MM-dd HH:mm');
				var end = $.fullCalendar.formatDate(endTime,'yyyy-MM-dd HH:mm');
				var allDay = thisAllDay;
				clearTimeout(timeFunName);
				/*timeFunName = setTimeout(function () {   
					window.open('PubOpinAction_showMsgBord.action?pubOpin.txDate='+tselDate+'&rdm='+Math.random(),'persFrame');
				}, 300); */
				$('#main').dblclick(function(){
					clearTimeout(timeFunName);
					$.fancybox({//调用fancybox弹出层 
		                'type':'ajax', 
		                'href':testUrl+"?eventId="+"&selDate="+selDate+"&startTime="+start+"&endTime="+end+"&allDay="+allDay
		            }); 
				});
			},
			
			eventClick: function(calEvent, jsEvent, view) {
				var eventId = calEvent.eventId;
				if(calEvent.impLevel == "3"){
					$.fancybox({//调用fancybox弹出层 
		                'type':'ajax', 
		                'href':showUrl+"?eventId="+eventId+"&rdm="+Math.random()
		            }); 
				}else{
					$.fancybox({//调用fancybox弹出层 
		                'type':'ajax', 
		                'href':testUrl+"?eventId="+eventId
		            }); 
				}
			},
			
			eventDrop:function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
				//if(event.repeat && event.repeat>0){
				if(event.impLevel!='1'){
					alert("只有事件提醒类型支持拖拽修改");
					revertFunc();
				}else{
					var start = $.fullCalendar.formatDate(event.start,'yyyy-MM-dd HH:mm');
					var end = $.fullCalendar.formatDate(event.end,'yyyy-MM-dd HH:mm');
					$.ajax({
				   		type:"POST",
				   		url:"<%=request.getContextPath()%>/DailyEventAction_update.action",
				   		async:false,
				   		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				   		data:"eventId="+event.eventId+"&start="+start+"&end="+end+"&allDay="+event.allDay,
						success : function(data) {
							if (data != null && data != "" && data != "undefined") {
								if(data.split("|")[0]=="success"){
								} else {
									revertFunc();
									alert("插入数据失败，请检查数据库日志");
								}
							} else {
								revertFunc();
								alert("系统操作失败!请联系管理员或检查日志");
							}
						}
					});
				}
			},
			
			eventResize:function( event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ) { 
				//if(event.repeat && event.repeat>0){
				if(dayDelta >= 1 || dayDelta<=-1){
					revertFunc(); //当前版本不允许拖拽延长时间
					return false;
				}else if(event.impLevel!='1'){
					alert("只有事件提醒类型支持拖拽修改");
					revertFunc();
				}else{
					var start = $.fullCalendar.formatDate(event.start,'yyyy-MM-dd HH:mm');
					var end = $.fullCalendar.formatDate(event.end,'yyyy-MM-dd HH:mm');
					$.ajax({
				   		type:"POST",
				   		url:"<%=request.getContextPath()%>/DailyEventAction_update.action",
				   		async:false,
				   		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				   		data:"eventId="+event.eventId+"&start="+start+"&end="+end+"&allDay="+event.allDay,
						success : function(data) {
							if (data != null && data != "" && data != "undefined") {
								if(data.split("|")[0]=="success"){
								} else {
									revertFunc();
									alert("插入数据失败，请检查数据库日志");
								}
							} else {
								revertFunc();
								alert("系统操作失败!请联系管理员或检查日志");
							}
						}
					});
				}
			},
			
			events: eventJson
		});
	});
/** 绑定事件到日期下拉框 **/
$(function(){
	$("#fc-dateSelect").delegate("select","change",function(){
		var fcsYear = $("#fcs_date_year").val();
		var fcsMonth = $("#fcs_date_month").val();
		$("#calendar").fullCalendar('gotoDate', fcsYear, fcsMonth);
		changeColorByTheme();
	});
	changeColorByTheme();
});

function changeColorByTheme(){
	var currTheme = "<%=theme%>";
	if(currTheme == "pink"){
		$(".fc-past").css("background","#fdf5fe");
		
	}
	$(".fc-other-month").css("background","#f3fcff");
}

</script>
</head>
<body class="body_bg">
<div id="main" style="width:100%;height: 100%" >
   <div id='calendar'></div>
   <div style="float: right;padding-right: 40px;font-size: 12px">
   		<img alt="" src="<%=request.getContextPath()%>/creditapp/calendar/chinaDaily/images/greenPoint.png"> 事件提醒
   		<img alt="" src="<%=request.getContextPath()%>/creditapp/calendar/chinaDaily/images/yellowPoint.png">	 定时业务	
   		<img alt="" src="<%=request.getContextPath()%>/creditapp/calendar/chinaDaily/images/redPoint.png">	 待办任务	
	</div>
</div>
</body>
</html>