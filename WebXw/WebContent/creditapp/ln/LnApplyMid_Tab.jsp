<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<%String dataType =(String) request.getAttribute("dataType"); %>
<html>
<head>
<title>泸州市商业银行授信业务管理系统</title>
<style type="text/css">
	.drawbd{height:70px;width:100%;padding-top:5px;background:url(<%=contextpath%>/themes/images/line-bd.png) no-repeat 0px 0px;color:white;}
	.drawbd .ico_blue{float:left;width:24px;height:25px;background:url(<%=contextpath%>/themes/images/21.png) no-repeat -48px 0px;color:#ccc;position:relative;z-index:1000;margin:2px -2px;}
	.drawbd .ico_red{float:left;width:24px;height:25px;background:url(<%=contextpath%>/themes/images/21.png) no-repeat -24px 0px;color:red;position:relative;z-index:1000;margin:2px -2px;}
	.drawbd .ico_green{float:left;width:24px;height:25px;background:url(<%=contextpath%>/themes/images/21.png) no-repeat 0px 0px;color:green;position:relative;z-index:1000;margin:2px -2px;}
	.drawbd .ico_arrow_right1{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px 0px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right2{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -6px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right3{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -12px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right4{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -18px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right5{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -24px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right6{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -30px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right7{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -36px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right8{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -42px;color:white;margin:11px -1px;position:relative;z-index:1;}
	.drawbd .ico_arrow_right9{float:left;width:73px;height:5px;background:url(<%=contextpath%>/themes/images/arrow.png) no-repeat 0px -48px;color:white;margin:11px -1px;position:relative;z-index:1;}
	
	.font_desc{display:block;width:60px;margin-top:30px;margin-left:-20px}
	.clear{clear:both; height: 0; line-height: 0; font-size: 0}
	</style>
<script type="text/javascript">
var dataType="<%=dataType%>";
if(dataType!=null&&dataType!=""&&dataType!="null"){
	if(dataType=="success"){
		parent.opener.location="LnApplyMidAction_findByPage.action";
	}
	/*if(parent.opener.location.href.indexOf("LnApplyMidAction_findByPage")){
		parent.opener.location.reload();
	}else{
		parent.opener.location.href="<%=contextpath%>/LnApplyMidAction_findByPage.action";
	}*/
}
</script>
</head>
<body class="body_bg" style="overflow-y: hidden">
<div class="right_bg">
<div class="right_w">
<div class="from_bg">
<div class="right_v">
<div class="drawbd" style="width:1200px;margin:0 auto">
							<div style="margin-left:6px" class="<s:if test="lnStage.valSts==01">ico_blue</s:if><s:elseif test="lnStage.valSts==02">ico_green</s:elseif><s:else>ico_red</s:else>">
							<span class="font_desc">校验<br/>
							<s:if test="lnStage.valSts==01">未校验</s:if><s:elseif test="lnStage.valSts==02">通过</s:elseif><s:else>未通过</s:else>
							</span>
							</div>
							<div class="
		<s:if test="lnStage.valSts==01&&lnStage.dupSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.valSts==01&&lnStage.dupSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.valSts==01&&lnStage.dupSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.valSts==02&&lnStage.dupSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.valSts==02&&lnStage.dupSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.valSts==02&&lnStage.dupSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.valSts==03&&lnStage.dupSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.valSts==03&&lnStage.dupSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.dupSts==01">ico_blue</s:if><s:elseif test="lnStage.dupSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">重复<br/>
							<s:if test="lnStage.dupSts==01">未检查</s:if><s:elseif test="lnStage.dupSts==02">不重复</s:elseif><s:else>重复</s:else></span></div>
							<div class="
		<s:if test="lnStage.dupSts==01&&lnStage.chkSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.dupSts==01&&lnStage.chkSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.dupSts==01&&lnStage.chkSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.dupSts==02&&lnStage.chkSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.dupSts==02&&lnStage.chkSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.dupSts==02&&lnStage.chkSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.dupSts==03&&lnStage.chkSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.dupSts==03&&lnStage.chkSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.chkSts==01">ico_blue</s:if><s:elseif test="lnStage.chkSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">筛查<br/>
							<s:if test="lnStage.chkSts==01">未筛查</s:if><s:elseif test="lnStage.chkSts==02">通过</s:elseif><s:else>未通过</s:else></span></div>
							<div class="
		<s:if test="lnStage.chkSts==01&&lnStage.evalSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.chkSts==01&&lnStage.evalSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.chkSts==01&&lnStage.evalSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.chkSts==02&&lnStage.evalSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.chkSts==02&&lnStage.evalSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.chkSts==02&&lnStage.evalSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.chkSts==03&&lnStage.evalSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.chkSts==03&&lnStage.evalSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div style="margin-left:-3px;" class="<s:if test="lnStage.evalSts==01">ico_blue</s:if><s:elseif test="lnStage.evalSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">评级<br/>
							<s:if test="lnStage.evalSts==01">未评级</s:if><s:elseif test="lnStage.evalSts==02">已评级</s:elseif><s:else>未评级</s:else></span></div>
							<div class="
		<s:if test="lnStage.evalSts==01&&lnStage.splitSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.evalSts==01&&lnStage.splitSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.evalSts==01&&lnStage.splitSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.evalSts==02&&lnStage.splitSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.evalSts==02&&lnStage.splitSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.evalSts==02&&lnStage.splitSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.evalSts==03&&lnStage.splitSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.evalSts==03&&lnStage.splitSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.splitSts==01">ico_blue</s:if><s:elseif test="lnStage.splitSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">拆分状态<br/>
							<s:if test="lnStage.splitSts==01">未拆分</s:if><s:elseif test="lnStage.splitSts==02">拆分成功</s:elseif><s:else>拆分失败</s:else></span></div>
							<div class="
		<s:if test="lnStage.splitSts==01&&lnStage.zdappSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.splitSts==01&&lnStage.zdappSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.splitSts==01&&lnStage.zdappSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.splitSts==02&&lnStage.zdappSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.splitSts==02&&lnStage.zdappSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.splitSts==02&&lnStage.zdappSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.splitSts==03&&lnStage.zdappSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.splitSts==03&&lnStage.zdappSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.zdappSts==01">ico_blue</s:if><s:elseif test="lnStage.zdappSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">自动审批<br/>
							<s:if test="lnStage.zdappSts==01">待审批</s:if><s:elseif test="lnStage.zdappSts==02">审批通过</s:elseif><s:else>否决</s:else></span></div>
							<div class="
		<s:if test="lnStage.zdappSts==01&&lnStage.pactSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.zdappSts==01&&lnStage.pactSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.zdappSts==01&&lnStage.pactSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.zdappSts==02&&lnStage.pactSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.zdappSts==02&&lnStage.pactSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.zdappSts==02&&lnStage.pactSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.zdappSts==03&&lnStage.pactSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.zdappSts==03&&lnStage.pactSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.pactSts==01">ico_blue</s:if><s:elseif test="lnStage.pactSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">合同<br/>
							<s:if test="lnStage.pactSts==01">未生成</s:if><s:elseif test="lnStage.pactSts==02">已生成</s:elseif><s:else>未生成</s:else></span></div>
							<div class="
		<s:if test="lnStage.pactSts==01&&lnStage.rgappSts==00">ico_arrow_right3</s:if>
<s:elseif test="lnStage.pactSts==01&&lnStage.rgappSts==01">ico_arrow_right3</s:elseif>
<s:elseif test="lnStage.pactSts==01&&lnStage.rgappSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.pactSts==01&&lnStage.rgappSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.pactSts==02&&lnStage.rgappSts==00">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.pactSts==02&&lnStage.rgappSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.pactSts==02&&lnStage.rgappSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.pactSts==02&&lnStage.rgappSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.pactSts==03&&lnStage.rgappSts==00">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.pactSts==03&&lnStage.rgappSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.pactSts==03&&lnStage.rgappSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.rgappSts==00">ico_blue</s:if><s:elseif test="lnStage.rgappSts==01">ico_blue</s:elseif><s:elseif test="lnStage.rgappSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">人工审批<br/>
							<s:if test="lnStage.rgappSts==00">无需审批</s:if><s:elseif test="lnStage.rgappSts==01">待审批</s:elseif><s:elseif test="lnStage.rgappSts==02">审批通过</s:elseif><s:else>否决</s:else></span></div>
							<div class="
		<s:if test="lnStage.rgappSts==00&&lnStage.dueSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.rgappSts==00&&lnStage.dueSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.rgappSts==00&&lnStage.dueSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.rgappSts==01&&lnStage.dueSts==01">ico_arrow_right3</s:elseif>
<s:elseif test="lnStage.rgappSts==01&&lnStage.dueSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.rgappSts==01&&lnStage.dueSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.rgappSts==02&&lnStage.dueSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.rgappSts==02&&lnStage.dueSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.rgappSts==02&&lnStage.dueSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.rgappSts==03&&lnStage.dueSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.rgappSts==03&&lnStage.dueSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div style="margin-left:-4px;" class="<s:if test="lnStage.dueSts==01">ico_blue</s:if><s:elseif test="lnStage.dueSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">借据<br/>
							<s:if test="lnStage.dueSts==01">未执行</s:if><s:elseif test="lnStage.dueSts==02">通过</s:elseif><s:else>未通过</s:else></span></div>
							<div class="
		<s:if test="lnStage.dueSts==01&&lnStage.partSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.dueSts==01&&lnStage.partSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.dueSts==01&&lnStage.partSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.dueSts==02&&lnStage.partSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.dueSts==02&&lnStage.partSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.dueSts==02&&lnStage.partSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.dueSts==03&&lnStage.partSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.dueSts==03&&lnStage.partSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.partSts==01">ico_blue</s:if><s:elseif test="lnStage.partSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">分账<br/>
							<s:if test="lnStage.partSts==01">未分账</s:if><s:elseif test="lnStage.partSts==02">分账通过</s:elseif><s:else>分账失败</s:else></span></div>
							<div class="
		<s:if test="lnStage.partSts==01&&lnStage.mstSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.partSts==01&&lnStage.mstSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.partSts==01&&lnStage.mstSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.partSts==02&&lnStage.mstSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.partSts==02&&lnStage.mstSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.partSts==02&&lnStage.mstSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.partSts==03&&lnStage.mstSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.partSts==03&&lnStage.mstSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.mstSts==01">ico_blue</s:if><s:elseif test="lnStage.mstSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">主文件<br/>
							<s:if test="lnStage.mstSts==01">未生成</s:if><s:elseif test="lnStage.mstSts==02">已生成</s:elseif><s:else>未生成</s:else></span></div>
							<div class="
		<s:if test="lnStage.mstSts==01&&lnStage.sendSts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.mstSts==01&&lnStage.sendSts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.mstSts==01&&lnStage.sendSts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.mstSts==02&&lnStage.sendSts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.mstSts==02&&lnStage.sendSts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.mstSts==02&&lnStage.sendSts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.mstSts==03&&lnStage.sendSts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.mstSts==03&&lnStage.sendSts==02">ico_arrow_right8</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.sendSts==01">ico_blue</s:if><s:elseif test="lnStage.sendSts==02">ico_green</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">支付<br/>
							<s:if test="lnStage.sendSts==01">未发送</s:if><s:elseif test="lnStage.sendSts==02">已发送</s:elseif><s:else>未发送</s:else></span></div>
							<div class="
		<s:if test="lnStage.sendSts==01&&lnStage.paySts==01">ico_arrow_right3</s:if>
<s:elseif test="lnStage.sendSts==01&&lnStage.paySts==02">ico_arrow_right6</s:elseif>
<s:elseif test="lnStage.sendSts==01&&lnStage.paySts==03">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.sendSts==01&&lnStage.paySts==04">ico_arrow_right4</s:elseif>
<s:elseif test="lnStage.sendSts==02&&lnStage.paySts==01">ico_arrow_right7</s:elseif>
<s:elseif test="lnStage.sendSts==02&&lnStage.paySts==02">ico_arrow_right2</s:elseif>
<s:elseif test="lnStage.sendSts==02&&lnStage.paySts==03">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.sendSts==02&&lnStage.paySts==04">ico_arrow_right9</s:elseif>
<s:elseif test="lnStage.sendSts==03&&lnStage.paySts==01">ico_arrow_right5</s:elseif>
<s:elseif test="lnStage.sendSts==03&&lnStage.paySts==02">ico_arrow_right8</s:elseif>
<s:elseif test="lnStage.sendSts==03&&lnStage.paySts==03">ico_arrow_right1</s:elseif>
<s:else>ico_arrow_right1</s:else>
							"></div>
							<div class="<s:if test="lnStage.paySts==01">ico_blue</s:if><s:elseif test="lnStage.paySts==02">ico_green</s:elseif><s:elseif test="lnStage.paySts==04">ico_red</s:elseif><s:else>ico_red</s:else>"><span class="font_desc">放款<br/>
							<s:if test="lnStage.paySts==01">未放款</s:if><s:elseif test="lnStage.paySts==02">已放款</s:elseif><s:elseif test="lnStage.paySts==04">部分成功</s:elseif><s:else>放款失败</s:else></span></div>
							<div class="clear"></div>
						</div>
<form action="#">
<table width="100%" align="center" height="100%">
	<tr>
		<td>
			<div class="tab_btn_div">
				<s:if test="flag!='no'">
					<s:if test="view!='view'">
						
					</s:if>
					<s:else>
						<!-- 当back=1时不显示返回 因为在待审批工作台--评级审批时要调用该页面查看个人客户信息，这时不用返回按钮 -->
						<dhcc:button typeclass="back_btn" value="返回列表" action="返回列表"
							onclick="LnApplyMidAction_findByPage.action"></dhcc:button>
					</s:else>
				</s:if>
			</div>
		</td>
	</tr>	
	<tr>
		<td>
		<dhcc:tabTag tabList="tabList"></dhcc:tabTag>
		</td>
	</tr>
</table>		
</form>
</div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
if (window.dialogArguments) {
	var inputs = document.getElementsByTagName("input");
	var b = inputs[inputs.length - 1];
	b.onclick = function click() {
		window.close();
	};
}
</script>
</html>