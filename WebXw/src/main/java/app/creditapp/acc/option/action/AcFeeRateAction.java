package  app.creditapp.acc.option.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFeeRateBo;
import app.creditapp.acc.option.entity.AcFeeRate;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: AcFeeRateAction.java
 * Description:
 **/
public class AcFeeRateAction extends BaseFormBean {

	//页面传值
	private AcFeeRate acFeeRate;
	private List<AcFeeRate> acFeeRateList;

	//注入AcFeeRateBo
	private AcFeeRateBo acFeeRateBo;

	private String query;
	private String feeId;
	private String frId;//费率定义编号
	private String longFeeId;//用于传递页面拼接的feeId
	private FormData formfee001;
	private FormData formfee002;
	private FormService formService = new FormService();
	
	public AcFeeRateAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		acFeeRate = new AcFeeRate();
		if(frId != null && frId.length()>0){
			acFeeRate.setFrId(frId);
		}
		Ipage ipage = this.getIpage();
		acFeeRateList = (List) acFeeRateBo.findByPage(ipage, acFeeRate).getResult();
		return "list";
	}
	
	public String findByPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfee001 = formService.getFormData("fee001");
		acFeeRate = new AcFeeRate();
		getFormValue(formfee001);
		setObjValue(formfee001, acFeeRate);
		Ipage ipage = this.getIpage();
		acFeeRateList = (List) acFeeRateBo.findByPage(ipage, acFeeRate).getResult();
		this.getHttpRequest().setAttribute("feeId", longFeeId);

		return "listForPop";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee002 = formService.getFormData("fee002");
		getFormValue(formfee002);
		acFeeRate = new AcFeeRate();
		acFeeRate.setFrId(frId);
		setObjValue(formfee002, acFeeRate);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee002 = formService.getFormData("fee002");
		getFormValue(formfee002);
		acFeeRate = new AcFeeRate();
		setObjValue(formfee002, acFeeRate);
		acFeeRateBo.insert(acFeeRate);
		getObjValue(formfee002, acFeeRate);
		this.addActionMessage("保存成功！");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee002 = formService.getFormData("fee002");
		getFormValue(formfee002);
		acFeeRate = new AcFeeRate();
		setObjValue(formfee002, acFeeRate);
		acFeeRateBo.update(acFeeRate);
		getObjValue(formfee002, acFeeRate);
		this.addActionMessage("保存成功！");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfee001 = formService.getFormData("fee001");
		acFeeRate = new AcFeeRate();
		acFeeRate.setFeeId(feeId);
		acFeeRateBo.del(acFeeRate);
		this.addActionMessage("删除成功");
		acFeeRate = new AcFeeRate();
		Ipage ipage = this.getIpage();
		acFeeRate.setFrId(frId);
		acFeeRateList = (List) acFeeRateBo.findByPage(ipage, acFeeRate).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfee002 = formService.getFormData("fee002");
		acFeeRate = new AcFeeRate();
		acFeeRate.setFeeId(feeId);
		acFeeRate = acFeeRateBo.getById(acFeeRate);
		frId = acFeeRate.getFrId();
		getObjValue(formfee002, acFeeRate);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfee002 = formService.getFormData("fee002");
		 getFormValue(formfee002);
		 validateFormData(formfee002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfee002 = formService.getFormData("fee002");
		 getFormValue(formfee002);
		 validateFormData(formfee002);
  	}
	public AcFeeRate getAcFeeRate() {
		return acFeeRate;
	}
	public void setAcFeeRate(AcFeeRate  acFeeRate) {
		this.acFeeRate = acFeeRate;
	}
	public List<AcFeeRate> getAcFeeRateList() {
		return acFeeRateList;
	}
	public void setAcFeeRateList(List<AcFeeRate> acFeeRateList) {
		this.acFeeRateList = acFeeRateList;
	}
	public AcFeeRateBo getAcFeeRateBo() {
		return acFeeRateBo;
	}
	public void setAcFeeRateBo(AcFeeRateBo acFeeRateBo) {
		this.acFeeRateBo = acFeeRateBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfee002() {
		return formfee002;
	}
	public void setFormfee002(FormData formfee002) {
		this.formfee002 = formfee002;
	}
	public FormData getFormfee001() {
		return formfee001;
	}
	public void setFormfee001(FormData formfee001) {
		this.formfee001 = formfee001;
	}
	public void setFeeId(String feeId){
		this.feeId = feeId;
	}		
	public String getFeeId(){
		return feeId;
	}
	public String getLongFeeId() {
		return longFeeId;
	}
	public void setLongFeeId(String longFeeId) {
		this.longFeeId = longFeeId;
	}
	public String getFrId() {
		return frId;
	}
	public void setFrId(String frId) {
		this.frId = frId;
	}
}