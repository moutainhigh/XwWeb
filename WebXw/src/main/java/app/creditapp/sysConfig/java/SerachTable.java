package app.creditapp.sysConfig.java;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import app.oscache.MBaseCache;
import app.util.ConfigUtil;
import app.util.DBUtils;


public class SerachTable {
	
	public static String[] names = null;
	public static String tableName;
	public static Map<String,String> mapRel;
	
	/**
	 * @description 废弃！！！！！
	 * @param sql
	 * @param obj
	 * @param con
	 * @return
	 * @throws Exception
	 * @version 1.0
	 */
	public static Map<String,String> checkLog(String sql,Object obj,Connection con) throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		String result;
		String[] keyArr = sql.split("WHERE")[1].trim().toUpperCase().split("AND");
		String strKey = "";
		for(int i=0;i<keyArr.length;i++){
			if(!keyArr[i].equals("")){
				strKey += keyArr[i].split("=")[0].trim() + ",";
			}
		}
		strKey = strKey.substring(0,strKey.length()-1);
		String[] arrayKey = strKey.split(","); 
		sql = sql.toUpperCase();
		
		String tablename = sql.split("SET")[0].split("UPDATE")[1].trim().toUpperCase(); 
		boolean tableifexist = MBaseCache.getCache().getBeanCacheForLogTable(tablename);
		if(!tableifexist){
			return null;
		}
		
		if(sql.indexOf("WHERE")<0){
			result = "error";
		}else{
			Map<String, String> mapNew = getNewObject(obj);
			Map<String, String> mapOld = getOldObject(sql,obj,con);
			if(mapOld == null){
				result = "error";
			}else{
				result = checkNewAndOld(arrayKey,mapOld,mapNew,mapRel);
			}
		}
		resultMap.put("tableInfo", tableName);
		resultMap.put("detail", result);
		return resultMap;
	}
	/**
	 * @description 废弃！！！！！！！！！
	 * @param arrayKey
	 * @param mapOld
	 * @param mapNew
	 * @param mapRel
	 * @return
	 * @version 1.0
	 */
	public static String checkNewAndOld(String[] arrayKey,Map<String, String> mapOld,Map<String, String> mapNew,Map<String,String> mapRel){
		String key = "";
		String result = "";
		String mold = "";
		String mnew = "";
			for(int i=0;i<mapNew.size();i++){
				if(mapOld.get(names[i])!=null)
					mold = mapOld.get(names[i]).toString();
				else
					mold = "";
				if(mapNew.get(names[i])!=null)
					mnew = mapNew.get(names[i]).toString();
				else
					mnew = "";
				if(!mold.equals(mnew) && !"".equals(mnew)){
					result = result + names[i] + "-" + mold + "-" + mnew + ";";
				}
			}
			for(int i=0;i<arrayKey.length;i++){
				key = key + arrayKey[i] + "-" + mapOld.get(arrayKey[i].replace("_", "")) + "-索引;";
			}
			result = key + result;
		return result;
	}
	/**
	 * @description 废弃！！！！
	 * @param sqlstr
	 * @param obj
	 * @param con
	 * @return
	 * @throws Exception
	 * @version 1.0
	 */
	public static Map<String,String> getOldObject(String sqlstr,Object obj,Connection con) throws Exception{
		//update table_a set b=*,a=* where c = '' and d=''
		String[] arr = sqlstr.split("WHERE");
		//update table_a set b=*,a=*
		String strBefore = arr[0];
		//where c = ''
		String strAfter = arr[1];
		String[] strs  = strAfter.split("AND");
		
		StringBuffer condition = new StringBuffer(); 
		for(String str:strs){
			str = str.toLowerCase().trim();
			String getname = ConfigUtil.columnName2GetMethodName(str.split("=")[0].trim());
			Method method = obj.getClass().getMethod(getname, new Class[]{});
			condition.append(str.split("=")[0].trim()+"= '"+method.invoke(obj, new Object[]{})+"' and ");
		}
		
		tableName = strBefore.split("SET")[0].split("UPDATE")[1].trim().toUpperCase();
		mapRel = getColName(tableName,con);
		tableName = tableName.trim() + "-" + getTblName(tableName,con);
		String selectSql = "select * from "+strBefore.split("SET")[0].split("UPDATE")[1].trim().toUpperCase()+" where "+condition.substring(0, condition.length()-5);
		Statement st = null;
		ResultSet rs = null;
		Map<String,String> map = null;
		ResultSetMetaData rsmd=null;
		try {
			if (con.isClosed()) {
				throw new IllegalStateException("error.unexpected");
			}
			st = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(selectSql);
			rsmd = rs.getMetaData();
			names = new String[rsmd.getColumnCount()]; 
			String columName;
			String columNameExt;
			int m = 0;
			while(rs.next()){
				map = new HashMap<String,String>();
				for(int i=0;i<rsmd.getColumnCount();i++){
					columName = rsmd.getColumnLabel(i+1);
					columNameExt = rsmd.getColumnLabel(i+1).replace("_","");
					names[i] = columNameExt;
					String value = format(rs.getString(columName));
					map.put(columNameExt,value);
				}
				m++;
			}
			if(m>1||m==0){
				map = null;
				return map;
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtils.closeStatement(st);
		}
		return map;
	}
	/**
	 * @description 获得该对象要更新的值[name,value]  废弃！！！！
	 * @param model
	 * @return
	 * @throws Exception
	 * @version 1.0
	 */
	public static Map<String,String> getNewObject(Object model) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		Method[] methods = model.getClass().getDeclaredMethods();
		for(int i =0 ;i<methods.length;i++){
            if(methods[i].getName().startsWith("get")){
            	String objectName = methods[i].getName();
                Object object = methods[i].invoke(model, null);
                String value = format(object);
                if( value!=null && !"".equals(value) ){
                	 map.put(objectName.substring(3).toUpperCase(),value);
                }
            }
         }
		return map;
	}
	
	public static String getTblName(String tableName,Connection con) throws Exception{
		String result = "";
		String sql = "select comments from user_tab_comments where table_name='"+tableName.trim()+"'";
		//System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			if (con.isClosed()) {
				throw new IllegalStateException("error.unexpected");
			}
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				result = rs.getString("comments");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtils.closeStatement(st);
		}
		return result;
	}
	
	public static Map<String,String> getColName(String tableName,Connection con){
		Map<String,String> resultMap = new HashMap<String,String>();
		String sql = "select column_name,comments from user_col_comments where table_name='"+tableName.trim()+"'";
		//System.out.println(sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			if (con.isClosed()) {
				throw new IllegalStateException("error.unexpected");
			}
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				resultMap.put(rs.getString("column_name"), rs.getString("comments"));
			}
		} catch (Exception e) {
			
		} finally {
			DBUtils.closeStatement(st);
		}
		return resultMap;
	}
	
	public static String format(Object object){
		String result = "";
		String val = String.valueOf(object);
		if(val==null||"0".equals(val)||"0.0".equals(val)||"null".equals(val)){
			result = "";
		}else{
			result = val;
		}
		return result;
	}
}
