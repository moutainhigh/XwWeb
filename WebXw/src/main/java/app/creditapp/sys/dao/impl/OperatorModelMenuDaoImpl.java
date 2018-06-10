package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.OperatorModelMenuDao;
import app.creditapp.sys.entity.OperatorModelMenu;
/**
* Title: OperatorModelMenuDaoImpl.java
* Description:
**/
public class OperatorModelMenuDaoImpl extends BaseIbatisDao implements OperatorModelMenuDao {

	public void del(OperatorModelMenu operatorModelMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("OperatorModelMenu.del", operatorModelMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<OperatorModelMenu> findByPage(OperatorModelMenu operatorModelMenu) throws DAOException {
		List operatorModelMenuList = null;
		try {
			operatorModelMenuList = getSqlMapClientTemplate().queryForList(
					"OperatorModelMenu.findByPage", operatorModelMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return operatorModelMenuList;
	}

	public OperatorModelMenu getById(OperatorModelMenu operatorModelMenu) throws DAOException {
		try {
			operatorModelMenu = (OperatorModelMenu) getSqlMapClientTemplate()
					.queryForObject("OperatorModelMenu.getById", operatorModelMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return operatorModelMenu;
	}

	public int getCount(OperatorModelMenu operatorModelMenu) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"OperatorModelMenu.findPage.count", operatorModelMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(OperatorModelMenu operatorModelMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("OperatorModelMenu.insert",
					operatorModelMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(OperatorModelMenu operatorModelMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().update("OperatorModelMenu.update",
					operatorModelMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<OperatorModelMenu> findAll() throws DAOException {
		List operatorModelMenuList = null;
		try {
			operatorModelMenuList = getSqlMapClientTemplate().queryForList(
					"OperatorModelMenu.findAll");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return operatorModelMenuList;
	}

	@Override
	public List<OperatorModelMenu> getAllMenuByParent(String menuNo)
			throws DAOException {
		List operatorModelMenuList = null;
		try {
			operatorModelMenuList = getSqlMapClientTemplate().queryForList(
					"OperatorModelMenu.getAllMenuByParent");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return operatorModelMenuList;
	}

	@Override
	public List<OperatorModelMenu> findAllByType(String cif_type) throws DAOException {
		List operatorModelMenuList = null;
		try {
			operatorModelMenuList = getSqlMapClientTemplate().queryForList(
					"OperatorModelMenu.findByType",cif_type);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return operatorModelMenuList;
	}

	@Override
	public OperatorModelMenu getByLastUrl(String last_url) throws DAOException {
		OperatorModelMenu operatorModelMenu = null ;
		try {
			operatorModelMenu = (OperatorModelMenu) getSqlMapClientTemplate()
					.queryForObject("OperatorModelMenu.getByLstUrl", last_url);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return operatorModelMenu;
	}
}