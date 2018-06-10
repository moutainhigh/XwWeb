package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcDamFormulaDao;
import app.creditapp.acc.option.entity.AcDamFormula;
/**
* Title: AcDamFormulaDaoImpl.java
* Description:
**/
public class AcDamFormulaDaoImpl extends BaseIbatisDao implements AcDamFormulaDao {

	public void del(AcDamFormula acDamFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcDamFormula.del", acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcDamFormula> findByPage(AcDamFormula acDamFormula) throws DAOException {
		List acDamFormulaList = null;
		try {
			acDamFormulaList = getSqlMapClientTemplate().queryForList(
					"AcDamFormula.findByPage", acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDamFormulaList;
	}

	public AcDamFormula getById(AcDamFormula acDamFormula) throws DAOException {
		try {
			acDamFormula = (AcDamFormula) getSqlMapClientTemplate()
					.queryForObject("AcDamFormula.getById", acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDamFormula;
	}

	public int getCount(AcDamFormula acDamFormula) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcDamFormula.findPage.count", acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcDamFormula acDamFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcDamFormula.insert",
					acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcDamFormula acDamFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcDamFormula.update",
					acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateSts(AcDamFormula acDamFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcDamFormula.updateSts",
					acDamFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}