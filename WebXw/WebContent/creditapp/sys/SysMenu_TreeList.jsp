<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" %>
<%@ page import="app.creditapp.sys.entity.SysMenu" %>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>小微金融信贷管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/creditapp/ztree/css/zTreeStyle.css" type="text/css">
	<style type="text/css">
	.contextMenu{
		visibility:hidden;
		z-index: 500; position: absolute; display: block; top: 141px; left: 196px;
	}
	.contextMenu ul{
		border: #a0c9de 1px solid;  padding-bottom: 1px; background-color: #fff; list-style-type: none; margin: 0px; padding-left: 1px; width: 120px;  padding-top: 1px;
	}
	.contextMenu ul li{
		border-bottom: #dce6ec 1px solid; margin: 0px; padding-left: -20px; display: block; color: #787878; cursor: pointer; line-height:30px;
	}
	.contextMenu ul li:hover{
		background-color:#f6fcff; color:#e7a300;
	}
	.contextMenu ul li img{ margin-right:5px;}
	
	</style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/creditapp/ztree/js/jquery.ztree.exedit-3.5.js"></script>
	</head>
<body class="body_bg">
	<div class="right_bg">
		<div class="right_w">
			<div class="from_bg">
				<div class="right_v">
					<div class="contextMenu" id="rMenu1">
						<ul>
							<li id="m_add" onclick="addTreeNode();"><img src="<%=request.getContextPath()%>/creditapp/sys/images/folder_add.png" />新增菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li id="m_edit" onclick="editTreeNode();"><img src="<%=request.getContextPath()%>/creditapp/sys/images/folder_edit.png" />编辑菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li id="m_del" onclick="removeTreeNode();"><img src="<%=request.getContextPath()%>/creditapp/sys/images/folder_delete.png" />删除菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
						</ul>
					</div>
					<div class="contextMenu" id="rMenu2">
						<ul>
							<li id="m_edit" onclick="editTreeNode();"><img src="<%=request.getContextPath()%>/creditapp/sys/images/page_edit.png" />编辑菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li id="m_editbtn" onclick="editbtnTreeNode();"><img src="<%=request.getContextPath()%>/creditapp/sys/images/page_btn.png" />配置菜单按钮</li>
							<li id="m_del" onclick="removeTreeNode();"><img src="<%=request.getContextPath()%>/creditapp/sys/images/page_delete.png" />删除菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
						</ul>
					</div>
					<table width="100%" height="100%" border="0" align="center" vAlign="top" class="TDstyle01">
						
						<tr>
							<td valign="top" align="left" width="40%" class="TDstyle02">
								<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w">
								<tr><td  colspan="2"align="center" class="bartitle" >菜单树列表</td></tr>
								</table>
								<ul>
									<div class="zTreeDemoBackground left">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
								</ul></td>
								
								<td  align="left" width="60%" class="TDstyle02" vAlign="top" style="vertical-align: top;text-align: right;">
			<div id="menuDiv" style="display:none;">
				<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w">
					<tr><td  colspan="2"align="center" class="bartitle" >修改菜单信息</td></tr>
					<tr><td align="right" width="30%" class="tdlable">菜单编号:</td><td width="70%" align="left" class="tdvalue"><input type="text" name="menuNo" id="menuNo" size="20" value="" readonly="readonly"/></td></tr>
					<tr><td align="right" class="tdlable">菜单名称:</td><td align="left" class="tdvalue"><input type="text" name="menuName" id="menuName"  size="20"  value=""/></td>
					<tr><td align="right" class="tdlable">父菜单编号:</td align="left" class="tdvalue"><td><input type="text" name="menuParent" id="menuParent"  size="20"  value="" readonly="readonly"/></td>
					<tr><td align="right" class="tdlable">菜单级别:</td><td align="left" class="tdvalue"><input type="text" name="menuLvl" id="menuLvl"  size="20" maxlength="1" value="" readonly="readonly"/></td>
					<tr><td align="right" class="tdlable">菜单URL:</td><td align="left" class="tdvalue"><input type="text" name="menuUrl" id="menuUrl"  size="55"  value=""/></td>
					<tr><td align="right" class="tdlable">是否启用:</td><td align="left" class="tdvalue">
						<input type="radio" name="menuSts" value="01"> 是&nbsp;&nbsp;
						<input type="radio" name="menuSts" value="02"> 否&nbsp;&nbsp;
					</td></tr>
					<tr><td colspan="2">
						<div class="from_btn">
							<input type="button" name="modbtn" id="modbtn" value="保存修改" onclick="updateMenu();" class="btn_4"/>
							<input type="button" name="delbtn" id="delbtn" value="删除" onclick="removeTreeNode();" class="btn_80"/>
						</div>
					</td></tr>
				</table>
			</div>
			<div id="menuAddDiv" style="display:none;">
				<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="from_w">
					<tr><td  colspan="2"align="center" class="bartitle" >新增菜单信息</td></tr>
					<tr><td width="30%" align="right" class="tdlable">菜单编号:</td><td width="70%" class="tdvalue"><input type="text" name="menuAddNo" id="menuAddNo" size="20" value=""/></td></tr>
					<tr><td align="right" class="tdlable">菜单名称:</td><td class="tdvalue"><input type="text" name="menuAddName" id="menuAddName"  size="20"  value=""/></td></tr>
					<tr><td align="right" class="tdlable">父菜单编号:</td><td class="tdvalue"><input type="text" name="menuAddParent" id="menuAddParent"  size="20"  value="" readonly="readonly"/></td></tr>
					<tr><td align="right" class="tdlable">菜单级别:</td><td class="tdvalue"><input type="text" name="menuAddLvl" id="menuAddLvl"  size="20" maxlength="1" value="" readonly="readonly"/></td></tr>
					<tr><td align="right" class="tdlable">菜单URL:</td><td class="tdvalue"><input type="text" name="menuAddUrl" id="menuAddUrl"  size="55"  value=""/></td></tr>
					<tr><td align="right" class="tdlable">是否启用:</td><td align="left" class="tdvalue">
						<input type="radio" name="menuAddSts" value="01" checked="checked"> 是&nbsp;&nbsp;
						<input type="radio" name="menuAddSts" value="02"> 否&nbsp;&nbsp;
					</td></tr>
					<tr><td colspan="2">
						<div class="from_btn">
							<input type="button" name="addbtn" id="addbtn" value="保存新增" onclick="insertMenu();" class="btn_4"/>
						</div>
					</td></tr>
				</table>
					
			</div>
		</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<SCRIPT type="text/javascript">
		var zTree, rMenu1, rMenu2;
		var setting = {
				async : {
					autoParam:["id"],
					url:"SysMenuAction_getRootMenu.action",
		            enable:false

				},
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
					next: dropNext
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onMouseDown: OnMouseDown,
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onDrag: onDrag,
				onDrop: onDrop,
				onRightClick: OnRightClick,
				onExpand: onExpand
			}
		}
		
		function OnMouseDown(event, treeId, treeNode) {
			if(treeNode){
				$("#menuAddDiv").css("display","none");
				$("#menuDiv").css("display","block");
				$("#menuNo").val(treeNode.id);
				$("#menuName").val(treeNode.name);
				$("#menuParent").val(treeNode.pId);
				$("#menuLvl").val(Number(treeNode.lvl));
				var url = treeNode.url=="null"?"":treeNode.url;
				$("#menuUrl").val(url);
				if(treeNode.sts=='01'){
					$("input[name='menuSts'][value='01']").attr("checked",true);
				}
				else{
					$("input[name='menuSts'][value='02']").attr("checked",true);
				}
			}
		}
		
		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu(treeNode.lvl, event.clientX, event.clientY+document.body.scrollTop);
			}
		}
		
		function showRMenu(lvl, x, y) {
			if (lvl =="0") {
				$("#m_edit").hide();
				$("#m_del").hide();
			} else {
				$("#m_edit").show();
				$("#m_del").show();
			}
			 if (lvl!="3") {
				$("#rMenu1 ul").show();
				
				rMenu1.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			} else {
				$("#rMenu2 ul").show();
				rMenu2.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			}

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		function hideRMenu() {
			if (rMenu1) rMenu1.css({"visibility": "hidden"});
			if (rMenu2) rMenu2.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu1" || $(event.target).parents("#rMenu1").length>0)) {
				rMenu1.css({"visibility" : "hidden"});
			}
			if (!(event.target.id == "rMenu2" || $(event.target).parents("#rMenu2").length>0)) {
				rMenu2.css({"visibility" : "hidden"});
			}
		}
		
		function dropPrev(treeId, nodes, targetNode) {
			return false;
		}
		function dropInner(treeId, nodes, targetNode) {
			var node = nodes[0];
			if((node.lvl==3 && targetNode.lvl==2) || (node.lvl==2 && targetNode.lvl==1)){
				return true;
			}
			return false;
		}
		function dropNext(treeId, nodes, targetNode) {
			var node = nodes[0];
			if(node.lvl != targetNode.lvl){
				return false;
			}
			return true;
		}
		var log, className = "dark", curDragNodes, autoExpandNode;
		function beforeDrag(treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					curDragNodes = null;
					return false;
				} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
					curDragNodes = null;
					return false;
				}
			}
			curDragNodes = treeNodes;
			return true;
		}
		function beforeDragOpen(treeId, treeNode) {
			autoExpandNode = treeNode;
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
			var node = treeNodes[0];
			if(!node || !targetNode){
				return false;
			}
			var nlvl = node.lvl;
			var tlvl = targetNode.lvl;
			//alert((node.getPreNode() == targetNode));
			if((node.getPreNode() == targetNode)){//拖拽节点是目标节点的下一个兄弟节点
				return false;
			}
			//平级互换
			if(nlvl ==  tlvl){
				if(confirm("确定把["+node.text+"]移动到["+targetNode.text+"]下方?")){
					return true;
				}
				return false;
			}
			if((nlvl==3 && tlvl==2) || (nlvl==2 && tlvl==1)){
				if(confirm("确定把["+node.text+"]移动到["+targetNode.text+"]下?")){
					return true;
				}
				return false;
			}
			alert("错误!只允许移动三级菜单到二级菜单下!");
			return false;
		}
		function onDrag(event, treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
		}
		function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			var node = treeNodes[0];
			if(!node || !targetNode){
				return;
			}
			if(node.lvl == targetNode.lvl){
				//alert("执行交换treeNodes");
				$.get("SysMenuAction_changeMenuSeq.action",{parentMenuNo:targetNode.id,menuNo:node.id,rdm:Math.random()},function(data){
					if(data=="success"){
						sAlert("菜单移动成功!");
					} else {
						sAlert("菜单移动提交数据失败!");
					}
				});
			}else if((node.lvl==3 && targetNode.lvl==2) || (node.lvl==2 && targetNode.lvl==1)){
				//alert("更换父级菜单");
				$.get("SysMenuAction_moveMenu.action",{parentMenuNo:targetNode.id,menuNo:node.id,rdm:Math.random()},function(data){
					if(data=="success"){
						alert("菜单移动成功!");
					} else {
						alert("菜单移动提交数据失败!");
					}
				});
			}
		}
		function onExpand(event, treeId, treeNode) {
			if (treeNode === autoExpandNode) {
				className = (className === "dark" ? "":"dark");
			}
		}

		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}
			
		function addTreeNode(){
			hideRMenu();
			var node = zTree.getSelectedNodes()[0];
			var allNodesofThisLev = node.children;
			var newNodeNo = getMaxMenuNumber(node.id,allNodesofThisLev);
			$("#menuDiv").css("display","none");
			$("#menuAddDiv").css("display","block");
			$("#menuAddParent").val(node.id);
			$("#menuAddLvl").val(Number(node.lvl)+1);
			$("#menuAddNo").val(newNodeNo);
		}
		
		function getMaxMenuNumber(pId,nodeArray){
			var maxNo = 0;
			var thisNo = "";
			for(var cnode in nodeArray){
				thisNo = nodeArray[cnode].id;
				thisNo = thisNo.substring(pId.length,thisNo.length);
				if(parseInt(thisNo)>maxNo){
					maxNo = parseInt(thisNo);
				}
			}
			maxNo++;
			if(maxNo<10)maxNo= "0" + maxNo;
			return pId + maxNo;
		}
		
		function editTreeNode(){
			hideRMenu();
			var node = zTree.getSelectedNodes()[0];
			$("#menuAddDiv").css("display","none");
			$("#menuDiv").css("display","block");
			$("#menuNo").val(node.id);
			$("#menuName").val(node.name);
			$("#menuParent").val(node.pId);
			$("#menuLvl").val(Number(node.lvl));
			var url = node.url=="null"?"":node.url;
			$("#menuUrl").val(url);
			if(node.sts=='01'){
				$("input[name='menuSts'][value='01']").attr("checked","checked");
			}
			else{
				$("input[name='menuSts'][value='02']").attr("checked",true);
			}
			
		}
		function removeTreeNode(){
			hideRMenu();
			var node = zTree.getSelectedNodes()[0];
			if(!node){
				alert("错误:节点不存在,可能已被删除!");
				return;
			}
			if(confirm("确定删除菜单["+node.text+"]?")){
				var url = "SysMenuAction_deleteMenu.action";
				$.post(url,{tcode:node.id,lvl:node.lvl,rdm:Math.random()},function(data){
					if($.trim(data)=="success"){
						zTree.removeNode(node);
						$("#menuDiv").css("display","none");
						alert("删除成功!");
					}else{
						alert("删除失败!");
					}
				});
			 }
			
		}
		function editbtnTreeNode(){
			hideRMenu();
			var node = zTree.getSelectedNodes()[0];
			var url = "SysButtonAction_findAllByMenu.action?menuNo=" + node.id;
			 window.showModalDialog(
					    url,window,"dialogWidth=900px;dialogHeight=400px;resizable=no;scrollbars=no;status:yes;help:no;");
			
		}
		function updateMenu(){
			var tcode = $("#menuNo").val();
			var tname = $("#menuName").val();
			var pcode = $("#menuParent").val();
			var lvl = $("#menuLvl").val();
			var url = $("#menuUrl").val();
			var menuSts = $("input[name='menuSts']:checked").val();
			if(tname=="" || tcode=="" || pcode=="" || lvl==""){
				alert("菜单要素不能为空!");
				return;
			}
			var reg = /[\d]{1}/;
			if(!reg.test(lvl)){
				alert("请正确输入菜单等级!");
				return;
			}
			$.post("SysMenuAction_updateMenu.action",{tcode:tcode,tname:encodeURI(tname),pcode:pcode,lvl:lvl,url:url,menuSts:menuSts,rdm:Math.random()},function(data){
				if($.trim(data)!="success"){
					alert($.trim(data));
					return;
				}else{
					var node = zTree.getSelectedNodes()[0];
					node.nanme=tname;
					node.pId = pcode;
					node.url = url;
					node.lvl = lvl;
					node.sts = menuSts;
					//var newNode = {'tId':node.tId,'checked':true,'name':tname,'id':tcode,'url':url,'sts':node.sts,'lvl':lvl,'pId':pcode,'children':[]};
					zTree.updateNode(node,false);
					alert("修改菜单成功!");
				}
			});
		}
		function insertMenu(){
			var tcode = $("#menuAddNo").val();
			var tname = $("#menuAddName").val();
			var pcode = $("#menuAddParent").val();
			var lvl = $("#menuAddLvl").val();
			var url = $("#menuAddUrl").val();
			var menuSts = $("input[name='menuAddSts']:checked").val();
			if(tname=="" || tcode=="" || pcode=="" || lvl==""){
				alert("菜单要素不能为空!");
				return;
			}
			var reg = /[\d]{1}/;
			if(!reg.test(lvl)){
				alert("请正确输入菜单等级!");
				return;
			}
			$.post("SysMenuAction_insertMenu.action",{tcode:tcode,tname:encodeURI(tname),pcode:pcode,lvl:lvl,url:url,menuSts:menuSts,rdm:Math.random()},function(data){
				if($.trim(data)!="success"){
					alert($.trim(data));
					return;
				}else{
					alert("新增菜单成功!");
					var parentNode = zTree.getSelectedNodes()[0];
					var newNode = {'checked':true,'name':tname,'id':tcode,'url':url,'sts':'1','lvl':lvl,'pId':pcode,'children':[]};
					zTree.addNodes(parentNode, newNode, false);
					
				}
			});
		}
		$(document).ready(function(){
			var zNodes= <%=(String)request.getAttribute("menuStr")%>;
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu1 = $("#rMenu1");
			rMenu2 = $("#rMenu2");
		});
	</SCRIPT>