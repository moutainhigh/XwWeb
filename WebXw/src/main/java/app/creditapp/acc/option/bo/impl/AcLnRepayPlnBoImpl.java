package  app.creditapp.acc.option.bo.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.AcComHoliday;
import accounting.plat.util.TimeUtil;
import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.option.bo.AcLnRepayPlnBo;
import app.creditapp.acc.option.dao.AcComHolidayDao;
import app.creditapp.acc.option.dao.AcLnRepayPlnDao;
import app.creditapp.acc.option.dao.AcLnRepayPlnSuspDao;
import app.creditapp.acc.option.dao.AcReplanFormulaDao;
import app.creditapp.acc.option.dao.AcReplanParmDao;
import app.creditapp.acc.option.dao.AcReplanSecDao;
import app.creditapp.acc.option.entity.AcLnRepayPln;
import app.creditapp.acc.option.entity.AcReplanFormula;
import app.creditapp.acc.option.entity.AcReplanParm;
import app.creditapp.acc.option.entity.AcReplanSec;
import app.creditapp.pact.dao.LnPactDao;
import app.creditapp.sys.dao.PrdtBaseDao;
import app.creditapp.sys.entity.PrdtBase;
import app.util.DateUtil;
import app.util.toolkit.Ipage;
/**
* Title: AcLnRepayPlnBoImplImpl.java
* Description:
**/
public class AcLnRepayPlnBoImpl extends BaseService implements AcLnRepayPlnBo {

	private AcLnRepayPlnDao acLnRepayPlnDao;
	private AcReplanSecDao acReplanSecDao;
	private AcReplanParmDao acReplanParmDao;
	private AcReplanFormulaDao acReplanFormulaDao;
	private AcComHolidayDao acComHolidayDao;
	private PrdtBaseDao prdtBaseDao;
	private LnPactDao lnPactDao;
	private AcLnMstDao acLnMstDao;
	private AcLnRepayPlnSuspDao acLnRepayPlnSuspDao;

	public LnPactDao getLnPactDao() {
		return lnPactDao;
	}

	public void setLnPactDao(LnPactDao lnPactDao) {
		this.lnPactDao = lnPactDao;
	}

	public void del(AcLnRepayPln acLnRepayPln) throws ServiceException {
		try {
			acLnRepayPlnDao.del(acLnRepayPln);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnRepayPln acLnRepayPln)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnRepayPlnDao
					.getCount(acLnRepayPln) });// 初始化分页类
			acLnRepayPln.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnRepayPlnDao.findByPage(acLnRepayPln));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	/**
	 * 还款计划试算
	 */
	public List calPlnsByParms(AcLnMst acLnMst)
			throws ServiceException {
		List<AcLnRepayPln> acLnRepayPlnList = new ArrayList<AcLnRepayPln>();
		List<AcReplanSec> acReplanSecList = new ArrayList<AcReplanSec>();
		
		DecimalFormat df =new java.text.DecimalFormat("###.00"); 
		
		DecimalFormat df6 =new java.text.DecimalFormat("###.000000"); 

		String opnDt = acLnMst.getOpnDt();//放款日
		String mtrDt = acLnMst.getMtrDt();//到期日
		String lstIntDt = acLnMst.getRepayDay();//还款日
		if(lstIntDt.length()==1){
			lstIntDt = 0+lstIntDt;
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
			String prdtNo = acLnMst.getPrdtNo();
			PrdtBase prdtBase = new PrdtBase();
			prdtBase.setPrdtNo(prdtNo);
			prdtBase = prdtBaseDao.getByPrdtNo(prdtBase);
			String replanId = prdtBase.getRepayNo();// 方案编号
			
			AcReplanParm acReplanParm = new AcReplanParm();
			acReplanParm.setReplanId(replanId);
			acReplanParm = acReplanParmDao.getById(acReplanParm);
			AcReplanSec acReplanSec = new AcReplanSec();
			acReplanSec.setReplanId(replanId);
			acReplanSecList = (List) acReplanSecDao.getListByReplanId(acReplanSec);
			int i = 0;// 期次--第几期
			if (acReplanSecList.size() > 0) {// 计算每一段还款计划
				for (AcReplanSec ars : acReplanSecList) {

					String extendNext = acReplanParm.getExtendNext();// 还款方案中的首期不足延至 期初/期末
					String extendDate = "";
					if("3".equals(extendNext)){
						extendDate = opnDt;
					}else{
						extendDate = getExtendDate(opnDt,lstIntDt);
					}
					
					
					String parmB = String.valueOf(acLnMst.getLoanTerm());// 期限月
					parmC = acReplanParm.getTermType();
					String parmD = String.valueOf(acLnMst.getLnRate());// 贷款月利率
//					String parmL = String.valueOf(acLnMst.getLnRateYear());// 贷款年利率
					String parmL = String.valueOf(0.1056);// 贷款年利率
					parmE = String.valueOf(acReplanParm.getFrequency());
					parmH = String.valueOf(ars.getCapitalRate());

					String begRepayNo = ars.getBegRepayNo();// 本段起始期次
					String endRepayNo = ars.getEndRepayNo();// 本段到期其次

					AcReplanFormula acReplanFormula = new AcReplanFormula();
					acReplanFormula.setFormulaId(ars.getFormulaId());
					acReplanFormula = acReplanFormulaDao
							.getById(acReplanFormula);
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
							if(TimeUtil.checkDate1BeforeDate2(acLnMst.getMtrDt(),DateUtil.getDateStr(extendDate,sumPerdNo))){
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
						for (; i <= Integer.parseInt(endRepayNo); i++) {// 计算每期还款计划
							AcLnRepayPln acLnRepayPln = new AcLnRepayPln();
							if(0==i){
								parmG = String.valueOf(i - Integer.parseInt(begRepayNo) + 1);// 计算本期期次
								acLnRepayPln.setBal(acLnMst.getLoanAmt());
								acLnRepayPln.setInstmAmt(0.00);
								acLnRepayPln.setLoanNo(acLnMst.getLoanNo());
								acLnRepayPln.setNormInt(0.00);
								acLnRepayPln.setPayDt(DateUtil.getDateStr(opnDt, i));
								acLnRepayPln.setPerdNo(i);
								acLnRepayPln.setPrcpAmt(0.00);
								acLnRepayPln.setLnRate(acLnMst.getLnRate());
								acLnRepayPlnList.add(acLnRepayPln);
							} else {
								parmG = String.valueOf(i - Integer.parseInt(begRepayNo) + 1);// 计算本期期次
								double normInt = 0.00;// 本期应收利息
								double prcpAmt = 0.00;// 本期应收本金
								String upPayDt = "";// 上期还款日
								String payDt = "";// 当期还款日
								AcLnRepayPln lstAcLnRepayPln = new AcLnRepayPln();
								for (AcLnRepayPln alrp : acLnRepayPlnList) {
									if ((i - 1) == alrp.getPerdNo()) {
										lstAcLnRepayPln = alrp;
										parmF = df.format(alrp.getBal());
										upPayDt = alrp.getPayDt();
									}
								}
								double normIntExtend = acLnMst.getLoanAmt() * acLnMst.getLnRate()
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
									}
								}else{
									if ("1".equals(acReplanParm.getTermType())) {// 还款周期为按日，则本期还款日为上期还款日+还款频率
										payDt = DateUtil.addByDay(opnDt, i * Integer.parseInt(parmE));
									}else{
										//使用下条语句，生成的还款计划首期在当月
//										payDt = DateUtil.getDateStr(extendDate, (i -1)*Integer.parseInt(parmE)*Integer.parseInt(parmC)).substring(0, 6)+lstIntDt;  
										//使用下条语句，生成的还款计划首期在次月
										payDt = DateUtil.getDateStr(upPayDt, Integer.parseInt(parmE)*Integer.parseInt(parmC)).substring(0, 6)+lstIntDt;
										while(!DateUtil.isDateStr(payDt)){
											payDt = DateUtil.addByDay(payDt, -1);
										}
										if(!DateUtil.isBefore(opnDt, payDt)){
											payDt = DateUtil.getDateStr(extendDate, (i) * Integer.parseInt(parmE)*Integer.parseInt(parmC)).substring(0, 6)+lstIntDt;
										}
									}
								}
								//判断账单日是否有效，若无效，则减一继续判断
								while(!DateUtil.isDateStr(payDt)){
									payDt = DateUtil.addByDay(payDt, -1);
								}
								//判断节假日是否顺延
								if("1".equals(acReplanParm.getHolidIfExt())){
									payDt = closeHoliday(payDt);//判定当天是否为节假日，若是则获取节假日后的第一个工作日
								}
								//获取上期与本期间相差天数
								String parmI = String.valueOf(DateUtil.getDaysBetween(upPayDt, payDt));
								//用参数值替换公式指标
								String payCap1 = this.replaceParms(payCap, parmA, parmB, parmC, parmD, parmE,
										parmF, parmG, parmH, parmI, String.valueOf(parmJ),parmK,parmL);
								String payInte1 = this.replaceParms(payInte, parmA, parmB, parmC, parmD, parmE,
										parmF, parmG, parmH, parmI, String.valueOf(parmJ),parmK,parmL);
								normInt = Calc.doCalc(payInte1).getValue();// 通过公式得出的本期应收利息
								prcpAmt = Calc.doCalc(payCap1).getValue();// 通过公式得出的本期应收本金
								if(1==i&&"1".equals(acReplanParm.getIfLoanInt())){//若为第一期，且方案选择放款日扣款
									payDt = opnDt;
								}
								if (i == 1 && "1".equals(extendNext)) {// 第一期利息需根据方案设定的期初/期末将差出的利息统计在期初或是期末
									normInt += normIntExtend;
								}
								if (i == sumPerdNo) {// 最后一期利息应该重新计算
									if("1".equals(extendNext) && !mtrDt.equals(payDt)){//若最后一期计算出的还款日与到期日不一致，则说明最后一期不是满月，需按日计算利息
										normInt = (Double.parseDouble(parmF)
												* acLnMst.getLnRate() / 30 * DateUtil.getDaysBetween(upPayDt, mtrDt));
									}else if ("2".equals(extendNext)) {// 首期不足移至期末
										normInt += normIntExtend;
									}
									payDt = mtrDt;
								}
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
								acLnRepayPln.setLnRate(acLnMst.getLnRate());
								acLnRepayPln.setFineInt(0.0);
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
				if(Math.abs(arp.getBal())<=1){
					arp.setBal(0.00);
				}else{
					arp.setBal(Double.parseDouble(df.format(arp.getBal())));
				}
			}
		}
		return acLnRepayPlnList;
	}
	/*
	 * 排除节假日后的第一个工作日
	 */
	public String  closeHoliday(String day){
		while(true){
			AcComHoliday acComHoliday = new AcComHoliday();
			acComHoliday = acComHolidayDao.getByDay(day);
			if(acComHoliday==null){
				break;
			}
			day = DateUtil.addByDay(day, 1);
		}
		
		return day;
	}
	//根据合同号进行查询
	public AcLnRepayPln getByIdforpact(AcLnRepayPln acLnRepayPln) throws ServiceException{
		
			try {
				acLnRepayPln = acLnRepayPlnDao.getByIdforpact(acLnRepayPln);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
			return acLnRepayPln;
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
	public static void main(String[] args) {
		String s = getExtendDate("20150510","20");
		System.out.println(s);
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
	public String replaceParms(String formula , String parmA,String parmB,String parmC,
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
	
	public AcLnRepayPln getById(AcLnRepayPln acLnRepayPln) throws ServiceException {
		try {
			acLnRepayPln = acLnRepayPlnDao.getById(acLnRepayPln);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnRepayPln;
	}
	@Override
	public AcLnRepayPln getAllAmt(AcLnRepayPln acLnRepayPln)
			throws ServiceException {
		try {
			acLnRepayPln = acLnRepayPlnDao.getAllAmt(acLnRepayPln);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnRepayPln;
	}
	public void insert(AcLnRepayPln acLnRepayPln) throws ServiceException {
		try {
			acLnRepayPlnDao.insert(acLnRepayPln);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnRepayPln acLnRepayPln) throws ServiceException {
		try {
			acLnRepayPlnDao.update(acLnRepayPln);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLnRepayPlnDao getAcLnRepayPlnDao() {
		return acLnRepayPlnDao;
	}

	public void setAcLnRepayPlnDao(AcLnRepayPlnDao acLnRepayPlnDao) {
		this.acLnRepayPlnDao = acLnRepayPlnDao;
	}

	public AcReplanSecDao getAcReplanSecDao() {
		return acReplanSecDao;
	}

	public void setAcReplanSecDao(AcReplanSecDao acReplanSecDao) {
		this.acReplanSecDao = acReplanSecDao;
	}

	public AcReplanParmDao getAcReplanParmDao() {
		return acReplanParmDao;
	}

	public void setAcReplanParmDao(AcReplanParmDao acReplanParmDao) {
		this.acReplanParmDao = acReplanParmDao;
	}

	public AcReplanFormulaDao getAcReplanFormulaDao() {
		return acReplanFormulaDao;
	}

	public void setAcReplanFormulaDao(AcReplanFormulaDao acReplanFormulaDao) {
		this.acReplanFormulaDao = acReplanFormulaDao;
	}

	public AcComHolidayDao getAcComHolidayDao() {
		return acComHolidayDao;
	}

	public void setAcComHolidayDao(AcComHolidayDao acComHolidayDao) {
		this.acComHolidayDao = acComHolidayDao;
	}

	public PrdtBaseDao getPrdtBaseDao() {
		return prdtBaseDao;
	}

	public void setPrdtBaseDao(PrdtBaseDao prdtBaseDao) {
		this.prdtBaseDao = prdtBaseDao;
	}

	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}

	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}

	public AcLnRepayPlnSuspDao getAcLnRepayPlnSuspDao() {
		return acLnRepayPlnSuspDao;
	}

	public void setAcLnRepayPlnSuspDao(AcLnRepayPlnSuspDao acLnRepayPlnSuspDao) {
		this.acLnRepayPlnSuspDao = acLnRepayPlnSuspDao;
	}

	@Override
	public AcLnRepayPln getByBrNoPactNoPerdNo(AcLnRepayPln acLnRepayPln)
			throws ServiceException {
		return acLnRepayPlnDao.getByBrNoPactNoPerdNo(acLnRepayPln);
		
	}


}