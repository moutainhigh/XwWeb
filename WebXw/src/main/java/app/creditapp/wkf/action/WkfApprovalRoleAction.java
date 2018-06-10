package app.creditapp.wkf.action;

import java.util.List;
import org.apache.struts2.ServletActionContext;

import app.util.User;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.entity.SysUser;
import app.creditapp.wkf.bo.WkfApprovalRoleBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.WkfApprovalRole;
import app.creditapp.wkf.entity.WkfApprovalUser;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: WkfApprovalRoleAction.java Description:
 * 
 **/
public class WkfApprovalRoleAction extends BaseFormBean {
	private static final long serialVersionUID = 4613830726381121870L;
	// 页面传值
	private WkfApprovalRole wkfApprovalRole;
	private WkfApprovalUser wkfApprovalUser;
	private List<WkfApprovalRole> wkfApprovalRoleList;

	// 注入WkfApprovalRoleBo
	private WkfApprovalRoleBo wkfApprovalRoleBo;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private SysUserBo sysUserBo;

	private String query;
	private String wkfRoleNo;
	private String wkfUserName;
	private FormData formwkf0001;
	private FormData formwkf0002;
	private FormData formwkf0005;
	private FormData formwkf0006;
	private FormData formwkf0009;
	private String saveFlag;
	private String wkfRoleName;
	private String members;
	private String wkfrolelev;
	private FormService formService = new FormService();

	public WkfApprovalRoleAction() {
		query = "";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findByPage() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0001 = formService.getFormData("wkf0001");
		wkfApprovalRole = new WkfApprovalRole();
		getFormValue(formwkf0001);
		setObjValue(formwkf0001, wkfApprovalRole);
		String role_lev = User.getFicode(getHttpRequest());
		if(role_lev.equals("4")){
			role_lev = "(0,1,2,3,4)";
		}else if(role_lev.equals("3")){
			role_lev = "(0,1,2,3)";
		}else if(role_lev.equals("2")){
			role_lev = "(0,1,2)";
		}else if(role_lev.equals("1")){
			role_lev = "(0,1)";
		}else if(role_lev.equals("0")){
			role_lev = "(0)";
		}
		wkfApprovalRole.setOp_no(User.getLoginid(ServletActionContext.getRequest()));
		wkfApprovalRole.setWkfrolelev(role_lev);
		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findByPage(ipage,wkfApprovalRole).getResult();
		return "list";
	}
	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findByPageSys() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		wkfApprovalRole = new WkfApprovalRole();
		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findByPage(ipage,wkfApprovalRole).getResult();
		return "sysList";
	}
	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findByPageForApprovalUser() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0006 = formService.getFormData("wkf0006");
		wkfApprovalRole = new WkfApprovalRole();
		getFormValue(formwkf0006);
		setObjValue(formwkf0006, wkfApprovalRole);
		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findByPage(ipage,wkfApprovalRole).getResult();
		return "approvalUserList";
	}
	@SuppressWarnings("unchecked")
	public String findByPageForGroup() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0009 = formService.getFormData("wkf0009");
		wkfApprovalRole = new WkfApprovalRole();
		getFormValue(formwkf0009);
		setObjValue(formwkf0009, wkfApprovalRole);
		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findByPage(ipage,wkfApprovalRole).getResult();
		return "groupList";
	}
	/**
	 * pop分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findByPagePop() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
//		formwkf0005 = formService.getFormData("wkf0005");
		wkfApprovalRole = new WkfApprovalRole();
//		getFormValue(formwkf0005);
//		setObjValue(formwkf0005, wkfApprovalRole);
//		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findWkfApprovalRole(wkfApprovalRole);
		String roleno = getHttpRequest().getParameter("RoleNo");
		getHttpRequest().setAttribute("RoleNo", roleno);
		
		return "popList";
	}
	
	public String findByPagePopList() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
//		formwkf0005 = formService.getFormData("wkf0005");
		wkfApprovalRole = new WkfApprovalRole();
//		getFormValue(formwkf0005);
//		setObjValue(formwkf0005, wkfApprovalRole);
//		Ipage ipage = this.getIpage();
		wkfApprovalRole.setBr_no(User.getBrno(this.getHttpRequest()));
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findWkfApprovalRole(wkfApprovalRole);
		return "popList1";
	}
	
	/**
	 * 获取新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0002 = formService.getFormData("wkf0002");
		wkfApprovalRole = new WkfApprovalRole();
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(User.getLoginid(ServletActionContext.getRequest()));
		sysUser = sysUserBo.getById(sysUser);
		wkfApprovalRole.setBr_no(User.getBrno(this.getHttpRequest()));
		wkfApprovalRole.setOp_no(User.getLoginid(this.getHttpRequest()));
		wkfApprovalRole.setTx_date(User.getSys_date(this.getHttpRequest()));
		String roleLev = User.getFicode(this.getHttpRequest());
		this.getHttpRequest().setAttribute("roleLev", roleLev);
		getObjValue(formwkf0002,wkfApprovalRole);
		return "input";
	}
	
	public String groupInput() throws Exception 
	{
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		if(null!=wkfRoleNo&&!"".equals(wkfRoleNo))
		{
			wkfApprovalRole = new WkfApprovalRole();
			wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
			wkfApprovalRole=wkfApprovalRoleBo.getById(wkfApprovalRole);
		}
		return "groupInput";
	}
	
	public String insertOrUpdate() throws Exception 
	{
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		wkfApprovalRole = new WkfApprovalRole();
		wkfApprovalRole.setWkfRoleName(wkfRoleName);
		wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
		wkfApprovalRoleBo.insertOrUpdate(wkfApprovalRole,members,saveFlag);
		if(null!=wkfRoleNo&&!"".equals(wkfRoleNo))
		{
			wkfApprovalRole = new WkfApprovalRole();
			wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
			wkfApprovalRole=wkfApprovalRoleBo.getById(wkfApprovalRole);
		}
		this.addActionMessage("操作成功");
		return "groupInput";
	}
	/**
	 * 新增保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0002 = formService.getFormData("wkf0002");
		getFormValue(formwkf0002);
		wkfApprovalRole = new WkfApprovalRole();
		setObjValue(formwkf0002, wkfApprovalRole);
		wkfApprovalRole.setWkfbrno(User.getBrno(this.getHttpRequest()));
		wkfApprovalRoleBo.insert(wkfApprovalRole);
		getObjValue(formwkf0002, wkfApprovalRole);
		this.addActionMessage("新增成功");
		return "detail";
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0002 = formService.getFormData("wkf0002");
		getFormValue(formwkf0002);
		wkfApprovalRole = new WkfApprovalRole();
		setObjValue(formwkf0002, wkfApprovalRole);
		wkfApprovalRole.setUp_date(User.getSys_date(this.getHttpRequest()));
		wkfApprovalRoleBo.update(wkfApprovalRole);
		getObjValue(formwkf0002, wkfApprovalRole);
		this.changeFormProperty(formwkf0002,"wkfRoleNo","readonly","1");
		this.addActionMessage("修改成功");
		return "detail";
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String del() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0001 = formService.getFormData("wkf0001");
		wkfApprovalRole = new WkfApprovalRole();
		wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
		wkfApprovalRoleBo.del(wkfApprovalRole);
//		wkfApprovalUser = new WkfApprovalUser();
//		wkfApprovalUser.setWkfRoleNo(wkfRoleNo);
//		wkfApprovalUserBo.delForRole(wkfApprovalUser);
		this.addActionMessage("删除成功");
		wkfApprovalRole = new WkfApprovalRole();
		wkfApprovalRole.setOp_no(User.getLoginid(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findByPage(ipage,wkfApprovalRole).getResult();
		return "list";
	}
	public String delGroup() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		wkfApprovalRole = new WkfApprovalRole();
		wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
		wkfApprovalRoleBo.delGroup(wkfApprovalRole);
		this.addActionMessage("删除成功");
		wkfApprovalRole = new WkfApprovalRole();
		Ipage ipage = this.getIpage();
		wkfApprovalRoleList = (List<WkfApprovalRole>) wkfApprovalRoleBo.findByPage(ipage,wkfApprovalRole).getResult();
		return "list";
	}
	

	/**
	 * 查询操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0002 = formService.getFormData("wkf0002");
		wkfApprovalRole = new WkfApprovalRole();
		wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
		wkfApprovalRole = wkfApprovalRoleBo.getById(wkfApprovalRole);
		getObjValue(formwkf0002, wkfApprovalRole);
		this.changeFormProperty(formwkf0002,"wkfRoleNo","readonly","1");
		return "detail";
	}

	/**
	 * 新增保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateInsert() {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0002 = formService.getFormData("wkf0002");
		getFormValue(formwkf0002);
		validateFormData(formwkf0002);
		wkfApprovalRole = new WkfApprovalRole();
		wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
		wkfApprovalRole = wkfApprovalRoleBo.getById(wkfApprovalRole);
		if(null!=wkfApprovalRole)
			this.addActionError("该角色编号["+wkfRoleNo+"]已存在");
	}

	/**
	 * 修改保存操作校验
	 * 
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate() {
		ActionContext.initialize(getHttpRequest(),getHttpResponse());
		formwkf0002 = formService.getFormData("wkf0002");
		getFormValue(formwkf0002);
		validateFormData(formwkf0002);
//		wkfApprovalRole = new WkfApprovalRole();
//		wkfApprovalRole.setWkfRoleNo(wkfRoleNo);
//		wkfApprovalRole = wkfApprovalRoleBo.getById(wkfApprovalRole);
//		if(null!=wkfApprovalRole)
//			this.addActionError("该角色编号["+wkfRoleNo+"]已存在");
	}

	public WkfApprovalRole getWkfApprovalRole() {
		return wkfApprovalRole;
	}

	public void setWkfApprovalRole(WkfApprovalRole wkfApprovalRole) {
		this.wkfApprovalRole = wkfApprovalRole;
	}

	public List<WkfApprovalRole> getWkfApprovalRoleList() {
		return wkfApprovalRoleList;
	}

	public void setWkfApprovalRoleList(List<WkfApprovalRole> wkfApprovalRoleList) {
		this.wkfApprovalRoleList = wkfApprovalRoleList;
	}

	public WkfApprovalRoleBo getWkfApprovalRoleBo() {
		return wkfApprovalRoleBo;
	}

	public void setWkfApprovalRoleBo(WkfApprovalRoleBo wkfApprovalRoleBo) {
		this.wkfApprovalRoleBo = wkfApprovalRoleBo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FormData getFormwkf0002() {
		return formwkf0002;
	}

	public void setFormwkf0002(FormData formwkf0002) {
		this.formwkf0002 = formwkf0002;
	}

	public FormData getFormwkf0001() {
		return formwkf0001;
	}

	public void setFormwkf0001(FormData formwkf0001) {
		this.formwkf0001 = formwkf0001;
	}

	public void setWkfRoleNo(String wkfRoleNo) {
		this.wkfRoleNo = wkfRoleNo;
	}

	public String getWkfRoleNo() {
		return wkfRoleNo;
	}

	public FormData getFormwkf0005() {
		return formwkf0005;
	}

	public void setFormwkf0005(FormData formwkf0005) {
		this.formwkf0005 = formwkf0005;
	}

	public void setFormwkf0006(FormData formwkf0006) {
		this.formwkf0006 = formwkf0006;
	}

	public FormData getFormwkf0006() {
		return formwkf0006;
	}

	public FormData getFormwkf0009() {
		return formwkf0009;
	}

	public void setFormwkf0009(FormData formwkf0009) {
		this.formwkf0009 = formwkf0009;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getWkfRoleName() {
		return wkfRoleName;
	}

	public void setWkfRoleName(String wkfRoleName) {
		this.wkfRoleName = wkfRoleName;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getWkfrolelev() {
		return wkfrolelev;
	}

	public void setWkfrolelev(String wkfrolelev) {
		this.wkfrolelev = wkfrolelev;
	}

	public SysUserBo getSysUserBo() {
		return sysUserBo;
	}

	public void setSysUserBo(SysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}


	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public WkfApprovalUser getWkfApprovalUser() {
		return wkfApprovalUser;
	}

	public void setWkfApprovalUser(WkfApprovalUser wkfApprovalUser) {
		this.wkfApprovalUser = wkfApprovalUser;
	}

	public String getWkfUserName() {
		return wkfUserName;
	}

	public void setWkfUserName(String wkfUserName) {
		this.wkfUserName = wkfUserName;
	}
}