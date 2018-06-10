package  app.creditapp.cif.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifAcctBo;
import app.creditapp.cif.entity.CifAcct;
import app.creditapp.pact.entity.LnPact;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CifAcctAction.java
 * Description:
 **/
public class CifAcctAction extends BaseFormBean {

	//页面传值
	private CifAcct cifAcct;
	private List<CifAcct> cifAcctList;
	private String backSts;

	//注入CifAcctBo
	private CifAcctBo cifAcctBo;

	private String query;
	private String acUse;		
	private String appId;		
	private String acNo;	
	private String cifNo;
	
	private FormData formacct0001;
	private FormData formacct00102;
	private FormService formService = new FormService();
	
	public CifAcctAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacct0001 = formService.getFormData("acct0001");
		cifAcct = new CifAcct();
		getFormValue(formacct0001);
		setObjValue(formacct0001, cifAcct);
		Ipage ipage = this.getIpage();
		cifAcctList = (List) cifAcctBo.findByPage(ipage, cifAcct).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacct00102 = formService.getFormData("acct00102");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacct00102 = formService.getFormData("acct00102");
		getFormValue(formacct00102);
		cifAcct = new CifAcct();
		setObjValue(formacct00102, cifAcct);
		cifAcctBo.insert(cifAcct);
		getObjValue(formacct00102, cifAcct);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacct00102 = formService.getFormData("acct00102");
		getFormValue(formacct00102);
		cifAcct = new CifAcct();
		setObjValue(formacct00102, cifAcct);
		cifAcctBo.update(cifAcct);
		getObjValue(formacct00102, cifAcct);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacct0001 = formService.getFormData("acct0001");
		cifAcct = new CifAcct();
		cifAcct.setAcUse(acUse);
		cifAcct.setAppId(appId);
		cifAcct.setAcNo(acNo);
		cifAcctBo.del(cifAcct);
		this.addActionMessage("删除成功");
		cifAcct = new CifAcct();
		Ipage ipage = this.getIpage();
		cifAcctList = (List) cifAcctBo.findByPage(ipage, cifAcct).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacct00102 = formService.getFormData("acct00102");
		cifAcct = new CifAcct();
		cifAcct.setAcUse(acUse);
		cifAcct.setCifNo(cifNo);
		cifAcct.setAppId(appId);
		cifAcct.setAcNo(acNo);
		ServletActionContext.getRequest().setAttribute("cfd", cifNo);
		cifAcct = cifAcctBo.getById(cifAcct);
		getObjValue(formacct00102, cifAcct);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacct00102 = formService.getFormData("acct00102");
		 getFormValue(formacct00102);
		 validateFormData(formacct00102);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacct00102 = formService.getFormData("acct00102");
		 getFormValue(formacct00102);
		 validateFormData(formacct00102);
  	}
	
	  /**
     * 根据客户号显示所有的的账户信息
     * @return
     * @throws Exception
     */
    public String listQuotaForCif() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	cifAcct = new CifAcct();
    	cifAcct.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifAcctList = (List) cifAcctBo.findByPageQuotaForCif(ipage, cifAcct).getResult();
    	return "list";
    }
    /**
     * 根据客户号显示所有的的账户信息
     * @return
     * @throws Exception
     */
    public String listQuotaForCif_Read() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	cifAcct = new CifAcct();
    	cifAcct.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifAcctList = (List) cifAcctBo.findByPageQuotaForCif(ipage, cifAcct).getResult();
    	return "list_Read";
    }
    public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
    
	public CifAcct getCifAcct() {
		return cifAcct;
	}
	public void setCifAcct(CifAcct  cifAcct) {
		this.cifAcct = cifAcct;
	}
	public List<CifAcct> getCifAcctList() {
		return cifAcctList;
	}
	public void setCifAcctList(List<CifAcct> cifAcctList) {
		this.cifAcctList = cifAcctList;
	}
	public CifAcctBo getCifAcctBo() {
		return cifAcctBo;
	}
	public void setCifAcctBo(CifAcctBo cifAcctBo) {
		this.cifAcctBo = cifAcctBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormacct00102() {
		return formacct00102;
	}
	public void setFormacct00102(FormData formacct00102) {
		this.formacct00102 = formacct00102;
	}
	public FormData getFormacct0001() {
		return formacct0001;
	}
	public void setFormacct0001(FormData formacct0001) {
		this.formacct0001 = formacct0001;
	}
	public void setAcUse(String acUse){
		this.acUse = acUse;
	}		
	public void setAppId(String appId){
		this.appId = appId;
	}		
	public void setAcNo(String acNo){
		this.acNo = acNo;
	}		
	public String getAcUse(){
		return acUse;
	}
	public String getAppId(){
		return appId;
	}
	public String getAcNo(){
		return acNo;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
}