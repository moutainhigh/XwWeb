<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="app.creditapp.sys.entity.SysOrg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String url = (String)request.getAttribute("CRP_FILEPATH")==""? "人行页面展示" :(String)request.getAttribute("CRP_FILEPATH");
%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base target="_self">   
    <title>信用报告详情页面</title>
    
  </head>
  <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  
  <script language="javascript" type="text/javascript"> 
    function openWindow(){//onload方法作废了，不知道谁把这种写法的滚动条屏蔽了
    // 测试使用：
    //window.open('<%=contextpath%>/creditapp/cif/creditReport/emptyReport.html','_self','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes');
    // 生产使用    ：window.open('<%=url%>',"_self","dialogWidth=100;dialogHeight=80;");
    }
</script> 
  <body>	
	<!-- <iframe id="myframe" scrolling="auto" frameborder="0" style="padding: 0px; width: 100%; height: 100%"
					src='<%=contextpath%>/creditapp/cif/creditReport/cs00001_34012219791010003X_20160727112928.html'></iframe> -->
		<% if("人行页面展示".equals(url)){%>
			<iframe id="myframe" scrolling="auto" frameborder="0" style="padding: 0px; width: 100%; height: 100%" src='<%=contextpath%>/creditapp/cif/creditReport/emptyReport.html'></iframe>
		<% }else{%>
			<iframe id="myframe1" scrolling="auto" frameborder="0" style="padding: 0px; width: 100%; height: 100%" src='<%=url%>'></iframe>
		<% }%>
  </body>
 </html>
