package  app.creditapp.acc.option.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFeeFormulaBo;
import app.creditapp.acc.option.entity.AcFeeFormula;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcFeeFormulaAction.java
 * Description:
 **/
public class AcFeeFormulaAction extends BaseFormBean {

	//页面传值
	private AcFeeFormula acFeeFormula;
	private List<AcFeeFormula> acFeeFormulaList;

	//注入AcFeeFormulaBo
	private AcFeeFormulaBo acFeeFormulaBo;

	private String query;
	private String feeFormId;
	private String feeFormName;
	private String frId;
	private String feeFormulaDes;
	private String feeFormula;
	private FormData formfee201;
	private FormData formfee202;
	private FormService formService = new FormService();
	
	public AcFeeFormulaAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfee201 = formService.getFormData("fee201");
		acFeeFormula = new AcFeeFormula();
		getFormValue(formfee201);
		setObjValue(formfee201, acFeeFormula);
		Ipage ipage = this.getIpage();
		acFeeFormulaList = (List) acFeeFormulaBo.findByPage(ipage, acFeeFormula).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee202 = formService.getFormData("fee202");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee202 = formService.getFormData("fee202");
		getFormValue(formfee202);
		acFeeFormula = new AcFeeFormula();
		setObjValue(formfee202, acFeeFormula);
		acFeeFormulaBo.insert(acFeeFormula);
		getObjValue(formfee202, acFeeFormula);
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
		formfee202 = formService.getFormData("fee202");
		getFormValue(formfee202);
		acFeeFormula = new AcFeeFormula();
		setObjValue(formfee202, acFeeFormula);
		acFeeFormulaBo.update(acFeeFormula);
		getObjValue(formfee202, acFeeFormula);
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
		formfee201 = formService.getFormData("fee201");
		acFeeFormula = new AcFeeFormula();
		acFeeFormula.setFeeFormId(feeFormId);
		acFeeFormulaBo.del(acFeeFormula);
		this.addActionMessage("删除成功");
		acFeeFormula = new AcFeeFormula();
		Ipage ipage = this.getIpage();
		acFeeFormulaList = (List) acFeeFormulaBo.findByPage(ipage, acFeeFormula).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfee202 = formService.getFormData("fee202");
		acFeeFormula = new AcFeeFormula();
		acFeeFormula.setFeeFormId(feeFormId);
		acFeeFormula = acFeeFormulaBo.getById(acFeeFormula);
		feeFormName = acFeeFormula.getFeeFormName();
		frId = acFeeFormula.getFrId();
		feeFormulaDes = acFeeFormula.getFeeFormulaDes();
		feeFormula = acFeeFormula.getFeeFormula();
		getObjValue(formfee202, acFeeFormula);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfee202 = formService.getFormData("fee202");
		 getFormValue(formfee202);
		 validateFormData(formfee202);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfee202 = formService.getFormData("fee202");
		 getFormValue(formfee202);
		 validateFormData(formfee202);
  	}
	public AcFeeFormula getAcFeeFormula() {
		return acFeeFormula;
	}
	public void setAcFeeFormula(AcFeeFormula  acFeeFormula) {
		this.acFeeFormula = acFeeFormula;
	}
	public List<AcFeeFormula> getAcFeeFormulaList() {
		return acFeeFormulaList;
	}
	public void setAcFeeFormulaList(List<AcFeeFormula> acFeeFormulaList) {
		this.acFeeFormulaList = acFeeFormulaList;
	}
	public AcFeeFormulaBo getAcFeeFormulaBo() {
		return acFeeFormulaBo;
	}
	public void setAcFeeFormulaBo(AcFeeFormulaBo acFeeFormulaBo) {
		this.acFeeFormulaBo = acFeeFormulaBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfee202() {
		return formfee202;
	}
	public void setFormfee202(FormData formfee202) {
		this.formfee202 = formfee202;
	}
	public FormData getFormfee201() {
		return formfee201;
	}
	public void setFormfee201(FormData formfee201) {
		this.formfee201 = formfee201;
	}
	public void setFeeFormId(String feeFormId){
		this.feeFormId = feeFormId;
	}		
	public String getFeeFormName() {
		return feeFormName;
	}
	public void setFeeFormName(String feeFormName) {
		this.feeFormName = feeFormName;
	}
	public String getFeeFormulaDes() {
		return feeFormulaDes;
	}
	public void setFeeFormulaDes(String feeFormulaDes) {
		this.feeFormulaDes = feeFormulaDes;
	}
	public String getFeeFormula() {
		return feeFormula;
	}
	public void setFeeFormula(String feeFormula) {
		this.feeFormula = feeFormula;
	}
	public String getFeeFormId(){
		return feeFormId;
	}
	public String getFrId() {
		return frId;
	}
	public void setFrId(String frId) {
		this.frId = frId;
	}
}