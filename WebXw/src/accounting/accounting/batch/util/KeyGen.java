package accounting.batch.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import accounting.plat.PUBConstant;

/**
 * 流水号管理器
 *
 */
public class KeyGen {

	private static KeyGen kg = new KeyGen();
	private long curNo = 0;		// 当前流水号
	private long endNo = 0;		// 持有的最大流水号
	private int cycle = 100;	// 持有流水号数量
	
	private KeyGen(){}
	
	/**
	 * 得到流水号管理器工厂方法
	 * @return 流水号管理器对象
	 */
	public static KeyGen getKeyGen(){
		return kg;
	}
	
	/**
	 * 设置管理器持有流水号数量,即每次从数据库中取到的流水号数量
	 * @param cycle 持有流水号数量
	 */
	private void setCycle(int cycle){
		this.cycle = cycle;
	}
	
	/**
	 * 得到持有流水号数量
	 * @return 持有流水号数量
	 */
	public int getCycle(){
		return cycle;
	}
	
//	/**
//	 * 得到流水号
//	 * @param conn	获取流水号的专用连接
//	 * @return	当前流水号
//	 * @throws Exception
//	 */
//	public synchronized String getTraceNo(Connection conn) throws Exception{
//		if(curNo==endNo){
//			curNo = getSimpleSerNum(cycle,PUBConstant.TRACENOATYPE, PUBConstant.OWNER, 18,conn) ;
//			endNo = curNo + cycle;
//		}
//		return String.valueOf(++curNo);
//	}
	
	/**
	 * 从数据库中得到流水号,并根据持有流水号数量对表更新
	 * @param cycle	持有流水号数量
	 * @param atype 流水号类型
	 * @param owner	流水号所有者
	 * @param length 流水号长度
	 * @param conn	数据库连接对象
	 * @return	当前流水号
	 * @throws Exception
	 */
	public static long getSimpleSerNum(int cycle,String atype, String owner, int length, Connection conn) throws Exception {
		StringBuffer primaryKey = new StringBuffer() ;

		String sql = "select cur_sernum from AC_CMIS_AUTOCODE where atype='" + atype + "' and owner='" + owner + "' for update" ;

		try {
			conn.setAutoCommit(false) ;
			Statement stmt = conn.createStatement() ;
			ResultSet rs = stmt.executeQuery(sql) ;
			if (rs.next()) {
				Long curSernum=Long.valueOf(rs.getString("cur_sernum"));
				String update = "update AC_CMIS_AUTOCODE set cur_sernum=" + (curSernum+ cycle) +" where atype='" + atype + "' and owner='" + owner + "'";
				stmt.executeUpdate(update) ;
				StringBuffer temp = new StringBuffer(curSernum+"") ;
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
			
		} catch (Exception e) {
			e.printStackTrace() ;
			try {
				conn.rollback() ;
			} catch (SQLException e1) {
				e1.printStackTrace() ;
				throw new Exception(e) ;
			}
			e.printStackTrace() ;
			throw new Exception(e) ;
		}
		return Long.valueOf(primaryKey.toString()) ;
	}
}
