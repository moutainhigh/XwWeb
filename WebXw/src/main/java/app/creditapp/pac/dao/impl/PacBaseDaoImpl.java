package app.creditapp.pac.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.pac.dao.PacBaseDao;
import app.creditapp.pac.entity.PacBase;

/**
 * Title: PacBaseDaoImpl.java Description:
 * 
 **/
public class PacBaseDaoImpl extends BaseIbatisDao implements PacBaseDao {

    public void del(PacBase pacBase) throws DAOException {
	try {
	    getSqlMapClientTemplate().delete("PacBase.del", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
    }
    
    public void updateDownCnt(String pac_id) throws DAOException {
	try {
	    getSqlMapClientTemplate().update("PacBase.updateDownCnt", pac_id);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
    }
    
    public PacBase getLastPacBase(PacBase pacBase) throws DAOException {
	try {
	    pacBase = (PacBase) getSqlMapClientTemplate().queryForObject(
		    "PacBase.getLastPacBase", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
	return pacBase;
    }

    public List<PacBase> findByPage(PacBase pacBase) throws DAOException {
	List pacBaseList = null;
	try {
	    pacBaseList = getSqlMapClientTemplate().queryForList(
		    "PacBase.findByPage", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
	return pacBaseList;
    }

    public List<PacBase> listPacBase(PacBase pacBase) throws DAOException {
	List<PacBase> pacBaseList = null;
	try {
	    pacBaseList = (List<PacBase>) getSqlMapClientTemplate()
		    .queryForList("PacBase.listPacBase", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
	return pacBaseList;
    }

    public PacBase getById(PacBase pacBase) throws DAOException {
	try {
	    pacBase = (PacBase) getSqlMapClientTemplate().queryForObject(
		    "PacBase.getById", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
	return pacBase;
    }

    public int getCount(PacBase pacBase) throws DAOException {
	int count = 0;
	try {
	    count = (Integer) getSqlMapClientTemplate().queryForObject(
		    "PacBase.findPage.count", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
	return count;
    }

    public void insert(PacBase pacBase) throws DAOException {
	try {
	    getSqlMapClientTemplate().insert("PacBase.insert", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}

    }

    public void update(PacBase pacBase) throws DAOException {
	try {
	    getSqlMapClientTemplate().update("PacBase.update", pacBase);
	} catch (Exception e) {
	    log.error(e);
	    throw new DAOException(e.getMessage());
	}
    }
    
    public List<PacBase> getyn(PacBase pacBase) throws DAOException {
    	List<PacBase> list = null;
    	try {
    	    list =  getSqlMapClientTemplate().queryForList(
    		    "PacBase.PacBaseyn", pacBase);
    	} catch (Exception e) {
    	    log.error(e);
    	    throw new DAOException(e.getMessage());
    	}
    	return list;
        }

	@Override
	public int getPacCount(PacBase pacBase) throws DAOException {
		// TODO Auto-generated method stub
		int count = 0;
		try{
			count = (Integer) getSqlMapClientTemplate().queryForObject("PacBase.getPacCount", pacBase);
		}catch(Exception e){
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}