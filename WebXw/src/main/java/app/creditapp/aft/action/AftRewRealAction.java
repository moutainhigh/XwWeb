package  app.creditapp.aft.action;
import java.util.List;

import app.util.DateUtil;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewDealBo;
import app.creditapp.aft.bo.AftRewRealBo;
import app.creditapp.aft.entity.AftRewDeal;
import app.creditapp.aft.entity.AftRewProj;
import app.creditapp.aft.entity.AftRewReal;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftRewRealAction.java
 * Description:
 **/
public class AftRewRealAction extends BaseFormBean {

	//页面传值
	private AftRewReal aftRewReal;
	private List<AftRewReal> aftRewRealList;

	//注入AftRewRealBo
	private AftRewRealBo aftRewRealBo;
	private AftRewDeal aftRewDeal;
	private AftRewDealBo aftRewDealBo;

	private String query;
	private String rewId;	
	private String rewName;
	private String dealRest;
	private FormData formaft0001;
	private FormData formaft0026;
	private FormData formaft0027;
	private FormData formaft0021;
	private FormData formaftrew0025;
	private FormService formService = new FormService();
	
	public AftRewRealAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0026 = formService.getFormData("aft0026");
		aftRewReal = new AftRewReal();
		getFormValue(formaft0026);
		setObjValue(formaft0026, aftRewReal);
		aftRewReal.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftRewRealList = (List) aftRewRealBo.findByPage(ipage, aftRewReal).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0027 = formService.getFormData("aft0027");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0027 = formService.getFormData("aft0027");
		getFormValue(formaft0027);
		aftRewReal = new AftRewReal();
		setObjValue(formaft0027, aftRewReal);
//		aftRewReal.setBrNo(User.getBrno(this.getHttpRequest()));
		aftRewReal.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftRewRealBo.insert(aftRewReal);
		getObjValue(formaft0027, aftRewReal);
		this.addActionMessage("保存成功");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0027 = formService.getFormData("aft0027");
		getFormValue(formaft0027);
		aftRewReal = new AftRewReal();
		setObjValue(formaft0027, aftRewReal);
		aftRewReal.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftRewRealBo.update(aftRewReal);
		getObjValue(formaft0027, aftRewReal);
		this.addActionMessage("保存成功");
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
			formaftrew0025 = formService.getFormData("aftrew0025");
			aftRewReal = new AftRewReal();
			aftRewReal.setRewId(rewId);
			aftRewReal = aftRewRealBo.getById(aftRewReal);
			getObjValue(formaftrew0025, aftRewReal);
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
		formaftrew0025 = formService.getFormData("aftrew0025");
		getFormValue(formaftrew0025);
		aftRewDeal = new AftRewDeal();
		setObjValue(formaftrew0025, aftRewDeal);
		aftRewDeal.setDealDate(DateUtil.getDate());
		aftRewDeal.setTxDate(DateUtil.getDate());
		aftRewDeal.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		aftRewDeal.setDealRest("02");
		aftRewDealBo.insert(aftRewDeal);
		
		aftRewReal = new AftRewReal();
		aftRewReal.setRewId(aftRewDeal.getRewId());
		aftRewReal = aftRewRealBo.getById(aftRewReal);
		aftRewReal.setDealRest("02");
		aftRewRealBo.update(aftRewReal);
		
		this.addActionMessage("保存成功");
		getObjValue(formaftrew0025, aftRewDeal);
		query = "query";
		return "detail";
	}
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0001 = formService.getFormData("aft0001");
		aftRewReal = new AftRewReal();
		aftRewReal.setRewId(rewId);
		aftRewRealBo.del(aftRewReal);
		this.addActionMessage("删除成功");
		aftRewReal = new AftRewReal();
		aftRewReal.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftRewRealList = (List) aftRewRealBo.findByPage(ipage, aftRewReal).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0027 = formService.getFormData("aft0027");
		aftRewReal = new AftRewReal();
		aftRewReal.setRewId(rewId);
		aftRewReal = aftRewRealBo.getById(aftRewReal);
		getObjValue(formaft0027, aftRewReal);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0027 = formService.getFormData("aft0027");
		 getFormValue(formaft0027);
		 validateFormData(formaft0027);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0027 = formService.getFormData("aft0027");
		 getFormValue(formaft0027);
		 validateFormData(formaft0027);
  	}
	public AftRewReal getAftRewReal() {
		return aftRewReal;
	}
	public void setAftRewReal(AftRewReal  aftRewReal) {
		this.aftRewReal = aftRewReal;
	}
	public List<AftRewReal> getAftRewRealList() {
		return aftRewRealList;
	}
	public void setAftRewRealList(List<AftRewReal> aftRewRealList) {
		this.aftRewRealList = aftRewRealList;
	}
	public AftRewRealBo getAftRewRealBo() {
		return aftRewRealBo;
	}
	public void setAftRewRealBo(AftRewRealBo aftRewRealBo) {
		this.aftRewRealBo = aftRewRealBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	public FormData getFormaft0001() {
		return formaft0001;
	}
	public void setFormaft0001(FormData formaft0001) {
		this.formaft0001 = formaft0001;
	}
	public void setRewId(String rewId){
		this.rewId = rewId;
	}		
	public String getRewId(){
		return rewId;
	}
	/**
	 * @return the formaft0026
	 */
	public FormData getFormaft0026() {
		return formaft0026;
	}
	/**
	 * @param formaft0026 the formaft0026 to set
	 */
	public void setFormaft0026(FormData formaft0026) {
		this.formaft0026 = formaft0026;
	}
	/**
	 * @return the dealRest
	 */
	public String getDealRest() {
		return dealRest;
	}
	/**
	 * @param dealRest the dealRest to set
	 */
	public void setDealRest(String dealRest) {
		this.dealRest = dealRest;
	}
	/**
	 * @return the formaft0021
	 */
	public FormData getFormaft0021() {
		return formaft0021;
	}
	/**
	 * @param formaft0021 the formaft0021 to set
	 */
	public void setFormaft0021(FormData formaft0021) {
		this.formaft0021 = formaft0021;
	}
	/**
	 * @return the formaftrew0025
	 */
	public FormData getFormaftrew0025() {
		return formaftrew0025;
	}
	/**
	 * @param formaftrew0025 the formaftrew0025 to set
	 */
	public void setFormaftrew0025(FormData formaftrew0025) {
		this.formaftrew0025 = formaftrew0025;
	}
	/**
	 * @return the aftRewDeal
	 */
	public AftRewDeal getAftRewDeal() {
		return aftRewDeal;
	}
	/**
	 * @param aftRewDeal the aftRewDeal to set
	 */
	public void setAftRewDeal(AftRewDeal aftRewDeal) {
		this.aftRewDeal = aftRewDeal;
	}
	/**
	 * @return the aftRewDealBo
	 */
	public AftRewDealBo getAftRewDealBo() {
		return aftRewDealBo;
	}
	/**
	 * @param aftRewDealBo the aftRewDealBo to set
	 */
	public void setAftRewDealBo(AftRewDealBo aftRewDealBo) {
		this.aftRewDealBo = aftRewDealBo;
	}
	/**
	 * @return the formaft0027
	 */
	public FormData getFormaft0027() {
		return formaft0027;
	}
	/**
	 * @param formaft0027 the formaft0027 to set
	 */
	public void setFormaft0027(FormData formaft0027) {
		this.formaft0027 = formaft0027;
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