package  app.creditapp.aft.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.aft.bo.AftRewDealBo;
import app.creditapp.aft.bo.AftRewFundBo;
import app.creditapp.aft.bo.AftRewPactBo;
import app.creditapp.aft.bo.AftRewProjBo;
import app.creditapp.aft.entity.AftRewDeal;
import app.creditapp.aft.entity.AftRewFund;
import app.creditapp.aft.entity.AftRewPact;
import app.creditapp.aft.entity.AftRewProj;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftRewDealAction.java
 * Description:
 **/
public class AftRewDealAction extends BaseFormBean {

	//页面传值
	private AftRewDeal aftRewDeal;
	
	private List<AftRewDeal> aftRewDealList;

	//注入AftRewDealBo
	private AftRewDealBo aftRewDealBo;
	

	private String query;
	private String recId;
	private String rewId;
	private String rewFlag;
	
	private FormData formaft0020;
	private FormData formaft0021;
	private FormService formService = new FormService();
	
	//01业务预警界面
	private FormData formaft0015;
	private AftRewPact aftRewPact;
	private AftRewPactBo aftRewPactBo;
	//02项目预警界面
	private FormData formaft0017;
	private AftRewProj aftRewProj;
	private AftRewProjBo aftRewProjBo;
	//03资金预警界面
	private FormData formaft0019;
	private AftRewFund aftRewFund;
	private AftRewFundBo aftRewFundBo;
	
	public AftRewDealAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0020 = formService.getFormData("aft0020");
		aftRewDeal = new AftRewDeal();
		getFormValue(formaft0020);
		setObjValue(formaft0020, aftRewDeal);
		Ipage ipage = this.getIpage();
		aftRewDealList = (List) aftRewDealBo.findByPage(ipage, aftRewDeal).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0021 = formService.getFormData("aft0021");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0021 = formService.getFormData("aft0021");
		getFormValue(formaft0021);
		aftRewDeal = new AftRewDeal();
		setObjValue(formaft0021, aftRewDeal);
		aftRewDeal.setOpNo(User.getLoginid(this.getHttpRequest()));
		aftRewDeal.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftRewDeal.setRewId(aftRewDeal.getRewName());
		aftRewDealBo.insert(aftRewDeal);
		this.addActionMessage("保存成功");
		getObjValue(formaft0021, aftRewDeal);
		this.changeFormProperty(formaft0021,"assId","readonly","1");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0021 = formService.getFormData("aft0021");
		getFormValue(formaft0021);
		
		aftRewProj = new AftRewProj();
		aftRewProj.setRewId(rewId);
		aftRewProj = aftRewProjBo.getById(aftRewProj);
		getObjValue(formaft0017, aftRewProj);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0020 = formService.getFormData("aft0020");
		aftRewDeal = new AftRewDeal();
		aftRewDeal.setRecId(recId);
		aftRewDealBo.del(aftRewDeal);
		this.addActionMessage("删除成功");
		aftRewDeal = new AftRewDeal();
		Ipage ipage = this.getIpage();
		aftRewDealList = (List) aftRewDealBo.findByPage(ipage, aftRewDeal).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0021 = formService.getFormData("aft0021");
		aftRewDeal = new AftRewDeal();
		aftRewDeal.setRecId(recId);
		aftRewDeal = aftRewDealBo.getById(aftRewDeal);
		getObjValue(formaft0021, aftRewDeal);
		return "detail";
	}
	public String getByIdToRew() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		if (rewFlag.equals("01")){
			formaft0015 = formService.getFormData("aft0015");
			aftRewPact = new AftRewPact();
			aftRewPact.setRewId(rewId);
			aftRewPact = aftRewPactBo.getById(aftRewPact);
			getObjValue(formaft0015, aftRewPact);
			return "detailToPact";
		}else if (rewFlag.equals("02")){
			formaft0017 = formService.getFormData("aft0017");
			aftRewProj = new AftRewProj();
			aftRewProj.setRewId(rewId);
			aftRewProj = aftRewProjBo.getById(aftRewProj);
			getObjValue(formaft0017, aftRewProj);
			return "detailToProj";
		}else if (rewFlag.equals("03")){
			formaft0019 = formService.getFormData("aft0019");
			aftRewFund = new AftRewFund();
			aftRewFund.setRewId(rewId);
			aftRewFund = aftRewFundBo.getById(aftRewFund);
			getObjValue(formaft0019, aftRewFund);
			return "detailToFund";
		}else{
			formaft0021 = formService.getFormData("aft0021");
			aftRewDeal = new AftRewDeal();
			aftRewDeal.setRecId(recId);
			aftRewDeal = aftRewDealBo.getById(aftRewDeal);
			getObjValue(formaft0021, aftRewDeal);
			return "detail";
		}
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0021 = formService.getFormData("aft0021");
		 getFormValue(formaft0021);
		 validateFormData(formaft0021);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0021 = formService.getFormData("aft0021");
		 getFormValue(formaft0021);
		 validateFormData(formaft0021);
  	}
	public AftRewDeal getAftRewDeal() {
		return aftRewDeal;
	}
	public void setAftRewDeal(AftRewDeal  aftRewDeal) {
		this.aftRewDeal = aftRewDeal;
	}
	public List<AftRewDeal> getAftRewDealList() {
		return aftRewDealList;
	}
	public void setAftRewDealList(List<AftRewDeal> aftRewDealList) {
		this.aftRewDealList = aftRewDealList;
	}
	public AftRewDealBo getAftRewDealBo() {
		return aftRewDealBo;
	}
	public void setAftRewDealBo(AftRewDealBo aftRewDealBo) {
		this.aftRewDealBo = aftRewDealBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaft0021() {
		return formaft0021;
	}
	public void setFormaft0021(FormData formaft0021) {
		this.formaft0021 = formaft0021;
	}
	public FormData getFormaft0020() {
		return formaft0020;
	}
	public void setFormaft0020(FormData formaft0020) {
		this.formaft0020 = formaft0020;
	}
	public void setRecId(String recId){
		this.recId = recId;
	}		
	public String getRecId(){
		return recId;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public String getRewId() {
		return rewId;
	}
	public void setRewId(String rewId) {
		this.rewId = rewId;
	}
	public String getRewFlag() {
		return rewFlag;
	}
	public void setRewFlag(String rewFlag) {
		this.rewFlag = rewFlag;
	}
	public AftRewProj getAftRewProj() {
		return aftRewProj;
	}
	public void setAftRewProj(AftRewProj aftRewProj) {
		this.aftRewProj = aftRewProj;
	}
	public FormData getFormaft0017() {
		return formaft0017;
	}
	public void setFormaft0017(FormData formaft0017) {
		this.formaft0017 = formaft0017;
	}
	public AftRewProjBo getAftRewProjBo() {
		return aftRewProjBo;
	}
	public void setAftRewProjBo(AftRewProjBo aftRewProjBo) {
		this.aftRewProjBo = aftRewProjBo;
	}
	public FormData getFormaft0015() {
		return formaft0015;
	}
	public void setFormaft0015(FormData formaft0015) {
		this.formaft0015 = formaft0015;
	}
	public AftRewPact getAftRewPact() {
		return aftRewPact;
	}
	public void setAftRewPact(AftRewPact aftRewPact) {
		this.aftRewPact = aftRewPact;
	}
	public AftRewPactBo getAftRewPactBo() {
		return aftRewPactBo;
	}
	public void setAftRewPactBo(AftRewPactBo aftRewPactBo) {
		this.aftRewPactBo = aftRewPactBo;
	}
	public FormData getFormaft0019() {
		return formaft0019;
	}
	public void setFormaft0019(FormData formaft0019) {
		this.formaft0019 = formaft0019;
	}
	public AftRewFund getAftRewFund() {
		return aftRewFund;
	}
	public void setAftRewFund(AftRewFund aftRewFund) {
		this.aftRewFund = aftRewFund;
	}
	public AftRewFundBo getAftRewFundBo() {
		return aftRewFundBo;
	}
	public void setAftRewFundBo(AftRewFundBo aftRewFundBo) {
		this.aftRewFundBo = aftRewFundBo;
	}
	
}