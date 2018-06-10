package  app.creditapp.gage.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.gage.dao.LnGageDao;
import app.creditapp.gage.entity.LnGage;
/**
* Title: LnGageDaoImpl.java
* Description:
**/
public class LnGageDaoImpl extends BaseIbatisDao implements LnGageDao {

	public void del(LnGage lnGage) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnGage.del", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnGage> findByPage(LnGage lnGage) throws DAOException {
		List lnGageList = null;
		try {
			lnGageList = getSqlMapClientTemplate().queryForList(
					"LnGage.findByPage", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageList;
	}

	public LnGage getById(LnGage lnGage) throws DAOException {
		try {
			lnGage = (LnGage) getSqlMapClientTemplate()
					.queryForObject("LnGage.getById", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGage;
	}

	public int getCount(LnGage lnGage) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnGage.findPage.count", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnGage lnGage) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnGage.insert",
					lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnGage lnGage) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnGage.update",
					lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<LnGage> findByPageQuotaForCif(LnGage lnGage) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"LnGage.findByPageQuotaForCif", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}

	@Override
	public int getCountQuotaForCif(LnGage lnGage) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnGage.findByPageQuotaForCif.count", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<LnGage> findByPageQuotaForLn(LnGage lnGage) throws DAOException {
		List lnGageList = null;
		try {
			lnGageList = getSqlMapClientTemplate().queryForList(
					"LnGage.findByPageQuotaForLn", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageList;
	}

	@Override
	public int getCountQuotaForLn(LnGage lnGage) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnGage.findByPageQuotaForLn.count", lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	@Override
	public int findlistBygegeStsCount() throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnGage.findlistBygegeSts.count");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//查询所有待解押产品信息
	@Override
	public List<LnGage> findlistBygegeSts(Map map) throws DAOException {
		List lnGageList = null;
		try {
			lnGageList = getSqlMapClientTemplate().queryForList(
					"LnGage.findlistBygegeSts",map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageList;
	}

	@Override
	public void updateSts(LnGage lnGage) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnGage.updateSts",
					lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}
	/**
	 * 根据押品ID查询申请ID
	 */
	@Override
	public String findAppIdByGageId(LnGage lnGage) throws DAOException {
		String appId = "";
		try {
			appId = (String) getSqlMapClientTemplate().queryForObject(
					"LnGage.findAppIdByGageId",lnGage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return appId;
	}
}