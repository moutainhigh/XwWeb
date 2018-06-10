package app.creditapp.approve.bo; 

import app.base.ServiceException;
import app.creditapp.acc.chg.entity.AftRelief;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.wkf.entity.Result;

/** 
 * ÀàËµÃ÷ 
 */
public interface ApproveAftBo {

	public Result doCommit(String taskId, String refId, String processId,String apprOp,LnApprIdea lnApprIdea, String apprIdea,
			String apprDesc,String transition, String tlrno, String nextUser,String roleName,String nextBranch)throws ServiceException;

	public void execAftRelief(AftRelief aftRelief) throws ServiceException;
}
