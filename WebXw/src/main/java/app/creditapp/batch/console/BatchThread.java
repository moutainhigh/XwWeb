package app.creditapp.batch.console;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import app.base.PUBParm;
import app.base.ServiceException;
import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.mfs.util.MessageUtil;
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
public class BatchThread implements Runnable {
	private final Logger logger = Logger.getLogger(BatchThread.class);
	private BatchNode batchNode;
	private Map<String,String> map;
	private String batchDate;
	private String errorMsg="";
	
	public BatchThread(String batchDate, BatchNode batchNode, Map<String,String> map) {
		this.batchNode = batchNode;
		this.map = map;
		this.batchDate = batchDate;
	}

	public void run() {
		runPreTask();
	}

	private void runPreTask() {
		BatchRunner runner = new BatchRunner();
		String message = runRule();
		if ("no".equals(message)) {
			BatchMessage.getInstance().append("节点号为:" + batchNode.getNode_no() + "任务的依赖任务没有成功运行，不可运行此节点任务").append("<br/>");
			logger.info("节点号为:" + batchNode.getNode_no() + "任务的依赖任务没有成功运行，不可运行此节点任务");
		} else if("pass".equals(message)){
			BatchMessage.getInstance().append("节点号为:" + batchNode.getNode_no() + "的任务已执行："+ message).append("<br/>");
			logger.info("节点号为:" + batchNode.getNode_no() + "的任务已执行："+ message);
		} else if("running".equals(message)){
			BatchMessage.getInstance().append("节点号为:" + batchNode.getNode_no() + "的任务正在执行中："+ message).append("<br/>");
			logger.info("节点号为:" + batchNode.getNode_no() + "的任务正在执行中："+ message);
		} else if("success".equals(message)){
			logger.info("节点号为:" + batchNode.getNode_no() + "的任务执行成功："+ message);
			runner.callNextRulesStart(batchNode.getNode_no(),map);
		} else if("error".equals(message)){
			BatchMessage.getInstance().append("节点号为:" + batchNode.getNode_no() + "的任务运行失败，错误信息为："+ message).append("<br/>");
			logger.info("节点号为:" + batchNode.getNode_no() + "的任务运行失败，错误信息为："+ errorMsg);
			if("1".equals(batchNode.getErr_flag())){//如果执行失败，则停止批量，发送失败邮件
				//执行失败发邮件
				String[] mail = getMail();
				new MessageUtil().sendMail(mail[1], batchDate+"日批量执行失败",
					"时间:"+new Date().toString() + "--" +batchNode.getNode_no()+"--"+batchNode.getNode_name()+" 批量执行失败："+errorMsg);
			}else if("2".equals(batchNode.getErr_flag())){//如果出错忽略，则继续执行
				runner.callNextRulesStart(batchNode.getNode_no(),map);
			}
		}
	}

	private String runRule() {
		BatchRunner runner = new BatchRunner();
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
				return "running";
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
			errorMsg = e.getMessage();
			return "error";
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
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtil.close(conn);
		}
		return sts;
	}

	private String[] getMail()  {
		String[] mail = new String[2];
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT ADD_NAME, NVL(PHONE_NO1, PHONE_NO2), EMAIL FROM PUB_ADDR WHERE ADD_NO = '0000000000'");
			if (rs.next()) {
				String name = rs.getString(1);
				mail[0] = name+"#"+rs.getString(2);//手机号
				mail[1] = name+"#"+rs.getString(3);//邮箱
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
}
