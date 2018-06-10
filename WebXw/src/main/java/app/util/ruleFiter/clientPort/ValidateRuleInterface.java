package app.util.ruleFiter.clientPort;

import java.io.IOException;
import java.util.Map;

import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.entity.ValidateRuleEntity;

/**
 * 分别从以下方式获取校验规则：
 * xml-已完成
 * excel
 * txt
 * 数据库
 *
 */
public interface ValidateRuleInterface {
	
	
	/**
	 * 不通过缓存直接获取，可能需要读取文件或者查询数据库。
	 * @param ruleId 规则ID，通过改编号获取对应的校验规则
	 * @return 映射map中，string键为需要校验的字段，ValidateParm为校验格式
	 * @throws IOException 
	 */
	public Map<String,ValidateParm> getRuleByIdDirectly(String ruleId) throws IOException;
	
	/**
	 * 从缓存中通过ruleId获取规则.如果校验规则数据发生变化，则需要刷新或重新加载缓存
	 * @param ruleId 规则ID，通过改编号获取对应的校验规则
	 * @return 映射map中，string键为需要校验的字段，ValidateParm为校验格式
	 * @throws IOException 
	 */
	public Map<String,ValidateParm> getRuleByIdFromCache(String ruleId) throws IOException;
	
	/**
	 * 可能需要读取文件或者查询数据库，通过该方法初始化缓存。缓存初始化结束后可以使用getRuleByIdFromCache方法获取对应的校验规则
	 * <br>注：可以不需要获取返回值
	 * @return ValidateRuleEntity类中封装了校验规则的一些说明和基本属性。同样通过ruleId获取对应的规则信息
	 * @throws IOException 
	 */
	public Map<String,ValidateRuleEntity> initValidateRuleEntityForCache() throws IOException;
}
