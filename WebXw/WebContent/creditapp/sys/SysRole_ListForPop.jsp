<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%
String user_no=(String)request.getAttribute("user_no");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <script type='text/javascript'>
	window.name="curWindow";
</script>
<html>
	<head>
		<title>列表</title>
<script type="text/javascript">
	function insertRole() {
		var role_no = document.getElementsByName("role_no");
		var role_nos = "";
		for(var i=0;i<role_no.length;i++){
			if(role_no[i].checked){
				role_nos = role_nos+"@"+role_no[i].value.split("=")[1];
			}
		}
		if(role_nos==0){
		alert("请选择该用户对应的角色！");
		}else{
		var user_no='<%=user_no%>';
		window.location = "SysUserRoleActionInsert.action?user_no="+user_no+"&role_no="+role_nos;   
		}
} 
</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="SysRoleAction_findByPageForPop.action">
		<p class="p_blank">&nbsp;</p>
		
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div class="tabCont">
							<div class="tabTitle">角色列表</div>
							<dhcc:button value="选定" action="选定" typeclass="t_ico_tj"
								onclick="insertRole();"></dhcc:button>
						</div>
							
						<dhcc:tableTag paginate="sysRoleList" property="tablesys0076"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>