<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>	
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=Edga" />
<%@ taglib uri="/WEB-INF/tld/pagetag.tld" prefix="pageC"%>
<%@taglib uri="/WEB-INF/tld/dict.tld" prefix="dict"%>
<%@ include file="/include/tld.jsp"%>
<html>
	<head>
		<base target="_self">
		<title>准入信息详情</title>
<script language="javascript" src="<%=request.getContextPath()%>/themes/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
</script>
	</head>
	<body class="body_bg">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="table_w">
						<div class="right_v">
				<s:form method="post" theme="simple" validate="true" name="cms_form">
					<table id="accessinfotable" width="100%" border="0" align="center" cellspacing=1 class="ls_list">
						<thead>
							<tr class="bartop" height="26">
								<th class="TDstyle02"  width="5%">
									序号
								</th>
								<th class="TDstyle02"  width="45%">
									检查项目
								</th>
								<th class="TDstyle02"  width="40%">
									检查结果
								</th>
								<th class="TDstyle02"  width="10%">
									标识
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="acc_check_info" value="accessCheckInfoList">
							<!-- 检查通过-->
							<s:if test='flag=="01"'>
								<tr style="display: none">
									<td align="left" class="TDstyle02" style="background-color: white">
										<s:property value="serial" />
									</td>
									<td align="left" class="TDstyle02" style="background-color: white">
										<s:property value="checkItem" />
									</td>
									<td align="left" class="TDstyle02" style="background-color: white">
										<s:property value="checkResult" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: white">
										<img alt="通过" src="<%=contextpath%>/creditapp/sys/images/success.gif" width="18" height="18" border="0" escape="false">
									</td>
								</tr>
							</s:if>
							<!-- 检查不通过-->
							<s:elseif test='flag=="00"'>
								<tr style="display: none">
									<td align="left" class="TDstyle02"  style="background-color: #C8FFC8">
										<s:property value="serial" />
									</td>
									<td align="left" class="TDstyle02" style="background-color: #C8FFC8">
										<s:property value="checkItem" />
									</td>
									<td align="left" class="TDstyle02" style="background-color: #C8FFC8">
										<s:property value="checkResult" />
									</td>
									<td align="center" class="TDstyle02" style="background-color: #C8FFC8">
										<img alt="通过" src="<%=contextpath%>/creditapp/sys/images/false.gif" width="18" height="18" border="0" escape="false">
									</td>
								</tr>
							</s:elseif>							
							</s:iterator>
						</tbody>
					</table>					
				</s:form> 
			</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var i = 0;
			$("#accessinfotable").find("tr").each(function(){
				$(this).show(1000*(i++))
			});
		</script>
	</body>
	
</html>