package  app.creditapp.ln.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.ln.bo.PreGageBo;
import app.creditapp.ln.entity.PreGage;
import app.util.toolkit.Ipage;

/**
 * Title: PreGageAction.java
 * Description:
 **/
public class PreGageAction extends BaseFormBean {

	//页面传值
	private PreGage preGage;
	private List<PreGage> preGageList;

	//注入PreGageBo
	private PreGageBo preGageBo;

	private String query;
	private String appId;		
	private FormData formlnpregage0001;
	private FormData formlnpregage0002;
	private FormService formService = new FormService();
	
	public PreGageAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpregage0001 = formService.getFormData("lnpregage0001");
		preGage = new PreGage();
		getFormValue(formlnpregage0001);
		setObjValue(formlnpregage0001, preGage);
		Ipage ipage = this.getIpage();
		preGageList = (List) preGageBo.findByPage(ipage, preGage).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpregage0002 = formService.getFormData("lnpregage0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpregage0002 = formService.getFormData("lnpregage0002");
		getFormValue(formlnpregage0002);
		preGage = new PreGage();
		setObjValue(formlnpregage0002, preGage);
		preGageBo.insert(preGage);
		getObjValue(formlnpregage0002, preGage);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpregage0002 = formService.getFormData("lnpregage0002");
		getFormValue(formlnpregage0002);
		preGage = new PreGage();
		setObjValue(formlnpregage0002, preGage);
		preGageBo.update(preGage);
		getObjValue(formlnpregage0002, preGage);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpregage0001 = formService.getFormData("lnpregage0001");
		preGage = new PreGage();
		preGage.setAppId(appId);
		preGageBo.del(preGage);
		this.addActionMessage("删除成功");
		preGage = new PreGage();
		Ipage ipage = this.getIpage();
		preGageList = (List) preGageBo.findByPage(ipage, preGage).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpregage0002 = formService.getFormData("lnpregage0002");
		preGage = new PreGage();
		preGage.setAppId(appId);
		preGage = preGageBo.getById(preGage);
		getObjValue(formlnpregage0002, preGage);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnpregage0002 = formService.getFormData("lnpregage0002");
		 getFormValue(formlnpregage0002);
		 validateFormData(formlnpregage0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnpregage0002 = formService.getFormData("lnpregage0002");
		 getFormValue(formlnpregage0002);
		 validateFormData(formlnpregage0002);
  	}
	
	/**
	    * 根据申请ID显示所有的的押品信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForLn() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	preGage = new PreGage();
	   	preGage.setAppId(appId);
	   	Ipage ipage = this.getIpage();
	   	preGageList = (List) preGageBo.findByPageQuotaForLn(ipage, preGage).getResult();
	   	return "list";
	   }
	
	
	public PreGage getPreGage() {
		return preGage;
	}
	public void setPreGage(PreGage  preGage) {
		this.preGage = preGage;
	}
	public List<PreGage> getPreGageList() {
		return preGageList;
	}
	public void setPreGageList(List<PreGage> preGageList) {
		this.preGageList = preGageList;
	}
	public PreGageBo getPreGageBo() {
		return preGageBo;
	}
	public void setPreGageBo(PreGageBo preGageBo) {
		this.preGageBo = preGageBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnpregage0002() {
		return formlnpregage0002;
	}
	public void setFormlnpregage0002(FormData formlnpregage0002) {
		this.formlnpregage0002 = formlnpregage0002;
	}
	public FormData getFormlnpregage0001() {
		return formlnpregage0001;
	}
	public void setFormlnpregage0001(FormData formlnpregage0001) {
		this.formlnpregage0001 = formlnpregage0001;
	}
	public void setAppId(String appId){
		this.appId = appId;
	}		
	public String getAppId(){
		return appId;
	}
}