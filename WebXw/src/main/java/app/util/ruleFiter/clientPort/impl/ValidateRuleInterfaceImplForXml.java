package app.util.ruleFiter.clientPort.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

import app.util.ruleFiter.InitValidateRuleByXml;
import app.util.ruleFiter.clientPort.ValidateRuleInterface;
import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.entity.ValidateRuleEntity;

public class ValidateRuleInterfaceImplForXml implements ValidateRuleInterface {
	public static Map<String,ValidateRuleEntity> cacheRuleEntityMap = null;
	@Override
	public Map<String, ValidateParm> getRuleByIdDirectly(String ruleId) throws IOException {
		Map<String, ValidateParm> valiRuleMap = new HashMap<String, ValidateParm>();
		try {
			valiRuleMap = getValidateRuleByXml(ruleId);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		return valiRuleMap;
	}
	
	private Map<String, ValidateParm> getValidateRuleByXml(String ruleId) throws DocumentException, IOException{
		InitValidateRuleByXml ivrbx = new InitValidateRuleByXml();
		ValidateRuleEntity entity = ivrbx.readFile().get(ruleId);
		return entity == null?null:entity.getValidateMap();
	}

	@Override
	public Map<String, ValidateRuleEntity> initValidateRuleEntityForCache() throws IOException {
		if(cacheRuleEntityMap == null){
			InitValidateRuleByXml ivrbx = new InitValidateRuleByXml();
			try {
				cacheRuleEntityMap = ivrbx.readFile();
			} catch (DocumentException e) {
				e.printStackTrace();
				System.out.println("没有读取到对应的校验xml配置文件");
				return null;
			}
		}
		return cacheRuleEntityMap;
	}
	
	public static boolean reloadRuleCache() throws IOException{
		InitValidateRuleByXml ivrbx = new InitValidateRuleByXml();
		try {
			cacheRuleEntityMap = ivrbx.readFile();
			return true;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Map<String, ValidateParm> getRuleByIdFromCache(String ruleId) throws IOException {
		ValidateRuleEntity entity = initValidateRuleEntityForCache().get(ruleId);
		if(entity==null)return null;
		return initValidateRuleEntityForCache().get(ruleId).getValidateMap();
	}
}
