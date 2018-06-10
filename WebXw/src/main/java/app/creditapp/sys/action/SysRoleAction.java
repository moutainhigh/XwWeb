package  app.creditapp.sys.action;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.base.ServiceException;
import app.base.SourceTemplate;
import app.creditapp.entity.SysRoleButton;
import app.creditapp.sys.bo.SysRoleBo;
import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.entity.SysButton;
import app.creditapp.sys.entity.SysRole;
import app.creditapp.sys.entity.SysUser;
import app.creditapp.sys.entity.SysUserRole;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: SysRoleAction.java
 * Description:
 **/
public class SysRoleAction extends BaseFormBean {

	//页面传值
	private SysRole sysRole;
	private SysUserBo sysUserBo;
	private List<SysRole> sysRoleList;

	//注入SysRoleBo
	private SysRoleBo sysRoleBo;

	private String query;
	private String role_no;	
	private String user_no;	
	private String menus;
	private String menuStr;
	private String boxStr;
	private String menu_no;
	private FormData formsys0074;
	private FormData formsys0075;
	private FormService formService = new FormService();
	
	public SysRoleAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0074 = formService.getFormData("sys0074");
		
		sysRole = new SysRole();
		getFormValue(formsys0074);
		setObjValue(formsys0074, sysRole);
		Ipage ipage = this.getIpage();
		sysRoleList = (List) sysRoleBo.findByPage(ipage, sysRole).getResult();
		return "list";
	}
	public String findByPageForPop(){
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0074 = formService.getFormData("sys0074");
		sysRole = new SysRole();
		getFormValue(formsys0074);
		setObjValue(formsys0074, sysRole);
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUser_no(user_no);
		Ipage ipage = this.getIpage();
		sysRoleList = (List<SysRole>) sysRoleBo.findByPageForPop(ipage, sysRole).getResult();
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0075 = formService.getFormData("sys0075");
		sysRole = new SysRole();
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(User.getLoginid(ServletActionContext.getRequest()));
		sysUser = sysUserBo.getById(sysUser);
		getObjValue(formsys0075, sysRole);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0075 = formService.getFormData("sys0075");
		getFormValue(formsys0075);
		sysRole = new SysRole();
		setObjValue(formsys0075, sysRole);
		sysRole.setRole_type("01");
		sysRoleBo.insert(sysRole);
		this.addActionMessage("操作成功");
		getObjValue(formsys0075, sysRole);
		this.changeFormProperty(formsys0075, "role_no", "readonly", "1");
		return "detail";
	}
	/**
	 * 启用
	 * @return
	 * @throws Exception
	 */
	public String active() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0074 = formService.getFormData("sys0074");
		sysRole = new SysRole();
		sysRole.setRole_no(role_no);
		sysRole = sysRoleBo.getById(sysRole);
		sysRole.setRole_sts("01");
		sysRoleBo.update(sysRole);
		this.addActionMessage("启用成功");
		sysRole = new SysRole();
		Ipage ipage = this.getIpage();
		sysRoleList = (List) sysRoleBo.findByPage(ipage, sysRole).getResult();
		return "list";
	}	
	/**
	 * 停用
	 * @return
	 * @throws Exception
	 */
	public String inactive() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0074 = formService.getFormData("sys0074");
		sysRole = new SysRole();
		sysRole.setRole_no(role_no);
		sysRole = sysRoleBo.getById(sysRole);
		sysRole.setRole_sts("02");
		sysRoleBo.update(sysRole);
		this.addActionMessage("停用成功");
		sysRole = new SysRole();
		Ipage ipage = this.getIpage();
		sysRoleList = (List) sysRoleBo.findByPage(ipage, sysRole).getResult();
		return "list";
	}	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0075 = formService.getFormData("sys0075");
		getFormValue(formsys0075);
		sysRole = new SysRole();
		setObjValue(formsys0075, sysRole);
//		sysRole.setRole_sts("01");
		String no=sysRole.getRole_no();
		SysRole sysRole1=new SysRole();
		sysRole1.setRole_no(no);
		sysRole1=sysRoleBo.getById(sysRole1);
		sysRole.setRole_sts(sysRole1.getRole_sts());
		sysRoleBo.update(sysRole);
		this.addActionMessage("操作成功");
		getObjValue(formsys0075, sysRole);
		this.changeFormProperty(formsys0075, "role_no", "readonly", "1");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0074 = formService.getFormData("sys0074");
		sysRole = new SysRole();
		sysRole.setRole_no(role_no);
		sysRoleBo.del(sysRole);
		this.addActionMessage("删除成功");
		sysRole = new SysRole();
		Ipage ipage = this.getIpage();
		sysRoleList = (List) sysRoleBo.findByPage(ipage, sysRole).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0075 = formService.getFormData("sys0075");
		sysRole = new SysRole();
		sysRole.setRole_no(role_no);
		sysRole = sysRoleBo.getById(sysRole);
		getObjValue(formsys0075, sysRole);
		this.changeFormProperty(formsys0075, "role_no", "readonly", "1");
		return "detail";
	}
	//角色管理对应菜单设置
	public String getAllMenuByRole() throws Exception{
		menuStr =  sysRoleBo.getAllJsonMenu();
		String menuno_str = sysRoleBo.checkJsp(role_no);
		this.getHttpRequest().setAttribute("menuno_str", menuno_str);
		return "allMenuByRole";
	}
	//角色管理对应菜单按钮设置
	public String getAllButtonByRole()throws Exception{
		menuStr =  sysRoleBo.getAllMenuByRoleNo(role_no);
		return "allButtonByRole";	
	}
	//保存已选对应按钮
	public String saveButton() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out = this.getHttpResponse().getWriter(); 
		SysRoleBo sysRoleBo = (SysRoleBo) SourceTemplate.getSpringContextInstance().getBean("sysRoleBo");// 从spring的容器里取得bo的实例
		String sts="";
		try {
			boxStr = URLDecoder.decode(boxStr, "UTF-8");
			sts=sysRoleBo.saveButton(role_no,menu_no,boxStr);
			out.print(sts);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return null;
	}
	//获取所有可选按钮
	public List<SysButton> getMenuButton() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		SysRoleBo sysRoleBo = (SysRoleBo) SourceTemplate.getSpringContextInstance().getBean("sysRoleBo");// 从spring的容器里取得bo的实例
		List<SysButton> list=null;
		try {
			list=sysRoleBo.getMenuButton(menu_no);
			JSONArray json_list = (JSONArray)JSON.toJSON(list);
			out.print(json_list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return null;
	}
	//获取角色对应已选按钮
	public List<SysRoleButton> getButtonList() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=utf-8"); 
		PrintWriter out = this.getHttpResponse().getWriter(); 
		SysRoleBo sysRoleBo = (SysRoleBo) SourceTemplate.getSpringContextInstance().getBean("sysRoleBo");// 从spring的容器里取得bo的实例
		List<SysRoleButton> list=null;
		try {
			list=sysRoleBo.getButtonList(role_no,menu_no);
//			JSONArray json_list = JSONArray.fromObject(list);
			JSONArray json_list = (JSONArray)JSON.toJSON(list);
			out.print(json_list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return null;
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0075 = formService.getFormData("sys0075");
		 getFormValue(formsys0075);
		 validateFormData(formsys0075);
		 sysRole = new SysRole();
		 setObjValue(formsys0075, sysRole);
		 int count = 0;		 
		 count = sysRoleBo.getCountByRoleNo(sysRole.getRole_no());
		 if (count > 0) {
			this.addActionError("该角色编号已存在");
		}
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0075 = formService.getFormData("sys0075");
		 getFormValue(formsys0075);
		 validateFormData(formsys0075);
  	}
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole  sysRole) {
		this.sysRole = sysRole;
	}
	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}
	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}
	public SysRoleBo getSysRoleBo() {
		return sysRoleBo;
	}
	public void setSysRoleBo(SysRoleBo sysRoleBo) {
		this.sysRoleBo = sysRoleBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0075() {
		return formsys0075;
	}
	public void setFormsys0075(FormData formsys0075) {
		this.formsys0075 = formsys0075;
	}
	public FormData getFormsys0074() {
		return formsys0074;
	}
	public void setFormsys0074(FormData formsys0074) {
		this.formsys0074 = formsys0074;
	}
	public void setRole_no(String role_no){
		this.role_no = role_no;
	}		
	public String getRole_no(){
		return role_no;
	}
	public String getMenus() {
		return menus;
	}
	public void setMenus(String menus) {
		this.menus = menus;
	}
	public String getMenuStr() {
		return menuStr;
	}
	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}
	public String getBoxStr() {
		return boxStr;
	}
	public void setBoxStr(String boxStr) {
		this.boxStr = boxStr;
	}
	public String getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(String menuNo) {
		menu_no = menuNo;
	}
	public SysUserBo getSysUserBo() {
		return sysUserBo;
	}
	public void setSysUserBo(SysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String userNo) {
		user_no = userNo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
}