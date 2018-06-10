package app.creditapp.inf.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.entity.WsBase;

/**
 * Title: WsBaseDaoImpl.java Description:
 **/
public class WsBaseDaoImpl extends BaseIbatisDao implements WsBaseDao {

	@Override
	public void insertDelBack(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsBase.insertDelBack", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	@Override
	public void updateDelBack(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsBase.updateDelBack", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	public void del(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsBase.del", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsBase> findByPage(WsBase wsBase) throws DAOException {
		List wsBaseList = null;
		try {
			wsBaseList = getSqlMapClientTemplate().queryForList("WsBase.findByPage", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsBaseList;
	}

	public WsBase getById(WsBase wsBase) throws DAOException {
		try {
			wsBase = (WsBase) getSqlMapClientTemplate().queryForObject("WsBase.getById", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsBase;
	}

	public int getCount(WsBase wsBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("WsBase.findPage.count", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsBase.insert", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsBase.update", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}


	@Override
	public void insertWsBaseReq(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsBase.insertReq", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void updateWsBaseResp(WsBase wsBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsBase.updateResp", wsBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}