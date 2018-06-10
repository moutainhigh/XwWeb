<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	<script language="javascript" type="text/javascript">
		$(document).ready(function(){
			//设置年份的选择
			var myDate= new Date();
			var startYear=myDate.getFullYear()-50;//起始年份
			var endYear=myDate.getFullYear()+50;//结束年份
			var obj=document.getElementsByName('workYear')[0];
			for (var i=startYear;i<=endYear;i++)
			{
			obj.options.add(new Option(i,i));
			}
			obj.options[obj.options.length-51].selected=1;
			}
		);
	</script> 
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CifPersCareerActionInsert.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formcif0014" mode="query" />
						<div class="from_btn">
							<dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="CifPersCareerAction_listQuotaForCif.action" param="cifNo=cifNo@brNo=brNo"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>