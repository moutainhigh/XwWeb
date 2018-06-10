package  app.creditapp.aft.action;
import java.util.List;

import app.util.DateUtil;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewDealBo;
import app.creditapp.aft.bo.AftRewProjBo;
import app.creditapp.aft.entity.AftRewDeal;
import app.creditapp.aft.entity.AftRewProj;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftRewProjAction.java
 **/
public class AftRewProjAction extends BaseFormBean {

	//页面传值
	private AftRewProj aftRewProj;
	private List<AftRewProj> aftRewProjList;

	//注入AftRewProjBo
	private AftRewProjBo aftRewProjBo;

	private String query;
	private String rewId;
	private String rewName;
	private FormData formaft0016;
	private FormData formaft0017;
	private FormService formService = new FormService();
	
	private String dealRest;
	private FormData formaft0021;
	private FormData formaftrew0015;
	private AftRewDeal aftRewDeal;
	private AftRewDealBo aftRewDealBo;
	
	public AftRewProjAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0016 = formService.getFormData("aft0016");
		aftRewProj = new AftRewProj();
		getFormValue(formaft0016);
		setObjValue(formaft0016, aftRewProj);
		aftRewProj.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		aftRewProjList = (List) aftRewProjBo.findByPage(ipage, aftRewProj).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0017 = formService.getFormData("aft0017");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0017 = formService.getFormData("aft0017");
		getFormValue(formaft0017);
		aftRewProj = new AftRewProj();
		setObjValue(formaft0017, aftRewProj);
		aftRewProj.setTxDate(User.getSys_date(this.getHttpRequest()));
//		aftRewProj.setRewNo(aftRewProj.getRewName());
		aftRewProjBo.insert(aftRewProj);
		this.addActionMessage("保存成功");
		getObjValue(formaft0017, aftRewProj);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0017 = formService.getFormData("aft0017");
		getFormValue(formaft0017);
		aftRewProj = new AftRewProj();
		setObjValue(formaft0017, aftRewProj);
		aftRewProj.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftRewProjBo.update(aftRewProj);
		this.addActionMessage("保存成功");
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
		formaft0016 = formService.getFormData("aft0016");
		aftRewProj = new AftRewProj();
		aftRewProj.setRewId(rewId);
		aftRewProjBo.del(aftRewProj);
		this.addActionMessage("删除成功");
		aftRewProj = new AftRewProj();
		Ipage ipage = this.getIpage();
		aftRewProjList = (List) aftRewProjBo.findByPage(ipage, aftRewProj).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0017 = formService.getFormData("aft0017");
		aftRewProj = new AftRewProj();
		aftRewProj.setRewId(rewId);
		aftRewProj = aftRewProjBo.getById(aftRewProj);
		getObjValue(formaft0017, aftRewProj);
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
			aftRewProj = new AftRewProj();
			aftRewProj.setRewId(rewId);
			aftRewProj = aftRewProjBo.getById(aftRewProj);
			getObjValue(formaftrew0015, aftRewProj);
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
		
		aftRewProj = new AftRewProj();
		aftRewProj.setRewId(aftRewDeal.getRewId());
		aftRewProj.setDealRest("02");
		aftRewProjBo.updateDealRest(aftRewProj);
		
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
		 formaft0017 = formService.getFormData("aft0017");
		 getFormValue(formaft0017);
		 validateFormData(formaft0017);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0017 = formService.getFormData("aft0017");
		 getFormValue(formaft0017);
		 validateFormData(formaft0017);
  	}
	public AftRewProj getAftRewProj() {
		return aftRewProj;
	}
	public void setAftRewProj(AftRewProj  aftRewProj) {
		this.aftRewProj = aftRewProj;
	}
	public List<AftRewProj> getAftRewProjList() {
		return aftRewProjList;
	}
	public void setAftRewProjList(List<AftRewProj> aftRewProjList) {
		this.aftRewProjList = aftRewProjList;
	}
	public AftRewProjBo getAftRewProjBo() {
		return aftRewProjBo;
	}
	public void setAftRewProjBo(AftRewProjBo aftRewProjBo) {
		this.aftRewProjBo = aftRewProjBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaft0017() {
		return formaft0017;
	}
	public void setFormaft0017(FormData formaft0017) {
		this.formaft0017 = formaft0017;
	}
	public FormData getFormaft0016() {
		return formaft0016;
	}
	public void setFormaft0016(FormData formaft0016) {
		this.formaft0016 = formaft0016;
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