package accounting.batch.run;

import java.io.File;
import java.sql.Connection;

import org.apache.log4j.Logger;

import accounting.batch.ChangeSysDateBatch;
import accounting.batch.DealAftRepyClearBatch;
import accounting.batch.LoanAftCompstBatch;
import accounting.batch.LoanAftRebuyBatch;
import accounting.batch.LoanExpandBatch;
import accounting.batch.LoanIntstSettlementBatch;
import accounting.batch.LoanRepayBatch;
import accounting.batch.LoanRepayLoAcLmAtpyBatch;
import accounting.batch.LoanStsTransferBatch;
import accounting.plat.PUBConstant;
import accounting.plat.core.OperationFactory;
import accounting.plat.core.init.SetSysInfo;
import app.util.DBUtils;

public class Main {
	private final Logger logger = Logger.getLogger(Main.class);
	public void acBatch() throws Exception {
		//初始化批量环境
		initialize();
		String txDt = PUBConstant.CUR_PRCS_DT;
		
		logger.info("营业日期["+txDt+"] 核算批量开始执行");

		logger.info("+-------oOOo---(BITIC)---oOOo-------+   ");
		logger.info("	|　　　 　　　　　　　　　　　　　　　|   ");
		logger.info("	|　    工 大 金 融 消费 信 贷 核 算 批 量      |   ");
		logger.info("	|　　　　　　　　　　　　　　　　 　　|   ");
		logger.info("+---------------------Oooo----------+   ");
		System.out.println("当前日期====" + txDt);
		
			boolean c = false;
			
			logger.info("营业日期["+txDt+"] 核算批量[贷款批扣]开始执行");
			LoanRepayBatch lrlo = new LoanRepayBatch();
			c = lrlo.doBatch(null, null);
			if (!c) {
				logger.info("营业日期["+txDt+"] 核算批量[贷款批扣]执行失败");
				throw new Exception("LoanRepayBatch");
			}
			System.out.println("贷款批扣 完成is OK!");
			logger.info("营业日期["+txDt+"] 核算批量[贷款批扣]执行成功");
			
			/**
			 * 形态转移
			 */
			logger.info("营业日期["+txDt+"] 核算批量[形态转移]开始执行");
			LoanStsTransferBatch lt = new LoanStsTransferBatch();
			c = lt.doBatch("1", "2");
			if (!c) {
				logger.info("营业日期["+txDt+"] 核算批量[形态转移]执行失败");
				throw new Exception("LoanStsTransferBatch");
			}
			System.out.println("形态转移 is OK!");
			logger.info("营业日期["+txDt+"] 核算批量[形态转移]执行成功");
			
			
			/**
			 * 核算系统换日
			 */
			logger.info("营业日期["+txDt+"] 核算批量[核算系统换日]开始执行");
			ChangeSysDateBatch cs = new ChangeSysDateBatch();
			c = cs.doBatch("1", "2");
			if (!c) {
				logger.info("营业日期["+txDt+"] 核算批量[核算系统换日]执行失败");
				throw new Exception("ChangeSysDateBatch");
			}
			System.out.println("核算系统换日 is OK!");
			logger.info("营业日期["+txDt+"] 核算批量[核算系统换日]执行成功");
			
			/**
			 * 展期
			 */
			logger.info("营业日期["+txDt+"] 核算批量[展期处理]开始执行");
			LoanExpandBatch leb = new LoanExpandBatch();
			c = leb.doBatch("1", "2");
			if (!c) {
				logger.info("营业日期["+txDt+"] 核算批量[展期处理]执行失败");
				throw new Exception("LoanExpandBatch");
			}
			System.out.println("展期 is OK!");
			logger.info("营业日期["+txDt+"] 核算批量[展期处理]执行成功");
			//txDt = TimeUtil.ADD_DAY(txDt, 1);

			/**
			 * 结息
			 */
//			logger.info("营业日期["+txDt+"] 核算批量[结息]开始执行");
//			LoanIntstSettlementBatch ls = new LoanIntstSettlementBatch();
//			c = ls.doBatch("1", "2");
//			if (!c) {
//				logger.info("营业日期["+txDt+"] 核算批量[结息]执行失败");
//				throw new Exception("LoanIntstSettlementBatch");
//			}
//			System.out.println("结息 is OK!");
//			logger.info("营业日期["+txDt+"] 核算批量[结息]执行成功");
			
			/**
			 * 贷款批扣数据准备
			 */
			logger.info("营业日期["+txDt+"] 核算批量[贷款批扣数据准备]开始执行");
			LoanRepayLoAcLmAtpyBatch lrbLo2 = new LoanRepayLoAcLmAtpyBatch();
			c = lrbLo2.doBatch(null, null);
			if (!c) {
				logger.info("营业日期["+txDt+"] 核算批量[贷款批扣数据准备]执行失败");
				throw new Exception("LoanRepayAcLmAtpyBatch");
			}
			System.out.println("贷款批扣 数据准备 is OK!");
			logger.info("营业日期["+txDt+"] 核算批量[贷款批扣数据准备]执行成功");
			
			logger.info("+-------oOOo---(BITIC)---oOOo-------+   ");
			logger.info("	|　　　 　　　　　　　　　　　　　　　|   ");
			logger.info("	|　北  京 信 托 消 费 信 贷 核 算 批 量  成 功　  |   ");
			logger.info("	|　　　　　　　　　　　　　　　　 　　|   ");
			logger.info("+---------------------Oooo----------+   ");
			
		}
	//}

	public static void main(String[] args) throws Exception {

		/**
		 * 系统初始化
		 */
		// cleanFiles("E:\\batch");
		// CreateLoanDownFile.cleanFiles("E:\\batch");
		// BusinessInitializer buz = new BusinessInitializer();
		// Main.initialize();
		
		new Main().acBatch();
		System.out.println("核算批处理完成!");
	}

	/**
	 * 删除文件夹中所有文件
	 * 
	 * @param path
	 *            文件夹绝对路径
	 */
	public void cleanFiles(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				f.delete();
			}
		}

	}

	public static void initialize() throws Exception {
		// 初始化
		OperationFactory.init();
		Connection connection = null;
		try {
			connection = DBUtils.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SetSysInfo.init(connection);
		connection.close();
	}

}
