<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<%@ include file="/include/incTab.jsp"%>
<html>
	<head>
		<title>还款方案</title>
		<script type="text/javascript">
			function jumplist(){ 
				window.location="AcReplanParmAction_findByPage.action";
			}
		</script>
	</head>
	<body class="body_bg">
	<div class="right_bg">	
	  <form action="#">
	    <table width="100%" align="center" height="90%">
	      <tr><td>
			<div class="tab_btn_div">
			<!--	<dhcc:button typeclass="back_btn" value="返回列表" action="返回列表" onclick="jumplist();"></dhcc:button> -->
			<dhcc:button typeclass="button3" value="返回列表" action="返回" onclick="javascript:window.location='AcReplanParmAction_findByPage.action'"></dhcc:button>
			</div>
		</td></tr>
	      <tr>
	        <td>
	          <dhcc:tabTag tabList="tabList"></dhcc:tabTag>
	        </td>
	      </tr>
	    </table>
  		 <input type="hidden" value="<s:property value="tpageNo"/>" name="tpageNo">
		<input type="hidden" value="<s:property value="tpageSize"/>" name="tpageSize">
		<input type="hidden" value="<s:property value="tappNo"/>" name="tappNo">
		<input type="hidden" value="<s:property value="tcifNo"/>" name="tcifNo">
		<input type="hidden" value="<s:property value="tcifName"/>" name="tcifName">
	  </form>
	</div>
	</body>
</html>