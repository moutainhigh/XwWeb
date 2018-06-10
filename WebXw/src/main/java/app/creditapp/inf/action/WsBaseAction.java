package  app.creditapp.inf.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.inf.bo.WsBaseBo;
import app.creditapp.inf.entity.WsBase;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WsBaseAction.java
 * Description:
 **/
public class WsBaseAction extends BaseFormBean {

	//页面传值
	private WsBase wsBase;
	private List<WsBase> wsBaseList;

	//注入WsBaseBo
	private WsBaseBo wsBaseBo;

	private String query;
	private String wsBaseId;		
	private FormData forminf0007;
	private FormData forminf0008;
	private FormService formService = new FormService();
	
	public WsBaseAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0007 = formService.getFormData("inf0007");
		wsBase = new WsBase();
		getFormValue(forminf0007);
		setObjValue(forminf0007, wsBase);
		Ipage ipage = this.getIpage();
		wsBaseList = (List) wsBaseBo.findByPage(ipage, wsBase).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0008 = formService.getFormData("inf0008");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0008 = formService.getFormData("inf0008");
		getFormValue(forminf0008);
		wsBase = new WsBase();
		setObjValue(forminf0008, wsBase);
		wsBaseBo.insert(wsBase);
		getObjValue(forminf0008, wsBase);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0008 = formService.getFormData("inf0008");
		getFormValue(forminf0008);
		wsBase = new WsBase();
		setObjValue(forminf0008, wsBase);
		wsBaseBo.update(wsBase);
		getObjValue(forminf0008, wsBase);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		forminf0007 = formService.getFormData("inf0007");
		wsBase = new WsBase();
		wsBase.setWsBaseId(wsBaseId);
		wsBaseBo.del(wsBase);
		this.addActionMessage("删除成功");
		wsBase = new WsBase();
		Ipage ipage = this.getIpage();
		wsBaseList = (List) wsBaseBo.findByPage(ipage, wsBase).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		forminf0008 = formService.getFormData("inf0008");
		wsBase = new WsBase();
		wsBase.setWsBaseId(wsBaseId);
		wsBase = wsBaseBo.getById(wsBase);
		getObjValue(forminf0008, wsBase);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0008 = formService.getFormData("inf0008");
		 getFormValue(forminf0008);
		 validateFormData(forminf0008);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 forminf0008 = formService.getFormData("inf0008");
		 getFormValue(forminf0008);
		 validateFormData(forminf0008);
  	}
	public WsBase getWsBase() {
		return wsBase;
	}
	public void setWsBase(WsBase  wsBase) {
		this.wsBase = wsBase;
	}
	public List<WsBase> getWsBaseList() {
		return wsBaseList;
	}
	public void setWsBaseList(List<WsBase> wsBaseList) {
		this.wsBaseList = wsBaseList;
	}
	public WsBaseBo getWsBaseBo() {
		return wsBaseBo;
	}
	public void setWsBaseBo(WsBaseBo wsBaseBo) {
		this.wsBaseBo = wsBaseBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getForminf0008() {
		return forminf0008;
	}
	public void setForminf0008(FormData forminf0008) {
		this.forminf0008 = forminf0008;
	}
	public FormData getForminf0007() {
		return forminf0007;
	}
	public void setForminf0007(FormData forminf0007) {
		this.forminf0007 = forminf0007;
	}
	public void setWsBaseId(String wsBaseId){
		this.wsBaseId = wsBaseId;
	}		
	public String getWsBaseId(){
		return wsBaseId;
	}
}