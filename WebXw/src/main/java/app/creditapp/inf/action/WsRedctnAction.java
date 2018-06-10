package  app.creditapp.inf.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.inf.bo.WsRedctnBo;
import app.creditapp.inf.entity.WsRedctn;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WsRedctnAction.java
 * Description:
 **/
public class WsRedctnAction extends BaseFormBean {

	//页面传值
	private WsRedctn wsRedctn;
	private List<WsRedctn> wsRedctnList;

	//注入WsRedctnBo
	private WsRedctnBo wsRedctnBo;

	private String query;
	private String wsId;
	private String batchNo;
	private FormData forminf0530;
	private FormData forminf0531;
	private FormService formService = new FormService();
	
	public WsRedctnAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0530 = formService.getFormData("inf0530");
		wsRedctn = new WsRedctn();
		getFormValue(forminf0530);
		setObjValue(forminf0530, wsRedctn);
		//wsRedctn.setBrNo(User.getBrno(this.getHttpRequest()));
		wsRedctn.setBatchNo(batchNo);
		Ipage ipage = this.getIpage();
		wsRedctnList = (List) wsRedctnBo.findByPage(ipage, wsRedctn).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0531 = formService.getFormData("inf0531");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0531 = formService.getFormData("inf0531");
		getFormValue(forminf0531);
		wsRedctn = new WsRedctn();
		setObjValue(forminf0531, wsRedctn);
		wsRedctn.setBrNo(User.getBrno(this.getHttpRequest()));
		wsRedctn.setTxDate(User.getSys_date(this.getHttpRequest()));
		wsRedctnBo.insert(wsRedctn);
		getObjValue(forminf0531, wsRedctn);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0531 = formService.getFormData("inf0531");
		getFormValue(forminf0531);
		wsRedctn = new WsRedctn();
		setObjValue(forminf0531, wsRedctn);
		wsRedctnBo.update(wsRedctn);
		getObjValue(forminf0531, wsRedctn);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0530 = formService.getFormData("inf0530");
		wsRedctn = new WsRedctn();
		wsRedctn.setWsId(wsId);
		wsRedctnBo.del(wsRedctn);
		this.addActionMessage("删除成功");
		wsRedctn = new WsRedctn();
		wsRedctn.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsRedctnList = (List) wsRedctnBo.findByPage(ipage, wsRedctn).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0531 = formService.getFormData("inf0531");
		wsRedctn = new WsRedctn();
		wsRedctn.setWsId(wsId);
		wsRedctn = wsRedctnBo.getById(wsRedctn);
		getObjValue(forminf0531, wsRedctn);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0531 = formService.getFormData("inf0531");
		 getFormValue(forminf0531);
		 validateFormData(forminf0531);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0531 = formService.getFormData("inf0531");
		 getFormValue(forminf0531);
		 validateFormData(forminf0531);
  	}
	public WsRedctn getWsRedctn() {
		return wsRedctn;
	}
	public void setWsRedctn(WsRedctn  wsRedctn) {
		this.wsRedctn = wsRedctn;
	}
	public List<WsRedctn> getWsRedctnList() {
		return wsRedctnList;
	}
	public void setWsRedctnList(List<WsRedctn> wsRedctnList) {
		this.wsRedctnList = wsRedctnList;
	}
	public WsRedctnBo getWsRedctnBo() {
		return wsRedctnBo;
	}
	public void setWsRedctnBo(WsRedctnBo wsRedctnBo) {
		this.wsRedctnBo = wsRedctnBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getForminf0531() {
		return forminf0531;
	}
	public void setForminf0531(FormData forminf0531) {
		this.forminf0531 = forminf0531;
	}
	public FormData getForminf0530() {
		return forminf0530;
	}
	public void setForminf0530(FormData forminf0530) {
		this.forminf0530 = forminf0530;
	}
	public void setWsId(String wsId){
		this.wsId = wsId;
	}		
	public String getWsId(){
		return wsId;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
}