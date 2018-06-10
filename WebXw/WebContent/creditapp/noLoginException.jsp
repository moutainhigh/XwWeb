<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=GBK" %>
<%@ include file="/include/tld.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head><title>一般异常</title>
<script type="text/javascript" language="javascript">
   funcExit();
		function funcExit(){		
		 //parent.location.href='login.jsp';
		 alert("对不起，由于您未登录或长时间未进行操作，会话已经失效，请重新登录系统！")
		 window.top.location.href='<%=basePath %>cmsindex.jsp';
		}
</script>
</head>
<body>
    
    <br/>
    <br/>
    
	<!--<s:actionmessage/>-->
	  
    <!--<input type="button"  value=" 退 出 " onclick="javascript:funcExit()"/>-->
</body>
</html>
