package  app.creditapp.acc.option.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.option.bo.AcFineFormulaBo;
import app.creditapp.acc.option.entity.AcFineFormula;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcFineFormulaAction.java
 * Description:
 **/
public class AcFineFormulaAction extends BaseFormBean {

	//页面传值
	private AcFineFormula acFineFormula;
	private List<AcFineFormula> acFineFormulaList;

	//注入AcFineFormulaBo
	private AcFineFormulaBo acFineFormulaBo;

	private String query;
	private String fineId;	
	private String fineName;
	private String overRate;
	private String fineFormulaDes;
	private String fineFormula;
	private FormData formfine001;
	private FormData formfine002;
	private FormService formService = new FormService();
	
	public AcFineFormulaAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfine001 = formService.getFormData("fine001");
		acFineFormula = new AcFineFormula();
		getFormValue(formfine001);
		setObjValue(formfine001, acFineFormula);
		Ipage ipage = this.getIpage();
		acFineFormulaList = (List) acFineFormulaBo.findByPage(ipage, acFineFormula).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfine002 = formService.getFormData("fine002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfine002 = formService.getFormData("fine002");
		getFormValue(formfine002);
		acFineFormula = new AcFineFormula();
		setObjValue(formfine002, acFineFormula);
		acFineFormula.setOpNo(User.getLoginid(this.getHttpRequest()));
		acFineFormula.setTxDate(User.getSys_date(this.getHttpRequest()));
		acFineFormula.setFineSts("0");//新增时默认状态为停用
//		acFineFormula.setOverRate(Double.parseDouble(overRate)/1000);
		acFineFormulaBo.insert(acFineFormula);
		getObjValue(formfine002, acFineFormula);
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
		formfine002 = formService.getFormData("fine002");
		getFormValue(formfine002);
		acFineFormula = new AcFineFormula();
		setObjValue(formfine002, acFineFormula);
		acFineFormula.setUpDate(User.getSys_date(this.getHttpRequest()));
//		acFineFormula.setOverRate(Double.parseDouble(overRate)/1000);
		acFineFormulaBo.update(acFineFormula);
		getObjValue(formfine002, acFineFormula);
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
		formfine001 = formService.getFormData("fine001");
		acFineFormula = new AcFineFormula();
		acFineFormula.setFineId(fineId);
		acFineFormulaBo.del(acFineFormula);
		this.addActionMessage("删除成功");
		acFineFormula = new AcFineFormula();
		Ipage ipage = this.getIpage();
		acFineFormulaList = (List) acFineFormulaBo.findByPage(ipage, acFineFormula).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		acFineFormula = new AcFineFormula();
		acFineFormula.setFineId(fineId);
		acFineFormula = acFineFormulaBo.getById(acFineFormula);
		fineName = acFineFormula.getFineName();
		overRate = String.valueOf(acFineFormula.getOverRate()*1000);
		fineFormulaDes = acFineFormula.getFineFormulaDes();
		fineFormula = acFineFormula.getFineFormula();
		return "detail";
	}
	
	/**
	 * 开启操作
	 * @return
	 * @throws Exception
	 */
	public String openReplan() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		acFineFormula = new AcFineFormula();
		acFineFormula.setFineId(fineId);
		acFineFormula.setFineSts("1");
		acFineFormulaBo.updateReplanSts(acFineFormula);
		this.addActionMessage("启用成功！");
		return findByPage();
	}
	
	/**
	 * 关闭操作
	 * @return
	 * @throws Exception
	 */
	public String closeReplan() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		acFineFormula = new AcFineFormula();
		acFineFormula.setFineId(fineId);
		acFineFormula.setFineSts("0");
		acFineFormulaBo.updateReplanSts(acFineFormula);
		this.addActionMessage("关闭成功！");
		return findByPage();
	}
	
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfine002 = formService.getFormData("fine002");
		 getFormValue(formfine002);
		 validateFormData(formfine002);
   }
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfine002 = formService.getFormData("fine002");
		 getFormValue(formfine002);
		 validateFormData(formfine002);
  	}
	public AcFineFormula getAcFineFormula() {
		return acFineFormula;
	}
	public void setAcFineFormula(AcFineFormula  acFineFormula) {
		this.acFineFormula = acFineFormula;
	}
	public List<AcFineFormula> getAcFineFormulaList() {
		return acFineFormulaList;
	}
	public void setAcFineFormulaList(List<AcFineFormula> acFineFormulaList) {
		this.acFineFormulaList = acFineFormulaList;
	}
	public AcFineFormulaBo getAcFineFormulaBo() {
		return acFineFormulaBo;
	}
	public void setAcFineFormulaBo(AcFineFormulaBo acFineFormulaBo) {
		this.acFineFormulaBo = acFineFormulaBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfine002() {
		return formfine002;
	}
	public void setFormfine002(FormData formfine002) {
		this.formfine002 = formfine002;
	}
	public FormData getFormfine001() {
		return formfine001;
	}
	public void setFormfine001(FormData formfine001) {
		this.formfine001 = formfine001;
	}
	public void setFineId(String fineId){
		this.fineId = fineId;
	}		
	public String getFineId(){
		return fineId;
	}
	public String getFineName() {
		return fineName;
	}
	public void setFineName(String fineName) {
		this.fineName = fineName;
	}
	public String getOverRate() {
		return overRate;
	}
	public void setOverRate(String overRate) {
		this.overRate = overRate;
	}
	public String getFineFormulaDes() {
		return fineFormulaDes;
	}
	public void setFineFormulaDes(String fineFormulaDes) {
		this.fineFormulaDes = fineFormulaDes;
	}
	public String getFineFormula() {
		return fineFormula;
	}
	public void setFineFormula(String fineFormula) {
		this.fineFormula = fineFormula;
	}
}