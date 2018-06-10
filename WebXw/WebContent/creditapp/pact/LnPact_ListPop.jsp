<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
		<script type="text/javascript">
			window.name="x";
		</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form" target="x"
		action="LnPactAction_findByPageForPop.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formlnpact0003" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
							<dhcc:button value="查询" action="查询" commit="true"
								typeclass="btn_80"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<p class="p_blank">&nbsp;</p>
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
						<s:hidden name="assId"></s:hidden>
							<div class="tabTitle">信息列表</div>
								<input type="button" value="确定" class="t_ico_tj" onclick="doReplace();"/>
							</div>
						<dhcc:tableTag paginate="lnPactList" property="tablelnpact0004"
									head="true" />
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</s:form>
	</body>
<script type="text/javascript">
	function doReplace(){
		var pactIds=document.getElementsByName("pactId");
		var pactId = "";
		for(var i=0;i<pactIds.length;i++){
			if(pactIds[i].checked){
				pactId = pactId + "@" + pactIds[i].value.split("=")[1];
			}
		}
		var assId = document.getElementsByName("assId")[0].value;
		$.ajax({
	   		type:"POST",
	   		url:"<%=request.getContextPath()%>/LnPactAction_doReplaceAjax.action?assId="+assId+"&pactId="+pactId,
	   		success:function(data){
	   			if(data == "success"){
	   				sAlert("保存成功!");
					window.close();
	   			}
	   		}
	   	});
		
 	}
	
	$(document).ready(function(){
		$("#tablist tr").eq(0).children().each(function(){
			if($(this).html() == "选择"){
				$(this).html("").unbind("click").append('<input type="checkbox" onclick="checkAll()">');
			}
		})
	});
	
	function checkAll(){
		var hadCheckedAll = true;
		$("input[type='checkbox']:gt(0)").each(function(){
			if(!$(this).is(":checked")){
				$(this).attr("checked","checked");
				hadCheckedAll = false;
			}
		});
		
		if(hadCheckedAll){
			$("input[type='checkbox']").each(function(){
				$(this).attr("checked",false);
			});
		}
	}
 	
	</script>  
</html>