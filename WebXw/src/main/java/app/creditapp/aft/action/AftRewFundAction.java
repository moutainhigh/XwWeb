package  app.creditapp.aft.action;
import java.util.List;

import app.util.DateUtil;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewDealBo;
import app.creditapp.aft.bo.AftRewFundBo;
import app.creditapp.aft.entity.AftRewDeal;
import app.creditapp.aft.entity.AftRewFund;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftRewFundAction.java
 **/
public class AftRewFundAction extends BaseFormBean {

	//页面传值
	private AftRewFund aftRewFund;
	private List<AftRewFund> aftRewFundList;

	//注入AftRewFundBo
	private AftRewFundBo aftRewFundBo;

	private String query;
	private String rewId;	
	private String rewName;
	private FormData formaft0018;
	private FormData formaft0019;
	private FormService formService = new FormService();
	
	private String dealRest;
	private FormData formaft0021;
	private FormData formaftrew0015;
	private AftRewDeal aftRewDeal;
	private AftRewDealBo aftRewDealBo;
	
	public AftRewFundAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0018 = formService.getFormData("aft0018");
		aftRewFund = new AftRewFund();
		getFormValue(formaft0018);
		setObjValue(formaft0018, aftRewFund);
		aftRewFund.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		aftRewFundList = (List) aftRewFundBo.findByPage(ipage, aftRewFund).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0019 = formService.getFormData("aft0019");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0019 = formService.getFormData("aft0019");
		getFormValue(formaft0019);
		aftRewFund = new AftRewFund();
		setObjValue(formaft0019, aftRewFund);
		aftRewFund.setTxDate(User.getSys_date(this.getHttpRequest()));
//		aftRewFund.setRewNo(aftRewFund.getRewName());
		aftRewFundBo.insert(aftRewFund);
		this.addActionMessage("保存成功");
		getObjValue(formaft0019, aftRewFund);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0019 = formService.getFormData("aft0019");
		getFormValue(formaft0019);
		aftRewFund = new AftRewFund();
		setObjValue(formaft0019, aftRewFund);
		aftRewFund.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftRewFundBo.update(aftRewFund);
		this.addActionMessage("保存成功");
		getObjValue(formaft0019, aftRewFund);
		return "detail";
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0018 = formService.getFormData("aft0018");
		aftRewFund = new AftRewFund();
		aftRewFund.setRewId(rewId);
		aftRewFundBo.del(aftRewFund);
		this.addActionMessage("删除成功");
		aftRewFund = new AftRewFund();
		Ipage ipage = this.getIpage();
		aftRewFundList = (List) aftRewFundBo.findByPage(ipage, aftRewFund).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0019 = formService.getFormData("aft0019");
		aftRewFund = new AftRewFund();
		aftRewFund.setRewId(rewId);
		aftRewFund = aftRewFundBo.getById(aftRewFund);
		getObjValue(formaft0019, aftRewFund);
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
			query = "query";
			formaft0021 = formService.getFormData("aft0021");
			aftRewDeal = new AftRewDeal();
			aftRewDeal.setRewId(rewId);
			aftRewDeal = aftRewDealBo.getById(aftRewDeal);
			aftRewDeal.setRewName(rewName);
			getObjValue(formaft0021, aftRewDeal);
			return "detailRead";
		}else{
			formaftrew0015 = formService.getFormData("aftrew0015");
			aftRewFund = new AftRewFund();
			aftRewFund.setRewId(rewId);
			aftRewFund = aftRewFundBo.getById(aftRewFund);
			getObjValue(formaftrew0015, aftRewFund);
			return "detail";
		}
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
		
		aftRewFund = new AftRewFund();
		aftRewFund.setRewId(aftRewDeal.getRewId());
		aftRewFund.setDealRest("02");
		aftRewFundBo.updateDealRest(aftRewFund);
		
		this.addActionMessage("保存成功");
		getObjValue(formaftrew0015, aftRewDeal);
		query = "query";
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0019 = formService.getFormData("aft0019");
		 getFormValue(formaft0019);
		 validateFormData(formaft0019);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0019 = formService.getFormData("aft0019");
		 getFormValue(formaft0019);
		 validateFormData(formaft0019);
  	}
	public AftRewFund getAftRewFund() {
		return aftRewFund;
	}
	public void setAftRewFund(AftRewFund  aftRewFund) {
		this.aftRewFund = aftRewFund;
	}
	public List<AftRewFund> getAftRewFundList() {
		return aftRewFundList;
	}
	public void setAftRewFundList(List<AftRewFund> aftRewFundList) {
		this.aftRewFundList = aftRewFundList;
	}
	public AftRewFundBo getAftRewFundBo() {
		return aftRewFundBo;
	}
	public void setAftRewFundBo(AftRewFundBo aftRewFundBo) {
		this.aftRewFundBo = aftRewFundBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaft0019() {
		return formaft0019;
	}
	public void setFormaft0019(FormData formaft0019) {
		this.formaft0019 = formaft0019;
	}
	public FormData getFormaft0018() {
		return formaft0018;
	}
	public void setFormaft0018(FormData formaft0018) {
		this.formaft0018 = formaft0018;
	}
	public void setRewId(String rewId){
		this.rewId = rewId;
	}		
	public String getRewId(){
		return rewId;
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
	public FormData getFormaftrew0015() {
		return formaftrew0015;
	}
	public void setFormaftrew0015(FormData formaftrew0015) {
		this.formaftrew0015 = formaftrew0015;
	}
	public AftRewDeal getAftRewDeal() {
		return aftRewDeal;
	}
	public void setAftRewDeal(AftRewDeal aftRewDeal) {
		this.aftRewDeal = aftRewDeal;
	}
	public AftRewDealBo getAftRewDealBo() {
		return aftRewDealBo;
	}
	public void setAftRewDealBo(AftRewDealBo aftRewDealBo) {
		this.aftRewDealBo = aftRewDealBo;
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