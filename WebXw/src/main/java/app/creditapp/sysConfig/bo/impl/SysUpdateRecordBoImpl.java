package  app.creditapp.sysConfig.bo.impl;

import java.util.HashMap;


import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sysConfig.bo.SysUpdateRecordBo;
import app.creditapp.sysConfig.dao.SysUpdateRecordDao;
import app.creditapp.sysConfig.entity.SysUpdateRecord;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;


/**
* Title: TableUpdateRecordBoImpl.java
* Description:
**/

public class SysUpdateRecordBoImpl extends BaseService implements SysUpdateRecordBo {

	private SysUpdateRecordDao sysUpdateRecordDao;

	public void del(String id) throws ServiceException {
		try {
			sysUpdateRecordDao.del(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysUpdateRecord sysUpdateRecord)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysUpdateRecordDao
					.getCount(sysUpdateRecord) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, sysUpdateRecord);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(sysUpdateRecordDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysUpdateRecord getById(String id) throws ServiceException {
		SysUpdateRecord sysUpdateRecord = null;
		try {
			sysUpdateRecord = sysUpdateRecordDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysUpdateRecord;
	}

	public void insert(SysUpdateRecord sysUpdateRecord) throws ServiceException {
		sysUpdateRecordDao.insert(sysUpdateRecord);
	}

	public void update(SysUpdateRecord sysUpdateRecord) throws ServiceException {
		try {
			sysUpdateRecordDao.update(sysUpdateRecord);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public SysUpdateRecordDao getSysUpdateRecordDao() {
		return sysUpdateRecordDao;
	}

	public void setSysUpdateRecordDao(SysUpdateRecordDao sysUpdateRecordDao) {
		this.sysUpdateRecordDao = sysUpdateRecordDao;
	}
}
