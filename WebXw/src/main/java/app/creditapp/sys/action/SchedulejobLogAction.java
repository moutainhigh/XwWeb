package  app.creditapp.sys.action;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SchedulejobLogBo;
import app.creditapp.sys.entity.SchedulejobLog;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: SchedulejobLogAction.java
 * Description:
 **/
public class SchedulejobLogAction extends BaseFormBean {

	//页面传值
	private SchedulejobLog schedulejobLog;
	private List<SchedulejobLog> schedulejobLogList;

	//注入SchedulejobLogBo
	private SchedulejobLogBo schedulejobLogBo;

	private String query;
	private String logId;		
	private FormData formsys0123;
	private FormData formsys0124;
	private FormService formService = new FormService();
	
	public SchedulejobLogAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0123 = formService.getFormData("sys0123");
		schedulejobLog = new SchedulejobLog();
		getFormValue(formsys0123);
		setObjValue(formsys0123, schedulejobLog);
		Ipage ipage = this.getIpage();
		schedulejobLogList = (List) schedulejobLogBo.findByPage(ipage, schedulejobLog).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0124 = formService.getFormData("sys0124");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0124 = formService.getFormData("sys0124");
		getFormValue(formsys0124);
		schedulejobLog = new SchedulejobLog();
		setObjValue(formsys0124, schedulejobLog);
		schedulejobLogBo.insert(schedulejobLog);
		getObjValue(formsys0124, schedulejobLog);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0124 = formService.getFormData("sys0124");
		getFormValue(formsys0124);
		schedulejobLog = new SchedulejobLog();
		setObjValue(formsys0124, schedulejobLog);
		schedulejobLogBo.update(schedulejobLog);
		getObjValue(formsys0124, schedulejobLog);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0123 = formService.getFormData("sys0123");
		schedulejobLog = new SchedulejobLog();
		schedulejobLog.setLogId(logId);
		schedulejobLogBo.del(schedulejobLog);
		this.addActionMessage("删除成功");
		schedulejobLog = new SchedulejobLog();
		Ipage ipage = this.getIpage();
		schedulejobLogList = (List) schedulejobLogBo.findByPage(ipage, schedulejobLog).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0124 = formService.getFormData("sys0124");
		schedulejobLog = new SchedulejobLog();
		schedulejobLog.setLogId(logId);
		schedulejobLog = schedulejobLogBo.getById(schedulejobLog);
		getObjValue(formsys0124, schedulejobLog);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0124 = formService.getFormData("sys0124");
		 getFormValue(formsys0124);
		 validateFormData(formsys0124);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0124 = formService.getFormData("sys0124");
		 getFormValue(formsys0124);
		 validateFormData(formsys0124);
  	}
	public SchedulejobLog getSchedulejobLog() {
		return schedulejobLog;
	}
	public void setSchedulejobLog(SchedulejobLog  schedulejobLog) {
		this.schedulejobLog = schedulejobLog;
	}
	public List<SchedulejobLog> getSchedulejobLogList() {
		return schedulejobLogList;
	}
	public void setSchedulejobLogList(List<SchedulejobLog> schedulejobLogList) {
		this.schedulejobLogList = schedulejobLogList;
	}
	public SchedulejobLogBo getSchedulejobLogBo() {
		return schedulejobLogBo;
	}
	public void setSchedulejobLogBo(SchedulejobLogBo schedulejobLogBo) {
		this.schedulejobLogBo = schedulejobLogBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0124() {
		return formsys0124;
	}
	public void setFormsys0124(FormData formsys0124) {
		this.formsys0124 = formsys0124;
	}
	public FormData getFormsys0123() {
		return formsys0123;
	}
	public void setFormsys0123(FormData formsys0123) {
		this.formsys0123 = formsys0123;
	}
	public void setLogId(String logId){
		this.logId = logId;
	}		
	public String getLogId(){
		return logId;
	}
}