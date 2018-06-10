package  app.creditapp.fund.bo.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.DateUtil;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundRepayPlanBo;
import app.creditapp.fund.dao.FundRepayPlanDao;
import app.creditapp.fund.entity.FundRepayPlan;


import app.creditapp.fund.entity.FundBase;
import app.creditapp.fund.dao.FundSplitTermDao;
import app.creditapp.fund.entity.FundSplitTerm;

/**
* Title: FundRepayPlanBoImplImpl.java
* Description:
**/
public class FundRepayPlanBoImpl extends BaseService implements FundRepayPlanBo {

	private FundRepayPlanDao fundRepayPlanDao;
	private FundSplitTermDao fundSplitTermDao;
	
	public void del(FundRepayPlan fundRepayPlan) throws ServiceException {
		try {
			fundRepayPlanDao.del(fundRepayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundRepayPlan fundRepayPlan)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundRepayPlanDao
					.getCount(fundRepayPlan) });// 初始化分页类
			fundRepayPlan.setStartNumAndEndNum (ipg);
			ipg.setResult(fundRepayPlanDao.findByPage(fundRepayPlan));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundRepayPlan getById(FundRepayPlan fundRepayPlan) throws ServiceException {
		try {
			fundRepayPlan = fundRepayPlanDao.getById(fundRepayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundRepayPlan;
	}

	public void insert(FundRepayPlan fundRepayPlan) throws ServiceException {
		try {
			fundRepayPlanDao.insert(fundRepayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundRepayPlan fundRepayPlan) throws ServiceException {
		try {
			fundRepayPlanDao.update(fundRepayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public double getBalByDate(FundRepayPlan fundRepayPlan) throws ServiceException {
		double sumbal = 0.00;
		try {
			sumbal = fundRepayPlanDao.getBalByDate(fundRepayPlan);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sumbal;
	}	
	//自动生成资金的清算计划
	public void auto_insert(FundBase fundBase) throws ServiceException {
		try {
			//按结息方式不同增加的月份
			int hkm ;
			//指定结息日
			String repay_day = fundBase.getRepayDay();
			//清算日期日是否大于结束日期的标志：1-大于
			int flag = -1;
			int days;
			//期次
			int repayIssue=0;
			//主键兑付计划编号
			String repayPlanNo;
			//清算日期
			String dateStr;
			//资金起始日期
			String begDate = fundBase.getBegDate();
			String endDate = fundBase.getEndDate();
			//存续起始日期
			String begtermDate;
			String endtermDate;		

			//结息计划方式
			String payType = fundBase.getRepayType();
			
			FundSplitTerm fundSplitTerm = new FundSplitTerm();
			fundSplitTerm.setFundNo(fundBase.getFundNo());
			Ipage ipage = new Ipage();
			ipage.setPageSize(100);
			fundSplitTerm.setStartNumAndEndNum(ipage);
			List<FundSplitTerm> fundSplitTermList=  fundSplitTermDao.findByPage(fundSplitTerm);
			//判断是否存在兑付计划信息，如存在删掉
			FundRepayPlan fundRepayPlan = new FundRepayPlan();
			fundRepayPlan.setFundNo(fundBase.getFundNo());
			int count = fundRepayPlanDao.getCount(fundRepayPlan);
			if(count > 0){ 
				fundRepayPlanDao.delByFunoNo(fundRepayPlan);
			}
			//判断兑付方式对应增加的月份
			if(("10").equals(payType)){
				 hkm=1;
			 }else if (("20").equals(payType)) {
				 hkm=1;
			 }else if (("11").equals(payType)) {
				 hkm=3;
			 }else if (("21").equals(payType)) {
				 hkm=3;
			 }else if (("12").equals(payType)) {
				 hkm=6;
			 }else if (("22").equals(payType)) {
				 hkm=6;
			 }else if (("13").equals(payType)) {
				 hkm=12;	 
			 }else if (("23").equals(payType)) {
				 hkm=12;
			 }else if (("30").equals(payType)) {
				 hkm=99;
			 }else {
				 flag = 1;
				 hkm=99;
			 };
			while(flag == -1){
				//判断是否是一次付清
				if(hkm==99){
				//清算日期等于结束日期
				dateStr=endDate;	
				}else {
				//获取首次清算日期
				dateStr=DateUtil.getDateStr(begDate, hkm);
				//判断指定结息日是否为空，不为空，清算日期的dd为指定结息日的dd
				  if(repay_day.length()>0){
					  dateStr=dateStr.substring(0,6)+repay_day;
				  }
				}
				//清算金额
				Double repayAmt=0.0;
				//期次号
				repayIssue++;
				//生成主键repayPlanNo
				repayPlanNo = UUID.randomUUID().toString().replaceAll("-", "");
				//dateStr>date2 return flag=1 dateStr=date2 return flag=0 dateStr<date2 flag=-1
				flag=DateUtil.compareDate(DateUtil.changeToShow(dateStr), DateUtil.changeToShow(endDate));

				//循环存续记录，获取本清算时间段内所有清算金额
				for(int i=0;i<fundSplitTermList.size();i++){
					// 最大开始日期
					String maxbegDate;
					// 最小结束日期
					String minendDate;
					//存续期内清算金额
					Double termrepayAmt;
					fundSplitTerm=fundSplitTermList.get(i);
					begtermDate = fundSplitTerm.getBegDate(); 
					endtermDate = fundSplitTerm.getEndDate();

					//判断两个日期时间段是否存在交集，如存在，找出最大开始日期，最小结束日期
					if(((begtermDate.compareTo(begDate) <=0) && begDate.compareTo(endtermDate) < 0) || 
						((begtermDate.compareTo(begDate) > 0) && (begtermDate.compareTo(dateStr)) < 0)	){
						if(begtermDate.compareTo(begDate) > 0){
							maxbegDate = begtermDate ;
						}else {
							maxbegDate = begDate ;
						}
						if(endtermDate.compareTo(dateStr) > 0){
							minendDate = dateStr;
						}else{
							minendDate = endtermDate;
						}
						//计算一个存续记录在一个清算周期内的天数
						days=DateUtil.getBetweenDays(DateUtil.changeToShow(maxbegDate), DateUtil.changeToShow(minendDate));
						//计算清算金额repayAmt=金额*融资利率*天数/年天数（资金的）
						termrepayAmt= (DateUtil.DoDcm(fundSplitTerm.getTermAmt()).multiply(DateUtil.FoDcm(fundBase.getFinanceRate())).multiply(DateUtil.IoDcm(days))).divide(DateUtil.IoDcm(fundBase.getYearDays()),3,BigDecimal.ROUND_HALF_UP).doubleValue();

						System.out.println("termrepayAmt="+termrepayAmt);
					}else{
						termrepayAmt = 0.0;
					}
					repayAmt =repayAmt+termrepayAmt;
					System.out.println("repayAmt="+repayAmt);
				}
				//如果清算计划为最后一期，最后一期日期为结束日期
				if(flag==1){
					dateStr = endDate;
				}
				fundRepayPlan.setFundNo(fundBase.getFundNo());
				fundRepayPlan.setRepayPlanNo(repayPlanNo);
				fundRepayPlan.setRepayAmt(repayAmt);
				fundRepayPlan.setRepayDate(dateStr);
				fundRepayPlan.setRepayIssue(repayIssue);
				fundRepayPlanDao.insert(fundRepayPlan);
				begDate=dateStr;
                
			}
		
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundRepayPlanDao getFundRepayPlanDao() {
		return fundRepayPlanDao;
	}

	public void setFundRepayPlanDao(FundRepayPlanDao fundRepayPlanDao) {
		this.fundRepayPlanDao = fundRepayPlanDao;
	}
	//引入
	public FundSplitTermDao getFundSplitTermDao() {
		return fundSplitTermDao;
	}

	public void setFundSplitTermDao(FundSplitTermDao fundSplitTermDao) {
		this.fundSplitTermDao = fundSplitTermDao;
	}

}