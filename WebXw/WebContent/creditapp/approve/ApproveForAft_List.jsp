<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%@ page language="java" import="app.creditapp.cif.entity.CifBlack" session = "true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="ApproveAftAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formaftrel0003" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
							<input type="submit" value="查询"  class="btn_80"/>
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
							<div class="tabTitle">息费减免审批列表</div>
						</div>
							
						<dhcc:tableTag paginate="aftReliefList" property="tableaftrel002"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>