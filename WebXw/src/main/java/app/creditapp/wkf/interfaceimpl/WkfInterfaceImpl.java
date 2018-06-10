package app.creditapp.wkf.interfaceimpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts2.ServletActionContext;

import com.dhcc.workflow.NodeType;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.ExecutionService;
import com.dhcc.workflow.api.TaskService;
import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.model.Transition;
import com.dhcc.workflow.pvm.internal.task.TaskConstants;
import com.dhcc.workflow.wfdl.internal.repository.WfdlDeployer;

import app.base.ServiceException;
import app.creditapp.wkf.AppConstant;
import app.creditapp.wkf.bo.AppWkfcfgBo;
import app.creditapp.wkf.bo.WkfApprovalRoleBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkf.entity.WkfApprovalRole;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.creditapp.wkf.entity.WkfParm;
import app.creditapp.wkfinterface.WkfInterface;
import app.util.User;

public class WkfInterfaceImpl implements WkfInterface {
	
	private WkfApprovalUserBo wkfApprovalUserBo;
	private AppWkfcfgBo appWkfcfgBo;
	private WkfApprovalRoleBo wkfApprovalRoleBo;

	/**
	 * 启动流程 processKey 流程key，obj 参数对象 ，appNo 申请号 ，opNo 操作员号 -- add by xrp
	 * 2013.4.2
	 */
	public String startProcess(String processKey, WkfParm obj, String appNo,
			String opNo) throws ServiceException, ClassNotFoundException {
		// 参数转换
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wf_app_id", appNo); // 业务申请编号
		map.put("wf_operator", opNo); // 操作员编号
		map.put("wf_app_value", obj.getWf_app_value());// 业务数据
		map.put("br_no", obj.getBr_no()); // 机构号
		map.put("cif_type", obj.getCif_type());// 客户类型
		map.put("prdt_no", obj.getPrdt_no());// 产品号
		map.put("grade", obj.getGrade());// 评定级别
		map.put("app_type", obj.getApp_type());// 申请类型
		map.put("amt", obj.getAmt());// 金额
		map.put("five_sts", obj.getFive_sts());// 五级状态
		map.put("vou_type", obj.getVou_type());// 担保方式
		map.put("vou_type_sub", obj.getVou_type_sub());//调整前五级状态
		map.put("auth_true", obj.getAuth_true());// 是否授信
		map.put("term_month", obj.getTerm_month());// 期限月
		map.put("county_max", obj.getCounty_max());// 到县阈值
		map.put("city_max", obj.getCity_max());// 到市阈值
		map.put("province_max", obj.getProvince_max());// 到省阈值
		map.put("br_lev", obj.getBr_lev());// 机构级别
		map.put("adv_grade", obj.getAdv_grade());//评级审批等级
		map.put("grade_pers", obj.getGrade_pers());//个人评级
		map.put("grade_corp", obj.getGrade_corp());//对公
		map.put("arti_sts", obj.getArti_sts());//调整前五级状态
		map.put("bad_five_sts", obj.getBad_five_sts());//不良上迁
		map.put("wkf_vou_type", obj.getWkf_vou_type());//是否信用贷款
		map.put("br_depart", obj.getBr_depart());//部门分类
		map.put("occur_type", obj.getOccur_type());//发生类型
		map.put("wkf_cif_type", obj.getWkf_cif_type());//客户类型
		map.put("prod_no", obj.getProd_no());//产品号
		
		map.put("better_five_sts", obj.getBetter_five_sts());//关注上迁正常
		map.put("down_five_sts", obj.getDown_five_sts());//关注上迁正常
		map.put("br_depart", obj.getBr_depart());//总行部门
		map.put("occur_type", obj.getOccur_type());//发生类型
		map.put("loan_oper_qua", obj.getLoan_oper_qua());//经营方式
		map.put("wkf_risk_type", obj.getWkf_risk_type());//授信分类

		
		if (obj.getApp_type().equals("01")) {
			map.put("auth_type", obj.getAuth_type());// 审批类型;例如:授信申请、授信调整
		} else if (obj.getApp_type().equals("5")) {
			map.put("prj_type", obj.getPrj_type());// 审批类型;例如:合作额度申请、合作额度调整
		}

		// 启动流程 返回流程ID，表示正常启动
		ExecutionService executionService = WFUtil.getExecutionService();
		String instanceId = null;
		try {
			if("8".equals(obj.getApp_type())){//报备审批流程启动者不是流程的执行人
				instanceId = executionService.startByKey(processKey, opNo, false,map).getId();
			}else{
				instanceId = executionService.startByKey(processKey, opNo, true,map).getId();
			}
		} catch (WorkflowException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instanceId;
	}
	
	/**
	 * 
	 * 功能描述：发回重审，提交，流程变量重新赋值（只修改可能会发生变化的，减少接口调用，提高效率）
	 * @param instanceId
	 * @param obj
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-24
	 * @修改日志：
	 */
	public Result doVarReset(String instanceId,WkfParm obj) throws ServiceException{
		Result result = new Result();
		try{
			ExecutionService sevice = WFUtil.getExecutionService();
			sevice.setVariable(instanceId,"wf_app_value", obj.getWf_app_value());// 业务数据
			sevice.setVariable(instanceId,"prdt_no", obj.getPrdt_no());// 产品号
			sevice.setVariable(instanceId,"grade", obj.getGrade());// 评定级别
			sevice.setVariable(instanceId,"amt", obj.getAmt());// 金额
			sevice.setVariable(instanceId,"five_sts", obj.getFive_sts());// 五级状态
			sevice.setVariable(instanceId,"vou_type", obj.getVou_type());// 担保方式
			sevice.setVariable(instanceId,"auth_true", obj.getAuth_true());// 是否授信
			sevice.setVariable(instanceId,"term_month", obj.getTerm_month());// 期限月
			sevice.setVariable(instanceId,"br_lev", obj.getBr_lev());//机构级别
			sevice.setVariable(instanceId,"grade_pers", obj.getGrade_pers());// 个人评级
			sevice.setVariable(instanceId,"grade_corp",  obj.getGrade_corp());// 对公评级
			sevice.setVariable(instanceId,"arti_sts", obj.getArti_sts());//调整前五级
			sevice.setVariable(instanceId,"arti_sts", obj.getArti_sts());//调整前五级
			sevice.setVariable(instanceId,"adv_grade", obj.getAdv_grade());//调整前五级
			sevice.setVariable(instanceId,"vou_type_sub", obj.getVou_type_sub());//担保细分
			sevice.setVariable(instanceId,"bad_five_sts", obj.getBad_five_sts());
			sevice.setVariable(instanceId,"better_five_sts", obj.getBetter_five_sts());
			sevice.setVariable(instanceId,"down_five_sts", obj.getDown_five_sts());
			sevice.setVariable(instanceId,"br_depart", obj.getBr_depart());//总行部门
			sevice.setVariable(instanceId,"occur_type", obj.getOccur_type());//发生类型
		}catch(Exception e){
			result.setException(e);
		}
		return result;
	}

	/**
	 * 
	 * 功能描述：得到是否允许追回
	 * 
	 * @param taskId
	 * @param tlrno
	 * @return
	 * @author xrp
	 * @date 2013-4-12
	 * @修改日志：
	 */
	public boolean recallTask(String taskId, String tlrno)
			throws ServiceException {
		try {
			return WFUtil.getTaskService().recall(taskId, tlrno);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Result doCommit(String taskId, String opinionType,
			String approvalOpinion, String transition, String opNo,
			String nextUser,String isBatchFlag,String nextBranch) throws ServiceException {
		if (AppConstant.OPINION_TYPE_ARREE.equals(opinionType))// 同意
			return agree(taskId, approvalOpinion, transition, nextUser, opNo,isBatchFlag,nextBranch);
		else if (AppConstant.OPINION_TYPE_DISARREE.equals(opinionType))// 不同意
			return disAgree(taskId, approvalOpinion, transition, nextUser, opNo);
		else if (AppConstant.OPINION_TYPE_REFUSE.equals(opinionType))// 否决
			return refuse(taskId, approvalOpinion, opNo);
		else if (AppConstant.OPINION_TYPE_ROLLBACK.equals(opinionType))// 退回
			return rollBack(taskId, approvalOpinion, transition,opNo);
		else if (AppConstant.OPINION_TYPE_RESTART.equals(opinionType))// 发回重审
			return rollbackToDefaultNode(taskId, approvalOpinion,opNo);
		return null;
	}

	/**
	 * 
	 * 功能描述：审批同意
	 * 
	 * @param taskId
	 * @param approvalOpinion
	 * @param transition
	 * @param nextUser
	 * @param opNo
	 * @return
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-17
	 * @修改日志：
	 */
	public Result agree(String taskId, String approvalOpinion,
			String transition, String nextUser, String opNo,String isBatchFlag,String nextBranch)
			throws ServiceException {
		Result result = new Result();
		String state = "";
		TaskService service = WFUtil.getTaskService();
		try {
			if("1".equals(isBatchFlag)){
				if(null==nextBranch||nextBranch.isEmpty()){
					state  = service.takeComplete(taskId, TaskConstants.PASS, approvalOpinion, transition, nextUser, opNo);
				}else{
					state = service.takeComplete(taskId, TaskConstants.PASS, approvalOpinion, transition, nextUser, opNo, nextBranch);
				}
			}else{
				if(null==nextBranch||nextBranch.isEmpty()){
					state = service.complete(taskId, TaskConstants.PASS,approvalOpinion, transition, nextUser, opNo);
				}else{
					state = service.takeComplete(taskId, TaskConstants.PASS, approvalOpinion, transition, nextUser, opNo, nextBranch);
				}
				
			}
			result.setProcessEnd(state);
			result.setIsPass("pass");
		} catch (Exception e) {
			e.printStackTrace();
			result.setException(e);
		}
		return result;
	}

	/**
	 * 
	 * 功能描述：审批不同意
	 * 
	 * @param taskId
	 * @param approvalOpinion
	 * @param transition
	 * @param nextUser
	 * @param opNo
	 * @return
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-17
	 * @修改日志：
	 */
	public Result disAgree(String taskId, String approvalOpinion,
			String transition, String nextUser, String opNo)
			throws ServiceException {
		Result result = new Result();
		try {
			String state = WFUtil.getTaskService().complete(taskId,
					TaskConstants.DISAGREE, approvalOpinion, transition,
					nextUser, opNo);
			result.setProcessEnd(state);

		} catch (Exception e) {
			result.setException(e);
		}
		return result;
	}

	/**
	 * 
	 * 功能描述：审批否决
	 * 
	 * @param taskId
	 * @param approvalOpinion
	 * @param transition
	 * @param nextUser
	 * @param opNo
	 * @return
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-17
	 * @修改日志：
	 */
	public Result refuse(String taskId, String approvalOpinion, String opNo)
			throws ServiceException {
		Result result = new Result();
		try {
			WFUtil.getTaskService().refuse(taskId, approvalOpinion, opNo);
			result.setProcessEnd(AppConstant.END_STATE);
		} catch (Exception e) {
			result.setException(e);
		}
		return result;
	}
	/**
	 * 
	 * 功能描述：根据条件查询本网点对应不同业务类型的审批流程（贷款审批）
	 * 
	 * @param appWkfcfg
	 * @return
	 * @throws ServiceException
	 * @author zsp
	 * @date 2013-09-26
	 * @修改日志：查询本网点的流程实例 2013-06-23
	 * @modify by sp 2013-12-3
	 */
	public String getWkfNo(String app_type, String prdt_no) throws ServiceException {
		AppWkfcfg appwkfinfo = new AppWkfcfg();
		String wkfNo = "";
		String brNo = User.getBrno(ServletActionContext.getRequest());
		try {
			appwkfinfo.setAppType(app_type);
			appwkfinfo.setPrdtNo("@"+prdt_no+"@");
			appwkfinfo.setBrNo(brNo);
			wkfNo = appWkfcfgBo.getByIdForLoan(appwkfinfo);
//			if( wkfNo != null && !"".equals(wkfNo) ) {
//				return wkfNo;
//			}else {/** 如果县联社没有给对应的网店配流程，则取查询省联社给县联社配置的审批流程**/
//				if(User.getOp_lev(ServletActionContext.getRequest()).equals("0")){//这哥们是分理处
//					appwkfinfo.setBrNo(tblorgdepartmentsbo.getByBrno(brNo).getUp_two());
//				}else if(User.getOp_lev(ServletActionContext.getRequest()).equals("1")){//信用社的哥们
//					appwkfinfo.setBrNo(tblorgdepartmentsbo.getByBrno(brNo).getUp_one());
//				}
//				wkfNo = appWkfcfgBo.getByIdForLoan(appwkfinfo);
//				if( wkfNo != null && !"".equals(wkfNo) ) {
//					return wkfNo;
//				}
//			}
			
			//如果县联社和省联社都没有配置审批流程则返回"null"字符串
			if ( wkfNo == null || "".equals(wkfNo) ) {
				throw new ServiceException("请联系管理员配置-本县贷款业务审批对应流程！");
			} 
			
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfNo;
	}

	/**
	 * 
	 * 功能描述：根据条件查询县联社对应不同业务类型的审批流程
	 * 
	 * @param appWkfcfg
	 * @return
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-7
	 * @修改日志：
	 * @modify by sp 2013-12-3
	 */
	public String getWkfNo(String app_type) throws ServiceException {
		AppWkfcfg appwkfinfo = new AppWkfcfg();
		String wkfNo = "";
		String brNo = User.getBrno(ServletActionContext.getRequest());
		try {
//			String upone = tblorgdepartmentsbo.getByBrno(
//					User.getBrno(ServletActionContext.getRequest()))
//					.getUp_one(); // 县
			
			appwkfinfo.setBrNo(brNo);
			appwkfinfo.setAppType(app_type);
			wkfNo = appWkfcfgBo.getById(appwkfinfo);
//			if(wkfNo!=null&&!"".equals(wkfNo)){
//				return wkfNo;
//			}else{/** 如果县联社没有给对应的网店配流程，则取查询省联社给县联社配置的审批流程**/
//				if(User.getBrlev(ServletActionContext.getRequest()).equals("0")){//这哥们是分理处
//					appwkfinfo.setBrNo(tblorgdepartmentsbo.getByBrno(brNo).getUp_two());
//				}else if(User.getBrlev(ServletActionContext.getRequest()).equals("1")){//信用社的哥们
//					appwkfinfo.setBrNo(tblorgdepartmentsbo.getByBrno(brNo).getUp_one());
//				}
//				wkfNo = appWkfcfgBo.getById(appwkfinfo);
//				if(wkfNo!=null&&!"".equals(wkfNo)){
//					return wkfNo;
//				}
//			}
			if (wkfNo == null || wkfNo == "") {
				wkfNo = "null";
				// throw new Exception("请联系管理员配置-本县对应业务审批流程！");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfNo;
	}

	/**
	 * 
	 * 功能描述：根据条件查询县联社对应不同业务类型的审批流程
	 * 
	 * @param appWkfcfg
	 * @return
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-7
	 * @修改日志：
	 * @modify by sp 2013-12-3
	 */
	public String getWkfNo(AppWkfcfg appWkfcfg) throws ServiceException {
		String wkfNo = "";
		try {
//			appWkfcfg.setBrNo(brNo);
			wkfNo = appWkfcfgBo.getById(appWkfcfg);
			if (wkfNo == null || wkfNo == "") {
				wkfNo = "null";
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfNo;
	}

	
	/**
	 * 
	 * 功能描述：审批退回
	 * 
	 * @param taskId
	 * @param approvalOpinion
	 * @param transition
	 * @param nextUser
	 * @param opNo
	 * @return
	 * @throws ServiceException
	 * @author xrp
	 * @date 2013-4-17
	 * @修改日志：
	 * @modify by sp 2013-11-02
	 */
	public Result rollBack(String taskId, String approvalOpinion,
			String transition,String operator) throws ServiceException {
		Result result = new Result();
		try {
			//WFUtil.getTaskService().rollback(taskId, approvalOpinion,transition);
			WFUtil.getTaskService().rollback(taskId, null, approvalOpinion, transition, operator);
		} catch (Exception e) {
			result.setException(e);
		}
		return result; 
	}

	/**
	 * 
	 * 功能描述：审批发回重审
	 * 
	 * @param taskId
	 * @param approvalOpinion
	 * @param transition
	 * @param nextUser
	 * @param opNo
	 * @return
	 * @throws ServiceException
	 * @修改日志：
	 */
	public Result rollbackToDefaultNode(String taskId, String approvalOpinion,String operator)
			throws ServiceException {
		Result result = new Result();
		try {
			//WFUtil.getTaskService().rollbackToDefaultNode(taskId,approvalOpinion);
			WFUtil.getTaskService().rollbackToDefaultNode(taskId, "sendback", approvalOpinion, operator);
		} catch (Exception e) {
			result.setException(e);
		}
		return result;
	}

	public String getSignTask() throws ServiceException {
		return NodeType.SIGNTASK;
	}

	public String getEndSts() throws ServiceException {
		return AppConstant.END_STATE;
	}

	public List<Transition> findNextTransition(String taskId)
			throws ServiceException {
		List<Transition> transitionList = WFUtil.getTaskService()
				.getTransitions(taskId);
		return transitionList;
	}

	public List<WkfApprovalUser> getAllList(WkfApprovalUser wkfApprovalUser)
			throws ServiceException {
		return wkfApprovalUserBo.getAllList(wkfApprovalUser);
	}

	public WkfApprovalRole getWkfRoleByRoleNo(WkfApprovalRole wkfApprovalRole) throws ServiceException {
		
		return wkfApprovalRoleBo.getById(wkfApprovalRole);
	}
	
	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public AppWkfcfgBo getAppWkfcfgBo() {
		return appWkfcfgBo;
	}

	public void setAppWkfcfgBo(AppWkfcfgBo appWkfcfgBo) {
		this.appWkfcfgBo = appWkfcfgBo;
	}

	public WkfApprovalRoleBo getWkfApprovalRoleBo() {
		return wkfApprovalRoleBo;
	}

	public void setWkfApprovalRoleBo(WkfApprovalRoleBo wkfApprovalRoleBo) {
		this.wkfApprovalRoleBo = wkfApprovalRoleBo;
	}
	
	/**
	 * @param oldProcessKey  原来的流程Key,  
	 * @param oldParam       原来的：用户,机构号，机构名称
	 * @param newParam       新的: 用户，机构号，机构名称, 
	 * @param templateFileName  所使用的流程模板文件绝对全路径
	 */
	public static void doChangeProcessDefinition(String oldProcessKey, String oldParam, String newParam, String templateFileName) {
		//Process Template File
		//String oldParam = "400001,40000,数据修改测试";//模板中的操作员管理员操作员号,机构号，机构名称
		//String newParams = "400001,40000,数据修改测试";//管理员操作员号,机构号，机构名称,最好不要超过10组
		
		String[] oldParams = oldParam.split(",");
		String[] newParams = newParam.split(",");
		String operatorKey = "author=\"" + oldParams[0] + "\"";
		String newOperator = "author=\"" + newParams[0] + "\"";
		String branchKey = "branch=\"" + oldParams[1] + "\"";
		String newBranch = "branch=\"" + newParams[1] + "\"";
		String branchNameKey = "branchName=\"" + oldParams[2] + "\"";
		String newBranchName = "branchName=\"" + newParams[2] + "\"";
		String candidateBranchKey = "candidate-branch=\"" + oldParams[1] + "\"";
		String newCandidateBranch = "candidate-branch=\"" + newParams[1] + "\"";
		
		
		String newProcessKey = getProcessKey(null);
		String processKey = "key=\"" + oldProcessKey + "\"";
		String newProcessKeyValue = "key=\"" + newProcessKey + "\"";
		
		String processNameKey = "name=\"" + oldProcessKey + "\"";
		String newProcessName = "name=\"" + newProcessKey + "\"";
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(templateFileName), "UTF-8"));
			String strLine = null;
			while( (strLine = br.readLine()) != null ) {
				if( strLine.indexOf(processKey) > -1 ) {
					strLine = strLine.replaceAll(processKey, newProcessKeyValue);
				}
				if( strLine.indexOf(processNameKey) > -1 ) {
					strLine = strLine.replaceAll(processNameKey, newProcessName);
				}
				
				if( strLine.indexOf(operatorKey) > -1 ) {
					strLine = strLine.replaceAll(operatorKey, newOperator);
				}
				if( strLine.indexOf(branchKey) > -1 ) {
					strLine = strLine.replaceAll(branchKey, newBranch);				
				}
				if( strLine.indexOf(branchNameKey) > -1 ) {
					strLine = strLine.replaceAll(branchNameKey, newBranchName);
				}
				if( strLine.indexOf(candidateBranchKey) > -1 ) {
					strLine = strLine.replaceAll(candidateBranchKey, newCandidateBranch);
				}
				sb.append(strLine).append("\r");
			}
			
		} catch(Exception e) {
			
		} finally {
			if( br != null ) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String templateProcessXML = sb.toString();
		System.out.println(templateProcessXML);
		//保存并更新流程
		WFUtil.getRepositoryService().create().addResourceFromString(newProcessKey + WfdlDeployer.wfdlExtension, templateProcessXML).deploy();
	}
	
	public static String getProcessKey(String name) {
		if( name == null || "".equals(name) || "null".equals(name) ) {
			return "process_" + getID();
		} else {
			return (name + "_" + getID());
		}
	}
	
	public static String getID()  {
		String value = fdf.format(calendar);
		return value;
	}

	public static void main(String[] args) {
		String[] datas = {
				"熊孝明1,000001,总行科技部1",
				"熊孝明2,000002,总行科技部2",
				"熊孝明3,000003,总行科技部3",
				"熊孝明4,000004,总行科技部4",
				"熊孝明5,000005,总行科技部5",
				"熊孝明6,000006,总行科技部6",
				"熊孝明7,000007,总行科技部7"
		};
		
		for(String value : datas) {
			doChangeProcessDefinition("process_201483017424585","李瑞萍,1628100000,焦作解放联社", value ,"f:\\template2.wfdl.xml");
		}
		
	} 
	
	private static FastDateFormat fdf = FastDateFormat.getInstance("yyMMddHHmmssSS");
	private static Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
	

}
