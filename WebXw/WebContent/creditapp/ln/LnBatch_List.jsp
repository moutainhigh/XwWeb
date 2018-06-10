<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>列表</title>
</head>
<script type="text/javascript">
	function jinjian() {
		window.location.href = 'LnApplyRegAction_uploadInput.action';
	}

	function query(form) {
		form.action = "SysRoleAction_findByPage.action";
		form.submit();
	}
</script>
<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="LnBatchAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td><dhcc:formTag property="formlnbatch0001" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
						<!--<input type="submit" class="btn_80" value="查询"/>-->
						
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
							<div class="tabTitle">正式进件申请</div>
							<!--<input type="button" class="t_ico_tj"
								onclick="window.location.href='LnApplyRegAction_download.action'"
								value="模板" /> <input type="button" class="t_ico_tj"
								onclick="window.location.href='LnApplyRegAction_uploadInput.action'"
								value="导入" />-->

							
							<dhcc:button value="模版" action="模版" typeclass="t_ico_tj"
								onclick="LnApplyRegAction_download.action"></dhcc:button>
							<dhcc:button value="导入" action="导入" typeclass="t_ico_tj"
								onclick="LnApplyRegAction_uploadInput.action"></dhcc:button>
							<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="LnBatchAction_input.action"></dhcc:button>
						</div>

						<dhcc:tableTag paginate="lnBatchList" property="tablelnbatch0001"
							head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>