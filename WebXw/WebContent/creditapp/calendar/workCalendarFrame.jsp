<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,IE=IE9" >
<title>日历搜索</title>

<script type="text/javascript">
</script>

</head>
	<frameset cols="*,30%" border="0" frameBorder="no" frameSpacing="0">
		<frame name="persFrame_left" id="mainFrame_left" noresize="noresize"
			src="DailyEventAction_initDailyView.action" />
			<!--xubinbin隐藏日历中的舆情信息-->
		<!--frame name="persFrame" id="persFrame" 
			src="PubOpinAction_showMsgBord.action" /-->
	
	</frameset>
<noframes>
<body>
	<div align="left">
	</div>
</body>
</noframes>
</html>