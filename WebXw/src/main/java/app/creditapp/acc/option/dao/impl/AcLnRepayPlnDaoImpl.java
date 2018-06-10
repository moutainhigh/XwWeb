package  app.creditapp.acc.option.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcLnRepayPlnDao;
import app.creditapp.acc.option.entity.AcLnRepayPln;
/**
* Title: AcLnRepayPlnDaoImpl.java
**/
public class AcLnRepayPlnDaoImpl extends BaseIbatisDao implements AcLnRepayPlnDao {

	public void del(AcLnRepayPln acLnRepayPln) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnRepayPln.del", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnRepayPln> findByPage(AcLnRepayPln acLnRepayPln) throws DAOException {
		List acLnRepayPlnList = null;
		try {
			acLnRepayPlnList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPln.findByPage", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnList;
	}
	
	public List<AcLnRepayPln> getListByLoanNo(AcLnRepayPln acLnRepayPln) throws DAOException {
		List acLnRepayPlnList = null;
		try {
			acLnRepayPlnList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPln.getListByLoanNo", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnList;
	}
	public AcLnRepayPln getById(AcLnRepayPln acLnRepayPln) throws DAOException {
		try {
			acLnRepayPln = (AcLnRepayPln) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPln.getById", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPln;
	}
	
	//取还款计划表最大的perd_no
	public int getByIdMaxPerdNo(AcLnRepayPln acLnRepayPln) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPln.getByIdMaxPerdNo", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int getCount(AcLnRepayPln acLnRepayPln) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnRepayPln.findPage.count", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnRepayPln acLnRepayPln) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnRepayPln.insert",
					acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnRepayPln acLnRepayPln) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnRepayPln.update",
					acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//根据合同号返回对应的数据
	public AcLnRepayPln getByIdforpact(AcLnRepayPln acLnRepayPln) throws DAOException{
		
		try {
			acLnRepayPln = (AcLnRepayPln) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPln.getByIdforpactNo", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPln;
	}

	@Override
	public AcLnRepayPln getAllAmt(AcLnRepayPln acLnRepayPln)
			throws DAOException {
		try {
			acLnRepayPln = (AcLnRepayPln) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPln.getAllAmt", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPln;
	}

	@Override
	public AcLnRepayPln getByBrNoPactNoPerdNo(AcLnRepayPln acLnRepayPln)
			throws DAOException {
		try {
			acLnRepayPln = (AcLnRepayPln) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPln.getByBrNoPactNoPerdNo", acLnRepayPln);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPln;
	}
}