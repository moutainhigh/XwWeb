<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
		<script type="text/javascript">
		
		</script>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="FundDetailAction_findByPage.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formfddetail0001" mode="query" />
								</td>
							</tr>
						</table>
						<div class="tools_372">
							<dhcc:button value="查询" action="查询" commit="true"
								typeclass="btn_80"></dhcc:button>
							<dhcc:button typeclass="btn_80" value="新增" action="新增" 
							onclick="FundDetailAction_input.action" param="fundNo=fundNo@fdType=fdType">></dhcc:button>	
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
							<div class="tabTitle">资金明细</div>

							<!--<s:if test="query!='query1'">
							  <s:if test="fdType=='02'">
							    <dhcc:button value="新增" action="新增" typeclass="t_ico_tj"
								  onclick="FundDetailAction_input.action" param="fundNo=fundNo@fdType=fdType"></dhcc:button>
							    <dhcc:button value="下载模板" action="下载模板" typeclass="t_ico_tj"
								  onclick="FunDetailAction_download.action" param="fundNo=fundNo@fdType=fdType@projNo=projNo"></dhcc:button>
								<dhcc:button value="批量上传" action="批量上传" typeclass="t_ico_tj"
								  onclick="FundDetailAction_uploadInput.action" param="fundNo=fundNo@fdType=fdType@projNo=projNo"></dhcc:button>
                              </s:if>
                           </s:if>-->
						</div>
							
						<dhcc:tableTag paginate="fundDetailList" property="tablefddetail0001"
									head="true" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
</html>