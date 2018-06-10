package  app.creditapp.sys.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.RulesBaseBo;
import app.creditapp.sys.entity.RulesBase;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: RulesBaseAction.java
 * Description:
 **/
public class RulesBaseAction extends BaseFormBean {

	//页面传值
	private RulesBase rulesBase;
	private List<RulesBase> rulesBaseList;

	//注入RulesBaseBo
	private RulesBaseBo rulesBaseBo;

	private String query;
	private String prdtNo;		
	private FormData formrules0001;
	private FormData formrules0002;
	private FormService formService = new FormService();
	
	public RulesBaseAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formrules0001 = formService.getFormData("rules0001");
		rulesBase = new RulesBase();
		getFormValue(formrules0001);
		setObjValue(formrules0001, rulesBase);
		Ipage ipage = this.getIpage();
		rulesBaseList = (List) rulesBaseBo.findByPage(ipage, rulesBase).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrules0002 = formService.getFormData("rules0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrules0002 = formService.getFormData("rules0002");
		getFormValue(formrules0002);
		rulesBase = new RulesBase();
		setObjValue(formrules0002, rulesBase);
		rulesBaseBo.insert(rulesBase);
		getObjValue(formrules0002, rulesBase);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrules0002 = formService.getFormData("rules0002");
		getFormValue(formrules0002);
		rulesBase = new RulesBase();
		setObjValue(formrules0002, rulesBase);
		rulesBaseBo.update(rulesBase);
		getObjValue(formrules0002, rulesBase);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrules0001 = formService.getFormData("rules0001");
		rulesBase = new RulesBase();
		rulesBase.setPrdtNo(prdtNo);
		rulesBaseBo.del(rulesBase);
		this.addActionMessage("删除成功");
		rulesBase = new RulesBase();
		Ipage ipage = this.getIpage();
		rulesBaseList = (List) rulesBaseBo.findByPage(ipage, rulesBase).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formrules0002 = formService.getFormData("rules0002");
		rulesBase = new RulesBase();
		rulesBase.setPrdtNo(prdtNo);
		rulesBase = rulesBaseBo.getById(rulesBase);
		getObjValue(formrules0002, rulesBase);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formrules0002 = formService.getFormData("rules0002");
		 getFormValue(formrules0002);
		 validateFormData(formrules0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formrules0002 = formService.getFormData("rules0002");
		 getFormValue(formrules0002);
		 validateFormData(formrules0002);
  	}
	public RulesBase getRulesBase() {
		return rulesBase;
	}
	public void setRulesBase(RulesBase  rulesBase) {
		this.rulesBase = rulesBase;
	}
	public List<RulesBase> getRulesBaseList() {
		return rulesBaseList;
	}
	public void setRulesBaseList(List<RulesBase> rulesBaseList) {
		this.rulesBaseList = rulesBaseList;
	}
	public RulesBaseBo getRulesBaseBo() {
		return rulesBaseBo;
	}
	public void setRulesBaseBo(RulesBaseBo rulesBaseBo) {
		this.rulesBaseBo = rulesBaseBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormrules0002() {
		return formrules0002;
	}
	public void setFormrules0002(FormData formrules0002) {
		this.formrules0002 = formrules0002;
	}
	public FormData getFormrules0001() {
		return formrules0001;
	}
	public void setFormrules0001(FormData formrules0001) {
		this.formrules0001 = formrules0001;
	}
	public void setPrdtNo(String prdtNo){
		this.prdtNo = prdtNo;
	}		
	public String getPrdtNo(){
		return prdtNo;
	}
}