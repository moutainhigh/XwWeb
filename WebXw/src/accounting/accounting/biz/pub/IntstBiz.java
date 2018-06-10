package accounting.biz.pub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import accounting.domain.loan.AcFineFormula;
import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnPayPln;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.PrdtBase;
import accounting.domain.sys.OperaInfo;
import accounting.interf.loan.IntstDetailDatas;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.TimeUtil;
import accounting.plat.util.TransUtil;
import app.util.DateUtil;

/**
 * 利息相关类
 * 
 * @project：nxnxnew
 * @className：IntstBiz
 * @author：su
 */
public class IntstBiz {
	

	/**
	 * 计算每一期的欠款表的罚息
	 * 
	 * @param acLnMst
	 *            贷款主文件
	 * @param acLnLo
	 *            贷款欠款表
	 * @param txDate
	 *            交易日期
	 * @param acLnParm
	 *            产品参数表
	 * @param conn
	 *            数据库连接
	 * @return 存放每一期欠款表利息的利息类
	 * @throws Exception 
	 */
	public static IntstDetailDatas calculatePerLoInt(AcLnMst acLnMst, AcLnLo acLnLo, OperaInfo operaInfo) throws Exception {
		IntstDetailDatas datas = new IntstDetailDatas();
//		PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(acLnMst.getPrdtNo());
		PrdtBase prdtBase = (PrdtBase) JdbcDao.query(new PrdtBase(), "prdt_no='"+acLnMst.getPrdtNo()+"'", "PRDT_BASE", operaInfo.getConn());

		double normInt = 0; 					// 欠正常利息
		double prcpAmt = 0; 					// 欠款本金
		double fineInt = 0; 					// 罚息
		
		//上次结罚息日期
		String begDate = acLnLo.getFineIntDt();
		if(begDate==null || begDate.equals("")){
			begDate = acLnLo.getPayDt() ;
		}

		// 计算天数
		int days = TimeUtil.getBetweenDays(begDate, operaInfo.getBizDt());
		
		// 计算欠款本金： 欠款本金=原本金-已还本金-减免本金
		prcpAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getPrcpAmt(), acLnLo.getRepayPrcpAmt()), acLnLo.getWvPrcpAmt()) ;
		
		//计算正常利息：欠正常利息=原正常利息-已还正常利息-减免正常利息
		normInt = NumberUtil.amtSubs(NumberUtil.amtSubs(acLnLo.getNormInt(), acLnLo.getRepayNormInt()), acLnLo.getWvNormInt());

		//计算本次结息产生的罚息
		fineInt = calFineInt(acLnLo ,acLnMst.getLoanAmt(),prcpAmt,normInt , days, prdtBase , operaInfo);
		
		//原欠款表罚息+本次结息计算出的罚息 = 实际欠罚息
		fineInt = NumberUtil.amtAdd(acLnLo.getFineInt(), fineInt);

		datas.setPrcpAmt(prcpAmt); // 欠本金
		datas.setNormInt(normInt); // 欠利息
		datas.setFineInt(fineInt); // 欠罚息
		return datas;
	}

	/**
	 * 
	 * @throws Exception 
	 * @作者 DHCC-LIUJ
	 * @日期 2018-4-25
	 * @描述 计算罚息
	 */
	public static double calFineInt(AcLnLo acLnLo,double loanAmt,double prcpAmt,double normInt, Integer day , PrdtBase prdtBase , OperaInfo operaInfo )throws Exception{
		double fineInt = 0.00;
		String defNo = prdtBase.getDefNo();//罚息编号
		
		AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "loan_no='"+acLnLo.getLoanNo()+"'", "AC_LN_MST", operaInfo.getConn());
		
		double lnRate = acLnMst.getLnRate();
		if(defNo != null && defNo.length()>0){
			AcFineFormula acFineFormula = (AcFineFormula)JdbcDao.query(new AcFineFormula(), "fine_id = '"+defNo+"'","ac_fine_formula", operaInfo.getConn());
			
			//总欠款记录
			List<AcLnLo> acLnLoList = (ArrayList)JdbcDao.queryList(new AcLnLo(), "loan_no = '"+acLnLo.getLoanNo()+"' and setl_sts='N'","ac_ln_lo", operaInfo.getConn());
			
			String fineFormula = acFineFormula.getFineFormula();//获取罚息配置公式
			double overRate = prdtBase.getOverRate();//获取逾期利率
			//基本指标与实际值映射关系
			Map<String,String> parmMp = new HashMap<String, String>();
			parmMp.put("A", String.valueOf(prcpAmt));
			parmMp.put("B", String.valueOf(normInt));
			parmMp.put("C", String.valueOf(day));
//			DecimalFormat nf = new DecimalFormat();  
//			nf.setMaximumFractionDigits(10); // 设置最大小数位
			DecimalFormat df6 =new java.text.DecimalFormat("0.000000");
			DecimalFormat df =new java.text.DecimalFormat("###.00"); 
			parmMp.put("D", df6.format(overRate));
			parmMp.put("E", df.format(loanAmt));
			parmMp.put("F", String.valueOf(acLnLoList.size()));
			parmMp.put("G", df6.format(lnRate/100/30));//日利率
			FeeBiz feeBiz = new FeeBiz();
			fineFormula = feeBiz.replaceParms(fineFormula, parmMp);
			
			fineInt = Calc.doCalc(fineFormula).getValue();
		}
		
		return fineInt;
		
	}
	
	/**
	 * 提前还款计算利息
	 * 
	 * @param acLnMst
	 *            贷款主文件
	 * @param acLnLoList
	 *            存放欠款表信息的list
	 * @param remAmt
	 *            提前还本金额
	 * @param conn
	 *            数据库连接
	 * @return datas 利息类
	 * @throws Exception 
	 */
	public static IntstDetailDatas calculateTtlIntIsMortgage(AcLnMst acLnMst,  double remAmt, PrdtBase prdtBase, OperaInfo operaInfo) throws Exception {
		
		IntstDetailDatas datas = new IntstDetailDatas() ;
		
		double normInt = 0.00; 			// 欠本金利息
		double prcpAmt = 0.00; 			// 欠款本金
		double fineInt = 0.00; 			// 罚息
		double curInt = 0.00; 			// 当期利息
		
		String intDt = operaInfo.getBizDt();	// 预计结息日，交易日期
		String txDt = operaInfo.getTxDt();		// 当前日期，系统日期
		
		String loanNo = acLnMst.getLoanNo();
		
		//当期营业日之后的还款计划
		List<AcLnRepayPln> acLnRepayPlnList = JdbcDao.queryList(new AcLnRepayPln(), "loan_no='"+loanNo+"' and pay_dt>='"+txDt+"' and setl_sts='"+PUBConstant.N+"'", "perd_no", "ac_ln_repay_pln", operaInfo.getConn());
		
		
		 
		//计算欠款表中的数据，到预结息日时产生的利息
		//ac_ln_lo欠款表，从中取出逾期的还款信息，欠本，欠息，罚息
		List<AcLnLo> acLnLoList = JdbcDao.queryList(new AcLnLo(), "loan_no='" + acLnMst.getLoanNo() +"' and setl_sts='N'", "perd_no", "ac_ln_lo", operaInfo.getConn());
		for (int i=0;i<acLnLoList.size();i++){
			AcLnLo acLnLo = (AcLnLo)acLnLoList.get(i) ;

//			IntstDetailDatas detailDatas = calculatePerLoInt(acLnMst, acLnLo, operaInfo);					

			prcpAmt = NumberUtil.amtAdd(prcpAmt, acLnLo.getPrcpAmt()); 
			normInt = NumberUtil.amtAdd(normInt, acLnLo.getNormInt());
			fineInt = NumberUtil.amtAdd(fineInt, acLnLo.getFineInt());
		}

		//最后一期还款计划
		AcLnRepayPln lastPayPln = (AcLnRepayPln)acLnRepayPlnList.get(acLnRepayPlnList.size()-1) ;
		String lastRepayDt = lastPayPln.getPayDt();		//最后一期还款计划还款日

		//如果当前营业日期>贷款到期日，则只需计算欠款表中数据产生的利息
		//否则进行如下操作计算当期利息
		if(TimeUtil.compareDate(txDt, lastRepayDt)<=0){
			// 预计结息日所在期的还款计划
			
			AcLnRepayPln acLnRepayPlnPur = getAcLnRepayPln(intDt, acLnRepayPlnList);
			// 预计结息日对应的还款计划期数
			int perdNoPur = acLnRepayPlnPur.getPerdNo() ;

			// 当前日所在期的还款计划
			AcLnRepayPln acLnRepayPlnCur = acLnRepayPlnList.get(0) ;
			int  perdNocur = acLnRepayPlnCur.getPerdNo();

			//将当前营业日至预结息日间的还款期当做拖欠处理11111111perdNoPur-perdNocur
			for(int i=0;i<perdNoPur-perdNocur;i++){
				
				AcLnLo acLnLoT = new AcLnLo() ;
				TransUtil.copyProperties((AcLnRepayPln)acLnRepayPlnList.get(i), acLnLoT);

//				IntstDetailDatas detailDatas = calculatePerLoInt(acLnMst, acLnLoT, operaInfo);	

				prcpAmt = NumberUtil.amtAdd(prcpAmt, acLnLoT.getPrcpAmt());
				normInt = NumberUtil.amtAdd(normInt, acLnLoT.getNormInt());
				fineInt = NumberUtil.amtAdd(fineInt, acLnLoT.getFineInt());
			}

			curInt = calCurInt(acLnMst, acLnRepayPlnPur, remAmt, operaInfo);
		}
		
		AcLnRepayPlnCur acLnRepayPlnCur = (AcLnRepayPlnCur)JdbcDao.query(new AcLnRepayPlnCur(), "LOAN_NO = '"+acLnMst.getLoanNo()+"'","AC_LN_REPAY_PLN_CUR", operaInfo.getConn());

		double feeAmt = FeeBiz.getDamAmt(acLnRepayPlnCur, acLnMst, operaInfo);
		
		datas.setFeeAmt(feeAmt);		//期缴费
		datas.setCurInt(curInt); 		// 当期利息
		datas.setRemPrcpAmt(remAmt) ;	// 提前还本金额
		
		datas.setPrcpAmt(prcpAmt);     //欠款本金
		datas.setNormInt(normInt);    //欠息
		datas.setFineInt(fineInt);    //罚息
		/**欠费用合计
		double loFeeAmt = 0.00;//欠费
		String loFeeAmtSql = "select nvl(sum(chrg_amt)-sum(repay_chrg_amt)-sum(wv_chrg_amt),0) as lo_fee_amt from ac_chrg_log where chrg_sts!='3' and loan_no='"+acLnMst.getLoanNo()+"'";
		PreparedStatement loFeeAmtPs = operaInfo.getConn().prepareStatement(loFeeAmtSql);
		ResultSet loFeeAmtRs = loFeeAmtPs.executeQuery();
		while (loFeeAmtRs.next()) {
			loFeeAmt = loFeeAmtRs.getDouble("lo_fee_amt");
		}
		loFeeAmtPs.close();
		loFeeAmtRs.close();
		*/
		datas.setLoFeeAmt(0.0);
		
		
		return datas;
	}


	/**
	 * 计算当前期利息（提前还款结息用）
	 * @param acLnMst
	 * @param acLnRepayPlnCur
	 * @param remAmt
	 * @param operaInfo
	 * @return
	 * @throws AccountingException
	 */
	public static double calCurInt(AcLnMst acLnMst,AcLnRepayPln acLnRepayPlnCur,double remAmt, OperaInfo operaInfo)throws AccountingException{

		double curInt = 0.00;
		
		int perdNoCur = acLnRepayPlnCur.getPerdNo();
		String intDt = operaInfo.getBizDt();
		
		// 结息日所在期的上一期记录
		AcLnRepayPln perAcLnRepayPln = null;
		if(perdNoCur>1){
			perAcLnRepayPln = (AcLnRepayPln)JdbcDao.query(new AcLnRepayPln(), "loan_no = '"+acLnMst.getLoanNo()+"' and perd_no = "+(perdNoCur-1), "AC_LN_REPAY_PLN", operaInfo.getConn());
		}
		
			
		if (perdNoCur > 1) {
			remAmt = perAcLnRepayPln.getBal();
		} else {
			remAmt = acLnMst.getLoanAmt();
		}
		String begDt = null;
		double bal = 0.00;
		if(perdNoCur>1){
			begDt = perAcLnRepayPln.getPayDt() ;
			bal = perAcLnRepayPln.getBal();
		}else{
			begDt = acLnMst.getOpnDt() ;
			bal = acLnMst.getLoanAmt();
		}

		if (operaInfo.getBizDt().equals(acLnRepayPlnCur.getPayDt())) {
			curInt = acLnRepayPlnCur.getNormInt();
		} else {
//			rateDaly = getDaylyRateByYearly(acLnMst.getCurNo(), acLnMst.getLnRate()*12) ;
			curInt = getInterestByAmtAndDaysAndRate(bal, begDt, intDt, acLnMst.getLnRate()/(30*100));
		}

		return curInt;
			
	}
	
	/**
	 * 通过金额,天数,利率获得利息
	 * 
	 * @param amt
	 *            金额
	 * @param date1
	 *            起始日期
	 * @param date2
	 *            结束日期
	 * @param rateDaly
	 *            日利率
	 * @return interest 利息
	 * @throws AccountingException
	 */
	public static double getInterestByAmtAndDaysAndRate(double amt, String date1, String date2, double rateDaly)
	throws AccountingException {
		double interest = 0;
		int betwDay = (int)DateUtil.getDaysBetween(date1, date2);
		
		// 获得利息
		interest = NumberUtil.amtMult(NumberUtil.amtMult(amt, rateDaly),betwDay);

		return interest;
	}
	/**
	 * 根据指定日期得出其所在的还款计划中的对象
	 * @param txDate				指定时期    	  
	 * @param acLnTtlPayPlnList		还款计划表
	 * 
	 * @return  acLnPayPln    		还款计划对象
	 * @throws AccountingException
	 */
	public static AcLnRepayPln getAcLnRepayPln(String txDate, List acLnRepayPlnList)throws AccountingException{
		int ttlCnt = acLnRepayPlnList.size() ;

		AcLnRepayPln acLnRepayPln = null ;
		for(int i = 0;i<ttlCnt;i++){
			acLnRepayPln = (AcLnRepayPln)acLnRepayPlnList.get(i) ;

			if(TimeUtil.compareDate(acLnRepayPln.getPayDt(), txDate) > 0)
				return acLnRepayPln ;
		}

		return acLnRepayPln;
	}
	
}
