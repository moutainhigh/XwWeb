package  app.creditapp.inf.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.inf.bo.WsRepyFineBo;
import app.creditapp.inf.entity.WsRepyFine;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WsRepyFineAction.java
 * Description:
 **/
public class WsRepyFineAction extends BaseFormBean {

	//页面传值
	private WsRepyFine wsRepyFine;
	private List<WsRepyFine> wsRepyFineList;

	//注入WsRepyFineBo
	private WsRepyFineBo wsRepyFineBo;

	private String query;
	private String wsId;
	private FormData formrepyfine0001;
	private FormData formrepyfine0002;
	private FormService formService = new FormService();
	
	public WsRepyFineAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formrepyfine0001 = formService.getFormData("repyfine0001");
		wsRepyFine = new WsRepyFine();
		getFormValue(formrepyfine0001);
		setObjValue(formrepyfine0001, wsRepyFine);
		wsRepyFine.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsRepyFineList = (List) wsRepyFineBo.findByPage(ipage, wsRepyFine).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrepyfine0002 = formService.getFormData("repyfine0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrepyfine0002 = formService.getFormData("repyfine0002");
		getFormValue(formrepyfine0002);
		wsRepyFine = new WsRepyFine();
		setObjValue(formrepyfine0002, wsRepyFine);
		wsRepyFine.setBrNo(User.getBrno(this.getHttpRequest()));
		wsRepyFineBo.insert(wsRepyFine);
		getObjValue(formrepyfine0002, wsRepyFine);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrepyfine0002 = formService.getFormData("repyfine0002");
		getFormValue(formrepyfine0002);
		wsRepyFine = new WsRepyFine();
		setObjValue(formrepyfine0002, wsRepyFine);
		wsRepyFineBo.update(wsRepyFine);
		getObjValue(formrepyfine0002, wsRepyFine);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formrepyfine0001 = formService.getFormData("repyfine0001");
		wsRepyFine = new WsRepyFine();
		wsRepyFine.setWsId(wsId);
		wsRepyFineBo.del(wsRepyFine);
		this.addActionMessage("删除成功");
		wsRepyFine = new WsRepyFine();
		wsRepyFine.setBrNo(User.getBrno(this.getHttpRequest()));
		Ipage ipage = this.getIpage();
		wsRepyFineList = (List) wsRepyFineBo.findByPage(ipage, wsRepyFine).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formrepyfine0002 = formService.getFormData("repyfine0002");
		wsRepyFine = new WsRepyFine();
		wsRepyFine.setWsId(wsId);
		wsRepyFine = wsRepyFineBo.getById(wsRepyFine);
		getObjValue(formrepyfine0002, wsRepyFine);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formrepyfine0002 = formService.getFormData("repyfine0002");
		 getFormValue(formrepyfine0002);
		 validateFormData(formrepyfine0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formrepyfine0002 = formService.getFormData("repyfine0002");
		 getFormValue(formrepyfine0002);
		 validateFormData(formrepyfine0002);
  	}
	public WsRepyFine getWsRepyFine() {
		return wsRepyFine;
	}
	public void setWsRepyFine(WsRepyFine  wsRepyFine) {
		this.wsRepyFine = wsRepyFine;
	}
	public List<WsRepyFine> getWsRepyFineList() {
		return wsRepyFineList;
	}
	public void setWsRepyFineList(List<WsRepyFine> wsRepyFineList) {
		this.wsRepyFineList = wsRepyFineList;
	}
	public WsRepyFineBo getWsRepyFineBo() {
		return wsRepyFineBo;
	}
	public void setWsRepyFineBo(WsRepyFineBo wsRepyFineBo) {
		this.wsRepyFineBo = wsRepyFineBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormrepyfine0002() {
		return formrepyfine0002;
	}
	public void setFormrepyfine0002(FormData formrepyfine0002) {
		this.formrepyfine0002 = formrepyfine0002;
	}
	public FormData getFormrepyfine0001() {
		return formrepyfine0001;
	}
	public void setFormrepyfine0001(FormData formrepyfine0001) {
		this.formrepyfine0001 = formrepyfine0001;
	}
	public void setWsId(String wsId){
		this.wsId = wsId;
	}		
	public String getWsId(){
		return wsId;
	}
}