<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />

<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery-1.11.2.min.js"></script>
 <!-- 
<script type="text/javascript" src="<%=path%>/themes/js/jquery-1.9.0.min.js"></script></head>
-->

<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.ztree.all-3.5.min.js"></script>
<%--滚动条js 和鼠标滚动事件js--%>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.mousewheel-3.0.6.min.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.mCustomScrollbar.js"></script>
	<!-- 自定义js 失去焦点校验 保存校验 -->
<script type="text/javascript" src='<%=path%>/creditapp/workMessage/js/uior_val.js'> </script>
<script type="text/javascript" src='<%=path%>/creditapp/workMessage/js/xcqi_cal.js'> </script>
<!-- 
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/myAlert.js"></script>
 -->
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/openDiv.js"></script>
<!-- 日期选择器S -->
<script src="<%=path%>/creditapp/workMessage/js/moment.min.js"></script>
<script src="<%=path%>/creditapp/workMessage/js/jquery.daterangepicker.js"></script>    
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/jquery.guide.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/js/B1.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/data/dataJson.js"></script>
<script type="text/javascript" src="<%=path%>/creditapp/workMessage/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=path%>/creditapp/workMessage/bootstrap/css/bootstrap.min.css" />

<script type="text/javascript">
$(function(){
	$("#test").click(function(){
		$(this).hide(1000);
	});
	$("#showDialog").modal({
		backdrop:false,
       	show:true,
        keyboard: false
	});
})

</script>
<body>
	<div class="modal fade  bs-example-modal-lg showDialog" id="showDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="margin:auto;width: 90%;">
			<div class="modal-content" style="height:96%">
				<div class="modal-header" style=" padding: 14px 10px;">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top:4px">
						<span aria-hidden="true">&times;</span>
					</button>
					<i class="i i-fukuanjihua" style="font-size:20px;color:#333;margin-right:7px"></i>
					<h4 class="modal-title" id="myModalLabel" style="color:#333;display:inline-block">风险配置信息</h4>
				</div>
				<div class="modal-body" style="padding: 4px;height: calc(100% - 56px); ">
					<iframe id="showDialogiframe" src="" scrolling="no" style="height: calc(100%); width: 100%;"></iframe>
				</div>
			</div>
		</div>
	</div>
	<div style="border: 1px;background-color: red;width: 200px;height: 100px" id="test">1111111111111111111111111111</div>
</body>

</html>
