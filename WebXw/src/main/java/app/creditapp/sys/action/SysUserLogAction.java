package  app.creditapp.sys.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysUserLogBo;
import app.creditapp.sys.entity.SysUserLog;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: SysUserLogAction.java
 * Description:
 **/
public class SysUserLogAction extends BaseFormBean {

	//页面传值
	private SysUserLog sysUserLog;
	private List<SysUserLog> sysUserLogList;

	//注入SysUserLogBo
	private SysUserLogBo sysUserLogBo;

	private String query;
	private String userId;		
	private FormData formsys0004;
	private FormData formsys0005;
	private FormService formService = new FormService();
	
	public SysUserLogAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0004 = formService.getFormData("sys0004");
		sysUserLog = new SysUserLog();
		getFormValue(formsys0004);
		setObjValue(formsys0004, sysUserLog);
		Ipage ipage = this.getIpage();
		sysUserLogList = (List) sysUserLogBo.findByPage(ipage, sysUserLog).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0005 = formService.getFormData("sys0005");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0005 = formService.getFormData("sys0005");
		getFormValue(formsys0005);
		sysUserLog = new SysUserLog();
		setObjValue(formsys0005, sysUserLog);
		sysUserLogBo.insert(sysUserLog);
		getObjValue(formsys0005, sysUserLog);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0005 = formService.getFormData("sys0005");
		getFormValue(formsys0005);
		sysUserLog = new SysUserLog();
		setObjValue(formsys0005, sysUserLog);
		sysUserLogBo.update(sysUserLog);
		getObjValue(formsys0005, sysUserLog);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0004 = formService.getFormData("sys0004");
		sysUserLog = new SysUserLog();
		sysUserLog.setUserId(userId);
		sysUserLogBo.del(sysUserLog);
		this.addActionMessage("删除成功");
		sysUserLog = new SysUserLog();
		Ipage ipage = this.getIpage();
		sysUserLogList = (List) sysUserLogBo.findByPage(ipage, sysUserLog).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0005 = formService.getFormData("sys0005");
		sysUserLog = new SysUserLog();
		sysUserLog.setUserId(userId);
		sysUserLog = sysUserLogBo.getById(sysUserLog);
		getObjValue(formsys0005, sysUserLog);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0005 = formService.getFormData("sys0005");
		 getFormValue(formsys0005);
		 validateFormData(formsys0005);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0005 = formService.getFormData("sys0005");
		 getFormValue(formsys0005);
		 validateFormData(formsys0005);
  	}
	public SysUserLog getSysUserLog() {
		return sysUserLog;
	}
	public void setSysUserLog(SysUserLog  sysUserLog) {
		this.sysUserLog = sysUserLog;
	}
	public List<SysUserLog> getSysUserLogList() {
		return sysUserLogList;
	}
	public void setSysUserLogList(List<SysUserLog> sysUserLogList) {
		this.sysUserLogList = sysUserLogList;
	}
	public SysUserLogBo getSysUserLogBo() {
		return sysUserLogBo;
	}
	public void setSysUserLogBo(SysUserLogBo sysUserLogBo) {
		this.sysUserLogBo = sysUserLogBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0005() {
		return formsys0005;
	}
	public void setFormsys0005(FormData formsys0005) {
		this.formsys0005 = formsys0005;
	}
	public FormData getFormsys0004() {
		return formsys0004;
	}
	public void setFormsys0004(FormData formsys0004) {
		this.formsys0004 = formsys0004;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}		
	public String getUserId(){
		return userId;
	}
}