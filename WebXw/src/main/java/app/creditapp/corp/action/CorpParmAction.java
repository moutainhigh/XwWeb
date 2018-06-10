package  app.creditapp.corp.action;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.bo.CorpParmBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.corp.entity.CorpParm;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

/**
 * Title: CorpParmAction.java
 * Description:
 **/
public class CorpParmAction extends BaseFormBean {

	//页面传值
	private CorpParm corpParm;
	private CorpBase corpBase;
	
	private List<CorpParm> corpParmList;

	//注入CorpParmBo
	private CorpParmBo corpParmBo;
	private CorpBaseBo corpBaseBo;

	private String query;
	private String parmId;		
	private String brNo;
	
	private FormData formcoop0003;
	private FormData formcoop0004;
	private FormService formService = new FormService();
	
	public CorpParmAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0003 = formService.getFormData("coop0003");
		corpParm = new CorpParm();
		getFormValue(formcoop0003);
		setObjValue(formcoop0003, corpParm);
		corpParm.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpParmList = (List) corpParmBo.findByPage(ipage, corpParm).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0004 = formService.getFormData("coop0004");
		
		corpParm = new CorpParm();
		corpBase = new CorpBase();
		 
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		getObjValue(formcoop0004, corpParm);
		
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formcoop0004 = formService.getFormData("coop0004");
		getFormValue(formcoop0004);
		corpParm = new CorpParm();
		setObjValue(formcoop0004, corpParm);
		corpParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		//corpParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		//corpParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		corpParmBo.insert(corpParm);
		getObjValue(formcoop0004, corpParm);
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
		formcoop0004 = formService.getFormData("coop0004");
		getFormValue(formcoop0004);
		corpParm = new CorpParm();
		setObjValue(formcoop0004, corpParm);
		
		
		corpParm.setBrNo(brNo);
		corpParm = corpParmBo.getById(corpParm);
		
		corpBase = new CorpBase();
		 
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		
		
		if(corpParm==null){
			corpParm = new CorpParm();
			setObjValue(formcoop0004, corpParm);
			corpParm.setBrNo(brNo);
			corpParm.setBrName(corpBase.getBrName());
			corpParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
			corpParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
			corpParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
			corpParmBo.insert(corpParm);
		}else{
			//corpParm = new CorpParm();
			setObjValue(formcoop0004, corpParm);
			corpParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
			corpParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
			corpParmBo.update(corpParm);
			//该合作机构号的配置信息进行更新的时候，判断该合作机构具备的信息是否完整，来进行判断此合作机构号能否进行启用
			int count = corpBaseBo.getAllInf(brNo);
			//等于4 的情况下 表示所有的信息已经填写完整，这个合作机构 就可以进行启用
			if(count == 4){
				CorpBase corpBase = new CorpBase();
				corpBase.setBrNo(brNo);
				corpBase = corpBaseBo.getById(corpBase);
				//设置合作机构的状态为 启用 状态，进行更新这个 合作机构号
				corpBase.setBrSts("01");
				corpBaseBo.update(corpBase);
			}
		}
		//清楚缓存
		try {
			MBaseCache.getCache().removeByKey(CachecodeUtil.CORP_PARM_STR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getObjValue(formcoop0004, corpParm);
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
		formcoop0003 = formService.getFormData("coop0003");
		corpParm = new CorpParm();
		corpParm.setParmId(parmId);
		corpParmBo.del(corpParm);
		this.addActionMessage("删除成功");
		corpParm = new CorpParm();
		corpParm.setBrNo(User.getBrno(ServletActionContext.getRequest()));
		Ipage ipage = this.getIpage();
		corpParmList = (List) corpParmBo.findByPage(ipage, corpParm).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formcoop0004 = formService.getFormData("coop0004");
		corpParm = new CorpParm();
		corpBase = new CorpBase();
		
		//corpParm.setParmId(parmId);
		corpParm.setBrNo(brNo);
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		corpParm = corpParmBo.getById(corpParm);
		if(corpParm==null){
			corpParm = new CorpParm();
			corpParm.setPutType("01");//放款模式
			corpParm.setMonthDays("01");//贷款月天数
			corpParm.setGraceDay(0);//逾期宽限期
			corpParm.setTolAmt(0.00);//入账容错额度
			corpParm.setRpyDay("01");//扣款日类型
			corpParm.setPayOrder("01");//扣款顺序
			corpParm.setAccType("01");//核算方式
			corpParm.setIfRelchk("0");//查征是否关联预审批校验
			
		}
		corpParm.setBrNo(brNo);
		corpParm.setBrName(corpBase.getBrName());
		
		corpParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		corpParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		corpParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		corpParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		corpParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		
		
		
		getObjValue(formcoop0004, corpParm);
		return "detail";
		
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0004 = formService.getFormData("coop0004");
		 getFormValue(formcoop0004);
		 validateFormData(formcoop0004);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formcoop0004 = formService.getFormData("coop0004");
		 getFormValue(formcoop0004);
		 validateFormData(formcoop0004);
  	}
	
	/**
	    * 根据合作机构号显示所有的的配置 信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	corpParm = new CorpParm();
	   	corpParm.setBrNo(brNo);
	   	Ipage ipage = this.getIpage();
	   	corpParmList = (List) corpParmBo.findByPageQuotaForCorp(ipage, corpParm).getResult();
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
	public CorpParm getCorpParm() {
		return corpParm;
	}
	public void setCorpParm(CorpParm  corpParm) {
		this.corpParm = corpParm;
	}
	public List<CorpParm> getCorpParmList() {
		return corpParmList;
	}
	public void setCorpParmList(List<CorpParm> corpParmList) {
		this.corpParmList = corpParmList;
	}
	public CorpParmBo getCorpParmBo() {
		return corpParmBo;
	}
	public void setCorpParmBo(CorpParmBo corpParmBo) {
		this.corpParmBo = corpParmBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormcoop0004() {
		return formcoop0004;
	}
	public void setFormcoop0004(FormData formcoop0004) {
		this.formcoop0004 = formcoop0004;
	}
	public FormData getFormcoop0003() {
		return formcoop0003;
	}
	public void setFormcoop0003(FormData formcoop0003) {
		this.formcoop0003 = formcoop0003;
	}
	public void setParmId(String parmId){
		this.parmId = parmId;
	}		
	public String getParmId(){
		return parmId;
	}
}