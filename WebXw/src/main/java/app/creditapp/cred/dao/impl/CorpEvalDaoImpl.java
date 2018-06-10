package  app.creditapp.cred.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cred.dao.CorpEvalDao;
import app.creditapp.cred.entity.CorpEval;
/**
* Title: CorpEvalDaoImpl.java
* Description:
**/
public class CorpEvalDaoImpl extends BaseIbatisDao implements CorpEvalDao {

	public void del(CorpEval corpEval) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpEval.del", corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpEval> findByPage(CorpEval corpEval) throws DAOException {
		List corpEvalList = null;
		try {
			corpEvalList = getSqlMapClientTemplate().queryForList(
					"CorpEval.findByPage", corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpEvalList;
	}

	public CorpEval getById(CorpEval corpEval) throws DAOException {
		try {
			corpEval = (CorpEval) getSqlMapClientTemplate()
					.queryForObject("CorpEval.getById", corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpEval;
	}
	//获取最新评级信息
	public CorpEval getByMaxId(CorpEval corpEval) throws DAOException{
		try {
			corpEval = (CorpEval) getSqlMapClientTemplate()
					.queryForObject("CorpEval.getByMaxId", corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpEval;
	}
	
	public int getCount(CorpEval corpEval) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpEval.findPage.count", corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpEval corpEval) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpEval.insert",
					corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpEval corpEval) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpEval.update",
					corpEval);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String corpSeq = "";
		try {
			corpSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CorpEval.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
}