package accounting.plat ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import accounting.plat.core.AccountingException;
import app.util.DBUtils;

/**
 * 生成主键类
 * 
 * 
 */
public class CreatePrimaryKey {
	
	     

	/**
	 * 获得主键
	 * 
	 * @param atype
	 *            编码类型
	 * @param owner
	 *            编码拥有者
	 * @param length
	 *            编码长度
	 * @return primaryKey 主键(atype+owner+编码)
	 */
	private static String getSerNum(String atype, String owner, int length) throws AccountingException {
		StringBuffer primaryKey = new StringBuffer() ;
		Connection conn = getConnection() ;

		String sql = "select  * from ac_cmis_AUTOCODE where atype='" + atype + "' and owner='" + owner + "' for update" ;

		try {
			conn.setAutoCommit(false) ;
			Statement stmt = conn.createStatement() ;
			ResultSet rs = stmt.executeQuery(sql) ;
			if (rs.next()) {
				Integer initcycle = Integer.parseInt(rs.getString(3)) ;
				Long curSernum=Long.valueOf(rs.getString(4));
				Integer zeroFlg = Integer.parseInt(rs.getString(5)) ;
				curSernum += initcycle ;
				String update = "update ac_cmis_AUTOCODE set cur_sernum=" + curSernum ;
				stmt.executeUpdate(update) ;
				StringBuffer temp = new StringBuffer(curSernum.toString()) ;
				if (temp.length() <= length) {
					int tempLength = length - temp.length() ;
					for (int i = 0 ; i <tempLength ; i++) {
						temp.insert(0, 0) ;
					}
				} else {
					temp = temp.delete(0, temp.length() - length) ;
				}
				primaryKey.append(atype).append(owner).append(temp) ;
			}
			conn.commit() ;
			rs.close() ;
			stmt.close() ;
			conn.close() ;
		} catch (Exception e) {
			try {
				conn.rollback() ;
			} catch (SQLException e1) {
				e1.printStackTrace() ;
				throw new AccountingException(e) ;
			}
			e.printStackTrace() ;
			throw new AccountingException(e) ;
		}
		return primaryKey.toString() ;
	}

	/**
	 * 获得主键
	 * 
	 * @param atype
	 *            编码类型
	 * @param owner
	 *            编码拥有者
	 * @param length
	 *            编码长度
	 * @return primaryKey 主键(单纯编码)
	 */
	public static String getSimpleSerNum(String atype, String owner, int length) throws AccountingException {
		StringBuffer primaryKey = new StringBuffer() ;
		Connection conn = getConnection() ;

		String sql = "select  * from ac_cmis_AUTOCODE where atype='" + atype + "' and owner='" + owner + "' for update" ;

		try {
			conn.setAutoCommit(false) ;
			Statement stmt = conn.createStatement() ;
			ResultSet rs = stmt.executeQuery(sql) ;
			if (rs.next()) {
				Integer initcycle = Integer.parseInt(rs.getString(3)) ;
				Long curSernum=Long.valueOf(rs.getString(4));
				//System.out.println(curSernum) ;
				//Integer zeroFlg = Integer.parseInt(rs.getString(5)) ;
				curSernum += initcycle ;
				String update = "update ac_cmis_AUTOCODE set cur_sernum=" + curSernum +" where atype='" + atype + "' and owner='" + owner+"'";
				System.out.println(update);
				stmt.executeUpdate(update) ;
				StringBuffer temp = new StringBuffer(curSernum.toString()) ;
				if (temp.length() <= length) {
					int tempLength = length - temp.length() ;
					for (int i = 0 ; i <tempLength ; i++) {
						temp.insert(0, 0) ;
					}
				} else {
					temp = temp.delete(0, temp.length() - length) ;
				}
				primaryKey.append(temp) ;
			}
			conn.commit() ;
			rs.close() ;
			stmt.close() ;
			conn.close() ;
		} catch (Exception e) {
			try {
				conn.rollback() ;
			} catch (SQLException e1) {
				e1.printStackTrace() ;
				throw new AccountingException(e) ;
			}
			e.printStackTrace() ;
			throw new AccountingException(e) ;
		}
		return primaryKey.toString() ;
	}
	
	private static String getSimpleSerNum(String atype, String owner, int length,Connection conn) throws AccountingException{
		StringBuffer primaryKey = new StringBuffer() ;

		String sql = "select  * from ac_cmis_AUTOCODE where atype='" + atype + "' and owner='" + owner + "' for update" ;

		try {
			conn.setAutoCommit(false) ;
			Statement stmt = conn.createStatement() ;
			ResultSet rs = stmt.executeQuery(sql) ;
			if (rs.next()) {
				Integer initcycle = Integer.parseInt(rs.getString(3)) ;
				Long curSernum=Long.valueOf(rs.getString(4));
				//System.out.println(curSernum) ;
				Integer zeroFlg = Integer.parseInt(rs.getString(5)) ;
				curSernum += initcycle ;
				String update = "update ac_cmis_AUTOCODE set cur_sernum=" + curSernum +"where atype='" + atype + "' and owner='" + owner+"'";
				stmt.executeUpdate(update) ;
				StringBuffer temp = new StringBuffer(curSernum.toString()) ;
				if (temp.length() <= length) {
					int tempLength = length - temp.length() ;
					for (int i = 0 ; i <tempLength ; i++) {
						temp.insert(0, 0) ;
					}
				} else {
					temp = temp.delete(0, temp.length() - length) ;
				}
				primaryKey.append(temp) ;
			}
			conn.commit() ;
			rs.close() ;
			stmt.close() ;
			conn.close() ;
		} catch (Exception e) {
			try {
				conn.rollback() ;
			} catch (SQLException e1) {
				e1.printStackTrace() ;
				throw new AccountingException(e) ;
			}
			e.printStackTrace() ;
			throw new AccountingException(e) ;
		}
		return primaryKey.toString() ;
	}
	/**
	 * 获得连接
	 */
	public static Connection getConnection() {
		Connection conn = null ;
		try {
//			 String className = "oracle.jdbc.driver.OracleDriver";
//			 Class.forName(className);
//			 String url = "jdbc:oracle:thin:@192.168.1.11:1521:cmis";
//			 String ID = "cmis";
//			 String pwd = "cmis";
//			 conn = DriverManager.getConnection(url, ID, pwd);
//			ConnectionPool cp = ConnectionPool.getInstance() ;
			conn = DBUtils.getConn();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return conn ;
	}

	public static void main(String[] args) {
		long l1 = System.currentTimeMillis() ;
		// for (int i = 0 ; i < 100 ; i++) {
		// CreatePrimaryKey cpk = new CreatePrimaryKey() ;
//		System.out.println(Long.MAX_VALUE) ;
		String s ;
		try {
//			s = getSerNum("jj", "all", 50) ;
			s=getSimpleSerNum("jj", "all", 5);
			System.out.println(s) ;
		} catch (AccountingException e) {
			e.printStackTrace() ;
		}

		// }
		// long l2 = System.currentTimeMillis() ;
		// System.out.println(l2 - l1) ;
	}
}
