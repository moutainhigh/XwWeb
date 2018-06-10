<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
		<script>
		function func_br(url){
		var brNo = url.split("?")[1].split("&")[1].split("=")[1];
		var brSts = url.split("?")[1].split("&")[0].split("=")[1];
		if(brSts=="00"){
		   //停用状态 变为 启用
		   var r = confirm("你确定要 启用 此合作机构？");
		   if(r==true){
		     window.location='CorpBaseAction_valiBr.action?brNo='+brNo+'&brSts='+brSts;
		   }
		}else{
		   //启用状态 变为 停止状态
		  var d = confirm("你确定要 停用 该合作机构吗？");
		  if(d==true){
		     var d = confirm("你真的确定要 停用 该合作机构吗？");
		     if(d==true){
		      window.location='CorpBaseAction_valiBr.action?brNo='+brNo+'&brSts='+brSts;
		     }
		  }
		}
		
		
		
		
		}
		
		
		
		
		</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CorpBaseAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formcoop0001" mode="query" />
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
							<div class="tabTitle">合作机构管理</div>
							<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="CorpBaseAction_input.action"></dhcc:button>
						</div>
							
						<dhcc:tableTag paginate="corpBaseList" property="tablecoop0001"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>