package  app.creditapp.aft.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcDebitBo;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.acc.log.bo.AcTraceLogBo;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.aft.bo.AftReverseBo;
import app.creditapp.aft.entity.AftReverse;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.wkf.entity.WkfParm;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftReverseAction.java
 * Description:
 **/
public class AftReverseAction extends BaseFormBean {

	//页面传值
	private AftReverse aftReverse;
	private List<AftReverse> aftReverseList;
	private AcTraceLog acTraceLog;
	private LnDue lnDue;
	private LnDueBo lnDueBo;
	//注入AftReverseBo
	private AftReverseBo aftReverseBo;
	//注入AcTraceLogBo
	private AcTraceLogBo acTraceLogBo;
	private AcDebitBo acDebitBo;
	private String query;
	private String reveId;	
	private String traceNo;
	private Integer traceCnt;
	private FormData formaft0127;
	private FormData formaft0128;
	private FormService formService = new FormService();
	
	public AftReverseAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0127 = formService.getFormData("aft0127");
		aftReverse = new AftReverse();
		getFormValue(formaft0127);
		setObjValue(formaft0127, aftReverse);
//		aftReverse.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftReverseList = (List) aftReverseBo.findByPage(ipage, aftReverse).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0128 = formService.getFormData("aft0128");
		aftReverse = new AftReverse();
		aftReverse.setOpName(User.getDisplayName(getHttpRequest()));
		aftReverse.setTxDate(User.getSys_date(getHttpRequest()));
		aftReverse.setUpDate(User.getSys_date(getHttpRequest()));
		aftReverse.setReveDate(User.getSys_date(getHttpRequest()));
		getObjValue(formaft0128, aftReverse);
		return "input";
	}
	/**
	 * 提交申请
	 * @return
	 * @throws Exception
	 */
	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0127 = formService.getFormData("aft0127");
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
		aftReverse = new AftReverse();
		aftReverse.setReveId(reveId);
		aftReverse.setReveSts("02");//更新状态为审批中
		aftReverseBo.updateReveSts(aftReverse);
		aftReverse = new AftReverse();
		Ipage ipage = this.getIpage();
		aftReverseList = (List) aftReverseBo.findByPage(ipage, aftReverse).getResult();
		this.addActionMessage("操作成功,下一节点操作员"+nextDesc);
		return "list";
	}	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0128 = formService.getFormData("aft0128");
		getFormValue(formaft0128);
		aftReverse = new AftReverse();
		setObjValue(formaft0128, aftReverse);
		aftReverse.setOpNo(User.getTlrno(this.getHttpRequest()));
		aftReverse.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftReverse.setReveSts("01");
		aftReverseBo.insert(aftReverse);
		this.addActionMessage("操作成功");
		getObjValue(formaft0128, aftReverse);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0128 = formService.getFormData("aft0128");
		getFormValue(formaft0128);
		aftReverse = new AftReverse();
		setObjValue(formaft0128, aftReverse);
		aftReverse.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftReverseBo.update(aftReverse);
		getObjValue(formaft0128, aftReverse);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaft0127 = formService.getFormData("aft0127");
		aftReverse = new AftReverse();
		aftReverse.setReveId(reveId);
		aftReverseBo.del(aftReverse);
		this.addActionMessage("删除成功");
		aftReverse = new AftReverse();
		aftReverse.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftReverseList = (List) aftReverseBo.findByPage(ipage, aftReverse).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaft0128 = formService.getFormData("aft0128");
		aftReverse = new AftReverse();
		aftReverse.setReveId(reveId);
		aftReverse = aftReverseBo.getById(aftReverse);
		getObjValue(formaft0128, aftReverse);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert() {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceNo(traceNo);
		acTraceLog.setTraceCnt(1);
		acTraceLog = acTraceLogBo.getById(acTraceLog);

		String loanNo = acTraceLog.getLoanNo();// 借据号
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
		formaft0128 = formService.getFormData("aft0128");
		getFormValue(formaft0128);
		validateFormData(formaft0128);
	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaft0128 = formService.getFormData("aft0128");
		 getFormValue(formaft0128);
		 validateFormData(formaft0128);
  	}
	public AftReverse getAftReverse() {
		return aftReverse;
	}
	public void setAftReverse(AftReverse  aftReverse) {
		this.aftReverse = aftReverse;
	}
	public List<AftReverse> getAftReverseList() {
		return aftReverseList;
	}
	public void setAftReverseList(List<AftReverse> aftReverseList) {
		this.aftReverseList = aftReverseList;
	}
	public AftReverseBo getAftReverseBo() {
		return aftReverseBo;
	}
	public void setAftReverseBo(AftReverseBo aftReverseBo) {
		this.aftReverseBo = aftReverseBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaft0128() {
		return formaft0128;
	}
	public void setFormaft0128(FormData formaft0128) {
		this.formaft0128 = formaft0128;
	}
	public FormData getFormaft0127() {
		return formaft0127;
	}
	public void setFormaft0127(FormData formaft0127) {
		this.formaft0127 = formaft0127;
	}
	public void setReveId(String reveId){
		this.reveId = reveId;
	}		
	public String getReveId(){
		return reveId;
	}
	public AcTraceLog getAcTraceLog() {
		return acTraceLog;
	}
	public void setAcTraceLog(AcTraceLog acTraceLog) {
		this.acTraceLog = acTraceLog;
	}
	public AcTraceLogBo getAcTraceLogBo() {
		return acTraceLogBo;
	}
	public void setAcTraceLogBo(AcTraceLogBo acTraceLogBo) {
		this.acTraceLogBo = acTraceLogBo;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public void setTraceCnt(Integer traceCnt) {
		this.traceCnt = traceCnt;
	}
	public Integer getTraceCnt() {
		return traceCnt;
	}
	public AcDebitBo getAcDebitBo() {
		return acDebitBo;
	}
	public void setAcDebitBo(AcDebitBo acDebitBo) {
		this.acDebitBo = acDebitBo;
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
	public void setLnDueBo(LnDueBo lnDueBo) {
		this.lnDueBo = lnDueBo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
}