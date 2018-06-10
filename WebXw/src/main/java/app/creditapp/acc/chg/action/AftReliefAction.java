package  app.creditapp.acc.chg.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.chg.bo.AftReliefBo;
import app.creditapp.acc.chg.entity.AftRelief;
import app.creditapp.acc.loan.bo.AcLnLoBo;
import app.creditapp.acc.loan.bo.AcLnMstBo;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.log.bo.AcChrgLogBo;
import app.creditapp.acc.log.entity.AcChrgLog;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.wkf.entity.WkfParm;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AftReliefAction.java
 * Description:
 **/
public class AftReliefAction extends BaseFormBean {

	//页面传值
	private AftRelief aftRelief;
	private List<AftRelief> aftReliefList;

	//注入AftReliefBo
	private AftReliefBo aftReliefBo;
	private AcLnMstBo acLnMstBo;
	private AcChrgLogBo acChrgLogBo;
	private LnDueBo lnDueBo;
	private AcLnLoBo acLnLoBo;
	private String query;
	private String refId;
	private String traceNo;
	private String pactNo;	
	private String apprType;
	private FormData formaftrel001;
	private FormData formaftrel002;
	private FormService formService = new FormService();
	
	public AftReliefAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftrel001 = formService.getFormData("aftrel001");
		aftRelief = new AftRelief();
		getFormValue(formaftrel001);
		setObjValue(formaftrel001, aftRelief);
		aftRelief.setPactNo(pactNo);
		Ipage ipage = this.getIpage();
		aftReliefList = (List) aftReliefBo.findByPage(ipage, aftRelief).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		aftRelief = new AftRelief();
		aftRelief.setOpNo(User.getTlrno(this.getHttpRequest()));
		aftRelief.setOpName(User.getDisplayName(this.getHttpRequest()));
		aftRelief.setTxDate(User.getSys_date(this.getHttpRequest()));
		aftRelief.setRefSts("01");
		getObjValue(formaftrel002, aftRelief);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		getFormValue(formaftrel002);
		aftRelief = new AftRelief();
		setObjValue(formaftrel002, aftRelief);
		aftRelief.setOpNo(User.getTlrno(this.getHttpRequest()));
		aftRelief.setTxDate(User.getSys_date(this.getHttpRequest()));
		String refId = aftReliefBo.getRefId();
		aftRelief.setRefId(refId);
//		aftRelief.setBrNo(User.getBrno(this.getHttpRequest()));
		aftReliefBo.insert(aftRelief);
		this.query = "query1";
		aftRelief = aftReliefBo.getById(aftRelief);
		getObjValue(formaftrel002, aftRelief);
		this.addActionMessage("操作成功");
		return "detail";
	}
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-12
	 * @描述	//根据合同号、机构号 查询欠款信息
	 */
	public String getLoMes() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=utf-8");
		HttpServletRequest requset=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String pactNo=requset.getParameter("pactNo");
		String brNo=requset.getParameter("brNo");
		String result = "";
		
		LnDue lnDue = new LnDue();
		lnDue.setPactNo(pactNo);
		lnDue.setBrNo(brNo);
		lnDue = lnDueBo.getByPactNoAndBrNo(lnDue);
		
		
		AcChrgLog acChrgLog = new AcChrgLog();
		acChrgLog.setLoanNo(lnDue.getDueNo());
		//欠费
		double loFee = acChrgLogBo.getLoFeeAmt(acChrgLog);
		AcLnLo acLnLo = new AcLnLo();
		
		acLnLo.setLoanNo(lnDue.getDueNo());
		acLnLo = acLnLoBo.getLoAmt(acLnLo);
		//欠本
		double loPrcp = acLnLo.getPrcpAmt();
		//欠息
		double loNorm = acLnLo.getNormInt();
		//欠罚
		double loFine = acLnLo.getFineInt();
		
		result = "欠本="+loPrcp+",欠息="+loNorm+",欠罚="+loFine+",欠费="+loFee;
		response.getWriter().write(result);
		response.getWriter().close();
		return null;		
	}	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		getFormValue(formaftrel002);
		aftRelief = new AftRelief();
		setObjValue(formaftrel002, aftRelief);
		aftRelief.setUpDate(User.getSys_date(this.getHttpRequest()));
		aftRelief.setOpNo(User.getTlrno(this.getHttpRequest()));
		aftReliefBo.update(aftRelief);
		getObjValue(formaftrel002, aftRelief);
		this.addActionMessage("修改成功");
		return "details";
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-7-21
	 * @描述	息费减免确认
	 */
	public String execAftRelief()throws Exception{
		
		aftRelief = new AftRelief();
		aftRelief.setRefId(refId);
		aftRelief = aftReliefBo.getById(aftRelief);
		aftReliefBo.execAftRelief(aftRelief);
		
		
		return "";
	}
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrel001 = formService.getFormData("aftrel001");
		aftRelief = new AftRelief();
		aftRelief.setRefId(refId);
		aftReliefBo.del(aftRelief);
		this.addActionMessage("删除成功");
		aftRelief = new AftRelief();
		aftRelief.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		aftReliefList = (List) aftReliefBo.findByPage(ipage, aftRelief).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		aftRelief = new AftRelief();
		aftRelief.setRefId(refId);
		aftRelief = aftReliefBo.getById(aftRelief);
		getObjValue(formaftrel002, aftRelief);
		return "detail";
	}
	public String getByIdForRead() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		aftRelief = new AftRelief();
		aftRelief.setRefId(refId);
		aftRelief = aftReliefBo.getById(aftRelief);
		getObjValue(formaftrel002, aftRelief);
		return "detailForRead";
	}
	public String getByIdForTrace() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		aftRelief = new AftRelief();
		aftRelief.setTraceNo(traceNo);
		aftRelief = aftReliefBo.getByIdForTrace(aftRelief);
		getObjValue(formaftrel002, aftRelief);
		return "detaill";
	}
	public String getByIdfor() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		aftRelief = new AftRelief();
		aftRelief.setRefId(refId);
		aftRelief = aftReliefBo.getById(aftRelief);
		getObjValue(formaftrel002, aftRelief);
		return "details";
	}
	/**
	 * 提交申请
	 * @return
	 * @throws Exception
	 */
	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrel001 = formService.getFormData("aftrel001");
		//组合流程变量，然后启动流程
		aftRelief = new AftRelief();
		aftRelief.setRefId(refId);
		aftRelief = aftReliefBo.getById(aftRelief);
		
		AcLnMst acLnMst = new AcLnMst();
		acLnMst.setPactNo(aftRelief.getPactNo());
		acLnMst.setBrNo(aftRelief.getBrNo());
		acLnMst = acLnMstBo.getByPactNo(acLnMst);
		AcChrgLog acChrgLog = new AcChrgLog();
		acChrgLog.setLoanNo(acLnMst.getLoanNo());
		//欠费
		double loFee = acChrgLogBo.getLoFeeAmt(acChrgLog);
		aftRelief.setLoFee(loFee);
		AcLnLo acLnLo = new AcLnLo();
		
		acLnLo.setLoanNo(acLnMst.getLoanNo());
		acLnLo = acLnLoBo.getLoAmt(acLnLo);
		//欠本
		double loPrcp = acLnLo.getPrcpAmt();
		aftRelief.setLoAmt(loPrcp);
		//欠息
		double loNorm = acLnLo.getNormInt();
		aftRelief.setLoIntst(loNorm);
		//欠罚
		double loFine = acLnLo.getFineInt();
		aftRelief.setLoFine(loFine);
		
		
		WkfParm parm = new WkfParm();
		parm.setApp_no(refId);//ID号
		parm.setApp_type("04");//黑名单
		
		StringBuilder wfAppValue = new StringBuilder();
		wfAppValue.append("合同号:"+aftRelief.getPactNo());
		wfAppValue.append(",合作机构名称:"+aftRelief.getBrName());
		wfAppValue.append(",客户名称:"+aftRelief.getCifName());
		
		parm.setWf_app_value(wfAppValue.toString());//业务串
		String nextDesc="";
		if(aftRelief.getRefAmt()==(0.0)&&aftRelief.getRefIntst()==(0.0)){
			aftRelief.setAppSts("03");
			aftReliefBo.update(aftRelief);
			aftReliefBo.execAftRelief(aftRelief);
			
			this.addActionMessage("操作成功");
		}else{
			 nextDesc =aftReliefBo.doSubmit(parm,aftRelief,User.getLoginid(getHttpRequest()), User.getBrno(getHttpRequest()));
			 this.addActionMessage("操作成功,下一节点操作员"+nextDesc);
		}
		Ipage ipage = this.getIpage();
		aftRelief = new AftRelief();
		aftReliefList = (List) aftReliefBo.findByPage(ipage, aftRelief).getResult();
		return "list";
	}	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formaftrel002 = formService.getFormData("aftrel002");
		getFormValue(formaftrel002);
		aftRelief = new AftRelief();
		setObjValue(formaftrel002, aftRelief);
		validateFormData(formaftrel002);
		if(aftRelief.getRefIntst()>aftRelief.getLoIntst()){
			this.addFieldError("减免利息", "减免利息不能大于欠息");
		}
		if(aftRelief.getRefFine()>aftRelief.getLoFine()){
			this.addFieldError("减免罚息", "减免罚息不能大于欠罚息");
		}
		if(aftRelief.getRefFee()>aftRelief.getLoFee()){
			this.addFieldError("减免费用", "减免费用不能大于欠费用");
		}
		if(aftRelief.getRefAmt()>aftRelief.getLoAmt()){
			this.addFieldError("减免本金", "减免本金不能大于欠本");
		}
		double refMont = aftRelief.getRefIntst()+aftRelief.getRefFine()+aftRelief.getRefFee()+aftRelief.getRefAmt();
		if(refMont<=0){
			this.addFieldError("减免总金额", "减免总金额大于0");
		}
		this.query = "query1";
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formaftrel002 = formService.getFormData("aftrel002");
		 getFormValue(formaftrel002);
		 aftRelief = new AftRelief();
		 setObjValue(formaftrel002, aftRelief);
		 validateFormData(formaftrel002);
		 if(aftRelief.getRefIntst()>aftRelief.getLoIntst()){
				this.addFieldError("减免利息", "减免利息不能大于欠息");
			}
		 if(aftRelief.getRefFine()>aftRelief.getLoFine()){
				this.addFieldError("减免罚息", "减免罚息不能大于欠罚息");
			}
		 if(aftRelief.getRefFee()>aftRelief.getLoFee()){
				this.addFieldError("减免费用", "减免费用不能大于欠费用");
			}
		 if(aftRelief.getRefAmt()>aftRelief.getLoAmt()){
				this.addFieldError("减免本金", "减免本金不能大于欠本");
			}
			double refMont = aftRelief.getRefIntst()+aftRelief.getRefFine()+aftRelief.getRefFee()+aftRelief.getRefAmt();
		 if(refMont<=0){
				this.addFieldError("减免总金额", "减免总金额大于0");
			}
  	}
	public AftRelief getAftRelief() {
		return aftRelief;
	}
	public void setAftRelief(AftRelief  aftRelief) {
		this.aftRelief = aftRelief;
	}
	public List<AftRelief> getAftReliefList() {
		return aftReliefList;
	}
	public void setAftReliefList(List<AftRelief> aftReliefList) {
		this.aftReliefList = aftReliefList;
	}
	public AftReliefBo getAftReliefBo() {
		return aftReliefBo;
	}
	public void setAftReliefBo(AftReliefBo aftReliefBo) {
		this.aftReliefBo = aftReliefBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormaftrel002() {
		return formaftrel002;
	}
	public void setFormaftrel002(FormData formaftrel002) {
		this.formaftrel002 = formaftrel002;
	}
	public FormData getFormaftrel001() {
		return formaftrel001;
	}
	public void setFormaftrel001(FormData formaftrel001) {
		this.formaftrel001 = formaftrel001;
	}
	public void setRefId(String refId){
		this.refId = refId;
	}		
	public String getRefId(){
		return refId;
	}
	public AcLnMstBo getAcLnMstBo() {
		return acLnMstBo;
	}
	public AcChrgLogBo getAcChrgLogBo() {
		return acChrgLogBo;
	}
	public AcLnLoBo getAcLnLoBo() {
		return acLnLoBo;
	}
	public void setAcLnMstBo(AcLnMstBo acLnMstBo) {
		this.acLnMstBo = acLnMstBo;
	}
	public void setAcChrgLogBo(AcChrgLogBo acChrgLogBo) {
		this.acChrgLogBo = acChrgLogBo;
	}
	public void setAcLnLoBo(AcLnLoBo acLnLoBo) {
		this.acLnLoBo = acLnLoBo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getApprType() {
		return apprType;
	}
	public void setApprType(String apprType) {
		this.apprType = apprType;
	}
	public LnDueBo getLnDueBo() {
		return lnDueBo;
	}
	public void setLnDueBo(LnDueBo lnDueBo) {
		this.lnDueBo = lnDueBo;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
}