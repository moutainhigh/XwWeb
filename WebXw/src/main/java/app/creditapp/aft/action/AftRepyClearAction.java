package  app.creditapp.aft.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRepyClearBo;
import app.creditapp.aft.entity.AftRepyClear;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftRepyClearAction.java
 **/
public class AftRepyClearAction extends BaseFormBean {

	//页面传值
	private AftRepyClear aftRepyClear;
	private List<AftRepyClear> aftRepyClearList;

	//注入AftRepyClearBo
	private AftRepyClearBo aftRepyClearBo;

	private String query;
	private String clearId;		
	private FormData formaftclear0001;
	private FormData formaftclear0002;
	private FormService formService = new FormService();
	
	public AftRepyClearAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftclear0001 = formService.getFormData("aftclear0001");
		aftRepyClear = new AftRepyClear();
		getFormValue(formaftclear0001);
		setObjValue(formaftclear0001, aftRepyClear);
		aftRepyClear.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftRepyClearList = (List) aftRepyClearBo.findByPage(ipage, aftRepyClear).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftclear0002 = formService.getFormData("aftclear0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftclear0002 = formService.getFormData("aftclear0002");
		getFormValue(formaftclear0002);
		aftRepyClear = new AftRepyClear();
		setObjValue(formaftclear0002, aftRepyClear);
		aftRepyClear.setBrNo(User.getBrno(this.getHttpRequest()));
		aftRepyClear.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftRepyClearBo.insert(aftRepyClear);
		getObjValue(formaftclear0002, aftRepyClear);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftclear0002 = formService.getFormData("aftclear0002");
		getFormValue(formaftclear0002);
		aftRepyClear = new AftRepyClear();
		setObjValue(formaftclear0002, aftRepyClear);
		aftRepyClear.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftRepyClearBo.update(aftRepyClear);
		getObjValue(formaftclear0002, aftRepyClear);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftclear0001 = formService.getFormData("aftclear0001");
		aftRepyClear = new AftRepyClear();
		aftRepyClear.setClearId(clearId);
		aftRepyClearBo.del(aftRepyClear);
		this.addActionMessage("删除成功");
		aftRepyClear = new AftRepyClear();
		aftRepyClear.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftRepyClearList = (List) aftRepyClearBo.findByPage(ipage, aftRepyClear).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftclear0002 = formService.getFormData("aftclear0002");
		aftRepyClear = new AftRepyClear();
		aftRepyClear.setClearId(clearId);
		aftRepyClear = aftRepyClearBo.getById(aftRepyClear);
		getObjValue(formaftclear0002, aftRepyClear);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaftclear0002 = formService.getFormData("aftclear0002");
		 getFormValue(formaftclear0002);
		 validateFormData(formaftclear0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaftclear0002 = formService.getFormData("aftclear0002");
		 getFormValue(formaftclear0002);
		 validateFormData(formaftclear0002);
  	}
	public AftRepyClear getAftRepyClear() {
		return aftRepyClear;
	}
	public void setAftRepyClear(AftRepyClear  aftRepyClear) {
		this.aftRepyClear = aftRepyClear;
	}
	public List<AftRepyClear> getAftRepyClearList() {
		return aftRepyClearList;
	}
	public void setAftRepyClearList(List<AftRepyClear> aftRepyClearList) {
		this.aftRepyClearList = aftRepyClearList;
	}
	public AftRepyClearBo getAftRepyClearBo() {
		return aftRepyClearBo;
	}
	public void setAftRepyClearBo(AftRepyClearBo aftRepyClearBo) {
		this.aftRepyClearBo = aftRepyClearBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaftclear0002() {
		return formaftclear0002;
	}
	public void setFormaftclear0002(FormData formaftclear0002) {
		this.formaftclear0002 = formaftclear0002;
	}
	public FormData getFormaftclear0001() {
		return formaftclear0001;
	}
	public void setFormaftclear0001(FormData formaftclear0001) {
		this.formaftclear0001 = formaftclear0001;
	}
	public void setClearId(String clearId){
		this.clearId = clearId;
	}		
	public String getClearId(){
		return clearId;
	}
}