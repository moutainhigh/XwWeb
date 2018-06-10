package  app.creditapp.fund.action;
import java.util.ArrayList;
import java.util.List;
import app.util.User;
import org.apache.struts2.ServletActionContext;
import app.util.toolkit.Ipage;
import app.creditapp.bat.entity.RptPrdtDaily;
import app.creditapp.fund.bo.FundProvProjBo;
import app.creditapp.fund.entity.FundBase;
import app.creditapp.fund.entity.FundProvProj;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.fund.bo.FundBaseBo;
import app.creditapp.proj.bo.ProjParmBo;
import app.creditapp.bat.bo.RptPrdtDailyBo;
import app.creditapp.proj.entity.ProjParm;
/**
 * Title: FundProvProjAction.java
 * Description:
 **/
public class FundProvProjAction extends BaseFormBean {

	//页面传值
	private FundProvProj fundProvProj;
	private List<FundProvProj> fundProvProjList;


	private List<FundBase> fundBaseList;

	//注入FundProvProjBo
	private FundProvProjBo fundProvProjBo;
	private FundBaseBo fundBaseBo;
	private ProjParmBo projParmBo;
	private RptPrdtDailyBo rptPrdtDailyBo; 
	private String query;
	private List tabList;
	private String projNo;
	private String projName;
	private String provProjNo;
	private String backSts;
//	private String begDate;
//	private String endDate;
	private FormData formfdpvpj0001;
	private FormData formfdpvpj0002;
	private FormData formfdpvpj0003;
	private FormData formfdpvpj0004;
	private String popSts;
	private FormService formService = new FormService();
	
	public FundProvProjAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdpvpj0001 = formService.getFormData("fdpvpj0001");
		formfdpvpj0004 = formService.getFormData("fdpvpj0004");
		fundProvProj = new FundProvProj();
		Ipage ipage = this.getIpage();
		fundProvProj.setNowDate(User.getSys_date(this.getHttpRequest()));
		fundProvProj.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		fundProvProj.setProjNo(projNo);
		fundProvProj.setProjName(projName);	
		popSts = ServletActionContext.getRequest().getParameter("popSts");
		if("0".equals(popSts)){//判断是否可进行pop选择0：不可选择
			getFormValue(formfdpvpj0004);
			fundProvProj.setProjNo(projNo);
			String begDate = ServletActionContext.getRequest().getParameter("begDate");
			String endDate = ServletActionContext.getRequest().getParameter("endDate");
			fundProvProj.setBegDate(begDate);
			fundProvProj.setEndDate(endDate);
			setObjValue(formfdpvpj0004, fundProvProj);
			this.popSts="0";
		}else{
			getFormValue(formfdpvpj0001);
			setObjValue(formfdpvpj0001, fundProvProj);
			this.popSts=null;
		}
		fundProvProjList = (List) fundProvProjBo.findByPage(ipage, fundProvProj).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdpvpj0002 = formService.getFormData("fdpvpj0002");
		formfdpvpj0003 = formService.getFormData("fdpvpj0003");
		fundProvProj = new FundProvProj();
		fundProvProj.setNowDate(User.getSys_date(this.getHttpRequest()));
		if("0".equals(popSts)){//判断pop选择
			fundProvProj.setProjNo(projNo);
			fundProvProj.setProjName(fundProvProjBo.getProjNameByProjNo(projNo));
			getObjValue(formfdpvpj0003, fundProvProj);
			this.popSts="0";
		}else{
			getObjValue(formfdpvpj0002, fundProvProj);
			this.popSts=null;
		}
		this.changeFormProperty(formfdpvpj0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		this.changeFormProperty(formfdpvpj0002, "txDate", "readonly", "1");//1是只读，0是可编辑
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdpvpj0002 = formService.getFormData("fdpvpj0002");
		formfdpvpj0003 = formService.getFormData("fdpvpj0003");
		String backSts = "";
		getFormValue(formfdpvpj0002);
		fundProvProj = new FundProvProj();
		setObjValue(formfdpvpj0002, fundProvProj);
		fundProvProj.setOpNo(User.getLoginid(this.getHttpRequest()));
		
		//检查所查项目下所有资金是否满足计提条件
		FundBase fundBase = new FundBase();
		fundBase.setProjNo(fundProvProj.getProjNo());
		int count = fundBaseBo.getCountunmatched(fundBase);
		//检查所选项目配置信息中所需信息是否完全
		ProjParm projParm = new ProjParm();
		projParm.setProjNo(fundProvProj.getProjNo());
		int projParm_count = projParmBo.count(projParm);
		//检查报表中是否存在数据
		RptPrdtDaily rptPrdtDaily = new RptPrdtDaily();
		rptPrdtDaily.setRptDate(fundProvProj.getBegDate());
		rptPrdtDaily.setProjNo(fundProvProj.getProjNo());
		rptPrdtDaily.setPrdtNo(fundProvProj.getEndDate());
		int rptPrdtDaily_count = rptPrdtDailyBo.getCount(rptPrdtDaily);
		if(count > 0|| projParm_count < 1 || rptPrdtDaily_count < 1){
			if(count > 0){
				this.addActionMessage("部分资金信息缺失必要信息，无法计提！");	
			}
			if(projParm_count < 1){
				this.addActionMessage("项目参数配置表数据不存在，无法计提！");	
			}
			if(rptPrdtDaily_count < 1){
				this.addActionMessage("计提日期范围内的系统数据不存在，无法计提！");	
			}			
		  backSts = "input";
		  if("0".equals(popSts)){//判断pop选择
			fundProvProj.setProjNo(projNo);
			getObjValue(formfdpvpj0003, fundProvProj);
			this.popSts="0";
		  }else{
			getObjValue(formfdpvpj0002, fundProvProj);
			this.popSts=null;
		  }
		}else{
		  fundProvProj=fundProvProjBo.insert(fundProvProj);
		  projNo=fundProvProj.getProjNo();
		  provProjNo=fundProvProj.getProvProjNo();
		  this.addActionMessage("计提成功");
		  query="query";
		  if("0".equals(popSts)){//判断pop选择
			getObjValue(formfdpvpj0003, fundProvProj);
			this.popSts="0";
		  }else{
			getObjValue(formfdpvpj0002, fundProvProj);
			this.popSts=null;
		  }
		 backSts = "detail";
//		getObjValue(formfdpvpj0002, fundProvProj);
		}	
		//return "all";
		return backSts;
		
	}

	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdpvpj0002 = formService.getFormData("fdpvpj0002");
		getFormValue(formfdpvpj0002);
		fundProvProj = new FundProvProj();
		setObjValue(formfdpvpj0002, fundProvProj);
		fundProvProjBo.update(fundProvProj);
		this.addActionMessage("修改成功");
		query="query";
		getObjValue(formfdpvpj0002, fundProvProj);
		return "detail";
	}
	
	
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formfdpvpj0001 = formService.getFormData("fdpvpj0001");
		formfdpvpj0004 = formService.getFormData("fdpvpj0004");
		fundProvProj = new FundProvProj();
		fundProvProj.setProvProjNo(provProjNo);
		fundProvProjBo.del(fundProvProj);
		if("0".equals(popSts)){//判断pop选择
			fundProvProj.setProjNo(projNo);
			getObjValue(formfdpvpj0004, fundProvProj);
			this.popSts="0";
		}else{
			getObjValue(formfdpvpj0001, fundProvProj);
			this.popSts=null;
		}
		this.addActionMessage("删除成功");
		fundProvProj = new FundProvProj();
		fundProvProj.setProvProjNo(projNo);
		fundProvProj.setProjNo(projNo);
		Ipage ipage = this.getIpage();
		fundProvProjList = (List) fundProvProjBo.findByPage(ipage, fundProvProj).getResult();
		return "list";
	}
	/**
	 * 资金信息table头
	 * @return
	 */
	public String getDetailTop() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		formfdpvpj0001 = formService.getFormData("fdpvpj0001");
		fundProvProj = new FundProvProj();
		fundProvProj.setProvProjNo(provProjNo);
		fundProvProj = fundProvProjBo.getById(fundProvProj);
		getObjValue(formfdpvpj0001, fundProvProj);
		query = "query";
		return "top";
	}
	/**
	 * 查看资金信息
	 * @return
	 */
	public String getAllDetail() {
		ActionContext.initialize(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		fundProvProj = new FundProvProj();
		fundProvProj.setProvProjNo(provProjNo);
		fundProvProj = fundProvProjBo.getById(fundProvProj);
		//cifType = cifPersInf.getCifType();
		//if_agri = cifPers.getIf_agri();
		return "all";
	}
	/**
	 * @作者 DHCC-ZBW
	 * @日期 2016-7-8
	 * @方法说明：tab页显示
	 * @返回参数 String
	 */
	public String getTab() {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		tab = new String[2];
		tab[0] = "基本信息";
		tab[1] = "FundProvProjAction_getById.action?provProjNo="+provProjNo+"&projNo="+projNo+"&query="+query+"&backSts="+backSts;
		tabList.add(tab);
		
		tab = new String[2];
		tab[0] = "融资报酬汇总";
		tab[1] = "FundProvAction_findByPage.action?provProjNo="+provProjNo+"&projNo="+projNo;
		tabList.add(tab);
		
		tab = new String[2];
		tab[0] = "融资报酬明细";
		tab[1] = "FundProvSplitTermAction_findByPage.action?provProjNo="+provProjNo+"&projNo="+projNo;
		tabList.add(tab);
		
		tab = new String[2];
		tab[0] = "信托管理费明细";
		tab[1] = "FundProvSplitTermAction_Manager_findByPage.action?provProjNo="+provProjNo+"&projNo="+projNo;
		tabList.add(tab);		
		
		tab = new String[2];
		tab[0] = "放贷服务费明细";
		tab[1] = "FundProvServiceAction_findByPage.action?provProjNo="+provProjNo+"&projNo="+projNo;
		tabList.add(tab);
		
		return "tab";
	}
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formfdpvpj0002 = formService.getFormData("fdpvpj0002");
		fundProvProj = new FundProvProj();
		fundProvProj.setProvProjNo(provProjNo);
		fundProvProj = fundProvProjBo.getById(fundProvProj);
		getObjValue(formfdpvpj0002, fundProvProj);
		this.changeFormProperty(formfdpvpj0002, "opNo", "readonly", "1");//1是只读，0是可编辑
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdpvpj0002 = formService.getFormData("fdpvpj0002");
		 getFormValue(formfdpvpj0002);
		 validateFormData(formfdpvpj0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formfdpvpj0002 = formService.getFormData("fdpvpj0002");
		 getFormValue(formfdpvpj0002);
		 validateFormData(formfdpvpj0002);
  	}
	public FundProvProj getFundProvProj() {
		return fundProvProj;
	}
	public void setFundProvProj(FundProvProj  fundProvProj) {
		this.fundProvProj = fundProvProj;
	}
	public List<FundProvProj> getFundProvProjList() {
		return fundProvProjList;
	}
	public void setFundProvProjList(List<FundProvProj> fundProvProjList) {
		this.fundProvProjList = fundProvProjList;
	}


	public List<FundBase> getFundBaseList() {
		return fundBaseList;
	}
	public void setFundBaseList(List<FundBase> fundBaseList) {
		this.fundBaseList = fundBaseList;
	}
	public FundBaseBo getFundBaseBo() {
		return fundBaseBo;
	}
	public void setFundBaseBo(FundBaseBo fundBaseBo) {
		this.fundBaseBo = fundBaseBo;
	}
	
	public FundProvProjBo getFundProvProjBo() {
		return fundProvProjBo;
	}
	public void setFundProvProjBo(FundProvProjBo fundProvProjBo) {
		this.fundProvProjBo = fundProvProjBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormfdpvpj0002() {
		return formfdpvpj0002;
	}
	public void setFormfdpvpj0002(FormData formfdpvpj0002) {
		this.formfdpvpj0002 = formfdpvpj0002;
	}
	public FormData getFormfdpvpj0001() {
		return formfdpvpj0001;
	}
	public void setFormfdpvpj0001(FormData formfdpvpj0001) {
		this.formfdpvpj0001 = formfdpvpj0001;
	}
	public void setProvProjNo(String provProjNo){
		this.provProjNo = provProjNo;
	}		
	public String getProvProjNo(){
		return provProjNo;
	}
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public FormData getFormfdpvpj0003() {
		return formfdpvpj0003;
	}
	public void setFormfdpvpj0003(FormData formfdpvpj0003) {
		this.formfdpvpj0003 = formfdpvpj0003;
	}
	public FormData getFormfdpvpj0004() {
		return formfdpvpj0004;
	}
	public void setFormfdpvpj0004(FormData formfdpvpj0004) {
		this.formfdpvpj0004 = formfdpvpj0004;
	}
	public String getPopSts() {
		return popSts;
	}
	public void setPopSts(String popSts) {
		this.popSts = popSts;
	}
	public String getBackSts() {
		return backSts;
	}
	public void setBackSts(String backSts) {
		this.backSts = backSts;
	}
	/**
	 * @return the projParmBo
	 */
	public ProjParmBo getProjParmBo() {
		return projParmBo;
	}
	/**
	 * @param projParmBo the projParmBo to set
	 */
	public void setProjParmBo(ProjParmBo projParmBo) {
		this.projParmBo = projParmBo;
	}
	/**
	 * @return the rptPrdtDailyBo
	 */
	public RptPrdtDailyBo getRptPrdtDailyBo() {
		return rptPrdtDailyBo;
	}
	/**
	 * @param rptPrdtDailyBo the rptPrdtDailyBo to set
	 */
	public void setRptPrdtDailyBo(RptPrdtDailyBo rptPrdtDailyBo) {
		this.rptPrdtDailyBo = rptPrdtDailyBo;
	}


}