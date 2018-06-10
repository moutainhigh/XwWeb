package  app.creditapp.fund.dao.impl;

import java.util.HashMap;
import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundDetailDao;
import app.creditapp.fund.entity.FundDetail;
/**
* Title: FundDetailDaoImpl.java
* Description:
**/
public class FundDetailDaoImpl extends BaseIbatisDao implements FundDetailDao {

	public void del(FundDetail fundDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundDetail.del", fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundDetail> findByPage(FundDetail fundDetail) throws DAOException {
		List fundDetailList = null;
		try {
			fundDetailList = getSqlMapClientTemplate().queryForList(
					"FundDetail.findByPage", fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundDetailList;
	}

	public FundDetail getById(FundDetail fundDetail) throws DAOException {
		try {
			fundDetail = (FundDetail) getSqlMapClientTemplate()
					.queryForObject("FundDetail.getById", fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundDetail;
	}

	public int getCount(FundDetail fundDetail) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundDetail.findPage.count", fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundDetail fundDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundDetail.insert",
					fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundDetail fundDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundDetail.update",
					fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//查看资金现金金额是否满足历史兑付流水
	public int findByhisfund(FundDetail fundDetail) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundDetail.findByhisfundNo.count", fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//同步数据
	public void reDeem(HashMap paramMap) throws DAOException {
		try {
			getSqlMapClientTemplate().queryForObject("FundDetail.redeem", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//新增资金次级信息引起其他变动
	public void insert_after(FundDetail fundDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().queryForObject("FundDetail.insert_after", fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//查询一段内的兑付金额
	public double getBalBytradeType(FundDetail fundDetail) throws DAOException {
		double bal = 0.00;
		try {
			bal = Double.parseDouble(getSqlMapClientTemplate().queryForObject("FundDetail.getBalBytradeType", fundDetail).toString());

		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return bal;
	}
	//获取明细表的最迟日期
	public String getMaxDate(FundDetail fundDetail) throws DAOException{
		String date = "";
		try {
			date = (String)getSqlMapClientTemplate().queryForObject(
					"FundDetail.getMaxDate" ,fundDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		if("".equals(date)||date==null){
			date = "0";
		}
		return date;
	}
}