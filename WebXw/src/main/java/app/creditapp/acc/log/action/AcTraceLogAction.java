package  app.creditapp.acc.log.action;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.acc.log.bo.AcTraceLogBo;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.wkf.entity.WkfParm;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: AcTraceLogAction.java
 * Description:
 **/
public class AcTraceLogAction extends BaseFormBean {

	//页面传值
	private AcTraceLog acTraceLog;
	private LnDue lnDue;
	private List<AcTraceLog> acTraceLogList;

	//注入AcTraceLogBo
	private AcTraceLogBo acTraceLogBo;
//	private AcDebitBo acDebitBo;
	private LnDueBo lnDueBo;
	private String query;
	private String pactNo;
	private String brNo;
	private Integer traceCnt;		
	private String traceNo;	
	private String backSts;
	private List tabList;
	private FormData formlog0001;
	private FormData formlog0011;
	private FormData formlog0002;
	private FormService formService = new FormService();
	
	public AcTraceLogAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlog0011 = formService.getFormData("log0011");
		acTraceLog = new AcTraceLog();
		getFormValue(formlog0011);
		setObjValue(formlog0011, acTraceLog);
		acTraceLog.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		acTraceLogList = (List) acTraceLogBo.findByPage(ipage, acTraceLog).getResult();
		return "list";
	}
	public String findByPageForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlog0011 = formService.getFormData("log0011");
		acTraceLog = new AcTraceLog();
		getFormValue(formlog0011);
		setObjValue(formlog0011, acTraceLog);
		Ipage ipage = this.getIpage();
		acTraceLogList = (List) acTraceLogBo.findByPage(ipage, acTraceLog).getResult();
		return "lists";
	}
	/**
	 * 提交申请
	 * @return
	 * @throws Exception
	 */
	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlog0011 = formService.getFormData("log0011");
		//组合流程变量，然后启动流程
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceNo(traceNo);
		acTraceLog.setTraceCnt(1);
		acTraceLog = acTraceLogBo.getById(acTraceLog);
		lnDue = new LnDue();
		lnDue.setDueNo(acTraceLog.getLoanNo());
		lnDue = lnDueBo.getById(lnDue);
		acTraceLog.setCifName(lnDue.getCifName());
		WkfParm parm = new WkfParm();
		parm.setApp_no(traceNo);//ID号
		parm.setApp_type("02");//冲账
		
		StringBuilder wfAppValue = new StringBuilder();
		wfAppValue.append("合作机构名称:"+acTraceLog.getBrName());
		wfAppValue.append(",合同号:"+acTraceLog.getPactNo());
		wfAppValue.append(",客户号:"+acTraceLog.getCifName());
		parm.setWf_app_value(wfAppValue.toString());//业务串
		String nextDesc =acTraceLogBo.doSubmit(parm,acTraceLog,User.getLoginid(getHttpRequest()), User.getBrno(getHttpRequest()));
		acTraceLog = new AcTraceLog();
		Ipage ipage = this.getIpage();
		acTraceLogList = (List) acTraceLogBo.findByPage(ipage, acTraceLog).getResult();
		this.addActionMessage("操作成功,下一节点操作员"+nextDesc);
		return "list";
	}	
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-19
	 * @描述	冲账校验
	
	public void validateDoSubmit() throws Exception{
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceNo(traceNo);
		acTraceLog.setTraceCnt(traceCnt);
		acTraceLog = acTraceLogBo.getById(acTraceLog);
		
		String loanNo = acTraceLog.getLoanNo();//借据号
		
		//非 贷款还款、提前还款、还款日变更、还款帐号变更、息费减免 不能进行冲账
		if(!"LNC4".equals(acTraceLog.getTxCde()) && !"LNAD".equals(acTraceLog.getTxCde()) 
				&& !"CGPD".equals(acTraceLog.getTxCde()) && !"LNUP".equals(acTraceLog.getTxCde())
					&& !"LNWV".equals(acTraceLog.getTxCde())){
			this.addActionError("该业务无法进行冲账交易！");
		}else{
			//查询大于本次交易流水号 的业务  若存在 则表示非最近一笔业务，无法进行冲账交易
			AcTraceLog lasAcTraceLog = new AcTraceLog();
			lasAcTraceLog.setLoanNo(loanNo);
			lasAcTraceLog = acTraceLogBo.getRecentTraceLog(lasAcTraceLog);
			if(!acTraceLog.getTraceNo().equals(lasAcTraceLog.getTraceNo())){
				this.addActionError("该业务不是最近一笔业务，无法进行冲账交易！");
			}else if("LNC4".equals(acTraceLog.getTxCde())){//还款冲账
				AcDebit acDebit = new AcDebit();
				acDebit.setTraceNo(traceNo);
				acDebit = acDebitBo.getById(acDebit);
				if(!"03".equals(acDebit.getSts())){
					this.addActionError("该还款未成功，不能发起冲账！");
				}
			}
		}
		
		formlog0011 = formService.getFormData("log0011");
		acTraceLog = new AcTraceLog();
		getFormValue(formlog0011);
		setObjValue(formlog0011, acTraceLog);
		Ipage ipage = this.getIpage();
		acTraceLogList = (List) acTraceLogBo.findByPage(ipage, acTraceLog).getResult();
		
	}
	 */
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlog0002 = formService.getFormData("log0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlog0002 = formService.getFormData("log0002");
		getFormValue(formlog0002);
		acTraceLog = new AcTraceLog();
		setObjValue(formlog0002, acTraceLog);
		acTraceLogBo.insert(acTraceLog);
		getObjValue(formlog0002, acTraceLog);
		return "detail";
	}

	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlog0002 = formService.getFormData("log0002");
		getFormValue(formlog0002);
		acTraceLog = new AcTraceLog();
		setObjValue(formlog0002, acTraceLog);
		acTraceLogBo.update(acTraceLog);
		getObjValue(formlog0002, acTraceLog);
		return "detail";
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlog0001 = formService.getFormData("log0001");
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceCnt(traceCnt);
		acTraceLog.setTraceNo(traceNo);
		acTraceLogBo.del(acTraceLog);
		this.addActionMessage("删除成功");
		acTraceLog = new AcTraceLog();
		Ipage ipage = this.getIpage();
		acTraceLogList = (List) acTraceLogBo.findByPage(ipage, acTraceLog).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlog0002 = formService.getFormData("log0002");
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceCnt(traceCnt);
		acTraceLog.setTraceNo(traceNo);
		acTraceLog = acTraceLogBo.getById(acTraceLog);
		getObjValue(formlog0002, acTraceLog);
		return "detail";
	}
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceCnt(traceCnt);
		acTraceLog.setTraceNo(traceNo);
		acTraceLog = acTraceLogBo.getById(acTraceLog);
		
		tabList = new ArrayList();
		String[] tab = null; 
		if(acTraceLog.getTxCde().equals("LNC4")){
			tab = new String[2];
			tab[0] = "详情";
			tab[1] = "AcLnPmLogAction_findByPage.action?traceNo=" + traceNo+  "&traceCnt="+traceCnt;
			tabList.add(tab);
		}else if(acTraceLog.getTxCde().equals("LNAD")){
			tab = new String[2];
			tab[0] = "详情";
			tab[1] = "AftAdvpayAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query+ "&traceCnt="+traceCnt;
			tabList.add(tab);
		}else if(acTraceLog.getTxCde().equals("CGPD")){
			tab = new String[2];
			tab[0] = "详情";
			tab[1] = "AftPaydayAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query+ "&traceCnt="+traceCnt;
			tabList.add(tab);
		}else if(acTraceLog.getTxCde().equals("LNUP")){
			tab = new String[2];
			tab[0] = "详情";
			tab[1] = "AftAcnoAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query+ "&traceCnt="+traceCnt;
			tabList.add(tab);
		}else if(acTraceLog.getTxCde().equals("LNWV")){
			tab = new String[2];
			tab[0] = "详情";
			tab[1] = "AftReliefAction_getByIdForTrace.action?traceNo=" + traceNo+ "&query="+ query+ "&traceCnt="+traceCnt;
			tabList.add(tab);
		}
		
		return "tab";
	}	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlog0002 = formService.getFormData("log0002");
		 getFormValue(formlog0002);
		 validateFormData(formlog0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlog0002 = formService.getFormData("log0002");
		 getFormValue(formlog0002);
		 validateFormData(formlog0002);
  	}
	public AcTraceLog getAcTraceLog() {
		return acTraceLog;
	}
	public void setAcTraceLog(AcTraceLog  acTraceLog) {
		this.acTraceLog = acTraceLog;
	}
	public List<AcTraceLog> getAcTraceLogList() {
		return acTraceLogList;
	}
	public void setAcTraceLogList(List<AcTraceLog> acTraceLogList) {
		this.acTraceLogList = acTraceLogList;
	}
	public AcTraceLogBo getAcTraceLogBo() {
		return acTraceLogBo;
	}
	public void setAcTraceLogBo(AcTraceLogBo acTraceLogBo) {
		this.acTraceLogBo = acTraceLogBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlog0002() {
		return formlog0002;
	}
	public void setFormlog0002(FormData formlog0002) {
		this.formlog0002 = formlog0002;
	}
	public FormData getFormlog0001() {
		return formlog0001;
	}
	public void setFormlog0001(FormData formlog0001) {
		this.formlog0001 = formlog0001;
	}
	public void setTraceCnt(Integer traceCnt){
		this.traceCnt = traceCnt;
	}		
	public void setTraceNo(String traceNo){
		this.traceNo = traceNo;
	}		
	public Integer getTraceCnt(){
		return traceCnt;
	}
	public String getTraceNo(){
		return traceNo;
	}
	public String getPactNo() {
		return pactNo;
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
	public LnDue getLnDue() {
		return lnDue;
	}
	public void setLnDue(LnDue lnDue) {
		this.lnDue = lnDue;
	}
	public LnDueBo getLnDueBo() {
		return lnDueBo;
	}
	public FormData getFormlog0011() {
		return formlog0011;
	}
	public void setFormlog0011(FormData formlog0011) {
		this.formlog0011 = formlog0011;
	}
	public void setLnDueBo(LnDueBo lnDueBo) {
		this.lnDueBo = lnDueBo;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
//	public AcDebitBo getAcDebitBo() {
//		return acDebitBo;
//	}
//	public void setAcDebitBo(AcDebitBo acDebitBo) {
//		this.acDebitBo = acDebitBo;
//	}
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
}