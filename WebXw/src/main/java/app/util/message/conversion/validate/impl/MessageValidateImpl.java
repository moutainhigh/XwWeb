package app.util.message.conversion.validate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.util.message.conversion.mapping.entity.MappingColumn;
import app.util.message.conversion.mapping.entity.MappingEntity;
import app.util.message.conversion.validate.MessageValidate;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.ruleFiter.type.ResultType;

public class MessageValidateImpl implements MessageValidate{
	
	private FiterEngineInterface fiterEngineInterface;
	
	public MessageValidateImpl() {
		super();
	}

	public MessageValidateImpl(FiterEngineInterface fiterEngineInterface) {
		super();
		this.fiterEngineInterface = fiterEngineInterface;
	}

	@Override
	public ValidateLog validateMessage(String ruleId, List<MappingEntity> entityList) {
		Map<String,ValidateParm> rulesMap = fiterEngineInterface.getRuleByIdDirectly(ruleId);
		List<ValidateLog> logList = new ArrayList<ValidateLog>();
		if(rulesMap==null){
			logList.add(new ValidateLog(ResultType.ERROR,"没有查询到对应的校验规则：ruleId="+ruleId));
		}else{
			for(MappingEntity entity:entityList){
				logList.addAll( fiterEngineInterface.validateDataMap(rulesMap, createDataMap(entity.getColumnList()), false));
			}
		}
		ValidateLog log =  new ValidateLog();
		log.setResultlogList(logList);
		log.showValidateResult(false);
		
		return log;
	}
	
	private Map<String,String> createDataMap(List<MappingColumn> columnList){
		Map<String,String> dataMap = new HashMap<String,String>();
		for(MappingColumn mc:columnList){
			dataMap.put(mc.getColumnName(), mc.getDataValue());
		}
		return dataMap;
	}

	public FiterEngineInterface getFiterEngineInterface() {
		return fiterEngineInterface;
	}

	public void setFiterEngineInterface(FiterEngineInterface fiterEngineInterface) {
		this.fiterEngineInterface = fiterEngineInterface;
	}
}
