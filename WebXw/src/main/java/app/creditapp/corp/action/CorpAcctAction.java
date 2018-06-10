package  app.creditapp.corp.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.corp.bo.CorpAcctBo;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.corp.entity.CorpBase;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: CorpAcctAction.java
 * Description:
 **/
public class CorpAcctAction extends BaseFormBean {

	//页面传值
	private CorpAcct corpAcct;
	private CorpBase corpBase;
	private List<CorpAcct> corpAcctList;

	//注入CorpAcctBo
	private CorpAcctBo corpAcctBo;
	private CorpBaseBo corpBaseBo;

	
	private String query;
	private String acctId;
	private String brNo;
	
	private FormData formcoop0013;
	private FormData formcoop0014;
	private FormService formService = new FormService();
	
	public CorpAcctAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0013 = formService.getFormData("coop0013");
		corpAcct = new CorpAcct();
		getFormValue(formcoop0013);
		setObjValue(formcoop0013, corpAcct);
		corpAcct.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpAcctList = (List) corpAcctBo.findByPage(ipage, corpAcct).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0014 = formService.getFormData("coop0014");
		String returnSts = "";
		corpAcct = new CorpAcct();
		corpBase = new CorpBase();
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpAcct.setBrNo(brNo);
		corpAcct.setBrName(corpBase.getBrName());
		if(corpAcctBo.getById(corpAcct)==null){//判断合作机构是否已添加账户信息
			corpAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
			corpAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
			corpAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
//			corpAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
//			corpAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
			getObjValue(formcoop0014, corpAcct);
			returnSts = "input";
		}else{
			Ipage ipage = this.getIpage();
		   	corpAcctList = (List) corpAcctBo.findByPageQuotaForCorp(ipage, corpAcct).getResult();
		   	this.addActionMessage(corpBase.getBrName()+"已添加账户信息");
		   	returnSts =  "list";
		}
		return returnSts;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0014 = formService.getFormData("coop0014");
		getFormValue(formcoop0014);
		corpAcct = new CorpAcct();
		setObjValue(formcoop0014, corpAcct);
		
		corpAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		//corpAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		//corpAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpAcctBo.insert(corpAcct);
		getObjValue(formcoop0014, corpAcct);
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
		formcoop0014 = formService.getFormData("coop0014");
		getFormValue(formcoop0014);
		corpAcct = new CorpAcct();
		setObjValue(formcoop0014, corpAcct);
		//corpAcct.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		//corpAcct.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		//corpAcct.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpAcct.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpAcct.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpAcctBo.update(corpAcct);
		getObjValue(formcoop0014, corpAcct);
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
		formcoop0013 = formService.getFormData("coop0013");
		corpAcct = new CorpAcct();
		corpAcct.setAcctId(acctId);
		//判断 该合作机构下 该账户信息是否是最后一条账户信息
		CorpAcct corpAcctForDel = new CorpAcct();
		corpAcctForDel.setBrNo(brNo);
		int count = corpAcctBo.getCount(corpAcctForDel);
		//当 账户信息 大于 1条的时候，可以进行删除操作
		if(count>1){
			 corpAcctBo.del(corpAcct);	
			 this.addActionMessage("删除成功");
		}else{
			//当账户信息 为1条的时候，判断现在 合作机构的状态，当合作机构的状态为 停用的时候 可以进行删除最后一条，正在启用的话，不能讲此账户信息删除
			CorpBase corpBase = new CorpBase();
			corpBase.setBrNo(brNo);
			corpBase = corpBaseBo.getById(corpBase);
			String brSts = corpBase.getBrSts();
			//合作机构 状态为 停用状态
			if("00".equals(brSts)){
				 corpAcctBo.del(corpAcct);
				 this.addActionMessage("删除成功");
			}else{
				this.addActionMessage("合作机构为启用状态，不能将此账户信息删除");
			}
		}
		corpAcct = new CorpAcct();
		corpAcct.setBrNo(brNo);
		Ipage ipage = this.getIpage();
		corpAcctList = (List) corpAcctBo.findByPage(ipage, corpAcct).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0014 = formService.getFormData("coop0014");
		corpAcct = new CorpAcct();
		corpAcct.setAcctId(acctId);
		corpAcct = corpAcctBo.getById(corpAcct);
		getObjValue(formcoop0014, corpAcct);
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0014 = formService.getFormData("coop0014");
		 getFormValue(formcoop0014);
		 validateFormData(formcoop0014);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0014 = formService.getFormData("coop0014");
		 getFormValue(formcoop0014);
		 validateFormData(formcoop0014);
  	}
	
	
	
	 /**
    * 根据合作机构号显示所有的的账户信息
    * @return
    * @throws Exception
    */
   public String listQuotaForCorp() throws Exception {
   	ActionContext.initialize(ServletActionContext.getRequest(),
       		ServletActionContext.getResponse());
   	corpAcct = new CorpAcct();
   	corpAcct.setBrNo(brNo);
   	Ipage ipage = this.getIpage();
   	corpAcctList = (List) corpAcctBo.findByPageQuotaForCorp(ipage, corpAcct).getResult();
   	return "list";
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
    public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public CorpAcct getCorpAcct() {
		return corpAcct;
	}
	public void setCorpAcct(CorpAcct  corpAcct) {
		this.corpAcct = corpAcct;
	}
	public List<CorpAcct> getCorpAcctList() {
		return corpAcctList;
	}
	public void setCorpAcctList(List<CorpAcct> corpAcctList) {
		this.corpAcctList = corpAcctList;
	}
	public CorpAcctBo getCorpAcctBo() {
		return corpAcctBo;
	}
	public void setCorpAcctBo(CorpAcctBo corpAcctBo) {
		this.corpAcctBo = corpAcctBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcoop0014() {
		return formcoop0014;
	}
	public void setFormcoop0014(FormData formcoop0014) {
		this.formcoop0014 = formcoop0014;
	}
	public FormData getFormcoop0013() {
		return formcoop0013;
	}
	public void setFormcoop0013(FormData formcoop0013) {
		this.formcoop0013 = formcoop0013;
	}
	public void setAcctId(String acctId){
		this.acctId = acctId;
	}		
	public String getAcctId(){
		return acctId;
	}
}