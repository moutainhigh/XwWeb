<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="../../include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>数据字典修改</title>
		<script src="<%=request.getContextPath()%>/creditapp/js/sys_manage.js"
			type="text/javascript"></script>
	</head>
	<body class="body_bg">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<s:form name="operform" action="ParmDicAction_update.action"
							method="post" theme="simple">
							<dhcc:formTag property="formsys4032" mode="query" />
							<div class="from_btn">					
									<dhcc:button typeclass="btn_80" commit="true" value="保存"
										action="保存"></dhcc:button>	
								<dhcc:button typeclass="btn_80" onclick="func_back()" value="返回"
									action="返回"></dhcc:button>
							</div>
							<s:token />
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	function edit(){
	window.location='ParmDicAction_getById.action?key_name=<s:property value="key_name"/>&opt_code=<s:property value="opt_code"/>';
	}
	function func_back(){
	window.location='ParmDicAction_findByPage.action?key_name=<s:property value="key_name"/>';
	}
	</script>
</html>