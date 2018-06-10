<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9,IE=edge"> 
<title>正在进入欢迎界面...</title>
</head>
<body>
<script type="text/javascript">
	var oldPage = "<%=request.getContextPath()%>/entor.jsp";
	var newPage = "<%=request.getContextPath()%>/login.jsp";
	function getBrowserInfo() {
		var agent = navigator.userAgent.toLowerCase();

		var regStr_ie = /msie [\d.]+;/gi;
		var regStr_ff = /firefox\/[\d.]+/gi;
		var regStr_chrome = /chrome\/[\d.]+/gi;
		var regStr_saf = /safari\/[\d.]+/gi;
		//IE
		if (agent.indexOf("msie") > 0) {
			var version = agent.match(regStr_ie);
			//return agent.match(regStr_ie);
			var browser = agent.match(regStr_ie);
			var verinfo = (browser+"").replace(/[^0-9.]/ig,""); 
			if(verinfo == "6.0"){
				alert("您的浏览器版本是IE6，请升级高版本浏览器！");
				return false;
			}
			if((verinfo == "7.0")||(verinfo == "8.0") || (verinfo == "9.0")){
				window.location.href=oldPage;
				return
			}else{
				window.location.href=newPage;
				return
			}
		}
		if(window.ActiveXObject || "ActiveXObject" in window){
			//return "IE 11";
			window.location.href=newPage;
		}
			
		//firefox
		if (agent.indexOf("firefox") > 0) {
			//return agent.match(regStr_ff);
			if(confirm("本系统基于IE内核开发，您使用的是FireFox浏览器，可能会造成部分页面兼容错误，影响业务操作，确定要继续吗？")){
				window.location.href=newPage;
			}
		}

		//Chrome
		if (agent.indexOf("chrome") > 0) {
			if(confirm("本系统基于IE内核开发，您使用的是Chrome浏览器，可能会造成部分页面兼容错误，影响业务操作，确定要继续吗？")){
				window.location.href=newPage;
			}
		}

		//Safari
		if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
			if(confirm("本系统基于IE内核开发，您使用的是safari浏览器，可能会造成部分页面兼容错误，影响业务操作，确定要继续吗？")){
				window.location.href=newPage;
			}
		}

	}
	
	var browser = getBrowserInfo() ;
	//alert(browser); 
	//var verinfo = (browser+"").replace(/[^0-9.]/ig,""); 
	
</script>
	
</body>
</html>