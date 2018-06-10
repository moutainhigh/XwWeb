package  app.creditapp.fund.action;
import java.text.DecimalFormat;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.bat.entity.RptPrdtDaily;
import app.creditapp.corp.entity.RptParams;
import app.creditapp.fund.bo.RptFundDailyBo;
import app.creditapp.fund.entity.RptFundDaily;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: RptFundDailyAction.java
 * Description:
 **/
public class RptFundDailyAction extends BaseFormBean {

	//页面传值
	private RptFundDaily rptFundDaily;
	private List<RptFundDaily> rptFundDailyList;

	//注入RptFundDailyBo
	private RptFundDailyBo rptFundDailyBo;
	private RptParams rptParams;
	
	private String query;
	private String fundNo;		
	private String fdType;		
	private String rptDate;		
	private FormData formfund0003;
	private FormData formfund0004;
	private FormService formService = new FormService();
	
	public RptFundDailyAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfund0003 = formService.getFormData("fund0003");
		rptFundDaily = new RptFundDaily();
		getFormValue(formfund0003);
		setObjValue(formfund0003, rptFundDaily);
		Ipage ipage = this.getIpage();
		rptFundDailyList = (List) rptFundDailyBo.findByPage(ipage, rptFundDaily).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfund0004 = formService.getFormData("fund0004");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfund0004 = formService.getFormData("fund0004");
		getFormValue(formfund0004);
		rptFundDaily = new RptFundDaily();
		setObjValue(formfund0004, rptFundDaily);
		rptFundDailyBo.insert(rptFundDaily);
		getObjValue(formfund0004, rptFundDaily);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfund0004 = formService.getFormData("fund0004");
		getFormValue(formfund0004);
		rptFundDaily = new RptFundDaily();
		setObjValue(formfund0004, rptFundDaily);
		rptFundDailyBo.update(rptFundDaily);
		getObjValue(formfund0004, rptFundDaily);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfund0003 = formService.getFormData("fund0003");
		rptFundDaily = new RptFundDaily();
		rptFundDaily.setFundNo(fundNo);
		rptFundDaily.setFdType(fdType);
		rptFundDaily.setRptDate(rptDate);
		rptFundDailyBo.del(rptFundDaily);
		this.addActionMessage("删除成功");
		rptFundDaily = new RptFundDaily();
		Ipage ipage = this.getIpage();
		rptFundDailyList = (List) rptFundDailyBo.findByPage(ipage, rptFundDaily).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfund0004 = formService.getFormData("fund0004");
		rptFundDaily = new RptFundDaily();
		rptFundDaily.setFundNo(fundNo);
		rptFundDaily.setFdType(fdType);
		rptFundDaily.setRptDate(rptDate);
		rptFundDaily = rptFundDailyBo.getById(rptFundDaily);
		getObjValue(formfund0004, rptFundDaily);
		return "detail";
	}
	public String echarts() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		RptFundDaily rptFundDaily = new RptFundDaily();
		rptFundDaily.setFundNo(fundNo);
		List<RptFundDaily> list = rptFundDailyBo.getList(rptFundDaily);
		
		String xStr = "";
		String data1 = "";
		String data2 = "";
		String data3 = "";
		String data4 = "";
		for(RptFundDaily rcd : list){
			xStr += ",'"+rcd.getRptDate()+"'";
			data1 += ","+new DecimalFormat("#.00").format(rcd.getFdAmt()/10000);
			data2 += ","+new DecimalFormat("#.00").format(rcd.getRightBal()/10000);
			data3 += ","+new DecimalFormat("#.00").format(rcd.getCashBal()/10000);
		}
		if(xStr.length()>0){
			xStr = xStr.substring(1);
			data1 = data1.substring(1);
			data2 = data2.substring(1);
			data3 = data3.substring(1);
		}
		rptParams = new RptParams();
		rptParams.setData1(xStr);
		rptParams.setData2(data1);
		rptParams.setData3(data2);
		rptParams.setData4(data3);
		return "detail";
	}	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfund0004 = formService.getFormData("fund0004");
		 getFormValue(formfund0004);
		 validateFormData(formfund0004);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfund0004 = formService.getFormData("fund0004");
		 getFormValue(formfund0004);
		 validateFormData(formfund0004);
  	}
	public RptFundDaily getRptFundDaily() {
		return rptFundDaily;
	}
	public void setRptFundDaily(RptFundDaily  rptFundDaily) {
		this.rptFundDaily = rptFundDaily;
	}
	public List<RptFundDaily> getRptFundDailyList() {
		return rptFundDailyList;
	}
	public void setRptFundDailyList(List<RptFundDaily> rptFundDailyList) {
		this.rptFundDailyList = rptFundDailyList;
	}
	public RptFundDailyBo getRptFundDailyBo() {
		return rptFundDailyBo;
	}
	public void setRptFundDailyBo(RptFundDailyBo rptFundDailyBo) {
		this.rptFundDailyBo = rptFundDailyBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfund0004() {
		return formfund0004;
	}
	public void setFormfund0004(FormData formfund0004) {
		this.formfund0004 = formfund0004;
	}
	public FormData getFormfund0003() {
		return formfund0003;
	}
	public void setFormfund0003(FormData formfund0003) {
		this.formfund0003 = formfund0003;
	}
	public void setFundNo(String fundNo){
		this.fundNo = fundNo;
	}		
	public void setFdType(String fdType){
		this.fdType = fdType;
	}		
	public void setRptDate(String rptDate){
		this.rptDate = rptDate;
	}		
	public String getFundNo(){
		return fundNo;
	}
	public String getFdType(){
		return fdType;
	}
	public String getRptDate(){
		return rptDate;
	}
	public RptParams getRptParams() {
		return rptParams;
	}
	public void setRptParams(RptParams rptParams) {
		this.rptParams = rptParams;
	}
}