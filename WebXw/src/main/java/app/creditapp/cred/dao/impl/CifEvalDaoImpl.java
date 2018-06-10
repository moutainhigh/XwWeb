package  app.creditapp.cred.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cred.dao.CifEvalDao;
import app.creditapp.cred.entity.CifEval;
/**
* Title: CifEvalDaoImpl.java
* Description:
**/
public class CifEvalDaoImpl extends BaseIbatisDao implements CifEvalDao {

	public void del(CifEval cifEval) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifEval.del", cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifEval> findByPage(CifEval cifEval) throws DAOException {
		List cifEvalList = null;
		try {
			cifEvalList = getSqlMapClientTemplate().queryForList(
					"CifEval.findByPage", cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifEvalList;
	}

	public CifEval getById(CifEval cifEval) throws DAOException {
		try {
			cifEval = (CifEval) getSqlMapClientTemplate()
					.queryForObject("CifEval.getById", cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifEval;
	}
	//获取最新评分信息
	public CifEval getByMaxId(CifEval cifEval) throws DAOException{
		try {
			cifEval = (CifEval) getSqlMapClientTemplate()
					.queryForObject("CifEval.getByMaxId", cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifEval;
	}
	
	public int getCount(CifEval cifEval) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifEval.findPage.count", cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifEval cifEval) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifEval.insert",
					cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifEval cifEval) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifEval.update",
					cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public int cifScoreUpdate(CifEval cifEval) throws DAOException {
		int result = 0;
		try {
			result = getSqlMapClientTemplate().update("CifEval.cifScoreUpdate",
					cifEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public String getKey() throws DAOException {
		String cifSeq = "";
		try {
			cifSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CifEval.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifSeq;
	}
}