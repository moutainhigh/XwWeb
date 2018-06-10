package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundSplitDetailDao;
import app.creditapp.fund.entity.FundSplitDetail;
/**
* Title: FundSplitDetailDaoImpl.java
* Description:
**/
public class FundSplitDetailDaoImpl extends BaseIbatisDao implements FundSplitDetailDao {

	public void del(FundSplitDetail fundSplitDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundSplitDetail.del", fundSplitDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundSplitDetail> findByPage(FundSplitDetail fundSplitDetail) throws DAOException {
		List fundSplitDetailList = null;
		try {
			fundSplitDetailList = getSqlMapClientTemplate().queryForList(
					"FundSplitDetail.findByPage", fundSplitDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitDetailList;
	}

	public FundSplitDetail getById(FundSplitDetail fundSplitDetail) throws DAOException {
		try {
			fundSplitDetail = (FundSplitDetail) getSqlMapClientTemplate()
					.queryForObject("FundSplitDetail.getById", fundSplitDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundSplitDetail;
	}

	public int getCount(FundSplitDetail fundSplitDetail) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundSplitDetail.findPage.count", fundSplitDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundSplitDetail fundSplitDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundSplitDetail.insert",
					fundSplitDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundSplitDetail fundSplitDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundSplitDetail.update",
					fundSplitDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}