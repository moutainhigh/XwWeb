package app.util.message.conversion.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import app.util.message.conversion.mapping.entity.MappingEntity;

/**
 * 主要目的为生成报文字段的映射配置
 * 读取配置文件，返回一个映射容器
 * 映射容器应当为一个有序的字段集合。集合长度应等于报文中每条记录的字段个数。
 * 配置文件可能为多种，比如xml或者txt
 * 
 * 配置集合应当保存在缓存中，有一个映射集合。
 *
 */
public abstract class MappingHandler {
	protected static Map<String,MappingEntity> mappingConfig = null;
	
	/**
	 * 根据映射编号取出字段有序集合
	 * @param mappingId
	 * @return
	 */
	public abstract MappingEntity findMappingEntityById(String mappingId);
	
	/**
	 * 根据映射文件初始化映射集合缓存
	 * @return
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public abstract Map<String,MappingEntity> initMappingConfig() throws IOException ;
	
	/**
	 * 刷新缓存
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void refreshConfig() throws IOException{
		if(mappingConfig!=null)mappingConfig.clear();
		mappingConfig = initMappingConfig();
	}
}
