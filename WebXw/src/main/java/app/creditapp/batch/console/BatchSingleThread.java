package app.creditapp.batch.console;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.log4j.Logger;

import app.base.PUBParm;
import app.base.ServiceException;
import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.prjbatch.Batch;
import app.creditapp.batch.prjbatch.ProcedureBatch;
import app.creditapp.batch.prjbatch.ShellBatch;
import app.creditapp.batch.prjbatch.SqlBatch;
import app.creditapp.batch.util.CheckDateKind;
import app.creditapp.batch.util.DBUtil;
import app.creditapp.batch.veiw.BatchMessage;

/**
 * 执行节点的线程调用
 * 
 * 
 */
public class BatchSingleThread implements Runnable {
	private final Logger logger = Logger.getLogger(BatchSingleThread.class);
	private BatchNode batchNode;
	private Map map;
	private String batchDate;

	public BatchSingleThread(String batchDate, BatchNode batchNode, Map map) {
		this.batchNode = batchNode;
		this.map = map;
		this.batchDate = batchDate;
	}

	public void run() {
		runPreTask();
	}

	private void runPreTask() {
		String message = runRule();
		if ("no".equals(message)) {
			BatchMessage.getInstance().append("节点号为:" + batchNode.getNode_no() + "任务的依赖任务没有成功运行，不可运行此节点任务").append("<br/>");
			logger.info("节点号为:" + batchNode.getNode_no() + "任务的依赖任务没有成功运行，不可运行此节点任务");
		}else if (!"success".equals(message)&&!"pass".equals(message)) {
			BatchMessage.getInstance().append("节点号为:" + batchNode.getNode_no() + "的任务运行失败，错误信息为："+ message).append("<br/>");
			logger.info("节点号为:" + batchNode.getNode_no() + "的任务运行失败，错误信息为："+ message);
		} else if("success".equals(message)){
			logger.info("节点号为:" + batchNode.getNode_no() + "的任务运行成 "+ message);
		}

	}

	private String runRule() {
		BatchRunner runner = new BatchRunner(this.batchDate);
		Batch batch = new Batch("");
		String nodeNo = batchNode.getNode_no();
		try {
			if(PUBParm.YES_NO_N.equals(batchNode.getUse_sts())){//判断节点是否启用，不启用，直接把节点状态更新为成功，跳过此节点
				batch.updateNoteSts(nodeNo, PUBParm.NODE_STATUS_4,batchDate);
				BatchMessage.getInstance().append(nodeNo + "节点未启用，跳过此节点!").append("<br/>");
				logger.info(nodeNo + "节点未启用，跳过此节点!");
				return "success";
			}
			
			boolean flag = CheckDateKind.checkKind(batchNode.getScheme_time_type(),batchNode.getScheme_time_detail(),batchDate);
			if(!flag){//不满足执行频率条件
				batch.updateNoteSts(nodeNo, PUBParm.NODE_STATUS_4,batchDate);
				BatchMessage.getInstance().append(nodeNo + "节点不满足执行频率条件，跳过此节点!");
				logger.info(nodeNo + "节点不满足执行频率条件，跳过此节点!");
				return "success";
			}
	
			// 在一次执行中用map记录节点是否执行过（防止同一个节点多次被执行）
			String nodePerSts = (String) map.get(nodeNo);
			if ("1".equals(nodePerSts)) {// 1表示执行过一次
				return "pass";//执行过一次
			}
			
			boolean preTaskState = runner.getFatherState(nodeNo);// 父节是否都执行成功
			if (!preTaskState) {
				return "no";//依赖任务没有成功运行
			}
			
			String nodeSts = runner.getTaskState(nodeNo);// 节点执行状态
			if (nodeSts.equals(PUBParm.NODE_STATUS_2)) {//执行中
				return "此任务正在运行中";
			}
	
			if (nodeSts.equals(PUBParm.NODE_STATUS_4)) {//执行成功
				return "success";
			}
			
			// 更新节点状态为执行中
			batch.updateNoteSts(nodeNo, PUBParm.NODE_STATUS_2,batchDate);
			
			// 在一次执行中用map记录节点是否执行过（防止同一个节点多次被执行）
			map.put(batchNode.getNode_no(), "1");// 1表示执行过一次
			
			// 判断节点类型
			switch (Integer.parseInt(batchNode.getScheme_no())) {
			// java
			case 1:
				Class clazz = Class.forName(batchNode.getScheme_info());
				Constructor constructor = clazz.getConstructor(String.class);// 包名.类名
				Method method = clazz.getMethod("doBatch",BatchNode.class);// 方法名，和参数的类对象
				method.invoke(constructor.newInstance(batchDate), batchNode);// 类的实例，和参数
				break;
			// Shell
			case 2:
				ShellBatch shellBatch = new ShellBatch(batchDate);
				shellBatch.doBatch(batchNode, batchNode.getScheme_info());
				break;

			// SQL
			case 3:
				SqlBatch sqlBatch = new SqlBatch(batchDate);
				sqlBatch.doBatch(batchNode);
				break;

			// 存储过程
			case 4:
				ProcedureBatch procedureBatch = new ProcedureBatch(batchDate);
				procedureBatch.doBatch(batchNode, batchNode.getScheme_info());
				break;
			}
			return "success";
		} catch (Exception e) {
			// 更新节点状态为执行失败
			batch.updateNoteSts(nodeNo, PUBParm.NODE_STATUS_3,batchDate);
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/**
	 * 获取节点启用标志
	 */
	public String getUseSts(String node_no) throws ServiceException {
		String sts = "";
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "SELECT USE_STS FROM BATCH_NODE WHERE NODE_NO = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, node_no);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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

}
