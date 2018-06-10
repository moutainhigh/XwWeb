package app.util.ruleFiter.clientPort.impl;

import java.util.HashMap;
import java.util.Map;

import app.util.ruleFiter.clientPort.ValidateRuleInterface;
import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.entity.ValidateRuleEntity;
import app.util.ruleFiter.type.RegexpType;
import app.util.ruleFiter.type.ValidateParmType;

public class ValidateRuleInterfaceImplForObject implements ValidateRuleInterface {
	private static Map<String, Map<String, ValidateParm>> objectValidateRuleMap = new HashMap<String, Map<String, ValidateParm>>();
	
	@Override
	public Map<String, ValidateParm> getRuleByIdDirectly(String ruleId) {
		Map<String, ValidateParm> valiRuleMap = new HashMap<String, ValidateParm>();
		if(ruleId.equals("testStudent"))return initTestRule();
		valiRuleMap =  objectValidateRuleMap.get(ruleId);
		return valiRuleMap;
	}
	
	private Map<String, ValidateParm> initTestRule(){
		Map<String, ValidateParm> parmMap = new HashMap<String, ValidateParm> ();
		
		ValidateParm parm = ValidateParm.initValidateParm();
		parm.setMinValue(1);;
		parm.setOnlyNumber(true);
		parmMap.put("age", parm);
		
		parm = ValidateParm.initValidateParm();
		parm.setMaxValue(10000);
		parmMap.put("cost", parm);
		
		
		parm = ValidateParm.initValidateParm();
		parm.setMaxLength(5);
		parm.setStartStr("O");
		parm.setWarningType(ValidateParmType.startStr);
		parmMap.put("name", parm);
		
		parm = ValidateParm.initValidateParm();
		parm.setRegexpType(RegexpType.EMAIL);
		parmMap.put("email", parm);
		
		return parmMap;
	}
	
	public void addRuleMap(String ruleId,Map<String, ValidateParm> valiRuleMap){
		objectValidateRuleMap.put(ruleId, valiRuleMap);
	}

	@Override
	public Map<String, ValidateRuleEntity> initValidateRuleEntityForCache() {
		return null;
	}

	@Override
	public Map<String, ValidateParm> getRuleByIdFromCache(String ruleId) {
		return null;
	}

}
