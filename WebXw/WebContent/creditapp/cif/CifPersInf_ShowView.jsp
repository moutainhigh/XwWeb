<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<head>
<title></title>
<%
	String pactNo = request.getParameter("pactNo");
	String brNo = "000100030"+request.getParameter("brNo");
	String bussType = "";
	String subType = "";
%>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="image-panel" style="width:100%;height:650px;"> 
			<iframe id="myframe" scrolling="auto" frameborder="2" marginwidth="100%" marginheight="100%" 
					src='http://10.7.56.31:7001/xtwd/qualitycontrol/qualityControlInner.jsp?docCode=<%=pactNo%>&bussType=&manageCom=<%=brNo%>&systemNo=003&subType=' 
					style='width:100%;height:100%;'>
			</iframe>
	</div>
</body>
</html>