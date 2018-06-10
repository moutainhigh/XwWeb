package  app.creditapp.corp.action;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.bo.CorpContBo;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.corp.entity.CorpCert;
import app.creditapp.corp.entity.CorpCont;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpContAction.java
 * Description:
 **/
public class CorpContAction extends BaseFormBean {

	//页面传值
	private CorpCont corpCont;
	private CorpBase corpBase;
	private List<CorpCont> corpContList;

	//注入CorpContBo
	private CorpContBo corpContBo;
	private CorpBaseBo corpBaseBo;

	private String query;
	private String contNo;		
	private String brNo;
	
	private FormData formcoop0011;
	private FormData formcoop0012;
	private FormService formService = new FormService();
	
	public CorpContAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0011 = formService.getFormData("coop0011");
		corpCont = new CorpCont();
		getFormValue(formcoop0011);
		setObjValue(formcoop0011, corpCont);
		corpCont.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpContList = (List) corpContBo.findByPage(ipage, corpCont).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0012 = formService.getFormData("coop0012");
		
		corpCont = new CorpCont();
		corpBase = new CorpBase();
		 
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpCont.setBrNo(brNo);
		corpCont.setBrName(corpBase.getBrName());
		
		corpCont.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpCont.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpCont.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//		corpCont.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
//		corpCont.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formcoop0012, corpCont);
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0012 = formService.getFormData("coop0012");
		getFormValue(formcoop0012);
		corpCont = new CorpCont();
		setObjValue(formcoop0012, corpCont);
		corpCont.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpCont.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpCont.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpCont.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpCont.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpContBo.insert(corpCont);
		getObjValue(formcoop0012, corpCont);
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
		formcoop0012 = formService.getFormData("coop0012");
		getFormValue(formcoop0012);
		corpCont = new CorpCont();
		setObjValue(formcoop0012, corpCont);
		//corpCont.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		//corpCont.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		//corpCont.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpCont.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpCont.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpContBo.update(corpCont);
		getObjValue(formcoop0012, corpCont);
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
		formcoop0011 = formService.getFormData("coop0011");
		corpCont = new CorpCont();
		corpCont.setContNo(contNo);
		//判断 该合作机构下 此联系人信息是否是最后一条账户信息
		CorpCont corpContForDel = new CorpCont();
		corpContForDel.setBrNo(brNo);
		int count = corpContBo.getCount(corpContForDel);
		//当 账户信息 大于 1条的时候，可以进行删除操作
		if(count>1){
			 corpContBo.del(corpCont);
			 this.addActionMessage("删除成功");
		}else{
			//当账户信息 为1条的时候，判断现在 合作机构的状态，当合作机构的状态为 停用的时候 可以进行删除最后一条，正在启用的话，不能将此联系人信息删除
			CorpBase corpBase = new CorpBase();
			corpBase.setBrNo(brNo);
			corpBase = corpBaseBo.getById(corpBase);
			String brSts = corpBase.getBrSts();
			//合作机构 状态为 停用状态
			if("00".equals(brSts)){
				corpContBo.del(corpCont);
				 this.addActionMessage("删除成功");
			}else{
				this.addActionMessage("合作机构为启用状态，不能将此联系人信息删除");
			}
		}
		corpCont = new CorpCont();
		corpCont.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		corpContList = (List) corpContBo.findByPage(ipage, corpCont).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0012 = formService.getFormData("coop0012");
		corpCont = new CorpCont();
		corpCont.setContNo(contNo);
		corpCont = corpContBo.getById(corpCont);
		getObjValue(formcoop0012, corpCont);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0012 = formService.getFormData("coop0012");
		 getFormValue(formcoop0012);
		 validateFormData(formcoop0012);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0012 = formService.getFormData("coop0012");
		 getFormValue(formcoop0012);
		 validateFormData(formcoop0012);
  	}
	
	 /**
	    * 根据合作机构号显示所有的的联系人 信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	corpCont = new CorpCont();
	   	corpCont.setBrNo(brNo);
	   	Ipage ipage = this.getIpage();
	   	corpContList = (List) corpContBo.findByPageQuotaForCorp(ipage, corpCont).getResult();
	   	return "list";
	   }
	   
	   
    public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}	
	public CorpCont getCorpCont() {
		return corpCont;
	}
	public void setCorpCont(CorpCont  corpCont) {
		this.corpCont = corpCont;
	}
	public List<CorpCont> getCorpContList() {
		return corpContList;
	}
	public void setCorpContList(List<CorpCont> corpContList) {
		this.corpContList = corpContList;
	}
	public CorpContBo getCorpContBo() {
		return corpContBo;
	}
	public void setCorpContBo(CorpContBo corpContBo) {
		this.corpContBo = corpContBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcoop0012() {
		return formcoop0012;
	}
	public void setFormcoop0012(FormData formcoop0012) {
		this.formcoop0012 = formcoop0012;
	}
	public FormData getFormcoop0011() {
		return formcoop0011;
	}
	public void setFormcoop0011(FormData formcoop0011) {
		this.formcoop0011 = formcoop0011;
	}
	public void setContNo(String contNo){
		this.contNo = contNo;
	}		
	public String getContNo(){
		return contNo;
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