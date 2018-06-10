function showtrigerType(){
			var trigerType =  $("#trigerType").val();
			if(trigerType=='1'){
				$("#interval").show();
				$("#timing").hide();
			}else if(trigerType=='2'){
				$("#interval").hide();
				$("#timing").show();
			}
		}
		function showtimingMode(){
			var timingMode = $("#timingMode").val();
			if(timingMode=='0'){//分
				$("#min").show();
				$("#hour").hide();
				$("#day").hide();
				$("#week").hide();
				$("#month").hide();
				$("#year").hide();
			}else if(timingMode=='1'){//时
				$("#min").hide();
				$("#hour").show();
				$("#day").hide();
				$("#week").hide();
				$("#month").hide();
				$("#year").hide();
			}else if(timingMode=='2'){//天
				$("#min").hide();
				$("#hour").hide();
				$("#day").show();
				$("#week").hide();
				$("#month").hide();
				$("#year").hide();
			}else if(timingMode=='3'){//周
				$("#min").hide();
				$("#hour").hide();
				$("#day").hide();
				$("#week").show();
				$("#month").hide();
				$("#year").hide();
			}else if(timingMode=='4'){//月
				$("#min").hide();
				$("#hour").hide();
				$("#day").hide();
				$("#week").hide();
				$("#month").show();
				$("#year").hide();
			}else if(timingMode=='5'){//年
				$("#min").hide();
				$("#hour").hide();
				$("#day").hide();
				$("#week").hide();
				$("#month").hide();
				$("#year").show();
			}
		}
		function splitweek(obj){
			
			var arr = new Array();
			arr = obj.split(",");
			for(var i=0;i<arr.length;i++){
				if(arr[i]=='2'){
					document.getElementById("week2").checked = true;
				}
				if(arr[i]=='3'){
					document.getElementById("week3").checked = true;
				}
				if(arr[i]=='4'){
					document.getElementById("week4").checked = true;
				}
				if(arr[i]=='5'){
					document.getElementById("week5").checked = true;
				}
				if(arr[i]=='6'){
					document.getElementById("week6").checked = true;
				}
				if(arr[i]=='7'){
					document.getElementById("week7").checked = true;
				}
				if(arr[i]=='1'){
					document.getElementById("week1").checked = true;
				}
				
			}
	}
	function testJobName(){
		var jobName = document.getElementsByName("tcName")[0].value;
		var reg = /^[0-9a-zA-Z]*$/g;
		
		var bl = reg.test(jobName);
		if(!bl){
			sAlert("任务名称只能是英文或字母");
			document.cms_form.elements["tcName"].value = "";
			return false;
		}
	}	
	function checkNumber(obj){
		var day = obj.value;
		var reg = /[0-9]+$/g;
		if(obj.value==''){
			sAlert("不能为空");
			return false;
		}
		if(!reg.test(day)){
			sAlert("只能为数字");
			
			return false;
		}
	}
	function checkMin(obj){
		var min = obj.value;
		if(min==''){
			sAlert("不能为空");
			return false;
		}
		if(min<=0||min>60){
			sAlert('请输入合法的秒数且不能为0');
			return false;
		}
	}
	function checkAll(){
		var result = true;
		var tcName = $("#tcName").val();
		if(tcName==''){
			sAlert('策略名称不能为空');
			result = false;
		}
		
		if(!checkTime()){
			result = false;
		}
		var trigerType = $("#trigerType").val();
		if(trigerType=='1'){
			var intervalTime = document.getElementsByName("intervalTime");
			if(!checkMonth(intervalTime[1].value)){
				result = false;
			}
			if(!checkWeekDay(intervalTime[2].value)){
				result = false;
			}
			if(!checkDay(intervalTime[3].value)){
				result = false;
			}
			if(!checkHour(intervalTime[4].value)){
				result = false;
			}
			if(!checkMin(intervalTime[5].value)){
				result = false;
			}
			if(!checkSecond(intervalTime[6].value)){
				result = false;
			}
			for(var i=0;i<intervalTime.length;i++){
				
				if(intervalTime[i].value==''){
					sAlert('间隔时间不能为空');
					result = false;
				}
			}
		}
		return result;
		//result = false;
	}

	function checkMonth(value) {
		if (value < 0 || value > 12) {
			alert("间隔时间：月份应该在1-12之间。。");	
			return false;
		}
		return true;
	}
	function checkWeekDay(value) {
		if (value < 0 || value > 7) {
			alert("间隔时间：周数应该在1-7之间。。");	
			return false;
		}
		return true;
	}
	function checkDay(value) {
		if (value < 0 || value > 30) {		
			alert("间隔时间：天数应该在1-30之间。。");		
			return false;
		}
		return true;
	}
	function checkHour(value) {
		if (value < 0 || value >= 24) {
			alert("间隔时间：小时应该在0-24之间。。");	
			return false;
		}
		return true;
	}
	function checkMin(value) {
		if (value < 0 || value >= 60) {		
			alert("间隔时间：分钟数应该在00-60之间。。");	
			return false;
		}
		return true;
	}
	function checkSecond(value) {
		if (value < 0 || value >= 60) {
			alert("间隔时间：秒数应该在00-60之间。。");	
			return false;
		}
		return true;
	}
