package app.creditapp.batch.util;

/**
 * FTP实体封装-
 *
 */
public class FTPBean {
	private String ip;
	private int port;
	private String userName;
	private String passwd;
	private String localPath;
	private String reomtePath;

	public FTPBean() {
		port = 21;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip.trim();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName.trim();
	}

	public String getPasswd() {
		return passwd;
	}
//若 密码为空 那么 就之密码为"";
	public void setPasswd(String passwd) {
		if(null == passwd){
			this.passwd = "";
		} else {
			this.passwd  = passwd;
		}
			
		
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath.trim();
	}

	public String getReomtePath() {
		return reomtePath;
	}

	public void setReomtePath(String reomtePath) {
		this.reomtePath = reomtePath.trim();
	}
}
