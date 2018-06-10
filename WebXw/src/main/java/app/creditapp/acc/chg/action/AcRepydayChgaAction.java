package  app.creditapp.acc.chg.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.chg.bo.AcRepydayChgaBo;
import app.creditapp.acc.chg.entity.AcRepydayChga;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcRepydayChgaAction.java
 * Description:
 **/
public class AcRepydayChgaAction extends BaseFormBean {

	//页面传值
	private AcRepydayChga acRepydayChga;
	private List<AcRepydayChga> acRepydayChgaList;

	//注入AcRepydayChgaBo
	private AcRepydayChgaBo acRepydayChgaBo;

	private String query;
	private String brNo;		
	private String pactNo;		
	private String batchNo;		
	private FormData formchg0001;
	private FormData formchg0002;
	private FormService formService = new FormService();
	
	public AcRepydayChgaAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formchg0001 = formService.getFormData("chg0001");
		acRepydayChga = new AcRepydayChga();
		getFormValue(formchg0001);
		setObjValue(formchg0001, acRepydayChga);
		acRepydayChga.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acRepydayChgaList = (List) acRepydayChgaBo.findByPage(ipage, acRepydayChga).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formchg0002 = formService.getFormData("chg0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formchg0002 = formService.getFormData("chg0002");
		getFormValue(formchg0002);
		acRepydayChga = new AcRepydayChga();
		setObjValue(formchg0002, acRepydayChga);
		acRepydayChga.setBrNo(User.getBrno(this.getHttpRequest()));
		acRepydayChgaBo.insert(acRepydayChga);
		getObjValue(formchg0002, acRepydayChga);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formchg0002 = formService.getFormData("chg0002");
		getFormValue(formchg0002);
		acRepydayChga = new AcRepydayChga();
		setObjValue(formchg0002, acRepydayChga);
		acRepydayChgaBo.update(acRepydayChga);
		getObjValue(formchg0002, acRepydayChga);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formchg0001 = formService.getFormData("chg0001");
		acRepydayChga = new AcRepydayChga();
		acRepydayChga.setBrNo(brNo);
		acRepydayChga.setPactNo(pactNo);
		acRepydayChga.setBatchNo(batchNo);
		acRepydayChgaBo.del(acRepydayChga);
		this.addActionMessage("删除成功");
		acRepydayChga = new AcRepydayChga();
		acRepydayChga.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acRepydayChgaList = (List) acRepydayChgaBo.findByPage(ipage, acRepydayChga).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formchg0002 = formService.getFormData("chg0002");
		acRepydayChga = new AcRepydayChga();
		acRepydayChga.setBrNo(brNo);
		acRepydayChga.setPactNo(pactNo);
		acRepydayChga.setBatchNo(batchNo);
		acRepydayChga = acRepydayChgaBo.getById(acRepydayChga);
		getObjValue(formchg0002, acRepydayChga);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formchg0002 = formService.getFormData("chg0002");
		 getFormValue(formchg0002);
		 validateFormData(formchg0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formchg0002 = formService.getFormData("chg0002");
		 getFormValue(formchg0002);
		 validateFormData(formchg0002);
  	}
	public AcRepydayChga getAcRepydayChga() {
		return acRepydayChga;
	}
	public void setAcRepydayChga(AcRepydayChga  acRepydayChga) {
		this.acRepydayChga = acRepydayChga;
	}
	public List<AcRepydayChga> getAcRepydayChgaList() {
		return acRepydayChgaList;
	}
	public void setAcRepydayChgaList(List<AcRepydayChga> acRepydayChgaList) {
		this.acRepydayChgaList = acRepydayChgaList;
	}
	public AcRepydayChgaBo getAcRepydayChgaBo() {
		return acRepydayChgaBo;
	}
	public void setAcRepydayChgaBo(AcRepydayChgaBo acRepydayChgaBo) {
		this.acRepydayChgaBo = acRepydayChgaBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormchg0002() {
		return formchg0002;
	}
	public void setFormchg0002(FormData formchg0002) {
		this.formchg0002 = formchg0002;
	}
	public FormData getFormchg0001() {
		return formchg0001;
	}
	public void setFormchg0001(FormData formchg0001) {
		this.formchg0001 = formchg0001;
	}
	public void setBrNo(String brNo){
		this.brNo = brNo;
	}		
	public void setPactNo(String pactNo){
		this.pactNo = pactNo;
	}		
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}		
	public String getBrNo(){
		return brNo;
	}
	public String getPactNo(){
		return pactNo;
	}
	public String getBatchNo(){
		return batchNo;
	}
}