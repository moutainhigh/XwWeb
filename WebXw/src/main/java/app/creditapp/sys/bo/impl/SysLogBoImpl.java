package  app.creditapp.sys.bo.impl;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysLogBo;
import app.creditapp.sys.dao.SysLogDao;
import app.creditapp.sys.entity.SysLog;
/**
* Title: SysLogBoImplImpl.java
* Description:
**/

public class SysLogBoImpl extends BaseService implements SysLogBo {
	private SysLogDao sysLogDao;

	public void delete(String op_id) throws ServiceException {
		try {
			sysLogDao.delete(op_id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysLog syslog) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysLogDao
					.getCount(syslog) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, syslog);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(sysLogDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysLog getByOp_id(String op_id) throws ServiceException {
		SysLog syslog = null;
		try {
			syslog = sysLogDao.getByOp_id(op_id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return syslog;
	}

	public void insertOrUpdate(SysLog syslog) throws ServiceException {
		try {
			if (StringUtils.isBlank(syslog.getLogId())) {
				sysLogDao.insert(syslog);
			} else {
				sysLogDao.update(syslog);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	} 

	public SysLogDao getSysLogDao() {
		return sysLogDao;
	}

	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}

}
