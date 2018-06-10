package accounting.interf.loan;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import accounting.biz.pub.FeeBiz;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.sys.AfferentDomain;
import accounting.domain.sys.OperaInfo;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.util.NumberUtil;

public class CalFeeOp extends Operation {

	/**
	 * 计算费用操作类
	 */
	public Object doExecute(AfferentDomain afferentDomain)throws AccountingException {
		
		CalFee calFee;
		
		if(afferentDomain instanceof CalFee){
			calFee = (CalFee) afferentDomain;
		}else{
			throw new AccountingException("参数类型不匹配！");
		}
	
		//设置返回对象
		CalFeeResult feeResult = new CalFeeResult();
		
		Connection conn = this.getConnection();
		
		//业务参数
		String traceNo = calFee.getTraceNo();
		int traceCnt = calFee.getTraceCnt();
		String loanNo = calFee.getLoanNo();
		String txCode = calFee.getTxCode();
		double txAmt = calFee.getTxAmt();
		String curNo = calFee.getCurNo();
		String saveInd = calFee.getSaveInd();
		String prdtNo = calFee.getPrdtNo();
		
		// 设置业务操作对象
		OperaInfo operaInfo = new OperaInfo(afferentDomain, traceNo, conn);
		
		//返回费用总金额
		double actualFeeAmt = 0.00;
		
		List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
		
		FeeBiz feeBiz = new FeeBiz();
		acChrgLogList = feeBiz.getAcChrgLogListFk(traceCnt, loanNo, prdtNo, txCode, txAmt, curNo, saveInd, operaInfo);
		
		for(AcChrgLog acChrgLog : acChrgLogList){
			actualFeeAmt = NumberUtil.amtAdd(actualFeeAmt, acChrgLog.getChrgAmt());
		}
		
		feeResult.setFeeAmt(actualFeeAmt);
		feeResult.setAcChrgLogList(acChrgLogList);
		feeResult.setTraceCnt(traceCnt + acChrgLogList.size());//流水序号已经到（多笔借据批量计算时解决主键冲突问题）
		
		return feeResult;
	}

}
