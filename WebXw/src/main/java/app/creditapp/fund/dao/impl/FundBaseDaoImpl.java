package  app.creditapp.fund.dao.impl;

import java.util.HashMap;
import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundBaseDao;
import app.creditapp.fund.entity.FundBase;
/**
* Title: FundBaseDaoImpl.java
* Description:
**/
public class FundBaseDaoImpl extends BaseIbatisDao implements FundBaseDao {

	public void del(FundBase fundBase) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundBase.del", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundBase> findByPage(FundBase fundBase) throws DAOException {
		List fundBaseList = null;
		try {
			fundBaseList = getSqlMapClientTemplate().queryForList(
					"FundBase.findByPage", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundBaseList;
	}

	public FundBase getById(FundBase fundBase) throws DAOException {
		try {
			fundBase = (FundBase) getSqlMapClientTemplate()
					.queryForObject("FundBase.getById", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundBase;
	}
	public List<FundBase> echarts(FundBase fundBase) throws DAOException {
		List fundBaseList = null;
		try {
			fundBase = (FundBase) getSqlMapClientTemplate()
					.queryForObject("FundBase.echarts", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundBaseList;
	}
	public int getCount(FundBase fundBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundBase.findPage.count", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
//查看是否存在不符合最定的数据
	public int getCountunmatched(FundBase fundBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundBase.unmatched", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//查看是否存在不符合最定的数据
	public int getCountunmatcheds(FundBase fundBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundBase.unmatcheds", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//资金清算提醒
	public int getFundRepayRemind(FundBase fundBase){
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundBase.getFundRepayRemind", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
//	public void insertadd() throws DAOException {
//		try {
//			getSqlMapClientTemplate().insert("FundBase.insertadd"
//					);
//		} catch (Exception e) {
//			log.error(e);
//			throw new DAOException(e.getMessage());
//		}
//
//	}
	public String getmaxtxDate(FundBase fundBase) throws DAOException {
		String maxtxdate ;
		try {
			maxtxdate = (String)getSqlMapClientTemplate().queryForObject(
					"FundDetail.findMax.txdate", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return maxtxdate;
	}
	public String getmaxtxDateSingle(FundBase fundBase) throws DAOException {
		String maxtxdate ;
		try {
			maxtxdate = (String)getSqlMapClientTemplate().queryForObject(
					"FundDetail.findMax.txdateSingle", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return maxtxdate;
	}
	public void insert(FundBase fundBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundBase.insert",
					fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundBase fundBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundBase.update",
					fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//按照字段更新数据
	public void updateBytransts(FundBase fundBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnDue.updateBytransts",fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//获得按照资金类型获取总金额
	public Double getByfdTypeCount(FundBase fundBase) throws DAOException {
		Double count ;
		try {
			count = (Double)getSqlMapClientTemplate().queryForObject(
					"FundBase.findPage.getByfdTypeCount", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//获得按照资金类型获取现金总金额
	public Double getByfdTypeCashbal(FundBase fundBase) throws DAOException {
		Double sum ;
		try {
			sum = (Double)getSqlMapClientTemplate().queryForObject(
					"FundBase.findPage.getByfdTypeCashbal", fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sum;
	}
	//同步数据
	public void sync(HashMap paramMap) throws DAOException {
		try {
			getSqlMapClientTemplate().queryForObject("FundBase.sync", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//同步数据
	public void syncSingle(HashMap paramMap) throws DAOException {
		try {
			getSqlMapClientTemplate().queryForObject("FundBase.syncSingle", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public List<FundBase> findByFdflag(FundBase fundBase) throws DAOException {
		List fundBaseList = null;
		try {
			fundBaseList = getSqlMapClientTemplate().queryForList("FundBase.findByFdflag", fundBase);;
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundBaseList;
	}

	@Override
	public void updateCash(FundBase fundBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundBase.updateCash",
					fundBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}
}