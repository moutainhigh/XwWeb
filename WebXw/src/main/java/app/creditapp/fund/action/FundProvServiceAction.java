package  app.creditapp.fund.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundProvServiceBo;
import app.creditapp.fund.entity.FundProvService;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: FundProvServiceAction.java
 * Description:
 **/
public class FundProvServiceAction extends BaseFormBean {

	//页面传值
	private FundProvService fundProvService;
	private List<FundProvService> fundProvServiceList;

	//注入FundProvServiceBo
	private FundProvServiceBo fundProvServiceBo;

	private String query;
	private String provServiceNo;		
	private FormData formfdps0001;
	private FormData formfdps0002;
	private FormService formService = new FormService();
	
	public FundProvServiceAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdps0001 = formService.getFormData("fdps0001");
		fundProvService = new FundProvService();
		getFormValue(formfdps0001);
		setObjValue(formfdps0001, fundProvService);
		Ipage ipage = this.getIpage();
		fundProvServiceList = (List) fundProvServiceBo.findByPage(ipage, fundProvService).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdps0002 = formService.getFormData("fdps0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdps0002 = formService.getFormData("fdps0002");
		getFormValue(formfdps0002);
		fundProvService = new FundProvService();
		setObjValue(formfdps0002, fundProvService);
		fundProvService.setOpNo(User.getTlrno(this.getHttpRequest()));
		fundProvService.setTxDate(User.getSys_date(this.getHttpRequest()));
		fundProvServiceBo.insert(fundProvService);
		getObjValue(formfdps0002, fundProvService);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdps0002 = formService.getFormData("fdps0002");
		getFormValue(formfdps0002);
		fundProvService = new FundProvService();
		setObjValue(formfdps0002, fundProvService);
		fundProvServiceBo.update(fundProvService);
		getObjValue(formfdps0002, fundProvService);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdps0001 = formService.getFormData("fdps0001");
		fundProvService = new FundProvService();
		fundProvService.setProvServiceNo(provServiceNo);
		fundProvServiceBo.del(fundProvService);
		this.addActionMessage("删除成功");
		fundProvService = new FundProvService();
		Ipage ipage = this.getIpage();
		fundProvServiceList = (List) fundProvServiceBo.findByPage(ipage, fundProvService).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdps0002 = formService.getFormData("fdps0002");
		fundProvService = new FundProvService();
		fundProvService.setProvServiceNo(provServiceNo);
		fundProvService = fundProvServiceBo.getById(fundProvService);
		getObjValue(formfdps0002, fundProvService);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdps0002 = formService.getFormData("fdps0002");
		 getFormValue(formfdps0002);
		 validateFormData(formfdps0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdps0002 = formService.getFormData("fdps0002");
		 getFormValue(formfdps0002);
		 validateFormData(formfdps0002);
  	}
	public FundProvService getFundProvService() {
		return fundProvService;
	}
	public void setFundProvService(FundProvService  fundProvService) {
		this.fundProvService = fundProvService;
	}
	public List<FundProvService> getFundProvServiceList() {
		return fundProvServiceList;
	}
	public void setFundProvServiceList(List<FundProvService> fundProvServiceList) {
		this.fundProvServiceList = fundProvServiceList;
	}
	public FundProvServiceBo getFundProvServiceBo() {
		return fundProvServiceBo;
	}
	public void setFundProvServiceBo(FundProvServiceBo fundProvServiceBo) {
		this.fundProvServiceBo = fundProvServiceBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdps0002() {
		return formfdps0002;
	}
	public void setFormfdps0002(FormData formfdps0002) {
		this.formfdps0002 = formfdps0002;
	}
	public FormData getFormfdps0001() {
		return formfdps0001;
	}
	public void setFormfdps0001(FormData formfdps0001) {
		this.formfdps0001 = formfdps0001;
	}
	public void setProvServiceNo(String provServiceNo){
		this.provServiceNo = provServiceNo;
	}		
	public String getProvServiceNo(){
		return provServiceNo;
	}
}