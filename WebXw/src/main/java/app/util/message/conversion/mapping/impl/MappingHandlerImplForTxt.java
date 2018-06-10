package app.util.message.conversion.mapping.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.util.message.conversion.mapping.MappingHandler;
import app.util.message.conversion.mapping.entity.MappingColumn;
import app.util.message.conversion.mapping.entity.MappingEntity;
import app.util.message.conversion.type.DataType;

public class MappingHandlerImplForTxt extends MappingHandler{
	final static Logger logger = LoggerFactory.getLogger(MappingHandlerImplForTxt.class);
	
	private String filePath;
	private final String splitStr = "\\|\\+\\|";
	private InputStream inputStream;
	public MappingHandlerImplForTxt(){
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		inputStream = classloader.getResourceAsStream("mappingConfig.properties");
//		if(resource!=null&& resource.exists()){
//			try {
//				filePath = resource.getFile().getAbsolutePath();
//			} catch (IOException e) {
//				filePath = "";
//			}
//		}else{
//			filePath="";
//		}
////		this.filePath = ClassLoader.getSystemResource("").getPath()+"mapingConfig.text";
	}
	
	public MappingHandlerImplForTxt(String filePath){
		this.filePath = filePath;
	}
	
	@Override
	public MappingEntity findMappingEntityById(String mappingId) {
		if(MappingHandler.mappingConfig.containsKey(mappingId))return MappingHandler.mappingConfig.get(mappingId);
		else return null;
	}

	@Override
	public Map<String, MappingEntity> initMappingConfig() throws IOException {
		// File file = new File(filePath);
		// if(!file.exists())throw new FileNotFoundException("没有找到相关映射配置文件:"+filePath);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		inputStream = classloader.getResourceAsStream("mappingConfig.properties");
		   BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream)); 
		Map<String, MappingEntity> mappingMap = new HashMap<String, MappingEntity>();
		String messageForLine = null;
		List<MappingColumn> columnList = null;
		
		try {
			while((messageForLine = bReader.readLine())!=null){
				if(messageForLine.indexOf("=")==-1){
					System.out.println("读取报文映射配置文件时出现错误,格式不正确："+messageForLine);
					continue;
				}
				String[] mappingArrays = messageForLine.split("=");//=号划分左右两边，左边包括mapID和表名，右边包括字段匹配和附加属性
				//先获取附加属性
				String[] tableAttribute = mappingArrays[1].split(":");
				String returnColumnName = null;
				String sequenceName = null;
				String ignoreAll = null;
				if(tableAttribute!=null && tableAttribute.length > 1){
					mappingArrays[1] = tableAttribute[0];
					if(tableAttribute[1].indexOf("{")!=-1 && tableAttribute[1].indexOf("}")!=-1){
						tableAttribute[1] = tableAttribute[1].replaceAll("\\{", "").replaceAll("\\}", "");
						for(String entity:tableAttribute[1].split(splitStr)){
							String[] entityArrays = entity.split("-");
							if("sequenceName".equalsIgnoreCase(entityArrays[0])){
								sequenceName = entityArrays[1];
							}
							if("returnColumn".equalsIgnoreCase(entityArrays[0])){
								returnColumnName = entityArrays[1];
							}
							if("ignoreAll".equalsIgnoreCase(entityArrays[0])){
								ignoreAll = entityArrays[1];
							}
						}
					}
				}
				//对表格对应字段进行解析
				columnList = new ArrayList<MappingColumn>();
				for(String entity:mappingArrays[1].split(splitStr)){
					String[] entityArrays = entity.split("-");
					if(entityArrays.length == 1){
						//默认VARCAHR类型
						columnList.add(new MappingColumn(entityArrays[0],DataType.VARCHAR));
					}else{
						MappingColumn mc = new MappingColumn(entityArrays[0],DataType.getDataType(entityArrays[1]));
						if(DataType.getDataType(entityArrays[1]) == DataType.DEFAULT){
							//如果是默认值，则存入默认值中
							try {
								mc.setDefaultValue(entityArrays[2]);
							} catch (ArrayIndexOutOfBoundsException e) {
								logger.error("默认值字段【"+entityArrays[0]+"】没有进行正确的配置");
								e.printStackTrace();
							}
						}
						columnList.add(mc);
					}
					
				}
				String[] idArrays = mappingArrays[0].split(":");//分号前为ID，分号后为表名
				MappingEntity entity = new MappingEntity(idArrays[1],columnList,sequenceName,returnColumnName);
				mappingMap.put(idArrays[0], entity);
			}
		} finally{
			if(bReader!=null)bReader.close();
		}
		return mappingMap;
	}
	
	
	public static void main(String[] args) throws IOException {
		String readPath = "C:/work/otherSpace/me2014/CMSWeb/src/main/java/app/util/message/conversion/test/mappingConfig1.txt";
		MappingHandler mh = new MappingHandlerImplForTxt(readPath);
		mh.refreshConfig();
		System.out.println(mh.findMappingEntityById("0001"));
	}

}
