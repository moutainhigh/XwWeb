<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String cfd = (String) request.getAttribute("cfd");
%>
<html>
	<head>
		<title>详情</title>
		<!--<script language="javascript" type="text/javascript">
		window.onload=function(){
		//设置年份的选择
		var myDate= new Date();
		var startYear=myDate.getFullYear()-50;//起始年份
		var endYear=myDate.getFullYear()+50;//结束年份
		var obj=document.getElementsByName('workYear')[0];
		for (var i=startYear;i<=endYear;i++)
		{
		obj.options.add(new Option(i,i));
		}
		var workYearValue = document.getElementById("workYear").value;
		for(var i=0;i<obj.length;i++){//返显
			if(workYearValue==obj[i].value){
				obj[i].selected = 1;  
				break;
			}
		}
		}
	</script> 
	-->
		<script>
	$(function() {
	var myDate = new Date();
	var startYear = myDate.getFullYear() - 50;//起始年份
	var endYear = myDate.getFullYear() + 50;//结束年份
	var obj = document.getElementsByName('workYear')[0];
	for ( var i = startYear; i <= endYear; i++) {
		obj.options.add(new Option(i, i));
	}
	var workYearValue = document.getElementById("workYear").value;
	for ( var i = 0; i < obj.length; i++) {//返显
		if (workYearValue == obj[i].value) {
			obj[i].selected = 1;
			break;
		}
	}
});
</script>


	</head>
	<body class="body_bg">
		<s:form method="post" theme="simple" name="cms_form"
			action="CifPersCareerActionUpdate.action">
			<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
							<s:if test="query=='query'">
								<dhcc:formTag property="formcif0017" mode="query" />
							</s:if>
							<s:else>
								<dhcc:formTag property="formcif0014" mode="query" />
							</s:else>
							<div class="from_btn">
								<s:if test="query!='query'">
									<dhcc:button typeclass="button3" commit="true" value="保存"
										action="保存"></dhcc:button>
								</s:if>
								<s:if test="querysts=='querysts'">
									<input type="button" value="返回"
										onclick="javascript:window.location='CifPersCareerAction_listQuotaForCif.action?cifNo=<%=cfd%>&query=query'"
										class="button_form" />
								</s:if>
								<s:else>
									<s:if test="backSts!=1">
										<dhcc:button typeclass="button_form" value="返回" action="返回"
											onclick="CifPersCareerAction_listQuotaForCif.action"
											param="cifNo=cifNo@brNo=brNo"></dhcc:button>
									</s:if>
								</s:else>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="workYear"
				value="<s:property value="cifPersCareer.workYear"></s:property>">
		</s:form>

	</body>
</html>