package app.creditapp.sys.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;


import app.creditapp.sys.bo.SysLogBo;
import app.creditapp.sys.entity.SysLog;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

public class SysLogAction extends BaseFormBean {

	// 页面传值
	private SysLog syslog;
	private List<SysLog> syslogList;

	// 注入SysLogBO
	private SysLogBo sysLogBo;
	
	private String query;
	
	private FormData formsys2005;	//查询表单
	private FormService formService = new FormService();

	public SysLogAction() {
		formsys2005=formService.getFormData("sys2005");
		query="";
	}
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		syslog=new SysLog();
		getFormValue(formsys2005);
		setObjValue(formsys2005, syslog);
		Ipage ipage = this.getIpage();
		syslogList=(List)sysLogBo.findByPage(ipage, syslog).getResult();
		return "list";
	}
    /**
     * 进入列表页面不做任何操作
     * @return
     * @throws Exception
     */
	public String tomain() throws Exception {
		syslogList=new ArrayList<SysLog>();
		return "list";
	}
	public SysLog getSyslog() {
		return syslog;
	}

	public void setSyslog(SysLog syslog) {
		this.syslog = syslog;
	}

	public List<SysLog> getSyslogList() {
		return syslogList;
	}

	public void setSyslogList(List<SysLog> syslogList) {
		this.syslogList = syslogList;
	}

	public SysLogBo getSysLogBo() {
		return sysLogBo;
	}

	public void setSysLogBo(SysLogBo sysLogBo) {
		this.sysLogBo = sysLogBo;
	}
	public FormData getFormsys2005() {
		return formsys2005;
	}
	public void setFormsys2005(FormData formsys2005) {
		this.formsys2005 = formsys2005;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
