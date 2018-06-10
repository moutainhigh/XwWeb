/**
 * 
 */
package app.creditapp.dao;

import app.base.DAOException;
import app.creditapp.entity.SysGlobal;

/**
 * 系统状态日期类
 * @see 
 * 修改记录:
 */
public interface SysGlobalDAO {
	/**
	 * 更新
	 * @param sysGlobal
	 * @throws DAOException
	 */
	public void update(SysGlobal sysGlobal) throws DAOException;
	
	public void updateSts(SysGlobal sysGlobal) throws DAOException;
	/**
	 * 获取
	 * @return
	 * @throws DAOException
	 */
	public SysGlobal getSysGlobal() throws DAOException;

}
