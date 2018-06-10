<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="js/jquery-1.11.2.min.js" ></script>
		<script type="text/javascript" src="js/AB.js" ></script>
		<link rel="stylesheet" href="css/A2B.css" />
		<style>
			html,body {
				height:100%
			}
		</style>
	</head>
	<body>
		<div href="#" class="rotate turnA" style="background-color:#3eafed;height: 50px;width: 50px;position: absolute;"  onClick="turnAorB(null);"></div>
		<div class="middle-center pt-perspective">
				<div class="pt-page pt-page-ontop" name="A" style="background-color: red;">
					1111111111111111111111111
				</div>
				<div class="pt-page pt-page-current" name="B" style="background-color: yellow;">
				2222222222222222222222
				</div>
		</div>
	</body>
</html>