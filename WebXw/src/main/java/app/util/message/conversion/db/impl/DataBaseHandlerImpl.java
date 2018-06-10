package app.util.message.conversion.db.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.util.DBUtils;
import app.util.message.conversion.db.DataBaseHandler;
import app.util.message.conversion.mapping.entity.MappingColumn;
import app.util.message.conversion.mapping.entity.MappingEntity;
import app.util.message.conversion.test.TestTh;
import app.util.message.conversion.type.DataType;

public class DataBaseHandlerImpl implements DataBaseHandler{

	
	@Override
	public boolean del(String tableName) {	
		PreparedStatement ps = null;
		Connection conn = null;
		String sql ="delete from "+tableName+" where app_id is null";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			System.out.println(sql);
			ps.executeUpdate();
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
		
	}


	@Override
	public boolean insertDataIntoDb(MappingEntity entity) {
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = checkSql(entity);
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.executeBatch();
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
	}
	
	
	@Override
	public boolean insertDataIntoDb(List<MappingEntity> entityList) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(checkSqlForBatch(entityList.get(0)));
			MappingColumn mc = new MappingColumn();
			
			for(MappingEntity entity:entityList){
				int addIndex = 0;
				for(int i=0; i<entity.getColumnList().size();i++){
					mc = entity.getColumnList().get(i);

						if(mc.getDataType() == DataType.NUMBER){
							ps.setDouble(i+1-addIndex, Double.valueOf(mc.getDataValue()));
						}else if(mc.getDataType() == DataType.SEQUENCE){
							addIndex++;
							continue;
						}else{
							ps.setString(i+1-addIndex, mc.getDataValue());
						}

					//System.out.println("i="+(i+1-addIndex)+",value = "+mc.getDataValue());
				}
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
		
	}
	
	@Override
	public String insertDataIntoDbStr(List<MappingEntity> entityList,String str) {
		Map<String,String> pactRep = new HashMap<String, String>();
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(checkSqlForBatch(entityList.get(0)));
			MappingColumn mc = new MappingColumn();
			
			for(MappingEntity entity:entityList){
				int addIndex = 0;
				for(int i=0; i<entity.getColumnList().size();i++){
					mc = entity.getColumnList().get(i);
					//&&pactRep.get(mc.getDataValue()
					if("0005".equals(str)&&"PACT_NO".equals(mc.getColumnName())){
						if("1".equals(pactRep.get(mc.getDataValue()))){
							pactRep.clear();
							return "repeat";
						}else{
							pactRep.put(mc.getDataValue(), "1");
						}
					}
					if("0010".equals(str)&&"PRE_PACT_NO".equals(mc.getColumnName())){
						if("1".equals(pactRep.get(mc.getDataValue()))){
							pactRep.clear();
							return "repeat";
						}else{
							pactRep.put(mc.getDataValue(), "1");
						}
					}
					if(mc.getDataType() == DataType.NUMBER){
						ps.setDouble(i+1-addIndex, Double.valueOf(mc.getDataValue()));
					}else if(mc.getDataType() == DataType.SEQUENCE){
						addIndex++;
						continue;
					}else{
						ps.setString(i+1-addIndex, mc.getDataValue());
					}
					//System.out.println("i="+(i+1-addIndex)+",value = "+mc.getDataValue());
				}
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			pactRep.clear();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			pactRep.clear();
			return "false";
		} finally {
			pactRep=null;
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
		
	}
	
	private String checkSql(MappingEntity entity){
		StringBuffer sql = new StringBuffer();
		StringBuffer columnStr = new StringBuffer();
		StringBuffer valueStr = new StringBuffer();
		sql.append(" insert into ").append(entity.getTableName()).append(" (");
		
		for(MappingColumn mc : entity.getColumnList()){
			columnStr.append(mc.getColumnName()).append(",");
			if(mc.getDataType()==DataType.VARCHAR)valueStr.append("'"+mc.getDataValue()+"'");
			else if(mc.getDataType() == DataType.SEQUENCE)continue;
			else valueStr.append(mc.getDataValue());
			valueStr.append(",");
		}
		columnStr.deleteCharAt(columnStr.lastIndexOf(","));
		valueStr.deleteCharAt(valueStr.lastIndexOf(","));
		
		sql.append(columnStr).append(") values (").append(valueStr).append(")");
		
		return sql.toString();
	}
	
	private String checkSqlForBatch(MappingEntity entity){
		StringBuffer sql = new StringBuffer();
		StringBuffer columnStr = new StringBuffer();
		StringBuffer valueStr = new StringBuffer();
		sql.append(" insert into ").append(entity.getTableName()).append(" (");
		
		MappingColumn mc = null;
		for(int i=0;i< entity.getColumnList().size();i++){
			mc = entity.getColumnList().get(i);
			if(mc.getDataType() == DataType.SEQUENCE){
				columnStr.append(mc.getColumnName()).append(",");
				valueStr.append(entity.getSequenceName()+".nextVal").append(",");
			}else{
				columnStr.append(mc.getColumnName()).append(",");
				valueStr.append("?,");
			}
			
		}
		columnStr.deleteCharAt(columnStr.lastIndexOf(","));
		valueStr.deleteCharAt(valueStr.lastIndexOf(","));
		
		sql.append(columnStr).append(") values (").append(valueStr).append(")");
		
		System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public List<Map<String, String>> exportDataFromDb(String sql) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet result = null;
		List<Map<String, String>> resultList = null;
		Map<String, String> resultMap = null;
		ResultSetMetaData rsmd = null;
		try {
			resultList = new ArrayList<Map<String, String>>();
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			rsmd = result.getMetaData();
			
			int count =rsmd.getColumnCount();
			String[] colNames = new String [count];
	        for(int i=1;i<=count;i++){
	        	colNames[i-1]=rsmd.getColumnName(i);
	        }
			while(result.next()){
				resultMap = new HashMap<String, String>();
				for(int i=0;i<colNames.length;i++){
	                resultMap.put(colNames[i], String.valueOf(result.getObject(colNames[i])));
	            }
				resultList.add(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
		return resultList;
	}

	public <T>List<T> exportDataFromDbToObjet(String sql, T clazz ) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet result = null;
		List<T> resultList = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			resultList = new ArrayList<T>();
			
			Field[] fields = clazz.getClass().getDeclaredFields();
			Method setMethod = null;
			String fieldName = null;
			
			while(result.next()){
				for(Field field: fields){
					fieldName = field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
	                setMethod = clazz.getClass().getMethod("set"+fieldName, field.getType());
	                
	                if (field.getGenericType().toString().equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
	                	setMethod.invoke(clazz, result.getString(field.getName()));
	                }
	                if (field.getGenericType().toString().equals("class java.lang.Integer")) {
	                	setMethod.invoke(clazz, result.getInt(field.getName()));
	                }
	                if (field.getGenericType().toString().equals("class java.lang.Double")) {
	                	setMethod.invoke(clazz,result.getDouble(field.getName()));
	                }
	            }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeStatement(ps);
			DBUtils.closeConnection(conn);
		}
		
		return resultList;
	}
	
	@Override
	public List<Map<String, String>> exportDataFromDb(List<?> objList) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = null;
		Method getMethod = null;
		String fieldName = null;
		
		Field[] fields = null;
		for(Object obj:objList){
			fields = obj.getClass().getDeclaredFields();
			resultMap = new HashMap<String, String>();
			for(Field field:fields){
				fieldName = field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
				getMethod = obj.getClass().getMethod("get"+fieldName);
				resultMap.put(field.getName().toLowerCase(), String.valueOf(getMethod.invoke(obj)) );
			}
			resultList.add(resultMap);
		}
		
		return resultList;
	}
	
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//		List<MappingColumn> columnList = new ArrayList<MappingColumn>();
//		columnList.add(new MappingColumn("testColumn1",DataType.VARCHAR,"testVar"));
//		columnList.add(new MappingColumn("testColumn2",DataType.VARCHAR,"testVar"));
//		columnList.add(new MappingColumn("testColumn3",DataType.NUMBER,"1231213"));
//		columnList.add(new MappingColumn("testColum4",DataType.VARCHAR,"testVar"));
//		columnList.add(new MappingColumn("tesdas1",DataType.NUMBER,"333.123"));
//		columnList.add(new MappingColumn("age",DataType.VARCHAR,"testVar"));
//		columnList.add(new MappingColumn("salary",DataType.NUMBER,"222.1234"));
//		columnList.add(new MappingColumn("hahah",DataType.VARCHAR,""));
//		columnList.add(new MappingColumn("aaaaaa",DataType.VARCHAR,"testVar"));
//		MappingEntity entity = new MappingEntity("testTable",columnList);
		
		DataBaseHandlerImpl dhi = new DataBaseHandlerImpl();
//		System.out.println(dhi.checkSql(entity));
		List<TestTh> testTsList = new ArrayList<TestTh>();
		testTsList.add(new TestTh("000001","idno0001","name00001",10,10.01));
		testTsList.add(new TestTh("000002","idno0002","name00002",11,11.01));
		testTsList.add(new TestTh("000003","idno0003","name00003",12,12.01));
		testTsList.add(new TestTh("000004","idno0004","name00004",13,13.01));
		testTsList.add(new TestTh("000005","idno0005","name00005",14,14.01));
		testTsList.add(new TestTh("000006","idno0006","name00006",15,15.01));
		System.out.println(dhi.exportDataFromDb(testTsList));
		
	}

}
