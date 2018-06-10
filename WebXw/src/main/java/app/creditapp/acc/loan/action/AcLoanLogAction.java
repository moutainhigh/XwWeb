package  app.creditapp.acc.loan.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcLoanLogBo;
import app.creditapp.acc.loan.entity.AcLoanLog;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcLoanLogAction.java
 * Description:
 **/
public class AcLoanLogAction extends BaseFormBean {

	//页面传值
	private AcLoanLog acLoanLog;
	private List<AcLoanLog> acLoanLogList;

	//注入AcLoanLogBo
	private AcLoanLogBo acLoanLogBo;

	private String query;
	private String loanLogNo;		
	private FormData formloanlog0001;
	private FormData formloanlog0002;
	private FormService formService = new FormService();
	
	public AcLoanLogAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formloanlog0001 = formService.getFormData("loanlog0001");
		acLoanLog = new AcLoanLog();
		getFormValue(formloanlog0001);
		setObjValue(formloanlog0001, acLoanLog);
		acLoanLog.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLoanLogList = (List) acLoanLogBo.findByPage(ipage, acLoanLog).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloanlog0002 = formService.getFormData("loanlog0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloanlog0002 = formService.getFormData("loanlog0002");
		getFormValue(formloanlog0002);
		acLoanLog = new AcLoanLog();
		setObjValue(formloanlog0002, acLoanLog);
		acLoanLog.setBrNo(User.getBrno(this.getHttpRequest()));
		acLoanLog.setTxDate(User.getSys_date(this.getHttpRequest()));
		acLoanLogBo.insert(acLoanLog);
		getObjValue(formloanlog0002, acLoanLog);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloanlog0002 = formService.getFormData("loanlog0002");
		getFormValue(formloanlog0002);
		acLoanLog = new AcLoanLog();
		setObjValue(formloanlog0002, acLoanLog);
		acLoanLog.setUpDate(User.getSys_date(this.getHttpRequest()));
		acLoanLogBo.update(acLoanLog);
		getObjValue(formloanlog0002, acLoanLog);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloanlog0001 = formService.getFormData("loanlog0001");
		acLoanLog = new AcLoanLog();
		acLoanLog.setLoanLogNo(loanLogNo);
		acLoanLogBo.del(acLoanLog);
		this.addActionMessage("删除成功");
		acLoanLog = new AcLoanLog();
		acLoanLog.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLoanLogList = (List) acLoanLogBo.findByPage(ipage, acLoanLog).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formloanlog0002 = formService.getFormData("loanlog0002");
		acLoanLog = new AcLoanLog();
		acLoanLog.setLoanLogNo(loanLogNo);
		acLoanLog = acLoanLogBo.getById(acLoanLog);
		getObjValue(formloanlog0002, acLoanLog);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formloanlog0002 = formService.getFormData("loanlog0002");
		 getFormValue(formloanlog0002);
		 validateFormData(formloanlog0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formloanlog0002 = formService.getFormData("loanlog0002");
		 getFormValue(formloanlog0002);
		 validateFormData(formloanlog0002);
  	}
	public AcLoanLog getAcLoanLog() {
		return acLoanLog;
	}
	public void setAcLoanLog(AcLoanLog  acLoanLog) {
		this.acLoanLog = acLoanLog;
	}
	public List<AcLoanLog> getAcLoanLogList() {
		return acLoanLogList;
	}
	public void setAcLoanLogList(List<AcLoanLog> acLoanLogList) {
		this.acLoanLogList = acLoanLogList;
	}
	public AcLoanLogBo getAcLoanLogBo() {
		return acLoanLogBo;
	}
	public void setAcLoanLogBo(AcLoanLogBo acLoanLogBo) {
		this.acLoanLogBo = acLoanLogBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormloanlog0002() {
		return formloanlog0002;
	}
	public void setFormloanlog0002(FormData formloanlog0002) {
		this.formloanlog0002 = formloanlog0002;
	}
	public FormData getFormloanlog0001() {
		return formloanlog0001;
	}
	public void setFormloanlog0001(FormData formloanlog0001) {
		this.formloanlog0001 = formloanlog0001;
	}
	public void setLoanLogNo(String loanLogNo){
		this.loanLogNo = loanLogNo;
	}		
	public String getLoanLogNo(){
		return loanLogNo;
	}
}