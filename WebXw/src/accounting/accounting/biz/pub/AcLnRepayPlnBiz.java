package accounting.biz.pub;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import accounting.biz.loan.RepayLoMortgageBiz;
import accounting.domain.biz.AcComHoliday;
import accounting.domain.biz.AcReplanFormula;
import accounting.domain.biz.AcReplanParm;
import accounting.domain.biz.AcReplanSec;
import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.AcLnRepayPlnHst;
import accounting.domain.loan.PrdtBase;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.TimeUtil;
import app.base.ServiceException;
import app.creditapp.acc.option.entity.AcLnSetlmtLog;
import app.util.DateUtil;

public class AcLnRepayPlnBiz {
	
	/**
	 * 
	 * @throws Exception 
	 * @作者 su
	 * @日期 2018-4-25
	 * @描述	放款反馈环节A类合作机构生成还款计划
	 */
public static List<AcLnRepayPln> getAcLnRepayPln(AcLnMst acLnMst,PrdtBase prdtBase,OperaInfo operaInfo)throws AccountingException{
	List<AcLnRepayPln> acLnRepayPlnList = new ArrayList<AcLnRepayPln>();
	List<AcReplanSec> acReplanSecList = new ArrayList<AcReplanSec>();
	
	DecimalFormat df =new java.text.DecimalFormat("###.00"); 
	
	DecimalFormat df6 =new java.text.DecimalFormat("###.000000"); 

	DecimalFormat nf6 =new java.text.DecimalFormat("0.00000000");

	String opnDt = acLnMst.getOpnDt();//放款日
	String mtrDt = acLnMst.getMtrDt();//到期日
	String lstIntDt = acLnMst.getRepayDay();//还款日
	if(lstIntDt==null || lstIntDt.length()==0){
		lstIntDt = opnDt.substring(6,8);
	}
	if(lstIntDt.length()==1){
		lstIntDt = 0+lstIntDt;
	}
	String upPayDt = acLnMst.getLstDt();// 上期还款日
	if(upPayDt == "" || "".equals(upPayDt)){
		upPayDt = opnDt;
	}
	//公式基本参数
	String parmA = df.format(acLnMst.getLoanAmt());//贷款本金
	String parmC = "";//还款周期
	String parmE ;//还款频率
	String parmF = "";//上期剩余本金
	String parmG = "";//本期期次
	String parmH = "";//本段本金比例
	int parmJ = 0;//本段总期数
	int sumPerdNo = 0;//总期数
	String parmK = "";//贷款总期数

	try {
		String replanId = prdtBase.getRepayNo();// 方案编号，后面有产品表后从产品表取
		AcReplanParm acReplanParm = (AcReplanParm)JdbcDao.query(new AcReplanParm(), "replan_id='"+replanId+"'", "ac_replan_parm", operaInfo.getConn());

		AcReplanSec acReplanSec = new AcReplanSec();
		acReplanSec.setReplanId(replanId);
		acReplanSecList = (ArrayList)JdbcDao.queryList(new AcReplanSec(), "replan_id='"+replanId+"'", "ac_replan_sec", operaInfo.getConn());

		if("3".equals(acReplanParm.getExtendNext())&&Integer.valueOf(acLnMst.getOpnDt().substring(6, 8))>Integer.valueOf(acLnMst.getRepayDay())){
			acLnMst.setLoanTerm(acLnMst.getLoanTerm()+1);
		}
		if ("4".equals(acReplanParm.getExtendNext())&&Integer.valueOf(acLnMst.getOpnDt().substring(6, 8))!=Integer.valueOf(acLnMst.getRepayDay())){
			acLnMst.setLoanTerm(acLnMst.getLoanTerm()+1);
		}
		int i = 0;// 期次--第几期
		if (acReplanSecList.size() > 0) {// 计算每一段还款计划
			for (AcReplanSec ars : acReplanSecList) {
				String extendNext = acReplanParm.getExtendNext();// 还款方案中的首期不足延至 期初/期末
				String extendDate = "";
				if("3".equals(extendNext)||"4".equals(extendNext)){
					extendDate = opnDt;
				}else{
					extendDate = getExtendDate(opnDt,lstIntDt);
				}
				
		
				String parmB = String.valueOf(acLnMst.getLoanTerm());// 期限月
				parmC = acReplanParm.getTermType();
//				DecimalFormat nf = new DecimalFormat();  
//				nf.setMaximumFractionDigits(10); // 设置最大小数位
				
				String parmD = String.valueOf(nf6.format(acLnMst.getLnRate()));// 贷款月利率  
				String parmL = String.valueOf(nf6.format(acLnMst.getLnRateYear()));// 贷款年利率
				
				parmE = String.valueOf(acReplanParm.getFrequency());
				parmH = String.valueOf(ars.getCapitalRate());

				String begRepayNo = ars.getBegRepayNo();// 本段起始期次
				String endRepayNo = ars.getEndRepayNo();// 本段到期其次

				AcReplanFormula acReplanFormula = (AcReplanFormula)JdbcDao.query(new AcReplanFormula(), "Formula_id='"+ars.getFormulaId()+"'", "ac_replan_formula", operaInfo.getConn());

				if (acReplanFormula != null) {
					String payCap = acReplanFormula.getPayCap();// 应收本金公式
					String payInte = acReplanFormula.getPayInte();// 应收利息
					if ("1".equals(parmC)) {// 还款周期为日
						sumPerdNo = (int) Math.ceil((DateUtil.getDaysBetween(
								acLnMst.getOpnDt(), mtrDt)) / 1.00 / acReplanParm.getFrequency());// 按日计息，贷款期限用起始日期-到期日期作为总期限，总期限/还款频率即为总期数
						parmC = "1";
						parmD = parmD + "/30";// 还款周期为按日，则月利率改为日利率
						parmL = parmL + "/365";// 还款周期为按日，则年利率改为日利率
						parmB = String.valueOf(DateUtil.getDaysBetween(
								acLnMst.getOpnDt(), mtrDt));
					} else if ("2".equals(parmC)) {// 还款周期为月
						sumPerdNo = acLnMst.getLoanTerm() / acReplanParm.getFrequency();
						//判定倒数第二期还款日是否大于贷款到日期,若大于到期日，则总期数-1
						if(TimeUtil.checkDate1BeforeDate2(acLnMst.getMtrDt(),DateUtil.getDateStr(extendDate,sumPerdNo-1))){
							sumPerdNo = sumPerdNo-1;
						}
						parmC = "1";
					} else if ("3".equals(parmC)) {// 还款周期为季
						sumPerdNo = acLnMst.getLoanTerm() / 3 / acReplanParm.getFrequency();
						//判定倒数第二期还款日是否大于贷款到日期,若大于到期日，则总期数-1
						if(TimeUtil.checkDate1BeforeDate2(acLnMst.getMtrDt(),DateUtil.getDateStr(extendDate,3*sumPerdNo-1))){
							sumPerdNo = sumPerdNo-1;
						}
						parmC = "3";
					} else if ("4".equals(parmC)) {// 还款周期为半年
						sumPerdNo = acLnMst.getLoanTerm() / 6 / acReplanParm.getFrequency();
						//判定倒数第二期还款日是否大于贷款到日期,若大于到期日，则总期数-1
						if(TimeUtil.checkDate1BeforeDate2(acLnMst.getMtrDt(),DateUtil.getDateStr(extendDate,6*sumPerdNo-1))){
							sumPerdNo = sumPerdNo-1;
						}
						parmC = "6";
					} else if ("5".equals(parmC)) {// 还款周期为年
						sumPerdNo = acLnMst.getLoanTerm() / 12 / acReplanParm.getFrequency();
						//判定倒数第二期还款日是否大于贷款到日期,若大于到期日，则总期数-1
						if(TimeUtil.checkDate1BeforeDate2(acLnMst.getMtrDt(),DateUtil.getDateStr(extendDate,12*sumPerdNo-1))){
							sumPerdNo = sumPerdNo-1;
						}
						parmC = "12";
					}else if ("6".equals(parmC)) {// 还款周期为一次性
						sumPerdNo = 1;
						parmC = "1";
					}
					
					if("1".equals(acReplanParm.getIfLoanInt())){//是否放款日扣息，若选是，还款计划总期数+1
						sumPerdNo += 1;
					}
					parmK = String.valueOf(sumPerdNo);
					begRepayNo = String.valueOf((int)Math.ceil(Calc.doCalc(begRepayNo.replaceAll("n", String
							.valueOf(sumPerdNo))).getValue()));
					endRepayNo = String.valueOf((int)Math.ceil(Calc.doCalc(endRepayNo.replaceAll("n", String
							.valueOf(sumPerdNo))).getValue()));
					i = Integer.parseInt(begRepayNo);
					parmJ = Integer.parseInt(endRepayNo) - Integer.parseInt(begRepayNo) + 1;
					if(0==(acLnRepayPlnList.size())){//判断若本期起始为第一期，则从第0期开始
						i=0;
					}
					//本段不是第一期，且本段起始期次小于等于前段最大期次，则跳过，排除重复期次 （如鸿申贷款期限为1期，还款计划分三段，导致重复期次）
					if(i!=0 && i <= acLnRepayPlnList.size()-1) continue;
					for (; i <= Integer.parseInt(endRepayNo); i++) {// 计算每期还款计划
						AcLnRepayPln acLnRepayPln = new AcLnRepayPln();
						if(0==i){
							parmG = String.valueOf(i - Integer.parseInt(begRepayNo) + 1);// 计算本期期次
							acLnRepayPln.setBal(acLnMst.getLoanAmt());
							acLnRepayPln.setInstmAmt(0.00);
							acLnRepayPln.setLoanNo(acLnMst.getLoanNo());
							acLnRepayPln.setNormInt(0.00);
							acLnRepayPln.setPayDt(DateUtil.getDateStr(upPayDt, i));
							acLnRepayPln.setPerdNo(i);
							acLnRepayPln.setPrcpAmt(0.00);
							acLnRepayPln.setLnRate(acLnMst.getLnRate());
							acLnRepayPlnList.add(acLnRepayPln);
						} else {
							parmG = String.valueOf(i - Integer.parseInt(begRepayNo) + 1);// 计算本期期次
							double normInt = 0.00;// 本期应收利息
							double prcpAmt = 0.00;// 本期应收本金
							String payDt = "";// 当期还款日
							AcLnRepayPln lstAcLnRepayPln = new AcLnRepayPln();
							for (AcLnRepayPln alrp : acLnRepayPlnList) {
								if ((i - 1) == alrp.getPerdNo()) {
									lstAcLnRepayPln = alrp;
									parmF = df.format(alrp.getBal());
									upPayDt = alrp.getPayDt();
								}
							}
							double normIntExtend = acLnMst.getLoanAmt() * acLnMst.getLnRate()/100
									/ 30 * DateUtil.getDaysBetween(opnDt,extendDate);

							if("1".equals(acReplanParm.getIfLoanInt())){//放款日扣息则多第一期放款日
								if(1!=i){
									if ("1".equals(acReplanParm.getTermType())) {// 还款周期为按日，则本期还款日为上期还款日+还款频率
										payDt = DateUtil.addByDay(
												opnDt, (i-1) * Integer.parseInt(parmE));
									}else{
										payDt = DateUtil.getDateStr(
												extendDate, (i-1) * Integer.parseInt(parmE)*Integer.parseInt(parmC));
									}
								}else{
									payDt=extendDate;
								}
							}else{
								if ("1".equals(acReplanParm.getTermType())) {// 还款周期为按日，则本期还款日为上期还款日+还款频率
									payDt = DateUtil.addByDay(upPayDt, Integer.parseInt(parmE));
								}else{
									payDt = DateUtil.getDateStr(upPayDt, Integer.parseInt(parmE)*Integer.parseInt(parmC)).substring(0, 6)+lstIntDt;
									if (i == 1 && "4".equals(extendNext)&&Integer.valueOf(acLnMst.getOpnDt().substring(6, 8))<Integer.valueOf(acLnMst.getRepayDay())) {
										payDt = DateUtil.addBMonth(payDt);
									}
									while(!DateUtil.isDateStr(payDt)){
										payDt = String.valueOf(Integer.parseInt(payDt)- 1);
									}
									if(!DateUtil.isBefore(opnDt, payDt)){
										payDt = DateUtil.getDateStr(upPayDt, Integer.parseInt(parmE)*Integer.parseInt(parmC)).substring(0, 6)+lstIntDt;
									}
								}
							}
							//判断账单日是否有效，若无效，则减一继续判断
							while(!DateUtil.isDateStr(payDt)){
								payDt = String.valueOf(Integer.parseInt(payDt)- 1);
							}
							//判断节假日是否顺延
							if("1".equals(acReplanParm.getHolidIfExt())){
								payDt = closeHoliday(payDt,operaInfo);//判定当天是否为节假日，若是则获取节假日后的第一个工作日
							}
							//获取上期与本期间相差天数
							if (i == sumPerdNo) {// 最后一期利息应该重新计算
								payDt = mtrDt;
							}
							String parmI = String.valueOf(DateUtil.getDaysBetween(upPayDt, payDt));
							//用参数值替换公式指标
							String payCap1 = replaceParms(payCap, parmA, parmB, parmC, parmD, parmE,
									parmF, parmG, parmH, parmI, String.valueOf(parmJ),parmK,parmL);
							String payInte1 = replaceParms(payInte, parmA, parmB, parmC, parmD, parmE,
									parmF, parmG, parmH, parmI, String.valueOf(parmJ),parmK,parmL);
							normInt = Calc.doCalc(payInte1).getValue();// 通过公式得出的本期应收利息
							normInt = NumberUtil.getDouble(normInt, 2);
							prcpAmt = Calc.doCalc(payCap1).getValue();// 通过公式得出的本期应收本金
							prcpAmt = NumberUtil.getDouble(prcpAmt, 2);
							if(1==i&&"1".equals(acReplanParm.getIfLoanInt())){//若为第一期，且方案选择放款日扣款
								payDt = opnDt;
							}
//							if (i == 1 && "1".equals(extendNext)) {// 第一期利息需根据方案设定的期初/期末将差出的利息统计在期初或是期末
//								normInt += normIntExtend;
//							}
							
							for (AcLnRepayPln alrp : acLnRepayPlnList) {
								if ((i - 1) == alrp.getPerdNo()) {
									Double bal = Double.parseDouble(df6.format(alrp.getBal() - prcpAmt));// 剩余本金
									acLnRepayPln.setBal(ParmBiz.parseAmt(bal,acReplanParm.getIntUnit(),acReplanParm.getIntType()));
								}
							}
							
							acLnRepayPln.setPayDt(payDt);
							acLnRepayPln.setLoanNo(acLnMst.getLoanNo());
							acLnRepayPln.setPactNo(acLnMst.getPactNo());
							acLnRepayPln.setBrNo(acLnMst.getBrNo());
							acLnRepayPln.setNormInt(ParmBiz.parseAmt(normInt,acReplanParm.getIntUnit(),acReplanParm.getIntType()));
							acLnRepayPln.setPerdNo(i);
							acLnRepayPln.setPrcpAmt(ParmBiz.parseAmt(prcpAmt,acReplanParm.getIntUnit(),acReplanParm.getIntType()));
							acLnRepayPln.setInstmAmt(ParmBiz.parseAmt(normInt + prcpAmt,acReplanParm.getIntUnit(),acReplanParm.getIntType()));
							acLnRepayPln.setFineInt(0.00);
							acLnRepayPln.setRepayPrcpAmt(0.00);
							acLnRepayPln.setRepayNormInt(0.00);
							acLnRepayPln.setRepayFineInt(0.00);
							acLnRepayPln.setOverRate(0.00);
							acLnRepayPln.setWvPrcpAmt(0.00);
							acLnRepayPln.setWvNormInt(0.00);
							acLnRepayPln.setWvFineInt(0.00);
							acLnRepayPln.setLnRate(acLnMst.getLnRate());
							acLnRepayPln.setIntSts(PUBConstant.INT_STS_10);//利息状态：正常
							acLnRepayPln.setPrcpSts(PUBConstant.INT_STS_10);//本金状态：正常
							acLnRepayPln.setSetlSts(PUBConstant.SETL_STS_N);//结清状态：未结清
							acLnRepayPlnList.add(acLnRepayPln);
						}
					}
				}else{
					throw new ServiceException("该还款方案中存在未配置还款计划公式的分段信息！");
				}
			}
		}else{
			throw new ServiceException("该还款方案配置无分段信息！");
		}
	} catch (Exception e) {
		throw new ServiceException(e.getMessage());
	}
	
	//对剩余本金进行四舍五入两位小数
	if(acLnRepayPlnList.size()>0){
		acLnRepayPlnList.remove(0);
		for(AcLnRepayPln arp : acLnRepayPlnList){
			if(Double.parseDouble(df.format(arp.getBal()))==0){
				arp.setBal(0.00);
			}else{
				arp.setBal(Double.parseDouble(df.format(arp.getBal())));

			}
		}
	}
	return acLnRepayPlnList;
	}


//根据起始日期与结息日，获取首期不足的结息日，如20150405放款 结息日为10号，则得到日期为20150410， 若结息日为01号，则得到日期为20150501
	public static String getExtendDate(String date ,String lstIntDt )throws ServiceException{
		String lstDate = date.substring(6,8);
		String nextDate = DateUtil.getDateStr(date,1);
		if(Integer.parseInt(lstDate)>Integer.parseInt(lstIntDt)){
			date = nextDate.substring(0,6)+lstIntDt;
		}else{
			date = date.substring(0,6)+lstIntDt;
		}
		while(!DateUtil.isDateStr(date)){
			date = DateUtil.addByDay(date, -1);
		}
		return date ;
	}
	
	
	/**
	 * 
	 * @throws AccountingException 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-7-20
	 * @描述	提前还款确认，需要进行还款计划变更
	 */
	public static void aftAdvpayChangeRepln(AcLnSetlmtLog acLnSetlmtLog,OperaInfo operaInfo) throws AccountingException{
		//贷款主文件
		AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+acLnSetlmtLog.getLoanNo()+"'", "ac_ln_mst", operaInfo.getConn());
		//当期还款计划
		AcLnRepayPlnCur acLnRepayPlnCur = (AcLnRepayPlnCur)JdbcDao.query(new AcLnRepayPlnCur(), "loan_no='"+acLnSetlmtLog.getLoanNo()+"'", "ac_ln_repay_pln_cur", operaInfo.getConn());
		String endDt = acLnRepayPlnCur.getEndDt();
		AcLnRepayPln acLnRepayPlnur = (AcLnRepayPln)JdbcDao.query(new AcLnRepayPln(), "loan_no='"+acLnSetlmtLog.getLoanNo()+"' and perd_no='"+acLnRepayPlnCur.getPerdNo()+"'", "ac_ln_repay_pln", operaInfo.getConn());
		//上期还款计划--用于获取上期还款计划剩余本金
		AcLnRepayPln lstAcLnRepayPln = (AcLnRepayPln)JdbcDao.query(new AcLnRepayPln(), "loan_no='"+acLnSetlmtLog.getLoanNo()+"' and perd_no='"+String.valueOf(acLnRepayPlnCur.getPerdNo()-1)+"'", "ac_ln_repay_pln", operaInfo.getConn());
		//获取产品信息
//		PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(acLnMst.getPrdtNo());
		PrdtBase prdtBase = (PrdtBase) JdbcDao.query(new PrdtBase(), "prdt_no='"+acLnMst.getPrdtNo()+"'", "PRDT_BASE", operaInfo.getConn());
		
		List<AcLnRepayPln> newAcPlnList = new ArrayList<AcLnRepayPln>();
		
		AcLnRepayPln wvAcLnRepayPln = RepayLoMortgageBiz.getWvAcLnRepayPln(acLnSetlmtLog.getLoanNo(), operaInfo.getConn(), acLnRepayPlnCur.getPerdNo());
		
		AcLnLo sumAcLnLo = RepayLoMortgageBiz.getSumAmt(acLnMst.getLoanNo(), operaInfo.getConn());//计算借据表中同一合同号下的欠款本金和利息
		
		// 总的欠本金额
		double sumLoPrcpAmt = NumberUtil.amtSubs(NumberUtil.amtSubs(
				sumAcLnLo.getPrcpAmt(), sumAcLnLo.getRepayPrcpAmt()),
				sumAcLnLo.getWvPrcpAmt());

		//上期还款计划剩余本金
		double lstBal = acLnMst.getLoanAmt();
		if(lstAcLnRepayPln !=null){
			lstBal = lstAcLnRepayPln.getBal();
		}
		
		//修改当期还款计划
		double normInt = NumberUtil.isAmtGreatAndEqual(acLnSetlmtLog.getCurInt(), acLnRepayPlnur.getNormInt())?acLnSetlmtLog.getCurInt():acLnRepayPlnur.getNormInt();
		acLnRepayPlnur.setNormInt(NumberUtil.amtSubs(normInt,acLnRepayPlnur.getRepayNormInt()));//变更当期利息
		acLnRepayPlnur.setPayDt(acLnSetlmtLog.getRecvDt());//变更还款日
		acLnRepayPlnur.setPrcpAmt(lstBal);//提前还本金额
				
		acLnRepayPlnur.setInstmAmt(NumberUtil.amtAdd(acLnRepayPlnur.getNormInt(), acLnRepayPlnur.getPrcpAmt()));
		acLnRepayPlnur.setBal(NumberUtil.amtAdd(NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtSubs(lstBal-acLnSetlmtLog.getRemPrcpAmt(), acLnRepayPlnur.getRepayPrcpAmt()),acLnRepayPlnur.getWvPrcpAmt()),wvAcLnRepayPln.getWvPrcpAmt()), sumLoPrcpAmt));//剩余本金lstBal-acLnSetlmtLog.getRemPrcpAmt()
				
		acLnRepayPlnCur.setNormInt(acLnRepayPlnur.getNormInt());
		acLnRepayPlnCur.setPrcpAmt(acLnRepayPlnur.getPrcpAmt());
		acLnRepayPlnCur.setTtlAmt(acLnRepayPlnur.getInstmAmt());
		acLnRepayPlnCur.setPayDt(acLnSetlmtLog.getRecvDt());
		acLnRepayPlnCur.setEndDt(DateUtil.addByDay(acLnSetlmtLog.getRecvDt(), acLnMst.getGraceDay()));
		
		//若剩余本金不为0，则说明还需要生成新的后续还款计划
		if(NumberUtil.isAmtGreatThanZero(acLnRepayPlnur.getBal())){
			acLnMst.setLoanAmt(NumberUtil.amtAdd(acLnRepayPlnur.getBal(), wvAcLnRepayPln.getWvPrcpAmt()));
			if("02".equals(acLnSetlmtLog.getRepayType())){//缩期数--需更改到期日
				acLnMst.setMtrDt(acLnSetlmtLog.getEndDate());
			}
			if(DateUtil.compareDate(DateUtil.changeToShow(endDt), DateUtil.changeToShow(acLnSetlmtLog.getRecvDt()))==0){
				acLnSetlmtLog.setRecvDt(DateUtil.addByDay(acLnSetlmtLog.getRecvDt(), 1));
			}
			
			//重新计算后半期贷款期限，用于生成新还款计划
			long[] terms = DateUtil.getMonthsAndDays(acLnSetlmtLog.getRecvDt(), acLnMst.getMtrDt());
			//重新计算后半期贷款期限，用于生成新还款计划acLnSetlmtLog.getRecvDt()
//			long[] terms = DateUtil.getMonthsAndDays(acLnSetlmtLog.getRecvDt().substring(0,6)+acLnMst.getOpnDt().substring(6,8), acLnMst.getMtrDt());
	
			int month = (int) terms[0];
			if(terms[1]>0){
				month += 1;
			}
			acLnMst.setLoanTerm(month);
//			acLnMst.setOpnDt(acLnSetlmtLog.getRecvDt());
			List<AcLnRepayPln> acLnRepayPlnList = getAcLnRepayPln(acLnMst, prdtBase, operaInfo);
			
			acLnRepayPlnur.setPrcpAmt(acLnSetlmtLog.getRemPrcpAmt());//提前还本金额
			acLnRepayPlnur.setInstmAmt(NumberUtil.amtAdd(acLnRepayPlnur.getNormInt(), acLnRepayPlnur.getPrcpAmt()));
			
			acLnRepayPlnCur.setPrcpAmt(acLnRepayPlnur.getPrcpAmt());
			acLnRepayPlnCur.setTtlAmt(acLnRepayPlnur.getInstmAmt());
			
			newAcPlnList.add(acLnRepayPlnur);
			//新还款计划应从当期开始发生变化
			for(AcLnRepayPln alrp : acLnRepayPlnList){
				//新生成的还款计划期次从1开始，实际应接着原还款计划最后一期连续
				alrp.setPerdNo(alrp.getPerdNo()+acLnRepayPlnur.getPerdNo());
				if(alrp.getPerdNo()==(acLnRepayPlnur.getPerdNo()+1)){
					alrp.setWvPrcpAmt(wvAcLnRepayPln.getWvPrcpAmt());
				}
				newAcPlnList.add(alrp);
			}
			
			
		}else{
			//全部提前还款减免记录到当期
			acLnRepayPlnur.setWvPrcpAmt(NumberUtil.amtAdd(wvAcLnRepayPln.getWvPrcpAmt(), acLnRepayPlnur.getWvPrcpAmt()));
//			acLnRepayPlnur.setWvNormInt(NumberUtil.amtAdd(wvAcLnRepayPln.getWvNormInt(), acLnRepayPlnur.getWvNormInt()));
			
			//新还款计划应从当期开始发生变化
			newAcPlnList.add(acLnRepayPlnur);
			
		}
		acLnRepayPlnCur.setWvPrcpAmt(acLnRepayPlnur.getWvPrcpAmt());
		acLnRepayPlnCur.setWvNormInt(acLnRepayPlnur.getWvNormInt());
		JdbcDao.update(acLnRepayPlnCur,"loan_no='"+acLnMst.getLoanNo()+"'", "ac_ln_repay_pln_cur", operaInfo.getConn());
		
		//查询原还款计划 将要变更的还款计划
		List<AcLnRepayPln> oldAcLnRepayPln = (ArrayList)JdbcDao.queryList(new AcLnRepayPln(), "loan_no='"+acLnSetlmtLog.getLoanNo()+"' and perd_no>='"+String.valueOf(acLnRepayPlnCur.getPerdNo())+"'", "ac_ln_repay_pln", operaInfo.getConn());
		
		for(AcLnRepayPln alrp : oldAcLnRepayPln){
			//新增还款计划历史
			AcLnRepayPlnHst acLnRepayPlnHst =  getRpHsByRp(alrp,operaInfo.getTraceNo());
			JdbcDao.insert(acLnRepayPlnHst, "ac_ln_repay_pln_hst", operaInfo.getConn());
			
			//删除原需要变更的还款计划
			JdbcDao.delete("loan_no='"+alrp.getLoanNo()+"' and perd_no='"+alrp.getPerdNo()+"'", "ac_ln_repay_pln", operaInfo.getConn());
		}
		//新增新的还款计划
		JdbcDao.insertList(newAcPlnList, "ac_ln_repay_pln", operaInfo.getConn());
		

	}
	
	
	/*
	 * 排除节假日后的第一个工作日
	 */
	public static String  closeHoliday(String day,OperaInfo operaInfo) throws AccountingException{
		while(true){
			AcComHoliday acComHoliday = (AcComHoliday)JdbcDao.query(new AcComHoliday(), "days='"+day+"'", "ac_com_holiday", operaInfo.getConn());
			if(acComHoliday==null){
				break;
			}
			day = DateUtil.addByDay(day, 1);
		}
		return day;
	}
	
	/**
	 * 公式用基本参数替换
	 * @param formula公式
	 * @param parmA
	 * @param parmB
	 * @param parmC
	 * @param parmD
	 * @param parmE
	 * @param parmF
	 * @param parmG
	 * @param parmH
	 * @return
	 * @throws ServiceException
	 */
	public static String replaceParms(String formula , String parmA,String parmB,String parmC,
			String parmD,String parmE,String parmF,String parmG,String parmH,String parmI,String parmJ,String parmK,String parmL) throws ServiceException{
		formula = formula.replace("A", parmA);
		formula = formula.replaceAll("B", parmB);
		formula = formula.replaceAll("C", parmC);
		formula = formula.replaceAll("D", parmD);
		formula = formula.replaceAll("E", parmE);
		formula = formula.replaceAll("F", parmF);
		formula = formula.replaceAll("G", parmG);
		formula = formula.replaceAll("H", parmH);
		formula = formula.replaceAll("I", parmI);
		formula = formula.replaceAll("J", parmJ);
		formula = formula.replaceAll("K", parmK);
		formula = formula.replaceAll("L", parmL);

		return formula;
	}
	
	
	/**
	 * 封装当期还款计划表
	 * @param acLnPayPln		还款计划
	 * @param acLnMst			贷款主文件
	 * @param curDate			营业日期
	 * @return acLnRepayPlnCur	当期还款计划
	 */
	public static AcLnRepayPlnCur getAcLnRepayPlnCur(AcLnRepayPln acLnRepayPln,AcLnMst acLnMst, String curDate){
		AcLnRepayPlnCur acLnRepayPlnCur = new AcLnRepayPlnCur() ;
		acLnRepayPlnCur.setPayDt(acLnRepayPln.getPayDt()) ;			
		acLnRepayPlnCur.setEndDt(TimeUtil.ADD_DAY(acLnRepayPln.getPayDt(), acLnMst.getGraceDay())) ;
		acLnRepayPlnCur.setLoanNo(acLnRepayPln.getLoanNo()) ;	
		acLnRepayPlnCur.setPactNo(acLnMst.getPactNo());
		acLnRepayPlnCur.setBrNo(acLnMst.getBrNo());
		acLnRepayPlnCur.setNormInt(acLnRepayPln.getNormInt()) ;		
		acLnRepayPlnCur.setPerdNo(acLnRepayPln.getPerdNo()) ;		
		acLnRepayPlnCur.setPrcpAmt(acLnRepayPln.getPrcpAmt()) ;		
		acLnRepayPlnCur.setTtlAmt(acLnRepayPln.getInstmAmt()) ;			
		acLnRepayPlnCur.setWrkDt(curDate) ;						

		return acLnRepayPlnCur ;
	}
	
	/*
	 * 根据还款计划获取还款计划历史信息
	 */
	public static AcLnRepayPlnHst getRpHsByRp(AcLnRepayPln acLnRepayPln,String traceNo){
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
		acLnRepayPlnHst.setRepayPrcpAmt(acLnRepayPln.getRepayPrcpAmt());
		acLnRepayPlnHst.setRepayNormInt(acLnRepayPln.getRepayNormInt());
		acLnRepayPlnHst.setRepayFineInt(acLnRepayPln.getRepayFineInt());
		acLnRepayPlnHst.setOverInd(acLnRepayPln.getOverInd());
		acLnRepayPlnHst.setLstPayDt(acLnRepayPln.getLstPayDt());
		acLnRepayPlnHst.setWvPrcpAmt(acLnRepayPln.getWvPrcpAmt());
		acLnRepayPlnHst.setWvNormInt(acLnRepayPln.getWvNormInt());
		acLnRepayPlnHst.setWvFineInt(acLnRepayPln.getWvFineInt());
		acLnRepayPlnHst.setPrcpSts(acLnRepayPln.getPrcpSts());
		acLnRepayPlnHst.setIntSts(acLnRepayPln.getIntSts());
		acLnRepayPlnHst.setSetlSts(acLnRepayPln.getSetlSts());
		acLnRepayPlnHst.setFineIntDt(acLnRepayPln.getFineIntDt());
		
		return acLnRepayPlnHst ;
	}
	
	
	

}
