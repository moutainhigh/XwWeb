<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>列表</title>
	</head>
	<%
		String query_name = request.getParameter("query_name");
		String disName = request.getParameter("disName");
		int len = 0;
		String[] arr = null;
		String[] dis_arr = null;
		if (query_name.indexOf(",") > -1) {
			arr = query_name.split(",");
			len = arr.length;
			dis_arr = disName.split(",");
		} else {
			len = 1;
		}
		String clo_name = request.getParameter("col_name");
		String sql = request.getParameter("sql");
		String query_sql = request.getParameter("query_sql");
		String hidden_col = request.getParameter("hidden_col");
		String db_bean_rel = request.getParameter("rel");
		String pageNo = request.getParameter("pageNo");
		String size = request.getParameter("size");
		String startNum = request.getParameter("startNum");
		String endNum = request.getParameter("endNum");
		String totalPage = request.getParameter("totalPage");
		String currPage = request.getParameter("currPage");
		String scene_id = request.getParameter("scene_id");
	%>
	<body class="body_bg" style="background: #F0F8FF">
		<s:form method="post" theme="simple" name="cms_form" action="#">
			<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
							<table width="100%" align="center" class="from_w">
								<tr>
									<div class="tabCont">
										<div class="tabTitle">
											<font size="2.0px" color="#AEA100"><strong>>>业务查询</strong>
											</font>
										</div>
									</div>
								</tr>
								<tr>
									<td>
										<%
											if (len > 1) {
													for (int i = 0; i < len; i++) {
										%>
										&nbsp;&nbsp;
										<font size="2.0px"><%=dis_arr[i]%></font>
										<input type="text" name="<%=arr[i]%>" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<%
											}
												} else {
										%>
										<%=disName%>
										<input type="text" name="<%=query_name%>" />
										<%
											}
										%>
									</td>
								</tr>
							</table>
							<div class="tools_372"><!--
								<dhcc:button value="查询" action="查询" typeclass="btn_80"
									onclick="query()"></dhcc:button>-->
								<input type="button" value="查询" onclick="query()" class="btn_80"/>
								<dhcc:button value="关闭" action="关闭" typeclass="btn_80"
									onclick="func_close()"></dhcc:button>
								<dhcc:button value="清空已选择" action="清空已选择" typeclass="btn_4"
									onclick="func_clean()"></dhcc:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</body>
	<script type="text/javascript">
	document.onkeydown =  function submitForm(evt) {
	  	//IE & Firefox兼容处理
	  	var evt = evt ? evt : (window.event ? window.event : null);
	  	if (evt.keyCode == 13) {
	  		query();
	  	}
	}
	
	function func_close() {
		window.close();
	}

	function func_clean(){
	    var db_rel = "<%=db_bean_rel%>";
	    if( db_rel!=null && db_rel!="" ){
       		var name_arr ;
       		if( db_rel.indexOf(",") > -1 ){
       			var arr = db_rel.split(",");
       			for( var i=0;i<arr.length;i++ ){
       				name_arr = arr[i].split("-");
       				window.parent.dialogArguments.document.getElementsByName(name_arr[1])[0].value="";
       			}
       		}else{
       			name_arr = db_rel.split("-");
       			window.parent.dialogArguments.document.getElementsByName(name_arr[1])[0].value="";
       		}
       	}
       	window.close();
	}

	function query() {
		var quer_name = "<%=query_name%>";
		var scene_id = "<%=scene_id%>";
		var query_sql="<%=query_sql%>";
		var hidden_col = "<%=hidden_col%>";
		var val = "";
		var parms = "";
		if (quer_name.indexOf(",") > 0) {
			var arr = quer_name.split(",");
			for ( var i = 0; i < arr.length; i++) {
				val = document.getElementsByName(arr[i])[0].value;
				if (val != null && val != "" && typeof (val) != 'undefined') {
					parms += arr[i] + "=" + val;
					parms += ",";
				}
			}
		} else {
			parms = quer_name + "="
					+ document.getElementsByName(quer_name)[0].value;
		}
		$.ajax( {
			type : "POST", //请求方式
			url : "<%=contextpath%>/PopParmConfAction_findByPop.action", //请求路径
			cache : false,
			data : {
				'scene_id' : scene_id,
				'parms' : encodeURI(parms),
				'query_sql':encodeURI(query_sql)
			},
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			dataType : 'json',
			success : function(json) {
				var db_rel = json.rel;
				var sql = json.sql;
				var col_name = json.col_name;
				var size = json.size;
				var query_name = json.query_name;
				var disName = json.disName;
				var if_query = json.if_query;
				var pageNo = json.pageNo;
				var pageSize = 0;
				if (parseInt(size) % parseInt(pageNo) == 0) {
					pageSize = parseInt(parseInt(size) / parseInt(pageNo));
				} else {
					pageSize = parseInt(parseInt(size) / parseInt(pageNo) + 1);
				}
				sql = sql.replace(/\%/g, "@");
				var url = "Pop_list.jsp?sql=" + sql + "&rel=" + db_rel + "&size="
						+ size + "&col_name=" + col_name
						+ "&pageNo=<%=pageNo%>&startNum=<%=startNum%>"
						+ "&endNum=<%=endNum%>&totalPage=" + pageSize
						+ "&currPage=<%=currPage%>" +"&hidden_col="+hidden_col+"&if_query="+if_query;
				window.parent.document.all["poplist"].src = url;
			}
		});
	}
</script>
</html>