<%@ page language="java" contentType="text/html; charset=GBK"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%Object themeObj = session==null?null:session.getValue("color");
String theme = themeObj==null?"blue":((String)themeObj);
%>
	<BODY style="background:#FFFFFF;margin:0;">
		<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top height=270>
						<DIV align=center>
							<BR>
								<!--<IMG height=311 src="<%=request.getContextPath() %>/themes/theme_<%=theme %>/images/404.png">-->
								<IMG src="<%=request.getContextPath() %>/themes/images/404.png">
						</DIV>
					</TD>
				</TR>
				
			</TBODY>
		</TABLE>
		<P align=center><a href="<%=request.getContextPath() %>/creditapp/welcome.jsp" style="text-decoration:none;color:#4b423c;margin-left:20px;font-size:14px;font-family:'Microsoft YaHei';">·µ»ØÊ×Ò³>></a>
		</P>
		<P align=center>
		</P>
	</BODY>
</html>
