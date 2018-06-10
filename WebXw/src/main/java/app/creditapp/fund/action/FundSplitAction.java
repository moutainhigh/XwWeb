package  app.creditapp.fund.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.fund.bo.FundPayPlanBo;
import app.creditapp.fund.bo.FundSplitBo;
import app.creditapp.fund.entity.FundRel;
import app.creditapp.fund.entity.FundSplit;
import app.util.User;
import app.util.toolkit.Ipage;
/**
 * Title: FundSplitAction.java
 * Description:
 **/
public class FundSplitAction extends BaseFormBean {

	//页面传值
	private FundSplit fundSplit;
	private List<FundSplit> fundSplitList;

	//注入FundSplitBo
	private FundSplitBo fundSplitBo;

	private String query;	
	private String partNo;		
	private String fundNo;
	private FundRel fundRel;
	private FormData formfdsplit0001;
	private FormData formfdsplit0002;
	private FormService formService = new FormService();
	
	//兑付计划生成所新增
	private FundPayPlanBo fundPayPlanBo;

	private List tabList;
	
	public FundSplitAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdsplit0001 = formService.getFormData("fdsplit0001");
		fundSplit = new FundSplit();
		getFormValue(formfdsplit0001);
		setObjValue(formfdsplit0001, fundSplit);
		Ipage ipage = this.getIpage();
		fundSplitList = (List) fundSplitBo.findByPage(ipage, fundSplit).getResult();
		for(int i = 0; i<fundSplitList.size();i++){
			fundSplitList.get(i).setQuery(query);
		}
		return "list";
	}
	public String echarts() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		fundSplit = new FundSplit();
		fundSplit.setFundNo(fundNo);
		fundSplitList = (List) fundSplitBo.echarts(fundSplit);
		
		String nodes = "";
		for(FundSplit fund: fundSplitList){
			nodes += ",{name:'"+fund.getPartName()+"',value:"+fund.getPartAmt()+"}";
		}
		if(nodes.length()>0){
			nodes = nodes.substring(1);
		}
		fundRel = new FundRel();
		fundRel.setTitle("");
		fundRel.setName("");
		fundRel.setData("");
		fundRel.setNodes(nodes);
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdsplit0002 = formService.getFormData("fdsplit0002");
		fundSplit = new FundSplit();
		fundSplit.setOpNo(User.getLoginid(this.getHttpRequest()));
		fundSplit.setFundNo(fundNo);
		getObjValue(formfdsplit0002, fundSplit);
		this.changeFormProperty(formfdsplit0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdsplit0002 = formService.getFormData("fdsplit0002");
		getFormValue(formfdsplit0002);
		fundSplit = new FundSplit();
		setObjValue(formfdsplit0002, fundSplit);
		fundSplitBo.insert(fundSplit);
		//新增兑付计划
		fdpayplan_insert(fundSplit);
		this.addActionMessage("新增成功");
		query="query";
		getObjValue(formfdsplit0002, fundSplit);
		return "detail";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdsplit0002 = formService.getFormData("fdsplit0002");
		getFormValue(formfdsplit0002);
		fundSplit = new FundSplit();
		setObjValue(formfdsplit0002, fundSplit);
		HttpServletRequest request = ServletActionContext.getRequest();
		fundSplit.setInvRate(Float.parseFloat(request.getParameter("invRate"))/100F);
		fundSplit.setOpNo(User.getLoginid(this.getHttpRequest()));
		fundSplitBo.update(fundSplit);
		//修改兑付计划
		fdpayplan_insert(fundSplit);
		this.addActionMessage("修改成功");
		query="query";
		getObjValue(formfdsplit0002, fundSplit);
		return "detail";
	}
	
	/**
	 * 分配方案新增添加兑付计划操作,分配方案新增需要开始时间，结束时间，兑付方式，方案金额，收益率
	 * @return
	 * @throws Exception
	 */
	public String fdpayplan_insert(FundSplit fundSplit) throws Exception {
		
		// 判断是否存在方案编号，兑付方式 
		if(fundSplit.getPayType()!=null && fundSplit.getInvRate() !=null){
			fundPayPlanBo.auto_insert(fundSplit);
		}
		
		return "detail";
	}
		
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdsplit0001 = formService.getFormData("fdsplit0001");
		fundSplit = new FundSplit();
		fundSplit.setPartNo(partNo);
		fundSplitBo.del(fundSplit);
		this.addActionMessage("删除成功");
		fundSplit = new FundSplit();
		Ipage ipage = this.getIpage();
		fundSplitList = (List) fundSplitBo.findByPage(ipage, fundSplit).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdsplit0002 = formService.getFormData("fdsplit0002");
		if(("query1").equals(query)){
			query="query";
		}
		fundSplit = new FundSplit();
		fundSplit.setPartNo(partNo);
		fundSplit = fundSplitBo.getById(fundSplit);
		getObjValue(formfdsplit0002, fundSplit);
		this.changeFormProperty(formfdsplit0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "detail";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdsplit0002 = formService.getFormData("fdsplit0002");
		 getFormValue(formfdsplit0002);
		 validateFormData(formfdsplit0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdsplit0002 = formService.getFormData("fdsplit0002");
		 getFormValue(formfdsplit0002);
		 validateFormData(formfdsplit0002);
  	}
	public FundSplit getFundSplit() {
		return fundSplit;
	}
	public void setFundSplit(FundSplit  fundSplit) {
		this.fundSplit = fundSplit;
	}
	public List<FundSplit> getFundSplitList() {
		return fundSplitList;
	}
	public void setFundSplitList(List<FundSplit> fundSplitList) {
		this.fundSplitList = fundSplitList;
	}
	public FundSplitBo getFundSplitBo() {
		return fundSplitBo;
	}
	public void setFundSplitBo(FundSplitBo fundSplitBo) {
		this.fundSplitBo = fundSplitBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdsplit0002() {
		return formfdsplit0002;
	}
	public void setFormfdsplit0002(FormData formfdsplit0002) {
		this.formfdsplit0002 = formfdsplit0002;
	}
	public FormData getFormfdsplit0001() {
		return formfdsplit0001;
	}
	public void setFormfdsplit0001(FormData formfdsplit0001) {
		this.formfdsplit0001 = formfdsplit0001;
	}	
	public void setPartNo(String partNo){
		this.partNo = partNo;
	}		
	public String getPartNo(){
		return partNo;
	}
	//兑付计划新增生成
	
	public FundPayPlanBo getFundPayPlanBo() {
		return fundPayPlanBo;
	}
	public void setFundPayPlanBo(FundPayPlanBo fundPayPlanBo) {
		this.fundPayPlanBo = fundPayPlanBo;
	}
	public String getFundNo() {
		return fundNo;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
	public FundRel getFundRel() {
		return fundRel;
	}
	public void setFundRel(FundRel fundRel) {
		this.fundRel = fundRel;
	}
}