package  app.creditapp.sys.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.ParmAreaBo;
import app.creditapp.sys.entity.ParmArea;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: ParmAreaAction.java
 * Description:
 **/
public class ParmAreaAction extends BaseFormBean {

	//页面传值
	private ParmArea parmArea;
	private List<ParmArea> parmAreaList;

	//注入ParmAreaBo
	private ParmAreaBo parmAreaBo;

	private String query;
	private String areaNo;		
	private FormData formsys0014;
	private FormData formsys0015;
	private FormService formService = new FormService();
	
	public ParmAreaAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0014 = formService.getFormData("sys0014");
		parmArea = new ParmArea();
		getFormValue(formsys0014);
		setObjValue(formsys0014, parmArea);
		Ipage ipage = this.getIpage();
		parmAreaList = (List) parmAreaBo.findByPage(ipage, parmArea).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0015 = formService.getFormData("sys0015");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0015 = formService.getFormData("sys0015");
		getFormValue(formsys0015);
		parmArea = new ParmArea();
		setObjValue(formsys0015, parmArea);
		parmAreaBo.insert(parmArea);
		getObjValue(formsys0015, parmArea);
		return "detail";
	}
	public String listDepartment() throws Exception{
		
		return "toDepartmentInfo";
	}
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0015 = formService.getFormData("sys0015");
		getFormValue(formsys0015);
		parmArea = new ParmArea();
		setObjValue(formsys0015, parmArea);
		parmAreaBo.update(parmArea);
		getObjValue(formsys0015, parmArea);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formsys0014 = formService.getFormData("sys0014");
		parmArea = new ParmArea();
		parmArea.setAreaNo(areaNo);
		parmAreaBo.del(parmArea);
		this.addActionMessage("删除成功");
		parmArea = new ParmArea();
		Ipage ipage = this.getIpage();
		parmAreaList = (List) parmAreaBo.findByPage(ipage, parmArea).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formsys0015 = formService.getFormData("sys0015");
		parmArea = new ParmArea();
		parmArea.setAreaNo(areaNo);
		parmArea = parmAreaBo.getById(parmArea);
		getObjValue(formsys0015, parmArea);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0015 = formService.getFormData("sys0015");
		 getFormValue(formsys0015);
		 validateFormData(formsys0015);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formsys0015 = formService.getFormData("sys0015");
		 getFormValue(formsys0015);
		 validateFormData(formsys0015);
  	}
	public ParmArea getParmArea() {
		return parmArea;
	}
	public void setParmArea(ParmArea  parmArea) {
		this.parmArea = parmArea;
	}
	public List<ParmArea> getParmAreaList() {
		return parmAreaList;
	}
	public void setParmAreaList(List<ParmArea> parmAreaList) {
		this.parmAreaList = parmAreaList;
	}
	public ParmAreaBo getParmAreaBo() {
		return parmAreaBo;
	}
	public void setParmAreaBo(ParmAreaBo parmAreaBo) {
		this.parmAreaBo = parmAreaBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormsys0015() {
		return formsys0015;
	}
	public void setFormsys0015(FormData formsys0015) {
		this.formsys0015 = formsys0015;
	}
	public FormData getFormsys0014() {
		return formsys0014;
	}
	public void setFormsys0014(FormData formsys0014) {
		this.formsys0014 = formsys0014;
	}
	public void setAreaNo(String areaNo){
		this.areaNo = areaNo;
	}		
	public String getAreaNo(){
		return areaNo;
	}
}