package  app.creditapp.aft.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.aft.bo.AftRewDealBo;
import app.creditapp.aft.bo.AftRewPactBo;
import app.creditapp.aft.entity.AftRewDeal;
import app.creditapp.aft.entity.AftRewPact;
import app.util.DateUtil;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftRewPactAction.java
 **/
public class AftRewPactAction extends BaseFormBean {

	//页面传值
	private AftRewPact aftRewPact;
	private List<AftRewPact> aftRewPactList;

	//注入AftRewPactBo
	private AftRewPactBo aftRewPactBo;

	private String query;
	private String rewId;	
	private String rewName;
	private FormData formaft0014;
	private FormData formaft0015;
	private FormData formaft0124;
	private FormData formaft0125;
	
	private FormService formService = new FormService();
	
	private String dealRest;
	private FormData formaft0021;
	private FormData formaftrew0015;
	private AftRewDeal aftRewDeal;
	private AftRewDealBo aftRewDealBo;
	
	
	public AftRewPactAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0014 = formService.getFormData("aft0014");
		aftRewPact = new AftRewPact();
		getFormValue(formaft0014);
		setObjValue(formaft0014, aftRewPact);
		aftRewPact.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		aftRewPactList = (List) aftRewPactBo.findByPage(ipage, aftRewPact).getResult();
		return "list";
	}	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0015 = formService.getFormData("aft0015");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0015 = formService.getFormData("aft0015");
		getFormValue(formaft0015);
		aftRewPact = new AftRewPact();
		setObjValue(formaft0015, aftRewPact);
//		aftRewPact.setBrNo(User.getBrno(this.getHttpRequest()));
		aftRewPact.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftRewPact.setDealRest("02");
//		aftRewPact.setRewNo(aftRewPact.getRewName());
		aftRewPactBo.insert(aftRewPact);
		this.addActionMessage("保存成功");
		getObjValue(formaft0015, aftRewPact);
		return "detail";
	}
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insertToRew() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrew0015 = formService.getFormData("aftrew0015");
		getFormValue(formaftrew0015);
		aftRewDeal = new AftRewDeal();
		setObjValue(formaftrew0015, aftRewDeal);
		aftRewDeal.setDealDate(DateUtil.getDate());
		aftRewDeal.setTxDate(DateUtil.getDate());
		aftRewDeal.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		aftRewDeal.setDealRest("02");
		aftRewDealBo.insert(aftRewDeal);
		
		aftRewPact = new AftRewPact();
		aftRewPact.setRewId(aftRewDeal.getRewId());
		aftRewPact.setDealRest("02");
		aftRewPactBo.updateDealRest(aftRewPact);
		
		this.addActionMessage("保存成功");
		getObjValue(formaftrew0015, aftRewDeal);
		query = "query";
		return "detail";
	}

	/**
	 * 分页查询催收列表
	 * @return
	 * @throws Exception
	 */
	public String findByPageForColl() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0125 = formService.getFormData("aft0125");
		aftRewPact = new AftRewPact();
		getFormValue(formaft0125);
		setObjValue(formaft0125, aftRewPact);
		Ipage ipage = this.getIpage();
		aftRewPactList = (List) aftRewPactBo.findByPage(ipage, aftRewPact).getResult();
		return "list";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0015 = formService.getFormData("aft0015");
		getFormValue(formaft0015);
		aftRewPact = new AftRewPact();
		setObjValue(formaft0015, aftRewPact);
		aftRewPact.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftRewPactBo.update(aftRewPact);
		this.addActionMessage("保存成功");
		getObjValue(formaft0015, aftRewPact);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0014 = formService.getFormData("aft0014");
		aftRewPact = new AftRewPact();
		aftRewPact.setRewId(rewId);
		aftRewPactBo.del(aftRewPact);
		this.addActionMessage("删除成功");
		aftRewPact = new AftRewPact();
		aftRewPact.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftRewPactList = (List) aftRewPactBo.findByPage(ipage, aftRewPact).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0015 = formService.getFormData("aft0015");
		aftRewPact = new AftRewPact();
		aftRewPact.setRewId(rewId);
		aftRewPact = aftRewPactBo.getById(aftRewPact);
		getObjValue(formaft0015, aftRewPact);
		return "detail";
	}
	/**
	 * 预警处理查询
	 * @return
	 * @throws Exception
	 */
	public String getByIdToRew() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		if("02".equals(dealRest)){
			
			formaft0021 = formService.getFormData("aft0021");
			aftRewDeal = new AftRewDeal();
			query = "query";
			aftRewDeal.setRewId(rewId);
			aftRewDeal = aftRewDealBo.getById(aftRewDeal);
			aftRewDeal.setRewName(rewName);
			getObjValue(formaft0021, aftRewDeal);
			return "detailRead";
		}else{
			formaftrew0015 = formService.getFormData("aftrew0015");
			aftRewPact = new AftRewPact();
			aftRewPact.setRewId(rewId);
			aftRewPact = aftRewPactBo.getById(aftRewPact);
			getObjValue(formaftrew0015, aftRewPact);
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
		 formaft0015 = formService.getFormData("aft0015");
		 getFormValue(formaft0015);
		 validateFormData(formaft0015);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0015 = formService.getFormData("aft0015");
		 getFormValue(formaft0015);
		 validateFormData(formaft0015);
  	}
	public AftRewPact getAftRewPact() {
		return aftRewPact;
	}
	public void setAftRewPact(AftRewPact  aftRewPact) {
		this.aftRewPact = aftRewPact;
	}
	public List<AftRewPact> getAftRewPactList() {
		return aftRewPactList;
	}
	public void setAftRewPactList(List<AftRewPact> aftRewPactList) {
		this.aftRewPactList = aftRewPactList;
	}
	public AftRewPactBo getAftRewPactBo() {
		return aftRewPactBo;
	}
	public void setAftRewPactBo(AftRewPactBo aftRewPactBo) {
		this.aftRewPactBo = aftRewPactBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaft0015() {
		return formaft0015;
	}
	public void setFormaft0015(FormData formaft0015) {
		this.formaft0015 = formaft0015;
	}
	public FormData getFormaft0014() {
		return formaft0014;
	}
	public void setFormaft0014(FormData formaft0014) {
		this.formaft0014 = formaft0014;
	}
	public void setRewId(String rewId){
		this.rewId = rewId;
	}		
	public String getRewId(){
		return rewId;
	}
	public FormData getFormaftrew0015() {
		return formaftrew0015;
	}
	public void setFormaftrew0015(FormData formaftrew0015) {
		this.formaftrew0015 = formaftrew0015;
	}
	public AftRewDealBo getAftRewDealBo() {
		return aftRewDealBo;
	}
	public void setAftRewDealBo(AftRewDealBo aftRewDealBo) {
		this.aftRewDealBo = aftRewDealBo;
	}
	public AftRewDeal getAftRewDeal() {
		return aftRewDeal;
	}
	public void setAftRewDeal(AftRewDeal aftRewDeal) {
		this.aftRewDeal = aftRewDeal;
	}
	public String getDealRest() {
		return dealRest;
	}
	public void setDealRest(String dealRest) {
		this.dealRest = dealRest;
	}
	public FormData getFormaft0021() {
		return formaft0021;
	}
	public void setFormaft0021(FormData formaft0021) {
		this.formaft0021 = formaft0021;
	}
	public FormData getFormaft0124() {
		return formaft0124;
	}
	public void setFormaft0124(FormData formaft0124) {
		this.formaft0124 = formaft0124;
	}
	public FormData getFormaft0125() {
		return formaft0125;
	}
	public void setFormaft0125(FormData formaft0125) {
		this.formaft0125 = formaft0125;
	}
	/**
	 * @return the rewName
	 */
	public String getRewName() {
		return rewName;
	}
	/**
	 * @param rewName the rewName to set
	 */
	public void setRewName(String rewName) {
		this.rewName = rewName;
	}
}