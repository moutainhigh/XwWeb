package  app.creditapp.ln.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.ln.bo.LnStageBo;
import app.creditapp.ln.entity.LnStage;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: LnStageAction.java
 * Description:
 **/
public class LnStageAction extends BaseFormBean {

	//页面传值
	private LnStage lnStage;
	private List<LnStage> lnStageList;

	//注入LnStageBo
	private LnStageBo lnStageBo;

	private String query;
	private String appId;		
	private FormData formlnstage0001;
	private FormData formlnstage0002;
	private FormService formService = new FormService();
	
	public LnStageAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnstage0001 = formService.getFormData("lnstage0001");
		lnStage = new LnStage();
		getFormValue(formlnstage0001);
		setObjValue(formlnstage0001, lnStage);
		Ipage ipage = this.getIpage();
		lnStageList = (List) lnStageBo.findByPage(ipage, lnStage).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnstage0002 = formService.getFormData("lnstage0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnstage0002 = formService.getFormData("lnstage0002");
		getFormValue(formlnstage0002);
		lnStage = new LnStage();
		setObjValue(formlnstage0002, lnStage);
		lnStageBo.insert(lnStage);
		getObjValue(formlnstage0002, lnStage);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnstage0002 = formService.getFormData("lnstage0002");
		getFormValue(formlnstage0002);
		lnStage = new LnStage();
		setObjValue(formlnstage0002, lnStage);
		lnStageBo.update(lnStage);
		getObjValue(formlnstage0002, lnStage);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnstage0001 = formService.getFormData("lnstage0001");
		lnStage = new LnStage();
		lnStage.setAppId(appId);
		lnStageBo.del(lnStage);
		this.addActionMessage("删除成功");
		lnStage = new LnStage();
		Ipage ipage = this.getIpage();
		lnStageList = (List) lnStageBo.findByPage(ipage, lnStage).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnstage0002 = formService.getFormData("lnstage0002");
		lnStage = new LnStage();
		lnStage.setAppId(appId);
		lnStage = lnStageBo.getById(lnStage);
		getObjValue(formlnstage0002, lnStage);
		return "detail";
	}
	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getSts() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		lnStage = new LnStage();
		lnStage.setAppId(appId);
		lnStage = lnStageBo.getById(lnStage);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnstage0002 = formService.getFormData("lnstage0002");
		 getFormValue(formlnstage0002);
		 validateFormData(formlnstage0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnstage0002 = formService.getFormData("lnstage0002");
		 getFormValue(formlnstage0002);
		 validateFormData(formlnstage0002);
  	}
	public LnStage getLnStage() {
		return lnStage;
	}
	public void setLnStage(LnStage  lnStage) {
		this.lnStage = lnStage;
	}
	public List<LnStage> getLnStageList() {
		return lnStageList;
	}
	public void setLnStageList(List<LnStage> lnStageList) {
		this.lnStageList = lnStageList;
	}
	public LnStageBo getLnStageBo() {
		return lnStageBo;
	}
	public void setLnStageBo(LnStageBo lnStageBo) {
		this.lnStageBo = lnStageBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnstage0002() {
		return formlnstage0002;
	}
	public void setFormlnstage0002(FormData formlnstage0002) {
		this.formlnstage0002 = formlnstage0002;
	}
	public FormData getFormlnstage0001() {
		return formlnstage0001;
	}
	public void setFormlnstage0001(FormData formlnstage0001) {
		this.formlnstage0001 = formlnstage0001;
	}
	public void setAppId(String appId){
		this.appId = appId;
	}		
	public String getAppId(){
		return appId;
	}
}