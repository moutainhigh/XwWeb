<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String indexUi = (String)request.getSession().getAttribute("indexUi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.ztree.all-3.5.min.js"></script>
<%--滚动条js 和鼠标滚动事件js--%>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.mousewheel-3.0.6.min.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.mCustomScrollbar.js"></script>
	<!-- 自定义js 失去焦点校验 保存校验 -->
<script type="text/javascript" src='<%=path%>/include/uior_val.js'> </script>
<script type="text/javascript" src='<%=path%>/include/xcqi_cal.js'> </script>
<!-- 日期选择器S -->
<script src="<%=path%>/creditapp/workMessage/js/moment.min.js"></script>
<script src="<%=path%>/creditapp/workMessage/js/jquery.daterangepicker.js"></script>    
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.guide.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/B1.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.gDialog.js"></script>

<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/jquery.gDialog.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/daterangepicker.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/normalize.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/animate.min.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/guide.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/zTreeStyle.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/jquery.mCustomScrollbar.css"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/creditapp/workMessage/css/B1.css" />

<script type="text/javascript" src="<%=path%>/creditapp/workMessage/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/css/iconfont.css" />
<script>
		var showData ;
		eval("showData = "+'<%=(String)request.getSession().getAttribute("messageData")%>');
		$(document).ready(function(){
			var initData = showData.ajaxData;
			menu_zNodes = initData.pasBigType;
			var pasSubType = initData.pasSubType;
			$.fn.zTree.init($("#menu_tree"), menu_setting, menu_zNodes);
			taskB.options.SysDate=initData.SysDate;
			taskB.initTasks(".work-zone-tasks",initData.sysTaskInfoArray.result, pasSubType);
			//taskB.initPasAwareCount(initData.pasAwareCount);
			//createTimeLine(initData.sysDate,10);
			createTimeLine('20161002',10);
			
			var pinUi = '<%="A".equals(indexUi)?"B":"A"%>';
			$("#pinDiv").click(function(){
				$.ajax({
					type : "POST",
					url : "LoginAction_changeSysUserUi.action",
					dataType : "text",
					data:{changeUi:pinUi},
					success : function(resultData) {
						if(resultData=="success"){
							if(pinUi == "A"){
								pinUi = "B";
								$("#pinDiv").attr("src","<%=request.getContextPath()%>/creditapp/workMessage/imgs/ding0.png")
							}else{
								pinUi = "A";
								$("#pinDiv").attr("src","<%=request.getContextPath()%>/creditapp/workMessage/imgs/pin.png")
							}
							window.parent.messageNotify("系统设定","已更改首页设定");
						}
					},
					error : function(xmlhq, ts, err) {
					}
				});
			});
		});
		function openBigForm(url,title,callback){
			window.top.openBigForm(url,title,callback);
		}
		
	mcTimeline($('body'));
</script>
</head>

<body class="_mCS_B1">
	<div class="work-zone-tree">
		<div class="work-zone-head">
			<div class="work-zone-title">
			<!--  
				<input type="button" value="+1" onclick="addNewMessage(3)">
				<input type="button" value="-1" onclick="subMessage(1)">
			-->
				<span class="all-tasks">全部任务</span> 
				
			</div>

		</div>
		<ul id="menu_tree" class="ztree"></ul>
		<div class="work-zone-line"></div>
		<div>
			<ul class="ztree">
				<li tabindex="0" class="level0" id="menu_tree_3" hidefocus="true" treenode="">
					<span title="" class="button level0 switch noline_docu" id="menu_tree_3_switch" treenode_switch="">
					</span></li>
			</ul>
		</div>
	</div>
	<div class="work-zone-tasks" id="work-zone-tasks"></div>
	<div class="pinDiv">
	<%if("A".equals(indexUi)){ %>
		<a><img id="pinDiv" style="padding-top:10px;cursor: pointer;" width="15px";height="15px" alt="固定为首页" src="<%=request.getContextPath()%>/creditapp/workMessage/imgs/ding0.png"> </a>
	<%}else{ %>	
		<a><img id="pinDiv" style="padding-top:3px;cursor: pointer;" width="15px";height="15px" alt="固定为首页" src="<%=request.getContextPath()%>/creditapp/workMessage/imgs/pin.png"> </a>
	<%} %>
	</div>
	<div class="work-zone-timeLine">
		<div class="time_contents">
			<div class="time-line-bg">
				<div class="time-line-border">最近</div>
				<div class="time-line-head " id="last_info"></div>
				
				<div class="time-line-line"></div>
				<div class="time-line-body">
					<dl class="time-line-dl"></dl>
				</div>
				<div class="time-line-border time-line-border2">
					<div class="time-line-head time-line-head2"></div>
					<div class="time-line-title">更多</div>
				</div>
			</div> 
		</div>
	</div>
	<!--  
	<div class="sysTaskHelp" style="">
		<i class="i i-wenhao"></i>
	</div>
	-->
	
</body>

</html>
