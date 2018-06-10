package app.creditapp.batch.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static Properties pathProperties;

	static {
		pathProperties = new Properties();
		try {
			pathProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FTPBean getFTPBean() {
		FTPBean bean = new FTPBean();
		bean.setIp(pathProperties.getProperty("ftp.ip"));
		String portStr = pathProperties.getProperty("ftp.port");
		bean.setPort(Integer.parseInt(portStr));
		bean.setUserName(pathProperties.getProperty("ftp.userName"));
		bean.setPasswd(pathProperties.getProperty("ftp.passWd"));
		bean.setReomtePath(pathProperties.getProperty("ftp.remotePath"));
		bean.setLocalPath(pathProperties.getProperty("ftp.localPath"));
		return bean;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(pathProperties.getProperty("jdbc.driver"));
			String url = pathProperties.getProperty("jdbc.url");
			String user = pathProperties.getProperty("jdbc.username");
			String password = pathProperties.getProperty("jdbc.password");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
				st = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		try {
			if (conn != null) {
				conn.setAutoCommit(autoCommit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
