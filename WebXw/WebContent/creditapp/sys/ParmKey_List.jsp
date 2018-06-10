<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.util.*" %> 
<%@page import="app.creditapp.sys.entity.ParmArea"%>
<%@page import="app.creditapp.sys.entity.ParmAreaTree"%> 
<%@ taglib uri="/WEB-INF/tld/dict.tld" prefix="dict"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<base target="_self">
<title>通过角色选择</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<script type='text/javascript'src='<%=basePath%>dwr/interface/TblOrgDepartmentsDwr.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/util.js'></script>
<script language="javascript" src="<%=basePath%>creditapp/pub/xtree/js/xtree.js" type="text/javascript"></script> 
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ParmKeyAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formsys0050" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
						<dhcc:button typeclass="btn_80"
								onclick="javascript:flushParmCache()" value="清除缓存"
								action="清除缓存"></dhcc:button>
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
							<div class="tabTitle">数据字典列表</div>
							<dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								onclick="ParmKeyAction_input.action"></dhcc:button>
						</div>
							
						<dhcc:tableTag paginate="ParmKeyList" property="tablesys0051"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
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
</html>