package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcReplanFormulaDao;
import app.creditapp.acc.option.entity.AcReplanFormula;
/**
* Title: AcReplanFormulaDaoImpl.java
* Description:
**/
public class AcReplanFormulaDaoImpl extends BaseIbatisDao implements AcReplanFormulaDao {

	public void del(AcReplanFormula acReplanFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcReplanFormula.del", acReplanFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcReplanFormula> findByPage(AcReplanFormula acReplanFormula) throws DAOException {
		List acReplanFormulaList = null;
		try {
			acReplanFormulaList = getSqlMapClientTemplate().queryForList(
					"AcReplanFormula.findByPage", acReplanFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanFormulaList;
	}

	public AcReplanFormula getById(AcReplanFormula acReplanFormula) throws DAOException {
		try {
			acReplanFormula = (AcReplanFormula) getSqlMapClientTemplate()
					.queryForObject("AcReplanFormula.getById", acReplanFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanFormula;
	}

	public int getCount(AcReplanFormula acReplanFormula) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcReplanFormula.findPage.count", acReplanFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcReplanFormula acReplanFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcReplanFormula.insert",
					acReplanFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcReplanFormula acReplanFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcReplanFormula.update",
					acReplanFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}