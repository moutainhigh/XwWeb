package app.creditapp.aft.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.creditapp.aft.bo.AftMessageAlarmBo;
import app.creditapp.aft.bo.AftRewFundBo;
import app.creditapp.aft.bo.AftRewPactBo;
import app.creditapp.aft.bo.AftRewProjBo;
import app.creditapp.aft.bo.AftRewRealBo;
import app.creditapp.aft.entity.AftRewFund;
import app.creditapp.aft.entity.AftRewPact;
import app.creditapp.aft.entity.AftRewProj;
import app.creditapp.aft.entity.AftRewReal;
import app.creditapp.aft.entity.aftMessage.AjaxData;
import app.creditapp.aft.entity.aftMessage.MessageAlarm;
import app.creditapp.aft.entity.aftMessage.MessageTask;
import app.creditapp.aft.entity.aftMessage.PasBigType;
import app.creditapp.aft.entity.aftMessage.PasSubType;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.aft.entity.aftMessage.SysTaskInfoArray;
import app.creditapp.aft.entity.aftMessage.SysTaskInfoResult;
import app.creditapp.aft.manager.ParmRewType;
import app.creditapp.fund.bo.FundBaseBo;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.DateUtil;
import app.util.toolkit.Ipage;

import com.alibaba.fastjson.JSON;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.TaskQuery;

public class AftMessageAlarmBoImpl implements AftMessageAlarmBo{
	private AftRewPactBo aftRewPactBo;
	private AftRewProjBo aftRewProjBo;
	private AftRewFundBo aftRewFundBo;
	private AftRewRealBo aftRewRealBo;
	private LnPactBo lnPactBo;
	private FundBaseBo fundBaseBo;
	
	public String initMessagePage(String userNo,String sendDate,boolean isRecent){
		AjaxData ajaxData = createAjaxData(userNo,sendDate,isRecent);
		return JSON.toJSONString(ajaxData);
	}
	
	@Override
	public AjaxData initAjaxData(String userNo,String sendDate, boolean isRecent) {
		return createAjaxData(userNo,sendDate,isRecent);
	}
	public void setFundBaseBo(FundBaseBo fundBaseBo) {
		this.fundBaseBo = fundBaseBo;
	}
	public AjaxData initAjaxDataForBetween(String userNo,String sendDate, String endDate) {
		return createAjaxDataForBetween(userNo,sendDate,endDate);
	}
	
	public void setAftRewPactBo(AftRewPactBo aftRewPactBo) {
		this.aftRewPactBo = aftRewPactBo;
	}

	public void setAftRewProjBo(AftRewProjBo aftRewProjBo) {
		this.aftRewProjBo = aftRewProjBo;
	}

	public void setAftRewFundBo(AftRewFundBo aftRewFundBo) {
		this.aftRewFundBo = aftRewFundBo;
	}

	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}
	

	/**
	 * @param aftRewRealBo the aftRewRealBo to set
	 */
	public void setAftRewRealBo(AftRewRealBo aftRewRealBo) {
		this.aftRewRealBo = aftRewRealBo;
	}

	private AjaxData createAjaxData(String userNo,String sendDate,boolean isRecent){
		AjaxData ajaxData = new AjaxData();
		MessageAlarm messageAlarm = createMessageAlarmFromData(userNo,sendDate,null,isRecent);
		ajaxData.setSumCount(messageAlarm.getSumCount());
		ajaxData.setPasAwareCount(0);
		ajaxData.setSysDate(sendDate);
		ajaxData.setPasBigType(createPasBigTypeList(messageAlarm));
		ajaxData.setPasSubType(createPasSubTypeList());
		ajaxData.setSysTaskInfoArray(createTaskInfoArray(messageAlarm));
		return ajaxData;
	}
	
	private AjaxData createAjaxDataForBetween(String userNo,String beginDate,String endDate){
		AjaxData ajaxData = new AjaxData();
		MessageAlarm messageAlarm = createMessageAlarmFromData(userNo,beginDate,endDate,false);
		ajaxData.setSumCount(messageAlarm.getSumCount());
		ajaxData.setPasAwareCount(0);
		ajaxData.setSysDate(beginDate);
		ajaxData.setPasBigType(createPasBigTypeList(messageAlarm));
		ajaxData.setPasSubType(createPasSubTypeList());
		ajaxData.setSysTaskInfoArray(createTaskInfoArray(messageAlarm));
		return ajaxData;
	}
	
	private SysTaskInfoArray createTaskInfoArray(MessageAlarm messageAlarm){
		SysTaskInfoArray infoArray = new SysTaskInfoArray();
		infoArray.setEndNum(15);
		infoArray.setHasNext(false);
		infoArray.setHasPrevious(false);
		infoArray.setOldEndNum(16);
		infoArray.setPageCounts(18);
		infoArray.setPageNo(1);
		infoArray.setPageSum(2);
		infoArray.setPageSize(15);
		infoArray.setStartRow(1);
		infoArray.setResult(createTaskInfoResultList(messageAlarm));
		return infoArray;
	}
	
	/**
	 * 根据日期获取消息面所有的数据库信息
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年8月3日
	 * @方法说明：
	 * @返回参数 MessageAlarm
	 */
	private MessageAlarm createMessageAlarmFromData(String userNo,String sysdate,String endDate,boolean isRecent){
		//待办任务
		//待审批数据
		List<MessageTask> messageTaskList = new ArrayList<MessageTask>();
		
		//apprType=03 黑名单 apprType=02 冲账审批 apprType=04 息费减免审批
		String branchId = "00000";
		WkfApprovalUser wkfUser = new WkfApprovalUser();
		wkfUser.setWkfUserName(userNo);
		wkfUser.setWkfBrNo(branchId);
//		TaskQuery query = WFUtil.getTaskService().createCustomQuery().candidate(userNo, branchId); //工作流
//		
//		int blackSum = (int)query.appType("03").count();
//		messageTaskList.add(new MessageTask("blackList","黑名单审批任务",blackSum,"","ApproveBlackAction_findByPage.action?apprType=03&menuno=C0102","01"));
//		int aftSum = (int) query.appType("04").count();//息费减免
//		messageTaskList.add(new MessageTask("mitigate","息费减免审批任务",aftSum,"","ApproveAftAction_findByPage.action?apprType=04&menuno=C0105","01"));
//		int acSum = (int) query.appType("02").count();//冲账审批
//		messageTaskList.add(new MessageTask("strikeBal","冲账审批任务",acSum,"","ApproveAcAction_findByPage.action?apprType=02&menuno=C0103","01"));
//		int applySum = (int) query.appType("01").count();//待审批任务
//		//int applySum = lnPactBo.getByIdForApp(new LnPact(),getIpage(),userNo).size();
//		messageTaskList.add(new MessageTask("approveTask","待审批任务",applySum,"","LnPactAction_findByPageAppr.action?apprType=01&menuno=C0101","01"));
		
		//资金未处理任务
		int cashUnprocessedSum = fundBaseBo.findByFdflag(userNo).size();
		messageTaskList.add(new MessageTask("cashUnproTask","资金未处理任务",cashUnprocessedSum,"",null,"02"));

		if(isRecent){
			AftRewPact rewPact = new AftRewPact();
			rewPact.setRewSts("01");
			rewPact.setLoginid(userNo);
			List<AftRewPact> rewPactList = aftRewPactBo.findForCollect(rewPact);//合同部分与其他模块不同，使用汇总方式查询
			
			AftRewProj rewProj = new AftRewProj();
			rewProj.setRewSts("01");
			rewProj.setLoginid(userNo);
			Ipage projPage = aftRewProjBo.findForAll(getIpage(), rewProj);
			List<AftRewProj> rewProjList = (List<AftRewProj>) projPage.getResult();
			
			AftRewFund rewFund = new AftRewFund();
			rewFund.setRewSts("01");
			rewProj.setLoginid(userNo);
			Ipage fundPage = aftRewFundBo.findForAll(getIpage(), rewFund);
			List<AftRewFund> rewFundList = (List<AftRewFund>) fundPage.getResult();
			
			AftRewReal rewReal = new AftRewReal();
			rewReal.setRewSts("01");
			rewReal.setLoginid(userNo);
			Ipage realPage = aftRewRealBo.findForAll(getIpage(), rewReal);
			List<AftRewReal> rewRealList = (List<AftRewReal>) realPage.getResult();
			
			MessageAlarm messageAlarm = new MessageAlarm(messageTaskList,rewPactList, rewProjList, rewFundList, rewRealList);
//			messageAlarm.setSumCount(rewPactList.size() + projPage.getPageCounts() + fundPage.getPageCounts());
			return messageAlarm;
		}else if(endDate == null){
			AftRewPact rewPact = new AftRewPact();
			rewPact.setRewDate(sysdate);
			rewPact.setRewSts("01");
			List<AftRewPact> rewPactList = aftRewPactBo.findForCollect(rewPact);
			
			
			AftRewProj rewProj = new AftRewProj();
			rewProj.setRewDate(sysdate);
			rewProj.setRewSts("01");
			List<AftRewProj> rewProjList = aftRewProjBo.findByList(rewProj);
			
			AftRewFund rewFund = new AftRewFund();
			rewFund.setRewDate(sysdate);
			rewFund.setRewSts("01");
			List<AftRewFund> rewFundList = aftRewFundBo.findByList(rewFund);
			
			AftRewReal rewReal = new AftRewReal();
			rewReal.setRewDate(sysdate);
			rewReal.setRewSts("01");
			List<AftRewReal> rewRealList = aftRewRealBo.findByList(rewReal);
			
			MessageAlarm messageAlarm = new MessageAlarm(messageTaskList,rewPactList, rewProjList, rewFundList, rewRealList);
			return messageAlarm;
		}else {
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("rewSts", "01");
			paramMap.put("beginDate", sysdate);
			paramMap.put("endDate", endDate);
			
			List<AftRewPact> rewPactList = aftRewPactBo.findForCollectBetween(paramMap);
			List<AftRewProj> rewProjList = aftRewProjBo.findByDateBetween(paramMap);
			List<AftRewFund> rewFundList = aftRewFundBo.findByDateBetween(paramMap);
			List<AftRewReal> rewRealList = aftRewRealBo.findByDateBetween(paramMap);
			MessageAlarm messageAlarm = new MessageAlarm(messageTaskList,rewPactList, rewProjList, rewFundList, rewRealList);
			return messageAlarm;
		}
	}
	
	private Ipage getIpage() {
		Ipage ipage = new Ipage();
		int	pageSize = 15;
		ipage.setPageSize(pageSize);
		ipage.setPageNo(1);									//设置当前页码为1
		return ipage;
	}
	
	/*
	 * 将所有的从数据库中取到的信息转化为SysTaskInfoResult列表
	 */
	private List<SysTaskInfoResult> createTaskInfoResultList(MessageAlarm messageAlarm){
		List<SysTaskInfoResult> resultList = new ArrayList<SysTaskInfoResult>();
		SysTaskInfoResult result = new SysTaskInfoResult();
		
		for(MessageTask task:messageAlarm.getMessageTaskList()){
			if(task.getTaskCount() == 0)continue;
			result = new SysTaskInfoResult();
			result.setBizPkno(task.getTaskId());
			result.setCreateDate(DateUtil.getSysGlobal().getSys_date());
			result.setCreateTime("00:00:00");
			result.setDueDate("");
			result.setEndDate("");
			result.setEndNum(0);
			result.setFormId("");
			result.setImportLev("1");
			result.setIsMustReply("1");
			result.setOptType("");
			result.setPasContent(task.getContent());
			result.setPasIsMust("0");
			result.setPasMaxNo("3");
			
			result.setPasNo("");
			result.setPasResult("");
			result.setPasSts("0");
			result.setPasTitle(task.getTaskName() + " (共"+task.getTaskCount()+"条记录)");
			result.setPasType("0");
			result.setPasUrl(task.getUrl());
			result.setStartNum(task.getTaskCount());
			result.setStartNumAndEndNum(0);
			result.setUserNo("");
			result.setWkfTaskNo("");
			if(task.getTaskType().equals("01")){
				result.setPasMinNo("301");
			}else{
				result.setPasMinNo("302");
				result.setPasSts("1");
			}
			resultList.add(result);
		}
		
		for(AftRewPact pact : messageAlarm.getRewPactList()){
			//合同部分是使用汇总方式读取的，赋值方式与其他部分略有不同
			result = new SysTaskInfoResult();
			result.setBizPkno(pact.getProjNo());
			result.setProjNo(pact.getProjNo());
			result.setCreateDate(DateUtil.getSysGlobal().getSys_date());
			result.setCreateTime("00:00:00");
			result.setDueDate("");
			result.setEndDate("");
			result.setEndNum(0);
			result.setFormId(pact.getRelId());
			result.setImportLev("1");
			result.setIsMustReply("1");
			result.setOptType("");
			result.setPasContent("【项目编号："+pact.getProjNo()+"，项目名称："+pact.getProjName()+"】  "+pact.getRewName());
			result.setPasIsMust("0");
			result.setPasMaxNo(pact.getRewType().substring(1));
			result.setPasMinNo("1".equals(result.getPasMaxNo())?"101":"201");
			result.setPasNo(pact.getRewNo());
			result.setPasResult("");
			result.setPasSts("0");
			result.setPasTitle(pact.getRewName() + " (共"+pact.getSumCount()+"条记录)");
			result.setPasType("0");
			result.setPasUrl("");
			result.setStartNum(pact.getSumCount());
			result.setStartNumAndEndNum(0);
			result.setUserNo(pact.getAcptNo());
			result.setWkfTaskNo("");
			
			//附加操作：
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为委外催收提醒，只有忽略不存在操作
			//当预警类型为还款计划未上传预警，只有忽略不存在操作
			//当预警类型为影像资料未上传预警，只有忽略不存在操作
			//当预警类型为资金到期结息预警，只有忽略不存在操作
			//当预警类型为项目到期还款提醒，只有忽略不存在操作
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Due_Audit 
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Entrust_Collection	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Payback_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Loanbefore_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Settlement	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Exchange	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Payback	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
				)
				result.setPasSts("1");
			//执行冻结的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Not_Suff_Funds 
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Suser_Not_Suff_Funds	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Rate	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Split_Bill_Fail
//					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
					)
				result.setPasResult("冻结项目");
			//执行解押的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Guar_Release)
				result.setPasResult("确定解押");
			resultList.add(result);
		}
		
		for(AftRewProj pact : messageAlarm.getRewProjList()){
			result = new SysTaskInfoResult();
			result.setBizPkno(pact.getRewId());
			result.setProjNo(pact.getProjNo());
			result.setCreateDate(pact.getRewDate());
			result.setCreateTime("00:00:00");
			result.setDueDate("");
			result.setEndDate("");
			result.setEndNum(0);
			result.setFormId("");
			result.setImportLev(pact.getRewSts().substring(1));
			result.setIsMustReply(pact.getRewSts().substring(1));
			result.setOptType("");
			result.setPasContent("【"+"项目编号："+pact.getProjNo()+", 项目名称："+pact.getProjName()+"】    "+pact.getRewCont());
			result.setPasIsMust("0");
			result.setPasMaxNo(pact.getRewType().substring(1));
			result.setPasMinNo("1".equals(result.getPasMaxNo())?"102":"202");
			result.setPasNo(pact.getRewNo());
			result.setPasResult("");
			result.setPasSts("01".equals(pact.getDealRest())?"0":"1");
			result.setPasTitle(pact.getRewName());
			result.setPasType("0");
			result.setPasUrl("");
			result.setStartNum(1);
			result.setStartNumAndEndNum(0);
			result.setUserNo(pact.getAcptNo());
			result.setWkfTaskNo("");
			
			//附加操作：
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为委外催收提醒，只有忽略不存在操作
			//当预警类型为还款计划未上传预警，只有忽略不存在操作
			//当预警类型为影像资料未上传预警，只有忽略不存在操作
			//当预警类型为资金到期结息预警，只有忽略不存在操作
			//当预警类型为项目到期还款提醒，只有忽略不存在操作
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Due_Audit 
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Entrust_Collection	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Payback_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Loanbefore_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Settlement	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Exchange	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Payback	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
				)
				result.setPasSts("1");
			//执行冻结的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Not_Suff_Funds 
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Suser_Not_Suff_Funds	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Rate	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Split_Bill_Fail	
//					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
					)
				result.setPasResult("冻结项目");
			//执行解押的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Guar_Release)
				result.setPasResult("确定解押");
			resultList.add(result);
		}
		
		for(AftRewFund pact : messageAlarm.getRewFundList()){
			result = new SysTaskInfoResult();
			result.setBizPkno(pact.getRewId());
			result.setProjNo(pact.getProjNo());
			result.setCreateDate(pact.getRewDate());
			result.setCreateTime("00:00:00");
			result.setDueDate("");
			result.setEndDate("");
			result.setEndNum(0);
			result.setFormId("");
			result.setImportLev(pact.getRewSts().substring(1));
			result.setIsMustReply(pact.getRewSts().substring(1));
			result.setOptType("");
			result.setPasContent("【项目编号："+pact.getProjNo()+"，"+"项目名称："+pact.getProjName()+"】   "+pact.getRewCont());
			result.setPasIsMust("0");
			result.setPasMaxNo(pact.getRewType().substring(1));
			result.setPasMinNo("1".equals(result.getPasMaxNo())?"103":"203");
			result.setPasNo(pact.getRewNo());
			result.setPasResult("");
			result.setPasSts("01".equals(pact.getDealRest())?"0":"1");
			result.setPasTitle(pact.getRewName());
			result.setPasType("0");
			result.setPasUrl("");
			result.setStartNum(1);
			result.setStartNumAndEndNum(0);
			result.setUserNo(pact.getAcptNo());
			result.setWkfTaskNo("");
			
			//附加操作：
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为委外催收提醒，只有忽略不存在操作
			//当预警类型为还款计划未上传预警，只有忽略不存在操作
			//当预警类型为影像资料未上传预警，只有忽略不存在操作
			//当预警类型为资金到期结息预警，只有忽略不存在操作
			//当预警类型为项目到期还款提醒，只有忽略不存在操作
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Due_Audit 
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Entrust_Collection	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Payback_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Loanbefore_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Settlement	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Exchange	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Payback	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
				)
				result.setPasSts("1");
			//执行冻结的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Not_Suff_Funds 
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Suser_Not_Suff_Funds	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Rate	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Split_Bill_Fail	
//					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
					)
				result.setPasResult("冻结项目");
			//执行解押的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Guar_Release)
				result.setPasResult("确定解押");
			resultList.add(result);
		}
		for(AftRewReal pact : messageAlarm.getRewRealList()){
			result = new SysTaskInfoResult();
			result.setBizPkno(pact.getRewId());
			result.setProjNo(pact.getProjNo());
			result.setCreateDate(pact.getRewDate());
			result.setCreateTime("00:00:00");
			result.setDueDate("");
			result.setEndDate("");
			result.setEndNum(0);
			result.setFormId("");
			result.setImportLev(pact.getRewSts().substring(1));
			result.setIsMustReply(pact.getRewSts().substring(1));
			result.setOptType("");
			result.setPasContent("【项目编号："+pact.getProjNo()+"，"+"项目名称："+pact.getProjName()+"】   "+pact.getRewCont());
			result.setPasIsMust("0");
			result.setPasMaxNo(pact.getRewType().substring(1));
			result.setPasMinNo("1".equals(result.getPasMaxNo())?"104":"204");
			result.setPasNo(pact.getRewNo());
			result.setPasResult("");
			result.setPasSts("01".equals(pact.getDealRest())?"0":"1");
			result.setPasTitle(pact.getRewName());
			result.setPasType("0");
			result.setPasUrl("");
			result.setStartNum(1);
			result.setStartNumAndEndNum(0);
			result.setUserNo(pact.getAcptNo());
			result.setWkfTaskNo("");
			
			//附加操作：
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为资金到期清算提醒时，只有忽略不存在操作
			//当预警类型为委外催收提醒，只有忽略不存在操作
			//当预警类型为还款计划未上传预警，只有忽略不存在操作
			//当预警类型为影像资料未上传预警，只有忽略不存在操作
			//当预警类型为资金到期结息预警，只有忽略不存在操作
			//当预警类型为项目到期还款提醒，只有忽略不存在操作
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Due_Audit 
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Entrust_Collection	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Payback_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Loanbefore_Datum_Not_Upload	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Settlement	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Funds_Due_Exchange	
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Payback
				|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
				)
				result.setPasSts("1");
			//执行冻结的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Cash_Not_Suff_Funds 
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Suser_Not_Suff_Funds	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Proj_Overdue_Rate	
					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Split_Bill_Fail	
//					|| ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Real_time_transaction_Rate
					)
				result.setPasResult("冻结项目");
			//执行解押的项目
			if(ParmRewType.getByRewNo(pact.getRewNo()) == ParmRewType.Guar_Release)
				result.setPasResult("确定解押");
			resultList.add(result);
		}
		return resultList;
	}
	
	/**
	 * 获取大菜单的列表
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年8月3日
	 * @方法说明：
	 * @返回参数 List<PasBigType>
	 */
	private List<PasBigType> createPasBigTypeList(MessageAlarm messageAlarm){
		String[] typeName={"风险预警","消息提醒","待办任务"};
		PasBigType bigType = new PasBigType();
		List<PasBigType> pasBigTypeList = new ArrayList<PasBigType>();
		for(int i =0;i < 3; i++){
			bigType = new PasBigType();
			bigType.setOptCode(String.valueOf(i+1));
			bigType.setOptName(typeName[i]);
			bigType.setSeqn(i+1);
			bigType.setStartNum(0);
			bigType.setId(String.valueOf(i+1));
			bigType.setName(typeName[i]);
			bigType.setOpen(true);
			bigType.setKeyName("PAS_BIG_TYPE");
			if(messageAlarm !=null){
				if(i==0)bigType.setCount(messageAlarm.getMessageCount());
				if(i==1)bigType.setCount(messageAlarm.getAlarmCount());
				if(i==2)bigType.setCount(messageAlarm.getTaskCount());
			}else{
				bigType.setCount("0");
			}
			pasBigTypeList.add(bigType);
		}
		return pasBigTypeList;
	}
	
	/**
	 * 获取子菜单列表
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年8月3日
	 * @方法说明：
	 * @返回参数 List<PasSubType>
	 */
	private List<PasSubType> createPasSubTypeList(){
		PasSubType subType = new PasSubType();
		List<PasSubType> pasSubTypeList = new ArrayList<PasSubType>();
		for(int i =0;i < PasSubTypeEntity.values().length-1; i++){
			PasSubTypeEntity typeEntity = PasSubTypeEntity.getTypeBySeqNo(i+1);
			subType = new PasSubType();
			subType.setOptCode(typeEntity.getSubTypeNo());
			subType.setOptName(typeEntity.getShowName());
			subType.setSeqn(i+1);
			subType.setStartNum(0);
			subType.setEndNum(0);
			subType.setStartNumAndEndNum(0);
			subType.setKeyName("PAS_SUB_TYPE");
			pasSubTypeList.add(subType);
		}
		return pasSubTypeList;
	}

	public AftRewPactBo getAftRewPactBo() {
		return aftRewPactBo;
	}

	public AftRewProjBo getAftRewProjBo() {
		return aftRewProjBo;
	}

	public AftRewFundBo getAftRewFundBo() {
		return aftRewFundBo;
	}

	public AftRewRealBo getAftRewRealBo() {
		return aftRewRealBo;
	}

	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}

	public FundBaseBo getFundBaseBo() {
		return fundBaseBo;
	}
}
