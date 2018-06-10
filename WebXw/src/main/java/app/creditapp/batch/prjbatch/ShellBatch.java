package app.creditapp.batch.prjbatch;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

import app.base.PUBParm;
import app.creditapp.batch.entity.BatchNode;
import app.creditapp.batch.util.DBUtil;
import app.creditapp.batch.util.FilePathUtil;

/**
 * 节点执行shell
 * 
 * 
 */
public class ShellBatch extends Batch {
	/**
	 * @param batchDate
	 */
	public ShellBatch(String batchDate) {
		super(batchDate);
	}

	public void doBatch(BatchNode batchNode, String shellName) throws Exception {
		nodeNo = batchNode.getNode_no();
		nodeName = batchNode.getNode_name();
		InputStreamReader ir = null;
		LineNumberReader input = null;
		String shellPath = "";
		try {
			shellPath = FilePathUtil.getShellPath()+"/"+shellName.trim();
			System.out.println("shellPath="+shellPath);
			Process process = null;
			process = Runtime.getRuntime().exec("/bin/sh "+shellPath +" "+batchDate);
			ir = new InputStreamReader(process.getInputStream(),"UTF-8");
			input = new LineNumberReader(ir);
			int result = process.waitFor(); // Shell退出状态
			StringBuffer sbf = new StringBuffer();
			String line = "";
			while ((line = input.readLine()) != null) {
				sbf.append(line + "\r\n");
			}
			System.out.println(sbf.toString());
			if (result != 0) { // shell非正常结束
				this.updateNoteSts(nodeNo,PUBParm.NODE_STATUS_3,batchDate);// 节点执行失败
				System.out.println("shell非正常结束");
				if (result == 127) {
					logInfo = "未找到要运行的命令";
				} else if (result == 126) {
					logInfo = "找到了命令但无法执行";
				} else if (result > 128) {
					logInfo = "命令被系统强行结束";
				} else {
					logInfo = sbf.toString();
				}
				throw new Exception(logInfo);
			} else {
				this.updateNoteSts(nodeNo,PUBParm.NODE_STATUS_4,batchDate);// 节点执行成功
				logInfo = "任务执行成功";
				logger.info("节点号为:" + nodeNo + "的任务执行成功");
			}
		} catch (Exception e) {
			DBUtil.rollback(conn);
			logger.error("节点号为:" + nodeNo + "的SHELL:" + shellPath + "执行失败:\n"
					+ e.getMessage());
			logInfo = e.getMessage().length() > 3000 ? e.getMessage()
					.substring(0, 2999) : e.getMessage();
			this.updateNoteSts(nodeNo,PUBParm.NODE_STATUS_3,batchDate);// 节点执行失败
			throw new Exception(e);
		} finally {
			try {
				this.insertBatchLog();// 插入日志信息
				if(ir != null){
					ir.close();
				}
				if(input != null){
					input.close();
				}
			} catch (Exception e) {
				throw new Exception(e);
			}
		}

	}
}
