package  app.creditapp.sys.dao.impl;
import java.util.List;
import java.util.Map;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysButtonDao;
import app.creditapp.sys.entity.SysButton;
/**
* Title: SysButtonDaoImpl.java
* Description:
**/
public class SysButtonDaoImpl extends BaseIbatisDao implements SysButtonDao {

	public void del(SysButton sysButton) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysButton.del", sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysButton> findByPage(SysButton sysButton) throws DAOException {
		List sysButtonList = null;
		try {
			sysButtonList = getSqlMapClientTemplate().queryForList(
					"SysButton.findByPage", sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysButtonList;
	}
	
	public List<SysButton> findAllByMenu(SysButton sysButton) throws DAOException {
		List sysButtonList = null;
		try {
			sysButtonList = getSqlMapClientTemplate().queryForList(
					"SysButton.findAllByMenu", sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysButtonList;
	}

	public SysButton getById(SysButton sysButton) throws DAOException {
		try {
			sysButton = (SysButton) getSqlMapClientTemplate()
					.queryForObject("SysButton.getById", sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysButton;
	}

	public int getCount(SysButton sysButton) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysButton.findPage.count", sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysButton sysButton) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysButton.insert",
					sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysButton sysButton) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysButton.update",
					sysButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}