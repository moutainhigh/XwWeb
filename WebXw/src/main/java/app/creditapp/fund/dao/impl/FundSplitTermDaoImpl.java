package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundSplitTermDao;
import app.creditapp.fund.entity.FundSplitTerm;
/**
* Title: FundSplitTermDaoImpl.java
* Description:
**/
public class FundSplitTermDaoImpl extends BaseIbatisDao implements FundSplitTermDao {

	public void del(FundSplitTerm fundSplitTerm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundSplitTerm.del", fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundSplitTerm> findByPage(FundSplitTerm fundSplitTerm) throws DAOException {
		List fundSplitTermList = null;
		try {
			fundSplitTermList = getSqlMapClientTemplate().queryForList(
					"FundSplitTerm.findByPage", fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitTermList;
	}

	public FundSplitTerm getById(FundSplitTerm fundSplitTerm) throws DAOException {
		try {
			fundSplitTerm = (FundSplitTerm) getSqlMapClientTemplate()
					.queryForObject("FundSplitTerm.getById", fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitTerm;
	}

	public int getCount(FundSplitTerm fundSplitTerm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundSplitTerm.findPage.count", fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundSplitTerm fundSplitTerm) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundSplitTerm.insert",
					fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundSplitTerm fundSplitTerm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundSplitTerm.update",
					fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//获取是否存在最大未到期存续段数据
	public FundSplitTerm getByTxdate(FundSplitTerm fundSplitTerm) throws DAOException {
		try {
			fundSplitTerm = (FundSplitTerm) getSqlMapClientTemplate()
					.queryForObject("FundSplitTerm.getByTxdate", fundSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitTerm;
	}
}