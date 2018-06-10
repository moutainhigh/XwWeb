/**
 * 
 */
package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import accounting.biz.loan.RepayBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.loan.AcLoanBackLog;
import accounting.domain.sys.OperaInfo;
import accounting.interf.loan.AcLoanBack;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.core.OperationFactory;
import accounting.plat.core.init.BusinessInitializer;
import accounting.plat.util.NumberUtil;

/**
 * 获取支付当日交易明细
 * 
 * 
 */
public class LoanDealZfDetail extends Batch {

	public boolean doBatch(String startOrg, String endOrg) {
		String step = "2";// 批量第二步
		boolean batchFlag = false;
		Connection conn = null; // 数据库连接

		PreparedStatement selectSysGlobalPst =  null;
		ResultSet selectSysGlobalRs = null;
		
		PreparedStatement selectAcComSysParmPst = null;
		ResultSet selectAcComSysParmRs = null;
		
		//查询支付与日间反馈状态不同的数据
				String selectDealZfDunInfoSql = "select a.loan_log_no,a.back_type,z.BUSINESS_ENTRY_TYPE,z.order_no,z.entry_no,z.status,z.message,a.back_res from ac_loan_back_log a ," +
						"(select order_no,entry_no,BUSINESS_ENTRY_TYPE,trade_type,message,(case when status='1' then '01' when status='2' then '02' else '03' end) as status" +
						" from zf_dun_info WHERE tx_dt=?) z where z.order_no = a.back_log_no and z.entry_no = a.back_cnt and a.back_res!=z.status";
		PreparedStatement selectDealZfDunInfoPst = null;
		ResultSet selectDealZfDunInfoRs = null;
		
		//查询填张明细
		String selectAcLnPmSql = "select c.loan_no,c.perd_no,c.REPAY_PRCP_AMT,c.REPAY_NORM_INT,c.REPAY_FINE_INT,c.repay_fee_amt, c.chrg_id from ac_debit b, ac_ln_pm_log c where b.debit_no = ?"+
								" and b.trace_no = c.trace_no  and b.loan_no = c.loan_no ";
		PreparedStatement selectAcLnPmPst = null; 
		ResultSet selectAcLnPmRs = null;
		
		//更新费用信息
		String updateAcChrgLogSql = "update ac_chrg_log set repay_chrg_amt=repay_chrg_amt-?,chrg_sts='01' where chrg_id=?";
		PreparedStatement updateAcChrgLogPst = null;
		
		//更新还款计划
		String updateAcPlnSql = "update ac_ln_repay_pln set repay_prcp_amt=repay_prcp_amt-?," +
				"repay_norm_int=repay_norm_int-?,repay_fine_int=repay_fine_int-? where loan_no=? and perd_no=?";
		PreparedStatement updateAcPlnPst = null;
		
		//更新当期还款计划
		String updateAcPlnCurSql = "update ac_ln_repay_pln_cur set repay_prcp_amt=repay_prcp_amt-?," +
				"repay_norm_int=repay_norm_int-? where loan_no=? and perd_no=?";
		PreparedStatement updateAcPlnCurPst = null;
		
		//更新欠款
		String updateLnLoSql = "update ac_ln_lo set repay_prcp_amt=repay_prcp_amt-?," +
				"repay_norm_int=repay_norm_int-?,repay_fine_int=repay_fine_int-? where loan_no=? and perd_no=?";
		PreparedStatement updateLnLoPst = null;
		
		//发送支付表
		String updateAcLoanBackLogSql = "update ac_loan_back_Log set back_res=? where back_log_no=? and back_cnt=?";
		PreparedStatement updateAcLoanBackLogPst = null;
		
		//更新扣款信息表
		String updateAcDebitSql = "update ac_debit set sts=?,rtn_dt=?,rtn_time=? where debit_no=?";
		PreparedStatement updateAcDebitPst = null;
		
		//更新进件扣款信息表
		String updateAcDebitSuspSql = "update ac_debit_susp set deal_sts=? where serial_no=?";
		PreparedStatement updateAcDebitSuspPst = null;
		
		//更新进件WS扣款信息表
		String updateWsRepyMesSql = "update ws_repy_mes set deal_sts=?,deal_desc=? where ws_id in(select ws_id from ac_debit_susp where serial_no=?)";
		PreparedStatement updateWsRepyMesPst = null;
		
		//放款信息
		String selectAcLoanLogSql = "select trace_no,loan_no,loan_amt from ac_loan_log where loan_log_no=?";
		PreparedStatement selectAcLoanLogPst = null;
		ResultSet selectAcLoanLogRs = null;
		
		//获取主文件信息
		String selectAcLnMstSql = "select loan_bal,(select app_id from ln_due where due_no=?),loan_sts,deal_sts from ac_ln_mst where loan_no=?";
		PreparedStatement selectAcLnMstPst = null;
		ResultSet selectAcLnMstRs = null;
		
		//更新主文件
		String updateAcLnMstSql = "update ac_ln_mst set loan_sts=?,deal_sts=?,loan_bal=?,up_dt=? where loan_no=?";
		PreparedStatement updateAcLnMstPst = null;
		ResultSet updateAcLnMstRs = null;
		
		//根据流水号查询费用信息
		String selectAcChrgLogListSql = "select loan_no from ac_chrg_log where trace_no=?";
		PreparedStatement selectAcChrgLogListPst = null;
		ResultSet selectAcChrgLogListRs = null;
		
		//更新放款明细表
		String updateAcLoanLogSql = "update ac_loan_log set loan_sts=?,up_date=? where loan_log_no=? ";
		PreparedStatement updateAcLoanLogPst = null;
		try {
			conn = this.getConnection();
			String txDt = PUBConstant.CUR_PRCS_DT; // 获取当前日期

			OperaInfo operaInfo = new OperaInfo(conn);
			operaInfo.setTraceCnt(1);

//			String batchDt = "";// 当前批量日期
//			selectSysGlobalPst = conn.prepareStatement("select sys_date from sys_global ");
//
//			selectSysGlobalRs = selectSysGlobalPst.executeQuery();
//			if (selectSysGlobalRs.next()) {
//				batchDt = selectSysGlobalRs.getString("sys_date");
//			}
//
//			String batchStp = "";// 当前批量成功运行步骤
//			selectAcComSysParmPst = conn.prepareStatement("select batch_stp from ac_com_sys_parm where batch_dt='"
//							+ batchDt + "'");
//
//			selectAcComSysParmRs = selectAcComSysParmPst.executeQuery();
//			if (selectAcComSysParmRs.next()) {
//				batchStp = selectAcComSysParmRs.getString("batch_stp");
//			}
//			if (Integer.parseInt(batchStp) < Integer.parseInt(step)) {
				
				//查询待处理业务
				selectDealZfDunInfoPst = conn.prepareStatement(selectDealZfDunInfoSql);
				selectDealZfDunInfoPst.setString(1, txDt);
				selectDealZfDunInfoRs = selectDealZfDunInfoPst.executeQuery();
				String orderNo = "";//订单编号
				String entryNo = "";//条目号
				String status = "";//支付日终状态
				String backRes = "";//日间反馈状态
				String loanLogNo = "";//放款、扣款流水号
				String backType = "";//交易类型
				String message = "";//描述
				String businessEntryType = "";//业务条目类型
				while(selectDealZfDunInfoRs.next()){
					 orderNo = selectDealZfDunInfoRs.getString("order_no");
					 entryNo = selectDealZfDunInfoRs.getString("entry_no");
					 status = selectDealZfDunInfoRs.getString("status");
					 backRes = selectDealZfDunInfoRs.getString("back_res");
					 loanLogNo = selectDealZfDunInfoRs.getString("loan_log_no");
					 backType = selectDealZfDunInfoRs.getString("back_type");
					 message = selectDealZfDunInfoRs.getString("message");
					 businessEntryType = selectDealZfDunInfoRs.getString("business_entry_type");
					 //原未决，现成功或失败、原失败，现成功或未决
					 if( (backRes.equals("03") && (status.equals("01") || status.equals("02"))) || 
							 backRes.equals("02") && (status.equals("01") || status.equals("03"))){
						 AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
						 acLoanBackLog.setLoanLogNo(loanLogNo);
						 acLoanBackLog.setBackLogNo(orderNo);
						 acLoanBackLog.setBackCnt(entryNo);
						 acLoanBackLog.setBackType(backType);
						 acLoanBackLog.setBackSts(status); // 处理状态
						 acLoanBackLog.setFailCaus(message); // 处理失败原因
						 dealZfBack(acLoanBackLog,conn);
						 if(backRes.equals("02") && (status.equals("01") )){//原失败，现成功
							 if ("00101".equals(businessEntryType)
										|| "00201".equals(businessEntryType)
										|| "00301".equals(businessEntryType)) {// 放款								selectAcLoanLogPst = conn.prepareStatement(selectAcLoanLogSql);
								selectAcLoanLogPst.setString(1, loanLogNo);
								selectAcLoanLogRs = selectAcLoanLogPst.executeQuery();
								
								if (selectAcLoanLogRs.next()) {
									double loanAmt = selectAcLoanLogRs.getDouble("loan_amt");// 放款金额
									String loanNo = selectAcLoanLogRs.getString("loan_no");//借据号
									RepayBiz.PROC_REACC_FUND(loanNo, loanAmt, 0, "对账回冲-原放款失败，现成功", "BACK", conn);
								}
							}
						}
					 }else if(backRes.equals("01") && (status.equals("02") || status.equals("03"))){//原成功，现失败或未决
						 if(PUBConstant.BACK_TYPE_02.equals(backType)){//扣款
								//查询扣款记录
							 selectAcLnPmPst = conn.prepareStatement(selectAcLnPmSql);
							 selectAcLnPmPst.setString(1, loanLogNo);
							 selectAcLnPmRs = selectAcLnPmPst.executeQuery();
							 
							 selectAcLnMstPst = conn.prepareStatement(selectAcLnMstSql);
							 while(selectAcLnPmRs.next()){
								 String loanNo = selectAcLnPmRs.getString("loan_no");
								 selectAcLnMstPst.setString(1, loanNo);
								 selectAcLnMstPst.setString(2, loanNo);
								 selectAcLnMstRs = selectAcLnMstPst.executeQuery();
								 String loanSts = "";
								 String dealSts = "";
								 double loanBal = 0.00;
								 while(selectAcLnMstRs.next()){
									 loanSts = selectAcLnMstRs.getString("loan_sts");
									 dealSts = selectAcLnMstRs.getString("dealSts");
									 loanBal = selectAcLnMstRs.getDouble("loan_bal");
								 }
								 
									if(selectAcLnPmRs.getString("chrg_id")!=null && selectAcLnPmRs.getString("chrg_id").length()>0){//存在费用编号，则说明该还款是还费用
										updateAcChrgLogPst = conn.prepareStatement(updateAcChrgLogSql);
										updateAcChrgLogPst.setDouble(1, selectAcLnPmRs.getDouble("repay_fee_amt"));
										updateAcChrgLogPst.setString(2, selectAcLnPmRs.getString("chrg_id"));
										updateAcChrgLogPst.executeQuery();
									}else{//其他为正常还本利罚
										updateAcPlnPst = conn.prepareStatement(updateAcPlnSql);
										updateAcPlnPst.setDouble(1, selectAcLnPmRs.getDouble("repay_prcp_amt"));
										updateAcPlnPst.setDouble(2, selectAcLnPmRs.getDouble("repay_norm_int"));
										updateAcPlnPst.setDouble(3, selectAcLnPmRs.getDouble("repay_fine_int"));
										updateAcPlnPst.setString(4, selectAcLnPmRs.getString("loan_no"));
										updateAcPlnPst.setString(5, selectAcLnPmRs.getString("perd_no"));
										updateAcPlnPst.execute();
										
										//实还本金大于0
										if(selectAcLnPmRs.getDouble("repay_prcp_amt")>0){
											updateAcLnMstPst = conn.prepareStatement(updateAcLnMstSql);
											updateAcLnMstPst.setString(1, loanSts);
											updateAcLnMstPst.setString(2, dealSts);
											updateAcLnMstPst.setDouble(3, loanBal);
											updateAcLnMstPst.setString(4, txDt);
											updateAcLnMstPst.setString(5, loanNo);
											updateAcLnMstPst.execute();

										}
										
										//回购当期还款计划
										updateAcPlnCurPst = conn.prepareStatement(updateAcPlnCurSql);
										updateAcPlnCurPst.setDouble(1, selectAcLnPmRs.getDouble("repay_prcp_amt"));
										updateAcPlnCurPst.setDouble(2, selectAcLnPmRs.getDouble("repay_norm_int"));
										updateAcPlnCurPst.setString(3, selectAcLnPmRs.getString("loan_no"));
										updateAcPlnCurPst.setString(4, selectAcLnPmRs.getString("perd_no"));
										updateAcPlnCurPst.execute();
										
										//回滚欠款
										updateLnLoPst = conn.prepareStatement(updateLnLoSql);
										updateLnLoPst.setDouble(1, selectAcLnPmRs.getDouble("repay_prcp_amt"));
										updateLnLoPst.setDouble(2, selectAcLnPmRs.getDouble("repay_norm_int"));
										updateLnLoPst.setDouble(3, selectAcLnPmRs.getDouble("repay_fine_int"));
										updateLnLoPst.setString(4, selectAcLnPmRs.getString("loan_no"));
										updateLnLoPst.setString(5, selectAcLnPmRs.getString("perd_no"));
										updateLnLoPst.execute();
										
										RepayBiz.PROC_REACC_FUND(loanNo, selectAcLnPmRs.getDouble("repay_prcp_amt"), selectAcLnPmRs.getDouble("repay_norm_int"), "对账回冲", "BACK", conn);

									}
								}
								//更新放款、扣款支付明细表
								updateAcLoanBackLogPst = conn.prepareStatement(updateAcLoanBackLogSql);
								updateAcLoanBackLogPst.setString(1, "02");
								updateAcLoanBackLogPst.setString(2, orderNo);
								updateAcLoanBackLogPst.setString(3, entryNo);
								updateAcLoanBackLogPst.execute();
								//更新扣款信息表
								updateAcDebitPst = conn.prepareStatement(updateAcDebitSql);
								updateAcDebitPst.setString(1, status);
								updateAcDebitPst.setString(2, txDt);
								updateAcDebitPst.setString(3, ParmBiz.getOracleTxTime(conn));
								updateAcDebitPst.setString(4, loanLogNo);
								updateAcDebitPst.execute();
								
								updateAcDebitSuspPst = conn.prepareStatement(updateAcDebitSuspSql);
								updateAcDebitSuspPst.setString(1, status);
								updateAcDebitSuspPst.setString(2, loanLogNo);
								updateAcDebitSuspPst.execute();
								
								updateWsRepyMesPst = conn.prepareStatement(updateWsRepyMesSql);
								updateWsRepyMesPst.setString(1, status);
								updateWsRepyMesPst.setString(2, message);
								updateWsRepyMesPst.setString(3, loanLogNo);
								updateWsRepyMesPst.execute();
								
							}else{//放款成功冲正
								//更新放款、扣款支付明细表
								updateAcLoanBackLogPst = conn.prepareStatement(updateAcLoanBackLogSql);
								updateAcLoanBackLogPst.setString(1, status);
								updateAcLoanBackLogPst.setString(2, orderNo);
								updateAcLoanBackLogPst.setString(3, entryNo);
								updateAcLoanBackLogPst.execute();
								
								//更新放款明细
								updateAcLoanLogPst = conn.prepareStatement(updateAcLoanLogSql);
								if("02".equals(status)){//失败
									updateAcLoanLogPst.setString(1, "04");//更新为失败
								}else{
									updateAcLoanLogPst.setString(1, "06");//更新为未决
								}
								updateAcLoanLogPst.setString(2, txDt);
								updateAcLoanLogPst.setString(3, loanLogNo);
								updateAcLoanLogPst.execute();
								
								selectAcLoanLogPst = conn.prepareStatement(selectAcLoanLogSql);
								selectAcLoanLogPst.setString(1, loanLogNo);
								selectAcLoanLogRs = selectAcLoanLogPst.executeQuery();
								
							if (selectAcLoanLogRs.next()) {
								double loanAmt = selectAcLoanLogRs.getDouble("loan_amt");// 放款金额
								String loanNo = selectAcLoanLogRs.getString("loan_no");//借据号
								String loanTraceNo = selectAcLoanLogRs.getString("trace_no");//流水号
								// 查询贷款主文件
								selectAcLnMstPst = conn.prepareStatement(selectAcLnMstSql);
								selectAcLnMstPst.setString(1, loanNo);
								selectAcLnMstPst.setString(2, loanNo);
								selectAcLnMstRs = selectAcLnMstPst.executeQuery();
								double loanBal = 0.00;
								while (selectAcLnMstRs.next()) {
									loanBal = selectAcLnMstRs.getDouble("loan_bal");
								}

								if ("00101".equals(businessEntryType)
										|| "00201".equals(businessEntryType)
										|| "00301".equals(businessEntryType)) {// 放款
									double exact = 0.0001;// 精确小数
									updateAcLnMstPst = conn.prepareStatement(updateAcLnMstSql);
									updateAcLnMstPst.setString(1, "");
									if ((loanBal - loanAmt) < exact&& (loanBal - loanAmt) > -exact) {//
										updateAcLnMstPst.setString(2, "04");
									} else {
										updateAcLnMstPst.setString(2, "05");
									}
									updateAcLnMstPst.setDouble(3, loanBal - loanAmt);
									updateAcLnMstPst.setString(4, ParmBiz.getBizDt(conn));
									updateAcLnMstPst.setString(5, loanNo);
									updateAcLnMstPst.execute();
									if(status.equals("02")){
										RepayBiz.PROC_REACC_FUND(loanNo, loanAmt, 0.00, "对账放款失败", "ADD", conn);
									}

								} else {// 放费
									selectAcChrgLogListPst = conn.prepareStatement(selectAcChrgLogListSql);
									selectAcChrgLogListPst.setString(1, loanTraceNo);
									selectAcChrgLogListRs = selectAcChrgLogListPst.executeQuery();
									double feeAmt = loanAmt;
									while(selectAcChrgLogListRs.next()){
										double repayChrgAmt = selectAcChrgLogListRs.getDouble("repay_chrg_amt");
										if (feeAmt > 0.0001&& NumberUtil.isAmtGreat(feeAmt,repayChrgAmt)) {
											updateAcChrgLogPst = conn.prepareStatement(updateAcChrgLogSql);
											updateAcChrgLogPst.setDouble(1, 0.00);
											updateAcChrgLogPst.setString(2, loanNo);
											updateAcChrgLogPst.execute();
											feeAmt = feeAmt- repayChrgAmt;
										} else if(feeAmt > 0.0001&& NumberUtil.isAmtGreat(repayChrgAmt,feeAmt)){
											updateAcChrgLogPst = conn.prepareStatement(updateAcChrgLogSql);
											updateAcChrgLogPst.setDouble(1, repayChrgAmt-feeAmt);
											updateAcChrgLogPst.setString(2, loanNo);
											updateAcChrgLogPst.execute();
											feeAmt = 0.00;
										}
									}

								}
							}
							}
					 }
				}
				
//				Statement updateAcComSysParmSt = conn.createStatement();
//				String updateAcComSysParmSql = "update AC_COM_SYS_PARM set batch_stp='"+step+"'";
//				updateAcComSysParmSt.executeUpdate(updateAcComSysParmSql);
//				updateAcComSysParmSt.close();
				
				conn.commit();
//			} else {
//				System.out.println(batchDt + "批量步骤2已执行");
//			}

			batchFlag = true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error(e);
			batchFlag = false;
		} finally {
			try {
				if(conn!=null) conn.close();
				if(selectSysGlobalPst!=null) selectSysGlobalPst.close();
				if(selectSysGlobalRs!=null) selectSysGlobalRs.close();
				if(selectAcComSysParmPst!=null) selectAcComSysParmPst.close();
				if(selectAcComSysParmRs!=null) selectAcComSysParmRs.close();
				if(selectDealZfDunInfoPst!=null) selectDealZfDunInfoPst.close();
				if(selectDealZfDunInfoRs!=null) selectDealZfDunInfoRs.close();
				if(selectAcLnPmPst!=null) selectAcLnPmPst.close();
				if(selectAcLnPmRs!=null) selectAcLnPmRs.close();
				if(updateAcChrgLogPst!=null) updateAcChrgLogPst.close();
				if(updateAcPlnPst!=null) updateAcPlnPst.close();
				if(updateAcPlnCurPst!=null) updateAcPlnCurPst.close();
				if(updateLnLoPst!=null) updateLnLoPst.close();
				if(updateAcLoanBackLogPst!=null) updateAcLoanBackLogPst.close();
				if(updateAcDebitPst!=null) updateAcDebitPst.close();
				if(updateAcDebitSuspPst!=null) updateAcDebitSuspPst.close();
				if(updateWsRepyMesPst!=null) updateWsRepyMesPst.close();
				if(selectAcLoanLogPst!=null) selectAcLoanLogPst.close();
				if(selectAcLoanLogRs!=null) selectAcLoanLogRs.close();
				if(selectAcLnMstPst!=null) selectAcLnMstPst.close();
				if(selectAcLnMstRs!=null) selectAcLnMstRs.close();
				if(updateAcLnMstPst!=null) updateAcLnMstPst.close();
				if(updateAcLnMstRs!=null) updateAcLnMstRs.close();
				if(selectAcChrgLogListPst!=null) selectAcChrgLogListPst.close();
				if(selectAcChrgLogListRs!=null) selectAcChrgLogListRs.close();
				if(updateAcLoanLogPst!=null) updateAcLoanLogPst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return batchFlag;
	}

	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-30
	 * @描述	处理支付回盘--批量对账
	 */
	public void dealZfBack(AcLoanBackLog acLoanBackLog,Connection conn) throws Exception{
		if (PUBConstant.BACK_TYPE_01
				.equals(acLoanBackLog.getBackType())) {// 放款
			AcLoanBack acLoanBack = new AcLoanBack();
			acLoanBack.setBackLogNo(acLoanBackLog.getBackLogNo());
			acLoanBack.setBackCnt(acLoanBackLog.getBackCnt());
			acLoanBack.setBackRes(acLoanBackLog.getBackSts());
			acLoanBack.setFailCaus("");
			try {
				acLoanBack.setBackDate(ParmBiz.getOracleUpDate(conn));
			} catch (AccountingException e) {
				e.printStackTrace();
			}
			acLoanBack.setTxDt(ParmBiz.getOracleTxDate(conn));
			Operation op = (Operation) OperationFactory
					.getFactoryInstance()
					.getOperation(TransCode.C3BK, conn);
			op.execute(acLoanBack);
		} else if (PUBConstant.BACK_TYPE_02.equals(acLoanBackLog
				.getBackType())) {// 扣款
			OperaInfo operaInfo = new OperaInfo(conn);
			operaInfo.setTraceCnt(1);
			operaInfo.setTraceNo(ParmBiz.getTraceNo(conn));
			operaInfo.setBizDt(ParmBiz.getBizDt(conn));
			operaInfo.setTxDt(ParmBiz.getOracleTxDate(conn));
			RepayBiz.acDebitBack(acLoanBackLog.getBackLogNo(),
					acLoanBackLog.getBackCnt(), acLoanBackLog.getBackSts(), acLoanBackLog.getFailCaus(),
					operaInfo,"","");
		} 
	}
	
	public static void main(String[] args) throws Exception {
		BusinessInitializer buz = new BusinessInitializer();
		buz.initialize();
		LoanDealZfDetail loanDealZfDetail = new LoanDealZfDetail();
		boolean c = false;
		c = loanDealZfDetail.doBatch(null, null);
		if (!c) {
			throw new Exception("LoanDealZfDetail");
		}
		System.out.println("处理支付当天交易进行对账 完成is OK!");
	}
}
