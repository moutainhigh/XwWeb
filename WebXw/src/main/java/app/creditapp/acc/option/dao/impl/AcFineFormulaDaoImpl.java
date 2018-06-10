package  app.creditapp.acc.option.dao.impl;
import java.util.List;
import java.util.Map;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcFineFormulaDao;
import app.creditapp.acc.option.entity.AcFineFormula;
/**
* Title: AcFineFormulaDaoImpl.java
* Description:
**/
public class AcFineFormulaDaoImpl extends BaseIbatisDao implements AcFineFormulaDao {

	public void del(AcFineFormula acFineFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcFineFormula.del", acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcFineFormula> findByPage(AcFineFormula acFineFormula) throws DAOException {
		List acFineFormulaList = null;
		try {
			acFineFormulaList = getSqlMapClientTemplate().queryForList(
					"AcFineFormula.findByPage", acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFineFormulaList;
	}

	public AcFineFormula getById(AcFineFormula acFineFormula) throws DAOException {
		try {
			acFineFormula = (AcFineFormula) getSqlMapClientTemplate()
					.queryForObject("AcFineFormula.getById", acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFineFormula;
	}

	public int getCount(AcFineFormula acFineFormula) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcFineFormula.findPage.count", acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcFineFormula acFineFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcFineFormula.insert",
					acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcFineFormula acFineFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcFineFormula.update",
					acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateReplanSts(AcFineFormula acFineFormula) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcFineFormula.updateReplanSts",
					acFineFormula);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}