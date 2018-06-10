package  app.creditapp.dev.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.dev.dao.TreeConfDao;
import app.creditapp.dev.entity.TreeConf;
/**
* Title: TreeConfDaoImpl.java
* Description:
**/
public class TreeConfDaoImpl extends BaseIbatisDao implements TreeConfDao {

	public void del(TreeConf treeConf) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("TreeConf.del", treeConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<TreeConf> findByPage(TreeConf treeConf) throws DAOException {
		List treeConfList = null;
		try {
			treeConfList = getSqlMapClientTemplate().queryForList(
					"TreeConf.findByPage", treeConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return treeConfList;
	}

	public TreeConf getById(TreeConf treeConf) throws DAOException {
		try {
			treeConf = (TreeConf) getSqlMapClientTemplate()
					.queryForObject("TreeConf.getById", treeConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return treeConf;
	}

	public int getCount(TreeConf treeConf) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"TreeConf.findPage.count", treeConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(TreeConf treeConf) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("TreeConf.insert",
					treeConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(TreeConf treeConf) throws DAOException {
		try {
			getSqlMapClientTemplate().update("TreeConf.update",
					treeConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}