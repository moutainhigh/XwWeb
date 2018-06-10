<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<script type="text/javascript">
		/*function func_tempUpdate(frm){
			screenLock();
			frm.action="CifPersInfActionTempUpdate_Detail.action";
			frm.submit();
		}
		function func_sync(frm){
			screenLock();
			frm.action="CifPersInfAction_sync.action";
			frm.submit();
		}*/
		
		function idNoconfirm(){
			var id_no = document.getElementsByName('idNo')[0];
			if(checkIdcard(id_no)){
				var num = trim(id_no.value);
				var len = num.length;
				if(len == 15){
		  		if(confirm("是否将15位身份证号转为18位身份证号，按“取消”表示不进行此操作，重新输入18位身份证号!")){
			  		$.get("CifPersInfAction_getIdNo15.action",{id_no:num,rdm:Math.random()},function(data){
			  			if(data!="8888"){
			  				document.cms_form.idNo.value=data;
			  				getSex();
			  			}else{
			  				sAlert("发生错误!");
			  			}
					});
		 		 }else{
		 			 document.cms_form.idNo.value="";
		 			 document.cms_form.idNo.focus();
		 		 }
			}
				if(len == 18){
					getSex();
				}else{
			 		document.cms_form.idNo.focus();
			 	}	
			}
		}
		function getSex(){
			var id = document.getElementsByName("idNo")[0].value;
			ereg = /^(0|2|4|6|8)$/;
			if(id.length == 18){
				var id_no = id.substr(16,1);
				if(ereg.test(id_no)){
					document.getElementsByName("sex")[0].value = '1'; 
				}else{
					document.getElementsByName("sex")[0].value = '0'; 
				}
				
			}else if(id.length == 15){
				var id_no = id.substr(14,1);
				if(ereg.test(id_no)){
					document.getElementsByName("sex")[0].value = '1';
				}else{
					document.getElementsByName("sex")[0].value = '0';
				}
			}
			//获取生日
				var birth = id.substr(6,8);
				document.getElementsByName("birthDay")[0].value = birth;
		}
		/*function setCorpTradeRequeired(){
			var cust_type = $("[name='custType']").val();
			var corp_trade = $("[name='corpTrade']").val();
			if(cust_type=="2" || cust_type=="3"){
				if(corp_trade ==null || corp_trade ==''){
					sAlert("单位所属行业不能为空!");
	      			return false;
	      		}else{
	      			return true;
		      		}
			}
			return true;
		}*/
		function getbirthDay(){
			var id_type = $("[name='idType']").val();
			if(id_type=="0"){
					var id_no = $("[name='idNo']").val();
					var year = id_no.substr(6,4);
					var month = id_no.substr(10,2);
					var date = id_no.substr(12,2);
					var birth = year+"-"+month+"-"+date;
					document.getElementsByName("birthDay")[0].value = birth;
			}else{
				fPopUpCalendarDlg();
			}
		}
		function getGradeDate(){
		    fPopUpCalendarDlg();
		
		}
		 function cifNameCheck(){
			var cif_name = document.getElementsByName("cifName")[0].value;
			var reg1 = /(^\d+$)|(^[a-zA-Z]+$)/g;
			if(cif_name.match(reg1)){
				sAlert("客户名称不能全是英文或者全是数字!");
				document.cms_form.elements["cifName"].value = "";
				return false;
			}
		}
		
		
		 function cifAdd(){
			var reside_addr = document.getElementsByName("resAddr")[0].value;
			var reg1 = /(^\d+$)|(^[a-zA-Z]+$)/g;
			if(reside_addr.match(reg1)){
				sAlert("请输入正确的住宅地址!");
				document.cms_form.elements["resAddr"].value = "";
				return false;
			}
		}
		
		 /*function cifHouseAdd(){
			var cens_addr = document.getElementsByName("censAddr")[0].value;
			var reg1 = /(^\d+$)|(^[a-zA-Z]+$)/g;
			if(cens_addr.match(reg1)){
				sAlert("请输入正确的户籍地址!");
				document.cms_form.elements["cens_addr"].value = "";
				return false;
			}
		}*/
		
		function checkCode(){
			var reside_code = document.getElementsByName("resCode")[0].value;
			if(reside_code.length!=6){
				sAlert("邮编格式错误！");
				document.cms_form.elements["resCode"].value = "";
				return false;
			}
		}
		
		function checkTel(){
			var cell = document.getElementsByName("phoneNo")[0].value;
			if(cell.length!=11){
				sAlert("号码格式错误！ 手机号为：1XXXXXXXXXX!");
				document.cms_form.elements["phoneNo"].value = "";
				return false;
			}
		}
		</script>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="CifPersInfActionInsert.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formcif0002" mode="query" />
						<div class="from_btn">
							<dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="CifPersInfAction_findByPage.action"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
	
</html>