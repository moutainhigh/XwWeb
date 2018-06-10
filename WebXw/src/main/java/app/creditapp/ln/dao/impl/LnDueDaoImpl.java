package  app.creditapp.ln.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnDueDao;
import app.creditapp.ln.entity.LnDue;
/**
* Title: LnDueDaoImpl.java
* Description:
**/
public class LnDueDaoImpl extends BaseIbatisDao implements LnDueDao {

	public void del(LnDue lnDue) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnDue.del", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnDue> findByPage(LnDue lnDue) throws DAOException {
		List lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.findByPage", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
	public List<LnDue> findByPageForPop(LnDue lnDue) throws DAOException {
		List lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.findByPageForPop", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
	public List<LnDue> findByPageForRead(LnDue lnDue) throws DAOException {
		List lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.findByPageForRead", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
	
	public List<LnDue > findListByFundNo(LnDue lnDue) throws DAOException{
		List lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.findListByFundNo", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}

	public List<LnDue> findAllFail(LnDue lnDue) throws DAOException{
		List lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.findPageFail",lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}

	//抽查回访列表
	public List<LnDue> getCheckList(LnDue lnDue) throws DAOException {
		List lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.getCheckList", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
	//抽查回访记录数
	public int getCnt(LnDue lnDue) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnDue.getCheckList.count", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public LnDue getById(LnDue lnDue) throws DAOException {
		try {
			lnDue = (LnDue) getSqlMapClientTemplate()
					.queryForObject("LnDue.getById", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDue;
	}
	
	
	
	public LnDue getByIdForDue(LnDue lnDue) throws DAOException {
		try {
			lnDue = (LnDue) getSqlMapClientTemplate()
					.queryForObject("LnDue.getByIdForDue", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDue;
	}
	public LnDue getByIdForPactNo(LnDue lnDue) throws DAOException {
		try {
			lnDue = (LnDue) getSqlMapClientTemplate()
			.queryForObject("LnDue.getByIdForPactNo", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDue;
	}
	
	public LnDue getByPactId(String pactId) throws DAOException {
		LnDue lnDue = null;
		try {
			lnDue = (LnDue) getSqlMapClientTemplate()
			.queryForObject("LnDue.getByPactId", pactId);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDue;
	}

	public LnDue getByPactNoAndBrNo(LnDue lnDue) throws DAOException {
		try {
			lnDue = (LnDue) getSqlMapClientTemplate()
					.queryForObject("LnDue.getByPactNoAndBrNo", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDue;
	}
	
	public int getCount(LnDue lnDue) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnDue.findPage.count", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountFail(LnDue lnDue) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnDue.findPageFail.count", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForPop(LnDue lnDue) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnDue.findPageForPop.count", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForRead(LnDue lnDue) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnDue.findPageForRead.count", lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnDue lnDue) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnDue.insert",
					lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnDue lnDue) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnDue.update",
					lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<LnDue> findList() throws DAOException {
		List<LnDue> lnDueDaoList = null;
		try {
			lnDueDaoList = getSqlMapClientTemplate().queryForList(
					"LnDueDao.findList");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueDaoList;
	}

	public List<LnDue> findListToWorkF() throws DAOException{
		List<LnDue> lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDueDao.findListToWorkF");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
	public int dueStsUpdate(LnDue LnDue) throws DAOException{
		int result = 0;
		try {
			result = getSqlMapClientTemplate().update("LnDue.dueStsUpdate",
					LnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return result;
	}

	@Override
	public List<LnDue> getLndueList(LnDue lnDue) throws DAOException {
		List<LnDue> lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList(
					"LnDue.getLndueList",lnDue);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
	
	@Override
	public List<LnDue> getLndueListByBrNo(String brNo) throws DAOException {
		List<LnDue> lnDueList = null;
		try {
			lnDueList = getSqlMapClientTemplate().queryForList("LnDue.getLndueListByBrno", brNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnDueList;
	}
}