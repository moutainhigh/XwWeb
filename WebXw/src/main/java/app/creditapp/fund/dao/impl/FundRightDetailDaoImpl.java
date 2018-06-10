package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundRightDetailDao;
import app.creditapp.fund.entity.FundRightDetail;
/**
* Title: FundRightDetailDaoImpl.java
* Description:
**/
public class FundRightDetailDaoImpl extends BaseIbatisDao implements FundRightDetailDao {

	public void del(FundRightDetail fundRightDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundRightDetail.del", fundRightDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundRightDetail> findByPage(FundRightDetail fundRightDetail) throws DAOException {
		List fundRightDetailList = null;
		try {
			fundRightDetailList = getSqlMapClientTemplate().queryForList(
					"FundRightDetail.findByPage", fundRightDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundRightDetailList;
	}

	public FundRightDetail getById(FundRightDetail fundRightDetail) throws DAOException {
		try {
			fundRightDetail = (FundRightDetail) getSqlMapClientTemplate()
					.queryForObject("FundRightDetail.getById", fundRightDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundRightDetail;
	}

	public int getCount(FundRightDetail fundRightDetail) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundRightDetail.findPage.count", fundRightDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundRightDetail fundRightDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundRightDetail.insert",
					fundRightDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundRightDetail fundRightDetail) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundRightDetail.update",
					fundRightDetail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}