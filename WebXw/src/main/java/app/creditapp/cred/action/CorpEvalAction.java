package  app.creditapp.cred.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.acc.log.bo.AcTraceLogBo;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.cred.bo.CorpEvalBo;
import app.creditapp.cred.entity.CorpEval;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.util.toolkit.Ipage;

/**
 * Title: CorpEvalAction.java
 * Description:
 **/
public class CorpEvalAction extends BaseFormBean {

	//页面传值
	private CorpEval corpEval;
	private LnPact lnPact;
	private AcTraceLog acTraceLog;
	private List<CorpEval> corpEvalList;

	//注入CorpEvalBo
	private CorpEvalBo corpEvalBo;
	private LnPactBo lnPactBo;
	private AcTraceLogBo acTraceLogBo;

	private List tabList;
	private String query;
	private String evalNo;		
	private String apprKind;		
	private String appId;		
	private String brNo;
	private String formSts;
	private String sealLevel;
	private FormData formcred0001;
	private FormData formcred0002;
	private FormService formService = new FormService();
	
	public CorpEvalAction() {
		query = "";
	}

	public String findByPage360() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		corpEval = new CorpEval();
		corpEval.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		corpEvalList = (List) corpEvalBo.findByPage(ipage, corpEval).getResult();
		return "list";
	}
	
	public String getFormSts() {
		return formSts;
	}
	public void setFormSts(String formSts) {
		this.formSts = formSts;
	}
	public String getSealLevel() {
		return sealLevel;
	}
	public void setSealLevel(String sealLevel) {
		this.sealLevel = sealLevel;
	}

	public CorpEval getCorpEval() {
		return corpEval;
	}

	public void setCorpEval(CorpEval corpEval) {
		this.corpEval = corpEval;
	}

	public LnPact getLnPact() {
		return lnPact;
	}

	public void setLnPact(LnPact lnPact) {
		this.lnPact = lnPact;
	}

	public AcTraceLog getAcTraceLog() {
		return acTraceLog;
	}

	public void setAcTraceLog(AcTraceLog acTraceLog) {
		this.acTraceLog = acTraceLog;
	}

	public List<CorpEval> getCorpEvalList() {
		return corpEvalList;
	}

	public void setCorpEvalList(List<CorpEval> corpEvalList) {
		this.corpEvalList = corpEvalList;
	}

	public CorpEvalBo getCorpEvalBo() {
		return corpEvalBo;
	}

	public void setCorpEvalBo(CorpEvalBo corpEvalBo) {
		this.corpEvalBo = corpEvalBo;
	}

	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}

	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}

	public AcTraceLogBo getAcTraceLogBo() {
		return acTraceLogBo;
	}

	public void setAcTraceLogBo(AcTraceLogBo acTraceLogBo) {
		this.acTraceLogBo = acTraceLogBo;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getEvalNo() {
		return evalNo;
	}

	public void setEvalNo(String evalNo) {
		this.evalNo = evalNo;
	}

	public String getApprKind() {
		return apprKind;
	}

	public void setApprKind(String apprKind) {
		this.apprKind = apprKind;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public FormData getFormcred0001() {
		return formcred0001;
	}

	public void setFormcred0001(FormData formcred0001) {
		this.formcred0001 = formcred0001;
	}

	public FormData getFormcred0002() {
		return formcred0002;
	}

	public void setFormcred0002(FormData formcred0002) {
		this.formcred0002 = formcred0002;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}
}