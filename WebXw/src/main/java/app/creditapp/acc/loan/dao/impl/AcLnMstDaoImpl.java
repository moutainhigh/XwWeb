package  app.creditapp.acc.loan.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnMstFail;
import app.creditapp.inf.entity.WsOut2105_1;

/**
* Title: AcLnMstDaoImpl.java
* Description:
**/
public class AcLnMstDaoImpl extends BaseIbatisDao implements AcLnMstDao {
	
	public int getCountforlist(AcLnMst acLnMst) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnMst.findPageforlist.count", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	//接口ws2105分页输出结果，带有分页功能
	public List<WsOut2105_1> findByPageforlist(Map map) throws DAOException {
		List<WsOut2105_1> wsOut2105_1 = null;
		try {
			wsOut2105_1 = getSqlMapClientTemplate().queryForList(
					"AcLnMst.findByPageForList", map);		
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return wsOut2105_1;
	}	

	public void del(AcLnMst acLnMst) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnMst.del", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnMst> findByPage(AcLnMst acLnMst) throws DAOException {
		List acLnMstList = null;
		try {
			acLnMstList = getSqlMapClientTemplate().queryForList(
					"AcLnMst.findByPage", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnMstList;
	}
	
	public List<AcLnMst> findByPagedk(AcLnMst acLnMst) throws DAOException {
		List acLnMstList = null;
		try {
			acLnMstList = getSqlMapClientTemplate().queryForList(
					"AcLnMst.findByPagedk", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnMstList;
	}


	public AcLnMst getById(AcLnMst acLnMst) throws DAOException {
		try {
			acLnMst = (AcLnMst) getSqlMapClientTemplate()
					.queryForObject("AcLnMst.getById", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnMst;
	}

	public AcLnMst getByPactNo(AcLnMst acLnMst) throws DAOException {
		try {
			acLnMst = (AcLnMst) getSqlMapClientTemplate()
					.queryForObject("AcLnMst.getByPactNo", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnMst;
	}

	
	public int getCount(AcLnMst acLnMst) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnMst.findPage.count", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountdk(AcLnMst acLnMst) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnMst.findPagedk.count", acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnMst acLnMst) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnMst.insert",
					acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnMst acLnMst) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnMst.update",
					acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public int getCountToLoanFail(AcLnMstFail acLnMstFail) throws DAOException{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnMstFail.findByPageToLoanFail.count", acLnMstFail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public List<AcLnMstFail > findByPageToLoanFail(AcLnMstFail acLnMstFail) throws DAOException{
		List acLnMstFailList = null;
		try {
			acLnMstFailList = getSqlMapClientTemplate().queryForList(
					"AcLnMstFail.findByPageToLoanFail", acLnMstFail);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnMstFailList;
	}
	@Override
	public void updateLoanSts(AcLnMst acLnMst) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnMst.updateLoanSts",
					acLnMst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}