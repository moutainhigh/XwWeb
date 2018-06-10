package accounting.batch;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import accounting.biz.pub.ParmBiz;
import accounting.plat.PUBConstant;
import accounting.plat.core.init.ConnectionPool;

public abstract class Batch {

	private Connection connection;
	private Logger logger;
	private String className;
	private String sql;
	private String txDt;

	protected Batch() {
		// initLogger();
		try {
			this.connection = this.getNewConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		className = this.getClass().getName();
	}

	/**
	 * 获取当前日期
	 * 
	 * @parm connection 数据库连接
	 * @return String 当前交易日期
	 * @throws Exception
	 */
	public String getTxDt(Connection connection) throws Exception {
		if (this.txDt != null) {
			return this.txDt;
		}
		Statement selectAcComSysParmSt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		// log("select sys_dt from AC_COM_SYS_PARM");
		ResultSet selectAcComSysParmRs = selectAcComSysParmSt.executeQuery("select sys_dt from AC_COM_SYS_PARM");
		if (selectAcComSysParmRs.next()) {
			this.txDt = selectAcComSysParmRs.getString(1);
		}
		selectAcComSysParmRs.close();
		selectAcComSysParmSt.close();
		return this.txDt;
	}

	/**
	 * 获取当前日期
	 * 
	 * @parm connection 数据库连接
	 * @return String 当前交易日期
	 * @throws Exception
	 */
	public String getTxTime(Connection connection) throws Exception {
		if (this.txDt != null) {
			return this.txDt;
		}
		Statement selectAcComSysParmSt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		// log("select sys_dt from AC_COM_SYS_PARM");
		ResultSet selectAcComSysParmRs = selectAcComSysParmSt.executeQuery("select sys_dt from AC_COM_SYS_PARM");
		if (selectAcComSysParmRs.next()) {
			this.txDt = selectAcComSysParmRs.getString(1);
		}
		selectAcComSysParmRs.close();
		selectAcComSysParmSt.close();
		return this.txDt;
	}
	protected String getClassName() {
		return className;
	}

	private void initLogger() {
		logger = Logger.getLogger("cmis.logger");
		Handler[] h = logger.getHandlers();
		if (h.length > 0) {
			return;
		}
		try {
			FileHandler fileHandler = new FileHandler("./src/accounting/accounting/batch/log/"
					+ PUBConstant.CUR_PRCS_DT + "_log.log", 10000000, 20, true);
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setFilter(new Filter() {
				public boolean isLoggable(LogRecord record) {
					return record.getLevel().equals(Level.INFO);
				}
			});
			logger.addHandler(fileHandler);

			FileHandler errorFileHandler = new FileHandler("./src/accounting/accounting/batch/log/"
					+ PUBConstant.CUR_PRCS_DT + "_error.log", 10000000, 20, true);
			errorFileHandler.setLevel(Level.ALL);
			errorFileHandler.setFormatter(new SimpleFormatter());
			errorFileHandler.setFilter(new Filter() {
				public boolean isLoggable(LogRecord record) {
					return record.getLevel().equals(Level.FINER);
				}
			});
			logger.addHandler(errorFileHandler);
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.ALL);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void log(String sql) {
//		 this.sql = sql;
//		 logger.logp(Level.INFO, className, "doBatch", sql);
	}

	protected void error(Exception e) {
		// logger.logp(Level.FINER, className, "doBatch", sql, e);
		try {
			Statement stmt = connection.createStatement();
			String rsSql = "insert into ac_batch_err_log values(ac_batch_err_log_SEQ.Nextval,'" + className
					+ ".doBatch" + "','" + e.toString() + "','" + txDt + "','" + ParmBiz.getOracleTxTime(connection)
					+ "')";
			ResultSet insertRs = stmt.executeQuery(rsSql);

			insertRs.close();
			stmt.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected Connection getNewConnection() throws Exception {
		connection = ConnectionPool.getInstance().getConnection();
		connection.setAutoCommit(false);
		return connection;
	}

	protected Connection getConnection() throws Exception {
		return this.connection;
	}

	public abstract boolean doBatch(String startOrg, String endOrg) throws Exception;

}
