<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
</head>
<script type="text/javascript">
	function show(){
		$("#ad").show();
	}
	function hide(){
		$("#ad").hide();
	}
</script>
		<body class="body_bg">
		<div class="Ci-box">
			<s:if test="cifPortrait.sex==1">
				<div class="Ci-personimgman">
					<s:if test="cifPortrait.cifBlacknum>0">
						<div class="hei">
							<img alt="" src="<%=contextpath%>/creditapp/cif/image/hmd.png">
						</div>
					</s:if>
				</div>
			</s:if>
			<s:elseif test="cifPortrait.sex==2">
				<div class="Ci-personimgwomen">
					<s:if test="cifPortrait.cifBlacknum>0">
						<div class="hei">
							<img alt="" src="<%=contextpath%>/creditapp/cif/image/hmd.png">
						</div>
					</s:if>
				</div>
			</s:elseif>
			<s:else>
				<div class="Ci-personimg">
					<s:if test="cifPortrait.cifBlacknum>0">
						<div class="hei">
							<img alt="" src="<%=contextpath%>/creditapp/cif/image/hmd.png">
						</div>
					</s:if>
				</div>
			</s:else>
			<div class="C1-star">
			<div class="C1-inblock">
			<s:if test="cifPortrait.grade<=20">
				<div class="stariy">
					<img alt="" src="<%=contextpath%>/creditapp/cif/image/star1.png">
				</div>
			</s:if>
			<s:elseif test="cifPortrait.grade>20&&cifPortrait.grade<=40">
				<% for(int i = 0; i<2; i++){%>
				<div class="stariy">
					<img alt="" src="<%=contextpath%>/creditapp/cif/image/star1.png">
				</div>
				<%}%>
			</s:elseif>
			<s:elseif test="cifPortrait.grade>40&&cifPortrait.grade<=60">
				<% for(int i = 0; i<3; i++){%>
				<div class="stariy">
					<img alt="" src="<%=contextpath%>/creditapp/cif/image/star1.png">
				</div>
				<%}%>
			</s:elseif>
			<s:elseif test="cifPortrait.grade>60&&cifPortrait.grade<=80">
				<% for(int i = 0; i<4; i++){%>
				<div class="stariy">
					<img alt="" src="<%=contextpath%>/creditapp/cif/image/star1.png">
				</div>
				<%}%>
			</s:elseif>
			<s:else>
				<% for(int i = 0; i<5; i++){%>
				<div class="stariy">
					<img alt="" src="<%=contextpath%>/creditapp/cif/image/star1.png">
				</div>
				<%}%>
			</s:else>
			</div>
			</div>
			<div class="Citfont" style=" margin:5px auto 10px; text-align:center">
					<s:property value="cifPortrait.prtDesc"></s:property>
			</div>
			<div>
				<table cellspacing="0" width="100%" class="cifpot-name">
					<tr>
						<td class="Citit" colspan="2">基本信息</td>
					</tr>
					<tr>
						<td  align="left" class="Citfont">
						<s:property value="cifPortrait.cifName"></s:property>
							<s:if test="cifPortrait.sex==1">
							/男
						</s:if>
						<s:if test="cifPortrait.sex==2">
							/女
						</s:if>
						/<s:property value="cifPortrait.birthDay"></s:property>岁/
						<s:if test="cifPortrait.edu==20">
								大学本科
							</s:if>
							<s:if test="cifPortrait.edu==30">
								大学专科和专科学校
							</s:if>
							<s:if test="cifPortrait.edu==40">
								中等专业学序中等技术学校
							</s:if>
							<s:if test="cifPortrait.edu==50">
								技术学校
							</s:if>
							<s:if test="cifPortrait.edu==60">
								高中
							</s:if>
							<s:if test="cifPortrait.edu==70">
								初中
							</s:if>
							<s:if test="cifPortrait.edu==80">
								小学
							</s:if>
							<s:if test="cifPortrait.edu==90">
								文盲或半文盲
							</s:if>
							<s:if test="cifPortrait.edu==99">
								学历未知
							</s:if>
							<s:if test="cifPortrait.marriage==10">
								/未婚
							</s:if>
							<s:if test="cifPortrait.marriage==20">
								/已婚
							</s:if>
							<s:if test="cifPortrait.marriage==21">
								/初婚
							</s:if>
							<s:if test="cifPortrait.marriage==22">
								/再婚
							</s:if>
							<s:if test="cifPortrait.marriage==23">
								/复婚
							</s:if>
							<s:if test="cifPortrait.marriage==30">
								/丧偶
							</s:if>
							<s:if test="cifPortrait.marriage==40">
								/离婚
							</s:if>
							<s:if test="cifPortrait.marriage==90">
								/未说明的婚姻状况
							</s:if>
							<s:if test="cifPortrait.cifType==01">
								/农户
							</s:if>
							<s:if test="cifPortrait.cifType==02">
								/工薪
							</s:if>
							<s:if test="cifPortrait.cifType==03">
								/个体工商户
							</s:if>
							<s:if test="cifPortrait.cifType==04">
								/学生
							</s:if>
						</td>
					</tr>
					<tr class="Citfont" align="left">
					<td colspan="2">
						TEL: &nbsp;<s:property value="cifPortrait.resTel"></s:property>
					</td>
					</tr>
					<tr align="left" class="Citfont" >
					<td colspan="2">
						Addr: &nbsp;<s:property value="cifPortrait.resAddr"></s:property>
					</td>
						
					</tr>
					</table>
					
					<div class="Ci-tipbox">
						<div class="Ci-tip Ci-orange">
							中产阶级
						</div>
						<div class="Ci-tip Ci-blue"  id="add" onmouseover="show()" onmouseout="hide()">
							<s:if test="cifPortrait.repdNum<=0">
										无逾期
									</s:if>
									<s:if test="cifPortrait.repdNum>=1&&cifPortrait.repdNum<=3">
										存在信用风险
									</s:if>
									<s:if test="cifPortrait.repdNum>3">
										信用较差
									</s:if>
						</div>
						<div class="Ci-tip Ci-green" style="display: none; position: absolute;z-index: 2;"  id="ad"> 
							<s:if test="cifPortrait.repdNum<=0">0次逾期，信用良好</s:if>
										<s:if test="cifPortrait.repdNum>=1&&cifPortrait.repdNum<=3"><s:property value="cifPortrait.repdNum"></s:property>次逾期，请注意存在信用风险</s:if>
										<s:if test="cifPortrait.repdNum>3"><s:property value="cifPortrait.repdNum"></s:property>次逾期，信用较差</s:if>
						</div>
						
						<div class="Ci-tip Ci-yellow">
							<s:if test="cifPortrait.workType==0">
								国家机关、党群组织、企业、事业单位负责人
							</s:if>
							<s:if test="cifPortrait.workType==1">
								专业技术人员
							</s:if>
							<s:if test="cifPortrait.workType==3">
								办事人员和有关人员
							</s:if>
							<s:if test="cifPortrait.workType==4">
								商业、服务业人员
							</s:if>
							<s:if test="cifPortrait.workType==5">
								农、林、牧、渔、水利业生产人员
							</s:if>
							<s:if test="cifPortrait.workType==6">
								生产、运输设备操作人员及有关人员
							</s:if>
							<s:if test='cifPortrait.workType=="X"'>
								军人
							</s:if>
							<s:if test='cifPortrait.workType=="Y"'>
								不便分类的其他从业人员
							</s:if>
							<s:if test='cifPortrait.workType=="Z"'>
								未知
							</s:if>
						</div>
						<div class="Ci-tip Ci-zi">
							<s:if test="cifPortrait.ifCar==0">
								无车
							</s:if>
							<s:else>
								有车一族
							</s:else>
						</div>
						<div class="Ci-tip Ci-green">
							<s:if test="cifPortrait.ifRoom==0">
								无房
							</s:if>
							<s:else>
								有房一族
							</s:else>
						</div>
						<s:iterator value="cifPortraitList" id="var" >
							<s:if test="#var.cifGroup==01">
								<div class="Ci-tip Ci-blue">
									融资客户
								</div>
							</s:if>
							<s:if test="#var.cifGroup==02">
								<div class="Ci-tip Ci-blue">
									消费客户
								</div>
							</s:if>
						</s:iterator>
						<div class="Ci-tip Ci-orange">
							<s:if test="cifPortrait.ifDc==0">
								代偿户
							</s:if>
							<s:else>
								非代偿户
							</s:else>
						</div>
						<div class="Ci-tip Ci-green">
							<s:if test="cifPortrait.ifHg==0">
								回购户
							</s:if>
							<s:else>
								非回购户
							</s:else>
						</div>
						<div class="Ci-tip Ci-zi">
							有超过该类<s:property value="cifPortrait.gradePersent"></s:property>%的客户
						</div>
			</div>
			
		</div>
	</body>
</html>