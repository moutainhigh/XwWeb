package  app.creditapp.proj.action;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;

import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.bo.ProjParmBo;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.ProjParm;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: ProjParmAction.java
 * Description:
 **/
public class ProjParmAction extends BaseFormBean {

	//页面传值
	private ProjParm projParm;
	private ProjBase projBase;
	
	private List<ProjParm> projParmList;

	//注入ProjParmBo
	private ProjParmBo projParmBo;
	private ProjBaseBo projBaseBo;

	private String query;
	private Float leverRate;		
	private String txDate;		
	private String upOpno;		
	private String deptNo;		
	private String truPayType;		
	private String upDate;		
	private Float rgAppRate;			
	private String projName;		
	private String opNo;		
	private String filler;			
	private String projNo;	
	private String projId;
	private Integer backDays;		
	private FormData formproj0005;
	private FormData formproj0006;
	private FormService formService = new FormService();
	
	public ProjParmAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formproj0005 = formService.getFormData("proj0005");
		projParm = new ProjParm();
		getFormValue(formproj0005);
		setObjValue(formproj0005, projParm);
		Ipage ipage = this.getIpage();
		projParmList = (List) projParmBo.findByPage(ipage, projParm).getResult();
		return "list";
	}
	
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0006 = formService.getFormData("proj0006");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0006 = formService.getFormData("proj0006");
		getFormValue(formproj0006);
		projParm = new ProjParm();
		setObjValue(formproj0006, projParm);
		
		projParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
		projParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
		projParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
		//projParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
		//projParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
		projParmBo.insert(projParm);
		getObjValue(formproj0006, projParm);
		return "detail";
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formproj0006 = formService.getFormData("proj0006");
		getFormValue(formproj0006);
		projParm = new ProjParm();
		setObjValue(formproj0006, projParm);
		
		projParm.setProjNo(projNo);
		projParm = projParmBo.getById(projParm);
		
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		
		if(projParm==null){
			projParm = new ProjParm();
			setObjValue(formproj0006, projParm);
			projParm.setProjNo(projNo);
//			projParm.setBackDays(66);
			
			
			projParm.setProjName(projBase.getProjName());
			projParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
			projParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
			projParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
			HttpServletRequest request = ServletActionContext.getRequest();
			BigDecimal MangFeerate = new BigDecimal(request.getParameter("mangFeerate"));
			BigDecimal parm = new BigDecimal(Integer.toString(1000));
			projParm.setMangFeerate(MangFeerate.divide(parm).floatValue());
			projParmBo.insert(projParm);
		}else{
			setObjValue(formproj0006, projParm);
			projParm.setLeverRate(leverRate);
			//修改信托管理费率
			HttpServletRequest request = ServletActionContext.getRequest();
			BigDecimal MangFeerate = new BigDecimal(request.getParameter("mangFeerate"));
			BigDecimal parm = new BigDecimal(Integer.toString(1000));
			projParm.setMangFeerate(MangFeerate.divide(parm).floatValue());
			projParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
			projParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
			projParmBo.update(projParm);
		}
		getObjValue(formproj0006, projParm);
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
		formproj0005 = formService.getFormData("proj0005");
		projParm = new ProjParm();
		projParm.setLeverRate(leverRate);
		projParm.setTxDate(txDate);
		projParm.setUpOpno(upOpno);
		projParm.setDeptNo(deptNo);
		projParm.setTruPayType(truPayType);
		projParm.setUpDate(upDate);
		projParm.setRgAppRate(rgAppRate);
		projParm.setProjName(projName);
		projParm.setOpNo(opNo);
		projParm.setFiller(filler);
		projParm.setProjNo(projNo);
		projParm.setBackDays(backDays);
		projParmBo.del(projParm);
		this.addActionMessage("删除成功");
		projParm = new ProjParm();
		Ipage ipage = this.getIpage();
		projParmList = (List) projParmBo.findByPage(ipage, projParm).getResult();
		return "list";
	}

	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		if(projNo==null || "".equals(projNo)){
			this.addActionMessage("请先在  项目信息 中补录小微项目编号！");
			return "   ";
		}else{
			formproj0006 = formService.getFormData("proj0006");
			projParm = new ProjParm();
			projParm.setLeverRate(leverRate);
			projParm.setTxDate(txDate);
			projParm.setUpOpno(upOpno);
			projParm.setDeptNo(deptNo);
			projParm.setTruPayType(truPayType);
			projParm.setUpDate(upDate);
			projParm.setRgAppRate(rgAppRate);
			projParm.setProjName(projName);
			projParm.setOpNo(opNo);
			projParm.setFiller(filler);
			projParm.setProjNo(projNo);
			projParm.setBackDays(backDays);
			
			
			projBase = new ProjBase();
			projBase.setProjId(projId);
			projBase.setProjNo(projNo);
			projBase = projBaseBo.getById(projBase);
			projParm = projParmBo.getById(projParm);
			
			if(projParm==null){
				projParm = new ProjParm();
				projParm.setBackDays(66);//回购天数阈默认值
				projParm.setFundEndDays(22);//资金到期兑付天数
				projParm.setProjEndDays(11);//项目到期天数
				projParm.setIntDays(10);  // 结息到期天数
				projParm.setOverDays(10);//逾期天数
				projParm.setMangFeerate(0.005f);//信托管理费率[默认为千分之五]
				projParm.setLeverRate(0.01f);
			}
			projParm.setProjNo(projNo);
			projParm.setProjName(projBase.getProjName());
			projParm.setDeptNo(User.getBrno(ServletActionContext.getRequest()));//登记部门
			projParm.setOpNo(User.getLoginid(ServletActionContext.getRequest()));//操作员
			projParm.setTxDate(User.getSys_date(ServletActionContext.getRequest()));//登记日期
			projParm.setUpDate(User.getSys_date(ServletActionContext.getRequest()));//修改日期
			projParm.setUpOpno(User.getLoginid(ServletActionContext.getRequest()));//修改人员
			getObjValue(formproj0006, projParm);
			return "detail";
		}
			
		
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formproj0006 = formService.getFormData("proj0006");
		 getFormValue(formproj0006);
		 validateFormData(formproj0006);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formproj0006 = formService.getFormData("proj0006");
		 getFormValue(formproj0006);
		 validateFormData(formproj0006);
  	}
	
	
	/**
	    * 根据项目显示所有的的配置 信息
	    * @return
	    * @throws Exception
	    */
	   public String listQuotaForCorp() throws Exception {
	   	ActionContext.initialize(ServletActionContext.getRequest(),
	       		ServletActionContext.getResponse());
	   	projParm = new ProjParm();
	   	projParm.setProjNo(projNo);
	   	Ipage ipage = this.getIpage();
	   	projParmList = (List) projParmBo.findByPageQuotaForCorp(ipage, projParm).getResult();
	   	return "list";
	   }
	
	public ProjParm getProjParm() {
		return projParm;
	}
	public void setProjParm(ProjParm  projParm) {
		this.projParm = projParm;
	}
	public List<ProjParm> getProjParmList() {
		return projParmList;
	}
	public void setProjParmList(List<ProjParm> projParmList) {
		this.projParmList = projParmList;
	}
	public ProjParmBo getProjParmBo() {
		return projParmBo;
	}
	public void setProjParmBo(ProjParmBo projParmBo) {
		this.projParmBo = projParmBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormproj0006() {
		return formproj0006;
	}
	public void setFormproj0006(FormData formproj0006) {
		this.formproj0006 = formproj0006;
	}
	public FormData getFormproj0005() {
		return formproj0005;
	}
	public void setFormproj0005(FormData formproj0005) {
		this.formproj0005 = formproj0005;
	}
	public void setLeverRate(Float leverRate){
		this.leverRate = leverRate;
	}		
	public void setTxDate(String txDate){
		this.txDate = txDate;
	}		
	public void setUpOpno(String upOpno){
		this.upOpno = upOpno;
	}		
	public void setDeptNo(String deptNo){
		this.deptNo = deptNo;
	}		
	public void setTruPayType(String truPayType){
		this.truPayType = truPayType;
	}		
	public void setUpDate(String upDate){
		this.upDate = upDate;
	}		
	public void setRgAppRate(Float rgAppRate){
		this.rgAppRate = rgAppRate;
	}			
	public void setProjName(String projName){
		this.projName = projName;
	}		
	public void setOpNo(String opNo){
		this.opNo = opNo;
	}		
	public void setFiller(String filler){
		this.filler = filler;
	}			
	public void setProjNo(String projNo){
		this.projNo = projNo;
	}		
	public void setBackDays(Integer backDays){
		this.backDays = backDays;
	}		
	public Float getLeverRate(){
		return leverRate;
	}
	public String getTxDate(){
		return txDate;
	}
	public String getUpOpno(){
		return upOpno;
	}
	public String getDeptNo(){
		return deptNo;
	}
	public String getTruPayType(){
		return truPayType;
	}
	public String getUpDate(){
		return upDate;
	}
	public Float getRgAppRate(){
		return rgAppRate;
	}
	public String getProjName(){
		return projName;
	}
	public String getOpNo(){
		return opNo;
	}
	public String getFiller(){
		return filler;
	}
	public String getProjNo(){
		return projNo;
	}
	public Integer getBackDays(){
		return backDays;
	}
	public ProjBase getProjBase() {
		return projBase;
	}
	public void setProjBase(ProjBase projBase) {
		this.projBase = projBase;
	}
	public ProjBaseBo getProjBaseBo() {
		return projBaseBo;
	}
	public void setProjBaseBo(ProjBaseBo projBaseBo) {
		this.projBaseBo = projBaseBo;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
}