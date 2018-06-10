package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysCache.java
* Description:
* @version：1.0
**/
public class SysCache extends BaseDomain {
	private String cache_no;//缓存序列号
	private String cache_name;//缓存名称
	private String cache_chn_name;//中文名称
	private String cache_type;//加载类型(0使用时加载，1启动加载)
	private String cache_desc;//描述

	/**
	 * @return 缓存序列号
	 */
	public String getCache_no() {
	 	return cache_no;
	}
	/**
	 * @设置 缓存序列号
	 * @param cache_no
	 */
	public void setCache_no(String cache_no) {
	 	this.cache_no = cache_no;
	}
	/**
	 * @return 缓存名称
	 */
	public String getCache_name() {
	 	return cache_name;
	}
	/**
	 * @设置 缓存名称
	 * @param cache_name
	 */
	public void setCache_name(String cache_name) {
	 	this.cache_name = cache_name;
	}
	/**
	 * @return 中文名称
	 */
	public String getCache_chn_name() {
	 	return cache_chn_name;
	}
	/**
	 * @设置 中文名称
	 * @param cache_chn_name
	 */
	public void setCache_chn_name(String cache_chn_name) {
	 	this.cache_chn_name = cache_chn_name;
	}
	/**
	 * @return 加载类型(0使用时加载，1启动加载)
	 */
	public String getCache_type() {
	 	return cache_type;
	}
	/**
	 * @设置 加载类型(0使用时加载，1启动加载)
	 * @param cache_type
	 */
	public void setCache_type(String cache_type) {
	 	this.cache_type = cache_type;
	}
	/**
	 * @return 描述
	 */
	public String getCache_desc() {
	 	return cache_desc;
	}
	/**
	 * @设置 描述
	 * @param cache_desc
	 */
	public void setCache_desc(String cache_desc) {
	 	this.cache_desc = cache_desc;
	}
}