package  app.creditapp.sys.dao.impl;
import java.util.List;
import java.util.Map;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.InterfaceBackMegDao;
import app.creditapp.sys.entity.InterfaceBackMeg;
/**
* Title: InterfaceBackMegDaoImpl.java
* Description:
**/
public class InterfaceBackMegDaoImpl extends BaseIbatisDao implements InterfaceBackMegDao {

	public void del(InterfaceBackMeg interfaceBackMeg) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("InterfaceBackMeg.del", interfaceBackMeg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<InterfaceBackMeg> findByPage(InterfaceBackMeg interfaceBackMeg) throws DAOException {
		List interfaceBackMegList = null;
		try {
			interfaceBackMegList = getSqlMapClientTemplate().queryForList(
					"InterfaceBackMeg.findByPage", interfaceBackMeg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return interfaceBackMegList;
	}

	public InterfaceBackMeg getById(InterfaceBackMeg interfaceBackMeg) throws DAOException {
		try {
			interfaceBackMeg = (InterfaceBackMeg) getSqlMapClientTemplate()
					.queryForObject("InterfaceBackMeg.getById", interfaceBackMeg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return interfaceBackMeg;
	}

	public int getCount(InterfaceBackMeg interfaceBackMeg) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"InterfaceBackMeg.findPage.count", interfaceBackMeg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(InterfaceBackMeg interfaceBackMeg) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("InterfaceBackMeg.insert",
					interfaceBackMeg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(InterfaceBackMeg interfaceBackMeg) throws DAOException {
		try {
			getSqlMapClientTemplate().update("InterfaceBackMeg.update",
					interfaceBackMeg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}