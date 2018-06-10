package app.creditapp.approve.bo.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import redis.clients.jedis.Jedis;

import app.base.BaseService;
import app.base.PUBParm;
import app.base.ServiceException;
import app.base.SourceTemplate;
import app.base.httpclient.entity.SendMessageEntity;
import app.base.httpclient.entity.SendMessageType;
import app.base.httpclient.work.SendMessageMain;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.approve.bo.ApproveLoanBo;
import app.creditapp.pact.dao.LnPactDao;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.ln.bo.LnApprIdeaBo;
import app.creditapp.ln.bo.LnStageBo;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.ln.worker.WorkDforPact;
import app.creditapp.ln.worker.WorkUtils;
import app.creditapp.redis.util.RedisUtil;
import app.creditapp.sys.entity.SysUserRole;
import app.creditapp.wkf.AppConstant;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkfinterface.WkfInterface;
import app.util.DateUtil;
import app.util.User;

import com.alibaba.fastjson.JSON;
import com.dhcc.workflow.WFUtil;

/**
 * 类说明
 */
public class ApproveLoanBoImpl extends BaseService implements
		ApproveLoanBo {
	Logger logger = Logger.getLogger(WorkDforPact.class);
	private WkfInterface wkfInterface;
	private LnPactDao lnPactDao;
	private LnApprIdeaBo lnApprIdeaBo;
	private String taskId;
	
	public Result doCommit(String taskId, String appId, String processId,
			String apprOp, LnApprIdea lnApprIdea, String apprIdea,
			String apprDesc, String transition, String tlrno, String nextUser,String roleName,String nextBranch) throws ServiceException {

		Result res = new Result();
		try {
			if (null != taskId) {
//				String[] nextRole = WFUtil.getTaskService().getNextTaskExecutor(taskId);
//				res = wkfInterface.doCommit(taskId, apprIdea, apprDesc,
//						transition, tlrno, nextUser, "",nextBranch);
				res.setProcessEnd("ended");
				res.setIsPass("pass");

				LnPact lnPact = new LnPact();

				 if (AppConstant.OPINION_TYPE_ARREE.equals(apprIdea)) {
					if (res.isProcessEnd()) {// 成功结束，更新合同表为“审批通过”
						// 更新合同表状态至审批通过
						lnPact.setAppId(appId);
						lnPact = lnPactDao.getByAppId(lnPact);
						lnPact.setApprSts("02");//审批状态为通过
						lnPact.setPactSts("02");
						lnPactDao.updateSts(lnPact);
//						LnStageBo lnStageBo = (LnStageBo) SourceTemplate.getSpringContextInstance().getBean("lnStageBo");
//						lnStageBo.updatests(appId, "RGAPP_STS", "02");// 更新人工审批状态为通过
						WorkUtils.getInstance().proc_up_stage(appId, "RGAPP_STS", "02","人工审批通过");
						// push入5号消息队列
//				    	Jedis jedis = RedisUtil.getJedis();
//				  		jedis.lpush(RedisUtil.QUEUE5, JSON.toJSONString(lnPact));
//				  		jedis.close();
				  		logger.info("人工审批通过-[合同编号为PactId=" + lnPact.getPactId()+",合同号为"+lnPact.getPactNo()+"]！");
					}else{
						lnPact.setApprSts("01");//审批状态为审批中
						lnPactDao.updateSts(lnPact);
//						String RoleNo = nextRole[1];//获取下一个流程节点角色
						SendMessageEntity sendMessageEntity=new SendMessageEntity();//发送消息
						String title="贷款审批";
			            String contet="有待审批业务";
				    	sendMessageEntity.setTitle(title);
				    	sendMessageEntity.setContet(contet);
//				    	sendMessageEntity.setGroupNo(RoleNo);
				    	sendMessageEntity.setSendType(SendMessageType.MESSAGE);
				    	sendMessageEntity.setPasSubTypeEntity(PasSubTypeEntity.RewFundMessage);
				    	SendMessageMain.sendMessage(sendMessageEntity);	
					}
				} else if (AppConstant.OPINION_TYPE_REFUSE.equals(apprIdea)) {// 如果审批意见为否决
//					LnStageBo lnStageBo = (LnStageBo) SourceTemplate.getSpringContextInstance().getBean("lnStageBo");
//					lnStageBo.updatests(appId, "RGAPP_STS", "03");// 更新人工审批状态为否决
					WorkUtils.getInstance().proc_up_stage(appId, "RGAPP_STS", "03","人工审批否决,"+apprDesc);
					lnPact.setApprSts("03");//审批状态为否决
					lnPactDao.updateSts(lnPact);
				}
				 lnPact.setAppId(appId);
				// lnPactDao.updateSts(lnPact);
				 lnApprIdea.setAppId(appId);
				 lnApprIdea.setApprId(lnApprIdea.getBatchNo());
				 lnApprIdea.setApprType(lnPact.getApprType());
				 lnApprIdea.setApprTime(DateUtil.getDateTime());
				 lnApprIdea.setApprIdea(apprIdea);
				 lnApprIdea.setApprDesc(apprDesc);
				 lnApprIdea.setApprOp(apprOp);
				 lnApprIdea.setApprRole(User.getWkfUserRoleNos(ServletActionContext.getRequest()).split("@")[0]);
				 lnApprIdea.setApprType("02");
				 lnApprIdea.setApprKind("01");
				 lnApprIdeaBo.insert(lnApprIdea);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return res;
	}


	private HttpServletRequest getHttpRequest() {
		// TODO Auto-generated method stub
		return null;
	}


	public WkfInterface getWkfInterface() {
		return wkfInterface;
	}


	public void setWkfInterface(WkfInterface wkfInterface) {
		this.wkfInterface = wkfInterface;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public LnPactDao getLnPactDao() {
		return lnPactDao;
	}


	public void setLnPactDao(LnPactDao lnPactDao) {
		this.lnPactDao = lnPactDao;
	}


	public LnApprIdeaBo getLnApprIdeaBo() {
		return lnApprIdeaBo;
	}


	public void setLnApprIdeaBo(LnApprIdeaBo lnApprIdeaBo) {
		this.lnApprIdeaBo = lnApprIdeaBo;
	}


	public Logger getLogger() {
		return logger;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}


}
