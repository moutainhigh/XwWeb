<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="GBK">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>小微金融信贷管理系统</title>
<script type="text/javascript" src="<%=path %>/loginFile/SVG/js/segment.min.js"></script>
<script type="text/javascript" src="<%=path %>/loginFile/SVG/js/d3-ease.v0.6.js"></script>
<script type="text/javascript" src="<%=path %>/loginFile/SVG/js/letters.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/loginFile/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="<%=path %>/loginFile/css/component.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/loginFile/css/styles.css">
<style type="text/css">
body,td,th {
	font-family: "Source Sans Pro", sans-serif;
}

body {
	background-color: #2B2B2B;
}

.Dfooter{ font-size:12px; font-family:"Microsoft YaHei"; color:#d4ddef;position: absolute;right: 30px;bottom: 15px;}
.Dfooter b{ color:#fff;}
</style>
</head>

<body>

	<div class="openTop">
		<div id="login_logo" style="text-align: center;margin-top:83px;">
			<img src="<%=path %>/loginFile/img/login-logo2.png">
		</div>
		<i></i>
	</div>
	<div class="openBottom">
		<i></i>
		<p class="Dfooter">技术支持 <b>· 河工大计科部</b>  </p>
	</div>
	<div id="starBack" style="display: none">
	<div class="container demo-1" >
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="largeBg large-header0" style="z-index:13"></div>
				<div class="largeBg large-header1" style="z-index:14"></div>
				<div class="largeBg large-header2" style="z-index:13"></div>				
			</div>
		</div>

	</div>
	<div></div>
	<div class="wrapper">
		<div class="sub_container">
			<h1>
				小微金融信贷管理系统
			</h1>
			<form class="form" action="<%=path %>/creditapp/cms.action" name="LoginForm" method="post">
				<input type="text" placeholder="登录名" name="op.user_no" id="IdInput" value="su1573"> 
				<input type="password" placeholder="密码" name="op.pass_word" id="PwdInput" value="000000">
				<button type="button" id="login-button" >登录</button>
			</form>
			<div class='spinner'>
				<div class='orbiter'>
					<div class='planet'></div>
					<div class='orbit-outer'>
						<div class='orbit-inner'>
							<div class='dot'></div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="text-sts"> 登陆成功<p>
				<!-- <input type="button" id="backToLogin" value="返回" /> -->
			</div>
			<div class="text-err"> 
				<div style="margin-bottom:  10px" id="errMessage"></div><p>
				登录失败<p>
				<button id="backToLogin">返回</button>
			</div>
			
		</div>

		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>

	</div>
	</div>
	
	<input id="loginFlag" type="hidden" value="0">
	<script type="text/javascript">
	//回车登陆
	document.onkeydown = function submitForm(evt) {
	  	//IE & Firefox兼容处理
	  	var evt = evt ? evt : (window.event ? window.event : null);
	  	if (evt.keyCode == 13) {
	  		//$('#login-button').click();
	  		if($("#loginFlag").val() == "0"){
	  			$('#login-button').click();
	  		}else if($("#loginFlag").val() == "1"){
	  			$('#backToLogin').click();
	  		}
	  	}
	};
	</script>
	<script type="text/javascript" src="<%=path %>/loginFile/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=path %>/loginFile/js/login.js"></script>
	<script src="<%=path %>/loginFile/js/TweenLite.min.js"></script>
	<script src="<%=path %>/loginFile/js/EasePack.min.js"></script>
	<script src="<%=path %>/loginFile/js/rAF.js"></script>
	<script src="<%=path %>/loginFile/js/demo-1.js"></script>
</body>
</html>
