	function testJobName(){
		var jobName = document.getElementsByName("jobName")[0].value;
		var reg = /^[0-9a-zA-Z]*$/g;
		var bl = reg.test(jobName);
		if(!bl){
			sAlert("任务名称只能是英文或字母");
			document.cms_form.elements["jobName"].value = "";
			return false;
		}
	}	
	function showMode(obj){
	
		if(obj.value=="2")
		{
		document.getElementById("day").style.display = "";
		document.getElementById("week").style.display = "none";
		document.getElementById("month").style.display = "none";
		document.getElementById("year").style.display = "none";
		document.getElementById("hour").style.display = "none";
		document.getElementById("min").style.display = "none";
		}
		if(obj.value=="3")
		{
		document.getElementById("day").style.display = "none";
		document.getElementById("week").style.display = "";
		document.getElementById("month").style.display = "none";
		document.getElementById("year").style.display = "none";
		document.getElementById("hour").style.display = "none";
		document.getElementById("min").style.display = "none";
		}
		if(obj.value=="4")
		{
		document.getElementById("day").style.display = "none";
		document.getElementById("week").style.display = "none";
		document.getElementById("month").style.display = "";
		document.getElementById("year").style.display = "none";
		document.getElementById("hour").style.display = "none";
		document.getElementById("min").style.display = "none";
		}
		if(obj.value=="5")
		{
		document.getElementById("day").style.display = "none";
		document.getElementById("week").style.display = "none";
		document.getElementById("month").style.display = "none";
		document.getElementById("year").style.display = "";
		document.getElementById("hour").style.display = "none";
		document.getElementById("min").style.display = "none";
		}
		if(obj.value=="")
		{
		document.getElementById("day").style.display = "none";
		document.getElementById("week").style.display = "none";
		document.getElementById("month").style.display = "none";
		document.getElementById("year").style.display = "none";
		document.getElementById("hour").style.display = "none";
		document.getElementById("min").style.display = "none";
		}
		
		if(obj.value=="1")
		{
		document.getElementById("day").style.display = "none";
		document.getElementById("week").style.display = "none";
		document.getElementById("month").style.display = "none";
		document.getElementById("year").style.display = "none";
		document.getElementById("hour").style.display = "";
		document.getElementById("min").style.display = "none";
		
		}
		if(obj.value=="0")
		{
		document.getElementById("day").style.display = "none";
		document.getElementById("week").style.display = "none";
		document.getElementById("month").style.display = "none";
		document.getElementById("year").style.display = "none";
		document.getElementById("hour").style.display = "none";
		document.getElementById("min").style.display = "";
		
		}
	}
	function showTrigger(obj){
		if(obj.value=="1"){
		document.getElementById("table1").style.display = "";
		document.getElementById("table2").style.display = "none";
		}
		if(obj.value=="2"){
		document.getElementById("table2").style.display = "";
		document.getElementById("table1").style.display = "none";
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
	function checkHour(obj){
		var hour = obj.value;
		var reg = /[0-9]+$/g;
		if(obj.value==''){
			sAlert("不能为空");
			return false;
		}
		if(obj.value=='0'){
			sAlert("不能为0");
			$('#timhour').val('');
			return false;
		}
		if(!reg.test(day)){
			sAlert("只能为数字");
			return false;
		}
	}
	function check(){
		var alltime =  new Array();
		alltime[0] = document.getElementById("years").value;
		alltime[1] = document.getElementById("monthes").value;
		alltime[2] = document.getElementById("weeks").value;
		alltime[3] = document.getElementById("days").value;
		alltime[4] = document.getElementById("houres").value;
		alltime[5] = document.getElementById("mins").value;
		alltime[6] = document.getElementById("seconds").value;
		if(alltime[0]==""||alltime[1]==""||alltime[2]==""||alltime[3]==""||alltime[4]==""||alltime[5]==""||alltime[6]==""){
			sAlert("间隔时间不能为空");
			return false;
		}else{
			var repeatCount = document.getElementById("repeatCount").value;
			if(repeatCount==""||repeatCount==null){
				sAlert("重复次数不能为空");
				return false;
			}else{
				document.getElementById("allTimes").innerText = alltime.toString();
				return true;
			}
		}	
	}
	function checkbox(){
		var inputs = document.getElementsByName("week");
		var a = 0;
		var result = true;
		for(var i=0;i<inputs.length;i++){
			if(!inputs[i].checked){
				a++;
			}
			if(a==inputs.length){
				sAlert('按周至少选择一个');      	
        		result = false;      			
			}
		}
		return result;
	}
	function checkAll(){
		var triggerType = document.getElementsByName("triggerType")[0].value;
		var bl = true;
		var reg = /^\d+$/g;
		if(triggerType=='1'){
			if(check()){
				bl = true;
			}else{
				bl = false;
			}	
		}
		else{
	
		var a = checkMode();	
			if(checkMode()){
				if(document.getElementById("mode").value=='2'){//天
					if(!reg.test(document.getElementById("timDay").value)){
						sAlert("天数为数字");
						$("#timDay").val("");
						bl = false;
					}
				}
				if(document.getElementById("mode").value=='3'){//周
					bl = checkbox();
				}
				if(document.getElementById("mode").value=='4'){//月
					if(!reg.test(document.getElementById("timMonth").value)){
						sAlert("天数为数字");
						$("#timMonth").val("");
						bl = false;
					}
				}
				if(document.getElementById("mode").value=='5'){//年
					var month = document.getElementById("timYear").value;
					if(!reg.test(month)){
						sAlert("月数为数字");
						$("#timYear").val("");
						bl = false;
					}else{
						var monthNum = parseInt(month);
						if(monthNum<1||12<monthNum){
							sAlert("请输入合法的月份");
							$("#timYear").val("");
							bl = false;
						}
					}
					if(isNaN(document.getElementById("timYearday").value)){
						sAlert("天数为数字");
						$("#timYearday").val("");
						bl = false;
					}
				}	
			}
		}
		return bl;
	}
	
	function starttime(){
		var obj = document.getElementById("startTime").value;
			if(obj.length==0){
				sAlert("不能为空");
				return false;
			}
	}
	function checkMode(){
		var trigger = document.getElementById("mode").value;
		
		var result = true;
		if(trigger==''||trigger==null){
			sAlert("请选择模式");
			result = false;
		}
		
		return result;
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