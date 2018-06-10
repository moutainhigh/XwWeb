package app.creditapp.batch.prjbatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import app.creditapp.batch.entity.BatchLog;
import app.creditapp.batch.util.DBUtil;

/**
 * 批量基准类
 * 
 */
public class Batch {
	public Logger logger = Logger.getLogger(this.getClass());
	protected BatchLog batchLog = new BatchLog();

	/** 系统日期 **/
	protected String sysDate;
	/** 数据库连接 **/
	protected Connection conn;
	/** 批量日期 **/
	protected String batchDate;

	/** 节点编号 **/
	protected String nodeNo;
	/** 节点名称 **/
	protected String nodeName;
	/** 节点运行开始时间 **/
	protected String begTime;
	/** 节点运行结束时间 **/
	protected String endTime;
	/** 节点状态 **/
	protected String nodeSts;

	protected String msg = "";
	protected String logInfo;// 日志信息

	public Batch(String batchDate) {
		this.conn = DBUtil.getConnection();
		this.batchDate = batchDate;
		begTime = this.getSysTime();
	}

	/**
	 * 更新指定日期批量运行节点的状态
	 * 
	 * @param nodeNo
	 *            节点编号
	 * @param node_sts
	 *            1-未执行 2-执行中 3-执行失败 4-执行成功
	 * @param batchDate
	 *            批量运行日期
	 */
	public void updateNoteSts(String nodeNo, String node_sts, String batchDate) {
		String sql = "UPDATE BATCH_EXE SET NODE_STATUS=? WHERE NODE_NO=? and BATCH_DATE=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, node_sts);
			ps.setString(2, nodeNo);
			ps.setString(3, batchDate);
			ps.executeUpdate();
			ps.close();
			DBUtil.commit(conn);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
		}
	}

	/**
	 * 根据节点插入批量日志
	 * 
	 * @throws Exception
	 */
	public void insertBatchLog() {
		String sql = "INSERT INTO BATCH_LOG (BATCH_DATE, NODE_NO, NODE_NAME, LOG_INFO, BEG_TIME, END_TIME) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, batchDate);
			ps.setString(2, nodeNo);
			ps.setString(3, nodeName);
			ps.setString(4, logInfo);
			ps.setString(5, begTime);
			ps.setString(6, this.getSysTime());
			ps.executeUpdate();
			DBUtil.commit(conn);
			logger.info(nodeNo + "节点插入批量日志成功");
		} catch (SQLException e) {
			logger.error(nodeNo + "节点插入批量日志失败");
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	/**
	 * 获取数据库时间
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public String getSysTime() {
		String tamp = "";
		String sql = "SELECT SYSDATE FROM DUAL";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tamp = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
		}
		return tamp;
	}

	/**
	 * 执行非查询SQL(不记录日志),如果参数params为null则无参数更新
	 * 
	 * @param sql
	 *            执行SQl
	 * @param params
	 *            SQl中的参数,数组中的序号+1 与参数位置匹配
	 * @return
	 * @throws Exception
	 */
	protected int execUpdate(String sql, Object[] params) throws Exception {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null && params.length >= 1) {
				for (int i = 0; i < params.length; i++) {
					if (params[i] instanceof Double) {
						ps.setDouble(i + 1, (Double) params[i]);
					} else if (params[i] instanceof Integer) {
						ps.setInt(i + 1, (Integer) params[i]);
					} else {
						ps.setString(i + 1, (String) params[i]);
					}
				}
			}
			result = ps.executeUpdate();
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
			logger.error(e.getMessage(),e);
			throw new Exception(e);
		} finally {
			DBUtil.close(ps);
		}
		return result;
	}

	/**
	 * 执行非查询SQL,如果参数params为null则无参数更新
	 * 
	 * @param msg
	 *            记录日志
	 * @param sql
	 *            执行SQl
	 * @param params
	 *            SQl中的参数,数组中的序号+1 与参数位置匹配
	 * @return
	 * @throws Exception
	 */
	protected int updateWithLog(String msg, String sql, Object[] params) throws Exception {
		long startTime = System.currentTimeMillis();
		long endTime = 0;
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null && params.length >= 1) {
				for (int i = 0; i < params.length; i++) {
					if (params[i] instanceof Double) {
						ps.setDouble(i + 1, (Double) params[i]);
					} else if (params[i] instanceof Integer) {
						ps.setInt(i + 1, (Integer) params[i]);
					} else {
						ps.setString(i + 1, (String) params[i]);
					}
				}
			}
			logger.info(sql);
			if (params != null && params.length != 0) {
				logger.info("参数: " + transParams(params));
			}
			result = ps.executeUpdate();
			endTime = System.currentTimeMillis();
			logger.info(msg + "执行结束，更新记录数:" + result);
			logger.info("执行时间：" + (endTime - startTime) + "ms");
			DBUtil.commit(conn);
		} catch (Exception e) {
			DBUtil.rollback(conn);
			throw new Exception(e);
		} finally {
			DBUtil.close(ps);
		}
		return result;
	}

	/**
	 * 格式化输出 例如数组 a b c 将输出 [a][b][c]
	 * 
	 * @param ao
	 *            数组参数
	 * @return
	 */
	private String transParams(Object[] ao) {
		if (ao == null || ao.length == 0) {
			return "[]";
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < ao.length; i++) {
			buf.append("[").append(ao[i]).append("] ");
		}
		return buf.toString();
	}

}
