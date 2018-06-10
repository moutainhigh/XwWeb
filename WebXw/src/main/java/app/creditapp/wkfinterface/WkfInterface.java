package app.creditapp.wkfinterface;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkf.entity.WkfApprovalRole;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.creditapp.wkf.entity.WkfParm;

import com.dhcc.workflow.api.model.Transition;

public interface WkfInterface 
{
	/**
	 * @param processKey 流程Id
	 * 
	 * @param obj 业务对象
	 * 
	 * @param appNo 业务主键值
	 * 
	 * @param primaryKeyName 业务主键名称
	 * 
	 * @param opNo 操作员
	 * 
	 * @throws ClassNotFoundException 
	 * @throws ServiceException 
	 */
	public String startProcess(String processKey,WkfParm obj,String appNo,String opNo) throws ServiceException, ClassNotFoundException;
	/**
	 * @param taskId 当前任务Id
	 * 
	 * @param approvalOpinion 审批意见
	 * 
	 * @param transition 跳转路径
	 * 
	 * @param nextUser 下一环节审批人
	 * 
	 * @return Result
	 */
	public Result agree(String taskId,String approvalOpinion,String transition,String nextUser,String opNo,String isBatchFlag,String nextBranch)throws ServiceException;
	/**
	 * @param taskId 当前任务Id
	 * 
	 * @param approvalOpinion 审批意见
	 * 
	 * @param opNo 操作员
	 * 
	 * @return Result
	 */
	public Result refuse(String taskId,String approvalOpinion,String opNo)throws ServiceException ;
	/**
	 * @param taskId 当前任务Id
	 * 
	 * @param approvalOpinion 审批意见
	 * 
	 * @param transition 跳转路径
	 * 
	 * @return Result
	 */
	public Result rollBack(String taskId,String approvalOpinion,String transition,String opNo)throws ServiceException ;
	/**
	 * @param taskId 当前任务Id
	 */
	public Result rollbackToDefaultNode(String taskId,String approvalOpinion,String opNo)throws ServiceException ;
	/**
	 * @see 会签任务
	 */
	public String getSignTask()throws ServiceException ;
	/**
	 * @see 结束状态
	 */
	public String getEndSts()throws ServiceException ;
	/**
	 * 
	 * @param taskId 当前任务Id
	 * 
	 * @param opinionType 意见类型
	 * 
	 * @param approvalOpinion 审批意见
	 * 
	 * @param transition 跳转路径
	 * 
	 * @param opNo 操作员
	 * 
	 * @param nextUser 下一环节审批人
	 * 
	 * @return Result 成功返回 Result
	 */
	public Result doCommit(String taskId,String opinionType, String approvalOpinion,String transition, String opNo, String nextUser,String isBatchFalg,String nextBranch)throws ServiceException;
	/**
	 * @param 任务ID
	 */
	public List<Transition> findNextTransition(String taskId)throws ServiceException;
	/**
	 * @ 获取所有审批用户
	 */
	public List<WkfApprovalUser> getAllList(WkfApprovalUser wkfApprovalUser)throws ServiceException;
	
	/**
	 * 
	 * 功能描述：根据条件查询县联社对应不同业务类型的审批流程
	 * @param appWkfcfg
	 * @return
	 * @throws ServiceException
	 * @修改日志：
	 */
	public String getWkfNo(String app_type,String prodname) throws ServiceException;
	
	
	public String getWkfNo(String app_type) throws ServiceException;
	
	public String getWkfNo(AppWkfcfg appWkfcfg) throws ServiceException;

	
	/**
	 * 
	 * 功能描述：得到是否允许追回
	 * @param taskId
	 * @param tlrno
	 * @return
	 * @修改日志：
	 */
	public boolean recallTask(String taskId, String tlrno) throws ServiceException ;
	
	/**
	 * 
	 * 功能描述：发回重审，提交，流程变量重新赋值（只修改可能会发生变化的，减少接口调用，提高效率）
	 * @param instanceId
	 * @param obj
	 * @throws ServiceException
	 * @修改日志：
	 */
	public Result doVarReset(String instanceId,WkfParm obj) throws ServiceException;
	
	/**
	 * 
     * @description 通过角色号查询角色详情
     * @param wkfApprovalRole_parm
     * @return
     * @throws ServiceException
     * @version 1.0
	 */
	public WkfApprovalRole getWkfRoleByRoleNo(WkfApprovalRole wkfApprovalRole) throws ServiceException;

}
