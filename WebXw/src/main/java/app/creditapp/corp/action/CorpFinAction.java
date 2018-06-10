package  app.creditapp.corp.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.bo.CorpFinBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.corp.entity.CorpCert;
import app.creditapp.corp.entity.CorpCont;
import app.creditapp.corp.entity.CorpFin;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpFinAction.java
 * Description:
 **/
public class CorpFinAction extends BaseFormBean {

	//页面传值
	private CorpFin corpFin;
	private CorpBase corpBase;
	private List<CorpFin> corpFinList;

	//注入CorpFinBo
	private CorpFinBo corpFinBo;
	private CorpBaseBo corpBaseBo;

	
	private String query;
	private String finId;	
	private String brNo;
	
	private FormData formcoop0007;
	private FormData formcoop0008;
	private FormService formService = new FormService();
	
	public CorpFinAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0007 = formService.getFormData("coop0007");
		corpFin = new CorpFin();
		getFormValue(formcoop0007);
		setObjValue(formcoop0007, corpFin);
		corpFin.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpFinList = (List) corpFinBo.findByPage(ipage, corpFin).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0008 = formService.getFormData("coop0008");
		corpFin = new CorpFin();
		corpBase = new CorpBase();
		 
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpFin.setBrNo(brNo);
		corpFin.setBrName(corpBase.getBrName());
		
		corpFin.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpFin.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpFin.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//		corpFin.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
//		corpFin.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formcoop0008, corpFin);
		
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0008 = formService.getFormData("coop0008");
		getFormValue(formcoop0008);
		corpFin = new CorpFin();
		setObjValue(formcoop0008, corpFin);
		corpFin.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpFin.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpFin.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//		corpFin.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
//		corpFin.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpFinBo.insert(corpFin);
		getObjValue(formcoop0008, corpFin);
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
		formcoop0008 = formService.getFormData("coop0008");
		getFormValue(formcoop0008);
		corpFin = new CorpFin();
		setObjValue(formcoop0008, corpFin);
		//corpFin.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		//corpFin.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		//corpFin.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpFin.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpFin.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpFinBo.update(corpFin);
		getObjValue(formcoop0008, corpFin);
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
		formcoop0007 = formService.getFormData("coop0007");
		corpFin = new CorpFin();
		corpFin.setFinId(finId);
		corpFinBo.del(corpFin);
		this.addActionMessage("删除成功");
		corpFin = new CorpFin();
		corpFin.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		corpFinList = (List) corpFinBo.findByPage(ipage, corpFin).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0008 = formService.getFormData("coop0008");
		corpFin = new CorpFin();
		corpFin.setFinId(finId);
		corpFin = corpFinBo.getById(corpFin);
		getObjValue(formcoop0008, corpFin);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0008 = formService.getFormData("coop0008");
		 getFormValue(formcoop0008);
		 validateFormData(formcoop0008);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0008 = formService.getFormData("coop0008");
		 getFormValue(formcoop0008);
		 validateFormData(formcoop0008);
  	}
	
	/**
	    * 根据合作机构号显示所有的的财务信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	corpFin = new CorpFin();
	   	corpFin.setBrNo(brNo);
	   	Ipage ipage = this.getIpage();
	   	corpFinList = (List) corpFinBo.findByPageQuotaForCorp(ipage, corpFin).getResult();
	   	return "list";
	   }
    public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public CorpFin getCorpFin() {
		return corpFin;
	}
	public void setCorpFin(CorpFin  corpFin) {
		this.corpFin = corpFin;
	}
	public List<CorpFin> getCorpFinList() {
		return corpFinList;
	}
	public void setCorpFinList(List<CorpFin> corpFinList) {
		this.corpFinList = corpFinList;
	}
	public CorpFinBo getCorpFinBo() {
		return corpFinBo;
	}
	public void setCorpFinBo(CorpFinBo corpFinBo) {
		this.corpFinBo = corpFinBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcoop0008() {
		return formcoop0008;
	}
	public void setFormcoop0008(FormData formcoop0008) {
		this.formcoop0008 = formcoop0008;
	}
	public FormData getFormcoop0007() {
		return formcoop0007;
	}
	public void setFormcoop0007(FormData formcoop0007) {
		this.formcoop0007 = formcoop0007;
	}
	public void setFinId(String finId){
		this.finId = finId;
	}		
	public String getFinId(){
		return finId;
	}
	public CorpBase getCorpBase() {
		return corpBase;
	}
	public void setCorpBase(CorpBase corpBase) {
		this.corpBase = corpBase;
	}
	public CorpBaseBo getCorpBaseBo() {
		return corpBaseBo;
	}
	public void setCorpBaseBo(CorpBaseBo corpBaseBo) {
		this.corpBaseBo = corpBaseBo;
	}
}