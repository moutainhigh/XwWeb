<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>
<%@ include file="/include/tld.jsp"%>
<%@ taglib uri="/WEB-INF/tld/dict.tld" prefix="dict" %>
<%@ page language="java" import="app.creditapp.pact.entity.LnPact" session = "true"%>
<%@page import="app.creditapp.pact.entity.LnPact,java.util.Map,java.util.HashMap,java.util.List,java.util.ArrayList"%>
<%@ page import="com.dhcc.workflow.pvm.internal.task.TaskImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
List<LnPact> lnPactList = (List <LnPact>)request.getAttribute("lnPactList");

Map<String,Map<String,List<LnPact>>> tableMap = new HashMap<String,Map<String,List<LnPact>>>();
for(LnPact lnPact : lnPactList){
	if(tableMap.get(lnPact.getBatchNo())==null){
		tableMap.put(lnPact.getBatchNo(),new HashMap<String,List<LnPact>>());
		tableMap.get(lnPact.getBatchNo()).put(lnPact.getProjNo(),new ArrayList<LnPact>());
		tableMap.get(lnPact.getBatchNo()).get(lnPact.getProjNo()).add(lnPact);
	}else{
		if(tableMap.get(lnPact.getBatchNo()).get(lnPact.getProjNo())==null){
			tableMap.get(lnPact.getBatchNo()).put(lnPact.getProjNo(),new ArrayList<LnPact>());
		}
		tableMap.get(lnPact.getBatchNo()).get(lnPact.getProjNo()).add(lnPact);
	}
}
%>
<html>
	<head>
		<title>列表</title>
		<style type="text/css">
		a:hover{background:#fff;color:000;}
		</style>
<script type="text/javascript">  
	
    function fixRowspan() {  
        var tb = document.getElementById("appraiseDetailBean");
        var row_span_num = 1;  
        var first_row_title = "";  
        var first_row_obj;
        
        /*
        for ( var i = 1; i < tb.rows.length - 1; i++) {  
           	var first_new_row_title = tb.rows[i].cells[7].innerHTML;
           	if (first_row_title != "" && first_row_title == first_new_row_title) {  
               	tb.rows[i].deleteCell(7);  
               	row_span_num++;  
               	if (i == tb.rows.length - 2) {  
                   	first_row_obj.setAttribute("rowSpan", row_span_num);  
                   	//first_row_obj.innerHTML = getFontVertical(first_row_title);  
                   	//first_row_obj.innerHTML = first_row_title.replace("/", "<br/>");  
               	}  
           	} else {  
               	if (first_row_title != "") {  
                   	first_row_obj.setAttribute("rowSpan", row_span_num);  
                   	//first_row_obj.innerHTML = getFontVertical(first_row_title);  
                   	//first_row_obj.innerHTML = first_row_title.replace("/", "<br/>");  
                   	row_span_num = 1;  
                   
               	}  
               	first_row_obj = tb.rows[i].cells[7];  
               	first_row_title = first_new_row_title;  
               	
          	 }  
		}
*/
        
        for(var j=6; j>=0;j--){
        	if( 6 >= j && j > 2)continue;
        	for ( var i = 1; i < tb.rows.length ; i++) {  
            	var first_new_row_title = tb.rows[i].cells[j].innerHTML;
            	
            	if (first_row_title != "" && first_row_title == first_new_row_title) { 
                	tb.rows[i].deleteCell(j);  
                	row_span_num++;  
                	if (i == tb.rows.length - 1) {  
                    	first_row_obj.setAttribute("rowSpan", row_span_num);  
                    	//first_row_obj.innerHTML = getFontVertical(first_row_title);  
                    	//first_row_obj.innerHTML = first_row_title.replace("/", "<br/>");  
                	}  
            	} else {  
                	if (first_row_title != "") {  
                    	first_row_obj.setAttribute("rowSpan", row_span_num);  
                    	//first_row_obj.innerHTML = getFontVertical(first_row_title);  
                    	//first_row_obj.innerHTML = first_row_title.replace("/", "<br/>");  
                    	row_span_num = 1;  
                	}  
                	first_row_obj = tb.rows[i].cells[j];  
                	first_row_title = first_new_row_title;  
           	 	}  
			}
		}
   	}  
    
 $(function(){
 	fixRowspan();
 });
</script> 
	</head>
<body class="body_bg">  
	<s:form method="post" theme="simple" name="cms_form"
		action="LnPactAction_findByPageAppr.action">
				<div class="right_bg">
			<input type="hidden" name="assId" value="<%=request.getParameter("assId")%>">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<table width="100%" align="center" class="searchstyle">
							<tr>
								<td>
									<dhcc:formTag property="formlnpact0001" mode="query" />
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
          	<div id="content"  class="right_v">  
					<center>
					<div class="tabCont">
							<div class="tabTitle">贷款审批</div>
					</div>
               <table  width="100%"  class="ls_list"  id="appraiseDetailBean" border="3">  
              <!--
               <table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w">
              -->
                      <thead> 
                       <tr>  
                       <!--
                        	<th scope="col"  align="center">进件批次号</th>
                        	-->
                            <th scope="col"  align="center">合作机构</th>  
                            <th scope="col"  align="center">项目名称</th>  
                            <th scope="col"  align="center">贷款性质</th>  
                            <th scope="col"  align="center">笔	    数</th>  
                            <th scope="col"  align="center">汇总金额(元)</th> 
                            <th scope="col"  align="center">平均金额(元)</th> 
                            <th scope="col" colspan="4" align="center"> <font class="button_color">操作</th>  
                        </tr>  
                    </thead> 
                     <tbody id="tab"> 
                     
                     	<% 
                     			
                     			String bacthNo = "";
					    		for(LnPact lnPact : lnPactList)	{
					    		
					   %>
					    			<tr>
					    			<!--
					    			<td align="center"><%=lnPact.getBatchNo()%></td>
					    			-->	
					    			<td align="center"><%=lnPact.getBrName()%></td>	
					    			<td align="center"><%=lnPact.getProjName()%></td>	
					    			<td align="center">
					    			<%request.setAttribute("lnType",lnPact.getLnType());%>
					    			<dict:translate ddname="LN_TYPE" name="lnType" ></dict:translate> </td>	
					    			<td align="center">	
					    				<a style="color:#4790d4;" href="LnPactAction_findByPageForList.action?brNo=<%=lnPact.getBrNo()%>&projNo=<%=lnPact.getProjNo()%>&lnType=<%=lnPact.getLnType()%>&url=<%=lnPact.getUrl()%>"/><%=lnPact.getNumAppr()%>
					    			</td>
					    			<td align="right"><script language='javascript'>formatAmt(<%=lnPact.getSumPactAmt()%>);</script></td>
					    			<td align="right"><script language='javascript'>formatAmt(<%=lnPact.getAverPactAmt()%>);</script></td>	
					    			<td align="center" width="4%" >
										<!-- <a style="color:#4790d4;"  href="ApproveLoanAction_getTab.action?projNo=<%=lnPact.getProjNo()%>&brNo=<%=lnPact.getBrNo()%>&lnType=<%=lnPact.getLnType()%>&url=<%=lnPact.getUrl()%>"/>审批 -->	
										<a style="color:#4790d4;" href="LnPactAction_findByPageForList.action?brNo=<%=lnPact.getBrNo()%>&projNo=<%=lnPact.getProjNo()%>&lnType=<%=lnPact.getLnType()%>&url=<%=lnPact.getUrl()%>"/>详情
									</td>
									<!--
									<td width="6%" align="center">
										<a href="ProcessToSVGAction_viewProcess.action?processInstanceId=<%=lnPact.getProcessId()%>"/>查看流程</a>
									</td>
									-->
									</tr>
					    <% 		
					    	}
					    	 
					    	 %>
					    	 
					   	
                     </tbody>
                    </table>
                </center>
			</div>  
		 
	</s:form> 
</body>  
</html>