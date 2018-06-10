package  app.creditapp.ln.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.ln.bo.LnAcctMidBo;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnAcctMid;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: LnAcctMidAction.java
 * Description:
 **/
public class LnAcctMidAction extends BaseFormBean {

	//页面传值
	private LnAcctMid lnAcctMid;
	private List<LnAcctMid> lnAcctMidList;
	private String backSts;
	
	//注入LnAcctMidBo
	private LnAcctMidBo lnAcctMidBo;

	private String query;
	private String appId;	
	private String acUse;		
	private String acNo;
	
	private FormData formlnacctmid0001;
	private FormData formlnacctmid0002;
	private FormService formService = new FormService();
	
	public LnAcctMidAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnacctmid0001 = formService.getFormData("lnacctmid0001");
		lnAcctMid = new LnAcctMid();
		getFormValue(formlnacctmid0001);
		setObjValue(formlnacctmid0001, lnAcctMid);
		Ipage ipage = this.getIpage();
		lnAcctMidList = (List) lnAcctMidBo.findByPage(ipage, lnAcctMid).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnacctmid0002 = formService.getFormData("lnacctmid0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnacctmid0002 = formService.getFormData("lnacctmid0002");
		getFormValue(formlnacctmid0002);
		lnAcctMid = new LnAcctMid();
		setObjValue(formlnacctmid0002, lnAcctMid);
		lnAcctMidBo.insert(lnAcctMid);
		getObjValue(formlnacctmid0002, lnAcctMid);
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
		formlnacctmid0002 = formService.getFormData("lnacctmid0002");
		getFormValue(formlnacctmid0002);
		lnAcctMid = new LnAcctMid();
		setObjValue(formlnacctmid0002, lnAcctMid);
		lnAcctMidBo.update(lnAcctMid);
		getObjValue(formlnacctmid0002, lnAcctMid);
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
		formlnacctmid0001 = formService.getFormData("lnacctmid0001");
		lnAcctMid = new LnAcctMid();
		lnAcctMid.setAppId(appId);
		lnAcctMidBo.del(lnAcctMid);
		this.addActionMessage("删除成功");
		lnAcctMid = new LnAcctMid();
		Ipage ipage = this.getIpage();
		lnAcctMidList = (List) lnAcctMidBo.findByPage(ipage, lnAcctMid).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnacctmid0002 = formService.getFormData("lnacctmid0002");
		lnAcctMid = new LnAcctMid();
		lnAcctMid.setAppId(appId);
		lnAcctMid.setAcUse(acUse);
		lnAcctMid.setAcNo(acNo);
		lnAcctMid = lnAcctMidBo.getById(lnAcctMid);
		getObjValue(formlnacctmid0002, lnAcctMid);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnacctmid0002 = formService.getFormData("lnacctmid0002");
		 getFormValue(formlnacctmid0002);
		 validateFormData(formlnacctmid0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnacctmid0002 = formService.getFormData("lnacctmid0002");
		 getFormValue(formlnacctmid0002);
		 validateFormData(formlnacctmid0002);
  	}
	
	 /**
	    * 根据申请ID显示所有的的账户信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForLn() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	lnAcctMid = new LnAcctMid();
	   	lnAcctMid.setAppId(appId);
	   	Ipage ipage = this.getIpage();
	   	lnAcctMidList = (List) lnAcctMidBo.findByPageQuotaForLn(ipage, lnAcctMid).getResult();
	   	return "list";
	   }
	   public String listQuotaForLn_Read() throws Exception {
		   	ActionContext.initialize(ServletActionContext.getRequest(),
		       		ServletActionContext.getResponse());
		   	lnAcctMid = new LnAcctMid();
		   	lnAcctMid.setAppId(appId);
		   	Ipage ipage = this.getIpage();
		   	lnAcctMidList = (List) lnAcctMidBo.findByPageQuotaForLn(ipage, lnAcctMid).getResult();
		   	return "list_Read";
		   }
	
	
	public LnAcctMid getLnAcctMid() {
		return lnAcctMid;
	}
	public void setLnAcctMid(LnAcctMid  lnAcctMid) {
		this.lnAcctMid = lnAcctMid;
	}
	public List<LnAcctMid> getLnAcctMidList() {
		return lnAcctMidList;
	}
	public void setLnAcctMidList(List<LnAcctMid> lnAcctMidList) {
		this.lnAcctMidList = lnAcctMidList;
	}
	public LnAcctMidBo getLnAcctMidBo() {
		return lnAcctMidBo;
	}
	public void setLnAcctMidBo(LnAcctMidBo lnAcctMidBo) {
		this.lnAcctMidBo = lnAcctMidBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnacctmid0002() {
		return formlnacctmid0002;
	}
	public void setFormlnacctmid0002(FormData formlnacctmid0002) {
		this.formlnacctmid0002 = formlnacctmid0002;
	}
	public FormData getFormlnacctmid0001() {
		return formlnacctmid0001;
	}
	public void setFormlnacctmid0001(FormData formlnacctmid0001) {
		this.formlnacctmid0001 = formlnacctmid0001;
	}
	public void setAppId(String appId){
		this.appId = appId;
	}		
	public String getAppId(){
		return appId;
	}
	public String getAcUse() {
		return acUse;
	}
	public void setAcUse(String acUse) {
		this.acUse = acUse;
	}
	public String getAcNo() {
		return acNo;
	}
	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
}