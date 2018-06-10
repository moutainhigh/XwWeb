	//jquery与dwr.util冲突处理
	var jq = jQuery.noConflict();
	//报表类型下拉列表change响应事件
	function func_query() {
		var model_id = jq("input[type=text][name=model_id]").val();
		if( model_id == "" ) {
			sAlert("请选择模板编号!");
			return false;
		}
		var key_name = jq("input[type=text][name=key_name]").val();
		if( key_name == "" ) {
			sAlert("请配置元素列!");
			return false;
		}
		
		$.post("ProdDicAction_getProdDicListForQuery.action", 
				{ model_id: model_id, key_name: key_name },     
				function(data) {
					if(data != null) {
						var pos = data.indexOf("}||{");
						var parm_dic_result = data.substr(0,(pos+1));
						var prod_dic_result = data.substr((pos+3));
						fillLeftTable(parm_dic_result);
						fillRightTable(prod_dic_result);
					} else {
						jq("#prod_parm_body").empty();
					}
				}
		);
	}
	//初始化left_table数据行
	function fillLeftTable(parm_dic_result) {
		if( parm_dic_result == "{}" ) {
			jq("#prod_parm_body").empty();
			return false;
		}
		var results = eval("["+parm_dic_result+"]");//把json串转化为js对象
		var length = results.length;
		var strHtml = "", key_name = "",opt_code="",opt_name="", seqn;
		for(var i = 0; i < length; i++) {
			seqn = (1000 + parseInt(results[i].seqn));//防止与right_table.tr中id重复
			opt_code = results[i].opt_code;
			opt_name = results[i].opt_name;
			var style='';
			if((i+1)%2==0){
				style='background-color:#F3FAF3;height:21px; line-height:21px;'
			}else{
				style='background-color:#fff;height:21px; line-height:21px;';
			}
			strHtml += "<tr id=\"left_tr_" + seqn + "\" style="+style+" >";
			strHtml += "<td align='center'><input type=\"checkbox\" id=\"left_checkbox_";
			strHtml += seqn + "\" value='" + seqn + "' /></td>";
			strHtml += "<td id=\"left_td_" + seqn + "\" align='center' onclick=\"left_td_click(this)\">";
			strHtml += "" + opt_code + "</td>";//要素编码
			strHtml += "<td onclick=\"left_td_click(this)\">";
			strHtml += "" + opt_name + "</td>";//要素中文名
			strHtml += "<input type=\"hidden\" id=\"left_col_name_"+seqn+"\" value='"+opt_name+"' />";//要素名
			strHtml += "</tr>";
		}
		jq("#prod_parm_body").html(strHtml);
	}
	
	//初始化right_table数据行
	function fillRightTable(prod_dic_result) {
		if( prod_dic_result == "{}" ) {
			jq("#prod_item_body").empty();
			return false;
		}
		var results = eval("["+prod_dic_result+"]");
		var length = results.length;
		var strHtml = "", key_name = "",opt_code="",opt_name="", seqn;
		for(var i = 0; i < length; i++) {
			seqn = results[i].seqn;
			key_name = results[i].key_name;
			opt_code = results[i].opt_code;
			opt_name = results[i].opt_name;
			var style='';
			if((i+1)%2==0){//设置行背景色
				style='background-color:#F3FAF3;'
			}else{
				style='background-color:#fff;';
			}
	    	strHtml += "<tr id=\"right_tr_" + seqn + "\" style="+style+">";
			strHtml += "<td   align=\"center\"><input type=\"checkbox\" id=\"right_checkbox_";
			strHtml += seqn + "\" value='" + seqn + "'/></td>";
			strHtml += "<td id=\"right_td_" + seqn + "\" onclick=\"right_td_click(this)\" align=\"center\">" + opt_code+"</td>";//要素中文名称
			strHtml += "<td   align=\"center\">" + opt_name + "</td>";//中文名
			strHtml += "<td   align=\"center\">" + seqn + "</td>";//行次
			strHtml += "<input type=\"hidden\" id=\"right_col_code_"+seqn+"\" value='"+opt_code+"' />";//要素名
			strHtml += "<input type=\"hidden\" id=\"right_col_name_"+seqn+"\" value='"+opt_name+"' />";//要素名
			strHtml += "</tr>";//组号
		}
		jq("#prod_item_body").html(strHtml);
	}	
	
	//左边模板中单元格单击响应事件
	function left_td_click(obj) {
		var hasSelected = !(jq(obj).parents("tr").find(":checkbox").attr("checked"));
		if(hasSelected) {
			jq(obj).parents("tr").children().removeClass("TDstyle01");
			jq(obj).parents("tr").css("background-color","#cccccc");
			jq(obj).parents("tr").css("font-size","12px");
		} else {
			jq(obj).parents("tr").children().addClass("TDstyle01");
		}
		jq(obj).parents("tr").find(":checkbox").attr("checked",hasSelected);
	}
	
	//右边模型中单元格单击响应事件
	function right_td_click(obj) {
		var hasSelected = !(jq(obj).parents("tr").find(":checkbox").attr("checked"));
		if(hasSelected) {
			jq(obj).parents("tr").children().removeClass("TDstyle01");
			jq(obj).parents("tr").css("background-color","#cccccc");
			jq(obj).parents("tr").css("font-size","12px");
		} else {
			jq(obj).parents("tr").children().addClass("TDstyle01");
		}
		jq(obj).parents("tr").find(":checkbox").attr("checked",hasSelected);
	}	
	
	//左边模板中复选框响应事件
	function left_checkbox_click(obj,group_no) {
		var tr_obj = jq(obj).parents("tr");
		var hasSelected = obj.checked;
		if( group_no != ""&&group_no!="null" ) {
			jq(("input[type=hidden][name=left_group_"+group_no+"]")).each(
				function() {
					var tr_obj = jq(this).parents("tr");
					if(hasSelected) {
						jq(tr_obj).children().removeClass("TDstyle01");
						jq(tr_obj).css("background-color","#cccccc");
						jq(tr_obj).css("font-size","12px");
					} else {
						jq(tr_obj).children().addClass("TDstyle01");
						jq(tr_obj).css("font-size","12px");
					}
					jq(tr_obj).find(":checkbox").attr("checked",hasSelected);
				}
			);
		} else {
			if(hasSelected) {
			jq(tr_obj).children().removeClass("TDstyle01");
			jq(tr_obj).css("background-color","#cccccc");
			jq(obj).parents("tr").css("font-size","12px");
			} else {
				jq(tr_obj).children().addClass("TDstyle01");
			}
			obj.checked=hasSelected;
		}
	}
	
	//右边模型中复选框响应事件
	function right_checkbox_click(obj,group_no) {
		var tr_obj = jq(obj).parents("tr");
		var hasSelected = obj.checked;
		if( group_no != ""&&group_no!="null" ) {
			jq(("input[type=hidden][name=right_group_"+group_no+"]")).each(
				function() {
					var tr_obj = jq(this).parents("tr");
					if(hasSelected) {
						jq(tr_obj).children().removeClass("TDstyle01");
						jq(tr_obj).css("background-color","#cccccc");
						jq(tr_obj).css("font-size","12px");
					} else {
						jq(tr_obj).children().addClass("TDstyle01");
						jq(tr_obj).css("font-size","12px");
					}
					jq(tr_obj).find(":checkbox").attr("checked",hasSelected);
				}
			);
		} else {	
			if(hasSelected) {
				jq(tr_obj).children().removeClass("TDstyle01");
				jq(tr_obj).css("background-color","#cccccc");
				jq(obj).parents("tr").css("font-size","12px");
			} else {
				jq(tr_obj).children().addClass("TDstyle01");
			}
			obj.checked=hasSelected;
		}
	}	
	
	//向右移动选中模板
	function func_toright() {
		var total = jq("input[type=checkbox][id*=left_checkbox_][checked]").length;
    	if(total <= 0) {
      		sAlert("请选择一条记录");
	      	return false;
	    }
	    jq("input[type=checkbox][id*=left_checkbox_][checked]").each(function() {
	    	var left_tr = jq(this).parents("tr");
	    	//读取所选择行的seqn和col_name
	    	var seqn = jq(this).val();
	    	var opt_code = jq(("#left_td_"+seqn)).text();
	    	var opt_name = jq("input[type=hidden][id=left_col_name_"+seqn+"]").val();
	    	//追加到right_table中
	    	var strHtml = "";
	    	strHtml += "<tr id=\"right_tr_" + seqn + "\" >";
			strHtml += "<td class=\"TDstyle01\" align=\"center\"><input type=\"checkbox\" id=\"right_checkbox_";
			strHtml += seqn + "\" value='" + seqn + "'/></td>";
			strHtml += "<td id=\"right_td_" + seqn + "\" class=\"TDstyle01\" align=\"center\" onclick=\"right_td_click(this)\">" + opt_code + "</td>";//要素中文名称
			strHtml += "<td class=\"TDstyle01\" align=\"center\">" + opt_name + "</td>";//可否为空
			strHtml += "<td class=\"TDstyle01\" align=\"center\">&nbsp;</td>";//行次
			strHtml += "<input type=\"hidden\" id=\"right_col_code_"+seqn+"\" value='"+opt_code+"' />";//要素名
			strHtml += "<input type=\"hidden\" id=\"right_col_name_"+seqn+"\" value='"+opt_name+"' />";//要素名
			strHtml += "</tr>";//组
	    	jq("#prod_item_body").append(strHtml);
	    	//删除所选择行
	    	jq(left_tr).remove();
	    	reorg_table("prod_item_body");
	    });
	}
	
	//向左移动选中模板
	function func_toleft() {
		var total = jq("input[type=checkbox][id*=right_checkbox_][checked]").length;
    	if(total <= 0) {
      		sAlert("请选择一条记录");
	      	return false;
	    }
	    jq("input[type=checkbox][id*=right_checkbox_][checked]").each(function() {
	    	var right_tr = jq(this).parents("tr");
	    	//读取所选择行的seqn和col_name
	    	var seqn = jq(this).val();
	    	var opt_code = jq(("#right_td_"+seqn)).text();
	    	var opt_name = jq("input[type=hidden][id=right_col_name_"+seqn+"]").val();
	    	//追加到left_table中
	    	var strHtml = "";
	    	strHtml += "<tr id=\"left_tr_" + seqn + "\" >";
			strHtml += "<td class=\"TDstyle01\" align='center'><input type=\"checkbox\" id=\"left_checkbox_";
			strHtml += seqn + "\" value='" + seqn + "'/></td>";
			strHtml += "<td id=\"left_td_" + seqn + "\" class=\"TDstyle01\" align='center' onclick=\"left_td_click(this)\">" + opt_code + "</td>";//要素中文名
			strHtml += "<td class=\"TDstyle01\">" + opt_name + "</td>";//要素中文名
			strHtml += "<input type=\"hidden\" id=\"left_col_name_"+seqn+"\" value='"+opt_name+"' />";//要素名
			strHtml += "</tr>";//组
			jq("#prod_parm_body").append(strHtml);
	    	//删除所选择行
	    	jq(right_tr).remove();
	    	reorg_table("prod_item_body");
	    });
	}
	
	//向上移动选中行
	function func_moveup() {
		var total = jq("input[type=checkbox][id*=right_checkbox_][checked]").length;
    	if(total <= 0) {
      		sAlert("请选择一条记录");
	      	return false;
	    }
	    if( total > 1 ) {
	    	sAlert("对不起，只能选择一行记录移动位置！");
	      	return false;
	    }
	    var checkbox_obj = (jq("input[type=checkbox][id*=right_checkbox_][checked]"))[0];
	    moveUp(checkbox_obj);
	}
	
	//向下移动选中行
	function func_movedown() {
		var total = jq("input[type=checkbox][id*=right_checkbox_][checked]").length;
    	if(total <= 0) {
      		sAlert("请选择一条记录");
	      	return false;
	    }
	    if( total > 1 ) {
	    	sAlert("对不起，只能选择一行记录移动位置！");
	      	return false;
	    }
	    var checkbox_obj = (jq("input[type=checkbox][id*=right_checkbox_][checked]"))[0];
	    moveDown(checkbox_obj);
	}	
	
	//使表格行上移，接收参数为单元格内的对象(当前是复选框)
	function moveUp(checkbox_obj) {
		//通过复选框对象获取表格行的引用
 		var tr_obj=checkbox_obj.parentNode.parentNode;
 		//如果不是第一行，则与上一行交换顺序
 		if(tr_obj.previousSibling)swapNode(tr_obj,tr_obj.previousSibling);
	}
	
	//使表格行下移，接收参数为单元格内的对象(当前是复选框)
	function moveDown(checkbox_obj) {
 		//通过复选框对象获取表格行的引用
 		var tr_obj=checkbox_obj.parentNode.parentNode;
 		//如果不是最后一行，则与下一行交换顺序
 		if(tr_obj.nextSibling)swapNode(tr_obj,tr_obj.nextSibling);
	}
	
	//定义通用的函数交换两个结点的位置
	function swapNode(node1,node2) {
 		//获取父结点
		var _parent=node1.parentNode;
		//获取两个结点的相对位置
		var _t1=node1.nextSibling;
		var _t2=node2.nextSibling;
		//将node2插入到原来node1的位置
		if(_t1)_parent.insertBefore(node2,_t1);
		else _parent.appendChild(node2);
		//将node1插入到原来node2的位置
		if(_t2)_parent.insertBefore(node1,_t2);
		else _parent.appendChild(node1);
		reorg_table("prod_item_body");
	}
	
	//用于产品基础要素配置之后的保存（保存后并不跳到下一个配置页面而是结束此次配置）
	function func_save() {
		var model_id = jq("input[type=text][name=model_id]").val();
		if(model_id == "") {
      		sAlert("请选择模板编号!");
	      	return false;
	    }
	    var key_name = jq("input[type=text][name=key_name]").val();
		if( key_name == "" ) {
			sAlert("请选择要素名称!");
			return false;
		}
		var total = jq("input[type=checkbox][id*=right_checkbox_]").length;
    	if(total <= 0) {
      		sAlert("请从左边表格中选择记录创建产品模型!");
	      	return false;
	    }
	    var seqn_number = 1;
	    var opt_code = "", opt_name = "";
	    var str_seqn = "", str_opt_code = "", str_opt_name = "";
	    var select_obj;
	    jq("input[type=checkbox][id*=right_checkbox_]").each(function() {
	    	var right_tr = jq(this).parents("tr");
	    	//读取所选择行的seqn和col_name
	    	seqn = jq(this).val();
	    	opt_code = jq("input[type=hidden][id=right_col_code_"+seqn+"]").val();;
	    	str_opt_code += opt_code + ",";
	    	//要素名
	    	opt_name = jq("input[type=hidden][id=right_col_name_"+seqn+"]").val();
	    	str_opt_name += opt_name + ",";
	    	//拼行次
	    	str_seqn += seqn_number + ",";
	    	seqn_number++;
	    });
	    jq("#prodDic_model_id").val(model_id);
	    jq("#prodDic_key_name").val(key_name);
	    jq("#prodDic_opt_code").val(str_opt_code);
	    jq("#prodDic_opt_name").val(str_opt_name);
	    jq("#prodDic_seqnString").val(str_seqn);
	    
	    document.operform.action = "ProdDicAction_insertOrUpdate.action";
	    document.operform.submit();
	}
	
	//遍历right_table(tbody)中的行，并重新生成行次
	function reorg_table(obj_id) {
		jq(("#"+obj_id+">tr")).each(function() {
	    	var row_number = (jq(this)[0]).rowIndex;
	    	jq(this).find("td:eq(3)").html(row_number);
	    });
	}
	
