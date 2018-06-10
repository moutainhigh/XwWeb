package app.creditapp.batch.mfs.batch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import app.base.PUBParm;
import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.mfs.util.MessageUtil;
import app.creditapp.batch.prjbatch.JavaBatch;
import app.creditapp.batch.util.DBUtil;

/**
 * 批量结束
 * 
 */
public class EndBatch extends JavaBatch {

	public EndBatch(String batchDate) {
		super(batchDate);
	}

	@Override
	public void doBatch(BatchNode batchNode) throws Exception {
		nodeNo = batchNode.getNode_no();
		nodeName = batchNode.getNode_name();
		try {
			// 更新系统状态为批量中
			String sql = "UPDATE SYS_GLOBAL SET SYS_STS = '01',BAT_DATE=SYS_DATE WHERE GLO_NO = '0000000000'";
			this.updateWithLog("批量结束", sql, null);

			logInfo = "任务执行成功";
			this.updateNoteSts(nodeNo, PUBParm.NODE_STATUS_4, batchDate);// 节点执行成功
			logger.info("节点号为:" + nodeNo + "的任务执行成功");
			String[] mail = getMail();
			// 发邮件
			// zlc 20170913 注释掉暂时不用的步骤
//			String msg = batchDate + "日批量程序成功,时间：" + new Date().toString() + "\n";
//			msg += getBatchInfo(batchDate);
//			new MessageUtil().sendMail(mail[1], batchDate + "日批量程序成功", msg);
		} catch (Exception e) {
			logger.error("---------节点号为:" + nodeNo + "的任务执行失败--------\n" + e.getMessage());
			DBUtil.rollback(conn);
			logInfo = e.getMessage().length() > 3000 ? e.getMessage().substring(0, 2999) : e.getMessage();
			this.updateNoteSts(nodeNo, PUBParm.NODE_STATUS_3, batchDate);// 节点执行失败
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			this.insertBatchLog();// 插入日志信息
		}
	}

	private String[] getMail() throws Exception {
		String[] mail = new String[2];
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("SELECT ADD_NAME, NVL(PHONE_NO1, PHONE_NO2), EMAIL FROM PUB_ADDR WHERE ADD_NO = '0000000000'");
			if (rs.next()) {
				String name = rs.getString(1);
				mail[0] = name + "#" + rs.getString(2);// 手机号
				mail[1] = name + "#" + rs.getString(3);// 邮箱
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return mail;
	}

	private String getBatchInfo(String batchDate) throws Exception {
		StringBuffer sb = new StringBuffer();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("SELECT NODE_NO, NODE_NAME, NODE_STATUS FROM BATCH_EXE WHERE NODE_STATUS != '4' AND BATCH_DATE = '"
							+ batchDate + "'");
			while (rs.next()) {
				sb.append("节点[" + rs.getString(1) + "--" + rs.getString(2) + "]");
				if ("1".equals(rs.getString(3))) {
					sb.append("未执行\n");
				} else if ("2".equals(rs.getString(3))) {
					sb.append("执行中\n");
				} else if ("3".equals(rs.getString(3))) {
					sb.append("执行失败\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return sb.toString();
	}

}
