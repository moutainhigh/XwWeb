package  app.creditapp.cif.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifPersCareerBo;
import app.creditapp.cif.bo.CifPersInfBo;
import app.creditapp.cif.entity.CifAcct;
import app.creditapp.cif.entity.CifPersCareer;
import app.creditapp.cif.entity.CifPersInf;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CifPersCareerAction.java
 * Description:
 **/
public class CifPersCareerAction extends BaseFormBean {

	//页面传值
	private CifPersCareer cifPersCareer;
	private CifPersInf cifPersInf;
	private List<CifPersCareer> cifPersCareerList;
	private String backSts;
	
	//注入CifPersCareerBo
	private CifPersCareerBo cifPersCareerBo;
	private CifPersInfBo cifPersInfBo;
	
	private String query;
	private String recId;	
	private String cifNo;
	private String brNo;
	private FormData formcif0013;
	private FormData formcif0014;
	private FormData formcif0017;
	private FormService formService = new FormService();
	
	public CifPersCareerAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcif0013 = formService.getFormData("cif0013");
		cifPersCareer = new CifPersCareer();
		getFormValue(formcif0013);
		setObjValue(formcif0013, cifPersCareer);
		cifPersCareer.setCifNo(cifNo);
		cifPersCareer.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		cifPersCareerList = (List) cifPersCareerBo.findByPage(ipage, cifPersCareer).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0014 = formService.getFormData("cif0014");
		cifPersCareer = new CifPersCareer();
		cifPersInf = new CifPersInf();
		cifPersInf.setCifNo(cifNo);
		cifPersInf = cifPersInfBo.getById(cifPersInf);
		cifPersCareer.setBrName(cifPersInf.getBrName());
		cifPersCareer.setCifName(cifPersInf.getCifName());
		cifPersCareer.setBrNo(cifPersInf.getBrNo());
		cifPersCareer.setCifNo(cifNo);
		cifPersCareer.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		cifPersCareer.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//		cifPersCareer.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		getObjValue(formcif0014, cifPersCareer);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcif0014 = formService.getFormData("cif0014");
		getFormValue(formcif0014);
		cifPersCareer = new CifPersCareer();
		setObjValue(formcif0014, cifPersCareer);
		cifPersCareer.setBrNo(brNo);
		cifPersCareer.setTxDate(User.getSys_date(ServletActionContext.getRequest()));
		cifPersCareer.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		String year = ServletActionContext.getRequest().getParameter("workYear");
		cifPersCareer.setWorkYear(year);
		cifPersCareerBo.insert(cifPersCareer);
		getObjValue(formcif0014, cifPersCareer);
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
		formcif0014 = formService.getFormData("cif0014");
		getFormValue(formcif0014);
		cifPersCareer = new CifPersCareer();
		setObjValue(formcif0014, cifPersCareer);
		String year = ServletActionContext.getRequest().getParameter("workYear");
		cifPersCareer.setWorkYear(year);
		cifPersCareer.setUpDate(User.getSys_date(ServletActionContext.getRequest()));
		cifPersCareerBo.update(cifPersCareer);
		getObjValue(formcif0014, cifPersCareer);
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
		cifPersCareer = new CifPersCareer();
		cifPersCareer.setRecId(recId);
		cifPersCareerBo.del(cifPersCareer);
		this.addActionMessage("删除成功");
		cifPersCareer = new CifPersCareer();
    	cifPersCareer.setCifNo(cifNo);
    	cifPersCareer.setBrNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifPersCareerList = (List) cifPersCareerBo.findByPageQuotaForCif(ipage, cifPersCareer).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcif0014 = formService.getFormData("cif0014");
		formcif0017 = formService.getFormData("cif0017");
		cifPersCareer = new CifPersCareer();
		cifPersCareer.setRecId(recId);
		ServletActionContext.getRequest().setAttribute("cfd", cifNo);
		cifPersCareer = cifPersCareerBo.getById(cifPersCareer);
		if("query".equals(query)){
			getObjValue(formcif0017, cifPersCareer);
		}else{
			getObjValue(formcif0014, cifPersCareer);
		}
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcif0014 = formService.getFormData("cif0014");
		 getFormValue(formcif0014);
		 validateFormData(formcif0014);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcif0014 = formService.getFormData("cif0014");
		 getFormValue(formcif0014);
		 validateFormData(formcif0014);
  	}
	
	 /**
     * 根据客户号显示所有的的职业信息
     * @return
     * @throws Exception
     */
    public String listQuotaForCif() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	cifPersCareer = new CifPersCareer();
    	cifPersCareer.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifPersCareerList = (List) cifPersCareerBo.findByPageQuotaForCif(ipage, cifPersCareer).getResult();
    	return "list";
    }
    /**
     * 根据客户号显示所有的的职业信息
     * @return
     * @throws Exception
     */
    public String listQuotaForCif_Read() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	cifPersCareer = new CifPersCareer();
    	cifPersCareer.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	cifPersCareerList = (List) cifPersCareerBo.findByPageQuotaForCif(ipage, cifPersCareer).getResult();
    	return "list_Read";
    }
    
    /**
	 * 根据客户号查询相关职业信息
	 * @return
	 * @throws Exception
	 */
	public String findForCif() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcif0014 = formService.getFormData("cif0014");
		cifPersCareer = new CifPersCareer();
		cifPersCareer.setCifNo(cifNo);
		cifPersCareer = cifPersCareerBo.findForCif(cifPersCareer);
		getObjValue(formcif0014, cifPersCareer);
		return "detail";
	}
	
	public CifPersCareer getCifPersCareer() {
		return cifPersCareer;
	}
	public void setCifPersCareer(CifPersCareer  cifPersCareer) {
		this.cifPersCareer = cifPersCareer;
	}
	public List<CifPersCareer> getCifPersCareerList() {
		return cifPersCareerList;
	}
	public void setCifPersCareerList(List<CifPersCareer> cifPersCareerList) {
		this.cifPersCareerList = cifPersCareerList;
	}
	public CifPersCareerBo getCifPersCareerBo() {
		return cifPersCareerBo;
	}
	public void setCifPersCareerBo(CifPersCareerBo cifPersCareerBo) {
		this.cifPersCareerBo = cifPersCareerBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcif0014() {
		return formcif0014;
	}
	public void setFormcif0014(FormData formcif0014) {
		this.formcif0014 = formcif0014;
	}
	public FormData getFormcif0013() {
		return formcif0013;
	}
	public void setFormcif0013(FormData formcif0013) {
		this.formcif0013 = formcif0013;
	}
	public void setRecId(String recId){
		this.recId = recId;
	}		
	public String getRecId(){
		return recId;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public CifPersInf getCifPersInf() {
		return cifPersInf;
	}
	public void setCifPersInf(CifPersInf cifPersInf) {
		this.cifPersInf = cifPersInf;
	}
	public CifPersInfBo getCifPersInfBo() {
		return cifPersInfBo;
	}
	public void setCifPersInfBo(CifPersInfBo cifPersInfBo) {
		this.cifPersInfBo = cifPersInfBo;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
	public FormData getFormcif0017() {
		return formcif0017;
	}
	public void setFormcif0017(FormData formcif0017) {
		this.formcif0017 = formcif0017;
	}
}