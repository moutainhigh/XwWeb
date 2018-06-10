package accounting.plat.core.init;

import java.sql.Connection;

import accounting.plat.core.OperationFactory;
import app.util.DBUtils;


/**
 * 平台初始化配置信息处理类
 * 
 */
public class BusinessInitializer {
	 public void initialize()throws Exception {
		//初始化
		 OperationFactory.init();
		 Connection connection = DBUtils.getConn();
	  
	   SetSysInfo.init(connection);
	   connection.close();
	 }
	 public void initForOpMain() throws Exception {
		//初始化
		 OperationFactory.init();
		 Connection connection = null;
		 try {
			 connection = DBUtils.getConn();
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		 SetSysInfo.init(connection);
		   connection.close();
	 }
		
}
