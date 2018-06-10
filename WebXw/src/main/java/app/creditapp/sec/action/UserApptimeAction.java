package  app.creditapp.sec.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.sec.bo.UserApptimeBo;
import app.creditapp.sec.entity.UserApptime;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;


/**
* Title: UserApptimeAction.java
* Description:
**/

public class UserApptimeAction extends BaseFormBean {

	//页面传值
	private UserApptime userApptime;
	private List<UserApptime> userApptimeList;

	//注入UserApptimeBo
	private UserApptimeBo userApptimeBo;

	private long id;
	private String query;
	private String view;
	private FormData formsec0032;
	private FormData formsec0031;
	private FormData formsec0033;
	
	private  FormService formService = new FormService();
	
	public UserApptimeAction() {
		formsec0032 = formService.getFormData("sec0032");
		formsec0031 = formService.getFormData("sec0031");
		formsec0033 = formService.getFormData("sec0033");
		query = "";
		view = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	 public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		userApptime = new UserApptime();
		getFormValue(formsec0031);
		setObjValue(formsec0031, userApptime);
		Ipage ipage = this.getIpage();
		userApptimeList = (List) userApptimeBo.findByPage(ipage, userApptime).getResult();
		return "list";
	}
	
	/**
	 * 新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formsec0032);
		userApptime = new UserApptime();
		setObjValue(formsec0032, userApptime);
		userApptimeBo.insert(userApptime);
		getObjValue(formsec0032, userApptime);
		id= userApptime.getId();
		query = "query";
		return "detail";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formsec0032);
		userApptime = new UserApptime();
		setObjValue(formsec0032, userApptime);
		userApptimeBo.update(userApptime);
		getObjValue(formsec0032, userApptime);
		id= userApptime.getId();
		query = "query";
		return "detail";
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		userApptimeBo.del(id);
		this.addActionMessage("删除成功");
		userApptime = new UserApptime();
		Ipage ipage = this.getIpage();
		userApptimeList = (List) userApptimeBo.findByPage(ipage, userApptime).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		userApptime = userApptimeBo.getById(id);
		getObjValue(formsec0032, userApptime);
		id= userApptime.getId();
		if(view.equals("view")){
			 query = "query";
		 }
		return "detail";
	}
	/**
	 * action汇总查询
	 * @return
	 * @throws Exception
	 */
	 public String allFindByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		userApptime = new UserApptime();
		getFormValue(formsec0033);
		setObjValue(formsec0033, userApptime);
		Ipage ipage = this.getIpage();
		userApptimeList = (List) userApptimeBo.allFindByPage(ipage, userApptime).getResult();
		return "alllist";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formsec0032);
		 validateFormData(formsec0032);
   }
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formsec0032);
		 validateFormData(formsec0032);
  }
	public UserApptime getUserApptime() {
		return userApptime;
	}
	public void setUserApptime(UserApptime  userApptime) {
		this.userApptime = userApptime;
	}
	public List<UserApptime> getUserApptimeList() {
		return userApptimeList;
	}
	public void setUserApptimeList(List<UserApptime> userApptimeList) {
		this.userApptimeList = userApptimeList;
	}
	public UserApptimeBo getUserApptimeBo() {
		return userApptimeBo;
	}
	public void setUserApptimeBo(UserApptimeBo userApptimeBo) {
		this.userApptimeBo = userApptimeBo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public FormData getFormsec0032() {
		return formsec0032;
	}
	public void setFormsec0032(FormData formsec0032) {
		this.formsec0032 = formsec0032;
	}
	public FormData getFormsec0031() {
		return formsec0031;
	}
	public void setFormsec0031(FormData formsec0031) {
		this.formsec0031 = formsec0031;
	}
	public FormData getFormsec0033() {
		return formsec0033;
	}
	public void setFormsec0033(FormData formsec0033) {
		this.formsec0033 = formsec0033;
	}
}
