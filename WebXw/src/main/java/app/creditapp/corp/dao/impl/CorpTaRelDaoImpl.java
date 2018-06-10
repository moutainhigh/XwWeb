package  app.creditapp.corp.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpTaRelDao;
import app.creditapp.corp.entity.CorpTaRel;
import app.creditapp.corp.entity.CorpTaRelforcif;
/**
* Title: CorpTaRelDaoImpl.java
**/
public class CorpTaRelDaoImpl extends BaseIbatisDao implements CorpTaRelDao {

	public void del(CorpTaRel corpTaRel) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpTaRel.del", corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpTaRel> findByPage(CorpTaRel corpTaRel) throws DAOException {
		List corpTaRelList = null;
		try {
			corpTaRelList = getSqlMapClientTemplate().queryForList(
					"CorpTaRel.findByPage", corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpTaRelList;
	}

	public CorpTaRel getById(CorpTaRel corpTaRel) throws DAOException {
		try {
			corpTaRel = (CorpTaRel) getSqlMapClientTemplate()
					.queryForObject("CorpTaRel.getById", corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpTaRel;
	}

	public int getCount(CorpTaRel corpTaRel) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpTaRel.findPage.count", corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpTaRel corpTaRel) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpTaRel.insert",corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	//通过dblink向软通ta表删除重复数据
	public void deleteforDblink() throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpTaRelforDblink.del");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//通过brNo查找所有符合条件的用户号
	public List getCifNo(CorpTaRel corpTaRel) throws DAOException {
		List list = null;
		try {
	
			list = getSqlMapClientTemplate().queryForList("CorpTaRel.getByBrNo", corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}
	//通过brNo查找视图中所有符合条件的用户号
	public List<CorpTaRelforcif> getByBrNoForDb(CorpTaRel corpTaRel) throws DAOException {
		List<CorpTaRelforcif> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("CorpTaRel.getByBrNoForDb", corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}
	public void update(CorpTaRel corpTaRel) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpTaRel.update",
					corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//通过dblink向软通ta表插如数据
	public void insertforCorp(CorpTaRel corpTaRel) throws DAOException{
		try {
			getSqlMapClientTemplate().insert("CorpTaRelforDblink.insert",
					corpTaRel);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
		
	}
}