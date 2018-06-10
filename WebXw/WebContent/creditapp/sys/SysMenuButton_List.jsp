<%@ page language="java" contentType="text/html; charset=GBK"	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<html>
	<head>
		<title>小微金融运管平台管理系统</title>
		<meta http-equiv="Pragma" content="no-cache"> 
		<meta http-equiv="Cache-Control" content="no-cache">   
		<meta http-equiv="Expires" content="0"> 
	</head>
<script type="text/javascript">
		<%
		String trees=(String)request.getAttribute("menuStr");
		String role_no=(String)request.getParameter("role_no");
		%>
		function selectAllBtn(){
			$("input:checkbox").each(function(){
				$(this).attr("checked",true);
			});
		}
		function unSelectBtn(){
			$("input:checkbox").each(function(){
				if($(this).attr("checked") || $(this).attr("checked")=="checked"){
					$(this).attr("checked",false);
				}else {
					$(this).attr("checked",true);
				}
			});
		}
</script>
	<body leftmargin="0" topmargin="0">
	<div class="right_bg">
		<div class="right_w">
		<div id="man_zone">
			<s:actionerror />
			<s:actionmessage />
			<s:fielderror />
			<s:form method="post" theme="simple" validate="true" name="cms_form" action="SysRoleAction_saveButton" enctype="multipart/form-data">
			<div class="from_btn" style="text-align: left;">
				<span><input type="button" name="btn4" value="展开" onclick="javascript:expandAll();" class="btn_80" /></span>
				<span><input type="button" name="btn4" value="折叠" onclick="javascript:collapseAll();" class="btn_80" /></span>
				<span><input type="button" name="btn4" value="保存" onclick="func_save();" class="btn_80" /></span>
				<span><input type="button" name="btn4" value="返回" onclick="javascript:window.location='SysRoleAction_findByPage.action'" class="btn_80" /></span>
			</div>
			
			<div>
				<table class="tableStyle01" align="left"  width="100%" border= "1 "   cellpadding= "0 "   cellspacing= "0 "   style= "border-collapse:   collapse ">
				
					<tr>
					<td valign="top" class="TDstyle02" width="50%">
					<div id="menuTree" width="100%" height="100%" style="padding:0px 10px"></div>
					<input type="hidden" name="menu_no" id="menu_no" size="100">
					<input type="hidden" name="role_no" id="role_no" size="100" value="<%=role_no %>">
					</td>
					<td valign="top" class="TDstyle02" width="50%">
						<div id="menutable" style="float:center;">
						<center>配置角色可见按钮</center>
						<table id="tab" width="100%" border="0" align="center" cellspacing=1
			class="ls_list">
			<thead>
			<tr>
				<th valign="top" width="15%" align="center" class="first">选择</th>
				<th valign="top" width="15%" align="center">编号</th>
				<th valign="top" width="70%" align="center" class="last">按钮功能</th>
			</tr>
			</thead>
						</table>
						</div>
					</td>
					</tr>
				</table>
			</div>
			<s:hidden name="role_no"></s:hidden>
			<s:token></s:token>
			</s:form>
		</div>
		</div>
		</div>
		<form name="operform" action="#" method="post">
    	</form>
	</body>
		<script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/js/tree/common-min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/js/tree/TreePanel.js"></script>
	<script type="text/javascript">
		var root=<%=trees%>;
		tree = new TreePanel({
				'root' : root,
				'renderTo':'menuTree',
				'handler':seeFocusNode
			});
		tree.render();
		
		function getMenus(){
			var menu='';
			for(var k in tree.nodeHash){
				var node = tree.nodeHash[k];
				if(node.checked==1||node.checked==2){
					var value = node.attributes['id'];
					if(value != null){
						menu+=value+",";
					}
				}
			}
			document.getElementById("menus").value=menu;
		}
	
		function func_save(){
		   var element=document.getElementsByName("checkboxList");
		   var boxStr="";
		   var count=0;
		   if(element.length>0){
		      for(var i=0;i<element.length;i++){
		         if(element[i].checked==true){  
		            if(boxStr==""){
		              boxStr=element[i].value;
		              count++;
		            }else{
		               boxStr=boxStr+"@"+element[i].value;  
		              count++;  
		            }                           
		         }                        
		      }
		      if(count==0){
		        alert("至少选择一项!");
		        return false;
		      } 
			}
			var role_no = document.getElementById("role_no").value;
			var menu_no=document.all("menu_no").value;
			var sts;
			$.ajaxSetup({   
            	async : false,  
            	contentType: "application/x-www-form-urlencoded; charset=utf-8"
        	}); 
			$.post("SysRoleAction_saveButton.action", {
				role_no : role_no,
				menu_no : menu_no,
				boxStr : encodeURI(boxStr)
				}, function(data) {
				if(data == null){
					return;
				} else {
					sts = data;
				}	
			});
			if(sts=="1") {
	       		alert("保存成功");	
	       	}else{
	       		alert("操作失败");
	       	}
		}
		function expandAll(){
			tree.expandAll();
		}
		function collapseAll(){
			tree.collapseAll();
		}
		function seeFocusNode(node){
				if(node!=null){
					if(node.attributes.lvl=='3'){
						var menu_no=node.attributes.id;
						document.all('menu_no').value=menu_no;
						var role_no = document.getElementById("role_no").value;
						var buttonlist;
						$.ajaxSetup({   
            				async : false  
        				}); 
			       		$.post("SysRoleAction_getMenuButton.action", {
							menu_no : menu_no
							}, function(data) {
								if(data == null){
									return;
								} else {
									buttonlist = data;
								}	
						},"json");	
						var tablehead = "";
		       			tablehead += "<thead>";
		       			tablehead += "<tr>";
		       			tablehead += "<th class='first' valign='top' width='15%' align='center'>选择</th>";
		       			tablehead += "<th valign='top' width='15%' align='center'>编号</th>";
		       			tablehead += "<th class='last' valign='top' width='70%' align='center'>按钮功能</th>";
		       			tablehead += "</tr>";
		       			
		       			$("#menutable").find("table").html(tablehead);
			       		if(buttonlist==""){
			       			return false;
			       		}
				       	var str = "<tbody id='tab'>";
				       	for(var i=0;i<buttonlist.length;i++){
				       		if(i%2==1){
				       			str += "<tr class='t2'>";
				       		}else {
				       			str += "<tr class='t1'>";
				       		}
				       		str += "<td align='center' width='15%' align='center'>";
				       		if(isCheckedButtonNo(menu_no,role_no,buttonlist[i].button_no)) {
								str += "<input type='checkbox' name='checkboxList' value='"+buttonlist[i].button_no+"' checked='true'>";//checkbox选中
							} else {
								str += "<input type='checkbox' name='checkboxList' value='"+buttonlist[i].button_no+"'>";//checkbox不选中
							}
				       		str += "</td>";
				       		str += "<td  align='center' width='15%' align='center'>";
				       		str += buttonlist[i].button_no;
				       		str += "</td>";
				       		str += "<td  align='center' width='70%' align='center'>";
				       		str += buttonlist[i].button_desc;
				       		str += "</td>";
				       		str += "</tr>";
				       	}
				       	str += "<tr>";
				       	str += "<td  align='center' colspan='3' align='center'>";
				       	str += "<div class='from_btn'><input type='button' value='全选' onclick='selectAllBtn();' class='button3'/>&nbsp;&nbsp;<input type='button' value='反选' onclick='unSelectBtn();' class='button3'/>&nbsp;&nbsp;<input type='button' name='btn4' value='保存' onclick='func_save()' class='button3'/></div>"
				       	str += "</td>";
				       	str += "</tr>";
				       	str += "</tbody>";
				      $("#menutable").find("table").append(str);
					}else{
						return;
					}
				}else{
					alert('没有选中节点')
				}
			}
	function isCheckedButtonNo(menu_no,role_no,button_no) {
		var rolebuttonlist;
			$.ajaxSetup({   
            	async : false  
        	});
			$.post("SysRoleAction_getButtonList.action", {
					role_no : role_no,
					menu_no : menu_no
				}, function(data) {
					if(data == null){
						return;
					} else {
						rolebuttonlist = data;
					}			
			},"json");	
			for(var i = 0; i < rolebuttonlist.length; i++){
				if(button_no == rolebuttonlist[i].button_no) 
					return true;
			}
			return false;
		}
	</script>
</html>