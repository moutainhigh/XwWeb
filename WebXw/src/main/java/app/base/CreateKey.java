package app.base ;

import org.apache.struts2.ServletActionContext;

import app.creditapp.dao.CreateKeyDao;
import app.util.User;
/**
 * 生成主键类
 */
public class CreateKey {
	
	private CreateKeyDao createKeyDao;
	
	public CreateKeyDao getCreateKeyDao() {
		return createKeyDao;
	}

	public void setCreateKeyDao(CreateKeyDao createKeyDao) {
		this.createKeyDao = createKeyDao;
	}
	
	/**
	 * @方法说明：获取预进件批次编号
	 * @返回参数 String
	 */
	public synchronized static String getPreBatchNo() throws ServiceException{
		String batchNo = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			batchNo = createKeyDao.getPreBatchNo();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return batchNo;
	}
	/**
	 * @方法说明：账户维护流水号UUID_SEQ
	 * @返回参数 String
	 */
	public synchronized static String getUUID() throws ServiceException{
		String UUID = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			UUID = createKeyDao.getUUID();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return UUID;
	}

	/**
	 * @方法说明：获取批次编号
	 * @返回参数 String
	 */
	public synchronized static String getBatchNo() throws ServiceException{
		String batchNo = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			batchNo = createKeyDao.getBatchNo();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return batchNo;
	}
	
	/**
	 * @方法说明：获取批次编号
	 * @返回参数 String
	 */
	public synchronized static String getWsId() throws ServiceException{
		String wsId = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			wsId = createKeyDao.getWsId();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return wsId;
	}
	
	/**
	 * @方法说明：获取批次编号
	 * @返回参数 String
	 */
	public synchronized static String getQueBatchNo() throws ServiceException{
		String batchNo = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			batchNo = createKeyDao.getQueBatchNo();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return batchNo;
	}
	
	/**
	 * @方法说明：生成预进件业务申请编号
	 * @返回参数 String
	 */
	public synchronized static String getPreAppId() throws ServiceException{
		String appId = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			appId = createKeyDao.getPreAppId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appId;
	}
	
	/**
	 * @方法说明：获取正式进件批次编号
	 * @返回参数 String
	 */
	public synchronized static String getLnBatchNo() throws ServiceException{
		String batchNo = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			batchNo = createKeyDao.getLnBatchNo();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return batchNo;
	}
	
	/**
	 * @方法说明：获取正式进件业务编号
	 * @返回参数 String
	 */
	public synchronized static String getLnApplyId() throws ServiceException{
		String applyId = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			applyId = createKeyDao.getLnApplyId();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return applyId;
	}
	
	
	 /**
	  * @功能描述 获取合同号
	  * @parm
	  * @说明
	  * */
	public String getKey(){
		StringBuffer primaryKey = new StringBuffer();
		String key = "";
		String sys_date = User.getSys_date(ServletActionContext.getRequest());
		CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
		key = createKeyDao.getKeyByDate(sys_date);
		if( key == null || key.equals("") || key.length()==10){  // 长度为10的不对，测试数据
			primaryKey.append(sys_date).append("99000001");
		}else{
			Long key_val = Long.parseLong(key)+1;
			key = key_val.toString();
			primaryKey.append(key);
		}
		return primaryKey.toString();
	}
	
	 /**
	  * @功能描述 编号生成(公共方法)
	  * @parm tableName 表名,digit 位数（除日期）,id 编号字段名
	  * @说明
	  * */
	public static String getPublicKey(String tableName,int digit,String id){
		return getPublicKey(tableName, digit, id, null, null);
	}
	
	
	public static String getPublicKey(String tableName,int digit,String id,Integer startIndex,Integer endIndex){
		StringBuffer primaryKey = new StringBuffer();
		String startNum = "";//起始号（除日期）
		for(int i=0 ; i< (digit-1) ; i++){
			startNum += "0";
		}
		startNum = startNum + "1";
 		String key = "";
		String sys_date = User.getSys_date(ServletActionContext.getRequest());
		CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
		key = createKeyDao.getPublicKeyByDate(sys_date,tableName,id);
		if( key == null || key.equals("") ){
			primaryKey.append(sys_date).append(startNum);
		}else{
			if(startIndex==null)startIndex = 0;
			if(endIndex==null)endIndex = key.length();
			key = key.substring(startIndex, endIndex);
			Long key_val = Long.parseLong(key)+1;
			key = key_val.toString();
			primaryKey.append(key);
		}
		return primaryKey.toString();
	}
	
	public static String getPublicKey(String tableName,int digit,String id,Integer startIndex){
		return getPublicKey(tableName, digit, id, startIndex, null);
	}
	
	/**
	 * @方法说明：获取资产池编号
	 * @返回参数 String
	 */
	public synchronized static String getPoolId() throws ServiceException{
		String poolId = "";
		try {
			CreateKeyDao createKeyDao = (CreateKeyDao)SourceTemplate.getSpringContextInstance().getBean("createKeyDao");
			poolId = createKeyDao.getPoolId();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return poolId;
	}
	
}
