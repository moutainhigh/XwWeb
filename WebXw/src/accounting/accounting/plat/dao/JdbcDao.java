package accounting.plat.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import accounting.domain.loan.AcLnMst;
import accounting.domain.sys.CMISDomain;
import accounting.plat.CreatePrimaryKey;
import accounting.plat.core.AccountingException;
import accounting.plat.util.StringUtil;

public class JdbcDao {

	public static void close(Statement stm, ResultSet r) throws AccountingException {
		if (r != null) {
			try {
				r.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AccountingException(e);
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AccountingException(e);
			}
		}
	}

	public static ResultSet executeQuery(Statement stmt ,ResultSet rs ,String sql, Connection conn) throws AccountingException {

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}
		return rs;
	}

	public static int executeUpdate(String tableName, String condition, Connection conn) throws AccountingException {
		int result = 0;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			String sql = "update " + tableName + " set " + condition;
//			 System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(stmt,rs);
		}

		return result;
	}

	public static int update(Object object, String condition, String tableName, Connection conn) throws AccountingException {
		int result = 0;
		Statement stmt = null;
		Map<String, String> map = namesAndValues(object);
		String sql = "update " + tableName + " set ";
		for (String s : map.keySet()) {
			sql += s + "=" + map.get(s) + ",";
		}
		if (sql.endsWith(",")) {
			sql = sql.substring(0, sql.length() - 1);
		}
		if (condition != null && !condition.trim().equals("")) {
			sql += " where " + condition;
		}
//		 System.out.println(sql);
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(stmt, null);
		}
		return result;
	}

	public static int delete(String condition, String tableName, Connection conn) throws AccountingException {
		int result = 0;
		Statement stmt = null;
		String sql = null;
		if (condition != null && !condition.trim().equals("")) {
			sql = "delete from " + tableName + " where " + condition;
		} else {
			sql = "delete from " + tableName;
		}
//		 System.out.println(sql);
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(stmt, null);
		}
		return result;
	}

	public static int insert(Object object, String tableName, Connection conn) throws AccountingException {
		int result = 0;
		Statement stmt = null;
		Map<String, String> map = namesAndValues(object);
		String sql = "insert into " + tableName + "(";
		String temp = ") values (";
		for (String s : map.keySet()) {
			sql += s + ",";
			temp += map.get(s) + ",";
		}
		sql = sql.concat(temp).concat(")");
		if (sql.contains(",)")) {
			sql = sql.replace(",)", ")");
		}
//		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(stmt, null);
		}
		return result;
	}

	/**
	 * @param list
	 * @param tableName
	 * @param conn
	 * @return
	 * @throws AccountingException
	 */
	public static int insertList(List list, String tableName, Connection conn) throws AccountingException {
		int result = 0;
//		 for (int i = 0; i < list.size(); i++) {
//		 Object o = list.get(i);
//		 int temp = insert(o, tableName, conn);
//		 if (temp != 0) {
//		 result++;
//		 }
//		 }
		int listSize = list.size();
//		System.out.println("listSize的长度："+listSize);
		PreparedStatement pst = null;
		StringBuffer sqlSb = null;
		StringBuffer valueSb = null;
		String sql = null;
		try {
			Object object = list.get(0);
			Field[] fields = object.getClass().getDeclaredFields();
			sqlSb = new StringBuffer("insert into ").append(tableName).append("( ");
			valueSb = new StringBuffer("values (");
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				if (i == fields.length - 1) {
					sqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(") ");
					valueSb.append("?)");
				} else {
					sqlSb.append(StringUtil.AddUnderlineByUppercase(fieldName)).append(",");
					valueSb.append("?,");
				}

			}
			sql = sqlSb.append(valueSb).toString();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < listSize; i++) {
				Object o = list.get(i);
				for (int j = 0; j < fields.length; j++) {
					Field field = o.getClass().getDeclaredField(fields[j].getName());
					field.setAccessible(true);
					String value = String.valueOf(field.get(o) == null ? "" : field.get(o));
					// System.out.println(field.getName()+":"+value);
					// System.out.println(field.getName());
					// System.out.println(field.getName()+":"+String.valueOf(field.get(o))==null?"":String.valueOf(field.get(o)));
					// System.out.println();
					pst.setString(j + 1, value);
				}
				pst.executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(pst,null);
		}
		return result;
	}
	

	public static CMISDomain query(CMISDomain domain, String condition, String tableName, Connection conn) throws AccountingException {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from " + tableName + " where " + condition;
		 System.out.println(sql);
		try {
			Class clazz = domain.getClass();
			CMISDomain object = null;
			Field[] fields = clazz.getDeclaredFields();
			rs = executeQuery(stmt,rs,sql, conn);
			stmt = rs.getStatement();
			int count = 1;
			while (rs.next()) {
				if (count == 2) {
					throw new AccountingException("查询到的结果不唯一");
				}
				count++;
				object = (CMISDomain) clazz.newInstance();
				object=parseObj(object, fields, rs);
			}
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(stmt,rs);
		}
	}

	public static List<CMISDomain> queryList(CMISDomain domain, String condition, String orderBy, String dec, String tableName, Connection conn) throws AccountingException {
		String sql = "";
		if (condition == null || condition.trim().equals("")) {
			sql = " 1=1 order by" + orderBy + " " + dec;
		} else {
			sql = condition + "order by " + orderBy;
		}
		List list = queryList(domain, sql, tableName, conn);

		return list;
	}

	public static CMISDomain queryFirst(CMISDomain cmisDomain, String condition, String tableName, Connection conn) throws AccountingException {
		String sql = "";
		if (condition == null || condition.trim().equals("")) {
			sql = " ROWNUM=1";
		} else {
			if (!condition.trim().toUpperCase().startsWith("ORDER")) {
				sql = " ROWNUM=1 AND " + condition;
			} else {
				sql = " ROWNUM=1 " + condition;
			}
		}

		CMISDomain domain = query(cmisDomain, sql, tableName, conn);

		return domain;
	}

	public static List queryList(CMISDomain domain, String condition, String orderBy, String tableName, Connection conn) throws AccountingException {
		String sql = "";
		if (condition == null || condition.trim().equals("")) {
			sql = " 1=1 order by " + orderBy;
		} else {
			sql = condition + "order by " + orderBy;
		}
		List list = queryList(domain, sql, tableName, conn);

		return list;
	}

	public static List<CMISDomain> queryList(CMISDomain domain, String condition, String tableName, Connection conn) throws AccountingException {
		ResultSet rs = null;
		Statement stmt = null;
		List<CMISDomain> list = new ArrayList<CMISDomain>();
		String sql = "";
		if (condition != null && !condition.trim().equals("")) {
			sql = "select * from " + tableName + " where " + condition;
		} else {
			sql = "select * from " + tableName;
		}
		System.out.println(sql);
		try {
			Class clazz = domain.getClass();
			Field[] fields = clazz.getDeclaredFields();
			rs = executeQuery(stmt,rs,sql, conn);
			Constructor<Object> constructor = clazz.getConstructor(null);
			while (rs.next()) {
				CMISDomain obj = (CMISDomain) constructor.newInstance(null);
				obj = parseObj(obj, fields, rs);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally{
			close(stmt, rs);
		}
		return list;
	}

	public static CMISDomain parseObj(CMISDomain obj, Field[] fields, ResultSet rs) throws Exception {
		for (Field f : fields) {
			f.setAccessible(true);
			String str = StringUtil.AddUnderlineByUppercase(f.getName());
//			System.out.println(str);
			// 新的属性赋值操作
			if (f.getType().toString().contains("String")) {
				f.set(obj, rs.getString(str));
			} else if (f.getType().toString().contains("Date")) {
				f.set(obj, rs.getDate(str));
			} else if (f.getType().toString().contains("int")||f.getType().toString().contains("Integer")) {
				f.set(obj, rs.getInt(str));
				if(f.get(obj)==null){
					f.set(obj, 0);
				}
			} else if (f.getType().toString().contains("double")||f.getType().toString().contains("Double")) {
				f.set(obj, rs.getDouble(str));
				if(f.get(obj)==null){
					f.set(obj, 0.00);
				}
			} else if (f.getType().toString().contains("long")||f.getType().toString().contains("Long")) {
				f.set(obj, rs.getLong(str));
				if(f.get(obj)==null){
					f.set(obj, 0);
				}
			}
		}
		return obj;
	}

	public static List<CMISDomain> queryList(CMISDomain domain, String sql, Connection conn) throws AccountingException {
		ResultSet rs = null;
		Statement stmt = null;
		List<CMISDomain> list = new ArrayList<CMISDomain>();
//		 System.out.println(sql);
		try {
			Class clazz = domain.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			Field[] fields = clazz.getDeclaredFields();
			rs = executeQuery(stmt,rs,sql, conn);
			ResultSetMetaData data = rs.getMetaData();
			int count = data.getColumnCount();
			StringBuffer buffer = new StringBuffer();
			for (int i = 1; i <= count; i++) {
				buffer.append(data.getColumnName(i) + ",");
			}
			String string = buffer.toString().toUpperCase();
			Constructor<Object> constructor = clazz.getConstructor(null);
			while (rs.next()) {
				CMISDomain obj = (CMISDomain) constructor.newInstance(null);
				obj=parseObj(obj, fields, rs);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally {
			close(stmt, rs);
		} 

		return list;
	}

	public static ResultSet queryResultSet(String condition, String tableName, Connection conn) throws AccountingException {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from " + tableName + " where " + condition;
		try{
		rs = executeQuery(stmt,rs,sql, conn);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally {
			close(stmt, null);
		} 
		return rs;
	}

	// 旧的转换方法
	/*
	 * public static Map<String, String> namesAndValues(Object object) throws
	 * AccountingException { Map<String, String> fieldMap = new HashMap<String,
	 * String>(); Map<String, String> map = new HashMap<String, String>(); try {
	 * Class clazz = object.getClass(); Field[] fields =
	 * clazz.getDeclaredFields(); Method methods[] = clazz.getDeclaredMethods();
	 * for (Field f : fields) { fieldMap.put(f.getName(),
	 * f.getType().toString()); } Set<String> set = fieldMap.keySet(); for
	 * (Method m : methods) { String str = m.getName(); if
	 * (str.startsWith("get") && str.length() > 3) { Object obj =
	 * m.invoke(object, null); if (obj != null) { for (String s : set) { String
	 * value = obj.toString(); if (str.substring(3, str.length()).toLowerCase()
	 * .equals(s.toLowerCase())) { String temp = fieldMap.get(s); if
	 * (temp.contains("String") || temp.contains("Date")) { value = "'" + value
	 * + "'"; } map.put(StringUtil.AddUnderlineByUppercase(s), value); } } } } }
	 * } catch (Exception e) { e.printStackTrace(); throw new
	 * AccountingException(e); } return map; }
	 */

	// 改进后的转换方法
	public static Map<String, String> namesAndValues(Object object) throws AccountingException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Class clazz = object.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				String type = f.getType().toString();
				if (f.get(object) != null) {
					String value = f.get(object).toString();
					if (type.contains("String") || type.contains("Date")) {
						value = "'" + value + "'";
					}
					map.put(StringUtil.AddUnderlineByUppercase(f.getName()), value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}
		return map;
	}

	public static void excuteUpdateSql(String sql, Connection conn) throws AccountingException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally {
			close(stmt, null);
		} 
	}
	/**
	 * @描述 回盘专用
	 */
	public static int updateSql(String sql, Connection conn) throws AccountingException {
		int result = 0;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally {
			close(stmt, null);
		}
		return result; 
	}
	/**
	 * 更新list
	 * 
	 * @param list
	 * @param condition
	 *            字符串,更新条件,例如"ac_id,ac_seqn",则会根据每条记录的acId和acSeqn字段值为条件对list进行更新
	 * @param tableName
	 * @param conn
	 * @return
	 * @throws AccountingException
	 */
	public static void updateList(List<CMISDomain> list, String condition, String tableName, Connection conn) throws AccountingException {
		Statement pst = null;
		try {
			pst = conn.createStatement();
			String[] keys = condition.split(",");
			for (int i = 0; i < list.size(); i++) {
				CMISDomain domain = list.get(i);
				Map<String, String> map = namesAndValues(domain);
				String conditionStr = new String();
				for (int j = 0; j < keys.length; j++) {
					conditionStr = conditionStr + keys[j] + "=" + map.get(keys[j]) + " and ";
				}
				conditionStr = conditionStr.substring(0, conditionStr.length() - 4);
				String sql = "update " + tableName + " set ";
				for (String s : map.keySet()) {
					sql += s + "=" + map.get(s) + ",";
				}
				if (sql.endsWith(",")) {
					sql = sql.substring(0, sql.length() - 1);
				}
				if (conditionStr != null && !conditionStr.trim().equals("")) {
					sql += " where " + conditionStr;
				}
				System.out.println(sql);
				pst.addBatch(sql);
			}
			pst.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}finally{
			close(pst,null);
		}
	}

	public static void updateAndInsert(CMISDomain domain, String condition, String tableName, Connection conn) throws AccountingException {
		int result = update(domain, condition, tableName, conn);
		if (result == 0) {
			insert(domain, tableName, conn);
		}
	}

	public static void updateAndInsert(List<CMISDomain> list, String condition, String tableName, Connection conn) throws AccountingException {
		for (int i = 0; i < list.size(); i++) {
			CMISDomain domain = list.get(i);
			updateAndInsert(domain, condition, tableName, conn);
		}
	}

	// public
	public static void main(String[] args) throws Exception {
		// List list = JdbcDao.queryList(new AcLnPayPln(), "ac_id='1214947'",
		// "ac_ln_pay_pln", ConnectionManager
		// .getConnection());
		// JdbcDao.insertList(list, "ac_ln_pay_pln", null);
		Connection conn = CreatePrimaryKey.getConnection();
//		AcLnMst acLnMst = (AcLnMst) JdbcDao.queryFirst(new AcLnMst(), "LOAN_NO='DKYCJJ2011102001' ORDER BY LOAN_NO DESC", "AC_LN_MST", conn);
//		System.out.print(acLnMst.getLoanNo());
		
		List acLnMsts=JdbcDao.queryList(new AcLnMst(), "SELECT * FROM AC_LN_MST", conn);
		System.out.print(((AcLnMst)acLnMsts.get(0)).getLoanNo());
		
		// List list = new ArrayList();
		// for (int i = 1; i < 5; i++) {
		// AcLnLo a = new AcLnLo();
		// a.setAcId(i + "");
		// a.setAcSeq("0");
		// a.setLoanNo(i + "");
		// a.setPerdNo(i);
		// list.add(a);
		// }
		// updateList(list, "loan_no,perd_no", "AC_LN_LO", conn);

		// String sql="loan_no='uior04'";
		// AcLnLo acLnLo=(AcLnLo) query(new AcLnLo(), sql, "ac_ln_lo", conn);
		// acLnLo.setAcmDt("2011-01-13");
		// acLnLo.setAcId("2011011301");
		// acLnLo.setAcSeqn("0");
		// acLnLo.setPerdNo(1);
		// acLnLo.setLoanNo("uior40");
		// updateAndInsert(acLnLo, "loan_no='uior40'", "ac_ln_lo", conn);
	}
}
