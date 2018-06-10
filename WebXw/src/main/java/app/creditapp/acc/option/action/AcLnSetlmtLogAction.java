package  app.creditapp.acc.option.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcLnSetlmtLogBo;
import app.creditapp.acc.option.entity.AcLnSetlmtLog;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcLnSetlmtLogAction.java
 * Description:
 **/
public class AcLnSetlmtLogAction extends BaseFormBean {

	//页面传值
	private AcLnSetlmtLog acLnSetlmtLog;
	private List<AcLnSetlmtLog> acLnSetlmtLogList;

	//注入AcLnSetlmtLogBo
	private AcLnSetlmtLogBo acLnSetlmtLogBo;

	private String query;
	private String traceNo;		
	private FormData formselt001;
	private FormData formselt002;
	private FormService formService = new FormService();
	
	public AcLnSetlmtLogAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formselt001 = formService.getFormData("selt001");
		acLnSetlmtLog = new AcLnSetlmtLog();
		getFormValue(formselt001);
		setObjValue(formselt001, acLnSetlmtLog);
//		acLnSetlmtLog.setBrNo(User.getBrno(this.getHttpRequest()));
		acLnSetlmtLog.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		acLnSetlmtLogList = (List) acLnSetlmtLogBo.findByPage(ipage, acLnSetlmtLog).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formselt002 = formService.getFormData("selt002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formselt002 = formService.getFormData("selt002");
		getFormValue(formselt002);
		acLnSetlmtLog = new AcLnSetlmtLog();
		setObjValue(formselt002, acLnSetlmtLog);
		acLnSetlmtLog.setBrNo(User.getBrno(this.getHttpRequest()));
		acLnSetlmtLogBo.insert(acLnSetlmtLog);
		getObjValue(formselt002, acLnSetlmtLog);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formselt002 = formService.getFormData("selt002");
		getFormValue(formselt002);
		acLnSetlmtLog = new AcLnSetlmtLog();
		setObjValue(formselt002, acLnSetlmtLog);
		acLnSetlmtLogBo.update(acLnSetlmtLog);
		getObjValue(formselt002, acLnSetlmtLog);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formselt001 = formService.getFormData("selt001");
		acLnSetlmtLog = new AcLnSetlmtLog();
		acLnSetlmtLog.setTraceNo(traceNo);
		acLnSetlmtLogBo.del(acLnSetlmtLog);
		this.addActionMessage("删除成功");
		acLnSetlmtLog = new AcLnSetlmtLog();
		acLnSetlmtLog.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLnSetlmtLogList = (List) acLnSetlmtLogBo.findByPage(ipage, acLnSetlmtLog).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formselt002 = formService.getFormData("selt002");
		acLnSetlmtLog = new AcLnSetlmtLog();
		acLnSetlmtLog.setTraceNo(traceNo);
		acLnSetlmtLog = acLnSetlmtLogBo.getById(acLnSetlmtLog);
		getObjValue(formselt002, acLnSetlmtLog);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formselt002 = formService.getFormData("selt002");
		 getFormValue(formselt002);
		 validateFormData(formselt002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formselt002 = formService.getFormData("selt002");
		 getFormValue(formselt002);
		 validateFormData(formselt002);
  	}
	public AcLnSetlmtLog getAcLnSetlmtLog() {
		return acLnSetlmtLog;
	}
	public void setAcLnSetlmtLog(AcLnSetlmtLog  acLnSetlmtLog) {
		this.acLnSetlmtLog = acLnSetlmtLog;
	}
	public List<AcLnSetlmtLog> getAcLnSetlmtLogList() {
		return acLnSetlmtLogList;
	}
	public void setAcLnSetlmtLogList(List<AcLnSetlmtLog> acLnSetlmtLogList) {
		this.acLnSetlmtLogList = acLnSetlmtLogList;
	}
	public AcLnSetlmtLogBo getAcLnSetlmtLogBo() {
		return acLnSetlmtLogBo;
	}
	public void setAcLnSetlmtLogBo(AcLnSetlmtLogBo acLnSetlmtLogBo) {
		this.acLnSetlmtLogBo = acLnSetlmtLogBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormselt002() {
		return formselt002;
	}
	public void setFormselt002(FormData formselt002) {
		this.formselt002 = formselt002;
	}
	public FormData getFormselt001() {
		return formselt001;
	}
	public void setFormselt001(FormData formselt001) {
		this.formselt001 = formselt001;
	}
	public void setTraceNo(String traceNo){
		this.traceNo = traceNo;
	}		
	public String getTraceNo(){
		return traceNo;
	}
}