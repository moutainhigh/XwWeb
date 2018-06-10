<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.net.URLEncoder"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ฯ๊ว้</title>
	</head>
	<%
		String sql = request.getParameter("sql");
		String query_sql = request.getParameter("query_sql");
		String hidden_col = request.getParameter("hidden_col");
		String rel = request.getParameter("rel");
		String col_name = request.getParameter("col_name");
		String size = request.getParameter("size");
		String query_name = request.getParameter("query_name");
		String disName = request.getParameter("disName");
		String pageNo = request.getParameter("pageNo");
		String if_query = request.getParameter("if_query");
		if( if_query==null ){
			if_query = "";
		}
		int startNum = 1;
		int endNum = startNum + Integer.parseInt(pageNo);
		int totalPage = 0;
		if (Integer.parseInt(size) % Integer.parseInt(pageNo) == 0) {
			totalPage = Integer.parseInt(size) / Integer.parseInt(pageNo);
		} else {
			totalPage = Integer.parseInt(size) / Integer.parseInt(pageNo)
					+ 1;
		}
		String currPage = "1";
		String scene_id = request.getParameter("scene_id");
	%>
	<body class="body_bg">
		<s:form method="post" theme="simple" name="cms_form" action="#">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="from_w">
				<tr>
					<td width="100%">
						<iframe id="menuFrm" name="menuFrm"
							src="Pop_query.jsp?sql=<%=URLEncoder.encode(sql)%>&rel=<%=rel%>&size=<%=size%>&col_name=<%=col_name%>&pageNo=<%=pageNo%>&startNum=<%=startNum%>&endNum=<%=endNum%>&totalPage=<%=totalPage%>&currPage=<%=currPage%>&query_name=<%=query_name%>&disName=<%=disName%>&scene_id=<%=scene_id%>&query_sql=<%=URLEncoder.encode(query_sql)%>&hidden_col=<%=hidden_col %>"
							height="140" frameborder=0 width="100%"></iframe>
					</td>
				</tr>
				<tr>
					<td width="100%">
						<%if( "0".equals(if_query) ){ %>
						<iframe id="poplist" name="poplist"
									src="Pop_msg.jsp"
									frameborder=0 height="450" width="100%"></iframe>
						<%}else{ %>
						<iframe id="poplist" name="poplist"
							src="Pop_list.jsp?sql=<%=URLEncoder.encode(sql)%>&rel=<%=rel%>&size=<%=size%>&col_name=<%=col_name%>&pageNo=<%=pageNo%>&startNum=<%=startNum%>&endNum=<%=endNum%>&totalPage=<%=totalPage%>&currPage=<%=currPage%>&query_name=<%=query_name%>&disName=<%=disName%>&hidden_col=<%=hidden_col %>"
							frameborder=0 height="450" width="100%"></iframe>
						<%} %>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>