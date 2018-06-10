package app.creditapp.batch.console;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import app.base.PUBParm;
import app.base.ServiceException;
import app.creditapp.batch.entity.BatchExe;
import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.entity.BatchStep;
import app.creditapp.batch.util.DBUtil;
import app.creditapp.batch.util.DateUtil;
import app.creditapp.batch.veiw.BatchMessage;
import app.util.ThreadPoolManager;

public class BatchRunner {
	private final Logger logger = Logger.getLogger(BatchRunner.class);
	private static Map<String,String> map;
	private String batchDate;
	/**
	 * 节点执行逻辑判断
	 * 
	 * 
	 */
	//private static BatchRunner runner = new BatchRunner();

	public BatchRunner() {
		try {
			batchDate = DateUtil.getBatchDate();
			map = new HashMap<String,String>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BatchRunner(String batchDate) {
		try {
			this.batchDate = batchDate;
			map = new HashMap<String,String>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行子节点
	 */
	public void callNextRulesStart(String node_no, Map<String,String> map) {
		try {
			List<BatchNode> children = this.listNodes(node_no);
			if (children.size() > 0) {
				BatchMessage.getInstance().append("父节点：" + node_no).append("<br/>");
				logger.info("父节点：" + node_no);
				for (BatchNode batchNode : children) {
					String nodeSts = this.getTaskState(batchNode.getNode_no());
					if (PUBParm.NODE_STATUS_4.equals(nodeSts)) {// 节点执行成功
						BatchMessage.getInstance().append("节点" + batchNode.getNode_no() + "已经执行成功");
						logger.info("节点" + batchNode.getNode_no() + "已经执行成功");
						callNextRulesStart(batchNode.getNode_no(), map);
					} else {
						ThreadPoolManager.getPubInstance().exec(new BatchThread(batchDate, batchNode, map));
						//Thread t = new Thread(new BatchThread(batchDate, batchNode, map));
						//t.start();
					}
				}
			} else {
				BatchMessage.getInstance().append("***********************批量执行成功！***********************");
				logger.info("***********************批量执行成功！***********************");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 更新批量日期
	 */
	public void updateBatchDate() {
		Connection conn = DBUtil.getConnection();
		try {

			PreparedStatement ps = null;
			String sql = "";
			boolean flag = true;
			sql = "SELECT NODE_NO, NODE_STATUS FROM BATCH_EXE WHERE BATCH_DATE=? AND NODE_STATUS!=4";
			ps = conn.prepareStatement(sql);
			ps.setString(1, batchDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flag = false;
			}
			if (flag == true) {
				sql = "UPDATE sys_global SET BAT_DATE=sys_date";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
			ps.close();
			rs.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	/**
	 * 根据节点号获得节点信息
	 */
	public BatchNode getById(String node_no) {
		BatchNode bn = new BatchNode();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT NODE_NO,NODE_NAME,SCHEME_NO,SCHEME_INFO,FILLER,SCHEME_TIME_TYPE,SCHEME_TIME_DETAIL,USE_STS,ERR_FLAG "
					+ " FROM BATCH_NODE WHERE NODE_NO = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node_no);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bn.setNode_no(rs.getString(1));
				bn.setNode_name(rs.getString(2));
				bn.setScheme_no(rs.getString(3));
				bn.setScheme_info(rs.getString(4));
				bn.setFiller(rs.getString(5));
				bn.setScheme_time_type(rs.getString(6));
				bn.setScheme_time_detail(rs.getString(7));
				bn.setUse_sts(rs.getString(8));
				bn.setErr_flag(rs.getString(9));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return bn;
	}

	/**
	 * 初始化批量执行表
	 */
	public void initBatchExe() {
		Connection conn = DBUtil.getConnection();
		try {
			// log("初始化批量执行表");
			boolean flag = true;
			PreparedStatement ps = null;
			String sql = "";
			sql = "SELECT NODE_NO FROM BATCH_EXE WHERE BATCH_DATE=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, batchDate);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = false;
			}
			if (flag == true) {
				ps.close();
				sql = "INSERT INTO BATCH_EXE SELECT DISTINCT NODE_NO,NODE_NAME,'1'," + batchDate + ",0 FROM BATCH_STEP";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate(sql);
				conn.commit();
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	/**
	 * 根据父节点获得子节点
	 */
	private List<BatchNode> listNodes(String node_no) {
		List<BatchNode> list = new ArrayList<BatchNode>();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT NODE_NO,NODE_NAME,SCHEME_NO,SCHEME_INFO,SCHEME_TIME_TYPE,SCHEME_TIME_DETAIL,USE_STS FROM BATCH_NODE WHERE NODE_NO IN (SELECT NODE_NO FROM BATCH_STEP WHERE UP_NODE_NO = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node_no);
			ResultSet rs = ps.executeQuery();
			BatchNode batchNode = null;
			while (rs.next()) {
				batchNode = new BatchNode();
				batchNode.setNode_no(rs.getString(1));
				batchNode.setNode_name(rs.getString(2));
				batchNode.setScheme_no(rs.getString(3));
				batchNode.setScheme_info(rs.getString(4));
				batchNode.setScheme_time_type(rs.getString(5));
				batchNode.setScheme_time_detail(rs.getString(6));
				batchNode.setUse_sts(rs.getString(7));
				list.add(batchNode);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return list;
	}

	/**
	 * 根据节点号返回节点状态
	 */
	public String getTaskState(String node_no) throws ServiceException {
		String sts = "";
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT NODE_STATUS FROM BATCH_EXE WHERE NODE_NO = ? AND BATCH_DATE=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node_no);
			ps.setString(2, batchDate);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sts = rs.getString(1);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtil.close(conn);
		}
		return sts;
	}

	/**
	 * 查询节点执行步骤
	 */
	public List<BatchStep> getNodeStepList() throws ServiceException {
		List<BatchStep> list = new ArrayList<BatchStep>();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT NODE_NO,NODE_NAME,UP_NODE_NO,UP_NODE_NAME FROM BATCH_STEP";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			BatchStep batchStep = null;
			while (rs.next()) {
				batchStep = new BatchStep();
				batchStep.setNode_no(rs.getString(1));
				batchStep.setNode_name(rs.getString(2));
				batchStep.setUp_node_no(rs.getString(3));
				batchStep.setUp_node_name(rs.getString(4));
				list.add(batchStep);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtil.close(conn);
		}
		return list;
	}

	/**
	 * 查询节点执行状态集合
	 */
	public List<BatchExe> getBatchExeList(String batchDate) throws ServiceException {
		List<BatchExe> list = new ArrayList<BatchExe>();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT B.NODE_NO,B.NODE_NAME,B.NODE_STATUS,B.BATCH_DATE,B.ARRIVE_STEP FROM BATCH_EXE B WHERE B.BATCH_DATE=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, batchDate);
			ResultSet rs = ps.executeQuery();
			BatchExe batchExe = null;
			while (rs.next()) {
				batchExe = new BatchExe();
				batchExe.setNode_no(rs.getString(1));
				batchExe.setNode_name(rs.getString(2));
				batchExe.setNode_status(rs.getString(3));
				batchExe.setBatch_date(rs.getString(4));
				batchExe.setArrive_step(rs.getInt(5));
				list.add(batchExe);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtil.close(conn);
		}
		return list;
	}

	/**
	 * 子节点的所有父节点是否都执行成功
	 * 
	 * @throws Exception
	 */
	public boolean getFatherState(String node_no) throws Exception {
		boolean state = true;
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT A.NODE_NO,A.NODE_STATUS,B.ERR_FLAG FROM BATCH_EXE A,BATCH_NODE B WHERE A.NODE_NO=B.NODE_NO AND A.NODE_NO IN (SELECT UP_NODE_NO FROM BATCH_STEP WHERE NODE_NO = ? ) AND A.BATCH_DATE= ? AND A.NODE_STATUS != '4'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node_no);
			ps.setString(2, batchDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//执行失败 并 可忽略
				if("3".equals(rs.getString(2)) && "2".equals(rs.getString(3))){
					continue;
				}else{
					state = false;
					break;
				}
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			DBUtil.close(conn);
		}
		return state;
	}

	/**
	 * 批量执行节点入口
	 * 
	 * @param nodeNo
	 */
	public void startBatch(String nodeNo) {
		this.initBatchExe();// 初始化当天批量步骤
		BatchNode batchNode = this.getById(nodeNo);
		String sts = this.getTaskState(batchNode.getNode_no());
		if (PUBParm.NODE_STATUS_4.equals(sts)) {
			logger.info("节点[" + batchNode.getNode_no() + "]已经执行成功");
			//子节点
			List<BatchNode> children = this.listNodes(nodeNo);
			if (children.size() > 0) {
				this.callNextRulesStart(batchNode.getNode_no(), map);
			} else {
				BatchMessage.getInstance().append("*******批量执行成功！*******").append(nodeNo).append("<br/>");
				logger.info("***********批量执行成功！************"+nodeNo);
			}
		} else {
			//使用线程池
			ThreadPoolManager.getPubInstance().exec(new BatchThread(batchDate, batchNode, map));
			//Thread thread = new Thread(new BatchThread(batchDate, batchNode, map));
			//thread.start();
		}
	}

	/**
	 * 单步执行
	 * @param batchDate
	 * @param nodeNo
	 * @return
	 */
	public String runnerSingle(String batchDate,String nodeNo){
		BatchNode batchNode = this.getById(nodeNo);
		String sts = this.getTaskState(batchNode.getNode_no());
		if (PUBParm.NODE_STATUS_4.equals(sts) || PUBParm.NODE_STATUS_2.equals(sts)) {
			return "0";
		}
		ThreadPoolManager.getPubInstance().exec(new BatchSingleThread(batchDate, batchNode, map));
		return "1";
	}
	
	public static void main(String[] args) {
		BatchRunner br = new BatchRunner();
		if (args != null && args.length > 0) {
			br.startBatch(args[0]);
		} else {
			br.startBatch("batch001");
		}
	}
}
