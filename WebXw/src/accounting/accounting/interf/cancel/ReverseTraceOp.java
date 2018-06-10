
package accounting.interf.cancel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import accounting.biz.pub.AcLnRepayPlnBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.loan.AcDebit;
import accounting.domain.loan.AcDebitDtl;
import accounting.domain.loan.AcLnDue;
import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnPmLog;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.AcLnRepayPlnHst;
import accounting.domain.loan.AcLnSetlmtLog;
import accounting.domain.loan.AcLoanLog;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.loan.AftAcno;
import accounting.domain.loan.AftPayday;
import accounting.domain.loan.AftReliefDtl;
import accounting.domain.loan.LnAcct;
import accounting.domain.loan.ProjAcct;
import accounting.domain.sys.AfferentDomain;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.TransUtil;

/**
 * 冲账交易操作类
 * 
 * 
 */
public class ReverseTraceOp extends Operation {

	@Override
	public Object doExecute(AfferentDomain afferentDomain) throws AccountingException {
		// 冲账交易数据传输对象
		ReverseTrace reverseTrace = null;
		// 判断数据传输对象类型
		if (afferentDomain instanceof ReverseTrace) {
			reverseTrace = (ReverseTrace) afferentDomain;
		} else {
			throw new AccountingException("冲账交易数据传输对象类型不符！");
		}

		// 数据库连接对象
		Connection conn = this.getConnection();

		// 获取当前流水号
		String traceNo = this.getTraceNo();

		// 冲账交易流水号
		long reverseTraceNo = reverseTrace.getReverseTraceNo();

		// 待冲账交易流水
		AcTraceLog reverseTraceLog = (AcTraceLog) this.getReverseTraceLogList(reverseTraceNo, conn).get(0);

		// 设置业务操作对象
		OperaInfo operaInfo = new OperaInfo(afferentDomain, traceNo, conn) ;
		operaInfo.setBizDt(reverseTraceLog.getTxDt()) ;	// 业务交易日期
		
		// 交易码
		String txCode = reverseTraceLog.getTxCde();

		if (txCode == null || txCode.equals("")) {
			throw new AccountingException("交易码不应为空！");
		} else if (txCode.equals(TransCode.LNC3)) {
			// 贷款发放冲账
//			this.reverseGrant(operaInfo, reverseTraceLog);
		} else if (txCode.equals(TransCode.LNC4)) {
			// 贷款还款冲账
			this.reverseRepay(operaInfo, reverseTraceLog) ;
		}else if(txCode.equals(TransCode.LNAD)) {
			//提前还款冲账
			this.reverseAdvpay(operaInfo, reverseTraceLog);
		}else if(txCode.equals(TransCode.CGPD)){
			//还款日变更冲账
			this.reverseRepayDt(operaInfo, reverseTraceLog);
		}else if(txCode.equals(TransCode.LNUP)){
			//还款帐号变更冲账
			this.reverseAcno(operaInfo, reverseTraceLog);
		}else if(txCode.equals(TransCode.LNWV)){
			//息费减免冲账
			this.reverseRelief(operaInfo, reverseTraceLog);
		}else {
			throw new AccountingException("交易码【" + txCode + "】不能进行冲账操作！");
		}
		
//		// 处理业务流水表
//		this.dealTraceLog(operaInfo, reverseTraceLog) ;

		return traceNo;
	}
	
	/**
	 * 新增冲账交易流水记录
	 * 
	 * @parm operaInfo 	交易操作对象
	 * @parm acTraceLog	原交易流水记录
	 * 
	 */
	public void dealTraceLog(OperaInfo operaInfo, AcTraceLog acTraceLog)throws AccountingException{

		String traceNo = operaInfo.getTraceNo() ;
		Connection conn = operaInfo.getConn() ;
		
		// 向数据库插入业务流水信息
		AcTraceLog newTraceLog = new AcTraceLog();
		TransUtil.copyProperties(acTraceLog, newTraceLog) ;

		newTraceLog.setTraceNo(traceNo); 				// 设置流水号
		newTraceLog.setTraceCnt(1); 					// 设置流水笔次
		newTraceLog.setTxDt(operaInfo.getTxDt());	 	// 设置交易日期
		newTraceLog.setTxTime(ParmBiz.getOracleTxTime(conn));	// 设置交易时间
		newTraceLog.setTxBrNo(operaInfo.getTxBrNo()) ;	// 设置交易机构
		newTraceLog.setTxCde(TransCode.LNRV); 			// 设置交易代码
		newTraceLog.setSubTxCde(acTraceLog.getTxCde());	// 设置子交易代码为“原交易代码”
		newTraceLog.setCancelInd(PUBConstant.REV_ROL_NORM);	// 设置撤销标志
		newTraceLog.setAmt(NumberUtil.amtSubs(0, acTraceLog.getAmt())); // 设置发生额

		JdbcDao.insert(newTraceLog, "ac_trace_log", conn);
	}

	/**
	 * 冲账交易流水列表
	 * 
	 * @param reverseTraceNo
	 *            冲账交易流水号
	 * @param conn
	 *            数据库连接对象
	 * @return 冲账交易流水列表
	 * @throws AccountingException
	 */
	private List getReverseTraceLogList(long reverseTraceNo, Connection conn) throws AccountingException {
		List reverseTraceLogList = null;
		String condition = "TRACE_NO='" + reverseTraceNo + "' ";
		reverseTraceLogList = JdbcDao.queryList(new AcTraceLog(), condition, "ac_trace_log", conn);
		return reverseTraceLogList;
	}
	
	/**
	 * 还款冲账
	 * 
	 * @param operaInfo
	 *            业务交易对象
	 * @param reverseTraceLog
	 *            还款交易流水对象
	 *            
	 * @return
	 * @throws AccountingException
	 */
	private void reverseRepay(OperaInfo operaInfo, AcTraceLog reverseTraceLog) throws AccountingException {
		String reverseTraceNo = reverseTraceLog.getTraceNo() ;
		String loanNo = reverseTraceLog.getLoanNo() ;
		
		Connection conn = operaInfo.getConn() ;
		String txDate = operaInfo.getTxDt() ;

		// 获得并更新贷款主表信息
		AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		// 判断贷款主表记录是否存在
		if (acLnMst == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的贷款主表记录！");
		}
		
		//借据信息
		AcLnDue acLnDue = (AcLnDue) JdbcDao.query(new AcLnDue(), "due_no='"+acLnMst.getLoanNo()+"'", "ln_due", conn);
	
		//还款日志--欠款还款信息
		List<AcLnPmLog> loAcLnPmLogList = (ArrayList) JdbcDao.queryList(new AcLnPmLog(), "trace_no='"+reverseTraceNo+"' and repay_fee_amt=0.00", "ac_ln_pm_log", conn);

		//还款日志--欠费还款信息
		List<AcLnPmLog> feeAcLnPmLogList = (ArrayList) JdbcDao.queryList(new AcLnPmLog(), "trace_no='"+reverseTraceNo+"' and repay_fee_amt>0.00", "ac_ln_pm_log", conn);
		
		//查询扣款成功的批扣文件
		AcDebit acDebit = (AcDebit) JdbcDao.query(new AcDebit(), "trace_no='"+reverseTraceNo+"'","ac_debit", conn);
		
		//冲账金额
		double loanAmt= acDebit.getRepayAmt();
		if (loanAmt > 0) {
			// 生成放款明细
			AcLoanLog acLoanLog = new AcLoanLog();
			acLoanLog.setTraceNo(operaInfo.getTraceNo());
			acLoanLog.setLoanLogNo(ParmBiz.getAcLoanLogNo(conn));
			acLoanLog.setLoanNo(acLnMst.getLoanNo());
			acLoanLog.setPactNo(acLnMst.getPactNo());
			acLoanLog.setBrNo(acLnMst.getBrNo());
			acLoanLog.setLoanAmt(loanAmt);
			acLoanLog.setXtAcNo("");
			acLoanLog.setCardChn("");
			acLoanLog.setLoanAcNo(acDebit.getAcNo());
			acLoanLog.setLoanAcType(acDebit.getAcType());
			acLoanLog.setLoanAcName(acDebit.getAcName());
			acLoanLog.setLoanBankCode(acDebit.getBankCode());
			acLoanLog.setLoanBankProv(acDebit.getBankProv());
			acLoanLog.setLoanBankCity(acDebit.getBankCity());
			acLoanLog.setLoanBankSite(acDebit.getBankSite());
			acLoanLog.setBusEntryType("");
			acLoanLog.setLoanSts("01");// 待处理
			acLoanLog.setTxDate(operaInfo.getTxDt());
			acLoanLog.setTxTime(ParmBiz.getOracleTxTime(conn));
			
			//回复本利罚还款
//			for(AcLnPmLog acLnPmLog : loAcLnPmLogList){
//				String perdNo = acLnPmLog.getPerdNo();
//				//获取还款计划
//				AcLnRepayPln acLnRepayPln = (AcLnRepayPln)JdbcDao.query(new AcLnRepayPln(), "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_repay_pln", conn);
//				acLnRepayPln.setRepayPrcpAmt(acLnRepayPln.getRepayPrcpAmt()-acLnPmLog.getRepayPrcpAmt());
//				acLnRepayPln.setRepayNormInt(acLnRepayPln.getRepayNormInt()-acLnPmLog.getRepayNormInt());
//				acLnRepayPln.setRepayFineInt(acLnRepayPln.getRepayFineInt()-acLnPmLog.getRepayFineInt());
//				JdbcDao.update(acLnRepayPln, "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_repay_pln", conn);
//				
//				//当期还款计划
//				AcLnRepayPlnCur acLnRepayPlnCur = (AcLnRepayPlnCur)JdbcDao.query(new AcLnRepayPlnCur(), "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_repay_pln_cur", conn);
//				if(acLnRepayPlnCur != null){
//					acLnRepayPlnCur.setRepayPrcpAmt(acLnRepayPlnCur.getRepayPrcpAmt()-acLnPmLog.getRepayPrcpAmt());
//					acLnRepayPlnCur.setRepayNormInt(acLnRepayPlnCur.getRepayNormInt()-acLnPmLog.getRepayNormInt());
//					JdbcDao.update(acLnRepayPlnCur, "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_repay_pln_cur", conn);
//				}
//				//欠款表更新
//				AcLnLo acLnLo = (AcLnLo)JdbcDao.query(new AcLnLo(), "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_lo", conn);
//				if(acLnLo != null){
//					acLnLo.setRepayPrcpAmt(acLnLo.getRepayPrcpAmt()-acLnPmLog.getRepayPrcpAmt());
//					acLnLo.setRepayNormInt(acLnLo.getRepayNormInt()-acLnPmLog.getRepayNormInt());
//					acLnLo.setRepayFineInt(acLnLo.getRepayFineInt()-acLnPmLog.getRepayFineInt());
//					JdbcDao.update(acLnLo, "loan_no='"+acLnMst.getLoanNo()+"' and perd_no='"+perdNo+"'", "ac_ln_lo", conn);
//				}
//				
//				acLnPmLog.setCancelDt(txDate);
//				acLnPmLog.setCancelTraceNo(Long.parseLong(operaInfo.getTraceNo()));
//				acLnPmLog.setCancelInd(PUBConstant.REV_ROL_REV);
//				JdbcDao.update(acLnPmLog, "trace_no='"+reverseTraceNo+"' and perd_no='"+perdNo+"' and repay_fee_amt=0.00", "ac_ln_pm_log", conn);
//			}
			//回复费用
//			for(AcLnPmLog acLnPmLog : feeAcLnPmLogList){
//				String perdNo = acLnPmLog.getPerdNo();
//				String chrgId = acLnPmLog.getChrgId();
//				AcChrgLog acChrgLog = (AcChrgLog)JdbcDao.query(new AcChrgLog(), "chrg_id='"+chrgId+"'", "ac_chrg_log", conn);
//				acChrgLog.setRepayChrgAmt(acChrgLog.getRepayChrgAmt()-acLnPmLog.getRepayFeeAmt());
//				JdbcDao.update(acChrgLog, "chrg_id='"+chrgId+"'", "ac_chrg_log", conn);
//				
//				acLnPmLog.setCancelDt(txDate);
//				acLnPmLog.setCancelTraceNo(Long.parseLong(operaInfo.getTraceNo()));
//				acLnPmLog.setCancelInd(PUBConstant.REV_ROL_REV);
//				JdbcDao.update(acLnPmLog, "trace_no='"+reverseTraceNo+"' and chrg_id='"+chrgId+"'", "ac_ln_pm_log", conn);
//			}
			
		}else{
			throw new AccountingException("无成功扣款记录，无法进行还款冲账交易！");
		}
		
		//新增冲账流水
		this.dealTraceLog(operaInfo,reverseTraceLog);
		
	}


	/**
	 * 冲账贷款放款交易
	 * 
	 * @param operaInfo
	 *            业务交易对象
	 * @param reverseTraceLog
	 *            放款交易流水对象
	 *            
	 * @return
	 * @throws AccountingException
	 */
	private void reverseGrant(OperaInfo operaInfo, AcTraceLog reverseTraceLog) throws AccountingException {
		String reverseTraceNo = reverseTraceLog.getTraceNo() ;
		String loanNo = reverseTraceLog.getLoanNo() ;
		
		Connection conn = operaInfo.getConn() ;
		String txDate = operaInfo.getTxDt() ;

		// 获得并更新贷款主表信息
		AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		// 判断贷款主表记录是否存在
		if (acLnMst == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的贷款主表记录！");
		}
		
		//获取放款明细
		List<AcLoanLog> acLoanLogList = (ArrayList) JdbcDao.queryList(new AcLoanLog(), "trace_no='"+reverseTraceNo+"'", "AC_LOAN_LOG", conn);

		//生成扣款明细
		for(AcLoanLog acLoanLog : acLoanLogList){
			AcDebitDtl acDebitDtl = getAcDebitDtlByLoanLog(acLoanLog,operaInfo);
			AcDebit acDebit = getAcDebitByDtl(acDebitDtl,acLnMst,operaInfo);
		}
		boolean debitFlag = false;
		
		if(debitFlag){
			
		}
		
		// 更新贷款主文件
		JdbcDao.executeUpdate("ac_ln_mst", "lst_dt='"+txDate+"', ac_sts='"+PUBConstant.AC_STS_CANCEL+"' where loan_no='"+loanNo+"'", conn) ;
	
		//获取还款计划
		List<AcLnRepayPln> alrpList = (ArrayList) JdbcDao.queryList(new AcLnRepayPln(), "loan_no='"+loanNo+"'", "AC_LN_REPAY_PLN", conn);
		//还款计划历史记录
		List<AcLnRepayPlnHst> alrphList = new ArrayList<AcLnRepayPlnHst>();
		//记还款计划历史
		for(AcLnRepayPln alrp : alrpList){
			AcLnRepayPlnHst alrph = new AcLnRepayPlnHst();
			alrph = AcLnRepayPlnBiz.getRpHsByRp(alrp, operaInfo.getTraceNo());
			alrphList.add(alrph);
		}
		
		// 新增还款计划历史
		JdbcDao.insertList(alrphList, "ac_ln_repay_pln_hst", operaInfo.getConn());

		// 删除还款计划表
		JdbcDao.delete("loan_no = '"+loanNo+"'", "ac_ln_pay_pln", conn);

		// 删除当期还款计划表
		JdbcDao.delete("loan_no = '"+loanNo+"'", "ac_ln_pay_pln_cur", conn);
		
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-16
	 * @描述	根据还款明细生成扣款明细
	 */
	public void getLoAcDebitDtlByPmLog(List<AcDebitDtl> acDebitDtlList,List<AcLnPmLog> loAcLnPmLogList,AcLnMst acLnMst,double loAmt,OperaInfo operaInfo) throws AccountingException{
		for(AcLnPmLog loAcLnPmLog : loAcLnPmLogList){
			AcDebitDtl acDebitDtl = new AcDebitDtl();
			acDebitDtl.setTraceNo(operaInfo.getTraceNo());
			acDebitDtl.setLoanNo(acLnMst.getLoanNo());
			acDebitDtl.setPactNo(acLnMst.getPactNo());
			acDebitDtl.setBrNo(acLnMst.getBrNo());
			acDebitDtl.setPerdNo(loAcLnPmLog.getPerdNo());
			acDebitDtl.setDdtlFeeAmt(0.00);
			acDebitDtl.setDdtlDate(ParmBiz.getOracleTxDate(operaInfo.getConn()));
			acDebitDtl.setDdtlSts(PUBConstant.DDTL_STS_PEND);// 待处理
			acDebitDtl.setTxDate(ParmBiz.getOracleTxDate(operaInfo.getConn()));
			acDebitDtl.setTxTime(ParmBiz.getOracleTxTime(operaInfo.getConn()));	
			acDebitDtl.setDdtlPrcpAmt(loAcLnPmLog.getRepayPrcpAmt());
			acDebitDtl.setDdtlNormInt(loAcLnPmLog.getRepayNormInt());
			acDebitDtl.setDdtlFineInt(loAcLnPmLog.getRepayFineInt());
			acDebitDtl.setDdtlAmt(loAcLnPmLog.getRepayPrcpAmt() + loAcLnPmLog.getRepayNormInt() + loAcLnPmLog.getRepayFineInt());
			loAmt = loAmt + loAcLnPmLog.getRepayPrcpAmt() + loAcLnPmLog.getRepayNormInt() + loAcLnPmLog.getRepayFineInt();
			acDebitDtlList.add(acDebitDtl);
		}		
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-16
	 * @描述	生成扣费用明细
	 */
	public void getFeeAcDebitDtlByPmLog(List<AcDebitDtl> acDebitDtlList,List<AcLnPmLog> feeAcLnPmLogList,AcLnMst acLnMst,double myFeeAmt,double otherFeeAmt,OperaInfo operaInfo) throws AccountingException{
		for(AcLnPmLog loAcLnPmLog : feeAcLnPmLogList){
			//获取费用表信息
			AcChrgLog acChrgLog = (AcChrgLog)JdbcDao.query(new AcChrgLog(), "chrg_id='"+loAcLnPmLog.getChrgId()+"'", "ac_chrg_log", operaInfo.getConn());
			//生成扣款明细
			AcDebitDtl acDebitDtl = new AcDebitDtl();
			acDebitDtl.setTraceNo(operaInfo.getTraceNo());
			acDebitDtl.setLoanNo(acLnMst.getLoanNo());
			acDebitDtl.setPactNo(acLnMst.getPactNo());
			acDebitDtl.setBrNo(acLnMst.getBrNo());
			acDebitDtl.setPerdNo(loAcLnPmLog.getPerdNo());
			acDebitDtl.setDdtlFeeAmt(loAcLnPmLog.getRepayFeeAmt());
			acDebitDtl.setDdtlDate(ParmBiz.getOracleTxDate(operaInfo.getConn()));
			acDebitDtl.setDdtlSts(PUBConstant.DDTL_STS_PEND);// 待处理
			acDebitDtl.setTxDate(ParmBiz.getOracleTxDate(operaInfo.getConn()));
			acDebitDtl.setTxTime(ParmBiz.getOracleTxTime(operaInfo.getConn()));	
			acDebitDtl.setDdtlPrcpAmt(0.00);
			acDebitDtl.setDdtlNormInt(0.00);
			acDebitDtl.setDdtlFineInt(0.00);
			acDebitDtl.setDdtlAmt(loAcLnPmLog.getRepayFeeAmt());
			//自收费
			if(PUBConstant.FEE_KIND_01.equals(acChrgLog.getFeeKind())){
				myFeeAmt = myFeeAmt + loAcLnPmLog.getRepayFeeAmt();
			}else if(PUBConstant.FEE_KIND_02.equals(acChrgLog.getFeeKind())){
				otherFeeAmt = otherFeeAmt + loAcLnPmLog.getRepayFeeAmt();
			}
			acDebitDtlList.add(acDebitDtl);
		}		
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-1
	 * @描述	根据放款日志生成扣款明细
	 */
	public AcDebitDtl getAcDebitDtlByLoanLog(AcLoanLog acLoanLog,OperaInfo operaInfo) throws AccountingException{
		AcDebitDtl acDebitDtl = new AcDebitDtl();
		acDebitDtl.setTraceNo(operaInfo.getTraceNo());
		acDebitDtl.setDdtlNo(ParmBiz.getAcDebitDtlNo(operaInfo.getConn()));
		acDebitDtl.setLoanNo(acLoanLog.getLoanNo());
		acDebitDtl.setPactNo(acLoanLog.getPactNo());
		acDebitDtl.setBrNo(acLoanLog.getBrNo());
		acDebitDtl.setDdtlPrcpAmt(acLoanLog.getLoanAmt());
		acDebitDtl.setDdtlNormInt(0.00);
		acDebitDtl.setDdtlFineInt(0.00);
		acDebitDtl.setDdtlFeeAmt(0.00);
		acDebitDtl.setDdtlAmt(acLoanLog.getLoanAmt());
		acDebitDtl.setDdtlDate(operaInfo.getBizDt());
		acDebitDtl.setXtAcNo(acLoanLog.getXtAcNo());
		acDebitDtl.setDdtlAcNo(acLoanLog.getLoanAcNo());
		acDebitDtl.setDdtlAcName(acLoanLog.getLoanAcName());
		acDebitDtl.setDdtlBankCode(acLoanLog.getLoanBankCode());
		acDebitDtl.setDdtlBankCity(acLoanLog.getLoanBankCity());
		acDebitDtl.setDdtlBankProv(acLoanLog.getLoanBankProv());
		acDebitDtl.setDdtlBankSite(acLoanLog.getLoanBankSite());
		acDebitDtl.setDdtlSts(PUBConstant.DDTL_STS_PEND);
		acDebitDtl.setTxDate(operaInfo.getBizDt());
		acDebitDtl.setTxTime(ParmBiz.getOracleTxTime(operaInfo.getConn()));
		
		return acDebitDtl;
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-1
	 * @描述	根据扣款明细生成扣款文件
	 */
	public AcDebit getAcDebitByDtl(AcDebitDtl acDebitDtl,AcLnMst acLnMst , OperaInfo operaInfo) throws AccountingException{
		
		AcDebit acDebit = new AcDebit();
		acDebit.setTraceNo(acDebitDtl.getTraceNo());
		acDebit.setTraceCnt(1);
		acDebit.setDebitNo(ParmBiz.getDebitNo(operaInfo.getConn()));
		acDebit.setTxDt(operaInfo.getBizDt());
		acDebit.setLoanNo(acDebitDtl.getLoanNo());
		acDebit.setPactNo(acDebitDtl.getPactNo());
		acDebit.setBrNo(acDebitDtl.getBrNo());
		acDebit.setAcctBankCde(acDebitDtl.getDdtlBankCode());
		acDebit.setAcNo(acDebitDtl.getDdtlAcNo());
		acDebit.setXtAcNo(acDebitDtl.getXtAcNo());
		acDebit.setCurNo(acLnMst.getCurNo());
		acDebit.setDebitType(PUBConstant.DEBIT_TYPE_01);
		acDebit.setAtpyAmt(acDebitDtl.getDdtlAmt());
		acDebit.setLoAmt(0.00);
		acDebit.setCurAmt(acDebitDtl.getDdtlAmt());
		acDebit.setMyFeeAmt(0.00);
		acDebit.setOtherFeeAmt(0.00);
		acDebit.setRepayAmt(0.00);
		acDebit.setSts(PUBConstant.DDTL_STS_PEND);
		acDebit.setCreateDt(operaInfo.getBizDt());
		return acDebit ;
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-1
	 * @描述	还款日变更冲账
	 */
	public void reverseRepayDt(OperaInfo operaInfo , AcTraceLog reverseTraceLog) throws AccountingException{
		String loanNo = reverseTraceLog.getLoanNo();
		Connection conn = operaInfo.getConn();
		
		// 获得贷款主表信息
		AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		// 判断贷款主表记录是否存在
		if (acLnMst == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的贷款主表记录！");
		}
		
		// 获得借据表信息
		AcLnDue acLnDue = (AcLnDue) JdbcDao.query(new AcLnDue(), "due_no='"+loanNo+"'", "ln_due", conn);
		// 判断贷款主表记录是否存在
		if (acLnDue == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】信息");
		}
		
		//获取 还款日变更记录
		AftPayday aftPayday = (AftPayday) JdbcDao.query(new AftPayday(), "trace_no='"+reverseTraceLog.getTraceNo()+"'", "aft_payday", conn);
		
		//获取还款计划历史
		List<AcLnRepayPlnHst> oldPlnHst = (ArrayList)JdbcDao.queryList(new AcLnRepayPlnHst(), "trace_no='"+reverseTraceLog.getTraceNo()+"' order by perd_no asc", "ac_ln_repay_pln_hst", conn);
		
		//新还款计划
		List<AcLnRepayPln> newPln = new ArrayList<AcLnRepayPln>();
		for(AcLnRepayPlnHst plnHst : oldPlnHst){
			AcLnRepayPln pln = getRpByRpHs(plnHst);
			newPln.add(pln);
		}
		if(oldPlnHst.size()>0){
		//变更起始期次
		int firstPerdNo = oldPlnHst.get(0).getPerdNo();
		
		//现还款计划
		List<AcLnRepayPln> nowPln = (ArrayList)JdbcDao.queryList(new AcLnRepayPln(), "loan_no='"+loanNo+"' and perd_no >='"+firstPerdNo+"'", "ac_ln_repay_pln", conn);

		for(AcLnRepayPln acLnRepayPln : nowPln){
			//新增还款计划历史
			AcLnRepayPlnHst acLnRepayPlnHst = new AcLnRepayPlnHst();
			acLnRepayPlnHst = getRpHsByRp(acLnRepayPln , operaInfo.getTraceNo());
			JdbcDao.insert(acLnRepayPlnHst, "AC_LN_REPAY_PLN_HST", conn);
			JdbcDao.delete("loan_no='"+loanNo+"' and perd_no='"+acLnRepayPln.getPerdNo()+"'", "ac_ln_repay_pln", conn);
		}
		
		//新增还款计划
		JdbcDao.insertList(newPln, "ac_ln_repay_pln", conn);
		}
		
		//将旧还款日变为新还款日
		acLnMst.setRepayDay(aftPayday.getOldPayday());
		acLnMst.setUpDt(operaInfo.getBizDt());
		//更新借据表还款日
		acLnDue.setPayDay(aftPayday.getOldPayday());
		acLnDue.setUpTime(ParmBiz.getOracleTxTime(conn));
		
		//更新贷款主表
		JdbcDao.update(acLnMst, "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		
		//更新借据表
		JdbcDao.update(acLnDue, "due_no='"+loanNo+"'", "ln_due", conn);
		
		//更新旧业务流水表
		reverseTraceLog.setCancelInd(PUBConstant.REV_ROL_REV);
		reverseTraceLog.setCancelTraceNo(operaInfo.getTraceNo());
		JdbcDao.update(reverseTraceLog, "trace_no='"+reverseTraceLog.getTraceNo()+"'", "ac_trace_log", conn);

		//新增冲账流水
		this.dealTraceLog(operaInfo,reverseTraceLog);
		
	}
	
	/*
	 * 根据还款计划获取还款计划历史信息
	 */
	private AcLnRepayPlnHst getRpHsByRp(AcLnRepayPln acLnRepayPln,String traceNo){
		AcLnRepayPlnHst acLnRepayPlnHst = new AcLnRepayPlnHst();
		
		acLnRepayPlnHst.setTraceNo(traceNo);
		acLnRepayPlnHst.setLoanNo(acLnRepayPln.getLoanNo());
		acLnRepayPlnHst.setPactNo(acLnRepayPln.getPactNo());
		acLnRepayPlnHst.setBrNo(acLnRepayPln.getBrNo());
		acLnRepayPlnHst.setPerdNo(acLnRepayPln.getPerdNo());
		acLnRepayPlnHst.setPayDt(acLnRepayPln.getPayDt());
		acLnRepayPlnHst.setInstmAmt(acLnRepayPln.getInstmAmt());
		acLnRepayPlnHst.setPrcpAmt(acLnRepayPln.getPrcpAmt());
		acLnRepayPlnHst.setNormInt(acLnRepayPln.getNormInt());
		acLnRepayPlnHst.setFineInt(acLnRepayPln.getFineInt());
		acLnRepayPlnHst.setBal(acLnRepayPln.getBal());
		return acLnRepayPlnHst ;
	}
	
	/*
	 * 根据还款计划历史获取还款计划信息
	 */
	private AcLnRepayPln getRpByRpHs(AcLnRepayPlnHst acLnRepayPlnHst){
		AcLnRepayPln acLnRepayPln = new AcLnRepayPln();
		
		acLnRepayPln.setLoanNo(acLnRepayPlnHst.getLoanNo());
		acLnRepayPln.setPactNo(acLnRepayPlnHst.getPactNo());
		acLnRepayPln.setBrNo(acLnRepayPlnHst.getBrNo());
		acLnRepayPln.setPerdNo(acLnRepayPlnHst.getPerdNo());
		acLnRepayPln.setPayDt(acLnRepayPlnHst.getPayDt());
		acLnRepayPln.setInstmAmt(acLnRepayPlnHst.getInstmAmt());
		acLnRepayPln.setPrcpAmt(acLnRepayPlnHst.getPrcpAmt());
		acLnRepayPln.setNormInt(acLnRepayPlnHst.getNormInt());
		acLnRepayPln.setFineInt(acLnRepayPlnHst.getFineInt());
		acLnRepayPln.setBal(acLnRepayPlnHst.getBal());
		return acLnRepayPln ;
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-1
	 * @描述	还款帐号变更冲账
	 */
	public void reverseAcno(OperaInfo operaInfo , AcTraceLog reverseTraceLog) throws AccountingException{
		String loanNo = reverseTraceLog.getLoanNo();
		Connection conn = operaInfo.getConn();
		
		// 获得贷款主表信息
		AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		// 判断贷款主表记录是否存在
		if (acLnMst == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的贷款主表记录！");
		}
		// 获得借据表信息
		AcLnDue acLnDue = (AcLnDue) JdbcDao.query(new AcLnDue(), "due_no='"+loanNo+"'", "ln_due", conn);
		if (acLnDue == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的借据信息！");
		}
		//获得贷后还款账号变更记录
		AftAcno aftAcno = (AftAcno) JdbcDao.query(new AftAcno(), "trace_no='"+reverseTraceLog.getTraceNo()+"'", "aft_acno", conn);
		if (aftAcno == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的还款账号变更记录！");
		}
		//查扣款账户
		LnAcct lnAcct = (LnAcct) JdbcDao.query(new LnAcct(), "app_id='"+acLnDue.getAppId()+"' and ac_use='01'", "ln_acct", conn);
		if (lnAcct == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的还款账号信息！");
		}
		//设置旧扣款账户信息
		lnAcct.setAcNo(aftAcno.getOldAcNo());
		lnAcct.setAcName(aftAcno.getOldAcName());
		lnAcct.setAcType(aftAcno.getOldAcType());
		lnAcct.setBankCode(aftAcno.getOldOpnCode());
		
		//更新贷款账户表
		JdbcDao.update(lnAcct, "app_id='"+acLnDue.getAppId()+"' and ac_use='01'", "ln_acct", conn);

		//更新旧业务流水表
//		reverseTraceLog.setCancelInd(PUBConstant.REV_ROL_REV);
//		reverseTraceLog.setCancelTraceNo(operaInfo.getTraceNo());
//		JdbcDao.update(reverseTraceLog, "trace_no='"+reverseTraceLog.getTraceNo()+"'", "ac_trace_log", conn);

		//新增冲账流水
		this.dealTraceLog(operaInfo,reverseTraceLog);
		
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-1
	 * @描述	息费减免冲账
	 */
	public void reverseRelief(OperaInfo operaInfo , AcTraceLog reverseTraceLog) throws AccountingException{
		String loanNo = reverseTraceLog.getLoanNo();
		Connection conn = operaInfo.getConn();
		
		// 获得贷款主表信息
		AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
		// 判断贷款主表记录是否存在
		if (acLnMst == null) {
			throw new AccountingException("查询不到借据【"+loanNo+"】对应 的贷款主表记录！");
		}
		
		// 获得贷后息费减免明细
		List<AftReliefDtl> ardList = (ArrayList)JdbcDao.queryList(new AftReliefDtl(), "trace_no='"+reverseTraceLog.getTraceNo()+"'","aft_relief_dtl", conn);
		
		//原减免金额=现有减免金额 - 新增的减免金额
		for(AftReliefDtl aftReliefDtl : ardList){
			if("01".equals(aftReliefDtl.getReliefType())){//本利罚减免明细
				//欠款表信息
				AcLnLo acLnLo = (AcLnLo)JdbcDao.query(new AcLnLo(), "loan_no='"+aftReliefDtl.getLoanNo()+"' and perd_no='"+aftReliefDtl.getPerdNo()+"'", "ac_ln_lo", conn);
				//还款计划
				AcLnRepayPln acLnRepayPln = (AcLnRepayPln)JdbcDao.query(new AcLnRepayPln(), "loan_no='"+aftReliefDtl.getLoanNo()+"' and perd_no='"+aftReliefDtl.getPerdNo()+"'", "ac_ln_repay_pln", conn);
				
				//更新欠款表各减免金额
				acLnLo.setWvPrcpAmt(acLnLo.getWvPrcpAmt()-aftReliefDtl.getRefAmt());
				acLnLo.setWvNormInt(acLnLo.getWvNormInt()-aftReliefDtl.getRefIntst());
				acLnLo.setWvFineInt(acLnLo.getWvFineInt()-aftReliefDtl.getRefFine());
				JdbcDao.update(acLnLo, "loan_no='"+aftReliefDtl.getLoanNo()+"' and perd_no='"+aftReliefDtl.getPerdNo()+"'", "ac_ln_lo", conn);
				
				//更新还款计划表各减免金额
				acLnRepayPln.setWvPrcpAmt(acLnRepayPln.getWvPrcpAmt()-aftReliefDtl.getRefAmt());
				acLnRepayPln.setWvNormInt(acLnRepayPln.getWvNormInt()-aftReliefDtl.getRefIntst());
				acLnRepayPln.setWvFineInt(acLnRepayPln.getWvFineInt()-aftReliefDtl.getRefFine());
				JdbcDao.update(acLnRepayPln, "loan_no='"+aftReliefDtl.getLoanNo()+"' and perd_no='"+aftReliefDtl.getPerdNo()+"'", "ac_ln_repay_pln", conn);
			}else{
				//回滚减免费用
				List<AcChrgLog> acChrgLogList = (ArrayList)JdbcDao.queryList(new AcChrgLog(), "loan_no='"+aftReliefDtl.getLoanNo()+"' and perd_no='"+aftReliefDtl.getPerdNo()+"'","ac_chrg_log", conn);
				double refFee = aftReliefDtl.getRefFee();
				for(AcChrgLog acChrgLog : acChrgLogList){
					if(refFee>0.00){
						if( NumberUtil.isAmtGreat(refFee, acChrgLog.getWvChrgAmt())){//若减免费用大于本期减免金额
							acChrgLog.setWvChrgAmt(0.00);
							refFee = refFee - acChrgLog.getWvChrgAmt();
						}else{
							acChrgLog.setWvChrgAmt( acChrgLog.getWvChrgAmt() - refFee);
							refFee = 0.00;
						}
						JdbcDao.update(acChrgLog, "chrg_id='"+acChrgLog.getChrgId()+"'", "ac_chrg_log", conn);
					}
				}
			
			}
		}
		
		
		//更新旧业务流水表
		reverseTraceLog.setCancelInd(PUBConstant.REV_ROL_REV);
		reverseTraceLog.setCancelTraceNo(operaInfo.getTraceNo());
//		JdbcDao.update(reverseTraceLog, "trace_no='"+reverseTraceLog.getTraceNo()+"'", "ac_trace_log", conn);

		//新增冲账流水
		this.dealTraceLog(operaInfo,reverseTraceLog);
		
	}
	
	/**
	 * 提前还款冲账
	 * 
	 * @param operaInfo			业务操作对象
	 * @param reverseTraceLog	待冲账交易流水对象
	 * 
	 * @throws AccountingException
	 */
	public void reverseAdvpay(OperaInfo operaInfo, AcTraceLog reverseTraceLog) throws AccountingException {
		String sql = "" ;
		
		String reverseTraceNo = reverseTraceLog.getTraceNo();	// 原交易流水号
		String loanNo = reverseTraceLog.getLoanNo() ;		// 借据号
		
		Connection conn = operaInfo.getConn() ;

		// 贷款主文件
		AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);

		// 获得贷款提前还款日志表
		AcLnSetlmtLog acLnSetlmtLog = (AcLnSetlmtLog) JdbcDao.query(new AcLnSetlmtLog(), "loan_no='"+loanNo+"' and trace_no=" + reverseTraceNo, "ac_ln_setlmt_log", conn);
		
		//获取还款计划历史
		List<AcLnRepayPlnHst> oldPlnHst = (ArrayList)JdbcDao.queryList(new AcLnRepayPlnHst(), "trace_no='"+reverseTraceLog.getTraceNo()+"' and loan_no='"+loanNo+"' order by perd_no asc", "ac_ln_repay_pln_hst", conn);
		
		//新还款计划
		List<AcLnRepayPln> newPln = new ArrayList<AcLnRepayPln>();
		for(AcLnRepayPlnHst plnHst : oldPlnHst){
			AcLnRepayPln pln = getRpByRpHs(plnHst);
			newPln.add(pln);
		}
		//变更起始期次
		int firstPerdNo = oldPlnHst.get(0).getPerdNo();
		
		//现还款计划
		List<AcLnRepayPln> nowPln = (ArrayList)JdbcDao.queryList(new AcLnRepayPln(), "loan_no='"+loanNo+"' and perd_no >='"+firstPerdNo+"'", "ac_ln_repay_pln", conn);

		for(AcLnRepayPln acLnRepayPln : nowPln){
			//新增还款计划历史
			AcLnRepayPlnHst acLnRepayPlnHst = new AcLnRepayPlnHst();
			acLnRepayPlnHst = getRpHsByRp(acLnRepayPln , operaInfo.getTraceNo());
			JdbcDao.insert(acLnRepayPlnHst, "AC_LN_REPAY_PLN_HST", conn);
			JdbcDao.delete("loan_no='"+loanNo+"' and perd_no='"+acLnRepayPln.getPerdNo()+"'", "ac_ln_repay_pln", conn);
		}
		
		//新增还款计划
		JdbcDao.insertList(newPln, "ac_ln_repay_pln", conn);
		
		//删除当期还款计划
		JdbcDao.delete("loan_no='"+loanNo+"' and perd_no='"+newPln.get(0).getPerdNo()+"'", "ac_ln_repay_pln_cur", conn);
		
		//新增当期还款计划
		AcLnRepayPlnCur acPlnCur = getPlnCurByPln(newPln.get(0),operaInfo);
		JdbcDao.insert(acPlnCur, "ac_ln_repay_pln_cur", conn);
		
		//更新旧业务流水表
		reverseTraceLog.setCancelInd(PUBConstant.REV_ROL_REV);
		reverseTraceLog.setCancelTraceNo(operaInfo.getTraceNo());
		JdbcDao.update(reverseTraceLog, "trace_no='"+reverseTraceLog.getTraceNo()+"'", "ac_trace_log", conn);

		//新增冲账流水
		this.dealTraceLog(operaInfo,reverseTraceLog);
	}
	
	/*
	 * 获取当期还款计划
	 */
	public AcLnRepayPlnCur getPlnCurByPln(AcLnRepayPln acLnRepayPln , OperaInfo operaInfo) throws AccountingException{
		AcLnRepayPlnCur acPlnCur = new AcLnRepayPlnCur();
		acPlnCur.setLoanNo(acLnRepayPln.getLoanNo());
		acPlnCur.setPactNo(acLnRepayPln.getPactNo());
		acPlnCur.setBrNo(acLnRepayPln.getBrNo());
		acPlnCur.setTtlAmt(acLnRepayPln.getInstmAmt());
		acPlnCur.setPerdNo(acLnRepayPln.getPerdNo());
		acPlnCur.setPrcpAmt(acLnRepayPln.getPrcpAmt());
		acPlnCur.setNormInt(acLnRepayPln.getNormInt());
		acPlnCur.setWrkDt(operaInfo.getBizDt());
		acPlnCur.setPayDt(acLnRepayPln.getPayDt());
		acPlnCur.setEndDt(acLnRepayPln.getPayDt());
		acPlnCur.setRepayPrcpAmt(acLnRepayPln.getRepayPrcpAmt());
		acPlnCur.setRepayNormInt(acLnRepayPln.getRepayNormInt());
		return acPlnCur;
	}
}