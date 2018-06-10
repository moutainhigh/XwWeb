package  app.creditapp.sec.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.sec.bo.LoginInfoSummaryBO;
import app.creditapp.sec.bo.UserApptimeBo;
import app.creditapp.sec.bo.UserMarkInfoBo;
import app.creditapp.sec.entity.LoginInfoSummary;
import app.creditapp.sec.entity.UserApptime;
import app.creditapp.sec.entity.UserMarkInfo;
import app.creditapp.sys.bo.SysLoginLogBo;
import app.creditapp.sys.entity.SysLoginLog;
import app.util.LoginSessionListener;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
* Title: UserMarkInfoAction.java
* Description:
**/

public class UserMarkInfoAction extends BaseFormBean {

	//页面传值
	private UserMarkInfo userMarkInfo;
	private List<UserMarkInfo> userMarkInfoList;
	private SysLoginLog sysloginlog;
	private List<SysLoginLog> sysloginlogByUserList;
	//注入UserMarkInfoBo
	private UserMarkInfoBo userMarkInfoBo;
	private SysLoginLogBo sysLoginLogBo;
	private UserApptimeBo userApptimeBo;
	private LoginInfoSummaryBO logininfosummarybo;
	
	private LoginInfoSummary loginInfoSummary;
	private String userId;
	private String query;
	private String view;
	private String passwordState;
	private FormData formsec0012;
	private FormData formsec0011;
	private FormData formsec0010;
	private  FormService formService = new FormService();
	
	public UserMarkInfoAction() {
		formsec0012 = formService.getFormData("sec0012");
		formsec0011 = formService.getFormData("sec0011");
		formsec0010 = formService.getFormData("sec0010");
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
		userMarkInfo = new UserMarkInfo();
		getFormValue(formsec0010);
		setObjValue(formsec0010, userMarkInfo);
		Ipage ipage = this.getIpage();
		userMarkInfoList = (List) userMarkInfoBo.findByPage(ipage, userMarkInfo).getResult();
		return "list";
	}
	 public String findByPageByUserId() throws Exception {
			ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
			sysloginlog=new SysLoginLog();
			sysloginlog.setOpNo(userId);
			Ipage ipage1 = this.getIpage();
			sysloginlogByUserList=(List)sysLoginLogBo.findByPage(ipage1, sysloginlog).getResult();
			if(sysloginlogByUserList.size()>0){
				sysloginlog.setBrNo(sysloginlogByUserList.get(0).getBrNo());
				sysloginlog.setOpName(sysloginlogByUserList.get(0).getOpName());
			}
			getObjValue(formsec0012, sysloginlog);
			getFormValue(formsec0012);
			setObjValue(formsec0012, sysloginlog);
			query = "query";
			return "userIdlist";
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
		getFormValue(formsec0011);
		userMarkInfo = new UserMarkInfo();
		setObjValue(formsec0011, userMarkInfo);
//		userMarkInfoBo.insert(userMarkInfo);
		getObjValue(formsec0011, userMarkInfo);
		userId= userMarkInfo.getUserId();
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
		getFormValue(formsec0011);
		userMarkInfo = new UserMarkInfo();
		setObjValue(formsec0011, userMarkInfo);
//		userMarkInfoBo.update(userMarkInfo);
		getObjValue(formsec0011, userMarkInfo);
		userId= userMarkInfo.getUserId();
		query = "query";
		return "detail";
	}
	
	public String updateSts() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		userMarkInfo = new UserMarkInfo();
		userMarkInfo.setUserId(userId);
		if( "1".equals(passwordState) ){
			userMarkInfo.setPasswordState("0");
		}else if( "0".equals(passwordState) ){
			userMarkInfo.setPasswordState("1");
		}
		userMarkInfoBo.updateSts(userMarkInfo);
		return findByPage();
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		userMarkInfoBo.del(userId);
		this.addActionMessage("删除成功");
		userMarkInfo = new UserMarkInfo();
		Ipage ipage = this.getIpage();
		userMarkInfoList = (List) userMarkInfoBo.findByPage(ipage, userMarkInfo).getResult();
		return "list";
	}

	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		userMarkInfo = userMarkInfoBo.getById(userId);
		getObjValue(formsec0011, userMarkInfo);
		userId= userMarkInfo.getUserId();
		if(view.equals("view")){
			 query = "query";
		 }
		return "detail";
	}
	/**
	 * 登录情况日统计查询操作
	 * @return
	 * @throws Exception
	 */
	public String getAllLogin() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		loginInfoSummary = logininfosummarybo.getSummaryInfo(User.getSys_date(ServletActionContext.getRequest()));
		loginInfoSummary.setOnlineUserCount(LoginSessionListener.hUserName.size()) ;
		UserApptime apptime = new UserApptime();
		apptime.setBelongDate(User.getSys_date(ServletActionContext.getRequest()));
		loginInfoSummary.setUserAppCount(userApptimeBo.allFindByPage(getIpage(), apptime).getPageCounts());
		if(view.equals("view")){
			 query = "query";
		 }
		getObjValue(formsec0011, loginInfoSummary);
		return "allLogin";
	}
	/**
	 * 登录情况当日查询操作
	 * @return
	 * @throws Exception
	 */
	public String getDayLogin() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		String login = app.util.DateUtil.getDate();
		userMarkInfo = userMarkInfoBo.getAllLogin(login);
		getObjValue(formsec0011, userMarkInfo);
		if(view.equals("view")){
			 query = "query";
		 }
		return "allLogin";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formsec0011);
		 validateFormData(formsec0011);
   }
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formsec0011);
		 validateFormData(formsec0011);
  }
	
	/**
	 * 登录情况当日查询操作
	 * @return
	 * @throws Exception
	 */
	public String replay() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		userMarkInfoBo.replay(userId);
		Ipage ipage = this.getIpage();
		userMarkInfoList = (List) userMarkInfoBo.findByPage(ipage, userMarkInfo).getResult();
		return "list";
	}
	public UserMarkInfo getUserMarkInfo() {
		return userMarkInfo;
	}
	public void setUserMarkInfo(UserMarkInfo  userMarkInfo) {
		this.userMarkInfo = userMarkInfo;
	}
	public List<UserMarkInfo> getUserMarkInfoList() {
		return userMarkInfoList;
	}
	public void setUserMarkInfoList(List<UserMarkInfo> userMarkInfoList) {
		this.userMarkInfoList = userMarkInfoList;
	}
	public UserMarkInfoBo getUserMarkInfoBo() {
		return userMarkInfoBo;
	}
	public void setUserMarkInfoBo(UserMarkInfoBo userMarkInfoBo) {
		this.userMarkInfoBo = userMarkInfoBo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public FormData getFormsec0011() {
		return formsec0011;
	}
	public FormData getFormsec0012() {
		return formsec0012;
	}
	public void setFormsec0012(FormData formsec0012) {
		this.formsec0012 = formsec0012;
	}
	public void setFormsec0011(FormData formsec0011) {
		this.formsec0011 = formsec0011;
	}
	public FormData getFormsec0010() {
		return formsec0010;
	}
	public void setFormsec0010(FormData formsec0010) {
		this.formsec0010 = formsec0010;
	}
	public List<SysLoginLog> getSysloginlogByUserList() {
		return sysloginlogByUserList;
	}
	public void setSysloginlogByUserList(List<SysLoginLog> sysloginlogByUserList) {
		this.sysloginlogByUserList = sysloginlogByUserList;
	}
	public SysLoginLog getSysloginlog() {
		return sysloginlog;
	}
	public void setSysloginlog(SysLoginLog sysloginlog) {
		this.sysloginlog = sysloginlog;
	}
	public SysLoginLogBo getSysLoginLogBo() {
		return sysLoginLogBo;
	}
	public void setSysLoginLogBo(SysLoginLogBo sysLoginLogBo) {
		this.sysLoginLogBo = sysLoginLogBo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public LoginInfoSummaryBO getLogininfosummarybo() {
		return logininfosummarybo;
	}
	public void setLogininfosummarybo(LoginInfoSummaryBO logininfosummarybo) {
		this.logininfosummarybo = logininfosummarybo;
	}
	public LoginInfoSummary getLoginInfoSummary() {
		return loginInfoSummary;
	}
	public void setLoginInfoSummary(LoginInfoSummary loginInfoSummary) {
		this.loginInfoSummary = loginInfoSummary;
	}
	public UserApptimeBo getUserApptimeBo() {
		return userApptimeBo;
	}
	public String getPasswordState() {
		return passwordState;
	}
	public void setPasswordState(String passwordState) {
		this.passwordState = passwordState;
	}
	public void setUserApptimeBo(UserApptimeBo userApptimeBo) {
		this.userApptimeBo = userApptimeBo;
	}
}
