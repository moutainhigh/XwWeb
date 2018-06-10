package app.util.ruleFiter.fiter.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.util.message.conversion.uitl.MessageUtil;
import app.util.ruleFiter.clientPort.ValidateRuleInterface;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.ruleFiter.type.ResultType;

public class FiterEngineImpl implements FiterEngineInterface {
	
	private ValidateRuleInterface validateRuleInterface;

	@Override
	public Map<String, ValidateParm> getRuleByIdDirectly(String ruleId) {
		try {
			return validateRuleInterface.getRuleByIdDirectly(ruleId);
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public Map<String,ValidateParm> getRuleByIdFormCache(String ruleId) throws IOException{
		try {
			return validateRuleInterface.getRuleByIdFromCache(ruleId);
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public List<ValidateLog> validateDataMap( Map<String, ValidateParm> rulesMap, Map<String, String> dataMap,boolean returnAtOnce) {
		//对报文数据进行校验
		List<ValidateLog> logList = new ArrayList<ValidateLog>();
		for(String rulekey:rulesMap.keySet()){
			if(MessageUtil.containsIgnoreCase(dataMap, rulekey)){
					logList.addAll(ValidateLog.validateService(rulekey,MessageUtil.getIgnoreCase(dataMap, rulekey),rulesMap.get(rulekey)));
				if(returnAtOnce && logList.size() > 0) break;
			}else{
				logList.add(new ValidateLog(ResultType.ERROR,"所发送报文中没有包含"+rulekey+"字段"));
			}
		}
		return logList;
	}

	@Override
	public List<ValidateLog> validateDataMap(String ruleId, Map<String, String> dataMap, boolean returnAtOnce) throws IOException {
//		Map<String,ValidateParm> rulesMap = validateRuleInterface.getRuleByIdDirectly(ruleId);
		Map<String,ValidateParm> rulesMap = validateRuleInterface.getRuleByIdFromCache(ruleId);
		List<ValidateLog> logList = new ArrayList<ValidateLog>();
		if(rulesMap==null){
			logList.add(new ValidateLog(ResultType.ERROR,"没有查询到对应的校验规则：ruleId="+ruleId));
		}else{
			logList = validateDataMap(rulesMap, dataMap, returnAtOnce);
		}
		return logList;
	}
	
	@Override
	public ValidateLog createValidateLog(String ruleId,Map<String,String> dataMap) throws IOException{
		ValidateLog log =  new ValidateLog();
		log.setResultlogList(validateDataMap(ruleId,dataMap,false));
		log.showValidateResult(false);
		return log;
	}
	
//	public List<ValidateLog> createValidateLog(String ruleId,List<?> objectList,boolean returnAtOnce) throws Exception{
//		List<ValidateLog> logList = new ArrayList<ValidateLog>();
//		ValidateLog log =  new ValidateLog();
//		Map<String, ValidateParm> ruleMap = getRuleByIdDirectly(ruleId);
//		for(Object cls :objectList){
//			log =  new ValidateLog();
//			log.setResultlogList(validateDataMap(ruleMap,getFieldVlaue(cls),false));
//			log.showValidateResult(false);
//			logList.add(log);
//		}
//		return logList;
//	}
	
	public ValidateLog createValidateLog(String ruleId,Object obj,boolean returnAtOnce) throws Exception{
		ValidateLog log =  new ValidateLog();
		log.setResultlogList(validateDataMap(ruleId,getFieldVlaue(obj),returnAtOnce));
		log.showValidateResult(true);
		return log;
	}
	
	public ValidateLog createValidateLog( Map<String, ValidateParm> rulesMap,Object obj,boolean returnAtOnce) throws Exception{
		ValidateLog log =  new ValidateLog();
		log.setResultlogList(validateDataMap(rulesMap, getFieldVlaue(obj),returnAtOnce));
		log.showValidateResult(true);
		return log;
	}
	
	
	/**
	 * 项目经理要求加入对类型的转换
	 * 转换过程中主要对值进行string转换，如果该属性为某个class类，则可能得不到正确结果。
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getFieldVlaue(Object obj) throws Exception {  
        Map<String, String> mapValue = new HashMap<String, String>();  
        Class<?> cls = obj.getClass();  
        Field[] fields = cls.getDeclaredFields();  
        for (Field field : fields) {  
            String name = field.getName();  
            String strGet;
            if(Character.isUpperCase(name.charAt(0)) && Character.isUpperCase(name.charAt(1))){
            	strGet = "get" + name.substring(0, 2).toUpperCase() + name.substring(2, name.length());  
            }else if(Character.isUpperCase(name.charAt(1)) && Character.isLowerCase(name.charAt(0)) ){
            	strGet = "get" + name.substring(0, 1) + name.substring(1, 2).toUpperCase() + name.substring(2, name.length());  
            }else{
            	strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());  
            }
            Method methodGet = cls.getDeclaredMethod(strGet);  
            Object object = methodGet.invoke(obj);  
            if(object == null)  mapValue.put(name, "");
            else{
            	if(isBaseNumberType(field)){
                	java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                	nf.setGroupingUsed(false);
                	mapValue.put(name, nf.format(object));
                }else{
                    mapValue.put(name, object.toString());  
                }
            }
        }  
        return mapValue;  
    }  
	
	private boolean isBaseNumberType(Field field){
		Type type = field.getType();
		String[] baseNumberArrays = {"int","class java.lang.Integer","double","class java.lang.Double","float","class java.lang.Float","long","class java.lang.Long"};
		 for(String s: baseNumberArrays){
		        if(s.equals(type.toString()))
		            return true;
		    }
		    return false;
	}

	public boolean doValidateFiter(String ruleId,Map<String,String> dataMap) throws IOException{
		return createValidateLog(ruleId,dataMap).getErrorList().size() == 0;
	}

	public void setValidateRuleInterface(ValidateRuleInterface validateRuleInterface) {
		this.validateRuleInterface = validateRuleInterface;
	}
	
}
