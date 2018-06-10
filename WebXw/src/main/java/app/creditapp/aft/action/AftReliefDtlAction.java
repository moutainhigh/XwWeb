package  app.creditapp.aft.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftReliefDtlBo;
import app.creditapp.aft.entity.AftReliefDtl;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftReliefDtlAction.java
 * Description:
 **/
public class AftReliefDtlAction extends BaseFormBean {

	//页面传值
	private AftReliefDtl aftReliefDtl;
	private List<AftReliefDtl> aftReliefDtlList;

	//注入AftReliefDtlBo
	private AftReliefDtlBo aftReliefDtlBo;

	private String query;
	private String reliefType;		
	private String perdNo;		
	private String traceNo;		
	private FormData formwv0001;
	private FormData formwv0002;
	private FormService formService = new FormService();
	
	public AftReliefDtlAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formwv0001 = formService.getFormData("wv0001");
		aftReliefDtl = new AftReliefDtl();
		getFormValue(formwv0001);
		setObjValue(formwv0001, aftReliefDtl);
		aftReliefDtl.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftReliefDtlList = (List) aftReliefDtlBo.findByPage(ipage, aftReliefDtl).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formwv0002 = formService.getFormData("wv0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formwv0002 = formService.getFormData("wv0002");
		getFormValue(formwv0002);
		aftReliefDtl = new AftReliefDtl();
		setObjValue(formwv0002, aftReliefDtl);
		aftReliefDtl.setBrNo(User.getBrno(this.getHttpRequest()));
		aftReliefDtlBo.insert(aftReliefDtl);
		getObjValue(formwv0002, aftReliefDtl);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formwv0002 = formService.getFormData("wv0002");
		getFormValue(formwv0002);
		aftReliefDtl = new AftReliefDtl();
		setObjValue(formwv0002, aftReliefDtl);
		aftReliefDtlBo.update(aftReliefDtl);
		getObjValue(formwv0002, aftReliefDtl);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formwv0001 = formService.getFormData("wv0001");
		aftReliefDtl = new AftReliefDtl();
		aftReliefDtl.setReliefType(reliefType);
		aftReliefDtl.setPerdNo(perdNo);
		aftReliefDtl.setTraceNo(traceNo);
		aftReliefDtlBo.del(aftReliefDtl);
		this.addActionMessage("删除成功");
		aftReliefDtl = new AftReliefDtl();
		aftReliefDtl.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftReliefDtlList = (List) aftReliefDtlBo.findByPage(ipage, aftReliefDtl).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formwv0002 = formService.getFormData("wv0002");
		aftReliefDtl = new AftReliefDtl();
		aftReliefDtl.setReliefType(reliefType);
		aftReliefDtl.setPerdNo(perdNo);
		aftReliefDtl.setTraceNo(traceNo);
		aftReliefDtl = aftReliefDtlBo.getById(aftReliefDtl);
		getObjValue(formwv0002, aftReliefDtl);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formwv0002 = formService.getFormData("wv0002");
		 getFormValue(formwv0002);
		 validateFormData(formwv0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formwv0002 = formService.getFormData("wv0002");
		 getFormValue(formwv0002);
		 validateFormData(formwv0002);
  	}
	public AftReliefDtl getAftReliefDtl() {
		return aftReliefDtl;
	}
	public void setAftReliefDtl(AftReliefDtl  aftReliefDtl) {
		this.aftReliefDtl = aftReliefDtl;
	}
	public List<AftReliefDtl> getAftReliefDtlList() {
		return aftReliefDtlList;
	}
	public void setAftReliefDtlList(List<AftReliefDtl> aftReliefDtlList) {
		this.aftReliefDtlList = aftReliefDtlList;
	}
	public AftReliefDtlBo getAftReliefDtlBo() {
		return aftReliefDtlBo;
	}
	public void setAftReliefDtlBo(AftReliefDtlBo aftReliefDtlBo) {
		this.aftReliefDtlBo = aftReliefDtlBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormwv0002() {
		return formwv0002;
	}
	public void setFormwv0002(FormData formwv0002) {
		this.formwv0002 = formwv0002;
	}
	public FormData getFormwv0001() {
		return formwv0001;
	}
	public void setFormwv0001(FormData formwv0001) {
		this.formwv0001 = formwv0001;
	}
	public void setReliefType(String reliefType){
		this.reliefType = reliefType;
	}		
	public void setPerdNo(String perdNo){
		this.perdNo = perdNo;
	}		
	public void setTraceNo(String traceNo){
		this.traceNo = traceNo;
	}		
	public String getReliefType(){
		return reliefType;
	}
	public String getPerdNo(){
		return perdNo;
	}
	public String getTraceNo(){
		return traceNo;
	}
}