package app.oscache;
/**
 * 
 * @对象说明 缓存单例类
 * @修改说明
 */
public class MBaseCache {
	
	private static BaseCache instance;
	/**
	 * 
	 * @方法说明： 缓存单例
	 * @return
	 * @修改说明：
	 */
	public static BaseCache getCache() {
		if (instance == null) {
			return getInstance();
		}
		return instance;
	}
	
	private synchronized static BaseCache getInstance() {

		if (instance == null) {
			instance = new BaseCache(CachecodeUtil.REFRESHPERIOD,CachecodeUtil.KEYPREFIX);
		}
		return instance;
	}
	//私有构造
	private MBaseCache(){
		
	}
}
