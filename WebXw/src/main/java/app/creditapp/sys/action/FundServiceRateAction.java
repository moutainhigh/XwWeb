package  app.creditapp.sys.action;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.sys.bo.FundServiceRateBo;
import app.creditapp.sys.entity.FundServiceRate;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: FundServiceRateAction.java
 * Description:
 **/
public class FundServiceRateAction extends BaseFormBean {

	//页面传值
	private FundServiceRate fundServiceRate;
	private List<FundServiceRate> fundServiceRateList;

	//注入FundServiceRateBo
	private FundServiceRateBo fundServiceRateBo;
	private ProjBase projBase;
	private ProjBaseBo projBaseBo;
	private String query;
	private String projNo;		
	private int stepNo;
	private String projId;
	private String serviceRateId;
	private FormData formfdrate0001;
	private FormData formfdrate0002;
	private FormService formService = new FormService();
	private String backSts;
	
	public FundServiceRateAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdrate0001 = formService.getFormData("fdrate0001");
		fundServiceRate = new FundServiceRate();
		fundServiceRate.setProjNo(projNo);
		getFormValue(formfdrate0001);
		setObjValue(formfdrate0001, fundServiceRate);
		fundServiceRate.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		fundServiceRateList = (List) fundServiceRateBo.findByPage(ipage, fundServiceRate).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdrate0002 = formService.getFormData("fdrate0002");
		fundServiceRate = new FundServiceRate();
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase.setProjId(projId);
		projBase = projBaseBo.getById(projBase);
		fundServiceRate.setProjNo(projNo);
		fundServiceRate.setProjName(projBase.getProjName());
		fundServiceRate.setOpNo(User.getLoginid(this.getHttpRequest()));
		fundServiceRate.setTxDate((User.getTime().replace("-", "")).substring(0, 8));
		getObjValue(formfdrate0002, fundServiceRate);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdrate0002 = formService.getFormData("fdrate0002");
		getFormValue(formfdrate0002);
		fundServiceRate = new FundServiceRate();
		setObjValue(formfdrate0002, fundServiceRate);
		//stepNo类型修改
		fundServiceRate.setStepNo(Integer.toString(stepNo));
		fundServiceRate.setOpNo(User.getLoginid(this.getHttpRequest()));
		//修改信托管理费率
		HttpServletRequest request = ServletActionContext.getRequest();
		BigDecimal SvRate = new BigDecimal(request.getParameter("svRate"));
		BigDecimal parm = new BigDecimal(Integer.toString(100));
		fundServiceRate.setSvRate(SvRate.divide(parm).floatValue());	
		
		fundServiceRateBo.insert(fundServiceRate);
		this.addActionMessage("新增成功");
		//返回新增页面
		fundServiceRate.setStepNo("");
		fundServiceRate.setSvRate(null);
		fundServiceRate.setMaxAmt(null);
		fundServiceRate.setMinAmt(null);
		getObjValue(formfdrate0002, fundServiceRate);
		return "input";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdrate0002 = formService.getFormData("fdrate0002");
		getFormValue(formfdrate0002);
		fundServiceRate = new FundServiceRate();
		setObjValue(formfdrate0002, fundServiceRate);
		//stepNo类型修改
		fundServiceRate.setStepNo(Integer.toString(stepNo));
		//修改信托管理费率
		HttpServletRequest request = ServletActionContext.getRequest();
		BigDecimal SvRate = new BigDecimal(request.getParameter("svRate"));
		BigDecimal parm = new BigDecimal(Integer.toString(100));
		fundServiceRate.setSvRate(SvRate.divide(parm).floatValue());
		
		fundServiceRateBo.update(fundServiceRate);
		this.addActionMessage("更新成功");
		getObjValue(formfdrate0002, fundServiceRate);
		return "detail";
	}      
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdrate0001 = formService.getFormData("fdrate0001");
		fundServiceRate = new FundServiceRate();
		fundServiceRate.setServiceRateId(serviceRateId);
		fundServiceRateBo.del(fundServiceRate);
		this.addActionMessage("删除成功");
		fundServiceRate = new FundServiceRate();
		fundServiceRate.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		fundServiceRateList = (List) fundServiceRateBo.findByPage(ipage, fundServiceRate).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdrate0002 = formService.getFormData("fdrate0002");
		fundServiceRate = new FundServiceRate();
		fundServiceRate.setServiceRateId(serviceRateId);
		fundServiceRate = fundServiceRateBo.getById(fundServiceRate);
		getObjValue(formfdrate0002, fundServiceRate);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdrate0002 = formService.getFormData("fdrate0002");
		 getFormValue(formfdrate0002);
		 validateFormData(formfdrate0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdrate0002 = formService.getFormData("fdrate0002");
		 getFormValue(formfdrate0002);
		 validateFormData(formfdrate0002);
  	}
	public FundServiceRate getFundServiceRate() {
		return fundServiceRate;
	}
	public void setFundServiceRate(FundServiceRate  fundServiceRate) {
		this.fundServiceRate = fundServiceRate;
	}
	public List<FundServiceRate> getFundServiceRateList() {
		return fundServiceRateList;
	}
	public void setFundServiceRateList(List<FundServiceRate> fundServiceRateList) {
		this.fundServiceRateList = fundServiceRateList;
	}
	public FundServiceRateBo getFundServiceRateBo() {
		return fundServiceRateBo;
	}
	public void setFundServiceRateBo(FundServiceRateBo fundServiceRateBo) {
		this.fundServiceRateBo = fundServiceRateBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdrate0002() {
		return formfdrate0002;
	}
	public void setFormfdrate0002(FormData formfdrate0002) {
		this.formfdrate0002 = formfdrate0002;
	}
	public FormData getFormfdrate0001() {
		return formfdrate0001;
	}
	public void setFormfdrate0001(FormData formfdrate0001) {
		this.formfdrate0001 = formfdrate0001;
	}
	public void setProjNo(String projNo){
		this.projNo = projNo;
	}		
	public void setStepNo(Integer stepNo){
		this.stepNo = stepNo;
	}		
	public String getProjNo(){
		return projNo;
	}
	public Integer getStepNo(){
		return stepNo;
	}
	public ProjBaseBo getProjBaseBo() {
		return projBaseBo;
	}
	public void setProjBaseBo(ProjBaseBo projBaseBo) {
		this.projBaseBo = projBaseBo;
	}
	public ProjBase getProjBase() {
		return projBase;
	}
	public void setProjBase(ProjBase projBase) {
		this.projBase = projBase;
	}
	public String getServiceRateId() {
		return serviceRateId;
	}
	public void setServiceRateId(String serviceRateId) {
		this.serviceRateId = serviceRateId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
	
}