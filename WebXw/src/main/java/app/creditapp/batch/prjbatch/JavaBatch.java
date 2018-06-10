package app.creditapp.batch.prjbatch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.util.DBUtil;

public abstract class JavaBatch extends Batch {

	protected int startNum;

	/**
	 * @param batchDate
	 * @throws Exception
	 */
	public JavaBatch(String batchDate) {
		super(batchDate);
	}

	/**
	 * 更新Java节点小步骤 
	 * 
	 * @param conn
	 * @param nodeNo
	 * @param step
	 */
	protected void updateNodeStep(String node_no) throws Exception {
		PreparedStatement ps = null;
		String sql = "UPDATE BATCH_EXE SET ARRIVE_STEP=? WHERE NODE_NO=? AND BATCH_DATE=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startNum++);
			ps.setString(2, node_no);
			ps.setString(3, batchDate);
			ps.executeUpdate();
			ps.close();
			DBUtil.commit(conn);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new Exception(e);
		}finally{
			DBUtil.close(ps);
		}
	}

	/**
	 * 获取Java节点小步骤
	 * 
	 * @param conn
	 * @param nodeNo
	 * @param step
	 */
	protected int getNodeStep(String node_no) throws Exception {
		int step = 0;
		try {
			String sql = "SELECT ARRIVE_STEP+1 FROM BATCH_EXE WHERE NODE_NO=? AND BATCH_DATE=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node_no);
			ps.setString(2, batchDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				step = rs.getInt(1);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new Exception(e);
		}
		return step;
	}

	public abstract void doBatch(BatchNode batchNode) throws Exception;
}
