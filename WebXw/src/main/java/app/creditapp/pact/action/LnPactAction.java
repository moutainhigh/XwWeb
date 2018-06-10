package  app.creditapp.pact.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.api.task.Task;

import app.creditapp.aft.bo.AftAssetRelBo;
import app.creditapp.aft.entity.AftAssetRel;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.creditapp.wkf.entity.WkfParm;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: LnPactAction.java
 * Description:
 **/

public class LnPactAction extends BaseFormBean {

	//页面传值
	private LnPact lnPact;
	private List<LnPact> lnPactList;
	private List<LnPact> lnPacttList;
	private int num;
	//注入LnPactBo
	private LnPactBo lnPactBo;
	private AftAssetRelBo aftAssetRelBo;
	private HttpServletRequest	request;
	List<WkfApprovalUser> wkfList;
	private List<Task> wkfTaskList;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private List tabList;
	
	private String query;
	private String pactId;	
	private String appId;	
	private String pactNo;
	private String brNo;		
	private String cifNo;
	private String assId;
	private String apprType;
	private String batchNo;
	private String projNo;
	private String lnType;
	private String url;
	private String formSts;
	private String dataSts;
	
	
	
	
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	private FormData formlnpact0001;
	private FormData formlnpact0002;
	private FormData formlnpact0003;
	private FormData formlnpact0004;
	private FormData formlnpact0005;
	private FormData formlnpact0021;
	private FormData formaft0123;
	private FormService formService = new FormService();
	
	public LnPactAction() {
		query = "";
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpact0005 = formService.getFormData("lnpact0005");
		lnPact = new LnPact();
		getFormValue(formlnpact0005);
		setObjValue(formlnpact0005, lnPact);
		lnPact.setLoginid(User.getLoginIdByAuth(this.getHttpRequest())); // 从session中获取权限
		Ipage ipage = this.getIpage();
		lnPactList = (List) lnPactBo.findByPage(ipage, lnPact).getResult();
		return "list";
	}
	public String findByPageForInterface() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpact0001 = formService.getFormData("lnpact0001");
		lnPact = new LnPact();
		getFormValue(formlnpact0001);
		setObjValue(formlnpact0001, lnPact);
		lnPact.setApprSts("01");
		Ipage ipage = this.getIpage();
		lnPactList = (List) lnPactBo.findByPage(ipage, lnPact).getResult();
		return "list";
	}
	public String findByPageForList() throws Exception {
//		formlnpact0004 = formService.getFormData("lnpact0004");
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
//		TaskQuery query =null;
//		query = WFUtil.getTaskService().createCustomQuery().candidate(userId, branchId); //工作流
//		
//		int firstResult = (ipage.getPageNo() - 1) * 10000;
//		query.approveByLast(false);
		
//		 List<Task> wkfTaskList = query.page(firstResult, 10000).orderDesc(TaskQuery.PROPERTY_DB_CREATEDATE).list();
//		  lnPactList = new ArrayList<LnPact>();
//		  int count = 0;
//			for (int i = 0; i<wkfTaskList.size(); i++){
//				Task task = wkfTaskList.get(i);
//				lnPact = new LnPact();
//				getFormValue(formlnpact0004);
//				setObjValue(formlnpact0004, lnPact);
//				lnPact.setAppId(task.getAppId());//查单笔
//				lnPact.setBrNo(brNo);
//				lnPact.setProjNo(projNo);
//				lnPact.setLnType(lnType);
//				lnPact = lnPactBo.getByIdForAppId(lnPact);
//				if(lnPact!=null){
//					lnPactList.add(lnPact);
//					count++;
//				}
//			}
//			ipage.initPageCounts(new Integer[] { count });
		
		
		lnPact = new LnPact();
		formlnpact0004 = formService.getFormData("lnpact0004");
		getFormValue(formlnpact0004);
		setObjValue(formlnpact0004, lnPact);
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getWfOrgNo(getHttpRequest());
		lnPact.setUserId(userId);
		lnPact.setBranchId(branchId);
		lnPact.setBrNo(brNo);
		lnPact.setProjNo(projNo);
		lnPact.setLnType(lnType);		
		Ipage ipage = this.getIpage();
		lnPactList = (List) lnPactBo.findByPageForTask(ipage, lnPact).getResult();
		
		return "list";
	}	
	
	public String findByPageAppr() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		request= ServletActionContext.getRequest();
		formlnpact0001 = formService.getFormData("lnpact0001");
		lnPact = new LnPact();
		getFormValue(formlnpact0001);
		setObjValue(formlnpact0001, lnPact);
		String userId = User.getLoginid(getHttpRequest());
		lnPact.setLoginid(User.getLoginIdByAuth(this.getHttpRequest()));
		String branchId = User.getWfOrgNo(getHttpRequest());
			lnPactList = (List) lnPactBo.getByIdForApp(lnPact,getIpage(),userId,branchId);
			request.setAttribute("lnPactList",lnPactList);
		return "list";
	}
	
	public String findByPageForAppr() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		lnPactList = lnPactBo.findByPageForAppr(lnPact);
		for(int i=0;i<lnPactList.size();i++){ 
			lnPact = lnPactList.get(i);
			
			//组合流程变量，然后启动流程
			WkfParm parm = new WkfParm();
			parm.setApp_no(lnPact.getPactId());
			parm.setApp_type("01");//不良资产处置方案审批
			parm.setBr_no(User.getBrno(this.getHttpRequest()));
			parm.setBr_lev(User.getFicode(this.getHttpRequest()));
			
			StringBuilder wfAppValue = new StringBuilder();
			wfAppValue.append("客户号:"+lnPact.getCifNo());
			wfAppValue.append(",客户名称:"+lnPact.getCifName());
			wfAppValue.append(",客户经理:"+User.getDisplayName(getHttpRequest()));

			parm.setWf_app_value(wfAppValue.toString());//业务串
			
			String nextDesc =lnPactBo.doSubmitUpdate(parm,lnPact,User.getLoginid(getHttpRequest()), User.getBrno(getHttpRequest()));
	    }  
		return "list";
	}
	public String findByPageForPop() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpact0003 = formService.getFormData("lnpact0003");
		lnPact = new LnPact();
		getFormValue(formlnpact0003);
		setObjValue(formlnpact0003, lnPact);
		Ipage ipage = this.getIpage();
		lnPact.setPactId(assId);
		lnPactList = (List) lnPactBo.findByPageForRel(ipage, lnPact).getResult();
		return "list";
	}
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpact0002 = formService.getFormData("lnpact0002");
		return "input";
	}
	
	/**
	 * 新增保存操作
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpact0002 = formService.getFormData("lnpact0002");
		getFormValue(formlnpact0002);
		lnPact = new LnPact();
		setObjValue(formlnpact0002, lnPact);
		lnPact.setTxDate(User.getSys_date(ServletActionContext.getRequest()));
		lnPact.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		lnPactBo.insert(lnPact);
		getObjValue(formlnpact0002, lnPact);
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
		formlnpact0002 = formService.getFormData("lnpact0002");
		getFormValue(formlnpact0002);
		lnPact = new LnPact();
		setObjValue(formlnpact0002, lnPact);
		lnPactBo.update(lnPact);
		getObjValue(formlnpact0002, lnPact);
		this.addActionMessage("操作成功");
		return "detail";
	}
	
	public String doReplace(){
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		Map<String,String> map = new ConcurrentHashMap();
		map.put("pactId", pactId);
//		System.out.println("============"+pactId);
		lnPactBo.doReplace(map);
		String[] pactIds = pactId.split(",");
		for(int i=0;i<pactIds.length;i++){
			if(!"".equals(pactIds[i]) && pactIds[i] != null){
				pactId = pactIds[i].split("=")[1];
				lnPact = new LnPact();
				lnPact.setPactId(pactId);
				lnPact = lnPactBo.getById(lnPact);
				
				AftAssetRel aftAssetRel = new AftAssetRel();
				aftAssetRel.setAssId(assId);
				aftAssetRel.setPactId(pactId);
				aftAssetRel.setPactNo(lnPact.getPactNo());
				aftAssetRel.setBrNo(lnPact.getBrNo());
				aftAssetRel.setCifNo(lnPact.getCifNo());
				aftAssetRelBo.insert(aftAssetRel);
			}
			
		}
		this.addActionMessage("保存成功");
		return "list";
	}
	
	public String doReplaceAjax() throws IOException{
		this.getHttpResponse().setContentType("text/html;charset=UTF-8");
		
		Map<String,String> map = new ConcurrentHashMap();
		map.put("pactId", pactId);
		lnPactBo.doReplace(map);
		String[] pactIds = pactId.split("@");
		for(int i=0;i<pactIds.length;i++){
			if(!"".equals(pactIds[i]) && pactIds[i] != null){
//				pactId = pactIds[i].split("=")[1];
//				pactId = pactIds[i];
				lnPact = new LnPact();
				lnPact.setPactId(pactIds[i]);
				lnPact = lnPactBo.getById(lnPact);
				
				AftAssetRel aftAssetRel = new AftAssetRel();
				aftAssetRel.setAssId(assId);
				aftAssetRel.setPactId(pactIds[i]);
				aftAssetRel.setPactNo(lnPact.getPactNo());
				aftAssetRel.setBrNo(lnPact.getBrNo());
				aftAssetRel.setCifNo(lnPact.getCifNo());
				aftAssetRelBo.insert(aftAssetRel);
			}
		}
		PrintWriter pw = this.getHttpResponse().getWriter();
		pw.write("success");
		return null;
	}
	
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formlnpact0001 = formService.getFormData("lnpact0001");
		lnPact = new LnPact();
		lnPact.setPactId(pactId);
		lnPactBo.del(lnPact);
		this.addActionMessage("删除成功");
		lnPact = new LnPact();
		Ipage ipage = this.getIpage();
		lnPactList = (List) lnPactBo.findByPage(ipage, lnPact).getResult();
		return "list";
	}
    //通过合同号获取关联人信息，共同借款人信息
	
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		lnPact = new LnPact();
		lnPact.setPactId(pactId);
		lnPact.setPactNo(pactNo);
		lnPact = lnPactBo.getById(lnPact);
		pactNo = lnPact.getPactNo();
		pactId = lnPact.getPactId();
		brNo = lnPact.getBrNo();
		lnPact.setPactNo(pactNo);
		lnPact.setBrNo(brNo);
		String appId = lnPact.getAppId();
		//获取机构号
//		String brNo = lnPact.getBrNo();
		
		tabList = new ArrayList();
		String[] tab = null; 
		
		tab = new String[2];
		tab[0] = "合同详细信息";
		tab[1] = "LnPactAction_getById.action?pactId=" + pactId+ "&query="+ query+ "&formSts="+formSts+"&pactNo="+pactNo;
		tabList.add(tab);
			
//		tab = new String[2];
//		tab[0] = "押品信息";
//		tab[1] = "LnGageMidAction_listQuotaForLn.action?appId=" + appId+ "&query="+ query + "";
//		tabList.add(tab);
		
		/*
		tab = new String[2];
		tab[0] = "账户信息";
		tab[1] = "LnAcctAction_listQuotaForLn.action?appId=" + appId+"&query="+ query + "";
		tabList.add(tab);
				
		tab = new String[2];
		tab[0] = "借款关联人";
		tab[1] = "LnRelAction_findByPageForRead.action?appId=" + appId+ "&query="+ query + "";
		tabList.add(tab);
				
		tab = new String[2];
		tab[0]="共同借款人信息";
		tab[1]="LnComAction_findByPageRead.action?appId="+appId+ "&query="+ query + "";
		tabList.add(tab);
			
		tab = new String[2];
		tab[0] = "还款计划";
		tab[1] = "AcLnRepayPlnAction_findByPageRead.action?pactNo=" + pactNo+ "&query="+ query + "";
		tabList.add(tab);
				
		tab = new String[2];
		tab[0] = "还款明细";
		tab[1] = "AcLnPmLogAction_findByPageForRead.action?pactNo=" + pactNo+ "&query="+ query + "&dataSts"+dataSts+"&brNo="+brNo;
		tabList.add(tab);
			
		tab = new String[2];
		tab[0] = "欠款明细";
		tab[1] = "AcLnLoAction_findByPageForRead.action?pactNo=" + pactNo+ "&query="+ query + "&dataSts"+dataSts+"&brNo="+brNo;
		tabList.add(tab);
				
		tab = new String[2];
		tab[0] = "回访信息";
		tab[1] = "AftCheckAction_findByPageRead.action?pactNo=" + pactNo+ "&query="+query+"&brNo="+brNo;
		tabList.add(tab);

		tab = new String[2];
		tab[0] = "业务流水";
		tab[1] = "AcTraceLogAction_findByPageForRead.action?pactNo="+pactNo+"&brNo="+brNo;
		tabList.add(tab);
		
//		tab = new String[2];
//		tab[0] = "影像材料";
//		tab[1] = "creditapp/cif/CifPersInf_ShowView.jsp?pactNo="+pactNo+"&brNo="+brNo;
//		tabList.add(tab);
		
		tab = new String[2];   	
		tab[0] = "影像展示菜单";
		tab[1] = "ImgPathAction_jump.action?transNo=" +pactNo+"&brNo="+brNo+ "&query="+ query + "";
		tabList.add(tab);	*/
		
		return "tab";
	}
	
	/**
	 * @作者 DHCC-ZLC
	 * @日期 2016-8-5
	 * @描述
	 */	
	public String getTab_360() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		lnPact = new LnPact();
		lnPact.setPactId(pactId);
		lnPact.setPactNo(pactNo);
		lnPact = lnPactBo.getById(lnPact);
		String appId = lnPact.getAppId();
		//获取机构号
		String brNo = lnPact.getBrNo();
		query="query";
		tabList = new ArrayList();
		String[] tab = null; 
		
		tab = new String[2];
		tab[0] = "合同详细信息";
		tab[1] = "LnPactAction_getById.action?pactId=" + pactId+ "&query="+ query;
		tabList.add(tab);
			
		tab = new String[2];
		tab[0] = "押品信息";
		tab[1] = "LnGageMidAction_listQuotaForLn.action?appId=" + appId+ "&query="+ query + "";
		tabList.add(tab);
		
		tab = new String[2];
		tab[0] = "账户信息";
		tab[1] = "LnAcctMidAction_listQuotaForLn.action?appId=" + appId+"&query="+ query + "";
		tabList.add(tab);
				
		tab = new String[2];
		tab[0] = "借款关联人";
		tab[1] = "LnRelAction_findByPage.action?appId=" + appId+ "&query="+ query + "";
		tabList.add(tab);
				
		tab = new String[2];
		tab[0]="共同借款人信息";
		tab[1]="LnComAction_findByPage.action?appId="+appId+ "&query="+ query + "";
		tabList.add(tab);
			
		tab = new String[2];
		tab[0] = "还款计划";
		tab[1] = "AcLnRepayPlnAction_findByPage.action?pactNo=" + pactId+ "&query="+ query + "";
		tabList.add(tab);
				
		tab = new String[2];
		tab[0] = "还款明细";
		tab[1] = "AcLnPmLogAction_findByPage.action?pactNo=" + pactId+ "&query="+ query + "";
		tabList.add(tab);
			
		tab = new String[2];
		tab[0] = "欠款明细";
		tab[1] = "AcLnLoAction_findByPage.action?pactNo=" + pactId+ "&query="+ query +"&dataSts"+dataSts;
		tabList.add(tab);
				
		tab = new String[2];
		tab[0] = "回访信息";
		tab[1] = "AftCheckAction_findByPagefortab.action?pactNo=" + pactId+ "&query="+ query + "";
		tabList.add(tab);

		tab = new String[2];
		tab[0] = "冲账管理";
		tab[1] = "AcTraceLogAction_findByPage.action?pactNo="+pactNo+"&brNo="+brNo;
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
		formlnpact0002 = formService.getFormData("lnpact0002");
		lnPact = new LnPact();
		lnPact.setPactId(pactId);
		lnPact.setPactNo(pactNo);
		lnPact = lnPactBo.getById(lnPact);
		ServletActionContext.getRequest().setAttribute("ctd", cifNo);
		getObjValue(formlnpact0002, lnPact);
		return "detail";
	}
	public String getByIdForAppId() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpact0002 = formService.getFormData("lnpact0002");
		lnPact = new LnPact();
		lnPact.setPactId(appId);
		lnPact = lnPactBo.getByIdForAppId(lnPact);
		getObjValue(formlnpact0002, lnPact);
		return "detail";
	}	
	/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getByIdDtl() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		formlnpact0002 = formService.getFormData("lnpact0002");
		lnPact = new LnPact();
		lnPact.setPactId(pactId);
		lnPact = lnPactBo.getById(lnPact);
		getObjValue(formlnpact0002, lnPact);
		query="query";
		return "detail";
	}
	
	/**
	 * 新增保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnpact0002 = formService.getFormData("lnpact0002");
		 getFormValue(formlnpact0002);
		 validateFormData(formlnpact0002);
   	}
   
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 formlnpact0002 = formService.getFormData("lnpact0002");
		 getFormValue(formlnpact0002);
		 validateFormData(formlnpact0002);
  	}
	
    /**
     * 根据客户号显示所有的额度合同
     * @return
     * @throws Exception
     */
    public String listQuotaForCif() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	lnPact = new LnPact();
    	lnPact.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	lnPactList = (List) lnPactBo.findByPageQuotaForCif(ipage, lnPact).getResult();
    	return "list";
    }
    /**
     * 根据客户号显示合同列表
     * @return
     * @throws Exception
     */
    public String listForCif() throws Exception {
    	ActionContext.initialize(ServletActionContext.getRequest(),
        		ServletActionContext.getResponse());
    	lnPact = new LnPact();
    	lnPact.setCifNo(cifNo);
    	Ipage ipage = this.getIpage();
    	lnPactList = (List) lnPactBo.findByPageQuotaForCif(ipage, lnPact).getResult();
    	return "list";
    }
	
	public LnPact getLnPact() {
		return lnPact;
	}
	public void setLnPact(LnPact  lnPact) {
		this.lnPact = lnPact;
	}
	public List<LnPact> getLnPactList() {
		return lnPactList;
	}
	public void setLnPactList(List<LnPact> lnPactList) {
		this.lnPactList = lnPactList;
	}
	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}
	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormlnpact0002() {
		return formlnpact0002;
	}
	public void setFormlnpact0002(FormData formlnpact0002) {
		this.formlnpact0002 = formlnpact0002;
	}
	public FormData getFormlnpact0001() {
		return formlnpact0001;
	}
	public void setFormlnpact0001(FormData formlnpact0001) {
		this.formlnpact0001 = formlnpact0001;
	}
	public void setPactId(String pactId){
		this.pactId = pactId;
	}		
	public String getPactId(){
		return pactId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAssId() {
		return assId;
	}
	public void setAssId(String assId) {
		this.assId = assId;
	}
	public FormData getFormlnpact0003() {
		return formlnpact0003;
	}
	public void setFormlnpact0003(FormData formlnpact0003) {
		this.formlnpact0003 = formlnpact0003;
	}
	public FormData getFormlnpact0021() {
		return formlnpact0021;
	}
	public void setFormlnpact0021(FormData formlnpact0021) {
		this.formlnpact0021 = formlnpact0021;
	}
	public FormData getFormaft0123() {
		return formaft0123;
	}
	public void setFormaft0123(FormData formaft0123) {
		this.formaft0123 = formaft0123;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public AftAssetRelBo getAftAssetRelBo() {
		return aftAssetRelBo;
	}
	public void setAftAssetRelBo(AftAssetRelBo aftAssetRelBo) {
		this.aftAssetRelBo = aftAssetRelBo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public List<WkfApprovalUser> getWkfList() {
		return wkfList;
	}
	public void setWkfList(List<WkfApprovalUser> wkfList) {
		this.wkfList = wkfList;
	}
	public List<Task> getWkfTaskList() {
		return wkfTaskList;
	}
	public void setWkfTaskList(List<Task> wkfTaskList) {
		this.wkfTaskList = wkfTaskList;
	}
	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}
	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}
	public String getApprType() {
		return apprType;
	}
	public void setApprType(String apprType) {
		this.apprType = apprType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getLnType() {
		return lnType;
	}
	public void setLnType(String lnType) {
		this.lnType = lnType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List getTabList() {
		return tabList;
	}
	public void setTabList(List tabList) {
		this.tabList = tabList;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public List<LnPact> getLnPacttList() {
		return lnPacttList;
	}
	public void setLnPacttList(List<LnPact> lnPacttList) {
		this.lnPacttList = lnPacttList;
	}
	public String getFormSts() {
		return formSts;
	}

	public String getDataSts() {
		return dataSts;
	}
	public void setDataSts(String dataSts) {
		this.dataSts = dataSts;
	}
	public void setFormSts(String formSts) {
		this.formSts = formSts;
	}
	public FormData getFormlnpact0004() {
		return formlnpact0004;
	}
	public void setFormlnpact0004(FormData formlnpact0004) {
		this.formlnpact0004 = formlnpact0004;
	}
	public FormData getFormlnpact0005() {
		return formlnpact0005;
	}
	public void setFormlnpact0005(FormData formlnpact0005) {
		this.formlnpact0005 = formlnpact0005;
	}
	
}