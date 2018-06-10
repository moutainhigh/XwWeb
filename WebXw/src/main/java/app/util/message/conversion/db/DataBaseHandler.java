package app.util.message.conversion.db;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import app.util.message.conversion.mapping.entity.MappingEntity;

/**
 * 主要负责与数据库的交互
 * 1.将组合好的数据（javabean，map）传入接口中，并插入数据库中
 * 2.根据所给的需求（sql），查询出数据，拼凑成Map或者javabean返回。
 * @param <E>
 *
 */
public interface DataBaseHandler{
	/**
	 * 将数据插入进数据库中
	 * 
	 * @param table
	 * @param valueMap
	 * @return
	 */
	public boolean insertDataIntoDb(MappingEntity entity);
	public boolean insertDataIntoDb(List<MappingEntity> entityList);
	public String insertDataIntoDbStr(List<MappingEntity> entityList,String str);
	
	/**
	 * 将查询出来的数据封装到Map映射中
	 * @param sql
	 * @return
	 */
	public List<Map<String, String>> exportDataFromDb(String sql);
	
	public List<Map<String, String>> exportDataFromDb(List<?> objList) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 将查询出来的数据封装到实体类中
	 * @param sql
	 * @param clazz
	 * @return
	 */
//	public <T> List<T>  exportDataFromDbToObjet(String sql, T clazz);
	
	/**
	 * 进件过程中，出现数据格式不正确等错误，把ln_acct_mid,ln_gage_mid,ln_rel_mid表中app_id为空的数据删除
	 */
	public boolean del(String tableName);
	
}
