package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftPoolRelDao;
import app.creditapp.aft.entity.AftPoolRel;
/**
* Title: AftPoolRelDaoImpl.java
* Description:
**/
public class AftPoolRelDaoImpl extends BaseIbatisDao implements AftPoolRelDao {

	public void del(AftPoolRel aftPoolRel) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftPoolRel.del", aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//根据资金池id和合同id删除
	public void delect(AftPoolRel aftPoolRel) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftPoolRel.delect", aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	//通过fundNo将ln_due中的数据批量插入aft_pool_rel表中 
	public void insertList(AftPoolRel aftPoolRel) throws DAOException{
		try {
			getSqlMapClientTemplate().insert("AftPoolRel.insertList",
					aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	
	public List<AftPoolRel> findByPage(AftPoolRel aftPoolRel) throws DAOException {
		List aftPoolRelList = null;
		try {
			aftPoolRelList = getSqlMapClientTemplate().queryForList(
					"AftPoolRel.findByPage", aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftPoolRelList;
	}

	public AftPoolRel getById(AftPoolRel aftPoolRel) throws DAOException {
		try {
			aftPoolRel = (AftPoolRel) getSqlMapClientTemplate()
					.queryForObject("AftPoolRel.getById", aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftPoolRel;
	}

	public int getCount(AftPoolRel aftPoolRel) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftPoolRel.findPage.count", aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftPoolRel aftPoolRel) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftPoolRel.insert",
					aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftPoolRel aftPoolRel) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftPoolRel.update",
					aftPoolRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}