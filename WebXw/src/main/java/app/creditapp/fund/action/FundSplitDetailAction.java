package  app.creditapp.fund.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundSplitDetailBo;
import app.creditapp.fund.entity.FundSplit;
import app.creditapp.fund.entity.FundSplitDetail;
import app.creditapp.fund.entity.FundSplitTerm;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.fund.bo.FundSplitTermBo;
/**
 * Title: FundSplitDetailAction.java
 * Description:
 **/
public class FundSplitDetailAction extends BaseFormBean {

	//页面传值
	private FundSplitDetail fundSplitDetail;
	private List<FundSplitDetail> fundSplitDetailList;

	//注入FundSplitDetailBo
	private FundSplitDetailBo fundSplitDetailBo;

	private String query;
	private String partId;	
	private String fundNo;
	private String partNo;
	private FormData formfdspdt0001;
	private FormData formfdspdt0002;
	private FormService formService = new FormService();
	
	//分配方案明细引入
	private FundSplitTermBo fundSplitTermBo;
	
	public FundSplitDetailAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdspdt0001 = formService.getFormData("fdspdt0001");
		fundSplitDetail = new FundSplitDetail();
		getFormValue(formfdspdt0001);
		setObjValue(formfdspdt0001, fundSplitDetail);
		Ipage ipage = this.getIpage();
		fundSplitDetailList = (List) fundSplitDetailBo.findByPage(ipage, fundSplitDetail).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdspdt0002 = formService.getFormData("fdspdt0002");
		fundSplitDetail = new FundSplitDetail();
		fundSplitDetail.setOpNo(User.getLoginid(this.getHttpRequest()));
		fundSplitDetail.setTxDate(User.getSys_date(this.getHttpRequest()));
		fundSplitDetail.setPartNo(partNo);
		getObjValue(formfdspdt0002, fundSplitDetail);
		this.changeFormProperty(formfdspdt0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		this.changeFormProperty(formfdspdt0002, "txDate", "readonly", "1");//1是只读，0是可编辑

		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdspdt0002 = formService.getFormData("fdspdt0002");
		getFormValue(formfdspdt0002);
		fundSplitDetail = new FundSplitDetail();
		setObjValue(formfdspdt0002, fundSplitDetail);
		fundSplitDetailBo.insert(fundSplitDetail);
		//新增分配方案存续数据
		fdsplitterm_insert(fundSplitDetail);
		this.addActionMessage("新增成功");
		query="query";
		getObjValue(formfdspdt0002, fundSplitDetail);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdspdt0002 = formService.getFormData("fdspdt0002");
		getFormValue(formfdspdt0002);
		fundSplitDetail = new FundSplitDetail();
		setObjValue(formfdspdt0002, fundSplitDetail);
		fundSplitDetailBo.update(fundSplitDetail);
		this.addActionMessage("修改成功");
		query="query";
		getObjValue(formfdspdt0002, fundSplitDetail);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdspdt0001 = formService.getFormData("fdspdt0001");
		fundSplitDetail = new FundSplitDetail();
		fundSplitDetail.setPartId(partId);
		fundSplitDetailBo.del(fundSplitDetail);
		this.addActionMessage("删除成功");
		fundSplitDetail = new FundSplitDetail();
		fundSplitDetail.setPartNo(partNo);
		Ipage ipage = this.getIpage();
		fundSplitDetailList = (List) fundSplitDetailBo.findByPage(ipage, fundSplitDetail).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdspdt0002 = formService.getFormData("fdspdt0002");
		fundSplitDetail = new FundSplitDetail();
		fundSplitDetail.setPartId(partId);
		fundSplitDetail = fundSplitDetailBo.getById(fundSplitDetail);
		getObjValue(formfdspdt0002, fundSplitDetail);
		this.changeFormProperty(formfdspdt0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "detail";
	}
	
	/**
	 * 分配方案明细新增,对应调整分配方案存续明细操作
	 * @return
	 * @throws Exception
	 */
	public String fdsplitterm_insert(FundSplitDetail fundSplitDetail) throws Exception {
		
		fundSplitTermBo.auto_insert(fundSplitDetail);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdspdt0002 = formService.getFormData("fdspdt0002");
		 getFormValue(formfdspdt0002);
		 validateFormData(formfdspdt0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdspdt0002 = formService.getFormData("fdspdt0002");
		 getFormValue(formfdspdt0002);
		 validateFormData(formfdspdt0002);
  	}
	public FundSplitDetail getFundSplitDetail() {
		return fundSplitDetail;
	}
	public void setFundSplitDetail(FundSplitDetail  fundSplitDetail) {
		this.fundSplitDetail = fundSplitDetail;
	}
	public List<FundSplitDetail> getFundSplitDetailList() {
		return fundSplitDetailList;
	}
	public void setFundSplitDetailList(List<FundSplitDetail> fundSplitDetailList) {
		this.fundSplitDetailList = fundSplitDetailList;
	}
	public FundSplitDetailBo getFundSplitDetailBo() {
		return fundSplitDetailBo;
	}
	public void setFundSplitDetailBo(FundSplitDetailBo fundSplitDetailBo) {
		this.fundSplitDetailBo = fundSplitDetailBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdspdt0002() {
		return formfdspdt0002;
	}
	public void setFormfdspdt0002(FormData formfdspdt0002) {
		this.formfdspdt0002 = formfdspdt0002;
	}
	public FormData getFormfdspdt0001() {
		return formfdspdt0001;
	}
	public void setFormfdspdt0001(FormData formfdspdt0001) {
		this.formfdspdt0001 = formfdspdt0001;
	}
	public void setPartId(String partId){
		this.partId = partId;
	}		
	public String getPartId(){
		return partId;
	}
	//分配方案存续期新增引入
	public FundSplitTermBo getFundSplitTermBo() {
		return fundSplitTermBo;
	}
	public void setFundSplitTermBo(FundSplitTermBo fundSplitTermBo) {
		this.fundSplitTermBo = fundSplitTermBo;
	}
	public String getFundNo() {
		return fundNo;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
}