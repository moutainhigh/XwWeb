package  app.creditapp.pact.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.pact.dao.LnPactDao;
import app.creditapp.pact.entity.LnPact;
/**
* Title: LnPactDaoImpl.java
* Description:
**/
public class LnPactDaoImpl extends BaseIbatisDao implements LnPactDao {

	public void del(LnPact lnPact) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnPact.del", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnPact> findByPage(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.findByPage", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	
	public List<LnPact> findByPageForTask(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.findByPageForTask", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageForList(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.findByPageForList", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageForAppr(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
			"LnPact.findByPageForAppr", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageForBatchNo(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
			"LnPact.findByPageForBatchNo", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageAppr(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
			"LnPact.findByPageAppr", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageForRel(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.findByPageForRel", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	
	public LnPact getById(LnPact lnPact) throws DAOException {
		try {
			lnPact = (LnPact) getSqlMapClientTemplate()
					.queryForObject("LnPact.getById", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}
	public LnPact getByIdForAppId(LnPact lnPact) throws DAOException {
		try {
			lnPact = (LnPact) getSqlMapClientTemplate()
					.queryForObject("LnPact.getByIdForAppId", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}

	public List<LnPact> getByIdForBatchNo(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.getByIdForBatchNo", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	
	public List<LnPact> getWfTaskList(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.getWfTaskList", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> getByIdForApp(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.getByIdForApp", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	public LnPact getByIdForAppr(LnPact lnPact) throws DAOException {
		try {
			lnPact = (LnPact) getSqlMapClientTemplate()
					.queryForObject("LnPact.getByIdForAppr", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}
	public LnPact getByIdForAppAndBatch(LnPact lnPact) throws DAOException {
		try {
			lnPact = (LnPact) getSqlMapClientTemplate()
					.queryForObject("LnPact.getByIdForAppAndBatch", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}
	public LnPact getByIdForList(LnPact lnPact) throws DAOException {
		try {
			lnPact = (LnPact) getSqlMapClientTemplate()
					.queryForObject("LnPact.getByIdForList", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}

	
	public LnPact getByAppId(LnPact lnPact) throws DAOException{
		try {
			lnPact = (LnPact) getSqlMapClientTemplate().queryForObject("LnPact.getByAppId", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}
	

	public int getCount(LnPact lnPact) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnPact.findPage.count", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public int getCountForTask(LnPact lnPact) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnPact.findPage.countForTask", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForList(LnPact lnPact) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnPact.findPageForList.count", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForRel(LnPact lnPact) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnPact.findPageForRel.count", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public void insert(LnPact lnPact) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnPact.insert",
					lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnPact lnPact) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnPact.update",
					lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void updateSts(LnPact lnPact) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnPact.updateSts",
					lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public List<LnPact>  findByPageQuotaForCif(LnPact lnPact) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPact.findByPageQuotaForCif", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}

	public int getCountQuotaForCif(LnPact lnPact)  throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnPact.findByPageQuotaForCif.count", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public LnPact getPactNoAndBrNo(LnPact lnPact) throws DAOException {
		try {
			lnPact = (LnPact) getSqlMapClientTemplate()
					.queryForObject("LnPact.getPactNoAndBrNo", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPact;
	}
	public List<LnPact> findListToWorkE() throws DAOException{
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnPactDao.findListToWorkE");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}
	
	//根据合同号取合同金额
	public int getPactAmtCount(LnPact lnPact)  throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnPact.getPactAmtCount", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	//合同表（ln_pact）的到期日end_date
	public String getMaxEndDate(LnPact lnPact)  throws DAOException {
		String maxEndDate = "";
		try {
			maxEndDate = (String) getSqlMapClientTemplate().queryForObject(
					"LnPact.getMaxEndDate", lnPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return maxEndDate;
	}
	
}