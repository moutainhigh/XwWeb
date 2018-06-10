package app.creditapp.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.sys.entity.SysCache;

public interface CacheService {
	/**
	 * 条件查询
	 * @return
	 * @throws ServiceException
	 */
	public List<Object> findByCondition(Object object) throws ServiceException;
	public List<String> findKeyByCondition() throws ServiceException;

	/**
	 * @功能说明：
	 * @修改说明：获取
	 */
	public List<Object> getCacheValue(String cache_name, String cache_key) throws ServiceException;
	/**
	 * @功能说明：根据缓存名获取缓存键值key
	 * @修改说明：
	 */
	public List<String> getCacheKey(String cache_name) throws ServiceException;
	/**
	 * @功能说明：获取系统自动加载的缓存
	 * @修改说明：
	 */
	public List<SysCache> getSysCache() throws ServiceException;
}
