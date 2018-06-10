package  app.creditapp.sysConfig.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sysConfig.dao.SysUpdateRecordDao;
import app.creditapp.sysConfig.entity.SysUpdateRecord;


/**
* Title: TableUpdateRecordDaoImpl.java
* Description:
**/

public class SysUpdateRecordDaoImpl extends BaseIbatisDao implements SysUpdateRecordDao {
	
	public void del(String id) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("TableUpdateRecord.del", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("É¾³ýÊ§°Ü");
		}
	}

	public List<SysUpdateRecord> findByPage(Map map) throws DAOException {
		List tableUpdateRecordList = null;
		try {
			tableUpdateRecordList = getSqlMapClientTemplate().queryForList(
					"TableUpdateRecord.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("·ÖÒ³²éÑ¯Ê§°Ü");
		}
		return tableUpdateRecordList;
	}

	public SysUpdateRecord getById(String id) throws DAOException {
		SysUpdateRecord sysUpdateRecord = null;
		try {
			sysUpdateRecord = (SysUpdateRecord) getSqlMapClientTemplate()
					.queryForObject("TableUpdateRecord.getById", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Ê§°Ü");
		}
		return sysUpdateRecord;
	}

	public int getCount(SysUpdateRecord sysUpdateRecord) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"TableUpdateRecord.findPage.count", sysUpdateRecord);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Í³¼ÆÊ§°Ü");
		}
		return count;
	}

	public void insert(SysUpdateRecord sysUpdateRecord) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("TableUpdateRecord.insert",
					sysUpdateRecord);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("ÐÂÔöÊ§°Ü");
		}

	}

	public void update(SysUpdateRecord sysUpdateRecord) throws DAOException {
		try {
			getSqlMapClientTemplate().update("TableUpdateRecord.update",
					sysUpdateRecord);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("¸üÐÂÊ§°Ü");
		}
	}

}
