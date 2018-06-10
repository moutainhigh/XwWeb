package app.util.syslog.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.util.syslog.bo.SysExceptionBo;

public class SysLogger {
	private static SysExceptionBo sysExceptionBo;
	final static Logger logger = LoggerFactory.getLogger(SysLogger.class);
	
	public static void info(String logMsg){
		SysLogger.info(logMsg,null);
	}
	
	public static void info(String logMsg,Throwable tab){
		logger.info(logMsg);
		if(tab!=null) sysExceptionBo.insertNewExceptionLog(logMsg, tab);
	}
	
	public static void warn(String logMsg){
		SysLogger.warn(logMsg,null);
	}
	
	public static void warn(String logMsg,Throwable tab){
		logger.warn(logMsg);
		if(tab!=null) sysExceptionBo.insertNewExceptionLog(logMsg, tab);
	}
	
	public static void error(String logMsg){
		SysLogger.error(logMsg,null);
	}
	
	public static void error(String logMsg,Throwable tab){
		logger.error(logMsg);
		if(tab!=null) sysExceptionBo.insertNewExceptionLog(logMsg, tab);
	}

	public  void setSysExceptionBo(SysExceptionBo sysExceptionBo) {
		SysLogger.sysExceptionBo = sysExceptionBo;
	}
	
}
