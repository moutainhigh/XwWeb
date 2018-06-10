package  app.creditapp.corp.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpTaRelBo;
import app.creditapp.corp.entity.CorpTaRel;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpTaRelAction.java
 * Description:
 **/
public class CorpTaRelAction extends BaseFormBean {

	//页面传值
	private CorpTaRel corpTaRel;
	private List<CorpTaRel> corpTaRelList;

	//注入CorpTaRelBo
	private CorpTaRelBo corpTaRelBo;

	private String query;
	private String relid;		
	private FormData formcorp0001;
	private FormData formcorp0002;
	private FormService formService = new FormService();
	
	public CorpTaRelAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcorp0001 = formService.getFormData("corp0001");
		corpTaRel = new CorpTaRel();
		getFormValue(formcorp0001);
		setObjValue(formcorp0001, corpTaRel);
		corpTaRel.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		corpTaRelList = (List) corpTaRelBo.findByPage(ipage, corpTaRel).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcorp0002 = formService.getFormData("corp0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcorp0002 = formService.getFormData("corp0002");
		getFormValue(formcorp0002);
		corpTaRel = new CorpTaRel();
		setObjValue(formcorp0002, corpTaRel);
		corpTaRel.setBrNo(User.getBrno(this.getHttpRequest()));
		corpTaRelBo.insert(corpTaRel);
		getObjValue(formcorp0002, corpTaRel);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcorp0002 = formService.getFormData("corp0002");
		getFormValue(formcorp0002);
		corpTaRel = new CorpTaRel();
		setObjValue(formcorp0002, corpTaRel);
		corpTaRelBo.update(corpTaRel);
		getObjValue(formcorp0002, corpTaRel);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcorp0001 = formService.getFormData("corp0001");
		corpTaRel = new CorpTaRel();
		corpTaRel.setRelid(relid);
		corpTaRelBo.del(corpTaRel);
		this.addActionMessage("删除成功");
		corpTaRel = new CorpTaRel();
		corpTaRel.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		corpTaRelList = (List) corpTaRelBo.findByPage(ipage, corpTaRel).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcorp0002 = formService.getFormData("corp0002");
		corpTaRel = new CorpTaRel();
		corpTaRel.setRelid(relid);
		corpTaRel = corpTaRelBo.getById(corpTaRel);
		getObjValue(formcorp0002, corpTaRel);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcorp0002 = formService.getFormData("corp0002");
		 getFormValue(formcorp0002);
		 validateFormData(formcorp0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcorp0002 = formService.getFormData("corp0002");
		 getFormValue(formcorp0002);
		 validateFormData(formcorp0002);
  	}
	public CorpTaRel getCorpTaRel() {
		return corpTaRel;
	}
	public void setCorpTaRel(CorpTaRel  corpTaRel) {
		this.corpTaRel = corpTaRel;
	}
	public List<CorpTaRel> getCorpTaRelList() {
		return corpTaRelList;
	}
	public void setCorpTaRelList(List<CorpTaRel> corpTaRelList) {
		this.corpTaRelList = corpTaRelList;
	}
	public CorpTaRelBo getCorpTaRelBo() {
		return corpTaRelBo;
	}
	public void setCorpTaRelBo(CorpTaRelBo corpTaRelBo) {
		this.corpTaRelBo = corpTaRelBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcorp0002() {
		return formcorp0002;
	}
	public void setFormcorp0002(FormData formcorp0002) {
		this.formcorp0002 = formcorp0002;
	}
	public FormData getFormcorp0001() {
		return formcorp0001;
	}
	public void setFormcorp0001(FormData formcorp0001) {
		this.formcorp0001 = formcorp0001;
	}
	public void setRelid(String relid){
		this.relid = relid;
	}		
	public String getRelid(){
		return relid;
	}
}