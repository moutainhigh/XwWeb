package app.base;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import app.creditapp.trade.Util.AppContext;
/**
 */
public class SourceTemplate {
	private static ApplicationContext context = null;
	public static BeanFactory getSpringContextInstance() {
//		if (context == null) {
//			throw new RuntimeException("could not find spring context, please initialize and set context.");
//		}
		if (context == null) {
			context=AppContext.getInstance().getAppContext();
		}
		return (BeanFactory) context;
	}
	
	public static Connection getConn() throws Exception {
		Connection conn = null;
//		BasicDataSource bs = getSpringContextInstance().getBean("dataSource", BasicDataSource.class);
		DataSource ds = getDataSource();
		conn = ds.getConnection();
		return conn;
	}
	
	public static DataSource getDataSource() throws Exception {
		// BasicDataSource bs = getSpringContextInstance().getBean("dataSource", BasicDataSource.class);
//		Context c = new InitialContext();    
//		DataSource ds = (DataSource)c.lookup("java:comp/env/cmsdata"); 
		System.out.println("111111111");
		DataSource ds = (DataSource) SourceTemplate.getSpringContextInstance().getBean("dataSource");
		return ds;
	}
	
	//仅CMS服务使用
	public static BeanFactory getCmsSpringContextInstance() {
		if (context == null) {
			context=AppContext.getInstance().getAppContext();
		}
		return (BeanFactory) context;
	}
	
	public static ApplicationContext getContext() {
		return context;
	}
	public static ApplicationContext getAc() {
		return context;
	}
	public static void setContext(ApplicationContext context) {
		if( SourceTemplate.context == null ) {
			SourceTemplate.context = context;
		}
	}
	
	public static void main(String [] args){
		try{
		DataSource dd = getDataSource();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
