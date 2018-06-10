package  app.creditapp.sys.action;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.sys.bo.AccessCheckInfoBo;
import app.creditapp.sys.bo.PrdtBaseBo;
import app.creditapp.sys.entity.AccessCheckInfo;
import app.creditapp.sys.entity.PrdtBase;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpBaseAction.java
 * Description:
 **/
public class ParamIntegAction extends BaseFormBean {

	//页面传值
	private PrdtBase prdtBase;
	private List<PrdtBase> prdtBaseList;
	private AccessCheckInfo accessCheckInfo;
	private List<AccessCheckInfo> accessCheckInfoList;
	//注入PrdtBaseBo
	private PrdtBaseBo prdtBaseBo;
	private String query;
	private String brNo;	
	
	//注入AccessCheckInfoBo
	private AccessCheckInfoBo accessCheckInfoBo;

	private FormData formsys0041;
	
	private FormService formService = new FormService();
	
	public ParamIntegAction() {
		query = "";
	}
	//跳转页
	public String skipPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "sk";
	}
	//参数类首页
	public String toParam() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		return "param";
	}
	//top页
	public String headPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "head";
	}
	//性能类首页
	public String performance() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "perf";
	}
	//日志类首页
	public String sysLog() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "log";
	}
	//监控类首页
	public String monitor() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "moni";
	}
	//工具类首页
	public String sysTools() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "tools";
	}
	//业务类首页
	public String business() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		return "busi";
	}
	
	/**
	 * 分页查询产品
	 * @return
	 * @throws Exception
	 */
	public String getPrdtList() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formsys0041 = formService.getFormData("sys0041");
		prdtBase = new PrdtBase();
		
		getFormValue(formsys0041);
		setObjValue(formsys0041, prdtBase);
        if(brNo==null || "".equals(brNo) || "brNo".equals(brNo)){
        	prdtBase.setBrNo(null);
		}
		Ipage ipage = this.getIpage();
		prdtBaseList = (List<PrdtBase>) prdtBaseBo.findByPage(ipage, prdtBase).getResult();
		return "prdt";
	}

	//准入信息详情
	public String findDetail() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		
		accessCheckInfoList = new ArrayList<AccessCheckInfo>();
		//1、当前选择的合作机构，是否关联了TA的客户信息
		accessCheckInfo = new AccessCheckInfo();
		accessCheckInfo.setBrNo(brNo);		
		int msg = accessCheckInfoBo.countForMsg(accessCheckInfo);
		accessCheckInfo.setSerial("1");
		accessCheckInfo.setCheckItem("合作机构是否关联了TA的客户信息");
		if (msg > 0){
			accessCheckInfo.setFlag("01");
			accessCheckInfo.setCheckResult("通过");
		}else{
			accessCheckInfo.setFlag("00");
			accessCheckInfo.setCheckResult("不通过;合作机构尚未关联TA的客户信息");
		}
		accessCheckInfoList.add(accessCheckInfo);
		//2、合作机构维度的配置参数是否配置完成
		accessCheckInfo = new AccessCheckInfo();
		accessCheckInfo.setBrNo(brNo);
		int conf = accessCheckInfoBo.countForConf(accessCheckInfo);
		accessCheckInfo.setSerial("2");
		accessCheckInfo.setCheckItem("合作机构维度的配置参数是否配置完成");
		if(conf == 1){
			accessCheckInfo.setFlag("01");
			accessCheckInfo.setCheckResult("通过");
		}else{
			accessCheckInfo.setFlag("00");
			accessCheckInfo.setCheckResult("不通过；合作机构维度的配置参数尚未配置完成");
		}
		accessCheckInfoList.add(accessCheckInfo);
		//3、合作机构是否已经关联信托项目
		accessCheckInfo = new AccessCheckInfo();
		accessCheckInfo.setBrNo(brNo);
		int rele = accessCheckInfoBo.countForRele(accessCheckInfo);
		accessCheckInfo.setSerial("3");
		accessCheckInfo.setCheckItem("合作机构是否已经关联信托项目");
		if (rele > 0){
			accessCheckInfo.setFlag("01");
			accessCheckInfo.setCheckResult("通过");
		}else{
			accessCheckInfo.setFlag("00");
			accessCheckInfo.setCheckResult("不通过；合作机构尚未关联信托项目");
		}
		accessCheckInfoList.add(accessCheckInfo);
		//4、信托项目维度是否参数已配置完成
		accessCheckInfo = new AccessCheckInfo();
		accessCheckInfo.setBrNo(brNo);
		int param = accessCheckInfoBo.countForParam(accessCheckInfo);
		accessCheckInfo.setSerial("4");
		accessCheckInfo.setCheckItem("信托项目维度是否参数已配置完成");
		if (param > 0){
			int paramConf = accessCheckInfoBo.countForParamConf(accessCheckInfo);
			if(paramConf == 0){
				accessCheckInfo.setFlag("01");
				accessCheckInfo.setCheckResult("通过");				
			}else{
				accessCheckInfo.setFlag("00");
				accessCheckInfo.setCheckResult("不通过；信托项目维度参数尚未配置完成");
			}
		}else{
			accessCheckInfo.setFlag("00");
			accessCheckInfo.setCheckResult("不通过；信托项目维度参数尚未配置完成");
		}
		accessCheckInfoList.add(accessCheckInfo);
		//5、合作机构关联的信托项目中运营人员是否已经全部指派
		accessCheckInfo = new AccessCheckInfo();
		accessCheckInfo.setBrNo(brNo);
		int desig = accessCheckInfoBo.countForDesig(accessCheckInfo);
		accessCheckInfo.setSerial("5");
		accessCheckInfo.setCheckItem("合作机构关联的信托项目中运营人员是否已经全部指派");
		if (desig == 0){
			accessCheckInfo.setFlag("01");
			accessCheckInfo.setCheckResult("通过");
		}else{
			accessCheckInfo.setFlag("00");
			accessCheckInfo.setCheckResult("不通过;合作机构关联的信托项目中运营人员尚未全部指派");
		}
		accessCheckInfoList.add(accessCheckInfo);
		
		return "det";
	}
	
	
	public void setBrNo(String brNo){
		this.brNo = brNo;
	}		
	public String getBrNo(){
		return brNo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public PrdtBaseBo getPrdtBaseBo() {
		return prdtBaseBo;
	}
	public void setPrdtBaseBo(PrdtBaseBo prdtBaseBo) {
		this.prdtBaseBo = prdtBaseBo;
	}
	public FormData getFormsys0041() {
		return formsys0041;
	}
	public void setFormsys0041(FormData formsys0041) {
		this.formsys0041 = formsys0041;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public PrdtBase getPrdtBase() {
		return prdtBase;
	}
	public void setPrdtBase(PrdtBase prdtBase) {
		this.prdtBase = prdtBase;
	}
	public List<PrdtBase> getPrdtBaseList() {
		return prdtBaseList;
	}
	public void setPrdtBaseList(List<PrdtBase> prdtBaseList) {
		this.prdtBaseList = prdtBaseList;
	}
	public AccessCheckInfo getAccessCheckInfo() {
		return accessCheckInfo;
	}
	public void setAccessCheckInfo(AccessCheckInfo accessCheckInfo) {
		this.accessCheckInfo = accessCheckInfo;
	}
	public AccessCheckInfoBo getAccessCheckInfoBo() {
		return accessCheckInfoBo;
	}
	public void setAccessCheckInfoBo(AccessCheckInfoBo accessCheckInfoBo) {
		this.accessCheckInfoBo = accessCheckInfoBo;
	}
	public List<AccessCheckInfo> getAccessCheckInfoList() {
		return accessCheckInfoList;
	}
	public void setAccessCheckInfoList(List<AccessCheckInfo> accessCheckInfoList) {
		this.accessCheckInfoList = accessCheckInfoList;
	}
}