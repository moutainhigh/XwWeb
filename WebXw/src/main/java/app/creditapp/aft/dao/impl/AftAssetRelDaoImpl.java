package  app.creditapp.aft.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftAssetRelDao;
import app.creditapp.aft.entity.AftAssetRel;
import app.creditapp.sys.entity.SysUserRole;
/**
* Title: AftAssetRelDaoImpl.java
* Description:
**/
public class AftAssetRelDaoImpl extends BaseIbatisDao implements AftAssetRelDao {

	public void del(AftAssetRel aftAssetRel) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftAssetRel.del", aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftAssetRel> findByPage(AftAssetRel aftAssetRel) throws DAOException {
		List aftAssetRelList = null;
		try {
			aftAssetRelList = getSqlMapClientTemplate().queryForList(
					"AftAssetRel.findByPage", aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAssetRelList;
	}

	public AftAssetRel getById(AftAssetRel aftAssetRel) throws DAOException {
		try {
			aftAssetRel = (AftAssetRel) getSqlMapClientTemplate()
					.queryForObject("AftAssetRel.getById", aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAssetRel;
	}

	public int getCount(AftAssetRel aftAssetRel) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftAssetRel.findPage.count", aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftAssetRel aftAssetRel) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftAssetRel.insert",
					aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftAssetRel aftAssetRel) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftAssetRel.update",
					aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public void updateForLn(AftAssetRel aftAssetRel) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftAssetRel.updateForLn",
					aftAssetRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	public void updateValue(Map<?, ?> map) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftAssetRel.updateValue", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("¸üÐÂÊ§°Ü");
		}
		
	}
	public double getTotalAmt(String pactNo,String recId) throws DAOException {
		double totalAmt=0.00;
		try {
			AftAssetRel aar = new AftAssetRel();
			aar.setPactNo(pactNo);
			aar.setRecId(recId);
			totalAmt = (Double) getSqlMapClientTemplate().queryForObject(
					"AftAssetRel.getTotalAmt",aar);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return totalAmt;
	}
}