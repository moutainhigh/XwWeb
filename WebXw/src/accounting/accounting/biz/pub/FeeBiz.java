package accounting.biz.pub;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import accounting.domain.fee.AcChrgLog;
import accounting.domain.loan.AcFeeFormula;
import accounting.domain.loan.AcFeeParm;
import accounting.domain.loan.AcFeeRate;
import accounting.domain.loan.AcLnDue;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.loan.PrdtBase;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import app.base.ServiceException;

public class FeeBiz {
	
		
	/**
	 * 
	 * @throws Exception 
	 * @作者 DHCC-LIUJ
	 * @日期 2018-4-25
	 * @描述	放款环节获取费用配置中场景为放款的服务费
	 */
	public static List<AcChrgLog> getAcChrgLogListFk(int traceCnt,String loanNo, String prdtNo, String txCode, double txAmt, String curNo, String saveInd, OperaInfo operaInfo)throws AccountingException{
		List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
		Connection conn = operaInfo.getConn();
		//借据信息
		AcLnDue acLnDue = (AcLnDue)JdbcDao.query(new AcLnDue(), "due_no='"+loanNo+"'", "ln_due", operaInfo.getConn());

		// 核算参数表信息
		PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(prdtNo);
		if (prdtBase == null) {
			throw new AccountingException("查询不到相应产品参数表信息!");
		}		
		
		String traceNo = operaInfo.getTraceNo();

		//费用编号
		String feeNo = prdtBase.getFeeNo();
		//费用金额
		double feeAmt = 0.0;
		//判定产品信息中是否配置费用公式
		if(feeNo != null && feeNo.length()>0){
			String[] feeNos = feeNo.split("\\@");
			for(int i=0;i < feeNos.length;i++){
				//费用配置
				AcFeeParm acFeeParm = (AcFeeParm)JdbcDao.query(new AcFeeParm(), "fee_parm_id='"+feeNos[i]+"'", "ac_fee_parm", operaInfo.getConn());
				if(acFeeParm != null && acFeeParm.getFeeFormId()!=null&&acFeeParm.getFeeFormId().length()>=0){
					//判定费用场景为放款
					if(txCode.equals(acFeeParm.getFeeScenes())){
						AcFeeFormula acFeeFormula = (AcFeeFormula)JdbcDao.query(new AcFeeFormula(), "fee_form_id='"+acFeeParm.getFeeFormId()+"'", "ac_fee_formula", operaInfo.getConn());
						AcLnRepayPlnCur acLnRepayPlnCur = null;
						feeAmt = getFeeAmt(acLnRepayPlnCur,acFeeFormula, acLnDue.getDueAmt(),acLnDue.getTermMon(), operaInfo);
						if(feeAmt>0){//计算出的服务费大于零时，增加费用明细
							AcChrgLog acChrgLog = new AcChrgLog();
							acChrgLog.setChrgId(ParmBiz.getAcChrgLogId(conn));
							acChrgLog.setTraceNo(traceNo);
							acChrgLog.setLoanNo(acLnDue.getDueNo());
							acChrgLog.setPactNo(acLnDue.getPactNo());
							acChrgLog.setBrNo(acLnDue.getBrNo());
							acChrgLog.setPerdNo(txCode);
							if(acFeeParm.getFeeMax()!=null){
								//若计算出的费用大于费用配置的最大值，则取费用配置最大值
								if(feeAmt>acFeeParm.getFeeMax()){
									feeAmt = acFeeParm.getFeeMax();
								}
							}
							if(acFeeParm.getFeeMin()!=null){
								//若计算出的费用小于费用配置的最小值，则取费用配置最小值
								if(feeAmt<acFeeParm.getFeeMin()){
									feeAmt = acFeeParm.getFeeMin();
								}
							}
							acChrgLog.setChrgAmt(feeAmt);
							acChrgLog.setFeeParmId(acFeeParm.getFeeFormId());
							acChrgLog.setFeeKind(acFeeParm.getFeeKind());
							acChrgLog.setRepayChrgAmt(0.00);
							acChrgLog.setChrgSts("01");//待处理
							acChrgLog.setTxDate(operaInfo.getTxDt());
							acChrgLog.setTxTime(ParmBiz.getOracleTxTime(conn));
							acChrgLogList.add(acChrgLog);
						}
					}
				}
			}
		}
		return acChrgLogList;
	}
	
	/**
	 * 
	 * @throws Exception 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-6-27
	 * @描述	根据借据信息与罚息公式信息获取费用金额
	 */
	public static double getFeeAmt(AcLnRepayPlnCur acLnRepayPlnCur,AcFeeFormula acFeeFormula,double amt, Integer termMon,OperaInfo operaInfo) throws AccountingException{
		double feeAmt = 0.0;//费用金额
		//费用公式
		String feeFormula = acFeeFormula.getFeeFormula();
		//基本指标对应实际值
		String parmA = String.valueOf(amt);//借据金额
		String parmB = "";//当期应收本金
		String parmC = "";//当期应收利息
		if(acLnRepayPlnCur != null){
			parmB = String.valueOf(acLnRepayPlnCur.getPrcpAmt());
			parmC = String.valueOf(acLnRepayPlnCur.getNormInt());
		}
		String parmD = "";//费率
		if(acFeeFormula.getFrId()!=null && acFeeFormula.getFrId().length()>0){
			String frId = acFeeFormula.getFrId() ;//费率编号
				List<AcFeeRate> acFeeRateList = (ArrayList)JdbcDao.queryList(new AcFeeRate(), "fr_id='"+frId+"' and '" + termMon +"' >= min_mon and max_mon > '" +termMon +"'", "ac_fee_rate", operaInfo.getConn());
				if(acFeeRateList.size()>0){
					parmD = String.valueOf(acFeeRateList.get(0).getFeeRate());
				}
		}
		//基本指标与实际值映射关系
		Map<String,String> parmMp = new HashMap<String, String>();
		parmMp.put("A", parmA);
		parmMp.put("B", parmB);
		parmMp.put("C", parmC);
		parmMp.put("D", parmD);
		String formula = replaceParms(acFeeFormula.getFeeFormula(), parmMp);
		
		try{
			feeAmt = Calc.doCalc(formula).getValue();
		}catch(Exception e){
			throw new AccountingException("公式：" + formula + "，不符合解析规则！");
		}
		
		return feeAmt; 
		
	}
	
	/**
	 * 
	 * @throws Exception 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-6-27
	 * @描述	根据借据信息与罚息公式信息获取费用金额
	 */
	public static double getFeeAmt(String acquRate,AcLnRepayPlnCur acLnRepayPlnCur,AcFeeFormula acFeeFormula,double amt, Integer termMon,OperaInfo operaInfo,double premRate) throws AccountingException{
		double feeAmt = 0.0;//费用金额
		//费用公式
		String feeFormula = acFeeFormula.getFeeFormula();
		DecimalFormat df = new DecimalFormat("0.00000000");
		//基本指标对应实际值
		String parmA = String.valueOf(amt);//借据金额
		String parmB = "";//当期应收本金
		String parmC = "";//当期应收利息
		if(acLnRepayPlnCur != null){
			parmB = String.valueOf(NumberUtil.amtSubs(NumberUtil.amtSubs(acLnRepayPlnCur.getPrcpAmt(), acLnRepayPlnCur.getRepayPrcpAmt()),acLnRepayPlnCur.getWvPrcpAmt()));
			parmC = String.valueOf(NumberUtil.amtSubs(NumberUtil.amtSubs(acLnRepayPlnCur.getNormInt(),acLnRepayPlnCur.getRepayNormInt()),acLnRepayPlnCur.getWvNormInt()));
		}
		String parmD = "";//费率
		//进件
		if("1".equals(acquRate)){
			parmD = String.valueOf(df.format(premRate/100));
		}else{
			if(acFeeFormula.getFrId()!=null && acFeeFormula.getFrId().length()>0){
				String frId = acFeeFormula.getFrId().replaceAll("@", "','") ;//费率编号
					List<AcFeeRate> acFeeRateList = (ArrayList)JdbcDao.queryList(new AcFeeRate(), "fee_id in ('"+frId+"') and '" + termMon +"' >= min_mon and max_mon > '" +termMon +"'", "ac_fee_rate", operaInfo.getConn());
					if(acFeeRateList.size()>0){
						parmD = String.valueOf(df.format(acFeeRateList.get(0).getFeeRate()));
					}
			}
		}
		
		
		//基本指标与实际值映射关系
		Map<String,String> parmMp = new HashMap<String, String>();
		parmMp.put("A", parmA);
		parmMp.put("B", parmB);
		parmMp.put("C", parmC);
		parmMp.put("D", parmD);
		String formula = replaceParms(acFeeFormula.getFeeFormula(), parmMp);
		
		try{
			feeAmt = Calc.doCalc(formula).getValue();
		}catch(Exception e){
			throw new AccountingException("公式：" + formula + "，不符合解析规则！");
		}
		
		return feeAmt; 
		
	}
	
	
	
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-6-27
	 * @描述 使用基础指标对应值替换字符串公式
	 */
	public static String replaceParms(String formula , Map<String,String> parmMp) throws ServiceException{
		for (Map.Entry<String, String> entry : parmMp.entrySet()) {
			formula = formula.replace(entry.getKey(), entry.getValue());
		}
		return formula;
	}
	
	/**
	 * 批量结息环节，扣款日结息产生
	 * @throws AccountingException 
	 */
	public static void getAcChrgLogLNAA(AcLnRepayPlnCur acLnRepayPlnCur,AcLnMst acLnMst, OperaInfo operaInfo) throws AccountingException{
		List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
		
		Connection conn = operaInfo.getConn();
		//产品参数
		PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(acLnMst.getPrdtNo());
		//费用编号
		String feeNo = prdtBase.getFeeNo();
		//费用金额
		double feeAmt = 0.0;
		//判定产品信息中是否配置费用公式
		if(feeNo != null && feeNo.length()>0){
			String[] feeNos = feeNo.split("\\|");
			for(int i=0;i < feeNos.length;i++){
				//费用配置
				AcFeeParm acFeeParm = (AcFeeParm)JdbcDao.query(new AcFeeParm(), "fee_parm_id='"+feeNos[i]+"'", "ac_fee_parm", operaInfo.getConn());
				if(acFeeParm != null && acFeeParm.getFeeFormId()!=null&&acFeeParm.getFeeFormId().length()>=0){
					//判定费用场景为还款
					if("LNAA".equals(acFeeParm.getFeeScenes())){
						AcFeeFormula acFeeFormula = (AcFeeFormula)JdbcDao.query(new AcFeeFormula(), "fee_form_id='"+acFeeParm.getFeeFormId()+"'", "ac_fee_formula", operaInfo.getConn());
						feeAmt = getFeeAmt(acLnRepayPlnCur,acFeeFormula, acLnMst.getLoanAmt(),acLnMst.getTermMon(), operaInfo);
						if(feeAmt>0){//计算出的服务费大于零时，增加费用明细
							AcChrgLog acChrgLog = new AcChrgLog();
							acChrgLog = new AcChrgLog();
							acChrgLog.setChrgId(ParmBiz.getAcChrgLogId(conn));
							acChrgLog.setTraceNo(operaInfo.getTraceNo());
							acChrgLog.setLoanNo(acLnMst.getLoanNo());
							acChrgLog.setPactNo(acLnMst.getPactNo());
							acChrgLog.setBrNo(acLnMst.getBrNo());
							acChrgLog.setPerdNo(acFeeParm.getFeeScenes());
							acChrgLog.setFeeParmId(acFeeParm.getFeeParmId());
							acChrgLog.setFeeKind(acFeeParm.getFeeKind());
							acChrgLog.setChrgAmt(feeAmt);
							acChrgLog.setRepayChrgAmt(0.00);
							acChrgLog.setChrgSts("01");//待处理
							acChrgLog.setTxDate(operaInfo.getTxDt());
							acChrgLog.setTxTime(ParmBiz.getOracleTxTime(conn));
							acChrgLogList.add(acChrgLog);
						}
					}
				}
			}
		}
		if(acChrgLogList.size()>0){
			JdbcDao.insertList(acChrgLogList, "ac_chrg_log", conn);
			// 新增交易流水表记录
			AcTraceLog log = new AcTraceLog();
			log.setTxBrNo(operaInfo.getTxBrNo());
			LoanBiz.insertLog(log, acLnMst, operaInfo, "LNAA", "LNAA", PUBConstant.INC);
		}
	}
	
	/**
	 * 提前还款产生费用
	 * @throws AccountingException 
	 */
	public static double getDamAmt(AcLnRepayPlnCur acLnRepayPlnCur,AcLnMst acLnMst, OperaInfo operaInfo) throws AccountingException{
		
		//产品参数
		//PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(acLnMst.getPrdtNo());
		PrdtBase prdtBase = (PrdtBase)JdbcDao.query(new PrdtBase(), "prdt_no='"+acLnMst.getPrdtNo()+"'", "prdt_base", operaInfo.getConn());
		//费用编号
		String feeNo = prdtBase.getFeeNo();
		//费用金额
		double feeAmt = 0.0;
		//判定产品信息中是否配置费用公式
		if(feeNo != null && feeNo.length()>0){
			String[] feeNos = feeNo.split("\\@");
			for(int i=0;i < feeNos.length;i++){
				//费用配置
				AcFeeParm acFeeParm = (AcFeeParm)JdbcDao.query(new AcFeeParm(), "fee_parm_id='"+feeNos[i]+"'", "ac_fee_parm", operaInfo.getConn());
				if(acFeeParm != null && acFeeParm.getFeeFormId()!=null&&acFeeParm.getFeeFormId().length()>=0){
					//判定费用场景为还款
					if("LNAA".equals(acFeeParm.getFeeScenes())){
						AcFeeFormula acFeeFormula = (AcFeeFormula)JdbcDao.query(new AcFeeFormula(), "fee_form_id='"+acFeeParm.getFeeFormId()+"'", "ac_fee_formula", operaInfo.getConn());
						feeAmt = feeAmt+getFeeAmt(acLnRepayPlnCur,acFeeFormula, acLnMst.getLoanAmt(),acLnMst.getTermMon(), operaInfo);
					}
				}
			}
		}
		return feeAmt;
	}
	
	/**
	 * 提前还款生成费用
	 * @param acLnRepayPlnCur
	 * @param acLnMst
	 * @param conn
	 * @param method 提前还款费用收取方式 01 全期 02 天数比例 03 无费用
	 * @param days 天数
	 * @return
	 * @throws AccountingException
	 */
	 
	public static List<AcChrgLog> getDamFeeAmt(AcLnRepayPlnCur acLnRepayPlnCur,app.creditapp.acc.loan.entity.AcLnMst acLnMst, Connection conn,String method,int days,String traceNo) throws AccountingException{
		List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
		OperaInfo operaInfo = new OperaInfo(conn);
		operaInfo.setTraceNo(traceNo);
		//产品参数
		PrdtBase prdtBase = (PrdtBase)JdbcDao.query(new PrdtBase(), "prdt_no='"+acLnMst.getPrdtNo()+"'", "prdt_base", conn);
		//费用编号
		String feeNo = prdtBase.getFeeNo();
		//费用金额
		double feeAmt = 0.0;
		double rate = 0;//费用收取比例
		if("01".equals(method)){
			rate = 1;
		}else if("02".equals(method)){
			rate = NumberUtil.div(days, 30, 2);
		}
		//判定产品信息中是否配置费用公式
		if(feeNo != null && feeNo.length()>0){
			String[] feeNos = feeNo.split("\\@");
			for(int i=0;i < feeNos.length;i++){
				//费用配置
				AcFeeParm acFeeParm = (AcFeeParm)JdbcDao.query(new AcFeeParm(), "fee_parm_id='"+feeNos[i]+"'", "ac_fee_parm", conn);
				if(acFeeParm != null && acFeeParm.getFeeFormId()!=null&&acFeeParm.getFeeFormId().length()>=0){
					//判定费用场景为还款
					if("LNAA".equals(acFeeParm.getFeeScenes())){
						AcFeeFormula acFeeFormula = (AcFeeFormula)JdbcDao.query(new AcFeeFormula(), "fee_form_id='"+acFeeParm.getFeeFormId()+"'", "ac_fee_formula", operaInfo.getConn());
						feeAmt = getFeeAmt(acFeeParm.getAcquRate(),acLnRepayPlnCur,acFeeFormula, acLnMst.getLoanAmt(),acLnMst.getTermMon(), operaInfo,acLnMst.getPremRate());
					
						feeAmt = NumberUtil.amtMult(feeAmt, rate);
						if(NumberUtil.isAmtGreatThanZero(feeAmt)){//计算出的服务费大于零时，增加费用明细
							if(acFeeParm.getFeeMax()!=null){
								//若计算出的费用大于费用配置的最大值，则取费用配置最大值
								if(feeAmt>acFeeParm.getFeeMax()){
									feeAmt = acFeeParm.getFeeMax();
								}
							}
							if(acFeeParm.getFeeMin()!=null){
								//若计算出的费用小于费用配置的最小值，则取费用配置最小值
								if(feeAmt<acFeeParm.getFeeMin()){
									feeAmt = acFeeParm.getFeeMin();
								}
							}
							AcChrgLog acChrgLog = new AcChrgLog();
							acChrgLog = new AcChrgLog();
							acChrgLog.setChrgId(ParmBiz.getAcChrgLogId(conn));
							acChrgLog.setTraceNo(operaInfo.getTraceNo());
							acChrgLog.setLoanNo(acLnMst.getLoanNo());
							acChrgLog.setPactNo(acLnMst.getPactNo());
							acChrgLog.setBrNo(acLnMst.getBrNo());
							acChrgLog.setPerdNo(String.valueOf(acLnRepayPlnCur.getPerdNo()));
							acChrgLog.setFeeParmId(acFeeParm.getFeeParmId());
							acChrgLog.setFeeKind(acFeeParm.getFeeKind());
							acChrgLog.setChrgAmt(feeAmt);
							acChrgLog.setRepayChrgAmt(0.00);
							acChrgLog.setChrgSts("01");//待处理
							acChrgLog.setTxDate(ParmBiz.getBizDt(conn));
							acChrgLog.setTxTime(ParmBiz.getOracleTxTime(conn));
							acChrgLog.setFeeType(acFeeParm.getRateType());
							acChrgLogList.add(acChrgLog);
					
						}
					}
				}
			}
		
		}
			return acChrgLogList;
		}
	
}
	

