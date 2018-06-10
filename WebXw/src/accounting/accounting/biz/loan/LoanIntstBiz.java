package accounting.biz.loan;

import java.sql.Connection;
import java.util.List;

import accounting.biz.pub.IntstBiz;
import accounting.biz.pub.LoanBiz;
import accounting.domain.loan.AcLnIntstLog;
import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.sys.OperaInfo;
import accounting.interf.loan.IntstDetailDatas;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import app.util.DateUtil;

/**
 * 单笔结息
 * 
 * @project：nxnxnew
 * @className：LoanIntstBiz
 */
public class LoanIntstBiz {

	/**
	 * 单笔结息
	 * 
	 * @param operaInfo
	 * 			      业务对象
	 * @param acLnMst
	 *            贷款主文件
	 * @param acLnLoList
	 *            贷款欠款表List
	 * @param txCode
	 *            交易代码
	 * @param subTxCode
	 *            交易子代码
	 * @param isBatch
	 *            是否是批处理,是的话 为true,用于判断是否更新还款计划
	 * @throws Exception 
	 * @业务说明:
	 */

	@SuppressWarnings("unchecked")
	public static IntstDetailDatas loanIntstSettlement(OperaInfo operaInfo, AcLnMst acLnMst, List<AcLnLo> acLnLoList, 
			String txCode, String subTxCode, boolean isBatch)
	throws Exception {

		IntstDetailDatas intstDatas = new IntstDetailDatas();
		

		String loanNo = acLnMst.getLoanNo();
		Connection conn = operaInfo.getConn() ;
		String txDate = operaInfo.getTxDt() ;
		
		double fineInt = 0.0;
		
		//获取当期还款计划
		AcLnRepayPlnCur acLnRepayPlnCur = (AcLnRepayPlnCur) JdbcDao.query(new AcLnRepayPlnCur(), "loan_no='"+acLnMst.getLoanNo()+"'", "ac_ln_repay_pln_cur", conn);
		//遍历欠款表数据，对每期欠款数据进行结息
		for (AcLnLo acLnLo : acLnLoList) {
			fineInt = 0.0;
			int perdNo = acLnLo.getPerdNo();
			
			// 计算每期的欠息
			IntstDetailDatas datas = IntstBiz.calculatePerLoInt(acLnMst, acLnLo, operaInfo);
			
			if (datas != null ) {
				fineInt = datas.getFineInt(); 	// 欠罚息
				double prcpAmt = datas.getPrcpAmt();	// 欠本金
				double normInt = datas.getNormInt();	// 欠正常利息
					
				intstDatas.setFineInt(NumberUtil.amtAdd(intstDatas.getFineInt(), fineInt));		//累加欠罚息
				intstDatas.setPrcpAmt(NumberUtil.amtAdd(intstDatas.getPrcpAmt(), prcpAmt));		//累加欠本金
				intstDatas.setNormInt(NumberUtil.amtAdd(intstDatas.getNormInt(), normInt));		//累加欠正常利息
				
				//结息日志
				AcLnIntstLog acLnIntstLog = new AcLnIntstLog();
				acLnIntstLog.setTraceNo(operaInfo.getTraceNo());
				acLnIntstLog.setLoanNo(acLnLo.getLoanNo());
				acLnIntstLog.setPerdNo(acLnLo.getPerdNo());
				acLnIntstLog.setPactNo(acLnLo.getPactNo());
				acLnIntstLog.setBrNo(acLnLo.getBrNo());
				acLnIntstLog.setNormInt(0.00);
				acLnIntstLog.setLstIntDt(acLnLo.getFineIntDt());
				acLnIntstLog.setFineInt(datas.getFineInt()-acLnLo.getFineInt());
				acLnIntstLog.setLstIntDt(acLnLo.getFineIntDt());
				acLnIntstLog.setTxDt(operaInfo.getBizDt());
				JdbcDao.insert(acLnIntstLog, "ac_ln_intst_log", conn);
				
//				acLnLo.setFineInt(datas.getFineInt()); // 罚息
				acLnLo.setFineIntDt(txDate);
				acLnLo.setFineInt(datas.getFineInt());

//				if (!isBatch) {// 日间结息，更新相应的还款计划表
//					AcLnPayPln acLnPayPln = (AcLnPayPln)JdbcDao.query(new AcLnPayPln(), "loan_no='"+loanNo+"' and perd_no="+perdNo, "ac_ln_pay_pln", conn);
//					acLnPayPln.setOverInt(acLnLo.getOverInt()); 	
//					acLnPayPln.setCmpdInt(acLnLo.getCmpdInt()); 	
//					acLnPayPln.setFineIntDt(txDate);	
//					acLnPayPln.setPrcpAcm(0);			
//					acLnPayPln.setIntstAcm(0);			
//					acLnPayPln.setCmpdAcm(0);			
//					acLnPayPln.setAcmDt(acLnLo.getAcmDt());
//					acLnPayPln.setAcmBegDt(acLnLo.getAcmBegDt());		
//					JdbcDao.update(acLnPayPln, "loan_no='"+loanNo+"' and perd_no=" + perdNo, "ac_ln_pay_pln", conn);
//				}
				
				//按日结息，每天都要进行结息
				acLnLo.setNextIntDt(DateUtil.addByDay(txDate,1));
				
				// 更新欠款表
				JdbcDao.update(acLnLo, "loan_no='"+loanNo+"' and perd_no=" + perdNo, "ac_ln_lo", conn);
				
				//更新还款计划罚息
				AcLnRepayPln acLnRepayPln = (AcLnRepayPln)JdbcDao.query(new AcLnRepayPln(), "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_repay_pln", conn);
				acLnRepayPln.setFineInt(acLnLo.getFineInt());
				JdbcDao.update(acLnRepayPln, "loan_no='"+acLnRepayPln.getLoanNo()+"' and perd_no='"+acLnRepayPln.getPerdNo()+"'", "ac_ln_repay_pln", conn);
					
			}
		}
		

	
		
		if(!isBatch){
			acLnMst.setLstIntDt(txDate);	
			acLnMst.setIcDt(txDate); 		
			acLnMst.setLstDt(txDate); 	
			JdbcDao.update(acLnMst, "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		}
		
		// 新增交易流水表记录
		AcTraceLog log = new AcTraceLog();
		log.setAmt(fineInt);
		log.setTxBrNo(operaInfo.getTxBrNo());
		LoanBiz.insertLog(log, acLnMst, operaInfo, txCode, subTxCode, PUBConstant.INC);
		
		return intstDatas;
	}

	
	/**
	 * 扣款日结当期利息
	 * @param acLnMst 主文件
	 * @param operaInfo 业务对象
	 * @param txCde 交易码
	 * @throws AccountingException
	 */
	public static void loanCurIntstSettlement(AcLnMst acLnMst,AcLnRepayPlnCur repayPlnCur, OperaInfo operaInfo, String txCde) throws AccountingException{
		Connection conn = operaInfo.getConn();
		String txDate = operaInfo.getTxDt();
		String loanNo = acLnMst.getLoanNo();

		acLnMst.setLstIntDt(repayPlnCur.getEndDt()); 	
		acLnMst.setIcDt(repayPlnCur.getEndDt()); 			
		acLnMst.setLstDt(txDate); 			
		JdbcDao.update(acLnMst, "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		
		AcLnIntstLog acLnIntstLog = new AcLnIntstLog();
		acLnIntstLog.setTraceNo(operaInfo.getTraceNo());
		acLnIntstLog.setLoanNo(acLnMst.getLoanNo());
		acLnIntstLog.setPerdNo(repayPlnCur.getPerdNo());
		acLnIntstLog.setPactNo(repayPlnCur.getPactNo());
		acLnIntstLog.setBrNo(repayPlnCur.getBrNo());
		acLnIntstLog.setNormInt(repayPlnCur.getNormInt()-repayPlnCur.getRepayNormInt());
		acLnIntstLog.setLstFineInt(0.00);
		acLnIntstLog.setFineInt(0.00);
		acLnIntstLog.setLstIntDt("");
		acLnIntstLog.setTxDt(operaInfo.getBizDt());
		JdbcDao.insert(acLnIntstLog, "ac_ln_intst_log", conn);
		
		AcTraceLog log = new AcTraceLog();
		log.setTxBrNo(operaInfo.getTxBrNo());
		log.setAmt(repayPlnCur.getNormInt());
		LoanBiz.insertLog(log, acLnMst, operaInfo, txCde, TransCode.LNSA, PUBConstant.INC);
		
	}
}	
