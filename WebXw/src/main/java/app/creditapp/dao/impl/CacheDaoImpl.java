package app.creditapp.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.dao.CacheDAO;
import app.creditapp.entity.CacheBase;

public class CacheDaoImpl extends BaseIbatisDao implements CacheDAO {

	public List<Object> findByCondition(Object key_name) throws DAOException {
		List list = null;
		CacheBase cb = new CacheBase();
		try {
			cb.setKey_name((String)key_name);
			list =  getSqlMapClientTemplate().queryForList("cacheBase.findByCondition", cb);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return list;
	}

	public List<String> findKeyByCondition() throws DAOException {
		List<String> list = null;
		try {
			list =  getSqlMapClientTemplate().queryForList("cacheBase.findKeyByCondition");
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return list;
	}
}
