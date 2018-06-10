<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>formtoolbox.jsp</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/drag/dragCss.css" type="text/css" />
	</head>
	<body>
	    <div id="winlinks">
			<div id="div1" class="components" style="left:5px; top:20px;" onmouseover="imgMouseover('input')" onmouseout="imgMouseout('input')">
				<img id="input" src="drag/TextEdit.png"  width=16px height=16px />
				<label>文本字段</label>
			</div>
			<div id="div2" class="components" style="left:5px; top:60px;" onmouseover="imgMouseover('textarea')" onmouseout="imgMouseout('textarea')">
				<img id="textarea" src="drag/TextArea.png" width=16px height=16px  />
				<label>文本区域</label>
			</div>
			<div id="div3" class="components" style="left:5px; top:100px;" onmouseover="imgMouseover('select')" onmouseout="imgMouseout('select')">
				<img id="select" src="drag/listbox.png" width=16px height=16px  />
			    <label>选择域</label>
			</div>
			<div id="div4" class="components" style="left:5px; top:140px;" onmouseover="imgMouseover('input_checkbox')" onmouseout="imgMouseout('input_checkbox')" >
				<img id="input_checkbox" src="drag/stock_form-checkbox.png" width=16px height=16px />
			    <label>复选域</label>
			</div>
			<div id="div5" class="components" style="left:5px; top:180px;" onmouseover="imgMouseover('input_radio')" onmouseout="imgMouseout('input_radio')" >
				<img id="input_radio" src="drag/RadioButton.png"  width=16px height=16px />
			    <label>单选域</label>
			</div>
			<div id="div6" class="components" style="left:5px; top:220px;" onmouseover="imgMouseover('input_pw')" onmouseout="imgMouseout('input_pw')" >
				<img id="input_pw" src="drag/password.png"  width=16px height=16px />
			    <label>密码域</label>
			</div>
			<div id="div99" class="components" style="left:5px; top:260px;" onmouseover="imgMouseover('input_hidden')" onmouseout="imgMouseout('input_hidden')" >
				<img id="input_hidden" src="drag/field_hidden.png"  width=16px height=16px />
			    <label>隐藏域</label>
			</div>
			<div id="div00" class="components" style="left:5px; top:300px;" onmouseover="imgMouseover('input_other')" onmouseout="imgMouseout('input_other')" >
				<img id="input_other" src="drag/navigate-check.png"  width=16px height=16px />
			    <label>其它元素</label>
			</div>
		</div>
		<div id="div11" class="dragDiv11"></div>
	</body>
	<script type="text/javascript">
		var oDiv;
		var dragImg;
		var dragLabel;
		var dragTable;
		var selectDiv;
		var newDivX=0;
		var newDivY=0;
		var num=0;
		var newElementType;
		var currElement;
		var myAlert;
		var flag=false;
	    function imgMouseover(id)
	    {
	        //window.parent.frames["p2"].imgMouseover(obj);
	       var obj=$ge(id);
			oDiv=$ge("div11");
			if(dragTable!=null) 
			{
				//oDiv.removeChild(dragImg);
				//oDiv.removeChild(dragLabel);
				oDiv.removeChild(dragTable);
			}
			newElementType=obj.id;
			oRect=obj.parentNode.getBoundingClientRect();
			selectDiv=obj.parentNode;
			//selectDiv.style.display="none";
			dragTable=$ce("table");
			var tr=dragTable.insertRow(0);
			var td1=tr.insertCell(0);
			var td2=tr.insertCell(1);
			dragImg=$ce("img");
			dragImg.className="dragImg";
			dragImg.src=obj.src;
			dragImg.height=16+"px";
			dragImg.width=16+"px";
			var label=obj.parentNode.getElementsByTagName("label")[0];
			dragLabel=$ce("label");
			dragLabel.innerHTML=label.innerHTML;
			td1.appendChild(dragImg);
			td2.appendChild(dragLabel);
			//oDiv.appendChild(dragImg);
			//oDiv.appendChild(dragLabel);
			oDiv.appendChild(dragTable);
			oDiv.style.left=oRect.left;
			oDiv.style.top=oRect.top;
			oDiv.style.background="#848284";
	    }
	    function imgMouseout(obj)
	    {
	        //window.parent.frames["p2"].imgMouseout(obj);
	        selectDiv.style.display="block";
			if(!flag)
			{
			}
	    }
	    function $ce(element) 
			{
				return document.createElement(element);
			}
			function $ge(element) 
			{
				if(document.getElementById(element)!=null)
				{
				    return document.getElementById(element);
				}
				else
				{
				    return document.getElementsByName(element)[0];
				}
			}
	</script>
</html>
