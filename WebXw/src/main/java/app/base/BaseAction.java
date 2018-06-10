package app.base;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import app.util.toolkit.Ipage;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction  extends ActionSupport implements ServletRequestAware, SessionAware, ServletContextAware,ServletResponseAware  {
	

	private static final long serialVersionUID = 331256994500361617L;

	
	private String message;
	private Ipage ipage;
	private String eadis_page;				//当前页码
	private String flag;					//页码跳转
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private ServletContext servletContext;
	private Map<String,Object> session;
	private String url;//跳转的url
	private String workitemId;//工作项ID
	private String processId;//流程实例ID
	private String segId;//业务流程任务ID
	private String parmId;//业务流程环节ID
	
	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}

	public ServletContext getServletContest() {
		return servletContext;
	}

	public void setServletContext(ServletContext sc) {
		this.servletContext = sc;
	}

	public Map<String,Object> getSession() {
		return session;
	}

	public void setSession(Map<String,Object> session) {
		this.session = session;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.httpResponse=arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.httpRequest=arg0;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Ipage getIpage() {
		if(ipage==null){
			ipage=new Ipage();
		}
		if(pageSize==0){
			pageSize = 15;
		}
		if(pageSize<0){
			pageSize = 15;
		}
		ipage.setPageSize(pageSize);
		if(StringUtils.isBlank(this.getEadis_page())){				
			ipage.setPageNo(1);									//设置当前页码为1
		}else if("1".equals(this.getFlag())){
			ipage.setPageNo(1);									//设置当前页码为1
		}else{
			ipage.setPageNo(Integer.valueOf(this.getEadis_page()));	//设置当前页码为页面选定值
        }
		return ipage;
	}

	public void setIpage(Ipage ipage) {
		this.ipage = ipage;
	}

	public String getEadis_page() {
		return eadis_page;
	}

	public void setEadis_page(String eadis_page) {
		 try{
	        	this.eadis_page =  Integer.parseInt(eadis_page)+"";
	        } catch (Exception e) {
	        	this.eadis_page =  "1";
	        }
		
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getSegId() {
		return segId;
	}

	public void setSegId(String segId) {
		this.segId = segId;
	}

	public String getParmId() {
		return parmId;
	}

	public void setParmId(String parmId) {
		this.parmId = parmId;
	}

	private int pageSize;


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSizeStr) {
        try{
        	this.pageSize =  Integer.parseInt(pageSizeStr);
        } catch (Exception e) {
        	this.pageSize =  15;
        }
	}
}
