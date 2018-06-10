package app.util.message.conversion.transfer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import app.util.message.conversion.entity.TransferResult;


/**
 * 目前已经准备好的输入和输出：
 * 导入数据：
 * 输入-
 * MappingHandler返回没有value值的MappingEntity，需要mappingId
 * MessageHandler中获取报文有序集合数据List<List<String>>，需要filePath
 * 
 * 输出-
 * DataBaseHandler中返回操作成功状态，需要插入value值后的MappingEntity
 * 
 * 导出数据：
 * 输入-
 * DataBaseHandler中exportDataFromDb方法返回多个字段-值配对的有续集合List<Map<String, String>>，需要sql语句,或者对象集合
 * MappingHandler返回没有value值的MappingEntity，需要mappingId
 * 
 * 输出-
 * MessageHandler中写入文件的方法，需要处理后的报文结果集List<List<String>>，文件路径filePath
 * 
 * 其他：
 * 添加开关决定是否加入校验接口
 *
 */
public interface TransferHandler {
	
	/**
	 * 将报文转化为持久化数据
	 * @param mappingId
	 * @return
	 * @throws IOException
	 */
	boolean messageToData(String mappingId) throws IOException;
	boolean messageToData(String mappingId,String filePath) throws IOException;
	
	/**
	 * 带验证的报文-数据库数据转化，需要多传递一个参数：校验规则ID
	 * @param mappingId
	 * @param ruleId
	 * @return
	 * @throws IOException
	 */
	boolean messageToDataWithValidate(String mappingId, String ruleId) throws IOException;
	boolean messageToDataWithValidate(String mappingId, String ruleId,String filePth) throws IOException;
	
	/**
	 * 
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年7月12日
	 * @方法说明：
	 * @返回参数 boolean
	 */
	boolean messageToDataWithValidate(String[] mappingIdArgs, String[] ruleIdArgs) throws Exception;
	boolean messageToDataWithValidate(String[] mappingId, String[] ruleId,String filePth) throws Exception;
	
	
	/**
	 * 对于复杂情况下的数据转换操作。允许同时进行多个sheet页面的转换
	 * 1.传入的mapping数组应与ruleId的个数相同，且一一对应。数组中的第一个值也对应着要读取的文件中的第一个页面，类似于一个Excel文件中的第一个sheet页面
	 * 2.replaceDefaultMap为要替换的对应字段的值，其中key为字段名，value为替换后的值。注：替换为全表替换，即本次操作该字段都会被替换为value这一个值。
	 * @作者 DHCC-LIYABIN
	 * @日期 2016年7月29日
	 * @方法说明：
	 * @返回参数 List<TransferResult>
	 */
	List<TransferResult> messageToDataForComplex(String[] mappingId, String[] ruleId,String filePth) throws Exception;
	List<TransferResult> messageToDataForComplex(String[] mappingId, String[] ruleId,String filePth,Map<String,String> replaceDefaultMap) throws Exception;
	/**
	 * 将已持久化数据转化为报文，并写入文件中
	 * @param mappingId
	 * @param sql
	 * @return
	 * @throws IOException
	 */
	boolean dataToMessage(String mappingId,String sql) throws IOException;
	boolean dataToMessage(String mappingId,String sql,String filePth) throws IOException;
	
	
	/**
	 * 将实体类集合转化为报文，并写入文件中
	 * @param mappingId
	 * @param objList
	 * @return
	 * @throws Exception
	 */
	public boolean objectToMessage(String mappingId, List<?> objList) throws Exception;
	public boolean objectToMessage(String mappingId, List<?> objList,String filePth) throws Exception;
	
	
	/**
	 * 刷新或初始化报文映射配置文件
	 * @throws IOException
	 */
	void refreshMappingConfig() throws IOException;
	
	/**
	 * 设定是否向报文文件进行追加操作。如果为true，则在原有文件基础上进行追加写入，如果为false，则重新写入文件
	 * 默认为true;
	 * @param isAdd
	 */
	void isAddFileContent(boolean isAdd);
	
}
