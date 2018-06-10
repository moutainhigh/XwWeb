package accounting.plat.core.init;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.Vector;

import app.creditapp.batch.util.DBUtil;
import app.util.DBUtils;

public class ConnectionPool {
	private Vector<Connection> pool;
	private String url;
	private String user;
	private String password;
	private String driverName;
	private int poolSize = 1;
	private static ConnectionPool instance = null;

	private ConnectionPool() {
		// init();
	}

	private void init() {
		pool = new Vector<Connection>(poolSize);
		// readConfig();
		addConnection();
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() {
		/*
		 * if(pool.size()>0){ Connection conn=pool.get(0); pool.remove(conn);
		 * return conn; }else{ return null; }
		 */
		Connection conn = null;
		conn = DBUtil.getConnection();
		return conn;
	}

	/*
	 * public synchronized void release(Connection conn){ pool.add(conn); }
	 * public synchronized void closePool(){ for(int i=0;i<poolSize;i++){ try {
	 * pool.get(i).close(); } catch (SQLException e) { e.printStackTrace(); } }
	 * }
	 */
	private void readConfig() {
		// String path=System.getProperty("user.dir")+"/src/dbpool.properties";
		String path = Thread.currentThread().getContextClassLoader().getResource("accounting/dbpool.properties")
				.getPath();
		try {
			FileInputStream is = new FileInputStream(path);
			Properties props = new Properties();
			props.load(is);
			this.driverName = props.getProperty("driverName");
			this.url = props.getProperty("url");
			this.user = props.getProperty("user");
			this.password = props.getProperty("password");
			this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addConnection() {
		Connection conn = null;
		for (int i = 0; i < poolSize; i++) {
			new oracle.jdbc.driver.OracleDriver();
			conn = DBUtils.getConn();
			pool.add(conn);
		}

	}
}
