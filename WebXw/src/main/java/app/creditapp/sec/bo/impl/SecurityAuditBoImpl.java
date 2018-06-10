package  app.creditapp.sec.bo.impl;

import java.util.HashMap;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sec.bo.SecurityAuditBo;
import app.creditapp.sec.dao.SecurityAuditDao;
import app.creditapp.sec.entity.SecurityAudit;
import app.creditapp.trade.bo.CipTradeBo;
import app.creditapp.trade.entity.CipTrade;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;


/**
* Title: SecurityAuditBoImpl.java
* Description:
**/

public class SecurityAuditBoImpl extends BaseService implements SecurityAuditBo {

	private SecurityAuditDao secAuditDao;
	private CipTradeBo cipTradeBo;

	public void del(String id) throws ServiceException {
		try {
			secAuditDao.del(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SecurityAudit secAudit)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) secAuditDao
					.getCount(secAudit) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, secAudit);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(secAuditDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SecurityAudit getById(String id) throws ServiceException {
		SecurityAudit secAudit = null;
		try {
			secAudit = secAuditDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return secAudit;
	}

	public void insert(SecurityAudit secAudit) throws ServiceException {
		try {
			secAuditDao.insert(secAudit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SecurityAudit secAudit) throws ServiceException {
		try {
			secAuditDao.update(secAudit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void updateIsUse(SecurityAudit secAudit) throws ServiceException {
		try {
			secAuditDao.updateIsUse(secAudit);
//			if("7".equals(secAudit.getItemNo())){
//				CipTrade cipTrade = new CipTrade();
//				cipTrade.setTx_flag(secAudit.getIsUse());
//				cipTradeBo.updateTxFlag(cipTrade);
//			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<SecurityAudit> getListForSec(SecurityAudit secAudit) throws ServiceException {
		List<SecurityAudit> list=null;
		try {
			list=(List)secAuditDao.getListForSec(secAudit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	
	public List<SecurityAudit> findAuditListByCode(String codeType) throws ServiceException {
		List<SecurityAudit> list=null;
		SecurityAudit secAudit = new SecurityAudit();
		secAudit.setCodeType(codeType);
		try {
			list=(List)secAuditDao.findAuditListByCode(secAudit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	
	public SecurityAuditDao getSecurityAuditDao() {
		return secAuditDao;
	}

	public void setSecurityAuditDao(SecurityAuditDao secAuditDao) {
		this.secAuditDao = secAuditDao;
	}

	public CipTradeBo getCipTradeBo() {
		return cipTradeBo;
	}

	public void setCipTradeBo(CipTradeBo cipTradeBo) {
		this.cipTradeBo = cipTradeBo;
	}
}
