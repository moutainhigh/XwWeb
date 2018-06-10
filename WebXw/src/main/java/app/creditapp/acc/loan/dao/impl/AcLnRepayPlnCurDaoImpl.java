package  app.creditapp.acc.loan.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcLnRepayPlnCurDao;
import app.creditapp.acc.loan.entity.AcLnRepayPlnCur;
/**
* Title: AcLnRepayPlnCurDaoImpl.java
* Description:
**/
public class AcLnRepayPlnCurDaoImpl extends BaseIbatisDao implements AcLnRepayPlnCurDao {

	public void del(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnRepayPlnCur.del", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnRepayPlnCur> findByPage(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		List acLnRepayPlnCurList = null;
		try {
			acLnRepayPlnCurList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPlnCur.findByPage", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnCurList;
	}

	public AcLnRepayPlnCur getById(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		try {
			acLnRepayPlnCur = (AcLnRepayPlnCur) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPlnCur.getById", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnCur;
	}
	
	//根据合同号和机构查询
	public AcLnRepayPlnCur getByPactNo(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		try {
			acLnRepayPlnCur = (AcLnRepayPlnCur) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPlnCur.getByPactNo", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnCur;
	}
	
	//根据合同号查 当期还款计划表中的应收本金-已还本金
//	public int pactAmt_repayPactAmt(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
//		int count = 0;
//		try {
//			count = (Integer) getSqlMapClientTemplate().queryForObject(
//					"AcLnRepayPlnCur.pactAmt_repayPactAmt", acLnRepayPlnCur);
//		} catch (Exception e) {
//			log.error(e);
//			throw new DAOException(e.getMessage());
//		}
//		return count;
//	}

	public int getCount(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnRepayPlnCur.findPage.count", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnRepayPlnCur.insert",
					acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnRepayPlnCur.update",
					acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	@Override
	public AcLnRepayPlnCur getByPactNoAndDt(AcLnRepayPlnCur acLnRepayPlnCur)
			throws DAOException {
		try {
			acLnRepayPlnCur = (AcLnRepayPlnCur) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPlnCur.getByPactNoAndDt", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnCur;
	}

	@Override
	public AcLnRepayPlnCur getCurByCnt(AcLnRepayPlnCur acLnRepayPlnCur) {
		try {
			acLnRepayPlnCur = (AcLnRepayPlnCur) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPlnCur.getCurByCnt", acLnRepayPlnCur);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnCur;
	}
}