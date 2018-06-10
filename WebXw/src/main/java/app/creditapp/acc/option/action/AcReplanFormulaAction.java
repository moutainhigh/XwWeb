package  app.creditapp.acc.option.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.option.bo.AcReplanFormulaBo;
import app.creditapp.acc.option.entity.AcReplanFormula;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcReplanFormulaAction.java
 * Description:
 **/
public class AcReplanFormulaAction extends BaseFormBean {

	//页面传值
	private AcReplanFormula acReplanFormula;
	private List<AcReplanFormula> acReplanFormulaList;

	//注入AcReplanFormulaBo
	private AcReplanFormulaBo acReplanFormulaBo;

	private String query;
	private String formulaId;
	private String formulaName;
	private String customEle;
	private String payCapDes;
	private String payCap;
	private String payInteDes;
	private String payInte;
	private FormData formpop001;
	private FormData formreplan005;
	private FormData formreplan006;
	private FormService formService = new FormService();
	
	public AcReplanFormulaAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formreplan005 = formService.getFormData("replan005");
		acReplanFormula = new AcReplanFormula();
		getFormValue(formreplan005);
		setObjValue(formreplan005, acReplanFormula);
		acReplanFormula.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acReplanFormulaList = (List) acReplanFormulaBo.findByPage(ipage, acReplanFormula).getResult();
//		for(AcReplanFormula arf : acReplanFormulaList){
//			if(arf.getPayInteDes().length()>15){
//				arf.setPayInteDes(arf.getPayInteDes().substring(0, 15));
//			}
//			if(arf.getPayCapDes().length()>15){
//				arf.setPayCapDes(arf.getPayCapDes().substring(0, 15));
//			}
//		}
		return "list";
	}
	
	public String findByPageForPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpop001 = formService.getFormData("pop001");
		acReplanFormula = new AcReplanFormula();
		getFormValue(formpop001);
		setObjValue(formpop001, acReplanFormula);
		acReplanFormula.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acReplanFormulaList = (List) acReplanFormulaBo.findByPage(ipage, acReplanFormula).getResult();
		return "listForPop";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formreplan006 = formService.getFormData("replan006");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		acReplanFormula = new AcReplanFormula();
		acReplanFormula.setFormulaName(formulaName);
		acReplanFormula.setCustomEle(customEle);
		acReplanFormula.setPayCapDes(payCapDes);
		acReplanFormula.setPayCap(payCap);
		acReplanFormula.setPayInteDes(payInteDes);
		acReplanFormula.setPayInte(payInte);
		acReplanFormula.setOpNo(User.getLoginid(this.getHttpRequest()));
		acReplanFormula.setBrNo(User.getBrno(this.getHttpRequest()));
		acReplanFormula.setUpDate(User.getSys_date(getHttpRequest()));
		acReplanFormulaBo.insert(acReplanFormula);
		this.addActionMessage("操作成功！");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		acReplanFormula = new AcReplanFormula();
		acReplanFormula.setFormulaId(formulaId);
		acReplanFormula.setFormulaName(formulaName);
		acReplanFormula.setCustomEle(customEle);
		acReplanFormula.setPayCapDes(payCapDes);
		acReplanFormula.setPayCap(payCap);
		acReplanFormula.setPayInteDes(payInteDes);
		acReplanFormula.setPayInte(payInte);
		acReplanFormula.setOpNo(User.getLoginid(this.getHttpRequest()));
		acReplanFormula.setBrNo(User.getBrno(this.getHttpRequest()));
		acReplanFormula.setUpDate(User.getSys_date(getHttpRequest()));
		acReplanFormulaBo.update(acReplanFormula);
		getObjValue(formreplan006, acReplanFormula);
		this.addActionMessage("操作成功！");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formreplan005 = formService.getFormData("replan005");
		acReplanFormula = new AcReplanFormula();
		acReplanFormula.setFormulaId(formulaId);
		acReplanFormulaBo.del(acReplanFormula);
		this.addActionMessage("删除成功");
		acReplanFormula = new AcReplanFormula();
		acReplanFormula.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acReplanFormulaList = (List) acReplanFormulaBo.findByPage(ipage, acReplanFormula).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formreplan006 = formService.getFormData("replan006");
		acReplanFormula = new AcReplanFormula();
		acReplanFormula.setFormulaId(formulaId);
		acReplanFormula = acReplanFormulaBo.getById(acReplanFormula);
		formulaName = acReplanFormula.getFormulaName();
		customEle = acReplanFormula.getCustomEle();
		payCapDes = acReplanFormula.getPayCapDes();
		payCap = acReplanFormula.getPayCap();
		payInteDes = acReplanFormula.getPayInteDes();
		payInte = acReplanFormula.getPayInte();
		getObjValue(formreplan006, acReplanFormula);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formreplan006 = formService.getFormData("replan006");
		 getFormValue(formreplan006);
		 validateFormData(formreplan006);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formreplan006 = formService.getFormData("replan006");
		 getFormValue(formreplan006);
		 validateFormData(formreplan006);
  	}
	public AcReplanFormula getAcReplanFormula() {
		return acReplanFormula;
	}
	public void setAcReplanFormula(AcReplanFormula  acReplanFormula) {
		this.acReplanFormula = acReplanFormula;
	}
	public List<AcReplanFormula> getAcReplanFormulaList() {
		return acReplanFormulaList;
	}
	public void setAcReplanFormulaList(List<AcReplanFormula> acReplanFormulaList) {
		this.acReplanFormulaList = acReplanFormulaList;
	}
	public AcReplanFormulaBo getAcReplanFormulaBo() {
		return acReplanFormulaBo;
	}
	public void setAcReplanFormulaBo(AcReplanFormulaBo acReplanFormulaBo) {
		this.acReplanFormulaBo = acReplanFormulaBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormreplan006() {
		return formreplan006;
	}
	public void setFormreplan006(FormData formreplan006) {
		this.formreplan006 = formreplan006;
	}
	public FormData getFormreplan005() {
		return formreplan005;
	}
	public void setFormreplan005(FormData formreplan005) {
		this.formreplan005 = formreplan005;
	}
	public void setFormulaId(String formulaId){
		this.formulaId = formulaId;
	}		
	public String getFormulaId(){
		return formulaId;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	public FormData getFormpop001() {
		return formpop001;
	}
	public void setFormpop001(FormData formpop001) {
		this.formpop001 = formpop001;
	}
	public String getCustomEle() {
		return customEle;
	}
	public void setCustomEle(String customEle) {
		this.customEle = customEle;
	}
	public String getPayCapDes() {
		return payCapDes;
	}
	public void setPayCapDes(String payCapDes) {
		this.payCapDes = payCapDes;
	}
	public String getPayCap() {
		return payCap;
	}
	public void setPayCap(String payCap) {
		this.payCap = payCap;
	}
	public String getPayInteDes() {
		return payInteDes;
	}
	public void setPayInteDes(String payInteDes) {
		this.payInteDes = payInteDes;
	}
	public String getPayInte() {
		return payInte;
	}
	public void setPayInte(String payInte) {
		this.payInte = payInte;
	}
}