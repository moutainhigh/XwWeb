package  app.creditapp.proj.action;
import java.io.PrintWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;

import app.util.User;
import app.util.toolkit.Ipage;
import app.creditapp.proj.bo.AllocateRegBo;
import app.creditapp.proj.entity.AllocateReg;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.bo.ProjAcctBo;

import com.alibaba.fastjson.JSON;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AllocateRegAction.java
 * Description:
 **/
public class AllocateRegAction extends BaseFormBean {

	//页面传值
	private AllocateReg allocateReg;
	private List<AllocateReg> allocateRegList;
	private ProjAcct projAcct;

	//注入AllocateRegBo
	private AllocateRegBo allocateRegBo;
	private ProjAcctBo projAcctBo;

	private String query;
	private String allocateRegId;	
	private String projNo;
	private String acctId;
	private String acctNo;	
	private double txAmt = 0.00;
    private double acctBal = 0.00;

	private FormData formalctreg0001;
	private FormData formalctreg0002;
	private FormService formService = new FormService();
	
	public AllocateRegAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formalctreg0001 = formService.getFormData("alctreg0001");
		allocateReg = new AllocateReg();
		getFormValue(formalctreg0001);
		setObjValue(formalctreg0001, allocateReg);
		Ipage ipage = this.getIpage();
		allocateRegList = (List) allocateRegBo.findByPage(ipage, allocateReg).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formalctreg0002 = formService.getFormData("alctreg0002");
		allocateReg = new AllocateReg();
		allocateReg.setProjNo(projNo);
		allocateReg.setAcctId(acctId);
		allocateReg.setAcctNo(acctNo);
		
		setObjValue(formalctreg0002, allocateReg);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		projAcct = new ProjAcct();
	   	projAcct.setProjNo(projNo);
	   	projAcct.setAcctType("01");
	   	projAcct = projAcctBo.getByProjNoAndAcctType(projAcct);
	   	
	   	acctBal = projAcct.getAcctBal()-txAmt;
	   	projAcct.setAcctBal(acctBal);
	   	if(acctBal > 0){
	   	//修改专户余额
	   		projAcct.setAcctBal(acctBal);
		   	projAcctBo.update(projAcct);
		//修改虚拟户余额
		   	ProjAcct projAcct1 = new ProjAcct();
		   	projAcct1.setAcctId(acctId);
		   	projAcct1 = projAcctBo.getById(projAcct1);
		   	projAcct1.setAcctBal(projAcct1.getAcctBal()+txAmt);
		   	projAcctBo.update(projAcct1);
		//插入虚拟户明细表
			allocateReg = new AllocateReg();
			allocateReg.setAcctId(acctId);
			allocateReg.setAcctNo(acctNo);
			allocateReg.setProjNo(projNo);
			allocateReg.setTxAmt(txAmt);
			allocateReg.setFAcctNo(projAcct.getAcctNo());
			allocateReg.setOpNo(User.getLoginid(this.getHttpRequest()));
			allocateRegBo.insert(allocateReg);
	   	}
		PrintWriter out = this.getHttpResponse().getWriter();
		JSON json = (JSON)JSON.toJSON(projAcct);	
		out.print(json);
		return  null ;
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formalctreg0002 = formService.getFormData("alctreg0002");
		getFormValue(formalctreg0002);
		allocateReg = new AllocateReg();
		setObjValue(formalctreg0002, allocateReg);
		allocateRegBo.update(allocateReg);
		getObjValue(formalctreg0002, allocateReg);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formalctreg0001 = formService.getFormData("alctreg0001");
		allocateReg = new AllocateReg();
		allocateReg.setAllocateRegId(allocateRegId);
		allocateRegBo.del(allocateReg);
		this.addActionMessage("删除成功");
		allocateReg = new AllocateReg();
		Ipage ipage = this.getIpage();
		allocateRegList = (List) allocateRegBo.findByPage(ipage, allocateReg).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formalctreg0002 = formService.getFormData("alctreg0002");
		allocateReg = new AllocateReg();
		allocateReg.setAllocateRegId(allocateRegId);
		allocateReg = allocateRegBo.getById(allocateReg);
		getObjValue(formalctreg0002, allocateReg);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formalctreg0002 = formService.getFormData("alctreg0002");
		 getFormValue(formalctreg0002);
		 validateFormData(formalctreg0002);
   }
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formalctreg0002 = formService.getFormData("alctreg0002");
		 getFormValue(formalctreg0002);
		 validateFormData(formalctreg0002);
  	}
	public AllocateReg getAllocateReg() {
		return allocateReg;
	}
	public void setAllocateReg(AllocateReg  allocateReg) {
		this.allocateReg = allocateReg;
	}
	public List<AllocateReg> getAllocateRegList() {
		return allocateRegList;
	}
	public void setAllocateRegList(List<AllocateReg> allocateRegList) {
		this.allocateRegList = allocateRegList;
	}
	public AllocateRegBo getAllocateRegBo() {
		return allocateRegBo;
	}
	public void setAllocateRegBo(AllocateRegBo allocateRegBo) {
		this.allocateRegBo = allocateRegBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormalctreg0002() {
		return formalctreg0002;
	}
	public void setFormalctreg0002(FormData formalctreg0002) {
		this.formalctreg0002 = formalctreg0002;
	}
	public FormData getFormalctreg0001() {
		return formalctreg0001;
	}
	public void setFormalctreg0001(FormData formalctreg0001) {
		this.formalctreg0001 = formalctreg0001;
	}
	public void setAllocateRegId(String allocateRegId){
		this.allocateRegId = allocateRegId;
	}		
	public String getAllocateRegId(){
		return allocateRegId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public double getTxAmt() {
		return txAmt;
	}
	public void setTxAmt(double txAmt) {
		this.txAmt = txAmt;
	}
	public ProjAcct getProjAcct() {
		return projAcct;
	}
	public void setProjAcct(ProjAcct projAcct) {
		this.projAcct = projAcct;
	}
	public ProjAcctBo getProjAcctBo() {
		return projAcctBo;
	}
	public void setProjAcctBo(ProjAcctBo projAcctBo) {
		this.projAcctBo = projAcctBo;
	}
	public double getAcctBal() {
		return acctBal;
	}
	public void setAcctBal(double acctBal) {
		this.acctBal = acctBal;
	}
}