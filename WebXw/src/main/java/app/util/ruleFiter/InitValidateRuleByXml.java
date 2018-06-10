package app.util.ruleFiter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import app.util.ruleFiter.entity.ValidateParm;
import app.util.ruleFiter.entity.ValidateRuleEntity;
import app.util.ruleFiter.type.RegexpType;
import app.util.ruleFiter.type.ValidateParmType;

public class InitValidateRuleByXml {
	private final String filePath;
	ResourceLoader loader = new DefaultResourceLoader();
	public InitValidateRuleByXml() throws IOException {
		super();
		filePath = "rule-files.xml";
	}

	public InitValidateRuleByXml(String filePath) {
		super();
		this.filePath = filePath;
	}

	public Map<String,ValidateRuleEntity> readFile() throws DocumentException{
		try {
			return readRules(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Map<String,ValidateRuleEntity> readRules(String xmlilePath) throws DocumentException, IOException{
		if(xmlilePath==null || xmlilePath.isEmpty()){
			throw new DocumentException("文件路径不正确，不应为空！");
		}
		SAXReader reader = new SAXReader();  
		
/*				Map<String,ValidateRuleEntity> vrCacheMap = new HashMap<String,ValidateRuleEntity>();  
		xmlilePath = loader.getResource(xmlilePath).getFile().getAbsolutePath();
//		xmlilePath =( (HashMap<String,String>) MBaseCache.getCache().getBeanCache(CachecodeUtil.SYS_PATH_STR, CachecodeUtil.SYS_PATH)).get("201")+"/"+xmlilePath;
	//  System.out.println(xmlilePath);
//		xmlilePath = loader.getResource("/").getURL().getPath() + xmlilePath;
		Document document = reader.read(new File(xmlilePath));  
		*/
		Map<String,ValidateRuleEntity> vrCacheMap = new HashMap<String,ValidateRuleEntity>();
		ClassLoader classloader =Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(xmlilePath);
		
//		URL path =classloader.getResource(xmlilePath);
//		String pathStr = path.toString();
//		System.out.println("pathStr-->"+pathStr);
//		System.out.println("xmlilePath1-->"+xmlilePath);
//		xmlilePath = InitValidateRuleByXml.class.getClassLoader().getResource(xmlilePath).getPath();
//		System.out.println("xmlilePath2-->"+xmlilePath);
//		xmlilePath = pathStr;
		//xmlilePath = loader.getResource(xmlilePath).getFile().getAbsolutePath();
//		xmlilePath =( (HashMap<String,String>) MBaseCache.getCache().getBeanCache(CachecodeUtil.SYS_PATH_STR, CachecodeUtil.SYS_PATH)).get("201")+"/"+xmlilePath;
	//  System.out.println(xmlilePath);
//		xmlilePath = loader.getResource("/").getURL().getPath() + xmlilePath;
		Document document = reader.read(is);
		Element rootElm = document.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> resources =  rootElm.elements("resource");
		if(resources!=null && resources.size()>0){
			for(Element resource:resources){
				vrCacheMap.putAll(readRules(resource.attributeValue("path")));
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Element> ruleList = rootElm.elements("rules");
		if(ruleList==null || ruleList.size()==0){
			return vrCacheMap;
		}
		
		ValidateRuleEntity entity = new ValidateRuleEntity();
		Map<String,ValidateParm> ruleValueMap = new HashMap<String,ValidateParm>();
		for(Element ruleBase:ruleList){
			entity = new ValidateRuleEntity();
			entity.setRuleId(ruleBase.elementText("ruleId"));
			entity.setRuleName(ruleBase.elementText("ruleName"));
			if(ruleBase.element("columnList") == null)continue;
			@SuppressWarnings("unchecked")
			List<Element> ruleElementList = ruleBase.element("columnList").elements();
			if(ruleElementList==null || ruleElementList.size() == 0){
				System.out.println("没有规则集设定");
				vrCacheMap.put(entity.getRuleId(), entity);
				continue;
			}
			
			ruleValueMap = new HashMap<String,ValidateParm>();
			for(Element ruleElement:ruleElementList){
				ruleValueMap.put(ruleElement.attributeValue("columenName"), initValidateParmByXml(ruleElement));
			}
			entity.setValidateMap(ruleValueMap);
			vrCacheMap.put(entity.getRuleId(), entity);
		}
		return vrCacheMap;
	}
	
	private ValidateParm initValidateParmByXml(Element ruleElement){
		ValidateParm parm = ValidateParm.initValidateParm();
		String parmName = null;
		String parmValue = null;
		@SuppressWarnings("unchecked")
		List<Element> columnList = ruleElement.elements();
		for(Element columnElm:columnList){
			parmName = columnElm.getName();
			parmValue = columnElm.getText() ;
			if(columnElm.getText()==null || columnElm.getText().trim().isEmpty())parmValue = null;
			if("canNull".equals(parmName))parm.setCanNull(parmValue==null?false:Boolean.valueOf(parmValue));
			if("maxLength".equals(parmName))parm.setMaxLength(parmValue==null?null:Integer.valueOf(parmValue));
			if("minLength".equals(parmName))parm.setMinLength(parmValue==null?null:Integer.valueOf(parmValue));
			if("maxValue".equals(parmName))parm.setMaxValue(parmValue==null?null:Integer.valueOf(parmValue));
			if("minValue".equals(parmName))parm.setMinValue(parmValue==null?null:Integer.valueOf(parmValue));
			if("startStr".equals(parmName))parm.setStartStr(parmValue==null?null:parmValue);
			if("endStr".equals(parmName))parm.setEndStr(parmValue==null?null:parmValue);
			if("containStr".equals(parmName))parm.setContainStr(parmValue==null?null:parmValue);
			if("onlyNumber".equals(parmName))parm.setOnlyNumber(parmValue==null?false:Boolean.valueOf(parmValue));
			if("complexNumber".equals(parmName))parm.setComplexNumber(parmValue==null?null:parmValue);
			if("regexpType".equals(parmName))parm.setRegexpType(parmValue==null?null:RegexpType.returnRegByName(parmValue));
		}
		
		if(ruleElement.attributeValue("warningType")!=null && ruleElement.attributeValue("warningType").trim().length()>0){
			List<ValidateParmType> warningTypeList = new ArrayList<ValidateParmType>();
			for(String typeName:ruleElement.attributeValue("warningType").split(",")){
				warningTypeList.add(ValidateParmType.returnTypeByName(typeName));
			}
			parm.setWarningTypeList(warningTypeList);
		}
		return parm;
	}
	
	public void xmlRuleWrite(Map<String,Map<String,ValidateRuleEntity>> fmap) throws Exception{
		for(String key : fmap.keySet()){
			String xmlilePath = "rule-config/"+key;
			Map<String,ValidateRuleEntity> map = fmap.get(key);
			Document document = this.map2Document(map);
			this.xmlRuleWrite(xmlilePath, document);
		}
	}
	
	public void xmlRuleWrite(String xmlilePath, Document document) throws Exception{
		xmlilePath = loader.getResource(xmlilePath).getFile().getAbsolutePath();
		XMLWriter writer = new XMLWriter(new FileOutputStream(xmlilePath));
		try{
		writer.write(document);
		writer.flush();
		}finally{
			writer.close();
		}
	}
	
	public Document map2Document(Map<String,ValidateRuleEntity> map){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("ruleRoot");
		for(String key : map.keySet()){
			ValidateRuleEntity vre = map.get(key);
			Element rel = root.addElement("rules");
			rel.addElement("ruleId").addText(vre.getRuleId());
			rel.addElement("ruleName").addText(vre.getRuleName());
			Element colel = rel.addElement("columnList");
			Map<String, ValidateParm> mmap = vre.getValidateMap();
			if(null != mmap)
			for(String kkey : mmap.keySet()){
				ValidateParm vp = mmap.get(kkey);
				Element clel = colel.addElement("columenName");
				clel.addAttribute("columenName", kkey);
				clel.addElement("canNull").addText(""+vp.isCanNull());
				clel.addElement("maxLength").addText(""+(null==vp.getMaxLength()?"":vp.getMaxLength()));
				clel.addElement("minLength").addText(""+(null==vp.getMinLength()?"":vp.getMinLength()));
				clel.addElement("maxValue").addText(""+(null==vp.getMaxValue()?"":vp.getMaxValue()));
				clel.addElement("minValue").addText(""+(null==vp.getMinValue()?"":vp.getMinValue()));
				clel.addElement("startStr").addText(""+(null==vp.getStartStr()?"":vp.getStartStr()));
				clel.addElement("endStr").addText(""+(null==vp.getEndStr()?"":vp.getEndStr()));
				clel.addElement("containStr").addText(""+(null==vp.getContainStr()?"":vp.getContainStr()));
				clel.addElement("onlyNumber").addText(""+vp.isOnlyNumber());
				clel.addElement("regexpType").addText(""+(null!=vp.getRegexpType()?vp.getRegexpType().getName():""));
				clel.addElement("complexNumber").addText(""+(null==vp.getComplexNumber()?"":vp.getComplexNumber()));
			}
		}
		return document;
	}


	
	
	public static void main(String[] args) throws DocumentException, IOException {
		InitValidateRuleByXml rx = new InitValidateRuleByXml();
		Map<String,ValidateRuleEntity> ruleMap = rx.readFile();
		System.out.println(ruleMap.size());
//		System.out.println(ClassLoader.getSystemResource("").getPath());
//		ResourceLoader loader = new DefaultResourceLoader();
//		System.out.println(loader.getResource("/").getURL().getPath().substring(1)+"ruleData.xml");
//		System.out.println(loader.getResource("ruleData.xml").getFile().getAbsolutePath());
	}
}
