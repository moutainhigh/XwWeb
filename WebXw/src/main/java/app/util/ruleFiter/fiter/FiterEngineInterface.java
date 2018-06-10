package app.util.ruleFiter.fiter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.entity.ValidateParm;

public interface FiterEngineInterface {
	/**
	 * 返回校验日志报告列表
	 * @param rulesMap 进行校验的规则Map
	 * @param dataMap  需要校验的数据集映射
	 * @param returnAtOnce 是否立即返回
	 * @return
	 */
	List<ValidateLog> validateDataMap(Map<String,ValidateParm> rulesMap,Map<String,String> dataMap,boolean returnAtOnce);

	/**
	 * 返回校验日志报告列表
	 * @param rulesMap 进行校验的规则Map
	 * @param dataMap  需要校验的数据集映射
	 * @param returnAtOnce 是否立即返回
	 * @return
	 * @throws IOException 
	 */
	List<ValidateLog> validateDataMap(String ruleId,Map<String,String> dataMap,boolean returnAtOnce) throws IOException;

	/**
	 * 仅判断是否校验成功。仅存在警告信息一样返回成功。
	 * @return 返回值为真，则校验成功，数据通过，返回值为假，则校验失败。
	 * @throws IOException 
	 */
	boolean doValidateFiter(String ruleId,Map<String,String> dataMap) throws IOException;
	
	/**
	 * 返回校验结果日志，日志中包含errorList以及warningList数据。
	 * @param ruleId 校验规则的标识ID
	 * @param dataMap需要校验的数值集合配对
	 * @return
	 * @throws IOException 
	 */
	ValidateLog createValidateLog(String ruleId,Map<String,String> dataMap) throws IOException;
	
	/**
	 * 不通过缓存直接获取，可能需要读取文件或者查询数据库。
	 * @param ruleId 规则ID，通过改编号获取对应的校验规则
	 * @return 映射map中，string键为需要校验的字段，ValidateParm为校验格式
	 * @throws IOException 
	 */
	Map<String,ValidateParm> getRuleByIdDirectly(String ruleId);
	
	/**
	 * 可能需要读取文件或者查询数据库，通过该方法初始化缓存。缓存初始化结束后可以使用getRuleByIdFormCache方法获取对应的校验规则
	 * <br>注：可以不需要获取返回值
	 * @throws IOException 
	 */
	public Map<String,ValidateParm> getRuleByIdFormCache(String ruleId) throws IOException;
	
	/**
	 * 根据对象转化进行校验
	 * 传递根据对象进行校验时，均根据缓存进行读取
	 * @param ruleId
	 * @param obj
	 * @param returnAtOnce
	 * @return
	 * @throws Exception
	 */
	ValidateLog createValidateLog(String ruleId,Object obj,boolean returnAtOnce) throws Exception;
	ValidateLog createValidateLog( Map<String, ValidateParm> rulesMap,Object obj,boolean returnAtOnce) throws Exception;
}
