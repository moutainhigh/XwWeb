<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>	
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
	<form  align="center">
	<tr>
		<td>
			<font size="4" style="font-family: 华文宋体;color:red">还款方案配置</font>
		</td>
	</tr>
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcReplanParmAction_findByPage.action'" href="javascript:void(0);">还款方案配置</a>
		</td>
	</tr>
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcReplanFormulaAction_findByPage.action'"
			 href="javascript:void(0);">还款计划公式配置</a>
		</td>
	</tr>
	<tr>
		<td>
			<font size="4" style="font-family: 华文宋体;color:red;">核算方式管理</font>
		</td>
	</tr>
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcFineFormulaAction_findByPage.action'"
			 href="javascript:void(0);">罚息公式配置</a>
		</td>
	</tr>
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcDamFormulaAction_findByPage.action'"
			 href="javascript:void(0);">提前还款违约金配置</a>
		</td>
	</tr>
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcDamFormulaAction_findByPage.action'"
			 href="javascript:void(0);">公共费率配置</a>
		</td>
	</tr>
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcFeeFormulaAction_findByPage.action'"
			 href="javascript:void(0);">费用公式配置</a>
		</td>
	</tr>			
	<tr>
		<td><a class="abatch" onclick="javascript:window.location='AcFeeParmAction_findByPage.action'"
			 href="javascript:void(0);">费用定义配置</a>
		</td>
	</tr>
	</form>
</body>
</html>