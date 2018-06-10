package app.oscache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import app.creditapp.entity.CacheBase;

/**
 * @对象说明 数据字典缓存处理
 * 表eadis_sys_datadict code,name,status 对应 name0,name1,name2
 * 表eadis_sys_datadictrel status对应name3
 * @修改说明
 */
public class Datadict {

	private List<CacheBase> datacache = new ArrayList<CacheBase>();// 缓存体

	public Datadict(ArrayList<CacheBase> list) {
		if (list != null) {
			this.datacache = list;
		}
	}

	@SuppressWarnings("unchecked")
	public Datadict(String ddname) {
		if (StringUtils.isNotBlank(ddname)) {
			List<CacheBase> list = (List<CacheBase>) MBaseCache
					.getCache().getBeanCache(ddname,CachecodeUtil.CACHE_DATADICT);
			if (list != null) {
				this.datacache = list;
			}
		}
	}
	
	public String getDatadictByCode(String code) {
		if (code == null) {
			return null;
		}
		String mess="";
		for (int i = 0; i < this.datacache.size(); i++) {
			if (code.equals(this.datacache.get(i).getOpt_code())) {
				mess=this.datacache.get(i).getOpt_name();
				break;
			}
		}
		return mess;
	}
	/**
	 * 
	 * @方法说明： 按照level取数据
	 * @param level 级别 如果==null则查询最低级别
	 * @return
	 * @修改说明：
	 */
	public List<CacheBase> getDatadictByLevel() {		
		return this.datacache;
	}

}
