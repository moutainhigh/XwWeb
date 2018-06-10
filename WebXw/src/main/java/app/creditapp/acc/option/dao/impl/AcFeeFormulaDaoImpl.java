package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcFeeFormulaDao;
import app.creditapp.acc.option.entity.AcFeeFormula;
/**
* Title: AcFeeFormulaDaoImpl.java
* Description:
**/
public class AcFeeFormulaDaoImpl extends BaseIbatisDao implements AcFeeFormulaDao {

	public void del(AcFeeFormula acFeeFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcFeeFormula.del", acFeeFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcFeeFormula> findByPage(AcFeeFormula acFeeFormula) throws DAOException {
		List acFeeFormulaList = null;
		try {
			acFeeFormulaList = getSqlMapClientTemplate().queryForList(
					"AcFeeFormula.findByPage", acFeeFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFeeFormulaList;
	}

	public AcFeeFormula getById(AcFeeFormula acFeeFormula) throws DAOException {
		try {
			acFeeFormula = (AcFeeFormula) getSqlMapClientTemplate()
					.queryForObject("AcFeeFormula.getById", acFeeFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFeeFormula;
	}

	public int getCount(AcFeeFormula acFeeFormula) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcFeeFormula.findPage.count", acFeeFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcFeeFormula acFeeFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcFeeFormula.insert",
					acFeeFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcFeeFormula acFeeFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcFeeFormula.update",
					acFeeFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}