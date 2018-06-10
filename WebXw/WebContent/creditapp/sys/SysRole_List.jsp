<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
	</head>
	<script type="text/javascript">	
	function query(form){
		form.action="SysRoleAction_findByPage.action";
		form.submit();
	}
	</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="SysRoleAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formsys0074" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
						<!--  <input type="button" class="btn_80" onclick="query(this.form)" value="查询"/> -->
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
							<div class="tabTitle">角色列表 </div>
							<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="SysRoleAction_input.action"></dhcc:button>
						</div>
							
						<dhcc:tableTag paginate="sysRoleList" property="tablesys0075"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>