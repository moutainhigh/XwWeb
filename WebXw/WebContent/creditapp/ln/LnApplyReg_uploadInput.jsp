<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<script type="text/javascript">
	
	</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="LnApplyRegAction_upload.action" enctype="multipart/form-data">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table id="formlnapplyreg0002" width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w" title="formlnapplyreg0002">
<tr>
<td align="right" class="tdlable" >导入文件&nbsp</td>
<td align="left" colspan="3" class="tdvalue" >
	<input type="file" title="导入文件" name="upload" class="BOTTOM_LINE">
</td>
</tr>
</table>
						<div class="from_btn">
						<!-- 
						<input type="submit" class="button3" value="保存"/>
						<input type="button" class="t_ico_tj"  
						onclick="window.location.href='LnBatchAction_findByPage.action'" value="返回" />
						 -->
							<dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="LnBatchAction_findByPage.action"></dhcc:button>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>