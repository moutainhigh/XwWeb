<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="app.util.DBUtils,java.sql.Connection"%>
<%@ page import="java.sql.ResultSet,java.sql.PreparedStatement"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Pop页列表</title>
	</head>
	<style type="text/css">
.over {
	background-color: #FFDC35;
}

.td {
	border: solid #add9c0;
	border-width: 0px 1px 1px 0px;
	padding: 10px 0px;
}

.table {
	border: solid #add9c0;
	border-width: 1px 0px 0px 1px;
}
.disableHref{
	cursor:default;
	color:#E5E0E0;
	text-decoration:none;
}
</style>
	<%
		String clo_name = request.getParameter("col_name");
		String sql = request.getParameter("sql");
		String hidden_col = request.getParameter("hidden_col");
		if( !"null".equals(hidden_col) ){
			hidden_col = ","+hidden_col +",";
		}
		sql = sql.replaceAll("@", "%");
		System.out.println("---"+sql);
		String db_bean_rel = request.getParameter("rel");
		String pageNo = request.getParameter("pageNo");
		String size = request.getParameter("size");
		String startNum = request.getParameter("startNum");
		String endNum = request.getParameter("endNum");
		String totalPage = request.getParameter("totalPage");
		String currPage = request.getParameter("currPage");
	%>
	<body class="body_bg">
		<s:form method="post" theme="simple" name="cms_form"
			action="PopParmConfActionInsert.action">
			<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
							<table id="tabPage" width="100%" border="0" align="center">
								<tr>
									<td width="40%">
										<font size="2.5px" color="#AEA100"><strong>>>信息列表</strong>
										</font>
									</td>
									<td align="right" width="60%">
										总记录：
										<font color="#FF5100"><strong><%=size%> </strong> </font>&nbsp;&nbsp;
										总页数：
										<font color="#FF5100"><strong><%=totalPage%></strong> </font>
										&nbsp;&nbsp;
										<a id="back" href="#" onclick="func_back()">上一页</a> &nbsp;|&nbsp;
										<a id="next" href="#" onclick="func_next()"> 下一页 </a>&nbsp;&nbsp;
										第&nbsp;
										<font color="#FF5100"><strong><%=currPage%></strong> </font>&nbsp;页
										&nbsp;&nbsp;
									</td>
								</tr>
							</table>
							<table id="tablist" width="100%" align="center" cellspacing="0"
								border="0"
								style="border: solid #BEBEBE; border-width: 0px 0px 0px 0px;">
								<%
										String[] clo_name_arr = clo_name.split(",");
										int clo_name_len = clo_name_arr.length;
										String len_flag = "";
								%>
									
								<thead>
									<tr>
										<%
											for (int i = 1; i < clo_name_len+1; i++) {
												len_flag = ","+i+",";
												if( hidden_col.indexOf(len_flag) == -1 ){
										%>
										<th scope="col" align="center" height="27"
											style="background: #BEBEBE; border: solid #FAEBD7; border-width: 0px 1px 1px 0px; padding: 1px 1px;">
											<font size="2.0px"><%=clo_name_arr[i-1]%></font>
										</th>
										<%
												}
												len_flag = "";
											}
										%>
									</tr>
								</thead>
								<tbody id="tab">

									<%
										Connection conn = null;
											PreparedStatement ps = null;
											ResultSet rs = null;
											String val = "";
											try {
												conn = DBUtils.getConn();
												ps = conn.prepareStatement(sql);
												ps.setInt(2, Integer.parseInt(startNum));
												ps.setInt(1, Integer.parseInt(endNum));
												rs = ps.executeQuery();
												int len = rs.getMetaData().getColumnCount();
												String len_rs = "";
												while (rs.next()) {
									%>
									<tr id="tr">
										<%
											for (int i = 1; i < len; i++) {//一行 几列的值
												len_rs = ","+i+",";
												if( hidden_col.indexOf(len_rs) == -1 ){
										%>
										<td align="left" height="25"
											style="border: solid #BEBEBE; border-width: 0px 1px 1px 0px; padding: 0px 0px; backgroud: #E0E0E0;"
											id='<%=rs.getMetaData().getColumnName(i).toLowerCase()%>'>
											&nbsp;<%
													if( rs.getObject(i)!=null ){
														out.print(rs.getObject(i));
													}
												  %>
										</td>
										<%
												}else{
										%>
												
													<td align="left" width="0" style="display: none"
													id='<%=rs.getMetaData().getColumnName(i).toLowerCase()%>'
													>&nbsp;<%=rs.getObject(i)%></td>
											<%
												}
												len_rs="";
											}
										%>
									</tr>
									<%
										}
											} catch (Exception e) {
												e.printStackTrace();
											} finally {
												DBUtils.closeResultset(rs);
												DBUtils.closeStatement(ps);
												DBUtils.closeConnection(conn);
											}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</body>
</html>
<script type="text/javascript">
var pageSize = "<%=pageNo%>";
var currPage = "<%=currPage%>";
var startNum = "<%=startNum%>";
var endNum = "<%=endNum%>";
var totalPage = "<%=totalPage%>";
var sql="<%=sql%>";

function func_back() {
	if (currPage == "1") {
		$("#back").addClass("disableHref");
		return;
	} else {
		currPage = parseFloat(currPage) - 1;
		startNum = parseFloat(startNum) - parseFloat(pageSize);
		endNum = parseFloat(endNum) - parseFloat(pageSize);
		sql = sql.replace(/\%/g,"@");
		var url = "Pop_list.jsp?sql="+sql+"&rel=<%=db_bean_rel%>&size=<%=size%>&col_name=<%=clo_name%>&"
				+ "pageNo=<%=pageNo%>&startNum="
				+ startNum
				+ "&endNum="
				+ endNum + "&totalPage=" + totalPage + "&currPage=" + currPage+"&hidden_col=<%=hidden_col%>";
		window.parent.document.all["poplist"].src = url;
	}
}
function func_next() {
	if (parseFloat(currPage) >= parseFloat(totalPage)) {
		$("#next").addClass("disableHref");
		return;
	} else {
		currPage = parseFloat(currPage) + 1;
		startNum = parseFloat(startNum) + parseFloat(pageSize);
		endNum = parseFloat(endNum) + parseFloat(pageSize);
		sql = sql.replace(/\%/g,"@");
		var url = "Pop_list.jsp?sql="+sql+"&rel=<%=db_bean_rel%>&size=<%=size%>&col_name=<%=clo_name%>&"
				+ "pageNo=<%=pageNo%>&startNum="
				+ startNum
				+ "&endNum="
				+ endNum + "&totalPage=" + totalPage + "&currPage=" + currPage+"&hidden_col=<%=hidden_col%>";
		window.parent.document.all["poplist"].src = url;
	}
}

$(function() {
	if (currPage == "1") {
		$("#back").addClass("disableHref");
	}
	if (parseFloat(currPage) >= parseFloat(totalPage)) {
		$("#next").addClass("disableHref");
	}
	$("#tablist tr").slice(1).hover(function() {
		$(this).addClass("trHover");
		//$(this).removeClass("ls_list");
	}, function() {
		$(this).removeClass("trHover");
		//$(this).addClass("ls_list");
	});

	$("#tablist tr").slice(1).click(function() {
		var isIe = true;
		var agent = navigator.userAgent.toLowerCase();
		if (agent.indexOf("chrome") > 0 || agent.indexOf("firefox") > 0) {
			isIe = false;
		}
		
		var db_rel = "<%=db_bean_rel%>";//对应关系  
		//alert(db_rel);
			var db_rel_arr = db_rel.split(",");
			var value = "";
			for ( var i = 0; i < db_rel_arr.length; i++) {
				var db_r_arr_split = db_rel_arr[i].split("-"); 
				value = $(this).children("#" + db_r_arr_split[0]).text();
				//alert(value);
				value = $.trim(value);
				if (isIe) {
					window.parent.dialogArguments.document.getElementsByName(db_r_arr_split[1])[0].value = value;

				}else{
					window.parent.opener.document.getElementsByName(db_r_arr_split[1])[0].value = value;
				}
			}
			if (isIe) {
				window.returnValue="success";
    			window.close();
    		}else{
    			window.parent.opener = null;
    			window.parent.open(" ", "_self");
    			window.parent.close();
    			
    		}
			
		});
});
</script>
