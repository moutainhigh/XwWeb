package app.creditapp.sys.action;

/**
 * 系统操作员登录登出日志查询
 * @mailto: shaoxinlong@dhcc.com.cn
 */
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.sys.bo.SysLoginLogBo;
import app.creditapp.sys.entity.SysLoginLog;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

public class SysLoginLogAction extends BaseFormBean {

	// 页面传值
	private SysLoginLog sysloginlog;
	private List<SysLoginLog> sysloginlogList;

	// 注入SysLoginLogBO
	private SysLoginLogBo sysLoginLogBo;

	private String query = "";
	private FormData formsys2007; // 查询表单
	private FormService formService = new FormService();

	public SysLoginLogAction() {
		formsys2007=formService.getFormData("sys2007");
		query="";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		sysloginlog=new SysLoginLog();
		getFormValue(formsys2007);
		setObjValue(formsys2007, sysloginlog);
		Ipage ipage = this.getIpage();
		sysloginlogList=(List)sysLoginLogBo.findByPage(ipage, sysloginlog).getResult();

		return "list";
	}

	/* 日志记录不需要增、删、改，以下功能不使用，保留 */
	// public String input() throws Exception{
	// if(sysloginlog!=null &&
	// StringUtils.isNotBlank(sysloginlog.getSession_id())){
	// sysloginlog = sysloginlogbo.getById(sysloginlog.getSession_id());
	// }
	// return "input";
	// }
	// public String insertOrUpdate() throws Exception{
	// sysloginlogbo.insertOrUpdate(sysloginlog);
	// this.setMessage("操作成功");
	// return "input_result";
	// }
	// public String del() throws Exception{
	// sysloginlogbo.del(sysloginlog.getSession_id());
	// this.setMessage("操作成功");
	// return "del_result";
	// }
	public String getById() throws Exception {
		sysloginlog = sysLoginLogBo.getById(sysloginlog.getSessionId());
		this.setMessage("操作成功");
		return "input_result";
	}

	public SysLoginLog getSysloginlog() {
		return sysloginlog;
	}

	public void setSysloginlog(SysLoginLog sysloginlog) {
		this.sysloginlog = sysloginlog;
	}

	public List<SysLoginLog> getSysloginlogList() {
		return sysloginlogList;
	}

	public void setSysloginloglist(List<SysLoginLog> sysloginlogList) {
		this.sysloginlogList = sysloginlogList;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	
	public void setSysloginlogList(List<SysLoginLog> sysloginlogList) {
		this.sysloginlogList = sysloginlogList;
	}

	public FormData getFormsys2007() {
		return formsys2007;
	}

	public void setFormsys2007(FormData formsys2007) {
		this.formsys2007 = formsys2007;
	}

	public SysLoginLogBo getSysLoginLogBo() {
		return sysLoginLogBo;
	}

	public void setSysLoginLogBo(SysLoginLogBo sysLoginLogBo) {
		this.sysLoginLogBo = sysLoginLogBo;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

}
