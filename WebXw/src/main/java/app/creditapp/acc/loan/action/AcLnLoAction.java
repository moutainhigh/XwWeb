package  app.creditapp.acc.loan.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcLnLoBo;
import app.creditapp.acc.loan.entity.AcLnLo;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcLnLoAction.java
 * Description:
 **/
public class AcLnLoAction extends BaseFormBean {

	//页面传值
	private AcLnLo acLnLo;
	private List<AcLnLo> acLnLoList;

	//注入AcLnLoBo
	private AcLnLoBo acLnLoBo;

	private String query;
	private Integer perdNo;		
	private String loanNo;	
	private String pactNo;		
	private String dataSts;
	private FormData formlo001;
	private FormData formlo002;
	private FormService formService = new FormService();
	
	public AcLnLoAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlo001 = formService.getFormData("lo001");
		acLnLo = new AcLnLo();
		getFormValue(formlo001);
		setObjValue(formlo001, acLnLo);
		acLnLo.setPactNo(pactNo);
		Ipage ipage = this.getIpage();
		acLnLoList = (List) acLnLoBo.findByPage(ipage, acLnLo).getResult();
		return "list";
	}
	public String findByPageForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlo001 = formService.getFormData("lo001");
		acLnLo = new AcLnLo();
		getFormValue(formlo001);
		setObjValue(formlo001, acLnLo);
		acLnLo.setPactNo(pactNo);
		Ipage ipage = this.getIpage();
		acLnLoList = (List) acLnLoBo.findByPage(ipage, acLnLo).getResult();
		return "listForRead";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlo002 = formService.getFormData("lo002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlo002 = formService.getFormData("lo002");
		getFormValue(formlo002);
		acLnLo = new AcLnLo();
		setObjValue(formlo002, acLnLo);
		acLnLo.setBrNo(User.getBrno(this.getHttpRequest()));
		acLnLoBo.insert(acLnLo);
		getObjValue(formlo002, acLnLo);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlo002 = formService.getFormData("lo002");
		getFormValue(formlo002);
		acLnLo = new AcLnLo();
		setObjValue(formlo002, acLnLo);
		acLnLoBo.update(acLnLo);
		getObjValue(formlo002, acLnLo);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlo001 = formService.getFormData("lo001");
		acLnLo = new AcLnLo();
		acLnLo.setPerdNo(perdNo);
		acLnLo.setLoanNo(loanNo);
		acLnLoBo.del(acLnLo);
		this.addActionMessage("删除成功");
		acLnLo = new AcLnLo();
		acLnLo.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLnLoList = (List) acLnLoBo.findByPage(ipage, acLnLo).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlo002 = formService.getFormData("lo002");
		acLnLo = new AcLnLo();
		acLnLo.setPerdNo(perdNo);
		acLnLo.setLoanNo(loanNo);
		acLnLo = acLnLoBo.getById(acLnLo);
		getObjValue(formlo002, acLnLo);
		return "detail";
	}
	public String getByIdForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlo002 = formService.getFormData("lo002");
		acLnLo = new AcLnLo();
		acLnLo.setPerdNo(perdNo);
		acLnLo.setLoanNo(loanNo);
		acLnLo = acLnLoBo.getById(acLnLo);
		getObjValue(formlo002, acLnLo);
		return "detailForRead";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlo002 = formService.getFormData("lo002");
		 getFormValue(formlo002);
		 validateFormData(formlo002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlo002 = formService.getFormData("lo002");
		 getFormValue(formlo002);
		 validateFormData(formlo002);
  	}
	public AcLnLo getAcLnLo() {
		return acLnLo;
	}
	public void setAcLnLo(AcLnLo  acLnLo) {
		this.acLnLo = acLnLo;
	}
	public List<AcLnLo> getAcLnLoList() {
		return acLnLoList;
	}
	public void setAcLnLoList(List<AcLnLo> acLnLoList) {
		this.acLnLoList = acLnLoList;
	}
	public AcLnLoBo getAcLnLoBo() {
		return acLnLoBo;
	}
	public void setAcLnLoBo(AcLnLoBo acLnLoBo) {
		this.acLnLoBo = acLnLoBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlo002() {
		return formlo002;
	}
	public void setFormlo002(FormData formlo002) {
		this.formlo002 = formlo002;
	}
	public FormData getFormlo001() {
		return formlo001;
	}
	public void setFormlo001(FormData formlo001) {
		this.formlo001 = formlo001;
	}
	public void setPerdNo(Integer perdNo){
		this.perdNo = perdNo;
	}		
	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}		
	public Integer getPerdNo(){
		return perdNo;
	}
	public String getLoanNo(){
		return loanNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public String getDataSts() {
		return dataSts;
	}
	public void setDataSts(String dataSts) {
		this.dataSts = dataSts;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
}