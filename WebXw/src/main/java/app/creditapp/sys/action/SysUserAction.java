package  app.creditapp.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.dbunit.util.Base64;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.opensymphony.xwork2.Action;

import app.base.SourceTemplate;
import app.creditapp.securityinterface.SecurityInterface;
import app.creditapp.sys.bo.PubNoteBo;
import app.creditapp.sys.bo.SysMenuBo;
import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.bo.SysUserRoleBo;
import app.creditapp.sys.entity.PubNote;
import app.creditapp.sys.entity.SysMenu;
import app.creditapp.sys.entity.SysUser;
import app.creditapp.sys.entity.SysUserRole;
import app.creditapp.wkf.bo.WkfApprovalRoleBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.util.User;
import app.util.toolkit.Ipage;

public class SysUserAction extends BaseFormBean{
	
	private static final long serialVersionUID = 8939604588603959956L;
	//页面传值
	private String menuJson;
	private String br_no;
	private SysUser op;
//	private SysOrgInfo sysOrgInfo;
	private SysUserRole sysUserRole;
	private String loadtoken;
	private String code;
	private String loginColor;
	private String loginMsg;
	private String appNoStr;
	private String changePWInfo;
	private List<SysUser> SysUserList;
	private List<PubNote> knlist;
	
	private String browserVersion;//判断从哪个登录入口登陆
	//注入SysUserBo
	private SysUserBo SysUserBo;
//	private SysOrgInfoBo sysOrgInfoBo;
	private SysUserRoleBo sysUserRoleBo;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private WkfApprovalRoleBo wkfApprovalRoleBo;
//	private ParmItemBo parmItemBo;
	private SysMenuBo sysMenuBo;
	//注入PubNoteBo
	private PubNoteBo pubNoteBo;
	
	private SecurityInterface securityInterface ;//XL安全审计调用接口

	private Map<String,String> menuTreeMap;
	private List<SysMenu> sysMenuLev1List;

	private String query;
	private String user_no;
	private SysUser SysUser;
	private List tabList;
	private FormData formsys0070;
	private FormData formsys0071;
	private FormData formsys0076;
	private FormData formaddr0001;
	private FormData formsys0099;
	private FormData formbid0003;
	private FormData formsys1070;
	private String user_name;
	private String duty;
	private String param;
	private FormService formService = new FormService();
	private String color;
	
	private String oldPassword;
	private String newPasword;
	private String role_no;
	private String info_id;
	private String isInsert;
	private String Period;
	
	public SysUserAction() {
		query = "";
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0070 = formService.getFormData("sys0070");
		SysUser = new SysUser();
		getFormValue(formsys0070);
		setObjValue(formsys0070, SysUser);
		Ipage ipage = this.getIpage();
		SysUserList = (List) SysUserBo.findByPage(ipage, SysUser).getResult();
		return "list";
	}
	
	
	/**
	 * 导航栏
	 */
	public String location() throws Exception{
		PubNote pubNote = new PubNote();
		pubNote.setContType("1");//在线编辑
		pubNote.setNoteType("8");
		knlist = pubNoteBo.getTop10(pubNote);
		return Action.SUCCESS;
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0071 = formService.getFormData("sys0071");
		SysUser = new SysUser();
		SysUser SysUser1 = new SysUser();
		SysUser1.setUser_no(User.getLoginid(ServletActionContext.getRequest()));
		SysUser1 = SysUserBo.getById(SysUser1);
		SysUser.setOrg_no("10000");
		SysUser.setTx_date(User.getSys_date(ServletActionContext.getRequest()));
		getObjValue(formsys0071, SysUser);
		return "input";
	}
	
	public String findByPageForPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0076 = formService.getFormData("sys0076");
		SysUser = new SysUser();
		getFormValue(formsys0076);
		setObjValue(formsys0076, SysUser);
		Ipage ipage = this.getIpage();
		SysUserList = (List) SysUserBo.findByPageForPop(ipage, SysUser).getResult();
		return "list";
	}	
	
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0071 = formService.getFormData("sys0071");
		getFormValue(formsys0071);
		SysUser = new SysUser();
		setObjValue(formsys0071, SysUser);
//		SysUser.setPass_word(Base64.encodeString(SysUser.getPass_word()));
		SysUser.setPass_word(Base64.encodeString("111111"));
		SysUser.setSkin("red");
		SysUser.setUp_date(User.getSys_date(ServletActionContext
				.getRequest()));
		SysUser.setTx_date(User.getSys_date(ServletActionContext
				.getRequest()));
		SysUser.setUser_sts("02");
		SysUserBo.insert(SysUser);
		this.addActionMessage("保存成功!");
		getObjValue(formsys0071, SysUser);
		SysUser.setUser_sts("01");
		isInsert = "yes";
		user_no = SysUser.getUser_no();
		return "list";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0071 = formService.getFormData("sys0071");
		getFormValue(formsys0071);
		SysUser = new SysUser();
		setObjValue(formsys0071, SysUser);
		String no=SysUser.getUser_no();
		SysUser SysUser1 = new SysUser();
		SysUser1.setUser_no(no);
		SysUser1 = SysUserBo.getById(SysUser1);
		SysUser.setUser_sts(SysUser1.getUser_sts());
//		SysUser.setPass_word(Base64.encodeString(SysUser.getPass_word()));
		SysUser.setUp_date(User.getSys_date(ServletActionContext
				.getRequest()));
		SysUserBo.update(SysUser);
//		SysUser.setPass_word(Base64.decodeToString(SysUser.getPass_word()));
		this.changeFormProperty(formsys0071,"user_no","readonly","1");
		this.changeFormProperty(formsys0071, "pass_word","fieldType", 99);
		getObjValue(formsys0071, SysUser);
		this.addActionMessage("保存成功!");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0070 = formService.getFormData("sys0070");
		SysUser = new SysUser();
		SysUser.setUser_no(user_no);
		SysUserBo.del(SysUser);
		this.addActionMessage("删除成功");
		SysUser = new SysUser();
		Ipage ipage = this.getIpage();
		SysUserList = (List) SysUserBo.findByPage(ipage, SysUser).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0071 = formService.getFormData("sys0071");
		SysUser = new SysUser();
		SysUser.setUser_no(user_no);
		SysUser.setUp_date(User.getSys_date(ServletActionContext.getRequest()));
		SysUser = SysUserBo.getById(SysUser);
//		SysUser.setPass_word(Base64.decodeToString(SysUser.getPass_word()));
		this.changeFormProperty(formsys0071,"user_no","readonly","1");
		this.changeFormProperty(formsys0071, "pass_word","fieldType", 99);
		getObjValue(formsys0071, SysUser);
		return "detail";
	}
	
	/**
	 * 启用
	 * @return
	 * @throws Exception
	 */
	public String active() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0070 = formService.getFormData("sys0070");
		SysUser = new SysUser();
		SysUser.setUser_no(user_no);
		SysUser = SysUserBo.getById(SysUser);
		SysUser.setUser_sts("01");
		SysUserBo.update(SysUser);
		this.addActionMessage("启用成功");
		SysUser = new SysUser();
		Ipage ipage = this.getIpage();
		SysUserList = (List) SysUserBo.findByPage(ipage, SysUser).getResult();
		return "list";
	}	
	/**
	 * 停用
	 * @return
	 * @throws Exception
	 */
	public String inactive() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0070 = formService.getFormData("sys0070");
		SysUser = new SysUser();
		SysUser.setUser_no(user_no);
		SysUser = SysUserBo.getById(SysUser);
		SysUser.setUser_sts("02");
		SysUserBo.update(SysUser);
		this.addActionMessage("停用成功");
		SysUser = new SysUser();
		Ipage ipage = this.getIpage();
		SysUserList = (List) SysUserBo.findByPage(ipage, SysUser).getResult();
		return "list";
	}	
	
	/**
	 * 工作流设计保存用户校验
	 * @param userId
	 * @param pwd
	 * @param brNo
	 * @return
	 * @throws Exception
	 */
	public String chkManagerPass(String userId,String pwd,String brNo) throws Exception {
		SysUserBo SysUserBo = (SysUserBo) SourceTemplate.getSpringContextInstance().getBean("SysUserBo");
		SysUser user = new SysUser();
		user.setUser_no(userId);
		user = SysUserBo.getById(user);
		 if(user!=null){
			 if(!user.getPass_word().equals(Base64.encodeString(pwd))){
				 return "wrongPass";
			 }else{
				 return "ok";
			 }
		 }else{
			 return "null";
		 }
	}
	
	public String changeSkin() throws IOException{
		this.getHttpResponse().setContentType("text/html;charset=UTF-8");
		op = new SysUser();
		op.setUser_no(User.getLoginid(this.getHttpRequest()));
		op.setSkin(color);
		SysUserBo.updateSkin(op);
		getHttpRequest().getSession(true).removeAttribute("color");//每次访问都要清空session中的color
		getHttpRequest().getSession(true).setAttribute("color",color);
		PrintWriter pw = this.getHttpResponse().getWriter();
		pw.write("success");
		return null;
	}
	
	/**
	 * @方法说明: 密码重置
	 * @return
	 * @throws Exception
	 * @return  String
	 * @throws
	 * @修改说明:
	 */
	public String resetPassword() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0070 = formService.getFormData("sys0070");
		SysUser = new SysUser();
		SysUser.setUser_no(user_no);
		SysUser = SysUserBo.getById(SysUser);
		SysUser.setPass_date(User.getSys_date(ServletActionContext
				.getRequest()));
		SysUserBo.update(SysUser);
		String changePwInfo = Base64.decodeToString(SysUser.getPass_word())+"/"+"000000/"+user_no;
		SysUserBo.changePassWord(changePwInfo);
		this.addActionMessage("密码重置'000000'成功");
		SysUser = new SysUser();
		Ipage ipage = this.getIpage();
		SysUserList = (List) SysUserBo.findByPage(ipage, SysUser).getResult();
		return "list";
	}


	public PubNoteBo getPubNoteBo() {
		return pubNoteBo;
	}


	public void setPubNoteBo(PubNoteBo pubNoteBo) {
		this.pubNoteBo = pubNoteBo;
	}


	public Map<String, String> getMenuTreeMap() {
		return menuTreeMap;
	}


	public void setMenuTreeMap(Map<String, String> menuTreeMap) {
		this.menuTreeMap = menuTreeMap;
	}


	public List<SysMenu> getSysMenuLev1List() {
		return sysMenuLev1List;
	}


	public void setSysMenuLev1List(List<SysMenu> sysMenuLev1List) {
		this.sysMenuLev1List = sysMenuLev1List;
	}


	public List<PubNote> getKnlist() {
		return knlist;
	}


	public void setKnlist(List<PubNote> knlist) {
		this.knlist = knlist;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getUser_no() {
		return user_no;
	}


	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}


	public SysUser getSysUser() {
		return SysUser;
	}


	public void setSysUser(SysUser sysUser) {
		SysUser = sysUser;
	}


	public List getTabList() {
		return tabList;
	}


	public void setTabList(List tabList) {
		this.tabList = tabList;
	}


	public FormData getFormsys0070() {
		return formsys0070;
	}


	public void setFormsys0070(FormData formsys0070) {
		this.formsys0070 = formsys0070;
	}


	public FormData getFormsys0071() {
		return formsys0071;
	}


	public void setFormsys0071(FormData formsys0071) {
		this.formsys0071 = formsys0071;
	}


	public FormData getFormsys0076() {
		return formsys0076;
	}


	public void setFormsys0076(FormData formsys0076) {
		this.formsys0076 = formsys0076;
	}


	public FormData getFormaddr0001() {
		return formaddr0001;
	}


	public void setFormaddr0001(FormData formaddr0001) {
		this.formaddr0001 = formaddr0001;
	}


	public FormData getFormsys0099() {
		return formsys0099;
	}


	public void setFormsys0099(FormData formsys0099) {
		this.formsys0099 = formsys0099;
	}


	public FormData getFormbid0003() {
		return formbid0003;
	}


	public void setFormbid0003(FormData formbid0003) {
		this.formbid0003 = formbid0003;
	}


	public FormData getFormsys1070() {
		return formsys1070;
	}


	public void setFormsys1070(FormData formsys1070) {
		this.formsys1070 = formsys1070;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getDuty() {
		return duty;
	}


	public void setDuty(String duty) {
		this.duty = duty;
	}


	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}


	public FormService getFormService() {
		return formService;
	}


	public void setFormService(FormService formService) {
		this.formService = formService;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPasword() {
		return newPasword;
	}


	public void setNewPasword(String newPasword) {
		this.newPasword = newPasword;
	}


	public String getRole_no() {
		return role_no;
	}


	public void setRole_no(String role_no) {
		this.role_no = role_no;
	}


	public String getInfo_id() {
		return info_id;
	}


	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}


	public String getIsInsert() {
		return isInsert;
	}


	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}


	public String getPeriod() {
		return Period;
	}


	public void setPeriod(String period) {
		Period = period;
	}

	public String getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(String menuJson) {
		this.menuJson = menuJson;
	}

	public String getBr_no() {
		return br_no;
	}

	public void setBr_no(String br_no) {
		this.br_no = br_no;
	}

	public SysUser getOp() {
		return op;
	}

	public void setOp(SysUser op) {
		this.op = op;
	}

	public SysUserRole getSysUserRole() {
		return sysUserRole;
	}

	public void setSysUserRole(SysUserRole sysUserRole) {
		this.sysUserRole = sysUserRole;
	}

	public String getLoadtoken() {
		return loadtoken;
	}

	public void setLoadtoken(String loadtoken) {
		this.loadtoken = loadtoken;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginColor() {
		return loginColor;
	}

	public void setLoginColor(String loginColor) {
		this.loginColor = loginColor;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}

	public String getAppNoStr() {
		return appNoStr;
	}

	public void setAppNoStr(String appNoStr) {
		this.appNoStr = appNoStr;
	}

	public String getChangePWInfo() {
		return changePWInfo;
	}

	public void setChangePWInfo(String changePWInfo) {
		this.changePWInfo = changePWInfo;
	}

	public List<SysUser> getSysUserList() {
		return SysUserList;
	}

	public void setSysUserList(List<SysUser> sysUserList) {
		SysUserList = sysUserList;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public SysUserBo getSysUserBo() {
		return SysUserBo;
	}

	public void setSysUserBo(SysUserBo sysUserBo) {
		SysUserBo = sysUserBo;
	}

	public SysUserRoleBo getSysUserRoleBo() {
		return sysUserRoleBo;
	}

	public void setSysUserRoleBo(SysUserRoleBo sysUserRoleBo) {
		this.sysUserRoleBo = sysUserRoleBo;
	}

	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public WkfApprovalRoleBo getWkfApprovalRoleBo() {
		return wkfApprovalRoleBo;
	}

	public void setWkfApprovalRoleBo(WkfApprovalRoleBo wkfApprovalRoleBo) {
		this.wkfApprovalRoleBo = wkfApprovalRoleBo;
	}

	public SysMenuBo getSysMenuBo() {
		return sysMenuBo;
	}

	public void setSysMenuBo(SysMenuBo sysMenuBo) {
		this.sysMenuBo = sysMenuBo;
	}

	public SecurityInterface getSecurityInterface() {
		return securityInterface;
	}

	public void setSecurityInterface(SecurityInterface securityInterface) {
		this.securityInterface = securityInterface;
	}
	
	
	
}