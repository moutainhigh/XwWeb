<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<script type='text/javascript' src='<%=contextpath%>/creditapp/sys/pop.js'></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
		<style type="text/css">
			table td{
				word-break:break-all;
				min-width: 100px;
			}
		</style>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="PrdtBaseActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formsys0102" mode="query" />
						<div class="from_btn">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
			<!--			 <input type="button" value="返回" onclick="javascript:window.location='PrdtBaseAction_findByPage.action?brNo=<s:property value="brNo"/>'" class="button_form"/>
						 	 <dhcc:button typeclass="button_form" value="关闭" action="关闭" onclick="window.close();"></dhcc:button>  -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
	<script type="text/javascript">
		//function funcAllowArreaPop() {
		
		   // var url = "PrdtBaseAction_findByPagePop.action";
		   // window.showModalDialog(
		   // url,window,"dialogWidth=40;dialogHeight=30;resizable=no;scrollbars=no;status:yes;help:no;");
		//}
	
	</script>
        <!-- 
		//function funcAllowArreaPop(){
			//展业地区
			//var roleno = document.getElementsByName(rolnoName)[0].value;
		  //  var url = "PrdtBaseAction_findByPagePop.action?parNames=" + parNames + "&popNames=" + popNames+ "&returnMethod=" + returnMethod;
		  //  url += "&RoleNo=" + roleno;
		    //window.showModalDialog(url,window,"dialogWidth=40;dialogHeight=30;resizable=no;scrollbars=no;status:yes;help:no;");


//}
-->
	
	
</html>