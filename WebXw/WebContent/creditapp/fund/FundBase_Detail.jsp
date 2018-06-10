<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
	</head>
	<body class="body_bg">
	<s:form method="post" theme="simple" name="cms_form"
		action="FundBaseActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<dhcc:formTag property="formfund0002" mode="query" />
						
						<input type="hidden" value="<s:property value="reDeem1"/>" name="reDeem1">
						
						<div class="from_btn">
						  <s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存" onclick="check(cms_form)" ></dhcc:button>
						  </s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</body>
	<script type="text/javascript">
	function check(frm) {
	 var repayType = document.getElementsByName("repayType")[0].value;
	 var repayDay = document.getElementsByName("repayDay")[0].value;
	 var fdType = document.getElementsByName("fdType")[0].value;
	 repayType = repayType.substr(0,1);
	 
	 if(fdType == 01 && (repayType == null || repayType == undefined || repayType == ''))
          {
		     sAlert("资金类型为优先级，清算周期必填!");
		     return false;
		  }
	 if(repayType == 1 && (repayDay == null || repayDay == undefined || repayDay == ''))
	      {
	         sAlert("清算周期类型为自然结息方式，指定清算日必填 !");
	         return false;
		  }
	 if(repayDay > 28)
          {
		     sAlert("指定清算日不能大于每月28号!");
		     document.getElementsByName("repayDay")[0].value = "";
		     return false;
		  }	  
     if(repayType != 1 && repayDay.length > 0)
          {
		     sAlert("清算周期类型为信托结息方式，无指定清算日!");
		     document.getElementsByName("repayDay")[0].value = "";
		     return false;
		  }   
  	    
		frm.submit();  
	}
	 function judge_repay_day() {
	 var repayType = document.getElementsByName("repayType")[0].value;
	 var repayDay = document.getElementsByName("repayDay")[0].value; 
	 repayType = repayType.substr(0,1);
	 if(repayType == 1 && (repayDay == null || repayDay == undefined || repayDay == ''))
	      {
	         sAlert("清算周期类型为自然结息方式，指定清算日必填 !");
	         return false;
		  }
	 if(repayDay > 28)
          {
		     sAlert("清算日不能大于每月28号!");
		     document.getElementsByName("repayDay")[0].value = "";
		     return false;
		  }	  
     if(repayType != 1 && repayDay.length > 0)
          {
		     sAlert("清算周期类型为信托结息方式，无指定清算日!");
		     document.getElementsByName("repayDay")[0].value = "";
		     return false;
		  } 
	 if(repayDay.length != 2)
         {
		     sAlert("指定清算日长度为2位，例如02,12!");
		     document.getElementsByName("repayDay")[0].value = "";
		     return false;
		  } 	   
	 	  
	}
	</script>
</html>