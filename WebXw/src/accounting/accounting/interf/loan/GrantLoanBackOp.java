package accounting.interf.loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import accounting.biz.loan.RepayBiz;
import accounting.biz.pub.AcLnRepayPlnBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.loan.AcLnDue;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.AcLoanBackLog;
import accounting.domain.loan.AcLoanLog;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.loan.AftAcno;
import accounting.domain.loan.LnAcct;
import accounting.domain.loan.LnApplyMid;
import accounting.domain.loan.LnBatch;
import accounting.domain.loan.LnPact;
import accounting.domain.loan.LnStage;
import accounting.domain.loan.PrdtBase;
import accounting.domain.loan.ProjAcct;
import accounting.domain.sys.AfferentDomain;
import accounting.domain.sys.OperaInfo;
import accounting.domain.ws.WsAcnoChg;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import app.util.DateUtil;


public class GrantLoanBackOp extends Operation {
	/**
	 * 放款反馈操作类 
	 * @param afferentDomain     贷款放款数据对象
	 * @return  
	 */
	public Object doExecute(AfferentDomain afferentDomain)throws AccountingException{
		AcLoanBack lb;
		
		if (afferentDomain instanceof AcLoanBack) {
			lb = (AcLoanBack) afferentDomain;
		} else {
			throw new AccountingException("参数类型不匹配！");
		}
		
		Connection conn = this.getConnection();

		//得到业务参数
		String traceNo = ParmBiz.getTraceNo(conn);
		String backRes = lb.getBackRes();//放款结果
		String status = lb.getStatus();//交易状态
		String failCaus = lb.getFailCaus();//失败原因
		String cardChn = lb.getCardChn();//支付渠道
		
		// 设置业务操作对象
		OperaInfo operaInfo = new OperaInfo(afferentDomain, traceNo, conn);
		operaInfo.setBizDt(ParmBiz.getBizDt(conn));//核算交易日期
		operaInfo.setTxDt(ParmBiz.getBizDt(conn));//核算交易日期

		
		//发送支付及反馈信息
		AcLoanBackLog acLoanBackLog = (AcLoanBackLog)JdbcDao.query(new AcLoanBackLog(), "back_log_no='"+lb.getBackLogNo()+"' and back_cnt='"+lb.getBackCnt()+"'", "ac_loan_back_log", conn);
		
		//根据放款流水号查询放款信息表
		AcLoanLog acLoanLog = (AcLoanLog)JdbcDao.query(new AcLoanLog(), "loan_log_no='"+acLoanBackLog.getLoanLogNo()+"'", "ac_loan_log", operaInfo.getConn());
		
		//主流水信息
		AcTraceLog acTraceLog = (AcTraceLog)JdbcDao.query(new AcTraceLog(), "trace_no='"+acLoanLog.getTraceNo()+"'", "ac_trace_log", conn);
		
		//借据信息
		AcLnDue acLnDue = null;

		//业务阶段信息
		LnStage lnStage = null;
		
		//正式进件申请明细
		LnApplyMid lnApplyMid = null;
		
		//正式进件合同表
		LnPact lnPact = null;
		
		//放款账号信息
		List<LnAcct> lnAcctList = null;
		
		//放款信息
		LnAcct lnAcct = null;
		
		// 贷款主表信息
		AcLnMst acLnMst = null;
		
		//进件主表信息
		LnBatch lnBatch = null;
		
		//放款成功获取贷款最终状态
		PreparedStatement queryResultStsPst = null;
		ResultSet queryResultStsRs = null;
		
		String txCde = acTraceLog.getTxCde();//交易代码
		if("01".equals(backRes)||"02".equals(backRes)){//放款结果为成功/失败，处理
		
		try{
					
			//借据信息
			acLnDue = (AcLnDue)JdbcDao.query(new AcLnDue(), "due_no='"+acLoanLog.getLoanNo()+"'", "ln_due", conn);

			//业务阶段信息
			lnStage = (LnStage)JdbcDao.query(new LnStage(), "app_id='"+acLnDue.getAppId()+"'", "ln_stage", conn);
			
			//正式进件申请明细
			lnApplyMid = (LnApplyMid)JdbcDao.query(new LnApplyMid(), "app_id='"+acLnDue.getAppId()+"'", "ln_apply_mid", conn);
			
			//正式进件合同表
			lnPact = (LnPact)JdbcDao.query(new LnPact(), "app_id='"+acLnDue.getAppId()+"'", "ln_pact", conn);
			
			//放款账号信息
			lnAcctList = (ArrayList)JdbcDao.queryList(new LnAcct(), "app_id='"+acLnDue.getAppId()+"' and ac_use='2' and ac_sts='01' ","ln_acct", conn);
			
			//放款信息
			lnAcct = (LnAcct)JdbcDao.query(new LnAcct(), "app_id='"+acLnDue.getAppId()+"' and ac_use='2' and ac_no='"+acLoanLog.getLoanAcNo()+"'", "ln_acct", conn);
			
			// 贷款主表信息
			acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+acLoanLog.getLoanNo()+"'", "ac_ln_mst", operaInfo.getConn());
			
			//进件主表信息
			lnBatch = (LnBatch)JdbcDao.query(new LnBatch(), "batch_no='"+acLnDue.getBatchNo()+"'", "ln_batch", conn);
			
			PrdtBase prdtBase = (PrdtBase) JdbcDao.query(new PrdtBase(), "prdt_no='"+acLnMst.getPrdtNo()+"'", "PRDT_BASE", conn);
			if (prdtBase == null) {
				throw new AccountingException("查询不到相应产品表信息!");
			}
			
			
			
			// 合作机构费用提取
			
			
			
			if("01".equals(backRes)){//放款结果为成功
				if(lnAcct==null){//费用
					//获取对应费用记录
					List<AcChrgLog> acChrgLogList = (ArrayList)JdbcDao.queryList(new AcChrgLog(), "trace_no='"+acLoanLog.getTraceNo()+"' and loan_no='"+acLnMst.getLoanNo()+"'", "ac_chrg_log", conn);
					double payChrgAmt = acLoanLog.getLoanAmt();
					for(AcChrgLog acChrgLog : acChrgLogList){
						if(payChrgAmt>0){
							if(NumberUtil.isAmtGreat(payChrgAmt,acChrgLog.getRepayChrgAmt())){
								acChrgLog.setPayChrgAmt(acChrgLog.getPayChrgAmt()+acChrgLog.getRepayChrgAmt());
								payChrgAmt = payChrgAmt - acChrgLog.getRepayChrgAmt();
							}else{
								acChrgLog.setPayChrgAmt(acChrgLog.getPayChrgAmt()+payChrgAmt);
								payChrgAmt = 0.00;
							}
						}
					}
				
			}else if(lnAcctList.size()==1){//一般合作机构放款
				//更新贷款主文件
				//获取回盘后当前日期作为贷款起始日
				String begDate = operaInfo.getBizDt();
				//根据新获取的贷款起始日 、贷款期限月、贷款期限日  -- 计算贷款到期日
				String endDate = DateUtil.addByDay(DateUtil.getDateStr(begDate, acLnMst.getTermMon()), acLnMst.getTermDay());
				acLnMst.setOpnDt(begDate);
				acLnMst.setIcDt(begDate);
				acLnMst.setMtrDt(endDate);
				acLnMst.setLoanBal(acLnMst.getLoanBal()+acLoanLog.getLoanAmt());//更新贷款余额
				acLnMst.setLoanSts("01");//更新状态为正常
				acLnMst.setDealSts("03");//更新处理状态为放款成功
				
				List<AcLnRepayPln> acLnRepayPlnList = new ArrayList<AcLnRepayPln>();
				AcLnRepayPlnBiz acLnRepayPlnBiz = new AcLnRepayPlnBiz();
				if (PUBConstant.BR_ACC_TYPE_A.equals(acLnMst
						.getBrAccType())) {// A类机构放款成功需生成还款计划
					acLnRepayPlnList = acLnRepayPlnBiz
							.getAcLnRepayPln(acLnMst, prdtBase,
									operaInfo);
					JdbcDao.insertList(acLnRepayPlnList,
							"AC_LN_REPAY_PLN", conn);

					// 生成还款计划当期数据
					AcLnRepayPlnCur acLnRepayPlnCur = acLnRepayPlnBiz
							.getAcLnRepayPlnCur(
									acLnRepayPlnList.get(0),
									acLnMst, operaInfo.getBizDt());
					JdbcDao.insert(acLnRepayPlnCur,
							"AC_LN_REPAY_PLN_CUR", conn);
					acLnMst.setCurDueDt(acLnRepayPlnCur.getPayDt()); // 当前归还日期
					acLnMst.setNextDueDt(acLnRepayPlnCur.getPayDt()); // 下一次还款日
					acLnMst.setIntToStpInd(PUBConstant.N);
				}
				
				acLoanLog.setLoanSts(PUBConstant.LOAN_STS_SUCC);//放款信息表状态更改为放款成功
				
				//更新借据表
				acLnDue.setBegDate(begDate);
				acLnDue.setEndDate(endDate);
				acLnDue.setDueSts("02");//正常
				//更新业务阶段表
				lnStage.setPaySts("02");//放款成功
				lnStage.setRsDesc("放款成功");
				lnBatch.setBatchSts("03");//处理完成
				lnBatch.setDueAmt(lnBatch.getDueAmt()+lnApplyMid.getAppAmt());
				lnApplyMid.setBegDate(begDate);
				lnApplyMid.setEndDate(endDate);
				lnPact.setBegDate(begDate);
				lnPact.setEndDate(endDate);
				
			}else if(lnAcctList.size()>=2){//维信卡卡贷 存在多个放款卡号
				//其他卡账号
				String otherAccNo ="";
				for(LnAcct otherLnAcct : lnAcctList){
					if(!otherLnAcct.getAcNo().equals(acLoanLog.getLoanAcNo())){//非本卡
						if(otherAccNo.equals("")){
							otherAccNo += otherLnAcct.getAcNo();
						}else{
							otherAccNo += "','"+otherLnAcct.getAcNo();
						}
					}
				}
				queryResultStsPst = conn.prepareStatement("select case when (select count(*) from (select * "+
             			" from (select loan_ac_no,loan_sts,"+
                        " dense_rank() over(partition by loan_ac_no order by up_date desc) AS xc "+
                        " from ac_loan_log where loan_ac_no in(?)  and app_id = ? ) "+
                        " where xc = 1 and loan_sts in ('02', '06')) ) > 0 then '01' "+
                        " when (select count(*) from(select * from (select loan_ac_no,loan_sts, "+
                        " dense_rank() over(partition by loan_ac_no order by up_date desc) AS xc "+
                        " from ac_loan_log where loan_ac_no in(?) and app_id = ? "+
                        " ) where xc = 1 and loan_sts in('04','05')) ) > 0 then "+
                        " '02' else '03' end as result_sts from dual");
				queryResultStsPst.setString(1, otherAccNo);
				queryResultStsPst.setString(2, acLnDue.getAppId());
				queryResultStsPst.setString(3, otherAccNo);
				queryResultStsPst.setString(4, acLnDue.getAppId());
				queryResultStsRs = queryResultStsPst.executeQuery();
				String resultSts ="";
				if(queryResultStsRs.next()){
					resultSts= queryResultStsRs.getString("result_sts");
				}
				
				if(resultSts.equals("03")){//成功
					//更新贷款主文件
					//获取回盘后当前日期作为贷款起始日
					String begDate = operaInfo.getBizDt();
					//根据新获取的贷款起始日 、贷款期限月、贷款期限日  -- 计算贷款到期日
					String endDate = DateUtil.addByDay(DateUtil.getDateStr(begDate, acLnMst.getTermMon()), acLnMst.getTermDay());
					acLnMst.setOpnDt(begDate);
					acLnMst.setIcDt(begDate);
					acLnMst.setMtrDt(endDate);
					acLnMst.setLoanBal(acLnMst.getLoanBal()+acLoanLog.getLoanAmt());//更新贷款余额
					acLnMst.setLoanSts("01");//更新状态为正常
					acLnMst.setDealSts("03");//更新处理状态为放款成功
					
					List<AcLnRepayPln> acLnRepayPlnList = new ArrayList<AcLnRepayPln>();
					AcLnRepayPlnBiz acLnRepayPlnBiz = new AcLnRepayPlnBiz();
					
					acLoanLog.setLoanSts(PUBConstant.LOAN_STS_SUCC);//放款信息表状态更改为放款成功
					
					//更新借据表
					acLnDue.setBegDate(begDate);
					acLnDue.setEndDate(endDate);
					acLnDue.setDueSts("02");//正常
					//更新业务阶段表
					lnStage.setPaySts("02");//放款成功
					lnStage.setRsDesc("放款成功");
					lnBatch.setBatchSts("03");//处理完成
					lnBatch.setDueAmt(lnBatch.getDueAmt()+lnApplyMid.getAppAmt());
					lnApplyMid.setBegDate(begDate);
					lnApplyMid.setEndDate(endDate);
					lnPact.setBegDate(begDate);
					lnPact.setEndDate(endDate);
					}else if(resultSts.equals("02")){//存在失败
						acLnDue.setBal(acLnDue.getBal()+acLoanLog.getLoanAmt());
						acLnMst.setLoanBal(acLnMst.getLoanBal()+acLoanLog.getLoanAmt());
						acLnMst.setDealSts("05");//更新处理状态为部分放款成功
						
						lnStage.setPaySts("04");//部分放款成功
						lnStage.setRsDesc("部分放款成功");
						lnBatch.setBatchSts("02");//处理中
					}else if(resultSts.equals("01")){//存在未决只更新余额
						acLnDue.setBal(acLnDue.getBal()+acLoanLog.getLoanAmt());
						acLnMst.setLoanBal(acLnMst.getLoanBal()+acLoanLog.getLoanAmt());
					}
				}
				acLoanLog.setLoanSts("03");//放款成功

			}else if("02".equals(backRes)){//02为失败
				if(lnAcct==null){//费用
					
				}else if(lnAcctList.size()==1) {//一般合作机构放款
					acLnMst.setDealSts("04");//更新处理状态为放款失败
					acLnMst.setFailCaus(failCaus);//失败原因
					acLnDue.setDueSts("09");//放款失败
					//更新业务阶段表
					lnStage.setPaySts("03");//放款失败
					lnStage.setRsDesc(failCaus);
					lnBatch.setBatchSts("03");//处理完成
//					_del_result = WorkUtils.getInstance().proc_pact_del(acLnDue.getPactId());
					//放款失败，资金回冲
					try {
						RepayBiz.PROC_REACC_FUND(acLnMst.getLoanNo(), acLoanLog.getLoanAmt(), 0.00,"放款失败", "ADD", conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(lnAcctList.size()>=2) {//卡卡贷
					
					//其他卡账号
					String otherAccNo ="";
					for(LnAcct otherLnAcct : lnAcctList){
						if(!otherLnAcct.getAcNo().equals(acLoanLog.getLoanAcNo())){//非本卡
							if(otherAccNo.equals("")){
								otherAccNo += otherLnAcct.getAcNo();
							}else{
								otherAccNo += "','"+otherLnAcct.getAcNo();
							}
						}
					}
					
					queryResultStsPst = conn.prepareStatement("select case when (select count(*) from (select * "+
                 			" from (select loan_ac_no,loan_sts,"+
                            " dense_rank() over(partition by loan_ac_no order by up_date desc) AS xc "+
                            " from ac_loan_log where loan_ac_no in(?)  and app_id = ? ) "+
                            " where xc = 1 and loan_sts in ('02', '06')) ) > 0 then '01' "+
                            " when (select count(*) from(select * from (select loan_ac_no,loan_sts, "+
                            " dense_rank() over(partition by loan_ac_no order by up_date desc) AS xc "+
                            " from ac_loan_log where loan_ac_no in(?) and app_id = ? "+
                            " ) where xc = 1 and loan_sts=?) ) > 0 then "+
                            " '02' else '03' end as result_sts from dual");
					queryResultStsPst.setString(1, otherAccNo);
					queryResultStsPst.setString(2, acLnDue.getAppId());
					queryResultStsPst.setString(3, otherAccNo);
					queryResultStsPst.setString(4, acLnDue.getAppId());
					queryResultStsPst.setString(5, "03");//成功
					queryResultStsRs = queryResultStsPst.executeQuery();
					String resultSts ="";
					if(queryResultStsRs.next()){
						resultSts= queryResultStsRs.getString("result_sts");
					}
					if(resultSts.equals("01")){//存在未决，不作处理
						
					}else if(resultSts.equals("02")){//存在成功
						acLnMst.setDealSts("05");// 更新处理状态为部分成功
						acLnMst.setFailCaus(failCaus);// 失败原因
						acLnDue.setDueSts("09");// 放款失败
						// 更新业务阶段表
						lnStage.setPaySts("04");// 放款部分成功
						lnStage.setRsDesc("部分放款成功");
						lnBatch.setBatchSts("02");//处理中
					}else if(resultSts.equals("03")){//其他既都为失败
						acLnMst.setDealSts("04");// 更新处理状态为放款失败
						acLnMst.setFailCaus(failCaus);// 失败原因
						acLnDue.setDueSts("09");// 放款失败
						// 更新业务阶段表
						lnStage.setPaySts("03");// 放款失败
						lnStage.setRsDesc(failCaus);
						lnBatch.setBatchSts("03");//处理完成
						
						//放款失败，资金回冲
						try {
							RepayBiz.PROC_REACC_FUND(acLnMst.getLoanNo(), acLoanLog.getLoanAmt(), 0.00,"放款失败", "ADD", conn);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				acLoanLog.setLoanSts("04");//放款信息表状态更改为放款失败
				acLoanLog.setUpDate(ParmBiz.getOracleUpDate(conn));
				
			}else{
				if(lnAcct==null){//费用
					
				}else {//一般合作机构放款
					acLnMst.setDealSts("07");//更新处理状态为放款未决
					lnStage.setRsDesc(failCaus);//更新进件阶段表描述,查询使用
				}
				acLoanLog.setLoanSts("06");//放款信息表状态更改为放款未决
				acLoanLog.setUpDate(ParmBiz.getOracleUpDate(conn));
			}
			
			acLnMst.setLstDt(operaInfo.getTxDt());						//设置上笔发生日期“当前营业日期”
			acLnMst.setUpDt(operaInfo.getTxDt());
			
			
			
			JdbcDao.update(acLnMst, "loan_no='"+acLnMst.getLoanNo()+"'", "ac_ln_mst", conn);
			
			//更新ln_pact表合同开始时间
			JdbcDao.update(lnPact, "app_id='"+acLnDue.getAppId()+"'", "ln_Pact", conn);
			//更新借据表状态
			JdbcDao.update(acLnDue, "due_no='"+acLnDue.getDueNo()+"'", "ln_due", conn);
			
			//更新业务阶段表
			JdbcDao.update(lnStage, "app_id='"+acLnDue.getAppId()+"'", "ln_stage", conn);
			
			//更新进件主表
			JdbcDao.update(lnBatch, "batch_no='"+acLnDue.getBatchNo()+"'", "ln_batch", conn);
			
			//更新ln_apply_mid表放款时间
			if(lnApplyMid != null){
				JdbcDao.update(lnApplyMid, "app_id='"+acLnDue.getAppId()+"'", "ln_apply_mid", conn);
			}

			//原放款流水为账户变更，则需要更新WS及AFT表
			if(TransCode.LNUP.equals(acTraceLog.getTxCde())){
				AftAcno aftAcno = (AftAcno)JdbcDao.query(new AftAcno(), "trace_no='"+acTraceLog.getTraceNo()+"'", "aft_acno", conn);
				if(aftAcno.getChgId().length()==20){
					WsAcnoChg wsAcnoChg = (WsAcnoChg)JdbcDao.query(new WsAcnoChg(), "ws_id='"+aftAcno.getChgId()+"'", "ws_acno_chg", conn);
					if("01".equals(backRes)){
						wsAcnoChg.setDealSts("03");
						wsAcnoChg.setDealDesc("处理成功");
						aftAcno.setChgSts("03");
					}else{
						wsAcnoChg.setDealSts("04");
						wsAcnoChg.setDealDesc(failCaus);
						aftAcno.setChgSts("04");
					}
					JdbcDao.update(wsAcnoChg, "ws_id='"+wsAcnoChg.getWsId()+"'", "ws_acno_chg", conn);
					JdbcDao.update(aftAcno, "chg_id='"+wsAcnoChg.getWsId()+"'", "aft_acno", conn);
				}
				//JdbcDao.query(aftAcno, "chg_id='"+aftAcno.getChgId()+"'", "aft_acno", conn);
				//更新账号变更流水为正常
				acTraceLog.setCancelInd("NORM");
				//JdbcDao.query(acTraceLog, "trace_no='"+acTraceLog.getTraceNo()+"'", "ac_trace_log", conn);
			}
			
//			//获得登记交易流水
//			AcTraceLog traceLog = this.getTraceLog(operaInfo, acLnMst,acLoanLog);
//			JdbcDao.insert(traceLog, "AC_TRACE_LOG", conn);
		
//		}
		
		//更新放款反馈表状态
		acLoanBackLog.setBackRes(lb.getBackRes());
		acLoanBackLog.setFailCaus(failCaus);
		acLoanBackLog.setBackDate(ParmBiz.getBizDt(conn));
		acLoanBackLog.setBackSts(PUBConstant.BACK_STS_06);//已处理
		acLoanBackLog.setStatus(status);
		acLoanBackLog.setCardChn(cardChn);
		acLoanBackLog.setUpTime(ParmBiz.getOracleUpDate(conn));
		RepayBiz.updateAcLoanBackLog(acLoanBackLog, operaInfo);
//		JdbcDao.update(acLoanBackLog, "back_log_no='"+acLoanBackLog.getBackLogNo()+"' and back_cnt='"+acLoanBackLog.getBackCnt()+"'", "ac_loan_back_log", conn);

		//更新放款信息表状态
		acLoanLog.setUpDate(ParmBiz.getOracleUpDate(conn));
		acLoanLog.setCardChn(cardChn);
		JdbcDao.update(acLoanLog, "loan_log_no='"+acLoanBackLog.getLoanLogNo()+"'", "ac_loan_log", conn);
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		}else if("04".equals(backRes)){//异常未决
			acLoanBackLog.setBackSts(PUBConstant.BACK_STS_05);
			RepayBiz.updateAcLoanBackLog(acLoanBackLog, operaInfo);
//			JdbcDao.update(acLoanBackLog, "back_log_no='"+acLoanBackLog.getBackLogNo()+"' and back_cnt='"+acLoanBackLog.getBackCnt()+"'", "ac_loan_back_log", conn);
		}
		return null;

	}
	
	
	/**
	 *  
	 * @作者 DHCC-LIUJ
	 * @日期 2016-6-28
	 * @描述	获取放款信息，根据借据号查询账户表账户用途为放款账户的记录，根据该记录生成放款信息
	 */
	private List<AcLoanLog> getAcLoanLogList(AcLnDue acLnDue , OperaInfo operaInfo) throws AccountingException{
		Connection conn = operaInfo.getConn() ;
		
		//账户信息表
		List<LnAcct> lnAcctList = (List<LnAcct>) JdbcDao.query(new LnAcct(), "app_id='"+acLnDue.getAppId()+"'", "ln_acct", operaInfo.getConn());
		
		//项目账号表
		ProjAcct projAcct = (ProjAcct) JdbcDao.query(new ProjAcct(), "proj_no='"+acLnDue.getProjNo()+"'", "proj_acct", operaInfo.getConn());

		List<AcLoanLog> acLoanLogList = new ArrayList<AcLoanLog>();
		for(int i=0;i<lnAcctList.size();i++){
			AcLoanLog acLoanLog = new AcLoanLog();
			acLoanLog.setTraceNo(operaInfo.getTraceNo());
			acLoanLog.setLoanLogNo(ParmBiz.getAcLoanLogNo(conn));
			acLoanLog.setLoanNo(acLnDue.getDueNo());
			acLoanLog.setPactNo(acLnDue.getPactNo());
			acLoanLog.setBrNo(acLnDue.getBrNo());
			acLoanLog.setLoanAmt(lnAcctList.get(i).getAcAmt());
			acLoanLog.setXtAcNo(projAcct.getAcctNo());//信托账户
			acLoanLog.setLoanAcNo(lnAcctList.get(i).getAcNo());//放款账号
			acLoanLog.setLoanAcType(lnAcctList.get(i).getAcType());
			acLoanLog.setLoanAcName(lnAcctList.get(i).getAcName());
			acLoanLog.setLoanBankCode(lnAcctList.get(i).getBankCode());
			acLoanLog.setLoanBankProv(lnAcctList.get(i).getBankProv());
			acLoanLog.setLoanBankCity(lnAcctList.get(i).getBankCity());
			acLoanLog.setLoanBankSite(lnAcctList.get(i).getBankSite());
			acLoanLog.setLoanSts("01");//待处理
			acLoanLog.setTxDate(operaInfo.getTxDt());
			acLoanLog.setTxTime(ParmBiz.getOracleTxTime(conn));
			acLoanLogList.add(acLoanLog);
		}
		return acLoanLogList;
	}
//	/**
//	 * 得到登记交易流水表信息
//	 * @param operaInfo 业务操作对象
//	 * @param lnMst 贷款主表对象
//	 * 
//	 * @return traceLog    登记交易流水表对象
//	 * @throws AccountingException
//	 */
//	private AcTraceLog getTraceLog(OperaInfo operaInfo,AcLnMst lnMst , AcLoanLog acLoanLog)throws AccountingException {
//		AcTraceLog traceLog = new AcTraceLog();
//		
//		Connection conn = operaInfo.getConn() ;
//
//		traceLog.setTraceNo(operaInfo.getTraceNo());//设置流水号
//		traceLog.setTraceCnt(1);					//设置流水笔次
//		traceLog.setTxDt(operaInfo.getTxDt());		//设置交易日期
//    	traceLog.setTxTime(ParmBiz.getOracleTxTime(conn)) ;	//设置交易时间
//		traceLog.setTxBrNo(operaInfo.getTxBrNo());	//设置交易机构号
//		traceLog.setTxCde(TransCode.LNC3);			//设置交易代码
//		traceLog.setSubTxCde(TransCode.LNC3);		//设置子交易代码
//		traceLog.setCurNo(lnMst.getCurNo());		//设置币种
//		traceLog.setPrdtNo(lnMst.getPrdtNo());		//设置产品编号
//		traceLog.setPactNo(lnMst.getPactNo());
//		traceLog.setBrNo(lnMst.getBrNo());
//		traceLog.setLoanNo(lnMst.getLoanNo());		//设置借据号
//		traceLog.setAddInd(PUBConstant.INC);		//设置增减标志为“增加”
//		traceLog.setCtInd(PUBConstant.TRANS);		//设置现转标志为“转账”
//		traceLog.setAmt(acLoanLog.getLoanAmt());		//设置发生额
//		traceLog.setCancelInd(PUBConstant.REV_ROL_NORM);// 设置撤销标志		
//		traceLog.setMemo("");
//		return traceLog;
//	}
	
	public void getAcTxDetail(){
		
	}

}