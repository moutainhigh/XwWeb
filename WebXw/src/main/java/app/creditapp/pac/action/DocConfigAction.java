package  app.creditapp.pac.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.pac.bo.DocConfigBo;
import app.creditapp.pac.entity.DocConfig;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: DocConfigAction.java
 * Description:
 **/
public class DocConfigAction extends BaseFormBean {

	//页面传值
	private DocConfig docConfig;
	private List<DocConfig> docConfigList;

	//注入DocConfigBo
	private DocConfigBo docConfigBo;

	private String query;
	private String docTypeId;	
	private String docTypeNo;
	private FormData formpac0035;
	private FormData formpac0036;
	private FormService formService = new FormService();
	
	public DocConfigAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpac0035 = formService.getFormData("pac0035");
		docConfig = new DocConfig();
		getFormValue(formpac0035);
		setObjValue(formpac0035, docConfig);
		Ipage ipage = this.getIpage();
		docConfigList = (List) docConfigBo.findByPage(ipage, docConfig).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpac0036 = formService.getFormData("pac0036");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpac0036 = formService.getFormData("pac0036");
		getFormValue(formpac0036);
		docConfig = new DocConfig();
		setObjValue(formpac0036, docConfig);	
		/* docConfig.setDocTypeNo(docTypeNo);
		 docConfig = docConfigBo.findDocTypeNo(docConfig);*/
		DocConfig docConfig1 = new DocConfig();
		docConfig1=docConfigBo.findDocTypeNo(docConfig);
		if (docConfig1!=null) {
			//提示
			this.addActionError("编号有冲突，此文档已存在");
			
		}else{
			
			docConfig.setTxDate(User.getSys_date(this.getHttpRequest()));
			docConfig.setOpNo(User.getTlrno(this.getHttpRequest()));
			docConfigBo.insert(docConfig);
			getObjValue(formpac0036, docConfig);
			
			this.addActionMessage("保存成功");
			//return "detail";
		}
		
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpac0036 = formService.getFormData("pac0036");
		getFormValue(formpac0036);
		docConfig = new DocConfig();
		setObjValue(formpac0036, docConfig);
		docConfig.setUpDate(User.getSys_date(this.getHttpRequest()));
		docConfig.setOpNo(User.getTlrno(this.getHttpRequest()));
		
		docConfig.setUpOpno(User.getTlrno(this.getHttpRequest()));
		docConfigBo.update(docConfig);
		getObjValue(formpac0036, docConfig);
		this.addActionMessage("修改成功");
//		this.changeFormProperty(formpac0036, "docTypeName", "readonly", "1");
//		 this.changeFormProperty(formpac0036, "docTypeNo", "readonly", "1");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formpac0035 = formService.getFormData("pac0035");
		docConfig = new DocConfig();
		docConfig.setDocTypeId(docTypeId);
		docConfigBo.del(docConfig);
		this.addActionMessage("删除成功");
		docConfig = new DocConfig();
		Ipage ipage = this.getIpage();
		docConfigList = (List) docConfigBo.findByPage(ipage, docConfig).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formpac0036 = formService.getFormData("pac0036");
		docConfig = new DocConfig();
		docConfig.setDocTypeId(docTypeId);
		docConfig = docConfigBo.getById(docConfig);
		getObjValue(formpac0036, docConfig);
		//this.changeFormProperty(formpac0036, "docTypeName", "readonly", "1");
		this.changeFormProperty(formpac0036, "docTypeNo", "readonly", "1");
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formpac0036 = formService.getFormData("pac0036");
		 getFormValue(formpac0036);
		 validateFormData(formpac0036);
		 
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formpac0036 = formService.getFormData("pac0036");
		 getFormValue(formpac0036);
		 validateFormData(formpac0036);
  	}
	public DocConfig getDocConfig() {
		return docConfig;
	}
	public void setDocConfig(DocConfig  docConfig) {
		this.docConfig = docConfig;
	}
	public List<DocConfig> getDocConfigList() {
		return docConfigList;
	}
	public void setDocConfigList(List<DocConfig> docConfigList) {
		this.docConfigList = docConfigList;
	}
	public DocConfigBo getDocConfigBo() {
		return docConfigBo;
	}
	public void setDocConfigBo(DocConfigBo docConfigBo) {
		this.docConfigBo = docConfigBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormpac0036() {
		return formpac0036;
	}
	public void setFormpac0036(FormData formpac0036) {
		this.formpac0036 = formpac0036;
	}
	public FormData getFormpac0035() {
		return formpac0035;
	}
	public void setFormpac0035(FormData formpac0035) {
		this.formpac0035 = formpac0035;
	}
	public void setDocTypeId(String docTypeId){
		this.docTypeId = docTypeId;
	}		
	public String getDocTypeId(){
		return docTypeId;
	}
}