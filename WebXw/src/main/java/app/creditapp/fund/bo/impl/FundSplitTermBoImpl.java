package  app.creditapp.fund.bo.impl;

import java.util.UUID;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.DateUtil;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundSplitTermBo;
import app.creditapp.fund.dao.FundSplitTermDao;
import app.creditapp.fund.entity.FundSplitTerm;

import app.creditapp.fund.entity.FundSplitDetail;
import app.creditapp.fund.entity.FundSplit;
import app.creditapp.fund.dao.FundSplitDao;


/**
* Title: FundSplitTermBoImplImpl.java
* Description:
**/
public class FundSplitTermBoImpl extends BaseService implements FundSplitTermBo {

	private FundSplitTermDao fundSplitTermDao;
	private FundSplitDao fundSplitDao;
	private FundSplitTerm fundSplitTerm;
	
	public void del(FundSplitTerm fundSplitTerm) throws ServiceException {
		try {
			fundSplitTermDao.del(fundSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundSplitTerm fundSplitTerm)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundSplitTermDao
					.getCount(fundSplitTerm) });// 初始化分页类
			fundSplitTerm.setStartNumAndEndNum (ipg);
			ipg.setResult(fundSplitTermDao.findByPage(fundSplitTerm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundSplitTerm getById(FundSplitTerm fundSplitTerm) throws ServiceException {
		try {
			fundSplitTerm = fundSplitTermDao.getById(fundSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundSplitTerm;
	}

	public void insert(FundSplitTerm fundSplitTerm) throws ServiceException {
		try {
			//生成主键payPlanNo
			String termNo = UUID.randomUUID().toString().replaceAll("-", "");
			//存续天数
			int days=DateUtil.getBetweenDays(DateUtil.changeToShow(fundSplitTerm.getBegDate()), DateUtil.changeToShow(fundSplitTerm.getEndDate()));

			fundSplitTerm.setTermNo(termNo);
			fundSplitTerm.setDays(days);
			fundSplitTermDao.insert(fundSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundSplitTerm fundSplitTerm) throws ServiceException {
		try {
			fundSplitTermDao.update(fundSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	//自动触发
	public void auto_insert(FundSplitDetail fundSplitDetail) throws ServiceException {
		try {
			FundSplit fundSplit = new FundSplit();
			fundSplit.setPartNo(fundSplitDetail.getPartNo());
			fundSplit = fundSplitDao.getById(fundSplit);		
			//赋值资金编号，方案编号，结束日期（赋值交易日期）
			fundSplitTerm = new FundSplitTerm();
			fundSplitTerm.setFundNo(fundSplit.getFundNo());
			fundSplitTerm.setPartNo(fundSplit.getPartNo());
			fundSplitTerm.setEndDate(fundSplitDetail.getTxDate());
			//分配方案在存续期的条数
			fundSplitTerm = fundSplitTermDao.getByTxdate(fundSplitTerm);
			
			//存续天数
			int days;
			//主键存续编号
			String termNo;
		
			//判断存续表中没有值，直接插入数据；
			//如果有值且交易类型是追加,更新原存续记录（到期日期=交易日期,存续天数）并增加一条当前记录（规模=之前规模+交易规模,起始日期=交易日期，结束日期=原结束日期）；
			//如果交易是赎回或兑付，更新原存续记录（规模=原规模-交易规模），新增一条兑付对应存续记录（规模=交易规模，起始日期=原起始日期，到期日期=交易日期）；
			if(fundSplitTerm == null){
				//插入新记录
				fundSplitTerm = new FundSplitTerm();
			        //生成主键termNo
			    termNo = UUID.randomUUID().toString().replaceAll("-", "");
			        //计算存续期天数
			    days=DateUtil.getBetweenDays(DateUtil.changeToShow(fundSplit.getBegDate()), DateUtil.changeToShow(fundSplit.getEndDate()));
			    fundSplitTerm.setTermNo(termNo);
				fundSplitTerm.setFundNo(fundSplit.getFundNo());
				fundSplitTerm.setPartNo(fundSplit.getPartNo());
			    fundSplitTerm.setTermAmt(fundSplitDetail.getTxAmt());
		    	fundSplitTerm.setInvRate(fundSplitDetail.getInvRate());
		        fundSplitTerm.setBegDate(fundSplit.getBegDate());
		        fundSplitTerm.setEndDate(fundSplit.getEndDate());
			    fundSplitTerm.setDays(days);
				fundSplitTermDao.insert(fundSplitTerm);
			}else if ("01".equals(fundSplitDetail.getTradeType())){
				//更新原记录（到期日期，存续天数）
				    //计算存续期天数
				days=DateUtil.getBetweenDays(DateUtil.changeToShow(fundSplitTerm.getBegDate()), DateUtil.changeToShow(fundSplitDetail.getTxDate()));
				fundSplitTerm.setEndDate(fundSplitDetail.getTxDate());
				fundSplitTerm.setDays(days);
				fundSplitTermDao.update(fundSplitTerm);
				//插入新记录
				    //生成主键termNo
				termNo = UUID.randomUUID().toString().replaceAll("-", "");
				   //计算存续期天数
				days=DateUtil.getBetweenDays(DateUtil.changeToShow(fundSplitDetail.getTxDate()), DateUtil.changeToShow(fundSplit.getEndDate()));
				   //规模=原规模-交易规模
				Double termAmt = fundSplitTerm.getTermAmt()+fundSplitDetail.getTxAmt();
				fundSplitTerm.setTermNo(termNo);
				fundSplitTerm.setTermAmt(termAmt);
				fundSplitTerm.setInvRate(fundSplitDetail.getInvRate());
				fundSplitTerm.setBegDate(fundSplitDetail.getTxDate());
				fundSplitTerm.setEndDate(fundSplit.getEndDate());
				fundSplitTerm.setDays(days);
				fundSplitTermDao.insert(fundSplitTerm);
			}else{
				//更新原记录
				   //规模=原规模-交易规模
				Double termAmt = fundSplitTerm.getTermAmt()-fundSplitDetail.getTxAmt();
				fundSplitTerm.setTermAmt(termAmt);
				fundSplitTermDao.update(fundSplitTerm);
				//插入新记录
				   //生成主键termNo
				termNo = UUID.randomUUID().toString().replaceAll("-", "");
				   //计算存续期天数
				days=DateUtil.getBetweenDays(DateUtil.changeToShow(fundSplitTerm.getBegDate()), DateUtil.changeToShow(fundSplitDetail.getTxDate()));		
				fundSplitTerm.setTermNo(termNo);
				fundSplitTerm.setTermAmt(fundSplitDetail.getTxAmt());
				fundSplitTerm.setInvRate(fundSplitDetail.getInvRate());
				fundSplitTerm.setBegDate(fundSplit.getBegDate());
				fundSplitTerm.setEndDate(fundSplitDetail.getTxDate());
				fundSplitTerm.setDays(days);
				fundSplitTermDao.insert(fundSplitTerm);
			}
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public FundSplitTermDao getFundSplitTermDao() {
		return fundSplitTermDao;
	}

	public void setFundSplitTermDao(FundSplitTermDao fundSplitTermDao) {
		this.fundSplitTermDao = fundSplitTermDao;
	}
	
	//引入
	public FundSplitDao getFundSplitDao() {
		return fundSplitDao;
	}

	public void setFundSplitDao(FundSplitDao fundSplitDao) {
		this.fundSplitDao = fundSplitDao;
	}
	public FundSplitTerm getFundSplitTerm() {
		return fundSplitTerm;
	}
	public void setFundSplitTerm(FundSplitTerm  fundSplitTerm) {
		this.fundSplitTerm = fundSplitTerm;
	}

}