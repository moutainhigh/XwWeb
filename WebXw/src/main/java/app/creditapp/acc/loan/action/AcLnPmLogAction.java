package  app.creditapp.acc.loan.action;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.acc.loan.bo.AcLnPmLogBo;
import app.creditapp.acc.loan.entity.AcLnPmLog;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: AcLnPmLogAction.java
 * Description:
 **/
public class AcLnPmLogAction extends BaseFormBean {

	//页面传值
	private AcLnPmLog acLnPmLog;
	private List<AcLnPmLog> acLnPmLogList;

	//注入AcLnPmLogBo
	private AcLnPmLogBo acLnPmLogBo;

	private String query;
	private String brNo;
	private String perdNo;		
	private String loanNo;		
	private String pactNo;		
	private String traceNo;		
	private FormData formpm0001;
	private FormData formpm0002;
	private String dataSts;
	private FormService formService = new FormService();
	
	public AcLnPmLogAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpm0001 = formService.getFormData("pm0001");
		acLnPmLog = new AcLnPmLog();		
		getFormValue(formpm0001);
		setObjValue(formpm0001, acLnPmLog);
		acLnPmLog.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		acLnPmLogList = (List) acLnPmLogBo.findByPage(ipage, acLnPmLog).getResult();
		return "list";
	}
	public String findByPageForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpm0001 = formService.getFormData("pm0001");
		acLnPmLog = new AcLnPmLog();		
		getFormValue(formpm0001);
		setObjValue(formpm0001, acLnPmLog);
		acLnPmLog.setPactNo(pactNo);
		acLnPmLog.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		acLnPmLogList = (List) acLnPmLogBo.findByPage(ipage, acLnPmLog).getResult();
		return "listForRead";
	}
	/***
	 * 分页查询默认日期
	 */
	public String findByPageDef() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpm0001 = formService.getFormData("pm0001");
		acLnPmLog = new AcLnPmLog();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String date=User.getSys_date(this.getHttpRequest());
        Date dt=sdf.parse(date);
        acLnPmLog.setTxDt(sdf.format(dt));
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		this.changeFormProperty(formpm0001, "txDt", "initValue",sd.format(dt));
		Ipage ipage = this.getIpage();
		acLnPmLogList = (List) acLnPmLogBo.findByPage(ipage, acLnPmLog).getResult();
        return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpm0002 = formService.getFormData("pm0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpm0002 = formService.getFormData("pm0002");
		getFormValue(formpm0002);
		acLnPmLog = new AcLnPmLog();
		setObjValue(formpm0002, acLnPmLog);
		acLnPmLog.setBrNo(User.getBrno(this.getHttpRequest()));
		acLnPmLogBo.insert(acLnPmLog);
		getObjValue(formpm0002, acLnPmLog);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpm0002 = formService.getFormData("pm0002");
		getFormValue(formpm0002);
		acLnPmLog = new AcLnPmLog();
		setObjValue(formpm0002, acLnPmLog);
		acLnPmLogBo.update(acLnPmLog);
		getObjValue(formpm0002, acLnPmLog);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpm0001 = formService.getFormData("pm0001");
		acLnPmLog = new AcLnPmLog();
		acLnPmLog.setPerdNo(perdNo);
		acLnPmLog.setLoanNo(loanNo);
		acLnPmLog.setTraceNo(traceNo);
		acLnPmLogBo.del(acLnPmLog);
		this.addActionMessage("删除成功");
		acLnPmLog = new AcLnPmLog();
		acLnPmLog.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		acLnPmLogList = (List) acLnPmLogBo.findByPage(ipage, acLnPmLog).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpm0002 = formService.getFormData("pm0002");
		acLnPmLog = new AcLnPmLog();
		acLnPmLog.setPerdNo(perdNo);
		acLnPmLog.setLoanNo(loanNo);
		acLnPmLog.setTraceNo(traceNo);
		acLnPmLog = acLnPmLogBo.getById(acLnPmLog);
		getObjValue(formpm0002, acLnPmLog);
		return "detail";
	}
	public String getByIdForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpm0002 = formService.getFormData("pm0002");
		acLnPmLog = new AcLnPmLog();
		acLnPmLog.setPerdNo(perdNo);
		acLnPmLog.setLoanNo(loanNo);
		acLnPmLog.setTraceNo(traceNo);
		acLnPmLog = acLnPmLogBo.getById(acLnPmLog);
		getObjValue(formpm0002, acLnPmLog);
		return "detailForRead";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formpm0002 = formService.getFormData("pm0002");
		 getFormValue(formpm0002);
		 validateFormData(formpm0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formpm0002 = formService.getFormData("pm0002");
		 getFormValue(formpm0002);
		 validateFormData(formpm0002);
  	}
	public AcLnPmLog getAcLnPmLog() {
		return acLnPmLog;
	}
	public void setAcLnPmLog(AcLnPmLog  acLnPmLog) {
		this.acLnPmLog = acLnPmLog;
	}
	public List<AcLnPmLog> getAcLnPmLogList() {
		return acLnPmLogList;
	}
	public void setAcLnPmLogList(List<AcLnPmLog> acLnPmLogList) {
		this.acLnPmLogList = acLnPmLogList;
	}
	public AcLnPmLogBo getAcLnPmLogBo() {
		return acLnPmLogBo;
	}
	public void setAcLnPmLogBo(AcLnPmLogBo acLnPmLogBo) {
		this.acLnPmLogBo = acLnPmLogBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormpm0002() {
		return formpm0002;
	}
	public void setFormpm0002(FormData formpm0002) {
		this.formpm0002 = formpm0002;
	}
	public FormData getFormpm0001() {
		return formpm0001;
	}
	public void setFormpm0001(FormData formpm0001) {
		this.formpm0001 = formpm0001;
	}
	public void setPerdNo(String perdNo){
		this.perdNo = perdNo;
	}		
	public void setLoanNo(String loanNo){
		this.loanNo = loanNo;
	}		
	public void setTraceNo(String traceNo){
		this.traceNo = traceNo;
	}		
	public String getPerdNo(){
		return perdNo;
	}
	public String getLoanNo(){
		return loanNo;
	}
	public String getTraceNo(){
		return traceNo;
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
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
}