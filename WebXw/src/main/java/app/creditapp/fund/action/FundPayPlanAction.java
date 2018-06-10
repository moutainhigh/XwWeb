package  app.creditapp.fund.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundPayPlanBo;
import app.creditapp.fund.entity.FundPayPlan;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: FundPayPlanAction.java
 * Description:
 **/
public class FundPayPlanAction extends BaseFormBean {

	//页面传值
	private FundPayPlan fundPayPlan;
	private List<FundPayPlan> fundPayPlanList;

	//注入FundPayPlanBo
	private FundPayPlanBo fundPayPlanBo;

	private String query;
	private String fundNo;	
	private String fdType;
	private String payPlanNo;		
	private FormData formfdlpan0001;
	private FormData formfdlpan0002;
	private FormService formService = new FormService();
	
	public FundPayPlanAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdlpan0001 = formService.getFormData("fdlpan0001");
		fundPayPlan = new FundPayPlan();
		getFormValue(formfdlpan0001);
		setObjValue(formfdlpan0001, fundPayPlan);
		Ipage ipage = this.getIpage();
		fundPayPlanList = (List) fundPayPlanBo.findByPage(ipage, fundPayPlan).getResult();
		return "list";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPageForAmt() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdlpan0001 = formService.getFormData("fdlpan0001");
		fundPayPlan = new FundPayPlan();
		getFormValue(formfdlpan0001);
		setObjValue(formfdlpan0001, fundPayPlan);
		fundPayPlan.setFundNo(fundNo);
		fundPayPlan.setFdType(fdType);
		Ipage ipage = this.getIpage();
		fundPayPlanList = (List) fundPayPlanBo.findByPageForAmt(ipage, fundPayPlan).getResult();
		for(int i = 0; i<fundPayPlanList.size();i++){
			fundPayPlanList.get(i).setQuery(query);
		}
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdlpan0002 = formService.getFormData("fdlpan0002");
		fundPayPlan = new FundPayPlan();
		fundPayPlan.setFundNo(fundNo);
		getObjValue(formfdlpan0002, fundPayPlan);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdlpan0002 = formService.getFormData("fdlpan0002");
		getFormValue(formfdlpan0002);
		fundPayPlan = new FundPayPlan();
		setObjValue(formfdlpan0002, fundPayPlan);
		fundPayPlanBo.insert(fundPayPlan);
		this.addActionMessage("新增成功");	
		getObjValue(formfdlpan0002, fundPayPlan);
		query="query";
		return "detail";
	}
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public  String insert1(String partNo,String payType,float invRate) throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdlpan0002 = formService.getFormData("fdlpan0002");
		getFormValue(formfdlpan0002);
		fundPayPlan = new FundPayPlan();
		setObjValue(formfdlpan0002, fundPayPlan);
		fundPayPlanBo.insert(fundPayPlan);
		this.addActionMessage("新增成功");
		getObjValue(formfdlpan0002, fundPayPlan);
		return "detail";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdlpan0002 = formService.getFormData("fdlpan0002");
		getFormValue(formfdlpan0002);
		fundPayPlan = new FundPayPlan();
		setObjValue(formfdlpan0002, fundPayPlan);
		fundPayPlanBo.update(fundPayPlan);
		this.addActionMessage("修改成功");
		query="query";
		getObjValue(formfdlpan0002, fundPayPlan);
		return "detail";
	}
	
	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdlpan0002 = formService.getFormData("fdlpan0002");
		fundPayPlan = new FundPayPlan();
		fundPayPlan.setPayPlanNo(payPlanNo);
		fundPayPlan = fundPayPlanBo.getById(fundPayPlan);
		getObjValue(formfdlpan0002, fundPayPlan);
		return "detail";
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdlpan0001 = formService.getFormData("fdlpan0001");
		fundPayPlan = new FundPayPlan();
		fundPayPlan.setPayPlanNo(payPlanNo);
		fundPayPlanBo.del(fundPayPlan);
		this.addActionMessage("删除成功");
		fundPayPlan = new FundPayPlan();
		fundPayPlan.setFundNo(fundNo);
		Ipage ipage = this.getIpage();
		fundPayPlanList = (List) fundPayPlanBo.findByPage(ipage, fundPayPlan).getResult();
		return "list";
	}

	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdlpan0002 = formService.getFormData("fdlpan0002");
		 getFormValue(formfdlpan0002);
		 validateFormData(formfdlpan0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdlpan0002 = formService.getFormData("fdlpan0002");
		 getFormValue(formfdlpan0002);
		 validateFormData(formfdlpan0002);
  	}
	public FundPayPlan getFundPayPlan() {
		return fundPayPlan;
	}
	public void setFundPayPlan(FundPayPlan  fundPayPlan) {
		this.fundPayPlan = fundPayPlan;
	}
	public List<FundPayPlan> getFundPayPlanList() {
		return fundPayPlanList;
	}
	public void setFundPayPlanList(List<FundPayPlan> fundPayPlanList) {
		this.fundPayPlanList = fundPayPlanList;
	}
	public FundPayPlanBo getFundPayPlanBo() {
		return fundPayPlanBo;
	}
	public void setFundPayPlanBo(FundPayPlanBo fundPayPlanBo) {
		this.fundPayPlanBo = fundPayPlanBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdlpan0002() {
		return formfdlpan0002;
	}
	public void setFormfdlpan0002(FormData formfdlpan0002) {
		this.formfdlpan0002 = formfdlpan0002;
	}
	public FormData getFormfdlpan0001() {
		return formfdlpan0001;
	}
	public void setFormfdlpan0001(FormData formfdlpan0001) {
		this.formfdlpan0001 = formfdlpan0001;
	}
	public void setPayPlanNo(String payPlanNo){
		this.payPlanNo = payPlanNo;
	}		
	public String getPayPlanNo(){
		return payPlanNo;
	}
	public String getFundNo() {
		return fundNo;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public String getFdType() {
		return fdType;
	}
	public void setFdType(String fdType) {
		this.fdType = fdType;
	}
}