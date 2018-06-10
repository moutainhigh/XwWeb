package  app.creditapp.sys.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.sys.bo.SysOrgBo;
import app.creditapp.sys.entity.SysOrg;
import app.util.toolkit.Ipage;

/**
 * Title: SysOrgAction.java
 * Description:
 **/
public class SysOrgAction extends BaseFormBean {

	//页面传值
	private SysOrg sysOrg;
	private List<SysOrg> sysOrgList;

	//注入SysOrgBo
	private SysOrgBo sysOrgBo;

	private String query;
	private String orgNo;		
	private FormData formsys0012;
	private FormData formsys0013;
	private FormService formService = new FormService();
	
	public SysOrgAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0012 = formService.getFormData("sys0012");
		sysOrg = new SysOrg();
		getFormValue(formsys0012);
		setObjValue(formsys0012, sysOrg);
		Ipage ipage = this.getIpage();
		sysOrgList = (List) sysOrgBo.findByPage(ipage, sysOrg).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0013 = formService.getFormData("sys0013");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0013 = formService.getFormData("sys0013");
		getFormValue(formsys0013);
		sysOrg = new SysOrg();
		setObjValue(formsys0013, sysOrg);
		sysOrgBo.insert(sysOrg);
		this.addActionMessage("保存成功");
		getObjValue(formsys0013, sysOrg);
		this.changeFormProperty(formsys0013, "orgNo", "readonly", "1");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0013 = formService.getFormData("sys0013");
		getFormValue(formsys0013);
		sysOrg = new SysOrg();
		setObjValue(formsys0013, sysOrg);
		sysOrgBo.update(sysOrg);
		this.addActionMessage("保存成功");
		getObjValue(formsys0013, sysOrg);
		this.changeFormProperty(formsys0013, "orgNo", "readonly", "1");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0012 = formService.getFormData("sys0012");
		sysOrg = new SysOrg();
		sysOrg.setOrgNo(orgNo);
		sysOrgBo.del(sysOrg);
		this.addActionMessage("删除成功");
		sysOrg = new SysOrg();
		Ipage ipage = this.getIpage();
		sysOrgList = (List) sysOrgBo.findByPage(ipage, sysOrg).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0013 = formService.getFormData("sys0013");
		sysOrg = new SysOrg();
		sysOrg.setOrgNo(orgNo);
		sysOrg = sysOrgBo.getById(sysOrg);
		getObjValue(formsys0013, sysOrg);
		this.changeFormProperty(formsys0013, "orgNo", "readonly", "1");
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0013 = formService.getFormData("sys0013");
		 getFormValue(formsys0013);
		 validateFormData(formsys0013);
		 sysOrg = new SysOrg();
		 setObjValue(formsys0013, sysOrg);
		 sysOrg = sysOrgBo.getById(sysOrg);
		 if(sysOrg!=null){
				this.addActionError("该部门编号已存在!");
		 }
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0013 = formService.getFormData("sys0013");
		 getFormValue(formsys0013);
		 validateFormData(formsys0013);
  	}
	public SysOrg getSysOrg() {
		return sysOrg;
	}
	public void setSysOrg(SysOrg  sysOrg) {
		this.sysOrg = sysOrg;
	}
	public List<SysOrg> getSysOrgList() {
		return sysOrgList;
	}
	public void setSysOrgList(List<SysOrg> sysOrgList) {
		this.sysOrgList = sysOrgList;
	}
	public SysOrgBo getSysOrgBo() {
		return sysOrgBo;
	}
	public void setSysOrgBo(SysOrgBo sysOrgBo) {
		this.sysOrgBo = sysOrgBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0013() {
		return formsys0013;
	}
	public void setFormsys0013(FormData formsys0013) {
		this.formsys0013 = formsys0013;
	}
	public FormData getFormsys0012() {
		return formsys0012;
	}
	public void setFormsys0012(FormData formsys0012) {
		this.formsys0012 = formsys0012;
	}
	public void setOrgNo(String orgNo){
		this.orgNo = orgNo;
	}		
	public String getOrgNo(){
		return orgNo;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
}