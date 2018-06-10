package  app.creditapp.sys.bo.impl;
import java.util.HashMap;
import app.base.BaseService;
import app.base.ServiceException;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysLoginLogBo;
import app.creditapp.sys.dao.SysLoginLogDao;
import app.creditapp.sys.entity.SysLoginLog;
/**
* Title: SysLoginLogBoImplImpl.java
* Description:
**/

public class SysLoginLogBoImpl extends BaseService implements SysLoginLogBo {
	  private SysLoginLogDao sysLoginLogDao; 
	  /*
	   * (non-Javadoc)
	   * @see app.creditapp.bo.SysLoginLogBO#del(java.lang.String)
	   */
	  public void del(String id) throws ServiceException { 
	    try {
	    	sysLoginLogDao.del(id); 
	    } catch (Exception e) {
	      throw new ServiceException(e.getMessage());
	    }
	  }
	  /*
	   * (non-Javadoc)
	   * @see app.creditapp.bo.SysLoginLogBO#findByPage(app.util.toolkit.Ipage, app.creditapp.entity.SysLoginLog)
	   */
	  public Ipage findByPage(Ipage ipg, SysLoginLog sysloginlog)  throws ServiceException { 
	    try {
	      ipg.initPageCounts(new Integer[]{(Integer)sysLoginLogDao.getCount(sysloginlog)});//初始化分页类 
	      HashMap<String,Object> map = (HashMap<String,Object>)IbatisUtils.getEntityPropertyMap(ipg, sysloginlog); 
	      map.put("startNum", ipg.getStartRow());
	      map.put("endNum", ipg.getEndNum());
	      ipg.setResult(sysLoginLogDao.findByPage(map)); 
	    } catch (Exception e) {
	      throw new ServiceException(e.getMessage());
	    }
	    return ipg; 
	  }
	  /*
	   * (non-Javadoc)
	   * @see app.creditapp.bo.SysLoginLogBO#getById(java.lang.String)
	   */
	  public SysLoginLog getById(String id) throws ServiceException { 
	    SysLoginLog sysloginlog = null; 
	    try {
	      sysloginlog = sysLoginLogDao.getById(id); 
	    } catch (Exception e) {
	      throw new ServiceException(e.getMessage());
	    }
	    return sysloginlog;
	  }
	  /*
	   * (non-Javadoc)
	   * @see app.creditapp.bo.SysLoginLogBO#insertOrUpdate(app.creditapp.entity.SysLoginLog)
	   */
	public void insertOrUpdate(SysLoginLog sysloginlog) throws ServiceException {
	    try { 
	      SysLoginLog sysloginlogs = sysLoginLogDao.getById(sysloginlog.getSessionId());
	      if(sysloginlogs==null){
	    	  sysLoginLogDao.insert(sysloginlog);  
	      } else {
	    	  sysLoginLogDao.update(sysloginlog);
	    }
	    } catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	   }
	}
	public SysLoginLogDao getSysLoginLogDao() {
		return sysLoginLogDao;
	}
	public void setSysLoginLogDao(SysLoginLogDao sysLoginLogDao) {
		this.sysLoginLogDao = sysLoginLogDao;
	}
}
