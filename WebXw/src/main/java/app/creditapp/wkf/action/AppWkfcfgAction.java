package  app.creditapp.wkf.action;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import app.creditapp.sys.entity.ProdBrno;
import app.creditapp.wkf.bo.AppWkfcfgBo;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WF;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.Deployment;

/**
* Title: AppWkfcfgAction.java
* Description:
**/
public class AppWkfcfgAction extends BaseFormBean {

	//页面传值
	private AppWkfcfg appWkfcfg;
	private ProdBrno prodBrno;
	private List<AppWkfcfg> appWkfcfgList;
	private List<ProdBrno> prodList;
	
	private int tpageNo;			//所在页数
	private int tpageSize;		//每页数据条数
	private String isEditReturn;//返回标志

	private String tappType;
	private String tbrNo;
	private String twkfNo;
	private String tbrName;


	//注入AppWkfcfgBo
	private AppWkfcfgBo appWkfcfgBo;
	
	private String query;
	private String view;
	private String id;
	private List<String> prdt_no;
	private List<String> prdtList;
	private String appType;	
	private String brNo;
	private String prdtNoList[];
	private String processKey;
	private FormData formwkfcfg001;
	private FormData formwkfcfg005;
	private FormData formwkfcfg003;
	private FormData formwkfcfg004;
	private FormService formService = new FormService();

	public AppWkfcfgAction() {
		formwkfcfg001 = formService.getFormData("wkfcfg001");
		formwkfcfg005 = formService.getFormData("wkfcfg005");
		formwkfcfg003 = formService.getFormData("wkfcfg003");
		formwkfcfg004 = formService.getFormData("wkfcfg004");
		query = "";
		view = "";
	}




	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	 public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		if(appWkfcfg==null){
		appWkfcfg = new AppWkfcfg();
		}
		appWkfcfg.setTxBrno(User.getBrno(this.getHttpRequest()));
		appWkfcfg.setTxBrnoName(User.getBrname(this.getHttpRequest()));
		getObjValue(formwkfcfg001,appWkfcfg);
		
		
		getFormValue(formwkfcfg001);
		setObjValue(formwkfcfg001, appWkfcfg);
		
//		if(StringUtils.isBlank(appWkfcfg.getBrNo())){
//			appWkfcfg.setBrNo(User.getBrno(this.getHttpRequest()));
//		}
		
		if(appWkfcfg.getBs_no()!="" && appWkfcfg.getBs_no()!=null){
			String brNo = appWkfcfg.getBs_no().split("-")[0];
			appWkfcfg.setBrNo(brNo);
		}
		Ipage ipage = this.getIpage();
		if("1".equals(isEditReturn)){
			  if(0!=this.tpageNo&&0!=this.tpageSize){
						ipage.setPageNo(this.tpageNo);
						ipage.setPageSize(this.tpageSize);
						this.setEadis_page(String.valueOf(this.tpageNo));
						this.setPageSize(String.valueOf(this.tpageSize));	
						appWkfcfg.setAppType(this.tappType);
						appWkfcfg.setBrNo(this.tbrNo);
//						appWkfcfg.setWkfNo(this.twkfNo);
//						appWkfcfg.setBrName(this.tbrName);
			 }
		}
		appWkfcfgList = (List) appWkfcfgBo.findByPage(ipage, appWkfcfg).getResult();
		if("1".equals(isEditReturn)){
		this.getObjValue(formwkfcfg001,appWkfcfg);
		}
		return "list";
	}
	
	/**
	 * 获取新增页面
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		if(StringUtils.isBlank(brNo)){
			brNo = User.getBrno(this.getHttpRequest());
			
		}
		if(appWkfcfg==null){
			appWkfcfg = new AppWkfcfg();
			}
		appWkfcfg.setBrNo("00000");
		appWkfcfg.setTxBrno(brNo);
		appWkfcfg.setAppType("1");
		appWkfcfg.setTxUserId(User.getLoginid(this.getHttpRequest()));
		appWkfcfg.setTxDate(User.getSys_date(this.getHttpRequest()));
		appWkfcfg.setTxModifyDate(User.getSys_date(this.getHttpRequest()));
		getObjValue(formwkfcfg004,appWkfcfg);
		String brLev = User.getFicode(this.getHttpRequest());
		this.getHttpRequest().setAttribute("brLev",brLev);
		//prodList = appWkfcfgBo.getProdList(brNo);
		return "input";
	}
	/**
	 * 暂停流程
	 * @return
	 * @throws Exception
	 */
	public String suspend() throws Exception{
		try{
			String id = getHttpRequest().getParameter(WF.PARAM_PROCESSDEF_ID);
			//key  processSts-1
			String processKey = getHttpRequest().getParameter("processKey");
			appWkfcfg=new AppWkfcfg();
			appWkfcfg.setProcessKey(processKey);
			appWkfcfg.setProcessSts("1");
			appWkfcfgBo.updateProcessStsByKey(appWkfcfg);
			this.addActionMessage("操作成功！");
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError("系统错误，停用失败!请联系管理员处理!");
		}
		
		return findByPage();
	}
	/**
	 * 启用流程
	 * @return
	 * @throws Exception
	 */
	public String activate() throws Exception{
		String id = getHttpRequest().getParameter(WF.PARAM_PROCESSDEF_ID);
		String processKey = getHttpRequest().getParameter("processKey");
		appWkfcfg = new AppWkfcfg();
		boolean isActived= false;
		try{
			List<Deployment> list = WFUtil.getRepositoryService().createDeploymentQuery().deploymentId(id).list();
			for(Deployment dp : list){
				if( dp.getId().equals(id) && !(Deployment.STATE_ACTIVE.equals(dp.getState())) ) {
					isActived = true;
					break;
				}
			}
			if(isActived){
				WFUtil.getRepositoryService().activate(id);
				appWkfcfg.setProcessKey(processKey);
				appWkfcfg.setProcessSts("0");
				appWkfcfgBo.updateProcessStsByKey(appWkfcfg);
				this.addActionMessage("操作成功！");
			}else{
				appWkfcfg.setProcessKey(processKey);
				appWkfcfg.setProcessSts("0");
				appWkfcfgBo.updateProcessStsByKey(appWkfcfg);
				this.addActionMessage("操作成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError("系统错误，启用失败!请联系管理员处理");
		}
		
		return findByPage();
	}
	
	
	public String getWkfProdList() throws Exception{
		String brNo = User.getBrno(this.getHttpRequest());
		prodList = appWkfcfgBo.getProdList(brNo);
		return "prodList";
	}
	
	
	
	/**
	 * 修改保存操作
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formwkfcfg005);
		appWkfcfg = new AppWkfcfg();
		setObjValue(formwkfcfg005, appWkfcfg);
		try{
			//如果修改返回的dbid和原来的dbid不一样则说明用户修改了流程
			appWkfcfg.setProcessSts("1");//未启用
			appWkfcfgBo.update(appWkfcfg,prdt_no);
			this.addActionMessage("操作成功");
			appWkfcfg.setTxBrno(User.getBrno(this.getHttpRequest()));
			appWkfcfg.setTxDate(User.getSys_date(this.getHttpRequest()));
			getObjValue(formwkfcfg005, appWkfcfg);
			//prodList = appWkfcfgBo.getProdList(User.getBrno(this.getHttpRequest()));
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError("修改发生错误，请联系管理员处理!");
		}
		return "detail";
	}
	
	public String insert() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		getFormValue(formwkfcfg004);
		appWkfcfg = new AppWkfcfg();
		setObjValue(formwkfcfg004, appWkfcfg);
		appWkfcfg.setTxBrno(User.getBrno(this.getHttpRequest()));
		appWkfcfg.setTxDate(User.getSys_date(this.getHttpRequest()));
		//第一次保存时默认processSts是未启用-1
		appWkfcfg.setProcessSts("1");
//		System.out.println("-------------------------------------ProcessId："+appWkfcfg.getProcessId());
//		System.out.println("-------------------------------------ProcessKey："+appWkfcfg.getProcessKey());
		appWkfcfgBo.insert(appWkfcfg,appWkfcfg.getPrdtNo());
		//保存之后启用流程
		//WFUtil.getRepositoryService().activate(appWkfcfg.getProcessId());
		
		this.addActionMessage("操作成功");
		getObjValue(formwkfcfg005, appWkfcfg);
		//brNo = User.getBrno(this.getHttpRequest());
		//prodList = appWkfcfgBo.getProdList(brNo);
//		appWkfcfg = new AppWkfcfg();
//		appWkfcfgList = appWkfcfgBo.getByProcessKey(appWkfcfg.getProcessKey());
		AppWkfcfg	appWkfcfg1=new AppWkfcfg();
		Ipage ipage = this.getIpage();
	
		appWkfcfgList = (List) appWkfcfgBo.findByPage(ipage, appWkfcfg1).getResult();
			return "list";
		
		
	}
	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		appWkfcfg = new AppWkfcfg();
		boolean isSuspend = false;
		String id = getHttpRequest().getParameter(WF.PARAM_PROCESSDEF_ID);
		List<Deployment> list = WFUtil.getRepositoryService().createDeploymentQuery().suspended().deploymentId(id).list();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId().equals(id)){
				isSuspend = true;
				break;
			}
		}
		try{
			if(isSuspend){
				WFUtil.getRepositoryService().delete(id);
			}else{
				WFUtil.getRepositoryService().suspend(id, false);
				WFUtil.getRepositoryService().delete(id);
			}
			appWkfcfg.setAppType(appType);
			appWkfcfg.setProcessKey(processKey);
			appWkfcfgBo.deleteByKeyAndType(appWkfcfg);
			this.addActionMessage("删除成功");
			AppWkfcfg	appWkfcfg1=new AppWkfcfg();
			Ipage ipage = this.getIpage();
		
			appWkfcfgList = (List) appWkfcfgBo.findByPage(ipage, appWkfcfg1).getResult();
			
			
			
		}catch(Exception e){
			this.addActionError("删除失败，请联系管理员");
		}
		return "list";
	}
	
	
		/**
	 * 查询操作
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		prdtList = new ArrayList<String>();
		appWkfcfg = new AppWkfcfg();
		if (brNo != null && !"".equals(brNo)) {
			appWkfcfg.setBrNo(brNo);
		} else {
			appWkfcfg.setBrNo(User.getBrno(this.getHttpRequest()));
		}
		appWkfcfg.setId(id);
		appWkfcfg = appWkfcfgBo.getById2(appWkfcfg);
		//appWkfcfg.setPrdtNo(appWkfcfg.getPrdtNo().replace("@", "|")+"|");
		if("1".equals(appWkfcfg.getAppType())){
			prodList = appWkfcfgBo.getProdList(User.getBrno(this.getHttpRequest()));
		}
//		if(appWkfcfg != null){
//			if(StringUtils.isNotBlank(appWkfcfg.getPrdtNo())){
//				prdtNoList = appWkfcfg.getPrdtNo().split("@");
//			}
//		}
//		if(prdtNoList !=null && prdtNoList.length>0){
//			for(int i=0;i<prdtNoList.length;i++){
//				if(StringUtils.isNotBlank(prdtNoList[i])){
//					prdtList.add(prdtNoList[i]);
//				}
//			}
//		}
		getObjValue(formwkfcfg005, appWkfcfg);
		this.tappType=appWkfcfg.getAppType();
		this.tbrNo=appWkfcfg.getBrNo();
		this.twkfNo=appWkfcfg.getProcessKey();
//		this.getHttpRequest().setAttribute("prodList", prodList);
//		this.getHttpRequest().setAttribute("dd", prdt_no);
		return "detail";
	}
	
	/**
	 * 修改保存操作校验
	 * @return
	 * @throws Exception
	 */
	public void validateUpdate(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 getFormValue(formwkfcfg005);
		 validateFormData(formwkfcfg005);
  	}
	
	public void validateInsert(){
		 ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		 appWkfcfg = new AppWkfcfg();
		 getFormValue(formwkfcfg004);
		 validateFormData(formwkfcfg004);
		 
 	}
	public AppWkfcfg getAppWkfcfg() {
		return appWkfcfg;
	}
	public void setAppWkfcfg(AppWkfcfg  appWkfcfg) {
		this.appWkfcfg = appWkfcfg;
	}
	public List<AppWkfcfg> getAppWkfcfgList() {
		return appWkfcfgList;
	}
	public void setAppWkfcfgList(List<AppWkfcfg> appWkfcfgList) {
		this.appWkfcfgList = appWkfcfgList;
	}
	public AppWkfcfgBo getAppWkfcfgBo() {
		return appWkfcfgBo;
	}
	public void setAppWkfcfgBo(AppWkfcfgBo appWkfcfgBo) {
		this.appWkfcfgBo = appWkfcfgBo;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public FormData getFormwkfcfg005() {
		return formwkfcfg005;
	}
	public void setFormwkfcfg002(FormData formwkfcfg005) {
		this.formwkfcfg005 = formwkfcfg005;
	}
	public FormData getFormwkfcfg001() {
		return formwkfcfg001;
	}
	public void setFormwkfcfg001(FormData formwkfcfg001) {
		this.formwkfcfg001 = formwkfcfg001;
	}
	public void setAppType(String appType){
		this.appType = appType;
	}		
	public String getAppType(){
		return appType;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public FormData getFormwkfcfg003() {
		return formwkfcfg003;
	}
	public String getProcessKey() {
		return processKey;
	}




	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}




	public void setFormwkfcfg003(FormData formwkfcfg003) {
		this.formwkfcfg003 = formwkfcfg003;
	}
	public String getView() {
		return view;
	}
	public String[] getPrdtNoList() {
		return prdtNoList;
	}

	public void setPrdtNoList(String[] prdtNoList) {
		this.prdtNoList = prdtNoList;
	}


	public void setView(String view) {
		this.view = view;
	}
	public ProdBrno getProdBrno() {
		return prodBrno;
	}


	public void setProdBrno(ProdBrno prodBrno) {
		this.prodBrno = prodBrno;
	}
	public List<ProdBrno> getProdList() {
		return prodList;
	}
	public void setProdList(List<ProdBrno> prodList) {
		this.prodList = prodList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getPrdt_no() {
		return prdt_no;
	}
	public void setPrdt_no(List<String> prdtNo) {
		prdt_no = prdtNo;
	}
	public List<String> getPrdtList() {
		return prdtList;
	}


	public void setPrdtList(List<String> prdtList) {
		this.prdtList = prdtList;
	}

	public FormData getFormwkfcfg004() {
		return formwkfcfg004;
	}


	public void setFormwkfcfg004(FormData formwkfcfg004) {
		this.formwkfcfg004 = formwkfcfg004;
	}


	public int getTpageNo() {
		return tpageNo;
	}




	public void setTpageNo(int tpageNo) {
		this.tpageNo = tpageNo;
	}




	public int getTpageSize() {
		return tpageSize;
	}




	public void setTpageSize(int tpageSize) {
		this.tpageSize = tpageSize;
	}




	public String getIsEditReturn() {
		return isEditReturn;
	}




	public void setIsEditReturn(String isEditReturn) {
		this.isEditReturn = isEditReturn;
	}




	public String getTappType() {
		return tappType;
	}




	public void setTappType(String tappType) {
		this.tappType = tappType;
	}




	public String getTbrNo() {
		return tbrNo;
	}




	public void setTbrNo(String tbrNo) {
		this.tbrNo = tbrNo;
	}




	public String getTwkfNo() {
		return twkfNo;
	}




	public void setTwkfNo(String twkfNo) {
		this.twkfNo = twkfNo;
	}




	public String getTbrName() {
		return tbrName;
	}




	public void setTbrName(String tbrName) {
		this.tbrName = tbrName;
	}


}