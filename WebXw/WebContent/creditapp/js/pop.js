function func_reset(lk){
	if( lk!=null && lk!="" ){
		if( lk.indexOf(",") > -1 ){
			var arr = lk.split(",");
			for( var i=0;i<arr.length;i++ ){
				document.getElementsByName(arr[i])[0].value="";
			}
		}else{
			document.getElementsByName(lk)[0].value="";
		}
	}else{
		return false;
	}
}
function showDialog(url,width,height){
	if(!width)width = screen.availWidth;
	if(!height) height = screen.availHeight-30;
	if (url.indexOf('?') != -1){
		url+='&';
	}else{
		url+='?';
	}
	url+="isDialog=1";
	var sFeatures;
    if (window.showModalDialog) {
        sFeatures = "dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogLeft=0px;dialogTop=0px;center=no;resizable=no;scrollbars=no;status:yes;help:no;";
        var returnResult = window.showModalDialog(url, window, sFeatures);
        if (returnResult) {
            return returnResult;
        }
    } else {
        sFeatures = "status=no,width="+width+"px,height="+height+"px,menubar=no,scrollbars=no";
       // window.margs = vArguments;
        window.open(url, "_blank", sFeatures);
    }
}
function showViewDialog(url){
	showNewDailogWindow(url,true,null,null,null,"dialogWidth=60;dialogHeight=32;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
	//window.showModalDialog(url,window,"dialogWidth=60;dialogHeight=32;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
	/*
	var width = screen.availWidth;
	var height = screen.availHeight-30;
	
	var sFeatures;
    if (window.showModalDialog) {
        sFeatures = "dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogLeft=0px;dialogTop=0px;center=no;resizable=no;scrollbars=no;status:yes;help:no;";
        var returnResult = window.showModalDialog(url, window, sFeatures);
        if (returnResult) {
            return returnResult;
        }
    } else {
        sFeatures = "status=no,width="+width+"px,height="+height+"px,menubar=no,scrollbars=no";
        window.open(url, "_blank", sFeatures);
    }
    */
	
	
}
function showViewDialogSmall(url){
	showNewDailogWindow(url,true,null,null,null,"dialogWidth=40;dialogHeight=15;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
}
function showDialog2(url,width,height){
	if(!width)width = screen.availWidth;
	if(!height) height = screen.availHeight-30;
	
	var sFeatures;
    if (window.showModalDialog) {
        sFeatures = "dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogLeft=0px;dialogTop=0px;center=no;resizable=no;scrollbars=no;status:yes;help:no;";
        var returnResult = window.showModalDialog(url, window, sFeatures);
        if (returnResult) {
            return returnResult;
        }
    } else {
        sFeatures = "status=no,width="+width+"px,height="+height+"px,menubar=no,scrollbars=no";
       // window.margs = vArguments;
        window.open(url, "_blank", sFeatures);
    }
}
function openWindow(url,width,height,isReload){
	if(!width)width = screen.availWidth-10;
	if(!height) height = screen.availHeight - 40;
	var iTop =Math.abs((screen.availHeight-40-height))/2; //获得窗口的垂直位置;
	var iLeft = Math.abs(screen.availWidth-10-width)/2; //获得窗口的水平位置;
	
	var sFeatures = "status=no,width="+width+"px,height="+height+"px,top="+iTop+"px,left="+iLeft+"px,menubar=no,scrollbars=no,alwaysLowered=yes;resizable=yes";
    var winObj = window.open(url, "_blank", sFeatures);
    if(isReload){
    	var loop = setInterval(function(){
        	if(winObj.closed){
        		clearInterval(loop);
        		window.location.reload();
        	}
        });
    }
}

function openWindowForRpt(url){
	var sFeatures = "height=800, width=1000, top=0, left=0, resizable=yes, scrollbars=yes, alwaysLowered=yes,menubar=no,status=no";
    window.open(url, "_blank", sFeatures);
}
//客户信息
function funcCifPop(parNames, popNames,flag) {
	var url = "CifOpRelAction_findCifPop.action?parNames=" + parNames
			+ "&popNames=" + popNames;
	showDialog(url);
}

//查询CifCorpInfpop
function funcCorpPop(parNames, popNames) {
	var url = "CifCorpInfAction_findCorpPop.action?parNames=" + parNames
	+ "&popNames=" + popNames;
	showDialog(url);
}


//保证人客户信息
function funcPromPop(parNames, popNames) {
    var url = "CifOpRelAction_findPromPop.action?parNames=" + parNames + "&popNames=" + popNames;
    showDialog(url);
}

//抵押人客户信息
function funcGawnPop(parNames, popNames) {
    var url = "CifOpRelAction_findGawnPop.action?parNames=" + parNames + "&popNames=" + popNames;
    
    showDialog(url);
}

//质押人客户信息
function funcPawnPop(parNames, popNames) {
    var url = "CifOpRelAction_findPawnPop.action?parNames=" + parNames + "&popNames=" + popNames;
    showDialog(url);
}
//详情页面点客户号弹出客户详情
function showCifDetail(str) {
	var url = "CifBaseAction_getDetail.action?" + str.split("?")[1];
	showDialog(url);
}
//详情页面点客户号弹出客户详情	担保专用
function showCifDetailForGrt(lk) {
	var cifNo;
	if(lk.split("=")[1]==""){
		cifNo = document.getElementsByName("cifNo")[0].value;
		if(cifNo==""){
			sAlert("请先选择客户！");
			return false;
		}else{
			var url = "CifBaseAction_getDetail.action?cifNo=" + cifNo;
			showDialog(url);
		}
	}else{
		var url = "CifBaseAction_getDetail.action" + lk.split("#")[1];
		showDialog(url);
	}
}

//查询所有客户经理信息--wanggang 20150618
function showMangNoPop(param) {
    var url = "SysUserAction_findMangNoPop.action?param=" + param;
    window.showModalDialog(url,window,"dialogWidth=55;dialogHeight=32;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
}


function funcSupPrdPop(parNames, popNames) {
	var url = "PrdModelAction_findPrdModelPopForDeal.action?parNames=" + parNames
	+ "&popNames=" + popNames;
	showDialog(url);
}
function funcPrdBasePop(parNames, popNames) {
    var url = "PrdBaseAction_findPop.action?";
    url = url + "parNames=" + parNames + "&popNames=" + popNames;
    showDialog(url);
}

//工作流pop start
function funcWkfApprovalRolePop(parNames, popNames) {
	var url = "WkfApprovalRoleAction_findByPagePop.action?parNames=" + parNames+ "&popNames=" + popNames;
	showNewDailogWindow(url);
	//showDialog(url);
}
function funcWkfPop(obj)
{
	var wfId=selectProcess();
	if(null!=wfId&&""!=wfId&&"null"!=wfId)
	{
		obj.value=wfId;
	}
}
function funcWkfApprovalUserPop(parNames, popNames,taskId) {
	var url = "WkfApprovalUserAction_findByPagePop.action?parNames=" + parNames+ "&popNames=" + popNames+"&taskId="+taskId;
	showDialog(url);
}
function funcWkfApprovalUserMapPop(parNames, popNames) {
	var url = "WkfApprovalUserAction_findByPageMapPop.action?parNames=" + parNames+ "&popNames=" + popNames;
	showDialog(url);
}
function funcWkfApprovalUserForReAssignPop(parNames, popNames,taskId) {
	var url = "WkfApprovalUserAction_findByPagePop.action?parNames=" + parNames+ "&popNames=" + popNames+"&taskId="+taskId;
	showDialog(url);
}
//工作流pop end

//用户pop
function funcTblOrgUserPop(parNames, popNames){
	var url = "TblOrgUserAction_findByPageForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	
	showDialog(url);
}

//选择风险拦截项目,查询拦截项目编号
function funcRewPreventRiskPop(parNames, popNames) {
    var url = "RewPreventRiskAction_findByPageForPop.action?parNames=" + parNames + "&popNames=" + popNames;
    showDialog(url);
}
//风险预警配置,查询模板参数
function funcRewTempParmPop(parNames, popNames, schemeId) {
    var schemeId = document.getElementsByName("schemeId")[0].value;
    var url = "RewTempParmAction_findByPage.action?parNames=" + parNames + "&popNames=" + popNames + "&schemeId=" + schemeId;
    showDialog(url);
}
//风险预警配置,配置风险阀值
function funcRewThresholdPop(parNames, popNames, schemeId) {
    var schemeId = document.getElementsByName("schemeId")[0].value;
    var url = "RewThresholdAction_findByPage.action?parNames=" + parNames + "&popNames=" + popNames + "&schemeId=" + schemeId;
    showDialog(url);
}
//风险拦截配置,新增拦截项目,查询预警方案名称,编号
function funcRewSchemePop(parNames, popNames, schemeId) {
    var url = "RewSchemeAction_temp.action?parNames=" + parNames + "&popNames=" + popNames;
    showDialog(url);
}

//pop返回调用的方法
function funcPopReturn(lk) {
    var parNames = document.getElementsByName('parNames')[0].value.split(',');
    var popNames = document.getElementsByName('popNames')[0].value.split(',');
    var obj = document.getElementsByName('returnMethod')[0];
    var returnMethod = "";
    if (typeof(obj) != "undefined") {
        returnMethod = obj.value;
    }
    var temps = lk.split("?");
    var pars = temps[1];
    var strs = pars.split("&");
	if(window.dialogArguments == null){//使用window.open的方式开启新窗口时取得父窗口
		var doc = window.opener.document;
	}else{//使用window.showModalDialog的方式开启新窗口时取得父窗口
		var doc = dialogArguments.document;
	}
    
    for (var i = 0; i < strs.length; i++) {
        for (var j = 0; j < popNames.length; j++) {
            var ss = strs[i].split('=');
            var name = ss[0];
            if (name == popNames[j]) {
                var obj = doc.getElementsByName(parNames[j])[0];
                
                obj.value = ss[1];
                
                obj.readonly = true;
            }
        }
    }
    if (typeof(returnMethod) != "undefined" && returnMethod != "") {
        dialogArguments[returnMethod]();
    }
    window.close();
}

function func_cif_pop(url){
		//window.showModalDialog(url, window, "dialogWidth="+(screen.availWidth)+";dialogHeight="+(screen.availHeight-30)+";center:yes;resizable=no;scrollbars=no;status:no;help:no;location:yes");
		//window.open(url,"window","status:no;help:no;border:thin;statusbar:no,left=0,top=0,resizable=yes,width="+(screen.availWidth)+"px,height="+(screen.availHeight-30)+"px");
		openWindow(url);
}
	
//业务模块pop
function funcBusiModulePop(){
	var module_type = document.getElementsByName("module_type")[0].value;
	var url = "BusiModuleAction_findForPop.action?module_type=" + module_type;
	window.showModalDialog(url, window, "dialogWidth=60;dialogHeight=30;resizable=no;scrollbars=no;status:no;help:no;");
}
//业务子模型pop
function funcBusiSubModelPop(){
	var module_type = document.getElementsByName("module_type")[0].value;
	var url = "BusiSubModelAction_findForPop.action?module_type=" + module_type;
	window.showModalDialog(url, window, "dialogWidth=60;dialogHeight=30;resizable=no;scrollbars=no;status:no;help:no;");
}
/**
*	通过传入的参数获取该参数的值，都为空给与提示
*/
function returnParms(query_parm){
	var isEmpty = true; 
	var val = "";
	var parms = "";
	if( query_parm.indexOf(",")>0 ){//where 后面有多个查询条件的
		var arr = query_parm.split(",");
		for( var i=0;i<arr.length;i++ ){
			if( arr[i].indexOf("@")>0 ){
				val = document.getElementsByName(arr[i].split("@")[0])[0].value ;
			}else{
				val = document.getElementsByName(arr[i])[0].value ;
			}
			if( val != null && val != "" && typeof(val)!='undefined' ){
				val = val.replace(/\,/g,"");
				parms += arr[i]+"="+val ;
				parms += ",";
				isEmpty=false;
				if(isHadChineseChart(val)){
					var len = getStrLength(val);
					if(len < 4){
						sAlert("请输入最少两个汉字进行查询!");
						return false;
					}
				}
			}
		}
		if( isEmpty ){
			//sAlert("请在相应页面属性输入值后，再点击放大镜查询!");
			//return false;
		}
	}else {//只有一个查询条件的
		if( query_parm != "" ){
			if( query_parm.indexOf("@")>0 ){
				val = document.getElementsByName(query_parm.split("@")[0])[0].value ;
			}else{
				val = document.getElementsByName(query_parm)[0].value ;
				val = val.replace(/\,/g,"");
			}
			if( val == "" ){
				//sAlert("请在相应页面属性输入值后，再点击放大镜查询!");
				//return false;
			}else{
				parms = query_parm + "=" + val;
			}
		}
	}
	return parms;
}

function isHadChineseChart(str){
	if(/.*[\u4e00-\u9fa5]+.*$/.test(str)){
		return true;
	}
	return false;
}

function getStrLength(str){
	if(str == null)return 0;
	if(typeof str != "string"){
		str += "";
	}
	return str.replace(/[^\x00-\xff]/g,"01").length;
}

/** 公用pop弹出页（单选框）
*	参数1标示场景ID
*   参数2表示要从页面传的值即SQL语句where后面的条件
**/
function func_popRadio(scene_id,query_parm,callFun){
	var parms = returnParms(query_parm);
	var returnVal;
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"PopParmConfAction_findByPop.action", //请求路径
	    cache: false, 
	    async: false,
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       		var db_rel = json.rel;
       		var sql = json.sql;
       		var col_name = json.col_name;
       		var size = json.size;
       		var query_name = json.query_name;
       		var disName = json.disName;
       		var pageNo = json.pageNo;
       		var query_sql= json.query_sql;
       		var hidden_col = json.hidden_col;
       		var if_query = json.if_query;
       		sql = sql.replace(/\%/g,"@");
       		sql = sql.replace(/\?/g,"%3F");
       		if( query_sql!=null ){
       			query_sql = query_sql.replace(/\%/g,"@");
       			query_sql = query_sql.replace(/\?/g,"%3F");
       		}
       		var url = "creditapp/dev/Pop_frame.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+
       					"&size="+size+"&query_name="+query_name+"&disName="+disName+"&pageNo="+pageNo+"&scene_id="+scene_id+
       					"&query_sql="+query_sql+"&hidden_col="+hidden_col+"&if_query="+if_query;
       		
       		var returnVal;
       		var agent = navigator.userAgent.toLowerCase();
       		if (agent.indexOf("chrome") > 0 || agent.indexOf("firefox") > 0) {
       			var width = screen.availWidth;
       			var height = screen.availHeight;
       			var sFeatures = "status=no,width="+width+"px,height="+height+"px,top=0px,left=0px,menubar=no,scrollbars=no,alwaysLowered=yes;resizable=yes";
       		    var winObj = window.open(url, "_blank", sFeatures);
       		    var loop = setInterval(function(){
		        	if(winObj.closed){
		        		clearInterval(loop);
		        		if(typeof callFun == "function"){
		           			callFun.call();
		           		}
		        	}
		        });
    		}else{
           		returnVal = window.showModalDialog(url,window,"dialogWidth=70;dialogHeight=38;center:yes;help:no;minimize:no;maximize:no;border:thin;status:no");
           		callFun.call();
    		}
	    }
    });
	return returnVal;
}
function func_popTzList(scene_id,query_parm){
	var parms = '';
	if('TZ34'==scene_id ||'TZ35'==scene_id||'TZ36'==scene_id){
		parms=query_parm;
	}else{
		parms = returnParms(query_parm);
	}
	var returnVal;
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"TaiZhangAction_tzList.action", //请求路径
	    cache: false, 
	    async: false,
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       		var db_rel = json.rel;
       		var sql = json.sql;
       		var col_name = json.col_name;
       		var size = json.size;
       		var query_name = json.query_name;
       		var disName = json.disName;
       		var pageNo = json.pageNo;
       		var query_sql= json.query_sql;
       		var hidden_col = json.hidden_col;
       		sql = sql.replace(/\%/g,"@");
       		sql = sql.replace(/\?/g,"%3F");
       		if( query_sql!=null ){
       			query_sql = query_sql.replace(/\%/g,"@");
       			query_sql = query_sql.replace(/\?/g,"%3F");
       		}
       		var url='';
       		if('TZ34'==scene_id || 'TZ35'==scene_id ||'TZ36'==scene_id){
       			var cif_no=parms.split("=")[1].split(",")[0];
       			url = "creditapp/dev/Pop_frameTz.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+
       					"&size="+size+"&query_name="+query_name+"&disName="+disName+"&pageNo="+pageNo+"&scene_id="+scene_id+"&query_sql="+query_sql+"&hidden_col="+hidden_col+"&cif_no="+cif_no;
       		}else{
       		url = "creditapp/dev/Pop_frameTz.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+
       					"&size="+size+"&query_name="+query_name+"&disName="+disName+"&pageNo="+pageNo+"&scene_id="+scene_id+"&query_sql="+query_sql+"&hidden_col="+hidden_col;
       		}
       		returnVal = window.showModalDialog(url,window,"dialogWidth="+screen.availWidth+";dialogHeight="+(screen.availHeight-30)+";scroll:yes;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
	   		//returnVal=showDialog(url);
	    }
    });
	return returnVal;
}

/** 公用pop弹出页（单选框）=================>>>废弃！！！
*	参数1标示场景ID
*   参数2表示要从页面传的值即SQL语句where后面的条件

function func_popRadio_bak(scene_id,query_parm,val){
	if( val!=null && val!="" ){
	  	var a = document.getElementsByName(val)[0].value;
	  	if( a=="" || typeof(a)=="undefined" ){
	  		sAlert("请输入条件再点击放大镜查询");
	  		return false;
	  	}
	}
	var parms = "";
	if( query_parm!=null && query_parm!="" ){
		parms = returnParms(query_parm);
		if( parms == false){
			return false;
		}
	}
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"PopParmConfAction_queryAjax.action", //请求路径
	    cache: false,   
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       	if( json.size=="1" ){
       			db_rel = json.rel;
       			var db_rel_arr = db_rel.split(",");
				var value = "";
				var temp = "";
				for( var i=0;i<db_rel_arr.length;i++ ){
					var db_r_arr_split = db_rel_arr[i].split("-");
					for(var key in json){  
        				if( key==db_r_arr_split[0] ){
        					document.getElementsByName(db_r_arr_split[1])[0].value = json[key];
        				}
    				}   
				}
       		}else if( json.size=="0" ){
       			sAlert("没有查询出符合条件的结果!");
       			return false;
       		}else{
       			var db_rel = json.rel;
       			var sql = json.sql;
       			var col_name = json.col_name;
       			sql = sql.replace(/\%/g,"@");
       			var url = "creditapp/dev/PopCommRadio_List.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name;
    			window.showModalDialog(url,window,"dialogWidth=55;dialogHeight=40;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
       		}
       }
    });
}
**/
/**
 * 用于集团家谱制作页面 弹出框选择
 * @param {Object} scene_id
 * @param {Object} query_parm
 */
function func_popForFamily(scene_id,query_parm){
	var parms = returnParms(query_parm);
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"PopParmConfAction_findByPop.action", //请求路径
	    cache: false,   
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       		var db_rel = json.rel;
       		var sql = json.sql;
       		var col_name = json.col_name;
       		var size = json.size;
       		var query_name = json.query_name;
       		var disName = json.disName;
       		var pageNo = json.pageNo;
       		var query_sql= json.query_sql;
       		sql = sql.replace(/\%/g,"@");
       		sql = sql.replace(/\?/g,"%3F");
       		if( query_sql!=null ){
       			query_sql = query_sql.replace(/\%/g,"@");
       			query_sql = query_sql.replace(/\?/g,"%3F");
       		}
       		var url = "creditapp/dev/Pop_frameForFamily.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+
       					"&size="+size+"&query_name="+query_name+"&disName="+disName+"&pageNo="+pageNo+"&scene_id="+scene_id+"&query_sql="+query_sql;
    		window.showModalDialog(url,window,"dialogWidth=70;dialogHeight=38;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
       }
    });
}
/** 公用pop弹出页（多选框）
*	参数1标示场景ID
*   参数2表示要从页面传的值即SQL语句where后面的条件
**/
function func_pop(scene_id,query_parm,entity){
	var parms = "";
	var returnVal ;
	if( query_parm!=null && query_parm!="" ){
		parms = returnParms(query_parm);
		if( parms == "" || parms==null ){
			sAlert("请先在放大镜对应的输入框输入值后，再点击查询！");
			return false;
		}
	}
	var entity_val = "";
	if( entity!=null && entity!="" ){
		entity_val = document.getElementsByName(entity)[0].value;
	}
	
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"PopParmConfAction_findByPopChB.action", //请求路径
	    cache: false,   
	    async: false,
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       		var db_rel = json.rel;
       		var sql = json.sql;
       		var size = json.size;
       		sql = sql.replace(/\%/g,"@");
       		sql = sql.replace(/\?/g,"%3F");
       		var url = "creditapp/dev/Pop_CheckBox.jsp?rel="+db_rel+"&sql="+sql+"&scene_id="+scene_id+"&size="+size+"&entity_val="+entity_val;
    		returnVal = window.showModalDialog(url,window,"dialogWidth=65;dialogHeight=35;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
       }
    });
	return returnVal;
}
/** 用于集团家谱制作页面 弹出
function func_popForFamily(scene_id,query_parm){
	var parms = "";
	if( query_parm!=null && query_parm!="" ){
		parms = returnParms(query_parm);
		if( parms == false){
			return false;
		}
	}
	$.ajax({ 
	    type:"POST", //请求方式
	    url:"PopParmConfAction_queryAjax.action", //请求路径
	    cache: false,   
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       	if( json.size=="1" ){
       			db_rel = json.rel;
       			var db_rel_arr = db_rel.split(",");
				var value = "";
				var temp = "";
				for( var i=0;i<db_rel_arr.length;i++ ){
					var db_r_arr_split = db_rel_arr[i].split("-");
					for(var key in json){  
        				if( key==db_r_arr_split[0] ){
        					document.getElementsByName(db_r_arr_split[1])[0].value = json[key];
        				}
    				}   
				}
       		}else if( json.size=="0" ){
       			sAlert("没有查询出符合条件的结果!");
       			return false;
       		}else{
       			var db_rel = json.rel;
       			var sql = json.sql;
       			var col_name = json.col_name;
       			sql = sql.replace(/\%/g,"@");
       			var url = "creditapp/dev/PopCommRadio_ListForFamily.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name;
    			window.showModalDialog(url,window,"dialogWidth=55;dialogHeight=30;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
       		}
       }
    });
}
**/
/** 公用pop弹出页（多选框）
*	参数1标示场景ID
*   参数2表示要从页面传的值即SQL语句where后面的条件
**/
function func_popCheckbox(scene_id,query_parm){
	var parms = returnParms(query_parm);
	if( parms!="" ){
		if(parms==false){
			return false;
		}
	}
	$.ajax({ 
	    type:"POST", 
	    url:"PopParmConfAction_createSql.action", 
	    cache: false,   
	    data:{'scene_id':scene_id, 'parms':encodeURI(parms)}, 
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",   
	    dataType: 'json',   
	    success:function(json){  
       		var db_rel = json.rel;
       		var sql = json.sql;
       		var col_name = json.col_name;
       		sql = sql.replace(/\%/g,"@");
       		var url = "creditapp/dev/PopCommCheckbox_List.jsp?rel="+db_rel+"&sql="+sql+"&col_name="+col_name+"&parms="+parms;
       		//window.showModalDialog(url,window,"dialogWidth=55;dialogHeight=30;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
       		showNewDailogWindow(url,false,null,null,null,"dialogWidth=55;dialogHeight=30;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
	    }
    });
}

function showNewDailogWindow(url,isView,width,height,config,ieConifg){
	if(!width)width = screen.availWidth-20;
	if(!height)height = screen.availHeight-80;
	var iTop = (screen.availHeight-height)/2; //获得窗口的垂直位置;
	var iLeft = (screen.availWidth-10-width)/2; //获得窗口的水平位置;
	
	var isIe = true;
	var agent = navigator.userAgent.toLowerCase();
	if (agent.indexOf("chrome") > 0 || agent.indexOf("firefox") > 0) {
		isIe = false;
	}
    if (isIe) {
    	if(ieConifg)config = ieConifg;
    	else if(isView)config = "dialogWidth=25;dialogHeight=30;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no";
    	else if(!config)config = "dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogLeft=0px;dialogTop=0px;center=no;resizable=no;scrollbars=no;status:yes;help:no;";
    	var returnResult = window.showModalDialog(url, window, config);
        if (returnResult) {
            return returnResult;
        }
    } else {
    	if(!config)config = "status=no,width="+width+"px,height="+height+"px,menubar=no,scrollbars=no,top="+iTop+",left="+iLeft+",status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no";
        var returnResult = window.open(url, "_blank", config);
    }
}

/** 公用树弹出页（单选框）
*	参数1标示场景ID
*   参数2表示要从页面传的值即SQL语句where后面的条件
**/
function func_PopTree(scene_id,query_parm,call_func){
	var parms = returnParms(query_parm);
	if( parms!="" ){
		if(parms==false){
			return false;
		}
	}
	url="TreeConfAction_getTree.action?scene_id="+scene_id+"&parms="+encodeURI(parms)+"&call_func="+call_func;
//	window.showModalDialog(url,window,"dialogWidth=25;dialogHeight=30;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
	showNewDailogWindow(url,true,600,700);
}
//机构pop树
function func_PopTreePot(scene_id,query_parm,call_func){
	var parms = returnParms(query_parm);
	if( parms!="" ){
		if(parms==false){
			return false;
		}
	}
	url="TreeConfAction_getTreePot.action?scene_id="+scene_id+"&parms="+encodeURI(parms)+"&call_func="+call_func;
    //window.showModalDialog(url,window,"dialogWidth=25;dialogHeight=30;center:yes;help:no;minimize:no;maximize:no;border:thin;statusbar:no");
	showNewDailogWindow(url,true,600,700);
}
/**
 * 与核心通信，账号查询，获取相应的金额
 */
function getAccBal(paramNames){
	var paramArr = paramNames.split(",");
	var acc_no = "";
	var br_no = "";	
	var amt = "";
	var cur_no = "";
	acc_no = $("[name='"+paramArr[0]+"']").val();
	cur_no = $("[name='"+paramArr[1]+"']").val();
    if(acc_no=="" || acc_no==null){
    	sAlert("请输入账号！");
    	return false;
    } else if(cur_no=="" || cur_no==null){
    	sAlert("请输入保证金币种！");
    	return false;
    }
    var result="";
    var url = "TradeInterAction_getAccNoList.action?acc_no="+acc_no+"&cur_no="+cur_no;
    screenLock();
	$.when(getAjax(url)).done(function(result){
		screenUnlock();
		var msgArr = result.split("@");
		var msg1 = msgArr[0];
		var msg2 = msgArr[1];
		if (msg1 == '0'){
			sAlert(msg2);
			$("[name='"+paramArr[0]+"']").val('');
			return false;
		} else if(msg1 == '1'){
			if(paramArr[1]!=null||paramArr[1]!=''){
				//$("[name='"+paramArr[2]+"']").val(msg2);
				sAlert("获取账户金额成功，账户可用余额为"+msg2);
			} else {
				sAlert("账号校验成功");
			}
		} else {
			sAlert("通信异常");
		}
	})
}

/**
 * 与核心通信，账号查询，获取相应的账户名称
 */
function getAccName(paramNames){
	var paramArr = paramNames.split(",");
	var acc_no = "";
	var cif_name = "";	
	var cur_no = "";
	acc_no = $("[name='"+paramArr[0]+"']").val();
	cur_no = $("[name='"+paramArr[1]+"']").val();
	cif_name = $("[name='"+paramArr[2]+"']").val();
    if(acc_no=="" || acc_no==null){
    	sAlert("请输入账号！");
    	return false;
    } else if(cur_no=="" || cur_no==null){
    	sAlert("请输入业务币种！");
    	return false;
    }
    var result="";
    var url = "TradeInterAction_getAccNameList.action?acc_no="+acc_no+"&cif_name="+cif_name+"&cur_no="+cur_no;
    screenLock();
	$.when(getAjax(url)).done(function(result){
		screenUnlock();
		var msgArr = result.split("@");
		var msg1 = msgArr[0];
		var msg2 = msgArr[1];
		if (msg1 == '0'){
			sAlert(msg2);
			$("[name='"+paramArr[0]+"']").val('');
			return false;
		} else if(msg1 == '1'){
			if(paramArr[2]!=null||paramArr[2]!=''){
				sAlert("获取账户【"+acc_no+"】账户名称【"+msg2+"】成功");
				$("[name='"+paramArr[3]+"']").val(msg2);
			} else {
				sAlert("账号校验成功");
			}
		} else {
			sAlert("通信异常");
			return false;
		}
	})
}

/**
 * 与核心通信，账号查询，获取相应的账户名称和金额
 */
function getAccNameAndAmt(paramNames){
	var paramArr = paramNames.split(",");
	var acc_no = "";
	var br_no = "";	
	var cur_no = "";
	acc_no = $("[name='"+paramArr[0]+"']").val();
	cur_no = $("[name='"+paramArr[1]+"']").val();
    if(acc_no=="" || acc_no==null){
    	sAlert("请输入账号！");
    	return false;
    } else if(cur_no=="" || cur_no==null){
    	sAlert("请输入保证金币种！");
    	return false;
    }
    var result="";
    var url = "TradeInterAction_getAccNameAndAmt.action?acc_no="+acc_no+"&cur_no="+cur_no;
    screenLock();
	$.when(getAjax(url)).done(function(result){
		screenUnlock();
		var msgArr = result.split("@");
		var msg1 = msgArr[0];
		var msg2 = msgArr[1];
		if (msg1 == '0'){
			sAlert(msg2);
			$("[name='"+paramArr[0]+"']").val('');
			return false;
		} else if(msg1 == '1'){
			var parmValues = msg2.split("#");
			if(paramArr[1]!=null||paramArr[1]!=''){
				$("[name='"+paramArr[2]+"']").val(parmValues[0]);
				//$("[name='"+paramArr[3]+"']").val(parmValues[1]);
				sAlert("账号校验成功，账户可用余额为"+parmValues[1]);
			} else {
				sAlert("账号校验成功");
			}
		} else {
			sAlert("通信异常");
			return false;
		}
	})
}
/**
 * 与电票系统通信，承兑票据信息查询
 */
function getBillInfo(paramNames){
    var paramArr = paramNames.split(",");
	var bill_no = "";
	var cif_name = "";
	var bill_flag = "";
	bill_no = $("[name='"+paramArr[0]+"']").val();
	cif_name = $("[name='"+paramArr[1]+"']").val();
	bill_flag = $("[name='"+paramArr[2]+"']").val();
    if(bill_no=="" || bill_no==null){
    	sAlert("请输入票据账号！");
    	return false;
    } else if(cif_name=="" || cif_name==null){
    	sAlert("请输入收款人名！");
    	return false;
    } else if(bill_flag=="" || bill_flag==null){
    	sAlert("请输入票据类型！");
    	return false;
    }
    if(bill_flag =='1'){
    	var result=null;
	    var url = "TradeInterAction_getBillInfoList.action?bill_no="+bill_no+"&cif_name="+cif_name;
	    screenLock();
		$.when(getAjax(url)).done(function(result){
			screenUnlock();
			var msgArr = result.split("@");
			var msg1 = msgArr[0];
			var msg2 = msgArr[1];
			if (msg1 == '0'){
				sAlert(msg2);
				$("[name='"+paramArr[0]+"']").val('');
				return false;
			} else {
				var parmValues = msg2.split("#");
				$("[name='"+paramArr[3]+"']").val(parmValues[0]);
				$("[name='"+paramArr[4]+"']").val(parmValues[1]);
				$("[name='"+paramArr[5]+"']").val(parmValues[2]);
				$("[name='"+paramArr[6]+"']").val(parmValues[3]);
				$("[name='"+paramArr[7]+"']").val(parmValues[4]);
				$("[name='"+paramArr[8]+"']").val(parmValues[5]);
				sAlert("获取票据信息成功");
			} 
		})
    } else {
    	sAlert("票据类型为纸票，请继续填写其他相关信息");
    }
}
/**
 * 与核心通信，借据信息查询 
 */
function getDueInfo(paramNames,dueFlag){
	var paramArr = paramNames.split(",");
	var due_no = $("[name='"+paramArr[0]+"']").val();
    if(due_no=="" || due_no==null){
    	alert("请输入借据号！");
    	return false;
    }
    var result="";
    var url = "TradeInterAction_getDueInfo.action?due_no="+due_no+"&dueFlag="+dueFlag;
    screenLock();
	$.when(getAjax(url)).done(function(result){
		screenUnlock();
		var msgArr = result.split("@");
		var msg1 = msgArr[0];
		var msg2 = msgArr[1];
		if (msg1 == '0'){
			alert(msg2);
			$("[name='"+paramArr[0]+"']").val('');
			return false;
		} else if(msg1 == '1'){
			var parmValues = msg2.split("#");
			alert("获取借据相关信息成功！");
			$("[name='"+paramArr[1]+"']").val(parmValues[0]);
			$("[name='"+paramArr[2]+"']").val(parmValues[1]);
			$("[name='"+paramArr[3]+"']").val(parmValues[2]);
			$("[name='"+paramArr[4]+"']").val(parmValues[3]);
			$("[name='"+paramArr[5]+"']").val('3');
		} 
	})
}
//加锁屏
function getAjax(url){
	var defer = $.Deferred();	
	$.ajax({ 
	   type: "post", 
	   url: url, 
	   success: function(data){
			defer.resolve(data);
		} 
    });
	return defer.promise();
}
/**
 * 利率测算--核心通信
 */
function trialRate(paramNames){
	var paramArr = paramNames.split(",");
	var sec_prod_no = $("[name='"+paramArr[0]+"']").val();   //保证金外部产品号
	var cur_no = $("[name='"+paramArr[1]+"']").val();        //币种
	var sec_term_type = $("[name='"+paramArr[2]+"']").val(); //保证金存储类型
	var sec_amt = $("[name='"+paramArr[3]+"']").val();       //金额
	var is_per_rate = $("[name='"+paramArr[4]+"']").val();   //优惠标志
	var cif_no = $("[name='"+paramArr[5]+"']").val();        //客户号
	var sec_acc = $("[name='"+paramArr[6]+"']").val();       //账号        
	var base_rate = $("[name='"+paramArr[7]+"']").val();     //基准利率      
	var rate_flt_per = $("[name='"+paramArr[8]+"']").val();  //利率浮动百分比
	var rate_flt_point = $("[name='"+paramArr[9]+"']").val();//利率浮动点数  
	var pre_rate_per = $("[name='"+paramArr[10]+"']").val();  //利率优惠比例  
	var pre_rate_point = $("[name='"+paramArr[11]+"']").val();//利率优惠点数  
	var ln_rate = $("[name='"+paramArr[12]+"']").val();       //账号执行利率
    if(sec_acc=="" || sec_acc==null){
    	sAlert("账号不能为空！");
    	return false;
    } else if(sec_prod_no=="" || sec_prod_no==null){
    	sAlert("保证金外部产品号不能为空！");
    	return false;
    } else if(sec_term_type=="" || sec_term_type==null){
    	sAlert("保证金存储类型不能为空！");
    	return false;
    } else if(is_per_rate=="" || is_per_rate==null){
    	sAlert("请选择是否优惠！");
    	return false;
    } else if(sec_amt=="" || sec_amt==null){
    	sAlert("保证金专户金额不能为空！");
    	return false;
    } else if(cur_no=="" || cur_no==null){
    	sAlert("保证金币种不能为空！");
    	return false;
    }
    var result="";
    screenLock();
	$.when(trialRateAjax(sec_prod_no,cur_no,sec_term_type,sec_amt,is_per_rate,cif_no,sec_acc,ln_rate)).done(function(result){
		screenUnlock();
		var msgArr = result.split("@");
		var msg1 = msgArr[0];
		var msg2 = msgArr[1];
		
		if (msg1 == '0'){
			sAlert(msg2);
			return false;
		}  else if(msg1 == '1'){
			if(sec_prod_no=='0030'){
				sAlert("请手工完善信息并执行【保存】操作!");
			}else{
				var parmValues = msg2.split("#");
				$("[name='"+paramArr[7]+"']").val(parmValues[0]);
				$("[name='"+paramArr[8]+"']").val(parmValues[1]);
				$("[name='"+paramArr[9]+"']").val(parmValues[2]);
				$("[name='"+paramArr[10]+"']").val(parmValues[3]);
				$("[name='"+paramArr[11]+"']").val(parmValues[4]);
				$("[name='"+paramArr[12]+"']").val(parmValues[5]);
				sAlert("利率测算成功");
			}
		} else {
			sAlert("通信异常");
		}
	})
}
//加锁屏
function trialRateAjax(sec_prod_no,cur_no,sec_term_type,sec_amt,is_per_rate,cif_no,sec_acc,ln_rate){
	var defer = $.Deferred();	
	$.post("TradeInterAction_trialRate.action", {
	    sec_prod_no:sec_prod_no,
		cur_no:cur_no,
		sec_term_type:sec_term_type,
		sec_amt:sec_amt,
		is_per_rate:is_per_rate,
		cif_no:cif_no,
		sec_acc:sec_acc,
		ln_rate:ln_rate
		}, function(data) {
			defer.resolve(data);
	});
	return defer.promise();
}
/**
 * 非分期提前还款利息测算、分期提前还款利息测算
 * @param {Object} paramNames
 * @return {TypeName} 
 * getMess;pop;earlierTrialRate('acc_no,return_type,pay_inte_way,pay_amt,chane_plan_way,pay_inte,');
 */
function earlierTrialRate(paramNames){
	var paramArr = paramNames.split(",");
	var acc_no = $("[name='"+paramArr[0]+"']").val();     //贷款账号
	var return_type = $("[name='"+paramArr[1]+"']").val();   //还款种类：1-部分归还/2-全部归还 
	var pay_inte_way = $("[name='"+paramArr[2]+"']").val(); //还息方式
	var pay_amt = $("[name='"+paramArr[3]+"']").val(); //本金
	var chane_plan_way = $("[name='"+paramArr[4]+"']").val(); //变更方式
	var pay_sum = $("[name='"+paramArr[6]+"']").val(); //归还总金额
	var due_no = $("[name='"+paramArr[8]+"']").val(); //借据号
	var if_cs = $("[name='"+paramArr[8]+"']").val(); //是否测算利息
	
	if(acc_no=="" || acc_no==null){
    	sAlert("账号不能为空！");
    	return false;
    } else if(return_type=="" || return_type==null){
    	sAlert("还款种类不能为空！");
    	return false;
    } else if(pay_inte_way=="" || pay_inte_way==null){
    	sAlert("还息方式不能为空！");
    	return false;
    } else if(pay_sum=="" || pay_sum==null){
    	sAlert("归还总金额不能为空！");
    	return false;
    }
	if(pay_inte_way == "1"){
    		sAlert("还息方式为[仅本金]时,不需要测算利息!");
    		return false;
    	}
    var result="";
    screenLock();
	$.when(earlierTrialRateAjax(acc_no,return_type,pay_inte_way,pay_amt,chane_plan_way,pay_sum,due_no,if_cs)).done(function(result){
		screenUnlock();
		var msgArr = result.split("@");
		var msg1 = msgArr[0];
		var msg2 = msgArr[1];
		if (msg1 == '0'){
			sAlert(msg2);
			var parmValues = msg2.split("#");
			$("[name='"+paramArr[5]+"']").val('');//利息
			$("[name='"+paramArr[9]+"']").val('');//是否已经测算利息标示  Y 测算
			return false;
		}  else if(msg1 == '1'){
			var parmValues = msg2.split("#");
			$("[name='"+paramArr[5]+"']").val(parmValues[0]);//利息
			$("[name='"+paramArr[3]+"']").val(parmValues[1]);//本金
			$("[name='"+paramArr[6]+"']").val(parmValues[2]);//总金额
			$("[name='"+paramArr[7]+"']").val(parmValues[3]);//违约金
			$("[name='"+paramArr[9]+"']").val('Y');//是否已经测算利息标示  Y 测算
			sAlert("提前还款利息测算成功");
		} else {
			sAlert("通信异常");
		}
	})	
}
//加锁屏
function earlierTrialRateAjax(acc_no,return_type,pay_inte_way,pay_amt,chane_plan_way,pay_sum,due_no){
	var defer = $.Deferred();	
	$.post("TradeInterAction_earlierTrialRate.action", {
	    acc_no:acc_no,
		return_type:return_type,
		pay_inte_way:pay_inte_way,
		pay_amt:pay_amt,
		chane_plan_way:chane_plan_way,
		pay_sum:pay_sum,
		due_no:due_no
		}, function(data) {
			defer.resolve(data);
	});
	return defer.promise();
}

function selectSql(parNames,popNames){
		var url = "LgsqlConfAction_findByPagePop.action?parNames=" + parNames
			+ "&popNames=" + popNames;
		window
		.showModalDialog(
				url,
				window,
				"dialogWidth=1080px;dialogHeight=500px;resizable=no;scrollbars=no;status:yes;help:no;");
}

function selectSQLScheme(parNames,popNames){
		var url = "LgschemeConfAction_findByPageForPop.action?parNames=" + parNames
			+ "&popNames=" + popNames;
		window
		.showModalDialog(
				url,
				window,
				"dialogWidth=1080px;dialogHeight=500px;resizable=no;scrollbars=no;status:yes;help:no;");
}
function getSecAmtTotal(paramNames){
	var paramArr = paramNames.split(",");
	var key_no = $("[name='"+paramArr[0]+"']").val();
	var scene_no = $("[name='scene_no']").val();
	var url = ""; 
	if(scene_no=="1"){
		url = "ApplyBaseAction_getSecAmtTotal.action?app_no="+key_no;
	}else if(scene_no=="3"){
		url = "LnPactAction_getSecAmtTotal.action?pact_no="+key_no;
	}
	$.when(getAjax(url)).done(function(result){
		screenUnlock();
		$("[name='"+paramArr[1]+"']").val(result);
	});
	
}
function forEndDateByBegDate(){
	fPopUpCalendarDlg();
	if($("[name='LN_PACT.BEG_DATE']").length>0){
		if($("[name='LN_PACT.BEG_DATE']").length>0){
			$("[name='LN_PACT.END_DATE']").val($("[name='LN_PACT.BEG_DATE']").val());
		}
	}
}

//核算产品编号pop
//function funcAcLnParmForPop(parNames,popNames){
//	var url="AcGlParmAction_findByPageForPop.action?parNames="+parNames+"&popNames="+popNames;
//	showDialog(url);
//}
function funcAcLnParmForPop(parNames,popNames){
	var url="AcLnParmAction_findByPageForPop.action?parNames="+parNames+"&popNames="+popNames;
	showDialog(url);
}
//还款顺序代码pop,利息核销顺序代码pop，逾期还款顺序代码pop
function funcAcLnRepaySeqForPop(parNames,popNames){
	var url="AcLnRepaySeqAction_findByPageForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	showDialog(url);
}
//费用编号pop
function funcAcChrgTypForPop(parNames,popNames){
	var url="AcChrgTypAction_findForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	showDialog(url);
}
//信贷产品编号pop
function funcAcPrdtMapForPop(parNames,popNames){
	var url="AcPrdtMapAction_findForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	showDialog(url);
}
//费率编号pop
function funGetAcComChrgRate(parNames,popNames){
	var url="AcComChrgRateAction_findByPageForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	showDialog(url);
}
//贷款主文件pop
function funSelectAcLnMst(parNames,popNames){
	var url="AcLnMstAction_LoanAcctAdj_findByPageForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	showDialog(url);
}

//保理登记簿pop
function funSelectAcFactReg(parNames,popNames){
	var url="AcFactRegAction_findByPageForPop.action?parNames=" + parNames + "&popNames=" + popNames;
	//showDialog(url);
	window.showModalDialog(
				url,
				window,
				"dialogWidth=1080px;dialogHeight=500px;resizable=no;scrollbars=no;status:yes;help:no;");
}

//20151117新增 -查询账务机构信息pop
function funcGetFinBrNoPop(){
	var url = "AcSysFinOrgInfoAction_listPop.action";
	var temp = window.showModalDialog(url, window, "dialogWidth=20;dialogHeight=30;resizable=no;scrollbars=no;status:no;help:no;");
	if("undefined" == typeof(temp)){
		document.getElementsByName('fin_br_no')[0].value = '';
	}else{
		document.getElementsByName('fin_br_no')[0].value = temp;
	}
}
//金融产品管理选取核算产品编号
function funcAcGlPop(parNames, popNames) {
    var url = "AcGlParmAction_findByPageForPop.action?";
    url = url + "parNames=" + parNames + "&popNames=" + popNames;
    showDialog(url);
}
//获取还款计划公式
function funcGetReplan(parNames,popNames){
	var url ="AcReplanFormulaAction_findByPageForPop.action?parNames="+parNames+"&popNames="+popNames;
	showDialog(url);
}
//新增抵押品信息POP
function funcGageRegGuaPop(parNames, popNames) {
	var gage_way = document.getElementsByName('gage_way')[0].value;
	var url = "GageRegAction_findByPageForGuaPop.action?parNames=" + parNames + "&popNames=" + popNames + "&gage_way=" + gage_way;
	showDialog(url);
}
//抵债物关联押品
function funcAftAssetPop(parNames,popNames,cifNo){
	var url ="LnGageAction_findByPageForPop.action?parNames="+parNames+"&popNames="+popNames+"&cifNo="+cifNo ;
	showDialog(url);
}
//用户视图
function funcSysUserPop(parNames,popNames){
	var url ="SysUserAction_findByPageForPop.action?parNames="+parNames+"&popNames="+popNames ;
	showDialog(url);
}
//预拨款
function funcProjAcctPop(parNames,popNames,projNo){
	var url ="../../ProjAcctAction_findByPageForPop.action?parNames="+parNames+"&popNames="+popNames+"&projNo="+projNo ;
	showDialog(url);
}
