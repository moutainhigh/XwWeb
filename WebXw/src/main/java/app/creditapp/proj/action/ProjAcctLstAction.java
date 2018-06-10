package  app.creditapp.proj.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.proj.bo.ProjAcctLstBo;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.entity.ProjAcctLst;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: ProjAcctLstAction.java
 * Description:
 **/
public class ProjAcctLstAction extends BaseFormBean {

	//页面传值
	private ProjAcctLst projAcctLst;
	private List<ProjAcctLst> projAcctLstList;
	private String backSts;
	//注入ProjAcctLstBo
	private ProjAcctLstBo projAcctLstBo;

	private String query;
	private String lstId;	
	private String acctId;
	
	private FormData formproj0009;
	private FormData formproj0010;
	private FormService formService = new FormService();
	
	public ProjAcctLstAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0009 = formService.getFormData("proj0009");
		projAcctLst = new ProjAcctLst();
		getFormValue(formproj0009);
		setObjValue(formproj0009, projAcctLst);
		Ipage ipage = this.getIpage();
		projAcctLstList = (List) projAcctLstBo.findByPage(ipage, projAcctLst).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0010 = formService.getFormData("proj0010");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0010 = formService.getFormData("proj0010");
		getFormValue(formproj0010);
		projAcctLst = new ProjAcctLst();
		setObjValue(formproj0010, projAcctLst);
		projAcctLst.setTxDate(User.getSys_date(this.getHttpRequest()));
		projAcctLstBo.insert(projAcctLst);
		getObjValue(formproj0010, projAcctLst);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0010 = formService.getFormData("proj0010");
		getFormValue(formproj0010);
		projAcctLst = new ProjAcctLst();
		setObjValue(formproj0010, projAcctLst);
		projAcctLstBo.update(projAcctLst);
		getObjValue(formproj0010, projAcctLst);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0009 = formService.getFormData("proj0009");
		projAcctLst = new ProjAcctLst();
		projAcctLst.setLstId(lstId);
		projAcctLstBo.del(projAcctLst);
		this.addActionMessage("删除成功");
		projAcctLst = new ProjAcctLst();
		Ipage ipage = this.getIpage();
		projAcctLstList = (List) projAcctLstBo.findByPage(ipage, projAcctLst).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0010 = formService.getFormData("proj0010");
		projAcctLst = new ProjAcctLst();
		projAcctLst.setLstId(lstId);
		projAcctLst = projAcctLstBo.getById(projAcctLst);
		getObjValue(formproj0010, projAcctLst);
		return "detail";
	}
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById_Read() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0010 = formService.getFormData("proj0010");
		projAcctLst = new ProjAcctLst();
		projAcctLst.setLstId(lstId);
		projAcctLst = projAcctLstBo.getById(projAcctLst);
		getObjValue(formproj0010, projAcctLst);
		return "detail_Read";
	}
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formproj0010 = formService.getFormData("proj0010");
		 getFormValue(formproj0010);
		 validateFormData(formproj0010);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formproj0010 = formService.getFormData("proj0010");
		 getFormValue(formproj0010);
		 validateFormData(formproj0010);
  	}
	
	/**
	    * 根据项目显示所有的的账户流水 信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	projAcctLst = new ProjAcctLst();
	   	projAcctLst.setAcctId(acctId);
	   	Ipage ipage = this.getIpage();
	   	projAcctLstList = (List) projAcctLstBo.findByPageQuotaForCorp(ipage, projAcctLst).getResult();
	   	return "list";
	   }
	   /**
	    * 根据项目显示所有的的账户流水 信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp_Read() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	projAcctLst = new ProjAcctLst();
	   	projAcctLst.setAcctId(acctId);
	   	Ipage ipage = this.getIpage();
	   	projAcctLstList = (List) projAcctLstBo.findByPageQuotaForCorp(ipage, projAcctLst).getResult();
	   	return "list_Read";
	   }
	public ProjAcctLst getProjAcctLst() {
		return projAcctLst;
	}
	public void setProjAcctLst(ProjAcctLst  projAcctLst) {
		this.projAcctLst = projAcctLst;
	}
	public List<ProjAcctLst> getProjAcctLstList() {
		return projAcctLstList;
	}
	public void setProjAcctLstList(List<ProjAcctLst> projAcctLstList) {
		this.projAcctLstList = projAcctLstList;
	}
	public ProjAcctLstBo getProjAcctLstBo() {
		return projAcctLstBo;
	}
	public void setProjAcctLstBo(ProjAcctLstBo projAcctLstBo) {
		this.projAcctLstBo = projAcctLstBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormproj0010() {
		return formproj0010;
	}
	public void setFormproj0010(FormData formproj0010) {
		this.formproj0010 = formproj0010;
	}
	public FormData getFormproj0009() {
		return formproj0009;
	}
	public void setFormproj0009(FormData formproj0009) {
		this.formproj0009 = formproj0009;
	}
	public void setLstId(String lstId){
		this.lstId = lstId;
	}		
	public String getLstId(){
		return lstId;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
}