package  app.creditapp.sys.bo.impl;
import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.dao.SysGlobalDAO;
import app.creditapp.entity.SysGlobal;
import app.creditapp.sys.bo.SysGlobalBo;
import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;
/**
* Title: SysAreaBoImplImpl.java
* Description:
**/

public class SysGlobalBoImpl extends BaseService implements SysGlobalBo {
	  private SysGlobalDAO sysGlobalDAO; 
	  public SysGlobal getSysGlobal() throws ServiceException { 
		  SysGlobal sysGlobal = null;
	    try {
	    	sysGlobal = sysGlobalDAO.getSysGlobal(); 
	    } catch (Exception e) {
	      throw new ServiceException(e.getMessage());
	    }
	    return sysGlobal;
	  }
	  
	 public SysGlobalDAO getSysGlobalDAO() {
		return sysGlobalDAO;
	}

	public void setSysGlobalDAO(SysGlobalDAO sysGlobalDAO) {
		this.sysGlobalDAO = sysGlobalDAO;
	}

	public void updateSts(SysGlobal sysGlobal)  throws ServiceException { 
	    try {
	    	//系统状态为日终完成（3）时才能启用
	    	if("1".equals(sysGlobal.getSys_sts())){
	    		String sysSts = getSysGlobal().getSys_sts();
	    		if("1".equals(sysSts)){
	    			throw new ServiceException("系统已经启用！");
	    		} else if("2".equals(sysSts)){
	    			throw new ServiceException("系统正在进行日终结算,请稍后再启用！");
	    		}
	    	}
	    	sysGlobalDAO.updateSts(sysGlobal);
	    	MBaseCache.getCache().put(CachecodeUtil.SYS_GLOBAL_STATUS_STR, sysGlobal.getSys_sts()
	    			, MBaseCache.getCache().getGroups(CachecodeUtil.SYS_GLOBAL_STATUS));
	    } catch (Exception e) {
	      throw new ServiceException(e.getMessage());
	    }
	  }
	public void updateDocSize(SysGlobal sysGlobal)  throws ServiceException { 
		try {
			sysGlobalDAO.update(sysGlobal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
