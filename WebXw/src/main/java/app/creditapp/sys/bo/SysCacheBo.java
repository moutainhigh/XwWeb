package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysCache;

/**
* Title: SysCacheBo.java
* Description:
**/
public interface SysCacheBo {

	public SysCache getById(SysCache sysCache) throws ServiceException;

	public void del(SysCache sysCache) throws ServiceException;

	public void insert(SysCache sysCache) throws ServiceException;

	public void update(SysCache sysCache) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysCache sysCache) throws ServiceException;
	/**
	 * @功能说明：获取系统自动加载的缓存
	 * @修改说明：
	 */
	public List<SysCache> getSysCache() throws ServiceException;

}