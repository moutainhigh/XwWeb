package  app.creditapp.ln.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.ln.bo.LnAcctBo;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnDue;
import app.util.toolkit.Ipage;

/**
 * Title: LnAcctAction.java
 * Description:
 **/
public class LnAcctAction extends BaseFormBean {

	//页面传值
	private LnAcct lnAcct;
	private LnDue lnDue;
	private List<LnAcct> lnAcctList;

	//注入LnAcctBo
	private LnAcctBo lnAcctBo;
	private LnDueBo lnDueBo;

	private String query;
	private String appId;
	private String cifNo;
	private String acUse;		
	private String acNo;
	private String formSts;
	private String loanNo;
	private String dataSts;
	
	private FormData formlnacct0001;
	private FormData formlnacct0002;
	private FormService formService = new FormService();
	
	public LnAcctAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnacct0001 = formService.getFormData("lnacct0001");
		lnAcct = new LnAcct();
		getFormValue(formlnacct0001);
		setObjValue(formlnacct0001, lnAcct);
		Ipage ipage = this.getIpage();
		lnAcctList = (List) lnAcctBo.findByPage(ipage, lnAcct).getResult();
		return "list";
	}
	public String findByPageRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnacct0001 = formService.getFormData("lnacct0001");
		lnAcct = new LnAcct();
		getFormValue(formlnacct0001);
		setObjValue(formlnacct0001, lnAcct);
		Ipage ipage = this.getIpage();
		lnAcctList = (List) lnAcctBo.findByPage(ipage, lnAcct).getResult();
		return "ListRead";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnacct0002 = formService.getFormData("lnacct0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnacct0002 = formService.getFormData("lnacct0002");
		getFormValue(formlnacct0002);
		lnAcct = new LnAcct();
		setObjValue(formlnacct0002, lnAcct);
		lnAcctBo.insert(lnAcct);
		getObjValue(formlnacct0002, lnAcct);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnacct0002 = formService.getFormData("lnacct0002");
		getFormValue(formlnacct0002);
		lnAcct = new LnAcct();
		setObjValue(formlnacct0002, lnAcct);
		lnAcctBo.update(lnAcct);
		getObjValue(formlnacct0002, lnAcct);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnacct0001 = formService.getFormData("lnacct0001");
		lnAcct = new LnAcct();
		lnAcct.setAppId(appId);
		lnAcctBo.del(lnAcct);
		this.addActionMessage("删除成功");
		lnAcct = new LnAcct();
		Ipage ipage = this.getIpage();
		lnAcctList = (List) lnAcctBo.findByPage(ipage, lnAcct).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnacct0002 = formService.getFormData("lnacct0002");
		lnAcct = new LnAcct();
		lnAcct.setCifNo(cifNo);
		lnAcct.setAppId(appId);
		lnAcct.setAcUse(acUse);
		lnAcct.setAcNo(acNo);
		lnAcct = lnAcctBo.getById(lnAcct);
		getObjValue(formlnacct0002, lnAcct);
		return "detail";
	}
	public String getByIdForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnacct0002 = formService.getFormData("lnacct0002");
		lnAcct = new LnAcct();
		lnDue = new LnDue();
		lnDue.setDueNo(loanNo);
		lnDue = lnDueBo.getById(lnDue);
		lnAcct.setAppId(lnDue.getPactId());
		lnAcct = lnAcctBo.getById(lnAcct);
		getObjValue(formlnacct0002, lnAcct);
		return "detail";
	}	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnacct0002 = formService.getFormData("lnacct0002");
		 getFormValue(formlnacct0002);
		 validateFormData(formlnacct0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnacct0002 = formService.getFormData("lnacct0002");
		 getFormValue(formlnacct0002);
		 validateFormData(formlnacct0002);
  	}
	
	 /**
	    * 根据申请ID显示所有的的账户信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForLn() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	lnAcct = new LnAcct();
	   	lnAcct.setAppId(appId);
	   	Ipage ipage = this.getIpage();
	   	lnAcctList = (List) lnAcctBo.findByPageQuotaForLn(ipage, lnAcct).getResult();
	   	return "list";
	   }
	
	public LnAcct getLnAcct() {
		return lnAcct;
	}
	public void setLnAcct(LnAcct  lnAcct) {
		this.lnAcct = lnAcct;
	}
	public List<LnAcct> getLnAcctList() {
		return lnAcctList;
	}
	public void setLnAcctList(List<LnAcct> lnAcctList) {
		this.lnAcctList = lnAcctList;
	}
	public LnAcctBo getLnAcctBo() {
		return lnAcctBo;
	}
	public void setLnAcctBo(LnAcctBo lnAcctBo) {
		this.lnAcctBo = lnAcctBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnacct0002() {
		return formlnacct0002;
	}
	public void setFormlnacct0002(FormData formlnacct0002) {
		this.formlnacct0002 = formlnacct0002;
	}
	public FormData getFormlnacct0001() {
		return formlnacct0001;
	}
	public void setFormlnacct0001(FormData formlnacct0001) {
		this.formlnacct0001 = formlnacct0001;
	}
	public void setAppId(String appId){
		this.appId = appId;
	}		
	public String getAppId(){
		return appId;
	}
	public String getAcUse() {
		return acUse;
	}
	public void setAcUse(String acUse) {
		this.acUse = acUse;
	}
	public String getAcNo() {
		return acNo;
	}
	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public LnDue getLnDue() {
		return lnDue;
	}
	public void setLnDue(LnDue lnDue) {
		this.lnDue = lnDue;
	}
	public LnDueBo getLnDueBo() {
		return lnDueBo;
	}
	public void setLnDueBo(LnDueBo lnDueBo) {
		this.lnDueBo = lnDueBo;
	}
	public String getFormSts() {
		return formSts;
	}
	public String getDataSts() {
		return dataSts;
	}
	public void setDataSts(String dataSts) {
		this.dataSts = dataSts;
	}
	public void setFormSts(String formSts) {
		this.formSts = formSts;
	}
	
}