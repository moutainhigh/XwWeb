package  app.creditapp.acc.loan.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.loan.bo.AcLnMstBo;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnMstFail;
import app.util.User;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcLnMstAction.java
 * Description:
 **/
public class AcLnMstAction extends BaseFormBean {

	//页面传值
	private AcLnMst acLnMst;
	private AcLnMstFail acLnMstFail;
	private List<AcLnMst> acLnMstList;
	private List<AcLnMstFail> acLnMstFailList;

	//注入AcLnMstBo
	private AcLnMstBo acLnMstBo;

	private String query;
	private String loanNo;	
	private String loanSts;
	
	private FormData formmst001;
	private FormData formloan0002;
	private FormData formloandk0002;
	private FormData formloanfail0001;
	public FormData getFormloanfail0001() {
		return formloanfail0001;
	}
	public FormData getFormloandk0002() {
		return formloandk0002;
	}
	public void setFormloandk0002(FormData formloandk0002) {
		this.formloandk0002 = formloandk0002;
	}
	public void setFormloanfail0001(FormData formloanfail0001) {
		this.formloanfail0001 = formloanfail0001;
	}
	private FormService formService = new FormService();
	
	public AcLnMstAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formmst001 = formService.getFormData("mst001");
		acLnMst = new AcLnMst();
		getFormValue(formmst001);
		setObjValue(formmst001, acLnMst);
		acLnMst.setBrNo(User.getBrno(this.getHttpRequest()));
		
		Ipage ipage = this.getIpage();
		acLnMstList = (List) acLnMstBo.findByPage(ipage, acLnMst).getResult();
		return "list";
	}
	public String findByPagedk() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formmst001 = formService.getFormData("mst001");
		acLnMst = new AcLnMst();
		acLnMst.setBrNo(User.getBrno(this.getHttpRequest()));
		getFormValue(formmst001);
		setObjValue(formmst001, acLnMst);
		acLnMst.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		acLnMstList = (List) acLnMstBo.findByPagedk(ipage, acLnMst).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan0002 = formService.getFormData("loan0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan0002 = formService.getFormData("loan0002");
		getFormValue(formloan0002);
		acLnMst = new AcLnMst();
		setObjValue(formloan0002, acLnMst);
		acLnMst.setBrNo(User.getBrno(this.getHttpRequest()));
		acLnMstBo.insert(acLnMst);
		getObjValue(formloan0002, acLnMst);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formloan0002 = formService.getFormData("loan0002");
		getFormValue(formloan0002);
		acLnMst = new AcLnMst();
		setObjValue(formloan0002, acLnMst);
		acLnMstBo.update(acLnMst);
		getObjValue(formloan0002, acLnMst);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formmst001 = formService.getFormData("mst001");
		acLnMst = new AcLnMst();
		acLnMst.setLoanNo(loanNo);
		acLnMstBo.del(acLnMst);
		this.addActionMessage("删除成功");
		acLnMst = new AcLnMst();
		acLnMst.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLnMstList = (List) acLnMstBo.findByPage(ipage, acLnMst).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formloan0002 = formService.getFormData("loan0002");
		acLnMst = new AcLnMst();
		acLnMst.setLoanNo(loanNo);
		acLnMst = acLnMstBo.getById(acLnMst);
		getObjValue(formloan0002, acLnMst);
		return "detail";
	}
	
	public String getByIddk() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formloandk0002 = formService.getFormData("loandk0002");
		acLnMst = new AcLnMst();
		acLnMst.setLoanNo(loanNo);
		acLnMst = acLnMstBo.getById(acLnMst);
		getObjValue(formloandk0002, acLnMst);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formloan0002 = formService.getFormData("loan0002");
		 getFormValue(formloan0002);
		 validateFormData(formloan0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formloan0002 = formService.getFormData("loan0002");
		 getFormValue(formloan0002);
		 validateFormData(formloan0002);
  	}
	
	/**
	 * 放款失败分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPageToLoanFail() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formloanfail0001 = formService.getFormData("loanfail0001");
		acLnMstFail = new AcLnMstFail();
		getFormValue(formloanfail0001);
		setObjValue(formloanfail0001, acLnMstFail);
		acLnMstFail.setDealSts("04");
		acLnMstFail.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		acLnMstFailList = (List) acLnMstBo.findByPageToLoanFail(ipage, acLnMstFail).getResult();
		return "list";
	}
	
	public AcLnMst getAcLnMst() {
		return acLnMst;
	}
	public void setAcLnMst(AcLnMst  acLnMst) {
		this.acLnMst = acLnMst;
	}
	public List<AcLnMst> getAcLnMstList() {
		return acLnMstList;
	}
	public void setAcLnMstList(List<AcLnMst> acLnMstList) {
		this.acLnMstList = acLnMstList;
	}
	public AcLnMstBo getAcLnMstBo() {
		return acLnMstBo;
	}
	public void setAcLnMstBo(AcLnMstBo acLnMstBo) {
		this.acLnMstBo = acLnMstBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormloan0002() {
		return formloan0002;
	}
	public void setFormloan0002(FormData formloan0002) {
		this.formloan0002 = formloan0002;
	}
	public FormData getFormmst001() {
		return formmst001;
	}
	public void setFormmst001(FormData formmst001) {
		this.formmst001 = formmst001;
	}
	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}		
	public String getLoanNo(){
		return loanNo;
	}
	public AcLnMstFail getAcLnMstFail() {
		return acLnMstFail;
	}
	public void setAcLnMstFail(AcLnMstFail acLnMstFail) {
		this.acLnMstFail = acLnMstFail;
	}
	public List<AcLnMstFail> getAcLnMstFailList() {
		return acLnMstFailList;
	}
	public void setAcLnMstFailList(List<AcLnMstFail> acLnMstFailList) {
		this.acLnMstFailList = acLnMstFailList;
	}
	public String getLoanSts() {
		return loanSts;
	}
	public void setLoanSts(String loanSts) {
		this.loanSts = loanSts;
	}
}