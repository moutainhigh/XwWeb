package  app.creditapp.inf.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.inf.bo.WsElyRepyBatchBo;
import app.creditapp.inf.entity.WsElyRepyBatch;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WsElyRepyBatchAction.java
 * Description:
 **/
public class WsElyRepyBatchAction extends BaseFormBean {

	//页面传值
	private WsElyRepyBatch wsElyRepyBatch;
	private List<WsElyRepyBatch> wsElyRepyBatchList;

	//注入WsElyRepyBatchBo
	private WsElyRepyBatchBo wsElyRepyBatchBo;

	private String query;
	private String batchNo;		
	private FormData forminf;
	private FormData forminf0069;
	private FormService formService = new FormService();
	
	public WsElyRepyBatchAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf = formService.getFormData("inf0068");
		wsElyRepyBatch = new WsElyRepyBatch();
		getFormValue(forminf);
		setObjValue(forminf, wsElyRepyBatch);
		Ipage ipage = this.getIpage();
		wsElyRepyBatchList = (List) wsElyRepyBatchBo.findByPage(ipage, wsElyRepyBatch).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0069 = formService.getFormData("inf0069");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0069 = formService.getFormData("inf0069");
		getFormValue(forminf0069);
		wsElyRepyBatch = new WsElyRepyBatch();
		setObjValue(forminf0069, wsElyRepyBatch);
		wsElyRepyBatch.setBrNo(User.getBrno(this.getHttpRequest()));
		wsElyRepyBatchBo.insert(wsElyRepyBatch);
		getObjValue(forminf0069, wsElyRepyBatch);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0069 = formService.getFormData("inf0069");
		getFormValue(forminf0069);
		wsElyRepyBatch = new WsElyRepyBatch();
		setObjValue(forminf0069, wsElyRepyBatch);
		wsElyRepyBatchBo.update(wsElyRepyBatch);
		getObjValue(forminf0069, wsElyRepyBatch);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf = formService.getFormData("inf0068");
		wsElyRepyBatch = new WsElyRepyBatch();
		wsElyRepyBatch.setBatchNo(batchNo);
		wsElyRepyBatchBo.del(wsElyRepyBatch);
		this.addActionMessage("删除成功");
		wsElyRepyBatch = new WsElyRepyBatch();
		wsElyRepyBatch.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsElyRepyBatchList = (List) wsElyRepyBatchBo.findByPage(ipage, wsElyRepyBatch).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0069 = formService.getFormData("inf0069");
		wsElyRepyBatch = new WsElyRepyBatch();
		wsElyRepyBatch.setBatchNo(batchNo);
		wsElyRepyBatch = wsElyRepyBatchBo.getById(wsElyRepyBatch);
		getObjValue(forminf0069, wsElyRepyBatch);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0069 = formService.getFormData("inf0069");
		 getFormValue(forminf0069);
		 validateFormData(forminf0069);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0069 = formService.getFormData("inf0069");
		 getFormValue(forminf0069);
		 validateFormData(forminf0069);
  	}
	public WsElyRepyBatch getWsElyRepyBatch() {
		return wsElyRepyBatch;
	}
	public void setWsElyRepyBatch(WsElyRepyBatch  wsElyRepyBatch) {
		this.wsElyRepyBatch = wsElyRepyBatch;
	}
	public List<WsElyRepyBatch> getWsElyRepyBatchList() {
		return wsElyRepyBatchList;
	}
	public void setWsElyRepyBatchList(List<WsElyRepyBatch> wsElyRepyBatchList) {
		this.wsElyRepyBatchList = wsElyRepyBatchList;
	}
	public WsElyRepyBatchBo getWsElyRepyBatchBo() {
		return wsElyRepyBatchBo;
	}
	public void setWsElyRepyBatchBo(WsElyRepyBatchBo wsElyRepyBatchBo) {
		this.wsElyRepyBatchBo = wsElyRepyBatchBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getForminf0069() {
		return forminf0069;
	}
	public void setForminf0069(FormData forminf0069) {
		this.forminf0069 = forminf0069;
	}
	public FormData getForminf() {
		return forminf;
	}
	public void setForminf(FormData forminf) {
		this.forminf = forminf;
	}
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}		
	public String getBatchNo(){
		return batchNo;
	}
}