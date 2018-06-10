package app.oscache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.SourceTemplate;
import app.creditapp.bo.CacheService;
import app.creditapp.corp.bo.CorpParmBo;
import app.creditapp.corp.entity.CorpParm;
import app.creditapp.proj.bo.ProjParmBo;
import app.creditapp.sys.bo.SysPathBo;
import app.creditapp.sys.entity.SysPath;

/**
 * 
 * @对象说明 数据转换缓存查询类
 * @修改说明
 */
public class CodeUtils {
	
	String key;

	
	public CodeUtils() {
	}
	
	CodeUtils(String flag) {
		this.key = flag;
	}


	/**
	 * @方法说明： 取缓存，将List<object[]>转换为List<bean>
	 * @return
	 * @修改说明：
	 */
	public Object getBeanCache(int select){
		return this.selectCache(select);
	}
	
	private CacheService cacheService;
	
	private List<Object> getDatadict() {
		/*没在action中调用，需要手动的注入该类 add by loudw*/
		cacheService = SourceTemplate.getSpringContextInstance().getBean("cacheService",CacheService.class);
		List<Object> result = cacheService.findByCondition(this.key);
		return result;
	}
	
	public  List<String> getAllKey(){
		cacheService = SourceTemplate.getSpringContextInstance().getBean("cacheService",CacheService.class);
		List<String> result = cacheService.findKeyByCondition();
		return result;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jul 5, 2016
	 * @方法说明 取项目参数缓存
	 * @返回参数 
	 */
	public List<Object> getProjParm() {
//		Map<String,Float> rgAppRageMap = new HashMap<String,Float>();
		ProjParmBo projParmBo = (ProjParmBo) SourceTemplate.getContext().getBean("projParmBo");
		List<Object> projParmList = projParmBo.findListBySts("1");
//		for (ProjParm projParm: projParmList) {
//			// rgAppRageMap.put(projParm.getProjNo(),projParm.getRgAppRate());
//		}
		return projParmList;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jul 5, 2016
	 * @方法说明 取合作机构参数缓存
	 * @返回参数 
	 */
	public List<Object> getCorpParm() {
//		Map<String,Float> rgAppRageMap = new HashMap<String,Float>();
		CorpParmBo corpParmBo = (CorpParmBo) SourceTemplate.getContext().getBean("corpParmBo");
		List<Object> corpParmList = corpParmBo.findListBySts(null);
//		for (ProjParm projParm: projParmList) {
//			// rgAppRageMap.put(projParm.getProjNo(),projParm.getRgAppRate());
//		}
		return corpParmList;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jul 5, 2016
	 * @方法说明 取合作机构参数缓存
	 * @返回参数 
	 */
	public Map<String,String> getSysPath() {
		//取出导入、下载模板路径
				Map<String,String> sysPathMap = new HashMap<String,String>();
				SysPathBo sysPathBo = (SysPathBo) SourceTemplate.getContext().getBean("sysPathBo");
				List<SysPath> sysPathList = sysPathBo.findList();//查询所有记录
				for (SysPath sysPath: sysPathList) {
					sysPathMap.put(sysPath.getPathId(),sysPath.getPathDir());
				}
//				this.put(CachecodeUtil.SYS_PATH_STR, sysPathMap);
		return sysPathMap;
	}
	
	
	/**
	 * @方法说明： 缓存选择
	 * @param select
	 * @return
	 * @修改说明：
	 */
	private List<Object> selectCache(int select){
		switch (select){
			//数据字典
		    case CachecodeUtil.CACHE_DATADICT:return this.getDatadict();
		    //信托项目参数配置表
		    case CachecodeUtil.PROJ_PARM:return this.getProjParm();
		    //合作机构参数配置表
		    case CachecodeUtil.CORP_PARM:return this.getCorpParm();
		    //系统参数配置表
//		    case CachecodeUtil.SYS_PATH:return this.getSysPath();
		    // 节假日加载
			//处置机构
//			case 2:return this.getOrgList();
			//cms机构
//			case 3:return this.getCmsOrgList();
			//核算机构
//			case 4:return this.getDhcOrgList();
			default: return null;
		}
	}
}
