package app.creditapp.dao.impl;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.dao.SysGlobalDAO;
import app.creditapp.entity.SysGlobal;

/**
 * 系统日期状态类
 * @see 
 * 修改记录:
 */
public class SysGlobalDAOImpl extends BaseIbatisDao implements SysGlobalDAO {

	/* 
	 * @see app.creditapp.dao.SysGlobalDAO#update(app.creditapp.entity.SysGlobal)
	 */
	public void update(SysGlobal sysGlobal) throws DAOException {
		try {
			this.getSqlMapClientTemplate().update("sys_global.update", sysGlobal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}
	
	public void updateSts(SysGlobal sysGlobal) throws DAOException {
		try {
			this.getSqlMapClientTemplate().update("sys_global.updateSts", sysGlobal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}

	/* 
	 * @see app.creditapp.dao.SysGlobalDAO#getSysGlobal()
	 */
	public SysGlobal getSysGlobal() throws DAOException {
		SysGlobal sysGlobal = null;
		try{
			sysGlobal = (SysGlobal)getSqlMapClientTemplate().queryForObject("sys_global.getSysGlobal");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return sysGlobal;
	}

}
