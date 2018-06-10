package app.oscache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import app.base.SourceTemplate;
import app.creditapp.acc.option.bo.AcComHolidayBo;
import app.creditapp.bo.SysRoleButtonBO;
import app.creditapp.entity.CacheBase;
import app.creditapp.entity.SysGlobal;
import app.creditapp.entity.SysRoleButton;
import app.creditapp.sys.bo.SysGlobalBo;
import app.creditapp.sys.bo.SysPathBo;
import app.creditapp.sys.entity.SysPath;
import app.creditapp.sysConfig.dao.SysRequireTableDao;
import app.creditapp.sysConfig.entity.SysRequireTable;
import app.util.DateUtil;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * @对象说明 缓存对象类
 * @修改说明
 */
public class BaseCache extends GeneralCacheAdministrator {
	private static final long serialVersionUID = -4397192926052141162L;
	// 过期时间(单位为秒);
	private int refreshPeriod; // 60*60*5
	// 关键字前缀字符;
	private String keyPrefix; // CMSII
	
	private Map<String,String> uploadParms;
	
	public BaseCache(int refreshPeriod, String keyPrefix) {
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	// 添加被缓存的对象--分组
	public void put(String key, Object value, String[] groups) {
		this.putInCache(this.keyPrefix + "_" + key, value, groups);
	}

	// 添加被缓存的对象;
	public void put(String key, Object value) {
		this.putInCache(this.keyPrefix + "_" + key, value,
				new String[] { "group_other" });
	}

	// 删除被缓存的对象--按key删除
	public void removeByKey(String key) {
		this.flushEntry(this.keyPrefix + "_" + key);
	}

	// 删除所有被缓存的对象;
	public void removeAll(Date date) {
		this.flushAll(date);
	}

	public void removeAll() {
		this.flushAll();
	}

	// 删除被缓存的对象--按组删除
	public void removeByGroup(String group) {
		this.flushGroup(group);
	}
	
	/**
	 * 清除所有按钮缓存,重新加载按钮缓存
	 * 
	 * @return
	 * @throws Exception
	 */
	public void reloadButton() throws Exception {
		this.removeByGroup(this.getGroups(5)[0]);
		this.initButton();
		this.initSecurity();
	}
	/**
	 * 清除安全审计缓存,重新加载
	 * 
	 * @return
	 * @throws Exception
	 */
	public void reloadSecurity() throws Exception {
		this.removeByKey("useSecurity");
		this.removeByKey("comm_flag");
		this.initSecurity();
	}

	/**
	 * 
	 * @方法说明： 取缓存实体
	 * @param key
	 *            关键字
	 * @param select
	 *            CachecodeUtil.CACHE_DATADICT 数据字典 CachecodeUtil.CACHE_ORG 处置机构
	 * @return ArrayList<CacheBean>
	 * @修改说明：
	 */ 
	public Object getBeanCache(String key, int select) {
		String _key = this.keyPrefix + "_" + key;
		Object result = null;
		try {
			result = getFromCache(_key, this.refreshPeriod);
		} catch (NeedsRefreshException e) {// 如果不存在，查询数据库
			boolean updated = false;
			try {
				result = new CodeUtils(key).getBeanCache(select);
				this.put(key, result, getGroups(select));
				updated = true;
			} catch (Exception exception) {
				this.removeByKey(key);
			} finally {
				if (!updated) {// 防止找不到对象缓存，一直等待下去。
					this.cancelUpdate(_key);
				}
			}
		}
		return result;
	}
	
	
	public Map<String,String> getUploadParm() {
		uploadParms = new HashMap<String,String>();
		List<CacheBase> caches =(List<CacheBase>) this.getBeanCache("UPLOAD_PARM", CachecodeUtil.CACHE_DATADICT);
		for (CacheBase base: caches) {
			uploadParms.put(base.getOpt_code(), base.getOpt_name());
		}
		return uploadParms;
	}
	
	
	public Object getBeanCacheNew(String key, int select) {
		String _key = this.keyPrefix + "_" + key;
		Object result = null;
		boolean updated = false;
		try {
			result = new CodeUtils(key).getBeanCache(select);
			this.put(key, result, getGroups(select));
			updated = true;
		} catch (Exception exception) {
			this.removeByKey(key);
		} finally {
			if (!updated) {// 防止找不到对象缓存，一直等待下去。
				this.cancelUpdate(_key);
			}
		}
		
		return result;
	}
	
	/**
	 * 功能描述：查询权限按钮缓存中的值
	 * @param key
	 * @param roleOrUser 系统权限模式 user = 角色 role = 权限
	 * @return
	 */
	public boolean getBeanCacheForButton(String key, String orguser) {
		String _key = this.keyPrefix + "_" + key;
		Object result = "";
		boolean flag = false;
		try {
			result = getFromCache(_key, -1);
			if ("0".equals(result)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception ee) {
			flag = false;
			this.cancelUpdate(_key);
		}
		return flag;
	}
	
	
	public void initSystemStatus() throws NeedsRefreshException {
		SysGlobalBo sysGlobalBo = (SysGlobalBo) SourceTemplate.getContext().getBean("sysGlobalBo");
		SysGlobal SysGlobal = sysGlobalBo.getSysGlobal();
		this.put(CachecodeUtil.SYS_GLOBAL_STATUS_STR, SysGlobal.getSys_sts(), getGroups(CachecodeUtil.SYS_GLOBAL_STATUS));
	}
	
     // 系统重新启动 节假日管理
//	public void initHoliday() throws NeedsRefreshException {		
//		AcComHolidayBo acComHolidayBo = (AcComHolidayBo)SourceTemplate.getContext().getBean("acComHolidayBo");
//		SysGlobal sg=DateUtil.getSysGlobal();
//		this.put("holidayJson", acComHolidayBo.findHolidayBySysDate(sg.getSys_date().substring(0, 4)));//自定义节假日
//	}
	
	public int getRefreshPeriod() {
		return refreshPeriod;
	}

	public void setRefreshPeriod(int refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public Map<String, String> getUploadParms() {
		return uploadParms;
	}

	public void setUploadParms(Map<String, String> uploadParms) {
		this.uploadParms = uploadParms;
	}

	public void initApprover() throws NeedsRefreshException {
		this.put(CachecodeUtil.APPROVER_PAS_FST_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_PAS_FST_CHECK));
		this.put(CachecodeUtil.APPROVER_PAS_DUE_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_PAS_DUE_CHECK));
		this.put(CachecodeUtil.APPROVER_COM_FST_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_COM_FST_CHECK));
		this.put(CachecodeUtil.APPROVER_COM_DUE_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_COM_DUE_CHECK));
		this.put(CachecodeUtil.APPROVER_COM_SUP_FST_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_COM_SUP_FST_CHECK));
		this.put(CachecodeUtil.APPROVER_COM_SUP_DUE_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_COM_SUP_DUE_CHECK));
		this.put(CachecodeUtil.APPROVER_EXT_FST_CHECK_STR, "0", getGroups(CachecodeUtil.APPROVER_EXT_FST_CHECK));
	}
	
	/**
	 * 功能描述：初始化权限按钮缓存
	 * @throws NeedsRefreshException 
	 */
	public void initButton() throws NeedsRefreshException {
		SysRoleButtonBO sysRoleButtonBO = (SysRoleButtonBO) SourceTemplate.getContext().getBean("sysRoleButtonBO");
		List<SysRoleButton> list = sysRoleButtonBO.getAll();
		for (SysRoleButton button : list) {
			String key = button.getMenu_no() + "@" + button.getButton_no() + "@" + button.getRole_no();
			this.put(key, "0", getGroups(5));
		}
	}
	/**
	 * 功能描述：初始化安全审计缓存
	 * @throws NeedsRefreshException 
	 */
	public void initSecurity() throws NeedsRefreshException {
//		SecurityAuditBo securityAuditBo = (SecurityAuditBo) SourceTemplate.getContext().getBean("securityAuditBo");
//		List<SecurityAudit> auditList = securityAuditBo.findAuditListByCode("PASSWORD");
//		for(SecurityAudit audit:auditList){
//			if(audit.getItemNo().equals("4")){
//				this.put("useSecurity", audit.getIsUse());//用户操作日记
//			}
//			if(audit.getItemNo().equals("5")){
//				this.put("dataSourceLog", audit.getIsUse());//数据库操作日记
//			}
//			if(audit.getItemNo().equals("6")){
//				this.put("loginLog", audit.getIsUse());//登陆日记
//			}
//		}
//		List<SecurityAudit> auditList_temp = securityAuditBo.findAuditListByCode("COMM_FLAG");
//		for(SecurityAudit audit:auditList_temp){
//			if(audit.getItemNo().equals("7")){
//				this.put("comm_flag", audit.getIsUse());//系统通信接口是否启用
//			}
//		}
//		ResourceBundle prop = ResourceBundle.getBundle("path");
//		String pushMessageServerPath = prop.getString("pushMessageServerPath").trim();
//		String pushMessageRequestPath = prop.getString("pushMessageRequestPath").trim();
//		String websocketEndpointPath = prop.getString("websocketEndpointPath").trim();
//		// 增加推送服务器消息的缓存
//		this.put("pushMessageServerPath", pushMessageServerPath);//推送服务器地址
//		this.put("pushMessageRequestPath", pushMessageRequestPath);//推送请求地址
//		this.put("websocketEndpointPath", websocketEndpointPath);//endpoint服务器地址地址
		
		//取出导入、下载模板路径
		Map<String,String> sysPathMap = new HashMap<String,String>();
		SysPathBo sysPathBo = (SysPathBo) SourceTemplate.getContext().getBean("sysPathBo");
		List<SysPath> sysPathList = sysPathBo.findList();//查询所有记录
		for (SysPath sysPath: sysPathList) {
			sysPathMap.put(sysPath.getPathId(),sysPath.getPathDir());
		}
		this.put(CachecodeUtil.SYS_PATH_STR, sysPathMap);
	}
	

	
	/**
	 * 功能描述：查询该表是否记录日志(缓存)
	 * @param key
	 * @return
	 */
	public boolean getBeanCacheForLogTable(String key) {
		String _key = this.keyPrefix + "_" + key;
		Object result = null;
		boolean flag = false;
		try {
			result = getFromCache(_key, this.refreshPeriod);
			if("0".equals(result)){
				flag = true;
			}
		} catch (NeedsRefreshException e) {
			boolean updated = false;
			try {
				SysRequireTableDao requireLogTableDao = (SysRequireTableDao) SourceTemplate
				.getContext().getBean("sysRequireTableDao");
				List<SysRequireTable> list = requireLogTableDao.getAll();
				for (SysRequireTable rlt : list) {
					if(key.equals(rlt.getTableCode())){
						this.put(rlt.getTableCode(), "0", new String[]{"0"});
						flag = true;
						updated = true;
						System.out.println("LOG表:" + rlt.getTableCode());
					}
				}
			} catch (Exception exception) {
				this.removeByKey(key);
			} finally {
				if (!updated) {// 防止找不到对象缓存，一直等待下去。
					this.cancelUpdate(_key);
				}
			}
		}
		return flag;
	}
	
	/**
	 * 功能描述：初始化REQUIRE_LOG_TABLE缓存
	 * @throws NeedsRefreshException 
	 */
	public void initRequireLogTable() throws NeedsRefreshException {
		SysRequireTableDao requireLogTableDao = (SysRequireTableDao) SourceTemplate
				.getContext().getBean("sysRequireTableDao");
		List<SysRequireTable> list = requireLogTableDao.getAll();
		for (SysRequireTable rlt : list) {
			this.put(rlt.getTableCode(), "0", new String[]{"0"});
		}
	}
	
	// 默认取数据字典
	public Object getCache(String key) {
		// 取数据字典
//		return getBeanCache(key, CachecodeUtil.CACHE_DATADICT);
		return getBeanCacheNew(key, CachecodeUtil.CACHE_DATADICT);
	}
	// 默认取数据字典  
	public Object getCacheFastMenu(String key) {
		// 取数据字典
		return getBeanCache(key, CachecodeUtil.FAST_MENU);
	}
	// 默认取数据字典
	public void initCache() {
		// 取数据字典
		CodeUtils util = new CodeUtils();
		for (String key : util.getAllKey()) {
			this.getCache(key);
		}
		util = null;
	}
	
	public String[] getGroups(int select) {
		String value = "";
		if (1 == select) {
			value = "group_datadict";
		} else if (2 == select) {
			value = "group_org";
		} else if (3 == select) {
			value = "group_cmsorg";
		} else if (4 == select) {
			value = "group_dhcorg";
		} else if (5 == select){
			value = "group_button";
		}else if (6 == select){
			value = "group_fastmenu";
		}else if (7 == select){
			value = "group_approvalOpSeq";
		} 
		else {
			value = "group_other";
		}
		return new String[] { value };
	}

}
