package app.util.message.conversion.validate;

import java.util.List;

import app.util.message.conversion.mapping.entity.MappingEntity;
import app.util.ruleFiter.entity.ValidateLog;


/**
 * 报文验证的桥接方法。
 * 单向验证，输入报文集合，返回验证报文日志文件
 * 获得的报文集合格式为：List<List<String>>，获取报文需要传入报文文件地址filePath
 * 验证时传入的参数为：校验配置：Map<String,ValidateParm> rulesMap，报文数据：Map<String,String> dataMap
 * rulesMap 可以通过输入ruleId获取
 * dataMap需要将报文集合和字段进行映射
 * 验证后返回：每一个dataMap返回一个ValidateLog集合
 * 
 * 转换办法：
 * 1.将报文集合与映射配置结合成为报文数据格式dataMap格式
 * 2.将dataMap进行校验，并对返回的validate进行处理
 *
 */
public interface MessageValidate {
	/**
	 * @param ruleId 通过规则ID获取对应的校验RuleMap，
	 * @param entityList 应为包含赋值以后的MappingColumn集合
	 * @return
	 */
	ValidateLog validateMessage(String ruleId,List<MappingEntity> entityList);
}
