package  app.creditapp.fund.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundSplitDao;
import app.creditapp.fund.entity.FundSplit;
/**
* Title: FundSplitDaoImpl.java
* Description:
**/
public class FundSplitDaoImpl extends BaseIbatisDao implements FundSplitDao {

	public void del(FundSplit fundSplit) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundSplit.del", fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundSplit> findByPage(FundSplit fundSplit) throws DAOException {
		List fundSplitList = null;
		try {
			fundSplitList = getSqlMapClientTemplate().queryForList(
					"FundSplit.findByPage", fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitList;
	}
	public List<FundSplit> echarts(FundSplit fundSplit) throws DAOException {
		List fundSplitList = null;
		try {
			fundSplitList =  getSqlMapClientTemplate()
					.queryForList("FundSplit.echarts", fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitList;
	}
	public FundSplit getById(FundSplit fundSplit) throws DAOException {
		try {
			fundSplit = (FundSplit) getSqlMapClientTemplate()
					.queryForObject("FundSplit.getById", fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplit;
	}

	public int getCount(FundSplit fundSplit) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundSplit.findPage.count", fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundSplit fundSplit) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundSplit.insert",
					fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundSplit fundSplit) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundSplit.update",
					fundSplit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}