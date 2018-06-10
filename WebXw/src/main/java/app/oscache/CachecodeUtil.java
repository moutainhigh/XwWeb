package app.oscache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @对象说明 常量定义和缓存获取方法
 * @修改说明
 */
public class CachecodeUtil {
	//缓存常量
	public final static int REFRESHPERIOD = -1; // 缓存过期时间
	public final static String KEYPREFIX = "CMSII"; //缓存在内存中命名前缀
	public final static String MFSPREFIX = "00003"; //缓存在内存中命名前缀
	
//	public static Map<String, DictNode> cacheMap = new HashMap<String, DictNode>();
	
	//数据常量
	public final static int CACHE_DATADICT=1;	//数据字典
	public final static int CACHE_MENU=2;		//菜单
	public final static int CACHE_CMSORG=3;		//CMS机构
	public final static int CACHE_DHCORG=4;		//核算机构
	public final static int FAST_MENU=8;		//快捷菜单
	public final static int APPROVER_PAS_FST_CHECK = 10;	//乘用车-分配审批人(初审员)
	public final static int APPROVER_PAS_DUE_CHECK = 11;	//乘用车-分配审批人(放款初审)
	public final static int APPROVER_COM_FST_CHECK = 12;	//商用车-分配审批人(初审员)
	public final static int APPROVER_COM_DUE_CHECK = 13;	//商用车-分配审批人(放款初审)
	public final static int APPROVER_COM_SUP_FST_CHECK = 14;	//商用车(供货商模式)-分配审批人(初审员)
	public final static int APPROVER_COM_SUP_DUE_CHECK = 15;	//商用车(供货商模式)-分配审批人(放款初审)
	public final static int APPROVER_EXT_FST_CHECK = 16;	//展期申请-分配审批人(初审员)
	public final static int MENUS = 208;
	public final static int SECURITY = 9;		//安全审计
	public final static int SYS_GLOBAL_STATUS = 999;	//系统状态
	public final static int SYS_GLOBAL_DOC_SIZE = 998;	//文件大小限制
	public final static int PROJ_PARM = 997;		// 项目参数配置
	public final static int CORP_PARM = 996;		// 机构参数配置
	public final static int HOLIDAY_JSON = 995;		// 节假日JSON
	public final static int SYS_PATH = 994;		// 系统路径参数配置
	public final static String PROJ_PARM_STR = "PROJ_PARM_STR";		// 项目配置参数表
	public final static String CORP_PARM_STR = "CORP_PARM_STR";		// 机构配置参数表
	public final static String SYS_PATH_STR = "SYS_PATH_STR";		// 系统路径参数配置
	public final static String FAST_MENU_STR = "FASTMENU";
	public final static String SYS_GLOBAL_DOC_SIZE_STR = "SYS_GLOBAL_DOC_SIZE_STR";
	public final static String SYS_GLOBAL_STATUS_STR = "SYS_GLOBAL_STATUS_STR";
	public final static String APPROVER_PAS_FST_CHECK_STR = "APPROVER_PAS_FST_CHECK";
	public final static String APPROVER_PAS_DUE_CHECK_STR = "APPROVER_PAS_DUE_CHECK";
	public final static String APPROVER_COM_FST_CHECK_STR = "APPROVER_COM_FST_CHECK";
	public final static String APPROVER_COM_DUE_CHECK_STR = "APPROVER_COM_DUE_CHECK";
	public final static String APPROVER_COM_SUP_FST_CHECK_STR = "APPROVER_COM_SUP_FST_CHECK";
	public final static String APPROVER_COM_SUP_DUE_CHECK_STR = "APPROVER_COM_SUP_DUE_CHECK";
	public final static String APPROVER_EXT_FST_CHECK_STR = "APPROVER_EXT_FST_CHECK";
	
	public final static String CRP_CRP_STS0 = "0";   //征信没有查询
	public final static String CRP_CRP_STS1 = "1";   //征信查询成功
	public final static String CRP_CRP_STS2 = "2";   //征信查询失败
	
	public final static String QUERY_TYPE1 = "1";   //征信本地
	public final static String QUERY_TYPE2 = "2";   //征信人行
	
	public static String getKey(int id){
		String key = "";
//		DictNode node = null;
//		Iterator<String> it=cacheMap.keySet().iterator();
//		while(it.hasNext()){
//			node = cacheMap.get(it.next());
//			if(node.getId() == id){
//				key = node.getKeyName();
//				break;
//			}
//		}
		return key.toLowerCase();
	}
	
}
