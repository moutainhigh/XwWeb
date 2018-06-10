package  app.creditapp.sysConfig.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sysConfig.dao.SysRequireTableDao;
import app.creditapp.sysConfig.entity.SysRequireTable;
import app.creditapp.sysConfig.entity.TableInfo;


/**
* Title: RequireLogTableDaoImpl.java
* Description:
**/

public class SysRequireTableDaoImpl extends BaseIbatisDao implements SysRequireTableDao {

	public void del(SysRequireTable sysRequireTable) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("RequireLogTable.del", sysRequireTable);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("…æ≥˝ ß∞‹");
		}
	}

	public List<SysRequireTable> findByPage(Map map) throws DAOException {
		List requireLogTableList = null;
		try {
			requireLogTableList = getSqlMapClientTemplate().queryForList(
					"RequireLogTable.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("∑÷“≥≤È—Ø ß∞‹");
		}
		return requireLogTableList;
	}

	public SysRequireTable getById(SysRequireTable sysRequireTable) throws DAOException {
		try {
			sysRequireTable = (SysRequireTable) getSqlMapClientTemplate()
					.queryForObject("RequireLogTable.getById", sysRequireTable);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø ß∞‹");
		}
		return sysRequireTable;
	}

	public int getCount(SysRequireTable sysRequireTable) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"RequireLogTable.findPage.count", sysRequireTable);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—ØÕ≥º∆ ß∞‹");
		}
		return count;
	}

	public void insert(SysRequireTable sysRequireTable) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("RequireLogTable.insert",
					sysRequireTable);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("–¬‘ˆ ß∞‹");
		}

	}

	public void update(SysRequireTable sysRequireTable) throws DAOException {
		try {
			getSqlMapClientTemplate().update("RequireLogTable.update",
					sysRequireTable);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("∏¸–¬ ß∞‹");
		}
	}
	
	public List<SysRequireTable> getAll() throws DAOException {
		List<SysRequireTable> list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList(
					"RequireLogTable.getAll");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø ß∞‹");
		}
		return list;
	}

	public List<TableInfo> getAllTable(TableInfo tableInfo) throws DAOException {
		List tableList = null;
		try {
			tableList = getSqlMapClientTemplate().queryForList(
					"TableInfo.getAll",tableInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("∑÷“≥≤È—Ø ß∞‹");
		}
		return tableList;
	}
	public List<TableInfo> getAllTableNotExistsDoc(TableInfo tableInfo) throws DAOException {
		List tableList = null;
		try {
			tableList = getSqlMapClientTemplate().queryForList(
					"TableInfo.getAllNotExistsDoc",tableInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("∑÷“≥≤È—Ø ß∞‹");
		}
		return tableList;
	}
}
