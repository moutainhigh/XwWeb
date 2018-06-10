/**去掉空格**/
function trim(str) {
    return str.replace(/\s+/g, "");
}

/**通过elename得到element**/
function getEleByName(elename) {
    return document.getElementsByName(elename)[0];
}

/**通过element得到alt值**/
function getAlt(ele) {
    return ele.getAttribute("alt");
}
/**检测中征码**/
function checkCreditNo(ele) {
    var alt = '中征码';
    if (ele == 'undefined' || ele == null) {
        sAlert(alt + "不存在");
        ele.value = "";
        return false;
    }
    var financecode = trim(ele.value);
    if (financecode == "") {
        if (ele.getAttribute('emptyok') == 'false') {
            sAlert(alt + "不能为空");
            ele.focus();
            ele.value = "";
            return false;
        } else {
            return true;
        }
    }
    if (financecode.match(/[A-Z0-9]{16}/) == null) {
        sAlert(alt + "错误!");
        ele.focus();
        ele.value = "";
        return false;
    }

    var w_i = new Array(14);
    var c_i = new Array(14);
    var j, s = 0;
    var checkid = 0;
    var c, i;

    w_i[0] = 1;
    w_i[1] = 3;
    w_i[2] = 5;
    w_i[3] = 7;
    w_i[4] = 11;
    w_i[5] = 2;
    w_i[6] = 13;
    w_i[7] = 1;
    w_i[8] = 1;
    w_i[9] = 17;
    w_i[10] = 19;
    w_i[11] = 97;
    w_i[12] = 23;
    w_i[13] = 29;

    for (j = 0; j < 14; j++) {
        if (financecode.charAt(j) >= '0' && financecode.charAt(j) <= '9') {
            c_i[j] = financecode.charCodeAt(j) - '0'.charCodeAt(0);
        }
        else if (financecode.charAt(j) >= 'A' && financecode.charAt(j) <= 'Z') {
            c_i[j] = financecode.charCodeAt(j) - 'A'.charCodeAt(0) + 10;
        }
        else {
            sAlert(alt + "编码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
        s = s + w_i[j] * c_i[j];
    }

    c = 1 + (s % 97);

    checkid = (financecode.charCodeAt(14) - '0'.charCodeAt(0)) * 10 + financecode.charCodeAt(15) - '0'.charCodeAt(0);
    if (c != checkid) {
        sAlert(alt + "错误!");
        ele.focus();
        ele.value = "";
        return false;
    }
    return true;
}

//校验身份证号码：
function CheckIDNo(ele) {
    var alt = '身份证号码';
    var num = trim(ele.value);
    if (typeof(num) == 'undefined' || num == null || num == "") {
        return true;
    }
    var re;
    var len = num.length;

    if (len == 15) {
        if (isNaN(num)) {
            sAlert(alt + "不是数字！");
            ele.focus();
            ele.value = "";
            return false;
        }
        re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
    } else if (len == 18) {
        if (isNaN(num.substring(0, 17))) {
            sAlert("输入的身份证位数前17位不是数字！");
            return false;
        }
        re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\S)$/);
    } else {
        sAlert(alt + "位数不对！");
        ele.value = "";
        ele.focus();
        return false;
    }
    var a = num.match(re);
    if (a != null) {
        if (len == 15) {
            var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
            var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
        }
        else {
            var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
            var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4] && D.getDate() == a[5];
        }
        if (!B) {
            sAlert(alt + a[0] + " 里出生日期不对！");
            ele.focus();
            ele.value = "";
            return false;
        }
    }
    return true;
}

//新添加的校验身份证号码，如果输入格式错误，不清空已填入的数据，提示错误，定位焦点并返回false（建议在提交表单前先调用此方法进行验证）：
function checkIdcard(ele) {
	var idcard = trim(ele.value);
	if (typeof(idcard) == 'undefined' || idcard == null || idcard == "") {
        return true;
    }
    var Errors = new Array("验证通过!", "身份证号码位数不对!", "身份证号码出生日期超出范围或含有非法字符!", "身份证号码校验错误!", "身份证地区非法!");
    var area = {
        11 : "北京", 12 : "天津", 13 : "河北", 14 : "山西", 15 : "内蒙古", 21 : "辽宁", 22 : "吉林", 23 : "黑龙江",
        31 : "上海", 32 : "江苏", 33 : "浙江", 34 : "安徽", 35 : "福建", 36 : "江西", 37 : "山东", 41 : "河南",
        42 : "湖北", 43 : "湖南", 44 : "广东", 45 : "广西", 46 : "海南", 50 : "重庆", 51 : "四川", 52 : "贵州",
        53 : "云南", 54 : "西藏", 61 : "陕西", 62 : "甘肃", 63 : "青海", 64 : "宁夏", 65 : "新疆", 71 : "台湾",
        81 : "香港", 82 : "澳门", 91 : "国外"
    }
    var retflag = false;
    var Y, JYM, S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    //地区检验
    if (area[parseInt(idcard.substr(0, 2))] == null) {
    	sAlert(Errors[4]);
    	ele.focus();
        return false;
    }
    //身份号码位数及格式检验
    switch (idcard.length) {
    case 15:
        if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
            ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; //测试出生日期的合法性
        } else {
            ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; //测试出生日期的合法性
        }
        if (ereg.test(idcard)) {
        	return true;
        } else {
            sAlert (Errors[2]);
            ele.focus();
            return false;
        }
        break;
    case 18:
        //18位身份号码检测
        //出生日期的合法性检查 
        //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9] | 30) | 02(0[1 - 9] | [1 - 2][0 - 9]))
        //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9] | 30) | 02(0[1 - 9] | 1[0 - 9] | 2[0 - 8]))
        if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
            ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; //闰年出生日期的合法性正则表达式
        } else {
            ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; //平年出生日期的合法性正则表达式
        }
        if (ereg.test(idcard)) { //测试出生日期的合法性
            //计算校验位
            S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3;
            Y = S % 11;
            M = "F";
            JYM = "10X98765432";
            M = JYM.substr(Y, 1); //判断校验位
            if (M == idcard_array[17]) { //检测ID的校验位
            	return true;
            } else {
            	sAlert (Errors[3]);
            	ele.focus();
                return false;
            }
        } else {
        	sAlert (Errors[2]);
        	ele.focus();
            return false;
        }
        break;
    default:
        sAlert (Errors[1]);
    	// ele.focus();
    	return false;
        break;
    }
}

/**测试组织机构代码**/
function checkORG(ele) {
    var alt = '组织机构代码';
    if (ele.value == 'undefined' || ele.value == null) {
        sAlert("组织机构代码不存在");
        ele.focus();
        ele.value = "";
        return false;
    }
    var financecode = trim(ele.value);
    if (financecode == "") {
        if (ele.getAttribute('emptyok') == 'false') {
            sAlert("组织机构代码不能为空");
            ele.focus();
            ele.value = "";
            return false;
        } else {
            return true;
        }
    }
    var fir_value, sec_value;
    var w_i = new Array(8);
    var c_i = new Array(8);
    var j, s = 0;
    var c, i;

    if (financecode == "00000000-0") {
        sAlert("组织机构代码错误!");
        ele.focus();
        ele.value = "";
        return false;
    }

    w_i[0] = 3;
    w_i[1] = 7;
    w_i[2] = 9;
    w_i[3] = 10;
    w_i[4] = 5;
    w_i[5] = 8;
    w_i[6] = 4;
    w_i[7] = 2;

    if (financecode.charAt(8) != '-') {
        sAlert("组织机构代码错误!");
        ele.focus();
        ele.value = "";
        return false;
    }

    for (i = 0; i < 10; i++) {
        c = financecode.charAt(i);
        if (c <= 'z' && c >= 'a') {
            sAlert("组织机构代码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
    }

    fir_value = financecode.charCodeAt(0);
    sec_value = financecode.charCodeAt(1);

    if (fir_value >= 'A'.charCodeAt(0) && fir_value <= 'Z'.charCodeAt(0)) {
        c_i[0] = fir_value + 32 - 87;
    } else {
        if (fir_value >= '0'.charCodeAt(0) && fir_value <= '9'.charCodeAt(0)) {
            c_i[0] = fir_value - '0'.charCodeAt(0);
        } else {
            sAlert("组织机构代码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
    }
    if(!isValidEntpCode(financecode)){
    	sAlert("组织机构代码错误!");
		ele.focus();
		ele.value = "";
		return false;
    }
    return true;
}
function isValidEntpCode(code) {
        var ws = [3, 7, 9, 10, 5, 8, 4, 2];
        var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var reg = /^([0-9A-Z]){8}-[0-9|X]$/;
        if (!reg.test(code)) {
                return false;
        }
        var sum = 0;
        for (var i = 0; i < 8; i++) {
			sum += str.indexOf(code.charAt(i)) * ws[i];
        }
        var C9 = 11 - (sum % 11);
        var last = code.charAt(9);
        if (C9 == 11) {
			return '0' == last;
        } else if (C9 == 10) {
			return 'X' == last
        } else {
			return C9 == last;
        }
}
/**
 * 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
 * @param telno 要检验的数据
 */
function checkTel(ele) {
    return checkTelAndMobile(ele);
}

function checkTelOnly(ele) {
	telno = trim(ele.value);
	if (typeof(telno) == 'undefined' || telno == null || telno == "") {
		return true;
	}
	var patrntel = /^0?\d{2,3}\-\d{6,8}$/;
	if (!patrntel.test(telno)) {
		sAlert("请输入正确的电话号码\n例如:\"区号-1234567\"");
		ele.focus();
		ele.value = "";
		return false;
	}
	ele.value = telno;
	return true;
}

/**
 * 校验手机号码
 * @param telno 要检验的数据
 */
function checkMobile(ele) {
    return checkMobileOnly(ele);
}

/**
 * 校验手机号码
 * @param telno 要检验的数据
 */
function checkMobileOnly(ele) {
	telno = trim(ele.value);
	if (typeof(telno) == 'undefined' || telno == null || telno == "") {
		return true;
	}
	var patrnmobile = /(^(?:13\d|15[0-9]|18[0-9]|17[0-9])-?\d{5}(\d{3}|\*{3})$)/;
	if (!patrnmobile.test(telno)) {
		sAlert("请输入正确的手机号码\n例如:\"139********\"");
		ele.focus();
		ele.value = "";
		return false;
	}
	ele.value = telno;
	return true;
}
/**
 * 校验机构信用代码证
 * @param telno 要检验的数据
 */
function checkCredCode(ele) {
	credcode = trim(ele.value);
	if (typeof(credcode) == 'undefined' || credcode == null || credcode == "") {
		return true;
	}
	var patrnmobile = /^[0-9a-zA-Z]{0,18}$/;
	if (!patrnmobile.test(credcode)) {
		sAlert("请输入正确的机构信用代码证");
		ele.focus();
		ele.value = "";
		return false;
	}
	ele.value = credcode;
	return true;
}
/**
 * 校验纯数字输入
 * @param telno 要检验的数据
 */
function checkCode(ele) {
	code = trim(ele.value);
	if (typeof(code) == 'undefined' || code == null || code == "") {
		return true;
	}
	var patrnmobile = /^[0-9]*$/;
	if (!patrnmobile.test(code)) {
		sAlert("请输入纯数字");
		ele.focus();
		ele.value = "";
		return false;
	}
	ele.value = code;
	return true;
}

/**
 * 校验手机号码 或者 座机号码/传真号码
 * @param telno 要检验的数据
 */
function checkTelAndMobile(ele) {
	telno = trim(ele.value);
	if (typeof(telno) == 'undefined' || telno == null || telno == "") {
		return true;
	}
	var patrntel = /^0?\d{2,3}\-\d{6,8}$/;
	var patrnmobile = /(^(?:13\d|15[0-9]|18[0-9]|17[0-9])-?\d{5}(\d{3}|\*{3})$)/;
	if (!patrntel.test(telno) && !patrnmobile.test(telno)) {
		sAlert("请输入正确的手机号码或电话号码\n例如:\"139********\"或\"区号-1234567\"");
		ele.focus();
		ele.value = "";
		return false;
	}
	ele.value = telno;
	return true;
}


/**
 * 校验数据是否为合法的email地址
 * @param umail 要校验的数据
 */
function checkemail(ele) {
    var umail = trim(ele.value);
    var alt = 'email地址';
    if (typeof(umail) == 'undefined' || umail == null || umail == "") {
        return true;
    }

    var re = /^[\-!#\$%&'\*\+\\\.\/0-9=\?A-Z\^_`a-z{|}~]+@[\-!#\$%&'\*\+\\\.\/0-9=\?A-Z\^_`a-z{|}~]+(\.[\-!#\$%&'\*\+\\\.\/0-9=\?A-Z\^_`a-z{|}~]+)+$/;
    var msgLang = 1;
    if (!re.test(umail)) {
        sAert(alt + "格式错误！");
        ele.focus();
        ele.value = "";
        return false;
    }
    ele.value = umail;
    return true;
}

/**
 * 校验邮政编码
 * @param zip 要检验的数据
 */
function checkPostalCode(ele) {
    var alt = '邮政编码';
    if (ele == 'undefined' || ele == null) {
        sAlert(alt + "不存在");
        ele.value = "";
        return false;
    }
    eleVal = trim(ele.value);
    if (eleVal == "") {
        if (ele.getAttribute('emptyok') == 'false') {
            sAlert(alt + "不能为空");
            ele.focus();
            ele.value = "";
            return false;
        } else {
            return true;
        }
    }
    var patrn = /^[0-9]{6}$/;
    if (!patrn.test(eleVal)) {
        sAlert(alt + "格式错误！");
        ele.focus();
        ele.value = "";
        return false;
    }
    ele.value = eleVal;
    return true;
}

/**
 * 校验比率
 */
function checkRate(ele) {
    var rate = trim(ele.value);
    var alt = '比率';
    if (typeof(rate) == 'undefined' || rate == null || rate == "") {
        return true;
    }
    if (!isNaN(parseFloat(rate))) {
        rate = parseFloat(rate);
        if (rate > 100) {
            sAlert(alt + "不能大于100%");
            ele.value = "";
        }
    } else {
        ele.focus();
        ele.value = "";
    }
}

/**
 * 把ele的值设为空
 */
function cleanInput(ele) {
	if (ele == 'undefined' || ele == null) {
		return;
	}
	ele.value = "";
}
/**
 * 营业执照号的规则校验
 * @param {Object} busCode
 * @return {TypeName} 
 */
function isValidBusCode(license){
	var license = $("[name='"+license+"']").val();
	var register_type = $("[name='register_type']");
	if(register_type!=null){
		if(register_type.val()!='A'){
			return true;
		}
	}
	var ret=false;
	if(license.length==18){
		ret=true;
	}else if(license.length==15){
	    var sum=0;
	    var s=[];
	    var p=[];
	    var a=[];
	    var m=10;
	    p[0]=m;
	    for(var i=0;i<license.length;i++){
	       a[i]=parseInt(license.substring(i,i+1),m);
	       s[i]=(p[i]%(m+1))+a[i];
	       if(0==s[i]%m){
	    	   p[i+1]=10*2;
	       }else{
	           p[i+1]=(s[i]%m)*2;
	       }    
	    }                                       
	    if(1==(s[14]%m)){
	   		ret=true;
		}else{
				    	
	    	sAlert("营业执照编号错误!");
	   		$("input[name='license']").val("");//清空输入域内容
	   		ret=false;
	    }
	  }else {	        
		    sAlert("营业执照编号输入有误，请重新输入!");
		    $("input[name='license']").val("");//清空输入域内容
	  }
	 return ret;
}

/**测试社会信用代码**/
function checkScredit(ele) {
    var alt = '社会信用代码';
    if (ele.value == 'undefined' || ele.value == null) {
        sAlert("社会信用代码不存在");
        ele.focus();
        ele.value = "";
        return false;
    }
    var financecode = trim(ele.value);
    if (financecode == "") {
        if (ele.getAttribute('emptyok') == 'false') {
            sAlert("社会信用代码不能为空");
            ele.focus();
            ele.value = "";
            return false;
        } else {
            return true;
        }
    }
    var fir_value, sec_value;
    var c_i = new Array(8);
    var j, s = 0;
    var c, i;

    if (financecode == "000000000000000000") {
        sAlert("社会信用代码错误!");
        ele.focus();
        ele.value = "";
        return false;
    }

    for (i = 0; i < 18; i++) {
        c = financecode.charAt(i);
        if (c <= 'z' && c >= 'a') {
            sAlert("社会信用代码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
    }

	for (i = 2; i < 8; i++) {
        c = financecode.charAt(i);
        if (!(c <= '9' && c >= '0')) {
            sAlert("社会信用代码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
    }

    fir_value = financecode.charCodeAt(0);
    sec_value = financecode.charCodeAt(1);

    if (fir_value >= 'A'.charCodeAt(0) && fir_value <= 'Z'.charCodeAt(0)) {
        c_i[0] = fir_value + 32 - 87;
    } else {
        if (fir_value >= '0'.charCodeAt(0) && fir_value <= '9'.charCodeAt(0)) {
            c_i[0] = fir_value - '0'.charCodeAt(0);
        } else {
            sAlert("社会信用代码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
    }
    
	var ws = [1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28];
        var str = '0123456789ABCDEFGHJKLMNPQRTUWXY';
        var reg = /^([0-9A-Z]){2}([0-9]){6}([0-9A-Z]){9}[0-9|A-H|J-N|P-R|T-U|W-Y]$/;
        if (!reg.test(financecode)) {
        	sAlert("社会信用代码错误!");
            ele.focus();
            ele.value = "";
            return false;
        }
        var sum = 0;
        for (var i = 0; i < 17; i++) {
			sum += str.indexOf(financecode.charAt(i)) * ws[i];
        }
        var C18 = 31 - (sum % 31);
        var last = financecode.charAt(17);
        if (C18 == 10) {
			C18 = 'A';
        } else if (C18 == 11) {
			 C18 = 'B';
        } else if (C18 == 12) {
			 C18 = 'C';
        } else if (C18 == 13) {
			 C18 = 'D';
        } else if (C18 == 14) {
			 C18 = 'E';
        } else if (C18 == 15) {
			 C18 = 'F';
        } else if (C18 == 16) {
			 C18 = 'G';
        } else if (C18 == 17) {
			 C18 = 'H';
        } else if (C18 == 18) {
			 C18 = 'J';
        } else if (C18 == 19) {
			 C18 = 'K';
        } else if (C18 == 20) {
			 C18 = 'L';
        } else if (C18 == 21) {
			 C18 = 'M';
        } else if (C18 == 22) {
			 C18 = 'N';
        } else if (C18 == 23) {
			 C18 = 'P';
        } else if (C18 == 24) {
			 C18 = 'Q';
        } else if (C18 == 25) {
			 C18 = 'R';
        } else if (C18 == 26) {
			 C18 = 'T';
        } else if (C18 == 27) {
			 C18 = 'U';
        } else if (18 == 28) {
			 C18 = 'W';
        } else if (C18 == 29) {
			 C18 = 'X';
        } else if (C18 == 30) {
			 C18 = 'Y';
        }
		if(C18 != last){
			sAlert("社会信用代码错误!");
            ele.focus();
            ele.value = "";
            return false;
		}else{
			return true;
		}
	}