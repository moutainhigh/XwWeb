package  app.creditapp.acc.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.acc.bo.WsRepyFeeBo;
import app.creditapp.acc.entity.WsRepyFee;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WsRepyFeeAction.java
 * Description:
 **/
public class WsRepyFeeAction extends BaseFormBean {

	//页面传值
	private WsRepyFee wsRepyFee;
	private List<WsRepyFee> wsRepyFeeList;

	//注入WsRepyFeeBo
	private WsRepyFeeBo wsRepyFeeBo;

	private String query;
	private String wsId;		
	private FormData formacc9999;
	private FormData formacc9998;
	private FormService formService = new FormService();
	
	public WsRepyFeeAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formacc9999 = formService.getFormData("acc9999");
		wsRepyFee = new WsRepyFee();
		getFormValue(formacc9999);
		setObjValue(formacc9999, wsRepyFee);
		wsRepyFee.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsRepyFeeList = (List) wsRepyFeeBo.findByPage(ipage, wsRepyFee).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc9998 = formService.getFormData("acc9998");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc9998 = formService.getFormData("acc9998");
		getFormValue(formacc9998);
		wsRepyFee = new WsRepyFee();
		setObjValue(formacc9998, wsRepyFee);
		wsRepyFee.setBrNo(User.getBrno(this.getHttpRequest()));
		wsRepyFeeBo.insert(wsRepyFee);
		getObjValue(formacc9998, wsRepyFee);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc9998 = formService.getFormData("acc9998");
		getFormValue(formacc9998);
		wsRepyFee = new WsRepyFee();
		setObjValue(formacc9998, wsRepyFee);
		wsRepyFeeBo.update(wsRepyFee);
		getObjValue(formacc9998, wsRepyFee);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formacc9999 = formService.getFormData("acc9999");
		wsRepyFee = new WsRepyFee();
		wsRepyFee.setWsId(wsId);
		wsRepyFeeBo.del(wsRepyFee);
		this.addActionMessage("删除成功");
		wsRepyFee = new WsRepyFee();
		wsRepyFee.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsRepyFeeList = (List) wsRepyFeeBo.findByPage(ipage, wsRepyFee).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
//	public String getById() throws Exception {
//		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		formacc9998 = formService.getFormData("acc9998");
//		wsRepyFee = new WsRepyFee();
//		wsRepyFee.setWsId(wsId);
//		wsRepyFee = wsRepyFeeBo.getById(wsRepyFee);
//		getObjValue(formacc9998, wsRepyFee);
//		return "detail";
//	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacc9998 = formService.getFormData("acc9998");
		 getFormValue(formacc9998);
		 validateFormData(formacc9998);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formacc9998 = formService.getFormData("acc9998");
		 getFormValue(formacc9998);
		 validateFormData(formacc9998);
  	}
	public WsRepyFee getWsRepyFee() {
		return wsRepyFee;
	}
	public void setWsRepyFee(WsRepyFee  wsRepyFee) {
		this.wsRepyFee = wsRepyFee;
	}
	public List<WsRepyFee> getWsRepyFeeList() {
		return wsRepyFeeList;
	}
	public void setWsRepyFeeList(List<WsRepyFee> wsRepyFeeList) {
		this.wsRepyFeeList = wsRepyFeeList;
	}
	public WsRepyFeeBo getWsRepyFeeBo() {
		return wsRepyFeeBo;
	}
	public void setWsRepyFeeBo(WsRepyFeeBo wsRepyFeeBo) {
		this.wsRepyFeeBo = wsRepyFeeBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormacc9998() {
		return formacc9998;
	}
	public void setFormacc9998(FormData formacc9998) {
		this.formacc9998 = formacc9998;
	}
	public FormData getFormacc9999() {
		return formacc9999;
	}
	public void setFormacc9999(FormData formacc9999) {
		this.formacc9999 = formacc9999;
	}
	public void setWsId(String wsId){
		this.wsId = wsId;
	}		
	public String getWsId(){
		return wsId;
	}
}