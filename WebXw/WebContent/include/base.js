function Page(pageNum) {
	try {
		document.page.currentPage.value = pageNum;
		document.page.submit();
		return true;
	} catch (e) {
		sAlert(e);
	}
}

function openurl(theURL, winName, features) {
	window.open(theURL, winName, features);
}

function my_print(ln){
	self.open(ln,"","height=620,width=700,resizeable=yes,scrollbars=yes");
}

function switch_updown() {
	if (mspan.title == "关闭上栏") {
		mspan.title = "打开上栏";
		document.all("mtop").style.display = "none";
	} else {
		mspan.title = "关闭上栏";
		document.all("mtop").style.display = "";
	}
}

function switch_leftRight() {
	if (mspan.title == "关闭客户列表") {
		mspan.title = "打开客户列表";
		document.all("mleft").style.display = "none";
	} else {
		mspan.title = "关闭客户列表";
		document.all("mleft").style.display = "";
	}
}

function replaceAll(str, str1, str2) {
	while (str.indexOf(str1) >= 0)
		str = str.replace(str1, str2);

	return str;
}

function getParam(str, leftFlag, rightFlag) {
	var param = "";
	while (str.indexOf(rightFlag) < str.indexOf(leftFlag)) {
		if (str.indexOf(rightFlag) < 0)
			break;
		str = str.substring(str.indexOf(rightFlag) + 1);
	}

	if (str.indexOf(leftFlag) >= 0 && str.indexOf(rightFlag) >= 0) {
		var pos1 = str.indexOf(leftFlag);
		var pos2 = str.indexOf(rightFlag);
		param = str.substring(pos1 + 1, pos2);
	}
	return param;
}

function samepara() {
	kind = document.all["param"].value;
	if (kind == "1") {
		para = document.all["param1"].value;
		document.all["param2"].value = para;
		document.all["param3"].value = para;
		document.all["param4"].value = para;
		document.all["param5"].value = para;
		document.all["param6"].value = para;
		document.all["param7"].value = para;
		document.all["param8"].value = para;
		document.all["param9"].value = para;
		document.all["param10"].value = para;
		document.all["param11"].value = para;
		document.all["param12"].value = para;
	}

}

function setElementValue(src, value) {
	document.all[src].value = value;
}

function setQuery(kind) {
	document.all["query"].value = kind;
}

function displaylayer() {
	laywait.style.visibility = "visible";

}

function popMessage(msg) {
	if (msg != null && msg != "") {
		sAlert(msg);
	}

}

function stat() {
	var a = pageYOffset + window.innerHeight - document.bar.document.height
			- 15;
	document.bar.top = pageYOffset;
	setTimeout('stat()', 2);
}
function iefd(offsetxpos, offsetypos) {
	bar.style.top = document.body.scrollTop + document.body.offsetHeight
			- offsetypos;
	bar.style.left = document.body.scrollLeft + document.body.offsetWidth
			- offsetxpos;
	setTimeout("iefd(" + offsetxpos + "," + offsetypos + ")", 2);
}//<!-- 浮动条 --->
function fix(offsetxpos,offsetypos){
  nome=navigator.appName;
  if(nome=='Netscape'){
    stat();
  }else{
    iefd(offsetxpos,offsetypos);
  }
}

//<!-- 清空表单内容 --->
function clearAll(theForm){
	len = theForm.elements.length;
	for(i=0;i<len;i++){
		//alert(theForm.elements[i].type);
		if(theForm.elements[i].type == 'checkbox'){
				theForm.elements[i].checked = false;
		}
		if(theForm.elements[i].type == 'radio'){
			theForm.elements[i].checked = false;
		}
		if(theForm.elements[i].type == 'select-one'){
			theForm.elements[i].value = theForm.elements[i].options[0].value ;
		}
		if(theForm.elements[i].type == 'text')
		  theForm.elements[i].value = "";
		  
		if(theForm.elements[i].type == 'textarea')
		  theForm.elements[i].value = "";
	}
} 

//<!-- 清空复选框 --->
function clearAllCheckbox(theForm){
	len = theForm.elements.length;
	for(i=0;i<len;i++){
		//alert(theForm.elements[i].type);
		if(theForm.elements[i].type == 'checkbox'){
				theForm.elements[i].checked = false;
		}
	}
} 
//<!-- 选中所有复选框 --->
function selAllCheckbox(theForm){
	len = theForm.elements.length;
	for(i=0;i<len;i++){
		//alert(theForm.elements[i].type);
		if(theForm.elements[i].type == 'checkbox'){
				theForm.elements[i].checked = true;
		}
	}
} 

//<!-- 计算选中所有复选框个数 --->
function countCheckbox(theForm){
	sum = 0;
	len = theForm.elements.length;
	for(i=0;i<len;i++){
		//alert(theForm.elements[i].type);
		if(theForm.elements[i].type == 'checkbox' && theForm.elements[i].checked == true){
				sum ++ ;
		}
	}
	return sum;
} 

//<!-- 得到选择域的文本 -->
	function getSelectText(src){
		return src.options[src.selectedIndex].text;
	}

//<!-- 确认执行操作 -->
function lkconfirm(lk){
  flag=window.confirm("请确定是否要执行此操作，按“取消”表示不进行此操作！");
  if(flag){
  	location.href = lk;
  	//location.href.click();
  }
}

//<!-- POP测试链接 -->
function poptest(lk){
  	var valueStr=lk.split("?")[1].split("&");
	var scene_id=valueStr[0].split("=")[1];//场景编号]
	var check_flag = scene_id.substring(0,1);
	var if_checkbox=valueStr[1].split("=")[1];//是否多选
	if(if_checkbox!='1'){
		func_popRadio(scene_id,'');
	}else{
		if(check_flag=='D'){    // 左右多选框
			func_pop(scene_id,'','');
		}else{
			func_popCheckbox(scene_id,'');
		}
	}
}

//带锁屏的确认操作
function lkconfirmWithLock(lk){
  flag=window.confirm("请确定是否要执行此操作，按“取消”表示不进行此操作！");
  if(flag){
	screenLock();
  	location.href = lk;
  }
}
//<!-- 详情页面中的查看按钮操作 -->
function fromInfoView(fieldName,fieldValue,lk){
	if(fieldValue=='' ||fieldValue==null ){
		sAlert(fieldName+"为空，请赋值后才能查看!");
		return;
	}
	window.open(lk,"window","status:no;help:no;border:thin;statusbar:no,left=100,top=30,resizable=yes,width=1200,height=600");
 
}
//<!-- TABLE中按钮执行JS操作 -->
function buttonforward(lk){
  	location.href = lk;
  	//location.href.click();
}
//<!--买断买入提交申请-->
function func_submitconfirm(lk) {
	var strs=lk.split("&");
	var str=strs[1];
	var temps=str.split("=");
	var status=temps[1];

	if (confirm('提交后就不能修改,确认提交！')) {
		if (status == "0" ||status == "2") {
			location.href = lk;
			//location.href.click();
		} else {
			sAlert('该申请已提交！');
			return;
		}
	}
}

//<!-- 链接url -->
function linkUrl(lk,win){
  	location.href = lk;
  	location.href.target = '_blank';
  	//location.href.click();
}


//the page could invoke history.goback(-1) to goback last page while isGoBack is not null
//but please specify the var isGoBack's value in your page, eg: isGoBack = true;
var isGoBack; 

function historyForward() {
	if(  isGoBack == "undefined" || isGoBack == null ) {
		window.history.forward(50);
	}
}

historyForward();

//<!-- 翻页JS -->
 function doEadisPage(url,v){
	
	  url = trim(url);
	  var formself;
	  var formfirst;
	  var forms = document.getElementsByTagName("form");
	  for(var i=0;i<forms.length;i++){
			var form = forms[i];
			if(form.action==url&& (form.name=='null'||form.name=='formBean')||form.name=='tableDataBean'){
					formself=form;
			}
			if(form.name!='null' && form.name!='formBean' && form.name!='tableDataBean'){
				if(form.action.indexOf("/")>=0){
	                var tempUrl =  form.action.substring(form.action.lastIndexOf("/")+1,form.action.length);
	                tempUrl = trim(tempUrl);
	                if(tempUrl==url){
	                	formfirst = form;
	                }
	            }else{
	            	 tempUrl = trim(form.action);
	                if(tempUrl==url){
	                	formfirst = form;
	                }
	            }
			}
	  }
	  
      if(formfirst){
    	   var eles=formfirst.elements;
		 	var esubmit;
            for(i=0;i<eles.length;i++){
               e=eles[i];
               if(e.name=='eadis_page'){
            	   e.value=v;
               }
               if(e.name=='formPage'){
            	   e.value=v;
               }
                if(e.name=='submitgo'){
            	   esubmit=e;
               }
            }
            esubmit.click();
      }else {
    		  var eles=formself.elements;
		 	var esubmit;
             for(i=0;i<eles.length;i++){
               e=eles[i];
                if(e.name=='formPage'){
            	   e.value=v;
               }
               if(e.name=='eadis_page'){
            	   e.value=v;
               }
                if(e.name=='submitgo'){
            	   esubmit=e;
               }
            }
               esubmit.click();
      }
 }
 function trim(oValue){
		oValue = oValue.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		return oValue;
	}
	/**
	*	控制数据字典项的显示内容
	*/ 
	function makeOptions(objName, allowOptionStr) {
		var obj = document.getElementsByName(objName)[0];
		var allowOptionStr = allowOptionStr.split(","); 
		if(obj){
			var options=obj.options;
			if(options){
			for ( var i = options.length - 1; i >= 0; i--) {
				var k = 0;
				//根据返回值移除，如果返回空则不做任何处理
				if (allowOptionStr != '' && options[i].value != '' ) {
					for(var j=0 ; j<allowOptionStr.length;j++){
						if(options[i].value !=allowOptionStr[j]){
							k ++;
						}
					}
					if(k !=allowOptionStr.length-1){
						options.remove(i);
					}
				}
			}
			}
		}
	}
	
	function getCursorPsn(inObject){
		var position = 0; 
		var domObj = inObject;
		if (document.selection) {	//for IE
			domObj.focus();
			var sel = document.selection.createRange();
			sel.moveStart('character', -domObj.value.length);
	
			position = sel.text.length;
		} else if (domObj.selectionStart || domObj.selectionStart == '0') {
			position = domObj.selectionStart;
		}
		return position;
	}
	
	function getOstr(inObject,position){
		var sumOstr = 0;
		var inStr = inObject.value;
		if(inStr.length > 0){
			var lStr = inStr.substring(0,position);
			for(var i=0; i<lStr.length; i++){
				var v = lStr.charAt(i);
				if(isNaN(v)){
					sumOstr++;
				}
			}
		}
		return sumOstr;
	}
		//<!-- 千分位 --> //wusheng
	function toMoney(inObject) {
		//获取光标位置
		var position = getCursorPsn(inObject);
		//获取光标前非数字个数
		var sumOstr = getOstr(inObject,position);
		
		var inStr = inObject.value;
		var i, charValue, outStr, id = 0,fs=0;
		//var fsInStr ="";

		for (i = 0; i < inStr.length; i++) {
			charValue = inStr.charAt(i);
			//alert("charValue:"+charValue+":"+i+":"+inStr.length);
			if (isNaN(parseInt(charValue, 10)) && (charValue != ".")
					&& (charValue != ",") && (charValue != "-")) {
				if(fs==1)
					inObject.value = "-"+inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				else
					inObject.value = inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				return;
			}
			if (i>0 && charValue == "-") {
				if(fs==1)
					inObject.value = "-"+inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				else
					inObject.value = inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				return;
			}
			if (i==0 && charValue == "-") {
				fs = 1;
				inStr = inStr.substr(1, inStr.length);
				i=-1;
			}
			if (charValue != "0") {
				id = 1;
			}
		
			if (id == 0 && i == 1 && charValue == "0") {
				inStr = inStr.substr(1, inStr.length - 1);
				i = 0;
			}
		}
		
		var valueArr;

		valueArr = inStr.split(".");

		if (valueArr.length > 2) {
			sAlert(inStr + " 非法金额!");
			inObject.focus();
			inObject.select();
			return;
		}

		var dotStr, dotValue;

		if (valueArr.length == 2) {
			dotValue = valueArr[1];
			if (dotValue.length == 0) {
				dotStr = "";
			} else {
				if (dotValue.length == 1)
					dotStr = dotValue+ "";
				else
					dotStr = dotValue.substring(0, dotValue.length);
			}
		}	
		
		var intArr;

		intArr = valueArr[0].split(",");

		var intValue = "";

		for (i = 0; i < intArr.length; i++) {
			intValue += intArr[i];
		}
		var intStr = "";

		if (intValue.length > 1 && intValue.charAt(0) == "0"
				&& intValue.charAt(1) != ".") {
			intValue = intValue.substr(1, intValue.length - 1);
		}

		while (intValue.length > 3) {
			intStr = ","
					+ intValue.substring(intValue.length - 3, intValue.length)
					+ intStr;
			intValue = intValue.substring(0, intValue.length - 3);
		}
		intStr = intValue + intStr;

		if (dotStr == null)
			outStr = intStr;
		else
			outStr = intStr + "." + dotStr;
		
		if(fs==1){
			outStr = "-" + outStr;
		}

		inObject.value = outStr;
		
		var esumOstr = getOstr(inObject,position);
		
		//设置光标位置
		position = position + (esumOstr - sumOstr);
		setgetCursorPsn(inObject,position);
		
		return;
	}
	
	function setgetCursorPsn(domObj,position){
		if (document.selection) {	//for IE
			domObj.focus();
			var sel = document.selection.createRange();
			var r = domObj.createTextRange();   
			r.collapse(true);  
			r.moveStart('character', position);
			r.select();   
			
		} else  {
			domObj.selectionStart = position;
		}
	}
	
	
	function toDouble(inObject) {
		var inStr = inObject.value;
		var i, charValue, outStr, id = 0,fs=0;
		for (i = 0; i < inStr.length; i++) {
			charValue = inStr.charAt(i);
			if (isNaN(parseInt(charValue, 10)) && (charValue != ".")
					&& (charValue != ",") && (charValue != "-")) {
				if(fs==1)
					inObject.value = "-"+inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				else
					inObject.value = inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				return;
			}
			if (i>0 && charValue == "-") {
				if(fs==1)
					inObject.value = "-"+inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				else
					inObject.value = inStr.substring(0, i)+inStr.substring(i+1, inStr.length);
				return;
			}
			if (i==0 && charValue == "-") {
				fs = 1;
				inStr = inStr.substr(1, inStr.length);
				i=-1;
			}
			if (charValue != "0") {
				id = 1;
			}
			if (id == 0 && i == 1 && charValue == "0") {
				inStr = inStr.substr(1, inStr.length - 1);
				i = 0;
			}
		}
		var valueArr;
		valueArr = inStr.split(".");
		if (valueArr.length > 2) {
			sAlert(inStr + " 非法数值!");
			inObject.focus();
			inObject.select();
			return;
		}
		var dotStr, dotValue;
		if (valueArr.length == 2) {
			dotValue = valueArr[1];
			if (dotValue.length == 0) {
				dotStr = "";
			} else {
				if (dotValue.length == 1)
					dotStr = dotValue+ "";
				else
					dotStr = dotValue.substring(0, dotValue.length);
			}
		}	
		var intArr;
		intArr = valueArr[0].split(",");
		var intValue = "";
		for (i = 0; i < intArr.length; i++) {
			intValue += intArr[i];
		}
		var intStr = "";
		if (intValue.length > 1 && intValue.charAt(0) == "0"
			&& intValue.charAt(1) != ".") {
			intValue = intValue.substr(1, intValue.length - 1);
		}
		while (intValue.length > 3) {
			intStr = intValue.substring(intValue.length - 3, intValue.length) + intStr;
			intValue = intValue.substring(0, intValue.length - 3);
		}
		intStr = intValue + intStr;
		if (dotStr == null)
			outStr = intStr;
		else
			outStr = intStr + "." + dotStr;
		if(fs==1){
			outStr = "-" + outStr;
		}
		inObject.value = outStr;
		return;
	}

	function lastMoney(inObject) {
		var inStr = inObject.value;
		
		var i, charValue, id = 0,fs=0;
		for (i = 0; i < inStr.length; i++) {
			charValue = inStr.charAt(i);
			// alert("charValue:"+charValue+":"+i+":"+inStr.length);
			if (isNaN(parseInt(charValue, 10)) && (charValue != ".")
					&& (charValue != ",") && (charValue != "-")) {
				if(fs==1)
					inStr = "-"+inStr.substring(0, i);
				else
					inStr = inStr.substring(0, i);
			}
			if (i>0 && charValue == "-") {
				if(fs==1)
					inStr = "-"+inStr.substring(0, i);
				else
					inStr = inStr.substring(0, i);
			}
			if (i==0 && charValue == "-") {
				fs = 1;
				inStr = inStr.substr(1, inStr.length);
				i=-1;
			}
			if (charValue != "0") {
				id = 1;
			}
		
			if (id == 0 && i == 1 && charValue == "0") {
				inStr = inStr.substr(1, inStr.length - 1);
				i = 0;
			}
		}
		
		var valueArr = inStr.split(".");

		if (valueArr.length > 2) {
			sAlert(inStr + " 非法金额!");
			inObject.focus();
			inObject.select();
			return;
		}

		var dotStr="", dotValue;

		if (valueArr.length == 2) {
			dotValue = valueArr[1];
			if (dotValue.length == 0) {
				dotStr = "";
			} else {
				if (dotValue.length == 1)
					dotStr = dotValue+ "";
				else
					dotStr = dotValue.substring(0, dotValue.length);
			}
		}	
		
		var intArr;

		intArr = valueArr[0].split(",");

		var intValue = "";

		for (i = 0; i < intArr.length; i++) {
			intValue += intArr[i];
		}
		var intStr = "";

		if (intValue.length > 1 && intValue.charAt(0) == "0"
				&& intValue.charAt(1) != ".") {
			intValue = intValue.substr(1, intValue.length - 1);
		}

		while (intValue.length > 3) {
			intStr = ","
					+ intValue.substring(intValue.length - 3, intValue.length)
					+ intStr;
			intValue = intValue.substring(0, intValue.length - 3);
		}
		intStr = intValue + intStr;

		if (dotStr == "")
			inStr = intStr;
		else
			inStr = intStr + "." + dotStr;
		
		if(fs==1){
			inStr = "-" + inStr;
		}
		var outStr;

		var valueArr = inStr.split(".");

		var dotStr="", dotValue;

		if (valueArr.length == 2) {
			dotValue = valueArr[1];
			if (dotValue.length == 0) {
				dotStr = "00";
			} else {
				if (dotValue.length == 1)
					dotStr = dotValue + "0";
				else{
					var is0 = 0;
					for (i = dotValue.length-1; i >= 2; i--) {
						var charValue = dotValue.charAt(i);
						if(charValue!="0" || is0!=0){
							if(charValue!=",")
								dotStr = charValue + dotStr;
							is0 = 1;
						}
					}
					dotStr = dotValue.substring(0, 2) + dotStr;
					if(dotStr.length > 6)
						dotStr = dotStr.substring(0, 6);
					else
						dotStr = dotStr.substring(0, dotStr.length);
					//dotStr = dotValue.substring(0, 2);
				}
			}
		} else {
			dotStr = "00";
		}

		var intStr = valueArr[0];

		if (intStr == "" || intStr == null)
			intStr = "0";

		outStr = intStr + "." + dotStr;

		if (outStr == "" || outStr == null || outStr == ".00"
				|| outStr == "0.00" || outStr == ".")
			inObject.value = "";
		else
			inObject.value = outStr;

		return;
	}
	function enterKey()
	{
		if (event.keyCode==13 && event.srcElement.type.toLowerCase()!="textarea") 
	   {
	       event.keyCode=9;
	   }
	   return;
	}

	function enterKeyEmpty()
	{
	}

/**
 * 锁屏直到页面刷新（div在tld.jsp中定义）
 */
function screenLock() {
	$("#screenLockDiv").css({"width" : document.body.scrollWidth,
		"height" : document.body.scrollHeight,
		"opacity" : "0.5"
	}).fadeIn('normal');
	if(document.getElementsByTagName('body') && document.getElementsByTagName('body')[0]){
		document.getElementsByTagName('body')[0].style.overflow = 'hidden'; // 隐藏滚动条
	}
		
}

/**
 * 解除锁屏
 */
function screenUnlock() {
	$("#screenLockDiv").fadeOut('normal');
	document.getElementsByTagName('body')[0].style.overflow = 'auto'; // 显示滚动条
}


function submitJsMethod(form, otherExtend) {
	screenLock();
	if (func_uior_calAll(form)) {
		try {
			if (otherExtend == "" || otherExtend == 'null'
					|| otherExtend == 'undefined' || otherExtend == null) {
				screenUnlock();
				return true;
			}
			var flag = true;
			var otherExtendGroup = otherExtend.split(";");
			for (var k = 0; k < otherExtendGroup.length; k++) {
				if (otherExtendGroup[k] != null
						&& otherExtendGroup[k] != undefined
						&& otherExtendGroup[k] != '') {
					flag = eval(otherExtendGroup[k]);
					if (!flag) {
						screenUnlock();
						return false;
					}
				}
			}
			screenUnlock();
			return flag;
		} catch (e) {
			screenUnlock();
			return false;
		}
	} else {
		screenUnlock();
		return false;
	}
}
function firstEadisPageFlag(){
	document.all('eadis_page').value='1';
	return true;
}
/**
 * 控制页面中 数据类型要素获取焦点时，是全部选中效果。再次点击文本域，可在定位文本域具体的位置。
 */
var numericaltimes = 0;
function selectInput(obj){
	if(++numericaltimes==1){
		obj.select();
	}
}

function resetTimes(){
	numericaltimes = 0;
}
/**
 * 该JS未使用
 */
function out(obj){
		//obj.style.color = "black";
}
/**
 * 该JS未使用
 */
function over(obj){
		//obj.style.color = "#06F";
}

/**
 * 显示textarea剩余可输入汉字数的Div（onfoucs事件，div在/include/tld.jsp中定义）
 * @param textarea 多行文本域对象
 */
function showCharsInfo(textarea) {
	if (textarea.maxlength && !document.getElementById(textarea.name).readOnly) {
		var obj = document.getElementById(textarea.name + "-charsdiv");
		if( obj ) {
			obj.style.display = "inline";
		}
		textareaInputCount(textarea);
	}
}
/**
 * 隐藏textarea剩余可输入汉字数的Div（onblur事件，div在/include/tld.jsp中定义）
 * @param textarea 多行文本域对象
 */
function hideCharsInfo(textarea) {
	if (textarea.maxlength && !document.getElementById(textarea.name).readOnly) {
		var obj = document.getElementById(textarea.name + "-charsdiv");
		if( obj ) {
			obj.style.display = "none";
		}
	}
}
/**
 * 计算并限制textarea可输入字节数（onkeyup事件，div在/include/tld.jsp中定义，一个汉字等于两个字节）
 * @param textarea 多行文本域对象
 */
function textareaInputCount(textarea) {
	var strValue = textarea.value; // 当前文本域值
	var strMaxlength = textarea.maxlength; // 可输入最大字节数
	if(!strMaxlength || strMaxlength==null){
		strMaxlength = $(textarea).prop("maxlength");
	}
    var byteCount = 0; // 已经输入的字节数
	var remainlength = strMaxlength; // 剩余字节数
    for (var i = 0; i < strValue.length; i++) {
        var c = strValue.charCodeAt(i);
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            byteCount++; //单字节加1
        } else {
            byteCount += 2;
        }
		remainlength = strMaxlength - byteCount;
		if (byteCount > strMaxlength) {
			textarea.value = strValue.substring(0, i);
			byteCount = strMaxlength;
			remainlength = 0;
			break;
		}
	}
    if (document.getElementById(textarea.name + "-charsdiv")) {
    	document.getElementById(textarea.name + "-charsdiv").innerHTML = parseInt(remainlength/2);
    }
}

/*
 * To custom css style for td of table.
 */
function setTableTdCSS(tableId, attrName, attrValue) {
	$( (tableId + " tr>td") ).each(function(){
		$(this).css(attrName, attrValue);
	});
}


//半角转换为全角函数 
function halfToFullWidth(obj) {
var	txtstring = obj.value;
var tmp = ""; 
	for(var i=0;i<txtstring.length;i++) { 
		if(txtstring.charCodeAt(i)==32) { 
		tmp= tmp+ String.fromCharCode(12288); 
		} else if(txtstring.charCodeAt(i)<127 && txtstring.charCodeAt(i)>32) { 
		 	tmp=tmp+String.fromCharCode(txtstring.charCodeAt(i)+65248); 
		} else{
			tmp += String.fromCharCode(txtstring.charCodeAt(i));
		}
	} 
obj.value=tmp;
} 
//全角转换为半角函数 
function fullToHalfWidth(obj) { 
var str = obj.value;
var tmp = ""; 
for(var i=0;i<str.length;i++) { 
	if(str.charCodeAt(i)==12288) { 
		tmp= tmp+ String.fromCharCode(32); 
	} else if(str.charCodeAt(i)>65280&&str.charCodeAt(i)<65375) { 
		tmp += String.fromCharCode(str.charCodeAt(i)-65248); 
	} else { 
		tmp += String.fromCharCode(str.charCodeAt(i)); 
	} 
} 
obj.value=tmp;
}

//下拉框禁止手动修改
	function banUpdate(){
		sAlert("禁止手动修改!");
		return false;
	}

//弹出页关闭后刷新父页面
	function show_dialog(href,reloadUrl){
		showDialog(href);
		if(reloadUrl){
			location.href = reloadUrl;
		}else{
			if(document.cms_form){
				document.cms_form.submit();
			}else{
				window.location.reload();
			}
		}
	}
//showDialog弹出页面时,再执行跳转不会当前页面跳转，而是又弹出新的页面，用此方法解决。 配合<base target="_self"> 使用
	function go_link(url){
	    var link = document.createElement("a");
		link.href=url;
		document.body.appendChild(link);
		link.click();
	}
	function printOpen(lk){
		var sFeatures = "height=800, width=1500, top=0, left=0, resizable=yes, scrollbars=yes, alwaysLowered=yes,menubar=no,status=no";
	    window.open(lk, "_blank", sFeatures);
	}
	if (!Array.prototype.indexOf)
	{
	    Array.prototype.indexOf = function(elt /*, from*/)
	    {
	        var len = this.length >>> 0;
	        var from = Number(arguments[1]) || 0;
	        from = (from < 0)
	            ? Math.ceil(from)
	            : Math.floor(from);
	        if (from < 0)
	            from += len;
	        for (; from < len; from++)
	        {
	            if (from in this &&
	                this[from] === elt)
	                return from;
	        }
	        return -1;
	    };
	}
    function Money(obj){//验证金额长度和金额类型控制为13位的金额输入
				var  mone=obj.value;
				if(mone.length>20){
					sAlert("最大输入13位");
					obj.value="";
				}
			}
    
function changeButtonPic(){
	if(arguments.length<2)return;
	var className = arguments[0];
	if(!className)className="btn_50";
	var buttonName;
	for(var index = 1;index<arguments.length;index++){
		buttonName = arguments[index];
		if(document.getElementsByName(buttonName)){
			var $button = $(document.getElementsByName(buttonName)).next();
			$button.attr("class",className);
		}
	}
}

// 检查整数类型
function checkInt(mythis){
	var title = mythis.title;
	if (isNaN(mythis.value)){
		sAlert(title + "不是整数,请重输!");
		mythis.value="0"
		mythis.focus();
		return false;
	}else{
		return true;
	}
}
/*
 * 金额格式化
 */
function formatAmt(value) {
	if( value == undefined || typeof(value) == "undefined" || null == value ) {
		document.write("0.00");
		return;
	}
	var amt = String(parseFloat(String(value)).toFixed(2));
	if( parseFloat(amt) < 1000 ) {
		document.write(amt);
		return;
	}
	var r1 = /\d{4}\./;
	if(r1.test(amt))
		amt = amt.replace(/(\d)(\d{3}\.)/,"$1,$2");
	else
		amt = amt.replace(/(\d)(\d{3})$/, "$1,$2");
	var r2 = /\d{4},/;
	while(r2.test(amt))
		amt = amt.replace(/(\d)(\d{3},)/, "$1,$2");
	document.write(amt);
}

//金额转换 ,s--转换对象  n-精确位数
function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 