<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<body class="body_bg">
	<script type="text/javascript">
	function certificate(){
	    var idNo = document.getElementsByName("idNo")[0].value;
		var reg =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		var bl = reg.test(idNo);
		if(!bl){
			sAlert("请输入正确的证件号码");
			document.cms_form.elements["idNo"].value = "";
			return false;
	}
	}
		$(document).ready(function(){	
		});
		//当用户机构非总行时，将用户所属部门隐藏
		function if_hid_brdepart(){
			var br_no = document.getElementsByName("br_no")[0].value;
			if(br_no != "00000"){
				$("select[name='br_depart']").parent("td").parent("tr").hide();
				$("select[name='br_depart']").value="";
			}
		}
		function getuser(){
			var user_no = document.getElementsByName("user_no")[0];
			var user_name = document.getElementsByName("user_name")[0];
			var pass_word = document.getElementsByName("pass_word")[0];
			if(user_no == null || typeof(user_no) == "undefined" || "" == user_no.value){
		    	alert("请输入登陆号!");
		    	return false ;
		    }
			$.ajax({
				url : 'SysUserAction_getUser.action',
				type : 'POST',
				async: false,
				data : "user_no=" + user_no.value,
				success : function(data){
				var returnInfo = new Array();
				returnInfo = data.split("*") ;
		  		 if(returnInfo[0]=="0"){//成把 姓名,机构,身份证取出来赋值
			  		user_name.value=returnInfo[1];
			  		id_no.value=returnInfo[2];
			  		pass_word.value="111111";
			       } else if(returnInfo[0]=="error.notlrno"){
					   alert("该操作员在核心不存在,请先在核心添加");	     
					   return false;
			  	   } else if(returnInfo[0]=="error.neibu"){
			           alert("核心返回的内部错误,请与管理员联系");	     
					   return false;	     
			  	   }else{
			  	        alert("通讯异常,稍后请重新发送或请与管理员联系");	     
					   return false;	
					   
			  	   }	   
		    	}
			});
		}

		function nochange(){
			sAlert("禁止手动修改!");
		}
	</script>
	<s:form method="post" theme="simple" name="cms_form"
		action="SysUserActionInsert.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formsys0071" mode="query" />
						<div class="from_btn">
							<dhcc:button typeclass="button3" commit="true" value="保存" action="保存"  ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="SysUserAction_findByPage.action"></dhcc:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
	<script type="text/javascript">
	function selAuthSplitId(){
		funcSysUserPop('user_no,user_name','user_no,user_name');
	}
</script>
</html>