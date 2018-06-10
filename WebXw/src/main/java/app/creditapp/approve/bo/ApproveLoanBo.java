package app.creditapp.approve.bo; 

import app.base.ServiceException;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.wkf.entity.Result;

/** 
 * ภเหตร๗ 
 */
public interface ApproveLoanBo {

	public Result doCommit(String taskId, String appId, String processId,String apprOp,LnApprIdea lnApprIdea, String apprIdea,
			String apprDesc,String transition, String tlrno, String nextUser,String roleName,String nextBranch)throws ServiceException;

}
