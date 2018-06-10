package app.creditapp.fund.bo.impl;

import java.math.BigDecimal;
import java.util.UUID;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundPayPlanBo;
import app.creditapp.fund.dao.FundPayPlanDao;
import app.creditapp.fund.entity.FundPayPlan;
import app.creditapp.fund.entity.FundSplit;
import app.util.DateUtil;

/**
 * Title: FundPayPlanBoImplImpl.java Description:
 **/
public class FundPayPlanBoImpl extends BaseService implements FundPayPlanBo {

	private FundPayPlanDao fundPayPlanDao;

	public void del(FundPayPlan fundPayPlan) throws ServiceException {
		try {
			fundPayPlanDao.del(fundPayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundPayPlan fundPayPlan)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundPayPlanDao
					.getCount(fundPayPlan) });// 初始化分页类
			fundPayPlan.setStartNumAndEndNum(ipg);
			ipg.setResult(fundPayPlanDao.findByPage(fundPayPlan));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public int findBycount(FundPayPlan fundPayPlan) throws ServiceException {
		int count = 0;
		try {
			count = (Integer) fundPayPlanDao.getCountForAmt(fundPayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public Ipage findByPageForAmt(Ipage ipg, FundPayPlan fundPayPlan)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundPayPlanDao
					.getCountForAmt(fundPayPlan) });// 初始化分页类
			fundPayPlan.setStartNumAndEndNum(ipg);
			ipg.setResult(fundPayPlanDao.findByPageForAmt(fundPayPlan));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundPayPlan getById(FundPayPlan fundPayPlan) throws ServiceException {
		try {
			fundPayPlan = fundPayPlanDao.getById(fundPayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundPayPlan;
	}

	public void insert(FundPayPlan fundPayPlan) throws ServiceException {
		try {
			fundPayPlanDao.insert(fundPayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public double getBalByDate(FundPayPlan fundPayPlan) throws ServiceException {
		double bal = 0.00;
		try {
			bal = fundPayPlanDao.getBalByDate(fundPayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return bal;
	}

	// 分配方案新增时，生成兑付计划
	public void auto_insert(FundSplit fundSplit) throws ServiceException {
		try {
			// 按结息方式不同增加的月份
			int hkm;
			// 指定结息日
			String pay_day = fundSplit.getPayDay();
			// 兑付日期日是否大于结束日期的标志
			int flag = -1;
			int days;
			// 期次
			int payIssue = 0;
			// 主键兑付计划编号
			String payPlanNo;
			// 兑付金额
			Double PayAmt;
			// 兑付日期
			String dateStr = null;
			String begDate1;
			String payType = fundSplit.getPayType();
			String begDate = fundSplit.getBegDate();
			String endDate = fundSplit.getEndDate();
			String txDate = fundSplit.getTxDate();
			// 判断是否存在兑付计划信息，如存在删掉
			FundPayPlan fundPayPlan = new FundPayPlan();
			fundPayPlan.setPartNo(fundSplit.getPartNo());
			int count = fundPayPlanDao.getCount(fundPayPlan);
			if (count > 0) {
				fundPayPlanDao.delByPartNo(fundPayPlan);
			}
			// 判断兑付方式对应增加的月份
			if (("10").equals(payType)) {
				hkm = 1;
			} else if (("20").equals(payType)) {
				hkm = 1;
			} else if (("11").equals(payType)) {
				hkm = 3;
			} else if (("21").equals(payType)) {
				hkm = 3;
			} else if (("12").equals(payType)) {
				hkm = 6;
			} else if (("22").equals(payType)) {
				hkm = 6;
			} else if (("13").equals(payType)) {
				hkm = 12;
			} else if (("23").equals(payType)) {
				hkm = 12;
			} else if (("30").equals(payType)) {
				hkm = 99;
			} else {
				flag = 1;
				hkm = 99;
			}
			while (flag == -1) {
				// 获取兑付日期
				if (hkm == 99) {
					dateStr = endDate;
				} else {
					if (payIssue == 0) {
						begDate1 = begDate;
						if (pay_day.length() > 0) {
							while(begDate1.compareTo(txDate)<=0){
								begDate1 = DateUtil.getDateStr(begDate1, hkm);
							}
							dateStr = begDate1.substring(0, 6) + pay_day;
							//如果起始日期按兑付周期指定结息日小于登记日期，第一期兑付计划起始日期为登记日期
							if(dateStr.compareTo(txDate)<=0){
								dateStr = DateUtil.getDateStr(dateStr, hkm);
							}
							begDate = txDate;
						}else{
							// 开始日期小于更改日期
							if (begDate.compareTo(txDate) <= 0) {
								dateStr = DateUtil.getDateStr(begDate, hkm);
								while(dateStr.compareTo(txDate)<0){
									dateStr = DateUtil.getDateStr(dateStr, hkm);
								}
								begDate = txDate;
							}else{
								dateStr = DateUtil.getDateStr(begDate, hkm);
							}
						}
					} else {
						dateStr = DateUtil.getDateStr(begDate, hkm);
					}
				}
				// 期次
				payIssue++;
				// 生成主键payPlanNo
				payPlanNo = UUID.randomUUID().toString().replaceAll("-", "");
				// dateStr>date2 return flag=1
				flag = DateUtil.compareDate(DateUtil.changeToShow(dateStr),
						DateUtil.changeToShow(endDate));
				// 期次内天数
				if (flag == 1) {
					days = DateUtil.getBetweenDays(DateUtil
							.changeToShow(begDate), DateUtil
							.changeToShow(endDate));
					dateStr = endDate;
				} else {
					days = DateUtil.getBetweenDays(DateUtil
							.changeToShow(begDate), DateUtil
							.changeToShow(dateStr));
				}
				// 计算兑付金额recPayAmt=金额*兑付利率*天数/年天数
				PayAmt = ((DateUtil.DoDcm(fundSplit.getPartAmt()).multiply(DateUtil.FoDcm(fundSplit.getInvRate())).multiply(DateUtil.IoDcm(days))).divide(DateUtil.IoDcm(fundSplit.getYearDays()), 3,BigDecimal.ROUND_HALF_UP)).doubleValue();
				int d = DateUtil.getBetweenDays(DateUtil.changeToShow(dateStr), DateUtil.changeToShow(endDate));
				//最够一期归还本金+兑付金额
				if(d == 0){
					PayAmt = PayAmt + fundSplit.getPartAmt();
				}
				fundPayPlan.setPayPlanNo(payPlanNo);
				fundPayPlan.setFundNo(fundSplit.getFundNo());
				fundPayPlan.setPartNo(fundSplit.getPartNo());
				fundPayPlan.setPayIssue(payIssue);
				fundPayPlan.setPayDate(dateStr);
				fundPayPlan.setPayAmt(PayAmt);
				fundPayPlanDao.insert(fundPayPlan);
				begDate = dateStr;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void update(FundPayPlan fundPayPlan) throws ServiceException {
		try {
			fundPayPlanDao.update(fundPayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundPayPlanDao getFundPayPlanDao() {
		return fundPayPlanDao;
	}

	public void setFundPayPlanDao(FundPayPlanDao fundPayPlanDao) {
		this.fundPayPlanDao = fundPayPlanDao;
	}

}