package  app.creditapp.approve.action;

import java.io.PrintWriter; 
import java.util.List;

import org.dbunit.util.Base64;

import app.base.SourceTemplate;
import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.entity.SysUser;

import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.Execution;
import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.activity.ActivityBehaviour;
import com.dhcc.workflow.api.task.Candidate;
import com.dhcc.workflow.identity.app.IAppBranch;
import com.dhcc.workflow.identity.service.interfaces.IBranchService;
import com.dhcc.workflow.pvm.internal.el.Expression;
import com.dhcc.workflow.pvm.internal.model.ActivityImpl;
import com.dhcc.workflow.pvm.internal.model.ComplexCondition;
import com.dhcc.workflow.pvm.internal.model.Condition;
import com.dhcc.workflow.pvm.internal.model.ExecutionImpl;
import com.dhcc.workflow.pvm.internal.model.TransitionImpl;
import com.dhcc.workflow.pvm.internal.task.SignTaskDefinitionImpl;
import com.dhcc.workflow.pvm.internal.task.TaskDefinitionImpl;
import com.dhcc.workflow.pvm.internal.util.StringUtil;
import com.dhcc.workflow.wfdl.internal.activity.DecisionRouteActivity;
import com.dhcc.workflow.wfdl.internal.activity.SignTaskActivity;
import com.dhcc.workflow.wfdl.internal.activity.TaskActivity;

/**
 * 该action 用于工作流调用ajax方法
**/
public class WorkFlowAction extends BaseFormBean {

	private String taskId;
	private String transitionName;
	private String branchId;
	
	private String chkManagerId;
	private String pwd;
	private String brNo;
	private SysUserBo sysUserBo;
	public WorkFlowAction() {
	}
	
	public String findExecutorByTransition() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=GBK");
		this.getHttpResponse().setCharacterEncoding("GBK");
		PrintWriter out = this.getHttpResponse().getWriter();
		TransitionImpl transition = WFUtil.getTaskService().getTransitionByName(taskId, transitionName);
		ActivityImpl targetActivity = transition.getDestination();
		ActivityBehaviour activityBehaviour = targetActivity.getActivityBehaviour();
		String candidateUsers = "";
		String candidateGroups = "";
		String candidateLevel = "";
		if ( activityBehaviour instanceof TaskActivity ) {
			TaskActivity activity = (TaskActivity) activityBehaviour;
			TaskDefinitionImpl taskDefinition = activity.getTaskDefinition();
			Expression userExpression = taskDefinition.getAssigneeExpression();
			if ( userExpression == null )
				userExpression = taskDefinition.getCandidateUsersExpression();
			if ( userExpression != null ) {
				if ( userExpression.isLiteralText() )
					candidateUsers = userExpression.evaluate().toString();
				else {
					Execution execution = WFUtil.getExecutionService().findExecutionByTaskId(taskId);
					candidateUsers = userExpression.evaluate(execution).toString();
				}
			}
			Expression groupExpression = taskDefinition.getCandidateGroupsExpression();
			if ( groupExpression != null ) {
				candidateGroups = groupExpression.evaluate().toString();
				candidateLevel = taskDefinition.getCandidateLevelExpression().evaluate().toString();
			}
		} else if ( activityBehaviour instanceof SignTaskActivity ) {
			SignTaskActivity activity = (SignTaskActivity) activityBehaviour;
			SignTaskDefinitionImpl taskDefinition = activity.getSignTaskDefinition();
			Expression userExpression = taskDefinition.getAssigneeExpression();
			if ( userExpression == null ) {
				userExpression = taskDefinition.getCandidateUsersExpression();
			}
			if ( userExpression != null ) {
				if ( userExpression.isLiteralText() )
					candidateUsers = userExpression.evaluate().toString();
				else {
					Execution execution = WFUtil.getExecutionService().findExecutionByTaskId(taskId);
					candidateUsers = userExpression.evaluate(execution).toString();
				}
			}
			Expression groupExpression = taskDefinition.getCandidateGroupsExpression();
			if ( groupExpression != null ) {
				candidateGroups = groupExpression.evaluate().toString();
				candidateLevel = taskDefinition.getCandidateLevelExpression().evaluate().toString();
			}
		} else if ( activityBehaviour instanceof DecisionRouteActivity ) {
			DecisionRouteActivity decisionActivity = (DecisionRouteActivity) activityBehaviour;
			List<Condition> conditions = decisionActivity.getDecisionRoute().getConditions();
			String decisionResult = null;
			if( conditions != null && conditions.size() > 0 ) {
				ExecutionImpl execution = (ExecutionImpl)WFUtil.getExecutionService().findExecutionByTaskId(taskId);
				for (Condition condition : conditions) {
					if ( (condition == null) || (condition.evaluate(execution)) ) {
						transitionName = ((ComplexCondition)condition).getResult();
						decisionResult = execution.getActivity().getOutgoingTransition(transitionName).getName();
					}
				}
			}
			
			if( decisionResult == null ) {
				throw new WorkflowException("could not return transition maybe because decision condition is not pass.");
			}
			
			TransitionImpl decisionTransition = targetActivity.getOutgoingTransition(decisionResult);
			ActivityImpl decisionTargetActivity = decisionTransition.getDestination();
			ActivityBehaviour decisionActivityBehaviour = decisionTargetActivity.getActivityBehaviour();
			if ( decisionActivityBehaviour instanceof TaskActivity ) {
				TaskActivity taskActivity = (TaskActivity) decisionActivityBehaviour;
				TaskDefinitionImpl taskDefinition = taskActivity.getTaskDefinition();
				Expression userExpression = taskDefinition.getAssigneeExpression();
				if ( userExpression == null )
					userExpression = taskDefinition.getCandidateUsersExpression();
				if ( userExpression != null ) {
					if ( userExpression.isLiteralText() ) {
						candidateUsers = userExpression.evaluate().toString();
					} else {
						Execution execution = WFUtil.getExecutionService().findExecutionByTaskId(taskId);
						candidateUsers = userExpression.evaluate(execution).toString();
					}
				}
				Expression groupExpression = taskDefinition.getCandidateGroupsExpression();
				if ( groupExpression != null ) {
					candidateGroups = groupExpression.evaluate().toString();
					candidateLevel = taskDefinition.getCandidateLevelExpression().evaluate().toString();
				}
				
			} else if ( decisionActivityBehaviour instanceof SignTaskActivity ) {
				SignTaskActivity taskActivity = (SignTaskActivity) activityBehaviour;
				SignTaskDefinitionImpl taskDefinition = taskActivity.getSignTaskDefinition();
				Expression userExpression = taskDefinition.getAssigneeExpression();
				if ( userExpression == null )
					userExpression = taskDefinition.getCandidateUsersExpression();
				if ( userExpression != null ) {
					if ( userExpression.isLiteralText() )
						candidateUsers = userExpression.evaluate().toString();
					else {
						Execution execution = WFUtil.getExecutionService().findExecutionByTaskId(taskId);
						candidateUsers = userExpression.evaluate(execution).toString();
					}
				}
				Expression groupExpression = taskDefinition.getCandidateGroupsExpression();
				if ( groupExpression != null ) {
					candidateGroups = groupExpression.evaluate().toString();
					candidateLevel = taskDefinition.getCandidateLevelExpression().evaluate().toString();
				}
			}
		}
		String result = "";
		result = result+candidateUsers+","+candidateGroups+",";
		if ( StringUtil.isEmpty(candidateGroups) ) {
			result = result+"";
		} else {
			result = result+findBranchByLevel(branchId, candidateLevel);
		}
		out.print(result);
		return null;
	}
	/**
	 * get branchId through current branch and candidate level.
	 * 
	 * @param branchId
	 * @param candidateLevel
	 * @return
	 */
	private String findBranchByLevel(String branchId, String candidateLevel) {
		if ( Candidate.SELF.equals(candidateLevel) ) {
			return branchId;
		} else if ( Candidate.UP_ONE.equals(candidateLevel) ) {
			return getUpOneBranch(branchId);
		} else if ( Candidate.UP_TWO.equals(candidateLevel) ) {
			return getUpTwoBranch(branchId);
		}
		return "";
	}
	
	private String getUpOneBranch(String branchId) {
		IBranchService service = (IBranchService) SourceTemplate.getSpringContextInstance().getBean("branchService");
		IAppBranch branch = null;
		try {
			branch = service.getById("",branchId);
			if( branch == null ) {
				return "";
			}
			return branch.getParentId();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String getUpTwoBranch(String branchId) {
		IBranchService service = (IBranchService) SourceTemplate.getSpringContextInstance().getBean("branchService");
		IAppBranch branch = null;
		try {
			branch = service.getById("",branchId);
			if( branch == null ) {
				return "";
			}
			
			if( StringUtil.isEmpty(branch.getUpTwo()) ) {
				branch = service.getById("",branch.getParentId());
				if( branch == null ) {
					return "";
				}
				return branch.getUpOne();
			}
			return branch.getParentId();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String chkManagerPass() throws Exception {
		this.getHttpResponse().setContentType("text/html;charset=GBK");
		this.getHttpResponse().setCharacterEncoding("GBK");
		PrintWriter out = this.getHttpResponse().getWriter();
		String result = "";
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(chkManagerId);
		sysUser = sysUserBo.getById(sysUser);
		 if(sysUser!=null){
			 if(!sysUser.getPass_word().equals(Base64.encodeString(pwd))){
				 result= "wrongPass";
			 }else{
				 result= "ok";
			 }
		 }else{
			 result= "null";
		 }
		out.print(result);
		return null;

	}
	
	public String getChkManagerId() {
		return chkManagerId;
	}

	public void setChkManagerId(String chkManagerId) {
		this.chkManagerId = chkManagerId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public SysUserBo getSysUserBo() {
		return sysUserBo;
	}

	public void setSysUserBo(SysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTransitionName() {
		return transitionName;
	}

	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}


}