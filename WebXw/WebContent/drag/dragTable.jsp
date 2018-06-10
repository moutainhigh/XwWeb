<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/loan.tld" prefix="dhcc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  String ttableId = request.getParameter("tableId");
  String currTableId="";
  if(ttableId!=null&&ttableId.trim()!="")
  {
      currTableId="当前编辑列表编号:"+ttableId;
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>列表编辑器</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=GBK">
		<script type='text/javascript' src= '<%=request.getContextPath()%>/drag/js/dragTable.js'></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/drag/js/common.js'></script>
		<script type='text/javascript' src= '<%=request.getContextPath()%>/drag/js/empty.js'></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/screencore.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/drag/css/dragCss.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/drag/css/style.css" type="text/css" />
		<style type="text/css">
			body{margin:0; padding:0;font-family:Verdana, Arial, Helvetica, sans-serif,"";font-size:12px;}
			a:link { 
			color:#AAAAAA; 
			text-decoration:none;
			} 
			a:visited { 
			color:#AAAAAA; 
			text-decoration:none; 
			} 
			a:hover { 
			cursor:pointer;
			color:#AAAAAA; 
			text-decoration:none; 
			} 
			a:active { 
			color:#AAAAAA; 
			text-decoration:none; 
			}  
		</style>
	</head>
	<body bgcolor="#FFFFFF">
		<div id="winlinks" class="divcolor">
		    <div class="divtoolboxlabel" align="center">工具箱</div>
			<div id="div0" class="components" style="left:5px; top:40px;" onmouseover="fun5('none')" onmouseout="fun6('none')">
				<img id="none" src="drag/images/toolbox/TextEdit.png"  width=16px height=16px />
				<label>文本描述</label>
			</div>
			<div id="div1" class="components" style="left:5px; top:80px;" onmouseover="fun5('date')" onmouseout="fun6('date')">
				<img id="date" src="drag/images/toolbox/date_select_start.png" width=16px height=16px  />
				<label>日期型</label>
			</div>
			<div id="div6" class="components" style="left:5px; top:120px;" onmouseover="fun5('select')" onmouseout="fun6('select')">
				<img id="select" src="drag/images/toolbox/listbox.png" width=16px height=16px  />
			    <label>下拉框</label>
			</div>
			<div id="div2" class="components" style="left:5px; top:160px;" onmouseover="fun5('input_checkbox')" onmouseout="fun6('input_checkbox')">
				<img id="input_checkbox" src="drag/images/toolbox/stock_form-checkbox.png" width=16px height=16px />
			    <label>复选框</label>
			</div>
			<div id="div4" class="components" style="left:5px; top:200px;" onmouseover="fun5('link')" onmouseout="fun6('link')">
				<img id="link" src="drag/images/toolbox/link.png"  width=16px height=16px />
			    <label>链接</label>
			</div>
			<div id="div5" class="components" style="left:5px; top:240px;" onmouseover="fun5('input_button')" onmouseout="fun6('input_button')">
				<img id="input_button" src="drag/images/toolbox/button.png"  width=16px height=16px />
			    <label>操作按钮</label>
			</div>
			<div id="div3" class="components" style="left:5px; top:280px;" onmouseover="fun5('input_split')" onmouseout="fun6('input_split')">
				<img id="input_split" src="drag/images/toolbox/cut.png"  width=16px height=16px />
			    <label>截取</label>
			</div>
			<div id="div00" class="components" style="left:5px; top:320px;" onmouseover="fun5('input_other')" onmouseout="fun6('input_other')">
				<img id="input_other" src="drag/images/toolbox/navigate-check.png"  width=16px height=16px />
			    <label>其它元素</label>
			</div>
		</div>
		<div style="width:5px; height:100%; position:absolute; left:99px; top:0px;"><img width=2px height=100% src="drag/images/toolbox/colline.png" /></div>
		<div id="currTableDiv" style="position:absolute; left:150px; top:0px;"><label id="currTableLabel"></label></div>
		<div style="height:30px; position:absolute; left:101px; top:1px;">
		    <table border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td>
			      <label><%=currTableId%></label>
			    </td>
			  </tr>
			</table>
		</div>
		<div id="tableMoveDiv" style="position:absolute; left:100px; top:80px;">
            <%
            String tId =(String )request.getAttribute("tableId");
            String tableId = "table"+tId;
           %>
            <dhcc:preTableTag  paginate="testValue" property="<%=tableId%>" head="true"> 
            </dhcc:preTableTag> 
		</div>
		<div id="searchTableDiv" class="searchTableDiv" ondblclick="closeDiv(this)">
		    <table width="100%">
		        <tr>
		            <td align="right">列表编号</td>
		            <td align="left"><input type="text" id="searchTableId"/></td>
		            <td align="right">列表名称</td>
		            <td align="left"><input type="text" id="searchTableTitle"/></td>
		            <td align="center"><button type="button" onclick="fun10()">查询</button></td>
		        </tr>
		    </table>
			<table align="center" width="100%" border="0" id="tableListTable">
		        <tr height="20">
		            <td align="center" height="20">列表名称</td>
		            <td align="center">列表编号</td>
		            <td align="center">修改列表</td>
		            <td align="center">删除列表</td>
		            <td align="center">列表维护</td>
		            <td align="center">复制</td>
		        </tr>
		    </table>
		    <table align="center">
	            <tr align="center">
	                <td align="center"><span style="cursor:hand;" onclick="fun14('first')"><font color="#0000ff">首页&nbsp;&nbsp;</font></span></td>
	                <td align="center"><span style="cursor:hand;" onclick="fun14('previous')"><font color="#0000ff">上一页&nbsp;&nbsp;</font></span></td>
	                <td align="center"><span style="cursor:hand;" onclick="fun14('next')"><font color="#0000ff">下一页&nbsp;&nbsp;</font></span></td>
	                <td align="center"><span style="cursor:hand;" onclick="fun14('last')"><font color="#0000ff">尾页</font></span></td>
	            </tr>
	        </table>
		    <div id="updateTableDiv" class="updateTableDiv" style="display:none;">
		        <table width="100%">
		        <tr>
		            <td align="right">列表ID<font color="red">*</font></td>
		            <td align="left"><input type="text" id="updateTableId" size="15"  readonly="readonly"/></td>
		            <td align="right">列表名称<font color="red">*</font></td>
		            <td align="left"><input type="text" id="updateTableTitle" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="right">类型<font color="red">*</font></td>
		            <td align="left"><select name="updatetableType" title="类型"  mustinput="1">
						<option value="1">显示页数</option>
						<option value="2">无翻转页</option>
						<option value="3">不显示页数</option>
						<option value="4">显示页数(无go)</option>
						<option value="5">不显示页数(无go)</option>
						</select>
					</td>
		            <td align="right">跳转页链接 <font color="red">*</font></td>
		            <td align="left"><input type="text" id="updatepageLink" /></td>
		        </tr>
		        <tr>
		            <td align="right">是否导出EXCEL<font color="red">*</font></td>
		            <td align="left"><select name="updateexportExcel" title="是否导出EXCEL"  mustinput="1">
						<option value="0">否</option>
						<option value="1">是</option>
						</select>
					</td>
		        </tr>
		        <tr>
		            <td align="center" colspan="2"><button type="button" onclick="fun9()">确定</button></td>
		            <td align="center" colspan="2"><button type="button" onclick="closeDiv2('updateTableDiv')">关闭</button></td>
		        </tr>
		    </table>
		    </div>
		    <div id="copyTableDiv" class="copyTableDiv" style="display:none;">
		        <table width="100%">
		        <tr>
		            <td align="right">原列表ID<font color="red">*</font></td>
		            <td align="left"><input type="text" id="copyTableId" size="15"  readonly="readonly"/></td>
		        </tr>
		        <tr>
		            <td align="right">新列表ID<font color="red">*</font></td>
		            <td align="left"><input type="text" id="newTableId" size="15"/></td>
		            <td align="right">列表名称<font color="red">*</font></td>
		            <td align="left"><input type="text" id="newTableTitle" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="right">类型<font color="red">*</font></td>
		            <td align="left"><select name="newTableType" title="类型"  mustinput="1">
						<option value="1">显示页数</option>
						<option value="2">无翻转页</option>
						<option value="3">不显示页数</option>
						<option value="4">显示页数(无go)</option>
						<option value="5">不显示页数(无go)</option>
						</select>
					</td>
		            <td align="right">跳转页链接<font color="red">*</font></td>
		            <td align="left"><input type="text" id="newPageLink" /></td>
		            <input type="hidden" readOnly="readOnly" id="newPara" size="15"  value="eadis_page"/>
		    		<input name="newExportExcel" type="hidden" value="0">
		        </tr>
		        <tr>
		            <td align="center" colspan="2"><button type="button" onclick="fun37()">确定</button></td>
		            <td align="center" colspan="2"><button type="button" onclick="closeDiv2('copyTableDiv')">关闭</button></td>
		        </tr>
		    </table>
		    </div>
		    <div id="searchTableLoadingDiv" class="searchTableLoadingDiv"><!--<img src="drag/images/loading.gif" width=40px height=40px/>--></div>
		</div>
		<div id="addTable" class="addTable">
		    <table width="100%">
		        <tr>
		            <td align="right">列表ID<font color="red">*</font></td>
		            <td align="left"><input type="text" id="insertTableId" size="15" /></td>
		            <td align="right">列表名称<font color="red">*</font></td>
		            <td align="left"><input type="text" id="insertTableTitle" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="right">类型<font color="red">*</font></td>
		            <td align="left"><select name="tableType" title="类型"  mustinput="1">
						<option value="1">显示页数</option>
						<option value="2">无翻转页</option>
						<option value="3">不显示页数</option>
						<option value="4">显示页数(无go)</option>
						<option value="5">不显示页数(无go)</option>
						</select>
					</td>
		            <td align="right">跳转页链接 <font color="red">*</font></td>
		            <td align="left"><input type="text" id="pageLink" ></td>
		            
		        </tr>
		        <tr>
		            <td align="right">是否导出EXCEL<font color="red">*</font> </td>
		            <td align="left"><select name="exportExcel" title="是否导出EXCEL"  mustinput="1" />
		            <option value="0" selected>否</option>
						<option value="1">是</option>
						</td>
		            <td align="right"></td>
		            <td align="left"><input type="hidden" readOnly="readOnly" id="para" size="15"  value="eadis_page"/></td>
		        </tr>
		        <tr>
		            <td align="center" colspan="2"><button type="button" onclick="fun8()">确定</button></td>
		            <td align="center" colspan="2"><button type="button" onclick="closeDiv2('addTable')">关闭</button></td>
		        </tr>
		    </table>
		</div>
		<div id="div11" class="dragDiv11"></div>
		<div id="new" style="height:100%; position:absolute; left:120px; top:0px;">
		</div>
		<div id="arrow" style="display:none">
		    <img src="drag/images/toolbox/arrow.ico" width=16px height=16px/>
		</div>
		<input type="hidden" name="tableId"  id="tableId" value="<s:property value="tableId"/>"/>
		<input type="hidden" name="query"  id="query" value="insert"/>
	    <input type="hidden" name="tableListId"  id="tableListId" value=""/>
		<div id="popAlert" style="display:none;background:#e2ecf5" onmousedown="dragPro('popAlert')">		
			<div id="tab_div">
	          <span id="tab1_span" class="span" onclick="fun7(this)">基本信息</span>
          </div>
		  <div id="tab1" name="a" class="table_tab_block">
		      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr> 
				      <td> 
				          <form id="submitPropertyTable" action="TableDataForToolBean_insertTableElement.action" method="post">
						      <table  width="96%" border="0" align="center"  cellpadding="0" cellspacing="0">
								<tr>
									<td align="right">列名称<font color="#FF0000">*</font> </td>
									<td align="left"><input type="text" title="列名称" name="tableList.label"></td>
									<td align="right">字段名&nbsp</td>
									<td align="left"><input type="text" title="字段名" name="tableList.fieldName"></td>
									<td align="right">宽度&nbsp</td>
									<td align="left"><input type="text" title="宽度" name="tableList.width"></td>
								</tr>
								<tr>
									<td align="right">对齐<font color="#FF0000">*</font> </td>
									<td align="left">
										<select name="tableList.align" title="对齐" class="form_select">
										     <option value="1">左对齐</option>
											 <option value="2">居中</option>
											 <option value="3">右对齐</option>
										</select>
									</td>
									<td align="right">列顺序<font color="#FF0000">*</font> </td>
									<td align="left"><input type="text" title="列顺序" name="tableList.indexed"  ></td>
									<td align="right">类型<font color="#FF0000">*</font> </td>
									<td align="left">
										<select name="tableList.type" title="类型">
										    <option value="0">文本框</option>
											<option value="1">日期型</option>
											<option value="2">复选框</option>
											<option value="3">截取</option>
											<option value="4">超链接</option>
											<option value="5">操作按钮</option>
											<option value="6">选项描述</option>
											<option value="7">树展示</option>
											<option value="8">无字段</option>
											<option value="9">文本框(可编辑)</option>
											<option value="10">选项描述(可编辑)</option>
											<option value="11">从库中选择</option>
											<option value="12">金额型(元)</option>
											<option value="13">金额型(分)</option>
											<option value="14">金额型(角)</option>
											<option value="15">金额型(万元)</option>
											<option value="16">金额型(亿元)</option>
											<option value="17">百分号％</option>
											<option value="18">千分号‰</option>
											<option value="19">万分号</option>
										</select>
									</td>
								</tr>
								<tr style="display:none">
									<td align="right">行数&nbsp</td>
									<td align="left"><input type="text" title="行数" name="tableList.row"    value="1"></td>
									<td align="right">跨行数&nbsp</td>
									<td align="left"><input type="text" title="跨行数" name="tableList.rowSpan"    value="1"></td>
									<td align="right">跨列数&nbsp</td>
									<td align="left"><input type="text" title="跨列数" name="tableList.colSpan"    value="1"></td>
								</tr>
								<tr>
									<td align="right">参数&nbsp</td>
									<td colspan="5" align="left"><input type="text" title="参数" name="tableList.typePara"  size="80"></td>
								</tr>
								<tr>
									<td align="right">按钮不显示条件&nbsp</td>
									<td colspan="5" align="left"><input type="text" title="按钮不显示条件" name="tableList.authority" size="80"></td>
								</tr>
								<tr>
									<td align="right">浮动框显示内容&nbsp</td>
									<td colspan="5" align="left"><input type="text" title="浮动框显示内容" name="tableList.mytitle" size="80"></td>
								</tr>
								<tr>
									<td align="right">按钮权限标志&nbsp</td>
									<td colspan="5" align="left"><input type="text" title="按钮权限标志" name="tableList.buttonMark" size="80"></td>
								</tr>
				              </table>
				          </form>
				      </td>
				  </tr>
				</table>
		      <table align="center" width="50%">
		          <tr>
		              <td><button type="button" onclick="fun11()">确定</button></td>
		              <td><button type="button" onclick="fun12()">删除</button></td>
		               <td><button type="button" onclick="closePopAlert()">关闭</button></td>
		          </tr>
		      </table>
		  </div>
		</div>
		<div id="tempDiv" onselectstart="return false" class="dragDiv">
		</div>
	</body>
</html>
