package  app.creditapp.fund.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundProvBo;
import app.creditapp.fund.entity.FundProv;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: FundProvAction.java
 * Description:
 **/
public class FundProvAction extends BaseFormBean {

	//页面传值
	private FundProv fundProv;
	private List<FundProv> fundProvList;

	//注入FundProvBo
	private FundProvBo fundProvBo;

	private String query;
	private String provNo;		
	private FormData formfdprov0001;
	private FormData formfdprov0002;
	private FormService formService = new FormService();
	
	public FundProvAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdprov0001 = formService.getFormData("fdprov0001");
		fundProv = new FundProv();
		getFormValue(formfdprov0001);
		setObjValue(formfdprov0001, fundProv);
		Ipage ipage = this.getIpage();
		fundProvList = (List) fundProvBo.findByPage(ipage, fundProv).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdprov0002 = formService.getFormData("fdprov0002");
		fundProv = new FundProv();
		fundProv.setOpNo(User.getLoginid(this.getHttpRequest()));
		getObjValue(formfdprov0002, fundProv);
		this.changeFormProperty(formfdprov0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdprov0002 = formService.getFormData("fdprov0002");
		getFormValue(formfdprov0002);
		fundProv = new FundProv();
		setObjValue(formfdprov0002, fundProv);
		fundProvBo.insert(fundProv);
		this.addActionMessage("新增成功");
		query="query";
		getObjValue(formfdprov0002, fundProv);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdprov0002 = formService.getFormData("fdprov0002");
		getFormValue(formfdprov0002);
		fundProv = new FundProv();
		setObjValue(formfdprov0002, fundProv);
		fundProvBo.update(fundProv);
		this.addActionMessage("修改成功");
		query="query";
		getObjValue(formfdprov0002, fundProv);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdprov0001 = formService.getFormData("fdprov0001");
		fundProv = new FundProv();
		fundProv.setProvNo(provNo);
		fundProvBo.del(fundProv);
		this.addActionMessage("删除成功");
		fundProv = new FundProv();
		Ipage ipage = this.getIpage();
		fundProvList = (List) fundProvBo.findByPage(ipage, fundProv).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdprov0002 = formService.getFormData("fdprov0002");
		fundProv = new FundProv();
		fundProv.setProvNo(provNo);
		fundProv = fundProvBo.getById(fundProv);
		getObjValue(formfdprov0002, fundProv);
		this.changeFormProperty(formfdprov0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdprov0002 = formService.getFormData("fdprov0002");
		 getFormValue(formfdprov0002);
		 validateFormData(formfdprov0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdprov0002 = formService.getFormData("fdprov0002");
		 getFormValue(formfdprov0002);
		 validateFormData(formfdprov0002);
  	}
	public FundProv getFundProv() {
		return fundProv;
	}
	public void setFundProv(FundProv  fundProv) {
		this.fundProv = fundProv;
	}
	public List<FundProv> getFundProvList() {
		return fundProvList;
	}
	public void setFundProvList(List<FundProv> fundProvList) {
		this.fundProvList = fundProvList;
	}
	public FundProvBo getFundProvBo() {
		return fundProvBo;
	}
	public void setFundProvBo(FundProvBo fundProvBo) {
		this.fundProvBo = fundProvBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdprov0002() {
		return formfdprov0002;
	}
	public void setFormfdprov0002(FormData formfdprov0002) {
		this.formfdprov0002 = formfdprov0002;
	}
	public FormData getFormfdprov0001() {
		return formfdprov0001;
	}
	public void setFormfdprov0001(FormData formfdprov0001) {
		this.formfdprov0001 = formfdprov0001;
	}
	public void setProvNo(String provNo){
		this.provNo = provNo;
	}		
	public String getProvNo(){
		return provNo;
	}
}