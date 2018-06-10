<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
<%
	String fundNo = request.getParameter("fundNo");
	String projNo = request.getParameter("projNo");
	String fdType = request.getParameter("fdType");
%>	
	</head>
	<frameset cols="0,*" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_left" id="persFrame_left" noresize="noresize"
			scrolling="no" src="#" />

		<frameset rows="100,*" border="0" frameBorder="no" frameSpacing="0">
			<frame name="persFrame_top" id="mainFrame_top" noresize="noresize"
				scrolling="no"
				src="FundBaseAction_getDetailTop.action?fundNo=<%=fundNo%>&query=<%=request.getParameter("query")%>" />
	<!--	
	    <s:if test="query=='query1'">		
			<frame name="persFrame" id="persFrame"
			 src="FundBaseAction_getTab.action?fundNo=<%=fundNo%>&projNo=<%=projNo%>&fdType=<%=fdType%>&query=<%=request.getParameter("query") %>" />
 		</s:if>  
 	-->	  
 		<s:if test="query!='query1'">		
			<frame name="persFrame" id="persFrame"
			 src="FundBaseAction_getTab_Update.action?fundNo=<%=fundNo%>&projNo=<%=projNo%>&fdType=<%=fdType%>&query=<%=request.getParameter("query") %>" />
 		</s:if>    
 		    </frameset>
	</frameset>
	<body class="body_bg">
	<div class="right_bg">	
  <form action="#">
    <table width="100%" align="center" height="90%">
      <tr>
        <td>
          <dhcc:tabTag tabList="tabList"></dhcc:tabTag>
        </td>
      </tr>
    </table>
  </form>
  </div>
</html>