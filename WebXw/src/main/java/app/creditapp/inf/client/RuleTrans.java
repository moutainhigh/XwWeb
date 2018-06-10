package app.creditapp.inf.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.SourceTemplate;
import app.creditapp.inf.client.entity.RuleFact;
import app.creditapp.inf.client.entity.RuleReturn;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.PreApply;
import app.creditapp.sys.bo.ParmRewRuleBo;
import app.creditapp.sys.bo.RulesBaseBo;
import app.creditapp.sys.entity.ParmRewRule;
import app.creditapp.sys.entity.RulesBase;
import app.oscache.Datadict;

/**
 * 将规则引擎返回的JSON串转为List
 *
 */
public class RuleTrans {
	
	private RuleReturn ruleReturn;
	private List<RuleReturn> ruleReturnList;
	
	/**
	 * 自动审批
	 * @param ruleFact
	 * @return
	 */
	public List<RuleReturn> translationAppAuto(RuleFact ruleFact,String prdtNo){
		
//		RulesBaseBo ruleBaseBo = (RulesBaseBo) SourceTemplate.getSpringContextInstance().getBean("rulesBaseBo");
//		RulesBase rulesBase = new RulesBase();
//		rulesBase.setPrdtNo(prdtNo);
//		List<RulesBase> rulesBaseList = null;
//		rulesBaseList = ruleBaseBo.findById(rulesBase);
//		
//		Map<String, String> codeValue = new HashMap<String, String>();
//		Map<String, String> codeDesc = new HashMap<String, String>();
//		Map<String, String> codeSts = new HashMap<String, String>();
//
//		for(int i=0;i<rulesBaseList.size();i++){
//			rulesBase = rulesBaseList.get(i);
//			codeValue.put(rulesBase.getCodeName(), rulesBase.getCodeValue());
//			codeDesc.put(rulesBase.getCodeName(), rulesBase.getCodeDesc());
//			codeSts.put(rulesBase.getCodeName(), rulesBase.getCodeSts());
//		}
//		
//		String appCodeDes = "";
//		ruleReturnList = new ArrayList<RuleReturn>();
//		if("100001".equals(prdtNo)){
//		//1、校验模型值1实名认证
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg1"));
//		ruleReturn.setCodeDes(codeDesc.get("arg1"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg1()));
//		if("01".equals(codeSts.get("arg1"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg1")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg1"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg1())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg1")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg1")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg1");
//			}
//		}
//		ruleReturnList.add(ruleReturn);
//		//2、校验模型值2入网时间
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg2"));
//		ruleReturn.setCodeDes(codeDesc.get("arg2"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg2()));
//		if("01".equals(codeSts.get("arg2"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg2")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg2"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg2())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg1")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg1")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg1");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//3、校验模型值3缴费习惯
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg3"));
//		ruleReturn.setCodeDes(codeDesc.get("arg3"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg3()));
//		if("01".equals(codeSts.get("arg3"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg3")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg3"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg3())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg3")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg3")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg3");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//4、校验模型值4停机时间
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg4"));
//		ruleReturn.setCodeDes(codeDesc.get("arg4"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg4()));
//		if("01".equals(codeSts.get("arg4"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg4")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg4"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg4())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg1")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg1")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg1");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//5、校验模型值5账户状态
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg5"));
//		ruleReturn.setCodeDes(codeDesc.get("arg5"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg5()));
//		if("01".equals(codeSts.get("arg5"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg5")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg5"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg5())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg5")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg5")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg5");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//6、校验模型值6通话时长
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg6"));
//		ruleReturn.setCodeDes(codeDesc.get("arg6"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg6()));
//		if("01".equals(codeSts.get("arg6"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg6")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg6"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg6())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg6")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg6")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg6");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//7、校验模型值7主叫次数
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg7"));
//		ruleReturn.setCodeDes(codeDesc.get("arg7"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg7()));
//		if("01".equals(codeSts.get("arg7"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg7")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg7"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg7())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg7")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg7")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg7");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//8、校验模型值8被叫次数
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg8"));
//		ruleReturn.setCodeDes(codeDesc.get("arg8"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg8()));
//		if("01".equals(codeSts.get("arg8"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg8")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg8"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg8())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg8")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg8")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg8");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//9、校验模型值9短信数
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg9"));
//		ruleReturn.setCodeDes(codeDesc.get("arg9"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg9()));
//		if("01".equals(codeSts.get("arg9"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg9")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg9"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg9())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg9")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg9")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg9");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//10、校验模型值10流量使用量
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg10"));
//		ruleReturn.setCodeDes(codeDesc.get("arg10"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg10()));
//		if("01".equals(codeSts.get("arg10"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg10")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg1"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg10())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg10")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg10")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg10");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//11、校验模型值11静默时间
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg11"));
//		ruleReturn.setCodeDes(codeDesc.get("arg11"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg11()));
//		if("01".equals(codeSts.get("arg11"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg11")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg1"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg11())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg11")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg11")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg11");
//			}
//		}
//		ruleReturnList.add(ruleReturn);		
//		//12、校验模型值12用户资格
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName(codeValue.get("arg12"));
//		ruleReturn.setCodeDes(codeDesc.get("arg12"));
//		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getarg12()));
//		if("01".equals(codeSts.get("arg12"))){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes(codeValue.get("arg12")+"符合贷款要求");
//		}else if("02".equals(codeSts.get("arg12"))){
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("拒绝贷款");
//		}else{
//			if("1".equals(ruleFact.getArg12())){
//				ruleReturn.setCodeRes("02");
//				ruleReturn.setCodeResDes(codeValue.get("arg12")+"符合贷款要求");
//			}else{
//				ruleReturn.setCodeRes("03");
//				ruleReturn.setCodeResDes(codeValue.get("arg12")+"不符合要求，拒绝贷款");
//				appCodeDes = appCodeDes + codeValue.get("arg12");
//			}
//		}
//		ruleReturnList.add(ruleReturn);						
//		//16、审批结果
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("审批结果");
//		ruleReturn.setCodeDes("所有项目均符合要求时，审批通过");
////		ruleReturn.setCodeValue(ruleFact.getAppRes());
//		if("02".equals(ruleFact.getAppRes())){
//			ruleReturn.setCodeValue("所有项目均符合要求");
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("自动审批通过");
//		}else{
//			ruleReturn.setCodeValue(appCodeDes+"等不符合要求");
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("自动审批否决，拒绝贷款");
//		}
//		ruleReturnList.add(ruleReturn);
////		//1、年龄
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("age"));
////		ruleReturn.setCodeDes(codeDesc.get("age"));
////		ruleReturn.setCodeValue(ruleFact.getAge()+" 岁");
////		if("01".equals(codeSts.get("age"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("age")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("age"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getAgeRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("age")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("age")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("age");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//2、性别
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("sex"));
////		ruleReturn.setCodeDes(codeDesc.get("sex"));
////		ruleReturn.setCodeValue(new Datadict("SEX").getDatadictByCode(ruleFact.getSex()));
////		if("01".equals(codeSts.get("sex"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("sex")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("sex"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getSexRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("sex")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("sex")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("sex");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//3、工作年限
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("workYear"));
////		ruleReturn.setCodeDes(codeDesc.get("workYear"));
////		ruleReturn.setCodeValue(ruleFact.getWorkYear()+" 年");
////		if("01".equals(codeSts.get("workYear"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("workYear")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("workYear"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getWorkYearRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("workYear")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("workYear")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("workYear");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//4、婚姻状况
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("marriage"));
////		ruleReturn.setCodeDes(codeDesc.get("marriage"));
////		ruleReturn.setCodeValue(new Datadict("MARRIAGE").getDatadictByCode(ruleFact.getMarriage()));
////		if("01".equals(codeSts.get("marriage"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("marriage")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("marriage"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getMarriageRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("marriage")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("marriage")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("marriage");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//4、是否有子女
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("children"));
////		ruleReturn.setCodeDes(codeDesc.get("children"));
////		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getChildren()));
////		if("01".equals(codeSts.get("children"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("children")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("children"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getChildrenRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("children")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("children")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("children");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//5、学历
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("edu"));
////		ruleReturn.setCodeDes(codeDesc.get("edu"));
////		ruleReturn.setCodeValue(new Datadict("EDU").getDatadictByCode(ruleFact.getEdu()));
////		if("01".equals(codeSts.get("edu"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("edu")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("edu"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getEduRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("edu")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("edu")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("edu");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//6、从业类型
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("cifType"));
////		ruleReturn.setCodeDes(codeDesc.get("cifType"));
////		ruleReturn.setCodeValue(new Datadict("CIF_TYPE").getDatadictByCode(ruleFact.getCifType()));
////		if("01".equals(codeSts.get("cifType"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("cifType")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("cifType"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getCifTypeRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("cifType")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("cifType")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("cifType");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//7、工作状态
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifWork"));
////		ruleReturn.setCodeDes(codeDesc.get("ifWork"));
////		ruleReturn.setCodeValue(new Datadict("WORK_STS").getDatadictByCode(ruleFact.getIfWork()));
////		if("01".equals(codeSts.get("ifWork"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifWork")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifWork"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfWorkRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifWork")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifWork")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifWork");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//8、月收入
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("income"));
////		ruleReturn.setCodeDes(codeDesc.get("income"));
////		ruleReturn.setCodeValue(ruleFact.getIncome()+" 元");
////		if("01".equals(codeSts.get("income"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("income")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("income"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIncomeRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("income")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("income")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("income");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//9、户籍属性
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifBendi"));
////		ruleReturn.setCodeDes(codeDesc.get("ifBendi"));
////		ruleReturn.setCodeValue(new Datadict("RULE_CIF_AREA").getDatadictByCode(ruleFact.getIfBendi()));
////		if("01".equals(codeSts.get("ifBendi"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifBendi")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifBendi"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfBendiRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifBendi")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifBendi")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifBendi");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//10、是否有车
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifCar"));
////		ruleReturn.setCodeDes(codeDesc.get("ifCar"));
////		ruleReturn.setCodeValue(new Datadict("IF_CAR").getDatadictByCode(ruleFact.getIfCar()));
////		if("01".equals(codeSts.get("ifCar"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifCar")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifCar"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfCarRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifCar")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifCar")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifCar");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//10、是否有按揭车贷
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifCarcred"));
////		ruleReturn.setCodeDes(codeDesc.get("ifCarcred"));
////		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfCarcred()));
////		if("01".equals(codeSts.get("ifCarcred"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifCarcred")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifCarcred"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfCarcredRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifCarcred")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifCarcred")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifCarcred");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//11、是否有房
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifRoom"));
////		ruleReturn.setCodeDes(codeDesc.get("ifRoom"));
////			ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfRoom()));
////		if("01".equals(codeSts.get("ifRoom"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifRoom")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifRoom"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfRoomRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifRoom")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifRoom")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifRoom");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//12、是否有按揭房贷
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifMort"));
////		ruleReturn.setCodeDes(codeDesc.get("ifMort"));
////		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfMort()));
////		if("01".equals(codeSts.get("ifMort"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifMort")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifMort"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfMortRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifMort")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifMort")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifMort");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//12、是否有贷记卡
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifCard"));
////		ruleReturn.setCodeDes(codeDesc.get("ifCard"));
////		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfCard()));
////		if("01".equals(codeSts.get("ifCard"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifCard")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifCard"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfCardRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifCard")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifCard")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifCard");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//12、贷记卡额度
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("cardAmt"));
////		ruleReturn.setCodeDes(codeDesc.get("cardAmt"));
////		ruleReturn.setCodeValue(ruleFact.getCardAmt()+" 元");
////		if("01".equals(codeSts.get("cardAmt"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("cardAmt")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("cardAmt"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getCardAmtRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("cardAmt")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("cardAmt")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("cardAmt");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////		//13、是否黑名单
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifBlack"));
////		ruleReturn.setCodeDes(codeDesc.get("ifBlack"));
////		if("01".equals(codeSts.get("ifBlack"))){
////			ruleReturn.setCodeValue("非黑名单用户");
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifBlack")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifBlack"))){
////			ruleReturn.setCodeValue("黑名单用户");
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfBlackRes())){
////				ruleReturn.setCodeValue("非黑名单用户");
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifBlack")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeValue("黑名单用户");
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifBlack")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifBlack");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
////
////		//14、联系电话
////		ruleReturn = new RuleReturn();
////		ruleReturn.setCodeName(codeValue.get("ifPhone"));
////		ruleReturn.setCodeDes(codeDesc.get("ifPhone"));
////		ruleReturn.setCodeValue(ruleFact.getIfPhone());
////		if("01".equals(codeSts.get("ifPhone"))){
////			ruleReturn.setCodeRes("02");
////			ruleReturn.setCodeResDes(codeValue.get("ifPhone")+"符合贷款要求");
////		}else if("02".equals(codeSts.get("ifPhone"))){
////			ruleReturn.setCodeRes("03");
////			ruleReturn.setCodeResDes("拒绝贷款");
////		}else{
////			if("02".equals(ruleFact.getIfPhoneRes())){
////				ruleReturn.setCodeRes("02");
////				ruleReturn.setCodeResDes(codeValue.get("ifPhone")+"符合贷款要求");
////			}else{
////				ruleReturn.setCodeRes("03");
////				ruleReturn.setCodeResDes(codeValue.get("ifPhone")+"不符合要求，拒绝贷款");
////				appCodeDes = appCodeDes + codeValue.get("ifPhone");
////			}
////		}
////		ruleReturnList.add(ruleReturn);
//		}else{
//			
//		}
//		
//		return ruleReturnList;
		
		//11.20注释
		RulesBaseBo ruleBaseBo = (RulesBaseBo) SourceTemplate.getSpringContextInstance().getBean("rulesBaseBo");
		RulesBase rulesBase = new RulesBase();
		rulesBase.setPrdtNo("100001");
		List<RulesBase> rulesBaseList = null;
		rulesBaseList = ruleBaseBo.findById(rulesBase);
		
		Map<String, String> codeValue = new HashMap<String, String>();
		Map<String, String> codeDesc = new HashMap<String, String>();
		Map<String, String> codeSts = new HashMap<String, String>();

		for(int i=0;i<rulesBaseList.size();i++){
			rulesBase = rulesBaseList.get(i);
			codeValue.put(rulesBase.getCodeName(), rulesBase.getCodeValue());
			codeDesc.put(rulesBase.getCodeName(), rulesBase.getCodeDesc());
			codeSts.put(rulesBase.getCodeName(), rulesBase.getCodeSts());
		}
		
		String appCodeDes = "";
		ruleReturnList = new ArrayList<RuleReturn>();
		//1、年龄
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("age"));
		ruleReturn.setCodeDes(codeDesc.get("age"));
//		ruleReturn.setCodeValue(ruleFact.getAge()+" 岁");
		if("01".equals(codeSts.get("age"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("age")+"符合贷款要求");
		}else if("02".equals(codeSts.get("age"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getAgeRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("age")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("age")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("age");
			}
		}
		ruleReturnList.add(ruleReturn);
		//2、性别
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("sex"));
		ruleReturn.setCodeDes(codeDesc.get("sex"));
		ruleReturn.setCodeValue(new Datadict("SEX").getDatadictByCode(ruleFact.getSex()));
		if("01".equals(codeSts.get("sex"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("sex")+"符合贷款要求");
		}else if("02".equals(codeSts.get("sex"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getSexRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("sex")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("sex")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("sex");
			}
		}
		ruleReturnList.add(ruleReturn);
		//3、工作年限
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("workYear"));
		ruleReturn.setCodeDes(codeDesc.get("workYear"));
//		ruleReturn.setCodeValue(ruleFact.getWorkYear()+" 年");
		if("01".equals(codeSts.get("workYear"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("workYear")+"符合贷款要求");
		}else if("02".equals(codeSts.get("workYear"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getWorkYearRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("workYear")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("workYear")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("workYear");
			}
		}
		ruleReturnList.add(ruleReturn);
		//4、婚姻状况
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("marriage"));
		ruleReturn.setCodeDes(codeDesc.get("marriage"));
		ruleReturn.setCodeValue(new Datadict("MARRIAGE").getDatadictByCode(ruleFact.getMarriage()));
		if("01".equals(codeSts.get("marriage"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("marriage")+"符合贷款要求");
		}else if("02".equals(codeSts.get("marriage"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getMarriageRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("marriage")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("marriage")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("marriage");
			}
		}
		ruleReturnList.add(ruleReturn);
		//4、是否有子女
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("children"));
		ruleReturn.setCodeDes(codeDesc.get("children"));
		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getChildren()));
		if("01".equals(codeSts.get("children"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("children")+"符合贷款要求");
		}else if("02".equals(codeSts.get("children"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getChildrenRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("children")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("children")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("children");
			}
		}
		ruleReturnList.add(ruleReturn);
		//5、学历
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("edu"));
		ruleReturn.setCodeDes(codeDesc.get("edu"));
		ruleReturn.setCodeValue(new Datadict("EDU").getDatadictByCode(ruleFact.getEdu()));
		if("01".equals(codeSts.get("edu"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("edu")+"符合贷款要求");
		}else if("02".equals(codeSts.get("edu"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getEduRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("edu")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("edu")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("edu");
			}
		}
		ruleReturnList.add(ruleReturn);
		//6、从业类型
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("cifType"));
		ruleReturn.setCodeDes(codeDesc.get("cifType"));
		ruleReturn.setCodeValue(new Datadict("CIF_TYPE").getDatadictByCode(ruleFact.getCifType()));
		if("01".equals(codeSts.get("cifType"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("cifType")+"符合贷款要求");
		}else if("02".equals(codeSts.get("cifType"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getCifTypeRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("cifType")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("cifType")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("cifType");
			}
		}
		ruleReturnList.add(ruleReturn);
		//7、工作状态
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifWork"));
		ruleReturn.setCodeDes(codeDesc.get("ifWork"));
		ruleReturn.setCodeValue(new Datadict("WORK_STS").getDatadictByCode(ruleFact.getIfWork()));
		if("01".equals(codeSts.get("ifWork"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifWork")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifWork"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfWorkRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifWork")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifWork")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifWork");
			}
		}
		ruleReturnList.add(ruleReturn);
		//8、月收入
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("income"));
		ruleReturn.setCodeDes(codeDesc.get("income"));
//		ruleReturn.setCodeValue(ruleFact.getIncome()+" 元");
		if("01".equals(codeSts.get("income"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("income")+"符合贷款要求");
		}else if("02".equals(codeSts.get("income"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIncomeRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("income")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("income")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("income");
			}
		}
		ruleReturnList.add(ruleReturn);
		//9、户籍属性
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifBendi"));
		ruleReturn.setCodeDes(codeDesc.get("ifBendi"));
		ruleReturn.setCodeValue(new Datadict("RULE_CIF_AREA").getDatadictByCode(ruleFact.getIfBendi()));
		if("01".equals(codeSts.get("ifBendi"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifBendi")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifBendi"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfBendiRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifBendi")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifBendi")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifBendi");
			}
		}
		ruleReturnList.add(ruleReturn);
		//10、是否有车
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifCar"));
		ruleReturn.setCodeDes(codeDesc.get("ifCar"));
		ruleReturn.setCodeValue(new Datadict("IF_CAR").getDatadictByCode(ruleFact.getIfCar()));
		if("01".equals(codeSts.get("ifCar"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifCar")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifCar"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfCarRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifCar")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifCar")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifCar");
			}
		}
		ruleReturnList.add(ruleReturn);
		//10、是否有按揭车贷
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifCarcred"));
		ruleReturn.setCodeDes(codeDesc.get("ifCarcred"));
		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfCarcred()));
		if("01".equals(codeSts.get("ifCarcred"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifCarcred")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifCarcred"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfCarcredRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifCarcred")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifCarcred")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifCarcred");
			}
		}
		ruleReturnList.add(ruleReturn);
		//11、是否有房
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifRoom"));
		ruleReturn.setCodeDes(codeDesc.get("ifRoom"));
			ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfRoom()));
		if("01".equals(codeSts.get("ifRoom"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifRoom")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifRoom"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfRoomRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifRoom")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifRoom")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifRoom");
			}
		}
		ruleReturnList.add(ruleReturn);
		//12、是否有按揭房贷
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifMort"));
		ruleReturn.setCodeDes(codeDesc.get("ifMort"));
		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfMort()));
		if("01".equals(codeSts.get("ifMort"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifMort")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifMort"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfMortRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifMort")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifMort")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifMort");
			}
		}
		ruleReturnList.add(ruleReturn);
		//12、是否有贷记卡
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifCard"));
		ruleReturn.setCodeDes(codeDesc.get("ifCard"));
		ruleReturn.setCodeValue(new Datadict("YES_NO").getDatadictByCode(ruleFact.getIfCard()));
		if("01".equals(codeSts.get("ifCard"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifCard")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifCard"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfCardRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifCard")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifCard")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifCard");
			}
		}
		ruleReturnList.add(ruleReturn);
		//12、贷记卡额度
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("cardAmt"));
		ruleReturn.setCodeDes(codeDesc.get("cardAmt"));
//		ruleReturn.setCodeValue(ruleFact.getCardAmt()+" 元");
		if("01".equals(codeSts.get("cardAmt"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("cardAmt")+"符合贷款要求");
		}else if("02".equals(codeSts.get("cardAmt"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getCardAmtRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("cardAmt")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("cardAmt")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("cardAmt");
			}
		}
		ruleReturnList.add(ruleReturn);
		//13、是否黑名单
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifBlack"));
		ruleReturn.setCodeDes(codeDesc.get("ifBlack"));
		if("01".equals(codeSts.get("ifBlack"))){
			ruleReturn.setCodeValue("非黑名单用户");
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifBlack")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifBlack"))){
			ruleReturn.setCodeValue("黑名单用户");
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfBlackRes())){
				ruleReturn.setCodeValue("非黑名单用户");
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifBlack")+"符合贷款要求");
			}else{
				ruleReturn.setCodeValue("黑名单用户");
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifBlack")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifBlack");
			}
		}
		ruleReturnList.add(ruleReturn);

		//14、联系电话
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName(codeValue.get("ifPhone"));
		ruleReturn.setCodeDes(codeDesc.get("ifPhone"));
		ruleReturn.setCodeValue(ruleFact.getIfPhone());
		if("01".equals(codeSts.get("ifPhone"))){
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(codeValue.get("ifPhone")+"符合贷款要求");
		}else if("02".equals(codeSts.get("ifPhone"))){
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("拒绝贷款");
		}else{
			if("02".equals(ruleFact.getIfPhoneRes())){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes(codeValue.get("ifPhone")+"符合贷款要求");
			}else{
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes(codeValue.get("ifPhone")+"不符合要求，拒绝贷款");
				appCodeDes = appCodeDes + codeValue.get("ifPhone");
			}
		}
		ruleReturnList.add(ruleReturn);
		
		//15、审批结果
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("审批结果");
		ruleReturn.setCodeDes("所有项目均符合要求时，审批通过");
//		ruleReturn.setCodeValue(ruleFact.getAppRes());
		if("02".equals(ruleFact.getAppRes())){
			ruleReturn.setCodeValue("所有项目均符合要求");
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes("自动审批通过");
		}else{
//			ruleReturn.setCodeValue(appCodeDes+"等不符合要求");
			ruleReturn.setCodeRes("03");
			ruleReturn.setCodeResDes("自动审批否决，拒绝贷款");
		}
		ruleReturnList.add(ruleReturn);
//		
		return ruleReturnList;
	}
//	/**
//	 * 自动审批
//	 * @param ruleFact
//	 * @return
//	 */
//	public List<RuleReturn> translationAppAuto(RuleFact ruleFact){
//		String appCodeDes = "";
//		ruleReturnList = new ArrayList<RuleReturn>();
//		//1、年龄
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("年龄");
//		ruleReturn.setCodeDes("年龄大于18并且年龄小于65");
//		ruleReturn.setCodeValue(ruleFact.getAge()+" 岁");
//		if("02".equals(ruleFact.getAgeRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("年龄符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("年龄不符合要求，拒绝贷款");
//			appCodeDes = appCodeDes + "年龄";
//		}
//		ruleReturnList.add(ruleReturn);
//		//2、学历
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("学历");
//		ruleReturn.setCodeDes("学历不为文盲或半文盲");
//		ruleReturn.setCodeValue(new Datadict("EDU").getDatadictByCode(ruleFact.getEdu()));
//		if("02".equals(ruleFact.getEduRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("学历符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("学历不符合要求，拒绝贷款");
//			appCodeDes = appCodeDes + "学历、";
//		}
//		ruleReturnList.add(ruleReturn);
//		//3、从业类型
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("从业类型");
//		ruleReturn.setCodeDes("从业类型不允许为农户或其他");
//		ruleReturn.setCodeValue(new Datadict("CIF_TYPE").getDatadictByCode(ruleFact.getCifType()));
//		if("02".equals(ruleFact.getCifTypeRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("从业类型符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("从业类型不符合要求，拒绝贷款");
//			appCodeDes = appCodeDes + "从业类型、";
//		}
//		ruleReturnList.add(ruleReturn);
//		//4、月收入
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("月收入");
//		ruleReturn.setCodeDes("月收入不得低于5000元");
//		ruleReturn.setCodeValue(ruleFact.getIncome()+" 元");
//		if("02".equals(ruleFact.getIncomeRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("月收入符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("月收入不符合要求，拒绝贷款");
//			appCodeDes = appCodeDes + "月收入、";
//		}
//		ruleReturnList.add(ruleReturn);
//		//5、户籍属性
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("户籍属性");
//		ruleReturn.setCodeDes("非本地籍户口不允许借款");
//		ruleReturn.setCodeValue(new Datadict("RULE_CIF_AREA").getDatadictByCode(ruleFact.getIfbendi()));
//		if("02".equals(ruleFact.getIfbendiRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("户籍属性符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("户籍属性不符合要求，拒绝贷款");
//			appCodeDes = appCodeDes + "户籍属性、";
//		}
//		ruleReturnList.add(ruleReturn);
//		//6、联系电话
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("联系电话");
//		ruleReturn.setCodeDes("联系电话不能为空"); 
//		ruleReturn.setCodeValue(ruleFact.getIfPhone());
//		if("02".equals(ruleFact.getIfphoneRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("联系电话符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("联系电话不符合要求，拒绝贷款");
//			appCodeDes = appCodeDes + "联系电话、";
//		}
//		ruleReturnList.add(ruleReturn);
//		//7、是否有车
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("是否有车");
//		ruleReturn.setCodeDes("是否有车不能为空");
//		ruleReturn.setCodeValue(new Datadict("IF_CAR").getDatadictByCode(ruleFact.getIfCar()));
//		if("02".equals(ruleFact.getIfCarRes())){
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("有车符合贷款要求");
//		}else{
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("是否有车不能为空，拒绝贷款");
//			appCodeDes = appCodeDes + "是否有车、";
//		}
//		ruleReturnList.add(ruleReturn);
//		//8、审批结果
//		ruleReturn = new RuleReturn();
//		ruleReturn.setCodeName("审批结果");
//		ruleReturn.setCodeDes("所有项目均符合要求时，审批通过");
////		ruleReturn.setCodeValue(ruleFact.getAppRes());
//		if("02".equals(ruleFact.getAppRes())){
//			ruleReturn.setCodeValue("所有项目均符合要求");
//			ruleReturn.setCodeRes("02");
//			ruleReturn.setCodeResDes("自动审批通过");
//		}else{
//			ruleReturn.setCodeValue(appCodeDes+"等不符合要求");
//			ruleReturn.setCodeRes("03");
//			ruleReturn.setCodeResDes("自动审批否决，拒绝贷款");
//		}
//		ruleReturnList.add(ruleReturn);
//		return ruleReturnList;
//	}

	/**
	 * 客户评级
	 * @param ruleFact
	 * @return
	 */
	public List<RuleReturn> translationCifEval(RuleFact ruleFact){
		ruleReturnList = new ArrayList<RuleReturn>();
		//1、用户名
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("用户名");
		ruleReturn.setCodeDes("用户名不可为空");
		ruleReturn.setCodeValue(ruleFact.getCifName());
		ruleReturn.setCodeRes(ruleFact.getCifNameScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getCifNameScore()+" 分");
		ruleReturnList.add(ruleReturn);
		//2、年龄
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("年龄");
		ruleReturn.setCodeDes("年龄应大于18岁小于65岁");
		ruleReturn.setCodeValue(ruleFact.getAge()+" 岁");
		ruleReturn.setCodeRes(ruleFact.getAgeScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getAgeScore()+" 分");
		ruleReturnList.add(ruleReturn);
		//3、客户评级总分
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("客户评级总分");
		ruleReturn.setCodeDes("客户评级总分为所有得分之和");
		ruleReturn.setCodeValue("一共包含两项评测");
		ruleReturn.setCodeRes((ruleFact.getCifNameScore()+ruleFact.getAgeScore())+"");
		ruleReturn.setCodeResDes("总得分为: "+(ruleFact.getCifNameScore()+ruleFact.getAgeScore())+" 分");
		ruleReturnList.add(ruleReturn);
		return ruleReturnList;
	}
	/**
	 * 合作机构评级
	 * @param ruleFact
	 * @return
	 */
	public List<RuleReturn> translationCorpEval(RuleFact ruleFact){
		ruleReturnList = new ArrayList<RuleReturn>();
		Double score = 0.00;
		//1、年度固有资产规模
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("年度固有资产");
		ruleReturn.setCodeValue("年度固有资产为: "+ruleFact.getNdgyzc()+"亿元");
		ruleReturn.setCodeRes(ruleFact.getNdgyzcScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getNdgyzcScore()+" 分");
		score = score + ruleFact.getNdgyzcScore();
		ruleReturnList.add(ruleReturn);
		//2、贷款代偿率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("贷款代偿率");
		ruleReturn.setCodeValue("贷款代偿率为: "+ruleFact.getDkdcl()+"%");
		ruleReturn.setCodeRes(ruleFact.getDkdclScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getDkdclScore()+" 分");
		score = score + ruleFact.getDkdclScore();
		ruleReturnList.add(ruleReturn);
		//3、贷款回购率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("贷款回购率");
		ruleReturn.setCodeValue("贷款回购率为: "+ruleFact.getDkhgl()+"%");
		ruleReturn.setCodeRes(ruleFact.getDkhglScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getDkhglScore()+" 分");
		score = score + ruleFact.getDkhglScore();
		ruleReturnList.add(ruleReturn);
		//4、不良贷款率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("不良贷款率");
		ruleReturn.setCodeValue("不良贷款率为: "+ruleFact.getBldkl()+"%");
		ruleReturn.setCodeRes(ruleFact.getBldklScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getBldklScore()+" 分");
		score = score + ruleFact.getBldklScore();
		ruleReturnList.add(ruleReturn);
		//5、累放增长率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("累放增长率");
		ruleReturn.setCodeValue("累放增长率为: "+ruleFact.getLfzzl()+"%");
		ruleReturn.setCodeRes(ruleFact.getLfzzlScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getLfzzlScore()+" 分");
		score = score + ruleFact.getLfzzlScore();
		ruleReturnList.add(ruleReturn);
		//6、贷款收息率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("贷款收息率");
		ruleReturn.setCodeValue("贷款收息率为: "+ruleFact.getDksxl()+"%");
		ruleReturn.setCodeRes(ruleFact.getDksxlScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getDksxlScore()+" 分");
		score = score + ruleFact.getDksxlScore();
		ruleReturnList.add(ruleReturn);
		//7、损失准备率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("损失准备率");
		ruleReturn.setCodeValue("损失准备率为: "+ruleFact.getSszbl()+"%");
		ruleReturn.setCodeRes(ruleFact.getSszblScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getSszblScore()+" 分");
		score = score + ruleFact.getSszblScore();
		ruleReturnList.add(ruleReturn);
		//8、新增客户率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("新增客户率");
		ruleReturn.setCodeValue("新增客户率为: "+ruleFact.getXzkhl()+"%");
		ruleReturn.setCodeRes(ruleFact.getXzkhlScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getXzkhlScore()+" 分");
		score = score + ruleFact.getXzkhlScore();
		ruleReturnList.add(ruleReturn);
		//9、余额增长率
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("余额增长率");
		ruleReturn.setCodeValue("余额增长率为: "+ruleFact.getYezzl()+"%");
		ruleReturn.setCodeRes(ruleFact.getYezzlScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getYezzlScore()+" 分");
		score = score + ruleFact.getYezzlScore();
		ruleReturnList.add(ruleReturn);
		//10、建筑贷款比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("建筑贷款比重");
		ruleReturn.setCodeValue("建筑贷款比重为: "+ruleFact.getJzdkbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getJzdkbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getJzdkbzScore()+" 分");
		score = score + ruleFact.getJzdkbzScore();
		ruleReturnList.add(ruleReturn);
		//11、股东贷款比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("股东贷款比重");
		ruleReturn.setCodeValue("股东贷款比重为: "+ruleFact.getGddkbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getGddkbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getGddkbzScore()+" 分");
		score = score + ruleFact.getGddkbzScore();
		ruleReturnList.add(ruleReturn);
		//12、单一贷款比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("单一贷款比重");
		ruleReturn.setCodeValue("单一贷款比重为: "+ruleFact.getDydkbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getDydkbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getDydkbzScore()+" 分");
		score = score + ruleFact.getDydkbzScore();
		ruleReturnList.add(ruleReturn);
		//13、前十贷款比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("前十贷款比重");
		ruleReturn.setCodeValue("前十贷款比重为: "+ruleFact.getQsdkbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getQsdkbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getQsdkbzScore()+" 分");
		score = score + ruleFact.getQsdkbzScore();
		ruleReturnList.add(ruleReturn);
		//14、三农贷款比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("三农贷款比重");
		ruleReturn.setCodeValue("三农贷款比重为: "+ruleFact.getSndkbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getSndkbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getSndkbzScore()+" 分");
		score = score + ruleFact.getSndkbzScore();
		ruleReturnList.add(ruleReturn);
		//15、小微贷款比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("小微贷款比重");
		ruleReturn.setCodeValue("小微贷款比重为: "+ruleFact.getXwdkbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getXwdkbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getXwdkbzScore()+" 分");
		score = score + ruleFact.getXwdkbzScore();
		ruleReturnList.add(ruleReturn);
		//16、贷款期限比重
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("贷款期限比重");
		ruleReturn.setCodeValue("贷款期限比重为: "+ruleFact.getDkqxbz()+"%");
		ruleReturn.setCodeRes(ruleFact.getDkqxbzScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getDkqxbzScore()+" 分");
		score = score + ruleFact.getDkqxbzScore();
		ruleReturnList.add(ruleReturn);
		//17、注册资本
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("注册资本");
		ruleReturn.setCodeValue("注册资本为: "+ruleFact.getZczb()+"亿元");
		ruleReturn.setCodeRes(ruleFact.getZczbScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getZczbScore()+" 分");
		score = score + ruleFact.getZczbScore();
		ruleReturnList.add(ruleReturn);
		//18、增资扩股
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("增资扩股");
		ruleReturn.setCodeValue("增资扩股为: "+ruleFact.getZzkg()+"%");
		ruleReturn.setCodeRes(ruleFact.getZzkgScore()+"");
		ruleReturn.setCodeResDes(ruleFact.getZzkgScore()+" 分");
		score = score + ruleFact.getZzkgScore();
		ruleReturnList.add(ruleReturn);
		//19、总分
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("机构总得分");
		ruleReturn.setCodeValue("机构总得分为所有得分项之和");
		ruleReturn.setCodeRes(score+"");
		ruleReturn.setScore(score);
		ruleReturn.setCodeResDes(score+" 分");
		ruleReturnList.add(ruleReturn);
		
		return ruleReturnList;
	}
	/**
	 * 正式进件筛查
	 * @param ruleFact
	 * @return
	 */
	public List<RuleReturn> translationLnChk(LnApplyMid lnApplyMid){
		//检验是否启用筛查	0停用 	1启用
		ParmRewRuleBo parmRewRuleBo = (ParmRewRuleBo)SourceTemplate.getSpringContextInstance().getBean("parmRewRuleBo");
		ParmRewRule parmRewRule = new ParmRewRule();
		parmRewRule.setSceneNo("02");
		List<ParmRewRule> parmRewRuleList = parmRewRuleBo.findAll(parmRewRule);
		Map<String, String> parm = new HashMap<String, String>();
		for(int i=0;i<parmRewRuleList.size();i++){
			parm.put(parmRewRuleList.get(i).getRuleId(), parmRewRuleList.get(i).getRuleSts());
		}
		
		ruleReturnList = new ArrayList<RuleReturn>();
		String chkRes = lnApplyMid.getChkRes();
		String finalChkDesc = "";
		if("1".equals(parm.get("R0010"))){
			//1、重复进件
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("进件是否重复");
			ruleReturn.setCodeValue(chkRes);
			if("07".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("重复进件，筛查未通过");
				finalChkDesc = "由于重复进件，筛查未通过";
			}else{
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("进件不重复，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0009"))){
			//2、黑名单
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("是否黑名单用户");
			ruleReturn.setCodeValue(chkRes);
			if("04".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("黑名单用户，筛查未通过");
				finalChkDesc = "由于黑名单用户，筛查未通过";
			}else if("07".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("是否黑名单用户未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("非黑名单用户，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0011"))){
			//3、婚姻状况
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("已婚用户必须存在配偶信息");
			ruleReturn.setCodeValue(chkRes);
			if("08".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("婚姻状况为已婚，但配偶信息不完整，筛查未通过");
				finalChkDesc = "由于婚姻状况为已婚，但配偶信息不完整，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("婚姻状况未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("婚姻状况符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0012"))){
			//4、合作机构号码必须存在且有效
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合作机构号码必须存在且有效");
			ruleReturn.setCodeValue(chkRes);
			if("09".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合作机构编号无效，筛查未通过");
				finalChkDesc = "由于合作机构编号无效，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合作机构号码未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合作机构号码符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//4、合作机构号码必须有账户
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合作机构必须存在账户信息");
			ruleReturn.setCodeValue(chkRes);
			if("17".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合作机构无账户信息，筛查未通过");
				finalChkDesc = "由于合作机构无账户信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"09".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合作机构账户信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合作机构存在账户信息，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//4、合作机构号码必须有配置
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合作机构必须存在配置信息");
			ruleReturn.setCodeValue(chkRes);
			if("18".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合作机构无配置信息，筛查未通过");
				finalChkDesc = "由于合作机构无配置信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"09".equals(chkRes)||"17".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合作机构配置信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合作机构存在配置信息，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0013"))){
			//5、信托项目编号必须存在、有效、且与合作机构号码对应
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("信托项目编号必须存在、有效、且与合作机构号码对应");
			ruleReturn.setCodeValue(chkRes);
			if("10".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("信托项目编号无效，或者与合作机构编号不符，筛查未通过");
				finalChkDesc = "由于信托项目编号无效，或者与合作机构编号不符，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("信托项目编号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("信托项目编号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0020"))){
			//5、项目不含资金信息
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("项目必须包含资金信息");
			ruleReturn.setCodeValue(chkRes);
			if("20".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("项目不含资金信息，筛查未通过");
				finalChkDesc = "项目不含资金信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("信托项目编号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("信托项目编号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0022"))){
			//5、项目不含资金信息
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("项目和渠道必须含有效的虚拟户信息");
			ruleReturn.setCodeValue(chkRes);
			if("21".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("该项目和渠道不含有效的虚拟户信息");
				finalChkDesc = "该项目和渠道不含有效的虚拟户信息";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"21".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("信托项目编号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("信托项目编号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0014"))){
			//6、产品号必须存在且有效
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("产品号必须存在且有效");
			ruleReturn.setCodeValue(chkRes);
			if("11".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("产品号不存在，或者产品号无效");
				finalChkDesc = "由于产品号不存在，或者产品号无效，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"20".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("产品号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("产品号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0015"))){
			//7、担保方式为抵押，必须存在有效的押品信息
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("担保方式为抵押，必须存在有效的押品信息");
			ruleReturn.setCodeValue(chkRes);
			if("12".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("担保方式为抵押，但是不存在押品信息");
				finalChkDesc = "由于不存在押品信息，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"20".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("押品信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("担保方式为抵押，押品信息符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//8、担保方式为抵押，抵押金额大于合同金额
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("担保方式为抵押，抵押金额大于合同金额");
			ruleReturn.setCodeValue(chkRes);
			if("13".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("担保方式为抵押，但是抵押金额小于合同金额");
				finalChkDesc = "由于抵押金额小于合同金额，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("抵押金额未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("担保方式为抵押，抵押金额符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0016"))){
			//9、账户信息必须完整
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("账户信息必须完整");
			ruleReturn.setCodeValue(chkRes);
			if("14".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("账户信息不完整，筛查未通过");
				finalChkDesc = "由于账户信息不完整，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("账户信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("账户信息符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//10、放款金额总和等于合同金额
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("放款金额总和等于合同金额");
			ruleReturn.setCodeValue(chkRes);
			if("15".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("账户放款金额总和不等于合同金额，筛查未通过");
				finalChkDesc = "由于账户放款金额总和不等于合同金额，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)||"14".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("放款金额与合同金额未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("放款金额与合同金额符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0018"))){
			//6、产品所允许的展业地区必须包含申请地点
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("产品所允许的展业地区必须包含申请地点");
			ruleReturn.setCodeValue(chkRes);
			if("19".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("产品所允许的展业地区不包含该申请地点");
				finalChkDesc = "由于产品所允许的展业地区不包含该申请地点，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)||"14".equals(chkRes)||"15".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("产品展业地区未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("产品所允许的展业地区包该含申请地点，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		//zlc 20170904
		if("1".equals(parm.get("R0023"))){
			//6、合同关联的影像信息必须上传
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合同关联的影像信息必须上传");
			ruleReturn.setCodeValue(chkRes);
			if("23".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合同关联的影像信息必须上传");
				finalChkDesc = "由于合同关联的影像信息未上传，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)||"14".equals(chkRes)||"15".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合同关联的影像信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合同关联的影像信息已经上传，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		//zlc 20170904
		if("1".equals(parm.get("R0024"))){
			//6、参数字段中参数个数不正确
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("参数字段中参数个数必须等于产品中配置参数个数");
			ruleReturn.setCodeValue(chkRes);
			if("24".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("参数字段中参数个数必须等于产品中配置参数个数");
				finalChkDesc = "由于参数字段中参数个数不等于产品中配置参数个数，筛查未通过";
			}else if("04".equals(chkRes)||"21".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)||"14".equals(chkRes)||"15".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("参数字段中参数个数未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("参数字段中参数个数等于产品中配置参数个数，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		//11、总结果
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("最终结果");
		ruleReturn.setCodeValue(chkRes);
		if("".equals(finalChkDesc)){
			ruleReturn.setCodeRes("04");
			ruleReturn.setCodeResDes("用户已通过所有筛查，进入下一个阶段");
		}else {
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(finalChkDesc);
		}
		ruleReturnList.add(ruleReturn);
		return ruleReturnList;
	}
	/**
	 * 预审批筛查
	 * @param ruleFact
	 * @return
	 */
	public List<RuleReturn> translationPreChk(PreApply preApply){
		//检验是否启用筛查	0停用 	1启用
		ParmRewRuleBo parmRewRuleBo = (ParmRewRuleBo)SourceTemplate.getSpringContextInstance().getBean("parmRewRuleBo");
		ParmRewRule parmRewRule = new ParmRewRule();
		parmRewRule.setSceneNo("01");
		List<ParmRewRule> parmRewRuleList = parmRewRuleBo.findAll(parmRewRule);
		Map<String, String> parm = new HashMap<String, String>();
		for(int i=0;i<parmRewRuleList.size();i++){
			parm.put(parmRewRuleList.get(i).getRuleId(), parmRewRuleList.get(i).getRuleSts());
		}
		ruleReturnList = new ArrayList<RuleReturn>();
		String chkRes = preApply.getChkRes();
		String finalChkDesc = "";
		
		if("1".equals(parm.get("R0001"))){
			//1、黑名单
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("是否黑名单用户");
			ruleReturn.setCodeValue(chkRes);
			if("04".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("黑名单用户，筛查未通过");
				finalChkDesc = "由于黑名单用户，筛查未通过";
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("非黑名单用户，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0002"))){
			//2、重复合同
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合同是否重复");
			ruleReturn.setCodeValue(chkRes);
			if("07".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("重复合同，筛查未通过");
				finalChkDesc = "由于合同重复，筛查未通过";
			}else if("04".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合同是否重复未筛查");
			}else{
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合同不重复，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0003"))){
			//3、婚姻状况
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("已婚用户必须存在配偶信息");
			ruleReturn.setCodeValue(chkRes);
			if("08".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("婚姻状况为已婚，但配偶信息不完整，筛查未通过");
				finalChkDesc = "由于配偶信息不完整，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("婚姻状况未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("婚姻状况符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0004"))){
			//4、合作机构号码必须存在且有效
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合作机构号码必须存在且有效");
			ruleReturn.setCodeValue(chkRes);
			if("09".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合作机构编号无效，筛查未通过");
				finalChkDesc = "由于合作机构编号无效，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合作机构号码未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合作机构号码符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//4、合作机构号码必须有账户
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合作机构必须存在账户信息");
			ruleReturn.setCodeValue(chkRes);
			if("17".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合作机构无账户信息，筛查未通过");
				finalChkDesc = "由于合作机构无账户信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"09".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合作机构账户信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合作机构存在账户信息，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//4、合作机构号码必须有配置
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("合作机构必须存在配置信息");
			ruleReturn.setCodeValue(chkRes);
			if("17".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("合作机构无配置信息，筛查未通过");
				finalChkDesc = "由于合作机构无配置信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"09".equals(chkRes)||"17".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("合作机构配置信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("合作机构存在配置信息，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0005"))){
			//5、信托项目编号必须存在、有效、且与合作机构号码对应
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("信托项目编号必须存在、有效、且与合作机构号码对应");
			ruleReturn.setCodeValue(chkRes);
			if("10".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("信托项目编号无效，或者与合作机构编号不符，筛查未通过");
				finalChkDesc = "由于信托项目编号无效，或者与合作机构编号不符，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("信托项目编号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("信托项目编号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0019"))){
			//5、项目必须包含资金信息
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("项目必须包含资金信息");
			ruleReturn.setCodeValue(chkRes);
			if("20".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("项目未包含资金信息，筛查未通过");
				finalChkDesc = "项目未包含资金信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("信托项目编号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("信托项目编号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0006"))){
			//6、产品号必须存在且有效
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("产品号必须存在且有效");
			ruleReturn.setCodeValue(chkRes);
			if("11".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("产品号不存在，或者产品号无效");
				finalChkDesc = "由于产品号不存在，或者产品号无效，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("产品号未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("产品号符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0007"))){
			//7、担保方式为抵押，必须存在有效的押品信息
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("担保方式为抵押，必须存在有效的押品信息");
			ruleReturn.setCodeValue(chkRes);
			if("12".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("担保方式为抵押，但是不存在押品信息");
				finalChkDesc = "由于不存在押品信息，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("押品信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("担保方式为抵押，押品信息符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//8、担保方式为抵押，抵押金额大于合同金额
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("担保方式为抵押，抵押金额大于合同金额");
			ruleReturn.setCodeValue(chkRes);
			if("13".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("担保方式为抵押，但是抵押金额小于合同金额");
				finalChkDesc = "由于抵押金额小于合同金额，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("抵押金额未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("担保方式为抵押，抵押金额符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0008"))){
			//9、账户信息必须完整
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("账户信息必须完整");
			ruleReturn.setCodeValue(chkRes);
			if("14".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("账户信息不完整，筛查未通过");
				finalChkDesc = "由于账户信息不完整，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("账户信息未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("账户信息符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
			//10、放款金额总和等于合同金额
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("放款金额总和等于合同金额");
			ruleReturn.setCodeValue(chkRes);
			if("15".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("账户放款金额总和不等于合同金额，筛查未通过");
				finalChkDesc = "由于账户放款金额总和不等于合同金额，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)||"14".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("放款金额与合同金额未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("放款金额与合同金额符合要求，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		if("1".equals(parm.get("R0017"))){
			//6、产品所允许的展业地区必须包含申请地点
			ruleReturn = new RuleReturn();
			ruleReturn.setCodeName("产品所允许的展业地区必须包含申请地点");
			ruleReturn.setCodeValue(chkRes);
			if("19".equals(chkRes)){
				ruleReturn.setCodeRes("02");
				ruleReturn.setCodeResDes("产品所允许的展业地区不包含该申请地点");
				finalChkDesc = "由于产品所允许的展业地区不包含该申请地点，筛查未通过";
			}else if("04".equals(chkRes)||"07".equals(chkRes)||"20".equals(chkRes)||"08".equals(chkRes)||"17".equals(chkRes)||"18".equals(chkRes)||"09".equals(chkRes)||"10".equals(chkRes)||"11".equals(chkRes)||"12".equals(chkRes)||"13".equals(chkRes)||"14".equals(chkRes)||"15".equals(chkRes)){
				ruleReturn.setCodeRes("03");
				ruleReturn.setCodeResDes("产品展业地区未筛查");
			}else {
				ruleReturn.setCodeRes("01");
				ruleReturn.setCodeResDes("产品所允许的展业地区包该含申请地点，筛查通过");
			}
			ruleReturnList.add(ruleReturn);
		}
		//11、总结果
		ruleReturn = new RuleReturn();
		ruleReturn.setCodeName("最终结果");
		ruleReturn.setCodeValue(chkRes);
		if("".equals(finalChkDesc)){
			ruleReturn.setCodeRes("04");
			ruleReturn.setCodeResDes("用户已通过所有筛查，进入下一个阶段");
		}else {
			ruleReturn.setCodeRes("02");
			ruleReturn.setCodeResDes(finalChkDesc);
		}
		ruleReturnList.add(ruleReturn);
		parm.clear();
		parm=null;
		return ruleReturnList;
	}
	/**
	 * 预审批筛查
	 * @param ruleFact
	 * @return
	 */
	public String resultError(RuleFact ruleFact){
		
		String s = null;
		//1、年龄
		if("03".equals(ruleFact.getAgeRes())){
			s = "年龄不符合要求";
		}
		//2、学历
		if("03".equals(ruleFact.getEduRes())){
			s = "学历不符合要求";
		}
		//3、从业类型
		if("03".equals(ruleFact.getCifTypeRes())){
			s = "从业类型不符合要求";
		}
		//4、月收入
		if("03".equals(ruleFact.getIncomeRes())){
			s = "月收入不符合要求";
		}
		//5、户籍属性
		if("03".equals(ruleFact.getIfBendiRes())){
			s = "户籍属性不符合要求";
		}
		//6、联系电话
		if("03".equals(ruleFact.getIfPhoneRes())){
			s = "联系电话不符合要求";
		}
		//7、是否有车
		if("03".equals(ruleFact.getIfCarRes())){
			s = "是否有车不符合要求";
		}
		//天安未来校验模型
		//1、实名认证
		if("03".equals(ruleFact.getIfCarRes())){
			s = "实名认证不符合要求";
		}
		//2、入网时间
		if("03".equals(ruleFact.getIfCarRes())){
			s = "入网时间不符合要求";
		}		
		//3、缴费习惯
		if("03".equals(ruleFact.getIfCarRes())){
			s = "缴费习惯不符合要求";
		}	
		//4、停机时间
		if("03".equals(ruleFact.getIfCarRes())){
			s = "停机时间不符合要求";
		}	
		//5、账户状态
		if("03".equals(ruleFact.getIfCarRes())){
			s = "账户状态不符合要求";
		}	
		//6、通话时长
		if("03".equals(ruleFact.getIfCarRes())){
			s = "通话时长不符合要求";
		}
		//7、主叫次数
		if("03".equals(ruleFact.getIfCarRes())){
			s = "主叫次数不符合要求";
		}
		//8、被叫次数
		if("03".equals(ruleFact.getIfCarRes())){
			s = "被叫次数不符合要求";
		}
		//9、短信数
		if("03".equals(ruleFact.getIfCarRes())){
			s = "短信数不符合要求";
		}
		//10、流量使用量
		if("03".equals(ruleFact.getIfCarRes())){
			s = "流量使用量不符合要求";
		}
		//11、静默时间
		if("03".equals(ruleFact.getIfCarRes())){
			s = "静默时间不符合要求";
		}
		//12、用户资格
		if("03".equals(ruleFact.getIfCarRes())){
			s = "用户资格不符合要求";
		}		
		return s;
	}
}
