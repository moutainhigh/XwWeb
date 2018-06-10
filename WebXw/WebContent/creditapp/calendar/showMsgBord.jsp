<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	Object themeObj = session==null?null:session.getValue("color");
	String theme = (themeObj==null||"".equals(themeObj))?"yellow":((String)themeObj);
%>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK">

<title>工作日历查看</title>
<style>
html { overflow-x:hidden; }
body, ul, li, p{padding:0; margin:0; list-style:none;}
.can{width:370px;margin-top:55px;}
.cantit{ background:#db5757; height:35px; line-height:35px; color:#1e1a15; font-size:12px; padding-left:15px;position:relative;}
.canbody{ background:#fff2e0; padding:22px 15px;}

.canUI{ border-left:1px solid #e1d8cd;}
.canUI li:first-child{margin-top:0; }
.canUI li:last-child{ border:none;}
.canUI li{width: 100%; margin-left:12px; margin-top:15px; padding-bottom:6px; display:inline-block; border-bottom: 1px dashed #e1d8cd; }
.canUI li i{ display:block; width:6px; height:6px; border-radius:3px; background:#a7a7a7; margin-left:-15px;}
.canUI li p{ font-size:12px; color:#a2a2a2; line-height:18px;}
.canUI li p.Ptit{ margin-top:-12px;}

.subpost{ background:url(<%=request.getContextPath()%>/creditapp/calendar/images/cani1.gif) no-repeat left center; height:16px; line-height:24px; padding-left:20px; color:#4a4848; font-size:12px; overflow:hidden; margin:10px 0;}
.subpost span{ float:right;}

.canbtn{ float:right; overflow:hidden; margin-top:10px;}
.canbtn a{ float:left; width:20px; height:20px; display:block; margin:0 4px;}
.canbtn .canb1{background:url(<%=request.getContextPath()%>/creditapp/calendar/images/shape-1.png) no-repeat 0px 0px;}
.canbtn .canb2{background:url(<%=request.getContextPath()%>/creditapp/calendar/images/shape-1.png) no-repeat -33px 0px;}
.canbtn .canb3{background:url(<%=request.getContextPath()%>/creditapp/calendar/images/shape-1.png) no-repeat -66px 0px;;}
.can-bz{ background:url(<%=request.getContextPath()%>/creditapp/calendar/images/bz.png) no-repeat; width:33px; height:44px; display:block; position:absolute; right:10px;bottom:23px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/workMessage/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/js/pop.js"></script>
</head>
<body>

<div class="can">
	<div class="cantit"><s:property value="pubOpin.txDate" /><span class="can-bz"></span></div>
    <div class="canbody">
    	<ul class="canUI">
        	<s:property escapeHtml="false" value="query"/>
        </ul>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var currTheme = "<%=theme%>";
	if(currTheme == "pink"){
		$(".cantit").css("background","#f27ca0").css("color","#fff");
		$(".canbody").css("background","#fee9fb");
		$(".canUI li p").css("color","#000");
		
	}
});
	function pop_desc(opinNo){
		//window.location.href='PubOpinAction_getById.action?opinNo='+opinNo+'&query=query';
		showViewDialog('PubOpinAction_getById.action?opinNo='+opinNo+'&query=query');
	}
</script>
</body>
</html>