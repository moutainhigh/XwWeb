package app.creditapp.batch.prjbatch;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import app.base.PUBParm;
import app.creditapp.batch.entity.BatchLog;
import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.util.DBUtil;
import app.creditapp.batch.util.DateUtil;
import app.util.DBUtils;

/**
 * 节点执行存储过程
 * 
 * 
 */
public class ProcedureBatch extends Batch {
	/**
	 * @param batchDate
	 */
	public ProcedureBatch(String batchDate) {
		super(batchDate);
	}

	public void doBatch(BatchNode batchNode, String procedure_name) throws Exception {
		nodeNo = batchNode.getNode_no();
		nodeName = batchNode.getNode_name();
		try {
			CallableStatement cs = conn.prepareCall("{call " + procedure_name
					+ "()}");
			cs.execute();
			cs.close();
			logInfo = "任务执行成功";
			this.updateNoteSts(nodeNo,PUBParm.NODE_STATUS_4,batchDate);// 节点执行成功
			logger.info("节点号为:" + nodeNo + "的任务执行成功");
		} catch (Exception e) {
			logger.error("---------节点号为:" + nodeNo + "的任务执行失败--------\n"
					+ e.getMessage());
			DBUtil.rollback(conn);
			logInfo = e.getMessage().length() > 3000 ? e.getMessage()
					.substring(0, 2999) : e.getMessage();
			this.updateNoteSts(nodeNo,PUBParm.NODE_STATUS_3,batchDate);// 节点执行失败
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			this.insertBatchLog();// 插入日志信息
		}

	}
}
