package  app.creditapp.acc.chg.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.chg.dao.AcRepydayChgaDao;
import app.creditapp.acc.chg.entity.AcRepydayChga;
/**
* Title: AcRepydayChgaDaoImpl.java
* Description:
**/
public class AcRepydayChgaDaoImpl extends BaseIbatisDao implements AcRepydayChgaDao {

	public void del(AcRepydayChga acRepydayChga) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcRepydayChga.del", acRepydayChga);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcRepydayChga> findByPage(AcRepydayChga acRepydayChga) throws DAOException {
		List acRepydayChgaList = null;
		try {
			acRepydayChgaList = getSqlMapClientTemplate().queryForList(
					"AcRepydayChga.findByPage", acRepydayChga);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acRepydayChgaList;
	}

	public AcRepydayChga getById(AcRepydayChga acRepydayChga) throws DAOException {
		try {
			acRepydayChga = (AcRepydayChga) getSqlMapClientTemplate()
					.queryForObject("AcRepydayChga.getById", acRepydayChga);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acRepydayChga;
	}

	public int getCount(AcRepydayChga acRepydayChga) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcRepydayChga.findPage.count", acRepydayChga);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcRepydayChga acRepydayChga) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcRepydayChga.insert",
					acRepydayChga);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcRepydayChga acRepydayChga) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcRepydayChga.update",
					acRepydayChga);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}