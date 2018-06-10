package accounting.biz.loan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import accounting.domain.biz.LoanRepay;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.loan.AcDebit;
import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnPmLog;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.StringUtil;
import accounting.plat.util.TimeUtil;

/**
 * 处理欠款还款的业务
 * 
 * @project：nxnxnew
 * @className：RepayLoMortgageBiz
 */
public class RepayLoMortgageBiz {
	
	
	/**
	 * 获得借据下的总的减免信息
	 * 
	 * @param loanNo
	 *            借据号
	 * @param conn
	 * @return
	 * @throws AccountingException
	 */
	public static AcLnRepayPln getWvAcLnRepayPln(String loanNo, Connection conn ,int perdNo)
			throws AccountingException {
		ResultSet rs = null;
		Statement st = null;
		AcLnRepayPln acLnRepayPln = new AcLnRepayPln();
		String sql = "SELECT SUM(wv_prcp_amt) wv_prcp_amt , SUM(wv_norm_int) wv_norm_int ,SUM(wv_fine_int) wv_fine_int FROM ac_ln_repay_pln WHERE loan_no ='"
				+ loanNo + "' AND PERD_NO >'"+perdNo+"'";

		try {
			rs = JdbcDao.executeQuery(st, rs, sql, conn);
			while (rs.next()) {
				acLnRepayPln.setWvPrcpAmt(rs.getDouble("wv_prcp_amt")); // 费用金额
				acLnRepayPln.setWvNormInt(rs.getDouble("wv_norm_int")); // 累计实收金额
				acLnRepayPln.setWvFineInt(rs.getDouble("wv_fine_int")); // 减免费用
			}
			st = rs.getStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally {
			JdbcDao.close(st, rs);
		}

		return acLnRepayPln;
	}
	
	
	
	
	/**
	 * 对每一期费用进行还款,并将期号,还款金额类型,还款金额放到map中.
	 * 
	 * @param perdAmtMap
	 *            存放每一期欠款的还款信息,<期号,<金额类型,还款金额>>
	 * @param amtType
	 *            金额类型
	 * @param remainAmt
	 *            可扣金额
	 * @param acChrgLog
	 *            费用表
	 * @param isAll
	 *            是否够还全部
	 * @return
	 */
	public static double dealRepayFeeAmt(Map<String, Map<String, Map<String, AcChrgLog>>> perdAmtMap, double remainAmt, AcChrgLog acChrgLog, boolean isAll) {
		double loAmt = 0;
		double txAmt = 0;// 实还
		double reAmt = acChrgLog.getRepayChrgAmt();// 已还
		Map<String, Map<String, AcChrgLog>> tempMap = null;
		Map<String, AcChrgLog> tempMapFee = null;

		loAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(acChrgLog.getChrgAmt(),acChrgLog.getRepayChrgAmt()), acChrgLog.getWvChrgAmt());
		// 如果是全部还清
		if (isAll) {
			txAmt = loAmt;
			remainAmt = NumberUtil.amtSubs(remainAmt, txAmt);
		} else {
			// 不够还欠款
			if (NumberUtil.isAmtGreat(loAmt, remainAmt)) {
				txAmt = remainAmt;
				remainAmt = 0;
			} else { // 够还欠款
				txAmt = loAmt;
				remainAmt = NumberUtil.amtSubs(remainAmt, loAmt);
			}
		}
		if (perdAmtMap.get(acChrgLog.getPerdNo()) == null) {
			tempMap = new HashMap<String, Map<String, AcChrgLog>>();

			if (tempMap.get(acChrgLog.getFeeKind()) == null) {
				tempMapFee = new HashMap<String, AcChrgLog>();
			} else {
				tempMapFee = tempMap.get(acChrgLog.getFeeKind());
			}
			
			acChrgLog.setRepayChrgAmt(NumberUtil.amtAdd(txAmt, reAmt));
			acChrgLog.setPayChrgAmt(txAmt);//本次实收
			tempMapFee.put(acChrgLog.getFeeType(), acChrgLog);

			tempMap.put(acChrgLog.getFeeKind(), tempMapFee);
			perdAmtMap.put(acChrgLog.getPerdNo(), tempMap);
		} else {
			tempMap = perdAmtMap.get(acChrgLog.getPerdNo());
			if (tempMap.get(acChrgLog.getFeeKind()) == null) {
				tempMapFee = new HashMap<String, AcChrgLog>();
			} else {
				tempMapFee = tempMap.get(acChrgLog.getFeeKind());
			}
			acChrgLog.setRepayChrgAmt(NumberUtil.amtAdd(txAmt, reAmt));
			acChrgLog.setPayChrgAmt(txAmt);//本次实收
			tempMapFee.put(acChrgLog.getFeeType(), acChrgLog);
			tempMap.put(acChrgLog.getFeeKind(), tempMapFee);

		}
		return remainAmt;
	}

	/**
	 * 处理欠款还款的业务:总入口
	 * 
	 * @param acLnMst
	 *            贷款主文件
	 * @param traceCntHst
	 *            贷款明细表中的交易笔次
	 * @param acLnSetlmtLog
	 *            贷款还款日志表
	 * @param txCode
	 *            交易代码
	 * @param operaInfo
	 *            业务交易对象
	 * 
	 * @return 交易笔次
	 * @throws AccountingException
	 */
	public static LoanRepay repayLoMortgageAmt(OperaInfo operaInfo,
			AcDebit acDebit, AcLnMst acLnMst, LoanRepay loanRepay, String txCode)
			throws AccountingException {

		Connection conn = operaInfo.getConn();

		// 获得存储贷款欠款表的list
		List<AcLnLo> acLnLoList = JdbcDao.queryList(new AcLnLo(), "loan_no='"
				+ acLnMst.getLoanNo() + "' and setl_sts='N'", "perd_no",
				"ac_ln_lo", conn);

		// 获得还款金额
		double repayAmt = acDebit.getRepayAmt();
		loanRepay.setRemainAmt(repayAmt);

		// 如果存在贷款欠款信息,则先还贷款欠款
		if (acLnLoList != null && acLnLoList.size() != 0) {
			// 按金额类型还款
			loanRepay = RepayLoMortgageBiz.repayLoMortgageByAmtType(operaInfo,
					acLnMst, acLnLoList, loanRepay, txCode);

		}

		return loanRepay;
	}

	/**
	 * 根据金额类型扣除欠款
	 * 
	 * @param operaInfo
	 *            业务操作对象
	 * @param acLnMst
	 *            贷款主文件
	 * @param acLnSetlmtLog
	 *            贷款还款日志表
	 * @param loanRepay
	 *            还款交易中用于保存方法间传递数据的对象
	 * @param txCode
	 *            交易代码
	 * 
	 * @return 还款交易中用于保存方法间传递数据的对象
	 * @throws AccountingException
	 */
	public static LoanRepay repayLoMortgageByAmtType(OperaInfo operaInfo,
			AcLnMst acLnMst, List<AcLnLo> acLnLoList, LoanRepay loanRepay,
			String txCode) throws AccountingException {
		Connection conn = operaInfo.getConn();

		double remainAmt = loanRepay.getRemainAmt();
		Map<String, Double> dcMap = new HashMap<String, Double>();
		String txDate = operaInfo.getTxDt();
		double ttlRepayAmt = 0;

		// 如果有可扣金额
		if (NumberUtil.isAmtGreatThanZero(remainAmt)) {

			String loanNo = acLnMst.getLoanNo();
			// 获得每种类型总的欠款金额,并存放在aclnlo中
			AcLnLo sumAcLnLo = getSumAmt(loanNo, conn);
//			AcLnLo sumAcLnLo = (AcLnLo)JdbcDao.query(new AcLnLo(), "loan_no = '"+loanNo+"'", "ac_ln_lo", conn);
			// 将欠款表以期号为主键放入到map中
			Map<String, AcLnLo> acLnLoMap = StringUtil.changeListToMap("perdNo", acLnLoList);
			// 存放每一期,每种金额类型的还款金额
			Map<Integer, Map<String, Double>> perdAmtMap = new HashMap<Integer, Map<String, Double>>(); // <期号,<类型,金额>>
			// 总的欠本金额
			double sumLoPrcpAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(
					sumAcLnLo.getPrcpAmt(), sumAcLnLo.getRepayPrcpAmt()),
					sumAcLnLo.getWvPrcpAmt());
			// 总的欠正常利息金额
			double sumLoNormInt = NumberUtil.amtSubs(NumberUtil.amtSubs(
					sumAcLnLo.getNormInt(), sumAcLnLo.getRepayNormInt()),
					sumAcLnLo.getWvNormInt());
			// 总的欠罚息金额
			double sumLoFineInt = NumberUtil.amtSubs(NumberUtil.amtSubs(
					sumAcLnLo.getFineInt(), sumAcLnLo.getRepayFineInt()),
					sumAcLnLo.getWvFineInt());

			// 总的欠款金额
			double sumLoAmt = NumberUtil.amtAdd(NumberUtil.amtAdd(sumLoPrcpAmt,
					sumLoNormInt), sumLoFineInt);

			// 如果还款金额<总的欠款金额,更新主表拖欠状态为Y
			if (NumberUtil.isAmtGreat(sumLoAmt, remainAmt)) {
				acLnMst.setDelqInd(PUBConstant.DELQ_IND_YES);
			} else {// 若欠款还清,更新主表拖欠状态为N
				acLnMst.setDelqInd(PUBConstant.DELQ_IND_NO);
			}

			// 如果还款金额=总欠款金额 && 贷款到期日<当前营业日期,更新贷款状态
			if (NumberUtil.isAmtGreatAndEqual(remainAmt, sumLoAmt)
					&& (TimeUtil.getBetweenDays(acLnMst.getMtrDt(), txDate) > acLnMst
							.getGraceDay())) {
				acLnMst.setLoanSts(PUBConstant.LOAN_STS_SETL);
			}
			// 有欠款
			if (NumberUtil.isAmtGreatThanZero(sumLoAmt)) {
				// 有欠本金额
				if (NumberUtil.isAmtGreatThanZero(sumLoPrcpAmt)) {
					// 如果总的欠本金额大于可扣金额
					if (NumberUtil.isAmtGreat(sumLoPrcpAmt, remainAmt)) {
						// 更新主文件余额
						acLnMst.setLoanBal(NumberUtil.amtSubs(acLnMst
								.getLoanBal(), remainAmt));

						// 遍历欠款表
						for (int i = 0; i < acLnLoList.size(); i++) {
							AcLnLo acLnLo = acLnLoList.get(i);
							// 如果可扣金额大于0
							if (NumberUtil.isAmtGreatThanZero(remainAmt)) {
								// 将每一期的期号,扣款金额类型,扣款金额放到map中.
								remainAmt = dealRepayAmt(perdAmtMap,
										PUBConstant.LN_AMT_TYP_PRCP, remainAmt,
										acLnLo, false);

							} else {
								break;
							}
						}
					} else { // 总的欠本金额小于等于可扣金额
						// 更新主文件余额
						acLnMst.setLoanBal(NumberUtil.amtSubs(acLnMst
								.getLoanBal(), sumLoPrcpAmt));
						for (int i = 0; i < acLnLoList.size(); i++) {
							AcLnLo acLnLo = acLnLoList.get(i);
							// 将每一期的期号,扣款金额类型,扣款金额放到map中.
							remainAmt = dealRepayAmt(perdAmtMap,
									PUBConstant.LN_AMT_TYP_PRCP, remainAmt,
									acLnLo, true);
						}
					}

				} 
				if (NumberUtil.isAmtGreatThanZero(sumLoNormInt)) { // 金额类型为正常利息,并且有欠的正常利息
					// 如果总的欠正常利息大于可扣金额
					if (NumberUtil.isAmtGreat(sumLoNormInt, remainAmt)) {
						for (int i = 0; i < acLnLoList.size(); i++) {
							AcLnLo acLnLo = acLnLoList.get(i);
							if (NumberUtil.isAmtGreatThanZero(remainAmt)) {
								// 将每一期的期号,扣款金额类型,扣款金额放到map中.
								remainAmt = dealRepayAmt(perdAmtMap,
										PUBConstant.LN_AMT_TYP_CRI, remainAmt,
										acLnLo, false);
							} else {
								break;
							}
						}
					} else { // 总的欠正常利息小于等于可扣金额
						for (int i = 0; i < acLnLoList.size(); i++) {
							AcLnLo acLnLo = acLnLoList.get(i);
							// 将每一期的期号,扣款金额类型,扣款金额放到map中.
							remainAmt = dealRepayAmt(perdAmtMap,
									PUBConstant.LN_AMT_TYP_CRI, remainAmt,
									acLnLo, true);
						}
					}
				} 
				if (NumberUtil.isAmtGreatThanZero(sumLoFineInt)) {// 有欠罚息
					// 如果总的欠罚息金额大于可扣金额
					if (NumberUtil.isAmtGreat(sumLoFineInt, remainAmt)) {
						for (int i = 0; i < acLnLoList.size(); i++) {
							AcLnLo acLnLo = acLnLoList.get(i);
							if (NumberUtil.isAmtGreatThanZero(remainAmt)) {
								// 将每一期的期号,扣款金额类型,扣款金额放到map中.
								remainAmt = dealRepayAmt(perdAmtMap,
										PUBConstant.LN_AMT_TYP_ODI, remainAmt,
										acLnLo, false);
							} else {
								break;
							}
						}
					} else { // 总的欠表内逾期利息金额小于等于可扣金额
						for (int i = 0; i < acLnLoList.size(); i++) {
							AcLnLo acLnLo = acLnLoList.get(i);
							// 将每一期的期号,扣款金额类型,扣款金额放到map中.
							remainAmt = dealRepayAmt(perdAmtMap,
									PUBConstant.LN_AMT_TYP_ODI, remainAmt,
									acLnLo, true);
						}
					}
				}

				// 遍历map,查看哪些欠款表进行的还款操作,并更新欠款表,插入还款明细表,贷款明细表.
				for (Integer perdNo : perdAmtMap.keySet()) {

					double perdRepayPrcpAmt = 0; // 本期本次还本金额
					double perdRepayNormInt = 0; // 本期本次还正常利息金额
					double perdRepayFineInt = 0; // 本期本次罚息金额

					if (perdAmtMap.get(perdNo).get(PUBConstant.LN_AMT_TYP_PRCP) != null) {
						perdRepayPrcpAmt = perdAmtMap.get(perdNo).get(
								PUBConstant.LN_AMT_TYP_PRCP);
					}
					if (perdAmtMap.get(perdNo).get(PUBConstant.LN_AMT_TYP_CRI) != null) {
						perdRepayNormInt = perdAmtMap.get(perdNo).get(
								PUBConstant.LN_AMT_TYP_CRI);
					}
					if (perdAmtMap.get(perdNo).get(PUBConstant.LN_AMT_TYP_ODI) != null) {
						perdRepayFineInt = perdAmtMap.get(perdNo).get(
								PUBConstant.LN_AMT_TYP_ODI);
					}
					// 获得当前期号的欠款表
					AcLnLo acLnLo = acLnLoMap.get(String.valueOf(perdNo));

					// 给欠款表赋值
					acLnLo.setRepayPrcpAmt(NumberUtil.amtAdd(acLnLo
							.getRepayPrcpAmt(), perdRepayPrcpAmt));
					acLnLo.setRepayNormInt(NumberUtil.amtAdd(acLnLo
							.getRepayNormInt(), perdRepayNormInt));
					acLnLo.setRepayFineInt(NumberUtil.amtAdd(acLnLo
							.getRepayFineInt(), perdRepayFineInt));

					// 检查当期欠款表是否还清
					if (chargePayOff(acLnLo)) {
						acLnLo.setPrcpSts(PUBConstant.AMT_STS_SETL);
						acLnLo.setIntSts(PUBConstant.AMT_STS_SETL);
						acLnLo.setSetlSts(PUBConstant.Y);
						acLnLo.setSetlDt(txDate);
					} else {
						acLnMst.setDelqInd(PUBConstant.DELQ_IND_YES);
					}

					// 总的还款金额
					ttlRepayAmt = NumberUtil.amtAdd(NumberUtil.amtAdd(NumberUtil.amtAdd(ttlRepayAmt,
									perdRepayPrcpAmt), perdRepayNormInt),perdRepayFineInt);

                   //	 还款明细表
					AcLnPmLog acLnPmLog = makeAcLnPmLog(operaInfo, acLnLo,
							perdRepayPrcpAmt, perdRepayNormInt,perdRepayFineInt);
					JdbcDao.insert(acLnPmLog, "ac_ln_pm_log", conn);

					try {
						RepayBiz.PROC_REACC_FUND(acLnMst.getLoanNo(), perdRepayPrcpAmt, perdRepayNormInt,"扣款填账","ADD", conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String sql = "update ac_ln_repay_pln set repay_prcp_amt="
							+ acLnLo.getRepayPrcpAmt() + " ,repay_norm_int="
							+ acLnLo.getRepayNormInt() + " , repay_fine_int ="
							+ acLnLo.getRepayFineInt() + " ,INT_STS ='"
							+ acLnLo.getIntSts() + "' , SETL_STS='"
							+ acLnLo.getSetlSts() + "' ,lst_pay_dt='" + txDate
							+ "'";
					JdbcDao.excuteUpdateSql(sql + " where loan_no='"
							+ acLnLo.getLoanNo() + "' and perd_no=" + perdNo,
							conn);

					// 更新欠款表，主要是本金状态、利息状态、结清状态
					JdbcDao.update(acLnLo, "loan_no='" + acLnLo.getLoanNo()
							+ "' and perd_no=" + perdNo, "ac_ln_lo", conn);


				}
			}
		}

		acLnMst.setLstDt(txDate); // 上笔交易日起
		acLnMst.setLastSetlDt(txDate); // 上次扣款日

		loanRepay.setRepayedAmt(ttlRepayAmt); // 当次总还款金额
		loanRepay.setRemainAmt(remainAmt); // 可扣金额
		loanRepay.setDcMap(dcMap); // 分录<金额类型, 金额>map

		return loanRepay;

	}

	/**
	 * 获得借据下的总的欠款信息
	 * 
	 * @param loanNo
	 *            借据号
	 * @param conn
	 * @return
	 * @throws AccountingException
	 */
	public static AcLnLo getSumAmt(String loanNo, Connection conn)
			throws AccountingException {
		ResultSet rs = null;
		Statement st = null;
		AcLnLo acLnLo = new AcLnLo();
		String sql = "SELECT SUM(PRCP_AMT) PRCP_AMT,SUM(NORM_INT) NORM_INT,SUM(FINE_INT) FINE_INT,SUM(REPAY_PRCP_AMT) REPAY_PRCP_AMT,SUM(REPAY_NORM_INT) REPAY_NORM_INT,SUM(REPAY_FINE_INT) REPAY_FINE_INT,SUM(WV_PRCP_AMT) WV_PRCP_AMT,SUM(WV_NORM_INT) WV_NORM_INT,SUM(WV_FINE_INT) WV_FINE_INT FROM AC_LN_LO WHERE LOAN_NO='"
				+ loanNo + "' and SETL_STS='" + PUBConstant.N + "'";
		
		try {
			rs = JdbcDao.executeQuery(st,rs,sql, conn);
			while (rs.next()) {
				acLnLo.setPrcpAmt(rs.getDouble("PRCP_AMT")); // 本金
				acLnLo.setNormInt(rs.getDouble("NORM_INT")); // 正常利息
				acLnLo.setFineInt(rs.getDouble("FINE_INT")); // 逾期利息
				acLnLo.setRepayPrcpAmt(rs.getDouble("REPAY_PRCP_AMT")); // 已还本金
				acLnLo.setRepayNormInt(rs.getDouble("REPAY_NORM_INT")); // 已还正常利息
				acLnLo.setRepayFineInt(rs.getDouble("REPAY_FINE_INT")); // 已还罚息利息
				acLnLo.setWvPrcpAmt(rs.getDouble("WV_PRCP_AMT")); // 减免本金
				acLnLo.setWvNormInt(rs.getDouble("WV_NORM_INT")); // 减免正常利息
				acLnLo.setWvFineInt(rs.getDouble("WV_FINE_INT")); // 减免罚息利息
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			JdbcDao.close(st, rs);
		}

		return acLnLo;
	}

	/**
	 * 对每一期欠款进行还款,并将期号,还款金额类型,还款金额放到map中.
	 * 
	 * @param perdAmtMap
	 *            存放每一期欠款的还款信息,<期号,<金额类型,还款金额>>
	 * @param amtType
	 *            金额类型
	 * @param remainAmt
	 *            可扣金额
	 * @param acLnLo
	 *            欠款表
	 * @param isAll
	 *            是否够还全部
	 * @return
	 */
	public static double dealRepayAmt(
			Map<Integer, Map<String, Double>> perdAmtMap, String amtType,
			double remainAmt, AcLnLo acLnLo, boolean isAll) {
		double loAmt = 0;
		double txAmt = 0;
		Map<String, Double> tempMap = null;
		// 根据还款金额类型取出该期欠款所对应的欠款金额
		if (amtType.equals(PUBConstant.LN_AMT_TYP_PRCP)) {
			loAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getPrcpAmt(),
					acLnLo.getRepayPrcpAmt()), acLnLo.getWvPrcpAmt());
		} else if (amtType.equals(PUBConstant.LN_AMT_TYP_CRI)) {
			loAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getNormInt(),
					acLnLo.getRepayNormInt()), acLnLo.getWvNormInt());
		} else if (amtType.equals(PUBConstant.LN_AMT_TYP_ODI)) {
			loAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getFineInt(),
					acLnLo.getRepayFineInt()), acLnLo.getWvFineInt());
		}
		// 如果是全部还清
		if (isAll) {
			txAmt = loAmt;
			remainAmt = NumberUtil.amtSubs(remainAmt, txAmt);
		} else {
			// 不够还欠款
			if (NumberUtil.isAmtGreat(loAmt, remainAmt)) {
				txAmt = remainAmt;
				remainAmt = 0;
			} else { // 够还欠款
				txAmt = loAmt;
				remainAmt = NumberUtil.amtSubs(remainAmt, loAmt);
			}
		}
		if (perdAmtMap.get(acLnLo.getPerdNo()) == null) {
			tempMap = new HashMap<String, Double>();
			tempMap.put(amtType, txAmt);
			perdAmtMap.put(acLnLo.getPerdNo(), tempMap);
		} else {
			tempMap = perdAmtMap.get(acLnLo.getPerdNo());
			tempMap.put(amtType, txAmt);
		}

		return remainAmt;
	}
	
	/**
	 * 判断是否已还清
	 * 
	 * @param acLnLo
	 *            贷款欠款表
	 * @return true 已还清,false 未还清
	 */
	public static boolean chargePayOff(AcLnLo acLnLo) {

		boolean flag = false;

		double txPrcp = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getPrcpAmt(), acLnLo.getRepayPrcpAmt()), acLnLo.getWvPrcpAmt()); // 所欠本金
		double txNorm = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getNormInt(), acLnLo.getRepayNormInt()),acLnLo.getWvNormInt()); // 所欠本金利息
		double txFine = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getFineInt(), acLnLo.getRepayFineInt()),acLnLo.getWvFineInt()); // 所欠罚息
		
		double ttlAmt = NumberUtil.amtAdd(NumberUtil.amtAdd(txFine, txNorm), txPrcp);
		
		// 如果都还完
		if (NumberUtil.isAmtEqualZero(ttlAmt)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 组装还款明细日志表
	 * 
	 * @param operaInfo
	 * 			  业务交易对象
	 * @param acLnLo
	 *            贷款欠款表
	 * @param prcpAmt
	 *            本金
	 * @param normInt
	 *            利息
	 * @param fineInt
	 *            罚息
	 *            
	 * @return
	 * @throws AccountingException
	 */
	public static AcLnPmLog makeAcLnPmLog(OperaInfo operaInfo, AcLnLo acLnLo, double prcpAmt, double normInt, double fineInt) throws AccountingException {
		AcLnPmLog acLnPmLog = new AcLnPmLog();

		acLnPmLog.setTraceNo(operaInfo.getTraceNo()); // 交易流水号
		acLnPmLog.setPactNo(acLnLo.getPactNo());
		acLnPmLog.setBrNo(acLnLo.getBrNo());
		acLnPmLog.setTxDt(operaInfo.getBizDt()); // 业务交易日期
		acLnPmLog.setRepayNormInt(normInt); // 利息
		acLnPmLog.setRepayFineInt(fineInt); // 罚息
		acLnPmLog.setRepayPrcpAmt(prcpAmt); // 还本金
		acLnPmLog.setLoanNo(acLnLo.getLoanNo());
		acLnPmLog.setPerdNo(String.valueOf(acLnLo.getPerdNo()));
		acLnPmLog.setIntSts(acLnLo.getIntSts());
		acLnPmLog.setPrcpSts(acLnLo.getPrcpSts());
		acLnPmLog.setCancelInd(PUBConstant.REV_ROL_NORM);

		return acLnPmLog;
	}
}
