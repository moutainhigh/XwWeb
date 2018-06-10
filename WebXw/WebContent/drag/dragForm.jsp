<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/loan.tld" prefix="dhcc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  String formId = request.getParameter("formId");
  String currFormId="";
  if(formId!=null&&formId.trim()!="")
  {
      currFormId="当前编辑表单编号:"+formId;
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>表单编辑器</title>
		<script type='text/javascript' src= '<%=request.getContextPath()%>/drag/js/dragForm.js'></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/drag/js/common.js'></script>
		<script type='text/javascript' src= '<%=request.getContextPath()%>/drag/js/empty.js'></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/screencore.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/drag/css/dragCss.css" type="text/css" />
		<link id="css" rel="stylesheet" href="<%=request.getContextPath()%>/drag/css/style.css" type="text/css" />
		<style type="text/css">
			body{margin:0; padding:0;font-family:Verdana, Arial, Helvetica, sans-serif,"";font-size:12px;}
			/*
			input{direction:rtl;unicode-bidi:bidi-override;}
			*/
		</style>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/themes/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$(".from_w > tbody > tr:even").addClass("evenRow");//奇数行
			var tabIndex = 1;
		    $("table.ls_list").attr("cellSpacing","0")
		    $("table.ls_list").find("tr").each(function(){
		    	if($(this).find("td.bartop").get(0) || $(this).find("td.bartitle").get(0)){
		    		tabIndex = 1;
		    	}else {
			    	if(tabIndex%2!=0){
			    		//$(this).addClass("evenRow");
			    		$(this).addClass("t2");
			    	}else {
			    		//$(this).addClass("oddRow");
			    		$(this).addClass("t1");
			    	}
			    	tabIndex++;
		    	}
		    });
		});
		
		</script>
	</head>
	<body bgcolor="#FFFFFF">
		<div id="winlinks" class="divcolor">
		    <div class="divtoolboxlabel" align="center">工具箱</div>
			<div id="div1" class="components" style="left:5px; top:40px;" onmouseover="fun7('input')" onmouseout="fun8('input')">
				<img id="input" src="drag/images/toolbox/TextEdit.png"  width=16px height=16px />
				<label>文本字段</label>
			</div>
			<div id="div5" class="components" style="left:5px; top:80px;" onmouseover="fun7('textarea')" onmouseout="fun8('textarea')">
				<img id="textarea" src="drag/images/toolbox/TextArea.png" width=16px height=16px  />
				<label>文本区域</label>
			</div>
			<div id="div2" class="components" style="left:5px; top:120px;" onmouseover="fun7('select')" onmouseout="fun8('select')">
				<img id="select" src="drag/images/toolbox/listbox.png" width=16px height=16px  />
			    <label>选择域</label>
			</div>
			<div id="div3" class="components" style="left:5px; top:160px;" onmouseover="fun7('input_checkbox')" onmouseout="fun8('input_checkbox')">
				<img id="input_checkbox" src="drag/images/toolbox/stock_form-checkbox.png" width=16px height=16px />
			    <label>复选域</label>
			</div>
			<div id="div6" class="components" style="left:5px; top:200px;" onmouseover="fun7('input_radio')" onmouseout="fun8('input_radio')">
				<img id="input_radio" src="drag/images/toolbox/RadioButton.png"  width=16px height=16px />
			    <label>单选域</label>
			</div>
			<div id="div6" class="components" style="left:5px; top:240px;" onmouseover="fun7('input_pw')" onmouseout="fun8('input_pw')">
				<img id="input_pw" src="drag/images/toolbox/password.png"  width=16px height=16px />
			    <label>密码域</label>
			</div>
			<div id="div99" class="components" style="left:5px; top:280px;" onmouseover="fun7('input_hidden')" onmouseout="fun8('input_hidden')">
				<img id="input_hidden" src="drag/images/toolbox/field_hidden.png"  width=16px height=16px />
			    <label>隐藏域</label>
			</div>
			<div id="div00" class="components" style="left:5px; top:320px;" onmouseover="fun7('input_other')" onmouseout="fun8('input_other')">
				<img id="input_other" src="drag/images/toolbox/navigate-check.png"  width=16px height=16px />
			    <label>其它元素</label>
			</div>
		</div>
		<div style="width:5px; height:100%; position:absolute; left:99px; top:0px;"><img width=2px height=100% src="drag/images/toolbox/colline.png" /></div>
		<div style="height:30px; position:absolute; left:101px; top:0px;" class="tab_w">
		    <ul>
				<li class="tab_mid"><a href="javaScript:fun16('insertRowDiv')">增加一行</a></li>
				<li class="tab_line"></li>
				<li class="tab_mid"><a href="javaScript:fun16('deleteRowDiv')">删除一行</a></li>
				<li class="tab_line"></li>
				<li class="tab_mid"><a href="javaScript:fun16('logicalFormulaDiv')">验证公式</a></li>
				<li class="tab_line"></li>
		    </ul>
		    <label id="currFormId"><%=currFormId%></label>
		</div>
		<div id="formMoveDiv" style="position:absolute; left:101px; top:80px;width:1000px">
		     <div width="95%">
		    	<dhcc:formPreviewTag property="form" mode="preQuery" />
		    	<a name="formitem" href="" target="mtopwin"></a>
		   	 </div>
		</div>
		<div id="hiddenElement" style="position:absolute;left:1110; top:10px;width:220px" onmousedown="dragPro('hiddenElement')">
		    <dhcc:tableTag  paginate="formElementList" property="table109" head="true"/>
		</div>
		<div id="searchFormDiv" class="searchFormDiv" ondblclick="closeDiv(this)">
		    <table width="100%">
		        <tr>
		            <td align="right">表单号</td>
		            <td align="left"><input type="text" id="searchFormId"/></td>
		            <td align="right">表单描述</td>
		            <td align="left"><input type="text" id="searchTitle"/></td>
		            <td align="center"><button type="button" onclick="fun13()">查询</button></td>
		        </tr>
		    </table>
			<table align="center" width="100%"  border="0" id="formListTable">
		        <tr height="20">
		            <td align="center" height="20">表单描述</td>
		            <td align="center">表单号</td>
		            <td align="center">修改描述</td>
		            <td align="center">删除表单</td>
		            <td align="center">详情维护</td>
		            <td align="center">复制</td>
		        </tr>
		    </table>
	        <table align="center">
	            <tr align="center">
	                <td align="center"><span style="cursor:hand;" onclick="fun25('first')"><font color="#0000ff">首页&nbsp;&nbsp;</font></span></td>
	                <td align="center"><span style="cursor:hand;" onclick="fun25('previous')"><font color="#0000ff">上一页&nbsp;&nbsp;</font></span></td>
	                <td align="center"><span style="cursor:hand;" onclick="fun25('next')"><font color="#0000ff">下一页&nbsp;&nbsp;</font></span></td>
	                <td align="center"><span style="cursor:hand;" onclick="fun25('last')"><font color="#0000ff">尾页</font></span></td>
	            </tr>
	        </table>
		    <div id="updateFormDiv" class="updateFormDiv" style="display:none;">
		        <table width="100%">
		        <tr>
		            <td align="right">表单号<font color="red">*</font></td>
		            <td align="left"><input type="text" id="updateFormId" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="right">表单描述<font color="red">*</font></td>
		            <td align="left"><input type="text" id="updateTitle" size="20"/></td>
		        </tr>
		         <tr>
		        <td align="right">是否显示标题<font color="red">*</font></td>
				      <td align="left">
				          <select name="updateformTitleShowFlag" title="是否显示标题" >
				          <option value="" selected></option>
				          <option value="false">否</option>
				          <option value="true">是</option>
				          </select>
				      </td>
		         </tr>
		        
		        <tr>
		            <td align="center"><button type="button" onclick="fun12()">确定</button></td>
		            <td align="center"><button type="button" onclick="closeDiv2('updateFormDiv')">关闭</button></td>
		        </tr>
		    </table>
		    </div>
		    <div id="copyFormDiv" class="copyFormDiv" style="display:none;">
		        <table width="100%">
		        	<tr>
			            <td align="right">原表单号<font color="red">*</font></td>
			            <td align="left"><input type="text" id="copyFormId" size="15" readonly="true"/></td>
			        </tr>
			        <tr>
			            <td align="right">新表单号<font color="red">*</font></td>
			            <td align="left"><input type="text" id="newFormId" size="15" /></td>
			        </tr>
			        <tr>
			            <td align="right">新表单描述<font color="red">*</font></td>
			            <td align="left"><input type="text" id="newTitle" size="20"/></td>
			        </tr>
			         <tr>
			        <td align="right">是否显示标题<font color="red">*</font></td>
					      <td align="left">
					          <select name="newTitleShowFlag" title="是否显示标题" >
					          <option value="" selected></option>
					          <option value="false">否</option>
					          <option value="true">是</option>
					          </select>
					      </td>
			         </tr>
			        <tr>
			            <td align="center"><button type="button" onclick="fun11()">确定</button></td>
			            <td align="center"><button type="button" onclick="closeDiv2('copyFormDiv')">关闭</button></td>
			        </tr>
		    	</table>
		    </div>
		    <div id="searchFormLoadingDiv" class="searchFormLoadingDiv"><!--<img src="drag/images/loading.gif" width=40px height=40px/>--></div>
		</div>
		<div id="addForm" class="addForm">
		    <table width="100%">
		        <tr>
		            <td align="right">表单号<font color="red">*</font></td>
		            <td align="left"><input type="text" id="insertFormId" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="right">表单描述<font color="red">*</font></td>
		            <td align="left"><input type="text" id="insertTitle" size="15" /></td>
		        </tr>
		         <tr>
		             <td align="right">是否显示标题<font color="red">*</font></td>
				      <td align="left">
				          <select name="insertformTitleShowFlag" title="是否显示标题" >
				          <option value="false">否</option>
				          <option value="true" selected>是</option>
				          </select>
				      </td>
		         </tr>
		        <tr>
		            <td align="center"><button type="button" onclick="fun10()">确定</button></td>
		            <td align="center"><button type="button" onclick="closeDiv2('addForm')">关闭</button></td>
		        </tr>
		    </table>
		</div>
		<div id="insertRowDiv" class="insertRowDiv">
		    <table width="100%">
		        <tr>
		            <td align="right">行号<font color="red">*</font></td>
		            <td align="left"><input type="text" id="insertRowNum" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="center"><button type="button" onclick="fun18()">确定</button></td>
		            <td align="center"><button type="button" onclick="closeDiv2('insertRowDiv')">关闭</button></td>
		        </tr>
		    </table>
		</div>
		<div id="deleteRowDiv" class="deleteRowDiv">
		    <table width="100%">
		        <tr>
		            <td align="right">行号<font color="red">*</font></td>
		            <td align="left"><input type="text" id="deleteRowNum" size="15" /></td>
		        </tr>
		        <tr>
		            <td align="center"><button type="button" onclick="fun19()">确定</button></td>
		            <td align="center"><button type="button" onclick="closeDiv2('deleteRowDiv')">关闭</button></td>
		        </tr>
		    </table>
		</div>
		<div id="logicalFormulaDiv" class="logicalFormulaDiv">
		    <table width="550px" border="0" cellpadding="0" cellspacing="1" style="border:#CAECD4;" bgcolor="EAEAEA" align="center">
			    <tr>
			      <td valign="top">
			        <table width="99%" border="1" align="center" cellpadding="5" cellspacing="0">
			          <tr> 
			              <td align="center"><select id="formElementSelect"></select></td>
			               <td>
			                <input name="b_insert" type="button" id="b_insert2" value="插入" onClick="fun20('formElementSelect')" class="btn_80"/>
			              </td>
			          </tr>
			          <tr height="15px"><td colspan="2"></td></tr>
			        </table>
			      </td>
			     </tr>
			     <tr>
			      <td width="100%" valign="top" align="center">
			      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			          <tr>
			            <td width="80%" align="center">
			            	<table width="80%"  border="1" align="center" cellpadding="0" cellspacing="1">
								<tr align="center" bgcolor="#CAECD4" height="30px"><!-- line 1 -->
									<td width="62px" onClick="fun22('&')" align="center">&</td>
									<td width="62px" onClick="fun22('|')" align="center">|</td>
									<td width="62px" onClick="fun22('!')" align="center">!</td>
					                <td width="62px" onClick="fun22('%')" align="center">%</td>
					                <td width="62px" onClick="fun22('>')" align="center">&gt;</td>
									<td width="62px" onClick="fun22('(')" align="center">(</td>
									<td width="62px" onClick="fun22(')')" align="center">)</td>
								</tr>
								<tr align="center" bgcolor="#CAECD4" height="30px">
				                  <td width="62px" onClick="fun22('7')" align="center">7</td>
				                  <td width="62px" onClick="fun22('8')" align="center">8</td>
				                  <td width="62px" onClick="fun22('9')" align="center">9</td>
				                  <td width="62px" onClick="fun22('/')" align="center">/</td>
				                  <td width="62px" onClick="fun22('<')" align="center">&lt;</td>
								  <td width="62px" onClick="fun22('[')" align="center">[</td>
								  <td width="62px" onClick="fun22(']')" align="center">]</td>
								</tr>
								<tr align="center" bgcolor="#CAECD4" height="30px">
				                  <td width="62px" onClick="fun22('4')" align="center">4</td>
				                  <td width="62px" onClick="fun22('5')" align="center">5</td>
				                  <td width="62px" onClick="fun22('6')" align="center">6</td>
				                  <td width="62px" onClick="fun22('*')" align="center">*</td>
				                  <td width="62px" onClick="fun22('>=')" align="center">≥</td>
								  <td width="62px" onClick="fun22('{')" align="center">{</td>
								  <td width="62px" onClick="fun22('}')" align="center">}</td>
								</tr>
								<tr align="center" bgcolor="#CAECD4" height="30px">
				                  <td width="62px" onClick="fun22('1')" align="center">1</td>
				                  <td width="62px" onClick="fun22('2')" align="center">2</td>
				                  <td width="62px" onClick="fun22('3')" align="center">3</td>
				                  <td width="62px" onClick="fun22('-')" align="center">-</td>
				                  <td width="62px" onClick="fun22('<=')" align="center">≤</td>
								  <td colspan="2" width="124px" onClick="fun22('$txDt')" align="center">营业日期</td>
								</tr>
								<tr align="center" bgcolor="#CAECD4" align="center" height="30px">
								  <td colspan="2" width="124px" onClick="fun22('0')" align="center">0</td>
								  <td width="62px" onClick="fun22('.')" align="center">.</td>
								  <td width="62px" onClick="fun22('+')" align="center">+</td>
								  <td width="62px" onClick="fun22('=')" align="center">=</td>
				                  <td width="62px" onClick="fun22(';')"  align="center">;</td>
				                  <td width="62px" onClick="fun24('formulavalidate')" align="center">←</td>
								</tr>
							</table>
						</td>
			            <td width="20%">
			           	 	<table width="100%">
				           	 	<tr align="left"><td>
				                	<input name="b_js" type="button" id="b_js" value="公式校验" onClick="fun16('formulavalidate')" class="btn_4"/>
				                </td></tr>
				                <tr height="20px"><td></td></tr>
				            </table>
			            </td>
			        </tr>
			       </table>
			      </td>
			    </tr>
			    <tr>
			      <td valign="top" align="left" width="100%" height="100%">
			          <textarea cols="90" rows="8" id="1formulavalidate1"></textarea>
			      </td>
			    </tr>
			    <tr style="border-top:0px;">
			    	<td valign="top" align="center">
			          <input type="button" name="Button" value="保存" onClick="fun16('saveformulavalidate')" class="btn_80"/>
			          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="button" name="Button" value="关闭" onClick="closeDiv2('logicalFormulaDiv')" class="btn_80"/>
			        </td></tr>
			  </table>
		</div>
		<div id="redForkDiv" class="redForkDiv">
		    <img id="redForkImg" src="drag/images/toolbox/redFork.png" width=8px height=8px onClick="fun16('redForkConfirm')"/>
		</div>
		<div id="redForkConfirm" class="redForkConfirm">
		    <table width="100%" class="redForkTable">
		        <tr>
		        	<td align="center"><button onClick="fun15()">删除</button></td>
		            <td align="center"><button onClick="fun51()">隐藏</button></td>
		            <td align="center"><button onClick="closeDiv2('redForkConfirm')">取消</button></td>
		        </tr>
		    </table>
		</div>
		<div id="div11" class="dragDiv11" onmouseout=""></div>
		<div id="new" style="height:100%; position:absolute; left:120px; top:0px;"></div>
		<form id="submitPropertyForm" action="FormDataForToolBean_insertFormElement.action" method="post">
		<input type="hidden" id="formId" value="<s:property value="formId"/>"/>
		<div id="popAlert" style="display:none;background:#e2ecf5" onmousedown="dragPro('popAlert')">		
			<div id="tab_div">
	          <span id="tab1_span" class="span" onclick="fun9(this)">基本信息</span>
	          <span id="tab2_span" class="span" onclick="fun9(this)">样式配置</span>
			  <span id="tab3_span" class="span" onclick="fun9(this)">事件配置</span>
			  <span id="tab4_span" class="span" onclick="fun9(this)">联动计算</span>
          </div>
		  <div id="tab1" class="tab_block">
		      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
			      <tr align="center">
			          <td align="right">标签名称&nbsp;</td>
			          <td align="left"><input type="text" title="标签名称" name="formActive.labelName"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"/></td>
			          <td align="right">字段名称&nbsp</td>
			          <td align="left"><input type="text" title="字段名称" name="formActive.fieldName"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"/></td>
			          <td align="right">字段类型&nbsp</td>
			          <td align="left">
			              <select name="formActive.fieldType" onmousedown="stopDarg()" onblur="dargBlur()">
			              <option value="1">文本字段</option>
						  <option value="2">选择域</option>
						  <option value="21">选择域(可选)</option>
						  <option value="22">选择域(手工)</option>
						  <option value="3">复选域</option>
						  <option value="31">自定复选域</option>
						  <option value="32">从库中复选域</option>
						  <option value="4">单选域</option>
						  <option value="41">单选域(手工)</option>
						  <option value="5">文本区域</option>
						  <option value="6">密码域</option>
						  <option value="7">从库中选择</option>
						  <option value="33">从库中选择(可选)</option>
						  <option value="8">自定义选择</option>
						  <option value="9">文件</option>
						  <option value="99">隐藏域</option>
						  </select>
			          </td>
			      </tr>
			      <tr>
			          <td align="right">行次<font color="#FF0000">*</font> </td>
			          <td align="left"><input type="text" title="行次" name="formActive.row"  maxlength="3" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>
			          <td align="right">标签列次&nbsp</td>
			          <td align="left"><input type="text" title="标签列次" name="formActive.labelCol"  maxlength="3" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>
			          <td align="right">字段列次&nbsp</td>
			          <td align="left"><input type="text" title="字段列次" name="formActive.fieldCol"  maxlength="3" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>
			      </tr>
			      <tr>
				      <td align="right">尺寸/选项&nbsp</td>
				      <td align="left"><input type="text" title="尺寸/选项" name="formActive.fieldSize" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				  	  <td align="right">单位&nbsp</td>
				      <td align="left"><input type="text" title="单位" name="formActive.unit"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				      <td align="right">数据类型&nbsp</td>
				      <td align="left">
				          <select name="formActive.dataType" title="数据类型" onchange="selectonchange()" onmousedown="stopDarg()" onblur="dargBlur()">
						  <option value=""></option>
						  <option value="0" selected>String</option>
						  <option value="1">Int</option>
						  <option value="2">Long</option>
						  <option value="3">Double(2)</option>
						  <option value="4">Float</option>
						  <option value="5">Boolean</option>
						  <option value="6">Date</option>
						  <option value="8">Double(4)</option>
						  <option value="9">Double(6)</option>
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
				  <tr>
				      <td align="right">是否只读&nbsp</td>
				      <td align="left">
				          <select name="formActive.readonly" title="是否只读" onmousedown="stopDarg()" onblur="dargBlur()">
				          <option value="" selected></option>
				          <option value="0">否</option>
				          <option value="1">是</option>
				          </select>
				      </td>
				      <td align="right" title="当段为选择域时，">是否必输&nbsp</td>
				      <td align="left" title="当段为选择域时，">
				          <select name="formActive.mustInput" title="是否必输" onmousedown="stopDarg()" onblur="dargBlur()">
				          <option value="" selected></option>
				          <option value="0">否</option>
				          <option value="1">是</option>
				          </select>
				      </td>
				  </tr>
				  <tr>
				      <td align="right">初值&nbsp</td>
				      <td align="left"><input type="text" title="初值" name="formActive.initValue"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				      <td align="right">最大长度&nbsp</td>
				      <td align="left"><input type="text" title="最大长度" name="formActive.maxLength"  maxlength="5" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				  </tr>
				  <tr>
				      <td align="right">字段说明&nbsp</td>
				      <td colspan="3" align="left"><input type="text" title="字段说明" name="formActive.alt"  size="40" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				   
				  </tr>
				  <tr>
				      <td align="right">参数&nbsp</td>
				      <td colspan="3" align="left"><input type="text" title="参数" name="formActive.para"  size="40" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				  </tr>
		      </table>
		      <table align="center" width="50%">
		          <tr>
		              <td><button type="button" onclick="fun14()">确定</button></td>
		              <td><button type="button" onclick="fun15()">删除</button></td>
		              <td><button type="button" onclick="closePopAlert()">关闭</button></td>
		          </tr>
		      </table>
		  </div>
		   <div id="tab2" class="tab_hide">
		      <table width="96%" border="0" align="center"  cellpadding="0" cellspacing="0">
                  <tr>
			          <td align="right">跨行数&nbsp</td>
			          <td align="left"><input type="text" title="跨行数" name="formActive.rowSpan"  maxlength="3" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>
			          <td align="right">标签跨列数&nbsp</td>
			          <td align="left"><input type="text" title="标签跨列数" name="formActive.labelColSpan"  maxlength="3" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>
			          <td align="right">字段跨列数&nbsp</td>
			          <td align="left"><input type="text" title="字段跨列数" name="formActive.fieldColSpan"  maxlength="3" size="5" onmousedown="stopDarg()" onblur="dargBlur()"></td>			         
			      </tr>
                  <tr>
                   <td align="right">定义范围&nbsp</td>
			          <td align="left">
				          <select name="formActive.selRange" title="定义范围" onmousedown="stopDarg()" onblur="dargBlur()">
				          <option value="0" selected>全部</option>
				          <option value="1">标签有效</option>
				          <option value="2">字段有效</option>
				          </select>
			          </td>
                   <td align="right">标签对齐&nbsp</td>
			          <td align="left">
			              <select name="formActive.labelAlign" title="标签对齐" onmousedown="stopDarg()" onblur="dargBlur()">
			              <option value="1">左对齐</option>
			              <option value="2">居中</option>
			              <option value="3" selected>右对齐</option>
			              </select>
			          </td>
			          <td align="right">字段对齐&nbsp</td>
			          <td align="left">
			              <select name="formActive.fieldAlign" title="字段对齐" onmousedown="stopDarg()" onblur="dargBlur()">
			              <option value="1" selected>左对齐</option>
			              <option value="2">居中</option>
			              <option value="3">右对齐</option>
			              </select>
			          </td>
                   </tr>
                   <tr>    
                   	  <td align="right">标签宽度&nbsp</td>
				      <td align="left"><input type="text" title="标签宽度" name="formActive.labelWidth"  maxlength="8" size="8" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				      <td align="right">标签样式&nbsp</td>
				      <td align="left"><input type="text" title="标签样式" name="formActive.labelStyle"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"></td>    
                   </tr>
                   <tr>   
               		  <td align="right">字段宽度&nbsp</td>
				      <td align="left"><input type="text" title="字段宽度" name="formActive.fieldWidth"  maxlength="3" size="8" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				      <td align="right">字段样式&nbsp</td>
				      <td align="left"><input type="text" title="字段样式" name="formActive.fieldStyle"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                   </tr>
                   <tr>  
              		 <td align="right">文字方向&nbsp</td>
				      <td align="left">
				          <select name="formActive.labelDirect" title="文字方向" onmousedown="stopDarg()" onblur="dargBlur()">
				          <option value="1">竖向</option>
				          <option value="0" selected>横向</option>
				          </select>
				      </td>
				      
				       <td align="right">文本域内容行数设置</td>
				      <td align="left"><input type="text" title="文本域内容行数设置" name="formActive.textAreaFieldRows"  size="16" onmousedown="stopDarg()" onblur="dargBlur()"></td>
			       </tr>
			       
			 
			       
              </table>
              <table align="center" width="50%">
		          <tr>
		              <td><button type="button" onclick="fun14()">确定</button></td>
		               <td><button type="button" onclick="fun15()">删除</button></td>
		              <td><button type="button" onclick="closePopAlert()">关闭</button></td>
		             
		          </tr>
		      </table>
		  </div>
		  <div id="tab3" class="tab_hide">
		      <table width="96%" border="0" align="center"  cellpadding="0" cellspacing="0">
                  <tr>
				      <td align="right">标签链接&nbsp</td>
				      <td colspan="3" align="left"><input type="text" title="标签链接" name="formActive.labelLink"  size="40" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				  </tr>
				  <tr>
				      <td align="right">按钮链接&nbsp</td>
				      <td colspan="3" align="left"><input type="text" title="按钮链接" name="formActive.buttonCondition"  size="40" onmousedown="stopDarg()" onblur="dargBlur()"></td>
				  </tr>
                  <tr>
                      <td align="right">点击事件&nbsp</td>
                      <td align="left"><input type="text" title="点击事件" name="formActive.onclick"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                      <td align="right">获得焦点&nbsp</td>
                      <td align="left"><input type="text" title="获得焦点" name="formActive.onfocus"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                  </tr>
                  <tr>
                      <td align="right" nowrap>内容变更事件&nbsp</td>
                      <td align="left"><input type="text" title="内容变更事件" name="formActive.onchange"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                      <td align="right" nowrap>焦点离开&nbsp</td>
                      <td align="left"><input type="text" title="焦点离开" name="formActive.onblur"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                  </tr>
                  <tr>
                      <td align="right">鼠标键按下&nbsp</td>
                      <td align="left"><input type="text" title="鼠标键按下" name="formActive.onmousedown"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                      <td align="right">键盘键松开&nbsp</td>
                      <td align="left"><input type="text" title="键盘键松开" name="formActive.onkeyup"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                  </tr>
                  <tr>
                      <td align="right">鼠标经过&nbsp</td>
                      <td align="left"><input type="text" title="鼠标经过" name="formActive.onkeypress"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                      <td align="right">键盘键按下&nbsp</td>
                      <td align="left"><input type="text" title="键盘键按下" name="formActive.onkeydown"  size="15" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                  </tr>
              </table>
              <table align="center" width="50%">
		          <tr>
		              <td><button type="button" onclick="fun14()">确定</button></td>
		               <td><button type="button" onclick="fun15()">删除</button></td>
		              <td><button type="button" onclick="closePopAlert()">关闭</button></td>
		             
		          </tr>
		      </table>
		  </div>
		  <div id="tab4" class="tab_hide">
		      <table width="96%" border="0" align="center"  cellpadding="0" cellspacing="0">
                  <tr>
                      <td align="right">事件类型&nbsp</td>
                      <td align="left">
                          <select name="formActive.calculationType" title="联动事件类型"  onmousedown="stopDarg()" onblur="dargBlur()">
                          <option value=""></option>
                          <option value="onBlur">onBlur</option>
                          <option value="onClick">onClick</option>
                          <option value="onFocus">onFocus</option>
                          <option value="onChange">onChange</option>
                          </select>
                      </td>
                  </tr>
                  <tr>
                      <td align="right">联动计算公式&nbsp;</td>
                      <td colspan="5" align="left"><input type="text" name="formActive.calculation" size="50" readOnly="true" onfocus="fun16('linkageCalculationDiv')" onmousedown="stopDarg()" onblur="dargBlur()"></td>
                  </tr>
              </table>
              <div id="linkageCalculationDiv" class="linkageCalculationDiv" style="display:none">
                  <table width="550px" border="0" cellpadding="0" cellspacing="1" style="border:#CAECD4;" bgcolor="#EAEAEA" align="center">
				    <tr>
				      <td valign="top">
				        <table width="99%" border="1" align="center" cellpadding="5" cellspacing="0">
				          <tr> 
				              <td align="center"><select id="lCformElementSelect"></select></td>
				               <td>
				                <input name="b_insert" type="button" id="b_insert2" value="插入" onClick="fun21('lCformElementSelect')" class="btn_80" onmousedown="stopDarg()" onblur="dargBlur()"/>
				              </td>
				          </tr>
				          <tr height="15px"><td colspan="2"></td></tr>
				        </table>
				      </td>
				     </tr>
				     <tr>
				      <td width="100%" valign="top" align="center">
				      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				          <tr>
				            <td width="80%" align="center">
				            	<table width="80%"  border="1" align="center" cellpadding="0" cellspacing="1">
									<tr align="center" bgcolor="#CAECD4" height="30px"><!-- line 1 -->
										<td width="62px" onClick="fun23('&')" align="center">&</td>
										<td width="62px" onClick="fun23('|')" align="center">|</td>
										<td width="62px" onClick="fun23('!')" align="center">!</td>
						                <td width="62px" onClick="fun23('%')" align="center">%</td>
						                <td width="62px" onClick="fun23('>')" align="center">&gt;</td>
										<td width="62px" onClick="fun23('(')" align="center">(</td>
										<td width="62px" onClick="fun23(')')" align="center">)</td>
									</tr>
									<tr align="center" bgcolor="#CAECD4" height="30px">
					                  <td width="62px" onClick="fun23('7')" align="center">7</td>
					                  <td width="62px" onClick="fun23('8')" align="center">8</td>
					                  <td width="62px" onClick="fun23('9')" align="center">9</td>
					                  <td width="62px" onClick="fun23('/')" align="center">/</td>
					                  <td width="62px" onClick="fun23('<')" align="center">&lt;</td>
									  <td width="62px" onClick="fun23('[')" align="center">[</td>
									  <td width="62px" onClick="fun23(']')" align="center">]</td>
									</tr>
									<tr align="center" bgcolor="#CAECD4" height="30px">
					                  <td width="62px" onClick="fun23('4')" align="center">4</td>
					                  <td width="62px" onClick="fun23('5')" align="center">5</td>
					                  <td width="62px" onClick="fun23('6')" align="center">6</td>
					                  <td width="62px" onClick="fun23('*')" align="center">*</td>
					                  <td width="62px" onClick="fun23('>=')" align="center">≥</td>
									  <td width="62px" onClick="fun23('{')" align="center">{</td>
									  <td width="62px" onClick="fun23('}')" align="center">}</td>
									</tr>
									<tr align="center" bgcolor="#CAECD4" height="30px">
					                  <td width="62px" onClick="fun23('1')" align="center">1</td>
					                  <td width="62px" onClick="fun23('2')" align="center">2</td>
					                  <td width="62px" onClick="fun23('3')" align="center">3</td>
					                  <td width="62px" onClick="fun23('-')" align="center">-</td>
					                  <td width="62px" onClick="fun23('<=')" align="center">≤</td>
									  <td colspan="2" width="124px" onClick="fun23('$txDt')" align="center">营业日期</td>
									</tr>
									<tr align="center" bgcolor="#CAECD4" align="center" height="30px">
									  <td colspan="2" width="124px" onClick="fun23('0')" align="center">0</td>
									  <td width="62px" onClick="fun23('.')" align="center">.</td>
									  <td width="62px" onClick="fun23('+')" align="center">+</td>
									  <td width="62px" onClick="fun23('=')" align="center">=</td>
					                  <td width="62px" onClick="fun23(';')"  align="center">;</td>
					                  <td width="62px" onClick="fun24('linkageCalculation')" align="center">←</td>
									</tr>
								</table>
							</td>
							<!-- 
				            <td width="20%">
				           	 	<table width="100%">
					           	 	<tr align="left"><td>
					                	<input name="b_js" type="button" id="b_js" value="公式校验" onClick="cLFormulavalidate()" class="btn_4"/>
					                </td></tr>
					                <tr height="20px"><td></td></tr>
					            </table>
				            </td>
				            -->
				        </tr>
				       </table>
				      </td>
				    </tr>
				    <tr>
				      <td valign="top" align="left" width="100%" height="100%">
				        <textarea name="linkageCalculation" cols="90" rows="8" id="linkageCalculation"></textarea>
				      </td>
				    </tr>
				    <tr style="border-top:0px;">
				    	<td valign="top" align="center">
				          <input type="button" name="Button" value="完成配置" onClick="fun17()" class="btn_4"/>
				          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          <input type="button" name="Button" value="关闭" onClick="closeDiv2('linkageCalculationDiv')" class="btn_80"/>
				        </td>
				    </tr>
				  </table>
              </div>
              <table align="center" width="50%">
		          <tr>
		              <td><button type="button" onclick="fun14()">确定</button></td>
		               <td><button type="button" onclick="fun15()">删除</button></td>
		              <td><button type="button" onclick="closePopAlert()">关闭</button></td>
		             
		          </tr>
		      </table>
		  </div>
		</div>
		</form>
		<div id="tempDiv" onselectstart="return false" style="cursor: hand; position: absolute; border: 1px solid black; background-color: #cccccc; display: none">
		</div>
	</body>
</html>
