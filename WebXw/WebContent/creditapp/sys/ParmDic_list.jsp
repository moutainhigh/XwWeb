<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<html>
	<head>
		<title>数据字典下拉项管理列表</title>
		<script type="text/javascript">
			function flushParmCache(){
				$.ajax({
			   		type:"POST",
			   		url:"<%=request.getContextPath()%>/ParmDicAction_flushCache.action",
					success : function(data) {
						if (data != null && data != "" && data != "undefined"
								&& data == "success") {
							sAlert("清理数据字典缓存成功，现在可以使用修改后的字典进行操作");
						} else {
							sAlert("清理数据字典缓存失败，请查看错误日记");
						}
					}
				});
	}
</script>
	</head>
	<body class="body_bg">
		<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
					<s:form method="post" theme="simple" validate="false"
						name="cms_form" action="ParmDicAction_findByPage.action">
						<div class="tools_372">
						 <dhcc:button typeclass="btn_80"
								onclick="javascript:flushParmCache()" value="清除缓存"
								action="清除缓存"></dhcc:button>
							
						</div>		
						<p class="p_blank">&nbsp;</p>
		
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">数据字典列表</div>
							<dhcc:button typeclass="t_ico_tj" onclick="func_add()" value="新增"
								action="新增" ></dhcc:button>
								<dhcc:button typeclass="t_ico_tj" onclick="func_back()" value="返回"
								action="返回" ></dhcc:button>
						</div>
						<dhcc:tableTag paginate="parmdicList" property="tablesys4033"
							head="true" />
					</div>
				</div>
			</div>
		</div>
						
					</s:form>
				</form>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	function func_add(){
	window.location='ParmDicAction_input.action?key_name=<s:property value="key_name"/>';
	}
	
	</script>
		<script type="text/javascript">
	function func_back(){
	window.location='ParmKeyAction_findByPage.action';
	}
	</script>
</html>