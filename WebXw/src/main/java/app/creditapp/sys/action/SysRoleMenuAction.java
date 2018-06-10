package  app.creditapp.sys.action;
import java.io.PrintWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.base.ServiceException;
import app.base.SourceTemplate;
import app.creditapp.sys.bo.SysRoleMenuBo;
import app.creditapp.sys.entity.SysRoleMenu;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: SysRoleMenuAction.java
 * Description:
 **/
public class SysRoleMenuAction extends BaseFormBean {

	//页面传值
	private SysRoleMenu sysRoleMenu;
	private List<SysRoleMenu> sysRoleMenuList;

	//注入SysRoleMenuBo
	private SysRoleMenuBo sysRoleMenuBo;

	private String query;
	private String rec_id;		
	private String menus;
	private String role_no;
	private FormData formsys1000;
	private FormData formsys1001;
	private FormService formService = new FormService();
	
	public SysRoleMenuAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys1000 = formService.getFormData("sys1000");
		sysRoleMenu = new SysRoleMenu();
		getFormValue(formsys1000);
		setObjValue(formsys1000, sysRoleMenu);
		Ipage ipage = this.getIpage();
		sysRoleMenuList = (List) sysRoleMenuBo.findByPage(ipage, sysRoleMenu).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys1001 = formService.getFormData("sys1001");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys1001 = formService.getFormData("sys1001");
		getFormValue(formsys1001);
		sysRoleMenu = new SysRoleMenu();
		setObjValue(formsys1001, sysRoleMenu);
		sysRoleMenuBo.insert(sysRoleMenu);
		getObjValue(formsys1001, sysRoleMenu);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys1001 = formService.getFormData("sys1001");
		getFormValue(formsys1001);
		sysRoleMenu = new SysRoleMenu();
		setObjValue(formsys1001, sysRoleMenu);
		sysRoleMenuBo.update(sysRoleMenu);
		getObjValue(formsys1001, sysRoleMenu);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys1000 = formService.getFormData("sys1000");
		sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setRec_id(rec_id);
		sysRoleMenuBo.del(sysRoleMenu);
		this.addActionMessage("删除成功");
		sysRoleMenu = new SysRoleMenu();
		Ipage ipage = this.getIpage();
		sysRoleMenuList = (List) sysRoleMenuBo.findByPage(ipage, sysRoleMenu).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys1001 = formService.getFormData("sys1001");
		sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setRec_id(rec_id);
		sysRoleMenu = sysRoleMenuBo.getById(sysRoleMenu);
		getObjValue(formsys1001, sysRoleMenu);
		return "detail";
	}
	//角色对应菜单的保存与修改
	public String saveAndUpdate() throws Exception {
		SysRoleMenuBo sysRoleMenuBo = (SysRoleMenuBo) SourceTemplate.getSpringContextInstance().getBean("sysRoleMenuBo");// 从spring的容器里取得bo的实例
		PrintWriter out = this.getHttpResponse().getWriter();
		String logo= null;
		try {
			logo = sysRoleMenuBo.saveAndUpdate(menus,role_no);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		out.print(logo);
		return null;
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys1001 = formService.getFormData("sys1001");
		 getFormValue(formsys1001);
		 validateFormData(formsys1001);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys1001 = formService.getFormData("sys1001");
		 getFormValue(formsys1001);
		 validateFormData(formsys1001);
  	}
	public SysRoleMenu getSysRoleMenu() {
		return sysRoleMenu;
	}
	public void setSysRoleMenu(SysRoleMenu  sysRoleMenu) {
		this.sysRoleMenu = sysRoleMenu;
	}
	public List<SysRoleMenu> getSysRoleMenuList() {
		return sysRoleMenuList;
	}
	public void setSysRoleMenuList(List<SysRoleMenu> sysRoleMenuList) {
		this.sysRoleMenuList = sysRoleMenuList;
	}
	public SysRoleMenuBo getSysRoleMenuBo() {
		return sysRoleMenuBo;
	}
	public void setSysRoleMenuBo(SysRoleMenuBo sysRoleMenuBo) {
		this.sysRoleMenuBo = sysRoleMenuBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys1001() {
		return formsys1001;
	}
	public void setFormsys1001(FormData formsys1001) {
		this.formsys1001 = formsys1001;
	}
	public FormData getFormsys1000() {
		return formsys1000;
	}
	public void setFormsys1000(FormData formsys1000) {
		this.formsys1000 = formsys1000;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public String getRole_no() {
		return role_no;
	}

	public void setRole_no(String roleNo) {
		role_no = roleNo;
	}
	public String getRec_id() {
		return rec_id;
	}
	public void setRec_id(String recId) {
		rec_id = recId;
	}
}