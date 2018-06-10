package  app.creditapp.inf.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.inf.bo.WsRepyPlanBo;
import app.creditapp.inf.entity.WsRepyPlan;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WsRepyPlanAction.java
 * Description:
 **/
public class WsRepyPlanAction extends BaseFormBean {

	//页面传值
	private WsRepyPlan wsRepyPlan;
	private List<WsRepyPlan> wsRepyPlanList;

	//注入WsRepyPlanBo
	private WsRepyPlanBo wsRepyPlanBo;

	private String query;
	private String wsId;		
	private FormData forminf0003;
	private FormData forminf0004;
	private String batchNo;	
	private FormService formService = new FormService();
	
	public WsRepyPlanAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0003 = formService.getFormData("inf0003");
		wsRepyPlan = new WsRepyPlan();
		getFormValue(forminf0003);
		setObjValue(forminf0003, wsRepyPlan);
		//wsRepyPlan.setBrNo(User.getBrno(this.getHttpRequest()));
		wsRepyPlan.setBatchNo(batchNo);
		Ipage ipage = this.getIpage();
		wsRepyPlanList = (List) wsRepyPlanBo.findByPage(ipage, wsRepyPlan).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0004 = formService.getFormData("inf0004");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0004 = formService.getFormData("inf0004");
		getFormValue(forminf0004);
		wsRepyPlan = new WsRepyPlan();
		setObjValue(forminf0004, wsRepyPlan);
		wsRepyPlan.setBrNo(User.getBrno(this.getHttpRequest()));
		wsRepyPlanBo.insert(wsRepyPlan);
		getObjValue(forminf0004, wsRepyPlan);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0004 = formService.getFormData("inf0004");
		getFormValue(forminf0004);
		wsRepyPlan = new WsRepyPlan();
		setObjValue(forminf0004, wsRepyPlan);
		wsRepyPlanBo.update(wsRepyPlan);
		getObjValue(forminf0004, wsRepyPlan);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0003 = formService.getFormData("inf0003");
		wsRepyPlan = new WsRepyPlan();
		wsRepyPlan.setWsId(wsId);
		wsRepyPlanBo.del(wsRepyPlan);
		this.addActionMessage("删除成功");
		wsRepyPlan = new WsRepyPlan();
		wsRepyPlan.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsRepyPlanList = (List) wsRepyPlanBo.findByPage(ipage, wsRepyPlan).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0004 = formService.getFormData("inf0004");
		wsRepyPlan = new WsRepyPlan();
		wsRepyPlan.setWsId(wsId);
		wsRepyPlan = wsRepyPlanBo.getById(wsRepyPlan);
		getObjValue(forminf0004, wsRepyPlan);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0004 = formService.getFormData("inf0004");
		 getFormValue(forminf0004);
		 validateFormData(forminf0004);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0004 = formService.getFormData("inf0004");
		 getFormValue(forminf0004);
		 validateFormData(forminf0004);
  	}
	public WsRepyPlan getWsRepyPlan() {
		return wsRepyPlan;
	}
	public void setWsRepyPlan(WsRepyPlan  wsRepyPlan) {
		this.wsRepyPlan = wsRepyPlan;
	}
	public List<WsRepyPlan> getWsRepyPlanList() {
		return wsRepyPlanList;
	}
	public void setWsRepyPlanList(List<WsRepyPlan> wsRepyPlanList) {
		this.wsRepyPlanList = wsRepyPlanList;
	}
	public WsRepyPlanBo getWsRepyPlanBo() {
		return wsRepyPlanBo;
	}
	public void setWsRepyPlanBo(WsRepyPlanBo wsRepyPlanBo) {
		this.wsRepyPlanBo = wsRepyPlanBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getForminf0004() {
		return forminf0004;
	}
	public void setForminf0004(FormData forminf0004) {
		this.forminf0004 = forminf0004;
	}
	public FormData getForminf0003() {
		return forminf0003;
	}
	public void setForminf0003(FormData forminf0003) {
		this.forminf0003 = forminf0003;
	}
	public void setWsId(String wsId){
		this.wsId = wsId;
	}		
	public String getWsId(){
		return wsId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
}