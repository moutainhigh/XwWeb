<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="app.creditapp.sys.entity.*,java.util.*" %>
<%
	Object themeObj = session==null?null:session.getValue("color");
	String theme = themeObj==null?"yellow":((String)themeObj);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<link href="../themes/css/main.css" type="text/css" rel="stylesheet" />
<link href="../themes/theme_<%=theme %>/Css/sysUI_<%=theme %>.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/creditapp/ztree/css/zTreeStyle.css" type="text/css" />
<script src="../themes/js/jquery-1.8.0.min.js" language="javascript" type="text/javascript" ></script>
<script src="../themes/js/jquery.easyui.min.js" language="javascript" type="text/javascript"></script>
<script src="../themes/js/jquery.ui.widget.js" language="javascript" type="text/javascript"></script>
<script src="../themes/js/jquery.ui.accordion.js" language="javascript" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/creditapp/ztree/js/jquery.ztree.core-3.5.min.js" language="javascript" type="text/javascript"></script>

<title>二级菜单列表</title>
</head>
<body class="body_bg1 body_bgleft" >
<div class="entry_accordion" id="entry_accordion">

<!--  一二级菜单联动示例-------------->
<%
Map<String,String> map;
String id = null;
String menuNo = null;
if(session==null || session.getValue("menuTreeMap")==null){
	map = null;
}else {
	map = (Map<String,String>)session.getValue("menuTreeMap");
	id = request.getParameter("id");
	menuNo = ((app.creditapp.sys.entity.SysMenu)((java.util.List)session.getAttribute("sysMenuLev1List")).get(0)).getMenuNo();
}
%>
<%=(map==null || map.values()==null)?("<script language='javascript'>alert('异常登录状态,请重新登录!');window.top.location.href='"+request.getContextPath()+"';</script>"):(id==null||id.equals(""))?((String)map.get(menuNo)):map.get(id) %>
  </div>
</body>
<script language="javascript" type="text/javascript">
		var clientBodyHeight = $(window).height();
		var rightFrameUrl = null;
		$("#entry_accordion").height(clientBodyHeight-20);
		//手风琴上下伸缩效果
		var colorLeft = "<%=themeObj%>";
		var first="<%=request.getParameter("first")%>";
		if(colorLeft == "yellow")colorLeft = "#fff";
		$("#accordion").accordion({  
        	collapsible:true, 
            active:0,
			autoHeight:false,
			fillSpace:true
        });
		if(first && first!=null && first!="null" && first!="undefiend"){
			window.parent.changeMainFrame(first);//加载完本页面之后再加载另一个frame；
		}else{
			/*
			$(document).ready(function(){
				$("#accordion ul li:first").click();
			});
			*/
			$(document).ready(function(){
				rightFrameUrl = $("#accordion ul li:first").attr("url");
				window.parent.changeMainFrame(rightFrameUrl);//加载完本页面之后再加载另一个frame；
			});
		}
		
		function goMenuUrl(obj){
			$(obj).parent().children().each(function(){
				$(this).attr("style","");
				$(this).removeClass("entry_onli");
				//alert($(this).text());
			});
			$(obj).addClass("entry_onli");
			$(obj).css("color",colorLeft).css("font-weight","bolder");
			screenLockFromLeft();
			parent.document.getElementById("rightFrame").src = $(obj).attr("url");
			rightFrameUrl = $(obj).attr("url");
			parent.document.getElementById("locFrame").contentWindow.document.getElementById("secondMenu").innerText = $(obj).parent("ul").parent("div").prev("a").text();
			parent.document.getElementById("locFrame").contentWindow.document.getElementById("thirdMenu").innerText = $(obj).text();
		};
		if(rightFrameUrl == null || rightFrameUrl == "undefined")window.parent.changeMainFrame(null);
		
		
		window.onresize=function(){  
			$("#entry_accordion").height($(window).height()-20);
			$("#accordion").accordion({  
	        	collapsible:true, 
	            active:0,
				autoHeight:false,
				fillSpace:true
	        });
		};  
		
		function screenLockFromLeft() {
			var rightFrame = parent.document.getElementById("rightFrame").contentWindow;
			if(typeof(rightFrame.screenLock) == "function"){
				rightFrame.screenLock();
			}
		}

</script>
</html>