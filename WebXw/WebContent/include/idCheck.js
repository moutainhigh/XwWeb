function PersIdNoCheck(idType,idNo){
	if(idType.value==0&&idType.value!=""){
		if(idNo.value!=""){
			if(!CheckIdValue(idNo)){
				idNo.value="";
			} 
		}
	} else if(idType.value=="A"&&idType.value!=""){
		if(idNo.value!=""){
			if(!CheckOrganFormat(idNo.value)){
				idNo.value="";
			}
		}
	} 
}

function PersIdNoCheckExt(idNo){
	if(idNo.value!=""){
		if(!CheckIdValue(idNo)){
			idNo.value="";
		}
	}
}

function CorpIdNoCheck(idNo){
	if(idNo.value!=""){
		if(!CheckOrganFormat(idNo.value)){
			idNo.value="";
		}
	}
	
}

function CheckIdValue(idNo){
	var idCard = idNo.value;
    /*
     * 检查身份证号函数
     * 返回值为true是身份证号符合规则 为false身份证号不符合规则
     */	
    var id=idCard;
    var id_length=id.length;

    if (id_length==0){
        alert("请输入身份证号码!");
        return false;
    }
    if (id_length!=15 && id_length!=18){
        alert("身份证号长度应为15位或18位！");
        return false;
    }

    if (id_length==15){
		var re = /^\d{15}$/;
		if(!re.test(id)){
			alert("15位身份证号必须为数字");
			return false;
		}
        yyyy="19"+id.substring(6,8);
        mm=id.substring(8,10);
        dd=id.substring(10,12);

        if (mm>12 || mm<=0){
            alert("输入身份证号,月份非法！");
            return false;
        }

        if (dd>31 || dd<=0){
            alert("输入身份证号,日期非法！");
            return false;
        }

        birthday=yyyy+ "-" +mm+ "-" +dd;

        if ("13579".indexOf(id.substring(14,15))!=-1){
            sex="1";
        }else{
            sex="2";
        }
        var id17 = id.substring(0,6)+"19"+id.substring(6);
        id = id17 + GetIdVerifyBit(id17);
        idNo.value=id;
    }else if (id_length==18){
		var re = /^\d{17}[\dXx]$/;
		if(!re.test(id)){
			alert("身份证前17位必须为数字,第18位为数字或X");
		  	return false;
		}

        yyyy=id.substring(6,10);
        
        if(yyyy>new Date().getFullYear() || yyyy<1800){
            alert("输入身份证号,年度非法！");
            return false;
        }

        mm=id.substring(10,12);
        if (mm>12 || mm<=0){
            alert("输入身份证号,月份非法！");
            return false;
        }

        dd=id.substring(12,14);
        if (dd>31 || dd<=0){
            alert("输入身份证号,日期非法！");
            return false;
        }

        if (id.charAt(17)=="x" || id.charAt(17)=="X")
        {
            if ("x"!=GetIdVerifyBit(id) && "X"!=GetIdVerifyBit(id)){
                alert("身份证校验错误，请检查最后一位！");
                return false;
            }

        }else{
            if (id.charAt(17)!=GetIdVerifyBit(id)){
                alert("身份证校验错误，请检查最后一位！");
                return false;
            }
        }

        birthday=id.substring(6,10) + "-" + id.substring(10,12) + "-" + id.substring(12,14);
        if ("13579".indexOf(id.substring(16,17)) > -1){
            sex="1";
        }else{
            sex="2";
        }
    }

    return true;
}
//通过身份证号计算校验位的数值
function GetIdVerifyBit(id){
    var result;
    var nNum=eval(id.charAt(0)*7+id.charAt(1)*9+id.charAt(2)*10+id.charAt(3)*5+id.charAt(4)*8+id.charAt(5)*4+id.charAt(6)*2+id.charAt(7)*1+id.charAt(8)*6+id.charAt(9)*3+id.charAt(10)*7+id.charAt(11)*9+id.charAt(12)*10+id.charAt(13)*5+id.charAt(14)*8+id.charAt(15)*4+id.charAt(16)*2);
    nNum=nNum%11;
    switch (nNum) {
       case 0 :
          result="1";
          break;
       case 1 :
          result="0";
          break;
       case 2 :
          result="X";
          break;
       case 3 :
          result="9";
          break;
       case 4 :
          result="8";
          break;
       case 5 :
          result="7";
          break;
       case 6 :
          result="6";
          break;
       case 7 :
          result="5";
          break;
       case 8 :
          result="4";
          break;
       case 9 :
          result="3";
          break;
       case 10 :
          result="2";
          break;
    }
    //document.write(result);
    return result;
}

	/*
     * 检查组织机构号函数
     * 返回值为true是组织机构号符合规则 为false组织机构号不符合规则
     */
function CheckOrganFormat(code)
	{
		//旧的组织机构代码证规则
		if(code.length!=10){
			alert("组织机构代码必须为10位！");
			return false;
		}
		var old_style = /^X\d{9}$/;
		if(old_style.test(code)){
			return true;
		}
		//现有的组织机构代码证规则
		var w_i = new Array();
		var c_i = new Array();
		var s = 0;
		var financecode =new Array();
		
		financecode[0]=code.charCodeAt(0);
		financecode[1]=code.charCodeAt(1);
		financecode[2]=code.charCodeAt(2);
		financecode[3]=code.charCodeAt(3);
		financecode[4]=code.charCodeAt(4);
		financecode[5]=code.charCodeAt(5);
		financecode[6]=code.charCodeAt(6);
		financecode[7]=code.charCodeAt(7);
		financecode[8]=code.charCodeAt(8);
		financecode[9]=code.charCodeAt(9);
		financecode[10]=code.charCodeAt(10);
		

		if (code=="00000000-0"){
			alert("请输入正确的组织机构代码");
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
		if (financecode[8] != 45){
			alert("请输入正确的组织机构代码");	
			return false;
			}
		var c;
		for (var i = 0; i < 10; i++)
		{
			c = financecode[i];
			if (c <= 122 && c >= 97)
			{
				alert("请输入正确的组织机构代码");
				return false;
			}
		}

		fir_value = financecode[0];
		sec_value = financecode[1];
		if (fir_value >= 65 && fir_value <= 90)
			c_i[0] = (fir_value + 32) - 87;
		else
		if (fir_value >= 48 && fir_value <= 57)
			c_i[0] = fir_value - 48;
		else
		{
			alert("请输入正确的组织机构代码");
			return false;
		}
		s += w_i[0] * c_i[0];
		if (sec_value >= 65 && sec_value <= 90)
			c_i[1] = (sec_value - 65) + 10;
		else
		if (sec_value >= 48 && sec_value <= 57)
			c_i[1] = sec_value - 48;
		else
		{
			alert("请输入正确的组织机构代码");
			return false;
		}
		s += w_i[1] * c_i[1];
		for (var j = 2; j < 8; j++)
		{
			if (financecode[j] < 48 || financecode[j] > 57){
				alert("请输入正确的组织机构代码");
				return false;
				}
				
			c_i[j] = financecode[j] - 48;
			s += w_i[j] * c_i[j];
		}

		c = 11 - s % 11;
		if(financecode[9] == 88 && c == 10 || c == 11 && financecode[9] == 48 || c == financecode[9] - 48)
		{
			return true;
		}else{
			alert("请输入正确的组织机构代码");			
			return false;
		}
	}
	