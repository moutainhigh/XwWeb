package app.util.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import app.util.toolkit.Ipage;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = -1017952786072702859L;
	// 需要显示的url
	private String urlPath=null;
	private String page=null;
	private String colCount=null;
	private String scope=null;
	private static final String FirstPage = "第一页";
	private static final String PreviousPage = "上一页";
	private static final String NextPage = "下一页";
	private static final String LastPage = "最后一页";

	public int doStartTag() throws JspException {
		Ipage ipage = null;
		int scope_type = parseScope(scope);
		if (page == null || "".equals(page)) {
			ipage = (Ipage) pageContext.getAttribute("ipage",scope_type);
		} else {
			ipage = (Ipage) pageContext.getAttribute(page,scope_type);
		}
		if (ipage == null) {
			return SKIP_BODY;
		}
		if(colCount==null || "".equals(colCount)){
			colCount="20";
		}
		int pageNo = ipage.getPageNo();
		int pageCounts = ipage.getPageCounts();
		int pageSum = ipage.getPageSum();
		
		PrintJumping(pageNo, ipage);
		PrintTr("<tr><td  height='23' class=\"page\" colspan=" + getColCount()+">");
		if (pageCounts >= 1) {
			String rowcount = "共" + pageCounts + "条记录&nbsp;  ";
			PrintPage(0, false, rowcount);
			PrintTr("<input type=\"hidden\" name=\"totalsize\" value='" + pageCounts + "' />");
		}

		String pagecount = "共" + pageSum + "页&nbsp/&nbsp;第"+pageNo+"页";
		PrintPage(0, false, pagecount);
		// 输出第一页

		PrintPage(1, pageNo != 1, FirstPage);
		// 输出上一页
		PrintPage(pageNo - 1, ipage.isHasPrevious(), PreviousPage);
		// 输出下一页
		PrintPage(pageNo + 1, ipage.isHasNext(), NextPage);
		// 最后一页输出
		PrintPage(pageSum, pageSum >= 1 && pageNo!=pageSum, LastPage);
		// 跳转到第几页
		ToPage(pageNo, pageSum);
		PrintTr("</td></tr>");
		return SKIP_BODY;

	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return this.page;
	}
	private int parseScope(String scope) {
		if (scope == null)
			scope = "request";
		if (scope.equalsIgnoreCase("requst")) {
			return PageContext.REQUEST_SCOPE;
		} else if (scope.equalsIgnoreCase("page")) {
			return PageContext.PAGE_SCOPE;
		} else if (scope.equalsIgnoreCase("session")) {
			return PageContext.SESSION_SCOPE;
		} else if (scope.equalsIgnoreCase("application")) {
			return PageContext.APPLICATION_SCOPE;
		}
		return PageContext.REQUEST_SCOPE;
	}
	public void PrintJumping(int pageNo, Ipage ipage) throws JspException {
		StringBuffer buf = new StringBuffer(urlPath);
		String paramStr = ipage.getParamsStr();
		buf.append(paramStr);
//		Map<String,Object> map = ipage.getParams();
//		if(map!=null && map.size()>0){
//			Set<String> set = map.keySet();
//			Object[] ps=set.toArray();
//			if(buf.indexOf("?")==-1){
//				buf.append("?");
//			}
//			for(int i=0;i<ps.length;i++){
//				if(map.get(ps[i])!=null){
//					buf.append((String)ps[i]+"="+map.get(ps[i])+"&");
//				}
//			}
//		}
		String href = buf.toString();
		
		buf = new StringBuffer();
		buf.append("\r\n<script language=\"JavaScript\">\r\n");
		buf.append("\r\n");
		buf.append("function eadis_goSearch(pagenum){");
		buf.append("\r\n");
		buf.append("document.cms_form.eadis_page.value = pagenum;");
		buf.append("\r\n");
		buf.append("document.cms_form.action='"+href+"'");
		buf.append("\r\n");
		buf.append("document.cms_form.submit();");
		buf.append("\r\n");
		buf.append("return true;");
		buf.append("\r\n");
		buf.append("}");
		buf.append("\r\n");
		buf.append("</script>\r\n");
		try {
			pageContext.getOut().flush();
			pageContext.getOut().write(buf.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
	}

	public void ToPage(int newPageNumber, int pageSum) throws JspException {

		StringBuffer buf = new StringBuffer();
		String select = "跳转到";
		select = select
				+ "\r\n<SELECT name= 'eadis_page' onchange=eadis_goSearch(this.value)"
				+ " style='width:45px;height:24px'>\r\n ";

		if (newPageNumber < 1 || newPageNumber > pageSum) {
			select = select + "</SELECT>页\r\n";
			buf.append(select);
		} else {
			//修改显示当前页前后50页
			int start=newPageNumber-50;
			int end= newPageNumber+50;
			if(pageSum<=100){//100页以内
				start=1;
				end=pageSum;
			}else{
				if(start<=0){
					start=1;
					end=100;
				}
				if(end>pageSum){
					start=pageSum-100;
				}
			}
			for (; start <= pageSum && start<=end; start++) {
				if (start == newPageNumber) {
					select = select + "<OPTION selected value=" + start + ">" + start
							+ "</OPTION>\r\n";
				} else {
					select = select + "<OPTION value=" + start + ">" + start
							+ "</OPTION>\r\n";
				}
			}
			if(pageSum>100){
				select = select + "<OPTION value=" + pageSum + ">" + pageSum
				+ "</OPTION>\r\n";
			}
			select = select + "</SELECT>&nbsp;页&nbsp;&nbsp;";
			buf.append(select);
		}
		
		try {
			pageContext.getOut().flush();
			pageContext.getOut().write(buf.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
	}

	public void PrintTr(String tr) throws JspException {
		try {
			pageContext.getOut().flush();
			pageContext.getOut().write(tr);
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
	}

	public void PrintPage(int newPageNumber, boolean next, String label)
			throws JspException {

		StringBuffer buf = new StringBuffer();
		buf.append("\r\n");
		if (!next) {
			if (label != null) {
				buf.append("&nbsp;" + label + "&nbsp;");
			}
		} else {
			buf.append("&nbsp;<a href=\"javascript:void(0)\" class=\"green\" onclick=\"eadis_goSearch('")
				.append(newPageNumber).append("')\">");
			if (label != null) {
				buf.append(label);
			}
			buf.append("</a>&nbsp;");
			buf.append("\r\n");
		}
		try {
			pageContext.getOut().flush();
			pageContext.getOut().write(buf.toString());
		} catch (IOException ioe) {
			throw new JspTagException(ioe.getMessage());
		}
	}

	public String getColCount() {
		return colCount;
	}

	public void setColCount(String colCount) {
		this.colCount = colCount;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}


}
