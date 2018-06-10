package  app.creditapp.sec.bo.impl;

import java.util.HashMap;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sec.bo.AuditInfoLogBo;
import app.creditapp.sec.dao.AuditInfoLogDao;
import app.creditapp.sec.entity.AuditInfoLog;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;


/**
* Title: AuditInfoLogBoImpl.java
* Description:
**/

public class AuditInfoLogBoImpl extends BaseService implements AuditInfoLogBo {

	private AuditInfoLogDao auditInfoLogDao;

	public void del(long id) throws ServiceException {
		try {
			auditInfoLogDao.del(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AuditInfoLog auditInfoLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) auditInfoLogDao
					.getCount(auditInfoLog) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, auditInfoLog);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(auditInfoLogDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AuditInfoLog getById(long id) throws ServiceException {
		AuditInfoLog auditInfoLog = null;
		try {
			auditInfoLog = auditInfoLogDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return auditInfoLog;
	}

	public void insert(AuditInfoLog auditInfoLog) throws ServiceException {
		try {
			auditInfoLogDao.insert(auditInfoLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AuditInfoLog auditInfoLog) throws ServiceException {
		try {
			auditInfoLogDao.update(auditInfoLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public int getCount(AuditInfoLog auditInfoLog)throws ServiceException {
		try {
			return auditInfoLogDao.getCount(auditInfoLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public AuditInfoLogDao getAuditInfoLogDao() {
		return auditInfoLogDao;
	}

	public void setAuditInfoLogDao(AuditInfoLogDao auditInfoLogDao) {
		this.auditInfoLogDao = auditInfoLogDao;
	}

}
