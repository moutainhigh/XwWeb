package app.creditapp.approve.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.Execution;
import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.task.Task;

import app.creditapp.approve.bo.ApproveLoanBo;
import app.creditapp.approve.entity.Anpact;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.entity.SysGlobal;
import app.creditapp.ln.bo.LnApplyRegBo;
import app.creditapp.ln.entity.LnApplyReg;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.ln.worker.WorkUtils;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.proj.bo.ProjBaseBo;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.redis.util.RedisUtil;
import app.creditapp.wkf.bo.WfTaskBo;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkf.entity.WfTask;
import app.util.User;
import app.util.toolkit.Ipage;
import redis.clients.jedis.Jedis;

public class ApproveLoanAction extends BaseFormBean {
	
	private LnPact lnPact;
	private LnPactBo lnPactBo;
	private List<LnPact> lnPactList;
	private FormData formapprove1001;
	private FormData formapprove1002;
	private FormData formlnpact0001;
	private FormService formService = new FormService();
	private String query;
	private LnApprIdea lnApprIdea;
	private CorpBase corpBase;
	private ProjBase projBase;
	private String apprType;
	private String taskIds;
	private String lnType;
	private String appIds;
	private String processIds;
	private String brNo;
	private String batchNo;
	private String url;
	private String taskId;
	private String processId;
	private String apprDesc;
	private String apprIdea;
	private HttpServletRequest	request;
	private ApproveLoanBo approveLoanBo;
	private CorpBaseBo corpBaseBo;
	private ProjBaseBo projBaseBo;
	private WfTaskBo wfTaskBo;
	private List tabList;
	private String projNo;
	private String abc;
	private LnApplyRegBo lnApplyRegBo;
//	private SysGlobalDAO sysGlobalDao;[
	private LnApplyReg lnApplyReg;
	private SysGlobal sysGlobal;
	private String pactNo;
	public String getPactNo() {
		return pactNo;
	}

	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}

	public LnApplyReg getLnApplyReg() {
		return lnApplyReg;
	}

	public void setLnApplyReg(LnApplyReg lnApplyReg) {
		this.lnApplyReg = lnApplyReg;
	}

	public SysGlobal getSysGlobal() {
		return sysGlobal;
	}

	public void setSysGlobal(SysGlobal sysGlobal) {
		this.sysGlobal = sysGlobal;
	}
//
//	public SysGlobalDAO getSysGlobalDao() {
//		return sysGlobalDao;
//	}
//
//	public void setSysGlobalDao(SysGlobalDAO sysGlobalDao) {
//		this.sysGlobalDao = sysGlobalDao;
//	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}
 
	public String getPrctNo() {
		return prctNo;
	}

	public void setPrctNo(String prctNo) {
		this.prctNo = prctNo;
	}

	private String prctNo;//前台页面传过来的合同编号
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ApproveLoanAction() {
		query = "";
	}
	
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formapprove1001 = formService.getFormData("approve1001");
		if(appIds.equals("")||appIds==null){
			
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getWfOrgNo(getHttpRequest());
		Ipage ipage = this.getIpage();
		
		lnPact = new LnPact();
		lnPact.setBrNo(brNo);
		lnPact.setProjNo(projNo);
		lnPact.setLnType(lnType);
		lnPact.setUserId(userId);
		lnPact.setBranchId(branchId);
		ipage.setPageSize(10000);
		List<LnPact> lnPactList = lnPactBo.getWfTaskList(lnPact);
		
		taskIds = "";
		processIds = "";
		for(LnPact lnPact : lnPactList){
			taskIds = taskIds + "@" + lnPact.getDbid();
			processIds = processIds + "@" + lnPact.getProcessId();
		}
		}
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setApprOp(User.getLoginid(this.getHttpRequest()));
		lnApprIdea.setApprTime(User.getSys_date(this.getHttpRequest()));
		lnApprIdea.setTaskId(taskIds);
		lnApprIdea.setProcessId(processIds);
		lnApprIdea.setBrNo(brNo);
		lnApprIdea.setPactNo(pactNo);
		corpBase = new CorpBase();
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		abc = ServletActionContext.getRequest().getParameter("abc");
		lnApprIdea.setBrName(corpBase.getBrName());
		lnApprIdea.setProjName(projBase.getProjName());
		// lnPactList = new ArrayList<LnPact>();
		// for(int i = 0; i<lnPactList.size();i ++){
		// lnApprIdea.setTaskId(lnPactList.get(i).getTaskId());
		// }
		getObjValue(formapprove1001, lnApprIdea);
		return "success";
	}
	public String inputForGo() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formapprove1002 = formService.getFormData("approve1002");
		if(appIds.equals("")||appIds==null){
			
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getWfOrgNo(getHttpRequest());
		Ipage ipage = this.getIpage();
		
		lnPact = new LnPact();
		lnPact.setBrNo(brNo);
		lnPact.setProjNo(projNo);
		lnPact.setLnType(lnType);
		lnPact.setUserId(userId);
		lnPact.setBranchId(branchId);
		ipage.setPageSize(10000);
		List<LnPact> lnPactList = lnPactBo.getWfTaskList(lnPact);
		
		taskIds = "";
		processIds = "";
		for(LnPact lnPact : lnPactList){
			taskIds = taskIds + "@" + lnPact.getDbid();
			processIds = processIds + "@" + lnPact.getProcessId();
		}
		}
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setApprOp(User.getLoginid(this.getHttpRequest()));
		lnApprIdea.setApprTime(User.getSys_date(this.getHttpRequest()));
		lnApprIdea.setTaskId(taskIds);
		lnApprIdea.setProcessId(processIds);
		lnApprIdea.setBrNo(brNo);
		corpBase = new CorpBase();
		corpBase.setBrNo(brNo);
		corpBase = corpBaseBo.getById(corpBase);
		projBase = new ProjBase();
		projBase.setProjNo(projNo);
		projBase = projBaseBo.getById(projBase);
		abc = ServletActionContext.getRequest().getParameter("abc");
		System.out.println(abc);
		if(abc=="1"||abc.equals("1")){
			lnApprIdea.setApprIdea("02");
			lnApprIdea.setApprDesc("审批不通过");
		}else{
			lnApprIdea.setApprIdea("01");
			lnApprIdea.setApprDesc("审批通过");
		}
		lnApprIdea.setBrName(corpBase.getBrName());
		lnApprIdea.setProjName(projBase.getProjName());
		// lnPactList = new ArrayList<LnPact>();
		// for(int i = 0; i<lnPactList.size();i ++){
		// lnApprIdea.setTaskId(lnPactList.get(i).getTaskId());
		// }
		getObjValue(formapprove1002, lnApprIdea);
		return "success";
	}
	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formapprove1001 = formService.getFormData("approve1001");
		getFormValue(formapprove1001);
		lnApprIdea = new LnApprIdea();
		lnPact = new LnPact();//apprIdea
		setObjValue(formapprove1001, lnApprIdea);
		lnApprIdea.setApprType("02");
		//String pactNo = "tianmaoo001";
		//lnApprIdea.setPactNo(pactNo);
		//String t = lnApplyRegbo.getByNo(lnApprIdea);
        //Integer t1 = Integer.parseInt(t);
	
		
		//从第三方获取到的数据br_no,pact_no
		
		Connection  conn = null;
		Statement stmt=null;
		ResultSet rs=null;
		
		lnApprIdea.setApprIdea(apprIdea);
		lnApprIdea.setApprDesc(apprDesc);
		lnPact.setTaskId(lnApprIdea.getTaskId());
		Result result = null;
		String s = lnApprIdea.getTaskId();
		String p = lnApprIdea.getProcessId();
		String[] array = s.split("@");
		String[] pro = p.split("@");
		//去合作方数据库中查询所需要的数据
	    List<Anpact> anpacts = new ArrayList<Anpact>();
		
		try {
			for (int i = 1; i < array.length; i++) {
				apprIdea = "01";  //审批写死，状态为01,
				taskId = array[i];
				processId = pro[i];
//				Task task = WFUtil.getTaskService().getTask(taskId);
				WfTask wfTask = new WfTask();
				wfTask.setTaskId(taskId);
				wfTask = wfTaskBo.findTaskByTaskId(wfTask);
				
				String appId = wfTask.getTaskAppId();
				lnPact =  new LnPact();
				Anpact  lp = new Anpact();
				lnPact.setAppId(appId);
				lnPact = lnPactBo.getByIdForAppr(lnPact);//根据申请ID去查询
				
				lp.setPact1No(lnPact.getPactNo());
				lp.setBr1No(lnPact.getBrNo());
				
				//审批try{}catch{}finally{}注释
				/*
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn=DriverManager.getConnection("jdbc:sqlserver://192.168.1.208:1433;DataBasename=INTRUST","ypex","ypEx_624=");  
		            stmt=conn.createStatement();  
		            rs=stmt.executeQuery("select * from V_XWXD_FD where LTrim(RTrim(CONTRACT_BH)) ="+"\'"+lnPact.getPactNo()+"\'");//contract_bh
		            if(rs!=null){
		            while(rs.next()){
		            	apprIdea = "01";
		            }
		            }
				} catch (ClassNotFoundException ex) {
					// TODO: handle exception
					System.out.println(ex);
				}  finally{  
		            try{  
		                if(rs!=null){  
		                    rs.close();  
		                }  
		                if(stmt!=null){  
		                    stmt.close();  
		                }  
		            if(conn!=null){  
		                conn.close();                 
		            }  
		            }catch(SQLException e){  
		                e.printStackTrace();  
		            }  
		        } 
				*/
				if("01".equals(apprIdea)){
					lp.setAPPR_STS("01");
					lnApprIdea.setApprIdea(apprIdea);
					result = approveLoanBo.doCommit(taskId, lnPact.getAppId(),
							lnPact.getProcessId(), User.getLoginid(this
									.getHttpRequest()), lnApprIdea, lnApprIdea
									.getApprIdea(), lnApprIdea.getApprDesc(), "",
							User.getLoginid(this.getHttpRequest()), "", User
									.getWkfUserRoleNames(getHttpRequest()), "");
					if(result.isProcessEnd()){
						if("01".equals(apprIdea)){
							// push入5号消息队列
							lnPact = lnPactBo.getById(lnPact);//合同id，合同编号，申请id，合作机构编号
							Jedis jedis = RedisUtil.getJedis();
					  		jedis.lpush(RedisUtil.QUEUE5, JSON.toJSONString(lnPact));
					  		jedis.close();
						}else if("02".equals(apprIdea)){
							String _del_result = WorkUtils.getInstance().proc_apply_del(appId);
							System.out.println("#####"+_del_result);
						}
					}
					
				}else if("02".equals(apprIdea)){
					lnApplyReg = new LnApplyReg();
					lnApplyReg.setBrNo(lnPact.getBrNo());
					lnApplyReg.setPactNo(lnPact.getPactNo());
					String t = lnApplyRegBo.getByNo(lnApplyReg);
					Integer Days = Integer.parseInt(t);
					if(Days>15){
						lp.setAPPR_STS("03");
					}else{
						lp.setAPPR_STS("02");
					}
				}
			
				if("02".equals(lp.getAPPR_STS())){
					this.addActionMessage("合同："+lp.getPact1No()+"审批中");
					if(i<array.length-1){
						continue;
					}else{
						return "list";
					}
				}else if("03".equals(lp.getAPPR_STS())){
					result = approveLoanBo.doCommit(taskId, lnPact.getAppId(),
							lnPact.getProcessId(), User.getLoginid(this
									.getHttpRequest()), lnApprIdea, 
									"02", lnApprIdea.getApprDesc(), "",//测试 中再 该回来 
							User.getLoginid(this.getHttpRequest()), "", User
									.getWkfUserRoleNames(getHttpRequest()), "");
					
					this.addActionMessage("合同:"+lp.getPact1No()+"审批失败");
					if(i<array.length-1){
						continue;
					}else{
						return "list";
					}
				}
				
				}
			
	/*		for(int i = 1; i < array.length; i++){
				//if(anpacts.get(i-1).getAPPR_STS().equals("01")){
				taskId = array[i];
				processId = pro[i];
				Task task = WFUtil.getTaskService().getTask(taskId);
				String appId = task.getAppId();
				lnPact =  new LnPact();
				Anpact  lp = new Anpact();
				lnPact.setAppId(appId);
				lnPact = lnPactBo.getByIdForAppr(lnPact);//根据申请ID去查询
				lnPact.setProcessId(processId);//设置工作流ID
				lnApprIdea.setApprIdea(apprIdea);
				String brNo = lnPact.getBrNo();
				
				result = approveLoanBo.doCommit(taskId, lnPact.getAppId(),
						lnPact.getProcessId(), User.getLoginid(this
								.getHttpRequest()), lnApprIdea, lnApprIdea
								.getApprIdea(), lnApprIdea.getApprDesc(), "",
						User.getLoginid(this.getHttpRequest()), "", User
								.getWkfUserRoleNames(getHttpRequest()), "");
				if(result.isProcessEnd()){
					if("01".equals(apprIdea)){
						// push入5号消息队列
						lnPact = lnPactBo.getById(lnPact);//合同id，合同编号，申请id，合作机构编号
						Jedis jedis = RedisUtil.getJedis();
				  		jedis.lpush(RedisUtil.QUEUE5, JSON.toJSONString(lnPact));
				  		jedis.close();
					}else if("02".equals(apprIdea)){
						String _del_result = WorkUtils.getInstance().proc_apply_del(appId);
						System.out.println("#####"+_del_result);
					}
				}
				
				//}
			}*/
		} catch (WorkflowException e) {
			return "input";
		} catch (Exception e) {
			// result = ExceptionHandler.handleException(e, result);
			e.printStackTrace();
			System.out.println(e);
			return "input";
		}//contract_bh
		// 提交完成之后不管审批同意与否之后的节点
		if(lnPact.getAppId()!=null){
		String nextNode = "END";
//		Execution execution = WFUtil.getExecutionService()
//				.findExecutionByAppId(lnPact.getAppId());
//		if (execution != null) {
//			String instanceId = execution.getProcessInstance().getId();
//			List<Task> taskList = WFUtil.getTaskService()
//					.findTasksByProcessInstanceId(instanceId);
//			if (taskList.size() > 0) {
//				nextNode = taskList.get(taskList.size() - 1).getDescription();
//			}
//		} else {
//			nextNode = "END";
//		}
		if(nextNode.equals("END")){
			WfTask wfTask = new WfTask();
			wfTask.setTaskAppId(lnPact.getAppId());
			wfTaskBo.updateToAPPROVE(wfTask);
			this.addActionMessage("合同:"+lnPact.getPactNo()+"审批通过" );
		}else{
			this.addActionMessage("提交成功,下一审批角色" + nextNode);
		}
		}else{
			this.addActionMessage("已提交，不能重复提交" );
		}
		return "list";
	}
	
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
		if (appIds != null && !appIds.equals("")) {
			tab = new String[2];
			tab[0] = "审批信息";
			tab[1] = "TaskAction_openApproveUrlForList.action?lnType=" + lnType
					+ "&projNo=" + projNo + "&brNo=" + brNo +"&url="+url+"&appIds="+appIds +"&query=" + query
					+ "";
			tabList.add(tab);
		} else {
			tab = new String[2];
			tab[0] = "审批信息";
			tab[1] = "TaskAction_openApproveUrl.action?lnType=" + lnType
					+ "&projNo=" + projNo + "&brNo=" + brNo + "&query=" + query
					+ "";
			tabList.add(tab);
		}
		
		tab = new String[2];
		tab[0] = "项目情况";
		tab[1] = "ProjBaseAction_getById.action?projNo=" + projNo+ "&query=query";
		tabList.add(tab);
		
		
		tab = new String[2];
		tab[0] = "合作机构情况";
		tab[1] = "CorpBaseAction_getById.action?brNo=" + brNo+ "&query=query"+"&backSts=1";
		tabList.add(tab);
		
		
		tab = new String[2];
		tab[0] = "合作机构评级";
		tab[1] = "CorpEvalAction_findByPageForRead.action?brNo=" + brNo+ "&formSts=1" ;
		tabList.add(tab);
		
		
		tab = new String[2];
		tab[0] = "当前贷款性质情况";
		tab[1] = "CorpBaseAction_showActionAmtForBal.action?lnType=" + lnType+ "&projNo="+projNo+"&query="+ query + "";
		tabList.add(tab);
		
		
		
	
		return "tab";
	}
	public LnApprIdea getLnApprIdea() {
		return lnApprIdea;
	}

	public void setLnApprIdea(LnApprIdea lnApprIdea) {
		this.lnApprIdea = lnApprIdea;
	}

	public void setFormapprove1001(FormData formapprove1001) {
		this.formapprove1001 = formapprove1001;
	}

	public FormData getFormapprove1001() {
		return formapprove1001;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getApprType() {
		return apprType;
	}

	public void setApprType(String apprType) {
		this.apprType = apprType;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public LnPact getLnPact() {
		return lnPact;
	}

	public void setLnPact(LnPact lnPact) {
		this.lnPact = lnPact;
	}

	public List<LnPact> getLnPactList() {
		return lnPactList;
	}

	public void setLnPactList(List<LnPact> lnPactList) {
		this.lnPactList = lnPactList;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}

	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}

	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ApproveLoanBo getApproveLoanBo() {
		return approveLoanBo;
	}

	public void setApproveLoanBo(ApproveLoanBo approveLoanBo) {
		this.approveLoanBo = approveLoanBo;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getApprDesc() {
		return apprDesc;
	}

	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
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

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getProcessIds() {
		return processIds;
	}

	public void setProcessIds(String processIds) {
		this.processIds = processIds;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public FormData getFormlnpact0001() {
		return formlnpact0001;
	}

	public void setFormlnpact0001(FormData formlnpact0001) {
		this.formlnpact0001 = formlnpact0001;
	}

	public String getLnType() {
		return lnType;
	}

	public void setLnType(String lnType) {
		this.lnType = lnType;
	}

	public String getAppIds() {
		return appIds;
	}

	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}

	public String getApprIdea() {
		return apprIdea;
	}

	public void setApprIdea(String apprIdea) {
		this.apprIdea = apprIdea;
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

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public FormData getFormapprove1002() {
		return formapprove1002;
	}

	public void setFormapprove1002(FormData formapprove1002) {
		this.formapprove1002 = formapprove1002;
	}

	public LnApplyRegBo getLnApplyRegBo() {
		return lnApplyRegBo;
	}

	public void setLnApplyRegBo(LnApplyRegBo lnApplyRegBo) {
		this.lnApplyRegBo = lnApplyRegBo;
	}

	public WfTaskBo getWfTaskBo() {
		return wfTaskBo;
	}

	public void setWfTaskBo(WfTaskBo wfTaskBo) {
		this.wfTaskBo = wfTaskBo;
	}
}
